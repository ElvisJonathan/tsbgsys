package com.tsbg.ecosys.dto;

import com.tsbg.ecosys.model.Epartner;
import com.tsbg.ecosys.model.Ecooperation;

import java.util.List;

public class EcTotalDto {

    private Epartner epartner;

    private List<Ecooperation> ecooperation;

    private List<com.tsbg.ecosys.model.Eccontacts> Eccontacts;

    public Epartner getEpartner() {
        return epartner;
    }

    public void setEpartner(Epartner epartner) {
        this.epartner = epartner;
    }


    public List<Ecooperation> getEcooperation() {
        return ecooperation;
    }

    public void setEcooperation(List<Ecooperation> ecooperation) {
        this.ecooperation = ecooperation;
    }

    public List<com.tsbg.ecosys.model.Eccontacts> getEccontacts() {
        return Eccontacts;
    }

    public void setEccontacts(List<com.tsbg.ecosys.model.Eccontacts> eccontacts) {
        Eccontacts = eccontacts;
    }
}
