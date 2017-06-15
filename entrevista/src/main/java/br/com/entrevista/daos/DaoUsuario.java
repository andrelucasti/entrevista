package br.com.entrevista.daos;

import org.springframework.data.repository.CrudRepository;

import br.com.entrevista.models.Usuario;

public interface DaoUsuario extends CrudRepository<Usuario, Integer>{

}
