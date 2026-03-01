package com.igexin.c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d {
    public String c;
    public d d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d f3176e;
    public boolean f;

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int f3177a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final /* synthetic */ int[] d = {1, 2, 3};

        public a(String str, int i) {
        }

        public static int[] a() {
            return (int[]) d.clone();
        }
    }

    public d(String str) {
        this.c = str;
    }

    public d(String str, byte b) {
        this.c = str;
        this.f = true;
    }

    public static int a() {
        return a.c;
    }

    public static void a(d dVar, String str, String str2, d dVar2) {
        if (str2 == null) {
            throw new NullPointerException("filter name can't be NULL");
        }
        if (dVar != null) {
            d dVar3 = dVar.f3176e;
            if (dVar.c.equals(str)) {
                dVar2.d = dVar;
                dVar.f3176e = dVar2;
                dVar2.f3176e = dVar3;
                if (dVar3 != null) {
                    dVar3.d = dVar2;
                }
            } else {
                while (dVar3.f3176e != null && !dVar3.c.equals(str)) {
                    dVar3 = dVar3.f3176e;
                }
                d dVar4 = dVar3.f3176e;
                if (dVar4 == null) {
                    dVar3.f3176e = dVar2;
                    dVar2.d = dVar3;
                } else {
                    dVar2.f3176e = dVar4;
                    dVar3.f3176e.d = dVar2;
                    dVar2.d = dVar3;
                    dVar3.f3176e = dVar2;
                }
            }
        }
        dVar2.c = str2;
    }

    public static void b(d dVar, String str, String str2, d dVar2) {
        if (str2 == null) {
            throw new NullPointerException("filter name can't be NULL");
        }
        if (dVar != null) {
            d dVar3 = dVar.d;
            if (dVar.c.equals(str)) {
                dVar.d = dVar2;
                dVar2.f3176e = dVar;
                dVar2.d = dVar3;
                dVar3.f3176e = dVar2;
            } else {
                while (dVar3.d != null && !dVar3.c.equals(str)) {
                    dVar3 = dVar3.d;
                }
                d dVar4 = dVar3.d;
                if (dVar4 == null) {
                    dVar3.d = dVar2;
                    dVar2.f3176e = dVar3;
                } else {
                    dVar4.f3176e = dVar2;
                    dVar2.d = dVar4;
                    dVar2.f3176e = dVar3;
                    dVar3.d = dVar2;
                }
            }
        }
        dVar2.c = str2;
    }

    public static int c() {
        return a.c;
    }

    private String d() {
        return this.c;
    }

    public final Object a(f fVar, Object obj) throws Exception {
        if (obj == null) {
            throw new NullPointerException("Nothing to encode!");
        }
        d dVar = this.d;
        if (dVar != null) {
            obj = dVar.a(fVar, obj);
        }
        return a(obj);
    }

    public abstract Object a(Object obj) throws Exception;

    public final void a(d dVar) {
        if (dVar == null) {
            return;
        }
        d dVar2 = dVar.d;
        dVar.d = this;
        this.f3176e = dVar;
        this.d = dVar2;
    }

    public final com.igexin.c.a.d.a.e b(f fVar, Object obj) throws Exception {
        Object objB = b(obj);
        d dVar = this.d;
        if (dVar != null && objB != null) {
            objB = dVar.b(fVar, objB);
        }
        return (com.igexin.c.a.d.a.e) objB;
    }

    public abstract Object b(Object obj) throws Exception;

    public final void b() {
        if (this.f) {
            return;
        }
        while (true) {
            d dVar = this.d;
            if (dVar == null) {
                return;
            }
            d dVar2 = dVar.d;
            dVar.d = null;
            this.d = dVar2;
        }
    }
}
