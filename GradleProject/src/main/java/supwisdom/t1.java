package supwisdom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import supwisdom.c2;

/* JADX INFO: compiled from: CascadingMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
public final class t1 extends a2 implements c2, View.OnKeyListener, PopupWindow.OnDismissListener {
    public static final int B = R.layout.abc_cascading_menu_item_layout;
    public boolean A;
    public final Context b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9226e;
    public final boolean f;
    public final Handler g;
    public View o;
    public View p;
    public boolean r;
    public boolean s;
    public int t;
    public int u;
    public boolean w;
    public c2.a x;
    public ViewTreeObserver y;
    public PopupWindow.OnDismissListener z;
    public final List<w1> h = new ArrayList();
    public final List<d> i = new ArrayList();
    public final ViewTreeObserver.OnGlobalLayoutListener j = new a();
    public final View.OnAttachStateChangeListener k = new b();
    public final c3 l = new c();
    public int m = 0;
    public int n = 0;
    public boolean v = false;
    public int q = g();

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!t1.this.isShowing() || t1.this.i.size() <= 0 || t1.this.i.get(0).f9231a.k()) {
                return;
            }
            View view = t1.this.p;
            if (view == null || !view.isShown()) {
                t1.this.dismiss();
                return;
            }
            Iterator<d> it = t1.this.i.iterator();
            while (it.hasNext()) {
                it.next().f9231a.show();
            }
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public class b implements View.OnAttachStateChangeListener {
        public b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = t1.this.y;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    t1.this.y = view.getViewTreeObserver();
                }
                t1 t1Var = t1.this;
                t1Var.y.removeGlobalOnLayoutListener(t1Var.j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public class c implements c3 {

        /* JADX INFO: compiled from: CascadingMenuPopup.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ d f9230a;
            public final /* synthetic */ MenuItem b;
            public final /* synthetic */ w1 c;

            public a(d dVar, MenuItem menuItem, w1 w1Var) {
                this.f9230a = dVar;
                this.b = menuItem;
                this.c = w1Var;
            }

            @Override // java.lang.Runnable
            public void run() {
                d dVar = this.f9230a;
                if (dVar != null) {
                    t1.this.A = true;
                    dVar.b.a(false);
                    t1.this.A = false;
                }
                if (this.b.isEnabled() && this.b.hasSubMenu()) {
                    this.c.a(this.b, 4);
                }
            }
        }

        public c() {
        }

        @Override // supwisdom.c3
        public void a(w1 w1Var, MenuItem menuItem) {
            t1.this.g.removeCallbacksAndMessages(null);
            int size = t1.this.i.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (w1Var == t1.this.i.get(i).b) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                return;
            }
            int i2 = i + 1;
            t1.this.g.postAtTime(new a(i2 < t1.this.i.size() ? t1.this.i.get(i2) : null, menuItem, w1Var), w1Var, SystemClock.uptimeMillis() + 200);
        }

        @Override // supwisdom.c3
        public void b(w1 w1Var, MenuItem menuItem) {
            t1.this.g.removeCallbacksAndMessages(w1Var);
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d3 f9231a;
        public final w1 b;
        public final int c;

        public d(d3 d3Var, w1 w1Var, int i) {
            this.f9231a = d3Var;
            this.b = w1Var;
            this.c = i;
        }

        public ListView a() {
            return this.f9231a.d();
        }
    }

    public t1(Context context, View view, int i, int i2, boolean z) {
        this.b = context;
        this.o = view;
        this.d = i;
        this.f9226e = i2;
        this.f = z;
        Resources resources = context.getResources();
        this.c = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.g = new Handler();
    }

    @Override // supwisdom.c2
    public void a(Parcelable parcelable) {
    }

    @Override // supwisdom.a2
    public void a(w1 w1Var) {
        w1Var.a(this, this.b);
        if (isShowing()) {
            d(w1Var);
        } else {
            this.h.add(w1Var);
        }
    }

    @Override // supwisdom.c2
    public boolean a() {
        return false;
    }

    @Override // supwisdom.c2
    public Parcelable b() {
        return null;
    }

    @Override // supwisdom.a2
    public void b(boolean z) {
        this.v = z;
    }

    public final int c(w1 w1Var) {
        int size = this.i.size();
        for (int i = 0; i < size; i++) {
            if (w1Var == this.i.get(i).b) {
                return i;
            }
        }
        return -1;
    }

    @Override // supwisdom.a2
    public boolean c() {
        return false;
    }

    public final int d(int i) {
        List<d> list = this.i;
        ListView listViewA = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        listViewA.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.p.getWindowVisibleDisplayFrame(rect);
        return this.q == 1 ? (iArr[0] + listViewA.getWidth()) + i > rect.right ? 0 : 1 : iArr[0] - i < 0 ? 1 : 0;
    }

    @Override // supwisdom.f2
    public void dismiss() {
        int size = this.i.size();
        if (size > 0) {
            d[] dVarArr = (d[]) this.i.toArray(new d[size]);
            for (int i = size - 1; i >= 0; i--) {
                d dVar = dVarArr[i];
                if (dVar.f9231a.isShowing()) {
                    dVar.f9231a.dismiss();
                }
            }
        }
    }

    public final d3 f() {
        d3 d3Var = new d3(this.b, null, this.d, this.f9226e);
        d3Var.a(this.l);
        d3Var.a((AdapterView.OnItemClickListener) this);
        d3Var.a((PopupWindow.OnDismissListener) this);
        d3Var.a(this.o);
        d3Var.f(this.n);
        d3Var.a(true);
        d3Var.g(2);
        return d3Var;
    }

    public final int g() {
        return lb.n(this.o) == 1 ? 0 : 1;
    }

    @Override // supwisdom.f2
    public boolean isShowing() {
        return this.i.size() > 0 && this.i.get(0).f9231a.isShowing();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        d dVar;
        int size = this.i.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                dVar = null;
                break;
            }
            dVar = this.i.get(i);
            if (!dVar.f9231a.isShowing()) {
                break;
            } else {
                i++;
            }
        }
        if (dVar != null) {
            dVar.b.a(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // supwisdom.f2
    public void show() {
        if (isShowing()) {
            return;
        }
        Iterator<w1> it = this.h.iterator();
        while (it.hasNext()) {
            d(it.next());
        }
        this.h.clear();
        View view = this.o;
        this.p = view;
        if (view != null) {
            boolean z = this.y == null;
            ViewTreeObserver viewTreeObserver = this.p.getViewTreeObserver();
            this.y = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.j);
            }
            this.p.addOnAttachStateChangeListener(this.k);
        }
    }

    @Override // supwisdom.a2
    public void b(int i) {
        this.r = true;
        this.t = i;
    }

    @Override // supwisdom.a2
    public void c(int i) {
        this.s = true;
        this.u = i;
    }

    public final MenuItem a(w1 w1Var, w1 w1Var2) {
        int size = w1Var.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = w1Var.getItem(i);
            if (item.hasSubMenu() && w1Var2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    @Override // supwisdom.a2
    public void c(boolean z) {
        this.w = z;
    }

    public final View a(d dVar, w1 w1Var) {
        v1 v1Var;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemA = a(dVar.b, w1Var);
        if (menuItemA == null) {
            return null;
        }
        ListView listViewA = dVar.a();
        ListAdapter adapter = listViewA.getAdapter();
        int i = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            headersCount = headerViewListAdapter.getHeadersCount();
            v1Var = (v1) headerViewListAdapter.getWrappedAdapter();
        } else {
            v1Var = (v1) adapter;
            headersCount = 0;
        }
        int count = v1Var.getCount();
        while (true) {
            if (i >= count) {
                i = -1;
                break;
            }
            if (menuItemA == v1Var.getItem(i)) {
                break;
            }
            i++;
        }
        if (i != -1 && (firstVisiblePosition = (i + headersCount) - listViewA.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < listViewA.getChildCount()) {
            return listViewA.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    public final void d(w1 w1Var) {
        d dVar;
        View viewA;
        int i;
        int i2;
        int i3;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.b);
        v1 v1Var = new v1(w1Var, layoutInflaterFrom, this.f, B);
        if (!isShowing() && this.v) {
            v1Var.a(true);
        } else if (isShowing()) {
            v1Var.a(a2.b(w1Var));
        }
        int iA = a2.a(v1Var, null, this.b, this.c);
        d3 d3VarF = f();
        d3VarF.a((ListAdapter) v1Var);
        d3VarF.e(iA);
        d3VarF.f(this.n);
        if (this.i.size() > 0) {
            List<d> list = this.i;
            dVar = list.get(list.size() - 1);
            viewA = a(dVar, w1Var);
        } else {
            dVar = null;
            viewA = null;
        }
        if (viewA != null) {
            d3VarF.d(false);
            d3VarF.a((Object) null);
            int iD = d(iA);
            boolean z = iD == 1;
            this.q = iD;
            if (Build.VERSION.SDK_INT >= 26) {
                d3VarF.a(viewA);
                i2 = 0;
                i = 0;
            } else {
                int[] iArr = new int[2];
                this.o.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                viewA.getLocationOnScreen(iArr2);
                if ((this.n & 7) == 5) {
                    iArr[0] = iArr[0] + this.o.getWidth();
                    iArr2[0] = iArr2[0] + viewA.getWidth();
                }
                i = iArr2[0] - iArr[0];
                i2 = iArr2[1] - iArr[1];
            }
            if ((this.n & 5) == 5) {
                if (!z) {
                    iA = viewA.getWidth();
                    i3 = i - iA;
                }
                i3 = i + iA;
            } else {
                if (z) {
                    iA = viewA.getWidth();
                    i3 = i + iA;
                }
                i3 = i - iA;
            }
            d3VarF.a(i3);
            d3VarF.b(true);
            d3VarF.b(i2);
        } else {
            if (this.r) {
                d3VarF.a(this.t);
            }
            if (this.s) {
                d3VarF.b(this.u);
            }
            d3VarF.a(e());
        }
        this.i.add(new d(d3VarF, w1Var, this.q));
        d3VarF.show();
        ListView listViewD = d3VarF.d();
        listViewD.setOnKeyListener(this);
        if (dVar == null && this.w && w1Var.h() != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) listViewD, false);
            TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(w1Var.h());
            listViewD.addHeaderView(frameLayout, null, false);
            d3VarF.show();
        }
    }

    @Override // supwisdom.c2
    public void a(boolean z) {
        Iterator<d> it = this.i.iterator();
        while (it.hasNext()) {
            a2.a(it.next().a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // supwisdom.c2
    public void a(c2.a aVar) {
        this.x = aVar;
    }

    @Override // supwisdom.c2
    public boolean a(h2 h2Var) {
        for (d dVar : this.i) {
            if (h2Var == dVar.b) {
                dVar.a().requestFocus();
                return true;
            }
        }
        if (!h2Var.hasVisibleItems()) {
            return false;
        }
        a((w1) h2Var);
        c2.a aVar = this.x;
        if (aVar != null) {
            aVar.a(h2Var);
        }
        return true;
    }

    @Override // supwisdom.c2
    public void a(w1 w1Var, boolean z) {
        int iC = c(w1Var);
        if (iC < 0) {
            return;
        }
        int i = iC + 1;
        if (i < this.i.size()) {
            this.i.get(i).b.a(false);
        }
        d dVarRemove = this.i.remove(iC);
        dVarRemove.b.b(this);
        if (this.A) {
            dVarRemove.f9231a.b((Object) null);
            dVarRemove.f9231a.d(0);
        }
        dVarRemove.f9231a.dismiss();
        int size = this.i.size();
        if (size > 0) {
            this.q = this.i.get(size - 1).c;
        } else {
            this.q = g();
        }
        if (size != 0) {
            if (z) {
                this.i.get(0).b.a(false);
                return;
            }
            return;
        }
        dismiss();
        c2.a aVar = this.x;
        if (aVar != null) {
            aVar.a(w1Var, true);
        }
        ViewTreeObserver viewTreeObserver = this.y;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.y.removeGlobalOnLayoutListener(this.j);
            }
            this.y = null;
        }
        this.p.removeOnAttachStateChangeListener(this.k);
        this.z.onDismiss();
    }

    @Override // supwisdom.a2
    public void a(int i) {
        if (this.m != i) {
            this.m = i;
            this.n = sa.a(i, lb.n(this.o));
        }
    }

    @Override // supwisdom.a2
    public void a(View view) {
        if (this.o != view) {
            this.o = view;
            this.n = sa.a(this.m, lb.n(view));
        }
    }

    @Override // supwisdom.f2
    public ListView d() {
        if (this.i.isEmpty()) {
            return null;
        }
        return this.i.get(r0.size() - 1).a();
    }

    @Override // supwisdom.a2
    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.z = onDismissListener;
    }
}
