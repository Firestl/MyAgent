package supwisdom;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R;
import com.taobao.weex.common.Constants;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.feature.weex_amap.adapter.Constant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyTimeCycle.java */
/* JADX INFO: loaded from: classes.dex */
public class j5 extends b5 {
    public String f;
    public int g = -1;
    public float h = Float.NaN;
    public float i = Float.NaN;
    public float j = Float.NaN;
    public float k = Float.NaN;
    public float l = Float.NaN;
    public float m = Float.NaN;
    public float n = Float.NaN;
    public float o = Float.NaN;
    public float p = Float.NaN;
    public float q = Float.NaN;
    public float r = Float.NaN;
    public float s = Float.NaN;
    public int t = 0;
    public float u = Float.NaN;
    public float v = 0.0f;

    /* JADX INFO: compiled from: KeyTimeCycle.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f8022a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f8022a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyTimeCycle_android_alpha, 1);
            f8022a.append(R.styleable.KeyTimeCycle_android_elevation, 2);
            f8022a.append(R.styleable.KeyTimeCycle_android_rotation, 4);
            f8022a.append(R.styleable.KeyTimeCycle_android_rotationX, 5);
            f8022a.append(R.styleable.KeyTimeCycle_android_rotationY, 6);
            f8022a.append(R.styleable.KeyTimeCycle_android_scaleX, 7);
            f8022a.append(R.styleable.KeyTimeCycle_transitionPathRotate, 8);
            f8022a.append(R.styleable.KeyTimeCycle_transitionEasing, 9);
            f8022a.append(R.styleable.KeyTimeCycle_motionTarget, 10);
            f8022a.append(R.styleable.KeyTimeCycle_framePosition, 12);
            f8022a.append(R.styleable.KeyTimeCycle_curveFit, 13);
            f8022a.append(R.styleable.KeyTimeCycle_android_scaleY, 14);
            f8022a.append(R.styleable.KeyTimeCycle_android_translationX, 15);
            f8022a.append(R.styleable.KeyTimeCycle_android_translationY, 16);
            f8022a.append(R.styleable.KeyTimeCycle_android_translationZ, 17);
            f8022a.append(R.styleable.KeyTimeCycle_motionProgress, 18);
            f8022a.append(R.styleable.KeyTimeCycle_wavePeriod, 20);
            f8022a.append(R.styleable.KeyTimeCycle_waveOffset, 21);
            f8022a.append(R.styleable.KeyTimeCycle_waveShape, 19);
        }

        public static void a(j5 j5Var, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f8022a.get(index)) {
                    case 1:
                        j5Var.h = typedArray.getFloat(index, j5Var.h);
                        break;
                    case 2:
                        j5Var.i = typedArray.getDimension(index, j5Var.i);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f8022a.get(index));
                        break;
                    case 4:
                        j5Var.j = typedArray.getFloat(index, j5Var.j);
                        break;
                    case 5:
                        j5Var.k = typedArray.getFloat(index, j5Var.k);
                        break;
                    case 6:
                        j5Var.l = typedArray.getFloat(index, j5Var.l);
                        break;
                    case 7:
                        j5Var.n = typedArray.getFloat(index, j5Var.n);
                        break;
                    case 8:
                        j5Var.m = typedArray.getFloat(index, j5Var.m);
                        break;
                    case 9:
                        j5Var.f = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.E0) {
                            int resourceId = typedArray.getResourceId(index, j5Var.b);
                            j5Var.b = resourceId;
                            if (resourceId == -1) {
                                j5Var.c = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            j5Var.c = typedArray.getString(index);
                        } else {
                            j5Var.b = typedArray.getResourceId(index, j5Var.b);
                        }
                        break;
                    case 12:
                        j5Var.f7023a = typedArray.getInt(index, j5Var.f7023a);
                        break;
                    case 13:
                        j5Var.g = typedArray.getInteger(index, j5Var.g);
                        break;
                    case 14:
                        j5Var.o = typedArray.getFloat(index, j5Var.o);
                        break;
                    case 15:
                        j5Var.p = typedArray.getDimension(index, j5Var.p);
                        break;
                    case 16:
                        j5Var.q = typedArray.getDimension(index, j5Var.q);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            j5Var.r = typedArray.getDimension(index, j5Var.r);
                        }
                        break;
                    case 18:
                        j5Var.s = typedArray.getFloat(index, j5Var.s);
                        break;
                    case 19:
                        j5Var.t = typedArray.getInt(index, j5Var.t);
                        break;
                    case 20:
                        j5Var.u = typedArray.getFloat(index, j5Var.u);
                        break;
                    case 21:
                        if (typedArray.peekValue(index).type == 5) {
                            j5Var.v = typedArray.getDimension(index, j5Var.v);
                        } else {
                            j5Var.v = typedArray.getFloat(index, j5Var.v);
                        }
                        break;
                }
            }
        }
    }

    public j5() {
        this.d = new HashMap<>();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(java.util.HashMap<java.lang.String, supwisdom.r5> r11) {
        /*
            Method dump skipped, instruction units count: 590
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.j5.c(java.util.HashMap):void");
    }

    @Override // supwisdom.b5
    public void b(HashMap<String, Integer> map) {
        if (this.g == -1) {
            return;
        }
        if (!Float.isNaN(this.h)) {
            map.put(Constant.JSONKEY.ALPHE, Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.i)) {
            map.put(Constants.Name.ELEVATION, Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.j)) {
            map.put("rotation", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.k)) {
            map.put("rotationX", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.l)) {
            map.put("rotationY", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.p)) {
            map.put("translationX", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.q)) {
            map.put("translationY", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.r)) {
            map.put("translationZ", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.m)) {
            map.put("transitionPathRotate", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.n)) {
            map.put("scaleX", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.n)) {
            map.put("scaleY", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.s)) {
            map.put(AbsoluteConst.JSON_KEY_PROGRESS, Integer.valueOf(this.g));
        }
        if (this.d.size() > 0) {
            Iterator<String> it = this.d.keySet().iterator();
            while (it.hasNext()) {
                map.put("CUSTOM," + it.next(), Integer.valueOf(this.g));
            }
        }
    }

    @Override // supwisdom.b5
    public void a(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyTimeCycle));
    }

    @Override // supwisdom.b5
    public void a(HashSet<String> hashSet) {
        if (!Float.isNaN(this.h)) {
            hashSet.add(Constant.JSONKEY.ALPHE);
        }
        if (!Float.isNaN(this.i)) {
            hashSet.add(Constants.Name.ELEVATION);
        }
        if (!Float.isNaN(this.j)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.k)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.l)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.m)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.s)) {
            hashSet.add(AbsoluteConst.JSON_KEY_PROGRESS);
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
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }
}
