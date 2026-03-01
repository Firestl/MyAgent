package com.baidu.speech.utils.cuid.util;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class CommonParam {
    public static final boolean DEBUG = false;
    public static final String TAG = "CommonParam";

    @Deprecated
    public static String getCUID(Context context) {
        return DeviceId.getCUID(context);
    }
}
