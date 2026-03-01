package com.umeng.analytics.pro;

import android.content.Context;
import supwisdom.ow1;

/* JADX INFO: compiled from: LenovoDeviceIdSupplier.java */
/* JADX INFO: loaded from: classes2.dex */
public class bd implements ax {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f5197a = 1;
    public ow1 b;
    public boolean c = false;
    public boolean d = false;

    @Override // com.umeng.analytics.pro.ax
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        if (!this.c) {
            ow1 ow1Var = new ow1();
            this.b = ow1Var;
            this.d = ow1Var.a(context, (ow1.b<String>) null) == 1;
            this.c = true;
        }
        bl.a("getOAID", "isSupported", Boolean.valueOf(this.d));
        if (this.d && this.b.b()) {
            return this.b.a();
        }
        return null;
    }
}
