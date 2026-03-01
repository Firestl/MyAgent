package supwisdom;

import com.sangfor.dx.dex.file.ItemType;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class j11 extends e21 implements Comparable {
    public final b51 b;
    public l11 c;

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        this.b.d();
        throw null;
    }

    @Override // supwisdom.f21
    public int c() {
        return 4;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return this.b.compareTo(((j11) obj).b);
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_CALL_SITE_ID_ITEM;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var, h61 h61Var) {
        int iD = this.c.d();
        if (!h61Var.e()) {
            h61Var.writeInt(iD);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(f());
        sb.append(' ');
        this.b.toString();
        throw null;
    }
}
