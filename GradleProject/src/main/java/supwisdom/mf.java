package supwisdom;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OrientationHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class mf {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RecyclerView.o f8379a;
    public int b;
    public final Rect c;

    /* JADX INFO: compiled from: OrientationHelper.java */
    public static class a extends mf {
        public a(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // supwisdom.mf
        public int a() {
            return this.f8379a.getWidth();
        }

        @Override // supwisdom.mf
        public int b() {
            return this.f8379a.getWidth() - this.f8379a.getPaddingRight();
        }

        @Override // supwisdom.mf
        public int c(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return this.f8379a.getDecoratedMeasuredHeight(view) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }

        @Override // supwisdom.mf
        public int d(View view) {
            return this.f8379a.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).leftMargin;
        }

        @Override // supwisdom.mf
        public int e(View view) {
            this.f8379a.getTransformedBoundingBox(view, true, this.c);
            return this.c.right;
        }

        @Override // supwisdom.mf
        public int f() {
            return this.f8379a.getPaddingLeft();
        }

        @Override // supwisdom.mf
        public int g() {
            return (this.f8379a.getWidth() - this.f8379a.getPaddingLeft()) - this.f8379a.getPaddingRight();
        }

        @Override // supwisdom.mf
        public void a(int i) {
            this.f8379a.offsetChildrenHorizontal(i);
        }

        @Override // supwisdom.mf
        public int b(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return this.f8379a.getDecoratedMeasuredWidth(view) + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        }

        @Override // supwisdom.mf
        public int f(View view) {
            this.f8379a.getTransformedBoundingBox(view, true, this.c);
            return this.c.left;
        }

        @Override // supwisdom.mf
        public int a(View view) {
            return this.f8379a.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).rightMargin;
        }

        @Override // supwisdom.mf
        public int c() {
            return this.f8379a.getPaddingRight();
        }

        @Override // supwisdom.mf
        public int d() {
            return this.f8379a.getWidthMode();
        }

        @Override // supwisdom.mf
        public int e() {
            return this.f8379a.getHeightMode();
        }
    }

    /* JADX INFO: compiled from: OrientationHelper.java */
    public static class b extends mf {
        public b(RecyclerView.o oVar) {
            super(oVar, null);
        }

        @Override // supwisdom.mf
        public int a() {
            return this.f8379a.getHeight();
        }

        @Override // supwisdom.mf
        public int b() {
            return this.f8379a.getHeight() - this.f8379a.getPaddingBottom();
        }

        @Override // supwisdom.mf
        public int c(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return this.f8379a.getDecoratedMeasuredWidth(view) + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        }

        @Override // supwisdom.mf
        public int d(View view) {
            return this.f8379a.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).topMargin;
        }

        @Override // supwisdom.mf
        public int e(View view) {
            this.f8379a.getTransformedBoundingBox(view, true, this.c);
            return this.c.bottom;
        }

        @Override // supwisdom.mf
        public int f() {
            return this.f8379a.getPaddingTop();
        }

        @Override // supwisdom.mf
        public int g() {
            return (this.f8379a.getHeight() - this.f8379a.getPaddingTop()) - this.f8379a.getPaddingBottom();
        }

        @Override // supwisdom.mf
        public void a(int i) {
            this.f8379a.offsetChildrenVertical(i);
        }

        @Override // supwisdom.mf
        public int b(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return this.f8379a.getDecoratedMeasuredHeight(view) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }

        @Override // supwisdom.mf
        public int f(View view) {
            this.f8379a.getTransformedBoundingBox(view, true, this.c);
            return this.c.top;
        }

        @Override // supwisdom.mf
        public int a(View view) {
            return this.f8379a.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).bottomMargin;
        }

        @Override // supwisdom.mf
        public int c() {
            return this.f8379a.getPaddingBottom();
        }

        @Override // supwisdom.mf
        public int d() {
            return this.f8379a.getHeightMode();
        }

        @Override // supwisdom.mf
        public int e() {
            return this.f8379a.getWidthMode();
        }
    }

    public /* synthetic */ mf(RecyclerView.o oVar, a aVar) {
        this(oVar);
    }

    public static mf a(RecyclerView.o oVar, int i) {
        if (i == 0) {
            return a(oVar);
        }
        if (i == 1) {
            return b(oVar);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public static mf b(RecyclerView.o oVar) {
        return new b(oVar);
    }

    public abstract int a();

    public abstract int a(View view);

    public abstract void a(int i);

    public abstract int b();

    public abstract int b(View view);

    public abstract int c();

    public abstract int c(View view);

    public abstract int d();

    public abstract int d(View view);

    public abstract int e();

    public abstract int e(View view);

    public abstract int f();

    public abstract int f(View view);

    public abstract int g();

    public int h() {
        if (Integer.MIN_VALUE == this.b) {
            return 0;
        }
        return g() - this.b;
    }

    public void i() {
        this.b = g();
    }

    public mf(RecyclerView.o oVar) {
        this.b = Integer.MIN_VALUE;
        this.c = new Rect();
        this.f8379a = oVar;
    }

    public static mf a(RecyclerView.o oVar) {
        return new a(oVar);
    }
}
