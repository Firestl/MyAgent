package com.umeng.analytics.pro;

import android.content.Context;
import supwisdom.jw1;

/* JADX INFO: compiled from: XiaomiDeviceIdSupplier.java */
/* JADX INFO: loaded from: classes2.dex */
public class bj implements ax {
    @Override // com.umeng.analytics.pro.ax
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        boolean zA = jw1.a();
        bl.a("getOAID", "isSupported", Boolean.valueOf(zA));
        if (zA) {
            return jw1.a(context);
        }
        return null;
    }
}
