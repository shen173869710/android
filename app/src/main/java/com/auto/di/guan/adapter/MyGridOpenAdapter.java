package com.auto.di.guan.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.auto.di.guan.R;
import com.auto.di.guan.db.ControlInfo;
import com.auto.di.guan.db.DeviceInfo;
import com.auto.di.guan.dialog.MainoptionDialog;
import com.auto.di.guan.entity.Entiy;
import com.auto.di.guan.jobqueue.TaskEntiy;
import com.auto.di.guan.jobqueue.TaskManager;
import com.auto.di.guan.jobqueue.task.TaskFactory;
import com.auto.di.guan.utils.NoFastClickUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class MyGridOpenAdapter extends BaseQuickAdapter<DeviceInfo, BaseViewHolder> {
    public MyGridOpenAdapter(List<DeviceInfo> data) {
        super(R.layout.grid_item, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, DeviceInfo deviceInfo) {
        TextView grid_item_device_id = holder.findView(R.id.grid_item_device_id);
        grid_item_device_id.setText(deviceInfo.getDeviceSort()+"");
        grid_item_device_id.setVisibility(View.VISIBLE);
        TextView grid_item_device_name = holder.findView(R.id.grid_item_device_name);
        ImageView grid_item_device = holder.findView(R.id.grid_item_device);
        TextView grid_item_device_value = holder.findView(R.id.grid_item_device_value);


        RelativeLayout grid_item_left_layout = holder.findView(R.id.grid_item_left_layout);
        TextView grid_item_left_sel = holder.findView(R.id.grid_item_left_sel);
        ImageView grid_item_left_image = holder.findView(R.id.grid_item_left_image);
        TextView grid_item_left_alias = holder.findView(R.id.grid_item_left_alias);
        TextView grid_item_left_id = holder.findView(R.id.grid_item_left_id);
        TextView grid_item_left_group = holder.findView(R.id.grid_item_left_group);


        RelativeLayout grid_item_right_layout = holder.findView(R.id.grid_item_right_layout);
        TextView grid_item_right_sel = holder.findView(R.id.grid_item_right_sel);
        ImageView grid_item_right_image = holder.findView(R.id.grid_item_right_image);
        TextView grid_item_right_alias = holder.findView(R.id.grid_item_right_alias);
        TextView grid_item_right_id = holder.findView(R.id.grid_item_right_id);
        TextView grid_item_right_group = holder.findView(R.id.grid_item_right_group);



                /******设备未绑定******/
        if (deviceInfo.getDeviceStatus() == Entiy.DEVEICE_UNBIND) {
            grid_item_device_name.setVisibility(View.INVISIBLE);
            grid_item_device.setVisibility(View.INVISIBLE);
            grid_item_device_value.setVisibility(View.INVISIBLE);
            grid_item_left_layout.setVisibility(View.INVISIBLE);
            grid_item_right_layout.setVisibility(View.INVISIBLE);
        }else {
            if (!TextUtils.isEmpty(deviceInfo.getDeviceName())) {
                grid_item_device_name.setText(deviceInfo.getDeviceName()+"");
                grid_item_device_name.setVisibility(View.VISIBLE);
            }
            grid_item_device_value.setVisibility(View.VISIBLE);
            grid_item_device_value.setText(deviceInfo.getElectricQuantity()+"%");
            grid_item_device.setVisibility(View.VISIBLE);
            grid_item_left_layout.setVisibility(View.VISIBLE);
            grid_item_left_sel.setVisibility(View.GONE);

            ControlInfo controlInfo_0 = deviceInfo.getValveDeviceSwitchList().get(0);
            if (controlInfo_0.getValveGroupId() == 0) {
                grid_item_left_group.setVisibility(View.GONE);
            }else {
                grid_item_left_group.setVisibility(View.VISIBLE);
                grid_item_left_group.setText(controlInfo_0.getValveGroupId()+"");
            }

            if (controlInfo_0.getValveStatus() == 0) {
                grid_item_left_image.setVisibility(View.INVISIBLE);
                grid_item_left_layout.setOnClickListener(null);
            }else {
                grid_item_left_image.setVisibility(View.VISIBLE);
                grid_item_left_image.setImageResource(Entiy.getImageResource(controlInfo_0.getValveStatus()));
                grid_item_left_id.setText(controlInfo_0.getValveName()+"");
                grid_item_left_alias.setText(controlInfo_0.getValveAlias()+"");

                grid_item_left_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(NoFastClickUtils.isFastClick()){
                            return;
                        }
                        openDevice(controlInfo_0);
                    }
                });
            }
            ControlInfo controlInfo_1 = deviceInfo.getValveDeviceSwitchList().get(1);
            grid_item_right_layout.setVisibility(View.VISIBLE);
            grid_item_right_sel.setVisibility(View.GONE);

            if (controlInfo_1.getValveGroupId() == 0) {
                grid_item_right_group.setVisibility(View.GONE);
            }else {
                grid_item_right_group.setVisibility(View.VISIBLE);
                grid_item_right_group.setText(controlInfo_1.getValveGroupId()+"");
            }
            if (controlInfo_1.getValveStatus() == 0) {
                grid_item_right_image.setVisibility(View.INVISIBLE);
                grid_item_right_layout.setOnClickListener(null);
            }else {
                grid_item_right_image.setVisibility(View.VISIBLE);
                grid_item_right_image.setImageResource(Entiy.getImageResource(controlInfo_1.getValveStatus()));
                grid_item_right_id.setText(controlInfo_1.getValveName()+"");
                grid_item_right_alias.setText(controlInfo_1.getValveAlias()+"");


                grid_item_right_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(NoFastClickUtils.isFastClick()){
                            return;
                        }
                       openDevice(controlInfo_1);
                    }
                });
            }

            if (controlInfo_0.getValveStatus() == 0) {
                grid_item_left_layout.setVisibility(View.INVISIBLE);
            }else{
                grid_item_left_layout.setVisibility(View.VISIBLE);
            }

            if (controlInfo_1.getValveStatus() == 0) {
                grid_item_right_layout.setVisibility(View.INVISIBLE);
            }else{
                grid_item_right_layout.setVisibility(View.VISIBLE);
            }
        }
    }

        private void openDevice(final ControlInfo controlInfo) {
        String status = "关闭";
        if (controlInfo.getValveStatus() == Entiy.CONTROL_STATUS＿RUN) {
            status = "开启";
        }
        MainoptionDialog.ShowDialog((Activity) getContext(),controlInfo , "手动操作",status,new MainoptionDialog.ItemClick() {
            @Override
            public void onItemClick(int index) {
                /**
                 *    index = 0  读
                 *    index = 1  开阀
                 *    index = 2  关阀
                 */
                if (index == 0) {
                    TaskFactory.createReadSingleTask(controlInfo, TaskEntiy.TASK_OPTION_READ ,Entiy.ACTION_TYPE_4);
                    TaskFactory.createReadEndTask(TaskEntiy.TASK_OPTION_READ,controlInfo);
                    TaskManager.getInstance().startTask();
                }else if (index == 1) {
                    TaskFactory.createOpenTask(controlInfo);
                    TaskFactory.createReadSingleTask(controlInfo, TaskEntiy.TASK_OPTION_OPEN_READ ,Entiy.ACTION_TYPE_4);
                    TaskFactory.createReadEndTask(TaskEntiy.TASK_OPTION_OPEN_READ,controlInfo);
                    TaskManager.getInstance().startTask();
                }else if (index == 2) {
                    TaskFactory.createCloseTask(controlInfo);
                    TaskFactory.createReadSingleTask(controlInfo, TaskEntiy.TASK_OPTION_CLOSE_READ ,Entiy.ACTION_TYPE_4);
                    TaskFactory.createReadEndTask(TaskEntiy.TASK_OPTION_CLOSE_READ,controlInfo);
                    TaskManager.getInstance().startTask();
                }
            }
        });
    }


