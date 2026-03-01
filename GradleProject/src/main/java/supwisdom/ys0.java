package supwisdom;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: AbstractFullBox.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ys0 extends ws0 implements vs {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9910e;
    public int f;

    public ys0(String str) {
        super(str);
    }

    public void a(int i) {
        this.f = i;
    }

    public final void c(ByteBuffer byteBuffer) {
        ks.c(byteBuffer, this.f9910e);
        ks.b(byteBuffer, this.f);
    }

    public int e() {
        return this.f;
    }

    public int f() {
        return this.f9910e;
    }
}
