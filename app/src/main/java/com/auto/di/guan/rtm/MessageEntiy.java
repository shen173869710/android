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


    // 自动轮灌开
    public static final int TYPE_AUTO_OPEN = 8;
    // 自动轮灌 暂停
    public static final int TYPE_AUTO_STOP = 9;
    // 自动轮灌 开始
    public static final int TYPE_AUTO_START = 10;
    // 自动轮灌 关闭
    public static final int TYPE_AUTO_CLOSE = 11;
    // 自动轮灌 下一组
    public static final int TYPE_AUTO_NEXT = 12;
    // 自动轮灌 轮灌时间
    public static final int TYPE_AUTO_TIME = 13;
    // 自动轮灌 设备状态
    public static final int TYPE_AUTO_STATUS = 14;

    // 创建分组
    public static final int TYPE_CREATE_GROUP = 20;
    // 解散所有分组
    public static final int TYPE_DEL_GROUP = 21;
    // 退出当前小组
    public static final int TYPE_EXIT_GROUP = 22;
    // 解散一个小组
    public static final int TYPE_DISS_GROUP = 23;
    // 轮灌设置
    public static final int TYPE_GROUP_LEVEL = 30;
    // 开泵
    public static final int TYPE_BENG_OPEN = 40;
    // 关泵
    public static final int TYPE_BENG_CLOSE = 41;

    // 自动轮灌查询开启
    public static final int TYPE_AUTO_POLL_START = 1113;
    // 自动轮灌查询关闭
    public static final int TYPE_AUTO_POLL_CLOSE = 1114;
    // 轮灌操作相关信息
    public static final int TYPE_MESSAGE = 100;


    /**
     *   tab点击事件
     */
    public static final int TYPE_CLICK = 999999;
    public static final int OPTION_TAB_0 = 100000;
    public static final int OPTION_TAB_1 = 100001;
    public static final int OPTION_TAB_2 = 100002;
    public static final int OPTION_TAB_3 = 100003;
    public static final int OPTION_TAB_4 = 100004;
    public static final int OPTION_TAB_5 = 100005;
    public static final int OPTION_TAB_6 = 100006;
    public static final int OPTION_TAB_7 = 100007;
    public static final int OPTION_TAB_8 = 100008;
    public static final int OPTION_TAB_9 = 100009;


    public static int [] OPTION_TAB = {
            OPTION_TAB_0,
            OPTION_TAB_1,
            OPTION_TAB_2,
            OPTION_TAB_3,
            OPTION_TAB_4,
            OPTION_TAB_5,
            OPTION_TAB_6,
            OPTION_TAB_7,
            OPTION_TAB_8,
            OPTION_TAB_9
    };

}
