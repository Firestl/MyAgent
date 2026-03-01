package supwisdom;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import supwisdom.u40;

/* JADX INFO: compiled from: DefaultTsPayloadReaderFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class d40 implements u40.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7292a;
    public final List<com.google.android.exoplayer2.j> b;

    public d40(int i) {
        this(i, Collections.emptyList());
    }

    @Override // supwisdom.u40.c
    public SparseArray<u40> a() {
        return new SparseArray<>();
    }

    public d40(int i, List<com.google.android.exoplayer2.j> list) {
        this.f7292a = i;
        if (!a(32) && list.isEmpty()) {
            list = Collections.singletonList(com.google.android.exoplayer2.j.a((String) null, "application/cea-608", (String) null, -1, 0, (String) null, (com.google.android.exoplayer2.c.a) null));
        }
        this.b = list;
    }

    @Override // supwisdom.u40.c
    public u40 a(int i, u40.b bVar) {
        if (i == 2) {
            return new n40(new h40());
        }
        if (i == 3 || i == 4) {
            return new n40(new l40(bVar.b));
        }
        if (i == 15) {
            if (a(2)) {
                return null;
            }
            return new n40(new c40(false, bVar.b));
        }
        if (i == 21) {
            return new n40(new k40());
        }
        if (i == 27) {
            if (a(4)) {
                return null;
            }
            return new n40(new i40(a(bVar), a(1), a(8)));
        }
        if (i == 36) {
            return new n40(new j40(a(bVar)));
        }
        if (i == 89) {
            return new n40(new f40(bVar.c));
        }
        if (i != 138) {
            if (i != 129) {
                if (i != 130) {
                    if (i == 134) {
                        if (a(16)) {
                            return null;
                        }
                        return new q40(new s40());
                    }
                    if (i != 135) {
                        return null;
                    }
                }
            }
            return new n40(new a40(bVar.b));
        }
        return new n40(new e40(bVar.b));
    }

    public final r40 a(u40.b bVar) {
        String str;
        int i;
        if (a(32)) {
            return new r40(this.b);
        }
        o80 o80Var = new o80(bVar.d);
        List<com.google.android.exoplayer2.j> arrayList = this.b;
        while (o80Var.b() > 0) {
            int iG = o80Var.g();
            int iD = o80Var.d() + o80Var.g();
            if (iG == 134) {
                arrayList = new ArrayList<>();
                int iG2 = o80Var.g() & 31;
                for (int i2 = 0; i2 < iG2; i2++) {
                    String strE = o80Var.e(3);
                    int iG3 = o80Var.g();
                    if ((iG3 & 128) != 0) {
                        i = iG3 & 63;
                        str = "application/cea-708";
                    } else {
                        str = "application/cea-608";
                        i = 1;
                    }
                    arrayList.add(com.google.android.exoplayer2.j.a((String) null, str, (String) null, -1, 0, strE, i, (com.google.android.exoplayer2.c.a) null));
                    o80Var.d(2);
                }
            }
            o80Var.c(iD);
        }
        return new r40(arrayList);
    }

    public final boolean a(int i) {
        return (i & this.f7292a) != 0;
    }
}
