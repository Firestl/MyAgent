package com.sangfor.sdk.entry;

import android.java.com.sangfor.sdk.utils.SPUtil;
import com.sangfor.sdk.base.SFSDKType;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class SFServerSelector {
    public static synchronized void a(SFSDKType sFSDKType) {
        SPUtil.put("SP_LAST_SERVER_TYPE_KEY", Integer.valueOf(sFSDKType.intValue()));
    }

    private native void cancelNative(long j);

    public static native long createNative(String str, Object obj);

    private native void destoryInstanceNative(long j);

    private native void executeNative(long j, int i);

    private native void setFilterHandlerNative(long j, Object obj);
}
