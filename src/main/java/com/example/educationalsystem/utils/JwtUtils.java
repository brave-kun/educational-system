package com.example.educationalsystem.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/9 15:13
 */

public class JwtUtils {

    public static final String SIGN = "kun";

    /**
     * 生成Token，header.payload.signature
     */
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, 24);//24小时过期
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k, v)->{
            builder.withClaim(k,v);
        });

        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));

        return token;

    }

    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    public static boolean verifyBool(String token){
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取token中payload信息
     */
    public static DecodedJWT getTokenInfo(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }

    /**
     * 获取指定字段信息
     * @param token
     * @param claim
     * @return
     */
    public static String getClaimField(String token,String claim){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(claim).asString();
        }catch (JWTDecodeException e){
            e.printStackTrace();
            return  null;
        }
    }

    /**
     * 获取用户名studentId,teacherId,adminId
     *
     * @param token token中包含了用户名
     * @return
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }


}

