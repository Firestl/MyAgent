package supwisdom;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintAttribute;
import com.taobao.weex.common.Constants;
import java.util.Arrays;
import java.util.LinkedHashMap;
import supwisdom.e7;

/* JADX INFO: compiled from: MotionPaths.java */
/* JADX INFO: loaded from: classes.dex */
public class o5 implements Comparable<o5> {
    public static String[] o = {"position", Constants.Name.X, Constants.Name.Y, "width", "height", "pathRotate"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t4 f8627a;
    public int b;
    public float c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f8628e;
    public float f;
    public float g;
    public float h;
    public float i;
    public int j;
    public LinkedHashMap<String, ConstraintAttribute> k;
    public int l;
    public double[] m;
    public double[] n;

    public o5() {
        this.b = 0;
        this.i = Float.NaN;
        this.j = b5.f7022e;
        this.k = new LinkedHashMap<>();
        this.l = 0;
        this.m = new double[18];
        this.n = new double[18];
    }

    public void a(h5 h5Var, o5 o5Var, o5 o5Var2) {
        float f = h5Var.f7023a / 100.0f;
        this.c = f;
        this.b = h5Var.i;
        float f2 = Float.isNaN(h5Var.j) ? f : h5Var.j;
        float f3 = Float.isNaN(h5Var.k) ? f : h5Var.k;
        float f4 = o5Var2.g;
        float f5 = o5Var.g;
        float f6 = o5Var2.h;
        float f7 = o5Var.h;
        this.d = this.c;
        float f8 = o5Var.f8628e;
        float f9 = o5Var.f;
        float f10 = (o5Var2.f8628e + (f4 / 2.0f)) - ((f5 / 2.0f) + f8);
        float f11 = (o5Var2.f + (f6 / 2.0f)) - (f9 + (f7 / 2.0f));
        float f12 = ((f4 - f5) * f2) / 2.0f;
        this.f8628e = (int) ((f8 + (f10 * f)) - f12);
        float f13 = ((f6 - f7) * f3) / 2.0f;
        this.f = (int) ((f9 + (f11 * f)) - f13);
        this.g = (int) (f5 + r9);
        this.h = (int) (f7 + r12);
        float f14 = Float.isNaN(h5Var.l) ? f : h5Var.l;
        float f15 = Float.isNaN(h5Var.o) ? 0.0f : h5Var.o;
        if (!Float.isNaN(h5Var.m)) {
            f = h5Var.m;
        }
        float f16 = Float.isNaN(h5Var.n) ? 0.0f : h5Var.n;
        this.l = 2;
        this.f8628e = (int) (((o5Var.f8628e + (f14 * f10)) + (f16 * f11)) - f12);
        this.f = (int) (((o5Var.f + (f10 * f15)) + (f11 * f)) - f13);
        this.f8627a = t4.a(h5Var.g);
        this.j = h5Var.h;
    }

    public void b(h5 h5Var, o5 o5Var, o5 o5Var2) {
        float f = h5Var.f7023a / 100.0f;
        this.c = f;
        this.b = h5Var.i;
        float f2 = Float.isNaN(h5Var.j) ? f : h5Var.j;
        float f3 = Float.isNaN(h5Var.k) ? f : h5Var.k;
        float f4 = o5Var2.g - o5Var.g;
        float f5 = o5Var2.h - o5Var.h;
        this.d = this.c;
        if (!Float.isNaN(h5Var.l)) {
            f = h5Var.l;
        }
        float f6 = o5Var.f8628e;
        float f7 = o5Var.g;
        float f8 = o5Var.f;
        float f9 = o5Var.h;
        float f10 = (o5Var2.f8628e + (o5Var2.g / 2.0f)) - ((f7 / 2.0f) + f6);
        float f11 = (o5Var2.f + (o5Var2.h / 2.0f)) - ((f9 / 2.0f) + f8);
        float f12 = f10 * f;
        float f13 = (f4 * f2) / 2.0f;
        this.f8628e = (int) ((f6 + f12) - f13);
        float f14 = f * f11;
        float f15 = (f5 * f3) / 2.0f;
        this.f = (int) ((f8 + f14) - f15);
        this.g = (int) (f7 + r7);
        this.h = (int) (f9 + r8);
        float f16 = Float.isNaN(h5Var.m) ? 0.0f : h5Var.m;
        this.l = 1;
        float f17 = (int) ((o5Var.f8628e + f12) - f13);
        this.f8628e = f17;
        float f18 = (int) ((o5Var.f + f14) - f15);
        this.f = f18;
        this.f8628e = f17 + ((-f11) * f16);
        this.f = f18 + (f10 * f16);
        this.f8627a = t4.a(h5Var.g);
        this.j = h5Var.h;
    }

    public o5(int i, int i2, h5 h5Var, o5 o5Var, o5 o5Var2) {
        this.b = 0;
        this.i = Float.NaN;
        this.j = b5.f7022e;
        this.k = new LinkedHashMap<>();
        this.l = 0;
        this.m = new double[18];
        this.n = new double[18];
        int i3 = h5Var.p;
        if (i3 == 1) {
            b(h5Var, o5Var, o5Var2);
        } else if (i3 != 2) {
            a(h5Var, o5Var, o5Var2);
        } else {
            a(i, i2, h5Var, o5Var, o5Var2);
        }
    }

    public void a(int i, int i2, h5 h5Var, o5 o5Var, o5 o5Var2) {
        float f = h5Var.f7023a / 100.0f;
        this.c = f;
        this.b = h5Var.i;
        float f2 = Float.isNaN(h5Var.j) ? f : h5Var.j;
        float f3 = Float.isNaN(h5Var.k) ? f : h5Var.k;
        float f4 = o5Var2.g;
        float f5 = o5Var.g;
        float f6 = o5Var2.h;
        float f7 = o5Var.h;
        this.d = this.c;
        float f8 = o5Var.f8628e;
        float f9 = o5Var.f;
        float f10 = o5Var2.f8628e + (f4 / 2.0f);
        float f11 = o5Var2.f + (f6 / 2.0f);
        float f12 = (f4 - f5) * f2;
        this.f8628e = (int) ((f8 + ((f10 - ((f5 / 2.0f) + f8)) * f)) - (f12 / 2.0f));
        float f13 = (f6 - f7) * f3;
        this.f = (int) ((f9 + ((f11 - (f9 + (f7 / 2.0f))) * f)) - (f13 / 2.0f));
        this.g = (int) (f5 + f12);
        this.h = (int) (f7 + f13);
        this.l = 3;
        if (!Float.isNaN(h5Var.l)) {
            this.f8628e = (int) (h5Var.l * ((int) (i - this.g)));
        }
        if (!Float.isNaN(h5Var.m)) {
            this.f = (int) (h5Var.m * ((int) (i2 - this.h)));
        }
        this.f8627a = t4.a(h5Var.g);
        this.j = h5Var.h;
    }

    public void b(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.f8628e;
        float f2 = this.f;
        float f3 = this.g;
        float f4 = this.h;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f5 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f = f5;
            } else if (i3 == 2) {
                f2 = f5;
            } else if (i3 == 3) {
                f3 = f5;
            } else if (i3 == 4) {
                f4 = f5;
            }
        }
        float f6 = f3 + f;
        float f7 = f4 + f2;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i4 = i + 1;
        fArr[i] = f + 0.0f;
        int i5 = i4 + 1;
        fArr[i4] = f2 + 0.0f;
        int i6 = i5 + 1;
        fArr[i5] = f6 + 0.0f;
        int i7 = i6 + 1;
        fArr[i6] = f2 + 0.0f;
        int i8 = i7 + 1;
        fArr[i7] = f6 + 0.0f;
        int i9 = i8 + 1;
        fArr[i8] = f7 + 0.0f;
        fArr[i9] = f + 0.0f;
        fArr[i9 + 1] = f7 + 0.0f;
    }

    public boolean b(String str) {
        return this.k.containsKey(str);
    }

    public final boolean a(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    public void a(o5 o5Var, boolean[] zArr, String[] strArr, boolean z) {
        zArr[0] = zArr[0] | a(this.d, o5Var.d);
        zArr[1] = zArr[1] | a(this.f8628e, o5Var.f8628e) | z;
        zArr[2] = z | a(this.f, o5Var.f) | zArr[2];
        zArr[3] = zArr[3] | a(this.g, o5Var.g);
        zArr[4] = a(this.h, o5Var.h) | zArr[4];
    }

    public void a(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.f8628e;
        float f2 = this.f;
        float f3 = this.g;
        float f4 = this.h;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f5 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f = f5;
            } else if (i3 == 2) {
                f2 = f5;
            } else if (i3 == 3) {
                f3 = f5;
            } else if (i3 == 4) {
                f4 = f5;
            }
        }
        fArr[i] = f + (f3 / 2.0f) + 0.0f;
        fArr[i + 1] = f2 + (f4 / 2.0f) + 0.0f;
    }

    public void a(View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3) {
        float f;
        float f2 = this.f8628e;
        float f3 = this.f;
        float f4 = this.g;
        float f5 = this.h;
        if (iArr.length != 0 && this.m.length <= iArr[iArr.length - 1]) {
            int i = iArr[iArr.length - 1] + 1;
            this.m = new double[i];
            this.n = new double[i];
        }
        Arrays.fill(this.m, Double.NaN);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.m[iArr[i2]] = dArr[i2];
            this.n[iArr[i2]] = dArr2[i2];
        }
        int i3 = 0;
        float f6 = Float.NaN;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        while (true) {
            double[] dArr4 = this.m;
            if (i3 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i3]) && (dArr3 == null || dArr3[i3] == 0.0d)) {
                f = f2;
            } else {
                double d = dArr3 != null ? dArr3[i3] : 0.0d;
                if (!Double.isNaN(this.m[i3])) {
                    d = this.m[i3] + d;
                }
                f = f2;
                float f11 = (float) d;
                float f12 = (float) this.n[i3];
                if (i3 == 1) {
                    f7 = f12;
                    f2 = f11;
                } else if (i3 == 2) {
                    f3 = f11;
                    f9 = f12;
                } else if (i3 == 3) {
                    f4 = f11;
                    f8 = f12;
                } else if (i3 == 4) {
                    f5 = f11;
                    f10 = f12;
                } else if (i3 == 5) {
                    f2 = f;
                    f6 = f11;
                }
                i3++;
            }
            f2 = f;
            i3++;
        }
        float f13 = f2;
        if (Float.isNaN(f6)) {
            if (!Float.isNaN(Float.NaN)) {
                view.setRotation(Float.NaN);
            }
        } else {
            view.setRotation((float) (((double) (Float.isNaN(Float.NaN) ? 0.0f : Float.NaN)) + ((double) f6) + Math.toDegrees(Math.atan2(f9 + (f10 / 2.0f), f7 + (f8 / 2.0f)))));
        }
        float f14 = f13 + 0.5f;
        int i4 = (int) f14;
        float f15 = f3 + 0.5f;
        int i5 = (int) f15;
        int i6 = (int) (f14 + f4);
        int i7 = (int) (f15 + f5);
        int i8 = i6 - i4;
        int i9 = i7 - i5;
        if ((i8 == view.getMeasuredWidth() && i9 == view.getMeasuredHeight()) ? false : true) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i8, 1073741824), View.MeasureSpec.makeMeasureSpec(i9, 1073741824));
        }
        view.layout(i4, i5, i6, i7);
    }

    public void a(float f, float f2, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float f7 = (float) dArr[i];
            double d = dArr2[i];
            int i2 = iArr[i];
            if (i2 == 1) {
                f3 = f7;
            } else if (i2 == 2) {
                f5 = f7;
            } else if (i2 == 3) {
                f4 = f7;
            } else if (i2 == 4) {
                f6 = f7;
            }
        }
        float f8 = f3 - ((0.0f * f4) / 2.0f);
        float f9 = f5 - ((0.0f * f6) / 2.0f);
        fArr[0] = (f8 * (1.0f - f)) + (((f4 * 1.0f) + f8) * f) + 0.0f;
        fArr[1] = (f9 * (1.0f - f2)) + (((f6 * 1.0f) + f9) * f2) + 0.0f;
    }

    public void a(double[] dArr, int[] iArr) {
        float[] fArr = {this.d, this.f8628e, this.f, this.g, this.h, this.i};
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] < 6) {
                dArr[i] = fArr[iArr[i2]];
                i++;
            }
        }
    }

    public int a(String str) {
        return this.k.get(str).c();
    }

    public int a(String str, double[] dArr, int i) {
        ConstraintAttribute constraintAttribute = this.k.get(str);
        if (constraintAttribute.c() == 1) {
            dArr[i] = constraintAttribute.b();
            return 1;
        }
        int iC = constraintAttribute.c();
        constraintAttribute.a(new float[iC]);
        int i2 = 0;
        while (i2 < iC) {
            dArr[i] = r1[i2];
            i2++;
            i++;
        }
        return iC;
    }

    public void a(float f, float f2, float f3, float f4) {
        this.f8628e = f;
        this.f = f2;
        this.g = f3;
        this.h = f4;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(o5 o5Var) {
        return Float.compare(this.d, o5Var.d);
    }

    public void a(e7.a aVar) {
        this.f8627a = t4.a(aVar.c.c);
        e7.c cVar = aVar.c;
        this.j = cVar.d;
        this.i = cVar.g;
        this.b = cVar.f7437e;
        float f = aVar.b.f7439e;
        for (String str : aVar.f.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.f.get(str);
            if (constraintAttribute.a() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.k.put(str, constraintAttribute);
            }
        }
    }
}
