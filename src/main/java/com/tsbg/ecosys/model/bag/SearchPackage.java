package com.tsbg.ecosys.model.bag;

import com.tsbg.ecosys.model.EcInfo;
import com.tsbg.ecosys.util.PageRequest;

/**
 * 用于封装pageRequest和EcInfo的封装类
 */
public class SearchPackage {

    public SearchPackage() {
    }

    private PageRequest pageRequest;

    private EcInfo ecInfo;

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public void setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    public EcInfo getEcInfo() {
        return ecInfo;
    }

    public void setEcInfo(EcInfo ecInfo) {
        this.ecInfo = ecInfo;
    }
}
