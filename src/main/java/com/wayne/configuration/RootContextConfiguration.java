package com.wayne.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author wayne
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.wayne.repository"})
public class RootContextConfiguration {

}
