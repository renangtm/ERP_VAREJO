package br.com.agftec.entidades;

import java.util.Date;

public class MovimentoProduto {

	private ProdutoRelatorio pr;
	
	private double quantidade;
	
	private TipoQuantidade tipoQuantidade;
	
	private Date data;
	
	private int numeroNota;
	
	private SaidaEntrada operacao;
	
	private double lucro;
	
	private double valor;
	
	private String destinatario;

	public ProdutoRelatorio getPr() {
		return pr;
	}

	
	
	public double getLucro() {
		return lucro;
	}



	public void setLucro(double lucro) {
		this.lucro = lucro;
	}



	public double getValor() {
		return valor;
	}



	public void setValor(double valor) {
		this.valor = valor;
	}



	public void setPr(ProdutoRelatorio pr) {
		this.pr = pr;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	
	
	public TipoQuantidade getTipoQuantidade() {
		return tipoQuantidade;
	}



	public void setTipoQuantidade(TipoQuantidade tipoQuantidade) {
		this.tipoQuantidade = tipoQuantidade;
	}



	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(int numeroNota) {
		this.numeroNota = numeroNota;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}



	public SaidaEntrada getOperacao() {
		return operacao;
	}



	public void setOperacao(SaidaEntrada operacao) {
		this.operacao = operacao;
	}
	
	
	
}
