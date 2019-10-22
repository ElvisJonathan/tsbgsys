package com.tsbg.ecosys.ecoService;



public interface JwtUsedOnceService {

    int selectByUsedOnce(String usedOnce);

    int insert(String usedOnce);
}
