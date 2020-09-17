package com.auto.di.guan.rtm;

public class MessageEntiy {


    // 登录
    public static final int TYPE_LOGIN = 1;
    // 登出
    public static final int TYPE_LOGOUT = 2;
    // 单个操作 读
    public static final int TYPE_SINGLE_READ = 3;
    // 单个操作 开
    public static final int TYPE_SINGLE_OPEN= 4;
    // 单个操作 关
    public static final int TYPE_SINGLE_CLOSE = 5;
    // 单组操作 开
    public static final int TYPE_GROUP_OPEN = 6;
    // 单组操作 关
    public static final int TYPE_GROUP_CLOSE = 7;
    // 单组自动轮灌开
    public static final int TYPE_AUTO_OPEN = 8;
    // 单组自动轮灌 暂停
    public static final int TYPE_AUTO_STOP = 9;
    // 单组自动轮灌 开始
    public static final int TYPE_AUTO_START = 10;
    // 单组自动轮灌 关闭
    public static final int TYPE_AUTO_CLOSE = 11;
    // 单组自动轮灌 下一组
    public static final int TYPE_AUTO_NEXT = 12;
    // 自动轮灌查询开启
    public static final int TYPE_AUTO_POLL_START = 13;
    // 自动轮灌查询关闭
    public static final int TYPE_AUTO_POLL_CLOSE = 14;
    // 轮灌操作相关信息
    public static final int TYPE_MESSAGE = 100;

}
