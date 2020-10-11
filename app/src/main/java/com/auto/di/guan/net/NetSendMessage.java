package com.auto.di.guan.net;

import com.auto.di.guan.BaseApp;
import com.auto.di.guan.api.ApiUtil;
import com.auto.di.guan.basemodel.model.request.BaseRequest;
import com.auto.di.guan.basemodel.model.respone.BaseRespone;
import com.auto.di.guan.db.ControlInfo;
import com.auto.di.guan.db.DeviceInfo;
import com.auto.di.guan.db.GroupInfo;
import com.auto.di.guan.db.sql.DeviceInfoSql;
import com.auto.di.guan.db.sql.GroupInfoSql;
import com.auto.di.guan.db.sql.UserActionSql;
import com.auto.di.guan.entity.BaseMessage;
import com.auto.di.guan.entity.CmdStatus;
import com.auto.di.guan.entity.MessageInfo;
import com.auto.di.guan.entity.Pro;
import com.auto.di.guan.utils.LogUtils;
import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.List;
import java.util.TreeMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NetSendMessage {

    /**
     *        发送登陆成功同步数据
     * @param
     */
    public static void sendLoginMessage(){
        List<DeviceInfo> deviceInfos = DeviceInfoSql.queryDeviceList();
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setEvent(1);
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setDevices(deviceInfos);
        messageInfo.setActions(UserActionSql.queryUserActionlList());
        messageInfo.setGroups(GroupInfoSql.queryGroupList());
        messageInfo.setLoginId(BaseApp.getUser().getUserId());
        baseMessage.setData(messageInfo);
        DisposableObserver disposableObserver = new DisposableObserver<BaseRespone>() {
            @Override
            public void onNext(BaseRespone baseRespone) {
                LogUtils.e("doSyncData", "response ======" + (new Gson().toJson(baseRespone)));
                Pro pro = new Pro();
                Pro.Message msg = new Pro.Message();
                msg.loginId = BaseApp.getUser().getUserId()+"";
                pro.event = "waterLogin";
                pro.content = msg;
                LogUtils.e("NetSendMessage", "发送消息------"+BaseApp.getUser().getParentLoginName() );
                EMMessage emMessage = EMMessage.createTxtSendMessage(new Gson().toJson(pro), BaseApp.getUser().getParentLoginName());
                EMClient.getInstance().chatManager().sendMessage(emMessage);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
            }
        };

        ApiUtil.createApiService().syncData(messageInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onTerminateDetach()
                .subscribeWith(disposableObserver);
    }


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



    public static void doSyncData(String json) {
        DisposableObserver disposableObserver = new DisposableObserver<BaseRespone>() {
            @Override
            public void onNext(BaseRespone baseRespone) {
                LogUtils.e("doSyncData", "response ======" + (new Gson().toJson(baseRespone)));

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
            }
        };

//        TreeMap<String, Object> treeMap = new TreeMap<>();
//        treeMap.put("data",json);
//        ApiUtil.createMerchantAPIService().syncData(BaseRequest.toMerchantTreeMap(treeMap))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .onTerminateDetach()
//                .subscribeWith(disposableObserver);
    }

}
