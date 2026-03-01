package com.igexin.push.core.a.a;

import android.text.TextUtils;
import com.igexin.push.core.d;
import com.igexin.push.core.k;
import com.igexin.push.d.c.i;
import com.igexin.push.d.c.p;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public final class f extends com.igexin.push.core.a.a {
    public static final String b = "RegisterResult";

    @Override // com.igexin.push.core.a.a
    public final void a() {
    }

    @Override // com.igexin.push.core.a.a
    public final boolean a(Object obj) {
        if (obj instanceof p) {
            p pVar = (p) obj;
            com.igexin.push.core.e.b(0L);
            com.igexin.c.a.c.a.a("register resp |" + pVar.b + "|" + com.igexin.push.core.e.z, new Object[0]);
            com.igexin.c.a.c.a.a("register resp cid = " + pVar.d + " device id = " + pVar.f3541e, new Object[0]);
            if (pVar.b != com.igexin.push.core.e.z) {
                com.igexin.push.core.e.v = false;
                com.igexin.c.a.c.a.a(b, "change session : from [" + com.igexin.push.core.e.z + "] to [" + pVar.b + Operators.ARRAY_END_STR);
                com.igexin.c.a.c.a.a("RegisterResult change session : from [" + com.igexin.push.core.e.z + "] to [" + pVar.b + Operators.ARRAY_END_STR, new Object[0]);
                com.igexin.c.a.c.a.a(b, "change cid : from [" + com.igexin.push.core.e.A + "] to [" + pVar.d + Operators.ARRAY_END_STR);
                com.igexin.c.a.c.a.a("RegisterResult change cid : from [" + com.igexin.push.core.e.A + "] to [" + pVar.d + Operators.ARRAY_END_STR, new Object[0]);
                if (TextUtils.isEmpty(pVar.d) || TextUtils.isEmpty(pVar.f3541e)) {
                    com.igexin.push.core.e.f.a().a(pVar.b);
                } else {
                    com.igexin.push.core.e.f fVarA = com.igexin.push.core.e.f.a();
                    String str = pVar.d;
                    String str2 = pVar.f3541e;
                    com.igexin.push.core.e.z = pVar.b;
                    if (TextUtils.isEmpty(com.igexin.push.core.e.H)) {
                        com.igexin.push.core.e.H = str2;
                    }
                    com.igexin.push.core.e.A = str;
                    fVarA.c();
                }
                com.igexin.push.core.e.Q = 0L;
            }
            long j = com.igexin.push.core.e.z;
            String str3 = com.igexin.push.core.e.A;
            String str4 = com.igexin.push.core.e.H;
            com.igexin.c.a.c.a.a("loginReqAfterRegister|new session:" + com.igexin.push.core.e.z + ", cid :" + com.igexin.push.core.e.A + ", devId :" + com.igexin.push.core.e.H, new Object[0]);
            k.a();
            i iVarD = k.d();
            com.igexin.push.e.a aVar = d.a.f3384a.h;
            StringBuilder sb = new StringBuilder("S-");
            sb.append(iVarD.b);
            aVar.a(sb.toString(), iVarD, true);
        }
        return true;
    }

    @Override // com.igexin.push.core.a.a
    public final void b() {
    }

    @Override // com.igexin.push.core.a.a
    public final boolean c() {
        return false;
    }
}
