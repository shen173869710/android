package com.auto.di.guan.rtm;


import com.auto.di.guan.BaseApp;
import com.auto.di.guan.db.ControlInfo;
import com.auto.di.guan.db.GroupInfo;
import com.auto.di.guan.db.sql.ControlInfoSql;
import com.auto.di.guan.db.sql.DeviceInfoSql;
import com.auto.di.guan.db.sql.GroupInfoSql;
import com.auto.di.guan.entity.CmdStatus;

import java.util.List;

public class MessageSend {
    public static void send(MessageInfo info) {
        BaseApp.getInstance().getChatManager().sendPeerMessage(info.toJson());
    }

    /**
     *  登录
     */
    public static void syncLogin(String managerId) {
        MessageLoginInfo info  = new MessageLoginInfo();
        info.setType(MessageEntiy.TYPE_LOGIN);
        send(info);
    }


    /**
     *  同步单个操作数据
     */
    public static void syncSingle(int type, ControlInfo controlInfo) {
        MessageInfo info = new MessageInfo();
        info.setType(type);
        info.setControlInfo(controlInfo);
        send(info);
    }

    /**
     *         更新单组
     * @param groupInfo
     */
    public static void syncGroup(int type, GroupInfo groupInfo) {
        MessageInfo info = new MessageInfo();
        info.setType(type);
        info.setGroupInfo(groupInfo);
        List<ControlInfo>controlInfos = ControlInfoSql.queryControlList(groupInfo.getGroupId());
        info.setControlInfos(controlInfos);
        send(info);
    }

    /**
     *        自动轮灌开
     */
    public static void syncAuto(int type, GroupInfo groupInfo) {
        MessageInfo info = new MessageInfo();
        info.setType(type);
        info.setControlInfos(ControlInfoSql.queryControlList());
        info.setGroupInfos(GroupInfoSql.queryGroupList());
        send(info);
    }

    /**
     *        自动轮灌关闭
     * @param type
     */
    public static void syncAutoClose(int type) {
        MessageInfo info = new MessageInfo();
        info.setType(MessageEntiy.TYPE_AUTO_CLOSE);
        info.setControlInfos(ControlInfoSql.queryControlList());
        info.setGroupInfos(GroupInfoSql.queryGroupList());
        send(info);
    }

    /**
     *        同步操作信息数据
     * @param cmdStatus
     */
    public static void syncOptionInfo(CmdStatus  cmdStatus) {
        MessageInfo info = new MessageInfo();
        info.setType(MessageEntiy.TYPE_MESSAGE);
        info.setCmdStatus(cmdStatus);
        send(info);
    }

    /**
     *        同步所有组的信息和设备信息
     * @param type
     */
    public static void syncGroupAndDeviceInfo(int type){
        MessageInfo info = new MessageInfo();
        info.setType(type);
        info.setDeviceInfos(DeviceInfoSql.queryDeviceList());
        info.setGroupInfos(GroupInfoSql.queryGroupList());
        send(info);
    }
}
