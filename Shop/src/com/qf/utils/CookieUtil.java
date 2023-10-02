package com.qf.utils;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CookieUtil {
    public static Cookie createCookie(String key, String value, int time){
        Cookie cookie = null;
        try {
            cookie = new Cookie(key, URLEncoder.encode(value,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cookie.setMaxAge(time);
        return cookie;
    }

    public static void main(String[] args) {

        for (int i = 1; i < 100; i++) {
            DBUtil.commonUpdate("insert into student(username,password,name,sex,age,hobbies) values(?,?,?,?,?,?)","xiaohei"+i,"123123","小黑"+i,"man",23, "football,shop");
        }


    }
}
