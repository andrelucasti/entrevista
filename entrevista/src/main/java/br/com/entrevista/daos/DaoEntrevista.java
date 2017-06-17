package br.com.entrevista.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.entrevista.models.Entrevista;

public interface DaoEntrevista extends JpaRepository<Entrevista, Integer>{
	
	@Query("select e from Entrevista e join fetch e.usuario u where u.id=:pId")
	public Entrevista findEntrevista(@Param("pId")Integer pId);

}
