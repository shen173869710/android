package com.auto.di.guan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.auto.di.guan.ControlBindActivity;
import com.auto.di.guan.R;
import com.auto.di.guan.adapter.MyGridAdapter;
import com.auto.di.guan.db.DeviceInfo;
import com.auto.di.guan.db.sql.DeviceInfoSql;
import com.auto.di.guan.entity.AdapterEvent;
import com.auto.di.guan.entity.Entiy;
import com.auto.di.guan.utils.LogUtils;
import com.auto.di.guan.utils.ShareUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FragmentTab1 extends BaseFragment {
    private GridView mGridView;
    private View view;
    private MyGridAdapter adapter;
    private List<DeviceInfo> deviceInfos = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_1, null);
        deviceInfos = DeviceInfoSql.queryDeviceList();
        mGridView = (GridView) view.findViewById(R.id.fragment_1_gridview);
        adapter = new MyGridAdapter(getActivity(), deviceInfos);
        mGridView.setAdapter(adapter);
        mGridView.setNumColumns(Entiy.GRID_COLUMNS);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                if ( deviceInfos.get(position).getDeviceStatus() == Entiy.DEVEICE_BIND) {
                        Intent intent = new Intent(getActivity(), ControlBindActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("info", deviceInfos.get(position));
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
                }else {
                    showToast("无可用的设备");
                }
            }
        });
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        deviceInfos = DeviceInfoSql.queryDeviceList();
        if (adapter != null)
        adapter.setData(deviceInfos);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        deviceInfos = DeviceInfoSql.queryDeviceList();
        if (adapter != null)
        adapter.setData(deviceInfos);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAdapterUpdate(AdapterEvent event) {
        LogUtils.e("------", "Main");
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
