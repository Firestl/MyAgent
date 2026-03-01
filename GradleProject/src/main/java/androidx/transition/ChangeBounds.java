package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import supwisdom.fh;
import supwisdom.ig;
import supwisdom.kg;
import supwisdom.l8;
import supwisdom.lb;
import supwisdom.lg;
import supwisdom.og;
import supwisdom.pg;
import supwisdom.sg;
import supwisdom.tg;
import supwisdom.zg;

/* JADX INFO: loaded from: classes.dex */
public class ChangeBounds extends Transition {
    public static final String[] M = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    public static final Property<Drawable, PointF> N = new b(PointF.class, "boundsOrigin");
    public static final Property<k, PointF> O = new c(PointF.class, "topLeft");
    public static final Property<k, PointF> P = new d(PointF.class, "bottomRight");
    public static final Property<View, PointF> Q = new e(PointF.class, "bottomRight");
    public static final Property<View, PointF> R = new f(PointF.class, "topLeft");
    public static final Property<View, PointF> S = new g(PointF.class, "position");
    public static lg T = new lg();
    public int[] J;
    public boolean K;
    public boolean L;

    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f1412a;
        public final /* synthetic */ BitmapDrawable b;
        public final /* synthetic */ View c;
        public final /* synthetic */ float d;

        public a(ViewGroup viewGroup, BitmapDrawable bitmapDrawable, View view, float f) {
            this.f1412a = viewGroup;
            this.b = bitmapDrawable;
            this.c = view;
            this.d = f;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            fh.b(this.f1412a).b(this.b);
            fh.a(this.c, this.d);
        }
    }

    public static class c extends Property<k, PointF> {
        public c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(k kVar, PointF pointF) {
            kVar.b(pointF);
        }
    }

    public static class d extends Property<k, PointF> {
        public d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(k kVar, PointF pointF) {
            kVar.a(pointF);
        }
    }

    public static class e extends Property<View, PointF> {
        public e(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            fh.a(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
        }
    }

    public static class f extends Property<View, PointF> {
        public f(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            fh.a(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
        }
    }

    public static class g extends Property<View, PointF> {
        public g(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            int iRound = Math.round(pointF.x);
            int iRound2 = Math.round(pointF.y);
            fh.a(view, iRound, iRound2, view.getWidth() + iRound, view.getHeight() + iRound2);
        }
    }

    public class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ k f1415a;
        public k mViewBounds;

        public h(k kVar) {
            this.f1415a = kVar;
            this.mViewBounds = this.f1415a;
        }
    }

    public class i extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1416a;
        public final /* synthetic */ View b;
        public final /* synthetic */ Rect c;
        public final /* synthetic */ int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f1417e;
        public final /* synthetic */ int f;
        public final /* synthetic */ int g;

        public i(View view, Rect rect, int i, int i2, int i3, int i4) {
            this.b = view;
            this.c = rect;
            this.d = i;
            this.f1417e = i2;
            this.f = i3;
            this.g = i4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1416a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f1416a) {
                return;
            }
            lb.a(this.b, this.c);
            fh.a(this.b, this.d, this.f1417e, this.f, this.g);
        }
    }

    public class j extends pg {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1418a = false;
        public final /* synthetic */ ViewGroup b;

        public j(ViewGroup viewGroup) {
            this.b = viewGroup;
        }

        @Override // supwisdom.pg, androidx.transition.Transition.f
        public void b(Transition transition) {
            zg.a(this.b, false);
        }

        @Override // androidx.transition.Transition.f
        public void c(Transition transition) {
            if (!this.f1418a) {
                zg.a(this.b, false);
            }
            transition.b(this);
        }

        @Override // supwisdom.pg, androidx.transition.Transition.f
        public void d(Transition transition) {
            zg.a(this.b, true);
        }
    }

    public ChangeBounds() {
        this.J = new int[2];
        this.K = false;
        this.L = false;
    }

    @Override // androidx.transition.Transition
    public void a(tg tgVar) {
        d(tgVar);
    }

    public void b(boolean z) {
        this.K = z;
    }

    @Override // androidx.transition.Transition
    public void c(tg tgVar) {
        d(tgVar);
    }

    public final void d(tg tgVar) {
        View view = tgVar.b;
        if (!lb.M(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        tgVar.f9283a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        tgVar.f9283a.put("android:changeBounds:parent", tgVar.b.getParent());
        if (this.L) {
            tgVar.b.getLocationInWindow(this.J);
            tgVar.f9283a.put("android:changeBounds:windowX", Integer.valueOf(this.J[0]));
            tgVar.f9283a.put("android:changeBounds:windowY", Integer.valueOf(this.J[1]));
        }
        if (this.K) {
            tgVar.f9283a.put("android:changeBounds:clip", lb.h(view));
        }
    }

    @Override // androidx.transition.Transition
    public String[] n() {
        return M;
    }

    public final boolean a(View view, View view2) {
        if (!this.L) {
            return true;
        }
        tg tgVarB = b(view, true);
        if (tgVarB == null) {
            if (view == view2) {
                return true;
            }
        } else if (view2 == tgVarB.b) {
            return true;
        }
        return false;
    }

    public static class b extends Property<Drawable, PointF> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Rect f1414a;

        public b(Class cls, String str) {
            super(cls, str);
            this.f1414a = new Rect();
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(Drawable drawable, PointF pointF) {
            drawable.copyBounds(this.f1414a);
            this.f1414a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
            drawable.setBounds(this.f1414a);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.f1414a);
            Rect rect = this.f1414a;
            return new PointF(rect.left, rect.top);
        }
    }

    public static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1419a;
        public int b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public View f1420e;
        public int f;
        public int g;

        public k(View view) {
            this.f1420e = view;
        }

        public void a(PointF pointF) {
            this.c = Math.round(pointF.x);
            this.d = Math.round(pointF.y);
            int i = this.g + 1;
            this.g = i;
            if (this.f == i) {
                a();
            }
        }

        public void b(PointF pointF) {
            this.f1419a = Math.round(pointF.x);
            this.b = Math.round(pointF.y);
            int i = this.f + 1;
            this.f = i;
            if (i == this.g) {
                a();
            }
        }

        public final void a() {
            fh.a(this.f1420e, this.f1419a, this.b, this.c, this.d);
            this.f = 0;
            this.g = 0;
        }
    }

    public ChangeBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.J = new int[2];
        this.K = false;
        this.L = false;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, og.b);
        boolean zA = l8.a(typedArrayObtainStyledAttributes, (XmlPullParser) attributeSet, "resizeClip", 0, false);
        typedArrayObtainStyledAttributes.recycle();
        b(zA);
    }

    @Override // androidx.transition.Transition
    public Animator a(ViewGroup viewGroup, tg tgVar, tg tgVar2) {
        int i2;
        View view;
        int i3;
        Rect rect;
        ObjectAnimator objectAnimator;
        Animator animatorA;
        if (tgVar == null || tgVar2 == null) {
            return null;
        }
        Map<String, Object> map = tgVar.f9283a;
        Map<String, Object> map2 = tgVar2.f9283a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view2 = tgVar2.b;
        if (a(viewGroup2, viewGroup3)) {
            Rect rect2 = (Rect) tgVar.f9283a.get("android:changeBounds:bounds");
            Rect rect3 = (Rect) tgVar2.f9283a.get("android:changeBounds:bounds");
            int i4 = rect2.left;
            int i5 = rect3.left;
            int i6 = rect2.top;
            int i7 = rect3.top;
            int i8 = rect2.right;
            int i9 = rect3.right;
            int i10 = rect2.bottom;
            int i11 = rect3.bottom;
            int i12 = i8 - i4;
            int i13 = i10 - i6;
            int i14 = i9 - i5;
            int i15 = i11 - i7;
            Rect rect4 = (Rect) tgVar.f9283a.get("android:changeBounds:clip");
            Rect rect5 = (Rect) tgVar2.f9283a.get("android:changeBounds:clip");
            if ((i12 == 0 || i13 == 0) && (i14 == 0 || i15 == 0)) {
                i2 = 0;
            } else {
                i2 = (i4 == i5 && i6 == i7) ? 0 : 1;
                if (i8 != i9 || i10 != i11) {
                    i2++;
                }
            }
            if ((rect4 != null && !rect4.equals(rect5)) || (rect4 == null && rect5 != null)) {
                i2++;
            }
            if (i2 <= 0) {
                return null;
            }
            if (!this.K) {
                view = view2;
                fh.a(view, i4, i6, i8, i10);
                if (i2 == 2) {
                    if (i12 == i14 && i13 == i15) {
                        animatorA = ig.a(view, S, g().a(i4, i6, i5, i7));
                    } else {
                        k kVar = new k(view);
                        ObjectAnimator objectAnimatorA = ig.a(kVar, O, g().a(i4, i6, i5, i7));
                        ObjectAnimator objectAnimatorA2 = ig.a(kVar, P, g().a(i8, i10, i9, i11));
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(objectAnimatorA, objectAnimatorA2);
                        animatorSet.addListener(new h(kVar));
                        animatorA = animatorSet;
                    }
                } else if (i4 == i5 && i6 == i7) {
                    animatorA = ig.a(view, Q, g().a(i8, i10, i9, i11));
                } else {
                    animatorA = ig.a(view, R, g().a(i4, i6, i5, i7));
                }
            } else {
                view = view2;
                fh.a(view, i4, i6, Math.max(i12, i14) + i4, Math.max(i13, i15) + i6);
                ObjectAnimator objectAnimatorA3 = (i4 == i5 && i6 == i7) ? null : ig.a(view, S, g().a(i4, i6, i5, i7));
                if (rect4 == null) {
                    i3 = 0;
                    rect = new Rect(0, 0, i12, i13);
                } else {
                    i3 = 0;
                    rect = rect4;
                }
                Rect rect6 = rect5 == null ? new Rect(i3, i3, i14, i15) : rect5;
                if (rect.equals(rect6)) {
                    objectAnimator = null;
                } else {
                    lb.a(view, rect);
                    lg lgVar = T;
                    Object[] objArr = new Object[2];
                    objArr[i3] = rect;
                    objArr[1] = rect6;
                    ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(view, "clipBounds", lgVar, objArr);
                    objectAnimatorOfObject.addListener(new i(view, rect5, i5, i7, i9, i11));
                    objectAnimator = objectAnimatorOfObject;
                }
                animatorA = sg.a(objectAnimatorA3, objectAnimator);
            }
            if (view.getParent() instanceof ViewGroup) {
                ViewGroup viewGroup4 = (ViewGroup) view.getParent();
                zg.a(viewGroup4, true);
                a(new j(viewGroup4));
            }
            return animatorA;
        }
        int iIntValue = ((Integer) tgVar.f9283a.get("android:changeBounds:windowX")).intValue();
        int iIntValue2 = ((Integer) tgVar.f9283a.get("android:changeBounds:windowY")).intValue();
        int iIntValue3 = ((Integer) tgVar2.f9283a.get("android:changeBounds:windowX")).intValue();
        int iIntValue4 = ((Integer) tgVar2.f9283a.get("android:changeBounds:windowY")).intValue();
        if (iIntValue == iIntValue3 && iIntValue2 == iIntValue4) {
            return null;
        }
        viewGroup.getLocationInWindow(this.J);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
        view2.draw(new Canvas(bitmapCreateBitmap));
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmapCreateBitmap);
        float fC = fh.c(view2);
        fh.a(view2, 0.0f);
        fh.b(viewGroup).a(bitmapDrawable);
        PathMotion pathMotionG = g();
        int[] iArr = this.J;
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, kg.a(N, pathMotionG.a(iIntValue - iArr[0], iIntValue2 - iArr[1], iIntValue3 - iArr[0], iIntValue4 - iArr[1])));
        objectAnimatorOfPropertyValuesHolder.addListener(new a(viewGroup, bitmapDrawable, view2, fC));
        return objectAnimatorOfPropertyValuesHolder;
    }
}
