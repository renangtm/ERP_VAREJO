package br.com.agftec.entidades;

import javax.persistence.Entity;

@Entity
public class PISNt5 extends Pis{
	
	public PISNt5(){
		
		this.setCst("05");
		
	}
	
	
	@Override
	public Object clone(){
		
		PISNt5 p = new PISNt5();
		
		return p;
		
	}
	
}
