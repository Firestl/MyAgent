package com.igexin.c.a.b.a.a;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a extends com.igexin.c.a.b.f {
    public volatile boolean f;
    public volatile int g;
    public String h;
    public volatile boolean i;

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.igexin.c.a.b.a.a.a$a, reason: collision with other inner class name */
    public static final class EnumC0073a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int f3155a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final /* synthetic */ int[] d = {1, 2, 3};

        public EnumC0073a(String str, int i) {
        }

        public static int[] a() {
            return (int[]) d.clone();
        }
    }

    public a(int i, com.igexin.c.a.b.d dVar) {
        super(i, null, dVar);
        this.g = EnumC0073a.f3155a;
        this.i = true;
    }

    public abstract void c_();

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void d() {
        super.d();
        this.o = true;
    }

    @Override // com.igexin.c.a.d.f
    public final void e() {
        Thread thread = this.K;
        if (!thread.isAlive() || thread.isInterrupted()) {
            return;
        }
        thread.interrupt();
    }

    @Override // com.igexin.c.a.d.f
    public final void f() {
    }

    public final boolean g() {
        return this.g == EnumC0073a.c;
    }
}
