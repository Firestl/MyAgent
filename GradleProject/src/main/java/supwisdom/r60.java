package supwisdom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import supwisdom.u60;

/* JADX INFO: compiled from: Mp4WebvttDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class r60 extends c60 {
    public static final int p = x80.g("payl");
    public static final int q = x80.g("sttg");
    public static final int r = x80.g("vttc");
    public final o80 n;
    public final u60.b o;

    public r60() {
        super("Mp4WebvttDecoder");
        this.n = new o80();
        this.o = new u60.b();
    }

    @Override // supwisdom.c60
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public s60 a(byte[] bArr, int i, boolean z) throws com.google.android.exoplayer2.g.f {
        this.n.a(bArr, i);
        ArrayList arrayList = new ArrayList();
        while (this.n.b() > 0) {
            if (this.n.b() < 8) {
                throw new com.google.android.exoplayer2.g.f("Incomplete Mp4Webvtt Top Level box header found.");
            }
            int iN = this.n.n();
            if (this.n.n() == r) {
                arrayList.add(a(this.n, this.o, iN - 8));
            } else {
                this.n.d(iN - 8);
            }
        }
        return new s60(arrayList);
    }

    public static y50 a(o80 o80Var, u60.b bVar, int i) throws com.google.android.exoplayer2.g.f {
        bVar.a();
        while (i > 0) {
            if (i < 8) {
                throw new com.google.android.exoplayer2.g.f("Incomplete vtt cue box header found.");
            }
            int iN = o80Var.n();
            int iN2 = o80Var.n();
            int i2 = iN - 8;
            String str = new String(o80Var.f8644a, o80Var.d(), i2);
            o80Var.d(i2);
            i = (i - 8) - i2;
            if (iN2 == q) {
                v60.a(str, bVar);
            } else if (iN2 == p) {
                v60.a((String) null, str.trim(), bVar, (List<t60>) Collections.emptyList());
            }
        }
        return bVar.b();
    }
}
