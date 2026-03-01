package supwisdom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R;
import com.taobao.weex.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: MotionScene.java */
/* JADX INFO: loaded from: classes.dex */
public class p5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MotionLayout f8767a;
    public MotionEvent n;
    public MotionLayout.f q;
    public boolean r;
    public float s;
    public float t;
    public g7 b = null;
    public b c = null;
    public boolean d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<b> f8768e = new ArrayList<>();
    public b f = null;
    public ArrayList<b> g = new ArrayList<>();
    public SparseArray<e7> h = new SparseArray<>();
    public HashMap<String, Integer> i = new HashMap<>();
    public SparseIntArray j = new SparseIntArray();
    public boolean k = false;
    public int l = 400;
    public int m = 0;
    public boolean o = false;
    public boolean p = false;

    /* JADX INFO: compiled from: MotionScene.java */
    public class a implements Interpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ t4 f8769a;

        public a(p5 p5Var, t4 t4Var) {
            this.f8769a = t4Var;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return (float) this.f8769a.a(f);
        }
    }

    /* JADX INFO: compiled from: MotionScene.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f8770a;
        public boolean b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f8771e;
        public String f;
        public int g;
        public int h;
        public float i;
        public final p5 j;
        public ArrayList<g5> k;
        public s5 l;
        public ArrayList<a> m;
        public int n;
        public boolean o;
        public int p;
        public int q;
        public int r;

        public b(p5 p5Var, b bVar) {
            this.f8770a = -1;
            this.b = false;
            this.c = -1;
            this.d = -1;
            this.f8771e = 0;
            this.f = null;
            this.g = -1;
            this.h = 400;
            this.i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.j = p5Var;
            if (bVar != null) {
                this.p = bVar.p;
                this.f8771e = bVar.f8771e;
                this.f = bVar.f;
                this.g = bVar.g;
                this.h = bVar.h;
                this.k = bVar.k;
                this.i = bVar.i;
                this.q = bVar.q;
            }
        }

        public int c() {
            return this.c;
        }

        public int d() {
            return this.q;
        }

        public int e() {
            return this.d;
        }

        public s5 f() {
            return this.l;
        }

        public boolean g() {
            return !this.o;
        }

        public void b(int i) {
            this.h = i;
        }

        public void a(Context context, XmlPullParser xmlPullParser) {
            this.m.add(new a(context, this, xmlPullParser));
        }

        public int b() {
            return this.h;
        }

        public int a() {
            return this.n;
        }

        public String a(Context context) {
            String resourceEntryName = this.d == -1 ? com.igexin.push.core.b.m : context.getResources().getResourceEntryName(this.d);
            if (this.c == -1) {
                return resourceEntryName + " -> null";
            }
            return resourceEntryName + " -> " + context.getResources().getResourceEntryName(this.c);
        }

        /* JADX INFO: compiled from: MotionScene.java */
        public static class a implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final b f8772a;
            public int b;
            public int c;

            public a(Context context, b bVar, XmlPullParser xmlPullParser) {
                this.b = -1;
                this.c = 17;
                this.f8772a = bVar;
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.OnClick);
                int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
                for (int i = 0; i < indexCount; i++) {
                    int index = typedArrayObtainStyledAttributes.getIndex(i);
                    if (index == R.styleable.OnClick_targetId) {
                        this.b = typedArrayObtainStyledAttributes.getResourceId(index, this.b);
                    } else if (index == R.styleable.OnClick_clickAction) {
                        this.c = typedArrayObtainStyledAttributes.getInt(index, this.c);
                    }
                }
                typedArrayObtainStyledAttributes.recycle();
            }

            public void a(MotionLayout motionLayout, int i, b bVar) {
                int i2 = this.b;
                View viewFindViewById = motionLayout;
                if (i2 != -1) {
                    viewFindViewById = motionLayout.findViewById(i2);
                }
                if (viewFindViewById == null) {
                    Log.e("MotionScene", "OnClick could not find id " + this.b);
                    return;
                }
                int i3 = bVar.d;
                int i4 = bVar.c;
                if (i3 == -1) {
                    viewFindViewById.setOnClickListener(this);
                    return;
                }
                if ((((this.c & 1) != 0 && i == i3) | ((this.c & 1) != 0 && i == i3) | ((this.c & 256) != 0 && i == i3) | ((this.c & 16) != 0 && i == i4)) || ((this.c & 4096) != 0 && i == i4)) {
                    viewFindViewById.setOnClickListener(this);
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MotionLayout motionLayout = this.f8772a.j.f8767a;
                if (motionLayout.j()) {
                    if (this.f8772a.d == -1) {
                        int currentState = motionLayout.getCurrentState();
                        if (currentState == -1) {
                            motionLayout.e(this.f8772a.c);
                            return;
                        }
                        b bVar = new b(this.f8772a.j, this.f8772a);
                        bVar.d = currentState;
                        bVar.c = this.f8772a.c;
                        motionLayout.setTransition(bVar);
                        motionLayout.p();
                        return;
                    }
                    b bVar2 = this.f8772a.j.c;
                    int i = this.c;
                    boolean z = false;
                    boolean z2 = ((i & 1) == 0 && (i & 256) == 0) ? false : true;
                    int i2 = this.c;
                    boolean z3 = ((i2 & 16) == 0 && (i2 & 4096) == 0) ? false : true;
                    if (z2 && z3) {
                        b bVar3 = this.f8772a.j.c;
                        b bVar4 = this.f8772a;
                        if (bVar3 != bVar4) {
                            motionLayout.setTransition(bVar4);
                        }
                        if (motionLayout.getCurrentState() != motionLayout.getEndState() && motionLayout.getProgress() <= 0.5f) {
                            z = z2;
                            z3 = false;
                        }
                    } else {
                        z = z2;
                    }
                    if (a(bVar2, motionLayout)) {
                        if (z && (this.c & 1) != 0) {
                            motionLayout.setTransition(this.f8772a);
                            motionLayout.p();
                            return;
                        }
                        if (z3 && (this.c & 16) != 0) {
                            motionLayout.setTransition(this.f8772a);
                            motionLayout.q();
                        } else if (z && (this.c & 256) != 0) {
                            motionLayout.setTransition(this.f8772a);
                            motionLayout.setProgress(1.0f);
                        } else {
                            if (!z3 || (this.c & 4096) == 0) {
                                return;
                            }
                            motionLayout.setTransition(this.f8772a);
                            motionLayout.setProgress(0.0f);
                        }
                    }
                }
            }

            public void a(MotionLayout motionLayout) {
                int i = this.b;
                if (i == -1) {
                    return;
                }
                View viewFindViewById = motionLayout.findViewById(i);
                if (viewFindViewById == null) {
                    Log.e("MotionScene", " (*)  could not find id " + this.b);
                    return;
                }
                viewFindViewById.setOnClickListener(null);
            }

            public boolean a(b bVar, MotionLayout motionLayout) {
                b bVar2 = this.f8772a;
                if (bVar2 == bVar) {
                    return true;
                }
                int i = bVar2.c;
                int i2 = this.f8772a.d;
                if (i2 == -1) {
                    return motionLayout.x != i;
                }
                int i3 = motionLayout.x;
                return i3 == i2 || i3 == i;
            }
        }

        public boolean a(int i) {
            return (i & this.r) != 0;
        }

        public final void a(p5 p5Var, Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Transition);
            a(p5Var, context, typedArrayObtainStyledAttributes);
            typedArrayObtainStyledAttributes.recycle();
        }

        public final void a(p5 p5Var, Context context, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                if (index == R.styleable.Transition_constraintSetEnd) {
                    this.c = typedArray.getResourceId(index, this.c);
                    if (Constants.Name.LAYOUT.equals(context.getResources().getResourceTypeName(this.c))) {
                        e7 e7Var = new e7();
                        e7Var.b(context, this.c);
                        p5Var.h.append(this.c, e7Var);
                    }
                } else if (index == R.styleable.Transition_constraintSetStart) {
                    this.d = typedArray.getResourceId(index, this.d);
                    if (Constants.Name.LAYOUT.equals(context.getResources().getResourceTypeName(this.d))) {
                        e7 e7Var2 = new e7();
                        e7Var2.b(context, this.d);
                        p5Var.h.append(this.d, e7Var2);
                    }
                } else if (index == R.styleable.Transition_motionInterpolator) {
                    int i2 = typedArray.peekValue(index).type;
                    if (i2 == 1) {
                        int resourceId = typedArray.getResourceId(index, -1);
                        this.g = resourceId;
                        if (resourceId != -1) {
                            this.f8771e = -2;
                        }
                    } else if (i2 == 3) {
                        String string = typedArray.getString(index);
                        this.f = string;
                        if (string.indexOf("/") > 0) {
                            this.g = typedArray.getResourceId(index, -1);
                            this.f8771e = -2;
                        } else {
                            this.f8771e = -1;
                        }
                    } else {
                        this.f8771e = typedArray.getInteger(index, this.f8771e);
                    }
                } else if (index == R.styleable.Transition_duration) {
                    this.h = typedArray.getInt(index, this.h);
                } else if (index == R.styleable.Transition_staggered) {
                    this.i = typedArray.getFloat(index, this.i);
                } else if (index == R.styleable.Transition_autoTransition) {
                    this.n = typedArray.getInteger(index, this.n);
                } else if (index == R.styleable.Transition_android_id) {
                    this.f8770a = typedArray.getResourceId(index, this.f8770a);
                } else if (index == R.styleable.Transition_transitionDisable) {
                    this.o = typedArray.getBoolean(index, this.o);
                } else if (index == R.styleable.Transition_pathMotionArc) {
                    this.p = typedArray.getInteger(index, -1);
                } else if (index == R.styleable.Transition_layoutDuringTransition) {
                    this.q = typedArray.getInteger(index, 0);
                } else if (index == R.styleable.Transition_transitionFlags) {
                    this.r = typedArray.getInteger(index, 0);
                }
            }
            if (this.d == -1) {
                this.b = true;
            }
        }

        public b(p5 p5Var, Context context, XmlPullParser xmlPullParser) {
            this.f8770a = -1;
            this.b = false;
            this.c = -1;
            this.d = -1;
            this.f8771e = 0;
            this.f = null;
            this.g = -1;
            this.h = 400;
            this.i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.h = p5Var.l;
            this.q = p5Var.m;
            this.j = p5Var;
            a(p5Var, context, Xml.asAttributeSet(xmlPullParser));
        }
    }

    public p5(Context context, MotionLayout motionLayout, int i) {
        this.f8767a = motionLayout;
        a(context, i);
        this.h.put(R.id.motion_base, new e7());
        this.i.put("motion_base", Integer.valueOf(R.id.motion_base));
    }

    public int e() {
        b bVar = this.c;
        if (bVar == null) {
            return -1;
        }
        return bVar.c;
    }

    public Interpolator f() {
        int i = this.c.f8771e;
        if (i == -2) {
            return AnimationUtils.loadInterpolator(this.f8767a.getContext(), this.c.g);
        }
        if (i == -1) {
            return new a(this, t4.a(this.c.f));
        }
        if (i == 0) {
            return new AccelerateDecelerateInterpolator();
        }
        if (i == 1) {
            return new AccelerateInterpolator();
        }
        if (i == 2) {
            return new DecelerateInterpolator();
        }
        if (i == 4) {
            return new AnticipateInterpolator();
        }
        if (i != 5) {
            return null;
        }
        return new BounceInterpolator();
    }

    public void g(int i) {
        b bVar = this.c;
        if (bVar != null) {
            bVar.b(i);
        } else {
            this.l = i;
        }
    }

    public float h() {
        b bVar = this.c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.c.l.c();
    }

    public boolean i() {
        b bVar = this.c;
        if (bVar == null || bVar.l == null) {
            return false;
        }
        return this.c.l.d();
    }

    public float j() {
        b bVar = this.c;
        if (bVar != null) {
            return bVar.i;
        }
        return 0.0f;
    }

    public int k() {
        b bVar = this.c;
        if (bVar == null) {
            return -1;
        }
        return bVar.d;
    }

    public final boolean l() {
        return this.q != null;
    }

    public void m() {
        b bVar = this.c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.c.l.f();
    }

    public boolean n() {
        Iterator<b> it = this.f8768e.iterator();
        while (it.hasNext()) {
            if (it.next().l != null) {
                return true;
            }
        }
        b bVar = this.c;
        return (bVar == null || bVar.l == null) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(int r7, int r8) {
        /*
            r6 = this;
            supwisdom.g7 r0 = r6.b
            r1 = -1
            if (r0 == 0) goto L16
            int r0 = r0.a(r7, r1, r1)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r7
        Ld:
            supwisdom.g7 r2 = r6.b
            int r2 = r2.a(r8, r1, r1)
            if (r2 == r1) goto L17
            goto L18
        L16:
            r0 = r7
        L17:
            r2 = r8
        L18:
            java.util.ArrayList<supwisdom.p5$b> r3 = r6.f8768e
            java.util.Iterator r3 = r3.iterator()
        L1e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L58
            java.lang.Object r4 = r3.next()
            supwisdom.p5$b r4 = (supwisdom.p5.b) r4
            int r5 = supwisdom.p5.b.a(r4)
            if (r5 != r2) goto L36
            int r5 = supwisdom.p5.b.b(r4)
            if (r5 == r0) goto L42
        L36:
            int r5 = supwisdom.p5.b.a(r4)
            if (r5 != r8) goto L1e
            int r5 = supwisdom.p5.b.b(r4)
            if (r5 != r7) goto L1e
        L42:
            r6.c = r4
            if (r4 == 0) goto L57
            supwisdom.s5 r7 = supwisdom.p5.b.k(r4)
            if (r7 == 0) goto L57
            supwisdom.p5$b r7 = r6.c
            supwisdom.s5 r7 = supwisdom.p5.b.k(r7)
            boolean r8 = r6.r
            r7.a(r8)
        L57:
            return
        L58:
            supwisdom.p5$b r7 = r6.f
            java.util.ArrayList<supwisdom.p5$b> r3 = r6.g
            java.util.Iterator r3 = r3.iterator()
        L60:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L74
            java.lang.Object r4 = r3.next()
            supwisdom.p5$b r4 = (supwisdom.p5.b) r4
            int r5 = supwisdom.p5.b.a(r4)
            if (r5 != r8) goto L60
            r7 = r4
            goto L60
        L74:
            supwisdom.p5$b r8 = new supwisdom.p5$b
            r8.<init>(r6, r7)
            supwisdom.p5.b.b(r8, r0)
            supwisdom.p5.b.a(r8, r2)
            if (r0 == r1) goto L86
            java.util.ArrayList<supwisdom.p5$b> r7 = r6.f8768e
            r7.add(r8)
        L86:
            r6.c = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.p5.a(int, int):void");
    }

    public final int b(int i) {
        int iA;
        g7 g7Var = this.b;
        return (g7Var == null || (iA = g7Var.a(i, -1, -1)) == -1) ? i : iA;
    }

    public ArrayList<b> c() {
        return this.f8768e;
    }

    public List<b> d(int i) {
        int iB = b(i);
        ArrayList arrayList = new ArrayList();
        for (b bVar : this.f8768e) {
            if (bVar.d == iB || bVar.c == iB) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    public b c(int i) {
        for (b bVar : this.f8768e) {
            if (bVar.f8770a == i) {
                return bVar;
            }
        }
        return null;
    }

    public final boolean e(int i) {
        int i2 = this.j.get(i);
        int size = this.j.size();
        while (i2 > 0) {
            if (i2 == i) {
                return true;
            }
            int i3 = size - 1;
            if (size < 0) {
                return true;
            }
            i2 = this.j.get(i2);
            size = i3;
        }
        return false;
    }

    public int[] b() {
        int size = this.h.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = this.h.keyAt(i);
        }
        return iArr;
    }

    public float g() {
        b bVar = this.c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.c.l.b();
    }

    public void c(float f, float f2) {
        b bVar = this.c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.c.l.d(f, f2);
    }

    public boolean b(MotionLayout motionLayout, int i) {
        if (l() || this.d) {
            return false;
        }
        for (b bVar : this.f8768e) {
            if (bVar.n != 0 && this.c != bVar) {
                if (i != bVar.d || (bVar.n != 4 && bVar.n != 2)) {
                    if (i == bVar.c && (bVar.n == 3 || bVar.n == 1)) {
                        motionLayout.setState(MotionLayout.j.FINISHED);
                        motionLayout.setTransition(bVar);
                        if (bVar.n == 3) {
                            motionLayout.q();
                            motionLayout.setState(MotionLayout.j.SETUP);
                            motionLayout.setState(MotionLayout.j.MOVING);
                        } else {
                            motionLayout.setProgress(0.0f);
                            motionLayout.a(true);
                            motionLayout.setState(MotionLayout.j.SETUP);
                            motionLayout.setState(MotionLayout.j.MOVING);
                            motionLayout.setState(MotionLayout.j.FINISHED);
                            motionLayout.l();
                        }
                        return true;
                    }
                } else {
                    motionLayout.setState(MotionLayout.j.FINISHED);
                    motionLayout.setTransition(bVar);
                    if (bVar.n == 4) {
                        motionLayout.p();
                        motionLayout.setState(MotionLayout.j.SETUP);
                        motionLayout.setState(MotionLayout.j.MOVING);
                    } else {
                        motionLayout.setProgress(1.0f);
                        motionLayout.a(true);
                        motionLayout.setState(MotionLayout.j.SETUP);
                        motionLayout.setState(MotionLayout.j.MOVING);
                        motionLayout.setState(MotionLayout.j.FINISHED);
                        motionLayout.l();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public int d() {
        b bVar = this.c;
        if (bVar != null) {
            return bVar.h;
        }
        return this.l;
    }

    public final void f(int i) {
        int i2 = this.j.get(i);
        if (i2 > 0) {
            f(this.j.get(i));
            e7 e7Var = this.h.get(i);
            e7 e7Var2 = this.h.get(i2);
            if (e7Var2 == null) {
                Log.e("MotionScene", "ERROR! invalid deriveConstraintsFrom: @id/" + z4.a(this.f8767a.getContext(), i2));
                return;
            }
            e7Var.a(e7Var2);
            this.j.put(i, -1);
        }
    }

    public void a(b bVar) {
        this.c = bVar;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.c.l.a(this.r);
    }

    public void a(MotionLayout motionLayout, int i) {
        for (b bVar : this.f8768e) {
            if (bVar.m.size() > 0) {
                Iterator it = bVar.m.iterator();
                while (it.hasNext()) {
                    ((b.a) it.next()).a(motionLayout);
                }
            }
        }
        for (b bVar2 : this.g) {
            if (bVar2.m.size() > 0) {
                Iterator it2 = bVar2.m.iterator();
                while (it2.hasNext()) {
                    ((b.a) it2.next()).a(motionLayout);
                }
            }
        }
        for (b bVar3 : this.f8768e) {
            if (bVar3.m.size() > 0) {
                Iterator it3 = bVar3.m.iterator();
                while (it3.hasNext()) {
                    ((b.a) it3.next()).a(motionLayout, i, bVar3);
                }
            }
        }
        for (b bVar4 : this.g) {
            if (bVar4.m.size() > 0) {
                Iterator it4 = bVar4.m.iterator();
                while (it4.hasNext()) {
                    ((b.a) it4.next()).a(motionLayout, i, bVar4);
                }
            }
        }
    }

    public b a(int i, float f, float f2, MotionEvent motionEvent) {
        if (i != -1) {
            List<b> listD = d(i);
            float f3 = 0.0f;
            b bVar = null;
            RectF rectF = new RectF();
            for (b bVar2 : listD) {
                if (!bVar2.o && bVar2.l != null) {
                    bVar2.l.a(this.r);
                    RectF rectFB = bVar2.l.b(this.f8767a, rectF);
                    if (rectFB == null || motionEvent == null || rectFB.contains(motionEvent.getX(), motionEvent.getY())) {
                        RectF rectFB2 = bVar2.l.b(this.f8767a, rectF);
                        if (rectFB2 == null || motionEvent == null || rectFB2.contains(motionEvent.getX(), motionEvent.getY())) {
                            float fA = bVar2.l.a(f, f2) * (bVar2.c == i ? -1.0f : 1.1f);
                            if (fA > f3) {
                                bVar = bVar2;
                                f3 = fA;
                            }
                        }
                    }
                }
            }
            return bVar;
        }
        return this.c;
    }

    public final void b(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.MotionScene);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == R.styleable.MotionScene_defaultDuration) {
                this.l = typedArrayObtainStyledAttributes.getInt(index, this.l);
            } else if (index == R.styleable.MotionScene_layoutDuringTransition) {
                this.m = typedArrayObtainStyledAttributes.getInteger(index, 0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public void b(float f, float f2) {
        b bVar = this.c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.c.l.c(f, f2);
    }

    public void a(boolean z) {
        this.r = z;
        b bVar = this.c;
        if (bVar == null || bVar.l == null) {
            return;
        }
        this.c.l.a(this.r);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(android.content.Context r9, int r10) {
        /*
            Method dump skipped, instruction units count: 388
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.p5.a(android.content.Context, int):void");
    }

    public final int a(Context context, String str) {
        int identifier;
        if (str.contains("/")) {
            identifier = context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), "id", context.getPackageName());
            if (this.k) {
                System.out.println("id getMap res = " + identifier);
            }
        } else {
            identifier = -1;
        }
        if (identifier != -1) {
            return identifier;
        }
        if (str != null && str.length() > 1) {
            return Integer.parseInt(str.substring(1));
        }
        Log.e("MotionScene", "error in parsing id");
        return identifier;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(android.content.Context r14, org.xmlpull.v1.XmlPullParser r15) {
        /*
            r13 = this;
            supwisdom.e7 r0 = new supwisdom.e7
            r0.<init>()
            r1 = 0
            r0.a(r1)
            int r2 = r15.getAttributeCount()
            r3 = -1
            r4 = 0
            r5 = -1
            r6 = -1
        L11:
            r7 = 1
            if (r4 >= r2) goto L77
            java.lang.String r8 = r15.getAttributeName(r4)
            java.lang.String r9 = r15.getAttributeValue(r4)
            boolean r10 = r13.k
            if (r10 == 0) goto L36
            java.io.PrintStream r10 = java.lang.System.out
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "id string = "
            r11.append(r12)
            r11.append(r9)
            java.lang.String r11 = r11.toString()
            r10.println(r11)
        L36:
            int r10 = r8.hashCode()
            r11 = -1496482599(0xffffffffa6cd7cd9, float:-1.4258573E-15)
            if (r10 == r11) goto L4e
            r11 = 3355(0xd1b, float:4.701E-42)
            if (r10 == r11) goto L44
            goto L58
        L44:
            java.lang.String r10 = "id"
            boolean r8 = r8.equals(r10)
            if (r8 == 0) goto L58
            r8 = 0
            goto L59
        L4e:
            java.lang.String r10 = "deriveConstraintsFrom"
            boolean r8 = r8.equals(r10)
            if (r8 == 0) goto L58
            r8 = 1
            goto L59
        L58:
            r8 = -1
        L59:
            if (r8 == 0) goto L63
            if (r8 == r7) goto L5e
            goto L74
        L5e:
            int r6 = r13.a(r14, r9)
            goto L74
        L63:
            int r5 = r13.a(r14, r9)
            java.util.HashMap<java.lang.String, java.lang.Integer> r7 = r13.i
            java.lang.String r8 = a(r9)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r5)
            r7.put(r8, r9)
        L74:
            int r4 = r4 + 1
            goto L11
        L77:
            if (r5 == r3) goto L91
            androidx.constraintlayout.motion.widget.MotionLayout r1 = r13.f8767a
            int r1 = r1.O
            if (r1 == 0) goto L82
            r0.b(r7)
        L82:
            r0.a(r14, r15)
            if (r6 == r3) goto L8c
            android.util.SparseIntArray r14 = r13.j
            r14.put(r5, r6)
        L8c:
            android.util.SparseArray<supwisdom.e7> r14 = r13.h
            r14.put(r5, r0)
        L91:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.p5.a(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public e7 a(int i) {
        return a(i, -1, -1);
    }

    public e7 a(int i, int i2, int i3) {
        int iA;
        if (this.k) {
            System.out.println("id " + i);
            System.out.println("size " + this.h.size());
        }
        g7 g7Var = this.b;
        if (g7Var != null && (iA = g7Var.a(i, i2, i3)) != -1) {
            i = iA;
        }
        if (this.h.get(i) == null) {
            Log.e("MotionScene", "Warning could not find ConstraintSet id/" + z4.a(this.f8767a.getContext(), i) + " In MotionScene");
            SparseArray<e7> sparseArray = this.h;
            return sparseArray.get(sparseArray.keyAt(0));
        }
        return this.h.get(i);
    }

    public void a(m5 m5Var) {
        b bVar = this.c;
        if (bVar != null) {
            Iterator it = bVar.k.iterator();
            while (it.hasNext()) {
                ((g5) it.next()).a(m5Var);
            }
        } else {
            b bVar2 = this.f;
            if (bVar2 != null) {
                Iterator it2 = bVar2.k.iterator();
                while (it2.hasNext()) {
                    ((g5) it2.next()).a(m5Var);
                }
            }
        }
    }

    public void a(MotionEvent motionEvent, int i, MotionLayout motionLayout) {
        MotionLayout.f fVar;
        MotionEvent motionEvent2;
        RectF rectF = new RectF();
        if (this.q == null) {
            this.q = this.f8767a.k();
        }
        this.q.a(motionEvent);
        if (i != -1) {
            int action = motionEvent.getAction();
            boolean z = false;
            if (action != 0) {
                if (action == 2 && !this.o) {
                    float rawY = motionEvent.getRawY() - this.t;
                    float rawX = motionEvent.getRawX() - this.s;
                    if ((rawX == 0.0d && rawY == 0.0d) || (motionEvent2 = this.n) == null) {
                        return;
                    }
                    b bVarA = a(i, rawX, rawY, motionEvent2);
                    if (bVarA != null) {
                        motionLayout.setTransition(bVarA);
                        RectF rectFB = this.c.l.b(this.f8767a, rectF);
                        if (rectFB != null && !rectFB.contains(this.n.getX(), this.n.getY())) {
                            z = true;
                        }
                        this.p = z;
                        this.c.l.f(this.s, this.t);
                    }
                }
            } else {
                this.s = motionEvent.getRawX();
                this.t = motionEvent.getRawY();
                this.n = motionEvent;
                this.o = false;
                if (this.c.l != null) {
                    RectF rectFA = this.c.l.a(this.f8767a, rectF);
                    if (rectFA == null || rectFA.contains(this.n.getX(), this.n.getY())) {
                        RectF rectFB2 = this.c.l.b(this.f8767a, rectF);
                        if (rectFB2 != null && !rectFB2.contains(this.n.getX(), this.n.getY())) {
                            this.p = true;
                        } else {
                            this.p = false;
                        }
                        this.c.l.e(this.s, this.t);
                        return;
                    }
                    this.n = null;
                    this.o = true;
                    return;
                }
                return;
            }
        }
        if (this.o) {
            return;
        }
        b bVar = this.c;
        if (bVar != null && bVar.l != null && !this.p) {
            this.c.l.a(motionEvent, this.q, i, this);
        }
        this.s = motionEvent.getRawX();
        this.t = motionEvent.getRawY();
        if (motionEvent.getAction() != 1 || (fVar = this.q) == null) {
            return;
        }
        fVar.recycle();
        this.q = null;
        int i2 = motionLayout.x;
        if (i2 != -1) {
            b(motionLayout, i2);
        }
    }

    public float a(float f, float f2) {
        b bVar = this.c;
        if (bVar == null || bVar.l == null) {
            return 0.0f;
        }
        return this.c.l.b(f, f2);
    }

    public int a() {
        b bVar = this.c;
        if (bVar != null) {
            return bVar.p;
        }
        return -1;
    }

    public void a(MotionLayout motionLayout) {
        for (int i = 0; i < this.h.size(); i++) {
            int iKeyAt = this.h.keyAt(i);
            if (e(iKeyAt)) {
                Log.e("MotionScene", "Cannot be derived from yourself");
                return;
            }
            f(iKeyAt);
        }
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            this.h.valueAt(i2).d(motionLayout);
        }
    }

    public static String a(String str) {
        if (str == null) {
            return "";
        }
        int iIndexOf = str.indexOf(47);
        return iIndexOf < 0 ? str : str.substring(iIndexOf + 1);
    }
}
