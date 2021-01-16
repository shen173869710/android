package com.auto.di.guan.rtm;


import com.auto.di.guan.BaseApp;
import com.auto.di.guan.db.ControlInfo;
import com.auto.di.guan.db.DeviceInfo;
import com.auto.di.guan.db.GroupInfo;
import com.auto.di.guan.db.sql.ControlInfoSql;
import com.auto.di.guan.db.sql.DeviceInfoSql;
import com.auto.di.guan.db.sql.GroupInfoSql;
import com.auto.di.guan.entity.CmdStatus;
import com.auto.di.guan.entity.Entiy;
import com.auto.di.guan.entity.RtmDevice;
import com.auto.di.guan.socket.SocketResult;
import com.auto.di.guan.utils.GzipUtil;
import com.auto.di.guan.utils.LogUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class MessageSend {

    private static final String TAG = "MessageSend";
    public static void send(MessageInfo info) {
//        int size = info.getDeviceInfos().size();
//        for (int i = 0; i < size; i++) {
//            DeviceInfo deviceInfo = info.getDeviceInfos().get(i);
//            deviceInfo.setDeviceImagePath(null);
//            deviceInfo.setCreateBy(null);
//            deviceInfo.setRemark(null);
//            deviceInfo.setCreateTime(null);
//
//            List<ControlInfo> controlInfos = deviceInfo.getValveDeviceSwitchList();
//            int cSize = controlInfos.size();
//            for (int j = 0; j < cSize; j++) {
//                ControlInfo controlInfo = controlInfos.get(j);
//                controlInfo.setValveImgagePath(null);
//                controlInfo.setCreateBy(null);
//                controlInfo.setUpdateBy(null);
//                controlInfo.setUpdateTime(null);
//            }
//        }


        String src = info.toJson();
        LogUtils.e(TAG, "发送数据的长度"+src.length());
            String data = GzipUtil.gzip(info.toJson());
            LogUtils.e(TAG, "压缩后的数据 =="+data);
        LogUtils.e(TAG, "压缩后的数据长度 =="+data.length());
            BaseApp.getInstance().getChatManager().sendPeerMessage(data);

    }

    /**
     *  登录
     */
    public static void syncLogin(String managerId) {
        MessageInfo info  = new MessageInfo();
        info.setDeviceInfos(DeviceInfoSql.queryDeviceList());
        info.setGroupInfos(GroupInfoSql.queryGroupList());
        info.setType(MessageEntiy.TYPE_LOGIN);
        info.setCloumn(Entiy.GUN_COLUMN);
        send(info);
    }

    /**
     *  登出
     */
    public static void syncLogout() {
        MessageInfo info  = new MessageInfo();
        info.setType(MessageEntiy.TYPE_LOGOUT);
        send(info);
    }
    /**
     *  同步单个操作数据
     */
    public static void syncSingle(int type, ControlInfo controlInfo) {
        LogUtils.e(TAG, "同步单个操作信息 type ="+type);
        MessageInfo info = new MessageInfo();
        info.setType(type);
        info.setControlInfo(ControlInfoSql.findControlById(controlInfo.getValveId()));
        send(info);
    }

    /**
     *         更新单组
     * @param groupInfo
     */
    public static void syncGroup(int type, GroupInfo groupInfo) {
        LogUtils.e(TAG, "同步单组操作信息");
        MessageInfo info = new MessageInfo();
        info.setType(type);
        info.setGroupInfo(groupInfo);
        List<ControlInfo>controlInfos = ControlInfoSql.queryControlList(groupInfo.getGroupId());
        info.setControlInfos(controlInfos);
        send(info);
    }

    /**
     *   同步轮灌命令执行
     */
    public static void syncAuto(int type) {
        MessageInfo info = new MessageInfo();
        info.setType(type);
        info.setDeviceInfos(DeviceInfoSql.queryDeviceList());
        info.setGroupInfos(GroupInfoSql.queryGroupList());
        send(info);
    }

    /**
     *  同步阀门的状态
     */
    public static void syncAutoStatus() {
        MessageInfo info = new MessageInfo();
        info.setType(MessageEntiy.TYPE_AUTO_STATUS);
        info.setDeviceInfos(DeviceInfoSql.queryDeviceList());
        info.setGroupInfos(GroupInfoSql.queryGroupList());
        send(info);
    }

    /**
     *        自动轮灌关闭
     */
    public static void syncAutoClose() {
        MessageInfo info = new MessageInfo();
        info.setType(MessageEntiy.TYPE_AUTO_CLOSE);
        info.setDeviceInfos(DeviceInfoSql.queryDeviceList());
        info.setGroupInfos(GroupInfoSql.queryGroupList());
        send(info);
    }

    /**
     *        同步操作信息数据
     * @param cmdStatus
     */
    public static void syncOptionInfo(CmdStatus  cmdStatus) {
       // LogUtils.e(TAG, "同步单个操作通讯信息");
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
        LogUtils.e(TAG, "同步创建分组信息");
        MessageInfo info = new MessageInfo();
        info.setType(type);
        info.setDeviceInfos(DeviceInfoSql.queryDeviceList());
        info.setGroupInfos(GroupInfoSql.queryGroupList());
        send(info);
    }

    /**
     *        同步所有水泵信息
     * @param results
     */
    public static void syncBengInfo(List<SocketResult>results, int type){
        MessageInfo info = new MessageInfo();
        info.setType(type);
        info.setSocketResults(results);
        send(info);
    }

    /**
     *   同步自动轮灌时间
     */
    public static void syncAutoTimeCount(GroupInfo groupInfo) {
        LogUtils.e(TAG, "同步自动轮灌时间");
        MessageInfo info = new MessageInfo();
        info.setType(MessageEntiy.TYPE_TIME_COUNT);
        info.setGroupInfo(groupInfo);
        send(info);
    }

    /**
     *   同步点击事件
     */
    public static void syncClickEvent() {
        MessageInfo info = new MessageInfo();
        info.setType(MessageEntiy.TYPE_CLICK);
        send(info);
    }

    /**
     *   同步activity 点击事件
     */
    public static void syncActivityEvent() {
        MessageInfo info = new MessageInfo();
        info.setType(MessageEntiy.TYPE_ACTIVITY);
        send(info);
    }

    /**
     *         压缩字符串
     * @param str
     * @return
     * @throws IOException
     */
    public static String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        return out.toString("ISO-8859-1");
    }

}
