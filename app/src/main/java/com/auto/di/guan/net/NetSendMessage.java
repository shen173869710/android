package com.auto.di.guan.net;

import com.auto.di.guan.db.ControlInfo;
import com.auto.di.guan.db.GroupInfo;
import com.auto.di.guan.entity.CmdStatus;

import java.util.List;

public class NetSendMessage {



    /**
     *        单个阀门的状态同步
     * @param controlInfo
     */
    public static void sendSingleOptionMessage(ControlInfo controlInfo){


    }

    /**
     *        同步组的状态
     * @param groupInfo
     */
    public static void sendGroupOptionMessage(GroupInfo groupInfo){

    }


    /**
     *        同步自动轮灌组的状态
     * @param groupInfo
     */
    public static void sendGroupAutoMessage(GroupInfo groupInfo){

    }


    /**
     *        同步执行的状态
     * @param list
     */
    public static void sendStatusMessage(List<CmdStatus> list){

    }

}
