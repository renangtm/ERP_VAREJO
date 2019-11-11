package br.com.agftec.transacional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class TransactionalService {

	@Transactional
	public void t(Runnable rn) {
		
		rn.run();
		
	}
	
}
