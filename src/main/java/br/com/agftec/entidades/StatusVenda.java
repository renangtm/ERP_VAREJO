package br.com.agftec.entidades;

public enum StatusVenda {
	
	EM_EXECUCAO(0,true),
	RESERVA(1,true),
	FINALIZANDO_PAGAMENTO(1,true),
	FECHADA(2,false),
	CANCELADA(3,false);
	
	private int id;
	private boolean disponivel;
	
	private StatusVenda(int id,boolean disponivel) {
		
		this.id = id;
		this.disponivel = disponivel;
		
	}
	
	public boolean isDisponivel() {
		return disponivel;
	}


	public int getId() {
		
		return this.id;
		
	}

}
