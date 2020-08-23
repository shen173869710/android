package com.auto.di.guan;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.auto.di.guan.basemodel.model.respone.BaseRespone;
import com.auto.di.guan.basemodel.model.respone.LoginRespone;
import com.auto.di.guan.basemodel.presenter.LoginPresenter;
import com.auto.di.guan.basemodel.view.ILoginView;
import com.auto.di.guan.db.ControlInfo;
import com.auto.di.guan.db.DeviceInfo;
import com.auto.di.guan.db.User;
import com.auto.di.guan.db.sql.DeviceInfoSql;
import com.auto.di.guan.db.sql.UserSql;
import com.auto.di.guan.entity.ElecEvent;
import com.auto.di.guan.entity.Entiy;
import com.auto.di.guan.mqtt.MqttSimple;
import com.auto.di.guan.utils.LogUtils;
import com.auto.di.guan.utils.NoFastClickUtils;
import com.auto.di.guan.utils.ToastUtils;
import com.auto.di.guan.view.XEditText;
import com.google.gson.Gson;
import com.trello.rxlifecycle3.LifecycleTransformer;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/**
 * 设备激活
 */
public class ActivationActivity extends IBaseActivity<LoginPresenter> implements ILoginView{

	@BindView(R.id.login_name)
	XEditText loginName;
	@BindView(R.id.login_pwd)
	XEditText loginPwd;
	@BindView(R.id.activiation)
	Button activiation;

	private MqttAndroidClient mqttAndroidClient;
	MqttSimple mqttSimple;
	@Override
	protected int setLayout() {
		return R.layout.activity_activation;
	}

	@Override
	protected void init() {
//		mqttAndroidClient = new MqttAndroidClient(getApplicationContext(), Config.serverUri, Config.clientId);
//		mqttSimple = new MqttSimple(mqttAndroidClient);
//		mqttSimple.init();
	}

	@Override
	protected void setListener() {
		activiation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(NoFastClickUtils.isFastClick()){
					return;
				}
				String name = loginName.getText().toString().trim();
				name = "13300000000";
				if (name == null && TextUtils.isEmpty(name)) {
					Toast.makeText(ActivationActivity.this, "请输入账号", Toast.LENGTH_LONG).show();
					return;
				}
				String pwd = loginPwd.getText().toString().trim();
				pwd = "123456";
				if (pwd == null && TextUtils.isEmpty(pwd)) {
					Toast.makeText(ActivationActivity.this, "请输入密码", Toast.LENGTH_LONG).show();
					return;
				}
				mPresenter.doDeviceActivation(name,pwd);
			}
		});
	}

	@Override
	protected LoginPresenter createPresenter() {
		return new LoginPresenter();
	}



	@Override
	public Activity getActivity() {
		return this;
	}


	@Override
	public void showDialog() {

	}

	@Override
	public void dismissDialog() {

	}

	@Override
	public void loginSuccess(BaseRespone respone) {

	}

	@Override
	public void loginFail(Throwable error, Integer code, String msg) {

	}

	@Override
	public void activationSuccess(BaseRespone respone) {
        LogUtils.e("---------",""+(new Gson().toJson(respone)));
        if (respone.getData() != null) {
			LoginRespone lr = (LoginRespone) respone.getData();

			if (lr.getSysRes() != null) {
				User user = lr.getSysRes();
				Entiy.GRID_COLUMNS =3;
				Entiy.GRID_ROW = 3;
				UserSql.insertUser(lr.getSysRes());
				BaseApp.setUser(lr.getSysRes());
			}
			if (lr.getValveDeviceInfos() != null) {
				List<DeviceInfo> deviceInfos =  lr.getValveDeviceInfos();
				DeviceInfoSql.insertDeviceInfoList(deviceInfos);
			}
			startActivity(new Intent(ActivationActivity.this, LoginActivity.class));
			finish();
		}
	}

	@Override
	public void activationFail(Throwable error, Integer code, String msg) {
        LogUtils.e("---------",""+msg);
		ToastUtils.showLongToast(""+msg);
	}


	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onElecEvent(ElecEvent elecEvent) {

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//		mqttAndroidClient.unregisterResources();
	}
}
