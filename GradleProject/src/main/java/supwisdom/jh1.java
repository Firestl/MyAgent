package supwisdom;

import android.graphics.Bitmap;
import android.net.Uri;
import com.squareup.picasso.Picasso;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.FunctionParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Request.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jh1 {
    public static final long s = TimeUnit.SECONDS.toNanos(5);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8062a;
    public long b;
    public int c;
    public final Uri d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8063e;
    public final String f;
    public final List<ph1> g;
    public final int h;
    public final int i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final float m;
    public final float n;
    public final float o;
    public final boolean p;
    public final Bitmap.Config q;
    public final Picasso.Priority r;

    public String a() {
        Uri uri = this.d;
        return uri != null ? String.valueOf(uri.getPath()) : Integer.toHexString(this.f8063e);
    }

    public boolean b() {
        return this.g != null;
    }

    public boolean c() {
        return (this.h == 0 && this.i == 0) ? false : true;
    }

    public String d() {
        long jNanoTime = System.nanoTime() - this.b;
        if (jNanoTime > s) {
            return g() + FunctionParser.Lexer.PLUS + TimeUnit.NANOSECONDS.toSeconds(jNanoTime) + 's';
        }
        return g() + FunctionParser.Lexer.PLUS + TimeUnit.NANOSECONDS.toMillis(jNanoTime) + "ms";
    }

    public boolean e() {
        return c() || this.m != 0.0f;
    }

    public boolean f() {
        return e() || b();
    }

    public String g() {
        return "[R" + this.f8062a + Operators.ARRAY_END;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Request{");
        int i = this.f8063e;
        if (i > 0) {
            sb.append(i);
        } else {
            sb.append(this.d);
        }
        List<ph1> list = this.g;
        if (list != null && !list.isEmpty()) {
            for (ph1 ph1Var : this.g) {
                sb.append(' ');
                sb.append(ph1Var.a());
            }
        }
        if (this.f != null) {
            sb.append(" stableKey(");
            sb.append(this.f);
            sb.append(Operators.BRACKET_END);
        }
        if (this.h > 0) {
            sb.append(" resize(");
            sb.append(this.h);
            sb.append(',');
            sb.append(this.i);
            sb.append(Operators.BRACKET_END);
        }
        if (this.j) {
            sb.append(" centerCrop");
        }
        if (this.k) {
            sb.append(" centerInside");
        }
        if (this.m != 0.0f) {
            sb.append(" rotation(");
            sb.append(this.m);
            if (this.p) {
                sb.append(" @ ");
                sb.append(this.n);
                sb.append(',');
                sb.append(this.o);
            }
            sb.append(Operators.BRACKET_END);
        }
        if (this.q != null) {
            sb.append(' ');
            sb.append(this.q);
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public jh1(Uri uri, int i, String str, List<ph1> list, int i2, int i3, boolean z, boolean z2, boolean z3, float f, float f2, float f3, boolean z4, Bitmap.Config config, Picasso.Priority priority) {
        this.d = uri;
        this.f8063e = i;
        this.f = str;
        if (list == null) {
            this.g = null;
        } else {
            this.g = Collections.unmodifiableList(list);
        }
        this.h = i2;
        this.i = i3;
        this.j = z;
        this.k = z2;
        this.l = z3;
        this.m = f;
        this.n = f2;
        this.o = f3;
        this.p = z4;
        this.q = config;
        this.r = priority;
    }

    /* JADX INFO: compiled from: Request.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Uri f8064a;
        public int b;
        public String c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f8065e;
        public boolean f;
        public boolean g;
        public boolean h;
        public float i;
        public float j;
        public float k;
        public boolean l;
        public List<ph1> m;
        public Bitmap.Config n;
        public Picasso.Priority o;

        public b(Uri uri, int i, Bitmap.Config config) {
            this.f8064a = uri;
            this.b = i;
            this.n = config;
        }

        public b a(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Width must be positive number or 0.");
            }
            if (i2 < 0) {
                throw new IllegalArgumentException("Height must be positive number or 0.");
            }
            if (i2 == 0 && i == 0) {
                throw new IllegalArgumentException("At least one dimension has to be positive number.");
            }
            this.d = i;
            this.f8065e = i2;
            return this;
        }

        public boolean b() {
            return (this.f8064a == null && this.b == 0) ? false : true;
        }

        public boolean c() {
            return (this.d == 0 && this.f8065e == 0) ? false : true;
        }

        public b a(ph1 ph1Var) {
            if (ph1Var != null) {
                if (ph1Var.a() != null) {
                    if (this.m == null) {
                        this.m = new ArrayList(2);
                    }
                    this.m.add(ph1Var);
                    return this;
                }
                throw new IllegalArgumentException("Transformation key must not be null.");
            }
            throw new IllegalArgumentException("Transformation must not be null.");
        }

        public jh1 a() {
            if (this.g && this.f) {
                throw new IllegalStateException("Center crop and center inside can not be used together.");
            }
            if (this.f && this.d == 0 && this.f8065e == 0) {
                throw new IllegalStateException("Center crop requires calling resize with positive width and height.");
            }
            if (this.g && this.d == 0 && this.f8065e == 0) {
                throw new IllegalStateException("Center inside requires calling resize with positive width and height.");
            }
            if (this.o == null) {
                this.o = Picasso.Priority.NORMAL;
            }
            return new jh1(this.f8064a, this.b, this.c, this.m, this.d, this.f8065e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.n, this.o);
        }
    }
}
