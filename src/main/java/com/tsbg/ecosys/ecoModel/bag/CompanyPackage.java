package com.tsbg.ecosys.ecoModel.bag;

import com.tsbg.ecosys.ecoModel.Epartner;
import com.tsbg.ecosys.ecoModel.Eccontacts;
import com.tsbg.ecosys.ecoModel.Ecooperation;
import com.tsbg.ecosys.ecoModel.UserInfo;

/**
 * 合作伙伴、合作情况、公司联系人的封装类
 */
public class CompanyPackage {

    public CompanyPackage() {
    }

    private Epartner epartner;

    private Ecooperation ecooperation;

    private Eccontacts eccontacts;

    private UserInfo userInfo;

    private Object[] fileName;

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

    public Eccontacts getEccontacts() {
        return eccontacts;
    }

    public void setEccontacts(Eccontacts eccontacts) {
        this.eccontacts = eccontacts;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Object[] getFileName() {
        return fileName;
    }

    public void setFileName(Object[] fileName) {
        this.fileName = fileName;
    }
}
