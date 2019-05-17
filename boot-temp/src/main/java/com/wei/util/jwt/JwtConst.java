package com.wei.util.jwt;

public class JwtConst {

//tocker在header中的名字
public static String auth_key = "auth";
//tocker中的前缀
public static String token_prefix = "Bearer_";
//生成token时，签名所用的key
public static String sign_key = "JwtSecret";
//过期时间,ms为单位
public static long expired_ms = 600 * 1000;

}
