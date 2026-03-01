package supwisdom;

import com.bumptech.glide.load.engine.GlideException;
import com.sangfor.dx.dex.file.ItemType;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import supwisdom.w41;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class n11 extends p21 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final w51 f8467e;
    public final ArrayList<v11> f;
    public final HashMap<v11, u41> g;
    public final ArrayList<v11> h;
    public final ArrayList<x11> i;
    public final ArrayList<x11> j;
    public w41 k;
    public byte[] l;

    public n11(w51 w51Var) {
        super(1, -1);
        if (w51Var == null) {
            throw new NullPointerException("thisClass == null");
        }
        this.f8467e = w51Var;
        this.f = new ArrayList<>(20);
        this.g = new HashMap<>(40);
        this.h = new ArrayList<>(20);
        this.i = new ArrayList<>(20);
        this.j = new ArrayList<>(20);
        this.k = null;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        if (!this.f.isEmpty()) {
            h();
            Iterator<v11> it = this.f.iterator();
            while (it.hasNext()) {
                it.next().a(t11Var);
            }
        }
        if (!this.h.isEmpty()) {
            Collections.sort(this.h);
            Iterator<v11> it2 = this.h.iterator();
            while (it2.hasNext()) {
                it2.next().a(t11Var);
            }
        }
        if (!this.i.isEmpty()) {
            Collections.sort(this.i);
            Iterator<x11> it3 = this.i.iterator();
            while (it3.hasNext()) {
                it3.next().a(t11Var);
            }
        }
        if (this.j.isEmpty()) {
            return;
        }
        Collections.sort(this.j);
        Iterator<x11> it4 = this.j.iterator();
        while (it4.hasNext()) {
            it4.next().a(t11Var);
        }
    }

    public void b(x11 x11Var) {
        if (x11Var == null) {
            throw new NullPointerException("method == null");
        }
        this.j.add(x11Var);
    }

    public final void c(t11 t11Var, h61 h61Var) {
        boolean zE = h61Var.e();
        if (zE) {
            h61Var.a(0, f() + " class data for " + this.f8467e.toHuman());
        }
        a(t11Var, h61Var, "static_fields", this.f.size());
        a(t11Var, h61Var, "instance_fields", this.h.size());
        a(t11Var, h61Var, "direct_methods", this.i.size());
        a(t11Var, h61Var, "virtual_methods", this.j.size());
        a(t11Var, h61Var, "static_fields", this.f);
        a(t11Var, h61Var, "instance_fields", this.h);
        a(t11Var, h61Var, "direct_methods", this.i);
        a(t11Var, h61Var, "virtual_methods", this.j);
        if (zE) {
            h61Var.d();
        }
    }

    @Override // supwisdom.p21
    public String g() {
        return toString();
    }

    public w41 h() {
        if (this.k == null && this.f.size() != 0) {
            this.k = j();
        }
        return this.k;
    }

    public boolean i() {
        return this.f.isEmpty() && this.h.isEmpty() && this.i.isEmpty() && this.j.isEmpty();
    }

    public final w41 j() {
        Collections.sort(this.f);
        int size = this.f.size();
        while (size > 0) {
            u41 u41Var = this.g.get(this.f.get(size - 1));
            if (u41Var instanceof n51) {
                if (((n51) u41Var).f() != 0) {
                    break;
                }
                size--;
            } else {
                if (u41Var != null) {
                    break;
                }
                size--;
            }
        }
        if (size == 0) {
            return null;
        }
        w41.a aVar = new w41.a(size);
        for (int i = 0; i < size; i++) {
            v11 v11Var = this.f.get(i);
            u41 u41VarA = this.g.get(v11Var);
            if (u41VarA == null) {
                u41VarA = y51.a(v11Var.d().getType());
            }
            aVar.a(i, u41VarA);
        }
        aVar.e();
        return new w41(aVar);
    }

    @Override // supwisdom.p21
    public void b(t21 t21Var, int i) {
        k61 k61Var = new k61();
        c(t21Var.b(), k61Var);
        byte[] bArrH = k61Var.h();
        this.l = bArrH;
        a(bArrH.length);
    }

    @Override // supwisdom.p21
    public void b(t11 t11Var, h61 h61Var) {
        if (h61Var.e()) {
            c(t11Var, h61Var);
        } else {
            h61Var.write(this.l);
        }
    }

    public void a(x11 x11Var) {
        if (x11Var != null) {
            this.i.add(x11Var);
            return;
        }
        throw new NullPointerException("method == null");
    }

    public void a(v11 v11Var) {
        if (v11Var != null) {
            this.h.add(v11Var);
            return;
        }
        throw new NullPointerException("field == null");
    }

    public void a(v11 v11Var, u41 u41Var) {
        if (v11Var != null) {
            if (this.k == null) {
                this.f.add(v11Var);
                this.g.put(v11Var, u41Var);
                return;
            }
            throw new UnsupportedOperationException("static fields already sorted");
        }
        throw new NullPointerException("field == null");
    }

    public static void a(t11 t11Var, h61 h61Var, String str, ArrayList<? extends w11> arrayList) {
        int size = arrayList.size();
        if (size == 0) {
            return;
        }
        if (h61Var.e()) {
            h61Var.a(0, GlideException.IndentedAppendable.INDENT + str + Constants.COLON_SEPARATOR);
        }
        int iA = 0;
        for (int i = 0; i < size; i++) {
            iA = arrayList.get(i).a(t11Var, h61Var, iA, i);
        }
    }

    public static void a(t11 t11Var, h61 h61Var, String str, int i) {
        if (h61Var.e()) {
            h61Var.a(String.format("  %-21s %08x", str + "_size:", Integer.valueOf(i)));
        }
        h61Var.c(i);
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_CLASS_DATA_ITEM;
    }
}
