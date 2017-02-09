package com.wayne.configuration;

import com.wayne.configuration.datasource.LocalDataSourceConfiguration;
import com.wayne.configuration.datasource.TestDataSourceConfiguration;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

/**
 * @author wayne
 * @version 1.0
 */
@Configuration
@Import({TestDataSourceConfiguration.class, LocalDataSourceConfiguration.class})
@ComponentScan(basePackages = {"com.wayne.repository", "com.wayne.service"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.wayne.repository")
public class RootContextConfiguration {

	@Autowired private DataSource dataSource;
	@Autowired private Properties hibernateProperties;

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan("com.wayne.domain");
		entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		entityManagerFactoryBean.setJpaDialect(new HibernateJpaDialect());
		entityManagerFactoryBean.setJpaProperties(hibernateProperties);
		return entityManagerFactoryBean;
	}

	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		return jpaTransactionManager;
	}

}
