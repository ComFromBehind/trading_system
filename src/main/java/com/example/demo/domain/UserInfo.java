package com.example.demo.domain;

public class UserInfo {
    private static String userInfo;

    public static void setUserInfo(String userInfo) {
        UserInfo.userInfo = userInfo;
    }

    public static String getUserInfo() {
        return userInfo;
    }
}
