package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Quaternion.java */
/* JADX INFO: loaded from: classes.dex */
public class pj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f8797a;
    public double b;
    public double c;
    public double d;

    public pj() {
    }

    public pj a(gj gjVar) {
        if (gjVar == null || !gjVar.f7739e) {
            return null;
        }
        double dCos = Math.cos(gjVar.b / 2.0d);
        double dCos2 = Math.cos(gjVar.c / 2.0d);
        double dCos3 = Math.cos(gjVar.d / 2.0d);
        double dSin = Math.sin(gjVar.b / 2.0d);
        double dSin2 = Math.sin(gjVar.c / 2.0d);
        double dSin3 = Math.sin(gjVar.d / 2.0d);
        String str = gjVar.f7738a;
        if ("XYZ".equals(str)) {
            double d = dSin * dCos2;
            double d2 = dCos * dSin2;
            this.f8797a = (d * dCos3) + (d2 * dSin3);
            this.b = (d2 * dCos3) - (d * dSin3);
            double d3 = dCos * dCos2;
            double d4 = dSin * dSin2;
            this.c = (d3 * dSin3) + (d4 * dCos3);
            this.d = (d3 * dCos3) - (d4 * dSin3);
        } else if ("YXZ".equals(str)) {
            double d5 = dSin * dCos2;
            double d6 = dCos * dSin2;
            this.f8797a = (d5 * dCos3) + (d6 * dSin3);
            this.b = (d6 * dCos3) - (d5 * dSin3);
            double d7 = dCos * dCos2;
            double d8 = dSin * dSin2;
            this.c = (d7 * dSin3) - (d8 * dCos3);
            this.d = (d7 * dCos3) + (d8 * dSin3);
        } else if ("ZXY".equals(str)) {
            double d9 = dSin * dCos2;
            double d10 = dCos * dSin2;
            this.f8797a = (d9 * dCos3) - (d10 * dSin3);
            this.b = (d10 * dCos3) + (d9 * dSin3);
            double d11 = dCos * dCos2;
            double d12 = dSin * dSin2;
            this.c = (d11 * dSin3) + (d12 * dCos3);
            this.d = (d11 * dCos3) - (d12 * dSin3);
        } else if ("ZYX".equals(str)) {
            double d13 = dSin * dCos2;
            double d14 = dCos * dSin2;
            this.f8797a = (d13 * dCos3) - (d14 * dSin3);
            this.b = (d14 * dCos3) + (d13 * dSin3);
            double d15 = dCos * dCos2;
            double d16 = dSin * dSin2;
            this.c = (d15 * dSin3) - (d16 * dCos3);
            this.d = (d15 * dCos3) + (d16 * dSin3);
        } else if ("YZX".equals(str)) {
            double d17 = dSin * dCos2;
            double d18 = dCos * dSin2;
            this.f8797a = (d17 * dCos3) + (d18 * dSin3);
            this.b = (d18 * dCos3) + (d17 * dSin3);
            double d19 = dCos * dCos2;
            double d20 = dSin * dSin2;
            this.c = (d19 * dSin3) - (d20 * dCos3);
            this.d = (d19 * dCos3) - (d20 * dSin3);
        } else if ("XZY".equals(str)) {
            double d21 = dSin * dCos2;
            double d22 = dCos * dSin2;
            this.f8797a = (d21 * dCos3) - (d22 * dSin3);
            this.b = (d22 * dCos3) - (d21 * dSin3);
            double d23 = dCos * dCos2;
            double d24 = dSin * dSin2;
            this.c = (d23 * dSin3) + (d24 * dCos3);
            this.d = (d23 * dCos3) + (d24 * dSin3);
        }
        return this;
    }

    public String toString() {
        return "Quaternion{x=" + this.f8797a + ", y=" + this.b + ", z=" + this.c + ", w=" + this.d + Operators.BLOCK_END;
    }

    public pj(double d, double d2, double d3, double d4) {
        this.f8797a = d;
        this.b = d2;
        this.c = d3;
        this.d = d4;
    }

    public pj a(uj ujVar, double d) {
        double d2 = d / 2.0d;
        double dSin = Math.sin(d2);
        this.f8797a = ujVar.f9416a * dSin;
        this.b = ujVar.b * dSin;
        this.c = ujVar.c * dSin;
        this.d = Math.cos(d2);
        return this;
    }

    public pj a(pj pjVar) {
        a(this, pjVar);
        return this;
    }

    public pj a(pj pjVar, pj pjVar2) {
        double d = pjVar.f8797a;
        double d2 = pjVar.b;
        double d3 = pjVar.c;
        double d4 = pjVar.d;
        double d5 = pjVar2.f8797a;
        double d6 = pjVar2.b;
        double d7 = pjVar2.c;
        double d8 = pjVar2.d;
        this.f8797a = (((d * d8) + (d4 * d5)) + (d2 * d7)) - (d3 * d6);
        this.b = (((d2 * d8) + (d4 * d6)) + (d3 * d5)) - (d * d7);
        this.c = (((d3 * d8) + (d4 * d7)) + (d * d6)) - (d2 * d5);
        this.d = (((d4 * d8) - (d * d5)) - (d2 * d6)) - (d3 * d7);
        return this;
    }
}
