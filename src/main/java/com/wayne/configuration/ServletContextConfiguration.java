package com.wayne.configuration;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author wayne
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.wayne.controller"})
public class ServletContextConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public HandlebarsViewResolver handlebarsViewResolver() {
		HandlebarsViewResolver handlebarsViewResolver = new HandlebarsViewResolver();
		handlebarsViewResolver.setPrefix("/WEB-INF/view/");
		handlebarsViewResolver.setSuffix(".hbs");
		return handlebarsViewResolver;
	}

	/**
	 * static resources path mapping
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}
