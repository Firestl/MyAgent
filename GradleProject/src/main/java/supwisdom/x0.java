package supwisdom;

import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import supwisdom.c2;
import supwisdom.w1;

/* JADX INFO: compiled from: ToolbarActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class x0 extends ActionBar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public x2 f9687a;
    public boolean b;
    public Window.Callback c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9688e;
    public ArrayList<ActionBar.a> f = new ArrayList<>();
    public final Runnable g = new a();
    public final Toolbar.e h = new b();

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            x0.this.p();
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public class b implements Toolbar.e {
        public b() {
        }

        @Override // androidx.appcompat.widget.Toolbar.e
        public boolean onMenuItemClick(MenuItem menuItem) {
            return x0.this.c.onMenuItemSelected(0, menuItem);
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public final class d implements w1.a {
        public d() {
        }

        @Override // supwisdom.w1.a
        public void a(w1 w1Var) {
            x0 x0Var = x0.this;
            if (x0Var.c != null) {
                if (x0Var.f9687a.a()) {
                    x0.this.c.onPanelClosed(108, w1Var);
                } else if (x0.this.c.onPreparePanel(0, null, w1Var)) {
                    x0.this.c.onMenuOpened(108, w1Var);
                }
            }
        }

        @Override // supwisdom.w1.a
        public boolean a(w1 w1Var, MenuItem menuItem) {
            return false;
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public class e extends p1 {
        public e(Window.Callback callback) {
            super(callback);
        }

        @Override // supwisdom.p1, android.view.Window.Callback
        public View onCreatePanelView(int i) {
            return i == 0 ? new View(x0.this.f9687a.getContext()) : super.onCreatePanelView(i);
        }

        @Override // supwisdom.p1, android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            boolean zOnPreparePanel = super.onPreparePanel(i, view, menu);
            if (zOnPreparePanel) {
                x0 x0Var = x0.this;
                if (!x0Var.b) {
                    x0Var.f9687a.b();
                    x0.this.b = true;
                }
            }
            return zOnPreparePanel;
        }
    }

    public x0(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        this.f9687a = new q3(toolbar, false);
        e eVar = new e(callback);
        this.c = eVar;
        this.f9687a.setWindowCallback(eVar);
        toolbar.setOnMenuItemClickListener(this.h);
        this.f9687a.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void a(Configuration configuration) {
        super.a(configuration);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void b(boolean z) {
        if (z == this.f9688e) {
            return;
        }
        this.f9688e = z;
        int size = this.f.size();
        for (int i = 0; i < size; i++) {
            this.f.get(i).a(z);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public void c(boolean z) {
    }

    @Override // androidx.appcompat.app.ActionBar
    public void d(boolean z) {
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean e() {
        return this.f9687a.e();
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean f() {
        if (!this.f9687a.h()) {
            return false;
        }
        this.f9687a.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public int g() {
        return this.f9687a.l();
    }

    @Override // androidx.appcompat.app.ActionBar
    public Context h() {
        return this.f9687a.getContext();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void i() {
        this.f9687a.setVisibility(8);
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean j() {
        this.f9687a.k().removeCallbacks(this.g);
        lb.a(this.f9687a.k(), this.g);
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void k() {
        this.f9687a.k().removeCallbacks(this.g);
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean l() {
        return this.f9687a.f();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void m() {
        this.f9687a.setVisibility(0);
    }

    public final Menu n() {
        if (!this.d) {
            this.f9687a.a(new c(), new d());
            this.d = true;
        }
        return this.f9687a.i();
    }

    public Window.Callback o() {
        return this.c;
    }

    public void p() {
        Menu menuN = n();
        w1 w1Var = menuN instanceof w1 ? (w1) menuN : null;
        if (w1Var != null) {
            w1Var.s();
        }
        try {
            menuN.clear();
            if (!this.c.onCreatePanelMenu(0, menuN) || !this.c.onPreparePanel(0, null, menuN)) {
                menuN.clear();
            }
        } finally {
            if (w1Var != null) {
                w1Var.r();
            }
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public final class c implements c2.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f9691a;

        public c() {
        }

        @Override // supwisdom.c2.a
        public boolean a(w1 w1Var) {
            Window.Callback callback = x0.this.c;
            if (callback == null) {
                return false;
            }
            callback.onMenuOpened(108, w1Var);
            return true;
        }

        @Override // supwisdom.c2.a
        public void a(w1 w1Var, boolean z) {
            if (this.f9691a) {
                return;
            }
            this.f9691a = true;
            x0.this.f9687a.g();
            Window.Callback callback = x0.this.c;
            if (callback != null) {
                callback.onPanelClosed(108, w1Var);
            }
            this.f9691a = false;
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public void a(CharSequence charSequence) {
        this.f9687a.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean a(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            l();
        }
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean a(int i, KeyEvent keyEvent) {
        Menu menuN = n();
        if (menuN == null) {
            return false;
        }
        menuN.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuN.performShortcut(i, keyEvent, 0);
    }
}
