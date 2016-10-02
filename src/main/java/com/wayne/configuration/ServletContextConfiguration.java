package com.wayne.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author wayne
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.wayne.controller"})
public class ServletContextConfiguration extends WebMvcConfigurerAdapter {

}
