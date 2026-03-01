package com.igexin.push.core.d;

import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class b implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3387a = "GTConfigProxy";
    public static volatile b b;
    public e c = new a();

    private void a(e eVar) {
        this.c = eVar;
    }

    public static b d() {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b = new b();
                }
            }
        }
        return b;
    }

    @Override // com.igexin.push.core.d.e
    public final Map<String, String> a() {
        e eVar = this.c;
        if (eVar != null) {
            return eVar.a();
        }
        return null;
    }

    @Override // com.igexin.push.core.d.e
    public final boolean a(Map<String, String> map) {
        e eVar = this.c;
        if (eVar != null) {
            return eVar.a(map);
        }
        return false;
    }

    @Override // com.igexin.push.core.d.e
    public final Map<String, String> b() {
        e eVar = this.c;
        if (eVar != null) {
            return eVar.b();
        }
        return null;
    }

    @Override // com.igexin.push.core.d.e
    public final boolean c() {
        e eVar = this.c;
        if (eVar != null) {
            return eVar.c();
        }
        return false;
    }
}
