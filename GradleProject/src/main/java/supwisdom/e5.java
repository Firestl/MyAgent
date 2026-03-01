package supwisdom;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R;
import com.taobao.weex.common.Constants;
import io.dcloud.feature.weex_amap.adapter.Constant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class e5 extends b5 {
    public String f;
    public int g = 0;
    public int h = -1;
    public float i = Float.NaN;
    public float j = 0.0f;
    public float k = Float.NaN;
    public int l = -1;
    public float m = Float.NaN;
    public float n = Float.NaN;
    public float o = Float.NaN;
    public float p = Float.NaN;
    public float q = Float.NaN;
    public float r = Float.NaN;
    public float s = Float.NaN;
    public float t = Float.NaN;
    public float u = Float.NaN;
    public float v = Float.NaN;
    public float w = Float.NaN;

    /* JADX INFO: compiled from: KeyCycle.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f7424a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f7424a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyCycle_motionTarget, 1);
            f7424a.append(R.styleable.KeyCycle_framePosition, 2);
            f7424a.append(R.styleable.KeyCycle_transitionEasing, 3);
            f7424a.append(R.styleable.KeyCycle_curveFit, 4);
            f7424a.append(R.styleable.KeyCycle_waveShape, 5);
            f7424a.append(R.styleable.KeyCycle_wavePeriod, 6);
            f7424a.append(R.styleable.KeyCycle_waveOffset, 7);
            f7424a.append(R.styleable.KeyCycle_waveVariesBy, 8);
            f7424a.append(R.styleable.KeyCycle_android_alpha, 9);
            f7424a.append(R.styleable.KeyCycle_android_elevation, 10);
            f7424a.append(R.styleable.KeyCycle_android_rotation, 11);
            f7424a.append(R.styleable.KeyCycle_android_rotationX, 12);
            f7424a.append(R.styleable.KeyCycle_android_rotationY, 13);
            f7424a.append(R.styleable.KeyCycle_transitionPathRotate, 14);
            f7424a.append(R.styleable.KeyCycle_android_scaleX, 15);
            f7424a.append(R.styleable.KeyCycle_android_scaleY, 16);
            f7424a.append(R.styleable.KeyCycle_android_translationX, 17);
            f7424a.append(R.styleable.KeyCycle_android_translationY, 18);
            f7424a.append(R.styleable.KeyCycle_android_translationZ, 19);
            f7424a.append(R.styleable.KeyCycle_motionProgress, 20);
        }

        public static void b(e5 e5Var, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f7424a.get(index)) {
                    case 1:
                        if (MotionLayout.E0) {
                            int resourceId = typedArray.getResourceId(index, e5Var.b);
                            e5Var.b = resourceId;
                            if (resourceId == -1) {
                                e5Var.c = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            e5Var.c = typedArray.getString(index);
                        } else {
                            e5Var.b = typedArray.getResourceId(index, e5Var.b);
                        }
                        break;
                    case 2:
                        e5Var.f7023a = typedArray.getInt(index, e5Var.f7023a);
                        break;
                    case 3:
                        e5Var.f = typedArray.getString(index);
                        break;
                    case 4:
                        e5Var.g = typedArray.getInteger(index, e5Var.g);
                        break;
                    case 5:
                        e5Var.h = typedArray.getInt(index, e5Var.h);
                        break;
                    case 6:
                        e5Var.i = typedArray.getFloat(index, e5Var.i);
                        break;
                    case 7:
                        if (typedArray.peekValue(index).type == 5) {
                            e5Var.j = typedArray.getDimension(index, e5Var.j);
                        } else {
                            e5Var.j = typedArray.getFloat(index, e5Var.j);
                        }
                        break;
                    case 8:
                        e5Var.l = typedArray.getInt(index, e5Var.l);
                        break;
                    case 9:
                        e5Var.m = typedArray.getFloat(index, e5Var.m);
                        break;
                    case 10:
                        e5Var.n = typedArray.getDimension(index, e5Var.n);
                        break;
                    case 11:
                        e5Var.o = typedArray.getFloat(index, e5Var.o);
                        break;
                    case 12:
                        e5Var.q = typedArray.getFloat(index, e5Var.q);
                        break;
                    case 13:
                        e5Var.r = typedArray.getFloat(index, e5Var.r);
                        break;
                    case 14:
                        e5Var.p = typedArray.getFloat(index, e5Var.p);
                        break;
                    case 15:
                        e5Var.s = typedArray.getFloat(index, e5Var.s);
                        break;
                    case 16:
                        e5Var.t = typedArray.getFloat(index, e5Var.t);
                        break;
                    case 17:
                        e5Var.u = typedArray.getDimension(index, e5Var.u);
                        break;
                    case 18:
                        e5Var.v = typedArray.getDimension(index, e5Var.v);
                        break;
                    case 19:
                        if (Build.VERSION.SDK_INT >= 21) {
                            e5Var.w = typedArray.getDimension(index, e5Var.w);
                        }
                        break;
                    case 20:
                        e5Var.k = typedArray.getFloat(index, e5Var.k);
                        break;
                    default:
                        Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f7424a.get(index));
                        break;
                }
            }
        }
    }

    public e5() {
        this.d = new HashMap<>();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float b(java.lang.String r3) {
        /*
            Method dump skipped, instruction units count: 300
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.e5.b(java.lang.String):float");
    }

    public void c(HashMap<String, f5> map) {
        for (String str : map.keySet()) {
            if (str.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute = this.d.get(str.substring(7));
                if (constraintAttribute != null && constraintAttribute.a() == ConstraintAttribute.AttributeType.FLOAT_TYPE) {
                    map.get(str).a(this.f7023a, this.h, this.l, this.i, this.j, constraintAttribute.b(), constraintAttribute);
                }
            } else {
                float fB = b(str);
                if (!Float.isNaN(fB)) {
                    map.get(str).a(this.f7023a, this.h, this.l, this.i, this.j, fB);
                }
            }
        }
    }

    @Override // supwisdom.b5
    public void a(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyCycle));
    }

    @Override // supwisdom.b5
    public void a(HashSet<String> hashSet) {
        if (!Float.isNaN(this.m)) {
            hashSet.add(Constant.JSONKEY.ALPHE);
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add(Constants.Name.ELEVATION);
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.s)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.t)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.u)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.v)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.w)) {
            hashSet.add("translationZ");
        }
        if (this.d.size() > 0) {
            Iterator<String> it = this.d.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // supwisdom.b5
    public void a(HashMap<String, q5> map) {
        q5 q5Var;
        z4.a("KeyCycle", "add " + map.size() + " values", 2);
        for (String str : map.keySet()) {
            q5Var = map.get(str);
            switch (str) {
                case "alpha":
                    q5Var.a(this.f7023a, this.m);
                    break;
                case "elevation":
                    q5Var.a(this.f7023a, this.n);
                    break;
                case "rotation":
                    q5Var.a(this.f7023a, this.o);
                    break;
                case "rotationX":
                    q5Var.a(this.f7023a, this.q);
                    break;
                case "rotationY":
                    q5Var.a(this.f7023a, this.r);
                    break;
                case "transitionPathRotate":
                    q5Var.a(this.f7023a, this.p);
                    break;
                case "scaleX":
                    q5Var.a(this.f7023a, this.s);
                    break;
                case "scaleY":
                    q5Var.a(this.f7023a, this.t);
                    break;
                case "translationX":
                    q5Var.a(this.f7023a, this.u);
                    break;
                case "translationY":
                    q5Var.a(this.f7023a, this.v);
                    break;
                case "translationZ":
                    q5Var.a(this.f7023a, this.w);
                    break;
                case "waveOffset":
                    q5Var.a(this.f7023a, this.j);
                    break;
                case "progress":
                    q5Var.a(this.f7023a, this.k);
                    break;
                default:
                    Log.v("KeyCycle", "WARNING KeyCycle UNKNOWN  " + str);
                    break;
            }
        }
    }
}
