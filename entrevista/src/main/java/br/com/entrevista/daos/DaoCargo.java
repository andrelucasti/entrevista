package br.com.entrevista.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entrevista.models.Cargo;

public interface DaoCargo extends JpaRepository<Cargo, Integer>{

}
