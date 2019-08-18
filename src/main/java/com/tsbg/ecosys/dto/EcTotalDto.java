package com.tsbg.ecosys.dto;

import com.tsbg.ecosys.model.EcInfo;
import com.tsbg.ecosys.model.Ecooperation;

public class EcTotalDto {

    private EcInfo ecInfo;

    private com.tsbg.ecosys.model.Eccontacts Eccontacts;

    private Ecooperation ecooperation;

    public EcInfo getEcInfo() {
        return ecInfo;
    }

    public void setEcInfo(EcInfo ecInfo) {
        this.ecInfo = ecInfo;
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
