package supwisdom;

import android.content.Context;
import android.view.SubMenu;

/* JADX INFO: compiled from: NavigationMenu.java */
/* JADX INFO: loaded from: classes.dex */
public class km0 extends w1 {
    public km0(Context context) {
        super(context);
    }

    @Override // supwisdom.w1, android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        y1 y1Var = (y1) a(i, i2, i3, charSequence);
        mm0 mm0Var = new mm0(e(), this, y1Var);
        y1Var.a(mm0Var);
        return mm0Var;
    }
}
