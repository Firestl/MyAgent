package com.umeng.commonsdk.service;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.utils.UMUtils;

/* JADX INFO: loaded from: classes2.dex */
public class UMGlobalContext {
    public static final String TAG = "UMGlobalContext";
    public String mAppVersion;
    public String mAppkey;
    public Context mApplicationContext;
    public String mChannel;
    public String mProcessName;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final UMGlobalContext f5409a = new UMGlobalContext();
    }

    public static Context getAppContext(Context context) {
        if (a.f5409a.mApplicationContext == null && context != null) {
            a.f5409a.mApplicationContext = context.getApplicationContext();
        }
        return a.f5409a.mApplicationContext;
    }

    public static UMGlobalContext getInstance(Context context) {
        if (a.f5409a.mApplicationContext == null && context != null) {
            a.f5409a.mApplicationContext = context;
        }
        return a.f5409a;
    }

    public String getAppVersion() {
        if (TextUtils.isEmpty(this.mAppVersion)) {
            this.mAppVersion = UMUtils.getAppVersionName(this.mApplicationContext);
        }
        return this.mAppVersion;
    }

    public String getAppkey() {
        if (TextUtils.isEmpty(this.mAppkey)) {
            this.mAppkey = UMConfigure.sAppkey;
        }
        return this.mAppkey;
    }

    public String getChannel() {
        if (TextUtils.isEmpty(this.mChannel)) {
            this.mChannel = UMConfigure.sChannel;
        }
        return this.mChannel;
    }

    public String getProcessName(Context context) {
        if (TextUtils.isEmpty(this.mProcessName)) {
            if (context != null) {
                Context context2 = a.f5409a.mApplicationContext;
                if (context2 != null) {
                    this.mProcessName = UMFrUtils.getCurrentProcessName(context2);
                } else {
                    this.mProcessName = UMFrUtils.getCurrentProcessName(context);
                }
            } else {
                this.mProcessName = UMFrUtils.getCurrentProcessName(a.f5409a.mApplicationContext);
            }
        }
        return this.mProcessName;
    }

    public boolean isMainProcess(Context context) {
        return UMUtils.isMainProgress(context);
    }

    public String toString() {
        if (a.f5409a.mApplicationContext == null) {
            return "uninitialized.";
        }
        StringBuilder sb = new StringBuilder(Operators.ARRAY_START_STR);
        sb.append("appkey:" + this.mAppkey + ",");
        sb.append("channel:" + this.mChannel + ",");
        sb.append("procName:" + this.mProcessName + Operators.ARRAY_END_STR);
        return sb.toString();
    }

    public UMGlobalContext() {
        this.mProcessName = "";
    }

    public static Context getAppContext() {
        return a.f5409a.mApplicationContext;
    }
}
