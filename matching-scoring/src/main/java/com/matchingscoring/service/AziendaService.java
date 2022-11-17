package com.matchingscoring.service;

import com.matchingscoring.model.Azienda;
import com.matchingscoring.model.Persona;
import com.matchingscoring.repository.IAziendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AziendaService {

    @Autowired
    private IAziendaRepository aziendaRepository;

    public Iterable<Azienda> findAll(){

        return aziendaRepository.findAll();
    }

    public Optional<Azienda> update(int id, Azienda azienda){

        Optional<Azienda> foundAzienda = aziendaRepository.findById(id);

        if(foundAzienda.isEmpty()){

            return Optional.empty();
        }

        foundAzienda.get().setNuovoCampo(azienda.getNuovoCampo());

        aziendaRepository.save(foundAzienda.get());

        return foundAzienda;
    }

    public void resetAll(){

        List<Azienda> aziende = (List<Azienda>) aziendaRepository.findAll();

        for(Azienda azienda: aziende) {

            azienda.setNuovoCampo("");
            aziendaRepository.save(azienda);
        }

    }
}
