package com.tsbg.mis.ecoDto;

import com.tsbg.mis.ecoModel.Eccontacts;
import com.tsbg.mis.ecoModel.Ecooperation;
import com.tsbg.mis.ecoModel.Epartner;

import java.util.List;

public class EcTotal_Excel {
    private List<Eccontacts> eccontactsList;
    private List<Epartner> epartnerList;
    private List<Ecooperation> ecooperationList;

    public List<Eccontacts> getEccontactsList() {
        return eccontactsList;
    }

    public void setEccontactsList(List<Eccontacts> eccontactsList) {
        this.eccontactsList = eccontactsList;
    }

    public List<Epartner> getEpartnerList() {
        return epartnerList;
    }

    public void setEpartnerList(List<Epartner> epartnerList) {
        this.epartnerList = epartnerList;
    }

    public List<Ecooperation> getEcooperationList() {
        return ecooperationList;
    }

    public void setEcooperationList(List<Ecooperation> ecooperationList) {
        this.ecooperationList = ecooperationList;
    }
}
