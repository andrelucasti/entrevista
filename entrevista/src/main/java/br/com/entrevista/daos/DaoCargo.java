package br.com.entrevista.daos;

import org.springframework.data.repository.CrudRepository;

import br.com.entrevista.models.Cargo;

public interface DaoCargo extends CrudRepository<Cargo, Integer>{

}
