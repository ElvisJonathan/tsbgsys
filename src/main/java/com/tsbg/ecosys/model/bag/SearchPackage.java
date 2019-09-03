package com.tsbg.ecosys.model.bag;

import com.tsbg.ecosys.model.Epartner;
import com.tsbg.ecosys.model.UserInfo;
import com.tsbg.ecosys.util.PageRequest;

/**
 * 用于封装pageRequest和EcInfo的封装类
 */
public class SearchPackage {

    public SearchPackage() {
    }

    private PageRequest pageRequest;

    private Epartner epartner;

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
}
