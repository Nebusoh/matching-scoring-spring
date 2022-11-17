package com.matchingscoring.controller.api;


import com.intuit.fuzzymatcher.component.MatchService;
import com.intuit.fuzzymatcher.domain.Document;
import com.intuit.fuzzymatcher.domain.Element;
import com.intuit.fuzzymatcher.domain.ElementType;
import com.intuit.fuzzymatcher.domain.Match;
import com.intuit.fuzzymatcher.function.TokenizerFunction;
import com.matchingscoring.model.*;
import com.matchingscoring.service.AziendaService;
import com.matchingscoring.service.PersonaService;
import com.matchingscoring.service.PesoMatchingService;
import com.matchingscoring.service.RegolaMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

@RestController
public class MatchingControllerApi {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private AziendaService aziendaService;

    @Autowired
    private RegolaMatchingService regolaMatchingService;

    @Autowired
    private PesoMatchingService pesoMatchingService;

    @RequestMapping(value = "/matching/api/matchingScoring")
    @ResponseBody
    public List<MyMatch> matchingScoring(int idRegolaMatching) {

        System.out.printf(String.valueOf(idRegolaMatching));

        List<RegolaMatching> regoleMatching = (List<RegolaMatching>) regolaMatchingService.findAll();
        Boolean personaCheck = false;
        List<PesoMatching> pesiMatching = (List<PesoMatching>) pesoMatchingService.findAll();
        List<Document> matchingScoring = new LinkedList<>();
        List<MyMatch> returnValues = new LinkedList<MyMatch>();

        for (RegolaMatching regolaMatching : regoleMatching)
            if (regolaMatching.getId() == idRegolaMatching && regolaMatching.getTipo().equals("PF"))
                personaCheck = true;

        if (personaCheck) {

            List<Persona> persone = (List<Persona>) personaService.findAll();

            try {
                Class<?> cls = Class.forName("com.matchingscoring.model.Persona");
                Field[] fieldList = cls.getDeclaredFields();

                int cont;

                for (Persona persona : persone) {
                    Document personaMatching = null;
                    cont = 0;
                    for (PesoMatching psM : pesiMatching) {
                        if (psM.getPesoMatchingId().getIdRegolaMatching() == idRegolaMatching) {
                            for (Field field : fieldList) {
                                field.setAccessible(true);
                                if ((field.getName().equals(psM.getPesoMatchingId().getIdCampo()))) {
                                    if (cont == 0) {
                                        personaMatching = new Document.Builder(String.valueOf(persona.getId())).addElement(new Element.Builder<String>().setType(ElementType.TEXT).setValue(field.get(persona)).setTokenizerFunction(TokenizerFunction.triGramTokenizer()).setWeight(psM.getPeso()).createElement()).createDocument();
                                        cont += 1;
                                    } else {
                                        Element tmp = new Element.Builder<String>().setType(ElementType.TEXT).setValue(field.get(persona)).setTokenizerFunction(TokenizerFunction.triGramTokenizer()).setWeight(psM.getPeso()).createElement();
                                        tmp.setDocument(personaMatching);
                                        personaMatching.getElements().add(tmp);
                                    }
                                }
                                if (field.getName().equals("nuovoCampo") && field.get(persona) != null) {
                                    Element tmp = new Element.Builder<String>().setType(ElementType.TEXT).setValue(field.get(persona)).setTokenizerFunction(TokenizerFunction.triGramTokenizer()).setWeight(psM.getPeso()).createElement();
                                    tmp.setDocument(personaMatching);
                                    personaMatching.getElements().add(tmp);
                                }
                            }
                        }
                    }
                    matchingScoring.add(personaMatching);
                }

            } catch (ClassNotFoundException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {

            List<Azienda> aziende = (List<Azienda>) aziendaService.findAll();

            try {
                Class<?> cls = Class.forName("com.matchingscoring.model.Azienda");
                Field[] fieldList = cls.getDeclaredFields();

                int cont;

                for (Azienda azienda : aziende) {
                    Document aziendaMatching = null;
                    cont = 0;
                    for (PesoMatching pesoMatching : pesiMatching) {
                        if (pesoMatching.getPesoMatchingId().getIdRegolaMatching() == idRegolaMatching) {
                            for (Field field : fieldList) {
                                field.setAccessible(true);
                                if (field.getName().equals(pesoMatching.getPesoMatchingId().getIdCampo())) {
                                    if (cont == 0) {
                                        aziendaMatching = new Document.Builder(String.valueOf(azienda.getId())).addElement(new Element.Builder<String>().setType(ElementType.TEXT).setValue(field.get(azienda)).setTokenizerFunction(TokenizerFunction.triGramTokenizer()).setWeight(pesoMatching.getPeso()).createElement()).createDocument();
                                        cont += 1;
                                    } else {
                                        Element tmp = new Element.Builder<String>().setType(ElementType.TEXT).setValue(field.get(azienda)).setTokenizerFunction(TokenizerFunction.triGramTokenizer()).setWeight(pesoMatching.getPeso()).createElement();
                                        tmp.setDocument(aziendaMatching);
                                        aziendaMatching.getElements().add(tmp);
                                    }
                                }
                                if (field.getName().equals("nuovoCampo") && field.get(azienda) != null) {
                                    Element tmp = new Element.Builder<String>().setType(ElementType.TEXT).setValue(field.get(azienda)).setTokenizerFunction(TokenizerFunction.triGramTokenizer()).setWeight(pesoMatching.getPeso()).createElement();
                                    tmp.setDocument(aziendaMatching);
                                    aziendaMatching.getElements().add(tmp);
                                }
                            }
                        }
                    }
                    matchingScoring.add(aziendaMatching);
                }
            } catch (ClassNotFoundException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        MatchService matchService = new MatchService();

        matchService.applyMatchByDocId(matchingScoring).forEach((key, value) -> value.forEach(match -> {

                MyMatch tmpMatch = new MyMatch();
                tmpMatch.setMatch(String.valueOf(match.getData()));
                tmpMatch.setMatchedWith(String.valueOf(match.getMatchedWith()));
                tmpMatch.setScore(match.getScore().getResult());

                returnValues.add(tmpMatch);
        }));

        returnValues.sort((o1, o2) -> Double.compare(o1.getScore(), o2.getScore()));

        return returnValues;
    }

    @RequestMapping(value = "/matching/api/deletePesoMatching", method = RequestMethod.DELETE)
    public void deletePesoMatching(int idRegolaMatching, String idCampo){

        System.out.printf(String.valueOf(idRegolaMatching) + " , " + idCampo);

        PesoMatchingId pesoMatchingId = new PesoMatchingId(idRegolaMatching, idCampo);
        Boolean isDeleted = pesoMatchingService.delete(pesoMatchingId);

        if(!isDeleted)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");

    }

    @RequestMapping(value = "/matching/api/resetPeople")
    public void resetPeople(){

        personaService.resetAll();
    }

    @RequestMapping(value = "/matching/api/resetCompanies")
    public void resetCompanies(){

        aziendaService.resetAll();
    }

}
