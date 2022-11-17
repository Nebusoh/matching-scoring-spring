package com.matchingscoring.service;

import com.matchingscoring.model.RegolaMatching;
import com.matchingscoring.repository.IRegolaMatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegolaMatchingService {

    @Autowired
    private IRegolaMatchingRepository regolaMatchingRepository;

    public Iterable<RegolaMatching> findAll(){

        return regolaMatchingRepository.findAll();
    }
}
