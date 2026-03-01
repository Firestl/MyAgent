package supwisdom;

import com.sangfor.dx.dex.file.ItemType;
import com.tencent.open.SocialOperation;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class b21 extends e21 {
    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_HEADER_ITEM;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
    }

    @Override // supwisdom.f21
    public int c() {
        return 112;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var, h61 h61Var) {
        int iC = t11Var.i().c();
        t21 t21VarG = t11Var.g();
        t21 t21VarH = t11Var.h();
        int iC2 = t21VarG.c();
        int iC3 = (t21VarH.c() + t21VarH.i()) - iC2;
        String strA = t11Var.d().a();
        if (h61Var.e()) {
            h61Var.a(8, "magic: " + new v51(strA).h());
            h61Var.a(4, "checksum");
            h61Var.a(20, SocialOperation.GAME_SIGNATURE);
            h61Var.a(4, "file_size:       " + m61.g(t11Var.f()));
            h61Var.a(4, "header_size:     " + m61.g(112));
            h61Var.a(4, "endian_tag:      " + m61.g(305419896));
            h61Var.a(4, "link_size:       0");
            h61Var.a(4, "link_off:        0");
            h61Var.a(4, "map_off:         " + m61.g(iC));
        }
        for (int i = 0; i < 8; i++) {
            h61Var.writeByte(strA.charAt(i));
        }
        h61Var.a(24);
        h61Var.writeInt(t11Var.f());
        h61Var.writeInt(112);
        h61Var.writeInt(305419896);
        h61Var.a(8);
        h61Var.writeInt(iC);
        t11Var.o().d(h61Var);
        t11Var.p().d(h61Var);
        t11Var.l().d(h61Var);
        t11Var.e().d(h61Var);
        t11Var.k().d(h61Var);
        t11Var.c().d(h61Var);
        if (h61Var.e()) {
            h61Var.a(4, "data_size:       " + m61.g(iC3));
            h61Var.a(4, "data_off:        " + m61.g(iC2));
        }
        h61Var.writeInt(iC3);
        h61Var.writeInt(iC2);
    }
}
