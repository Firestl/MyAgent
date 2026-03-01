package com.umeng.analytics.pro;

import android.content.Context;
import supwisdom.ew1;

/* JADX INFO: compiled from: MeizuDeviceIdSupplier.java */
/* JADX INFO: loaded from: classes2.dex */
public class be implements ax {
    @Override // com.umeng.analytics.pro.ax
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        boolean zA = ew1.a();
        bl.a("getOAID", "isSupported", Boolean.valueOf(zA));
        if (zA) {
            return ew1.a(context);
        }
        return null;
    }
}
