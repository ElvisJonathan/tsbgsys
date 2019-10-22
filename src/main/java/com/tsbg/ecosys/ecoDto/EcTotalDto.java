package com.tsbg.ecosys.ecoDto;

import com.tsbg.ecosys.ecoModel.Epartner;
import com.tsbg.ecosys.ecoModel.Ecooperation;

import java.util.List;

public class EcTotalDto {

    private Epartner epartner;

    private List<Ecooperation> ecooperation;

    private List<com.tsbg.ecosys.ecoModel.Eccontacts> Eccontacts;

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

    public List<com.tsbg.ecosys.ecoModel.Eccontacts> getEccontacts() {
        return Eccontacts;
    }

    public void setEccontacts(List<com.tsbg.ecosys.ecoModel.Eccontacts> eccontacts) {
        Eccontacts = eccontacts;
    }
}
