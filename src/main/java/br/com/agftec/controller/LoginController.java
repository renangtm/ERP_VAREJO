package br.com.agftec.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agftec.controller.dto.LoginDto;
import br.com.agftec.controller.form.LoginForm;
import br.com.agftec.entidades.Usuario;
import br.com.agftec.seguranca.TokenService;
import br.com.agftec.service.UsuarioService;
import br.com.agftec.transacional.TransactionalService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UsuarioService us;

	@Autowired
	private TokenService tks;

	@Autowired
	private TransactionalService ts;

	@PostMapping
	@Transactional
	public Mono<ResponseEntity<LoginDto>> login(@Valid @RequestBody LoginForm frmLogin) {

		return Mono.create(s -> {

			ts.t(() -> {

				try {

					Usuario usuario = (Usuario) us.loadUserByUsername(frmLogin.getUsuario());
					
					if (frmLogin.getSenha().equals(usuario.getPassword())) {

						LoginDto loginDto = new LoginDto();

						loginDto.setToken(tks.getToken(usuario));
						loginDto.setTipo("Bearer");

						s.success(ResponseEntity.ok().body(loginDto));

					} else {

						s.success(ResponseEntity.notFound().build());

					}

				} catch (UsernameNotFoundException ex) {

					s.success(ResponseEntity.notFound().build());

				}

			});

		});

	}

}
