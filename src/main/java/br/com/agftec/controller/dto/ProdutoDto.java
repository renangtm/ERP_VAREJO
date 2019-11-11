package br.com.agftec.controller.dto;

import org.springframework.data.domain.Page;

import br.com.agftec.entidades.Produto;
import br.com.agftec.entidades.TipoQuantidade;
import br.com.agftec.entidades.UnidadePeso;
import br.com.agftec.entidades.UnidadeVolume;

public class ProdutoDto {

	public static Page<ProdutoDto> converter(Page<Produto> produtos) {

		return produtos.map(ProdutoDto::new);

	}

	private int id;
	private String nome;
	private String imagem;
	private double custo;
	private double valor;
	private TipoQuantidade tipoQuantidade;
	private UnidadePeso upeso;
	private UnidadeVolume uvolume;
	private double estoque;
	private double disponivel;
	private double peso;
	private double volume;

	public ProdutoDto(Produto prod) {

		this.id = prod.getId();
		this.nome = prod.getNome();
		this.custo = prod.getCusto();
		this.imagem = prod.getImagem();
		this.tipoQuantidade = prod.getEstoque().getTipo();
		this.estoque = prod.getEstoque().getQuantidade();
		this.disponivel = prod.getEstoque().getDisponivel();
		this.peso = prod.getPeso();
		this.volume = prod.getVolume();
		this.upeso = prod.getUnidade_peso();
		this.uvolume = prod.getUnidade_volume();
		this.valor = prod.getValor();

	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getImagem() {
		return imagem;
	}

	public double getCusto() {
		return custo;
	}

	public double getValor() {
		return valor;
	}

	public TipoQuantidade getTipoQuantidade() {
		return tipoQuantidade;
	}

	public UnidadePeso getUpeso() {
		return upeso;
	}

	public UnidadeVolume getUvolume() {
		return uvolume;
	}

	public double getEstoque() {
		return estoque;
	}

	public double getDisponivel() {
		return disponivel;
	}

	public double getPeso() {
		return peso;
	}

	public double getVolume() {
		return volume;
	}

}
