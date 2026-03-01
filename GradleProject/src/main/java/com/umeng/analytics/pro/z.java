package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.commonsdk.statistics.idtracking.Envelope;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;

/* JADX INFO: compiled from: SessionIdGenerateServiceImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class z implements y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f5322a = AnalyticsConfig.kContinueSessionMillis;

    @Override // com.umeng.analytics.pro.y
    public void a(long j) {
        this.f5322a = j;
    }

    @Override // com.umeng.analytics.pro.y
    public long a() {
        return this.f5322a;
    }

    @Override // com.umeng.analytics.pro.y
    public String a(Context context) {
        String appkey = UMUtils.getAppkey(context);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (appkey != null) {
            return UMUtils.MD5(jCurrentTimeMillis + appkey + Envelope.dummyID2);
        }
        throw new RuntimeException("Appkey is null or empty, Please check!");
    }

    @Override // com.umeng.analytics.pro.y
    public boolean a(long j, long j2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        return (j == 0 || jCurrentTimeMillis - j >= this.f5322a) && j2 > 0 && jCurrentTimeMillis - j2 > this.f5322a;
    }

    @Override // com.umeng.analytics.pro.y
    public void a(Context context, String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(context).edit();
            editorEdit.putString("session_id", str);
            editorEdit.putLong(w.b, 0L);
            editorEdit.putLong(w.f5318e, jCurrentTimeMillis);
            editorEdit.putLong(w.f, 0L);
            editorEdit.commit();
        } catch (Exception unused) {
        }
    }
}
