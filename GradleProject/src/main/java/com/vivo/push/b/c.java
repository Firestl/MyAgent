package com.vivo.push.b;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: compiled from: BaseAppCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends com.vivo.push.o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5562a;
    public String b;
    public long c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5563e;
    public String f;

    public c(int i, String str) {
        super(i);
        this.c = -1L;
        this.d = -1;
        this.f5562a = null;
        this.b = str;
    }

    public final void a(int i) {
        this.f5563e = i;
    }

    public final void b(String str) {
        this.f5562a = str;
    }

    @Override // com.vivo.push.o
    public void c(com.vivo.push.a aVar) {
        aVar.a("req_id", this.f5562a);
        aVar.a("package_name", this.b);
        aVar.a("sdk_version", 800L);
        aVar.a("PUSH_APP_STATUS", this.d);
        if (TextUtils.isEmpty(this.f)) {
            return;
        }
        aVar.a("BaseAppCommand.EXTRA__HYBRIDVERSION", this.f);
    }

    @Override // com.vivo.push.o
    public void d(com.vivo.push.a aVar) {
        this.f5562a = aVar.a("req_id");
        this.b = aVar.a("package_name");
        this.c = aVar.b("sdk_version", 0L);
        this.d = aVar.b("PUSH_APP_STATUS", 0);
        this.f = aVar.a("BaseAppCommand.EXTRA__HYBRIDVERSION");
    }

    public final int f() {
        return this.f5563e;
    }

    public final void g() {
        this.f = null;
    }

    public final String h() {
        return this.f5562a;
    }

    @Override // com.vivo.push.o
    public String toString() {
        return "BaseAppCommand";
    }

    public final int a(Context context) {
        if (this.d == -1) {
            String str = this.b;
            if (TextUtils.isEmpty(str)) {
                com.vivo.push.util.o.a("BaseAppCommand", "pkg name is null");
                String strA = a();
                if (TextUtils.isEmpty(strA)) {
                    com.vivo.push.util.o.a("BaseAppCommand", "src is null");
                    return -1;
                }
                str = strA;
            }
            this.d = com.vivo.push.util.s.b(context, str);
            if (!TextUtils.isEmpty(this.f)) {
                this.d = 2;
            }
        }
        return this.d;
    }
}
