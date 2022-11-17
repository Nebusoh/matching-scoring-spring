package com.matchingscoring.repository;

import com.matchingscoring.model.Azienda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAziendaRepository extends CrudRepository<Azienda, Integer> {

}
