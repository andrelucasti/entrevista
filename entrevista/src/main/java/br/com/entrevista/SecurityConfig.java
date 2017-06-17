package br.com.entrevista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService dao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			
			.antMatchers("/aprovados.xhtml").hasAuthority("ENTREVISTADOR")
			.antMatchers("/naoaprovados.xhtml").hasAuthority("ENTREVISTADOR")
			.antMatchers("/entrevista.xhtml").hasAuthority("ENTREVISTADOR")
			.antMatchers("/entrevistados.xhtml").hasAuthority("ENTREVISTADOR")
			.antMatchers("/cadastro_entrevistado").permitAll()
			
			.anyRequest().authenticated().and().formLogin().loginPage("/login.xhtml").permitAll()
			.defaultSuccessUrl("/entrevistados.xhtml")
			.failureForwardUrl("/erro.xhtml")
			.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login.xhtml");
			
			

	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(dao);
	}

	
	
		
	
}
