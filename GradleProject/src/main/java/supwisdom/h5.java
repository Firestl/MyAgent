package supwisdom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;

/* JADX INFO: compiled from: KeyPosition.java */
/* JADX INFO: loaded from: classes.dex */
public class h5 extends i5 {
    public String g = null;
    public int h = b5.f7022e;
    public int i = 0;
    public float j = Float.NaN;
    public float k = Float.NaN;
    public float l = Float.NaN;
    public float m = Float.NaN;
    public float n = Float.NaN;
    public float o = Float.NaN;
    public int p = 0;

    /* JADX INFO: compiled from: KeyPosition.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f7801a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f7801a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyPosition_motionTarget, 1);
            f7801a.append(R.styleable.KeyPosition_framePosition, 2);
            f7801a.append(R.styleable.KeyPosition_transitionEasing, 3);
            f7801a.append(R.styleable.KeyPosition_curveFit, 4);
            f7801a.append(R.styleable.KeyPosition_drawPath, 5);
            f7801a.append(R.styleable.KeyPosition_percentX, 6);
            f7801a.append(R.styleable.KeyPosition_percentY, 7);
            f7801a.append(R.styleable.KeyPosition_keyPositionType, 9);
            f7801a.append(R.styleable.KeyPosition_sizePercent, 8);
            f7801a.append(R.styleable.KeyPosition_percentWidth, 11);
            f7801a.append(R.styleable.KeyPosition_percentHeight, 12);
            f7801a.append(R.styleable.KeyPosition_pathMotionArc, 10);
        }

        public static void b(h5 h5Var, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f7801a.get(index)) {
                    case 1:
                        if (MotionLayout.E0) {
                            int resourceId = typedArray.getResourceId(index, h5Var.b);
                            h5Var.b = resourceId;
                            if (resourceId == -1) {
                                h5Var.c = typedArray.getString(index);
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            h5Var.c = typedArray.getString(index);
                        } else {
                            h5Var.b = typedArray.getResourceId(index, h5Var.b);
                        }
                        break;
                    case 2:
                        h5Var.f7023a = typedArray.getInt(index, h5Var.f7023a);
                        break;
                    case 3:
                        if (typedArray.peekValue(index).type == 3) {
                            h5Var.g = typedArray.getString(index);
                        } else {
                            h5Var.g = t4.c[typedArray.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        h5Var.f = typedArray.getInteger(index, h5Var.f);
                        break;
                    case 5:
                        h5Var.i = typedArray.getInt(index, h5Var.i);
                        break;
                    case 6:
                        h5Var.l = typedArray.getFloat(index, h5Var.l);
                        break;
                    case 7:
                        h5Var.m = typedArray.getFloat(index, h5Var.m);
                        break;
                    case 8:
                        float f = typedArray.getFloat(index, h5Var.k);
                        h5Var.j = f;
                        h5Var.k = f;
                        break;
                    case 9:
                        h5Var.p = typedArray.getInt(index, h5Var.p);
                        break;
                    case 10:
                        h5Var.h = typedArray.getInt(index, h5Var.h);
                        break;
                    case 11:
                        h5Var.j = typedArray.getFloat(index, h5Var.j);
                        break;
                    case 12:
                        h5Var.k = typedArray.getFloat(index, h5Var.k);
                        break;
                    default:
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + f7801a.get(index));
                        break;
                }
            }
            if (h5Var.f7023a == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    @Override // supwisdom.b5
    public void a(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyPosition));
    }

    @Override // supwisdom.b5
    public void a(HashMap<String, q5> map) {
    }
}
