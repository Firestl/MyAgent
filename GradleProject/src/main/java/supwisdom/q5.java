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
import java.util.Arrays;

/* JADX INFO: compiled from: SplineSet.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class q5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public s4 f8878a;
    public int[] b = new int[10];
    public float[] c = new float[10];
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f8879e;

    /* JADX INFO: compiled from: SplineSet.java */
    public static class a extends q5 {
        @Override // supwisdom.q5
        public void a(View view, float f) {
            view.setAlpha(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class c extends q5 {
        @Override // supwisdom.q5
        public void a(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f));
            }
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class d extends q5 {
        @Override // supwisdom.q5
        public void a(View view, float f) {
        }

        public void a(View view, float f, double d, double d2) {
            view.setRotation(a(f) + ((float) Math.toDegrees(Math.atan2(d2, d))));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class e extends q5 {
        @Override // supwisdom.q5
        public void a(View view, float f) {
            view.setPivotX(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class f extends q5 {
        @Override // supwisdom.q5
        public void a(View view, float f) {
            view.setPivotY(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class g extends q5 {
        public boolean f = false;

        @Override // supwisdom.q5
        public void a(View view, float f) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f));
                return;
            }
            if (this.f) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f)));
                } catch (IllegalAccessException e2) {
                    Log.e("SplineSet", "unable to setProgress", e2);
                } catch (InvocationTargetException e3) {
                    Log.e("SplineSet", "unable to setProgress", e3);
                }
            }
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class h extends q5 {
        @Override // supwisdom.q5
        public void a(View view, float f) {
            view.setRotation(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class i extends q5 {
        @Override // supwisdom.q5
        public void a(View view, float f) {
            view.setRotationX(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class j extends q5 {
        @Override // supwisdom.q5
        public void a(View view, float f) {
            view.setRotationY(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class k extends q5 {
        @Override // supwisdom.q5
        public void a(View view, float f) {
            view.setScaleX(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class l extends q5 {
        @Override // supwisdom.q5
        public void a(View view, float f) {
            view.setScaleY(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class m {
        public static void a(int[] iArr, float[] fArr, int i, int i2) {
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

        public static int b(int[] iArr, float[] fArr, int i, int i2) {
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

        public static void c(int[] iArr, float[] fArr, int i, int i2) {
            int i3 = iArr[i];
            iArr[i] = iArr[i2];
            iArr[i2] = i3;
            float f = fArr[i];
            fArr[i] = fArr[i2];
            fArr[i2] = f;
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class n extends q5 {
        @Override // supwisdom.q5
        public void a(View view, float f) {
            view.setTranslationX(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class o extends q5 {
        @Override // supwisdom.q5
        public void a(View view, float f) {
            view.setTranslationY(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class p extends q5 {
        @Override // supwisdom.q5
        public void a(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f));
            }
        }
    }

    public abstract void a(View view, float f2);

    public void a(String str) {
        this.f8879e = str;
    }

    public float b(float f2) {
        return (float) this.f8878a.b(f2, 0);
    }

    public String toString() {
        String str = this.f8879e;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i2 = 0; i2 < this.d; i2++) {
            str = str + Operators.ARRAY_START_STR + this.b[i2] + " , " + decimalFormat.format(this.c[i2]) + "] ";
        }
        return str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static supwisdom.q5 b(java.lang.String r1) {
        /*
            Method dump skipped, instruction units count: 388
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.q5.b(java.lang.String):supwisdom.q5");
    }

    public float a(float f2) {
        return (float) this.f8878a.a(f2, 0);
    }

    public static q5 a(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new b(str, sparseArray);
    }

    public void a(int i2, float f2) {
        int[] iArr = this.b;
        if (iArr.length < this.d + 1) {
            this.b = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.c;
            this.c = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.b;
        int i3 = this.d;
        iArr2[i3] = i2;
        this.c[i3] = f2;
        this.d = i3 + 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(int r10) {
        /*
            r9 = this;
            int r0 = r9.d
            if (r0 != 0) goto L5
            return
        L5:
            int[] r1 = r9.b
            float[] r2 = r9.c
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            supwisdom.q5.m.a(r1, r2, r4, r0)
            r0 = 1
            r1 = 1
        L11:
            int r2 = r9.d
            if (r0 >= r2) goto L24
            int[] r2 = r9.b
            int r5 = r0 + (-1)
            r5 = r2[r5]
            r2 = r2[r0]
            if (r5 == r2) goto L21
            int r1 = r1 + 1
        L21:
            int r0 = r0 + 1
            goto L11
        L24:
            double[] r0 = new double[r1]
            r2 = 2
            int[] r2 = new int[r2]
            r2[r3] = r3
            r2[r4] = r1
            java.lang.Class<double> r1 = double.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r2)
            double[][] r1 = (double[][]) r1
            r2 = 0
            r3 = 0
        L37:
            int r5 = r9.d
            if (r2 >= r5) goto L64
            if (r2 <= 0) goto L48
            int[] r5 = r9.b
            r6 = r5[r2]
            int r7 = r2 + (-1)
            r5 = r5[r7]
            if (r6 != r5) goto L48
            goto L61
        L48:
            int[] r5 = r9.b
            r5 = r5[r2]
            double r5 = (double) r5
            r7 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r5 = r5 * r7
            r0[r3] = r5
            r5 = r1[r3]
            float[] r6 = r9.c
            r6 = r6[r2]
            double r6 = (double) r6
            r5[r4] = r6
            int r3 = r3 + 1
        L61:
            int r2 = r2 + 1
            goto L37
        L64:
            supwisdom.s4 r10 = supwisdom.s4.a(r10, r0, r1)
            r9.f8878a = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.q5.a(int):void");
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class b extends q5 {
        public SparseArray<ConstraintAttribute> f;
        public float[] g;

        public b(String str, SparseArray<ConstraintAttribute> sparseArray) {
            String str2 = str.split(",")[1];
            this.f = sparseArray;
        }

        @Override // supwisdom.q5
        public void a(int i) {
            int size = this.f.size();
            int iC = this.f.valueAt(0).c();
            double[] dArr = new double[size];
            this.g = new float[iC];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, iC);
            for (int i2 = 0; i2 < size; i2++) {
                int iKeyAt = this.f.keyAt(i2);
                ConstraintAttribute constraintAttributeValueAt = this.f.valueAt(i2);
                dArr[i2] = ((double) iKeyAt) * 0.01d;
                constraintAttributeValueAt.a(this.g);
                int i3 = 0;
                while (true) {
                    if (i3 < this.g.length) {
                        dArr2[i2][i3] = r6[i3];
                        i3++;
                    }
                }
            }
            this.f8878a = s4.a(i, dArr, dArr2);
        }

        @Override // supwisdom.q5
        public void a(int i, float f) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        public void a(int i, ConstraintAttribute constraintAttribute) {
            this.f.append(i, constraintAttribute);
        }

        @Override // supwisdom.q5
        public void a(View view, float f) {
            this.f8878a.a(f, this.g);
            this.f.valueAt(0).a(view, this.g);
        }
    }
}
