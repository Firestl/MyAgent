package supwisdom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;

/* JADX INFO: compiled from: ViewOverlayApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class ch implements eh {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f7210a;

    public ch(Context context, ViewGroup viewGroup, View view) {
        this.f7210a = new a(context, viewGroup, view, this);
    }

    public static ch c(View view) {
        ViewGroup viewGroupD = d(view);
        if (viewGroupD == null) {
            return null;
        }
        int childCount = viewGroupD.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroupD.getChildAt(i);
            if (childAt instanceof a) {
                return ((a) childAt).d;
            }
        }
        return new wg(viewGroupD.getContext(), viewGroupD, view);
    }

    public static ViewGroup d(View view) {
        while (view != null) {
            if (view.getId() == 16908290 && (view instanceof ViewGroup)) {
                return (ViewGroup) view;
            }
            if (view.getParent() instanceof ViewGroup) {
                view = (ViewGroup) view.getParent();
            }
        }
        return null;
    }

    @Override // supwisdom.eh
    public void a(Drawable drawable) {
        this.f7210a.a(drawable);
    }

    @Override // supwisdom.eh
    public void b(Drawable drawable) {
        this.f7210a.b(drawable);
    }

    /* JADX INFO: compiled from: ViewOverlayApi14.java */
    public static class a extends ViewGroup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ViewGroup f7211a;
        public View b;
        public ArrayList<Drawable> c;
        public ch d;

        static {
            try {
                ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", Integer.TYPE, Integer.TYPE, Rect.class);
            } catch (NoSuchMethodException unused) {
            }
        }

        public a(Context context, ViewGroup viewGroup, View view, ch chVar) {
            super(context);
            this.c = null;
            this.f7211a = viewGroup;
            this.b = view;
            setRight(viewGroup.getWidth());
            setBottom(viewGroup.getHeight());
            viewGroup.addView(this);
            this.d = chVar;
        }

        public void a(Drawable drawable) {
            if (this.c == null) {
                this.c = new ArrayList<>();
            }
            if (this.c.contains(drawable)) {
                return;
            }
            this.c.add(drawable);
            invalidate(drawable.getBounds());
            drawable.setCallback(this);
        }

        public void b(Drawable drawable) {
            ArrayList<Drawable> arrayList = this.c;
            if (arrayList != null) {
                arrayList.remove(drawable);
                invalidate(drawable.getBounds());
                drawable.setCallback(null);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas canvas) {
            this.f7211a.getLocationOnScreen(new int[2]);
            this.b.getLocationOnScreen(new int[2]);
            canvas.translate(r0[0] - r1[0], r0[1] - r1[1]);
            canvas.clipRect(new Rect(0, 0, this.b.getWidth(), this.b.getHeight()));
            super.dispatchDraw(canvas);
            ArrayList<Drawable> arrayList = this.c;
            int size = arrayList == null ? 0 : arrayList.size();
            for (int i = 0; i < size; i++) {
                this.c.get(i).draw(canvas);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
            if (this.f7211a == null) {
                return null;
            }
            rect.offset(iArr[0], iArr[1]);
            if (!(this.f7211a instanceof ViewGroup)) {
                invalidate(rect);
                return null;
            }
            iArr[0] = 0;
            iArr[1] = 0;
            int[] iArr2 = new int[2];
            a(iArr2);
            rect.offset(iArr2[0], iArr2[1]);
            return super.invalidateChildInParent(iArr, rect);
        }

        @Override // android.view.View, android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            invalidate(drawable.getBounds());
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable drawable) {
            ArrayList<Drawable> arrayList;
            return super.verifyDrawable(drawable) || ((arrayList = this.c) != null && arrayList.contains(drawable));
        }

        public void b(View view) {
            super.removeView(view);
            if (a()) {
                this.f7211a.removeView(this);
            }
        }

        public void a(View view) {
            if (view.getParent() instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (viewGroup != this.f7211a && viewGroup.getParent() != null && lb.K(viewGroup)) {
                    int[] iArr = new int[2];
                    int[] iArr2 = new int[2];
                    viewGroup.getLocationOnScreen(iArr);
                    this.f7211a.getLocationOnScreen(iArr2);
                    lb.e(view, iArr[0] - iArr2[0]);
                    lb.f(view, iArr[1] - iArr2[1]);
                }
                viewGroup.removeView(view);
                if (view.getParent() != null) {
                    viewGroup.removeView(view);
                }
            }
            super.addView(view, getChildCount() - 1);
        }

        public boolean a() {
            ArrayList<Drawable> arrayList;
            return getChildCount() == 0 && ((arrayList = this.c) == null || arrayList.size() == 0);
        }

        public final void a(int[] iArr) {
            int[] iArr2 = new int[2];
            int[] iArr3 = new int[2];
            this.f7211a.getLocationOnScreen(iArr2);
            this.b.getLocationOnScreen(iArr3);
            iArr[0] = iArr3[0] - iArr2[0];
            iArr[1] = iArr3[1] - iArr2[1];
        }
    }
}
