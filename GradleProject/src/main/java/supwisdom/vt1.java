package supwisdom;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.List;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import supwisdom.ut1;
import supwisdom.ws1;

/* JADX INFO: compiled from: StreamAllocation.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vt1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final cs1 f9548a;
    public ut1.a b;
    public ft1 c;
    public final ls1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final gs1 f9549e;
    public final rs1 f;
    public final Object g;
    public final ut1 h;
    public int i;
    public st1 j;
    public boolean k;
    public boolean l;
    public boolean m;
    public yt1 n;

    /* JADX INFO: compiled from: StreamAllocation.java */
    public static final class a extends WeakReference<vt1> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f9550a;

        public a(vt1 vt1Var, Object obj) {
            super(vt1Var);
            this.f9550a = obj;
        }
    }

    public vt1(ls1 ls1Var, cs1 cs1Var, gs1 gs1Var, rs1 rs1Var, Object obj) {
        this.d = ls1Var;
        this.f9548a = cs1Var;
        this.f9549e = gs1Var;
        this.f = rs1Var;
        this.h = new ut1(cs1Var, i(), gs1Var, rs1Var);
        this.g = obj;
    }

    public yt1 a(zs1 zs1Var, ws1.a aVar, boolean z) {
        try {
            yt1 yt1VarA = a(aVar.connectTimeoutMillis(), aVar.readTimeoutMillis(), aVar.writeTimeoutMillis(), zs1Var.r(), zs1Var.x(), z).a(zs1Var, aVar, this);
            synchronized (this.d) {
                this.n = yt1VarA;
            }
            return yt1VarA;
        } catch (IOException e2) {
            throw new RouteException(e2);
        }
    }

    public yt1 b() {
        yt1 yt1Var;
        synchronized (this.d) {
            yt1Var = this.n;
        }
        return yt1Var;
    }

    public synchronized st1 c() {
        return this.j;
    }

    public boolean d() {
        ut1.a aVar;
        return this.c != null || ((aVar = this.b) != null && aVar.b()) || this.h.a();
    }

    public void e() {
        st1 st1Var;
        Socket socketA;
        synchronized (this.d) {
            st1Var = this.j;
            socketA = a(true, false, false);
            if (this.j != null) {
                st1Var = null;
            }
        }
        kt1.a(socketA);
        if (st1Var != null) {
            this.f.connectionReleased(this.f9549e, st1Var);
        }
    }

    public void f() {
        st1 st1Var;
        Socket socketA;
        synchronized (this.d) {
            st1Var = this.j;
            socketA = a(false, true, false);
            if (this.j != null) {
                st1Var = null;
            }
        }
        kt1.a(socketA);
        if (st1Var != null) {
            it1.f7984a.a(this.f9549e, (IOException) null);
            this.f.connectionReleased(this.f9549e, st1Var);
            this.f.callEnd(this.f9549e);
        }
    }

    public final Socket g() {
        st1 st1Var = this.j;
        if (st1Var == null || !st1Var.k) {
            return null;
        }
        return a(false, false, true);
    }

    public ft1 h() {
        return this.c;
    }

    public final tt1 i() {
        return it1.f7984a.a(this.d);
    }

    public String toString() {
        st1 st1VarC = c();
        return st1VarC != null ? st1VarC.toString() : this.f9548a.toString();
    }

    public Socket b(st1 st1Var) {
        if (this.n == null && this.j.n.size() == 1) {
            Reference<vt1> reference = this.j.n.get(0);
            Socket socketA = a(true, false, false);
            this.j = st1Var;
            st1Var.n.add(reference);
            return socketA;
        }
        throw new IllegalStateException();
    }

    public final st1 a(int i, int i2, int i3, int i4, boolean z, boolean z2) throws Throwable {
        while (true) {
            st1 st1VarA = a(i, i2, i3, i4, z);
            synchronized (this.d) {
                if (st1VarA.l == 0) {
                    return st1VarA;
                }
                if (st1VarA.a(z2)) {
                    return st1VarA;
                }
                e();
            }
        }
    }

    public final st1 a(int i, int i2, int i3, int i4, boolean z) throws Throwable {
        Socket socketG;
        Socket socketA;
        st1 st1Var;
        st1 st1Var2;
        ft1 ft1VarC;
        boolean z2;
        boolean z3;
        ut1.a aVar;
        synchronized (this.d) {
            if (!this.l) {
                if (this.n == null) {
                    if (!this.m) {
                        st1 st1Var3 = this.j;
                        socketG = g();
                        socketA = null;
                        if (this.j != null) {
                            st1Var2 = this.j;
                            st1Var = null;
                        } else {
                            st1Var = st1Var3;
                            st1Var2 = null;
                        }
                        if (!this.k) {
                            st1Var = null;
                        }
                        if (st1Var2 == null) {
                            it1.f7984a.a(this.d, this.f9548a, this, null);
                            if (this.j != null) {
                                st1Var2 = this.j;
                                ft1VarC = null;
                                z2 = true;
                            } else {
                                ft1VarC = this.c;
                            }
                        } else {
                            ft1VarC = null;
                        }
                        z2 = false;
                    } else {
                        throw new IOException("Canceled");
                    }
                } else {
                    throw new IllegalStateException("codec != null");
                }
            } else {
                throw new IllegalStateException("released");
            }
        }
        kt1.a(socketG);
        if (st1Var != null) {
            this.f.connectionReleased(this.f9549e, st1Var);
        }
        if (z2) {
            this.f.connectionAcquired(this.f9549e, st1Var2);
        }
        if (st1Var2 != null) {
            return st1Var2;
        }
        if (ft1VarC != null || ((aVar = this.b) != null && aVar.b())) {
            z3 = false;
        } else {
            this.b = this.h.c();
            z3 = true;
        }
        synchronized (this.d) {
            if (this.m) {
                throw new IOException("Canceled");
            }
            if (z3) {
                List<ft1> listA = this.b.a();
                int size = listA.size();
                int i5 = 0;
                while (true) {
                    if (i5 >= size) {
                        break;
                    }
                    ft1 ft1Var = listA.get(i5);
                    it1.f7984a.a(this.d, this.f9548a, this, ft1Var);
                    if (this.j != null) {
                        st1Var2 = this.j;
                        this.c = ft1Var;
                        z2 = true;
                        break;
                    }
                    i5++;
                }
            }
            if (!z2) {
                if (ft1VarC == null) {
                    ft1VarC = this.b.c();
                }
                this.c = ft1VarC;
                this.i = 0;
                st1Var2 = new st1(this.d, ft1VarC);
                a(st1Var2, false);
            }
        }
        if (z2) {
            this.f.connectionAcquired(this.f9549e, st1Var2);
            return st1Var2;
        }
        st1Var2.a(i, i2, i3, i4, z, this.f9549e, this.f);
        i().a(st1Var2.e());
        synchronized (this.d) {
            this.k = true;
            it1.f7984a.b(this.d, st1Var2);
            if (st1Var2.d()) {
                socketA = it1.f7984a.a(this.d, this.f9548a, this);
                st1Var2 = this.j;
            }
        }
        kt1.a(socketA);
        this.f.connectionAcquired(this.f9549e, st1Var2);
        return st1Var2;
    }

    public void a(boolean z, yt1 yt1Var, long j, IOException iOException) {
        st1 st1Var;
        Socket socketA;
        boolean z2;
        this.f.responseBodyEnd(this.f9549e, j);
        synchronized (this.d) {
            if (yt1Var != null) {
                if (yt1Var == this.n) {
                    if (!z) {
                        this.j.l++;
                    }
                    st1Var = this.j;
                    socketA = a(z, false, true);
                    if (this.j != null) {
                        st1Var = null;
                    }
                    z2 = this.l;
                }
            }
            throw new IllegalStateException("expected " + this.n + " but was " + yt1Var);
        }
        kt1.a(socketA);
        if (st1Var != null) {
            this.f.connectionReleased(this.f9549e, st1Var);
        }
        if (iOException != null) {
            this.f.callFailed(this.f9549e, it1.f7984a.a(this.f9549e, iOException));
        } else if (z2) {
            it1.f7984a.a(this.f9549e, (IOException) null);
            this.f.callEnd(this.f9549e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.net.Socket a(boolean r2, boolean r3, boolean r4) {
        /*
            r1 = this;
            r0 = 0
            if (r4 == 0) goto L5
            r1.n = r0
        L5:
            r4 = 1
            if (r3 == 0) goto La
            r1.l = r4
        La:
            supwisdom.st1 r3 = r1.j
            if (r3 == 0) goto L4e
            if (r2 == 0) goto L12
            r3.k = r4
        L12:
            supwisdom.yt1 r2 = r1.n
            if (r2 != 0) goto L4e
            boolean r2 = r1.l
            if (r2 != 0) goto L20
            supwisdom.st1 r2 = r1.j
            boolean r2 = r2.k
            if (r2 == 0) goto L4e
        L20:
            supwisdom.st1 r2 = r1.j
            r1.a(r2)
            supwisdom.st1 r2 = r1.j
            java.util.List<java.lang.ref.Reference<supwisdom.vt1>> r2 = r2.n
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L4a
            supwisdom.st1 r2 = r1.j
            long r3 = java.lang.System.nanoTime()
            r2.o = r3
            supwisdom.it1 r2 = supwisdom.it1.f7984a
            supwisdom.ls1 r3 = r1.d
            supwisdom.st1 r4 = r1.j
            boolean r2 = r2.a(r3, r4)
            if (r2 == 0) goto L4a
            supwisdom.st1 r2 = r1.j
            java.net.Socket r2 = r2.socket()
            goto L4b
        L4a:
            r2 = r0
        L4b:
            r1.j = r0
            r0 = r2
        L4e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.vt1.a(boolean, boolean, boolean):java.net.Socket");
    }

    public void a() {
        yt1 yt1Var;
        st1 st1Var;
        synchronized (this.d) {
            this.m = true;
            yt1Var = this.n;
            st1Var = this.j;
        }
        if (yt1Var != null) {
            yt1Var.cancel();
        } else if (st1Var != null) {
            st1Var.a();
        }
    }

    public void a(IOException iOException) {
        st1 st1Var;
        boolean z;
        Socket socketA;
        synchronized (this.d) {
            st1Var = null;
            if (iOException instanceof StreamResetException) {
                ErrorCode errorCode = ((StreamResetException) iOException).errorCode;
                if (errorCode == ErrorCode.REFUSED_STREAM) {
                    int i = this.i + 1;
                    this.i = i;
                    if (i > 1) {
                        this.c = null;
                        z = true;
                    }
                    z = false;
                } else {
                    if (errorCode != ErrorCode.CANCEL) {
                        this.c = null;
                        z = true;
                    }
                    z = false;
                }
            } else {
                if (this.j != null && (!this.j.d() || (iOException instanceof ConnectionShutdownException))) {
                    if (this.j.l == 0) {
                        if (this.c != null && iOException != null) {
                            this.h.a(this.c, iOException);
                        }
                        this.c = null;
                    }
                    z = true;
                }
                z = false;
            }
            st1 st1Var2 = this.j;
            socketA = a(z, false, true);
            if (this.j == null && this.k) {
                st1Var = st1Var2;
            }
        }
        kt1.a(socketA);
        if (st1Var != null) {
            this.f.connectionReleased(this.f9549e, st1Var);
        }
    }

    public void a(st1 st1Var, boolean z) {
        if (this.j == null) {
            this.j = st1Var;
            this.k = z;
            st1Var.n.add(new a(this, this.g));
            return;
        }
        throw new IllegalStateException();
    }

    public final void a(st1 st1Var) {
        int size = st1Var.n.size();
        for (int i = 0; i < size; i++) {
            if (st1Var.n.get(i).get() == this) {
                st1Var.n.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }
}
