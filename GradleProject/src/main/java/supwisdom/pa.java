package supwisdom;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: ActionProvider.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class pa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f8781a;
    public b b;

    /* JADX INFO: compiled from: ActionProvider.java */
    public interface a {
        void b(boolean z);
    }

    /* JADX INFO: compiled from: ActionProvider.java */
    public interface b {
        void onActionProviderVisibilityChanged(boolean z);
    }

    public pa(Context context) {
    }

    public View a(MenuItem menuItem) {
        return c();
    }

    public void a(SubMenu subMenu) {
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }

    public abstract View c();

    public boolean d() {
        return false;
    }

    public boolean e() {
        return false;
    }

    public void f() {
        this.b = null;
        this.f8781a = null;
    }

    public void a(boolean z) {
        a aVar = this.f8781a;
        if (aVar != null) {
            aVar.b(z);
        }
    }

    public void a(a aVar) {
        this.f8781a = aVar;
    }

    public void a(b bVar) {
        if (this.b != null && bVar != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.b = bVar;
    }
}
