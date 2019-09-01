package com.tsbg.ecosys.dto;

import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.Ecooperation;
import com.tsbg.ecosys.model.Epartner;

import java.util.List;

public class EcTotalDtol {
    private Epartner epartner;

    private Ecooperation ecooperation;

    private Eccontacts Eccontacts;

    public Epartner getEpartner() {
        return epartner;
    }

    public void setEpartner(Epartner epartner) {
        this.epartner = epartner;
    }

    public Ecooperation getEcooperation() {
        return ecooperation;
    }

    public void setEcooperation(Ecooperation ecooperation) {
        this.ecooperation = ecooperation;
    }

    public com.tsbg.ecosys.model.Eccontacts getEccontacts() {
        return Eccontacts;
    }

    public void setEccontacts(com.tsbg.ecosys.model.Eccontacts eccontacts) {
        Eccontacts = eccontacts;
    }
}
