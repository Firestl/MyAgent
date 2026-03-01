package com.sangfor.atrust.sdp_tunnel;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import android.os.ParcelFileDescriptor;
import com.sangfor.sdk.utils.SFLogN;
import java.io.IOException;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class SdpVpnService extends VpnService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public VpnServiceSession f3874a;
    public a b;

    /* JADX INFO: compiled from: Proguard */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ParcelFileDescriptor f3875a = null;

        public a(VpnService vpnService) {
        }

        public void a() {
            SFLogN.c("SdpVpnService", "closeTunnel");
            ParcelFileDescriptor parcelFileDescriptor = this.f3875a;
            if (parcelFileDescriptor != null) {
                try {
                    parcelFileDescriptor.close();
                } catch (IOException e2) {
                    SFLogN.b("SdpVpnService", "closeTunnel error", e2);
                }
                SFLogN.c("SdpVpnService", "VNIC closed");
            } else {
                SFLogN.c("SdpVpnService", "closeTunnel mParcelFileDescriptor is null");
            }
            this.f3875a = null;
        }
    }

    public static void a(Context context) {
        ComponentName componentNameStartService = context.startService(new Intent(context, (Class<?>) SdpVpnService.class));
        if (componentNameStartService == null) {
            SFLogN.d("SdpVpnService", "start failed.startService failed, ret:" + componentNameStartService);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        SFLogN.c("SdpVpnService", "onCreate");
        this.f3874a = VpnServiceSession.d();
        this.b = new a(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        SFLogN.c("SdpVpnService", "onDestroy");
        VpnServiceSession vpnServiceSession = this.f3874a;
        if (vpnServiceSession != null) {
            vpnServiceSession.c();
        }
    }

    @Override // android.net.VpnService
    public void onRevoke() {
        super.onRevoke();
        SFLogN.c("SdpVpnService", "onRevoke");
        this.f3874a.a(this.b);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        SFLogN.c("SdpVpnService", "onStartCommand");
        this.f3874a.b(this.b);
        return 2;
    }
}
