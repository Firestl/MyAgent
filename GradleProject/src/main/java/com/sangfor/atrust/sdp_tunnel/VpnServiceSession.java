package com.sangfor.atrust.sdp_tunnel;

import android.content.Context;
import com.sangfor.atrust.sdp_tunnel.SdpVpnService;
import com.sangfor.sdk.utils.SFLogN;
import supwisdom.my0;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class VpnServiceSession {
    public static Context c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SdpVpnService.a f3877a;
    public final Object b;

    /* JADX INFO: compiled from: Proguard */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final VpnServiceSession f3878a = new VpnServiceSession();
    }

    public static final VpnServiceSession d() {
        return b.f3878a;
    }

    private native void notifyVpnServiceStop(boolean z);

    public final void a(boolean z) {
        SFLogN.c("VpnServiceSession", "close called after Vpn revoked");
        notifyVpnServiceStop(z);
        synchronized (this.b) {
            a();
        }
    }

    public final Context b() {
        return c;
    }

    public void c() {
        SFLogN.c("VpnServiceSession", "onServiceStop");
        a(false);
    }

    public VpnServiceSession() {
        new my0();
        this.f3877a = null;
        this.b = new Object();
    }

    public void b(SdpVpnService.a aVar) {
        SFLogN.c("VpnServiceSession", "VpnService onServiceStart");
        try {
            synchronized (this.b) {
                this.f3877a = aVar;
                SFLogN.c("VpnServiceSession", "mLock before notifyAll()");
                this.b.notifyAll();
                SFLogN.c("VpnServiceSession", "mLock passed notifyAll()");
            }
        } catch (Exception e2) {
            SFLogN.a("VpnServiceSession", "mLock.notifyAll() failed!", "", e2);
        }
    }

    public final void a() {
        SFLogN.c("VpnServiceSession", "stopAll");
        SdpVpnService.a aVar = this.f3877a;
        if (aVar != null) {
            aVar.a();
        }
    }

    public void a(SdpVpnService.a aVar) {
        SFLogN.c("VpnServiceSession", "onServiceRevoke");
        a(true);
    }

    public void b(boolean z) {
        if (z) {
            SdpVpnService.a(b());
            return;
        }
        SFLogN.c("VpnServiceSession", "vpnPermission failed!");
        try {
            synchronized (this.b) {
                SFLogN.c("VpnServiceSession", "mLock before notifyAll()");
                this.b.notifyAll();
                SFLogN.c("VpnServiceSession", "mLock passed notifyAll()");
            }
        } catch (Throwable th) {
            SFLogN.a("VpnServiceSession", "mLock.notifyAll() failed!", th);
        }
    }
}
