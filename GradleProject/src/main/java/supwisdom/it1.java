package supwisdom;

import java.io.IOException;
import java.net.Socket;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import supwisdom.dt1;
import supwisdom.us1;

/* JADX INFO: compiled from: Internal.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class it1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static it1 f7984a;

    public abstract int a(dt1.a aVar);

    @Nullable
    public abstract IOException a(gs1 gs1Var, @Nullable IOException iOException);

    public abstract Socket a(ls1 ls1Var, cs1 cs1Var, vt1 vt1Var);

    public abstract gs1 a(zs1 zs1Var, bt1 bt1Var);

    public abstract st1 a(ls1 ls1Var, cs1 cs1Var, vt1 vt1Var, ft1 ft1Var);

    public abstract tt1 a(ls1 ls1Var);

    public abstract vt1 a(gs1 gs1Var);

    public abstract void a(ms1 ms1Var, SSLSocket sSLSocket, boolean z);

    public abstract void a(us1.a aVar, String str);

    public abstract void a(us1.a aVar, String str, String str2);

    public abstract boolean a(cs1 cs1Var, cs1 cs1Var2);

    public abstract boolean a(ls1 ls1Var, st1 st1Var);

    public abstract void b(ls1 ls1Var, st1 st1Var);
}
