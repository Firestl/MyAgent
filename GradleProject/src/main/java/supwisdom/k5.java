package supwisdom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: KeyTrigger.java */
/* JADX INFO: loaded from: classes.dex */
public class k5 extends b5 {
    public String f = null;
    public int g;
    public String h;
    public String i;
    public int j;
    public int k;
    public View l;
    public float m;
    public boolean n;
    public boolean o;
    public boolean p;
    public float q;
    public Method r;
    public Method s;
    public Method t;
    public float u;
    public boolean v;
    public RectF w;
    public RectF x;

    /* JADX INFO: compiled from: KeyTrigger.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f8124a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f8124a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyTrigger_framePosition, 8);
            f8124a.append(R.styleable.KeyTrigger_onCross, 4);
            f8124a.append(R.styleable.KeyTrigger_onNegativeCross, 1);
            f8124a.append(R.styleable.KeyTrigger_onPositiveCross, 2);
            f8124a.append(R.styleable.KeyTrigger_motionTarget, 7);
            f8124a.append(R.styleable.KeyTrigger_triggerId, 6);
            f8124a.append(R.styleable.KeyTrigger_triggerSlack, 5);
            f8124a.append(R.styleable.KeyTrigger_motion_triggerOnCollision, 9);
            f8124a.append(R.styleable.KeyTrigger_motion_postLayoutCollision, 10);
            f8124a.append(R.styleable.KeyTrigger_triggerReceiver, 11);
        }

        public static void a(k5 k5Var, TypedArray typedArray, Context context) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f8124a.get(index)) {
                    case 1:
                        k5Var.h = typedArray.getString(index);
                        continue;
                        break;
                    case 2:
                        k5Var.i = typedArray.getString(index);
                        continue;
                        break;
                    case 4:
                        k5Var.f = typedArray.getString(index);
                        continue;
                        break;
                    case 5:
                        k5Var.m = typedArray.getFloat(index, k5Var.m);
                        continue;
                        break;
                    case 6:
                        k5Var.j = typedArray.getResourceId(index, k5Var.j);
                        continue;
                        break;
                    case 7:
                        if (MotionLayout.E0) {
                            int resourceId = typedArray.getResourceId(index, k5Var.b);
                            k5Var.b = resourceId;
                            if (resourceId == -1) {
                                k5Var.c = typedArray.getString(index);
                            } else {
                                continue;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            k5Var.c = typedArray.getString(index);
                        } else {
                            k5Var.b = typedArray.getResourceId(index, k5Var.b);
                        }
                        break;
                    case 8:
                        int integer = typedArray.getInteger(index, k5Var.f7023a);
                        k5Var.f7023a = integer;
                        k5Var.q = (integer + 0.5f) / 100.0f;
                        continue;
                        break;
                    case 9:
                        k5Var.k = typedArray.getResourceId(index, k5Var.k);
                        continue;
                        break;
                    case 10:
                        k5Var.v = typedArray.getBoolean(index, k5Var.v);
                        continue;
                        break;
                    case 11:
                        k5Var.g = typedArray.getResourceId(index, k5Var.g);
                        break;
                }
                Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(index) + "   " + f8124a.get(index));
            }
        }
    }

    public k5() {
        int i = b5.f7022e;
        this.g = i;
        this.h = null;
        this.i = null;
        this.j = i;
        this.k = i;
        this.l = null;
        this.m = 0.1f;
        this.n = true;
        this.o = true;
        this.p = true;
        this.q = Float.NaN;
        this.v = false;
        this.w = new RectF();
        this.x = new RectF();
        this.d = new HashMap<>();
    }

    @Override // supwisdom.b5
    public void a(HashMap<String, q5> map) {
    }

    @Override // supwisdom.b5
    public void a(HashSet<String> hashSet) {
    }

    @Override // supwisdom.b5
    public void a(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyTrigger), context);
    }

    public final void a(RectF rectF, View view, boolean z) {
        rectF.top = view.getTop();
        rectF.bottom = view.getBottom();
        rectF.left = view.getLeft();
        rectF.right = view.getRight();
        if (z) {
            view.getMatrix().mapRect(rectF);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(float r11, android.view.View r12) {
        /*
            Method dump skipped, instruction units count: 636
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.k5.a(float, android.view.View):void");
    }
}
