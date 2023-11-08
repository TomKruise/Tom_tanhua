package com.tanhua.sso.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;

public class TestJWT {

    public static void main(String[] args) {

        String[] answer = wawawa("abcd");

        for (String s : answer) {
            System.out.println(s);
        }

        System.out.println(answer.length);

//        String secret = "tom";
//
//        Map<String, Object> claims = new HashMap<String, Object>();
//        claims.put("mobile", "12345789");
//        claims.put("id", "2");
//
//        // 生成token
//        String jwt = Jwts.builder()
//                .setClaims(claims) //设置响应数据体
//                .signWith(SignatureAlgorithm.HS256, secret) //设置加密方法和加密盐
//                .compact();
//
//        System.out.println(jwt); //eyJhbGciOiJIUzI1NiJ9.eyJtb2JpbGUiOiIxMjM0NTc4OSIsImlkIjoiMiJ9.VivsfLzrsKFOJo_BdGIf6cKY_7wr2jMOMOIGaFt_tps
//
//        // 通过token解析数据
//        Map<String, Object> body = Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(jwt)
//                .getBody();
//
//        System.out.println(body); //{mobile=12345789, id=2}
    }

    private static String[] wawawa(String str) {
        if (null == str) {
            return null;
        }

        if (str.length() < 2) {
            return new String[] {str};
        }

        List<String> answerList = crack(str);

        return answerList.toArray(new String[answerList.size()]);
    }

    private static List<String> crack(String str) {
        int strLen = str.length();
        List<String> answerList = new ArrayList<>();

        for (int i = 0; i < strLen; i++) {
            boolean[] mark = new boolean[strLen];
            char c = str.charAt(i);

            StringBuilder subStr = new StringBuilder();
            subStr.append(c);
            mark[i] = true;

            labor(answerList, subStr, mark, str);
        }

        return answerList;
    }

    private static void labor(List<String> answerList, StringBuilder subStr, boolean[] mark, String target) {
        int length = target.length();

        if (subStr.length() == length) {
            answerList.add(subStr.toString());
        } else {
            for (int i = 0; i < length; i++) {
                if (false == mark[i]) {
                    boolean[] newMark = Arrays.copyOf(mark, length);
                    StringBuilder newSubStr = new StringBuilder();
                    newSubStr.append(subStr);
                    newSubStr.append(target.charAt(i));
                    newMark[i] = true;

                    labor(answerList, newSubStr, newMark, target);
                }
            }
        }


    }
}