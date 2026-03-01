package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: Matrix.java */
/* JADX INFO: loaded from: classes.dex */
public class nt0 {
    public static final nt0 j = new nt0(1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    public static final nt0 k = new nt0(0.0d, 1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    public static final nt0 l = new nt0(-1.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    public static final nt0 m = new nt0(0.0d, -1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f8565a;
    public double b;
    public double c;
    public double d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f8566e;
    public double f;
    public double g;
    public double h;
    public double i;

    public nt0(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
        this.f8565a = d5;
        this.b = d6;
        this.c = d7;
        this.d = d;
        this.f8566e = d2;
        this.f = d3;
        this.g = d4;
        this.h = d8;
        this.i = d9;
    }

    public void a(ByteBuffer byteBuffer) {
        ks.b(byteBuffer, this.d);
        ks.b(byteBuffer, this.f8566e);
        ks.a(byteBuffer, this.f8565a);
        ks.b(byteBuffer, this.f);
        ks.b(byteBuffer, this.g);
        ks.a(byteBuffer, this.b);
        ks.b(byteBuffer, this.h);
        ks.b(byteBuffer, this.i);
        ks.a(byteBuffer, this.c);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || nt0.class != obj.getClass()) {
            return false;
        }
        nt0 nt0Var = (nt0) obj;
        return Double.compare(nt0Var.d, this.d) == 0 && Double.compare(nt0Var.f8566e, this.f8566e) == 0 && Double.compare(nt0Var.f, this.f) == 0 && Double.compare(nt0Var.g, this.g) == 0 && Double.compare(nt0Var.h, this.h) == 0 && Double.compare(nt0Var.i, this.i) == 0 && Double.compare(nt0Var.f8565a, this.f8565a) == 0 && Double.compare(nt0Var.b, this.b) == 0 && Double.compare(nt0Var.c, this.c) == 0;
    }

    public int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this.f8565a);
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.b);
        int i = (((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32))) * 31) + ((int) (jDoubleToLongBits2 ^ (jDoubleToLongBits2 >>> 32)));
        long jDoubleToLongBits3 = Double.doubleToLongBits(this.c);
        int i2 = (i * 31) + ((int) (jDoubleToLongBits3 ^ (jDoubleToLongBits3 >>> 32)));
        long jDoubleToLongBits4 = Double.doubleToLongBits(this.d);
        int i3 = (i2 * 31) + ((int) (jDoubleToLongBits4 ^ (jDoubleToLongBits4 >>> 32)));
        long jDoubleToLongBits5 = Double.doubleToLongBits(this.f8566e);
        int i4 = (i3 * 31) + ((int) (jDoubleToLongBits5 ^ (jDoubleToLongBits5 >>> 32)));
        long jDoubleToLongBits6 = Double.doubleToLongBits(this.f);
        int i5 = (i4 * 31) + ((int) (jDoubleToLongBits6 ^ (jDoubleToLongBits6 >>> 32)));
        long jDoubleToLongBits7 = Double.doubleToLongBits(this.g);
        int i6 = (i5 * 31) + ((int) (jDoubleToLongBits7 ^ (jDoubleToLongBits7 >>> 32)));
        long jDoubleToLongBits8 = Double.doubleToLongBits(this.h);
        int i7 = (i6 * 31) + ((int) (jDoubleToLongBits8 ^ (jDoubleToLongBits8 >>> 32)));
        long jDoubleToLongBits9 = Double.doubleToLongBits(this.i);
        return (i7 * 31) + ((int) (jDoubleToLongBits9 ^ (jDoubleToLongBits9 >>> 32)));
    }

    public String toString() {
        if (equals(j)) {
            return "Rotate 0°";
        }
        if (equals(k)) {
            return "Rotate 90°";
        }
        if (equals(l)) {
            return "Rotate 180°";
        }
        if (equals(m)) {
            return "Rotate 270°";
        }
        return "Matrix{u=" + this.f8565a + ", v=" + this.b + ", w=" + this.c + ", a=" + this.d + ", b=" + this.f8566e + ", c=" + this.f + ", d=" + this.g + ", tx=" + this.h + ", ty=" + this.i + Operators.BLOCK_END;
    }
}
