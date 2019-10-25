package com.tsbg.mis.ecoDto;

import com.tsbg.mis.ecoModel.Epartner;
import com.tsbg.mis.ecoModel.Ecooperation;

import java.util.List;

public class EcTotalDto {

    private Epartner epartner;

    private List<Ecooperation> ecooperation;

    private List<com.tsbg.mis.ecoModel.Eccontacts> Eccontacts;

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

    public List<com.tsbg.mis.ecoModel.Eccontacts> getEccontacts() {
        return Eccontacts;
    }

    public void setEccontacts(List<com.tsbg.mis.ecoModel.Eccontacts> eccontacts) {
        Eccontacts = eccontacts;
    }
}
