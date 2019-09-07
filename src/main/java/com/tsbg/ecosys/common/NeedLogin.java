package com.tsbg.ecosys.common;

import java.lang.annotation.*;

/**
 * @author wzh
 * @version 1.0.0
 * @date 2019 19:16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedLogin {
}
