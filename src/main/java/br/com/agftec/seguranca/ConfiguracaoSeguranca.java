package br.com.agftec.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class ConfiguracaoSeguranca {
	
	@Autowired
	private SecurityContextRepository secRepo;
	
	@Autowired
	private AuthenticationManager auth;
	
	@Bean
	public SecurityWebFilterChain filtroSeguranca(ServerHttpSecurity http) {

		return http
				.csrf().disable()
				.formLogin().disable()
				.httpBasic().disable()
				.logout().disable()
				.authenticationManager(auth)
				.securityContextRepository(secRepo)
				.authorizeExchange()
				.pathMatchers("/login")
				.permitAll()
				.pathMatchers(HttpMethod.GET,"/produto")
				.authenticated()
				.and()
				.build();
		
	}
	

}
