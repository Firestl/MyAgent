package com.sangfor.sdk.entry;

import android.text.TextUtils;
import com.sangfor.sdk.base.SFException;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class SFAppStoreEntry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3923a;

    private native void checkAppUpdateNative(long j, String str, Object obj);

    private native void deletePackageNative(long j, String str, Object obj);

    private native boolean getEmmAppListNative(long j, Object obj);

    private native void getInstalledAppInfoNative(long j, String str, Object obj);

    private native void getLocalHttpsServerInfoNative(long j, String str, Object obj);

    private native void gotoTrustSettingsNative(long j, String str, Object obj);

    private native void installAppNative(long j, String str, Object obj);

    private native void notifyInstallEventNative(long j, String str, String str2);

    private native void pauseDownloadAppNative(long j, String str, Object obj);

    private native void registerAppStoreEventListenerNative(long j, Object obj);

    private native void remoteUpdateNative(long j, String str, Object obj);

    private native void setLocalHttpsServerInfoNative(long j, String str, Object obj);

    private native void startDownloadAppNative(long j, String str, Object obj);

    private native void unInstallAppNative(long j, String str, Object obj);

    private native void unRegisterAppStoreEventListenerNative(long j, Object obj);

    public final void a() {
        if (this.f3923a == 0) {
            throw new RuntimeException(SFException.ERROR_NATVIE_NULL);
        }
    }

    public synchronized void a(String str, String str2) {
        a();
        if (!TextUtils.isEmpty(str2)) {
            notifyInstallEventNative(this.f3923a, str, str2);
        } else {
            throw new IllegalArgumentException(SFException.ERROR_PARAM_IS_EMPRTY);
        }
    }
}
