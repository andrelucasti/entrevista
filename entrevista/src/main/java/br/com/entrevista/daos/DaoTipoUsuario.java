package br.com.entrevista.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.entrevista.models.EnumTipoUsuario;
import br.com.entrevista.models.TipoUsuario;

public interface DaoTipoUsuario extends JpaRepository<TipoUsuario, Integer>{
	
	@Query("select tp from TipoUsuario tp where tp.tipoUsuario=:pTipoUsuario")
	public TipoUsuario findByName( @Param("pTipoUsuario") EnumTipoUsuario pTipoUsuario);

}
