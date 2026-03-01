package supwisdom;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyCycleOscillator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f7566a;
    public ConstraintAttribute b;
    public String c;
    public int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7567e = 0;
    public ArrayList<p> f = new ArrayList<>();

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public class a implements Comparator<p> {
        public a(f5 f5Var) {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(p pVar, p pVar2) {
            return Integer.compare(pVar.f7570a, pVar2.f7570a);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class b extends f5 {
        @Override // supwisdom.f5
        public void a(View view, float f) {
            view.setAlpha(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class c extends f5 {
        public float[] g = new float[1];

        @Override // supwisdom.f5
        public void a(View view, float f) {
            this.g[0] = a(f);
            this.b.a(view, this.g);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class e extends f5 {
        @Override // supwisdom.f5
        public void a(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class f extends f5 {
        @Override // supwisdom.f5
        public void a(View view, float f) {
        }

        public void a(View view, float f, double d, double d2) {
            view.setRotation(a(f) + ((float) Math.toDegrees(Math.atan2(d2, d))));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class g extends f5 {
        public boolean g = false;

        @Override // supwisdom.f5
        public void a(View view, float f) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f));
                return;
            }
            if (this.g) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.g = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f)));
                } catch (IllegalAccessException e2) {
                    Log.e("KeyCycleOscillator", "unable to setProgress", e2);
                } catch (InvocationTargetException e3) {
                    Log.e("KeyCycleOscillator", "unable to setProgress", e3);
                }
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class h extends f5 {
        @Override // supwisdom.f5
        public void a(View view, float f) {
            view.setRotation(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class i extends f5 {
        @Override // supwisdom.f5
        public void a(View view, float f) {
            view.setRotationX(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class j extends f5 {
        @Override // supwisdom.f5
        public void a(View view, float f) {
            view.setRotationY(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class k extends f5 {
        @Override // supwisdom.f5
        public void a(View view, float f) {
            view.setScaleX(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class l extends f5 {
        @Override // supwisdom.f5
        public void a(View view, float f) {
            view.setScaleY(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class m extends f5 {
        @Override // supwisdom.f5
        public void a(View view, float f) {
            view.setTranslationX(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class n extends f5 {
        @Override // supwisdom.f5
        public void a(View view, float f) {
            view.setTranslationY(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class o extends f5 {
        @Override // supwisdom.f5
        public void a(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f7570a;
        public float b;
        public float c;
        public float d;

        public p(int i, float f, float f2, float f3) {
            this.f7570a = i;
            this.b = f3;
            this.c = f2;
            this.d = f;
        }
    }

    public abstract void a(View view, float f2);

    public boolean a() {
        return this.f7567e == 1;
    }

    public float b(float f2) {
        return (float) this.f7566a.a(f2);
    }

    @TargetApi(19)
    public void c(float f2) {
        int size = this.f.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.f, new a(this));
        double[] dArr = new double[size];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
        this.f7566a = new d(this.d, this.f7567e, size);
        int i2 = 0;
        for (p pVar : this.f) {
            float f3 = pVar.d;
            dArr[i2] = ((double) f3) * 0.01d;
            double[] dArr3 = dArr2[i2];
            float f4 = pVar.b;
            dArr3[0] = f4;
            double[] dArr4 = dArr2[i2];
            float f5 = pVar.c;
            dArr4[1] = f5;
            this.f7566a.a(i2, pVar.f7570a, f3, f5, f4);
            i2++;
        }
        this.f7566a.c(f2);
        s4.a(0, dArr, dArr2);
    }

    public String toString() {
        String str = this.c;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator<p> it = this.f.iterator();
        while (it.hasNext()) {
            str = str + Operators.ARRAY_START_STR + it.next().f7570a + " , " + decimalFormat.format(r3.b) + "] ";
        }
        return str;
    }

    public static f5 b(String str) {
        if (str.startsWith("CUSTOM")) {
            return new c();
        }
        switch (str) {
            case "alpha":
                return new b();
            case "elevation":
                return new e();
            case "rotation":
                return new h();
            case "rotationX":
                return new i();
            case "rotationY":
                return new j();
            case "transitionPathRotate":
                return new f();
            case "scaleX":
                return new k();
            case "scaleY":
                return new l();
            case "waveOffset":
                return new b();
            case "waveVariesBy":
                return new b();
            case "translationX":
                return new m();
            case "translationY":
                return new n();
            case "translationZ":
                return new o();
            case "progress":
                return new g();
            default:
                return null;
        }
    }

    public void a(String str) {
        this.c = str;
    }

    public float a(float f2) {
        return (float) this.f7566a.b(f2);
    }

    public void a(int i2, int i3, int i4, float f2, float f3, float f4, ConstraintAttribute constraintAttribute) {
        this.f.add(new p(i2, f2, f3, f4));
        if (i4 != -1) {
            this.f7567e = i4;
        }
        this.d = i3;
        this.b = constraintAttribute;
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public w4 f7568a = new w4();
        public float[] b;
        public double[] c;
        public float[] d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float[] f7569e;
        public s4 f;
        public double[] g;
        public double[] h;

        public d(int i, int i2, int i3) {
            new HashMap();
            this.f7568a.a(i);
            this.b = new float[i3];
            this.c = new double[i3];
            this.d = new float[i3];
            this.f7569e = new float[i3];
            float[] fArr = new float[i3];
        }

        public double a(float f) {
            s4 s4Var = this.f;
            if (s4Var != null) {
                double d = f;
                s4Var.b(d, this.h);
                this.f.a(d, this.g);
            } else {
                double[] dArr = this.h;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
            }
            double d2 = f;
            double d3 = this.f7568a.d(d2);
            double dC = this.f7568a.c(d2);
            double[] dArr2 = this.h;
            return dArr2[0] + (d3 * dArr2[1]) + (dC * this.g[1]);
        }

        public double b(float f) {
            s4 s4Var = this.f;
            if (s4Var != null) {
                s4Var.a(f, this.g);
            } else {
                double[] dArr = this.g;
                dArr[0] = this.f7569e[0];
                dArr[1] = this.b[0];
            }
            return this.g[0] + (this.f7568a.d(f) * this.g[1]);
        }

        public void c(float f) {
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, this.c.length, 2);
            float[] fArr = this.b;
            this.g = new double[fArr.length + 1];
            this.h = new double[fArr.length + 1];
            if (this.c[0] > 0.0d) {
                this.f7568a.a(0.0d, this.d[0]);
            }
            double[] dArr2 = this.c;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.f7568a.a(1.0d, this.d[length]);
            }
            for (int i = 0; i < dArr.length; i++) {
                dArr[i][0] = this.f7569e[i];
                int i2 = 0;
                while (true) {
                    if (i2 < this.b.length) {
                        dArr[i2][1] = r4[i2];
                        i2++;
                    }
                }
                this.f7568a.a(this.c[i], this.d[i]);
            }
            this.f7568a.a();
            double[] dArr3 = this.c;
            if (dArr3.length > 1) {
                this.f = s4.a(0, dArr3, dArr);
            } else {
                this.f = null;
            }
        }

        public void a(int i, int i2, float f, float f2, float f3) {
            this.c[i] = ((double) i2) / 100.0d;
            this.d[i] = f;
            this.f7569e[i] = f2;
            this.b[i] = f3;
        }
    }

    public void a(int i2, int i3, int i4, float f2, float f3, float f4) {
        this.f.add(new p(i2, f2, f3, f4));
        if (i4 != -1) {
            this.f7567e = i4;
        }
        this.d = i3;
    }
}
