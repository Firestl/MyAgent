package supwisdom;

import com.sangfor.dx.dex.file.ItemType;
import com.taobao.weex.el.parse.Operators;
import java.util.Iterator;
import java.util.List;
import supwisdom.p21;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class c31<T extends p21> extends p21 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ItemType f7141e;
    public final List<T> f;

    public c31(ItemType itemType, List<T> list) {
        super(a((List<? extends p21>) list), b((List<? extends p21>) list));
        if (itemType == null) {
            throw new NullPointerException("itemType == null");
        }
        this.f = list;
        this.f7141e = itemType;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        Iterator<T> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().a(t11Var);
        }
    }

    @Override // supwisdom.p21
    public void b(t21 t21Var, int i) {
        int i2 = i + i();
        int i3 = -1;
        int iE = -1;
        boolean z = true;
        for (T t : this.f) {
            int iC = t.c();
            if (z) {
                iE = t.e();
                i3 = iC;
                z = false;
            } else {
                if (iC != i3) {
                    throw new UnsupportedOperationException("item size mismatch");
                }
                if (t.e() != iE) {
                    throw new UnsupportedOperationException("item alignment mismatch");
                }
            }
            i2 = t.a(t21Var, i2) + iC;
        }
    }

    @Override // supwisdom.p21
    public final String g() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(Operators.BLOCK_START_STR);
        boolean z = true;
        for (T t : this.f) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(t.g());
        }
        sb.append(Operators.BLOCK_END_STR);
        return sb.toString();
    }

    public final List<T> h() {
        return this.f;
    }

    public final int i() {
        return e();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(c31.class.getName());
        sb.append(this.f);
        return sb.toString();
    }

    public static int a(List<? extends p21> list) {
        try {
            return Math.max(4, list.get(0).e());
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("items.size() == 0");
        } catch (NullPointerException unused2) {
            throw new NullPointerException("items == null");
        }
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return this.f7141e;
    }

    public static int b(List<? extends p21> list) {
        return (list.size() * list.get(0).c()) + a(list);
    }

    @Override // supwisdom.p21
    public void b(t11 t11Var, h61 h61Var) {
        int size = this.f.size();
        if (h61Var.e()) {
            h61Var.a(0, f() + Operators.SPACE_STR + b());
            StringBuilder sb = new StringBuilder();
            sb.append("  size: ");
            sb.append(m61.g(size));
            h61Var.a(4, sb.toString());
        }
        h61Var.writeInt(size);
        Iterator<T> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().a(t11Var, h61Var);
        }
    }
}
