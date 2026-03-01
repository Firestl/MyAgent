package supwisdom;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.R;
import supwisdom.w1;

/* JADX INFO: compiled from: PopupMenu.java */
/* JADX INFO: loaded from: classes.dex */
public class e3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w1 f7409a;
    public final View b;
    public final b2 c;
    public d d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f7410e;

    /* JADX INFO: compiled from: PopupMenu.java */
    public class a implements w1.a {
        public a() {
        }

        @Override // supwisdom.w1.a
        public void a(w1 w1Var) {
        }

        @Override // supwisdom.w1.a
        public boolean a(w1 w1Var, MenuItem menuItem) {
            d dVar = e3.this.d;
            if (dVar != null) {
                return dVar.onMenuItemClick(menuItem);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: PopupMenu.java */
    public class b implements PopupWindow.OnDismissListener {
        public b() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            e3 e3Var = e3.this;
            c cVar = e3Var.f7410e;
            if (cVar != null) {
                cVar.a(e3Var);
            }
        }
    }

    /* JADX INFO: compiled from: PopupMenu.java */
    public interface c {
        void a(e3 e3Var);
    }

    /* JADX INFO: compiled from: PopupMenu.java */
    public interface d {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public e3(Context context, View view) {
        this(context, view, 0);
    }

    public Menu a() {
        return this.f7409a;
    }

    public void b() {
        this.c.f();
    }

    public e3(Context context, View view, int i) {
        this(context, view, i, R.attr.popupMenuStyle, 0);
    }

    public void a(d dVar) {
        this.d = dVar;
    }

    public e3(Context context, View view, int i, int i2, int i3) {
        this.b = view;
        w1 w1Var = new w1(context);
        this.f7409a = w1Var;
        w1Var.a(new a());
        b2 b2Var = new b2(context, this.f7409a, view, false, i2, i3);
        this.c = b2Var;
        b2Var.a(i);
        this.c.a(new b());
    }
}
