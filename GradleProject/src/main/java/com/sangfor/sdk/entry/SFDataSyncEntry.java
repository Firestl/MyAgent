package com.sangfor.sdk.entry;

import com.sangfor.sdk.base.SFException;
import com.sangfor.sdk.base.SFSyncDataResult;
import java.util.Map;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class SFDataSyncEntry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3925a;
    public Map<String, String> b;

    private native String getAuthServerInfoNative(long j);

    private native String getCompleteDataNative(long j);

    private native long getCompleteDataSizeNative(long j);

    private native String getDataByTypeNative(long j, int i);

    private native String getSessionDataNative(long j);

    private native int updateCompleteDataNative(long j, String str);

    public final void a() {
        if (this.f3925a == 0) {
            throw new RuntimeException(SFException.ERROR_NATVIE_NULL);
        }
    }

    public synchronized SFSyncDataResult b(String str) {
        a();
        return SFSyncDataResult.ValueOf(updateCompleteDataNative(this.f3925a, str));
    }

    public String a(String str) {
        return this.b.remove(str);
    }
}
