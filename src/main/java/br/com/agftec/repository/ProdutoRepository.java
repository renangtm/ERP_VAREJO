package br.com.agftec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agftec.entidades.Empresa;
import br.com.agftec.entidades.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer>{

	public Produto findByCodigoBarra(String codigo);
	
	public Page<Produto> findByEmpresaAndNomeContaining(Empresa empresa,String nome,Pageable pg);
	
}
