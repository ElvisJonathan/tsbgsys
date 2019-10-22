package com.tsbg.ecosys.ecoDto;

import com.tsbg.ecosys.ecoModel.Ecooperation;
import com.tsbg.ecosys.ecoModel.Epartner;

public class EcTotalDtol {
    private Epartner epartner;

    private com.tsbg.ecosys.ecoModel.Eccontacts Eccontacts;

    private Ecooperation ecooperation;


    public Epartner getEpartner() {
        return epartner;
    }

    public void setEpartner(Epartner epartner) {
        this.epartner = epartner;
    }

    public com.tsbg.ecosys.ecoModel.Eccontacts getEccontacts() {
        return Eccontacts;
    }

    public void setEccontacts(com.tsbg.ecosys.ecoModel.Eccontacts eccontacts) {
        Eccontacts = eccontacts;
    }

    public Ecooperation getEcooperation() {
        return ecooperation;
    }

    public void setEcooperation(Ecooperation ecooperation) {
        this.ecooperation = ecooperation;
    }
}
