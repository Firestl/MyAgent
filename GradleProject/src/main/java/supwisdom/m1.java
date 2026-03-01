package supwisdom;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import supwisdom.i1;

/* JADX INFO: compiled from: SupportActionModeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class m1 extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f8336a;
    public final i1 b;

    public m1(Context context, i1 i1Var) {
        this.f8336a = context;
        this.b = i1Var;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.b.a();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.b.b();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new e2(this.f8336a, (f9) this.b.c());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.b.d();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.b.e();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.b.g();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.b.h();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.b.i();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.b.j();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.b.a(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.b.a(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.b.a(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.b.b(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.b.a(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.b.a(i);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.b.b(i);
    }

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements i1.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ActionMode.Callback f8337a;
        public final Context b;
        public final ArrayList<m1> c = new ArrayList<>();
        public final p4<Menu, Menu> d = new p4<>();

        public a(Context context, ActionMode.Callback callback) {
            this.b = context;
            this.f8337a = callback;
        }

        @Override // supwisdom.i1.a
        public boolean a(i1 i1Var, Menu menu) {
            return this.f8337a.onCreateActionMode(b(i1Var), a(menu));
        }

        @Override // supwisdom.i1.a
        public boolean b(i1 i1Var, Menu menu) {
            return this.f8337a.onPrepareActionMode(b(i1Var), a(menu));
        }

        @Override // supwisdom.i1.a
        public boolean a(i1 i1Var, MenuItem menuItem) {
            return this.f8337a.onActionItemClicked(b(i1Var), new z1(this.b, (g9) menuItem));
        }

        public ActionMode b(i1 i1Var) {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                m1 m1Var = this.c.get(i);
                if (m1Var != null && m1Var.b == i1Var) {
                    return m1Var;
                }
            }
            m1 m1Var2 = new m1(this.b, i1Var);
            this.c.add(m1Var2);
            return m1Var2;
        }

        @Override // supwisdom.i1.a
        public void a(i1 i1Var) {
            this.f8337a.onDestroyActionMode(b(i1Var));
        }

        public final Menu a(Menu menu) {
            Menu menu2 = this.d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            e2 e2Var = new e2(this.b, (f9) menu);
            this.d.put(menu, e2Var);
            return e2Var;
        }
    }
}
