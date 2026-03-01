package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.ActionMenuView;
import androidx.customview.view.AbsSavedState;
import java.util.ArrayList;
import java.util.List;
import supwisdom.b1;
import supwisdom.c2;
import supwisdom.h2;
import supwisdom.h3;
import supwisdom.j1;
import supwisdom.lb;
import supwisdom.n1;
import supwisdom.p3;
import supwisdom.q3;
import supwisdom.sa;
import supwisdom.v3;
import supwisdom.va;
import supwisdom.w1;
import supwisdom.x2;
import supwisdom.y1;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    public ColorStateList A;
    public boolean B;
    public boolean C;
    public final ArrayList<View> D;
    public final ArrayList<View> E;
    public final int[] F;
    public e G;
    public final ActionMenuView.d H;
    public q3 I;
    public ActionMenuPresenter J;
    public d K;
    public c2.a L;
    public w1.a M;
    public boolean N;
    public final Runnable O;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ActionMenuView f1199a;
    public TextView b;
    public TextView c;
    public ImageButton d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ImageView f1200e;
    public Drawable f;
    public CharSequence g;
    public ImageButton h;
    public View i;
    public Context j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public h3 t;
    public int u;
    public int v;
    public int w;
    public CharSequence x;
    public CharSequence y;
    public ColorStateList z;

    public class a implements ActionMenuView.d {
        public a() {
        }

        @Override // androidx.appcompat.widget.ActionMenuView.d
        public boolean onMenuItemClick(MenuItem menuItem) {
            e eVar = Toolbar.this.G;
            if (eVar != null) {
                return eVar.onMenuItemClick(menuItem);
            }
            return false;
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toolbar.this.r();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toolbar.this.c();
        }
    }

    public interface e {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    private MenuInflater getMenuInflater() {
        return new n1(getContext());
    }

    public void a(w1 w1Var, ActionMenuPresenter actionMenuPresenter) {
        if (w1Var == null && this.f1199a == null) {
            return;
        }
        i();
        w1 w1VarJ = this.f1199a.j();
        if (w1VarJ == w1Var) {
            return;
        }
        if (w1VarJ != null) {
            w1VarJ.b(this.J);
            w1VarJ.b(this.K);
        }
        if (this.K == null) {
            this.K = new d();
        }
        actionMenuPresenter.c(true);
        if (w1Var != null) {
            w1Var.a(actionMenuPresenter, this.j);
            w1Var.a(this.K, this.j);
        } else {
            actionMenuPresenter.a(this.j, (w1) null);
            this.K.a(this.j, (w1) null);
            actionMenuPresenter.a(true);
            this.K.a(true);
        }
        this.f1199a.setPopupTheme(this.k);
        this.f1199a.setPresenter(actionMenuPresenter);
        this.J = actionMenuPresenter;
    }

    public boolean b() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.f1199a) != null && actionMenuView.i();
    }

    public void c() {
        d dVar = this.K;
        y1 y1Var = dVar == null ? null : dVar.b;
        if (y1Var != null) {
            y1Var.collapseActionView();
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
    }

    public void d() {
        ActionMenuView actionMenuView = this.f1199a;
        if (actionMenuView != null) {
            actionMenuView.d();
        }
    }

    public void e() {
        if (this.h == null) {
            AppCompatImageButton appCompatImageButton = new AppCompatImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            this.h = appCompatImageButton;
            appCompatImageButton.setImageDrawable(this.f);
            this.h.setContentDescription(this.g);
            LayoutParams layoutParamsGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            layoutParamsGenerateDefaultLayoutParams.f1081a = 8388611 | (this.n & 112);
            layoutParamsGenerateDefaultLayoutParams.b = 2;
            this.h.setLayoutParams(layoutParamsGenerateDefaultLayoutParams);
            this.h.setOnClickListener(new c());
        }
    }

    public final void f() {
        if (this.t == null) {
            this.t = new h3();
        }
    }

    public final void g() {
        if (this.f1200e == null) {
            this.f1200e = new AppCompatImageView(getContext());
        }
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.h;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.h;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        h3 h3Var = this.t;
        if (h3Var != null) {
            return h3Var.a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i = this.v;
        return i != Integer.MIN_VALUE ? i : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        h3 h3Var = this.t;
        if (h3Var != null) {
            return h3Var.b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        h3 h3Var = this.t;
        if (h3Var != null) {
            return h3Var.c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        h3 h3Var = this.t;
        if (h3Var != null) {
            return h3Var.d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i = this.u;
        return i != Integer.MIN_VALUE ? i : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        w1 w1VarJ;
        ActionMenuView actionMenuView = this.f1199a;
        return actionMenuView != null && (w1VarJ = actionMenuView.j()) != null && w1VarJ.hasVisibleItems() ? Math.max(getContentInsetEnd(), Math.max(this.v, 0)) : getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        return lb.n(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return lb.n(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.u, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.f1200e;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.f1200e;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        h();
        return this.f1199a.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.d;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.d;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.J;
    }

    public Drawable getOverflowIcon() {
        h();
        return this.f1199a.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.j;
    }

    public int getPopupTheme() {
        return this.k;
    }

    public CharSequence getSubtitle() {
        return this.y;
    }

    public final TextView getSubtitleTextView() {
        return this.c;
    }

    public CharSequence getTitle() {
        return this.x;
    }

    public int getTitleMarginBottom() {
        return this.s;
    }

    public int getTitleMarginEnd() {
        return this.q;
    }

    public int getTitleMarginStart() {
        return this.p;
    }

    public int getTitleMarginTop() {
        return this.r;
    }

    public final TextView getTitleTextView() {
        return this.b;
    }

    public x2 getWrapper() {
        if (this.I == null) {
            this.I = new q3(this, true);
        }
        return this.I;
    }

    public final void h() {
        i();
        if (this.f1199a.j() == null) {
            w1 w1Var = (w1) this.f1199a.getMenu();
            if (this.K == null) {
                this.K = new d();
            }
            this.f1199a.setExpandedActionViewsExclusive(true);
            w1Var.a(this.K, this.j);
        }
    }

    public final void i() {
        if (this.f1199a == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.f1199a = actionMenuView;
            actionMenuView.setPopupTheme(this.k);
            this.f1199a.setOnMenuItemClickListener(this.H);
            this.f1199a.a(this.L, this.M);
            LayoutParams layoutParamsGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            layoutParamsGenerateDefaultLayoutParams.f1081a = 8388613 | (this.n & 112);
            this.f1199a.setLayoutParams(layoutParamsGenerateDefaultLayoutParams);
            a((View) this.f1199a, false);
        }
    }

    public final void j() {
        if (this.d == null) {
            this.d = new AppCompatImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            LayoutParams layoutParamsGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            layoutParamsGenerateDefaultLayoutParams.f1081a = 8388611 | (this.n & 112);
            this.d.setLayoutParams(layoutParamsGenerateDefaultLayoutParams);
        }
    }

    public boolean k() {
        d dVar = this.K;
        return (dVar == null || dVar.b == null) ? false : true;
    }

    public boolean l() {
        ActionMenuView actionMenuView = this.f1199a;
        return actionMenuView != null && actionMenuView.f();
    }

    public boolean m() {
        ActionMenuView actionMenuView = this.f1199a;
        return actionMenuView != null && actionMenuView.g();
    }

    public boolean n() {
        ActionMenuView actionMenuView = this.f1199a;
        return actionMenuView != null && actionMenuView.h();
    }

    public final void o() {
        removeCallbacks(this.O);
        post(this.O);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.O);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.C = false;
        }
        if (!this.C) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.C = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.C = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x02a3 A[LOOP:0: B:104:0x02a1->B:105:0x02a3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02c5 A[LOOP:1: B:107:0x02c3->B:108:0x02c5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02fe A[LOOP:2: B:116:0x02fc->B:117:0x02fe, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0229  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 787
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        char c2;
        char c3;
        int measuredWidth;
        int iMax;
        int iCombineMeasuredStates;
        int measuredWidth2;
        int iCombineMeasuredStates2;
        int iMax2;
        int measuredHeight;
        int[] iArr = this.F;
        if (v3.a(this)) {
            c2 = 1;
            c3 = 0;
        } else {
            c2 = 0;
            c3 = 1;
        }
        if (d(this.d)) {
            a(this.d, i, 0, i2, 0, this.o);
            measuredWidth = this.d.getMeasuredWidth() + a(this.d);
            iMax = Math.max(0, this.d.getMeasuredHeight() + b(this.d));
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.d.getMeasuredState());
        } else {
            measuredWidth = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (d(this.h)) {
            a(this.h, i, 0, i2, 0, this.o);
            measuredWidth = this.h.getMeasuredWidth() + a(this.h);
            iMax = Math.max(iMax, this.h.getMeasuredHeight() + b(this.h));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.h.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = 0 + Math.max(currentContentInsetStart, measuredWidth);
        iArr[c2] = Math.max(0, currentContentInsetStart - measuredWidth);
        if (d(this.f1199a)) {
            a(this.f1199a, i, iMax3, i2, 0, this.o);
            measuredWidth2 = this.f1199a.getMeasuredWidth() + a(this.f1199a);
            iMax = Math.max(iMax, this.f1199a.getMeasuredHeight() + b(this.f1199a));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f1199a.getMeasuredState());
        } else {
            measuredWidth2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax4 = iMax3 + Math.max(currentContentInsetEnd, measuredWidth2);
        iArr[c3] = Math.max(0, currentContentInsetEnd - measuredWidth2);
        if (d(this.i)) {
            iMax4 += a(this.i, i, iMax4, i2, 0, iArr);
            iMax = Math.max(iMax, this.i.getMeasuredHeight() + b(this.i));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.i.getMeasuredState());
        }
        if (d(this.f1200e)) {
            iMax4 += a(this.f1200e, i, iMax4, i2, 0, iArr);
            iMax = Math.max(iMax, this.f1200e.getMeasuredHeight() + b(this.f1200e));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f1200e.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (((LayoutParams) childAt.getLayoutParams()).b == 0 && d(childAt)) {
                iMax4 += a(childAt, i, iMax4, i2, 0, iArr);
                iMax = Math.max(iMax, childAt.getMeasuredHeight() + b(childAt));
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i4 = this.r + this.s;
        int i5 = this.p + this.q;
        if (d(this.b)) {
            a(this.b, i, iMax4 + i5, i2, i4, iArr);
            int measuredWidth3 = this.b.getMeasuredWidth() + a(this.b);
            measuredHeight = this.b.getMeasuredHeight() + b(this.b);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.b.getMeasuredState());
            iMax2 = measuredWidth3;
        } else {
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
            measuredHeight = 0;
        }
        if (d(this.c)) {
            iMax2 = Math.max(iMax2, a(this.c, i, iMax4 + i5, i2, measuredHeight + i4, iArr));
            measuredHeight += this.c.getMeasuredHeight() + b(this.c);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.c.getMeasuredState());
        }
        int iMax5 = Math.max(iMax, measuredHeight);
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax4 + iMax2 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, (-16777216) & iCombineMeasuredStates2), q() ? 0 : View.resolveSizeAndState(Math.max(iMax5 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        ActionMenuView actionMenuView = this.f1199a;
        w1 w1VarJ = actionMenuView != null ? actionMenuView.j() : null;
        int i = savedState.c;
        if (i != 0 && this.K != null && w1VarJ != null && (menuItemFindItem = w1VarJ.findItem(i)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (savedState.d) {
            o();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i);
        }
        f();
        this.t.a(i == 1);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        y1 y1Var;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        d dVar = this.K;
        if (dVar != null && (y1Var = dVar.b) != null) {
            savedState.c = y1Var.getItemId();
        }
        savedState.d = n();
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.B = false;
        }
        if (!this.B) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.B = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.B = false;
        }
        return true;
    }

    public void p() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((LayoutParams) childAt.getLayoutParams()).b != 2 && childAt != this.f1199a) {
                removeViewAt(childCount);
                this.E.add(childAt);
            }
        }
    }

    public final boolean q() {
        if (!this.N) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (d(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean r() {
        ActionMenuView actionMenuView = this.f1199a;
        return actionMenuView != null && actionMenuView.k();
    }

    public void setCollapseContentDescription(int i) {
        setCollapseContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setCollapseIcon(int i) {
        setCollapseIcon(b1.c(getContext(), i));
    }

    public void setCollapsible(boolean z) {
        this.N = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.v) {
            this.v = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.u) {
            this.u = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i) {
        setLogo(b1.c(getContext(), i));
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(b1.c(getContext(), i));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        j();
        this.d.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.G = eVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        h();
        this.f1199a.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i) {
        if (this.k != i) {
            this.k = i;
            if (i == 0) {
                this.j = getContext();
            } else {
                this.j = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setSubtitle(int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitleTextColor(int i) {
        setSubtitleTextColor(ColorStateList.valueOf(i));
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitleMarginBottom(int i) {
        this.s = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i) {
        this.q = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i) {
        this.p = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i) {
        this.r = i;
        requestLayout();
    }

    public void setTitleTextColor(int i) {
        setTitleTextColor(ColorStateList.valueOf(i));
    }

    public static class LayoutParams extends ActionBar.LayoutParams {
        public int b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = 0;
        }

        public void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.b = 0;
            this.f1081a = 8388627;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ActionBar.LayoutParams) layoutParams);
            this.b = 0;
            this.b = layoutParams.b;
        }

        public LayoutParams(ActionBar.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.b = 0;
            a(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = 0;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.toolbarStyle);
    }

    public void b(Context context, int i) {
        this.l = i;
        TextView textView = this.b;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            e();
        }
        ImageButton imageButton = this.h;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            e();
            this.h.setImageDrawable(drawable);
        } else {
            ImageButton imageButton = this.h;
            if (imageButton != null) {
                imageButton.setImageDrawable(this.f);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            g();
            if (!c(this.f1200e)) {
                a((View) this.f1200e, true);
            }
        } else {
            ImageView imageView = this.f1200e;
            if (imageView != null && c(imageView)) {
                removeView(this.f1200e);
                this.E.remove(this.f1200e);
            }
        }
        ImageView imageView2 = this.f1200e;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        ImageView imageView = this.f1200e;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            j();
        }
        ImageButton imageButton = this.d;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            j();
            if (!c(this.d)) {
                a((View) this.d, true);
            }
        } else {
            ImageButton imageButton = this.d;
            if (imageButton != null && c(imageButton)) {
                removeView(this.d);
                this.E.remove(this.d);
            }
        }
        ImageButton imageButton2 = this.d;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.c;
            if (textView != null && c(textView)) {
                removeView(this.c);
                this.E.remove(this.c);
            }
        } else {
            if (this.c == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.c = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.c.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.m;
                if (i != 0) {
                    this.c.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.A;
                if (colorStateList != null) {
                    this.c.setTextColor(colorStateList);
                }
            }
            if (!c(this.c)) {
                a((View) this.c, true);
            }
        }
        TextView textView2 = this.c;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.y = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.A = colorStateList;
        TextView textView = this.c;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.b;
            if (textView != null && c(textView)) {
                removeView(this.b);
                this.E.remove(this.b);
            }
        } else {
            if (this.b == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.b = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.b.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.l;
                if (i != 0) {
                    this.b.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.z;
                if (colorStateList != null) {
                    this.b.setTextColor(colorStateList);
                }
            }
            if (!c(this.b)) {
                a((View) this.b, true);
            }
        }
        TextView textView2 = this.b;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.x = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.z = colorStateList;
        TextView textView = this.b;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int c;
        public boolean d;

        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.c = parcel.readInt();
            this.d = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public class d implements c2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public w1 f1204a;
        public y1 b;

        public d() {
        }

        @Override // supwisdom.c2
        public void a(Context context, w1 w1Var) {
            y1 y1Var;
            w1 w1Var2 = this.f1204a;
            if (w1Var2 != null && (y1Var = this.b) != null) {
                w1Var2.a(y1Var);
            }
            this.f1204a = w1Var;
        }

        @Override // supwisdom.c2
        public void a(Parcelable parcelable) {
        }

        @Override // supwisdom.c2
        public void a(c2.a aVar) {
        }

        @Override // supwisdom.c2
        public void a(w1 w1Var, boolean z) {
        }

        @Override // supwisdom.c2
        public boolean a() {
            return false;
        }

        @Override // supwisdom.c2
        public boolean a(h2 h2Var) {
            return false;
        }

        @Override // supwisdom.c2
        public Parcelable b() {
            return null;
        }

        @Override // supwisdom.c2
        public boolean b(w1 w1Var, y1 y1Var) {
            Toolbar.this.e();
            ViewParent parent = Toolbar.this.h.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.h);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.h);
            }
            Toolbar.this.i = y1Var.getActionView();
            this.b = y1Var;
            ViewParent parent2 = Toolbar.this.i.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.i);
                }
                LayoutParams layoutParamsGenerateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                layoutParamsGenerateDefaultLayoutParams.f1081a = 8388611 | (toolbar4.n & 112);
                layoutParamsGenerateDefaultLayoutParams.b = 2;
                toolbar4.i.setLayoutParams(layoutParamsGenerateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.i);
            }
            Toolbar.this.p();
            Toolbar.this.requestLayout();
            y1Var.a(true);
            KeyEvent.Callback callback = Toolbar.this.i;
            if (callback instanceof j1) {
                ((j1) callback).a();
            }
            return true;
        }

        @Override // supwisdom.c2
        public int getId() {
            return 0;
        }

        @Override // supwisdom.c2
        public void a(boolean z) {
            if (this.b != null) {
                w1 w1Var = this.f1204a;
                boolean z2 = false;
                if (w1Var != null) {
                    int size = w1Var.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        }
                        if (this.f1204a.getItem(i) == this.b) {
                            z2 = true;
                            break;
                        }
                        i++;
                    }
                }
                if (z2) {
                    return;
                }
                a(this.f1204a, this.b);
            }
        }

        @Override // supwisdom.c2
        public boolean a(w1 w1Var, y1 y1Var) {
            KeyEvent.Callback callback = Toolbar.this.i;
            if (callback instanceof j1) {
                ((j1) callback).b();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.i);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.h);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.i = null;
            toolbar3.a();
            this.b = null;
            Toolbar.this.requestLayout();
            y1Var.a(false);
            return true;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.w = 8388627;
        this.D = new ArrayList<>();
        this.E = new ArrayList<>();
        this.F = new int[2];
        this.H = new a();
        this.O = new b();
        p3 p3VarA = p3.a(getContext(), attributeSet, R.styleable.Toolbar, i, 0);
        lb.a(this, context, R.styleable.Toolbar, attributeSet, p3VarA.a(), i, 0);
        this.l = p3VarA.g(R.styleable.Toolbar_titleTextAppearance, 0);
        this.m = p3VarA.g(R.styleable.Toolbar_subtitleTextAppearance, 0);
        this.w = p3VarA.e(R.styleable.Toolbar_android_gravity, this.w);
        this.n = p3VarA.e(R.styleable.Toolbar_buttonGravity, 48);
        int iB = p3VarA.b(R.styleable.Toolbar_titleMargin, 0);
        iB = p3VarA.g(R.styleable.Toolbar_titleMargins) ? p3VarA.b(R.styleable.Toolbar_titleMargins, iB) : iB;
        this.s = iB;
        this.r = iB;
        this.q = iB;
        this.p = iB;
        int iB2 = p3VarA.b(R.styleable.Toolbar_titleMarginStart, -1);
        if (iB2 >= 0) {
            this.p = iB2;
        }
        int iB3 = p3VarA.b(R.styleable.Toolbar_titleMarginEnd, -1);
        if (iB3 >= 0) {
            this.q = iB3;
        }
        int iB4 = p3VarA.b(R.styleable.Toolbar_titleMarginTop, -1);
        if (iB4 >= 0) {
            this.r = iB4;
        }
        int iB5 = p3VarA.b(R.styleable.Toolbar_titleMarginBottom, -1);
        if (iB5 >= 0) {
            this.s = iB5;
        }
        this.o = p3VarA.c(R.styleable.Toolbar_maxButtonHeight, -1);
        int iB6 = p3VarA.b(R.styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int iB7 = p3VarA.b(R.styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int iC = p3VarA.c(R.styleable.Toolbar_contentInsetLeft, 0);
        int iC2 = p3VarA.c(R.styleable.Toolbar_contentInsetRight, 0);
        f();
        this.t.a(iC, iC2);
        if (iB6 != Integer.MIN_VALUE || iB7 != Integer.MIN_VALUE) {
            this.t.b(iB6, iB7);
        }
        this.u = p3VarA.b(R.styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.v = p3VarA.b(R.styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.f = p3VarA.b(R.styleable.Toolbar_collapseIcon);
        this.g = p3VarA.e(R.styleable.Toolbar_collapseContentDescription);
        CharSequence charSequenceE = p3VarA.e(R.styleable.Toolbar_title);
        if (!TextUtils.isEmpty(charSequenceE)) {
            setTitle(charSequenceE);
        }
        CharSequence charSequenceE2 = p3VarA.e(R.styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(charSequenceE2)) {
            setSubtitle(charSequenceE2);
        }
        this.j = getContext();
        setPopupTheme(p3VarA.g(R.styleable.Toolbar_popupTheme, 0));
        Drawable drawableB = p3VarA.b(R.styleable.Toolbar_navigationIcon);
        if (drawableB != null) {
            setNavigationIcon(drawableB);
        }
        CharSequence charSequenceE3 = p3VarA.e(R.styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(charSequenceE3)) {
            setNavigationContentDescription(charSequenceE3);
        }
        Drawable drawableB2 = p3VarA.b(R.styleable.Toolbar_logo);
        if (drawableB2 != null) {
            setLogo(drawableB2);
        }
        CharSequence charSequenceE4 = p3VarA.e(R.styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(charSequenceE4)) {
            setLogoDescription(charSequenceE4);
        }
        if (p3VarA.g(R.styleable.Toolbar_titleTextColor)) {
            setTitleTextColor(p3VarA.a(R.styleable.Toolbar_titleTextColor));
        }
        if (p3VarA.g(R.styleable.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(p3VarA.a(R.styleable.Toolbar_subtitleTextColor));
        }
        if (p3VarA.g(R.styleable.Toolbar_menu)) {
            c(p3VarA.g(R.styleable.Toolbar_menu, 0));
        }
        p3VarA.b();
    }

    public final boolean d(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void c(int i) {
        getMenuInflater().inflate(i, getMenu());
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ActionBar.LayoutParams) {
            return new LayoutParams((ActionBar.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public final int b(View view, int i, int[] iArr, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin - iArr[1];
        int iMax = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int iA = a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iA, iMax, view.getMeasuredHeight() + iA);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin);
    }

    public final boolean c(View view) {
        return view.getParent() == this || this.E.contains(view);
    }

    public final int b(int i) {
        int i2 = i & 112;
        return (i2 == 16 || i2 == 48 || i2 == 80) ? i2 : this.w & 112;
    }

    public final int b(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public void a(Context context, int i) {
        this.m = i;
        TextView textView = this.c;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    public void a(int i, int i2) {
        f();
        this.t.b(i, i2);
    }

    public final void a(View view, boolean z) {
        LayoutParams layoutParamsGenerateLayoutParams;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParamsGenerateLayoutParams = generateDefaultLayoutParams();
        } else if (!checkLayoutParams(layoutParams)) {
            layoutParamsGenerateLayoutParams = generateLayoutParams(layoutParams);
        } else {
            layoutParamsGenerateLayoutParams = (LayoutParams) layoutParams;
        }
        layoutParamsGenerateLayoutParams.b = 1;
        if (z && this.i != null) {
            view.setLayoutParams(layoutParamsGenerateLayoutParams);
            this.E.add(view);
        } else {
            addView(view, layoutParamsGenerateLayoutParams);
        }
    }

    public final void a(View view, int i, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public final int a(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i5) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + iMax + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    public final int a(List<View> list, int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int size = list.size();
        int i3 = 0;
        int measuredWidth = 0;
        while (i3 < size) {
            View view = list.get(i3);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int i4 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin - i;
            int i5 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin - i2;
            int iMax = Math.max(0, i4);
            int iMax2 = Math.max(0, i5);
            int iMax3 = Math.max(0, -i4);
            int iMax4 = Math.max(0, -i5);
            measuredWidth += iMax + view.getMeasuredWidth() + iMax2;
            i3++;
            i2 = iMax4;
            i = iMax3;
        }
        return measuredWidth;
    }

    public final int a(View view, int i, int[] iArr, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin - iArr[0];
        int iMax = i + Math.max(0, i3);
        iArr[0] = Math.max(0, -i3);
        int iA = a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iA, iMax + measuredWidth, view.getMeasuredHeight() + iA);
        return iMax + measuredWidth + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
    }

    public final int a(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int iB = b(layoutParams.f1081a);
        if (iB == 48) {
            return getPaddingTop() - i2;
        }
        if (iB != 80) {
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int height = getHeight();
            int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
            int i3 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            if (iMax < i3) {
                iMax = i3;
            } else {
                int i4 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
                int i5 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                if (i4 < i5) {
                    iMax = Math.max(0, iMax - (i5 - i4));
                }
            }
            return paddingTop + iMax;
        }
        return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin) - i2;
    }

    public final void a(List<View> list, int i) {
        boolean z = lb.n(this) == 1;
        int childCount = getChildCount();
        int iA = sa.a(i, lb.n(this));
        list.clear();
        if (!z) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.b == 0 && d(childAt) && a(layoutParams.f1081a) == iA) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i3 = childCount - 1; i3 >= 0; i3--) {
            View childAt2 = getChildAt(i3);
            LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
            if (layoutParams2.b == 0 && d(childAt2) && a(layoutParams2.f1081a) == iA) {
                list.add(childAt2);
            }
        }
    }

    public final int a(int i) {
        int iN = lb.n(this);
        int iA = sa.a(i, iN) & 7;
        return (iA == 1 || iA == 3 || iA == 5) ? iA : iN == 1 ? 5 : 3;
    }

    public final int a(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return va.b(marginLayoutParams) + va.a(marginLayoutParams);
    }

    public void a() {
        for (int size = this.E.size() - 1; size >= 0; size--) {
            addView(this.E.get(size));
        }
        this.E.clear();
    }

    public void a(c2.a aVar, w1.a aVar2) {
        this.L = aVar;
        this.M = aVar2;
        ActionMenuView actionMenuView = this.f1199a;
        if (actionMenuView != null) {
            actionMenuView.a(aVar, aVar2);
        }
    }
}
