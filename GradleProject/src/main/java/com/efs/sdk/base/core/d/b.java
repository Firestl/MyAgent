package com.efs.sdk.base.core.d;

import com.efs.sdk.base.core.util.Log;
import com.efs.sdk.base.protocol.ILogProtocol;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f1840a;
    public c b = new c();
    public byte[] c;
    public File d;

    public b(String str, byte b) {
        this.f1840a = new a(str, b);
    }

    private void d() {
        byte[] bArr;
        a aVar = this.f1840a;
        if (aVar.c == 0 && (bArr = this.c) != null) {
            aVar.f = bArr.length;
        } else if (this.f1840a.c == 1 && this.d.exists()) {
            this.f1840a.f = this.d.length();
        }
    }

    public final long a() {
        d();
        return this.f1840a.f;
    }

    public final boolean b() {
        return !"none".equals(this.f1840a.d);
    }

    public final void c() {
        this.b.f1841a = true;
    }

    public final void b(int i) {
        this.f1840a.c = i;
    }

    public final void a(String str) {
        this.f1840a.d = str;
    }

    public final void a(int i) {
        this.f1840a.f1839e = i;
        d();
    }

    public final void a(byte[] bArr) {
        this.c = bArr;
        d();
    }

    public static b a(ILogProtocol iLogProtocol) {
        b bVar = null;
        try {
            b bVar2 = new b(iLogProtocol.getLogType(), iLogProtocol.getLogProtocol());
            try {
                int bodyType = iLogProtocol.getBodyType();
                if (bodyType == 0) {
                    bVar2.b(0);
                    bVar2.a(iLogProtocol.generate());
                } else if (bodyType != 1) {
                    Log.w("efs.base", "Can not support body type: " + iLogProtocol.getBodyType());
                } else {
                    bVar2.b(1);
                    bVar2.d = new File(iLogProtocol.getFilePath());
                }
                return bVar2;
            } catch (Exception e2) {
                e = e2;
                bVar = bVar2;
                Log.e("efs.base", "log send error", e);
                return bVar;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }
}
