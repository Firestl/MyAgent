package supwisdom;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.Guideline;
import androidx.constraintlayout.widget.R;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConstraintSet.java */
/* JADX INFO: loaded from: classes.dex */
public class e7 {
    public static final int[] d = {0, 4, 8};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static SparseIntArray f7430e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<String, ConstraintAttribute> f7431a = new HashMap<>();
    public boolean b = true;
    public HashMap<Integer, a> c = new HashMap<>();

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f7432a;
        public final d b = new d();
        public final c c = new c();
        public final b d = new b();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final e f7433e = new e();
        public HashMap<String, ConstraintAttribute> f = new HashMap<>();

        public a clone() {
            a aVar = new a();
            aVar.d.a(this.d);
            aVar.c.a(this.c);
            aVar.b.a(this.b);
            aVar.f7433e.a(this.f7433e);
            aVar.f7432a = this.f7432a;
            return aVar;
        }

        public final void a(ConstraintHelper constraintHelper, int i, Constraints.LayoutParams layoutParams) {
            a(i, layoutParams);
            if (constraintHelper instanceof Barrier) {
                b bVar = this.d;
                bVar.d0 = 1;
                Barrier barrier = (Barrier) constraintHelper;
                bVar.b0 = barrier.getType();
                this.d.e0 = barrier.getReferencedIds();
                this.d.c0 = barrier.getMargin();
            }
        }

        public final void a(int i, Constraints.LayoutParams layoutParams) {
            a(i, (ConstraintLayout.LayoutParams) layoutParams);
            this.b.d = layoutParams.o0;
            e eVar = this.f7433e;
            eVar.b = layoutParams.r0;
            eVar.c = layoutParams.s0;
            eVar.d = layoutParams.t0;
            eVar.f7441e = layoutParams.u0;
            eVar.f = layoutParams.v0;
            eVar.g = layoutParams.w0;
            eVar.h = layoutParams.x0;
            eVar.i = layoutParams.y0;
            eVar.j = layoutParams.z0;
            eVar.k = layoutParams.A0;
            eVar.m = layoutParams.q0;
            eVar.l = layoutParams.p0;
        }

        public final void a(int i, ConstraintLayout.LayoutParams layoutParams) {
            this.f7432a = i;
            b bVar = this.d;
            bVar.h = layoutParams.d;
            bVar.i = layoutParams.f1248e;
            bVar.j = layoutParams.f;
            bVar.k = layoutParams.g;
            bVar.l = layoutParams.h;
            bVar.m = layoutParams.i;
            bVar.n = layoutParams.j;
            bVar.o = layoutParams.k;
            bVar.p = layoutParams.l;
            bVar.q = layoutParams.p;
            bVar.r = layoutParams.q;
            bVar.s = layoutParams.r;
            bVar.t = layoutParams.s;
            bVar.u = layoutParams.z;
            bVar.v = layoutParams.A;
            bVar.w = layoutParams.B;
            bVar.x = layoutParams.m;
            bVar.y = layoutParams.n;
            bVar.z = layoutParams.o;
            bVar.A = layoutParams.P;
            bVar.B = layoutParams.Q;
            bVar.C = layoutParams.R;
            bVar.g = layoutParams.c;
            bVar.f7435e = layoutParams.f1247a;
            bVar.f = layoutParams.b;
            bVar.c = ((ViewGroup.MarginLayoutParams) layoutParams).width;
            bVar.d = ((ViewGroup.MarginLayoutParams) layoutParams).height;
            bVar.D = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            bVar.E = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            bVar.F = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            bVar.G = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            bVar.P = layoutParams.E;
            bVar.Q = layoutParams.D;
            bVar.S = layoutParams.G;
            bVar.R = layoutParams.F;
            bVar.h0 = layoutParams.S;
            bVar.i0 = layoutParams.T;
            bVar.T = layoutParams.H;
            bVar.U = layoutParams.I;
            bVar.V = layoutParams.L;
            bVar.W = layoutParams.M;
            bVar.X = layoutParams.J;
            bVar.Y = layoutParams.K;
            bVar.Z = layoutParams.N;
            bVar.a0 = layoutParams.O;
            bVar.g0 = layoutParams.U;
            bVar.K = layoutParams.u;
            bVar.M = layoutParams.w;
            bVar.J = layoutParams.t;
            bVar.L = layoutParams.v;
            bVar.O = layoutParams.x;
            bVar.N = layoutParams.y;
            if (Build.VERSION.SDK_INT >= 17) {
                bVar.H = layoutParams.getMarginEnd();
                this.d.I = layoutParams.getMarginStart();
            }
        }

