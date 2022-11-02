package com.nerfilin.springboot.form.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nerfilin.springboot.form.app.models.domain.Usuario;

@Controller
public class FormController {
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(Model model) {
		model.addAttribute("titulo", "Formulario Usuarios");
		return "form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String procesarForm(Model model, 
			@RequestParam(value = "usernameTest") String username,
			@RequestParam(value = "passwordTest") String password,
			@RequestParam(value = "emailTest") String email) {
		
		Usuario usuario = new Usuario();
		usuario.setUsernameTest(username);
		usuario.setPasswordTest(password);
		usuario.setEmailTest(email);
		
		model.addAttribute("titulo", "Resultado del formulario");
		model.addAttribute("usuario", usuario);

		return "resultado";
	}
	
}
