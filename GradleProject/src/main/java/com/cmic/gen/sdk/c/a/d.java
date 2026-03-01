package com.cmic.gen.sdk.c.a;

import android.content.Context;
import android.net.Network;
import android.os.Build;
import com.cmic.gen.sdk.e.n;
import com.cmic.gen.sdk.e.r;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class d implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f1696a;

    public void a(b bVar) {
        this.f1696a = bVar;
    }

    @Override // com.cmic.gen.sdk.c.a.b
    public void a(final com.cmic.gen.sdk.c.c.c cVar, final com.cmic.gen.sdk.c.d.c cVar2, final com.cmic.gen.sdk.a aVar) {
        if (!cVar.b()) {
            b(cVar, cVar2, aVar);
            return;
        }
        r rVarA = r.a((Context) null);
        if (Build.VERSION.SDK_INT >= 21) {
            rVarA.a(new r.a() { // from class: com.cmic.gen.sdk.c.a.d.1

                /* JADX INFO: renamed from: e, reason: collision with root package name */
                public final AtomicBoolean f1698e = new AtomicBoolean(false);

                @Override // com.cmic.gen.sdk.e.r.a
                public void a(final Network network) {
                    if (this.f1698e.getAndSet(true)) {
                        return;
                    }
                    n.a(new n.a(null, aVar) { // from class: com.cmic.gen.sdk.c.a.d.1.1
                        @Override // com.cmic.gen.sdk.e.n.a
                        public void a() {
                            if (network == null) {
                                cVar2.a(com.cmic.gen.sdk.c.d.a.a(102508));
                            } else {
                                com.cmic.gen.sdk.e.c.b("WifiChangeInterceptor", "onAvailable");
                                cVar.a(network);
                                AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                d.this.b(cVar, cVar2, aVar);
                            }
                        }
                    });
                }
            });
        } else {
            com.cmic.gen.sdk.e.c.a("WifiChangeInterceptor", "低版本不在支持wifi切换");
            cVar2.a(com.cmic.gen.sdk.c.d.a.a(102508));
        }
    }

    public void b(com.cmic.gen.sdk.c.c.c cVar, final com.cmic.gen.sdk.c.d.c cVar2, com.cmic.gen.sdk.a aVar) {
        b bVar = this.f1696a;
        if (bVar != null) {
            bVar.a(cVar, new com.cmic.gen.sdk.c.d.c() { // from class: com.cmic.gen.sdk.c.a.d.2
                @Override // com.cmic.gen.sdk.c.d.c
                public void a(com.cmic.gen.sdk.c.d.a aVar2) {
                    cVar2.a(aVar2);
                }

                @Override // com.cmic.gen.sdk.c.d.c
                public void a(com.cmic.gen.sdk.c.d.b bVar2) {
                    cVar2.a(bVar2);
                }
            }, aVar);
        }
    }
}
