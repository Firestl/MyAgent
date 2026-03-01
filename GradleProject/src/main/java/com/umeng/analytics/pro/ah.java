package com.umeng.analytics.pro;

import android.content.SharedPreferences;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.service.UMGlobalContext;

/* JADX INFO: compiled from: LaunchTimesCondition.java */
/* JADX INFO: loaded from: classes2.dex */
public class ah implements ac {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5165a;

    public ah(int i) {
        this.f5165a = 0;
        this.f5165a = i;
    }

    @Override // com.umeng.analytics.pro.ac
    public boolean a() {
        long j = 0;
        try {
            SharedPreferences sharedPreferencesA = at.a(UMGlobalContext.getAppContext());
            if (sharedPreferencesA != null) {
                j = sharedPreferencesA.getLong(at.f5177a, 0L);
                if (j >= this.f5165a) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "launch times skipped. times: " + j + " ; config: " + this.f5165a);
        return false;
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
