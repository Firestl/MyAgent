package supwisdom;

import java.util.List;

/* JADX INFO: compiled from: DvbDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class z50 extends c60 {
    public final a60 n;

    public z50(List<byte[]> list) {
        super("DvbDecoder");
        o80 o80Var = new o80(list.get(0));
        this.n = new a60(o80Var.h(), o80Var.h());
    }

    @Override // supwisdom.c60
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public b60 a(byte[] bArr, int i, boolean z) {
        if (z) {
            this.n.a();
        }
        return new b60(this.n.a(bArr, i));
    }
}
