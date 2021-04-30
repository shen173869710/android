package com.auto.di.guan.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auto.di.guan.BaseApp;
import com.auto.di.guan.MainActivity;
import com.auto.di.guan.R;
import com.auto.di.guan.adapter.DialogListViewAdapter;
import com.auto.di.guan.adapter.FloatStatusAdapter;
import com.auto.di.guan.db.ControlInfo;
import com.auto.di.guan.db.sql.ControlInfoSql;
import com.auto.di.guan.entity.CmdStatus;
import com.auto.di.guan.floatWindow.FloatWindow;
import com.auto.di.guan.floatWindow.IFloatWindow;
import com.auto.di.guan.floatWindow.MoveType;
import com.auto.di.guan.floatWindow.Screen;
import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2018/7/25.
 *   悬浮窗显示状态
 */

public class FloatStatusUtil {
    private static FloatStatusUtil instance = new FloatStatusUtil();
    private RecyclerView mListView;
    private FloatStatusAdapter adapter;
    private ArrayList<ControlInfo> infos = new ArrayList<>();
    private View view;
    private DonutProgress donutProgress;
    FloatWindow.B b;
    public static synchronized FloatStatusUtil getInstance() {
        return instance;
    }

    private final String TAG = "FloatStatusUtil";
    public void initFloatWindow(Context mContext) {
        view = View.inflate(BaseApp.getInstance(), R.layout.dialog_run_listview, null);
        view.setFocusableInTouchMode(true);
        mListView = (RecyclerView) view.findViewById(R.id.list);
        donutProgress = view.findViewById(R.id.progress);
        donutProgress.setMax(120);
        donutProgress.setProgress(90);

        List<ControlInfo> list = ControlInfoSql.queryControlList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValveStatus() > 1) {
                infos.add(list.get(i));
            }
        }


        view.findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListView.getVisibility() == View.VISIBLE) {
                    mListView.setVisibility(View.GONE);
                    LogUtils.e(TAG, "gon");
                }else {
                    mListView.setVisibility(View.VISIBLE);
                    LogUtils.e(TAG, "VISIBLE");
                    int size = Math.round(BaseApp.getContext().getResources().getDimension(R.dimen.float_status_size));
//                    b.setView(view).setWidth(size*10).build();
                }
            }
        });


        adapter = new FloatStatusAdapter(infos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
//        layoutManager.setStackFromEnd(true);
        mListView.setLayoutManager(layoutManager);
        mListView.setAdapter(adapter);
    }

    public void cleanList() {
        if (adapter != null) {
            infos.clear();
            adapter.notifyDataSetChanged();
        }
    }

    public boolean isShow() {
        if (FloatWindow.get(TAG) == null) {
            return false;
        }
        return FloatWindow.get(TAG).isShowing();
    }

    public void show (){
        if (FloatWindow.get(TAG) == null) {
            int size = Math.round(BaseApp.getContext().getResources().getDimension(R.dimen.float_status_size));
            b = FloatWindow.with(BaseApp.getInstance());
                    b.setView(view)
//                    .setWidth(size*8)
                    .setHeight(size)
                    .setX(Screen.width,0.9f)
                    .setY(Screen.height,0.5f)
//                    .setDesktopShow(true)
                    .setFilter(true, MainActivity.class)
                    .setMoveType(MoveType.active)
                    .setTag(TAG)
                    .build();
            FloatWindow.get(TAG).show();
        }else {
            if (!FloatWindow.get(TAG).isShowing()) {
                FloatWindow.get(TAG).show();
            }
        }
    }

    public void distory() {
        infos.clear();
        adapter.notifyDataSetChanged();
        FloatWindow.destroy(TAG);
    }


}
