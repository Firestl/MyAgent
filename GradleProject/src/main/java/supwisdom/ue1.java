package supwisdom;

import com.squareup.okhttp.Protocol;
import com.taobao.weex.el.parse.Operators;
import java.util.Collections;
import java.util.List;
import supwisdom.oe1;

/* JADX INFO: compiled from: Response.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ue1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final se1 f9403a;
    public final Protocol b;
    public final int c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ne1 f9404e;
    public final oe1 f;
    public final ve1 g;
    public ue1 h;
    public ue1 i;
    public final ue1 j;
    public volatile de1 k;

    /* JADX INFO: compiled from: Response.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public se1 f9405a;
        public Protocol b;
        public int c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ne1 f9406e;
        public oe1.b f;
        public ve1 g;
        public ue1 h;
        public ue1 i;
        public ue1 j;

        public b() {
            this.c = -1;
            this.f = new oe1.b();
        }

        public b a(se1 se1Var) {
            this.f9405a = se1Var;
            return this;
        }

        public b b(String str, String str2) {
            this.f.c(str, str2);
            return this;
        }

        public b c(ue1 ue1Var) {
            if (ue1Var != null) {
                a("networkResponse", ue1Var);
            }
            this.h = ue1Var;
            return this;
        }

        public b d(ue1 ue1Var) {
            if (ue1Var != null) {
                b(ue1Var);
            }
            this.j = ue1Var;
            return this;
        }

        public b a(Protocol protocol) {
            this.b = protocol;
            return this;
        }

        public final void b(ue1 ue1Var) {
            if (ue1Var.g != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public b a(int i) {
            this.c = i;
            return this;
        }

        public b(ue1 ue1Var) {
            this.c = -1;
            this.f9405a = ue1Var.f9403a;
            this.b = ue1Var.b;
            this.c = ue1Var.c;
            this.d = ue1Var.d;
            this.f9406e = ue1Var.f9404e;
            this.f = ue1Var.f.a();
            this.g = ue1Var.g;
            this.h = ue1Var.h;
            this.i = ue1Var.i;
            this.j = ue1Var.j;
        }

        public b a(String str) {
            this.d = str;
            return this;
        }

        public b a(ne1 ne1Var) {
            this.f9406e = ne1Var;
            return this;
        }

        public b a(String str, String str2) {
            this.f.a(str, str2);
            return this;
        }

        public b a(oe1 oe1Var) {
            this.f = oe1Var.a();
            return this;
        }

        public b a(ve1 ve1Var) {
            this.g = ve1Var;
            return this;
        }

        public b a(ue1 ue1Var) {
            if (ue1Var != null) {
                a("cacheResponse", ue1Var);
            }
            this.i = ue1Var;
            return this;
        }

        public final void a(String str, ue1 ue1Var) {
            if (ue1Var.g == null) {
                if (ue1Var.h == null) {
                    if (ue1Var.i == null) {
                        if (ue1Var.j == null) {
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

        public ue1 a() {
            if (this.f9405a != null) {
                if (this.b != null) {
                    if (this.c >= 0) {
                        return new ue1(this);
                    }
                    throw new IllegalStateException("code < 0: " + this.c);
                }
                throw new IllegalStateException("protocol == null");
            }
            throw new IllegalStateException("request == null");
        }
    }

    public Protocol k() {
        return this.b;
    }

    public se1 l() {
        return this.f9403a;
    }

    public String toString() {
        return "Response{protocol=" + this.b + ", code=" + this.c + ", message=" + this.d + ", url=" + this.f9403a.i() + Operators.BLOCK_END;
    }

    public ue1(b bVar) {
        this.f9403a = bVar.f9405a;
        this.b = bVar.b;
        this.c = bVar.c;
        this.d = bVar.d;
        this.f9404e = bVar.f9406e;
        this.f = bVar.f.a();
        this.g = bVar.g;
        this.h = bVar.h;
        this.i = bVar.i;
        this.j = bVar.j;
    }

    public String a(String str) {
        return a(str, null);
    }

    public de1 b() {
        de1 de1Var = this.k;
        if (de1Var != null) {
            return de1Var;
        }
        de1 de1VarA = de1.a(this.f);
        this.k = de1VarA;
        return de1VarA;
    }

    public ue1 c() {
        return this.i;
    }

    public List<he1> d() {
        String str;
        int i = this.c;
        if (i == 401) {
            str = "WWW-Authenticate";
        } else {
            if (i != 407) {
                return Collections.emptyList();
            }
            str = "Proxy-Authenticate";
        }
        return rf1.a(g(), str);
    }

    public int e() {
        return this.c;
    }

    public ne1 f() {
        return this.f9404e;
    }

    public oe1 g() {
        return this.f;
    }

    public String h() {
        return this.d;
    }

    public ue1 i() {
        return this.h;
    }

    public b j() {
        return new b();
    }

    public String a(String str, String str2) {
        String strA = this.f.a(str);
        return strA != null ? strA : str2;
    }

    public ve1 a() {
        return this.g;
    }
}
