package com.auto.di.guan.rtm;


import com.auto.di.guan.db.ControlInfo;
import com.auto.di.guan.db.GroupInfo;
import com.auto.di.guan.jobqueue.event.LoginEvent;
import com.auto.di.guan.utils.LogUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 *     解析消息
 */
public class MessageParse {
    public static final String TAG = "MessageParse";

    public static void praseData(String data, String peerId) {
        MessageInfo info = new Gson().fromJson(data, MessageInfo.class);
        if (info == null) {
            LogUtils.e(TAG, "gson 数据解析异常");
            return;
        }
        switch (info.getType()) {
            case MessageEntiy.TYPE_LOGIN:
                // 登录
                MessageSend.doLogin(peerId);
                break;
            case MessageEntiy.TYPE_LOGOUT:
                // 登出

                break;
            case MessageEntiy.TYPE_SINGLE_READ:
                // 单个操作 读
            case MessageEntiy.TYPE_SINGLE_OPEN:
                // 单个操作 开
            case MessageEntiy.TYPE_SINGLE_CLOSE:
                // 单个操作 关
                if (info.getControlInfos() != null) {
                    dealGroup(info.getControlInfos());
                }
                break;
            case MessageEntiy.TYPE_GROUP_OPEN:
                // 单组操作 开
                break;
            case MessageEntiy.TYPE_GROUP_CLOSE:
                // 单组操作 关
                break;
            case MessageEntiy.TYPE_AUTO_OPEN:
                // 单组自动轮灌开
                break;
            case MessageEntiy.TYPE_AUTO_STOP:
                // 单组自动轮灌 暂停
                break;
            case MessageEntiy.TYPE_AUTO_START:
                // 单组自动轮灌 开始
                break;
            case MessageEntiy.TYPE_AUTO_NEXT:
                // 单组自动轮灌 下一组

                break;
            case MessageEntiy.TYPE_MESSAGE:

                break;
        }
    }

    /**
     *   处理单个操作
     */
    public static void dealSingle(ControlInfo controlInfo) {

    }

    /**
     *  处理多组操作
     */
    public static void dealGroup(List<ControlInfo>list) {
    }

    /**
     *  处理自动轮灌
     */
    public static void dealAuto(List<ControlInfo>list, List<GroupInfo>infos) {

    }
}
