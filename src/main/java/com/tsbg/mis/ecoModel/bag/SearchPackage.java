package com.tsbg.mis.ecoModel.bag;

import com.tsbg.mis.ecoModel.Eccontacts;
import com.tsbg.mis.ecoModel.Epartner;
import com.tsbg.mis.powerModel.UserInfo;
import com.tsbg.mis.util.PageRequest;

/**
 * 用于封装pageRequest和EcInfo的封装类
 */
public class SearchPackage {

    public SearchPackage() {
    }

    private PageRequest pageRequest;

    private Epartner epartner;

    private Eccontacts eccontacts;

    private UserInfo userInfo;

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public void setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    public Epartner getEpartner() {
        return epartner;
    }

    public void setEpartner(Epartner epartner) {
        this.epartner = epartner;
    }


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Eccontacts getEccontacts() {
        return eccontacts;
    }

    public void setEccontacts(Eccontacts eccontacts) {
        this.eccontacts = eccontacts;
    }
}
