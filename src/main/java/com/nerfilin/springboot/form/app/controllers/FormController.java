package com.nerfilin.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.nerfilin.springboot.form.app.editors.NombreMayusculaEditor;
import com.nerfilin.springboot.form.app.models.domain.Pais;
import com.nerfilin.springboot.form.app.models.domain.Usuario;
import com.nerfilin.springboot.form.app.validation.UsuarioValidador;

@Controller
@SessionAttributes({ "usuario", "user" })
public class FormController {

	// Aplicara el validado en los metodos que posean el @Valid
	@Autowired
	private UsuarioValidador validador;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// agrega lo validadores a
		binder.addValidators(validador);

		// fecha
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); // indulgencia, false = estricto true = no estricto
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, false)); // primer
																												// valor:
																												// tipo
																												// de
																												// dato,
																												// segundo:
																												// campo
																												// del
																												// atributo
																												// (si
																												// no se
																												// especificar
																												// es
																												// porque
																												// es
																												// global)

		binder.registerCustomEditor(String.class, new NombreMayusculaEditor()); // puede reciribir 3 argumentos y como
																				// segundo argumento se puede añadir el
																				// nombre del campo
		// binder.registerCustomEditor(String.class, "nombre", new
		// NombreMayusculaEditor());

	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return Arrays.asList(
				new Pais(1, "ES", "España"), 
				new Pais(2, "ME", "Mexico"), 
				new Pais(3, "CH", "Chile"),
				new Pais(4, "AR", "Argentina"), 
				new Pais(5, "PE", "Peru"), 
				new Pais(6, "CO", "Colombia"));
	}

	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("España", "Mexico", "Chile", "Argentina", "Peru", "Colombia");
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<String, String>();
		paises.put("ES", "España");
		paises.put("MX", "Mexico");
		paises.put("CH", "Chile");
		paises.put("AR", "Argentina");
		paises.put("PE", "Peru");
		paises.put("CO", "Colombia");

		return paises;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setIdentificador("123");
		model.addAttribute("titulo", "Formulario Usuarios");
		model.addAttribute("user", usuario);
		return "form";
	}

	// Con @ModelAttribute se le puede cambiar el nombre la clase que se usara.
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String procesarForm(@Valid @ModelAttribute("user") Usuario usuario, BindingResult result, Model model,
			SessionStatus status) {

		// validador del campo validador.validate(usuario, result);

		model.addAttribute("titulo", "Resultado del formulario");

		if (result.hasErrors()) {
//			Map<String, String> errores = new HashMap<>();
//			result.getFieldErrors().forEach(err ->{
//				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
//			});
//			model.addAttribute("error", errores);
			return "form";
		}

		model.addAttribute("usuario", usuario);
		status.setComplete();

		return "resultado";
	}

}
