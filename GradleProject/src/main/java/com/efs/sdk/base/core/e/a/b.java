package com.efs.sdk.base.core.e.a;

import com.efs.sdk.base.core.b.a;
import com.efs.sdk.base.core.controller.ControllerCenter;
import com.efs.sdk.base.core.f.f;

/* JADX INFO: loaded from: classes.dex */
public final class b extends a {
    @Override // com.efs.sdk.base.core.e.a.a
    public final void a(com.efs.sdk.base.core.d.b bVar) {
        com.efs.sdk.base.core.b.e eVarA;
        byte[] bArr;
        if (bVar.b.f1841a) {
            b(bVar);
            return;
        }
        com.efs.sdk.base.core.b.a aVar = a.b.f1805a;
        if ("wa".equals(bVar.f1840a.f1838a) || com.efs.sdk.base.core.b.c.a().f1807a) {
            if ((bVar.f1840a.c == 0 && ((bArr = bVar.c) == null || bArr.length == 0)) || (eVarA = aVar.c.a(bVar.f1840a.b)) == null) {
                return;
            }
            eVarA.a(bVar);
            return;
        }
        if (!aVar.f1804a) {
            com.efs.sdk.base.core.f.f fVar = f.a.f1852a;
            int i = com.efs.sdk.base.core.config.a.c.a().d.f1827a;
            if (fVar.b != null || ControllerCenter.getGlobalEnvStruct().isEnableWaStat()) {
                fVar.b.send(fVar.a("disk_limit", i));
            }
        }
        aVar.f1804a = true;
    }
}
