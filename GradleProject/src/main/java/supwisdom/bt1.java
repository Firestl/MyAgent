package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import supwisdom.us1;

/* JADX INFO: compiled from: Request.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bt1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final vs1 f7096a;
    public final String b;
    public final us1 c;

    @Nullable
    public final ct1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map<Class<?>, Object> f7097e;

    @Nullable
    public volatile fs1 f;

    /* JADX INFO: compiled from: Request.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @Nullable
        public vs1 f7098a;
        public String b;
        public us1.a c;

        @Nullable
        public ct1 d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Map<Class<?>, Object> f7099e;

        public a() {
            this.f7099e = Collections.emptyMap();
            this.b = "GET";
            this.c = new us1.a();
        }

        public a a(vs1 vs1Var) {
            if (vs1Var == null) {
                throw new NullPointerException("url == null");
            }
            this.f7098a = vs1Var;
            return this;
        }

        public a b(String str) {
            if (str == null) {
                throw new NullPointerException("url == null");
            }
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                str = "http:" + str.substring(3);
            } else if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                str = "https:" + str.substring(4);
            }
            a(vs1.d(str));
            return this;
        }

        public a c() {
            a("HEAD", (ct1) null);
            return this;
        }

        public a d(ct1 ct1Var) {
            a("PUT", ct1Var);
            return this;
        }

        public a c(ct1 ct1Var) {
            a("POST", ct1Var);
            return this;
        }

        public a a(String str, String str2) {
            this.c.a(str, str2);
            return this;
        }

        public a a(String str) {
            this.c.b(str);
            return this;
        }

        public a(bt1 bt1Var) {
            Map<Class<?>, Object> linkedHashMap;
            this.f7099e = Collections.emptyMap();
            this.f7098a = bt1Var.f7096a;
            this.b = bt1Var.b;
            this.d = bt1Var.d;
            if (bt1Var.f7097e.isEmpty()) {
                linkedHashMap = Collections.emptyMap();
            } else {
                linkedHashMap = new LinkedHashMap<>(bt1Var.f7097e);
            }
            this.f7099e = linkedHashMap;
            this.c = bt1Var.c.b();
        }

        public a a(us1 us1Var) {
            this.c = us1Var.b();
            return this;
        }

        public a a(fs1 fs1Var) {
            String string = fs1Var.toString();
            if (string.isEmpty()) {
                a(HttpHeaders.HEAD_KEY_CACHE_CONTROL);
                return this;
            }
            b(HttpHeaders.HEAD_KEY_CACHE_CONTROL, string);
            return this;
        }

        public a b(String str, String str2) {
            this.c.c(str, str2);
            return this;
        }

        public a b() {
            a("GET", (ct1) null);
            return this;
        }

        public a a(@Nullable ct1 ct1Var) {
            a("DELETE", ct1Var);
            return this;
        }

        public a b(ct1 ct1Var) {
            a("PATCH", ct1Var);
            return this;
        }

        public a a(String str, @Nullable ct1 ct1Var) {
            if (str != null) {
                if (str.length() != 0) {
                    if (ct1Var != null && !bu1.b(str)) {
                        throw new IllegalArgumentException("method " + str + " must not have a request body.");
                    }
                    if (ct1Var == null && bu1.e(str)) {
                        throw new IllegalArgumentException("method " + str + " must have a request body.");
                    }
                    this.b = str;
                    this.d = ct1Var;
                    return this;
                }
                throw new IllegalArgumentException("method.length() == 0");
            }
            throw new NullPointerException("method == null");
        }

        public a a(@Nullable Object obj) {
            a((Class<? super Object>) Object.class, obj);
            return this;
        }

        public <T> a a(Class<? super T> cls, @Nullable T t) {
            if (cls == null) {
                throw new NullPointerException("type == null");
            }
            if (t == null) {
                this.f7099e.remove(cls);
            } else {
                if (this.f7099e.isEmpty()) {
                    this.f7099e = new LinkedHashMap();
                }
                this.f7099e.put(cls, cls.cast(t));
            }
            return this;
        }

        public bt1 a() {
            if (this.f7098a != null) {
                return new bt1(this);
            }
            throw new IllegalStateException("url == null");
        }
    }

    public bt1(a aVar) {
        this.f7096a = aVar.f7098a;
        this.b = aVar.b;
        this.c = aVar.c.a();
        this.d = aVar.d;
        this.f7097e = kt1.a(aVar.f7099e);
    }

    @Nullable
    public String a(String str) {
        return this.c.a(str);
    }

    public List<String> b(String str) {
        return this.c.b(str);
    }

    public us1 c() {
        return this.c;
    }

    public boolean d() {
        return this.f7096a.h();
    }

    public String e() {
        return this.b;
    }

    public a f() {
        return new a(this);
    }

    public vs1 g() {
        return this.f7096a;
    }

    public String toString() {
        return "Request{method=" + this.b + ", url=" + this.f7096a + ", tags=" + this.f7097e + Operators.BLOCK_END;
    }

    @Nullable
    public ct1 a() {
        return this.d;
    }

    public fs1 b() {
        fs1 fs1Var = this.f;
        if (fs1Var != null) {
            return fs1Var;
        }
        fs1 fs1VarA = fs1.a(this.c);
        this.f = fs1VarA;
        return fs1VarA;
    }

    @Nullable
    public <T> T a(Class<? extends T> cls) {
        return cls.cast(this.f7097e.get(cls));
    }
}
