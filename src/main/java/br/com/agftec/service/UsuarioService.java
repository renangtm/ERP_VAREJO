package br.com.agftec.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.agftec.entidades.Usuario;
import br.com.agftec.repository.UsuarioRepository;
@Service
public class UsuarioService{

	@Autowired
	private UsuarioRepository userRepo;

	public Usuario loadUserByUsername(String username){

		Optional<Usuario> usuario = this.userRepo.findByUsuario(username);
		
		if (!usuario.isPresent()) {

			throw new UsernameNotFoundException("");

		}

		return usuario.get();

	}

}
