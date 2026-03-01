package supwisdom;

import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

/* JADX INFO: compiled from: TimeCycleSplineSet.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class r5 {
    public static float k = 6.2831855f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public s4 f9010a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9011e;
    public String f;
    public long i;
    public int b = 0;
    public int[] c = new int[10];
    public float[][] d = (float[][]) Array.newInstance((Class<?>) float.class, 10, 3);
    public float[] g = new float[3];
    public boolean h = false;
    public float j = Float.NaN;

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class a extends r5 {
        @Override // supwisdom.r5
        public boolean a(View view, float f, long j, d5 d5Var) {
            view.setAlpha(a(f, j, view, d5Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class c extends r5 {
        @Override // supwisdom.r5
        public boolean a(View view, float f, long j, d5 d5Var) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f, j, view, d5Var));
            }
            return this.h;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class d extends r5 {
        @Override // supwisdom.r5
        public boolean a(View view, float f, long j, d5 d5Var) {
            return this.h;
        }

        public boolean a(View view, d5 d5Var, float f, long j, double d, double d2) {
            view.setRotation(a(f, j, view, d5Var) + ((float) Math.toDegrees(Math.atan2(d2, d))));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class e extends r5 {
        public boolean l = false;

        @Override // supwisdom.r5
        public boolean a(View view, float f, long j, d5 d5Var) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f, j, view, d5Var));
            } else {
                if (this.l) {
                    return false;
                }
                Method method = null;
                try {
                    method = view.getClass().getMethod("setProgress", Float.TYPE);
                } catch (NoSuchMethodException unused) {
                    this.l = true;
                }
                Method method2 = method;
                if (method2 != null) {
                    try {
                        method2.invoke(view, Float.valueOf(a(f, j, view, d5Var)));
                    } catch (IllegalAccessException e2) {
                        Log.e("SplineSet", "unable to setProgress", e2);
                    } catch (InvocationTargetException e3) {
                        Log.e("SplineSet", "unable to setProgress", e3);
                    }
                }
            }
            return this.h;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class f extends r5 {
        @Override // supwisdom.r5
        public boolean a(View view, float f, long j, d5 d5Var) {
            view.setRotation(a(f, j, view, d5Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class g extends r5 {
        @Override // supwisdom.r5
        public boolean a(View view, float f, long j, d5 d5Var) {
            view.setRotationX(a(f, j, view, d5Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class h extends r5 {
        @Override // supwisdom.r5
        public boolean a(View view, float f, long j, d5 d5Var) {
            view.setRotationY(a(f, j, view, d5Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class i extends r5 {
        @Override // supwisdom.r5
        public boolean a(View view, float f, long j, d5 d5Var) {
            view.setScaleX(a(f, j, view, d5Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class j extends r5 {
        @Override // supwisdom.r5
        public boolean a(View view, float f, long j, d5 d5Var) {
            view.setScaleY(a(f, j, view, d5Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class k {
        public static void a(int[] iArr, float[][] fArr, int i, int i2) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i2;
            iArr2[1] = i;
            int i3 = 2;
            while (i3 > 0) {
                int i4 = i3 - 1;
                int i5 = iArr2[i4];
                i3 = i4 - 1;
                int i6 = iArr2[i3];
                if (i5 < i6) {
                    int iB = b(iArr, fArr, i5, i6);
                    int i7 = i3 + 1;
                    iArr2[i3] = iB - 1;
                    int i8 = i7 + 1;
                    iArr2[i7] = i5;
                    int i9 = i8 + 1;
                    iArr2[i8] = i6;
                    i3 = i9 + 1;
                    iArr2[i9] = iB + 1;
                }
            }
        }

        public static int b(int[] iArr, float[][] fArr, int i, int i2) {
            int i3 = iArr[i2];
            int i4 = i;
            while (i < i2) {
                if (iArr[i] <= i3) {
                    c(iArr, fArr, i4, i);
                    i4++;
                }
                i++;
            }
            c(iArr, fArr, i4, i2);
            return i4;
        }

        public static void c(int[] iArr, float[][] fArr, int i, int i2) {
            int i3 = iArr[i];
            iArr[i] = iArr[i2];
            iArr[i2] = i3;
            float[] fArr2 = fArr[i];
            fArr[i] = fArr[i2];
            fArr[i2] = fArr2;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class l extends r5 {
        @Override // supwisdom.r5
        public boolean a(View view, float f, long j, d5 d5Var) {
            view.setTranslationX(a(f, j, view, d5Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class m extends r5 {
        @Override // supwisdom.r5
        public boolean a(View view, float f, long j, d5 d5Var) {
            view.setTranslationY(a(f, j, view, d5Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class n extends r5 {
        @Override // supwisdom.r5
        public boolean a(View view, float f, long j, d5 d5Var) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f, j, view, d5Var));
            }
            return this.h;
        }
    }

    public void a(String str) {
        this.f = str;
    }

    public abstract boolean a(View view, float f2, long j2, d5 d5Var);

    public String toString() {
        String str = this.f;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i2 = 0; i2 < this.f9011e; i2++) {
            str = str + Operators.ARRAY_START_STR + this.c[i2] + " , " + decimalFormat.format(this.d[i2]) + "] ";
        }
        return str;
    }

    public float a(float f2, long j2, View view, d5 d5Var) {
        this.f9010a.a(f2, this.g);
        float[] fArr = this.g;
        float f3 = fArr[1];
        if (f3 == 0.0f) {
            this.h = false;
            return fArr[2];
        }
        if (Float.isNaN(this.j)) {
            float fA = d5Var.a(view, this.f, 0);
            this.j = fA;
            if (Float.isNaN(fA)) {
                this.j = 0.0f;
            }
        }
        float f4 = (float) ((((double) this.j) + (((j2 - this.i) * 1.0E-9d) * ((double) f3))) % 1.0d);
        this.j = f4;
        d5Var.a(view, this.f, 0, f4);
        this.i = j2;
        float f5 = this.g[0];
        float fA2 = (a(this.j) * f5) + this.g[2];
        this.h = (f5 == 0.0f && f3 == 0.0f) ? false : true;
        return fA2;
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class b extends r5 {
        public String l;
        public SparseArray<ConstraintAttribute> m;
        public SparseArray<float[]> n = new SparseArray<>();
        public float[] o;
        public float[] p;

        public b(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.l = str.split(",")[1];
            this.m = sparseArray;
        }

        @Override // supwisdom.r5
        public void a(int i) {
            int size = this.m.size();
            int iC = this.m.valueAt(0).c();
            double[] dArr = new double[size];
            int i2 = iC + 2;
            this.o = new float[i2];
            this.p = new float[iC];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, i2);
            for (int i3 = 0; i3 < size; i3++) {
                int iKeyAt = this.m.keyAt(i3);
                ConstraintAttribute constraintAttributeValueAt = this.m.valueAt(i3);
                float[] fArrValueAt = this.n.valueAt(i3);
                dArr[i3] = ((double) iKeyAt) * 0.01d;
                constraintAttributeValueAt.a(this.o);
                int i4 = 0;
                while (true) {
                    if (i4 < this.o.length) {
                        dArr2[i3][i4] = r8[i4];
                        i4++;
                    }
                }
                dArr2[i3][iC] = fArrValueAt[0];
                dArr2[i3][iC + 1] = fArrValueAt[1];
            }
            this.f9010a = s4.a(i, dArr, dArr2);
        }

        @Override // supwisdom.r5
        public void a(int i, float f, float f2, int i2, float f3) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute,...)");
        }

        public void a(int i, ConstraintAttribute constraintAttribute, float f, int i2, float f2) {
            this.m.append(i, constraintAttribute);
            this.n.append(i, new float[]{f, f2});
            this.b = Math.max(this.b, i2);
        }

        @Override // supwisdom.r5
        public boolean a(View view, float f, long j, d5 d5Var) {
            this.f9010a.a(f, this.o);
            float[] fArr = this.o;
            float f2 = fArr[fArr.length - 2];
            float f3 = fArr[fArr.length - 1];
            long j2 = j - this.i;
            if (Float.isNaN(this.j)) {
                float fA = d5Var.a(view, this.l, 0);
                this.j = fA;
                if (Float.isNaN(fA)) {
                    this.j = 0.0f;
                }
            }
            float f4 = (float) ((((double) this.j) + ((j2 * 1.0E-9d) * ((double) f2))) % 1.0d);
            this.j = f4;
            this.i = j;
            float fA2 = a(f4);
            this.h = false;
            for (int i = 0; i < this.p.length; i++) {
                this.h |= ((double) this.o[i]) != 0.0d;
                this.p[i] = (this.o[i] * fA2) + f3;
            }
            this.m.valueAt(0).a(view, this.p);
            if (f2 != 0.0f) {
                this.h = true;
            }
            return this.h;
        }
    }

    public float a(float f2) {
        float fAbs;
        switch (this.b) {
            case 1:
                return Math.signum(f2 * k);
            case 2:
                fAbs = Math.abs(f2);
                break;
            case 3:
                return (((f2 * 2.0f) + 1.0f) % 2.0f) - 1.0f;
            case 4:
                fAbs = ((f2 * 2.0f) + 1.0f) % 2.0f;
                break;
            case 5:
                return (float) Math.cos(f2 * k);
            case 6:
                float fAbs2 = 1.0f - Math.abs(((f2 * 4.0f) % 4.0f) - 2.0f);
                fAbs = fAbs2 * fAbs2;
                break;
            default:
                return (float) Math.sin(f2 * k);
        }
        return 1.0f - fAbs;
    }

    public static r5 a(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new b(str, sparseArray);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static supwisdom.r5 a(java.lang.String r1, long r2) {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.r5.a(java.lang.String, long):supwisdom.r5");
    }

    public void a(long j2) {
        this.i = j2;
    }

    public void a(int i2, float f2, float f3, int i3, float f4) {
        int[] iArr = this.c;
        int i4 = this.f9011e;
        iArr[i4] = i2;
        float[][] fArr = this.d;
        fArr[i4][0] = f2;
        fArr[i4][1] = f3;
        fArr[i4][2] = f4;
        this.b = Math.max(this.b, i3);
        this.f9011e++;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(int r12) {
        /*
            r11 = this;
            int r0 = r11.f9011e
            if (r0 != 0) goto L1d
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "Error no points added to "
            r12.append(r0)
            java.lang.String r0 = r11.f
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            java.lang.String r0 = "SplineSet"
            android.util.Log.e(r0, r12)
            return
        L1d:
            int[] r1 = r11.c
            float[][] r2 = r11.d
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            supwisdom.r5.k.a(r1, r2, r4, r0)
            r0 = 1
            r1 = 0
        L29:
            int[] r2 = r11.c
            int r5 = r2.length
            if (r0 >= r5) goto L3b
            r5 = r2[r0]
            int r6 = r0 + (-1)
            r2 = r2[r6]
            if (r5 == r2) goto L38
            int r1 = r1 + 1
        L38:
            int r0 = r0 + 1
            goto L29
        L3b:
            if (r1 != 0) goto L3e
            r1 = 1
        L3e:
            double[] r0 = new double[r1]
            r2 = 3
            r5 = 2
            int[] r6 = new int[r5]
            r6[r3] = r2
            r6[r4] = r1
            java.lang.Class<double> r1 = double.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r6)
            double[][] r1 = (double[][]) r1
            r2 = 0
            r6 = 0
        L52:
            int r7 = r11.f9011e
            if (r2 >= r7) goto L93
            if (r2 <= 0) goto L63
            int[] r7 = r11.c
            r8 = r7[r2]
            int r9 = r2 + (-1)
            r7 = r7[r9]
            if (r8 != r7) goto L63
            goto L90
        L63:
            int[] r7 = r11.c
            r7 = r7[r2]
            double r7 = (double) r7
            r9 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r7 = r7 * r9
            r0[r6] = r7
            r7 = r1[r6]
            float[][] r8 = r11.d
            r9 = r8[r2]
            r9 = r9[r4]
            double r9 = (double) r9
            r7[r4] = r9
            r7 = r1[r6]
            r9 = r8[r2]
            r9 = r9[r3]
            double r9 = (double) r9
            r7[r3] = r9
            r7 = r1[r6]
            r8 = r8[r2]
            r8 = r8[r5]
            double r8 = (double) r8
            r7[r5] = r8
            int r6 = r6 + 1
        L90:
            int r2 = r2 + 1
            goto L52
        L93:
            supwisdom.s4 r12 = supwisdom.s4.a(r12, r0, r1)
            r11.f9010a = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.r5.a(int):void");
    }
}