//    private LayoutInflater mInflater = null;
//    private Context context;
//    private List<DeviceInfo> datas = new ArrayList<DeviceInfo>();
//
//    private int screenWidth;
//    private int screenHight;
//    private DisplayMetrics dm = new DisplayMetrics();
//    private WindowManager manager;
//    public MyGridOpenAdapter(Context context, List<DeviceInfo> datas) {
//        this.context = context;
//        this.datas = datas;
//        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        manager.getDefaultDisplay().getMetrics(dm);
//        screenWidth = dm.widthPixels;
//        screenHight = dm.heightPixels;
//    }
//
//    @Override
//    public int getCount() {
//        return datas.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return datas.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
//        if (convertView == null) {
//            holder = new ViewHolder();
//            convertView = mInflater.inflate(R.layout.grid_item, null);
//            holder.grid_item_layout = (RelativeLayout) convertView.findViewById(R.id.grid_item_layout);
//            holder.grid_item_device = (ImageView) convertView.findViewById(R.id.grid_item_device);
//            holder.grid_item_device_id = (TextView) convertView.findViewById(R.id.grid_item_device_id);
//            holder.grid_item_device_name = (TextView) convertView.findViewById(R.id.grid_item_device_name);
//            holder.grid_item_device_value = (TextView) convertView.findViewById(R.id.grid_item_device_value);
//
//            holder.grid_item_left_layout = (RelativeLayout) convertView.findViewById(R.id.grid_item_left_layout);
//            holder.grid_item_left_image = (ImageView) convertView.findViewById(R.id.grid_item_left_image);
//            holder.grid_item_left_group = (TextView) convertView.findViewById(R.id.grid_item_left_group);
//            holder.grid_item_left_sel = (TextView) convertView.findViewById(R.id.grid_item_left_sel);
//            holder.grid_item_left_id = (TextView) convertView.findViewById(R.id.grid_item_left_id);
//            holder.grid_item_left_alias = (TextView) convertView.findViewById(R.id.grid_item_left_alias);
//
//            holder.grid_item_right_layout = (RelativeLayout) convertView.findViewById(R.id.grid_item_right_layout);
//            holder.grid_item_right_image = (ImageView) convertView.findViewById(R.id.grid_item_right_image);
//            holder.grid_item_right_group = (TextView) convertView.findViewById(R.id.grid_item_right_group);
//            holder.grid_item_right_sel = (TextView) convertView.findViewById(R.id.grid_item_right_sel);
//            holder.grid_item_right_id = (TextView) convertView.findViewById(R.id.grid_item_right_id);
//            holder.grid_item_right_alias = (TextView) convertView.findViewById(R.id.grid_item_right_alias);
//
//            int itemWidth = screenWidth - (int)context.getResources().getDimension(R.dimen.main_table_list_width);
//            int itemHeight = screenHight - (int)context.getResources().getDimension(R.dimen.main_grid_width)- MainActivity.windowTop;
//            AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) convertView.getLayoutParams();
//            if (layoutParams == null) {
//                layoutParams = new AbsListView.LayoutParams(itemWidth/ Entiy.GUN_COLUMN, itemWidth/ Entiy.GUN_COLUMN);
//                holder.grid_item_layout.setLayoutParams(layoutParams);
//            }else {
//                layoutParams.width = itemWidth/ Entiy.GUN_COLUMN;
//                layoutParams.height = itemWidth/ Entiy.GUN_COLUMN;
//            }
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
////        int itemWidth = screenWidth - (int)context.getResources().getDimension(R.dimen.main_table_list_width);
////        int itemHeight = screenHight - (int)context.getResources().getDimension(R.dimen.main_grid_width)- MainActivity.windowTop;
////        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(itemWidth/ Entiy.GRID_COLUMNS, itemWidth/ Entiy.GRID_COLUMNS);
////        holder.grid_item_layout.setLayoutParams(layoutParams);
//        final DeviceInfo deviceInfo = datas.get(position);
//        holder.grid_item_device_id.setText(deviceInfo.getDeviceSort()+"");
//
//        /******设备未绑定******/
//        if (deviceInfo.getDeviceStatus() == Entiy.DEVEICE_UNBIND) {
//            holder.grid_item_device_name.setVisibility(View.INVISIBLE);
//            holder.grid_item_device.setVisibility(View.INVISIBLE);
//            holder.grid_item_device_value.setVisibility(View.INVISIBLE);
//            holder.grid_item_left_layout.setVisibility(View.INVISIBLE);
//            holder.grid_item_right_layout.setVisibility(View.INVISIBLE);
//        }else {
//            if (!TextUtils.isEmpty(deviceInfo.getDeviceName())) {
//                holder.grid_item_device_name.setText(deviceInfo.getDeviceName()+"");
//                holder.grid_item_device_name.setVisibility(View.VISIBLE);
//            }
//            holder.grid_item_device_value.setVisibility(View.VISIBLE);
//            holder.grid_item_device_value.setText(deviceInfo.getElectricQuantity()+"%");
//            holder.grid_item_device.setVisibility(View.VISIBLE);
//            holder.grid_item_left_layout.setVisibility(View.VISIBLE);
//            holder.grid_item_left_sel.setVisibility(View.GONE);
//
//
//            ControlInfo controlInfo_0 = deviceInfo.getValveDeviceSwitchList().get(0);
//            if (controlInfo_0.getValveGroupId() == 0) {
//                holder.grid_item_left_group.setVisibility(View.GONE);
//            }else {
//                holder.grid_item_left_group.setVisibility(View.VISIBLE);
//                holder.grid_item_left_group.setText(controlInfo_0.getValveGroupId()+"");
//            }
//
//            if (controlInfo_0.getValveStatus() == 0) {
//                holder.grid_item_left_image.setVisibility(View.INVISIBLE);
//                holder.grid_item_left_layout.setOnClickListener(null);
//            }else {
//                holder.grid_item_left_image.setVisibility(View.VISIBLE);
//                holder.grid_item_left_image.setImageResource(Entiy.getImageResource(controlInfo_0.getValveStatus()));
//                holder.grid_item_left_id.setText(controlInfo_0.getValveName()+"");
//                holder.grid_item_left_alias.setText(controlInfo_0.getValveAlias()+"");
//
//                holder.grid_item_left_layout.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if(NoFastClickUtils.isFastClick()){
//                            return;
//                        }
//                        openDevice(controlInfo_0);
//                    }
//                });
//            }
//            ControlInfo controlInfo_1 = deviceInfo.getValveDeviceSwitchList().get(1);
//            holder.grid_item_right_layout.setVisibility(View.VISIBLE);
//            holder.grid_item_right_sel.setVisibility(View.GONE);
//
//            if (controlInfo_1.getValveGroupId() == 0) {
//                holder.grid_item_right_group.setVisibility(View.GONE);
//            }else {
//                holder.grid_item_right_group.setVisibility(View.VISIBLE);
//                holder.grid_item_right_group.setText(controlInfo_1.getValveGroupId()+"");
//            }
//            if (controlInfo_1.getValveStatus() == 0) {
//                holder.grid_item_right_image.setVisibility(View.INVISIBLE);
//                holder.grid_item_right_layout.setOnClickListener(null);
//            }else {
//                holder.grid_item_right_image.setVisibility(View.VISIBLE);
//                holder.grid_item_right_image.setImageResource(Entiy.getImageResource(controlInfo_1.getValveStatus()));
//                holder.grid_item_right_id.setText(controlInfo_1.getValveName()+"");
//                holder.grid_item_right_alias.setText(controlInfo_1.getValveAlias()+"");
//
//
//                holder.grid_item_right_layout.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if(NoFastClickUtils.isFastClick()){
//                            return;
//                        }
//                       openDevice(controlInfo_1);
//                    }
//                });
//            }
//
//            if (controlInfo_0.getValveStatus() == 0) {
//                holder.grid_item_left_layout.setVisibility(View.INVISIBLE);
//            }else{
//                holder.grid_item_left_layout.setVisibility(View.VISIBLE);
//            }
//
//            if (controlInfo_1.getValveStatus() == 0) {
//                holder.grid_item_right_layout.setVisibility(View.INVISIBLE);
//            }else{
//                holder.grid_item_right_layout.setVisibility(View.VISIBLE);
//            }
//        }
//        return convertView;
//    }
//
//
//    class ViewHolder {
//        public RelativeLayout grid_item_layout;
//        public ImageView grid_item_device;
//        public TextView grid_item_device_id;
//        public TextView grid_item_device_name;
//        public TextView grid_item_device_value;
//
//        public RelativeLayout grid_item_left_layout;
//        public ImageView grid_item_left_image;
//        public TextView grid_item_left_group;
//        public TextView grid_item_left_sel;
//        public TextView grid_item_left_id;
//        public TextView grid_item_left_alias;
//
//        public RelativeLayout grid_item_right_layout;
//        public ImageView grid_item_right_image;
//        public TextView grid_item_right_group;
//        public TextView grid_item_right_sel;
//        public TextView grid_item_right_id;
//        public TextView grid_item_right_alias;
//    }

