package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import java.util.ArrayList;
import supwisdom.b2;
import supwisdom.b3;
import supwisdom.c2;
import supwisdom.d2;
import supwisdom.f2;
import supwisdom.h1;
import supwisdom.h2;
import supwisdom.pa;
import supwisdom.r1;
import supwisdom.r3;
import supwisdom.w1;
import supwisdom.y1;
import supwisdom.y8;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuPresenter extends r1 implements pa.a {
    public b A;
    public final f B;
    public int C;
    public d j;
    public Drawable k;
    public boolean l;
    public boolean m;
    public boolean n;
    public int o;
    public int p;
    public int q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public int v;
    public final SparseBooleanArray w;
    public e x;
    public a y;
    public c z;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1128a;

        public class a implements Parcelable.Creator<SavedState> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1128a);
        }

        public SavedState(Parcel parcel) {
            this.f1128a = parcel.readInt();
        }
    }

    public class a extends b2 {
        public a(Context context, h2 h2Var, View view) {
            super(context, h2Var, view, false, R.attr.actionOverflowMenuStyle);
            if (!((y1) h2Var.getItem()).h()) {
                View view2 = ActionMenuPresenter.this.j;
                a(view2 == null ? (View) ActionMenuPresenter.this.h : view2);
            }
            a(ActionMenuPresenter.this.B);
        }

        @Override // supwisdom.b2
        public void e() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.y = null;
            actionMenuPresenter.C = 0;
            super.e();
        }
    }

    public class b extends ActionMenuItemView.b {
        public b() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.b
        public f2 a() {
            a aVar = ActionMenuPresenter.this.y;
            if (aVar != null) {
                return aVar.c();
            }
            return null;
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e f1130a;

        public c(e eVar) {
            this.f1130a = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ActionMenuPresenter.this.c != null) {
                ActionMenuPresenter.this.c.a();
            }
            View view = (View) ActionMenuPresenter.this.h;
            if (view != null && view.getWindowToken() != null && this.f1130a.g()) {
                ActionMenuPresenter.this.x = this.f1130a;
            }
            ActionMenuPresenter.this.z = null;
        }
    }

    public class d extends AppCompatImageView implements ActionMenuView.a {

        public class a extends b3 {
            public a(View view, ActionMenuPresenter actionMenuPresenter) {
                super(view);
            }

            @Override // supwisdom.b3
            public f2 b() {
                e eVar = ActionMenuPresenter.this.x;
                if (eVar == null) {
                    return null;
                }
                return eVar.c();
            }

            @Override // supwisdom.b3
            public boolean c() {
                ActionMenuPresenter.this.j();
                return true;
            }

            @Override // supwisdom.b3
            public boolean d() {
                ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
                if (actionMenuPresenter.z != null) {
                    return false;
                }
                actionMenuPresenter.f();
                return true;
            }
        }

        public d(Context context) {
            super(context, null, R.attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            r3.a(this, getContentDescription());
            setOnTouchListener(new a(this, ActionMenuPresenter.this));
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean a() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean b() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.j();
            return true;
        }

        @Override // android.widget.ImageView
        public boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                y8.a(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    public class e extends b2 {
        public e(Context context, w1 w1Var, View view, boolean z) {
            super(context, w1Var, view, z, R.attr.actionOverflowMenuStyle);
            a(8388613);
            a(ActionMenuPresenter.this.B);
        }

        @Override // supwisdom.b2
        public void e() {
            if (ActionMenuPresenter.this.c != null) {
                ActionMenuPresenter.this.c.close();
            }
            ActionMenuPresenter.this.x = null;
            super.e();
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
        this.w = new SparseBooleanArray();
        this.B = new f();
    }

    public boolean h() {
        return this.z != null || i();
    }

    public boolean i() {
        e eVar = this.x;
        return eVar != null && eVar.d();
    }

    public boolean j() {
        w1 w1Var;
        if (!this.m || i() || (w1Var = this.c) == null || this.h == null || this.z != null || w1Var.j().isEmpty()) {
            return false;
        }
        c cVar = new c(new e(this.b, this.c, this.j, true));
        this.z = cVar;
        ((View) this.h).post(cVar);
        return true;
    }

    @Override // supwisdom.r1, supwisdom.c2
    public void a(Context context, w1 w1Var) {
        super.a(context, w1Var);
        Resources resources = context.getResources();
        h1 h1VarA = h1.a(context);
        if (!this.n) {
            this.m = h1VarA.g();
        }
        if (!this.t) {
            this.o = h1VarA.b();
        }
        if (!this.r) {
            this.q = h1VarA.c();
        }
        int measuredWidth = this.o;
        if (this.m) {
            if (this.j == null) {
                d dVar = new d(this.f8995a);
                this.j = dVar;
                if (this.l) {
                    dVar.setImageDrawable(this.k);
                    this.k = null;
                    this.l = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.j.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.j.getMeasuredWidth();
        } else {
            this.j = null;
        }
        this.p = measuredWidth;
        this.v = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    @Override // supwisdom.r1
    public d2 b(ViewGroup viewGroup) {
        d2 d2Var = this.h;
        d2 d2VarB = super.b(viewGroup);
        if (d2Var != d2VarB) {
            ((ActionMenuView) d2VarB).setPresenter(this);
        }
        return d2VarB;
    }

    public void c(boolean z) {
        this.u = z;
    }

    public void d(boolean z) {
        this.m = z;
        this.n = true;
    }

    public Drawable e() {
        d dVar = this.j;
        if (dVar != null) {
            return dVar.getDrawable();
        }
        if (this.l) {
            return this.k;
        }
        return null;
    }

    public boolean f() {
        Object obj;
        c cVar = this.z;
        if (cVar != null && (obj = this.h) != null) {
            ((View) obj).removeCallbacks(cVar);
            this.z = null;
            return true;
        }
        e eVar = this.x;
        if (eVar == null) {
            return false;
        }
        eVar.b();
        return true;
    }

    public boolean g() {
        a aVar = this.y;
        if (aVar == null) {
            return false;
        }
        aVar.b();
        return true;
    }

    public class f implements c2.a {
        public f() {
        }

        @Override // supwisdom.c2.a
        public boolean a(w1 w1Var) {
            if (w1Var == ActionMenuPresenter.this.c) {
                return false;
            }
            ActionMenuPresenter.this.C = ((h2) w1Var).getItem().getItemId();
            c2.a aVarC = ActionMenuPresenter.this.c();
            if (aVarC != null) {
                return aVarC.a(w1Var);
            }
            return false;
        }

        @Override // supwisdom.c2.a
        public void a(w1 w1Var, boolean z) {
            if (w1Var instanceof h2) {
                w1Var.m().a(false);
            }
            c2.a aVarC = ActionMenuPresenter.this.c();
            if (aVarC != null) {
                aVarC.a(w1Var, z);
            }
        }
    }

    public boolean d() {
        return f() | g();
    }

    @Override // supwisdom.c2
    public Parcelable b() {
        SavedState savedState = new SavedState();
        savedState.f1128a = this.C;
        return savedState;
    }

    @Override // supwisdom.pa.a
    public void b(boolean z) {
        if (z) {
            super.a((h2) null);
            return;
        }
        w1 w1Var = this.c;
        if (w1Var != null) {
            w1Var.a(false);
        }
    }

    public void a(Configuration configuration) {
        if (!this.r) {
            this.q = h1.a(this.b).c();
        }
        w1 w1Var = this.c;
        if (w1Var != null) {
            w1Var.c(true);
        }
    }

    public void a(Drawable drawable) {
        d dVar = this.j;
        if (dVar != null) {
            dVar.setImageDrawable(drawable);
        } else {
            this.l = true;
            this.k = drawable;
        }
    }

    @Override // supwisdom.r1
    public View a(y1 y1Var, View view, ViewGroup viewGroup) {
        View actionView = y1Var.getActionView();
        if (actionView == null || y1Var.f()) {
            actionView = super.a(y1Var, view, viewGroup);
        }
        actionView.setVisibility(y1Var.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // supwisdom.r1
    public void a(y1 y1Var, d2.a aVar) {
        aVar.a(y1Var, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.h);
        if (this.A == null) {
            this.A = new b();
        }
        actionMenuItemView.setPopupCallback(this.A);
    }

    @Override // supwisdom.r1
    public boolean a(int i, y1 y1Var) {
        return y1Var.h();
    }

    @Override // supwisdom.r1, supwisdom.c2
    public void a(boolean z) {
        super.a(z);
        ((View) this.h).requestLayout();
        w1 w1Var = this.c;
        boolean z2 = false;
        if (w1Var != null) {
            ArrayList<y1> arrayListC = w1Var.c();
            int size = arrayListC.size();
            for (int i = 0; i < size; i++) {
                pa paVarA = arrayListC.get(i).a();
                if (paVarA != null) {
                    paVarA.a(this);
                }
            }
        }
        w1 w1Var2 = this.c;
        ArrayList<y1> arrayListJ = w1Var2 != null ? w1Var2.j() : null;
        if (this.m && arrayListJ != null) {
            int size2 = arrayListJ.size();
            if (size2 == 1) {
                z2 = !arrayListJ.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.j == null) {
                this.j = new d(this.f8995a);
            }
            ViewGroup viewGroup = (ViewGroup) this.j.getParent();
            if (viewGroup != this.h) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.j);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.h;
                actionMenuView.addView(this.j, actionMenuView.e());
            }
        } else {
            d dVar = this.j;
            if (dVar != null) {
                Object parent = dVar.getParent();
                Object obj = this.h;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.j);
                }
            }
        }
        ((ActionMenuView) this.h).setOverflowReserved(this.m);
    }

    @Override // supwisdom.r1
    public boolean a(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.j) {
            return false;
        }
        return super.a(viewGroup, i);
    }

    @Override // supwisdom.r1, supwisdom.c2
    public boolean a(h2 h2Var) {
        boolean z = false;
        if (!h2Var.hasVisibleItems()) {
            return false;
        }
        h2 h2Var2 = h2Var;
        while (h2Var2.t() != this.c) {
            h2Var2 = (h2) h2Var2.t();
        }
        View viewA = a(h2Var2.getItem());
        if (viewA == null) {
            return false;
        }
        this.C = h2Var.getItem().getItemId();
        int size = h2Var.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            MenuItem item = h2Var.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i++;
        }
        a aVar = new a(this.b, h2Var, viewA);
        this.y = aVar;
        aVar.a(z);
        this.y.f();
        super.a(h2Var);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View a(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.h;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof d2.a) && ((d2.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    @Override // supwisdom.c2
    public boolean a() {
        ArrayList<y1> arrayListN;
        int size;
        int i;
        int iB;
        int i2;
        ActionMenuPresenter actionMenuPresenter = this;
        w1 w1Var = actionMenuPresenter.c;
        View view = null;
        int i3 = 0;
        if (w1Var != null) {
            arrayListN = w1Var.n();
            size = arrayListN.size();
        } else {
            arrayListN = null;
            size = 0;
        }
        int i4 = actionMenuPresenter.q;
        int i5 = actionMenuPresenter.p;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.h;
        boolean z = false;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            y1 y1Var = arrayListN.get(i8);
            if (y1Var.k()) {
                i6++;
            } else if (y1Var.j()) {
                i7++;
            } else {
                z = true;
            }
            if (actionMenuPresenter.u && y1Var.isActionViewExpanded()) {
                i4 = 0;
            }
        }
        if (actionMenuPresenter.m && (z || i7 + i6 > i4)) {
            i4--;
        }
        int i9 = i4 - i6;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.w;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.s) {
            int i10 = actionMenuPresenter.v;
            iB = i5 / i10;
            i = i10 + ((i5 % i10) / iB);
        } else {
            i = 0;
            iB = 0;
        }
        int i11 = 0;
        int i12 = 0;
        while (i11 < size) {
            y1 y1Var2 = arrayListN.get(i11);
            if (y1Var2.k()) {
                View viewA = actionMenuPresenter.a(y1Var2, view, viewGroup);
                if (actionMenuPresenter.s) {
                    iB -= ActionMenuView.b(viewA, i, iB, iMakeMeasureSpec, i3);
                } else {
                    viewA.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                }
                int measuredWidth = viewA.getMeasuredWidth();
                i5 -= measuredWidth;
                if (i12 == 0) {
                    i12 = measuredWidth;
                }
                int groupId = y1Var2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                y1Var2.d(true);
                i2 = size;
            } else if (y1Var2.j()) {
                int groupId2 = y1Var2.getGroupId();
                boolean z2 = sparseBooleanArray.get(groupId2);
                boolean z3 = (i9 > 0 || z2) && i5 > 0 && (!actionMenuPresenter.s || iB > 0);
                boolean z4 = z3;
                i2 = size;
                if (z3) {
                    View viewA2 = actionMenuPresenter.a(y1Var2, null, viewGroup);
                    if (actionMenuPresenter.s) {
                        int iB2 = ActionMenuView.b(viewA2, i, iB, iMakeMeasureSpec, 0);
                        iB -= iB2;
                        if (iB2 == 0) {
                            z4 = false;
                        }
                    } else {
                        viewA2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    }
                    boolean z5 = z4;
                    int measuredWidth2 = viewA2.getMeasuredWidth();
                    i5 -= measuredWidth2;
                    if (i12 == 0) {
                        i12 = measuredWidth2;
                    }
                    z3 = z5 & (!actionMenuPresenter.s ? i5 + i12 <= 0 : i5 < 0);
                }
                if (z3 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z2) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i13 = 0; i13 < i11; i13++) {
                        y1 y1Var3 = arrayListN.get(i13);
                        if (y1Var3.getGroupId() == groupId2) {
                            if (y1Var3.h()) {
                                i9++;
                            }
                            y1Var3.d(false);
                        }
                    }
                }
                if (z3) {
                    i9--;
                }
                y1Var2.d(z3);
            } else {
                i2 = size;
                y1Var2.d(false);
                i11++;
                size = i2;
                view = null;
                i3 = 0;
                actionMenuPresenter = this;
            }
            i11++;
            size = i2;
            view = null;
            i3 = 0;
            actionMenuPresenter = this;
        }
        return true;
    }

    @Override // supwisdom.r1, supwisdom.c2
    public void a(w1 w1Var, boolean z) {
        d();
        super.a(w1Var, z);
    }

    @Override // supwisdom.c2
    public void a(Parcelable parcelable) {
        int i;
        MenuItem menuItemFindItem;
        if ((parcelable instanceof SavedState) && (i = ((SavedState) parcelable).f1128a) > 0 && (menuItemFindItem = this.c.findItem(i)) != null) {
            a((h2) menuItemFindItem.getSubMenu());
        }
    }

    public void a(ActionMenuView actionMenuView) {
        this.h = actionMenuView;
        actionMenuView.a(this.c);
    }
}
