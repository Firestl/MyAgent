package com.igexin.c.a.b.a.a;

import android.text.TextUtils;
import com.igexin.c.a.b.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public final class e extends a {
    public static final String P = "GS-R";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3165a = -2035;
    public byte[] Q;
    public h j;
    public com.igexin.c.a.b.a.a.a.b k;
    public com.igexin.c.a.b.d l;

    public e(h hVar, com.igexin.c.a.b.d dVar) {
        super(-2035, dVar);
        this.l = dVar;
        this.j = hVar;
    }

    private void a(com.igexin.c.a.b.a.a.a.b bVar) {
        this.k = bVar;
    }

    @Override // com.igexin.c.a.b.f, com.igexin.c.a.d.f, com.igexin.c.a.d.a.a
    public final void a() {
        super.a();
        com.igexin.c.a.c.a.a(P, "rt dispose");
        com.igexin.c.a.c.a.a("GS-R|rt dispose", new Object[0]);
        if (this.k != null) {
            if (this.g != a.EnumC0073a.b) {
                this.k.a();
            } else if (!TextUtils.isEmpty(this.h)) {
                this.k.a(new Exception(this.h));
            }
        }
        if (this.Q != null) {
            this.Q = null;
        }
        this.k = null;
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void b_() throws Exception {
        super.b_();
        Thread threadCurrentThread = Thread.currentThread();
        com.igexin.c.a.c.a.a("GS-R|" + threadCurrentThread + " running", new Object[0]);
        while (this.i && !threadCurrentThread.isInterrupted() && !this.f) {
            try {
                com.igexin.c.a.d.a.e eVarB = this.l.b(null, this.j);
                if (eVarB != null) {
                    com.igexin.c.a.b.e.a().a(eVarB);
                    com.igexin.c.a.b.e.a().b();
                } else {
                    com.igexin.c.a.c.a.a(P, "read error data");
                    com.igexin.c.a.c.a.a("GS-R|read error data", new Object[0]);
                }
                this.g = a.EnumC0073a.f3155a;
            } catch (Throwable th) {
                this.i = false;
                if (this.g != a.EnumC0073a.c) {
                    this.g = a.EnumC0073a.b;
                    this.h = (th.getMessage() == null || !th.getMessage().equals("read = -1, end of stream !")) ? th.toString() : "end of stream";
                }
                com.igexin.c.a.c.a.a(th);
            }
        }
        threadCurrentThread.isInterrupted();
        this.f = true;
        com.igexin.c.a.c.a.a("GS-R|finish ~~~~~~", new Object[0]);
    }

    @Override // com.igexin.c.a.d.a.e
    public final int c() {
        return -2035;
    }

    @Override // com.igexin.c.a.b.a.a.a
    public final void c_() {
        this.i = false;
        this.g = a.EnumC0073a.c;
    }
}
