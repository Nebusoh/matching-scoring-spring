package com.matchingscoring.repository;

import com.matchingscoring.model.PesoMatching;
import com.matchingscoring.model.PesoMatchingId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPesoMatchingRepository extends CrudRepository<PesoMatching, PesoMatchingId> {

}
