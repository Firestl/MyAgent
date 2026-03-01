package supwisdom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.util.ArrayList;
import supwisdom.d2;

/* JADX INFO: compiled from: MenuAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class v1 extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public w1 f9464a;
    public int b = -1;
    public boolean c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LayoutInflater f9465e;
    public final int f;

    public v1(w1 w1Var, LayoutInflater layoutInflater, boolean z, int i) {
        this.d = z;
        this.f9465e = layoutInflater;
        this.f9464a = w1Var;
        this.f = i;
        a();
    }

    public void a(boolean z) {
        this.c = z;
    }

    public w1 b() {
        return this.f9464a;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.b < 0 ? (this.d ? this.f9464a.j() : this.f9464a.n()).size() : r0.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f9465e.inflate(this.f, viewGroup, false);
        }
        int groupId = getItem(i).getGroupId();
        int i2 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f9464a.o() && groupId != (i2 >= 0 ? getItem(i2).getGroupId() : groupId));
        d2.a aVar = (d2.a) view;
        if (this.c) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.a(getItem(i), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }

    public void a() {
        y1 y1VarF = this.f9464a.f();
        if (y1VarF != null) {
            ArrayList<y1> arrayListJ = this.f9464a.j();
            int size = arrayListJ.size();
            for (int i = 0; i < size; i++) {
                if (arrayListJ.get(i) == y1VarF) {
                    this.b = i;
                    return;
                }
            }
        }
        this.b = -1;
    }

    @Override // android.widget.Adapter
    public y1 getItem(int i) {
        ArrayList<y1> arrayListJ = this.d ? this.f9464a.j() : this.f9464a.n();
        int i2 = this.b;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return arrayListJ.get(i);
    }
}
