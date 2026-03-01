package com.efs.sdk.base.core.f;

import com.efs.sdk.base.core.controller.ControllerCenter;
import com.efs.sdk.base.core.f.f;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class d extends a {
    public AtomicInteger c = new AtomicInteger(0);
    public AtomicInteger d = new AtomicInteger(0);
    public AtomicInteger b = new AtomicInteger(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AtomicInteger f1850e = new AtomicInteger(0);
    public AtomicInteger f = new AtomicInteger(0);

    @Override // com.efs.sdk.base.core.f.a
    public final void a() {
        if ((this.c.get() == 0 && this.d.get() == 0 && this.b.get() == 0 && this.f.get() == 0 && this.f1850e.get() == 0) || this.f1847a == null || !ControllerCenter.getGlobalEnvStruct().isEnableWaStat()) {
            return;
        }
        ControllerCenter controllerCenter = this.f1847a;
        int i = this.c.get();
        int i2 = this.d.get();
        int i3 = this.b.get();
        int i4 = this.f.get();
        int i5 = this.f1850e.get();
        b bVar = new b("efs_core", "lf_st", f.a.f1852a.f1851a.c);
        bVar.put("create_cnt", Integer.valueOf(i));
        bVar.put("cache_cnt", Integer.valueOf(i2));
        bVar.put("req_cnt", Integer.valueOf(i3));
        bVar.put("err_cnt", Integer.valueOf(i4));
        bVar.put("expire_cnt", Integer.valueOf(i5));
        this.c.addAndGet(i * (-1));
        this.d.addAndGet(i2 * (-1));
        this.b.addAndGet(i3 * (-1));
        this.f.addAndGet(i4 * (-1));
        this.f1850e.addAndGet(i5 * (-1));
        controllerCenter.send(bVar);
    }

    public final void b() {
        this.c.incrementAndGet();
    }

    public final void c() {
        this.d.incrementAndGet();
    }

    public final void d() {
        this.f1850e.incrementAndGet();
    }

    public final void e() {
        this.f.incrementAndGet();
    }
}
