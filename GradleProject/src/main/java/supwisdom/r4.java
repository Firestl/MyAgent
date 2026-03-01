package supwisdom;

import java.util.Arrays;

/* JADX INFO: compiled from: ArcCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public class r4 extends s4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final double[] f9005a;
    public a[] b;

    /* JADX INFO: compiled from: ArcCurveFit.java */
    public static class a {
        public static double[] s = new double[91];

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public double[] f9006a;
        public double b;
        public double c;
        public double d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public double f9007e;
        public double f;
        public double g;
        public double h;
        public double i;
        public double j;
        public double k;
        public double l;
        public double m;
        public double n;
        public double o;
        public double p;
        public boolean q;
        public boolean r;

        public a(int i, double d, double d2, double d3, double d4, double d5, double d6) {
            this.r = false;
            this.q = i == 1;
            this.c = d;
            this.d = d2;
            this.i = 1.0d / (d2 - d);
            if (3 == i) {
                this.r = true;
            }
            double d7 = d5 - d3;
            double d8 = d6 - d4;
            if (!this.r && Math.abs(d7) >= 0.001d && Math.abs(d8) >= 0.001d) {
                this.f9006a = new double[101];
                this.j = d7 * ((double) (this.q ? -1 : 1));
                this.k = d8 * ((double) (this.q ? 1 : -1));
                this.l = this.q ? d5 : d3;
                this.m = this.q ? d4 : d6;
                a(d3, d4, d5, d6);
                this.n = this.b * this.i;
                return;
            }
            this.r = true;
            this.f9007e = d3;
            this.f = d5;
            this.g = d4;
            this.h = d6;
            double dHypot = Math.hypot(d8, d7);
            this.b = dHypot;
            this.n = dHypot * this.i;
            double d9 = this.d;
            double d10 = this.c;
            this.l = d7 / (d9 - d10);
            this.m = d8 / (d9 - d10);
        }

        public double a() {
            double d = this.j * this.p;
            double dHypot = this.n / Math.hypot(d, (-this.k) * this.o);
            if (this.q) {
                d = -d;
            }
            return d * dHypot;
        }

        public double b() {
            double d = this.j * this.p;
            double d2 = (-this.k) * this.o;
            double dHypot = this.n / Math.hypot(d, d2);
            return this.q ? (-d2) * dHypot : d2 * dHypot;
        }

        public double c() {
            return this.l + (this.j * this.o);
        }

        public double d() {
            return this.m + (this.k * this.p);
        }

        public double e(double d) {
            if (d <= 0.0d) {
                return 0.0d;
            }
            if (d >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.f9006a;
            double length = d * ((double) (dArr.length - 1));
            int i = (int) length;
            return dArr[i] + ((length - ((double) i)) * (dArr[i + 1] - dArr[i]));
        }

        public void f(double d) {
            double dE = e((this.q ? this.d - d : d - this.c) * this.i) * 1.5707963267948966d;
            this.o = Math.sin(dE);
            this.p = Math.cos(dE);
        }

        public double c(double d) {
            double d2 = (d - this.c) * this.i;
            double d3 = this.f9007e;
            return d3 + (d2 * (this.f - d3));
        }

        public double d(double d) {
            double d2 = (d - this.c) * this.i;
            double d3 = this.g;
            return d3 + (d2 * (this.h - d3));
        }

        public double a(double d) {
            return this.l;
        }

        public double b(double d) {
            return this.m;
        }

        public final void a(double d, double d2, double d3, double d4) {
            double dHypot;
            double d5 = d3 - d;
            double d6 = d2 - d4;
            int i = 0;
            double d7 = 0.0d;
            double d8 = 0.0d;
            double d9 = 0.0d;
            while (true) {
                if (i >= s.length) {
                    break;
                }
                double d10 = d7;
                double radians = Math.toRadians((((double) i) * 90.0d) / ((double) (r15.length - 1)));
                double dSin = Math.sin(radians) * d5;
                double dCos = Math.cos(radians) * d6;
                if (i > 0) {
                    dHypot = d10 + Math.hypot(dSin - d8, dCos - d9);
                    s[i] = dHypot;
                } else {
                    dHypot = d10;
                }
                i++;
                d9 = dCos;
                d7 = dHypot;
                d8 = dSin;
            }
            double d11 = d7;
            this.b = d11;
            int i2 = 0;
            while (true) {
                double[] dArr = s;
                if (i2 >= dArr.length) {
                    break;
                }
                dArr[i2] = dArr[i2] / d11;
                i2++;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= this.f9006a.length) {
                    return;
                }
                double length = ((double) i3) / ((double) (r1.length - 1));
                int iBinarySearch = Arrays.binarySearch(s, length);
                if (iBinarySearch >= 0) {
                    this.f9006a[i3] = iBinarySearch / (s.length - 1);
                } else if (iBinarySearch == -1) {
                    this.f9006a[i3] = 0.0d;
                } else {
                    int i4 = -iBinarySearch;
                    int i5 = i4 - 2;
                    double[] dArr2 = s;
                    this.f9006a[i3] = (((double) i5) + ((length - dArr2[i5]) / (dArr2[i4 - 1] - dArr2[i5]))) / ((double) (dArr2.length - 1));
                }
                i3++;
            }
        }
    }

    public r4(int[] iArr, double[] dArr, double[][] dArr2) {
        int i;
        this.f9005a = dArr;
        this.b = new a[dArr.length - 1];
        int i2 = 0;
        int i3 = 1;
        int i4 = 1;
        while (i2 < this.b.length) {
            int i5 = iArr[i2];
            if (i5 == 0) {
                i = 3;
            } else if (i5 == 1) {
                i3 = 1;
                i = 1;
            } else if (i5 == 2) {
                i3 = 2;
                i = 2;
            } else if (i5 != 3) {
                i = i4;
            } else {
                i3 = i3 == 1 ? 2 : 1;
                i = i3;
            }
            int i6 = i2 + 1;
            this.b[i2] = new a(i, dArr[i2], dArr[i6], dArr2[i2][0], dArr2[i2][1], dArr2[i6][0], dArr2[i6][1]);
            i4 = i;
            i2 = i6;
        }
    }

    @Override // supwisdom.s4
    public void a(double d, double[] dArr) {
        a[] aVarArr = this.b;
        if (d < aVarArr[0].c) {
            d = aVarArr[0].c;
        }
        a[] aVarArr2 = this.b;
        if (d > aVarArr2[aVarArr2.length - 1].d) {
            d = aVarArr2[aVarArr2.length - 1].d;
        }
        int i = 0;
        while (true) {
            a[] aVarArr3 = this.b;
            if (i >= aVarArr3.length) {
                return;
            }
            if (d <= aVarArr3[i].d) {
                if (aVarArr3[i].r) {
                    dArr[0] = aVarArr3[i].c(d);
                    dArr[1] = this.b[i].d(d);
                    return;
                } else {
                    aVarArr3[i].f(d);
                    dArr[0] = this.b[i].c();
                    dArr[1] = this.b[i].d();
                    return;
                }
            }
            i++;
        }
    }

    @Override // supwisdom.s4
    public void b(double d, double[] dArr) {
        a[] aVarArr = this.b;
        if (d < aVarArr[0].c) {
            d = aVarArr[0].c;
        } else if (d > aVarArr[aVarArr.length - 1].d) {
            d = aVarArr[aVarArr.length - 1].d;
        }
        int i = 0;
        while (true) {
            a[] aVarArr2 = this.b;
            if (i >= aVarArr2.length) {
                return;
            }
            if (d <= aVarArr2[i].d) {
                if (aVarArr2[i].r) {
                    dArr[0] = aVarArr2[i].a(d);
                    dArr[1] = this.b[i].b(d);
                    return;
                } else {
                    aVarArr2[i].f(d);
                    dArr[0] = this.b[i].a();
                    dArr[1] = this.b[i].b();
                    return;
                }
            }
            i++;
        }
    }

    @Override // supwisdom.s4
    public void a(double d, float[] fArr) {
        a[] aVarArr = this.b;
        if (d < aVarArr[0].c) {
            d = aVarArr[0].c;
        } else if (d > aVarArr[aVarArr.length - 1].d) {
            d = aVarArr[aVarArr.length - 1].d;
        }
        int i = 0;
        while (true) {
            a[] aVarArr2 = this.b;
            if (i >= aVarArr2.length) {
                return;
            }
            if (d <= aVarArr2[i].d) {
                if (aVarArr2[i].r) {
                    fArr[0] = (float) aVarArr2[i].c(d);
                    fArr[1] = (float) this.b[i].d(d);
                    return;
                } else {
                    aVarArr2[i].f(d);
                    fArr[0] = (float) this.b[i].c();
                    fArr[1] = (float) this.b[i].d();
                    return;
                }
            }
            i++;
        }
    }

    @Override // supwisdom.s4
    public double b(double d, int i) {
        a[] aVarArr = this.b;
        int i2 = 0;
        if (d < aVarArr[0].c) {
            d = aVarArr[0].c;
        }
        a[] aVarArr2 = this.b;
        if (d > aVarArr2[aVarArr2.length - 1].d) {
            d = aVarArr2[aVarArr2.length - 1].d;
        }
        while (true) {
            a[] aVarArr3 = this.b;
            if (i2 >= aVarArr3.length) {
                return Double.NaN;
            }
            if (d <= aVarArr3[i2].d) {
                if (aVarArr3[i2].r) {
                    if (i == 0) {
                        return aVarArr3[i2].a(d);
                    }
                    return aVarArr3[i2].b(d);
                }
                aVarArr3[i2].f(d);
                if (i == 0) {
                    return this.b[i2].a();
                }
                return this.b[i2].b();
            }
            i2++;
        }
    }

    @Override // supwisdom.s4
    public double a(double d, int i) {
        a[] aVarArr = this.b;
        int i2 = 0;
        if (d < aVarArr[0].c) {
            d = aVarArr[0].c;
        } else if (d > aVarArr[aVarArr.length - 1].d) {
            d = aVarArr[aVarArr.length - 1].d;
        }
        while (true) {
            a[] aVarArr2 = this.b;
            if (i2 >= aVarArr2.length) {
                return Double.NaN;
            }
            if (d <= aVarArr2[i2].d) {
                if (aVarArr2[i2].r) {
                    if (i == 0) {
                        return aVarArr2[i2].c(d);
                    }
                    return aVarArr2[i2].d(d);
                }
                aVarArr2[i2].f(d);
                if (i == 0) {
                    return this.b[i2].c();
                }
                return this.b[i2].d();
            }
            i2++;
        }
    }

    @Override // supwisdom.s4
    public double[] a() {
        return this.f9005a;
    }
}
