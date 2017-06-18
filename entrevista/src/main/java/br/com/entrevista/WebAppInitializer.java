package br.com.entrevista;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext appCtx = new AnnotationConfigWebApplicationContext();

		appCtx.scan("br.com.entrevista");
		//appCtx.scan(AppInitializer.class.getPackage().getName());

		servletContext.addListener(new ContextLoaderListener(appCtx));
		servletContext.addListener(new RequestContextListener());
	}
	
	


}
