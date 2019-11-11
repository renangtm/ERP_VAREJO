package br.com.agftec.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agftec.controller.dto.PageDto;
import br.com.agftec.controller.dto.ProdutoDto;
import br.com.agftec.controller.form.PageForm;
import br.com.agftec.entidades.Usuario;
import br.com.agftec.repository.ProdutoRepository;
import br.com.agftec.transacional.TransactionalService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository pr;

	@Autowired
	private TransactionalService ts;

	@GetMapping
	public Mono<ResponseEntity<PageDto<ProdutoDto>>> getProdutos(@Valid @RequestBody PageForm paginacao, Authentication aut) {

		return Mono.create(s -> {

			ts.t(() -> {

				Usuario usuario = (Usuario) aut.getPrincipal();
				
				s.success(ResponseEntity.ok().body(new PageDto<ProdutoDto>(
						ProdutoDto.converter(pr.findByEmpresaAndNomeContaining(usuario.getPf().getEmpresa(),
								paginacao.getFiltro(), paginacao.get())))));

			});

		});

	}

}
