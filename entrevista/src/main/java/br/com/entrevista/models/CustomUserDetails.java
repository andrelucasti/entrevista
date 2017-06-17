package br.com.entrevista.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	
	public CustomUserDetails(Usuario pUsuario) {
		this.usuario = pUsuario;
	
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities();
	}

	@Override
	public String getPassword() {
		return this.usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return this.usuario.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	private List<GrantedAuthority> authorities(){
		List<GrantedAuthority> auths = new ArrayList<>();
		
		for (TipoUsuario tpUsuario: this.usuario.getTipoUsuarios()) {
			
			auths.add(new SimpleGrantedAuthority(tpUsuario.getTipoUsuario().name()));
			
		}
		
		return auths;
		
	}

}
