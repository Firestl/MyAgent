package supwisdom;

import android.graphics.Insets;
import android.graphics.Rect;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class o8 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final o8 f8642e = new o8(0, 0, 0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8643a;
    public final int b;
    public final int c;
    public final int d;

    public o8(int i, int i2, int i3, int i4) {
        this.f8643a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public static o8 a(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? f8642e : new o8(i, i2, i3, i4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o8.class != obj.getClass()) {
            return false;
        }
        o8 o8Var = (o8) obj;
        return this.d == o8Var.d && this.f8643a == o8Var.f8643a && this.c == o8Var.c && this.b == o8Var.b;
    }

    public int hashCode() {
        return (((((this.f8643a * 31) + this.b) * 31) + this.c) * 31) + this.d;
    }

    public String toString() {
        return "Insets{left=" + this.f8643a + ", top=" + this.b + ", right=" + this.c + ", bottom=" + this.d + Operators.BLOCK_END;
    }

    public static o8 a(Rect rect) {
        return a(rect.left, rect.top, rect.right, rect.bottom);
    }

    public static o8 a(o8 o8Var, o8 o8Var2) {
        return a(Math.max(o8Var.f8643a, o8Var2.f8643a), Math.max(o8Var.b, o8Var2.b), Math.max(o8Var.c, o8Var2.c), Math.max(o8Var.d, o8Var2.d));
    }

    public static o8 a(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets a() {
        return Insets.of(this.f8643a, this.b, this.c, this.d);
    }
}
