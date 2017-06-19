package br.com.entrevista;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


/**
 * 
 * @author Andre
 * 
 * Toda requisição feita meu servlet container vai fazer com que passe pelo meu Filtro springSecurityFilterChain
 * que é responsável por toda segurança da minha aplicação.
 *
 */
@Configuration
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	
	
	//Se não estiver usando o spring como IoC/DI
	/*
	public SpringSecurityInitializer() {
		super(SecurityConfig.class);
	}
	*/

}
