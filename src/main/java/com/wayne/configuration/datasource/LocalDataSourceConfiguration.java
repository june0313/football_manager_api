package com.wayne.configuration.datasource;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

/**
 * @author wayne
 * @version 1.0
 */
@Configuration
@Profile("local")
public class LocalDataSourceConfiguration {

	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/football?serverTimezone=UTC");
		dataSource.setUsername("football");
		dataSource.setPassword("football");
		return dataSource;
	}

	@Bean
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", org.hibernate.dialect.MySQLDialect.class);
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		properties.put("hibernate.use_sql_comments", true);
		properties.put("hibernate.id.new_generator_mappings", true);
		properties.put("hibernate.hbm2ddl.auto", "create");
		return properties;
	}

}
