package supwisdom;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.transition.R;

/* JADX INFO: compiled from: GhostViewApi14.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ViewConstructor"})
public class cg extends View implements eg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f7204a;
    public ViewGroup b;
    public View c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7205e;
    public int f;
    public Matrix g;
    public final Matrix h;
    public final ViewTreeObserver.OnPreDrawListener i;

    /* JADX INFO: compiled from: GhostViewApi14.java */
    public class a implements ViewTreeObserver.OnPreDrawListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            View view;
            cg cgVar = cg.this;
            cgVar.g = cgVar.f7204a.getMatrix();
            lb.Q(cg.this);
            cg cgVar2 = cg.this;
            ViewGroup viewGroup = cgVar2.b;
            if (viewGroup == null || (view = cgVar2.c) == null) {
                return true;
            }
            viewGroup.endViewTransition(view);
            lb.Q(cg.this.b);
            cg cgVar3 = cg.this;
            cgVar3.b = null;
            cgVar3.c = null;
            return true;
        }
    }

    public cg(View view) {
        super(view.getContext());
        this.h = new Matrix();
        this.i = new a();
        this.f7204a = view;
        setLayerType(2, null);
    }

    public static eg a(View view, ViewGroup viewGroup) {
        cg cgVarA = a(view);
        if (cgVarA == null) {
            FrameLayout frameLayoutA = a(viewGroup);
            if (frameLayoutA == null) {
                return null;
            }
            cgVarA = new cg(view);
            frameLayoutA.addView(cgVarA);
        }
        cgVarA.d++;
        return cgVarA;
    }

    public static void b(View view) {
        cg cgVarA = a(view);
        if (cgVarA != null) {
            int i = cgVarA.d - 1;
            cgVarA.d = i;
            if (i <= 0) {
                ViewParent parent = cgVarA.getParent();
                if (parent instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) parent;
                    viewGroup.endViewTransition(cgVarA);
                    viewGroup.removeView(cgVarA);
                }
            }
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a(this.f7204a, this);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        getLocationOnScreen(iArr);
        this.f7204a.getLocationOnScreen(iArr2);
        iArr2[0] = (int) (iArr2[0] - this.f7204a.getTranslationX());
        iArr2[1] = (int) (iArr2[1] - this.f7204a.getTranslationY());
        this.f7205e = iArr2[0] - iArr[0];
        this.f = iArr2[1] - iArr[1];
        this.f7204a.getViewTreeObserver().addOnPreDrawListener(this.i);
        this.f7204a.setVisibility(4);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        this.f7204a.getViewTreeObserver().removeOnPreDrawListener(this.i);
        this.f7204a.setVisibility(0);
        a(this.f7204a, (cg) null);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        this.h.set(this.g);
        this.h.postTranslate(this.f7205e, this.f);
        canvas.setMatrix(this.h);
        this.f7204a.draw(canvas);
    }

    @Override // android.view.View, supwisdom.eg
    public void setVisibility(int i) {
        super.setVisibility(i);
        this.f7204a.setVisibility(i == 0 ? 4 : 0);
    }

    public static FrameLayout a(ViewGroup viewGroup) {
        while (!(viewGroup instanceof FrameLayout)) {
            ViewParent parent = viewGroup.getParent();
            if (!(parent instanceof ViewGroup)) {
                return null;
            }
            viewGroup = (ViewGroup) parent;
        }
        return (FrameLayout) viewGroup;
    }

    @Override // supwisdom.eg
    public void a(ViewGroup viewGroup, View view) {
        this.b = viewGroup;
        this.c = view;
    }

    public static void a(View view, cg cgVar) {
        view.setTag(R.id.ghost_view, cgVar);
    }

    public static cg a(View view) {
        return (cg) view.getTag(R.id.ghost_view);
    }
}
