package supwisdom;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import androidx.recyclerview.R;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ItemTouchHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class gf extends RecyclerView.n implements RecyclerView.p {
    public g A;
    public Rect C;
    public long D;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f7720e;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    public f m;
    public int o;
    public int q;
    public RecyclerView r;
    public VelocityTracker t;
    public List<RecyclerView.b0> u;
    public List<Integer> v;
    public ra z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<View> f7719a = new ArrayList();
    public final float[] b = new float[2];
    public RecyclerView.b0 c = null;
    public int l = -1;
    public int n = 0;
    public List<h> p = new ArrayList();
    public final Runnable s = new a();
    public RecyclerView.j w = null;
    public View x = null;
    public int y = -1;
    public final RecyclerView.r B = new b();

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            gf gfVar = gf.this;
            if (gfVar.c == null || !gfVar.f()) {
                return;
            }
            gf gfVar2 = gf.this;
            RecyclerView.b0 b0Var = gfVar2.c;
            if (b0Var != null) {
                gfVar2.b(b0Var);
            }
            gf gfVar3 = gf.this;
            gfVar3.r.removeCallbacks(gfVar3.s);
            lb.a(gf.this.r, this);
        }
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public class c extends h {
        public final /* synthetic */ int n;
        public final /* synthetic */ RecyclerView.b0 o;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(RecyclerView.b0 b0Var, int i, int i2, float f, float f2, float f3, float f4, int i3, RecyclerView.b0 b0Var2) {
            super(b0Var, i, i2, f, f2, f3, f4);
            this.n = i3;
            this.o = b0Var2;
        }

        @Override // supwisdom.gf.h, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            if (this.k) {
                return;
            }
            if (this.n <= 0) {
                gf gfVar = gf.this;
                gfVar.m.clearView(gfVar.r, this.o);
            } else {
                gf.this.f7719a.add(this.o.itemView);
                this.h = true;
                int i = this.n;
                if (i > 0) {
                    gf.this.a(this, i);
                }
            }
            gf gfVar2 = gf.this;
            View view = gfVar2.x;
            View view2 = this.o.itemView;
            if (view == view2) {
                gfVar2.c(view2);
            }
        }
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h f7723a;
        public final /* synthetic */ int b;

        public d(h hVar, int i) {
            this.f7723a = hVar;
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = gf.this.r;
            if (recyclerView == null || !recyclerView.isAttachedToWindow()) {
                return;
            }
            h hVar = this.f7723a;
            if (hVar.k || hVar.f7727e.getAdapterPosition() == -1) {
                return;
            }
            RecyclerView.l itemAnimator = gf.this.r.getItemAnimator();
            if ((itemAnimator == null || !itemAnimator.a((RecyclerView.l.a) null)) && !gf.this.c()) {
                gf.this.m.onSwiped(this.f7723a.f7727e, this.b);
            } else {
                gf.this.r.post(this);
            }
        }
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public class e implements RecyclerView.j {
        public e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.j
        public int a(int i, int i2) {
            gf gfVar = gf.this;
            View view = gfVar.x;
            if (view == null) {
                return i2;
            }
            int iIndexOfChild = gfVar.y;
            if (iIndexOfChild == -1) {
                iIndexOfChild = gfVar.r.indexOfChild(view);
                gf.this.y = iIndexOfChild;
            }
            return i2 == i + (-1) ? iIndexOfChild : i2 < iIndexOfChild ? i2 : i2 + 1;
        }
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public static abstract class f {
        public static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
        public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
        public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
        public static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000;
        public static final int RELATIVE_DIR_FLAGS = 3158064;
        public static final Interpolator sDragScrollInterpolator = new a();
        public static final Interpolator sDragViewScrollCapInterpolator = new b();
        public int mCachedMaxScrollSpeed = -1;

        /* JADX INFO: compiled from: ItemTouchHelper.java */
        public static class a implements Interpolator {
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                return f * f * f * f * f;
            }
        }

        /* JADX INFO: compiled from: ItemTouchHelper.java */
        public static class b implements Interpolator {
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                float f2 = f - 1.0f;
                return (f2 * f2 * f2 * f2 * f2) + 1.0f;
            }
        }

        public static int convertToRelativeDirection(int i, int i2) {
            int i3;
            int i4 = i & ABS_HORIZONTAL_DIR_FLAGS;
            if (i4 == 0) {
                return i;
            }
            int i5 = i & (~i4);
            if (i2 == 0) {
                i3 = i4 << 2;
            } else {
                int i6 = i4 << 1;
                i5 |= (-789517) & i6;
                i3 = (i6 & ABS_HORIZONTAL_DIR_FLAGS) << 2;
            }
            return i5 | i3;
        }

        public static hf getDefaultUIUtil() {
            return Cif.f7952a;
        }

        private int getMaxDragScroll(RecyclerView recyclerView) {
            if (this.mCachedMaxScrollSpeed == -1) {
                this.mCachedMaxScrollSpeed = recyclerView.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.mCachedMaxScrollSpeed;
        }

        public static int makeFlag(int i, int i2) {
            return i2 << (i * 8);
        }

        public static int makeMovementFlags(int i, int i2) {
            return makeFlag(2, i) | makeFlag(1, i2) | makeFlag(0, i2 | i);
        }

        public boolean canDropOver(RecyclerView recyclerView, RecyclerView.b0 b0Var, RecyclerView.b0 b0Var2) {
            return true;
        }

        public RecyclerView.b0 chooseDropTarget(RecyclerView.b0 b0Var, List<RecyclerView.b0> list, int i, int i2) {
            int bottom;
            int iAbs;
            int top;
            int iAbs2;
            int left;
            int iAbs3;
            int right;
            int iAbs4;
            int width = i + b0Var.itemView.getWidth();
            int height = i2 + b0Var.itemView.getHeight();
            int left2 = i - b0Var.itemView.getLeft();
            int top2 = i2 - b0Var.itemView.getTop();
            int size = list.size();
            RecyclerView.b0 b0Var2 = null;
            int i3 = -1;
            for (int i4 = 0; i4 < size; i4++) {
                RecyclerView.b0 b0Var3 = list.get(i4);
                if (left2 > 0 && (right = b0Var3.itemView.getRight() - width) < 0 && b0Var3.itemView.getRight() > b0Var.itemView.getRight() && (iAbs4 = Math.abs(right)) > i3) {
                    b0Var2 = b0Var3;
                    i3 = iAbs4;
                }
                if (left2 < 0 && (left = b0Var3.itemView.getLeft() - i) > 0 && b0Var3.itemView.getLeft() < b0Var.itemView.getLeft() && (iAbs3 = Math.abs(left)) > i3) {
                    b0Var2 = b0Var3;
                    i3 = iAbs3;
                }
                if (top2 < 0 && (top = b0Var3.itemView.getTop() - i2) > 0 && b0Var3.itemView.getTop() < b0Var.itemView.getTop() && (iAbs2 = Math.abs(top)) > i3) {
                    b0Var2 = b0Var3;
                    i3 = iAbs2;
                }
                if (top2 > 0 && (bottom = b0Var3.itemView.getBottom() - height) < 0 && b0Var3.itemView.getBottom() > b0Var.itemView.getBottom() && (iAbs = Math.abs(bottom)) > i3) {
                    b0Var2 = b0Var3;
                    i3 = iAbs;
                }
            }
            return b0Var2;
        }

        public void clearView(RecyclerView recyclerView, RecyclerView.b0 b0Var) {
            Cif.f7952a.a(b0Var.itemView);
        }

        public int convertToAbsoluteDirection(int i, int i2) {
            int i3;
            int i4 = i & RELATIVE_DIR_FLAGS;
            if (i4 == 0) {
                return i;
            }
            int i5 = i & (~i4);
            if (i2 == 0) {
                i3 = i4 >> 2;
            } else {
                int i6 = i4 >> 1;
                i5 |= (-3158065) & i6;
                i3 = (i6 & RELATIVE_DIR_FLAGS) >> 2;
            }
            return i5 | i3;
        }

        public final int getAbsoluteMovementFlags(RecyclerView recyclerView, RecyclerView.b0 b0Var) {
            return convertToAbsoluteDirection(getMovementFlags(recyclerView, b0Var), lb.n(recyclerView));
        }

        public long getAnimationDuration(RecyclerView recyclerView, int i, float f, float f2) {
            RecyclerView.l itemAnimator = recyclerView.getItemAnimator();
            return itemAnimator == null ? i == 8 ? 200L : 250L : i == 8 ? itemAnimator.e() : itemAnimator.f();
        }

        public int getBoundingBoxMargin() {
            return 0;
        }

        public float getMoveThreshold(RecyclerView.b0 b0Var) {
            return 0.5f;
        }

        public abstract int getMovementFlags(RecyclerView recyclerView, RecyclerView.b0 b0Var);

        public float getSwipeEscapeVelocity(float f) {
            return f;
        }

        public float getSwipeThreshold(RecyclerView.b0 b0Var) {
            return 0.5f;
        }

        public float getSwipeVelocityThreshold(float f) {
            return f;
        }

        public boolean hasDragFlag(RecyclerView recyclerView, RecyclerView.b0 b0Var) {
            return (getAbsoluteMovementFlags(recyclerView, b0Var) & 16711680) != 0;
        }

        public boolean hasSwipeFlag(RecyclerView recyclerView, RecyclerView.b0 b0Var) {
            return (getAbsoluteMovementFlags(recyclerView, b0Var) & 65280) != 0;
        }

        public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i, int i2, int i3, long j) {
            int iSignum = (int) (((int) (((int) Math.signum(i2)) * getMaxDragScroll(recyclerView) * sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, (Math.abs(i2) * 1.0f) / i)))) * sDragScrollInterpolator.getInterpolation(j <= 2000 ? j / 2000.0f : 1.0f));
            return iSignum == 0 ? i2 > 0 ? 1 : -1 : iSignum;
        }

        public abstract boolean isItemViewSwipeEnabled();

        public abstract boolean isLongPressDragEnabled();

        public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.b0 b0Var, float f, float f2, int i, boolean z) {
            Cif.f7952a.b(canvas, recyclerView, b0Var.itemView, f, f2, i, z);
        }

        public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.b0 b0Var, float f, float f2, int i, boolean z) {
            Cif.f7952a.a(canvas, recyclerView, b0Var.itemView, f, f2, i, z);
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.b0 b0Var, List<h> list, int i, float f, float f2) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                h hVar = list.get(i2);
                hVar.c();
                int iSave = canvas.save();
                onChildDraw(canvas, recyclerView, hVar.f7727e, hVar.i, hVar.j, hVar.f, false);
                canvas.restoreToCount(iSave);
            }
            if (b0Var != null) {
                int iSave2 = canvas.save();
                onChildDraw(canvas, recyclerView, b0Var, f, f2, i, true);
                canvas.restoreToCount(iSave2);
            }
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.b0 b0Var, List<h> list, int i, float f, float f2) {
            int size = list.size();
            boolean z = false;
            for (int i2 = 0; i2 < size; i2++) {
                h hVar = list.get(i2);
                int iSave = canvas.save();
                onChildDrawOver(canvas, recyclerView, hVar.f7727e, hVar.i, hVar.j, hVar.f, false);
                canvas.restoreToCount(iSave);
            }
            if (b0Var != null) {
                int iSave2 = canvas.save();
                onChildDrawOver(canvas, recyclerView, b0Var, f, f2, i, true);
                canvas.restoreToCount(iSave2);
            }
            for (int i3 = size - 1; i3 >= 0; i3--) {
                h hVar2 = list.get(i3);
                if (hVar2.l && !hVar2.h) {
                    list.remove(i3);
                } else if (!hVar2.l) {
                    z = true;
                }
            }
            if (z) {
                recyclerView.invalidate();
            }
        }

        public abstract boolean onMove(RecyclerView recyclerView, RecyclerView.b0 b0Var, RecyclerView.b0 b0Var2);

        /* JADX WARN: Multi-variable type inference failed */
        public void onMoved(RecyclerView recyclerView, RecyclerView.b0 b0Var, int i, RecyclerView.b0 b0Var2, int i2, int i3, int i4) {
            RecyclerView.o layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof i) {
                ((i) layoutManager).prepareForDrop(b0Var.itemView, b0Var2.itemView, i3, i4);
                return;
            }
            if (layoutManager.canScrollHorizontally()) {
                if (layoutManager.getDecoratedLeft(b0Var2.itemView) <= recyclerView.getPaddingLeft()) {
                    recyclerView.scrollToPosition(i2);
                }
                if (layoutManager.getDecoratedRight(b0Var2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.scrollToPosition(i2);
                }
            }
            if (layoutManager.canScrollVertically()) {
                if (layoutManager.getDecoratedTop(b0Var2.itemView) <= recyclerView.getPaddingTop()) {
                    recyclerView.scrollToPosition(i2);
                }
                if (layoutManager.getDecoratedBottom(b0Var2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.scrollToPosition(i2);
                }
            }
        }

        public void onSelectedChanged(RecyclerView.b0 b0Var, int i) {
            if (b0Var != null) {
                Cif.f7952a.b(b0Var.itemView);
            }
        }

        public abstract void onSwiped(RecyclerView.b0 b0Var, int i);
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public class g extends GestureDetector.SimpleOnGestureListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7725a = true;

        public g() {
        }

        public void a() {
            this.f7725a = false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            View viewB;
            RecyclerView.b0 childViewHolder;
            if (!this.f7725a || (viewB = gf.this.b(motionEvent)) == null || (childViewHolder = gf.this.r.getChildViewHolder(viewB)) == null) {
                return;
            }
            gf gfVar = gf.this;
            if (gfVar.m.hasDragFlag(gfVar.r, childViewHolder)) {
                int pointerId = motionEvent.getPointerId(0);
                int i = gf.this.l;
                if (pointerId == i) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(i);
                    float x = motionEvent.getX(iFindPointerIndex);
                    float y = motionEvent.getY(iFindPointerIndex);
                    gf gfVar2 = gf.this;
                    gfVar2.d = x;
                    gfVar2.f7720e = y;
                    gfVar2.i = 0.0f;
                    gfVar2.h = 0.0f;
                    if (gfVar2.m.isLongPressDragEnabled()) {
                        gf.this.c(childViewHolder, 2);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public static class h implements Animator.AnimatorListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final float f7726a;
        public final float b;
        public final float c;
        public final float d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final RecyclerView.b0 f7727e;
        public final int f;
        public final ValueAnimator g;
        public boolean h;
        public float i;
        public float j;
        public boolean k = false;
        public boolean l = false;
        public float m;

        /* JADX INFO: compiled from: ItemTouchHelper.java */
        public class a implements ValueAnimator.AnimatorUpdateListener {
            public a() {
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                h.this.a(valueAnimator.getAnimatedFraction());
            }
        }

        public h(RecyclerView.b0 b0Var, int i, int i2, float f, float f2, float f3, float f4) {
            this.f = i2;
            this.f7727e = b0Var;
            this.f7726a = f;
            this.b = f2;
            this.c = f3;
            this.d = f4;
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.g = valueAnimatorOfFloat;
            valueAnimatorOfFloat.addUpdateListener(new a());
            this.g.setTarget(b0Var.itemView);
            this.g.addListener(this);
            a(0.0f);
        }

        public void a(long j) {
            this.g.setDuration(j);
        }

        public void b() {
            this.f7727e.setIsRecyclable(false);
            this.g.start();
        }

        public void c() {
            float f = this.f7726a;
            float f2 = this.c;
            if (f == f2) {
                this.i = this.f7727e.itemView.getTranslationX();
            } else {
                this.i = f + (this.m * (f2 - f));
            }
            float f3 = this.b;
            float f4 = this.d;
            if (f3 == f4) {
                this.j = this.f7727e.itemView.getTranslationY();
            } else {
                this.j = f3 + (this.m * (f4 - f3));
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            a(1.0f);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.l) {
                this.f7727e.setIsRecyclable(true);
            }
            this.l = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        public void a() {
            this.g.cancel();
        }

        public void a(float f) {
            this.m = f;
        }
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public interface i {
        void prepareForDrop(View view, View view2, int i, int i2);
    }

    public gf(f fVar) {
        this.m = fVar;
    }

    public static boolean a(View view, float f2, float f3, float f4, float f5) {
        return f2 >= f4 && f2 <= f4 + ((float) view.getWidth()) && f3 >= f5 && f3 <= f5 + ((float) view.getHeight());
    }

    public final void b() {
        this.r.removeItemDecoration(this);
        this.r.removeOnItemTouchListener(this.B);
        this.r.removeOnChildAttachStateChangeListener(this);
        for (int size = this.p.size() - 1; size >= 0; size--) {
            this.m.clearView(this.r, this.p.get(0).f7727e);
        }
        this.p.clear();
        this.x = null;
        this.y = -1;
        e();
        i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public void b(View view) {
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0122  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(androidx.recyclerview.widget.RecyclerView.b0 r24, int r25) {
        /*
            Method dump skipped, instruction units count: 335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.gf.c(androidx.recyclerview.widget.RecyclerView$b0, int):void");
    }

    public void d() {
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.t = VelocityTracker.obtain();
    }

    public final void e() {
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.t = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean f() {
        /*
            Method dump skipped, instruction units count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.gf.f():boolean");
    }

    public final void g() {
        this.q = ViewConfiguration.get(this.r.getContext()).getScaledTouchSlop();
        this.r.addItemDecoration(this);
        this.r.addOnItemTouchListener(this.B);
        this.r.addOnChildAttachStateChangeListener(this);
        h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.y yVar) {
        rect.setEmpty();
    }

    public final void h() {
        this.A = new g();
        this.z = new ra(this.r.getContext(), this.A);
    }

    public final void i() {
        g gVar = this.A;
        if (gVar != null) {
            gVar.a();
            this.A = null;
        }
        if (this.z != null) {
            this.z = null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.y yVar) {
        float f2;
        float f3;
        this.y = -1;
        if (this.c != null) {
            a(this.b);
            float[] fArr = this.b;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.m.onDraw(canvas, recyclerView, this.c, this.p, this.n, f2, f3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.y yVar) {
        float f2;
        float f3;
        if (this.c != null) {
            a(this.b);
            float[] fArr = this.b;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.m.onDrawOver(canvas, recyclerView, this.c, this.p, this.n, f2, f3);
    }

    public void a(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.r;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            b();
        }
        this.r = recyclerView;
        if (recyclerView != null) {
            Resources resources = recyclerView.getResources();
            this.f = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_velocity);
            this.g = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_max_velocity);
            g();
        }
    }

    public final int d(RecyclerView.b0 b0Var) {
        if (this.n == 2) {
            return 0;
        }
        int movementFlags = this.m.getMovementFlags(this.r, b0Var);
        int iConvertToAbsoluteDirection = (this.m.convertToAbsoluteDirection(movementFlags, lb.n(this.r)) & 65280) >> 8;
        if (iConvertToAbsoluteDirection == 0) {
            return 0;
        }
        int i2 = (movementFlags & 65280) >> 8;
        if (Math.abs(this.h) > Math.abs(this.i)) {
            int iA = a(b0Var, iConvertToAbsoluteDirection);
            if (iA > 0) {
                return (i2 & iA) == 0 ? f.convertToRelativeDirection(iA, lb.n(this.r)) : iA;
            }
            int iB = b(b0Var, iConvertToAbsoluteDirection);
            if (iB > 0) {
                return iB;
            }
        } else {
            int iB2 = b(b0Var, iConvertToAbsoluteDirection);
            if (iB2 > 0) {
                return iB2;
            }
            int iA2 = a(b0Var, iConvertToAbsoluteDirection);
            if (iA2 > 0) {
                return (i2 & iA2) == 0 ? f.convertToRelativeDirection(iA2, lb.n(this.r)) : iA2;
            }
        }
        return 0;
    }

    public final void a(float[] fArr) {
        if ((this.o & 12) != 0) {
            fArr[0] = (this.j + this.h) - this.c.itemView.getLeft();
        } else {
            fArr[0] = this.c.itemView.getTranslationX();
        }
        if ((this.o & 3) != 0) {
            fArr[1] = (this.k + this.i) - this.c.itemView.getTop();
        } else {
            fArr[1] = this.c.itemView.getTranslationY();
        }
    }

    public void b(RecyclerView.b0 b0Var) {
        if (!this.r.isLayoutRequested() && this.n == 2) {
            float moveThreshold = this.m.getMoveThreshold(b0Var);
            int i2 = (int) (this.j + this.h);
            int i3 = (int) (this.k + this.i);
            if (Math.abs(i3 - b0Var.itemView.getTop()) >= b0Var.itemView.getHeight() * moveThreshold || Math.abs(i2 - b0Var.itemView.getLeft()) >= b0Var.itemView.getWidth() * moveThreshold) {
                List<RecyclerView.b0> listA = a(b0Var);
                if (listA.size() == 0) {
                    return;
                }
                RecyclerView.b0 b0VarChooseDropTarget = this.m.chooseDropTarget(b0Var, listA, i2, i3);
                if (b0VarChooseDropTarget == null) {
                    this.u.clear();
                    this.v.clear();
                    return;
                }
                int adapterPosition = b0VarChooseDropTarget.getAdapterPosition();
                int adapterPosition2 = b0Var.getAdapterPosition();
                if (this.m.onMove(this.r, b0Var, b0VarChooseDropTarget)) {
                    this.m.onMoved(this.r, b0Var, adapterPosition2, b0VarChooseDropTarget, adapterPosition, i2, i3);
                }
            }
        }
    }

    public void a(h hVar, int i2) {
        this.r.post(new d(hVar, i2));
    }

    public final List<RecyclerView.b0> a(RecyclerView.b0 b0Var) {
        RecyclerView.b0 b0Var2 = b0Var;
        List<RecyclerView.b0> list = this.u;
        if (list == null) {
            this.u = new ArrayList();
            this.v = new ArrayList();
        } else {
            list.clear();
            this.v.clear();
        }
        int boundingBoxMargin = this.m.getBoundingBoxMargin();
        int iRound = Math.round(this.j + this.h) - boundingBoxMargin;
        int iRound2 = Math.round(this.k + this.i) - boundingBoxMargin;
        int i2 = boundingBoxMargin * 2;
        int width = b0Var2.itemView.getWidth() + iRound + i2;
        int height = b0Var2.itemView.getHeight() + iRound2 + i2;
        int i3 = (iRound + width) / 2;
        int i4 = (iRound2 + height) / 2;
        RecyclerView.o layoutManager = this.r.getLayoutManager();
        int childCount = layoutManager.getChildCount();
        int i5 = 0;
        while (i5 < childCount) {
            View childAt = layoutManager.getChildAt(i5);
            if (childAt != b0Var2.itemView && childAt.getBottom() >= iRound2 && childAt.getTop() <= height && childAt.getRight() >= iRound && childAt.getLeft() <= width) {
                RecyclerView.b0 childViewHolder = this.r.getChildViewHolder(childAt);
                if (this.m.canDropOver(this.r, this.c, childViewHolder)) {
                    int iAbs = Math.abs(i3 - ((childAt.getLeft() + childAt.getRight()) / 2));
                    int iAbs2 = Math.abs(i4 - ((childAt.getTop() + childAt.getBottom()) / 2));
                    int i6 = (iAbs * iAbs) + (iAbs2 * iAbs2);
                    int size = this.u.size();
                    int i7 = 0;
                    for (int i8 = 0; i8 < size && i6 > this.v.get(i8).intValue(); i8++) {
                        i7++;
                    }
                    this.u.add(i7, childViewHolder);
                    this.v.add(i7, Integer.valueOf(i6));
                }
            }
            i5++;
            b0Var2 = b0Var;
        }
        return this.u;
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public class b implements RecyclerView.r {
        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.r
        public void a(RecyclerView recyclerView, MotionEvent motionEvent) {
            gf.this.z.a(motionEvent);
            VelocityTracker velocityTracker = gf.this.t;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            if (gf.this.l == -1) {
                return;
            }
            int actionMasked = motionEvent.getActionMasked();
            int iFindPointerIndex = motionEvent.findPointerIndex(gf.this.l);
            if (iFindPointerIndex >= 0) {
                gf.this.a(actionMasked, motionEvent, iFindPointerIndex);
            }
            gf gfVar = gf.this;
            RecyclerView.b0 b0Var = gfVar.c;
            if (b0Var == null) {
                return;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (iFindPointerIndex >= 0) {
                        gfVar.a(motionEvent, gfVar.o, iFindPointerIndex);
                        gf.this.b(b0Var);
                        gf gfVar2 = gf.this;
                        gfVar2.r.removeCallbacks(gfVar2.s);
                        gf.this.s.run();
                        gf.this.r.invalidate();
                        return;
                    }
                    return;
                }
                if (actionMasked != 3) {
                    if (actionMasked != 6) {
                        return;
                    }
                    int actionIndex = motionEvent.getActionIndex();
                    if (motionEvent.getPointerId(actionIndex) == gf.this.l) {
                        gf.this.l = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
                        gf gfVar3 = gf.this;
                        gfVar3.a(motionEvent, gfVar3.o, actionIndex);
                        return;
                    }
                    return;
                }
                VelocityTracker velocityTracker2 = gfVar.t;
                if (velocityTracker2 != null) {
                    velocityTracker2.clear();
                }
            }
            gf.this.c(null, 0);
            gf.this.l = -1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.r
        public boolean b(RecyclerView recyclerView, MotionEvent motionEvent) {
            int iFindPointerIndex;
            h hVarA;
            gf.this.z.a(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                gf.this.l = motionEvent.getPointerId(0);
                gf.this.d = motionEvent.getX();
                gf.this.f7720e = motionEvent.getY();
                gf.this.d();
                gf gfVar = gf.this;
                if (gfVar.c == null && (hVarA = gfVar.a(motionEvent)) != null) {
                    gf gfVar2 = gf.this;
                    gfVar2.d -= hVarA.i;
                    gfVar2.f7720e -= hVarA.j;
                    gfVar2.a(hVarA.f7727e, true);
                    if (gf.this.f7719a.remove(hVarA.f7727e.itemView)) {
                        gf gfVar3 = gf.this;
                        gfVar3.m.clearView(gfVar3.r, hVarA.f7727e);
                    }
                    gf.this.c(hVarA.f7727e, hVarA.f);
                    gf gfVar4 = gf.this;
                    gfVar4.a(motionEvent, gfVar4.o, 0);
                }
            } else if (actionMasked == 3 || actionMasked == 1) {
                gf gfVar5 = gf.this;
                gfVar5.l = -1;
                gfVar5.c(null, 0);
            } else {
                int i = gf.this.l;
                if (i != -1 && (iFindPointerIndex = motionEvent.findPointerIndex(i)) >= 0) {
                    gf.this.a(actionMasked, motionEvent, iFindPointerIndex);
                }
            }
            VelocityTracker velocityTracker = gf.this.t;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            return gf.this.c != null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.r
        public void a(boolean z) {
            if (z) {
                gf.this.c(null, 0);
            }
        }
    }

    public View b(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        RecyclerView.b0 b0Var = this.c;
        if (b0Var != null) {
            View view = b0Var.itemView;
            if (a(view, x, y, this.j + this.h, this.k + this.i)) {
                return view;
            }
        }
        for (int size = this.p.size() - 1; size >= 0; size--) {
            h hVar = this.p.get(size);
            View view2 = hVar.f7727e.itemView;
            if (a(view2, x, y, hVar.i, hVar.j)) {
                return view2;
            }
        }
        return this.r.findChildViewUnder(x, y);
    }

    public boolean c() {
        int size = this.p.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.p.get(i2).l) {
                return true;
            }
        }
        return false;
    }

    public final int b(RecyclerView.b0 b0Var, int i2) {
        if ((i2 & 3) == 0) {
            return 0;
        }
        int i3 = this.i > 0.0f ? 2 : 1;
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null && this.l > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.m.getSwipeVelocityThreshold(this.g));
            float xVelocity = this.t.getXVelocity(this.l);
            float yVelocity = this.t.getYVelocity(this.l);
            int i4 = yVelocity <= 0.0f ? 1 : 2;
            float fAbs = Math.abs(yVelocity);
            if ((i4 & i2) != 0 && i4 == i3 && fAbs >= this.m.getSwipeEscapeVelocity(this.f) && fAbs > Math.abs(xVelocity)) {
                return i4;
            }
        }
        float height = this.r.getHeight() * this.m.getSwipeThreshold(b0Var);
        if ((i2 & i3) == 0 || Math.abs(this.i) <= height) {
            return 0;
        }
        return i3;
    }

    public final RecyclerView.b0 c(MotionEvent motionEvent) {
        View viewB;
        RecyclerView.o layoutManager = this.r.getLayoutManager();
        int i2 = this.l;
        if (i2 == -1) {
            return null;
        }
        int iFindPointerIndex = motionEvent.findPointerIndex(i2);
        float x = motionEvent.getX(iFindPointerIndex) - this.d;
        float y = motionEvent.getY(iFindPointerIndex) - this.f7720e;
        float fAbs = Math.abs(x);
        float fAbs2 = Math.abs(y);
        int i3 = this.q;
        if (fAbs < i3 && fAbs2 < i3) {
            return null;
        }
        if (fAbs > fAbs2 && layoutManager.canScrollHorizontally()) {
            return null;
        }
        if ((fAbs2 <= fAbs || !layoutManager.canScrollVertically()) && (viewB = b(motionEvent)) != null) {
            return this.r.getChildViewHolder(viewB);
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.p
    public void a(View view) {
        c(view);
        RecyclerView.b0 childViewHolder = this.r.getChildViewHolder(view);
        if (childViewHolder == null) {
            return;
        }
        RecyclerView.b0 b0Var = this.c;
        if (b0Var != null && childViewHolder == b0Var) {
            c(null, 0);
            return;
        }
        a(childViewHolder, false);
        if (this.f7719a.remove(childViewHolder.itemView)) {
            this.m.clearView(this.r, childViewHolder);
        }
    }

    public void c(RecyclerView.b0 b0Var) {
        if (!this.m.hasDragFlag(this.r, b0Var)) {
            Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
            return;
        }
        if (b0Var.itemView.getParent() != this.r) {
            Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
            return;
        }
        d();
        this.i = 0.0f;
        this.h = 0.0f;
        c(b0Var, 2);
    }

    public void a(RecyclerView.b0 b0Var, boolean z) {
        for (int size = this.p.size() - 1; size >= 0; size--) {
            h hVar = this.p.get(size);
            if (hVar.f7727e == b0Var) {
                hVar.k |= z;
                if (!hVar.l) {
                    hVar.a();
                }
                this.p.remove(size);
                return;
            }
        }
    }

    public void c(View view) {
        if (view == this.x) {
            this.x = null;
            if (this.w != null) {
                this.r.setChildDrawingOrderCallback(null);
            }
        }
    }

    public void a(int i2, MotionEvent motionEvent, int i3) {
        RecyclerView.b0 b0VarC;
        int absoluteMovementFlags;
        if (this.c != null || i2 != 2 || this.n == 2 || !this.m.isItemViewSwipeEnabled() || this.r.getScrollState() == 1 || (b0VarC = c(motionEvent)) == null || (absoluteMovementFlags = (this.m.getAbsoluteMovementFlags(this.r, b0VarC) & 65280) >> 8) == 0) {
            return;
        }
        float x = motionEvent.getX(i3);
        float y = motionEvent.getY(i3);
        float f2 = x - this.d;
        float f3 = y - this.f7720e;
        float fAbs = Math.abs(f2);
        float fAbs2 = Math.abs(f3);
        int i4 = this.q;
        if (fAbs >= i4 || fAbs2 >= i4) {
            if (fAbs > fAbs2) {
                if (f2 < 0.0f && (absoluteMovementFlags & 4) == 0) {
                    return;
                }
                if (f2 > 0.0f && (absoluteMovementFlags & 8) == 0) {
                    return;
                }
            } else {
                if (f3 < 0.0f && (absoluteMovementFlags & 1) == 0) {
                    return;
                }
                if (f3 > 0.0f && (absoluteMovementFlags & 2) == 0) {
                    return;
                }
            }
            this.i = 0.0f;
            this.h = 0.0f;
            this.l = motionEvent.getPointerId(0);
            c(b0VarC, 1);
        }
    }

    public h a(MotionEvent motionEvent) {
        if (this.p.isEmpty()) {
            return null;
        }
        View viewB = b(motionEvent);
        for (int size = this.p.size() - 1; size >= 0; size--) {
            h hVar = this.p.get(size);
            if (hVar.f7727e.itemView == viewB) {
                return hVar;
            }
        }
        return null;
    }

    public void a(MotionEvent motionEvent, int i2, int i3) {
        float x = motionEvent.getX(i3);
        float y = motionEvent.getY(i3);
        float f2 = x - this.d;
        this.h = f2;
        this.i = y - this.f7720e;
        if ((i2 & 4) == 0) {
            this.h = Math.max(0.0f, f2);
        }
        if ((i2 & 8) == 0) {
            this.h = Math.min(0.0f, this.h);
        }
        if ((i2 & 1) == 0) {
            this.i = Math.max(0.0f, this.i);
        }
        if ((i2 & 2) == 0) {
            this.i = Math.min(0.0f, this.i);
        }
    }

    public final int a(RecyclerView.b0 b0Var, int i2) {
        if ((i2 & 12) == 0) {
            return 0;
        }
        int i3 = this.h > 0.0f ? 8 : 4;
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null && this.l > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.m.getSwipeVelocityThreshold(this.g));
            float xVelocity = this.t.getXVelocity(this.l);
            float yVelocity = this.t.getYVelocity(this.l);
            int i4 = xVelocity <= 0.0f ? 4 : 8;
            float fAbs = Math.abs(xVelocity);
            if ((i4 & i2) != 0 && i3 == i4 && fAbs >= this.m.getSwipeEscapeVelocity(this.f) && fAbs > Math.abs(yVelocity)) {
                return i4;
            }
        }
        float width = this.r.getWidth() * this.m.getSwipeThreshold(b0Var);
        if ((i2 & i3) == 0 || Math.abs(this.h) <= width) {
            return 0;
        }
        return i3;
    }

    public final void a() {
        if (Build.VERSION.SDK_INT >= 21) {
            return;
        }
        if (this.w == null) {
            this.w = new e();
        }
        this.r.setChildDrawingOrderCallback(this.w);
    }
}
