package com.auto.di.guan.utils;

import android.text.TextUtils;
import android.util.Log;

import com.auto.di.guan.BaseApp;
import com.auto.di.guan.entity.Pro;
import com.auto.di.guan.net.NetSendMessage;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.List;

public class HuanXinUtil {

    private static String TAG = "HuanXinUtil";
    public  static EMMessageListener msgListener = new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            //收到消息
            EMMessage message = messages.get(0);
            EMTextMessageBody body = (EMTextMessageBody) message.getBody();
            LogUtils.e(TAG, "onMessageReceived------"+(body.getMessage()) );

            if (!TextUtils.isEmpty(body.getMessage())
                    && body.getMessage().contains("waterLogin")) {


                LogUtils.e(TAG, "onMessageReceived------收到登陆消息");
                NetSendMessage.sendLoginMessage();

//                Pro pro = new Gson().fromJson(body.getMessage(), Pro.class);
//
//                if (pro != null && TextUtils.isEmpty(pro.event) && pro.event.equals("waterLogin")) {
//                    LogUtils.e(TAG, "onMessageReceived------收到登陆消息");
//                    NetSendMessage.sendLoginMessage();
//                }

//                Pro pro = new Pro();
//                Pro.Message msg = new Pro.Message();
//                msg.loginId = BaseApp.getUser().getUserId()+"";
//                pro.event = "waterLogin";
//                pro.content = msg;
//                LogUtils.e(TAG, "发送消息------"+BaseApp.getUser().getParentLoginName() );
//                EMMessage emMessage = EMMessage.createTxtSendMessage(new Gson().toJson(pro), BaseApp.getUser().getParentLoginName());
//                EMClient.getInstance().chatManager().sendMessage(emMessage);
            }
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
            LogUtils.e(TAG, "onCmdMessageReceived------"+(new Gson().toJson(messages)) );
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
            LogUtils.e(TAG, "onMessageRead------"+(new Gson().toJson(messages)) );
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
        }
        @Override
        public void onMessageRecalled(List<EMMessage> messages) {
            //消息被撤回
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
            LogUtils.e(TAG, "onMessageChanged------");
        }
    };


    public static void login() {

        LogUtils.e(TAG, "user = "+BaseApp.getUser().getLoginName()+ "password = "+ BaseApp.getUser().getPassword());
        EMClient.getInstance().login(BaseApp.getUser().getLoginName(),BaseApp.getUser().getPassword(),new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                LogUtils.e(TAG, "登录聊天服务器成功！");
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
//                EMMessage emMessage = EMMessage.createTxtSendMessage("收到111", "100");
//                EMClient.getInstance().chatManager().sendMessage(emMessage);
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                LogUtils.e(TAG, "登录聊天服务器失败！"+message);
            }
        });

        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }


    public static void stop(){
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }


//    public static void sendMessage(String json) {
//        LogUtils.e(TAG, "     sendMessage   ----->"+json);
//        EMMessage emMessage = EMMessage.createTxtSendMessage(json, BaseApp.getUser().getParentLoginName());
//        EMClient.getInstance().chatManager().sendMessage(emMessage);
//    }
}
