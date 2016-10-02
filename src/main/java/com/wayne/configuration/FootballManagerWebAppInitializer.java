package com.wayne.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author wayne
 * @version 1.0
 */
public class FootballManagerWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		addRootContextLoaderListener(servletContext);
		addDispatcherServlet(servletContext);
		addUTF8CharacterEncodingFilter(servletContext);
	}

	/**
	 * RootContext 를 로딩하는 리스너를 등록한다
	 *
	 * @param servletContext 초기화할 ServletContext
	 */
	private void addRootContextLoaderListener(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootContextConfiguration.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
	}

	/**
	 * DispatcherServlet 을 추가한다
	 *
	 * @param servletContext 초기화할 ServletContext
	 */
	private void addDispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext servletApplicationContext = new AnnotationConfigWebApplicationContext();
		servletApplicationContext.register(ServletContextConfiguration.class);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletApplicationContext);

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("appServlet", dispatcherServlet);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

	/**
	 * UTF-8 CharacterEncodingFilter 를 추가한다
	 *
	 * @param servletContext 초기화할 ServletContext
	 */
	private void addUTF8CharacterEncodingFilter(ServletContext servletContext) {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		FilterRegistration.Dynamic filter = servletContext.addFilter("encoding", characterEncodingFilter);
		filter.addMappingForUrlPatterns(null, false, "/*");
	}
}
