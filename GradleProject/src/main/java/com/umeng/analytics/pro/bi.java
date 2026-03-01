package com.umeng.analytics.pro;

import android.content.Context;
import supwisdom.nw1;

/* JADX INFO: compiled from: VivoDeviceIdSupplier.java */
/* JADX INFO: loaded from: classes2.dex */
public class bi implements ax {
    @Override // com.umeng.analytics.pro.ax
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        boolean zA = nw1.a(context);
        bl.a("getOAID", "isSupported", Boolean.valueOf(zA));
        if (zA) {
            return nw1.b(context);
        }
        return null;
    }
}
