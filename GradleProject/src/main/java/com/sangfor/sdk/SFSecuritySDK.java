package com.sangfor.sdk;

import com.sangfor.sdk.base.SFException;
import com.sangfor.sdk.base.SFSDKType;
import com.sangfor.sdk.entry.SFAppStoreEntry;
import com.sangfor.sdk.entry.SFAuthEntry;
import com.sangfor.sdk.entry.SFDataSyncEntry;
import com.sangfor.sdk.entry.SFOnlineEntry;
import java.util.HashSet;
import java.util.Map;
import supwisdom.c91;
import supwisdom.v61;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class SFSecuritySDK {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SFAuthEntry f3887a = null;
    public SFOnlineEntry b = null;
    public SFAppStoreEntry c = null;
    public SFDataSyncEntry d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c91 f3888e = null;
    public long f;

    static {
        v61.a();
    }

    public SFSecuritySDK() {
        new HashSet();
        new HashSet();
        this.f = 0L;
        SFSDKType sFSDKType = SFSDKType.SDK_TYPE_UNKNOWN;
    }

    private native boolean allowResetPasswordNative(long j);

    private native void cancelNative(long j);

    private native boolean checkAppAuthorizedNative(long j, String str);

    private native long createSessionNative(int i);

    private native void destroySessionNative(long j);

    public static native String encodeAESAndBase64Native(String str, String str2, String str3);

    private native long getAppLockEntryNative(long j);

    private native long getAppStoreEntryNative(long j);

    private native long getAuthEntryNative(long j);

    private native long getDataSyncEntryNative(long j);

    private native long getDiagnosisEntryNative(long j);

    private native int getFlagsNative(long j);

    public static native String getGlobalConfigNative(String str);

    private native long getLineEntryNative(long j);

    private native int getModeNative(long j);

    private native long getMultiProcessesEntryNative(long j);

    private native long getOnlineEntryNative(long j);

    private native String getOptionNative(long j, String str);

    private native void getPswStrategyNative(long j, Object obj);

    private native long getSandboxEntryNative(long j);

    public static native String getSdkVersionNative();

    public static native long getSocks5ProxyPortNative();

    private native long getSsoEntryNative(long j);

    private native long getTunnelEntryNative(long j);

    private native String getValueForKeyNative(long j, String str);

    private native long getWebAppEntryNative(long j);

    private native String[] getWhiteAppListNative(long j);

    private native String[] getWhiteSignatureListNative(long j);

    private native void initNative(long j, int i, int i2, Map<String, String> map);

    public static native boolean isSpaSeedExistNative(String str);

    private native boolean isSupportAutoTicketNative(long j);

    public static native void loadNative(int i, int i2, Map<String, String> map);

    private native void logoutNative(long j);

    private native boolean needProcessNative(long j, String str);

    private native void reUploadLogNative(long j, String str);

    private native void refuseUploadLogNative(long j, String str);

    private native void resetPasswordNative(long j, String str, String str2, Object obj);

    private native void setAuthResultListenerNative(long j, Object obj);

    private native void setCommonHttpsRequestListenerNative(long j, Object obj);

    private native void setGenericNotificationListenerNative(long j, Object obj);

    public static native boolean setGlobalConfigNative(String str, String str2);

    private native void setLineResultListenerNative(long j, Object obj);

    private native void setLogoutListenerNative(long j, Object obj);

    private native boolean setNetworkWhitelistNative(long j, String str);

    private native void setOfflineListenerNative(long j, Object obj);

    private native void setOnlineListenerNative(long j, Object obj);

    private native void setOnlineStateListenerNative(long j, Object obj);

    private native boolean setOptionNative(long j, String str, String str2);

    public static native void setSpaConfigNative(String str, boolean z, Object obj);

    private native void setTrustDeviceListenerNative(long j, Object obj);

    private native void setTunnelStatusListenerNative(long j, Object obj);

    private native void setUploadLogListenerNative(long j, Object obj);

    private native int setValueForKeyNative(long j, String str, String str2);

    private native boolean setWhiteAppListNative(long j, String[] strArr);

    private native boolean setWhiteSignatureListNative(long j, String[] strArr);

    public static native boolean spaSeedFormatCheckNative(String str);

    private native boolean startAutoTicketNative(long j);

    private native void startSessionAuthNative(long j, String str, String str2);

    private native void unInitNative(long j);

    private native void uploadLogNative(long j, String str);

    public final void a() {
        if (this.f == 0) {
            throw new RuntimeException(SFException.ERROR_NATVIE_NULL);
        }
    }

    public SFAppStoreEntry b() {
        return this.c;
    }

    public SFAuthEntry c() {
        return this.f3887a;
    }

    public SFDataSyncEntry d() {
        return this.d;
    }

    public int e() {
        a();
        return getFlagsNative(this.f);
    }

    public c91 f() {
        return this.f3888e;
    }

    public SFOnlineEntry g() {
        return this.b;
    }

    public String a(String str) {
        a();
        return getValueForKeyNative(this.f, str);
    }
}
