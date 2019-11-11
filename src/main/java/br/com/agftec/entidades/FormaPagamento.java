package br.com.agftec.entidades;

public interface FormaPagamento {
	
	public String cnpjCredenciadoraCartao();
	
	public int codigoCredenciadoraCartao();
	
	public FormaPagamentoNota getFormaPagamento();
	
	public String getNome();
	
	
}
