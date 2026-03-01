package com.sangfor.sdk.entry;

import com.sangfor.sdk.base.SFAuthType;
import com.sangfor.sdk.base.SFException;
import java.util.Map;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class SFAuthEntry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3924a;

    private native void cancelNative(long j);

    private native String checkNewPwdMathRuleNative(long j, String str, String str2, String str3);

    private native void checkWeakPwdCodeNative(long j, String str, Object obj);

    private native long createAuthInstanceNative(long j);

    private native boolean doSecondaryAuthNative(long j, int i, Map<String, String> map);

    private native void getAuthConfigNative(long j, String str, Object obj);

    private native int getAuthStatusNative(long j);

    private native void reGetRandCodeNative(long j, Object obj);

    private native void reGetSmsCodeNative(long j, String str, Object obj);

    private native boolean startPrimaryAuthNative(long j, String str, String str2, Map<String, String> map, int i);

    private native boolean startPrimaryAuthWithTypeNative(long j, String str, int i, Map<String, String> map, int i2);

    public synchronized boolean a(SFAuthType sFAuthType, Map<String, String> map) {
        a();
        return doSecondaryAuthNative(this.f3924a, sFAuthType.intValue(), map);
    }

    public final void a() {
        if (this.f3924a == 0) {
            throw new RuntimeException(SFException.ERROR_NATVIE_NULL);
        }
    }
}
