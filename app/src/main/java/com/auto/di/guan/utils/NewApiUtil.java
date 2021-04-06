package com.auto.di.guan.utils;

import android.text.TextUtils;

import com.auto.di.guan.api.ApiUtil;
import com.auto.di.guan.api.HttpManager;
import com.auto.di.guan.basemodel.model.respone.BaseRespone;
import com.google.gson.Gson;

public class NewApiUtil {
    private static String TAG = "NewApiUtil";

    public static void getToken() {
        HttpManager.newApi(ApiUtil.createApiService("http://openapi.ecois.info").
                getToken("vn8WKjFPgKhoyN0k", "iZ5QmduqPTG5CpTkvALizYlcP$TYIoQ*"), new HttpManager.OnResultListener() {
            @Override
            public void onSuccess(BaseRespone respone) {
                LogUtils.e(TAG, "respone ==" +respone.getToken());

                if (respone != null && !TextUtils.isEmpty(respone.getToken())) {
                    ApiUtil.authorization = respone.getToken();
                    getDeviceInfo("18092100088696");

                }
            }
            @Override
            public void onError(Throwable error, Integer code,String msg) {
                LogUtils.e("----", msg);
            }
        });

    }


    public static void getDeviceList() {
        HttpManager.newApi(ApiUtil.createApiService("http://openapi.ecois.info").
                getDeviceList(1, 200), new HttpManager.OnResultListener() {
            @Override
            public void onSuccess(BaseRespone respone) {
                LogUtils.e("----", new Gson().toJson(respone));
            }
            @Override
            public void onError(Throwable error, Integer code,String msg) {
                LogUtils.e("----", msg);
            }
        });

    }


    public static void getDeviceInfo(String device) {
        HttpManager.newApi(ApiUtil.createApiService("http://openapi.ecois.info").
                getDeviceInfo(device), new HttpManager.OnResultListener() {
            @Override
            public void onSuccess(BaseRespone respone) {
                LogUtils.e("----", new Gson().toJson(respone));

                getDeviceData("18092100088696");
            }
            @Override
            public void onError(Throwable error, Integer code,String msg) {
                LogUtils.e("----", msg);
            }
        });
    }

    public static void getDeviceData(String device) {
        HttpManager.newApi(ApiUtil.createApiService("http://openapi.ecois.info").
                getDeviceData(device), new HttpManager.OnResultListener() {
            @Override
            public void onSuccess(BaseRespone respone) {
                LogUtils.e("----", new Gson().toJson(respone));
            }
            @Override
            public void onError(Throwable error, Integer code,String msg) {
                LogUtils.e("----", msg);
            }
        });
    }


}