        public void a(ConstraintLayout.LayoutParams layoutParams) {
            b bVar = this.d;
            layoutParams.d = bVar.h;
            layoutParams.f1248e = bVar.i;
            layoutParams.f = bVar.j;
            layoutParams.g = bVar.k;
            layoutParams.h = bVar.l;
            layoutParams.i = bVar.m;
            layoutParams.j = bVar.n;
            layoutParams.k = bVar.o;
            layoutParams.l = bVar.p;
            layoutParams.p = bVar.q;
            layoutParams.q = bVar.r;
            layoutParams.r = bVar.s;
            layoutParams.s = bVar.t;
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = bVar.D;
            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = bVar.E;
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = bVar.F;
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = bVar.G;
            layoutParams.x = bVar.O;
            layoutParams.y = bVar.N;
            layoutParams.u = bVar.K;
            layoutParams.w = bVar.M;
            layoutParams.z = bVar.u;
            layoutParams.A = bVar.v;
            layoutParams.m = bVar.x;
            layoutParams.n = bVar.y;
            layoutParams.o = bVar.z;
            layoutParams.B = bVar.w;
            layoutParams.P = bVar.A;
            layoutParams.Q = bVar.B;
            layoutParams.E = bVar.P;
            layoutParams.D = bVar.Q;
            layoutParams.G = bVar.S;
            layoutParams.F = bVar.R;
            layoutParams.S = bVar.h0;
            layoutParams.T = bVar.i0;
            layoutParams.H = bVar.T;
            layoutParams.I = bVar.U;
            layoutParams.L = bVar.V;
            layoutParams.M = bVar.W;
            layoutParams.J = bVar.X;
            layoutParams.K = bVar.Y;
            layoutParams.N = bVar.Z;
            layoutParams.O = bVar.a0;
            layoutParams.R = bVar.C;
            layoutParams.c = bVar.g;
            layoutParams.f1247a = bVar.f7435e;
            layoutParams.b = bVar.f;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = bVar.c;
            ((ViewGroup.MarginLayoutParams) layoutParams).height = bVar.d;
            String str = bVar.g0;
            if (str != null) {
                layoutParams.U = str;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                layoutParams.setMarginStart(this.d.I);
                layoutParams.setMarginEnd(this.d.H);
            }
            layoutParams.c();
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f7430e = sparseIntArray;
        sparseIntArray.append(R.styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        f7430e.append(R.styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        f7430e.append(R.styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        f7430e.append(R.styleable.Constraint_layout_constraintRight_toRightOf, 30);
        f7430e.append(R.styleable.Constraint_layout_constraintTop_toTopOf, 36);
        f7430e.append(R.styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        f7430e.append(R.styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        f7430e.append(R.styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        f7430e.append(R.styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        f7430e.append(R.styleable.Constraint_layout_editor_absoluteX, 6);
        f7430e.append(R.styleable.Constraint_layout_editor_absoluteY, 7);
        f7430e.append(R.styleable.Constraint_layout_constraintGuide_begin, 17);
        f7430e.append(R.styleable.Constraint_layout_constraintGuide_end, 18);
        f7430e.append(R.styleable.Constraint_layout_constraintGuide_percent, 19);
        f7430e.append(R.styleable.Constraint_android_orientation, 27);
        f7430e.append(R.styleable.Constraint_layout_constraintStart_toEndOf, 32);
        f7430e.append(R.styleable.Constraint_layout_constraintStart_toStartOf, 33);
        f7430e.append(R.styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        f7430e.append(R.styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        f7430e.append(R.styleable.Constraint_layout_goneMarginLeft, 13);
        f7430e.append(R.styleable.Constraint_layout_goneMarginTop, 16);
        f7430e.append(R.styleable.Constraint_layout_goneMarginRight, 14);
        f7430e.append(R.styleable.Constraint_layout_goneMarginBottom, 11);
        f7430e.append(R.styleable.Constraint_layout_goneMarginStart, 15);
        f7430e.append(R.styleable.Constraint_layout_goneMarginEnd, 12);
        f7430e.append(R.styleable.Constraint_layout_constraintVertical_weight, 40);
        f7430e.append(R.styleable.Constraint_layout_constraintHorizontal_weight, 39);
        f7430e.append(R.styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        f7430e.append(R.styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        f7430e.append(R.styleable.Constraint_layout_constraintHorizontal_bias, 20);
        f7430e.append(R.styleable.Constraint_layout_constraintVertical_bias, 37);
        f7430e.append(R.styleable.Constraint_layout_constraintDimensionRatio, 5);
        f7430e.append(R.styleable.Constraint_layout_constraintLeft_creator, 82);
        f7430e.append(R.styleable.Constraint_layout_constraintTop_creator, 82);
        f7430e.append(R.styleable.Constraint_layout_constraintRight_creator, 82);
        f7430e.append(R.styleable.Constraint_layout_constraintBottom_creator, 82);
        f7430e.append(R.styleable.Constraint_layout_constraintBaseline_creator, 82);
        f7430e.append(R.styleable.Constraint_android_layout_marginLeft, 24);
        f7430e.append(R.styleable.Constraint_android_layout_marginRight, 28);
        f7430e.append(R.styleable.Constraint_android_layout_marginStart, 31);
        f7430e.append(R.styleable.Constraint_android_layout_marginEnd, 8);
        f7430e.append(R.styleable.Constraint_android_layout_marginTop, 34);
        f7430e.append(R.styleable.Constraint_android_layout_marginBottom, 2);
        f7430e.append(R.styleable.Constraint_android_layout_width, 23);
        f7430e.append(R.styleable.Constraint_android_layout_height, 21);
        f7430e.append(R.styleable.Constraint_android_visibility, 22);
        f7430e.append(R.styleable.Constraint_android_alpha, 43);
        f7430e.append(R.styleable.Constraint_android_elevation, 44);
        f7430e.append(R.styleable.Constraint_android_rotationX, 45);
        f7430e.append(R.styleable.Constraint_android_rotationY, 46);
        f7430e.append(R.styleable.Constraint_android_rotation, 60);
        f7430e.append(R.styleable.Constraint_android_scaleX, 47);
        f7430e.append(R.styleable.Constraint_android_scaleY, 48);
        f7430e.append(R.styleable.Constraint_android_transformPivotX, 49);
        f7430e.append(R.styleable.Constraint_android_transformPivotY, 50);
        f7430e.append(R.styleable.Constraint_android_translationX, 51);
        f7430e.append(R.styleable.Constraint_android_translationY, 52);
        f7430e.append(R.styleable.Constraint_android_translationZ, 53);
        f7430e.append(R.styleable.Constraint_layout_constraintWidth_default, 54);
        f7430e.append(R.styleable.Constraint_layout_constraintHeight_default, 55);
        f7430e.append(R.styleable.Constraint_layout_constraintWidth_max, 56);
        f7430e.append(R.styleable.Constraint_layout_constraintHeight_max, 57);
        f7430e.append(R.styleable.Constraint_layout_constraintWidth_min, 58);
        f7430e.append(R.styleable.Constraint_layout_constraintHeight_min, 59);
        f7430e.append(R.styleable.Constraint_layout_constraintCircle, 61);
        f7430e.append(R.styleable.Constraint_layout_constraintCircleRadius, 62);
        f7430e.append(R.styleable.Constraint_layout_constraintCircleAngle, 63);
        f7430e.append(R.styleable.Constraint_animate_relativeTo, 64);
        f7430e.append(R.styleable.Constraint_transitionEasing, 65);
        f7430e.append(R.styleable.Constraint_drawPath, 66);
        f7430e.append(R.styleable.Constraint_transitionPathRotate, 67);
        f7430e.append(R.styleable.Constraint_motionStagger, 79);
        f7430e.append(R.styleable.Constraint_android_id, 38);
        f7430e.append(R.styleable.Constraint_motionProgress, 68);
        f7430e.append(R.styleable.Constraint_layout_constraintWidth_percent, 69);
        f7430e.append(R.styleable.Constraint_layout_constraintHeight_percent, 70);
        f7430e.append(R.styleable.Constraint_chainUseRtl, 71);
        f7430e.append(R.styleable.Constraint_barrierDirection, 72);
        f7430e.append(R.styleable.Constraint_barrierMargin, 73);
        f7430e.append(R.styleable.Constraint_constraint_referenced_ids, 74);
        f7430e.append(R.styleable.Constraint_barrierAllowsGoneWidgets, 75);
        f7430e.append(R.styleable.Constraint_pathMotionArc, 76);
        f7430e.append(R.styleable.Constraint_layout_constraintTag, 77);
        f7430e.append(R.styleable.Constraint_visibilityMode, 78);
        f7430e.append(R.styleable.Constraint_layout_constrainedWidth, 80);
        f7430e.append(R.styleable.Constraint_layout_constrainedHeight, 81);
    }

    public void b(boolean z) {
    }

    public void c(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.c.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.c.containsKey(Integer.valueOf(id))) {
                this.c.put(Integer.valueOf(id), new a());
            }
            a aVar = this.c.get(Integer.valueOf(id));
            aVar.f = ConstraintAttribute.a(this.f7431a, childAt);
            aVar.a(id, layoutParams);
            aVar.b.b = childAt.getVisibility();
            if (Build.VERSION.SDK_INT >= 17) {
                aVar.b.d = childAt.getAlpha();
                aVar.f7433e.b = childAt.getRotation();
                aVar.f7433e.c = childAt.getRotationX();
                aVar.f7433e.d = childAt.getRotationY();
                aVar.f7433e.f7441e = childAt.getScaleX();
                aVar.f7433e.f = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    e eVar = aVar.f7433e;
                    eVar.g = pivotX;
                    eVar.h = pivotY;
                }
                aVar.f7433e.i = childAt.getTranslationX();
                aVar.f7433e.j = childAt.getTranslationY();
                if (Build.VERSION.SDK_INT >= 21) {
                    aVar.f7433e.k = childAt.getTranslationZ();
                    e eVar2 = aVar.f7433e;
                    if (eVar2.l) {
                        eVar2.m = childAt.getElevation();
                    }
                }
            }
            if (childAt instanceof Barrier) {
                Barrier barrier = (Barrier) childAt;
                aVar.d.j0 = barrier.c();
                aVar.d.e0 = barrier.getReferencedIds();
                aVar.d.b0 = barrier.getType();
                aVar.d.c0 = barrier.getMargin();
            }
        }
    }

    public a d(int i) {
        return a(i);
    }

    public int e(int i) {
        return a(i).b.b;
    }

    public int f(int i) {
        return a(i).b.c;
    }

    public int g(int i) {
        return a(i).d.c;
    }

    public void a(e7 e7Var) {
        for (Integer num : e7Var.c.keySet()) {
            int iIntValue = num.intValue();
            a aVar = e7Var.c.get(num);
            if (!this.c.containsKey(Integer.valueOf(iIntValue))) {
                this.c.put(Integer.valueOf(iIntValue), new a());
            }
            a aVar2 = this.c.get(Integer.valueOf(iIntValue));
            b bVar = aVar2.d;
            if (!bVar.b) {
                bVar.a(aVar.d);
            }
            d dVar = aVar2.b;
            if (!dVar.f7438a) {
                dVar.a(aVar.b);
            }
            e eVar = aVar2.f7433e;
            if (!eVar.f7440a) {
                eVar.a(aVar.f7433e);
            }
            c cVar = aVar2.c;
            if (!cVar.f7436a) {
                cVar.a(aVar.c);
            }
            for (String str : aVar.f.keySet()) {
                if (!aVar2.f.containsKey(str)) {
                    aVar2.f.put(str, aVar.f.get(str));
                }
            }
        }
    }

    public void b(ConstraintLayout constraintLayout) {
        a(constraintLayout, true);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    public void d(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.c.containsKey(Integer.valueOf(id))) {
                this.c.put(Integer.valueOf(id), new a());
            }
            a aVar = this.c.get(Integer.valueOf(id));
            if (!aVar.d.b) {
                aVar.a(id, layoutParams);
                if (childAt instanceof ConstraintHelper) {
                    aVar.d.e0 = ((ConstraintHelper) childAt).getReferencedIds();
                    if (childAt instanceof Barrier) {
                        Barrier barrier = (Barrier) childAt;
                        aVar.d.j0 = barrier.c();
                        aVar.d.b0 = barrier.getType();
                        aVar.d.c0 = barrier.getMargin();
                    }
                }
                aVar.d.b = true;
            }
            d dVar = aVar.b;
            if (!dVar.f7438a) {
                dVar.b = childAt.getVisibility();
                aVar.b.d = childAt.getAlpha();
                aVar.b.f7438a = true;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                e eVar = aVar.f7433e;
                if (!eVar.f7440a) {
                    eVar.f7440a = true;
                    eVar.b = childAt.getRotation();
                    aVar.f7433e.c = childAt.getRotationX();
                    aVar.f7433e.d = childAt.getRotationY();
                    aVar.f7433e.f7441e = childAt.getScaleX();
                    aVar.f7433e.f = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        e eVar2 = aVar.f7433e;
                        eVar2.g = pivotX;
                        eVar2.h = pivotY;
                    }
                    aVar.f7433e.i = childAt.getTranslationX();
                    aVar.f7433e.j = childAt.getTranslationY();
                    if (Build.VERSION.SDK_INT >= 21) {
                        aVar.f7433e.k = childAt.getTranslationZ();
                        e eVar3 = aVar.f7433e;
                        if (eVar3.l) {
                            eVar3.m = childAt.getElevation();
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7438a = false;
        public int b = 0;
        public int c = 0;
        public float d = 1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f7439e = Float.NaN;

        public void a(d dVar) {
            this.f7438a = dVar.f7438a;
            this.b = dVar.b;
            this.d = dVar.d;
            this.f7439e = dVar.f7439e;
            this.c = dVar.c;
        }

        public void a(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PropertySet);
            this.f7438a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R.styleable.PropertySet_android_alpha) {
                    this.d = typedArrayObtainStyledAttributes.getFloat(index, this.d);
                } else if (index == R.styleable.PropertySet_android_visibility) {
                    this.b = typedArrayObtainStyledAttributes.getInt(index, this.b);
                    this.b = e7.d[this.b];
                } else if (index == R.styleable.PropertySet_visibilityMode) {
                    this.c = typedArrayObtainStyledAttributes.getInt(index, this.c);
                } else if (index == R.styleable.PropertySet_motionProgress) {
                    this.f7439e = typedArrayObtainStyledAttributes.getFloat(index, this.f7439e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public void b(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    a aVarA = a(context, Xml.asAttributeSet(xml));
                    if (name.equalsIgnoreCase("Guideline")) {
                        aVarA.d.f7434a = true;
                    }
                    this.c.put(Integer.valueOf(aVarA.f7432a), aVarA);
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class c {
        public static SparseIntArray h;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7436a = false;
        public int b = -1;
        public String c = null;
        public int d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7437e = 0;
        public float f = Float.NaN;
        public float g = Float.NaN;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            h = sparseIntArray;
            sparseIntArray.append(R.styleable.Motion_motionPathRotate, 1);
            h.append(R.styleable.Motion_pathMotionArc, 2);
            h.append(R.styleable.Motion_transitionEasing, 3);
            h.append(R.styleable.Motion_drawPath, 4);
            h.append(R.styleable.Motion_animate_relativeTo, 5);
            h.append(R.styleable.Motion_motionStagger, 6);
        }

        public void a(c cVar) {
            this.f7436a = cVar.f7436a;
            this.b = cVar.b;
            this.c = cVar.c;
            this.d = cVar.d;
            this.f7437e = cVar.f7437e;
            this.g = cVar.g;
            this.f = cVar.f;
        }

        public void a(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Motion);
            this.f7436a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (h.get(index)) {
                    case 1:
                        this.g = typedArrayObtainStyledAttributes.getFloat(index, this.g);
                        break;
                    case 2:
                        this.d = typedArrayObtainStyledAttributes.getInt(index, this.d);
                        break;
                    case 3:
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            this.c = typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            this.c = t4.c[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        this.f7437e = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.b = e7.b(typedArrayObtainStyledAttributes, index, this.b);
                        break;
                    case 6:
                        this.f = typedArrayObtainStyledAttributes.getFloat(index, this.f);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class e {
        public static SparseIntArray n;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7440a = false;
        public float b = 0.0f;
        public float c = 0.0f;
        public float d = 0.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f7441e = 1.0f;
        public float f = 1.0f;
        public float g = Float.NaN;
        public float h = Float.NaN;
        public float i = 0.0f;
        public float j = 0.0f;
        public float k = 0.0f;
        public boolean l = false;
        public float m = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            n = sparseIntArray;
            sparseIntArray.append(R.styleable.Transform_android_rotation, 1);
            n.append(R.styleable.Transform_android_rotationX, 2);
            n.append(R.styleable.Transform_android_rotationY, 3);
            n.append(R.styleable.Transform_android_scaleX, 4);
            n.append(R.styleable.Transform_android_scaleY, 5);
            n.append(R.styleable.Transform_android_transformPivotX, 6);
            n.append(R.styleable.Transform_android_transformPivotY, 7);
            n.append(R.styleable.Transform_android_translationX, 8);
            n.append(R.styleable.Transform_android_translationY, 9);
            n.append(R.styleable.Transform_android_translationZ, 10);
            n.append(R.styleable.Transform_android_elevation, 11);
        }

        public void a(e eVar) {
            this.f7440a = eVar.f7440a;
            this.b = eVar.b;
            this.c = eVar.c;
            this.d = eVar.d;
            this.f7441e = eVar.f7441e;
            this.f = eVar.f;
            this.g = eVar.g;
            this.h = eVar.h;
            this.i = eVar.i;
            this.j = eVar.j;
            this.k = eVar.k;
            this.l = eVar.l;
            this.m = eVar.m;
        }

        public void a(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Transform);
            this.f7440a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (n.get(index)) {
                    case 1:
                        this.b = typedArrayObtainStyledAttributes.getFloat(index, this.b);
                        break;
                    case 2:
                        this.c = typedArrayObtainStyledAttributes.getFloat(index, this.c);
                        break;
                    case 3:
                        this.d = typedArrayObtainStyledAttributes.getFloat(index, this.d);
                        break;
                    case 4:
                        this.f7441e = typedArrayObtainStyledAttributes.getFloat(index, this.f7441e);
                        break;
                    case 5:
                        this.f = typedArrayObtainStyledAttributes.getFloat(index, this.f);
                        break;
                    case 6:
                        this.g = typedArrayObtainStyledAttributes.getDimension(index, this.g);
                        break;
                    case 7:
                        this.h = typedArrayObtainStyledAttributes.getDimension(index, this.h);
                        break;
                    case 8:
                        this.i = typedArrayObtainStyledAttributes.getDimension(index, this.i);
                        break;
                    case 9:
                        this.j = typedArrayObtainStyledAttributes.getDimension(index, this.j);
                        break;
                    case 10:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.k = typedArrayObtainStyledAttributes.getDimension(index, this.k);
                        }
                        break;
                    case 11:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.l = true;
                            this.m = typedArrayObtainStyledAttributes.getDimension(index, this.m);
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static int b(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    public void a(Context context, int i) {
        c((ConstraintLayout) LayoutInflater.from(context).inflate(i, (ViewGroup) null));
    }

    public a b(int i) {
        if (this.c.containsKey(Integer.valueOf(i))) {
            return this.c.get(Integer.valueOf(i));
        }
        return null;
    }

    public void a(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.c.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraints.getChildAt(i);
            Constraints.LayoutParams layoutParams = (Constraints.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.c.containsKey(Integer.valueOf(id))) {
                this.c.put(Integer.valueOf(id), new a());
            }
            a aVar = this.c.get(Integer.valueOf(id));
            if (childAt instanceof ConstraintHelper) {
                aVar.a((ConstraintHelper) childAt, id, layoutParams);
            }
            aVar.a(id, layoutParams);
        }
    }

    public void a(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (this.c.containsKey(Integer.valueOf(id))) {
                if (this.b && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (this.c.containsKey(Integer.valueOf(id))) {
                    ConstraintAttribute.a(childAt, this.c.get(Integer.valueOf(id)).f);
                }
            } else {
                Log.v("ConstraintSet", "id unknown " + z4.a(childAt));
            }
        }
    }

    public int c(int i) {
        return a(i).d.d;
    }

    public void a(ConstraintHelper constraintHelper, ConstraintWidget constraintWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        int id = constraintHelper.getId();
        if (this.c.containsKey(Integer.valueOf(id))) {
            a aVar = this.c.get(Integer.valueOf(id));
            if (constraintWidget instanceof j6) {
                constraintHelper.a(aVar, (j6) constraintWidget, layoutParams, sparseArray);
            }
        }
    }

    public void a(int i, ConstraintLayout.LayoutParams layoutParams) {
        if (this.c.containsKey(Integer.valueOf(i))) {
            this.c.get(Integer.valueOf(i)).a(layoutParams);
        }
    }

    public void a(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.c.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (this.c.containsKey(Integer.valueOf(id))) {
                if (this.b && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.c.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        a aVar = this.c.get(Integer.valueOf(id));
                        if (childAt instanceof Barrier) {
                            aVar.d.d0 = 1;
                        }
                        int i2 = aVar.d.d0;
                        if (i2 != -1 && i2 == 1) {
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(aVar.d.b0);
                            barrier.setMargin(aVar.d.c0);
                            barrier.setAllowsGoneWidget(aVar.d.j0);
                            b bVar = aVar.d;
                            int[] iArr = bVar.e0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = bVar.f0;
                                if (str != null) {
                                    bVar.e0 = a(barrier, str);
                                    barrier.setReferencedIds(aVar.d.e0);
                                }
                            }
                        }
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                        layoutParams.c();
                        aVar.a(layoutParams);
                        if (z) {
                            ConstraintAttribute.a(childAt, aVar.f);
                        }
                        childAt.setLayoutParams(layoutParams);
                        d dVar = aVar.b;
                        if (dVar.c == 0) {
                            childAt.setVisibility(dVar.b);
                        }
                        if (Build.VERSION.SDK_INT >= 17) {
                            childAt.setAlpha(aVar.b.d);
                            childAt.setRotation(aVar.f7433e.b);
                            childAt.setRotationX(aVar.f7433e.c);
                            childAt.setRotationY(aVar.f7433e.d);
                            childAt.setScaleX(aVar.f7433e.f7441e);
                            childAt.setScaleY(aVar.f7433e.f);
                            if (!Float.isNaN(aVar.f7433e.g)) {
                                childAt.setPivotX(aVar.f7433e.g);
                            }
                            if (!Float.isNaN(aVar.f7433e.h)) {
                                childAt.setPivotY(aVar.f7433e.h);
                            }
                            childAt.setTranslationX(aVar.f7433e.i);
                            childAt.setTranslationY(aVar.f7433e.j);
                            if (Build.VERSION.SDK_INT >= 21) {
                                childAt.setTranslationZ(aVar.f7433e.k);
                                e eVar = aVar.f7433e;
                                if (eVar.l) {
                                    childAt.setElevation(eVar.m);
                                }
                            }
                        }
                    } else {
                        Log.v("ConstraintSet", "WARNING NO CONSTRAINTS for view " + id);
                    }
                }
            } else {
                Log.w("ConstraintSet", "id unknown " + z4.a(childAt));
            }
        }
        for (Integer num : hashSet) {
            a aVar2 = this.c.get(num);
            int i3 = aVar2.d.d0;
            if (i3 != -1 && i3 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                b bVar2 = aVar2.d;
                int[] iArr2 = bVar2.e0;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = bVar2.f0;
                    if (str2 != null) {
                        bVar2.e0 = a(barrier2, str2);
                        barrier2.setReferencedIds(aVar2.d.e0);
                    }
                }
                barrier2.setType(aVar2.d.b0);
                barrier2.setMargin(aVar2.d.c0);
                ConstraintLayout.LayoutParams layoutParamsGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.b();
                aVar2.a(layoutParamsGenerateDefaultLayoutParams);
                constraintLayout.addView(barrier2, layoutParamsGenerateDefaultLayoutParams);
            }
            if (aVar2.d.f7434a) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.LayoutParams layoutParamsGenerateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                aVar2.a(layoutParamsGenerateDefaultLayoutParams2);
                constraintLayout.addView(guideline, layoutParamsGenerateDefaultLayoutParams2);
            }
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class b {
        public static SparseIntArray k0;
        public int c;
        public int d;
        public int[] e0;
        public String f0;
        public String g0;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7434a = false;
        public boolean b = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7435e = -1;
        public int f = -1;
        public float g = -1.0f;
        public int h = -1;
        public int i = -1;
        public int j = -1;
        public int k = -1;
        public int l = -1;
        public int m = -1;
        public int n = -1;
        public int o = -1;
        public int p = -1;
        public int q = -1;
        public int r = -1;
        public int s = -1;
        public int t = -1;
        public float u = 0.5f;
        public float v = 0.5f;
        public String w = null;
        public int x = -1;
        public int y = 0;
        public float z = 0.0f;
        public int A = -1;
        public int B = -1;
        public int C = -1;
        public int D = -1;
        public int E = -1;
        public int F = -1;
        public int G = -1;
        public int H = -1;
        public int I = -1;
        public int J = -1;
        public int K = -1;
        public int L = -1;
        public int M = -1;
        public int N = -1;
        public int O = -1;
        public float P = -1.0f;
        public float Q = -1.0f;
        public int R = 0;
        public int S = 0;
        public int T = 0;
        public int U = 0;
        public int V = -1;
        public int W = -1;
        public int X = -1;
        public int Y = -1;
        public float Z = 1.0f;
        public float a0 = 1.0f;
        public int b0 = -1;
        public int c0 = 0;
        public int d0 = -1;
        public boolean h0 = false;
        public boolean i0 = false;
        public boolean j0 = true;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            k0 = sparseIntArray;
            sparseIntArray.append(R.styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            k0.append(R.styleable.Layout_layout_constraintLeft_toRightOf, 25);
            k0.append(R.styleable.Layout_layout_constraintRight_toLeftOf, 28);
            k0.append(R.styleable.Layout_layout_constraintRight_toRightOf, 29);
            k0.append(R.styleable.Layout_layout_constraintTop_toTopOf, 35);
            k0.append(R.styleable.Layout_layout_constraintTop_toBottomOf, 34);
            k0.append(R.styleable.Layout_layout_constraintBottom_toTopOf, 4);
            k0.append(R.styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            k0.append(R.styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            k0.append(R.styleable.Layout_layout_editor_absoluteX, 6);
            k0.append(R.styleable.Layout_layout_editor_absoluteY, 7);
            k0.append(R.styleable.Layout_layout_constraintGuide_begin, 17);
            k0.append(R.styleable.Layout_layout_constraintGuide_end, 18);
            k0.append(R.styleable.Layout_layout_constraintGuide_percent, 19);
            k0.append(R.styleable.Layout_android_orientation, 26);
            k0.append(R.styleable.Layout_layout_constraintStart_toEndOf, 31);
            k0.append(R.styleable.Layout_layout_constraintStart_toStartOf, 32);
            k0.append(R.styleable.Layout_layout_constraintEnd_toStartOf, 10);
            k0.append(R.styleable.Layout_layout_constraintEnd_toEndOf, 9);
            k0.append(R.styleable.Layout_layout_goneMarginLeft, 13);
            k0.append(R.styleable.Layout_layout_goneMarginTop, 16);
            k0.append(R.styleable.Layout_layout_goneMarginRight, 14);
            k0.append(R.styleable.Layout_layout_goneMarginBottom, 11);
            k0.append(R.styleable.Layout_layout_goneMarginStart, 15);
            k0.append(R.styleable.Layout_layout_goneMarginEnd, 12);
            k0.append(R.styleable.Layout_layout_constraintVertical_weight, 38);
            k0.append(R.styleable.Layout_layout_constraintHorizontal_weight, 37);
            k0.append(R.styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            k0.append(R.styleable.Layout_layout_constraintVertical_chainStyle, 40);
            k0.append(R.styleable.Layout_layout_constraintHorizontal_bias, 20);
            k0.append(R.styleable.Layout_layout_constraintVertical_bias, 36);
            k0.append(R.styleable.Layout_layout_constraintDimensionRatio, 5);
            k0.append(R.styleable.Layout_layout_constraintLeft_creator, 76);
            k0.append(R.styleable.Layout_layout_constraintTop_creator, 76);
            k0.append(R.styleable.Layout_layout_constraintRight_creator, 76);
            k0.append(R.styleable.Layout_layout_constraintBottom_creator, 76);
            k0.append(R.styleable.Layout_layout_constraintBaseline_creator, 76);
            k0.append(R.styleable.Layout_android_layout_marginLeft, 23);
            k0.append(R.styleable.Layout_android_layout_marginRight, 27);
            k0.append(R.styleable.Layout_android_layout_marginStart, 30);
            k0.append(R.styleable.Layout_android_layout_marginEnd, 8);
            k0.append(R.styleable.Layout_android_layout_marginTop, 33);
            k0.append(R.styleable.Layout_android_layout_marginBottom, 2);
            k0.append(R.styleable.Layout_android_layout_width, 22);
            k0.append(R.styleable.Layout_android_layout_height, 21);
            k0.append(R.styleable.Layout_layout_constraintCircle, 61);
            k0.append(R.styleable.Layout_layout_constraintCircleRadius, 62);
            k0.append(R.styleable.Layout_layout_constraintCircleAngle, 63);
            k0.append(R.styleable.Layout_layout_constraintWidth_percent, 69);
            k0.append(R.styleable.Layout_layout_constraintHeight_percent, 70);
            k0.append(R.styleable.Layout_chainUseRtl, 71);
            k0.append(R.styleable.Layout_barrierDirection, 72);
            k0.append(R.styleable.Layout_barrierMargin, 73);
            k0.append(R.styleable.Layout_constraint_referenced_ids, 74);
            k0.append(R.styleable.Layout_barrierAllowsGoneWidgets, 75);
        }

        public void a(b bVar) {
            this.f7434a = bVar.f7434a;
            this.c = bVar.c;
            this.b = bVar.b;
            this.d = bVar.d;
            this.f7435e = bVar.f7435e;
            this.f = bVar.f;
            this.g = bVar.g;
            this.h = bVar.h;
            this.i = bVar.i;
            this.j = bVar.j;
            this.k = bVar.k;
            this.l = bVar.l;
            this.m = bVar.m;
            this.n = bVar.n;
            this.o = bVar.o;
            this.p = bVar.p;
            this.q = bVar.q;
            this.r = bVar.r;
            this.s = bVar.s;
            this.t = bVar.t;
            this.u = bVar.u;
            this.v = bVar.v;
            this.w = bVar.w;
            this.x = bVar.x;
            this.y = bVar.y;
            this.z = bVar.z;
            this.A = bVar.A;
            this.B = bVar.B;
            this.C = bVar.C;
            this.D = bVar.D;
            this.E = bVar.E;
            this.F = bVar.F;
            this.G = bVar.G;
            this.H = bVar.H;
            this.I = bVar.I;
            this.J = bVar.J;
            this.K = bVar.K;
            this.L = bVar.L;
            this.M = bVar.M;
            this.N = bVar.N;
            this.O = bVar.O;
            this.P = bVar.P;
            this.Q = bVar.Q;
            this.R = bVar.R;
            this.S = bVar.S;
            this.T = bVar.T;
            this.U = bVar.U;
            this.V = bVar.V;
            this.W = bVar.W;
            this.X = bVar.X;
            this.Y = bVar.Y;
            this.Z = bVar.Z;
            this.a0 = bVar.a0;
            this.b0 = bVar.b0;
            this.c0 = bVar.c0;
            this.d0 = bVar.d0;
            this.g0 = bVar.g0;
            int[] iArr = bVar.e0;
            if (iArr != null) {
                this.e0 = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.e0 = null;
            }
            this.f0 = bVar.f0;
            this.h0 = bVar.h0;
            this.i0 = bVar.i0;
            this.j0 = bVar.j0;
        }

        public void a(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Layout);
            this.b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                int i2 = k0.get(index);
                if (i2 == 80) {
                    this.h0 = typedArrayObtainStyledAttributes.getBoolean(index, this.h0);
                } else if (i2 != 81) {
                    switch (i2) {
                        case 1:
                            this.p = e7.b(typedArrayObtainStyledAttributes, index, this.p);
                            break;
                        case 2:
                            this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.G);
                            break;
                        case 3:
                            this.o = e7.b(typedArrayObtainStyledAttributes, index, this.o);
                            break;
                        case 4:
                            this.n = e7.b(typedArrayObtainStyledAttributes, index, this.n);
                            break;
                        case 5:
                            this.w = typedArrayObtainStyledAttributes.getString(index);
                            break;
                        case 6:
                            this.A = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.A);
                            break;
                        case 7:
                            this.B = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.B);
                            break;
                        case 8:
                            if (Build.VERSION.SDK_INT >= 17) {
                                this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.H);
                            }
                            break;
                        case 9:
                            this.t = e7.b(typedArrayObtainStyledAttributes, index, this.t);
                            break;
                        case 10:
                            this.s = e7.b(typedArrayObtainStyledAttributes, index, this.s);
                            break;
                        case 11:
                            this.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.M);
                            break;
                        case 12:
                            this.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.N);
                            break;
                        case 13:
                            this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.J);
                            break;
                        case 14:
                            this.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.L);
                            break;
                        case 15:
                            this.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.O);
                            break;
                        case 16:
                            this.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.K);
                            break;
                        case 17:
                            this.f7435e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f7435e);
                            break;
                        case 18:
                            this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f);
                            break;
                        case 19:
                            this.g = typedArrayObtainStyledAttributes.getFloat(index, this.g);
                            break;
                        case 20:
                            this.u = typedArrayObtainStyledAttributes.getFloat(index, this.u);
                            break;
                        case 21:
                            this.d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.d);
                            break;
                        case 22:
                            this.c = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.c);
                            break;
                        case 23:
                            this.D = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.D);
                            break;
                        case 24:
                            this.h = e7.b(typedArrayObtainStyledAttributes, index, this.h);
                            break;
                        case 25:
                            this.i = e7.b(typedArrayObtainStyledAttributes, index, this.i);
                            break;
                        case 26:
                            this.C = typedArrayObtainStyledAttributes.getInt(index, this.C);
                            break;
                        case 27:
                            this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.E);
                            break;
                        case 28:
                            this.j = e7.b(typedArrayObtainStyledAttributes, index, this.j);
                            break;
                        case 29:
                            this.k = e7.b(typedArrayObtainStyledAttributes, index, this.k);
                            break;
                        case 30:
                            if (Build.VERSION.SDK_INT >= 17) {
                                this.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.I);
                            }
                            break;
                        case 31:
                            this.q = e7.b(typedArrayObtainStyledAttributes, index, this.q);
                            break;
                        case 32:
                            this.r = e7.b(typedArrayObtainStyledAttributes, index, this.r);
                            break;
                        case 33:
                            this.F = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.F);
                            break;
                        case 34:
                            this.m = e7.b(typedArrayObtainStyledAttributes, index, this.m);
                            break;
                        case 35:
                            this.l = e7.b(typedArrayObtainStyledAttributes, index, this.l);
                            break;
                        case 36:
                            this.v = typedArrayObtainStyledAttributes.getFloat(index, this.v);
                            break;
                        case 37:
                            this.Q = typedArrayObtainStyledAttributes.getFloat(index, this.Q);
                            break;
                        case 38:
                            this.P = typedArrayObtainStyledAttributes.getFloat(index, this.P);
                            break;
                        case 39:
                            this.R = typedArrayObtainStyledAttributes.getInt(index, this.R);
                            break;
                        case 40:
                            this.S = typedArrayObtainStyledAttributes.getInt(index, this.S);
                            break;
                        default:
                            switch (i2) {
                                case 54:
                                    this.T = typedArrayObtainStyledAttributes.getInt(index, this.T);
                                    break;
                                case 55:
                                    this.U = typedArrayObtainStyledAttributes.getInt(index, this.U);
                                    break;
                                case 56:
                                    this.V = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.V);
                                    break;
                                case 57:
                                    this.W = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.W);
                                    break;
                                case 58:
                                    this.X = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.X);
                                    break;
                                case 59:
                                    this.Y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.Y);
                                    break;
                                default:
                                    switch (i2) {
                                        case 61:
                                            this.x = e7.b(typedArrayObtainStyledAttributes, index, this.x);
                                            break;
                                        case 62:
                                            this.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.y);
                                            break;
                                        case 63:
                                            this.z = typedArrayObtainStyledAttributes.getFloat(index, this.z);
                                            break;
                                        default:
                                            switch (i2) {
                                                case 69:
                                                    this.Z = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                                    break;
                                                case 70:
                                                    this.a0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                                    break;
                                                case 71:
                                                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                                    break;
                                                case 72:
                                                    this.b0 = typedArrayObtainStyledAttributes.getInt(index, this.b0);
                                                    break;
                                                case 73:
                                                    this.c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.c0);
                                                    break;
                                                case 74:
                                                    this.f0 = typedArrayObtainStyledAttributes.getString(index);
                                                    break;
                                                case 75:
                                                    this.j0 = typedArrayObtainStyledAttributes.getBoolean(index, this.j0);
                                                    break;
                                                case 76:
                                                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + k0.get(index));
                                                    break;
                                                case 77:
                                                    this.g0 = typedArrayObtainStyledAttributes.getString(index);
                                                    break;
                                                default:
                                                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + k0.get(index));
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                } else {
                    this.i0 = typedArrayObtainStyledAttributes.getBoolean(index, this.i0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public final a a(int i) {
        if (!this.c.containsKey(Integer.valueOf(i))) {
            this.c.put(Integer.valueOf(i), new a());
        }
        return this.c.get(Integer.valueOf(i));
    }

    /* JADX WARN: Code restructure failed: missing block: B:98:0x017b, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.content.Context r10, org.xmlpull.v1.XmlPullParser r11) {
        /*
            Method dump skipped, instruction units count: 450
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.e7.a(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public final a a(Context context, AttributeSet attributeSet) {
        a aVar = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Constraint);
        a(context, aVar, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return aVar;
    }

    public final void a(Context context, a aVar, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index != R.styleable.Constraint_android_id && R.styleable.Constraint_android_layout_marginStart != index && R.styleable.Constraint_android_layout_marginEnd != index) {
                aVar.c.f7436a = true;
                aVar.d.b = true;
                aVar.b.f7438a = true;
                aVar.f7433e.f7440a = true;
            }
            switch (f7430e.get(index)) {
                case 1:
                    b bVar = aVar.d;
                    bVar.p = b(typedArray, index, bVar.p);
                    break;
                case 2:
                    b bVar2 = aVar.d;
                    bVar2.G = typedArray.getDimensionPixelSize(index, bVar2.G);
                    break;
                case 3:
                    b bVar3 = aVar.d;
                    bVar3.o = b(typedArray, index, bVar3.o);
                    break;
                case 4:
                    b bVar4 = aVar.d;
                    bVar4.n = b(typedArray, index, bVar4.n);
                    break;
                case 5:
                    aVar.d.w = typedArray.getString(index);
                    break;
                case 6:
                    b bVar5 = aVar.d;
                    bVar5.A = typedArray.getDimensionPixelOffset(index, bVar5.A);
                    break;
                case 7:
                    b bVar6 = aVar.d;
                    bVar6.B = typedArray.getDimensionPixelOffset(index, bVar6.B);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        b bVar7 = aVar.d;
                        bVar7.H = typedArray.getDimensionPixelSize(index, bVar7.H);
                    }
                    break;
                case 9:
                    b bVar8 = aVar.d;
                    bVar8.t = b(typedArray, index, bVar8.t);
                    break;
                case 10:
                    b bVar9 = aVar.d;
                    bVar9.s = b(typedArray, index, bVar9.s);
                    break;
                case 11:
                    b bVar10 = aVar.d;
                    bVar10.M = typedArray.getDimensionPixelSize(index, bVar10.M);
                    break;
                case 12:
                    b bVar11 = aVar.d;
                    bVar11.N = typedArray.getDimensionPixelSize(index, bVar11.N);
                    break;
                case 13:
                    b bVar12 = aVar.d;
                    bVar12.J = typedArray.getDimensionPixelSize(index, bVar12.J);
                    break;
                case 14:
                    b bVar13 = aVar.d;
                    bVar13.L = typedArray.getDimensionPixelSize(index, bVar13.L);
                    break;
                case 15:
                    b bVar14 = aVar.d;
                    bVar14.O = typedArray.getDimensionPixelSize(index, bVar14.O);
                    break;
                case 16:
                    b bVar15 = aVar.d;
                    bVar15.K = typedArray.getDimensionPixelSize(index, bVar15.K);
                    break;
                case 17:
                    b bVar16 = aVar.d;
                    bVar16.f7435e = typedArray.getDimensionPixelOffset(index, bVar16.f7435e);
                    break;
                case 18:
                    b bVar17 = aVar.d;
                    bVar17.f = typedArray.getDimensionPixelOffset(index, bVar17.f);
                    break;
                case 19:
                    b bVar18 = aVar.d;
                    bVar18.g = typedArray.getFloat(index, bVar18.g);
                    break;
                case 20:
                    b bVar19 = aVar.d;
                    bVar19.u = typedArray.getFloat(index, bVar19.u);
                    break;
                case 21:
                    b bVar20 = aVar.d;
                    bVar20.d = typedArray.getLayoutDimension(index, bVar20.d);
                    break;
                case 22:
                    d dVar = aVar.b;
                    dVar.b = typedArray.getInt(index, dVar.b);
                    d dVar2 = aVar.b;
                    dVar2.b = d[dVar2.b];
                    break;
                case 23:
                    b bVar21 = aVar.d;
                    bVar21.c = typedArray.getLayoutDimension(index, bVar21.c);
                    break;
                case 24:
                    b bVar22 = aVar.d;
                    bVar22.D = typedArray.getDimensionPixelSize(index, bVar22.D);
                    break;
                case 25:
                    b bVar23 = aVar.d;
                    bVar23.h = b(typedArray, index, bVar23.h);
                    break;
                case 26:
                    b bVar24 = aVar.d;
                    bVar24.i = b(typedArray, index, bVar24.i);
                    break;
                case 27:
                    b bVar25 = aVar.d;
                    bVar25.C = typedArray.getInt(index, bVar25.C);
                    break;
                case 28:
                    b bVar26 = aVar.d;
                    bVar26.E = typedArray.getDimensionPixelSize(index, bVar26.E);
                    break;
                case 29:
                    b bVar27 = aVar.d;
                    bVar27.j = b(typedArray, index, bVar27.j);
                    break;
                case 30:
                    b bVar28 = aVar.d;
                    bVar28.k = b(typedArray, index, bVar28.k);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        b bVar29 = aVar.d;
                        bVar29.I = typedArray.getDimensionPixelSize(index, bVar29.I);
                    }
                    break;
                case 32:
                    b bVar30 = aVar.d;
                    bVar30.q = b(typedArray, index, bVar30.q);
                    break;
                case 33:
                    b bVar31 = aVar.d;
                    bVar31.r = b(typedArray, index, bVar31.r);
                    break;
                case 34:
                    b bVar32 = aVar.d;
                    bVar32.F = typedArray.getDimensionPixelSize(index, bVar32.F);
                    break;
                case 35:
                    b bVar33 = aVar.d;
                    bVar33.m = b(typedArray, index, bVar33.m);
                    break;
                case 36:
                    b bVar34 = aVar.d;
                    bVar34.l = b(typedArray, index, bVar34.l);
                    break;
                case 37:
                    b bVar35 = aVar.d;
                    bVar35.v = typedArray.getFloat(index, bVar35.v);
                    break;
                case 38:
                    aVar.f7432a = typedArray.getResourceId(index, aVar.f7432a);
                    break;
                case 39:
                    b bVar36 = aVar.d;
                    bVar36.Q = typedArray.getFloat(index, bVar36.Q);
                    break;
                case 40:
                    b bVar37 = aVar.d;
                    bVar37.P = typedArray.getFloat(index, bVar37.P);
                    break;
                case 41:
                    b bVar38 = aVar.d;
                    bVar38.R = typedArray.getInt(index, bVar38.R);
                    break;
                case 42:
                    b bVar39 = aVar.d;
                    bVar39.S = typedArray.getInt(index, bVar39.S);
                    break;
                case 43:
                    d dVar3 = aVar.b;
                    dVar3.d = typedArray.getFloat(index, dVar3.d);
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar = aVar.f7433e;
                        eVar.l = true;
                        eVar.m = typedArray.getDimension(index, eVar.m);
                    }
                    break;
                case 45:
                    e eVar2 = aVar.f7433e;
                    eVar2.c = typedArray.getFloat(index, eVar2.c);
                    break;
                case 46:
                    e eVar3 = aVar.f7433e;
                    eVar3.d = typedArray.getFloat(index, eVar3.d);
                    break;
                case 47:
                    e eVar4 = aVar.f7433e;
                    eVar4.f7441e = typedArray.getFloat(index, eVar4.f7441e);
                    break;
                case 48:
                    e eVar5 = aVar.f7433e;
                    eVar5.f = typedArray.getFloat(index, eVar5.f);
                    break;
                case 49:
                    e eVar6 = aVar.f7433e;
                    eVar6.g = typedArray.getDimension(index, eVar6.g);
                    break;
                case 50:
                    e eVar7 = aVar.f7433e;
                    eVar7.h = typedArray.getDimension(index, eVar7.h);
                    break;
                case 51:
                    e eVar8 = aVar.f7433e;
                    eVar8.i = typedArray.getDimension(index, eVar8.i);
                    break;
                case 52:
                    e eVar9 = aVar.f7433e;
                    eVar9.j = typedArray.getDimension(index, eVar9.j);
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar10 = aVar.f7433e;
                        eVar10.k = typedArray.getDimension(index, eVar10.k);
                    }
                    break;
                case 54:
                    b bVar40 = aVar.d;
                    bVar40.T = typedArray.getInt(index, bVar40.T);
                    break;
                case 55:
                    b bVar41 = aVar.d;
                    bVar41.U = typedArray.getInt(index, bVar41.U);
                    break;
                case 56:
                    b bVar42 = aVar.d;
                    bVar42.V = typedArray.getDimensionPixelSize(index, bVar42.V);
                    break;
                case 57:
                    b bVar43 = aVar.d;
                    bVar43.W = typedArray.getDimensionPixelSize(index, bVar43.W);
                    break;
                case 58:
                    b bVar44 = aVar.d;
                    bVar44.X = typedArray.getDimensionPixelSize(index, bVar44.X);
                    break;
                case 59:
                    b bVar45 = aVar.d;
                    bVar45.Y = typedArray.getDimensionPixelSize(index, bVar45.Y);
                    break;
                case 60:
                    e eVar11 = aVar.f7433e;
                    eVar11.b = typedArray.getFloat(index, eVar11.b);
                    break;
                case 61:
                    b bVar46 = aVar.d;
                    bVar46.x = b(typedArray, index, bVar46.x);
                    break;
                case 62:
                    b bVar47 = aVar.d;
                    bVar47.y = typedArray.getDimensionPixelSize(index, bVar47.y);
                    break;
                case 63:
                    b bVar48 = aVar.d;
                    bVar48.z = typedArray.getFloat(index, bVar48.z);
                    break;
                case 64:
                    c cVar = aVar.c;
                    cVar.b = b(typedArray, index, cVar.b);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        aVar.c.c = typedArray.getString(index);
                    } else {
                        aVar.c.c = t4.c[typedArray.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    aVar.c.f7437e = typedArray.getInt(index, 0);
                    break;
                case 67:
                    c cVar2 = aVar.c;
                    cVar2.g = typedArray.getFloat(index, cVar2.g);
                    break;
                case 68:
                    d dVar4 = aVar.b;
                    dVar4.f7439e = typedArray.getFloat(index, dVar4.f7439e);
                    break;
                case 69:
                    aVar.d.Z = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    aVar.d.a0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    b bVar49 = aVar.d;
                    bVar49.b0 = typedArray.getInt(index, bVar49.b0);
                    break;
                case 73:
                    b bVar50 = aVar.d;
                    bVar50.c0 = typedArray.getDimensionPixelSize(index, bVar50.c0);
                    break;
                case 74:
                    aVar.d.f0 = typedArray.getString(index);
                    break;
                case 75:
                    b bVar51 = aVar.d;
                    bVar51.j0 = typedArray.getBoolean(index, bVar51.j0);
                    break;
                case 76:
                    c cVar3 = aVar.c;
                    cVar3.d = typedArray.getInt(index, cVar3.d);
                    break;
                case 77:
                    aVar.d.g0 = typedArray.getString(index);
                    break;
                case 78:
                    d dVar5 = aVar.b;
                    dVar5.c = typedArray.getInt(index, dVar5.c);
                    break;
                case 79:
                    c cVar4 = aVar.c;
                    cVar4.f = typedArray.getFloat(index, cVar4.f);
                    break;
                case 80:
                    b bVar52 = aVar.d;
                    bVar52.h0 = typedArray.getBoolean(index, bVar52.h0);
                    break;
                case 81:
                    b bVar53 = aVar.d;
                    bVar53.i0 = typedArray.getBoolean(index, bVar53.i0);
                    break;
                case 82:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f7430e.get(index));
                    break;
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f7430e.get(index));
                    break;
            }
        }
    }

    public final int[] a(View view, String str) {
        int iIntValue;
        Object objA;
        String[] strArrSplit = str.split(",");
        Context context = view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i = 0;
        int i2 = 0;
        while (i < strArrSplit.length) {
            String strTrim = strArrSplit[i].trim();
            try {
                iIntValue = R.id.class.getField(strTrim).getInt(null);
            } catch (Exception unused) {
                iIntValue = 0;
            }
            if (iIntValue == 0) {
                iIntValue = context.getResources().getIdentifier(strTrim, "id", context.getPackageName());
            }
            if (iIntValue == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (objA = ((ConstraintLayout) view.getParent()).a(0, strTrim)) != null && (objA instanceof Integer)) {
                iIntValue = ((Integer) objA).intValue();
            }
            iArr[i2] = iIntValue;
            i++;
            i2++;
        }
        return i2 != strArrSplit.length ? Arrays.copyOf(iArr, i2) : iArr;
    }

    public int[] a() {
        Integer[] numArr = (Integer[]) this.c.keySet().toArray(new Integer[0]);
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = numArr[i].intValue();
        }
        return iArr;
    }

    public void a(boolean z) {
        this.b = z;
    }
}
