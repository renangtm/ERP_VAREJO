package br.com.agftec.controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class PageForm {
	
	@Min(0)
	private int porPagina;
	
	@Min(0)
	private int pagina;
	
	private String filtro;
	
	private List<String> ordem;

	public int getPorPagina() {
		return porPagina;
	}

	public void setPorPagina(int porPagina) {
		this.porPagina = porPagina;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public List<String> getOrdem() {
		return ordem;
	}

	public void setOrdem(List<String> ordem) {
		this.ordem = ordem;
	}

	public Pageable get() {
		
		if(this.ordem == null) {
			
			this.ordem = new ArrayList<String>();
			
		}
		
		Sort sort = Sort.by(this.ordem.stream().map(s->{
			
			String[] ac = s.split(",");
			
			Direction d = Direction.ASC;
			
			if(ac.length > 1) {
				if(ac[1].toUpperCase().equals("DESC")) {
					d = Direction.DESC;
				}
			}
			
			return new Order(d,ac[0]);
			
		}).collect(Collectors.toList()));
				
		Pageable p = PageRequest.of(this.pagina, this.porPagina, sort);		
		
		return p;
		
	}

	public String getFiltro() {
		
		if(filtro == null)
			return "";
		
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
}
