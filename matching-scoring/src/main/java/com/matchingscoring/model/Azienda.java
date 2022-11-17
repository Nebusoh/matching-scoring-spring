package com.matchingscoring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Azienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ragioneSociale;
    private String partitaIva;
    private String referente;
    private String via;
    private String numeroCivico;
    private String citta;
    private String provincia;
    private String nuovoCampo;

    public Azienda(int id, String ragioneSociale, String partitaIva, String referente, String via, String numeroCivico, String citta, String provincia, String nuovoCampo) {
        this.id = id;
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.referente = referente;
        this.via = via;
        this.numeroCivico = numeroCivico;
        this.citta = citta;
        this.provincia = provincia;
        this.nuovoCampo = nuovoCampo;
    }

    public Azienda() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getReferente() {
        return referente;
    }

    public void setReferente(String referente) {
        this.referente = referente;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNuovoCampo() {
        return nuovoCampo;
    }

    public void setNuovoCampo(String nuovoCampo) {
        this.nuovoCampo = nuovoCampo;
    }

    @Override
    public String toString() {
        return "Azienda{" +
                "id=" + id +
                ", ragioneSociale='" + ragioneSociale + '\'' +
                ", partitaIva='" + partitaIva + '\'' +
                ", referente='" + referente + '\'' +
                ", via='" + via + '\'' +
                ", numeroCivico='" + numeroCivico + '\'' +
                ", citta='" + citta + '\'' +
                ", provincia='" + provincia + '\'' +
                ", nuovoCampo='" + nuovoCampo + '\'' +
                '}';
    }
}
