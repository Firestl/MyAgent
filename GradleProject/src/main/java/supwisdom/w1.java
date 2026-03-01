package supwisdom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: MenuBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class w1 implements f9 {
    public static final int[] A = {1, 4, 5, 3, 2, 0};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f9576a;
    public final Resources b;
    public boolean c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f9577e;
    public ContextMenu.ContextMenuInfo m;
    public CharSequence n;
    public Drawable o;
    public View p;
    public y1 x;
    public boolean z;
    public int l = 0;
    public boolean q = false;
    public boolean r = false;
    public boolean s = false;
    public boolean t = false;
    public boolean u = false;
    public ArrayList<y1> v = new ArrayList<>();
    public CopyOnWriteArrayList<WeakReference<c2>> w = new CopyOnWriteArrayList<>();
    public boolean y = false;
    public ArrayList<y1> f = new ArrayList<>();
    public ArrayList<y1> g = new ArrayList<>();
    public boolean h = true;
    public ArrayList<y1> i = new ArrayList<>();
    public ArrayList<y1> j = new ArrayList<>();
    public boolean k = true;

    /* JADX INFO: compiled from: MenuBuilder.java */
    public interface a {
        void a(w1 w1Var);

        boolean a(w1 w1Var, MenuItem menuItem);
    }

    /* JADX INFO: compiled from: MenuBuilder.java */
    public interface b {
        boolean a(y1 y1Var);
    }

    public w1(Context context) {
        this.f9576a = context;
        this.b = context.getResources();
        e(true);
    }

    public void a(c2 c2Var) {
        a(c2Var, this.f9576a);
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        int i5;
        PackageManager packageManager = this.f9576a.getPackageManager();
        List<ResolveInfo> listQueryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = listQueryIntentActivityOptions != null ? listQueryIntentActivityOptions.size() : 0;
        if ((i4 & 1) == 0) {
            removeGroup(i);
        }
        for (int i6 = 0; i6 < size; i6++) {
            ResolveInfo resolveInfo = listQueryIntentActivityOptions.get(i6);
            int i7 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i7 < 0 ? intent : intentArr[i7]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            MenuItem intent3 = add(i, i2, i3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i5 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i5] = intent3;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void b(c2 c2Var) {
        for (WeakReference<c2> weakReference : this.w) {
            c2 c2Var2 = weakReference.get();
            if (c2Var2 == null || c2Var2 == c2Var) {
                this.w.remove(weakReference);
            }
        }
    }

    public w1 c(int i) {
        this.l = i;
        return this;
    }

    @Override // android.view.Menu
    public void clear() {
        y1 y1Var = this.x;
        if (y1Var != null) {
            a(y1Var);
        }
        this.f.clear();
        c(true);
    }

    public void clearHeader() {
        this.o = null;
        this.n = null;
        this.p = null;
        c(false);
    }

    @Override // android.view.Menu
    public void close() {
        a(true);
    }

    public String d() {
        return "android:menu:actionviewstates";
    }

    public void d(Bundle bundle) {
        a(bundle);
    }

    public void e(Bundle bundle) {
        int size = size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((h2) item.getSubMenu()).e(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(d(), sparseArray);
        }
    }

    public void f(Bundle bundle) {
        b(bundle);
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            y1 y1Var = this.f.get(i2);
            if (y1Var.getItemId() == i) {
                return y1Var;
            }
            if (y1Var.hasSubMenu() && (menuItemFindItem = y1Var.getSubMenu().findItem(i)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    public Drawable g() {
        return this.o;
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i) {
        return this.f.get(i);
    }

    public CharSequence h() {
        return this.n;
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        if (this.z) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.f.get(i).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public View i() {
        return this.p;
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return a(i, keyEvent) != null;
    }

    public ArrayList<y1> j() {
        b();
        return this.j;
    }

    public boolean k() {
        return this.t;
    }

    public Resources l() {
        return this.b;
    }

    public w1 m() {
        return this;
    }

    public ArrayList<y1> n() {
        if (!this.h) {
            return this.g;
        }
        this.g.clear();
        int size = this.f.size();
        for (int i = 0; i < size; i++) {
            y1 y1Var = this.f.get(i);
            if (y1Var.isVisible()) {
                this.g.add(y1Var);
            }
        }
        this.h = false;
        this.k = true;
        return this.g;
    }

    public boolean o() {
        return this.y;
    }

    public boolean p() {
        return this.c;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i, int i2) {
        return a(findItem(i), i2);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        y1 y1VarA = a(i, keyEvent);
        boolean zA = y1VarA != null ? a(y1VarA, i2) : false;
        if ((i2 & 2) != 0) {
            a(true);
        }
        return zA;
    }

    public boolean q() {
        return this.d;
    }

    public void r() {
        this.q = false;
        if (this.r) {
            this.r = false;
            c(this.s);
        }
    }

    @Override // android.view.Menu
    public void removeGroup(int i) {
        int iA = a(i);
        if (iA >= 0) {
            int size = this.f.size() - iA;
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (i2 >= size || this.f.get(iA).getGroupId() != i) {
                    break;
                }
                a(iA, false);
                i2 = i3;
            }
            c(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i) {
        a(b(i), true);
    }

    public void s() {
        if (this.q) {
            return;
        }
        this.q = true;
        this.r = false;
        this.s = false;
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.f.size();
        for (int i2 = 0; i2 < size; i2++) {
            y1 y1Var = this.f.get(i2);
            if (y1Var.getGroupId() == i) {
                y1Var.c(z2);
                y1Var.setCheckable(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.y = z;
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i, boolean z) {
        int size = this.f.size();
        for (int i2 = 0; i2 < size; i2++) {
            y1 y1Var = this.f.get(i2);
            if (y1Var.getGroupId() == i) {
                y1Var.setEnabled(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i, boolean z) {
        int size = this.f.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            y1 y1Var = this.f.get(i2);
            if (y1Var.getGroupId() == i && y1Var.e(z)) {
                z2 = true;
            }
        }
        if (z2) {
            c(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.c = z;
        c(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.f.size();
    }

    public static int f(int i) {
        int i2 = ((-65536) & i) >> 16;
        if (i2 >= 0) {
            int[] iArr = A;
            if (i2 < iArr.length) {
                return (i & 65535) | (iArr[i2] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    public void a(c2 c2Var, Context context) {
        this.w.add(new WeakReference<>(c2Var));
        c2Var.a(context, this);
        this.k = true;
    }

    @Override // android.view.Menu
    public MenuItem add(int i) {
        return a(0, 0, 0, this.b.getString(i));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.b.getString(i));
    }

    public void c(Bundle bundle) {
        MenuItem menuItemFindItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(d());
        int size = size();
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((h2) item.getSubMenu()).c(bundle);
            }
        }
        int i2 = bundle.getInt("android:menu:expandedactionview");
        if (i2 <= 0 || (menuItemFindItem = findItem(i2)) == null) {
            return;
        }
        menuItemFindItem.expandActionView();
    }

    public void d(y1 y1Var) {
        this.h = true;
        c(true);
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return a(i, i2, i3, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        y1 y1Var = (y1) a(i, i2, i3, charSequence);
        h2 h2Var = new h2(this.f9576a, this, y1Var);
        y1Var.a(h2Var);
        return h2Var;
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, int i4) {
        return a(i, i2, i3, this.b.getString(i4));
    }

    public final void b(boolean z) {
        if (this.w.isEmpty()) {
            return;
        }
        s();
        for (WeakReference<c2> weakReference : this.w) {
            c2 c2Var = weakReference.get();
            if (c2Var == null) {
                this.w.remove(weakReference);
            } else {
                c2Var.a(z);
            }
        }
        r();
    }

    public w1 d(int i) {
        a(0, null, i, null, null);
        return this;
    }

    public final boolean a(h2 h2Var, c2 c2Var) {
        if (this.w.isEmpty()) {
            return false;
        }
        boolean zA = c2Var != null ? c2Var.a(h2Var) : false;
        for (WeakReference<c2> weakReference : this.w) {
            c2 c2Var2 = weakReference.get();
            if (c2Var2 == null) {
                this.w.remove(weakReference);
            } else if (!zA) {
                zA = c2Var2.a(h2Var);
            }
        }
        return zA;
    }

    public void d(boolean z) {
        this.z = z;
    }

    public y1 f() {
        return this.x;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.b.getString(i4));
    }

    public final void a(Bundle bundle) {
        Parcelable parcelable;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:presenters");
        if (sparseParcelableArray == null || this.w.isEmpty()) {
            return;
        }
        for (WeakReference<c2> weakReference : this.w) {
            c2 c2Var = weakReference.get();
            if (c2Var == null) {
                this.w.remove(weakReference);
            } else {
                int id = c2Var.getId();
                if (id > 0 && (parcelable = (Parcelable) sparseParcelableArray.get(id)) != null) {
                    c2Var.a(parcelable);
                }
            }
        }
    }

    public final void b(Bundle bundle) {
        Parcelable parcelableB;
        if (this.w.isEmpty()) {
            return;
        }
        SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
        for (WeakReference<c2> weakReference : this.w) {
            c2 c2Var = weakReference.get();
            if (c2Var == null) {
                this.w.remove(weakReference);
            } else {
                int id = c2Var.getId();
                if (id > 0 && (parcelableB = c2Var.b()) != null) {
                    sparseArray.put(id, parcelableB);
                }
            }
        }
        bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
    }

    public final void e(boolean z) {
        this.d = z && this.b.getConfiguration().keyboard != 1 && mb.d(ViewConfiguration.get(this.f9576a), this.f9576a);
    }

    public void c(boolean z) {
        if (!this.q) {
            if (z) {
                this.h = true;
                this.k = true;
            }
            b(z);
            return;
        }
        this.r = true;
        if (z) {
            this.s = true;
        }
    }

    public Context e() {
        return this.f9576a;
    }

    public w1 e(int i) {
        a(i, null, 0, null, null);
        return this;
    }

    public void a(a aVar) {
        this.f9577e = aVar;
    }

    public MenuItem a(int i, int i2, int i3, CharSequence charSequence) {
        int iF = f(i3);
        y1 y1VarA = a(i, i2, i3, iF, charSequence, this.l);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.m;
        if (contextMenuInfo != null) {
            y1VarA.a(contextMenuInfo);
        }
        ArrayList<y1> arrayList = this.f;
        arrayList.add(a(arrayList, iF), y1VarA);
        c(true);
        return y1VarA;
    }

    public int b(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.f.get(i2).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    public void c(y1 y1Var) {
        this.k = true;
        c(true);
    }

    public void b() {
        ArrayList<y1> arrayListN = n();
        if (this.k) {
            boolean zA = false;
            for (WeakReference<c2> weakReference : this.w) {
                c2 c2Var = weakReference.get();
                if (c2Var == null) {
                    this.w.remove(weakReference);
                } else {
                    zA |= c2Var.a();
                }
            }
            if (zA) {
                this.i.clear();
                this.j.clear();
                int size = arrayListN.size();
                for (int i = 0; i < size; i++) {
                    y1 y1Var = arrayListN.get(i);
                    if (y1Var.h()) {
                        this.i.add(y1Var);
                    } else {
                        this.j.add(y1Var);
                    }
                }
            } else {
                this.i.clear();
                this.j.clear();
                this.j.addAll(n());
            }
            this.k = false;
        }
    }

    public ArrayList<y1> c() {
        b();
        return this.i;
    }

    public final y1 a(int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        return new y1(this, i, i2, i3, i4, charSequence, i5);
    }

    public final void a(int i, boolean z) {
        if (i < 0 || i >= this.f.size()) {
            return;
        }
        this.f.remove(i);
        if (z) {
            c(true);
        }
    }

    public void a(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.f.size();
        s();
        for (int i = 0; i < size; i++) {
            y1 y1Var = this.f.get(i);
            if (y1Var.getGroupId() == groupId && y1Var.i() && y1Var.isCheckable()) {
                y1Var.b(y1Var == menuItem);
            }
        }
        r();
    }

    public int a(int i) {
        return a(i, 0);
    }

    public int a(int i, int i2) {
        int size = size();
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < size) {
            if (this.f.get(i2).getGroupId() == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public boolean b(y1 y1Var) {
        boolean zB = false;
        if (this.w.isEmpty()) {
            return false;
        }
        s();
        for (WeakReference<c2> weakReference : this.w) {
            c2 c2Var = weakReference.get();
            if (c2Var == null) {
                this.w.remove(weakReference);
            } else {
                zB = c2Var.b(this, y1Var);
                if (zB) {
                    break;
                }
            }
        }
        r();
        if (zB) {
            this.x = y1Var;
        }
        return zB;
    }

    public boolean a(w1 w1Var, MenuItem menuItem) {
        a aVar = this.f9577e;
        return aVar != null && aVar.a(w1Var, menuItem);
    }

    public void a() {
        a aVar = this.f9577e;
        if (aVar != null) {
            aVar.a(this);
        }
    }

    public static int a(ArrayList<y1> arrayList, int i) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).c() <= i) {
                return size + 1;
            }
        }
        return 0;
    }

    public void a(List<y1> list, int i, KeyEvent keyEvent) {
        boolean zP = p();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.f.size();
            for (int i2 = 0; i2 < size; i2++) {
                y1 y1Var = this.f.get(i2);
                if (y1Var.hasSubMenu()) {
                    ((w1) y1Var.getSubMenu()).a(list, i, keyEvent);
                }
                char alphabeticShortcut = zP ? y1Var.getAlphabeticShortcut() : y1Var.getNumericShortcut();
                if (((modifiers & 69647) == ((zP ? y1Var.getAlphabeticModifiers() : y1Var.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (zP && alphabeticShortcut == '\b' && i == 67)) && y1Var.isEnabled()) {
                        list.add(y1Var);
                    }
                }
            }
        }
    }

    public y1 a(int i, KeyEvent keyEvent) {
        char numericShortcut;
        ArrayList<y1> arrayList = this.v;
        arrayList.clear();
        a(arrayList, i, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return arrayList.get(0);
        }
        boolean zP = p();
        for (int i2 = 0; i2 < size; i2++) {
            y1 y1Var = arrayList.get(i2);
            if (zP) {
                numericShortcut = y1Var.getAlphabeticShortcut();
            } else {
                numericShortcut = y1Var.getNumericShortcut();
            }
            if ((numericShortcut == keyData.meta[0] && (metaState & 2) == 0) || ((numericShortcut == keyData.meta[2] && (metaState & 2) != 0) || (zP && numericShortcut == '\b' && i == 67))) {
                return y1Var;
            }
        }
        return null;
    }

    public boolean a(MenuItem menuItem, int i) {
        return a(menuItem, (c2) null, i);
    }

    public boolean a(MenuItem menuItem, c2 c2Var, int i) {
        y1 y1Var = (y1) menuItem;
        if (y1Var == null || !y1Var.isEnabled()) {
            return false;
        }
        boolean zG = y1Var.g();
        pa paVarA = y1Var.a();
        boolean z = paVarA != null && paVarA.a();
        if (y1Var.f()) {
            zG |= y1Var.expandActionView();
            if (zG) {
                a(true);
            }
        } else if (y1Var.hasSubMenu() || z) {
            if ((i & 4) == 0) {
                a(false);
            }
            if (!y1Var.hasSubMenu()) {
                y1Var.a(new h2(e(), this, y1Var));
            }
            h2 h2Var = (h2) y1Var.getSubMenu();
            if (z) {
                paVarA.a(h2Var);
            }
            zG |= a(h2Var, c2Var);
            if (!zG) {
                a(true);
            }
        } else if ((i & 1) == 0) {
            a(true);
        }
        return zG;
    }

    public final void a(boolean z) {
        if (this.u) {
            return;
        }
        this.u = true;
        for (WeakReference<c2> weakReference : this.w) {
            c2 c2Var = weakReference.get();
            if (c2Var == null) {
                this.w.remove(weakReference);
            } else {
                c2Var.a(this, z);
            }
        }
        this.u = false;
    }

    public final void a(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources resourcesL = l();
        if (view != null) {
            this.p = view;
            this.n = null;
            this.o = null;
        } else {
            if (i > 0) {
                this.n = resourcesL.getText(i);
            } else if (charSequence != null) {
                this.n = charSequence;
            }
            if (i2 > 0) {
                this.o = y7.c(e(), i2);
            } else if (drawable != null) {
                this.o = drawable;
            }
            this.p = null;
        }
        c(false);
    }

    public w1 a(CharSequence charSequence) {
        a(0, charSequence, 0, null, null);
        return this;
    }

    public w1 a(Drawable drawable) {
        a(0, null, 0, drawable, null);
        return this;
    }

    public w1 a(View view) {
        a(0, null, 0, null, view);
        return this;
    }

    public boolean a(y1 y1Var) {
        boolean zA = false;
        if (!this.w.isEmpty() && this.x == y1Var) {
            s();
            for (WeakReference<c2> weakReference : this.w) {
                c2 c2Var = weakReference.get();
                if (c2Var == null) {
                    this.w.remove(weakReference);
                } else {
                    zA = c2Var.a(this, y1Var);
                    if (zA) {
                        break;
                    }
                }
            }
            r();
            if (zA) {
                this.x = null;
            }
        }
        return zA;
    }
}
