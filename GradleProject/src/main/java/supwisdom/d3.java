package supwisdom;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.ListMenuItemView;
import androidx.appcompat.widget.ListPopupWindow;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: MenuPopupWindow.java */
/* JADX INFO: loaded from: classes.dex */
public class d3 extends ListPopupWindow implements c3 {
    public static Method J;
    public c3 I;

    /* JADX INFO: compiled from: MenuPopupWindow.java */
    public static class a extends z2 {
        public final int o;
        public final int p;
        public c3 q;
        public MenuItem r;

        public a(Context context, boolean z) {
            super(context, z);
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT < 17 || 1 != configuration.getLayoutDirection()) {
                this.o = 22;
                this.p = 21;
            } else {
                this.o = 21;
                this.p = 22;
            }
        }

        @Override // supwisdom.z2, android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            int headersCount;
            v1 v1Var;
            int iPointToPosition;
            int i;
            if (this.q != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    v1Var = (v1) headerViewListAdapter.getWrappedAdapter();
                } else {
                    headersCount = 0;
                    v1Var = (v1) adapter;
                }
                y1 item = null;
                if (motionEvent.getAction() != 10 && (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i = iPointToPosition - headersCount) >= 0 && i < v1Var.getCount()) {
                    item = v1Var.getItem(i);
                }
                MenuItem menuItem = this.r;
                if (menuItem != item) {
                    w1 w1VarB = v1Var.b();
                    if (menuItem != null) {
                        this.q.b(w1VarB, menuItem);
                    }
                    this.r = item;
                    if (item != null) {
                        this.q.a(w1VarB, item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i == this.o) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView == null || i != this.p) {
                return super.onKeyDown(i, keyEvent);
            }
            setSelection(-1);
            ((v1) getAdapter()).b().a(false);
            return true;
        }

        public void setHoverListener(c3 c3Var) {
            this.q = c3Var;
        }

        @Override // supwisdom.z2, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                J = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public d3(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // androidx.appcompat.widget.ListPopupWindow
    public z2 a(Context context, boolean z) {
        a aVar = new a(context, z);
        aVar.setHoverListener(this);
        return aVar;
    }

    public void b(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.E.setExitTransition((Transition) obj);
        }
    }

    public void d(boolean z) {
        if (Build.VERSION.SDK_INT > 28) {
            this.E.setTouchModal(z);
            return;
        }
        Method method = J;
        if (method != null) {
            try {
                method.invoke(this.E, Boolean.valueOf(z));
            } catch (Exception unused) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    public void a(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.E.setEnterTransition((Transition) obj);
        }
    }

    @Override // supwisdom.c3
    public void b(w1 w1Var, MenuItem menuItem) {
        c3 c3Var = this.I;
        if (c3Var != null) {
            c3Var.b(w1Var, menuItem);
        }
    }

    public void a(c3 c3Var) {
        this.I = c3Var;
    }

    @Override // supwisdom.c3
    public void a(w1 w1Var, MenuItem menuItem) {
        c3 c3Var = this.I;
        if (c3Var != null) {
            c3Var.a(w1Var, menuItem);
        }
    }
}
