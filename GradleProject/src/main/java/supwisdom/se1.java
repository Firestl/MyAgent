package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import supwisdom.oe1;

/* JADX INFO: compiled from: Request.java */
/* JADX INFO: loaded from: classes2.dex */
public final class se1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f9158a;
    public final String b;
    public final oe1 c;
    public final te1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f9159e;
    public volatile URL f;
    public volatile URI g;
    public volatile de1 h;

    /* JADX INFO: compiled from: Request.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f9160a;
        public URL b;
        public String c;
        public oe1.b d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public te1 f9161e;
        public Object f;

        public b() {
            this.c = "GET";
            this.d = new oe1.b();
        }

        public b a(URL url) {
            if (url == null) {
                throw new IllegalArgumentException("url == null");
            }
            this.b = url;
            this.f9160a = url.toString();
            return this;
        }

        public b b(String str) {
            if (str == null) {
                throw new IllegalArgumentException("url == null");
            }
            this.f9160a = str;
            this.b = null;
            return this;
        }

        public b(se1 se1Var) {
            this.f9160a = se1Var.f9158a;
            this.b = se1Var.f;
            this.c = se1Var.b;
            this.f9161e = se1Var.d;
            this.f = se1Var.f9159e;
            this.d = se1Var.c.a();
        }

        public b a(String str, String str2) {
            this.d.a(str, str2);
            return this;
        }

        public b b(String str, String str2) {
            this.d.c(str, str2);
            return this;
        }

        public b a(String str) {
            this.d.b(str);
            return this;
        }

        public b b() {
            a("GET", (te1) null);
            return this;
        }

        public b a(oe1 oe1Var) {
            this.d = oe1Var.a();
            return this;
        }

        public b a(de1 de1Var) {
            String string = de1Var.toString();
            if (string.isEmpty()) {
                a(HttpHeaders.HEAD_KEY_CACHE_CONTROL);
                return this;
            }
            b(HttpHeaders.HEAD_KEY_CACHE_CONTROL, string);
            return this;
        }

        public b a(String str, te1 te1Var) {
            if (str != null && str.length() != 0) {
                if (te1Var != null && !pf1.b(str)) {
                    throw new IllegalArgumentException("method " + str + " must not have a request body.");
                }
                if (te1Var == null && pf1.b(str)) {
                    te1Var = te1.a(null, gf1.f7729a);
                }
                this.c = str;
                this.f9161e = te1Var;
                return this;
            }
            throw new IllegalArgumentException("method == null || method.length() == 0");
        }

        public se1 a() {
            if (this.f9160a != null) {
                return new se1(this);
            }
            throw new IllegalStateException("url == null");
        }
    }

    public URI g() throws IOException {
        try {
            URI uri = this.g;
            if (uri != null) {
                return uri;
            }
            URI uriA = ef1.c().a(h());
            this.g = uriA;
            return uriA;
        } catch (URISyntaxException e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public URL h() {
        try {
            URL url = this.f;
            if (url != null) {
                return url;
            }
            URL url2 = new URL(this.f9158a);
            this.f = url2;
            return url2;
        } catch (MalformedURLException e2) {
            throw new RuntimeException("Malformed URL: " + this.f9158a, e2);
        }
    }

    public String i() {
        return this.f9158a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request{method=");
        sb.append(this.b);
        sb.append(", url=");
        sb.append(this.f9158a);
        sb.append(", tag=");
        Object obj = this.f9159e;
        if (obj == this) {
            obj = null;
        }
        sb.append(obj);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public se1(b bVar) {
        this.f9158a = bVar.f9160a;
        this.b = bVar.c;
        this.c = bVar.d.a();
        this.d = bVar.f9161e;
        this.f9159e = bVar.f != null ? bVar.f : this;
        this.f = bVar.b;
    }

    public String a(String str) {
        return this.c.a(str);
    }

    public List<String> b(String str) {
        return this.c.c(str);
    }

    public oe1 c() {
        return this.c;
    }

    public boolean d() {
        return h().getProtocol().equals("https");
    }

    public String e() {
        return this.b;
    }

    public b f() {
        return new b();
    }

    public te1 a() {
        return this.d;
    }

    public de1 b() {
        de1 de1Var = this.h;
        if (de1Var != null) {
            return de1Var;
        }
        de1 de1VarA = de1.a(this.c);
        this.h = de1VarA;
        return de1VarA;
    }
}
