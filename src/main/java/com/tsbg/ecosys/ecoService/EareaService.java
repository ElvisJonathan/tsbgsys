package com.tsbg.ecosys.ecoService;

public interface EareaService {

    //根据地区名查找对应的地区编号（特殊情况需要用厂区名来查找编号）
    Integer selectAreaNoByAreaName(String area);
}
