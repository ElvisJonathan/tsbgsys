package com.tsbg.ecosys.ecoModel.bag;

import com.tsbg.ecosys.ecoModel.Epartner;

/**
 * 封装ID数组和epartner
 */
public class HidePackage {

    private Object[] data;

    private Epartner epartner;


    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }

    public Epartner getEpartner() {
        return epartner;
    }

    public void setEpartner(Epartner epartner) {
        this.epartner = epartner;
    }
}
