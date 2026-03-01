package supwisdom;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/* JADX INFO: compiled from: ActionMode.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class i1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f7896a;
    public boolean b;

    /* JADX INFO: compiled from: ActionMode.java */
    public interface a {
        void a(i1 i1Var);

        boolean a(i1 i1Var, Menu menu);

        boolean a(i1 i1Var, MenuItem menuItem);

        boolean b(i1 i1Var, Menu menu);
    }

    public abstract void a();

    public abstract void a(int i);

    public abstract void a(View view);

    public abstract void a(CharSequence charSequence);

    public void a(Object obj) {
        this.f7896a = obj;
    }

    public abstract View b();

    public abstract void b(int i);

    public abstract void b(CharSequence charSequence);

    public abstract Menu c();

    public abstract MenuInflater d();

    public abstract CharSequence e();

    public Object f() {
        return this.f7896a;
    }

    public abstract CharSequence g();

    public boolean h() {
        return this.b;
    }

    public abstract void i();

    public abstract boolean j();

    public void a(boolean z) {
        this.b = z;
    }
}
