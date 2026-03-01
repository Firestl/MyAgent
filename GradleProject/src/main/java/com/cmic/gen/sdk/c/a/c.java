package com.cmic.gen.sdk.c.a;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f1694a;
    public com.cmic.gen.sdk.c.d.c b;
    public final com.cmic.gen.sdk.c.a c = new com.cmic.gen.sdk.c.a();

    public void a(b bVar) {
        this.f1694a = bVar;
    }

    @Override // com.cmic.gen.sdk.c.a.b
    public void a(com.cmic.gen.sdk.c.c.c cVar, com.cmic.gen.sdk.c.d.c cVar2, com.cmic.gen.sdk.a aVar) {
        b(cVar, cVar2, aVar);
    }

    public void b(final com.cmic.gen.sdk.c.c.c cVar, final com.cmic.gen.sdk.c.d.c cVar2, final com.cmic.gen.sdk.a aVar) {
        if (this.f1694a != null) {
            this.b = new com.cmic.gen.sdk.c.d.c() { // from class: com.cmic.gen.sdk.c.a.c.1
                @Override // com.cmic.gen.sdk.c.d.c
                public void a(com.cmic.gen.sdk.c.d.a aVar2) {
                    if (!cVar.j()) {
                        cVar2.a(aVar2);
                        return;
                    }
                    com.cmic.gen.sdk.e.c.a("RetryAndRedirectInterceptor", "retry: " + cVar.a());
                    c.this.b(cVar, cVar2, aVar);
                }

                @Override // com.cmic.gen.sdk.c.d.c
                public void a(com.cmic.gen.sdk.c.d.b bVar) {
                    com.cmic.gen.sdk.c.c.c cVarB;
                    if (bVar.d()) {
                        cVarB = c.this.c.a(cVar, bVar, aVar);
                    } else {
                        if (TextUtils.isEmpty(c.this.c.a())) {
                            cVar2.a(bVar);
                            return;
                        }
                        cVarB = c.this.c.b(cVar, bVar, aVar);
                    }
                    c.this.b(cVarB, cVar2, aVar);
                }
            };
            if (cVar.g()) {
                this.f1694a.a(cVar, this.b, aVar);
            } else {
                cVar2.a(com.cmic.gen.sdk.c.d.a.a(200025));
            }
        }
    }
}
