package com.loc;

import android.content.Context;
import android.text.TextUtils;
import io.dcloud.common.adapter.util.Logger;

/* JADX INFO: compiled from: MobileUpdateStrategy.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bn extends bq {
    public Context b;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3686e;
    public int f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3685a = "iKey";
    public int g = 0;

    public bn(Context context, boolean z, int i, int i2, String str) {
        a(context, z, i, i2, str, 0);
    }

    public bn(Context context, boolean z, int i, int i2, String str, int i3) {
        a(context, z, i, i2, str, i3);
    }

    private void a(Context context, boolean z, int i, int i2, String str, int i3) {
        this.b = context;
        this.d = z;
        this.f3686e = i;
        this.f = i2;
        this.f3685a = str;
        this.g = i3;
    }

    @Override // com.loc.bq
    public final void a(int i) {
        if (n.q(this.b) == 1) {
            return;
        }
        String strA = u.a(System.currentTimeMillis(), Logger.TIMESTAMP_YYYY_MM_DD);
        String strA2 = z.a(this.b, this.f3685a);
        if (!TextUtils.isEmpty(strA2)) {
            String[] strArrSplit = strA2.split("\\|");
            if (strArrSplit == null || strArrSplit.length < 2) {
                z.b(this.b, this.f3685a);
            } else if (strA.equals(strArrSplit[0])) {
                i += Integer.parseInt(strArrSplit[1]);
            }
        }
        z.a(this.b, this.f3685a, strA + "|" + i);
    }

    @Override // com.loc.bq
    public final boolean a() {
        if (n.q(this.b) == 1) {
            return true;
        }
        if (!this.d) {
            return false;
        }
        String strA = z.a(this.b, this.f3685a);
        if (TextUtils.isEmpty(strA)) {
            return true;
        }
        String[] strArrSplit = strA.split("\\|");
        if (strArrSplit != null && strArrSplit.length >= 2) {
            return !u.a(System.currentTimeMillis(), Logger.TIMESTAMP_YYYY_MM_DD).equals(strArrSplit[0]) || Integer.parseInt(strArrSplit[1]) < this.f;
        }
        z.b(this.b, this.f3685a);
        return true;
    }

    @Override // com.loc.bq
    public final int b() {
        int i;
        int i2 = Integer.MAX_VALUE;
        if ((n.q(this.b) != 1 && (i = this.f3686e) > 0) || ((i = this.g) > 0 && i < Integer.MAX_VALUE)) {
            i2 = i;
        }
        bq bqVar = this.c;
        return bqVar != null ? Math.max(i2, bqVar.b()) : i2;
    }
}
