package com.auto.di.guan.utils;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auto.di.guan.BaseApp;
import com.auto.di.guan.MainActivity;
import com.auto.di.guan.R;
import com.auto.di.guan.adapter.FloatStatusAdapter;
import com.auto.di.guan.db.ControlInfo;
import com.auto.di.guan.db.GroupInfo;
import com.auto.di.guan.db.sql.ControlInfoSql;
import com.auto.di.guan.floatWindow.FloatWindow;
import com.auto.di.guan.floatWindow.MoveType;
import com.auto.di.guan.floatWindow.Screen;
import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/25.
 * 悬浮窗显示状态
 */

public class FloatStatusUtil {
    private static FloatStatusUtil instance = new FloatStatusUtil();
    private RecyclerView mListView;
    private FloatStatusAdapter adapter;
    private ArrayList<ControlInfo> infos = new ArrayList<>();
    private View view;
    private TextView textView;
    private DonutProgress donutProgress;

    LinearLayout linearLayout;

    public static synchronized FloatStatusUtil getInstance() {
        return instance;
    }

    private final String TAG = "FloatStatusUtil";

    public void initFloatWindow(Context mContext) {
        view = View.inflate(BaseApp.getInstance(), R.layout.dialog_run_listview, null);
        view.setFocusableInTouchMode(true);
        mListView = (RecyclerView) view.findViewById(R.id.list);
        donutProgress = view.findViewById(R.id.progress);

        linearLayout = view.findViewById(R.id.layout_list);
        textView = view.findViewById(R.id.text);
//        textView.setText("");

        donutProgress.setMax(120);
        donutProgress.setProgress(90);

        List<ControlInfo> list = ControlInfoSql.queryControlList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValveStatus() > 1) {
                if (i <  8) {
                    infos.add(list.get(i));
                }
            }
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListView.getVisibility() == View.VISIBLE) {
                    mListView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    LogUtils.e(TAG, "gon");
                } else {
                    mListView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                    LogUtils.e(TAG, "VISIBLE");
                }
            }
        });
        adapter = new FloatStatusAdapter(infos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
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

    public void show() {
        if (FloatWindow.get(TAG) == null) {
            int size = Math.round(BaseApp.getContext().getResources().getDimension(R.dimen.float_status_size));
            FloatWindow.with(BaseApp.getInstance())
                    .setView(view)
//                    .setWidth(size*8)
                    .setHeight(size)
                    .setX(Screen.width, 0.9f)
                    .setY(Screen.height, 0.5f)
                    .setFilter(true, MainActivity.class)
                    .setMoveType(MoveType.active)
                    .setTag(TAG)
                    .build();
            FloatWindow.get(TAG).show();
        } else {
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

    public void onGroupStatusEvent(GroupInfo info) {
        List<ControlInfo> infos = ControlInfoSql.queryControlList(info.getGroupId());
        if (info.getGroupStatus() == 1) {
            List<ControlInfo> list = ControlInfoSql.queryControlList();
            int size = list.size();
            if (size > 0) {
                infos.clear();
                infos.addAll(list);
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
            if (donutProgress != null) {
                donutProgress.setMax(info.getGroupTime());
                donutProgress.setProgress(info.getGroupRunTime());
                textView.setText("时长:" + info.getGroupTime() + "\n运行:" + info.getGroupRunTime());
            }
        }
    }

    public void onAutoCountEvent(GroupInfo info) {
        if (donutProgress != null) {
            donutProgress.setMax(info.getGroupTime());
            donutProgress.setProgress(info.getGroupRunTime());
            textView.setText("时长:" + info.getGroupTime() + "\n运行:" + info.getGroupRunTime());
        }
    }

}
