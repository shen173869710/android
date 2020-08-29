package com.auto.di.guan.net;


import com.auto.di.guan.db.GroupInfo;
import com.auto.di.guan.db.sql.GroupInfoSql;
import com.auto.di.guan.entity.Entiy;
import com.auto.di.guan.jobqueue.event.AutoTaskEvent;
import com.auto.di.guan.jobqueue.event.Fragment32Event;
import com.auto.di.guan.jobqueue.task.TaskFactory;
import com.auto.di.guan.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

/**
 *   后台网络操作
 */
public class NetOption {

    public static String LOGIN = "login";
    /**单个操作**/
    public static String  SINGLE_READ = "single_read";
    public static String SINGLE_OPEN  = "single_open";
    public static String SINGLE_CLOSE = "single_close";

    /**轮灌分组**/
    public static String SET_GROUP = "set_group";

    /**单租操作**/
    public static String GROUP_OPEN = "group_open";
    public static String GROUP_CLOSE = "group_close";

    /****/

    public static String GROUP_AUTO = "group_auto";


    public static void doOption(int type) {
        switch (type) {
            case 1:
                doLogin();
                break;
            case 2:
            case 3:
            case 4:
                doSingleOption();
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                doGroupOption();
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;

        }
    }

    /**
     *   做登录
     */
    public static void doLogin() {
    }

    /**
     *  执行单个操作
     */
    public static void doSingleOption() {
//        if (index == 0) {
//            TaskFactory.createReadSingleTask(controlInfo, TaskEntiy.TASK_OPTION_READ , Entiy.ACTION_TYPE_4);
//            TaskFactory.createReadEndTask();
//            TaskManager.getInstance().startTask();
//        }else if (index == 1) {
//            TaskFactory.createOpenTask(controlInfo);
//            TaskFactory.createReadSingleTask(controlInfo, TaskEntiy.TASK_OPTION_OPEN_READ ,Entiy.ACTION_TYPE_4);
//            TaskFactory.createReadEndTask();
//            TaskManager.getInstance().startTask();
//        }else if (index == 2) {
//            TaskFactory.createCloseTask(controlInfo);
//            TaskFactory.createReadSingleTask(controlInfo, TaskEntiy.TASK_OPTION_CLOSE_READ ,Entiy.ACTION_TYPE_4);
//            TaskFactory.createReadEndTask();
//            TaskManager.getInstance().startTask();
//        }
    }

    /**
     *  组单独的操作
     */
    public static void doGroupOption() {
//        if (index == 1) {
//            TaskFactory.createGroupOpenTask(groupInfo);
//            TaskManager.getInstance().startTask();
//        }else if (index == 2) {
//            TaskFactory.createGroupCloseTask(groupInfo);
//            TaskManager.getInstance().startTask();
//        }
    }

    /**
     *   自动轮灌设置
     */
    public static void doAutoSetting(List<GroupInfo> groupInfos) {
        int size = groupInfos.size();
        HashMap<Integer, Integer> lv = new HashMap<>();
        for (int i = 0; i < size; i++) {
            GroupInfo groupInfo = groupInfos.get(i);
            if (groupInfo.getGroupIsJoin()) {
                if (groupInfo.getGroupTime() == 0 || groupInfo.getGroupLevel() == 0) {
                    ToastUtils.showLongToast("轮灌优先级或者轮灌时长不能为0");
                    return;
                }
            }else {
                groupInfo.setGroupRunTime(0);
                groupInfo.setGroupLevel(0);
                groupInfo.setGroupTime(0);
            }

            int level = groupInfo.getGroupLevel();
            if (level > 0) {
                if (lv.containsKey(level)) {
                    ToastUtils.showLongToast("不能设置相同的轮灌优先级,或者优先级不能为空");
                    return;
                }
                lv.put(level,level);
            }
        }
        GroupInfoSql.updateGroupList(groupInfos);
        EventBus.getDefault().post(new Fragment32Event());
    }

    /**
     *   自动轮灌设置
     */
    public static void doAutoOption() {

    }

    private static void  doGroupSetting() {

    }


    /**
     *   暂停轮灌
     */
    public static void stopOrStart(boolean start) {

//        info.setGroupStop(false);
//        GroupInfoSql.updateGroup(info);
//        EventBus.getDefault().post(new AutoTaskEvent(Entiy.RUN_DO_START, info));
//        notifyDataSetChanged();
    }

    public static void doNextGroup (GroupInfo groupInfo) {

    }


}