//    private void openDevice(final ControlInfo controlInfo) {
//        String status = "关闭";
//        if (controlInfo.getValveStatus() == Entiy.CONTROL_STATUS＿RUN) {
//            status = "开启";
//        }
//        MainoptionDialog.ShowDialog((Activity) context,controlInfo , "手动操作",status,new MainoptionDialog.ItemClick() {
//            @Override
//            public void onItemClick(int index) {
//                /**
//                 *    index = 0  读
//                 *    index = 1  开阀
//                 *    index = 2  关阀
//                 */
//                if (index == 0) {
//                    TaskFactory.createReadSingleTask(controlInfo, TaskEntiy.TASK_OPTION_READ ,Entiy.ACTION_TYPE_4);
//                    TaskFactory.createReadEndTask(TaskEntiy.TASK_OPTION_READ,controlInfo);
//                    TaskManager.getInstance().startTask();
//                }else if (index == 1) {
//                    TaskFactory.createOpenTask(controlInfo);
//                    TaskFactory.createReadSingleTask(controlInfo, TaskEntiy.TASK_OPTION_OPEN_READ ,Entiy.ACTION_TYPE_4);
//                    TaskFactory.createReadEndTask(TaskEntiy.TASK_OPTION_OPEN_READ,controlInfo);
//                    TaskManager.getInstance().startTask();
//                }else if (index == 2) {
//                    TaskFactory.createCloseTask(controlInfo);
//                    TaskFactory.createReadSingleTask(controlInfo, TaskEntiy.TASK_OPTION_CLOSE_READ ,Entiy.ACTION_TYPE_4);
//                    TaskFactory.createReadEndTask(TaskEntiy.TASK_OPTION_CLOSE_READ,controlInfo);
//                    TaskManager.getInstance().startTask();
//                }
//            }
//        });
//    }
}
