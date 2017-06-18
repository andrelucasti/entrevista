package br.com.entrevista.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class ActiveIndexController {
	
	private String activeIndex;



	public String getActiveIndex() {
		return activeIndex;
	}



	public void addActiveIndex(String activeIndex) {
		System.err.println("Entrou");
		this.activeIndex = activeIndex;
	}

	
	
	

}
