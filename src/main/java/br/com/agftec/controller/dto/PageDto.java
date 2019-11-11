package br.com.agftec.controller.dto;

import java.util.List;

import org.springframework.data.domain.Page;

public class PageDto<T> {

	private int pagina;
	
	private int porPagina;
	
	private int total;
	
	private List<T> elementos;
	
	public PageDto(Page<T> pagina) {
		
		this.pagina = pagina.getNumber();
		this.porPagina = pagina.getPageable().getPageSize();
		this.total = pagina.getTotalPages();
		this.elementos = pagina.getContent();
		
	}

	public int getPagina() {
		return pagina;
	}

	public int getPorPagina() {
		return porPagina;
	}

	public int getTotal() {
		return total;
	}

	public List<T> getElementos() {
		return elementos;
	}
	
}
