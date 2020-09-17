package com.auto.di.guan.rtm;


import com.auto.di.guan.BaseApp;
import com.auto.di.guan.GroupStatusActivity;
import com.auto.di.guan.db.ControlInfo;
import com.auto.di.guan.db.GroupInfo;
import com.auto.di.guan.db.sql.GroupInfoSql;
import com.auto.di.guan.entity.Entiy;
import com.auto.di.guan.jobqueue.TaskEntiy;
import com.auto.di.guan.jobqueue.TaskManager;
import com.auto.di.guan.jobqueue.event.AutoTaskEvent;
import com.auto.di.guan.jobqueue.task.TaskFactory;
import com.auto.di.guan.utils.LogUtils;
import com.auto.di.guan.utils.PollingUtils;
import com.auto.di.guan.utils.ToastUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 *     解析消息
 */
public class MessageParse {
    public static final String TAG = "MessageParse------web------";

    public static void praseData(String data, String peerId) {
        MessageInfo info = new Gson().fromJson(data, MessageInfo.class);
        if (info == null) {
            LogUtils.e(TAG, "gson 数据解析异常");
            return;
        }

        GroupInfo groupInfo = info.getGroupInfo();
        ControlInfo controlInfo = info.getControlInfo();

        switch (info.getType()) {
            case MessageEntiy.TYPE_LOGIN:
                // 登录
                MessageSend.syncLogin(peerId);
                break;
            case MessageEntiy.TYPE_LOGOUT:
                // 登出
                break;
            case MessageEntiy.TYPE_SINGLE_READ:
                // 单个操作 读
            case MessageEntiy.TYPE_SINGLE_OPEN:
                // 单个操作 开
            case MessageEntiy.TYPE_SINGLE_CLOSE:
                // 单个操作 关
                if (controlInfo != null) {
                    dealSingle(info.getType(), controlInfo);
                }
                break;
            case MessageEntiy.TYPE_GROUP_OPEN:
                // 单组操作 开
            case MessageEntiy.TYPE_GROUP_CLOSE:
                // 单组操作 关
                if (groupInfo != null) {
                    dealGroup(info.getType(), groupInfo);
                }
                break;
            case MessageEntiy.TYPE_AUTO_OPEN:
                // 单组自动轮灌开
                dealAutoOpen();
                break;
            case MessageEntiy.TYPE_AUTO_CLOSE:
                // 单组自动轮灌开
                dealAutoClose();
                break;
            case MessageEntiy.TYPE_AUTO_START:
                // 单组自动轮灌 暂停
                if (groupInfo != null) {
                    dealAutoStart(groupInfo);
                }
                break;
            case MessageEntiy.TYPE_AUTO_STOP:
                // 单组自动轮灌 开始
                if (groupInfo != null) {
                    dealAutoStop(groupInfo);
                }
                break;
            case MessageEntiy.TYPE_AUTO_NEXT:
                // 单组自动轮灌 下一组
                if (groupInfo != null) {
                    dealAutoNext(groupInfo);
                }
                break;
            case MessageEntiy.TYPE_MESSAGE:

                break;
        }
    }
    /**
     *   处理单个操作
     */
    public static void dealSingle(int type, ControlInfo controlInfo) {
        if (type == MessageEntiy.TYPE_SINGLE_READ) {
            LogUtils.e(TAG, "收到单个读操作");
            TaskFactory.createReadSingleTask(controlInfo, TaskEntiy.TASK_OPTION_READ , Entiy.ACTION_TYPE_4);
            TaskFactory.createReadEndTask(TaskEntiy.TASK_OPTION_READ);
            TaskManager.getInstance().startTask();
        }else if (type == MessageEntiy.TYPE_SINGLE_OPEN) {
            LogUtils.e(TAG, "收到单个开启操作");
            TaskFactory.createOpenTask(controlInfo);
            TaskFactory.createReadSingleTask(controlInfo, TaskEntiy.TASK_OPTION_OPEN_READ ,Entiy.ACTION_TYPE_4);
            TaskFactory.createReadEndTask(TaskEntiy.TASK_OPTION_OPEN_READ);
            TaskManager.getInstance().startTask();
        }else if (type == MessageEntiy.TYPE_SINGLE_CLOSE) {
            LogUtils.e(TAG, "收到单个关闭操作");
            TaskFactory.createCloseTask(controlInfo);
            TaskFactory.createReadSingleTask(controlInfo, TaskEntiy.TASK_OPTION_CLOSE_READ ,Entiy.ACTION_TYPE_4);
            TaskFactory.createReadEndTask(TaskEntiy.TASK_OPTION_CLOSE_READ);
            TaskManager.getInstance().startTask();
        }
    }

