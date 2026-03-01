package supwisdom;

import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import com.taobao.weex.common.Constants;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.feature.weex_amap.adapter.Constant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import supwisdom.e7;
import supwisdom.q5;

/* JADX INFO: compiled from: MotionConstrainedPoint.java */
/* JADX INFO: loaded from: classes.dex */
public class l5 implements Comparable<l5> {
    public int c;
    public float o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f8248a = 1.0f;
    public int b = 0;
    public float d = 0.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f8249e = 0.0f;
    public float f = 0.0f;
    public float g = 0.0f;
    public float h = 1.0f;
    public float i = 1.0f;
    public float j = Float.NaN;
    public float k = Float.NaN;
    public float l = 0.0f;
    public float m = 0.0f;
    public float n = 0.0f;
    public float p = Float.NaN;
    public float q = Float.NaN;
    public LinkedHashMap<String, ConstraintAttribute> r = new LinkedHashMap<>();

    public void a(float f, float f2, float f3, float f4) {
    }

    public final boolean a(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    public void b(View view) {
        a(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        a(view);
    }

    public void a(l5 l5Var, HashSet<String> hashSet) {
        if (a(this.f8248a, l5Var.f8248a)) {
            hashSet.add(Constant.JSONKEY.ALPHE);
        }
        if (a(this.d, l5Var.d)) {
            hashSet.add(Constants.Name.ELEVATION);
        }
        int i = this.c;
        int i2 = l5Var.c;
        if (i != i2 && this.b == 0 && (i == 0 || i2 == 0)) {
            hashSet.add(Constant.JSONKEY.ALPHE);
        }
        if (a(this.f8249e, l5Var.f8249e)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.p) || !Float.isNaN(l5Var.p)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.q) || !Float.isNaN(l5Var.q)) {
            hashSet.add(AbsoluteConst.JSON_KEY_PROGRESS);
        }
        if (a(this.f, l5Var.f)) {
            hashSet.add("rotationX");
        }
        if (a(this.g, l5Var.g)) {
            hashSet.add("rotationY");
        }
        if (a(this.j, l5Var.j)) {
            hashSet.add("transformPivotX");
        }
        if (a(this.k, l5Var.k)) {
            hashSet.add("transformPivotY");
        }
        if (a(this.h, l5Var.h)) {
            hashSet.add("scaleX");
        }
        if (a(this.i, l5Var.i)) {
            hashSet.add("scaleY");
        }
        if (a(this.l, l5Var.l)) {
            hashSet.add("translationX");
        }
        if (a(this.m, l5Var.m)) {
            hashSet.add("translationY");
        }
        if (a(this.n, l5Var.n)) {
            hashSet.add("translationZ");
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(l5 l5Var) {
        return Float.compare(this.o, l5Var.o);
    }

    public void a(View view) {
        this.c = view.getVisibility();
        this.f8248a = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        if (Build.VERSION.SDK_INT >= 21) {
            this.d = view.getElevation();
        }
        this.f8249e = view.getRotation();
        this.f = view.getRotationX();
        this.g = view.getRotationY();
        this.h = view.getScaleX();
        this.i = view.getScaleY();
        this.j = view.getPivotX();
        this.k = view.getPivotY();
        this.l = view.getTranslationX();
        this.m = view.getTranslationY();
        if (Build.VERSION.SDK_INT >= 21) {
            this.n = view.getTranslationZ();
        }
    }

    public void a(e7.a aVar) {
        e7.d dVar = aVar.b;
        int i = dVar.c;
        this.b = i;
        int i2 = dVar.b;
        this.c = i2;
        this.f8248a = (i2 == 0 || i != 0) ? aVar.b.d : 0.0f;
        e7.e eVar = aVar.f7433e;
        boolean z = eVar.l;
        this.d = eVar.m;
        this.f8249e = eVar.b;
        this.f = eVar.c;
        this.g = eVar.d;
        this.h = eVar.f7441e;
        this.i = eVar.f;
        this.j = eVar.g;
        this.k = eVar.h;
        this.l = eVar.i;
        this.m = eVar.j;
        this.n = eVar.k;
        t4.a(aVar.c.c);
        e7.c cVar = aVar.c;
        this.p = cVar.g;
        int i3 = cVar.f7437e;
        this.q = aVar.b.f7439e;
        for (String str : aVar.f.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.f.get(str);
            if (constraintAttribute.a() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.r.put(str, constraintAttribute);
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void a(HashMap<String, q5> map, int i) {
        for (String str : map.keySet()) {
            q5 q5Var = map.get(str);
            byte b = -1;
            switch (str.hashCode()) {
                case -1249320806:
                    if (str.equals("rotationX")) {
                        b = 3;
                    }
                    break;
                case -1249320805:
                    if (str.equals("rotationY")) {
                        b = 4;
                    }
                    break;
                case -1225497657:
                    if (str.equals("translationX")) {
                        b = 11;
                    }
                    break;
                case -1225497656:
                    if (str.equals("translationY")) {
                        b = 12;
                    }
                    break;
                case -1225497655:
                    if (str.equals("translationZ")) {
                        b = 13;
                    }
                    break;
                case -1001078227:
                    if (str.equals(AbsoluteConst.JSON_KEY_PROGRESS)) {
                        b = 8;
                    }
                    break;
                case -908189618:
                    if (str.equals("scaleX")) {
                        b = 9;
                    }
                    break;
                case -908189617:
                    if (str.equals("scaleY")) {
                        b = 10;
                    }
                    break;
                case -760884510:
                    if (str.equals("transformPivotX")) {
                        b = 5;
                    }
                    break;
                case -760884509:
                    if (str.equals("transformPivotY")) {
                        b = 6;
                    }
                    break;
                case -40300674:
                    if (str.equals("rotation")) {
                        b = 2;
                    }
                    break;
                case -4379043:
                    if (str.equals(Constants.Name.ELEVATION)) {
                        b = 1;
                    }
                    break;
                case 37232917:
                    if (str.equals("transitionPathRotate")) {
                        b = 7;
                    }
                    break;
                case 92909918:
                    if (str.equals(Constant.JSONKEY.ALPHE)) {
                        b = 0;
                    }
                    break;
            }
            switch (b) {
                case 0:
                    q5Var.a(i, Float.isNaN(this.f8248a) ? 1.0f : this.f8248a);
                    break;
                case 1:
                    q5Var.a(i, Float.isNaN(this.d) ? 0.0f : this.d);
                    break;
                case 2:
                    q5Var.a(i, Float.isNaN(this.f8249e) ? 0.0f : this.f8249e);
                    break;
                case 3:
                    q5Var.a(i, Float.isNaN(this.f) ? 0.0f : this.f);
                    break;
                case 4:
                    q5Var.a(i, Float.isNaN(this.g) ? 0.0f : this.g);
                    break;
                case 5:
                    q5Var.a(i, Float.isNaN(this.j) ? 0.0f : this.j);
                    break;
                case 6:
                    q5Var.a(i, Float.isNaN(this.k) ? 0.0f : this.k);
                    break;
                case 7:
                    q5Var.a(i, Float.isNaN(this.p) ? 0.0f : this.p);
                    break;
                case 8:
                    q5Var.a(i, Float.isNaN(this.q) ? 0.0f : this.q);
                    break;
                case 9:
                    q5Var.a(i, Float.isNaN(this.h) ? 1.0f : this.h);
                    break;
                case 10:
                    q5Var.a(i, Float.isNaN(this.i) ? 1.0f : this.i);
                    break;
                case 11:
                    q5Var.a(i, Float.isNaN(this.l) ? 0.0f : this.l);
                    break;
                case 12:
                    q5Var.a(i, Float.isNaN(this.m) ? 0.0f : this.m);
                    break;
                case 13:
                    q5Var.a(i, Float.isNaN(this.n) ? 0.0f : this.n);
                    break;
                default:
                    if (!str.startsWith("CUSTOM")) {
                        Log.e("MotionPaths", "UNKNOWN spline " + str);
                    } else {
                        String str2 = str.split(",")[1];
                        if (!this.r.containsKey(str2)) {
                            Log.e("MotionPaths", "UNKNOWN customName " + str2);
                        } else {
                            ConstraintAttribute constraintAttribute = this.r.get(str2);
                            if (q5Var instanceof q5.b) {
                                ((q5.b) q5Var).a(i, constraintAttribute);
                            } else {
                                Log.e("MotionPaths", str + " splineSet not a CustomSet frame = " + i + ", value" + constraintAttribute.b() + q5Var);
                            }
                        }
                    }
                    break;
            }
        }
    }

    public void a(ConstraintWidget constraintWidget, e7 e7Var, int i) {
        a(constraintWidget.E(), constraintWidget.F(), constraintWidget.D(), constraintWidget.l());
        a(e7Var.d(i));
    }
}
