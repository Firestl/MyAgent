package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.io.Closeable;
import javax.annotation.Nullable;
import okhttp3.Protocol;
import supwisdom.us1;

/* JADX INFO: compiled from: Response.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dt1 implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final bt1 f7379a;
    public final Protocol b;
    public final int c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Nullable
    public final ts1 f7380e;
    public final us1 f;

    @Nullable
    public final et1 g;

    @Nullable
    public final dt1 h;

    @Nullable
    public final dt1 i;

    @Nullable
    public final dt1 j;
    public final long k;
    public final long l;

    @Nullable
    public volatile fs1 m;

    /* JADX INFO: compiled from: Response.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @Nullable
        public bt1 f7381a;

        @Nullable
        public Protocol b;
        public int c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @Nullable
        public ts1 f7382e;
        public us1.a f;

        @Nullable
        public et1 g;

        @Nullable
        public dt1 h;

        @Nullable
        public dt1 i;

        @Nullable
        public dt1 j;
        public long k;
        public long l;

        public a() {
            this.c = -1;
            this.f = new us1.a();
        }

        public a a(bt1 bt1Var) {
            this.f7381a = bt1Var;
            return this;
        }

        public a b(String str, String str2) {
            this.f.c(str, str2);
            return this;
        }

        public a c(@Nullable dt1 dt1Var) {
            if (dt1Var != null) {
                a("networkResponse", dt1Var);
            }
            this.h = dt1Var;
            return this;
        }

        public a d(@Nullable dt1 dt1Var) {
            if (dt1Var != null) {
                b(dt1Var);
            }
            this.j = dt1Var;
            return this;
        }

        public a a(Protocol protocol) {
            this.b = protocol;
            return this;
        }

        public final void b(dt1 dt1Var) {
            if (dt1Var.g != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public a a(int i) {
            this.c = i;
            return this;
        }

        public a(dt1 dt1Var) {
            this.c = -1;
            this.f7381a = dt1Var.f7379a;
            this.b = dt1Var.b;
            this.c = dt1Var.c;
            this.d = dt1Var.d;
            this.f7382e = dt1Var.f7380e;
            this.f = dt1Var.f.b();
            this.g = dt1Var.g;
            this.h = dt1Var.h;
            this.i = dt1Var.i;
            this.j = dt1Var.j;
            this.k = dt1Var.k;
            this.l = dt1Var.l;
        }

        public a a(String str) {
            this.d = str;
            return this;
        }

        public a b(long j) {
            this.k = j;
            return this;
        }

        public a a(@Nullable ts1 ts1Var) {
            this.f7382e = ts1Var;
            return this;
        }

        public a a(String str, String str2) {
            this.f.a(str, str2);
            return this;
        }

        public a a(us1 us1Var) {
            this.f = us1Var.b();
            return this;
        }

        public a a(@Nullable et1 et1Var) {
            this.g = et1Var;
            return this;
        }

        public a a(@Nullable dt1 dt1Var) {
            if (dt1Var != null) {
                a("cacheResponse", dt1Var);
            }
            this.i = dt1Var;
            return this;
        }

        public final void a(String str, dt1 dt1Var) {
            if (dt1Var.g == null) {
                if (dt1Var.h == null) {
                    if (dt1Var.i == null) {
                        if (dt1Var.j == null) {
                            return;
                        }
                        throw new IllegalArgumentException(str + ".priorResponse != null");
                    }
                    throw new IllegalArgumentException(str + ".cacheResponse != null");
                }
                throw new IllegalArgumentException(str + ".networkResponse != null");
            }
            throw new IllegalArgumentException(str + ".body != null");
        }

        public a a(long j) {
            this.l = j;
            return this;
        }

        public dt1 a() {
            if (this.f7381a != null) {
                if (this.b != null) {
                    if (this.c >= 0) {
                        if (this.d != null) {
                            return new dt1(this);
                        }
                        throw new IllegalStateException("message == null");
                    }
                    throw new IllegalStateException("code < 0: " + this.c);
                }
                throw new IllegalStateException("protocol == null");
            }
            throw new IllegalStateException("request == null");
        }
    }

    public dt1(a aVar) {
        this.f7379a = aVar.f7381a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.f7380e = aVar.f7382e;
        this.f = aVar.f.a();
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.l = aVar.l;
    }

    @Nullable
    public String a(String str) {
        return a(str, null);
    }

    public fs1 b() {
        fs1 fs1Var = this.m;
        if (fs1Var != null) {
            return fs1Var;
        }
        fs1 fs1VarA = fs1.a(this.f);
        this.m = fs1VarA;
        return fs1VarA;
    }

    public int c() {
        return this.c;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        et1 et1Var = this.g;
        if (et1Var == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        et1Var.close();
    }

    @Nullable
    public ts1 d() {
        return this.f7380e;
    }

    public us1 e() {
        return this.f;
    }

    public boolean f() {
        int i = this.c;
        return i >= 200 && i < 300;
    }

    public String g() {
        return this.d;
    }

    public a h() {
        return new a(this);
    }

    @Nullable
    public dt1 i() {
        return this.j;
    }

    public long j() {
        return this.l;
    }

    public bt1 k() {
        return this.f7379a;
    }

    public long l() {
        return this.k;
    }

    public String toString() {
        return "Response{protocol=" + this.b + ", code=" + this.c + ", message=" + this.d + ", url=" + this.f7379a.g() + Operators.BLOCK_END;
    }

    @Nullable
    public String a(String str, @Nullable String str2) {
        String strA = this.f.a(str);
        return strA != null ? strA : str2;
    }

    @Nullable
    public et1 a() {
        return this.g;
    }
}
