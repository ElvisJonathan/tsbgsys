/*
package com.tsbg.ecosys.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW3FF5700199E5B7B29135B2D5316D4344";//额外的盐

    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";//负荷

    */
/**
     /**
     * get jwt String of object
     * @param object
     *            the POJO object
     * @param maxAge
     *            the milliseconds of life time
      HEAD
     * @return the jwt token
     *//*
//     * @return the jwt token*/
//
//
//    public static <T> String sign(T object, long maxAge) {
//        try {
//            final JWTSigner signer = new JWTSigner(SECRET);
//            final Map<String, Object> claims = new HashMap<String, Object>();
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonString = mapper.writeValueAsString(object);
//            claims.put(PAYLOAD, jsonString);
//            claims.put(EXP, System.currentTimeMillis() + maxAge);//失效时间
//            return signer.sign(claims);
//        } catch(Exception e) {
//            return null;
//        }
//    }
//
//
///**
//     * get the object of jwt if not expired
//     * @param jwt
//     * @return POJO object
//     *//*
//    /* *
//     * get the object of jwt if not expired
//     * @param jwt
//     * @return POJO object*/
//
//
//    public static<T> T unsign(String jwt, Class<T> classT) {
//        final JWTVerifier verifier = new JWTVerifier(SECRET);
//        try {
//            final Map<String,Object> claims= verifier.verify(jwt);
//            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
//                long exp = (Long)claims.get(EXP);
//                long currentTimeMillis = System.currentTimeMillis();
//                if (exp > currentTimeMillis) {
//                    String json = (String)claims.get(PAYLOAD);
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    return objectMapper.readValue(json, classT);//表示可用
//                }
//            }
//            return null;//表示失效
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}*/
//}
