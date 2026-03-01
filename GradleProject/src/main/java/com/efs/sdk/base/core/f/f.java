package com.efs.sdk.base.core.f;

import com.efs.sdk.base.core.controller.ControllerCenter;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c f1851a;
    public ControllerCenter b;
    public d c;
    public g d;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f1852a = new f(0);
    }

    public /* synthetic */ f(byte b) {
        this();
    }

    public final void a(String str, String str2, String str3) {
        this.d.a(str, str2, str3);
    }

    public f() {
        this.f1851a = new c();
        this.c = new d();
        this.d = new g();
    }

    public final void a(int i) {
        ControllerCenter controllerCenter = this.b;
        if (controllerCenter != null) {
            controllerCenter.send(a("flow_limit", i));
        }
    }

    public final void a(int i, String str) {
        if (this.b != null || ControllerCenter.getGlobalEnvStruct().isEnableWaStat()) {
            b bVarA = a("flow_limit_type", i);
            bVarA.put("code", str);
            this.b.send(bVarA);
        }
    }

    public final b a(String str, int i) {
        b bVar = new b("efs_core", str, this.f1851a.c);
        bVar.put("cver", Integer.valueOf(i));
        return bVar;
    }
}
