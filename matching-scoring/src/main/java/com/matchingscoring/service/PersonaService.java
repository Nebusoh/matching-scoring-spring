package com.matchingscoring.service;

import com.matchingscoring.model.Azienda;
import com.matchingscoring.model.Persona;
import com.matchingscoring.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private IPersonaRepository personaRepository;


    public Iterable<Persona> findAll(){

       return personaRepository.findAll();
    }

    public Optional<Persona> update(int id, Persona persona){

        Optional<Persona> foundPersona = personaRepository.findById(id);

        if(foundPersona.isEmpty()){

            return Optional.empty();
        }

        foundPersona.get().setNuovoCampo(persona.getNuovoCampo());

        personaRepository.save(foundPersona.get());

        return foundPersona;
    }

    public void resetAll(){

        List<Persona> persone = (List<Persona>) personaRepository.findAll();

        for(Persona persona: persone) {

            persona.setNuovoCampo("");
            personaRepository.save(persona);
        }
    }
}
