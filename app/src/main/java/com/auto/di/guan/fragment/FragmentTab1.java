package com.auto.di.guan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auto.di.guan.ControlBindActivity;
import com.auto.di.guan.R;
import com.auto.di.guan.adapter.MyGridAdapter;
import com.auto.di.guan.db.DeviceInfo;
import com.auto.di.guan.db.sql.DeviceInfoSql;
import com.auto.di.guan.entity.Entiy;
import com.auto.di.guan.event.BindIdEvent;
import com.auto.di.guan.utils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class FragmentTab1 extends BaseFragment {
    private RecyclerView recyclerView;
    private View view;
    private MyGridAdapter adapter;
    private List<DeviceInfo> deviceInfos = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_1, null);
        deviceInfos = DeviceInfoSql.queryDeviceList();
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_1_gridview);
        deviceInfos = DeviceInfoSql.queryDeviceList();
        adapter = new MyGridAdapter(deviceInfos);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), Entiy.GUN_COLUMN));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
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
    public void refreshData() {
        if (adapter != null) {
            adapter.setNewData(DeviceInfoSql.queryDeviceList());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBindIdEvent(BindIdEvent event) {
        LogUtils.e("FragmentTab1", "onBindIdEvent（）");
       refreshData();
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
