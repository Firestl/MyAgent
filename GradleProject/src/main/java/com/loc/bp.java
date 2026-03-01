package com.loc;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: compiled from: TimeUpdateStrategy.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bp extends bq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3688a;
    public long b;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f3689e;

    public bp(Context context, int i, String str, bq bqVar) {
        super(bqVar);
        this.f3688a = i;
        this.d = str;
        this.f3689e = context;
    }

    @Override // com.loc.bq
    public final void a(boolean z) {
        super.a(z);
        if (z) {
            String str = this.d;
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.b = jCurrentTimeMillis;
            z.a(this.f3689e, str, String.valueOf(jCurrentTimeMillis));
        }
    }

    @Override // com.loc.bq
    public final boolean a() {
        if (this.b == 0) {
            String strA = z.a(this.f3689e, this.d);
            this.b = TextUtils.isEmpty(strA) ? 0L : Long.parseLong(strA);
        }
        return System.currentTimeMillis() - this.b >= ((long) this.f3688a);
    }
}
