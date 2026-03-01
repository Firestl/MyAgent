package com.igexin.push.core.a.a;

import com.igexin.push.core.j;
import com.igexin.push.d.c;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends com.igexin.push.core.a.a {
    public static final String b = "HeartbeatAction";

    @Override // com.igexin.push.core.a.a
    public final void a() {
    }

    @Override // com.igexin.push.core.a.a
    public final boolean a(Object obj) {
        if (!(obj instanceof com.igexin.push.d.c.f)) {
            return true;
        }
        c.b.f3517a.d();
        com.igexin.c.a.c.a.a("heartbeatRsp", new Object[0]);
        j.a().a(j.a.f3487a);
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
