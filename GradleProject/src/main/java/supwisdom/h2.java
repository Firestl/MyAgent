package supwisdom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import com.xiaomi.mipush.sdk.Constants;
import supwisdom.w1;

/* JADX INFO: compiled from: SubMenuBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class h2 extends w1 implements SubMenu {
    public w1 B;
    public y1 C;

    public h2(Context context, w1 w1Var, y1 y1Var) {
        super(context);
        this.B = w1Var;
        this.C = y1Var;
    }

    @Override // supwisdom.w1
    public void a(w1.a aVar) {
        this.B.a(aVar);
    }

    @Override // supwisdom.w1
    public boolean b(y1 y1Var) {
        return this.B.b(y1Var);
    }

    @Override // supwisdom.w1
    public String d() {
        y1 y1Var = this.C;
        int itemId = y1Var != null ? y1Var.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.d() + Constants.COLON_SEPARATOR + itemId;
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.C;
    }

    @Override // supwisdom.w1
    public w1 m() {
        return this.B.m();
    }

    @Override // supwisdom.w1
    public boolean o() {
        return this.B.o();
    }

    @Override // supwisdom.w1
    public boolean p() {
        return this.B.p();
    }

    @Override // supwisdom.w1
    public boolean q() {
        return this.B.q();
    }

    @Override // supwisdom.w1, android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.B.setGroupDividerEnabled(z);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        super.a(drawable);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        super.a(charSequence);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        super.a(view);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.C.setIcon(drawable);
        return this;
    }

    @Override // supwisdom.w1, android.view.Menu
    public void setQwertyMode(boolean z) {
        this.B.setQwertyMode(z);
    }

    public Menu t() {
        return this.B;
    }

    @Override // supwisdom.w1
    public boolean a(w1 w1Var, MenuItem menuItem) {
        return super.a(w1Var, menuItem) || this.B.a(w1Var, menuItem);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i) {
        super.d(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i) {
        super.e(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i) {
        this.C.setIcon(i);
        return this;
    }

    @Override // supwisdom.w1
    public boolean a(y1 y1Var) {
        return this.B.a(y1Var);
    }
}
