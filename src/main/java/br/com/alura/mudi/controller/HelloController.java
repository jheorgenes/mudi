package br.com.alura.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //Define que a classe é um controlador
public class HelloController {

	@GetMapping("/hello") //Mapea o caminho para o método abaixo
	public String hello(Model model) { //Mesma coisa que usar o HttpServletRequest
		model.addAttribute("nome", "Mundo"); //Adicionando uma variável pro escopo do html com o nome nome e com o valor Mundo
		return "hello"; //Executa o html cujo o nome é o hello.
	}
}