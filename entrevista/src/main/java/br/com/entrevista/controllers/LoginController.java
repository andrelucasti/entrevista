package br.com.entrevista.controllers;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@RequestScope
public class LoginController {
	
	
	
	
	
	public void login() throws ServletException, IOException{
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();  
		HttpServletRequest request = ((HttpServletRequest)context.getRequest());
				
		ServletResponse resposnse = ((ServletResponse)context.getResponse());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, resposnse);
		
		FacesContext.getCurrentInstance().responseComplete();
		
	}
	
	

}
