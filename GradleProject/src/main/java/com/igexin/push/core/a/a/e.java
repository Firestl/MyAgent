package com.igexin.push.core.a.a;

import com.igexin.push.core.d;
import com.igexin.push.d.c.q;

/* JADX INFO: loaded from: classes2.dex */
public final class e extends com.igexin.push.core.a.a {
    public static final String b = com.igexin.push.config.c.f3286a + "_RegisterFailResultAction";

    @Override // com.igexin.push.core.a.a
    public final void a() {
    }

    @Override // com.igexin.push.core.a.a
    public final boolean a(Object obj) {
        if ((obj instanceof q) && ((q) obj).b == 1) {
            com.igexin.c.a.c.a.a(b, "Register failed because of the wrong appid");
            com.igexin.c.a.c.a.a(b + "|Register failed because of the wrong appid", new Object[0]);
            com.igexin.c.a.c.a.d.a().a("Register failed because of the wrong appid = " + com.igexin.push.core.e.f3403a);
            com.igexin.push.core.e.q = true;
            d.a.f3384a.h.b();
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
