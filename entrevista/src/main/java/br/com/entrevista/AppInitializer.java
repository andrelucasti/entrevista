package br.com.entrevista;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class AppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext contex) throws ServletException {
		
		
		AnnotationConfigWebApplicationContext appCtx = new AnnotationConfigWebApplicationContext();
		
		//appCtx.scan("br.com.entrevista");
		appCtx.scan(AppInitializer.class.getPackage().getName());
		
		contex.addListener(new ContextLoaderListener(appCtx));
		contex.addListener(new RequestContextListener());
		
		
	}

}
