package supwisdom;

import com.sangfor.dx.dex.file.ItemType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class i11 extends p21 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g11 f7897e;
    public ArrayList<y11> f;
    public ArrayList<j21> g;
    public ArrayList<q21> h;

    public i11() {
        super(4, -1);
        this.f7897e = null;
        this.f = null;
        this.g = null;
        this.h = null;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        o21 o21VarR = t11Var.r();
        g11 g11Var = this.f7897e;
        if (g11Var != null) {
            this.f7897e = (g11) o21VarR.b(g11Var);
        }
        ArrayList<y11> arrayList = this.f;
        if (arrayList != null) {
            Iterator<y11> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a(t11Var);
            }
        }
        ArrayList<j21> arrayList2 = this.g;
        if (arrayList2 != null) {
            Iterator<j21> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                it2.next().a(t11Var);
            }
        }
        ArrayList<q21> arrayList3 = this.h;
        if (arrayList3 != null) {
            Iterator<q21> it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                it3.next().a(t11Var);
            }
        }
    }

    @Override // supwisdom.p21
    public int b(p21 p21Var) {
        if (i()) {
            return this.f7897e.compareTo(((i11) p21Var).f7897e);
        }
        throw new UnsupportedOperationException("uninternable instance");
    }

    @Override // supwisdom.p21
    public String g() {
        throw new RuntimeException("unsupported");
    }

    public boolean h() {
        return this.f7897e == null && this.f == null && this.g == null && this.h == null;
    }

    public int hashCode() {
        g11 g11Var = this.f7897e;
        if (g11Var == null) {
            return 0;
        }
        return g11Var.hashCode();
    }

    public boolean i() {
        return this.f7897e != null && this.f == null && this.g == null && this.h == null;
    }

    @Override // supwisdom.p21
    public void b(t21 t21Var, int i) {
        a(((a(this.f) + a(this.g) + a(this.h)) * 8) + 16);
    }

    @Override // supwisdom.p21
    public void b(t11 t11Var, h61 h61Var) {
        boolean zE = h61Var.e();
        int iC = p21.c(this.f7897e);
        int iA = a(this.f);
        int iA2 = a(this.g);
        int iA3 = a(this.h);
        if (zE) {
            h61Var.a(0, f() + " annotations directory");
            h61Var.a(4, "  class_annotations_off: " + m61.g(iC));
            h61Var.a(4, "  fields_size:           " + m61.g(iA));
            h61Var.a(4, "  methods_size:          " + m61.g(iA2));
            h61Var.a(4, "  parameters_size:       " + m61.g(iA3));
        }
        h61Var.writeInt(iC);
        h61Var.writeInt(iA);
        h61Var.writeInt(iA2);
        h61Var.writeInt(iA3);
        if (iA != 0) {
            Collections.sort(this.f);
            if (zE) {
                h61Var.a(0, "  fields:");
            }
            Iterator<y11> it = this.f.iterator();
            while (it.hasNext()) {
                it.next().a(t11Var, h61Var);
            }
        }
        if (iA2 != 0) {
            Collections.sort(this.g);
            if (zE) {
                h61Var.a(0, "  methods:");
            }
            Iterator<j21> it2 = this.g.iterator();
            while (it2.hasNext()) {
                it2.next().a(t11Var, h61Var);
            }
        }
        if (iA3 != 0) {
            Collections.sort(this.h);
            if (zE) {
                h61Var.a(0, "  parameters:");
            }
            Iterator<q21> it3 = this.h.iterator();
            while (it3.hasNext()) {
                it3.next().a(t11Var, h61Var);
            }
        }
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_ANNOTATIONS_DIRECTORY_ITEM;
    }

    public static int a(ArrayList<?> arrayList) {
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }
}
