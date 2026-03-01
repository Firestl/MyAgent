package com.heytap.msp.push;

import android.content.Context;
import com.heytap.mcssdk.d;
import com.heytap.msp.push.callback.ICallBackResultService;
import com.heytap.msp.push.mode.MessageStat;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class HeytapPushManager {
    public static final String EVENT_ID_APP_BLACK_LIST = "app_black_list";
    public static final String EVENT_ID_PUSH_ADD_MESSAGE_NO_DISTURBING = "add_message_no_disturbing";
    public static final String EVENT_ID_PUSH_ADD_MESSAGE_TOP = "add_message_top";
    public static final String EVENT_ID_PUSH_CLICK = "push_click";
    public static final String EVENT_ID_PUSH_DELETE = "push_delete";
    public static final String EVENT_ID_PUSH_EXCEPTION = "push_exception";
    public static final String EVENT_ID_PUSH_MESSAGE_REPEAT = "message_repeat";
    public static final String EVENT_ID_PUSH_NO_IMSI = "imsi_not_exist";
    public static final String EVENT_ID_PUSH_NO_SHOW = "push_no_show";
    public static final String EVENT_ID_PUSH_REVOKE = "push_revoke";
    public static final String EVENT_ID_PUSH_REVOKE_DELETE = "push_revoke_delete";
    public static final String EVENT_ID_PUSH_SHOW = "push_show";
    public static final String EVENT_ID_READ_MESSAGE = "push_read_message";

    public static void clearNotificationType() {
        clearNotificationType(null);
    }

    public static void clearNotificationType(JSONObject jSONObject) {
        d.k().d(jSONObject);
    }

    public static void clearNotifications() {
        clearNotifications(null);
    }

    public static void clearNotifications(JSONObject jSONObject) {
        d.k().d(jSONObject);
    }

    public static String getMcsPackageName() {
        return d.k().l();
    }

    public static void getNotificationStatus() {
        getNotificationStatus(null);
    }

    public static void getNotificationStatus(JSONObject jSONObject) {
        d.k().c(jSONObject);
    }

    public static ICallBackResultService getPushCallback() {
        return d.k().q();
    }

    public static void getPushStatus() {
        d.k().r();
    }

    public static int getPushVersionCode() {
        return d.k().u();
    }

    public static String getPushVersionName() {
        return d.k().t();
    }

    public static String getReceiveSdkAction() {
        return d.k().m();
    }

    public static void getRegister() {
        getRegister(null);
    }

    public static void getRegister(JSONObject jSONObject) {
        d.k().a(jSONObject);
    }

    public static String getRegisterID() {
        return d.k().a();
    }

    public static String getSDKVersion() {
        return d.s();
    }

    public static void init(Context context, boolean z) {
        d.k().a(context, z);
    }

    public static boolean isSupportPush() {
        return d.k().n();
    }

    public static void openNotificationSettings() {
        openNotificationSettings(null);
    }

    public static void openNotificationSettings(JSONObject jSONObject) {
        d.k().f(jSONObject);
    }

    public static void pausePush() {
        pausePush(null);
    }

    public static void pausePush(JSONObject jSONObject) {
        d.k().g(jSONObject);
    }

    public static void register(Context context, String str, String str2, ICallBackResultService iCallBackResultService) {
        register(context, str, str2, null, iCallBackResultService);
    }

    public static void register(Context context, String str, String str2, JSONObject jSONObject, ICallBackResultService iCallBackResultService) {
        d.k().a(context, str, str2, jSONObject, iCallBackResultService);
    }

    public static void requestNotificationPermission() {
        d.k().j();
    }

    public static void resumePush() {
        resumePush(null);
    }

    public static void resumePush(JSONObject jSONObject) {
        d.k().h(jSONObject);
    }

    public static void setAppKeySecret(String str, String str2) {
        d.k().a(str, str2);
    }

    public static void setNotificationType(int i) {
        setNotificationType(i, null);
    }

    public static void setNotificationType(int i, JSONObject jSONObject) {
        d.k().a(i, jSONObject);
    }

    public static void setPushCallback(ICallBackResultService iCallBackResultService) {
        d.k().a(iCallBackResultService);
    }

    public static void setPushTime(List<Integer> list, int i, int i2, int i3, int i4) {
        setPushTime(list, i, i2, i3, i4, null);
    }

    public static void setPushTime(List<Integer> list, int i, int i2, int i3, int i4, JSONObject jSONObject) {
        d.k().a(list, i, i2, i3, i4, jSONObject);
    }

    public static void setRegisterID(String str) {
        d.k().a(str);
    }

    public static void statisticMessage(Context context, MessageStat messageStat) {
        d.a(context, messageStat);
    }

    public static void statisticMessage(Context context, List<MessageStat> list) {
        d.a(context, list);
    }

    public static void unRegister() {
        unRegister(null);
    }

    public static void unRegister(Context context, String str, String str2, JSONObject jSONObject, ICallBackResultService iCallBackResultService) {
        d.k().b(context, str, str2, jSONObject, iCallBackResultService);
    }

    public static void unRegister(JSONObject jSONObject) {
        d.k().b(jSONObject);
    }
}
