package supwisdom;

import android.content.Context;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class w61 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f9591a;
    public boolean b;

    /* JADX INFO: compiled from: Proguard */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final w61 f9592a = new w61();
    }

    public static synchronized boolean a() {
        return c().b;
    }

    public static Context b() {
        return c().f9591a;
    }

    public static final w61 c() {
        return b.f9592a;
    }

    public w61() {
        this.b = false;
    }
}
