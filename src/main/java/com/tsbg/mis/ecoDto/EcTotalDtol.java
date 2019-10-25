package com.tsbg.mis.ecoDto;

import com.tsbg.mis.ecoModel.Ecooperation;
import com.tsbg.mis.ecoModel.Epartner;

public class EcTotalDtol {
    private Epartner epartner;

    private com.tsbg.mis.ecoModel.Eccontacts Eccontacts;

    private Ecooperation ecooperation;


    public Epartner getEpartner() {
        return epartner;
    }

    public void setEpartner(Epartner epartner) {
        this.epartner = epartner;
    }

    public com.tsbg.mis.ecoModel.Eccontacts getEccontacts() {
        return Eccontacts;
    }

    public void setEccontacts(com.tsbg.mis.ecoModel.Eccontacts eccontacts) {
        Eccontacts = eccontacts;
    }

    public Ecooperation getEcooperation() {
        return ecooperation;
    }

    public void setEcooperation(Ecooperation ecooperation) {
        this.ecooperation = ecooperation;
    }
}
