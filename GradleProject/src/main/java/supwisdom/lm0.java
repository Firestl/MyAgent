package supwisdom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.internal.ParcelableSparseArray;
import java.util.ArrayList;
import supwisdom.c2;

/* JADX INFO: compiled from: NavigationMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class lm0 implements c2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public NavigationMenuView f8301a;
    public LinearLayout b;
    public c2.a c;
    public w1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8302e;
    public c f;
    public LayoutInflater g;
    public int h;
    public boolean i;
    public ColorStateList j;
    public ColorStateList k;
    public Drawable l;
    public int m;
    public int n;
    public int o;
    public int p;
    public final View.OnClickListener q = new a();

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            lm0.this.b(true);
            y1 itemData = ((NavigationMenuItemView) view).getItemData();
            lm0 lm0Var = lm0.this;
            boolean zA = lm0Var.d.a(itemData, lm0Var, 0);
            if (itemData != null && itemData.isCheckable() && zA) {
                lm0.this.f.a(itemData);
            }
            lm0.this.b(false);
            lm0.this.a(false);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class b extends k {
        public b(View view) {
            super(view);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public class c extends RecyclerView.g<k> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ArrayList<e> f8304a = new ArrayList<>();
        public y1 b;
        public boolean c;

        public c() {
            c();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(k kVar, int i) {
            int itemViewType = getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    ((TextView) kVar.itemView).setText(((g) this.f8304a.get(i)).a().getTitle());
                    return;
                } else {
                    if (itemViewType != 2) {
                        return;
                    }
                    f fVar = (f) this.f8304a.get(i);
                    kVar.itemView.setPadding(0, fVar.b(), 0, fVar.a());
                    return;
                }
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) kVar.itemView;
            navigationMenuItemView.setIconTintList(lm0.this.k);
            lm0 lm0Var = lm0.this;
            if (lm0Var.i) {
                navigationMenuItemView.setTextAppearance(lm0Var.h);
            }
            ColorStateList colorStateList = lm0.this.j;
            if (colorStateList != null) {
                navigationMenuItemView.setTextColor(colorStateList);
            }
            Drawable drawable = lm0.this.l;
            lb.a(navigationMenuItemView, drawable != null ? drawable.getConstantState().newDrawable() : null);
            g gVar = (g) this.f8304a.get(i);
            navigationMenuItemView.setNeedsEmptyIcon(gVar.b);
            navigationMenuItemView.setHorizontalPadding(lm0.this.m);
            navigationMenuItemView.setIconPadding(lm0.this.n);
            navigationMenuItemView.a(gVar.a(), 0);
        }

        public y1 b() {
            return this.b;
        }

        public final void c() {
            if (this.c) {
                return;
            }
            this.c = true;
            this.f8304a.clear();
            this.f8304a.add(new d());
            int i = -1;
            int size = lm0.this.d.n().size();
            boolean z = false;
            int size2 = 0;
            for (int i2 = 0; i2 < size; i2++) {
                y1 y1Var = lm0.this.d.n().get(i2);
                if (y1Var.isChecked()) {
                    a(y1Var);
                }
                if (y1Var.isCheckable()) {
                    y1Var.c(false);
                }
                if (y1Var.hasSubMenu()) {
                    SubMenu subMenu = y1Var.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i2 != 0) {
                            this.f8304a.add(new f(lm0.this.p, 0));
                        }
                        this.f8304a.add(new g(y1Var));
                        int size3 = this.f8304a.size();
                        int size4 = subMenu.size();
                        boolean z2 = false;
                        for (int i3 = 0; i3 < size4; i3++) {
                            y1 y1Var2 = (y1) subMenu.getItem(i3);
                            if (y1Var2.isVisible()) {
                                if (!z2 && y1Var2.getIcon() != null) {
                                    z2 = true;
                                }
                                if (y1Var2.isCheckable()) {
                                    y1Var2.c(false);
                                }
                                if (y1Var.isChecked()) {
                                    a(y1Var);
                                }
                                this.f8304a.add(new g(y1Var2));
                            }
                        }
                        if (z2) {
                            a(size3, this.f8304a.size());
                        }
                    }
                } else {
                    int groupId = y1Var.getGroupId();
                    if (groupId != i) {
                        size2 = this.f8304a.size();
                        z = y1Var.getIcon() != null;
                        if (i2 != 0) {
                            size2++;
                            ArrayList<e> arrayList = this.f8304a;
                            int i4 = lm0.this.p;
                            arrayList.add(new f(i4, i4));
                        }
                    } else if (!z && y1Var.getIcon() != null) {
                        a(size2, this.f8304a.size());
                        z = true;
                    }
                    g gVar = new g(y1Var);
                    gVar.b = z;
                    this.f8304a.add(gVar);
                    i = groupId;
                }
            }
            this.c = false;
        }

        public void d() {
            c();
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            return this.f8304a.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public long getItemId(int i) {
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemViewType(int i) {
            e eVar = this.f8304a.get(i);
            if (eVar instanceof f) {
                return 2;
            }
            if (eVar instanceof d) {
                return 3;
            }
            if (eVar instanceof g) {
                return ((g) eVar).a().hasSubMenu() ? 1 : 0;
            }
            throw new RuntimeException("Unknown item type.");
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public k onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == 0) {
                lm0 lm0Var = lm0.this;
                return new h(lm0Var.g, viewGroup, lm0Var.q);
            }
            if (i == 1) {
                return new j(lm0.this.g, viewGroup);
            }
            if (i == 2) {
                return new i(lm0.this.g, viewGroup);
            }
            if (i != 3) {
                return null;
            }
            return new b(lm0.this.b);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onViewRecycled(k kVar) {
            if (kVar instanceof h) {
                ((NavigationMenuItemView) kVar.itemView).f();
            }
        }

        public final void a(int i, int i2) {
            while (i < i2) {
                ((g) this.f8304a.get(i)).b = true;
                i++;
            }
        }

        public void a(y1 y1Var) {
            if (this.b == y1Var || !y1Var.isCheckable()) {
                return;
            }
            y1 y1Var2 = this.b;
            if (y1Var2 != null) {
                y1Var2.setChecked(false);
            }
            this.b = y1Var;
            y1Var.setChecked(true);
        }

        public Bundle a() {
            Bundle bundle = new Bundle();
            y1 y1Var = this.b;
            if (y1Var != null) {
                bundle.putInt("android:menu:checked", y1Var.getItemId());
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.f8304a.size();
            for (int i = 0; i < size; i++) {
                e eVar = this.f8304a.get(i);
                if (eVar instanceof g) {
                    y1 y1VarA = ((g) eVar).a();
                    View actionView = y1VarA != null ? y1VarA.getActionView() : null;
                    if (actionView != null) {
                        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                        actionView.saveHierarchyState(parcelableSparseArray);
                        sparseArray.put(y1VarA.getItemId(), parcelableSparseArray);
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:action_views", sparseArray);
            return bundle;
        }

        public void a(Bundle bundle) {
            y1 y1VarA;
            View actionView;
            ParcelableSparseArray parcelableSparseArray;
            y1 y1VarA2;
            int i = bundle.getInt("android:menu:checked", 0);
            if (i != 0) {
                this.c = true;
                int size = this.f8304a.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    e eVar = this.f8304a.get(i2);
                    if ((eVar instanceof g) && (y1VarA2 = ((g) eVar).a()) != null && y1VarA2.getItemId() == i) {
                        a(y1VarA2);
                        break;
                    }
                    i2++;
                }
                this.c = false;
                c();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                int size2 = this.f8304a.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    e eVar2 = this.f8304a.get(i3);
                    if ((eVar2 instanceof g) && (y1VarA = ((g) eVar2).a()) != null && (actionView = y1VarA.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(y1VarA.getItemId())) != null) {
                        actionView.restoreHierarchyState(parcelableSparseArray);
                    }
                }
            }
        }

        public void a(boolean z) {
            this.c = z;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class d implements e {
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public interface e {
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class f implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f8305a;
        public final int b;

        public f(int i, int i2) {
            this.f8305a = i;
            this.b = i2;
        }

        public int a() {
            return this.b;
        }

        public int b() {
            return this.f8305a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class g implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final y1 f8306a;
        public boolean b;

        public g(y1 y1Var) {
            this.f8306a = y1Var;
        }

        public y1 a() {
            return this.f8306a;
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class h extends k {
        public h(LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(R.layout.design_navigation_item, viewGroup, false));
            this.itemView.setOnClickListener(onClickListener);
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class i extends k {
        public i(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_separator, viewGroup, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static class j extends k {
        public j(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_subheader, viewGroup, false));
        }
    }

    /* JADX INFO: compiled from: NavigationMenuPresenter.java */
    public static abstract class k extends RecyclerView.b0 {
        public k(View view) {
            super(view);
        }
    }

    @Override // supwisdom.c2
    public void a(Context context, w1 w1Var) {
        this.g = LayoutInflater.from(context);
        this.d = w1Var;
        this.p = context.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
    }

    @Override // supwisdom.c2
    public boolean a() {
        return false;
    }

    @Override // supwisdom.c2
    public boolean a(h2 h2Var) {
        return false;
    }

    @Override // supwisdom.c2
    public boolean a(w1 w1Var, y1 y1Var) {
        return false;
    }

    public void b(int i2) {
        this.f8302e = i2;
    }

    @Override // supwisdom.c2
    public boolean b(w1 w1Var, y1 y1Var) {
        return false;
    }

    public y1 c() {
        return this.f.b();
    }

    public int d() {
        return this.b.getChildCount();
    }

    public void e(int i2) {
        this.h = i2;
        this.i = true;
        a(false);
    }

    public int f() {
        return this.m;
    }

    public int g() {
        return this.n;
    }

    @Override // supwisdom.c2
    public int getId() {
        return this.f8302e;
    }

    public ColorStateList h() {
        return this.j;
    }

    public ColorStateList i() {
        return this.k;
    }

    @Override // supwisdom.c2
    public Parcelable b() {
        Bundle bundle = new Bundle();
        if (this.f8301a != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.f8301a.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        c cVar = this.f;
        if (cVar != null) {
            bundle.putBundle("android:menu:adapter", cVar.a());
        }
        if (this.b != null) {
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            this.b.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
        }
        return bundle;
    }

    public void c(int i2) {
        this.m = i2;
        a(false);
    }

    public void d(int i2) {
        this.n = i2;
        a(false);
    }

    public Drawable e() {
        return this.l;
    }

    public d2 a(ViewGroup viewGroup) {
        if (this.f8301a == null) {
            this.f8301a = (NavigationMenuView) this.g.inflate(R.layout.design_navigation_menu, viewGroup, false);
            if (this.f == null) {
                this.f = new c();
            }
            this.b = (LinearLayout) this.g.inflate(R.layout.design_navigation_item_header, (ViewGroup) this.f8301a, false);
            this.f8301a.setAdapter(this.f);
        }
        return this.f8301a;
    }

    public void b(ColorStateList colorStateList) {
        this.j = colorStateList;
        a(false);
    }

    @Override // supwisdom.c2
    public void a(boolean z) {
        c cVar = this.f;
        if (cVar != null) {
            cVar.d();
        }
    }

    public void b(boolean z) {
        c cVar = this.f;
        if (cVar != null) {
            cVar.a(z);
        }
    }

    @Override // supwisdom.c2
    public void a(c2.a aVar) {
        this.c = aVar;
    }

    @Override // supwisdom.c2
    public void a(w1 w1Var, boolean z) {
        c2.a aVar = this.c;
        if (aVar != null) {
            aVar.a(w1Var, z);
        }
    }

    @Override // supwisdom.c2
    public void a(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.f8301a.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.f.a(bundle2);
            }
            SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.b.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }

    public void a(y1 y1Var) {
        this.f.a(y1Var);
    }

    public View a(int i2) {
        View viewInflate = this.g.inflate(i2, (ViewGroup) this.b, false);
        a(viewInflate);
        return viewInflate;
    }

    public void a(View view) {
        this.b.addView(view);
        NavigationMenuView navigationMenuView = this.f8301a;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    public void a(ColorStateList colorStateList) {
        this.k = colorStateList;
        a(false);
    }

    public void a(Drawable drawable) {
        this.l = drawable;
        a(false);
    }

    public void a(tb tbVar) {
        int i2 = tbVar.i();
        if (this.o != i2) {
            this.o = i2;
            if (this.b.getChildCount() == 0) {
                NavigationMenuView navigationMenuView = this.f8301a;
                navigationMenuView.setPadding(0, this.o, 0, navigationMenuView.getPaddingBottom());
            }
        }
        lb.a(this.b, tbVar);
    }
}
