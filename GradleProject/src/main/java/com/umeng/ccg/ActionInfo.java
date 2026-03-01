package com.umeng.ccg;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public interface ActionInfo {
    String getModule(Context context);

    String[] getSupportAction(Context context);

    boolean getSwitchState(Context context, String str);

    void onCommand(Context context, String str, Object obj);
}
