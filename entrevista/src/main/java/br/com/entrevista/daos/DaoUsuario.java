package br.com.entrevista.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.entrevista.models.EnumTipoUsuario;
import br.com.entrevista.models.Usuario;

public interface DaoUsuario extends JpaRepository<Usuario, Integer>{
	
	@Query("select u from Usuario u left join fetch u.entrevista e join fetch u.tipoUsuarios t  where t.tipoUsuario=:pTipoUsuario and e.id = null")
	public List<Usuario> findAllUsuariosEntrevistados(@Param("pTipoUsuario") EnumTipoUsuario pTipoUsuario);
	
	@Query("select u from Usuario u join fetch u.entrevista e where e.isAprovado = 1")
	public List<Usuario> findAllUsuariosAprovados();
	
	@Query("select u from Usuario u join fetch u.entrevista e where e.isAprovado = 0")
	public List<Usuario> findAllUsuariosNaoAprovados();
	
	@Query("select u from Usuario u  join fetch u.tipoUsuarios where u.login=:pLogin")
	public Usuario findUsuarioByLogin(@Param("pLogin") String pLogin);

}
