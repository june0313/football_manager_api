package com.wayne.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * @author wayne
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.wayne.repository"})
public class RootContextConfiguration {

	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:test");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setDataSource(dataSource());
		return jpaTransactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan("com.wayne.domain");
		entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		entityManagerFactoryBean.setJpaProperties(hibernateProperties());
		return entityManagerFactoryBean;
	}

	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", org.hibernate.dialect.H2Dialect.class);
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		properties.put("hibernate.use_sql_comments", true);
		properties.put("hibernate.id.new_generator_mappings", true);
		properties.put("hibernate.hbm2ddl.auto", "create");
		return properties;
	}

}
