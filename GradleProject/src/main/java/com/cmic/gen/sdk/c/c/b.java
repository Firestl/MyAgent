package com.cmic.gen.sdk.c.c;

import com.cmic.gen.sdk.c.b.e;
import com.cmic.gen.sdk.e.p;

/* JADX INFO: loaded from: classes.dex */
public class b extends c {
    public final e b;
    public boolean c;

    public b(String str, e eVar, String str2, String str3) {
        super(str, eVar, str2, str3);
        this.c = false;
        this.b = eVar;
    }

    public void a(com.cmic.gen.sdk.a aVar) {
        if (this.c) {
            return;
        }
        com.cmic.gen.sdk.c.b.a aVarC = this.b.c();
        String[] strArrA = null;
        if (!aVar.b("isCloseIpv4", false)) {
            strArrA = p.a(true);
            aVarC.q(strArrA[0]);
        }
        if (!aVar.b("isCloseIpv6", false)) {
            if (strArrA == null) {
                strArrA = p.a(true);
            }
            aVarC.r(strArrA[1]);
        }
        aVarC.n(aVarC.u(aVar.b("appkey")));
        this.b.a(aVarC);
        this.b.a(true);
        this.f1717a = this.b.b().toString();
        this.c = true;
    }
}
