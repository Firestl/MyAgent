package com.igexin.assist.control.oppo;

import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.heytap.msp.push.HeytapPushManager;
import com.heytap.msp.push.callback.ICallBackResultService;
import com.igexin.assist.MessageBean;
import com.igexin.assist.action.MessageManger;
import com.igexin.assist.control.AbstractPushManager;
import com.igexin.assist.sdk.AssistPushConsts;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class ManufacturePushManager implements ICallBackResultService, AbstractPushManager {
    public static final String OPPO_VERSION = "2.1.0";
    public static final String PLUGIN_VERSION = "3.0.2";
    public static final String TAG = "Assist_OP";
    public String appKey;
    public String appSecret;
    public Context context;

    public ManufacturePushManager(Context context) {
        this.appKey = "";
        this.appSecret = "";
        try {
            this.context = context;
            Log.d(TAG, "oppo plugin version = 3.0.2, oppo sdk version = 2.1.0");
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            String str = (String) applicationInfo.metaData.get(AssistPushConsts.OPPOPUSH_APPKEY);
            this.appKey = str;
            this.appKey = str.replace(AssistPushConsts.OPPO_PREFIX, "");
            String str2 = (String) applicationInfo.metaData.get(AssistPushConsts.OPPOPUSH_APPSECRET);
            this.appSecret = str2;
            this.appSecret = str2.replace(AssistPushConsts.OPPO_PREFIX, "");
            createNotificationChannel(context);
        } catch (Throwable th) {
            Log.d(TAG, th.getMessage());
        }
    }

    public static boolean checkOppoDevice(Context context) {
        try {
            HeytapPushManager.init(context, false);
            return HeytapPushManager.isSupportPush();
        } catch (Throwable th) {
            Log.d(TAG, th.getMessage());
            return false;
        }
    }

    private void createNotificationChannel(Context context) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        Class<?> cls = Class.forName("android.app.NotificationChannel");
        Constructor<?> constructor = cls.getConstructor(String.class, CharSequence.class, Integer.TYPE);
        if (constructor == null || ((Parcelable) NotificationManager.class.getMethod("getNotificationChannel", String.class).invoke(notificationManager, "Default")) != null) {
            return;
        }
        NotificationManager.class.getMethod("createNotificationChannel", cls).invoke(notificationManager, (Parcelable) constructor.newInstance("Default", "Default", 3));
    }

    @Override // com.igexin.assist.control.AbstractPushManager
    public String getBrandCode() {
        return "4";
    }

    @Override // com.igexin.assist.control.AbstractPushManager
    public String getToken(Context context) {
        return HeytapPushManager.getRegisterID();
    }

    @Override // com.igexin.assist.control.AbstractPushManager
    public boolean isSupport() {
        Context context = this.context;
        if (context == null) {
            return false;
        }
        try {
            HeytapPushManager.init(context, false);
            return HeytapPushManager.isSupportPush();
        } catch (Throwable th) {
            Log.d(TAG, th.getMessage());
            return false;
        }
    }

    @Override // com.heytap.msp.push.callback.ICallBackResultService
    public void onGetNotificationStatus(int i, int i2) {
    }

    @Override // com.heytap.msp.push.callback.ICallBackResultService
    public void onGetPushStatus(int i, int i2) {
    }

    @Override // com.heytap.msp.push.callback.ICallBackResultService
    public void onRegister(int i, String str) {
        try {
            Log.d(TAG, "onToken :" + str + ", code = " + i);
            if (this.context == null || i != 0 || TextUtils.isEmpty(str) || str.equalsIgnoreCase("InvalidAppKey")) {
                return;
            }
            MessageManger.getInstance().addMessage(new MessageBean(this.context, "token", AssistPushConsts.OPPO_PREFIX.concat(String.valueOf(str))));
        } catch (Throwable th) {
            Log.d(TAG, th.getMessage());
        }
    }

    @Override // com.heytap.msp.push.callback.ICallBackResultService
    public void onSetPushTime(int i, String str) {
    }

    @Override // com.heytap.msp.push.callback.ICallBackResultService
    public void onUnRegister(int i) {
    }

    @Override // com.igexin.assist.control.AbstractPushManager
    public void register(Context context) {
        try {
            this.context = context;
            if (!TextUtils.isEmpty(this.appKey) && !TextUtils.isEmpty(this.appSecret)) {
                Log.d(TAG, "Register oppo push, pkg = " + context.getPackageName() + ", appKey = " + this.appKey + ", appSecret = " + this.appSecret);
                if (isSupport()) {
                    HeytapPushManager.register(context, this.appKey, this.appSecret, this);
                    return;
                } else {
                    Log.d(TAG, "not support oppo push.");
                    return;
                }
            }
            Log.d(TAG, "Register oppo push appKey or appSecret is null or empty");
        } catch (Throwable th) {
            Log.d(TAG, th.getMessage());
        }
    }

    @Override // com.igexin.assist.control.AbstractPushManager
    public void setSilentTime(Context context, int i, int i2) {
        if (i2 == 0) {
            turnOnPush(context);
            return;
        }
        int i3 = (i + i2) % 24;
        Log.d(TAG, "getui setSilentTime" + i + Constants.COLON_SEPARATOR + i2);
        Log.d(TAG, "oppo push setAcceptTime" + i3 + Constants.COLON_SEPARATOR + i);
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < 7; i4++) {
            arrayList.add(Integer.valueOf(i4));
        }
        HeytapPushManager.setPushTime(arrayList, i3, 0, i, 0);
    }

    @Override // com.igexin.assist.control.AbstractPushManager
    public void turnOffPush(Context context) {
        try {
            HeytapPushManager.pausePush();
        } catch (Throwable th) {
            Log.d(TAG, th.getMessage());
        }
    }

    @Override // com.igexin.assist.control.AbstractPushManager
    public void turnOnPush(Context context) {
        try {
            HeytapPushManager.resumePush();
        } catch (Throwable th) {
            Log.d(TAG, th.getMessage());
        }
    }

    @Override // com.igexin.assist.control.AbstractPushManager
    public void unregister(Context context) {
        try {
            HeytapPushManager.unRegister();
        } catch (Throwable th) {
            Log.d(TAG, th.getMessage());
        }
    }
}
