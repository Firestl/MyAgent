package com.sangfor.sdk.entry;

import com.sangfor.sdk.base.SFException;
import com.sangfor.sdk.base.SFOnlineState;
import java.util.Map;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class SFOnlineEntry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3926a;

    private native void changeOnlineStateNative(long j, int i);

    private native void changeOnlineStateWithoutRequestNative(long j, int i);

    private native Map<String, String> getOnlineInfoNative(long j);

    private native int queryStateNative(long j);

    private native void setAutoOnlineEnableNative(long j, boolean z);

    public synchronized void a(SFOnlineState sFOnlineState) {
        a();
        changeOnlineStateWithoutRequestNative(this.f3926a, sFOnlineState.toInit());
    }

    public final void a() {
        if (this.f3926a == 0) {
            throw new RuntimeException(SFException.ERROR_NATVIE_NULL);
        }
    }
}
