package supwisdom;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.ExpandedMenuView;
import java.util.ArrayList;
import supwisdom.c2;
import supwisdom.d2;

/* JADX INFO: compiled from: ListMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class u1 implements c2, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f9349a;
    public LayoutInflater b;
    public w1 c;
    public ExpandedMenuView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9350e;
    public int f;
    public int g;
    public c2.a h;
    public a i;
    public int j;

    /* JADX INFO: compiled from: ListMenuPresenter.java */
    public class a extends BaseAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9351a = -1;

        public a() {
            a();
        }

        public void a() {
            y1 y1VarF = u1.this.c.f();
            if (y1VarF != null) {
                ArrayList<y1> arrayListJ = u1.this.c.j();
                int size = arrayListJ.size();
                for (int i = 0; i < size; i++) {
                    if (arrayListJ.get(i) == y1VarF) {
                        this.f9351a = i;
                        return;
                    }
                }
            }
            this.f9351a = -1;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = u1.this.c.j().size() - u1.this.f9350e;
            return this.f9351a < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                u1 u1Var = u1.this;
                view = u1Var.b.inflate(u1Var.g, viewGroup, false);
            }
            ((d2.a) view).a(getItem(i), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public y1 getItem(int i) {
            ArrayList<y1> arrayListJ = u1.this.c.j();
            int i2 = i + u1.this.f9350e;
            int i3 = this.f9351a;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return arrayListJ.get(i2);
        }
    }

    public u1(Context context, int i) {
        this(i, 0);
        this.f9349a = context;
        this.b = LayoutInflater.from(context);
    }

    @Override // supwisdom.c2
    public void a(Context context, w1 w1Var) {
        if (this.f != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, this.f);
            this.f9349a = contextThemeWrapper;
            this.b = LayoutInflater.from(contextThemeWrapper);
        } else if (this.f9349a != null) {
            this.f9349a = context;
            if (this.b == null) {
                this.b = LayoutInflater.from(context);
            }
        }
        this.c = w1Var;
        a aVar = this.i;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // supwisdom.c2
    public boolean a() {
        return false;
    }

    @Override // supwisdom.c2
    public boolean a(w1 w1Var, y1 y1Var) {
        return false;
    }

    public void b(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.d;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    }

    @Override // supwisdom.c2
    public boolean b(w1 w1Var, y1 y1Var) {
        return false;
    }

    public ListAdapter c() {
        if (this.i == null) {
            this.i = new a();
        }
        return this.i;
    }

    @Override // supwisdom.c2
    public int getId() {
        return this.j;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.c.a(this.i.getItem(i), this, 0);
    }

    public u1(int i, int i2) {
        this.g = i;
        this.f = i2;
    }

    @Override // supwisdom.c2
    public Parcelable b() {
        if (this.d == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        b(bundle);
        return bundle;
    }

    public d2 a(ViewGroup viewGroup) {
        if (this.d == null) {
            this.d = (ExpandedMenuView) this.b.inflate(R.layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.i == null) {
                this.i = new a();
            }
            this.d.setAdapter((ListAdapter) this.i);
            this.d.setOnItemClickListener(this);
        }
        return this.d;
    }

    @Override // supwisdom.c2
    public void a(boolean z) {
        a aVar = this.i;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // supwisdom.c2
    public void a(c2.a aVar) {
        this.h = aVar;
    }

    @Override // supwisdom.c2
    public boolean a(h2 h2Var) {
        if (!h2Var.hasVisibleItems()) {
            return false;
        }
        new x1(h2Var).a((IBinder) null);
        c2.a aVar = this.h;
        if (aVar == null) {
            return true;
        }
        aVar.a(h2Var);
        return true;
    }

    @Override // supwisdom.c2
    public void a(w1 w1Var, boolean z) {
        c2.a aVar = this.h;
        if (aVar != null) {
            aVar.a(w1Var, z);
        }
    }

    public void a(Bundle bundle) {
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.d.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // supwisdom.c2
    public void a(Parcelable parcelable) {
        a((Bundle) parcelable);
    }
}
