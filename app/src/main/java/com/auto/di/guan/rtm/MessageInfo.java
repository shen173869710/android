package com.auto.di.guan.rtm;


import com.auto.di.guan.db.ControlInfo;
import com.auto.di.guan.db.GroupInfo;
import com.google.gson.Gson;

import java.util.List;

public class MessageInfo {


    private int type;
    private List<ControlInfo> controlInfos;
    private List<GroupInfo>groupInfos;

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



    public String toJson() {
        return new Gson().toJson(this);
    }
}
