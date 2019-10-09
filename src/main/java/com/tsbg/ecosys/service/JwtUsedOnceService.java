package com.tsbg.ecosys.service;



public interface JwtUsedOnceService {

    int selectByUsedOnce(String usedOnce);

    int insert(String usedOnce);
}
