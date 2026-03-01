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

/* JADX INFO: compiled from: KeyAttributes.java */
/* JADX INFO: loaded from: classes.dex */
public class c5 extends b5 {
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
    public float t = Float.NaN;
    public float u = Float.NaN;

    /* JADX INFO: compiled from: KeyAttributes.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f7145a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f7145a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyAttribute_android_alpha, 1);
            f7145a.append(R.styleable.KeyAttribute_android_elevation, 2);
            f7145a.append(R.styleable.KeyAttribute_android_rotation, 4);
            f7145a.append(R.styleable.KeyAttribute_android_rotationX, 5);
            f7145a.append(R.styleable.KeyAttribute_android_rotationY, 6);
            f7145a.append(R.styleable.KeyAttribute_android_transformPivotX, 19);
            f7145a.append(R.styleable.KeyAttribute_android_transformPivotY, 20);
            f7145a.append(R.styleable.KeyAttribute_android_scaleX, 7);
            f7145a.append(R.styleable.KeyAttribute_transitionPathRotate, 8);
            f7145a.append(R.styleable.KeyAttribute_transitionEasing, 9);
            f7145a.append(R.styleable.KeyAttribute_motionTarget, 10);
            f7145a.append(R.styleable.KeyAttribute_framePosition, 12);
            f7145a.append(R.styleable.KeyAttribute_curveFit, 13);
            f7145a.append(R.styleable.KeyAttribute_android_scaleY, 14);
            f7145a.append(R.styleable.KeyAttribute_android_translationX, 15);
            f7145a.append(R.styleable.KeyAttribute_android_translationY, 16);
            f7145a.append(R.styleable.KeyAttribute_android_translationZ, 17);
            f7145a.append(R.styleable.KeyAttribute_motionProgress, 18);
        }

        public static void a(c5 c5Var, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f7145a.get(index)) {
                    case 1:
                        c5Var.h = typedArray.getFloat(index, c5Var.h);
                        break;
                    case 2:
                        c5Var.i = typedArray.getDimension(index, c5Var.i);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(index) + "   " + f7145a.get(index));
                        break;
                    case 4:
                        c5Var.j = typedArray.getFloat(index, c5Var.j);
                        break;
                    case 5:
                        c5Var.k = typedArray.getFloat(index, c5Var.k);
                        break;
                    case 6:
                        c5Var.l = typedArray.getFloat(index, c5Var.l);
                        break;
                    case 7:
                        c5Var.p = typedArray.getFloat(index, c5Var.p);
                        break;
                    case 8:
                        c5Var.o = typedArray.getFloat(index, c5Var.o);
                        break;
                    case 9:
                        c5Var.f = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.E0) {
                            int resourceId = typedArray.getResourceId(index, c5Var.b);
                            c5Var.b = resourceId;
                            if (resourceId == -1) {
                                c5Var.c = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            c5Var.c = typedArray.getString(index);
                        } else {
                            c5Var.b = typedArray.getResourceId(index, c5Var.b);
                        }
                        break;
                    case 12:
                        c5Var.f7023a = typedArray.getInt(index, c5Var.f7023a);
                        break;
                    case 13:
                        c5Var.g = typedArray.getInteger(index, c5Var.g);
                        break;
                    case 14:
                        c5Var.q = typedArray.getFloat(index, c5Var.q);
                        break;
                    case 15:
                        c5Var.r = typedArray.getDimension(index, c5Var.r);
                        break;
                    case 16:
                        c5Var.s = typedArray.getDimension(index, c5Var.s);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            c5Var.t = typedArray.getDimension(index, c5Var.t);
                        }
                        break;
                    case 18:
                        c5Var.u = typedArray.getFloat(index, c5Var.u);
                        break;
                    case 19:
                        c5Var.m = typedArray.getDimension(index, c5Var.m);
                        break;
                    case 20:
                        c5Var.n = typedArray.getDimension(index, c5Var.n);
                        break;
                }
            }
        }
    }

    public c5() {
        this.d = new HashMap<>();
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
        if (!Float.isNaN(this.m)) {
            map.put("transformPivotX", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.n)) {
            map.put("transformPivotY", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.r)) {
            map.put("translationX", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.s)) {
            map.put("translationY", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.t)) {
            map.put("translationZ", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.o)) {
            map.put("transitionPathRotate", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.p)) {
            map.put("scaleX", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.q)) {
            map.put("scaleY", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.u)) {
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
        a.a(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyAttribute));
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
        if (!Float.isNaN(this.m)) {
            hashSet.add("transformPivotX");
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add("transformPivotY");
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.s)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.t)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.u)) {
            hashSet.add(AbsoluteConst.JSON_KEY_PROGRESS);
        }
        if (this.d.size() > 0) {
            Iterator<String> it = this.d.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d8  */
    @Override // supwisdom.b5
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.util.HashMap<java.lang.String, supwisdom.q5> r7) {
        /*
            Method dump skipped, instruction units count: 578
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.c5.a(java.util.HashMap):void");
    }
}
