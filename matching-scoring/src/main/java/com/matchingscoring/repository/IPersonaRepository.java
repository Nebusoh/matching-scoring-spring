package com.matchingscoring.repository;

import com.matchingscoring.model.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends CrudRepository<Persona, Integer> {

}
