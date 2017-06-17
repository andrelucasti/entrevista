package br.com.entrevista.daos;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.entrevista.models.CustomUserDetails;
import br.com.entrevista.models.Usuario;

@Transactional
@Repository
public class DaoCustomUserDetails implements UserDetailsService {
	
	@Autowired
	private DaoUsuario dao;

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario findUsuarioByLogin = dao.findUsuarioByLogin(username);
		return new CustomUserDetails(findUsuarioByLogin);
	}

}
