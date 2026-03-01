package supwisdom;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* JADX INFO: compiled from: MenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a2 implements f2, c2, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Rect f6846a;

    public static boolean b(w1 w1Var) {
        int size = w1Var.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = w1Var.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    public abstract void a(int i);

    @Override // supwisdom.c2
    public void a(Context context, w1 w1Var) {
    }

    public void a(Rect rect) {
        this.f6846a = rect;
    }

    public abstract void a(View view);

    public abstract void a(PopupWindow.OnDismissListener onDismissListener);

    public abstract void a(w1 w1Var);

    @Override // supwisdom.c2
    public boolean a(w1 w1Var, y1 y1Var) {
        return false;
    }

    public abstract void b(int i);

    public abstract void b(boolean z);

    @Override // supwisdom.c2
    public boolean b(w1 w1Var, y1 y1Var) {
        return false;
    }

    public abstract void c(int i);

    public abstract void c(boolean z);

    public boolean c() {
        return true;
    }

    public Rect e() {
        return this.f6846a;
    }

    @Override // supwisdom.c2
    public int getId() {
        return 0;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        a(listAdapter).f9464a.a((MenuItem) listAdapter.getItem(i), this, c() ? 0 : 4);
    }

    public static int a(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        View view = null;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < count; i4++) {
            int itemViewType = listAdapter.getItemViewType(i4);
            if (itemViewType != i3) {
                view = null;
                i3 = itemViewType;
            }
            if (viewGroup == null) {
                viewGroup = new FrameLayout(context);
            }
            view = listAdapter.getView(i4, view, viewGroup);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i) {
                return i;
            }
            if (measuredWidth > i2) {
                i2 = measuredWidth;
            }
        }
        return i2;
    }

    public static v1 a(ListAdapter listAdapter) {
        if (listAdapter instanceof HeaderViewListAdapter) {
            return (v1) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter();
        }
        return (v1) listAdapter;
    }
}
