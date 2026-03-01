package supwisdom;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import supwisdom.u60;

/* JADX INFO: compiled from: WebvttDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class w60 extends c60 {
    public final v60 n;
    public final o80 o;
    public final u60.b p;
    public final q60 q;
    public final List<t60> r;

    public w60() {
        super("WebvttDecoder");
        this.n = new v60();
        this.o = new o80();
        this.p = new u60.b();
        this.q = new q60();
        this.r = new ArrayList();
    }

    @Override // supwisdom.c60
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public y60 a(byte[] bArr, int i, boolean z) throws com.google.android.exoplayer2.g.f {
        this.o.a(bArr, i);
        this.p.a();
        this.r.clear();
        x60.a(this.o);
        while (!TextUtils.isEmpty(this.o.y())) {
        }
        ArrayList arrayList = new ArrayList();
        while (true) {
            int iA = a(this.o);
            if (iA == 0) {
                return new y60(arrayList);
            }
            if (iA == 1) {
                b(this.o);
            } else if (iA == 2) {
                if (!arrayList.isEmpty()) {
                    throw new com.google.android.exoplayer2.g.f("A style block was found after the first cue.");
                }
                this.o.y();
                t60 t60VarA = this.q.a(this.o);
                if (t60VarA != null) {
                    this.r.add(t60VarA);
                }
            } else if (iA == 3 && this.n.a(this.o, this.p, this.r)) {
                arrayList.add(this.p.b());
                this.p.a();
            }
        }
    }

    public static int a(o80 o80Var) {
        int i = -1;
        int iD = 0;
        while (i == -1) {
            iD = o80Var.d();
            String strY = o80Var.y();
            i = strY == null ? 0 : "STYLE".equals(strY) ? 2 : "NOTE".startsWith(strY) ? 1 : 3;
        }
        o80Var.c(iD);
        return i;
    }

    public static void b(o80 o80Var) {
        while (!TextUtils.isEmpty(o80Var.y())) {
        }
    }
}
