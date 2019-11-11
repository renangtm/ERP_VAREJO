package br.com.agftec.entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_pessoa")
	private PessoaFisica pf;

	@Column
	private String usuario;

	@Column
	private String senha;

	// mappedBy='usuario'
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario")
	private List<Permissao> permissoes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PessoaFisica getPf() {
		return this.pf;
	}

	public void setPf(PessoaFisica pf) {
		this.pf = pf;
	}

	public Usuario() {

		this.permissoes = new ArrayList<Permissao>();
		this.pf = new PessoaFisica();

	}

	public Permissao getPermissao(TipoPermissao tp) {

		List<Permissao> p = this.permissoes.stream().filter(x -> x.getTipo().equals(tp)).collect(Collectors.toList());

		if (p.size() > 0)
			return p.get(0);

		Permissao falsa = new Permissao();
		falsa.setTipo(tp);

		return falsa;

	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.permissoes.stream().flatMap(p -> p.getPermissoes().stream()).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
