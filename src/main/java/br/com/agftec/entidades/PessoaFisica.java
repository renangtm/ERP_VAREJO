package br.com.agftec.entidades;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class PessoaFisica extends Pessoa{

	@Column
	private String cpf;
	
	@Column
	private String rg;
	
	@Column
	private Calendar data_nascimento;
	
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="id_pessoa")
	private Usuario usuario;
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PessoaFisica(){
		super();
		
		this.data_nascimento = Calendar.getInstance();
	
		
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Calendar getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Calendar data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	
	
	
}
