package com.auto.di.guan.rtm;


import com.auto.di.guan.db.ControlInfo;
import com.auto.di.guan.db.DeviceInfo;
import com.auto.di.guan.db.GroupInfo;
import com.auto.di.guan.db.sql.DeviceInfoSql;
import com.auto.di.guan.entity.CmdStatus;
import com.auto.di.guan.entity.RtmDevice;
import com.auto.di.guan.socket.SocketResult;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MessageInfo {

    /**
     *    管水员的ID
     */
    private Long managerId;
    /**
     *    管水员登录的ID
     */
    private String loginId;
    /**
     *   消息的类型
     */
    private int type;
    /**
     *     item 点击的位置
     */
    private int postion;
    /**
     *     设备的列
     */
    private int cloumn;
    /**
     *   当前显示的页面
     */
    private int index;

    private ControlInfo controlInfo;
    private GroupInfo groupInfo;
    private List<ControlInfo> controlInfos;
    private List<GroupInfo>groupInfos;
    private CmdStatus cmdStatus;

    private List<SocketResult> socketResults;
    private List<DeviceInfo>deviceInfos;

    public ControlInfo getControlInfo() {
        return controlInfo;
    }

    public void setControlInfo(ControlInfo controlInfo) {
        this.controlInfo = controlInfo;
    }

    public GroupInfo getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ControlInfo> getControlInfos() {
        return controlInfos;
    }

    public void setControlInfos(List<ControlInfo> controlInfos) {
        this.controlInfos = controlInfos;
    }

    public List<GroupInfo> getGroupInfos() {
        return groupInfos;
    }

    public void setGroupInfos(List<GroupInfo> groupInfos) {
        this.groupInfos = groupInfos;
    }

    public CmdStatus getCmdStatus() {
        return cmdStatus;
    }

    public void setCmdStatus(CmdStatus cmdStatus) {
        this.cmdStatus = cmdStatus;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public List<DeviceInfo> getDeviceInfos() {
        return deviceInfos;
    }

    public void setDeviceInfos(List<DeviceInfo> deviceInfos) {



        this.deviceInfos = deviceInfos;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public List<SocketResult> getSocketResults() {
        return socketResults;
    }

    public void setSocketResults(List<SocketResult> socketResults) {
        this.socketResults = socketResults;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public int getCloumn() {
        return cloumn;
    }

    public void setCloumn(int cloumn) {
        this.cloumn = cloumn;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }



}