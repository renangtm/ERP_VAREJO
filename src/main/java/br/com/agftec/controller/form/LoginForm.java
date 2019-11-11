package br.com.agftec.controller.form;

import javax.validation.constraints.NotEmpty;

public class LoginForm {
	
	@NotEmpty
	private String usuario;
	
	@NotEmpty
	private String senha;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

}
