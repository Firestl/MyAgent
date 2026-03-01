package supwisdom;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import supwisdom.e7;
import supwisdom.f5;
import supwisdom.q5;
import supwisdom.r5;

/* JADX INFO: compiled from: MotionController.java */
/* JADX INFO: loaded from: classes.dex */
public class m5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f8359a;
    public int b;
    public s4[] h;
    public s4 i;
    public int[] m;
    public double[] n;
    public double[] o;
    public String[] p;
    public int[] q;
    public HashMap<String, r5> w;
    public HashMap<String, q5> x;
    public HashMap<String, f5> y;
    public k5[] z;
    public int c = -1;
    public o5 d = new o5();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public o5 f8360e = new o5();
    public l5 f = new l5();
    public l5 g = new l5();
    public float j = Float.NaN;
    public float k = 0.0f;
    public float l = 1.0f;
    public int r = 4;
    public float[] s = new float[4];
    public ArrayList<o5> t = new ArrayList<>();
    public float[] u = new float[1];
    public ArrayList<b5> v = new ArrayList<>();
    public int A = b5.f7022e;

    public m5(View view) {
        b(view);
    }

    public o5 a(int i) {
        return this.t.get(i);
    }

    public float b() {
        return this.f8360e.f8628e;
    }

    public float c() {
        return this.f8360e.f;
    }

    public final float d() {
        float[] fArr = new float[2];
        float f = 1.0f / 99;
        double d = 0.0d;
        double d2 = 0.0d;
        int i = 0;
        float fHypot = 0.0f;
        while (i < 100) {
            float f2 = i * f;
            double dA = f2;
            t4 t4Var = this.d.f8627a;
            float f3 = Float.NaN;
            float f4 = 0.0f;
            for (o5 o5Var : this.t) {
                t4 t4Var2 = o5Var.f8627a;
                float f5 = f;
                if (t4Var2 != null) {
                    float f6 = o5Var.c;
                    if (f6 < f2) {
                        f4 = f6;
                        t4Var = t4Var2;
                    } else if (Float.isNaN(f3)) {
                        f3 = o5Var.c;
                    }
                }
                f = f5;
            }
            float f7 = f;
            if (t4Var != null) {
                if (Float.isNaN(f3)) {
                    f3 = 1.0f;
                }
                dA = (((float) t4Var.a((f2 - f4) / r16)) * (f3 - f4)) + f4;
            }
            this.h[0].a(dA, this.n);
            this.d.a(this.m, this.n, fArr, 0);
            if (i > 0) {
                fHypot = (float) (((double) fHypot) + Math.hypot(d2 - ((double) fArr[1]), d - ((double) fArr[0])));
            }
            d = fArr[0];
            d2 = fArr[1];
            i++;
            f = f7;
        }
        return fHypot;
    }

    public String toString() {
        return " start: x: " + this.d.f8628e + " y: " + this.d.f + " end: x: " + this.f8360e.f8628e + " y: " + this.f8360e.f;
    }

    public void a(float[] fArr, int i) {
        int i2 = i;
        float f = 1.0f;
        float f2 = 1.0f / (i2 - 1);
        HashMap<String, q5> map = this.x;
        q5 q5Var = map == null ? null : map.get("translationX");
        HashMap<String, q5> map2 = this.x;
        q5 q5Var2 = map2 == null ? null : map2.get("translationY");
        HashMap<String, f5> map3 = this.y;
        f5 f5Var = map3 == null ? null : map3.get("translationX");
        HashMap<String, f5> map4 = this.y;
        f5 f5Var2 = map4 != null ? map4.get("translationY") : null;
        int i3 = 0;
        while (i3 < i2) {
            float f3 = i3 * f2;
            float f4 = 0.0f;
            if (this.l != f) {
                if (f3 < this.k) {
                    f3 = 0.0f;
                }
                float f5 = this.k;
                if (f3 > f5 && f3 < 1.0d) {
                    f3 = (f3 - f5) * this.l;
                }
            }
            double dA = f3;
            t4 t4Var = this.d.f8627a;
            float f6 = Float.NaN;
            for (o5 o5Var : this.t) {
                t4 t4Var2 = o5Var.f8627a;
                if (t4Var2 != null) {
                    float f7 = o5Var.c;
                    if (f7 < f3) {
                        f4 = f7;
                        t4Var = t4Var2;
                    } else if (Float.isNaN(f6)) {
                        f6 = o5Var.c;
                    }
                }
            }
            if (t4Var != null) {
                if (Float.isNaN(f6)) {
                    f6 = 1.0f;
                }
                dA = (((float) t4Var.a((f3 - f4) / r16)) * (f6 - f4)) + f4;
            }
            this.h[0].a(dA, this.n);
            s4 s4Var = this.i;
            if (s4Var != null) {
                double[] dArr = this.n;
                if (dArr.length > 0) {
                    s4Var.a(dA, dArr);
                }
            }
            int i4 = i3 * 2;
            this.d.a(this.m, this.n, fArr, i4);
            if (f5Var != null) {
                fArr[i4] = fArr[i4] + f5Var.a(f3);
            } else if (q5Var != null) {
                fArr[i4] = fArr[i4] + q5Var.a(f3);
            }
            if (f5Var2 != null) {
                int i5 = i4 + 1;
                fArr[i5] = fArr[i5] + f5Var2.a(f3);
            } else if (q5Var2 != null) {
                int i6 = i4 + 1;
                fArr[i6] = fArr[i6] + q5Var2.a(f3);
            }
            i3++;
            i2 = i;
            f = 1.0f;
        }
    }

    public void b(int i) {
        this.A = i;
    }

    public final void b(o5 o5Var) {
        o5Var.a((int) this.f8359a.getX(), (int) this.f8359a.getY(), this.f8359a.getWidth(), this.f8359a.getHeight());
    }

    public void b(View view) {
        this.f8359a = view;
        this.b = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).a();
        }
    }

    public void b(ConstraintWidget constraintWidget, e7 e7Var) {
        o5 o5Var = this.d;
        o5Var.c = 0.0f;
        o5Var.d = 0.0f;
        b(o5Var);
        this.d.a(constraintWidget.E(), constraintWidget.F(), constraintWidget.D(), constraintWidget.l());
        e7.a aVarD = e7Var.d(this.b);
        this.d.a(aVarD);
        this.j = aVarD.c.f;
        this.f.a(constraintWidget, e7Var, this.b);
    }

    public int a(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] dArrA = this.h[0].a();
        if (iArr != null) {
            Iterator<o5> it = this.t.iterator();
            int i = 0;
            while (it.hasNext()) {
                iArr[i] = it.next().l;
                i++;
            }
        }
        int i2 = 0;
        for (double d : dArrA) {
            this.h[0].a(d, this.n);
            this.d.a(this.m, this.n, fArr, i2);
            i2 += 2;
        }
        return i2 / 2;
    }

    public void a(float f, float[] fArr, int i) {
        this.h[0].a(a(f, (float[]) null), this.n);
        this.d.b(this.m, this.n, fArr, i);
    }

    public final void a(o5 o5Var) {
        if (Collections.binarySearch(this.t, o5Var) == 0) {
            Log.e("MotionController", " KeyPath positon \"" + o5Var.d + "\" outside of range");
        }
        this.t.add((-r0) - 1, o5Var);
    }

    public void a(ArrayList<b5> arrayList) {
        this.v.addAll(arrayList);
    }

    public void a(b5 b5Var) {
        this.v.add(b5Var);
    }

    public void a(int i, int i2, float f, long j) {
        ArrayList arrayList;
        r5 r5VarA;
        ConstraintAttribute constraintAttribute;
        q5 q5VarB;
        ConstraintAttribute constraintAttribute2;
        new HashSet();
        HashSet<String> hashSet = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        HashSet<String> hashSet3 = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        int i3 = this.A;
        if (i3 != b5.f7022e) {
            this.d.j = i3;
        }
        this.f.a(this.g, hashSet2);
        ArrayList<b5> arrayList2 = this.v;
        if (arrayList2 != null) {
            arrayList = null;
            for (b5 b5Var : arrayList2) {
                if (b5Var instanceof h5) {
                    h5 h5Var = (h5) b5Var;
                    a(new o5(i, i2, h5Var, this.d, this.f8360e));
                    int i4 = h5Var.f;
                    if (i4 != b5.f7022e) {
                        this.c = i4;
                    }
                } else if (b5Var instanceof e5) {
                    b5Var.a(hashSet3);
                } else if (b5Var instanceof j5) {
                    b5Var.a(hashSet);
                } else if (b5Var instanceof k5) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add((k5) b5Var);
                } else {
                    b5Var.b(map);
                    b5Var.a(hashSet2);
                }
            }
        } else {
            arrayList = null;
        }
        char c = 0;
        if (arrayList != null) {
            this.z = (k5[]) arrayList.toArray(new k5[0]);
        }
        char c2 = 1;
        if (!hashSet2.isEmpty()) {
            this.x = new HashMap<>();
            for (String str : hashSet2) {
                if (str.startsWith("CUSTOM,")) {
                    SparseArray sparseArray = new SparseArray();
                    String str2 = str.split(",")[c2];
                    for (b5 b5Var2 : this.v) {
                        HashMap<String, ConstraintAttribute> map2 = b5Var2.d;
                        if (map2 != null && (constraintAttribute2 = map2.get(str2)) != null) {
                            sparseArray.append(b5Var2.f7023a, constraintAttribute2);
                        }
                    }
                    q5VarB = q5.a(str, (SparseArray<ConstraintAttribute>) sparseArray);
                } else {
                    q5VarB = q5.b(str);
                }
                if (q5VarB != null) {
                    q5VarB.a(str);
                    this.x.put(str, q5VarB);
                }
                c2 = 1;
            }
            ArrayList<b5> arrayList3 = this.v;
            if (arrayList3 != null) {
                for (b5 b5Var3 : arrayList3) {
                    if (b5Var3 instanceof c5) {
                        b5Var3.a(this.x);
                    }
                }
            }
            this.f.a(this.x, 0);
            this.g.a(this.x, 100);
            for (String str3 : this.x.keySet()) {
                this.x.get(str3).a(map.containsKey(str3) ? map.get(str3).intValue() : 0);
            }
        }
        if (!hashSet.isEmpty()) {
            if (this.w == null) {
                this.w = new HashMap<>();
            }
            for (String str4 : hashSet) {
                if (!this.w.containsKey(str4)) {
                    if (str4.startsWith("CUSTOM,")) {
                        SparseArray sparseArray2 = new SparseArray();
                        String str5 = str4.split(",")[1];
                        for (b5 b5Var4 : this.v) {
                            HashMap<String, ConstraintAttribute> map3 = b5Var4.d;
                            if (map3 != null && (constraintAttribute = map3.get(str5)) != null) {
                                sparseArray2.append(b5Var4.f7023a, constraintAttribute);
                            }
                        }
                        r5VarA = r5.a(str4, (SparseArray<ConstraintAttribute>) sparseArray2);
                    } else {
                        r5VarA = r5.a(str4, j);
                    }
                    if (r5VarA != null) {
                        r5VarA.a(str4);
                        this.w.put(str4, r5VarA);
                    }
                }
            }
            ArrayList<b5> arrayList4 = this.v;
            if (arrayList4 != null) {
                for (b5 b5Var5 : arrayList4) {
                    if (b5Var5 instanceof j5) {
                        ((j5) b5Var5).c(this.w);
                    }
                }
            }
            for (String str6 : this.w.keySet()) {
                this.w.get(str6).a(map.containsKey(str6) ? map.get(str6).intValue() : 0);
            }
        }
        int i5 = 2;
        int size = this.t.size() + 2;
        o5[] o5VarArr = new o5[size];
        o5VarArr[0] = this.d;
        o5VarArr[size - 1] = this.f8360e;
        if (this.t.size() > 0 && this.c == -1) {
            this.c = 0;
        }
        Iterator<o5> it = this.t.iterator();
        int i6 = 1;
        while (it.hasNext()) {
            o5VarArr[i6] = it.next();
            i6++;
        }
        HashSet hashSet4 = new HashSet();
        for (String str7 : this.f8360e.k.keySet()) {
            if (this.d.k.containsKey(str7)) {
                if (!hashSet2.contains("CUSTOM," + str7)) {
                    hashSet4.add(str7);
                }
            }
        }
        String[] strArr = (String[]) hashSet4.toArray(new String[0]);
        this.p = strArr;
        this.q = new int[strArr.length];
        int i7 = 0;
        while (true) {
            String[] strArr2 = this.p;
            if (i7 >= strArr2.length) {
                break;
            }
            String str8 = strArr2[i7];
            this.q[i7] = 0;
            int i8 = 0;
            while (true) {
                if (i8 >= size) {
                    break;
                }
                if (o5VarArr[i8].k.containsKey(str8)) {
                    int[] iArr = this.q;
                    iArr[i7] = iArr[i7] + o5VarArr[i8].k.get(str8).c();
                    break;
                }
                i8++;
            }
            i7++;
        }
        boolean z = o5VarArr[0].j != b5.f7022e;
        int length = 18 + this.p.length;
        boolean[] zArr = new boolean[length];
        for (int i9 = 1; i9 < size; i9++) {
            o5VarArr[i9].a(o5VarArr[i9 - 1], zArr, this.p, z);
        }
        int i10 = 0;
        for (int i11 = 1; i11 < length; i11++) {
            if (zArr[i11]) {
                i10++;
            }
        }
        int[] iArr2 = new int[i10];
        this.m = iArr2;
        this.n = new double[iArr2.length];
        this.o = new double[iArr2.length];
        int i12 = 0;
        for (int i13 = 1; i13 < length; i13++) {
            if (zArr[i13]) {
                this.m[i12] = i13;
                i12++;
            }
        }
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, size, this.m.length);
        double[] dArr2 = new double[size];
        for (int i14 = 0; i14 < size; i14++) {
            o5VarArr[i14].a(dArr[i14], this.m);
            dArr2[i14] = o5VarArr[i14].c;
        }
        int i15 = 0;
        while (true) {
            int[] iArr3 = this.m;
            if (i15 >= iArr3.length) {
                break;
            }
            if (iArr3[i15] < o5.o.length) {
                String str9 = o5.o[this.m[i15]] + " [";
                for (int i16 = 0; i16 < size; i16++) {
                    str9 = str9 + dArr[i16][i15];
                }
            }
            i15++;
        }
        this.h = new s4[this.p.length + 1];
        int i17 = 0;
        while (true) {
            String[] strArr3 = this.p;
            if (i17 >= strArr3.length) {
                break;
            }
            String str10 = strArr3[i17];
            int i18 = 0;
            double[] dArr3 = null;
            int i19 = 0;
            double[][] dArr4 = null;
            while (i18 < size) {
                if (o5VarArr[i18].b(str10)) {
                    if (dArr4 == null) {
                        dArr3 = new double[size];
                        int[] iArr4 = new int[i5];
                        iArr4[1] = o5VarArr[i18].a(str10);
                        iArr4[c] = size;
                        dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, iArr4);
                    }
                    dArr3[i19] = o5VarArr[i18].c;
                    o5VarArr[i18].a(str10, dArr4[i19], 0);
                    i19++;
                }
                i18++;
                i5 = 2;
                c = 0;
            }
            i17++;
            this.h[i17] = s4.a(this.c, Arrays.copyOf(dArr3, i19), (double[][]) Arrays.copyOf(dArr4, i19));
            i5 = 2;
            c = 0;
        }
        this.h[0] = s4.a(this.c, dArr2, dArr);
        if (o5VarArr[0].j != b5.f7022e) {
            int[] iArr5 = new int[size];
            double[] dArr5 = new double[size];
            double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
            for (int i20 = 0; i20 < size; i20++) {
                iArr5[i20] = o5VarArr[i20].j;
                dArr5[i20] = o5VarArr[i20].c;
                dArr6[i20][0] = o5VarArr[i20].f8628e;
                dArr6[i20][1] = o5VarArr[i20].f;
            }
            this.i = s4.a(iArr5, dArr5, dArr6);
        }
        float fD = Float.NaN;
        this.y = new HashMap<>();
        if (this.v != null) {
            for (String str11 : hashSet3) {
                f5 f5VarB = f5.b(str11);
                if (f5VarB != null) {
                    if (f5VarB.a() && Float.isNaN(fD)) {
                        fD = d();
                    }
                    f5VarB.a(str11);
                    this.y.put(str11, f5VarB);
                }
            }
            for (b5 b5Var6 : this.v) {
                if (b5Var6 instanceof e5) {
                    ((e5) b5Var6).c(this.y);
                }
            }
            Iterator<f5> it2 = this.y.values().iterator();
            while (it2.hasNext()) {
                it2.next().c(fD);
            }
        }
    }

    public void a(View view) {
        o5 o5Var = this.d;
        o5Var.c = 0.0f;
        o5Var.d = 0.0f;
        o5Var.a(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.f.b(view);
    }

    public void a(ConstraintWidget constraintWidget, e7 e7Var) {
        o5 o5Var = this.f8360e;
        o5Var.c = 1.0f;
        o5Var.d = 1.0f;
        b(o5Var);
        this.f8360e.a(constraintWidget.E(), constraintWidget.F(), constraintWidget.D(), constraintWidget.l());
        this.f8360e.a(e7Var.d(this.b));
        this.g.a(constraintWidget, e7Var, this.b);
    }

    public final float a(float f, float[] fArr) {
        float f2 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else if (this.l != 1.0d) {
            if (f < this.k) {
                f = 0.0f;
            }
            float f3 = this.k;
            if (f > f3 && f < 1.0d) {
                f = (f - f3) * this.l;
            }
        }
        t4 t4Var = this.d.f8627a;
        float f4 = Float.NaN;
        for (o5 o5Var : this.t) {
            t4 t4Var2 = o5Var.f8627a;
            if (t4Var2 != null) {
                float f5 = o5Var.c;
                if (f5 < f) {
                    t4Var = t4Var2;
                    f2 = f5;
                } else if (Float.isNaN(f4)) {
                    f4 = o5Var.c;
                }
            }
        }
        if (t4Var != null) {
            float f6 = (Float.isNaN(f4) ? 1.0f : f4) - f2;
            double d = (f - f2) / f6;
            f = (((float) t4Var.a(d)) * f6) + f2;
            if (fArr != null) {
                fArr[0] = (float) t4Var.b(d);
            }
        }
        return f;
    }

    public boolean a(View view, float f, long j, d5 d5Var) {
        r5.d dVar;
        boolean zA;
        double d;
        float fA = a(f, (float[]) null);
        HashMap<String, q5> map = this.x;
        if (map != null) {
            Iterator<q5> it = map.values().iterator();
            while (it.hasNext()) {
                it.next().a(view, fA);
            }
        }
        HashMap<String, r5> map2 = this.w;
        if (map2 != null) {
            dVar = null;
            boolean zA2 = false;
            for (r5 r5Var : map2.values()) {
                if (r5Var instanceof r5.d) {
                    dVar = (r5.d) r5Var;
                } else {
                    zA2 |= r5Var.a(view, fA, j, d5Var);
                }
            }
            zA = zA2;
        } else {
            dVar = null;
            zA = false;
        }
        s4[] s4VarArr = this.h;
        if (s4VarArr != null) {
            double d2 = fA;
            s4VarArr[0].a(d2, this.n);
            this.h[0].b(d2, this.o);
            s4 s4Var = this.i;
            if (s4Var != null) {
                double[] dArr = this.n;
                if (dArr.length > 0) {
                    s4Var.a(d2, dArr);
                    this.i.b(d2, this.o);
                }
            }
            this.d.a(view, this.m, this.n, this.o, (double[]) null);
            HashMap<String, q5> map3 = this.x;
            if (map3 != null) {
                for (q5 q5Var : map3.values()) {
                    if (q5Var instanceof q5.d) {
                        double[] dArr2 = this.o;
                        ((q5.d) q5Var).a(view, fA, dArr2[0], dArr2[1]);
                    }
                }
            }
            if (dVar != null) {
                double[] dArr3 = this.o;
                d = d2;
                zA = dVar.a(view, d5Var, fA, j, dArr3[0], dArr3[1]) | zA;
            } else {
                d = d2;
            }
            int i = 1;
            while (true) {
                s4[] s4VarArr2 = this.h;
                if (i >= s4VarArr2.length) {
                    break;
                }
                s4VarArr2[i].a(d, this.s);
                this.d.k.get(this.p[i - 1]).a(view, this.s);
                i++;
            }
            l5 l5Var = this.f;
            if (l5Var.b == 0) {
                if (fA <= 0.0f) {
                    view.setVisibility(l5Var.c);
                } else if (fA >= 1.0f) {
                    view.setVisibility(this.g.c);
                } else if (this.g.c != l5Var.c) {
                    view.setVisibility(0);
                }
            }
            if (this.z != null) {
                int i2 = 0;
                while (true) {
                    k5[] k5VarArr = this.z;
                    if (i2 >= k5VarArr.length) {
                        break;
                    }
                    k5VarArr[i2].a(fA, view);
                    i2++;
                }
            }
        } else {
            o5 o5Var = this.d;
            float f2 = o5Var.f8628e;
            o5 o5Var2 = this.f8360e;
            float f3 = f2 + ((o5Var2.f8628e - f2) * fA);
            float f4 = o5Var.f;
            float f5 = f4 + ((o5Var2.f - f4) * fA);
            float f6 = o5Var.g;
            float f7 = o5Var2.g;
            float f8 = o5Var.h;
            float f9 = o5Var2.h;
            float f10 = f3 + 0.5f;
            int i3 = (int) f10;
            float f11 = f5 + 0.5f;
            int i4 = (int) f11;
            int i5 = (int) (f10 + ((f7 - f6) * fA) + f6);
            int i6 = (int) (f11 + ((f9 - f8) * fA) + f8);
            int i7 = i5 - i3;
            int i8 = i6 - i4;
            if (f7 != f6 || f9 != f8) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i7, 1073741824), View.MeasureSpec.makeMeasureSpec(i8, 1073741824));
            }
            view.layout(i3, i4, i5, i6);
        }
        HashMap<String, f5> map4 = this.y;
        if (map4 != null) {
            for (f5 f5Var : map4.values()) {
                if (f5Var instanceof f5.f) {
                    double[] dArr4 = this.o;
                    ((f5.f) f5Var).a(view, fA, dArr4[0], dArr4[1]);
                } else {
                    f5Var.a(view, fA);
                }
            }
        }
        return zA;
    }

    public void a(float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        float fA = a(f, this.u);
        s4[] s4VarArr = this.h;
        int i = 0;
        if (s4VarArr != null) {
            double d = fA;
            s4VarArr[0].b(d, this.o);
            this.h[0].a(d, this.n);
            float f4 = this.u[0];
            while (true) {
                dArr = this.o;
                if (i >= dArr.length) {
                    break;
                }
                dArr[i] = dArr[i] * ((double) f4);
                i++;
            }
            s4 s4Var = this.i;
            if (s4Var != null) {
                double[] dArr2 = this.n;
                if (dArr2.length > 0) {
                    s4Var.a(d, dArr2);
                    this.i.b(d, this.o);
                    this.d.a(f2, f3, fArr, this.m, this.o, this.n);
                    return;
                }
                return;
            }
            this.d.a(f2, f3, fArr, this.m, dArr, this.n);
            return;
        }
        o5 o5Var = this.f8360e;
        float f5 = o5Var.f8628e;
        o5 o5Var2 = this.d;
        float f6 = f5 - o5Var2.f8628e;
        float f7 = o5Var.f - o5Var2.f;
        float f8 = (o5Var.g - o5Var2.g) + f6;
        float f9 = (o5Var.h - o5Var2.h) + f7;
        fArr[0] = (f6 * (1.0f - f2)) + (f8 * f2);
        fArr[1] = (f7 * (1.0f - f3)) + (f9 * f3);
    }

    public void a(float f, int i, int i2, float f2, float f3, float[] fArr) {
        float fA = a(f, this.u);
        HashMap<String, q5> map = this.x;
        q5 q5Var = map == null ? null : map.get("translationX");
        HashMap<String, q5> map2 = this.x;
        q5 q5Var2 = map2 == null ? null : map2.get("translationY");
        HashMap<String, q5> map3 = this.x;
        q5 q5Var3 = map3 == null ? null : map3.get("rotation");
        HashMap<String, q5> map4 = this.x;
        q5 q5Var4 = map4 == null ? null : map4.get("scaleX");
        HashMap<String, q5> map5 = this.x;
        q5 q5Var5 = map5 == null ? null : map5.get("scaleY");
        HashMap<String, f5> map6 = this.y;
        f5 f5Var = map6 == null ? null : map6.get("translationX");
        HashMap<String, f5> map7 = this.y;
        f5 f5Var2 = map7 == null ? null : map7.get("translationY");
        HashMap<String, f5> map8 = this.y;
        f5 f5Var3 = map8 == null ? null : map8.get("rotation");
        HashMap<String, f5> map9 = this.y;
        f5 f5Var4 = map9 == null ? null : map9.get("scaleX");
        HashMap<String, f5> map10 = this.y;
        f5 f5Var5 = map10 != null ? map10.get("scaleY") : null;
        y4 y4Var = new y4();
        y4Var.a();
        y4Var.a(q5Var3, fA);
        y4Var.b(q5Var, q5Var2, fA);
        y4Var.a(q5Var4, q5Var5, fA);
        y4Var.a(f5Var3, fA);
        y4Var.b(f5Var, f5Var2, fA);
        y4Var.a(f5Var4, f5Var5, fA);
        s4 s4Var = this.i;
        if (s4Var != null) {
            double[] dArr = this.n;
            if (dArr.length > 0) {
                double d = fA;
                s4Var.a(d, dArr);
                this.i.b(d, this.o);
                this.d.a(f2, f3, fArr, this.m, this.o, this.n);
            }
            y4Var.a(f2, f3, i, i2, fArr);
            return;
        }
        int i3 = 0;
        if (this.h != null) {
            double dA = a(fA, this.u);
            this.h[0].b(dA, this.o);
            this.h[0].a(dA, this.n);
            float f4 = this.u[0];
            while (true) {
                double[] dArr2 = this.o;
                if (i3 < dArr2.length) {
                    dArr2[i3] = dArr2[i3] * ((double) f4);
                    i3++;
                } else {
                    this.d.a(f2, f3, fArr, this.m, dArr2, this.n);
                    y4Var.a(f2, f3, i, i2, fArr);
                    return;
                }
            }
        } else {
            o5 o5Var = this.f8360e;
            float f5 = o5Var.f8628e;
            o5 o5Var2 = this.d;
            float f6 = f5 - o5Var2.f8628e;
            f5 f5Var6 = f5Var5;
            float f7 = o5Var.f - o5Var2.f;
            f5 f5Var7 = f5Var4;
            float f8 = (o5Var.g - o5Var2.g) + f6;
            float f9 = (o5Var.h - o5Var2.h) + f7;
            fArr[0] = (f6 * (1.0f - f2)) + (f8 * f2);
            fArr[1] = (f7 * (1.0f - f3)) + (f9 * f3);
            y4Var.a();
            y4Var.a(q5Var3, fA);
            y4Var.b(q5Var, q5Var2, fA);
            y4Var.a(q5Var4, q5Var5, fA);
            y4Var.a(f5Var3, fA);
            y4Var.b(f5Var, f5Var2, fA);
            y4Var.a(f5Var7, f5Var6, fA);
            y4Var.a(f2, f3, i, i2, fArr);
        }
    }

    public int a() {
        int iMax = this.d.b;
        Iterator<o5> it = this.t.iterator();
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().b);
        }
        return Math.max(iMax, this.f8360e.b);
    }
}
