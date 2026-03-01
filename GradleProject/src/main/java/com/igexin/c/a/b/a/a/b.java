package com.igexin.c.a.b.a.a;

import android.text.TextUtils;
import com.igexin.c.a.b.a.a.a;
import com.igexin.push.config.SDKUrlConfig;
import java.net.InetSocketAddress;
import java.net.Socket;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3156a = -2037;
    public static final String j = "GS-C";
    public static final int k = 10000;
    public Socket P;
    public com.igexin.c.a.b.a.a.a.d l;

    public b(com.igexin.c.a.b.a.a.a.d dVar) {
        super(-2037, null);
        this.l = dVar;
    }

    @Override // com.igexin.c.a.b.f, com.igexin.c.a.d.f, com.igexin.c.a.d.a.a
    public final void a() {
        Socket socket;
        super.a();
        com.igexin.c.a.c.a.a("GS-C|sc dispose", new Object[0]);
        if (this.l != null) {
            if (this.g == a.EnumC0073a.c) {
                this.l.a();
            } else if (this.g == a.EnumC0073a.b) {
                if (!TextUtils.isEmpty(this.h)) {
                    this.l.a(new Exception(this.h));
                }
            } else if (this.g == a.EnumC0073a.f3155a && (socket = this.P) != null) {
                this.l.a(socket);
            }
        }
        this.l = null;
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void b_() throws Exception {
        super.b_();
        com.igexin.push.c.c.a().d().a();
        String connectAddress = SDKUrlConfig.getConnectAddress();
        try {
            String[] strArrA = com.igexin.c.a.b.g.a(connectAddress);
            String str = strArrA[1];
            int i = Integer.parseInt(strArrA[2]);
            com.igexin.c.a.c.a.a("GS-C|start connect :  " + connectAddress + " *********", new Object[0]);
            com.igexin.c.a.b.a.a.a.d dVar = this.l;
            if (dVar != null) {
                dVar.b();
            }
            Socket socket = new Socket();
            this.P = socket;
            try {
                socket.connect(new InetSocketAddress(str, i), 10000);
                com.igexin.c.a.c.a.a("GS-C|connected :  " + connectAddress + " #########", new Object[0]);
                com.igexin.c.a.c.a.a("GS-C|local-" + connectAddress + " port:" + i, new Object[0]);
                if (this.g != a.EnumC0073a.c) {
                    this.g = a.EnumC0073a.f3155a;
                }
            } catch (Exception e2) {
                if (this.g != a.EnumC0073a.c) {
                    this.g = a.EnumC0073a.b;
                    this.h = e2.toString();
                }
            }
            this.f = true;
        } catch (Exception e3) {
            com.igexin.c.a.c.a.a(e3);
            com.igexin.c.a.c.a.a("GS-C|ips invalid, " + e3.toString(), new Object[0]);
            throw e3;
        }
    }

    @Override // com.igexin.c.a.d.a.e
    public final int c() {
        return -2037;
    }

    @Override // com.igexin.c.a.b.a.a.a
    public final void c_() {
        this.g = a.EnumC0073a.c;
    }
}
