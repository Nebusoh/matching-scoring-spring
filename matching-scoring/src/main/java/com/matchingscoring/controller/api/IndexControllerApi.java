package com.matchingscoring.controller.api;

import com.matchingscoring.model.*;
import com.matchingscoring.service.AziendaService;
import com.matchingscoring.service.PersonaService;
import com.matchingscoring.service.PesoMatchingService;
import com.matchingscoring.service.RegolaMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
public class IndexControllerApi {

    @Autowired
    private RegolaMatchingService regolaMatchingService;

    @Autowired
    private PesoMatchingService pesoMatchingService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private AziendaService aziendaService;

    @RequestMapping("/index/api/peopleRules")
    public Iterable<RegolaMatching> findPeoplesMatchingRules() {

        List<RegolaMatching> regoleMatching = (List<RegolaMatching>) regolaMatchingService.findAll();
        List<RegolaMatching> regoleMatchingPersone = new LinkedList<RegolaMatching>();

        for (RegolaMatching regolaMatching : regoleMatching) {

            if (regolaMatching.getTipo().equals("PF")) {
                regoleMatchingPersone.add(regolaMatching);
            }
        }

        return regoleMatchingPersone;
    }

    @RequestMapping("/index/api/companiesRules")
    public Iterable<RegolaMatching> findCompaniesMatchingRules() {

        List<RegolaMatching> regoleMatching = (List<RegolaMatching>) regolaMatchingService.findAll();
        List<RegolaMatching> regoleMatchingAziende = new LinkedList<RegolaMatching>();

        for (RegolaMatching regolaMatching : regoleMatching) {
            if (regolaMatching.getTipo().equals("PG"))
                regoleMatchingAziende.add(regolaMatching);
        }

        return regoleMatchingAziende;
    }

    @RequestMapping(value = "/index/api/pesoMatching", method = RequestMethod.POST)
    public PesoMatching create(@RequestBody PesoMatching pesoMatching) {

        return pesoMatchingService.create(pesoMatching);
    }

    @RequestMapping("/index/api/people")
    public Iterable<Persona> findPeople(){

        return personaService.findAll();
    }

    @RequestMapping("/index/api/companies")
    public Iterable<Azienda> findCompanies(){

        return aziendaService.findAll();
    }

    @RequestMapping(value = "/index/api/people/{id}", method = RequestMethod.PUT)
    public Persona updatePersona(@PathVariable int id, @RequestBody Persona persona){

        Optional<Persona> updatedPersona = personaService.update(id, persona);

        if (updatedPersona.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }

        return updatedPersona.get();
    }

    @RequestMapping(value = "/index/api/companies/{id}", method = RequestMethod.PUT)
    public Azienda updateAzienda(@PathVariable int id, @RequestBody Azienda azienda){

        Optional<Azienda> updatedAzienda = aziendaService.update(id, azienda);

        if (updatedAzienda.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }

        return updatedAzienda.get();
    }
}
