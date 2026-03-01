package com.umeng.analytics.pro;

import android.content.Context;
import supwisdom.cw1;

/* JADX INFO: compiled from: OppoDeviceIdSupplier.java */
/* JADX INFO: loaded from: classes2.dex */
public class bg implements ax {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f5199a = false;

    @Override // com.umeng.analytics.pro.ax
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        if (!this.f5199a) {
            cw1.a(context);
            this.f5199a = true;
        }
        boolean zA = cw1.a();
        bl.a("getOAID", "isSupported", Boolean.valueOf(zA));
        if (zA) {
            return cw1.b(context);
        }
        return null;
    }
}
