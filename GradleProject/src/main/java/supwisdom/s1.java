package supwisdom;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class s1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f9107a;
    public p4<g9, MenuItem> b;
    public p4<h9, SubMenu> c;

    public s1(Context context) {
        this.f9107a = context;
    }

    public final MenuItem a(MenuItem menuItem) {
        if (!(menuItem instanceof g9)) {
            return menuItem;
        }
        g9 g9Var = (g9) menuItem;
        if (this.b == null) {
            this.b = new p4<>();
        }
        MenuItem menuItem2 = this.b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        z1 z1Var = new z1(this.f9107a, g9Var);
        this.b.put(g9Var, z1Var);
        return z1Var;
    }

    public final void b() {
        p4<g9, MenuItem> p4Var = this.b;
        if (p4Var != null) {
            p4Var.clear();
        }
        p4<h9, SubMenu> p4Var2 = this.c;
        if (p4Var2 != null) {
            p4Var2.clear();
        }
    }

    public final void b(int i) {
        if (this.b == null) {
            return;
        }
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            if (this.b.keyAt(i2).getItemId() == i) {
                this.b.removeAt(i2);
                return;
            }
        }
    }

    public final SubMenu a(SubMenu subMenu) {
        if (!(subMenu instanceof h9)) {
            return subMenu;
        }
        h9 h9Var = (h9) subMenu;
        if (this.c == null) {
            this.c = new p4<>();
        }
        SubMenu subMenu2 = this.c.get(h9Var);
        if (subMenu2 != null) {
            return subMenu2;
        }
        i2 i2Var = new i2(this.f9107a, h9Var);
        this.c.put(h9Var, i2Var);
        return i2Var;
    }

    public final void a(int i) {
        if (this.b == null) {
            return;
        }
        int i2 = 0;
        while (i2 < this.b.size()) {
            if (this.b.keyAt(i2).getGroupId() == i) {
                this.b.removeAt(i2);
                i2--;
            }
            i2++;
        }
    }
}
