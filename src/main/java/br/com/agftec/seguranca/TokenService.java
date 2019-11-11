package br.com.agftec.seguranca;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.agftec.entidades.Usuario;
import br.com.agftec.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${token.chave}")
	private String chave;

	@Value("${token.expiracao}")
	private Long tempo;

	@Autowired
	private UsuarioRepository repoUsu;

	public String getToken(Usuario usuario) {

		Date data = new Date();
		data.setTime(data.getTime() + tempo);

		return Jwts.builder().setIssuer("API_ERPVRJ").setSubject(usuario.getId() + "").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, this.chave).compact();

	}
	
	public Usuario getUsuarioByToken(String token) {

		try {

			Optional<Usuario> usuario = this.repoUsu.findById(Integer
					.parseInt(Jwts.parser().setSigningKey(this.chave).parseClaimsJws(token).getBody().getSubject()));

			return usuario.orElse(null);

		} catch (Exception ex) {

			System.out.println(ex);
			
		}

		return null;

	}

}
