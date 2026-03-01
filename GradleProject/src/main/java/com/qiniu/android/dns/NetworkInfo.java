package com.qiniu.android.dns;

/* JADX INFO: loaded from: classes2.dex */
public final class NetworkInfo {
    public static final NetworkInfo b = new NetworkInfo(NetSatus.NO_NETWORK, 0);
    public static final NetworkInfo c = new NetworkInfo(NetSatus.WIFI, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3866a;

    public enum NetSatus {
        NO_NETWORK,
        WIFI,
        MOBILE
    }

    public NetworkInfo(NetSatus netSatus, int i) {
        this.f3866a = i;
    }
}
