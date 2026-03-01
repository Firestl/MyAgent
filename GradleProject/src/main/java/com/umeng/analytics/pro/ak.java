package com.umeng.analytics.pro;

import android.text.TextUtils;

/* JADX INFO: compiled from: TimePeriodItem.java */
/* JADX INFO: loaded from: classes2.dex */
public class ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5167a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5168e = false;
    public int d = -1;
    public int c = -1;
    public int b = -1;

    public ak(String str) {
        this.f5167a = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a();
    }

    private void a() {
        try {
            if (!this.f5167a.contains("-")) {
                this.d = Integer.valueOf(this.f5167a).intValue();
                this.f5168e = false;
                return;
            }
            String[] strArrSplit = this.f5167a.split("-");
            if (strArrSplit.length == 2) {
                this.b = Integer.valueOf(strArrSplit[0]).intValue();
                this.c = Integer.valueOf(strArrSplit[1]).intValue();
                if (this.b < 1) {
                    this.b = 1;
                }
                if (this.c > 24) {
                    this.c = 24;
                }
            }
            this.f5168e = true;
        } catch (Throwable unused) {
        }
    }

    public boolean a(int i) {
        int i2;
        if (this.f5168e) {
            int i3 = this.b;
            if (i3 != -1 && (i2 = this.c) != -1 && i >= i3 && i <= i2) {
                return true;
            }
        } else {
            int i4 = this.d;
            if (i4 != -1 && i == i4) {
                return true;
            }
        }
        return false;
    }
}
