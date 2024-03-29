package br.com.agftec.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="padrao_codigo")
public class PadraoCodigo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String nome;
	
	@Column
	private String digitoInicial;
	
	@Column
	private int digitosCodigoProduto;
	
	@Column
	private int digitosUnidade;
	
	@Column
	private int casasDecimais;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name="id_empresa")
	private Empresa empresa;
	
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public int getCasasDecimais() {
		return casasDecimais;
	}

	public void setCasasDecimais(int casasDecimais) {
		this.casasDecimais = casasDecimais;
	}

	@Column
	@Enumerated(EnumType.ORDINAL)
	private TipoQuantidade tipo;

	public PadraoCodigo(){
		
		this.tipo = TipoQuantidade.GR;
		
	}
	
	public int getTamanhoCodigo(){
		
		return this.digitoInicial.length()+this.digitosCodigoProduto+this.digitosUnidade;
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDigitoInicial() {
		return digitoInicial;
	}

	public void setDigitoInicial(String digitoInicial) {
		this.digitoInicial = digitoInicial;
	}

	public int getDigitosCodigoProduto() {
		return digitosCodigoProduto;
	}

	public void setDigitosCodigoProduto(int digitosCodigoProduto) {
		this.digitosCodigoProduto = digitosCodigoProduto;
	}

	public int getDigitosUnidade() {
		return digitosUnidade;
	}

	public void setDigitosUnidade(int digitosUnidade) {
		this.digitosUnidade = digitosUnidade;
	}

	public TipoQuantidade getTipo() {
		return tipo;
	}

	public void setTipo(TipoQuantidade tipo) {
		this.tipo = tipo;
	}
	
}
