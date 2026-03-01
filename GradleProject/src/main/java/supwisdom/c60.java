package supwisdom;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: SimpleSubtitleDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c60 extends a20<a70, b70, com.google.android.exoplayer2.g.f> implements p60 {
    public c60(String str) {
        super(new a70[2], new b70[2]);
        a(1024);
    }

    public abstract m60 a(byte[] bArr, int i, boolean z) throws com.google.android.exoplayer2.g.f;

    @Override // supwisdom.p60
    public void a(long j) {
    }

    @Override // supwisdom.a20
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public final a70 g() {
        return new a70();
    }

    @Override // supwisdom.a20
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public final b70 h() {
        return new f60(this);
    }

    public final void a(b70 b70Var) {
        super.a(b70Var);
    }

    @Override // supwisdom.a20
    public final com.google.android.exoplayer2.g.f a(a70 a70Var, b70 b70Var, boolean z) {
        try {
            ByteBuffer byteBuffer = a70Var.c;
            b70Var.a(a70Var.d, a(byteBuffer.array(), byteBuffer.limit(), z), a70Var.f);
            b70Var.d(Integer.MIN_VALUE);
            return null;
        } catch (com.google.android.exoplayer2.g.f e2) {
            return e2;
        }
    }
}
