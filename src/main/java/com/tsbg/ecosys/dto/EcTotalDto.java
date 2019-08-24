package com.tsbg.ecosys.dto;

import com.tsbg.ecosys.model.Epartner;
import com.tsbg.ecosys.model.Ecooperation;

public class EcTotalDto {

    private Epartner epartner;

    private com.tsbg.ecosys.model.Eccontacts Eccontacts;

    private Ecooperation ecooperation;

    public Epartner getEpartner() {
        return epartner;
    }

    public void setEpartner(Epartner epartner) {
        this.epartner = epartner;
    }

    public com.tsbg.ecosys.model.Eccontacts getEccontacts() {
        return Eccontacts;
    }

    public void setEccontacts(com.tsbg.ecosys.model.Eccontacts eccontacts) {
        Eccontacts = eccontacts;
    }

    public Ecooperation getEcooperation() {
        return ecooperation;
    }

    public void setEcooperation(Ecooperation ecooperation) {
        this.ecooperation = ecooperation;
    }
}
