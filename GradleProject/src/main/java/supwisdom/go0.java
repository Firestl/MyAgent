package supwisdom;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: MacConfig.java */
/* JADX INFO: loaded from: classes.dex */
public final class go0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Deprecated
    public static final gp0 f7751a;

    static {
        new fo0().c();
        f7751a = gp0.o();
        try {
            a();
        } catch (GeneralSecurityException e2) {
            throw new ExceptionInInitializerError(e2);
        }
    }

    @Deprecated
    public static void a() throws GeneralSecurityException {
        b();
    }

    public static void b() throws GeneralSecurityException {
        fo0.a(true);
        eo0.a(true);
        ho0.d();
    }
}
