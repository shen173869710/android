package com.auto.di.guan.rtm;


import com.auto.di.guan.db.ControlInfo;
import com.auto.di.guan.db.GroupInfo;
import com.auto.di.guan.db.UserAction;
import com.auto.di.guan.entity.CmdStatus;
import com.google.gson.Gson;

import java.util.List;

public class MessageInfo {


    private int type;
    private ControlInfo controlInfo;
    private GroupInfo groupInfo;
    private List<ControlInfo> controlInfos;
    private List<GroupInfo>groupInfos;
    private List<CmdStatus> cmdStatuses;



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

    public List<CmdStatus> getCmdStatuses() {
        return cmdStatuses;
    }

    public void setCmdStatuses(List<CmdStatus> cmdStatuses) {
        this.cmdStatuses = cmdStatuses;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
