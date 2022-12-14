package com.nerfilin.springboot.form.app.models.domain;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.nerfilin.springboot.form.app.validation.IdentificadorRegex;
import com.nerfilin.springboot.form.app.validation.Requerido;

public class Usuario {

	// @Pattern(regexp = "[0-9]{2}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}")
	@IdentificadorRegex
	private String identificador;

	// @NotEmpty()
	private String nombre;

	// @NotEmpty
	@Requerido
	private String apellido;

	@NotBlank
	@Size(min = 3, max = 8)
	private String usernameTest;

	@NotEmpty
	private String passwordTest;

	@NotEmpty
	@Email
	private String emailTest;

	@NotNull
	private Integer cuenta;

	@NotNull
	//@Future // acepta fechas futuras a un valor
	@Past // acepta fecha pasadas a un valor
	//@DateTimeFormat(pattern = "yyyy-MM-dd") sirve para cambiar el formato del campo date
	private Date fechaNacimiento;
	
	@Valid
	private Pais pais;

	public String getUsernameTest() {
		return usernameTest;
	}

	public void setUsernameTest(String usernameTest) {
		this.usernameTest = usernameTest;
	}

	public String getPasswordTest() {
		return passwordTest;
	}

	public void setPasswordTest(String passwordTest) {
		this.passwordTest = passwordTest;
	}

	public String getEmailTest() {
		return emailTest;
	}

	public void setEmailTest(String emialTest) {
		this.emailTest = emialTest;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Integer getCuenta() {
		return cuenta;
	}

	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