    /**
     *  单组操作开关
     */
    public static void dealGroup(int type, GroupInfo groupInfo) {
        if (type == MessageEntiy.TYPE_GROUP_OPEN) {
            LogUtils.e(TAG, "收到单组开启操作");
            TaskFactory.createGroupOpenTask(groupInfo);
            TaskManager.getInstance().startTask();
        }else if (type == MessageEntiy.TYPE_GROUP_CLOSE) {
            LogUtils.e(TAG, "收到单组关闭操作");
            TaskFactory.createGroupCloseTask(groupInfo);
            TaskManager.getInstance().startTask();
        }
    }

    /**
     *  处理自动轮灌开启
     */
    public static void dealAutoOpen() {
        LogUtils.e(TAG, "收到自动轮灌开启操作");
        List<GroupInfo> groupInfos = GroupInfoSql.queryGroupSettingList();
        if (groupInfos != null && groupInfos.size() > 0) {
            if (GroupInfoSql.queryOpenGroupList() != null) {
                ToastUtils.showLongToast("自动轮灌正在运行当中, 无法再次开启自动轮灌");
                return;
            }
            PollingUtils.stopPollingService(BaseApp.getContext());
            TaskFactory.createAutoGroupOpenTask(groupInfos.get(0));
            TaskManager.getInstance().startTask();
        }
    }

    /**
     *  处理自动轮毂关闭
     */
    private static void dealAutoClose() {
        LogUtils.e(TAG, "收到自动轮灌关闭操作");
        List<GroupInfo> groupInfos = GroupInfoSql.queryGroupSettingList();
        if (groupInfos != null && groupInfos.size() > 0) {
            for (int i = 0; i < groupInfos.size(); i++) {
                GroupInfo info = groupInfos.get(i);
                info.setGroupRunTime(0);
                info.setGroupTime(0);
                info.setGroupStatus(Entiy.GROUP_STATUS_COLSE);
            }
            GroupInfoSql.updateGroupList(groupInfos);
            PollingUtils.stopPollingService(BaseApp.getContext());
            EventBus.getDefault().post(new AutoTaskEvent(Entiy.RUN_DO_FINISH));

            MessageSend.syncAutoClose(MessageEntiy.TYPE_AUTO_CLOSE);
        }
    }

    /**
     *  处理自动轮毂开始
     */
    private static void dealAutoStart(GroupInfo info) {
        LogUtils.e(TAG, "收到自动轮灌开始操作");
        List<GroupInfo> groupInfos = GroupInfoSql.queryGroupInfoById(info.getGroupId());
        if (groupInfos == null || groupInfos.size() != 1) {
            LogUtils.e(TAG, "自动轮灌开始,当前组不存在");
        }else {
            GroupInfo groupInfo = groupInfos.get(0);
            groupInfo.setGroupStop(false);
            GroupInfoSql.updateGroup(groupInfo);
            EventBus.getDefault().post(new AutoTaskEvent(Entiy.RUN_DO_START, groupInfo));
            MessageSend.syncAuto(MessageEntiy.TYPE_AUTO_START, groupInfo);
        }
    }

    /**
     *  处理自动轮毂暂停
     */
    private static void dealAutoStop(GroupInfo info) {
        LogUtils.e(TAG, "收到自动轮灌暂停操作");
        List<GroupInfo> groupInfos = GroupInfoSql.queryGroupInfoById(info.getGroupId());
        if (groupInfos == null || groupInfos.size() != 1) {
            LogUtils.e(TAG, "自动轮灌暂停,当前组不存在");
        } else {
            GroupInfo groupInfo = groupInfos.get(0);
            groupInfo.setGroupStop(true);
            GroupInfoSql.updateGroup(groupInfo);
            EventBus.getDefault().post(new AutoTaskEvent(Entiy.RUN_DO_STOP, groupInfo));

            MessageSend.syncAuto(MessageEntiy.TYPE_AUTO_STOP, groupInfo);
        }
    }

    /**
     *  处理自动轮毂下一组
     */
    private static void dealAutoNext(GroupInfo info) {
        LogUtils.e(TAG, "收到自动轮灌开启下一组操作");
        List<GroupInfo> groupInfos = GroupInfoSql.queryGroupInfoById(info.getGroupId());
        if (groupInfos == null || groupInfos.size() != 1) {
            LogUtils.e(TAG, "自动轮灌暂停,当前组不存在");
        } else {
            GroupInfo groupInfo = groupInfos.get(0);
            groupInfo.setGroupRunTime(info.getGroupTime());
            GroupInfoSql.updateGroup(groupInfo);
            MessageSend.syncAuto(MessageEntiy.TYPE_AUTO_NEXT, groupInfo);
        }
    }
}
