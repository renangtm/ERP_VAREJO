package br.com.agftec.entidades;

public class TabelaIcms {
	
	
	private TabelaIcms(){
		
	}

	public static double getIcms(Estado e1, Estado e2){
		
		if(
				e2.getSigla().equals("MG") ||
				e2.getSigla().equals("RS") ||
				e2.getSigla().equals("SC") ||
				e2.getSigla().equals("RJ") ||
				e2.getSigla().equals("PR") ||
				e2.getSigla().equals("SP")){
			
			return 12;
			
		}
		
		return 7;
		
	}
	
}
