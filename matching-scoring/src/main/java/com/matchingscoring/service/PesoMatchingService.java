package com.matchingscoring.service;

import com.matchingscoring.model.PesoMatching;
import com.matchingscoring.model.PesoMatchingId;
import com.matchingscoring.repository.IPesoMatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PesoMatchingService {

    @Autowired
    private IPesoMatchingRepository pesoMatchingRepository;

    public Iterable<PesoMatching> findAll() {

        return pesoMatchingRepository.findAll();
    }

    public PesoMatching create(PesoMatching pesoMatching) {

        return pesoMatchingRepository.save(pesoMatching);
    }

    public Boolean delete(PesoMatchingId pesoMatchingId){

        Optional<PesoMatching> foundPesoMatching = pesoMatchingRepository.findById(pesoMatchingId);

        if(foundPesoMatching.isEmpty()){
            return false;
        }

        pesoMatchingRepository.delete(foundPesoMatching.get());

        return true;
    }
}
