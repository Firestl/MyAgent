package supwisdom;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: BottomNavigationMenu.java */
/* JADX INFO: loaded from: classes.dex */
public final class pl0 extends w1 {
    public pl0(Context context) {
        super(context);
    }

    @Override // supwisdom.w1
    public MenuItem a(int i, int i2, int i3, CharSequence charSequence) {
        if (size() + 1 > 5) {
            throw new IllegalArgumentException("Maximum number of items supported by BottomNavigationView is 5. Limit can be checked with BottomNavigationView#getMaxItemCount()");
        }
        s();
        MenuItem menuItemA = super.a(i, i2, i3, charSequence);
        if (menuItemA instanceof y1) {
            ((y1) menuItemA).c(true);
        }
        r();
        return menuItemA;
    }

    @Override // supwisdom.w1, android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        throw new UnsupportedOperationException("BottomNavigationView does not support submenus");
    }
}
