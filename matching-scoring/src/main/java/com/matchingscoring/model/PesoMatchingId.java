package com.matchingscoring.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PesoMatchingId implements Serializable {

    private int idRegolaMatching;

    private String idCampo;

    public PesoMatchingId() {
    }

    public PesoMatchingId(int idRegolaMatching, String idCampoMatching) {
        this.idRegolaMatching = idRegolaMatching;
        this.idCampo = idCampoMatching;
    }

    public int getIdRegolaMatching() {
        return idRegolaMatching;
    }

    public void setIdRegolaMatching(int idRegolaMatching) {
        this.idRegolaMatching = idRegolaMatching;
    }

    public String getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(String idCampo) {
        this.idCampo = idCampo;
    }

    @Override
    public String toString() {
        return "PesoMatchingId{" +
                "idRegolaMatching=" + idRegolaMatching +
                ", idCampoMatching='" + idCampo + '\'' +
                '}';
    }

}
