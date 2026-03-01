package com.zx.a.I8b7;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a1 f6283a;
    public v1 b;
    public boolean c;

    public final class a implements Runnable {
        /* JADX WARN: Code restructure failed: missing block: B:12:?, code lost:
        
            throw null;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r4 = this;
                r0 = 0
                throw r0     // Catch: java.lang.Throwable -> L2
            L2:
                r1 = move-exception
                java.lang.Exception r2 = new java.lang.Exception     // Catch: java.lang.Throwable -> L11
                java.lang.String r3 = r1.getMessage()     // Catch: java.lang.Throwable -> L11
                java.lang.Throwable r1 = r1.getCause()     // Catch: java.lang.Throwable -> L11
                r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L11
                throw r0     // Catch: java.lang.Throwable -> L11
            L11:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.zx.a.I8b7.t0.a.run():void");
        }
    }

    public t0(v1 v1Var, a1 a1Var) {
        this.b = v1Var;
        this.f6283a = a1Var;
    }

    public d1 a() throws Exception {
        synchronized (this) {
            if (this.c) {
                throw new IllegalStateException("Already Executed");
            }
            this.c = true;
        }
        try {
            r rVar = this.b.f6296a;
            synchronized (rVar) {
                rVar.d.add(this);
            }
            return b();
        } finally {
            r rVar2 = this.b.f6296a;
            rVar2.a(rVar2.d, this, false);
        }
    }

    public d1 b() throws Exception {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.b.b);
        this.f6283a.getClass();
        arrayList.add(new e());
        arrayList.add(new i(this.b));
        arrayList.add(new f());
        a1 a1Var = this.f6283a;
        if (arrayList.size() <= 0) {
            throw new AssertionError();
        }
        u0 u0Var = new u0(arrayList, null, 1, a1Var);
        c0 c0Var = (c0) arrayList.get(0);
        d1 d1VarA = c0Var.a(u0Var);
        if (d1VarA == null) {
            throw new NullPointerException("interceptor " + c0Var + " returned null");
        }
        if (d1VarA.f6207e != null) {
            return d1VarA;
        }
        throw new IllegalStateException("interceptor " + c0Var + " returned a response with no body");
    }
}
