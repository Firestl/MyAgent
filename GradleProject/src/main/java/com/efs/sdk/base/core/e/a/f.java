package com.efs.sdk.base.core.e.a;

/* JADX INFO: loaded from: classes.dex */
public final class f extends a {
    @Override // com.efs.sdk.base.core.e.a.a
    public final void a(com.efs.sdk.base.core.d.b bVar) {
        Double d;
        com.efs.sdk.base.core.config.a.c cVarA = com.efs.sdk.base.core.config.a.c.a();
        String str = bVar.f1840a.f1838a;
        com.efs.sdk.base.core.config.a.b bVar2 = cVarA.d;
        if (com.efs.sdk.base.core.config.a.c.f1829a.nextDouble() * 100.0d <= ((!bVar2.f.containsKey(str) || (d = bVar2.f.get(str)) == null) ? 100.0d : d.doubleValue())) {
            b(bVar);
        }
    }
}
