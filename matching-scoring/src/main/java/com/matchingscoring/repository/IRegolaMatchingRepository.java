package com.matchingscoring.repository;

import com.matchingscoring.model.RegolaMatching;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegolaMatchingRepository extends CrudRepository<RegolaMatching, Integer> {

}
