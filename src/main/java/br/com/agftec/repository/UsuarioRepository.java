package br.com.agftec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agftec.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

	public Optional<Usuario> findByUsuario(String login);
	
	public Optional<Usuario> findById(int id);
	
}
