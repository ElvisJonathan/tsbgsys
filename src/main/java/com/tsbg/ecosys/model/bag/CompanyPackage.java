package com.tsbg.ecosys.model.bag;

import com.tsbg.ecosys.model.EcInfo;
import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.Ecooperation;

/**
 * 合作伙伴、合作情况、公司联系人的封装类
 */
public class CompanyPackage {

    public CompanyPackage() {
    }

    private EcInfo ecInfo;

    private Ecooperation ecooperation;

    private Eccontacts eccontacts;

    public EcInfo getEcInfo() {
        return ecInfo;
    }

    public void setEcInfo(EcInfo ecInfo) {
        this.ecInfo = ecInfo;
    }

    public Ecooperation getEcooperation() {
        return ecooperation;
    }

    public void setEcooperation(Ecooperation ecooperation) {
        this.ecooperation = ecooperation;
    }

    public Eccontacts getEccontacts() {
        return eccontacts;
    }

    public void setEccontacts(Eccontacts eccontacts) {
        this.eccontacts = eccontacts;
    }
}
