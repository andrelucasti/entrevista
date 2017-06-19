package br.com.entrevista;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * 
 * @author Andre
 * 
 * Implementação da  interface WebApplicationInitializer, que consegue conversar 
 * com o Spring através da classe {@link SpringServletContainerInitializer}, 
 * que implementa a interface {@link ServletContainerInitializer} 3.0
 * 
 */
public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		//Habilitando suporte a anotações do spring
		AnnotationConfigWebApplicationContext appCtx = new AnnotationConfigWebApplicationContext();

		appCtx.scan("br.com.entrevista");
		//appCtx.scan(AppInitializer.class.getPackage().getName());
		
		//Adicionando o listener do spring na qual vai ouvir através do appCtx todas as classes de configuração no meu projeto.
		servletContext.addListener(new ContextLoaderListener(appCtx));
		//Spring que é o responsavel pelos Beans, então estou pedindo para ouvir o suporte aos escopos do Spring.
		servletContext.addListener(new RequestContextListener());
	}
	
	


}
