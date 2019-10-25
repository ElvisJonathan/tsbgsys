package com.tsbg.mis.ecoService;



public interface JwtUsedOnceService {

    int selectByUsedOnce(String usedOnce);

    int insert(String usedOnce);
}
