package com.vivo.push.util;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class NotifyUtil {
    public static BaseNotifyDataAdapter sNotifyData = null;
    public static String sNotifyDataAdapter = "com.vivo.push.util.NotifyDataAdapter";
    public static BaseNotifyLayoutAdapter sNotifyLayout = null;
    public static String sNotifyLayoutAdapter = "com.vivo.push.util.NotifyLayoutAdapter";

    public static BaseNotifyDataAdapter getNotifyDataAdapter(Context context) {
        initAdapter(context);
        return sNotifyData;
    }

    public static BaseNotifyLayoutAdapter getNotifyLayoutAdapter(Context context) {
        initAdapter(context);
        return sNotifyLayout;
    }

    public static Object getObjectByReflect(String str, Object obj) {
        Class<?> cls;
        Object objNewInstance = null;
        try {
            cls = Class.forName(str);
        } catch (Exception unused) {
            cls = null;
        }
        if (cls != null) {
            try {
                objNewInstance = cls.newInstance();
            } catch (Exception unused2) {
            }
        }
        return objNewInstance == null ? obj : objNewInstance;
    }

    public static synchronized void initAdapter(Context context) {
        if (sNotifyData == null) {
            BaseNotifyDataAdapter baseNotifyDataAdapter = (BaseNotifyDataAdapter) getObjectByReflect(sNotifyDataAdapter, new h());
            sNotifyData = baseNotifyDataAdapter;
            baseNotifyDataAdapter.init(context);
        }
        if (sNotifyLayout == null) {
            BaseNotifyLayoutAdapter baseNotifyLayoutAdapter = (BaseNotifyLayoutAdapter) getObjectByReflect(sNotifyLayoutAdapter, new i());
            sNotifyLayout = baseNotifyLayoutAdapter;
            baseNotifyLayoutAdapter.init(context);
        }
    }
}
