package com.matchingscoring.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class PesoMatching {

    @EmbeddedId
    private PesoMatchingId pesoMatchingId;

    private int peso;

    public PesoMatching(PesoMatchingId pesoMatchingId, int peso) {
        this.pesoMatchingId = pesoMatchingId;
        this.peso = peso;
    }

    public PesoMatching() {

    }

    public PesoMatchingId getPesoMatchingId() {
        return pesoMatchingId;
    }

    public void setPesoMatchingId(PesoMatchingId pesoMatchingId) {
        this.pesoMatchingId = pesoMatchingId;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "PesoMatching{" +
                "pesoMatchingId=" + pesoMatchingId +
                ", peso=" + peso +
                '}';
    }

}
