package supwisdom;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.a.e;
import com.google.android.exoplayer2.e.d;
import java.nio.ByteBuffer;
import supwisdom.m10;

/* JADX INFO: compiled from: MediaCodecAudioRenderer.java */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public class p10 extends com.google.android.exoplayer2.e.b implements k80 {
    public final m10.a V;
    public final com.google.android.exoplayer2.a.e W;
    public boolean X;
    public boolean Y;
    public MediaFormat Z;
    public int a0;
    public int b0;
    public long c0;
    public boolean d0;

    public p10(i50 i50Var, c20<e20> c20Var, boolean z, Handler handler, m10 m10Var, l10 l10Var, com.google.android.exoplayer2.a.c... cVarArr) {
        super(1, i50Var, c20Var, z);
        this.W = new com.google.android.exoplayer2.a.e(l10Var, cVarArr, new b());
        this.V = new m10.a(handler, m10Var);
    }

    public void D() {
    }

    @Override // com.google.android.exoplayer2.e.b
    public void E() throws com.google.android.exoplayer2.e {
        try {
            this.W.c();
        } catch (e.h e2) {
            throw com.google.android.exoplayer2.e.a(e2, v());
        }
    }

    public void a(int i, long j, long j2) {
    }

    public void b(int i) {
    }

    @Override // com.google.android.exoplayer2.e.b
    public void b(com.google.android.exoplayer2.j jVar) throws com.google.android.exoplayer2.e {
        super.b(jVar);
        this.V.a(jVar);
        this.a0 = "audio/raw".equals(jVar.f) ? jVar.t : 2;
        this.b0 = jVar.r;
    }

    @Override // supwisdom.t10, supwisdom.h90
    public k80 c() {
        return this;
    }

    @Override // com.google.android.exoplayer2.e.b, supwisdom.h90
    public boolean n() {
        return this.W.e() || super.n();
    }

    @Override // supwisdom.k80
    public long o() {
        long jA = this.W.a(u());
        if (jA != Long.MIN_VALUE) {
            if (!this.d0) {
                jA = Math.max(this.c0, jA);
            }
            this.c0 = jA;
            this.d0 = false;
        }
        return this.c0;
    }

    @Override // supwisdom.k80
    public g90 p() {
        return this.W.f();
    }

    @Override // com.google.android.exoplayer2.e.b, supwisdom.t10
    public void q() {
        super.q();
        this.W.a();
    }

    @Override // com.google.android.exoplayer2.e.b, supwisdom.t10
    public void r() {
        this.W.h();
        super.r();
    }

    @Override // com.google.android.exoplayer2.e.b, supwisdom.t10
    public void s() {
        try {
            this.W.j();
            try {
                super.s();
            } finally {
            }
        } catch (Throwable th) {
            try {
                super.s();
                throw th;
            } finally {
            }
        }
    }

    @Override // com.google.android.exoplayer2.e.b, supwisdom.h90
    public boolean u() {
        return super.u() && this.W.d();
    }

    /* JADX INFO: compiled from: MediaCodecAudioRenderer.java */
    public final class b implements e.i {
        public b() {
        }

        @Override // com.google.android.exoplayer2.a.e.i
        public void a(int i) {
            p10.this.V.a(i);
            p10.this.b(i);
        }

        @Override // com.google.android.exoplayer2.a.e.i
        public void a() {
            p10.this.D();
            p10.this.d0 = true;
        }

        @Override // com.google.android.exoplayer2.a.e.i
        public void a(int i, long j, long j2) {
            p10.this.V.a(i, j, j2);
            p10.this.a(i, j, j2);
        }
    }

    @Override // com.google.android.exoplayer2.e.b
    public int a(i50 i50Var, com.google.android.exoplayer2.j jVar) throws d.b {
        int i;
        int i2;
        String str = jVar.f;
        boolean z = false;
        if (!l80.a(str)) {
            return 0;
        }
        int i3 = x80.f9718a >= 21 ? 16 : 0;
        if (a(str) && i50Var.a() != null) {
            return i3 | 4 | 3;
        }
        h50 h50VarA = i50Var.a(str, false);
        if (h50VarA == null) {
            return 1;
        }
        if (x80.f9718a < 21 || (((i = jVar.s) == -1 || h50VarA.a(i)) && ((i2 = jVar.r) == -1 || h50VarA.b(i2)))) {
            z = true;
        }
        return i3 | 4 | (z ? 3 : 2);
    }

    public static boolean b(String str) {
        return x80.f9718a < 24 && "OMX.SEC.aac.dec".equals(str) && "samsung".equals(x80.c) && (x80.b.startsWith("zeroflte") || x80.b.startsWith("herolte") || x80.b.startsWith("heroqlte"));
    }

    @Override // com.google.android.exoplayer2.e.b
    public h50 a(i50 i50Var, com.google.android.exoplayer2.j jVar, boolean z) throws d.b {
        h50 h50VarA;
        if (a(jVar.f) && (h50VarA = i50Var.a()) != null) {
            this.X = true;
            return h50VarA;
        }
        this.X = false;
        return super.a(i50Var, jVar, z);
    }

    public boolean a(String str) {
        return this.W.a(str);
    }

    @Override // com.google.android.exoplayer2.e.b
    public void a(h50 h50Var, MediaCodec mediaCodec, com.google.android.exoplayer2.j jVar, MediaCrypto mediaCrypto) {
        this.Y = b(h50Var.f7802a);
        if (this.X) {
            MediaFormat mediaFormatB = jVar.b();
            this.Z = mediaFormatB;
            mediaFormatB.setString("mime", "audio/raw");
            mediaCodec.configure(this.Z, (Surface) null, mediaCrypto, 0);
            this.Z.setString("mime", jVar.f);
            return;
        }
        mediaCodec.configure(jVar.b(), (Surface) null, mediaCrypto, 0);
        this.Z = null;
    }

    @Override // com.google.android.exoplayer2.e.b
    public void a(String str, long j, long j2) {
        this.V.a(str, j, j2);
    }

    @Override // com.google.android.exoplayer2.e.b
    public void a(MediaCodec mediaCodec, MediaFormat mediaFormat) throws com.google.android.exoplayer2.e {
        int[] iArr;
        int i;
        boolean z = this.Z != null;
        String string = z ? this.Z.getString("mime") : "audio/raw";
        if (z) {
            mediaFormat = this.Z;
        }
        int integer = mediaFormat.getInteger("channel-count");
        int integer2 = mediaFormat.getInteger("sample-rate");
        if (this.Y && integer == 6 && (i = this.b0) < 6) {
            iArr = new int[i];
            for (int i2 = 0; i2 < this.b0; i2++) {
                iArr[i2] = i2;
            }
        } else {
            iArr = null;
        }
        try {
            this.W.a(string, integer, integer2, this.a0, 0, iArr);
        } catch (e.c e2) {
            throw com.google.android.exoplayer2.e.a(e2, v());
        }
    }

    @Override // com.google.android.exoplayer2.e.b, supwisdom.t10
    public void a(boolean z) throws com.google.android.exoplayer2.e {
        super.a(z);
        this.V.a(this.T);
        int i = t().f8033a;
        if (i != 0) {
            this.W.b(i);
        } else {
            this.W.g();
        }
    }

    @Override // com.google.android.exoplayer2.e.b, supwisdom.t10
    public void a(long j, boolean z) throws com.google.android.exoplayer2.e {
        super.a(j, z);
        this.W.i();
        this.c0 = j;
        this.d0 = true;
    }

    @Override // supwisdom.k80
    public g90 a(g90 g90Var) {
        return this.W.a(g90Var);
    }

    @Override // com.google.android.exoplayer2.e.b
    public boolean a(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) throws com.google.android.exoplayer2.e {
        if (this.X && (i2 & 2) != 0) {
            mediaCodec.releaseOutputBuffer(i, false);
            return true;
        }
        if (z) {
            mediaCodec.releaseOutputBuffer(i, false);
            this.T.f9698e++;
            this.W.b();
            return true;
        }
        try {
            if (!this.W.a(byteBuffer, j3)) {
                return false;
            }
            mediaCodec.releaseOutputBuffer(i, false);
            this.T.d++;
            return true;
        } catch (e.d | e.h e2) {
            throw com.google.android.exoplayer2.e.a(e2, v());
        }
    }

    @Override // supwisdom.t10, supwisdom.j50.b
    public void a(int i, Object obj) throws com.google.android.exoplayer2.e {
        if (i == 2) {
            this.W.a(((Float) obj).floatValue());
        } else if (i != 3) {
            super.a(i, obj);
        } else {
            this.W.a(((Integer) obj).intValue());
        }
    }
}
