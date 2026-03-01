package supwisdom;

import android.text.Layout;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: WebvttCssStyle.java */
/* JADX INFO: loaded from: classes.dex */
public final class t60 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f9258a;
    public String b;
    public List<String> c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f9259e;
    public int f;
    public boolean g;
    public int h;
    public boolean i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public float o;
    public Layout.Alignment p;

    public t60() {
        a();
    }

    public void a() {
        this.f9258a = "";
        this.b = "";
        this.c = Collections.emptyList();
        this.d = "";
        this.f9259e = null;
        this.g = false;
        this.i = false;
        this.j = -1;
        this.k = -1;
        this.l = -1;
        this.m = -1;
        this.n = -1;
        this.p = null;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public boolean d() {
        return this.k == 1;
    }

    public String e() {
        return this.f9259e;
    }

    public int f() {
        if (this.g) {
            return this.f;
        }
        throw new IllegalStateException("Font color not defined");
    }

    public boolean g() {
        return this.g;
    }

    public int h() {
        if (this.i) {
            return this.h;
        }
        throw new IllegalStateException("Background color not defined.");
    }

    public boolean i() {
        return this.i;
    }

    public Layout.Alignment j() {
        return this.p;
    }

    public int k() {
        return this.n;
    }

    public float l() {
        return this.o;
    }

    public int b() {
        if (this.l == -1 && this.m == -1) {
            return -1;
        }
        return (this.l == 1 ? 1 : 0) | (this.m == 1 ? 2 : 0);
    }

    public boolean c() {
        return this.j == 1;
    }

    public t60 d(String str) {
        this.f9259e = x80.d(str);
        return this;
    }

    public t60 c(boolean z) {
        this.m = z ? 1 : 0;
        return this;
    }

    public t60 b(boolean z) {
        this.l = z ? 1 : 0;
        return this;
    }

    public t60 b(int i) {
        this.h = i;
        this.i = true;
        return this;
    }

    public void a(String str) {
        this.f9258a = str;
    }

    public void a(String[] strArr) {
        this.c = Arrays.asList(strArr);
    }

    public int a(String str, String str2, String[] strArr, String str3) {
        if (this.f9258a.isEmpty() && this.b.isEmpty() && this.c.isEmpty() && this.d.isEmpty()) {
            return str2.isEmpty() ? 1 : 0;
        }
        int iA = a(a(a(0, this.f9258a, str, 1073741824), this.b, str2, 2), this.d, str3, 4);
        if (iA == -1 || !Arrays.asList(strArr).containsAll(this.c)) {
            return 0;
        }
        return iA + (this.c.size() * 4);
    }

    public t60 a(boolean z) {
        this.k = z ? 1 : 0;
        return this;
    }

    public t60 a(int i) {
        this.f = i;
        this.g = true;
        return this;
    }

    public static int a(int i, String str, String str2, int i2) {
        if (str.isEmpty() || i == -1) {
            return i;
        }
        if (str.equals(str2)) {
            return i + i2;
        }
        return -1;
    }
}
