package com.umeng.analytics.pro;

import android.content.SharedPreferences;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.service.UMGlobalContext;

/* JADX INFO: compiled from: IntervalPeriodCondition.java */
/* JADX INFO: loaded from: classes2.dex */
public class ag implements ac {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5164a;
    public long b;

    public ag(String str, long j) {
        this.f5164a = "";
        this.b = 0L;
        this.f5164a = str;
        this.b = j;
    }

    @Override // com.umeng.analytics.pro.ac
    public boolean a() {
        try {
            String str = at.b + this.f5164a;
            SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
            if (sharedPreferencesA == null) {
                return false;
            }
            long jCurrentTimeMillis = System.currentTimeMillis() - sharedPreferencesA.getLong(str, 0L);
            if (jCurrentTimeMillis > this.b * 1000) {
                return true;
            }
            UMRTLog.i(UMRTLog.RTLOG_TAG, "internal period skipped. elapse: " + jCurrentTimeMillis + "; config: " + (this.b * 1000));
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.umeng.analytics.pro.ac
    public boolean b() {
        return !a();
    }

    @Override // com.umeng.analytics.pro.ac
    public long c() {
        return 0L;
    }
}
