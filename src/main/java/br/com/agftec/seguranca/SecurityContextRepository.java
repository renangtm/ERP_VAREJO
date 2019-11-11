package br.com.agftec.seguranca;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerWebExchange;

import br.com.agftec.entidades.Usuario;
import reactor.core.publisher.Mono;

@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

	@Autowired
	private TokenService tks;

	@Override
	public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {

		throw new UnsupportedOperationException("Operacao nao suportada");

	}

	@Override
	@Transactional
	public Mono<SecurityContext> load(ServerWebExchange req) {

		try {

			String token = req.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION).split(" ", 2)[1];

			Usuario usu = tks.getUsuarioByToken(token);

			if (usu == null) {

				return Mono.empty();

			} else {

				System.out.println(usu.getAuthorities());

				return Mono.just(new SecurityContextImpl(
						new UsernamePasswordAuthenticationToken(usu, null, usu.getAuthorities().stream()
								.map(p -> new SimpleGrantedAuthority(p.getAuthority())).collect(Collectors.toList()))));

			}

		} catch (Exception ex) {

		}

		return Mono.empty();

	}

}
