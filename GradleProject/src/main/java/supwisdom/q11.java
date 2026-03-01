package supwisdom;

import com.sangfor.dex.util.ExceptionWithContext;
import com.sangfor.dx.dex.file.ItemType;
import com.taobao.weex.el.parse.Operators;
import java.util.Iterator;
import org.bouncycastle.asn1.util.ASN1Dump;
import supwisdom.jz0;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class q11 extends p21 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final r51 f8862e;
    public final jz0 f;
    public m11 g;
    public final boolean h;
    public final d61 i;
    public s11 j;

    /* JADX INFO: compiled from: Proguard */
    public class a implements jz0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ t11 f8863a;

        public a(q11 q11Var, t11 t11Var) {
            this.f8863a = t11Var;
        }

        @Override // supwisdom.jz0.a
        public int a(u41 u41Var) {
            e21 e21VarA = this.f8863a.a(u41Var);
            if (e21VarA == null) {
                return -1;
            }
            return e21VarA.d();
        }
    }

    public q11(r51 r51Var, jz0 jz0Var, boolean z, d61 d61Var) {
        super(4, -1);
        if (r51Var == null) {
            throw new NullPointerException("ref == null");
        }
        if (jz0Var == null) {
            throw new NullPointerException("code == null");
        }
        if (d61Var == null) {
            throw new NullPointerException("throwsList == null");
        }
        this.f8862e = r51Var;
        this.f = jz0Var;
        this.h = z;
        this.i = d61Var;
        this.g = null;
        this.j = null;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        o21 o21VarA = t11Var.a();
        z21 z21VarP = t11Var.p();
        if (this.f.j() || this.f.i()) {
            s11 s11Var = new s11(this.f, this.h, this.f8862e);
            this.j = s11Var;
            o21VarA.a((p21) s11Var);
        }
        if (this.f.h()) {
            Iterator<b61> it = this.f.b().iterator();
            while (it.hasNext()) {
                z21VarP.b(it.next());
            }
            this.g = new m11(this.f);
        }
        Iterator<u41> it2 = this.f.d().iterator();
        while (it2.hasNext()) {
            t11Var.b(it2.next());
        }
    }

    @Override // supwisdom.p21
    public void b(t21 t21Var, int i) {
        int iC;
        t11 t11VarB = t21Var.b();
        this.f.a(new a(this, t11VarB));
        m11 m11Var = this.g;
        if (m11Var != null) {
            m11Var.a(t11VarB);
            iC = this.g.c();
        } else {
            iC = 0;
        }
        int iH = this.f.e().h();
        if ((iH & 1) != 0) {
            iH++;
        }
        a((iH * 2) + 16 + iC);
    }

    public final void c(t11 t11Var, h61 h61Var) {
        try {
            this.f.e().a(h61Var);
        } catch (RuntimeException e2) {
            throw ExceptionWithContext.withContext(e2, "...while writing instructions for " + this.f8862e.toHuman());
        }
    }

    @Override // supwisdom.p21
    public String g() {
        return this.f8862e.toHuman();
    }

    public final int h() {
        return this.f8862e.a(this.h);
    }

    public final int i() {
        return this.f.e().i();
    }

    public final int j() {
        return this.f.e().j();
    }

    public String toString() {
        return "CodeItem{" + g() + Operators.BLOCK_END_STR;
    }

    @Override // supwisdom.p21
    public void b(t11 t11Var, h61 h61Var) {
        boolean zE = h61Var.e();
        int iJ = j();
        int i = i();
        int iH = h();
        int iH2 = this.f.e().h();
        boolean z = (iH2 & 1) != 0;
        m11 m11Var = this.g;
        int iB = m11Var == null ? 0 : m11Var.b();
        s11 s11Var = this.j;
        int iD = s11Var == null ? 0 : s11Var.d();
        if (zE) {
            h61Var.a(0, f() + ' ' + this.f8862e.toHuman());
            StringBuilder sb = new StringBuilder();
            sb.append("  registers_size: ");
            sb.append(m61.d(iJ));
            h61Var.a(2, sb.toString());
            h61Var.a(2, "  ins_size:       " + m61.d(iH));
            h61Var.a(2, "  outs_size:      " + m61.d(i));
            h61Var.a(2, "  tries_size:     " + m61.d(iB));
            h61Var.a(4, "  debug_off:      " + m61.g(iD));
            h61Var.a(4, "  insns_size:     " + m61.g(iH2));
            if (this.i.size() != 0) {
                h61Var.a(0, "  throws " + a61.b(this.i));
            }
        }
        h61Var.writeShort(iJ);
        h61Var.writeShort(iH);
        h61Var.writeShort(i);
        h61Var.writeShort(iB);
        h61Var.writeInt(iD);
        h61Var.writeInt(iH2);
        c(t11Var, h61Var);
        if (this.g != null) {
            if (z) {
                if (zE) {
                    h61Var.a(2, "  padding: 0");
                }
                h61Var.writeShort(0);
            }
            this.g.a(t11Var, h61Var);
        }
        if (!zE || this.j == null) {
            return;
        }
        h61Var.a(0, "  debug info");
        this.j.a(t11Var, h61Var, ASN1Dump.TAB);
    }

    @Override // supwisdom.f21
    public ItemType a() {
        return ItemType.TYPE_CODE_ITEM;
    }
}
