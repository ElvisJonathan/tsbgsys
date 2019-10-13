package com.tsbg.ecosys.vo;

import java.util.List;

public class PowerVo {
    private List<Integer> arr;

    private String[] arr2;

    public PowerVo() {
    }

    public PowerVo(List<Integer> arr, String[] arr2) {
        this.arr = arr;
        this.arr2 = arr2;
    }

    public List<Integer> getArr() {
        return arr;
    }

    public void setArr(List<Integer> arr) {
        this.arr = arr;
    }

    public String[] getArr2() {
        return arr2;
    }

    public void setArr2(String[] arr2) {
        this.arr2 = arr2;
    }
}
