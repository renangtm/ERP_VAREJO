package br.com.agftec.entidades;

import org.springframework.security.core.GrantedAuthority;

public class MicroPermissao implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	public MicroPermissao(String nome) {
		
		this.nome = nome;
		
	}

	

	@Override
	public String toString() {
		
		return this.nome;
		
	}



	@Override
	public String getAuthority() {
		
		return this.nome;
		
	}

}
