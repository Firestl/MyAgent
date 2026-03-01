package com.igexin.push.d;

/* JADX INFO: loaded from: classes2.dex */
public final class d implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3543a = "NormalModel";

    @Override // com.igexin.push.d.b
    public final long a() {
        long j;
        boolean zA = com.igexin.push.g.c.a();
        com.igexin.push.core.e.n = com.igexin.push.g.c.e();
        boolean z = com.igexin.push.core.e.p;
        boolean z2 = com.igexin.push.core.e.s;
        boolean z3 = com.igexin.push.core.e.n;
        com.igexin.c.a.c.a.a("NormalModel|isSdkOn = " + com.igexin.push.core.e.p + " isPushOn = " + com.igexin.push.core.e.s + " isBlockEndTime = " + zA + " isNetworkAvailable = " + com.igexin.push.core.e.n, new Object[0]);
        if (!com.igexin.push.core.e.n || !com.igexin.push.core.e.p || !com.igexin.push.core.e.s || !zA) {
            com.igexin.c.a.c.a.a(f3543a, "reconnect stop, interval= 20min ++++++");
            com.igexin.c.a.c.a.a("NormalModel|reconnect stop, interval= 20min ++++", new Object[0]);
            return com.igexin.push.config.c.g;
        }
        long j2 = com.igexin.push.core.e.O;
        if (j2 <= 0) {
            j = 1;
        } else {
            j = j2 + (j2 <= 300 ? 150L : j2 <= 10000 ? 500L : j2 <= com.igexin.push.config.c.k ? 1500L : com.igexin.push.config.c.l);
        }
        com.igexin.push.core.e.O = j;
        if (com.igexin.push.core.e.O > com.igexin.push.config.c.g) {
            com.igexin.push.core.e.O = com.igexin.push.config.c.g;
        }
        long j3 = com.igexin.push.core.e.O;
        com.igexin.c.a.c.a.a("NormalModel|after add auto reconnect delay time = ".concat(String.valueOf(j3)), new Object[0]);
        return j3;
    }
}
