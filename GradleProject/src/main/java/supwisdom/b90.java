package supwisdom;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer2.e.d;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import supwisdom.d90;

/* JADX INFO: compiled from: MediaCodecVideoRenderer.java */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public class b90 extends com.google.android.exoplayer2.e.b {
    public static final int[] w0 = {1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    public final c90 V;
    public final d90.a W;
    public final long X;
    public final int Y;
    public final boolean Z;
    public com.google.android.exoplayer2.j[] a0;
    public b b0;
    public Surface c0;
    public int d0;
    public boolean e0;
    public long f0;
    public long g0;
    public int h0;
    public int i0;
    public int j0;
    public float k0;
    public int l0;
    public int m0;
    public int n0;
    public float o0;
    public int p0;
    public int q0;
    public int r0;
    public float s0;
    public boolean t0;
    public int u0;
    public c v0;

    /* JADX INFO: compiled from: MediaCodecVideoRenderer.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f7038a;
        public final int b;
        public final int c;

        public b(int i, int i2, int i3) {
            this.f7038a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    /* JADX INFO: compiled from: MediaCodecVideoRenderer.java */
    @TargetApi(23)
    public final class c implements MediaCodec.OnFrameRenderedListener {
        @Override // android.media.MediaCodec.OnFrameRenderedListener
        public void onFrameRendered(MediaCodec mediaCodec, long j, long j2) {
            b90 b90Var = b90.this;
            if (this != b90Var.v0) {
                return;
            }
            b90Var.D();
        }

        public c(MediaCodec mediaCodec) {
            mediaCodec.setOnFrameRenderedListener(this, new Handler());
        }
    }

    public b90(Context context, i50 i50Var, long j, c20<e20> c20Var, boolean z, Handler handler, d90 d90Var, int i) {
        super(2, i50Var, c20Var, z);
        this.X = j;
        this.Y = i;
        this.V = new c90(context);
        this.W = new d90.a(handler, d90Var);
        this.Z = K();
        this.f0 = -9223372036854775807L;
        this.l0 = -1;
        this.m0 = -1;
        this.o0 = -1.0f;
        this.k0 = -1.0f;
        this.d0 = 1;
        G();
    }

    private void C() {
        if (this.e0) {
            this.W.a(this.c0);
        }
    }

    public static boolean K() {
        return x80.f9718a <= 22 && "foster".equals(x80.b) && "NVIDIA".equals(x80.c);
    }

    public static void d(MediaCodec mediaCodec, int i) {
        mediaCodec.setVideoScalingMode(i);
    }

    public static int e(com.google.android.exoplayer2.j jVar) {
        int i = jVar.m;
        if (i == -1) {
            return 0;
        }
        return i;
    }

    private void o() {
        this.f0 = this.X > 0 ? SystemClock.elapsedRealtime() + this.X : -9223372036854775807L;
    }

    private void p() {
        MediaCodec mediaCodecY;
        this.e0 = false;
        if (x80.f9718a < 23 || !this.t0 || (mediaCodecY = y()) == null) {
            return;
        }
        this.v0 = new c(mediaCodecY);
    }

    public void D() {
        if (this.e0) {
            return;
        }
        this.e0 = true;
        this.W.a(this.c0);
    }

    public final void G() {
        this.p0 = -1;
        this.q0 = -1;
        this.s0 = -1.0f;
        this.r0 = -1;
    }

    public final void H() {
        if (this.p0 == this.l0 && this.q0 == this.m0 && this.r0 == this.n0 && this.s0 == this.o0) {
            return;
        }
        this.W.a(this.l0, this.m0, this.n0, this.o0);
        this.p0 = this.l0;
        this.q0 = this.m0;
        this.r0 = this.n0;
        this.s0 = this.o0;
    }

    public final void I() {
        if (this.p0 == -1 && this.q0 == -1) {
            return;
        }
        this.W.a(this.l0, this.m0, this.n0, this.o0);
    }

    public final void J() {
        if (this.h0 > 0) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            this.W.a(this.h0, jElapsedRealtime - this.g0);
            this.h0 = 0;
            this.g0 = jElapsedRealtime;
        }
    }

    @Override // com.google.android.exoplayer2.e.b
    public int a(i50 i50Var, com.google.android.exoplayer2.j jVar) throws d.b {
        boolean z;
        int i;
        int i2;
        String str = jVar.f;
        if (!l80.b(str)) {
            return 0;
        }
        com.google.android.exoplayer2.c.a aVar = jVar.i;
        if (aVar != null) {
            z = false;
            for (int i3 = 0; i3 < aVar.c; i3++) {
                z |= aVar.a(i3).f2269e;
            }
        } else {
            z = false;
        }
        h50 h50VarA = i50Var.a(str, z);
        if (h50VarA == null) {
            return 1;
        }
        boolean zA = h50VarA.a(jVar.c);
        if (zA && (i = jVar.j) > 0 && (i2 = jVar.k) > 0) {
            if (x80.f9718a >= 21) {
                zA = h50VarA.a(i, i2, jVar.l);
            } else {
                boolean z2 = i * i2 <= com.google.android.exoplayer2.e.d.b();
                if (!z2) {
                    Log.d("MediaCodecVideoRenderer", "FalseCheck [legacyFrameSize, " + jVar.j + Constants.Name.X + jVar.k + "] [" + x80.f9719e + Operators.ARRAY_END_STR);
                }
                zA = z2;
            }
        }
        return (zA ? 3 : 2) | (h50VarA.b ? 8 : 4) | (h50VarA.c ? 16 : 0);
    }

    @Override // com.google.android.exoplayer2.e.b
    public void b(com.google.android.exoplayer2.j jVar) throws com.google.android.exoplayer2.e {
        super.b(jVar);
        this.W.a(jVar);
        this.k0 = d(jVar);
        this.j0 = e(jVar);
    }

    public boolean b(long j, long j2) {
        return j < -30000;
    }

    public final void c(MediaCodec mediaCodec, int i) {
        H();
        v80.a("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, true);
        v80.a();
        this.T.d++;
        this.i0 = 0;
        D();
    }

    @Override // com.google.android.exoplayer2.e.b, supwisdom.h90
    public boolean n() {
        if ((this.e0 || super.x()) && super.n()) {
            this.f0 = -9223372036854775807L;
            return true;
        }
        if (this.f0 == -9223372036854775807L) {
            return false;
        }
        if (SystemClock.elapsedRealtime() < this.f0) {
            return true;
        }
        this.f0 = -9223372036854775807L;
        return false;
    }

    @Override // com.google.android.exoplayer2.e.b, supwisdom.t10
    public void q() {
        super.q();
        this.h0 = 0;
        this.g0 = SystemClock.elapsedRealtime();
        this.f0 = -9223372036854775807L;
    }

    @Override // com.google.android.exoplayer2.e.b, supwisdom.t10
    public void r() {
        J();
        super.r();
    }

    @Override // com.google.android.exoplayer2.e.b, supwisdom.t10
    public void s() {
        this.l0 = -1;
        this.m0 = -1;
        this.o0 = -1.0f;
        this.k0 = -1.0f;
        G();
        p();
        this.V.b();
        this.v0 = null;
        try {
            super.s();
        } finally {
            this.T.a();
            this.W.b(this.T);
        }
    }

    @Override // com.google.android.exoplayer2.e.b
    public boolean x() {
        Surface surface;
        return super.x() && (surface = this.c0) != null && surface.isValid();
    }

    public static float d(com.google.android.exoplayer2.j jVar) {
        float f = jVar.n;
        if (f == -1.0f) {
            return 1.0f;
        }
        return f;
    }

    public final void b(MediaCodec mediaCodec, int i) {
        v80.a("dropVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        v80.a();
        x10 x10Var = this.T;
        x10Var.f++;
        this.h0++;
        int i2 = this.i0 + 1;
        this.i0 = i2;
        x10Var.g = Math.max(i2, x10Var.g);
        if (this.h0 == this.Y) {
            J();
        }
    }

    public static int c(com.google.android.exoplayer2.j jVar) {
        int i = jVar.g;
        return i != -1 ? i : a(jVar.f, jVar.j, jVar.k);
    }

    @Override // com.google.android.exoplayer2.e.b, supwisdom.t10
    public void a(boolean z) throws com.google.android.exoplayer2.e {
        super.a(z);
        int i = t().f8033a;
        this.u0 = i;
        this.t0 = i != 0;
        this.W.a(this.T);
        this.V.a();
    }

    @Override // supwisdom.t10
    public void a(com.google.android.exoplayer2.j[] jVarArr) throws com.google.android.exoplayer2.e {
        this.a0 = jVarArr;
        super.a(jVarArr);
    }

    @Override // com.google.android.exoplayer2.e.b, supwisdom.t10
    public void a(long j, boolean z) throws com.google.android.exoplayer2.e {
        super.a(j, z);
        p();
        this.i0 = 0;
        if (z) {
            o();
        } else {
            this.f0 = -9223372036854775807L;
        }
    }

    @Override // supwisdom.t10, supwisdom.j50.b
    public void a(int i, Object obj) throws com.google.android.exoplayer2.e {
        if (i == 1) {
            a((Surface) obj);
            return;
        }
        if (i == 4) {
            this.d0 = ((Integer) obj).intValue();
            MediaCodec mediaCodecY = y();
            if (mediaCodecY != null) {
                d(mediaCodecY, this.d0);
                return;
            }
            return;
        }
        super.a(i, obj);
    }

    public final void a(Surface surface) throws com.google.android.exoplayer2.e {
        if (this.c0 == surface) {
            if (surface != null) {
                I();
                C();
                return;
            }
            return;
        }
        this.c0 = surface;
        int iD = d();
        if (iD == 1 || iD == 2) {
            MediaCodec mediaCodecY = y();
            if (x80.f9718a >= 23 && mediaCodecY != null && surface != null) {
                a(mediaCodecY, surface);
            } else {
                z();
                F();
            }
        }
        if (surface != null) {
            I();
            p();
            if (iD == 2) {
                o();
                return;
            }
            return;
        }
        G();
        p();
    }

    @Override // com.google.android.exoplayer2.e.b
    public void a(h50 h50Var, MediaCodec mediaCodec, com.google.android.exoplayer2.j jVar, MediaCrypto mediaCrypto) throws d.b {
        b bVarA = a(h50Var, jVar, this.a0);
        this.b0 = bVarA;
        mediaCodec.configure(a(jVar, bVarA, this.Z, this.u0), this.c0, mediaCrypto, 0);
        if (x80.f9718a < 23 || !this.t0) {
            return;
        }
        this.v0 = new c(mediaCodec);
    }

    @Override // com.google.android.exoplayer2.e.b
    public void a(String str, long j, long j2) {
        this.W.a(str, j, j2);
    }

    @Override // com.google.android.exoplayer2.e.b
    public void a(y10 y10Var) {
        if (x80.f9718a >= 23 || !this.t0) {
            return;
        }
        D();
    }

    @Override // com.google.android.exoplayer2.e.b
    public void a(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        int integer;
        int integer2;
        boolean z = mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top");
        if (z) {
            integer = (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1;
        } else {
            integer = mediaFormat.getInteger("width");
        }
        this.l0 = integer;
        if (z) {
            integer2 = (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1;
        } else {
            integer2 = mediaFormat.getInteger("height");
        }
        this.m0 = integer2;
        this.o0 = this.k0;
        if (x80.f9718a >= 21) {
            int i = this.j0;
            if (i == 90 || i == 270) {
                int i2 = this.l0;
                this.l0 = this.m0;
                this.m0 = i2;
                this.o0 = 1.0f / this.o0;
            }
        } else {
            this.n0 = this.j0;
        }
        d(mediaCodec, this.d0);
    }

    @Override // com.google.android.exoplayer2.e.b
    public boolean a(MediaCodec mediaCodec, boolean z, com.google.android.exoplayer2.j jVar, com.google.android.exoplayer2.j jVar2) {
        if (a(z, jVar, jVar2)) {
            int i = jVar2.j;
            b bVar = this.b0;
            if (i <= bVar.f7038a && jVar2.k <= bVar.b && jVar2.g <= bVar.c) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.e.b
    public boolean a(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) {
        if (z) {
            a(mediaCodec, i);
            return true;
        }
        if (!this.e0) {
            if (x80.f9718a >= 21) {
                a(mediaCodec, i, System.nanoTime());
            } else {
                c(mediaCodec, i);
            }
            return true;
        }
        if (d() != 2) {
            return false;
        }
        long jElapsedRealtime = (j3 - j) - ((SystemClock.elapsedRealtime() * 1000) - j2);
        long jNanoTime = System.nanoTime();
        long jA = this.V.a(j3, jNanoTime + (jElapsedRealtime * 1000));
        long j4 = (jA - jNanoTime) / 1000;
        if (b(j4, j2)) {
            b(mediaCodec, i);
            return true;
        }
        if (x80.f9718a >= 21) {
            if (j4 < 50000) {
                a(mediaCodec, i, jA);
                return true;
            }
        } else if (j4 < com.igexin.push.config.c.k) {
            if (j4 > 11000) {
                try {
                    Thread.sleep((j4 - 10000) / 1000);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
            c(mediaCodec, i);
            return true;
        }
        return false;
    }

    public final void a(MediaCodec mediaCodec, int i) {
        v80.a("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        v80.a();
        this.T.f9698e++;
    }

    @TargetApi(21)
    public final void a(MediaCodec mediaCodec, int i, long j) {
        H();
        v80.a("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, j);
        v80.a();
        this.T.d++;
        this.i0 = 0;
        D();
    }

    @SuppressLint({"InlinedApi"})
    public static MediaFormat a(com.google.android.exoplayer2.j jVar, b bVar, boolean z, int i) {
        MediaFormat mediaFormatB = jVar.b();
        mediaFormatB.setInteger("max-width", bVar.f7038a);
        mediaFormatB.setInteger("max-height", bVar.b);
        int i2 = bVar.c;
        if (i2 != -1) {
            mediaFormatB.setInteger("max-input-size", i2);
        }
        if (z) {
            mediaFormatB.setInteger("auto-frc", 0);
        }
        if (i != 0) {
            a(mediaFormatB, i);
        }
        return mediaFormatB;
    }

    @TargetApi(23)
    public static void a(MediaCodec mediaCodec, Surface surface) {
        mediaCodec.setOutputSurface(surface);
    }

    @TargetApi(21)
    public static void a(MediaFormat mediaFormat, int i) {
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", i);
    }

    public static b a(h50 h50Var, com.google.android.exoplayer2.j jVar, com.google.android.exoplayer2.j[] jVarArr) throws d.b {
        int iMax = jVar.j;
        int iMax2 = jVar.k;
        int iC = c(jVar);
        if (jVarArr.length == 1) {
            return new b(iMax, iMax2, iC);
        }
        boolean z = false;
        for (com.google.android.exoplayer2.j jVar2 : jVarArr) {
            if (a(h50Var.b, jVar, jVar2)) {
                z |= jVar2.j == -1 || jVar2.k == -1;
                iMax = Math.max(iMax, jVar2.j);
                iMax2 = Math.max(iMax2, jVar2.k);
                iC = Math.max(iC, c(jVar2));
            }
        }
        if (z) {
            Log.w("MediaCodecVideoRenderer", "Resolutions unknown. Codec max resolution: " + iMax + Constants.Name.X + iMax2);
            Point pointA = a(h50Var, jVar);
            if (pointA != null) {
                iMax = Math.max(iMax, pointA.x);
                iMax2 = Math.max(iMax2, pointA.y);
                iC = Math.max(iC, a(jVar.f, iMax, iMax2));
                Log.w("MediaCodecVideoRenderer", "Codec max resolution adjusted to: " + iMax + Constants.Name.X + iMax2);
            }
        }
        return new b(iMax, iMax2, iC);
    }

    public static Point a(h50 h50Var, com.google.android.exoplayer2.j jVar) throws d.b {
        boolean z = jVar.k > jVar.j;
        int i = z ? jVar.k : jVar.j;
        int i2 = z ? jVar.j : jVar.k;
        float f = i2 / i;
        for (int i3 : w0) {
            int i4 = (int) (i3 * f);
            if (i3 <= i || i4 <= i2) {
                break;
            }
            if (x80.f9718a >= 21) {
                int i5 = z ? i4 : i3;
                if (!z) {
                    i3 = i4;
                }
                Point pointA = h50Var.a(i5, i3);
                if (h50Var.a(pointA.x, pointA.y, jVar.l)) {
                    return pointA;
                }
            } else {
                int iA = x80.a(i3, 16) * 16;
                int iA2 = x80.a(i4, 16) * 16;
                if (iA * iA2 <= com.google.android.exoplayer2.e.d.b()) {
                    int i6 = z ? iA2 : iA;
                    if (!z) {
                        iA = iA2;
                    }
                    return new Point(i6, iA);
                }
            }
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(java.lang.String r7, int r8, int r9) {
        /*
            r0 = -1
            if (r8 == r0) goto L86
            if (r9 != r0) goto L7
            goto L86
        L7:
            int r1 = r7.hashCode()
            r2 = 5
            r3 = 1
            r4 = 4
            r5 = 3
            r6 = 2
            switch(r1) {
                case -1664118616: goto L46;
                case -1662541442: goto L3c;
                case 1187890754: goto L32;
                case 1331836730: goto L28;
                case 1599127256: goto L1e;
                case 1599127257: goto L14;
                default: goto L13;
            }
        L13:
            goto L50
        L14:
            java.lang.String r1 = "video/x-vnd.on2.vp9"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L50
            r7 = 5
            goto L51
        L1e:
            java.lang.String r1 = "video/x-vnd.on2.vp8"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L50
            r7 = 3
            goto L51
        L28:
            java.lang.String r1 = "video/avc"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L50
            r7 = 2
            goto L51
        L32:
            java.lang.String r1 = "video/mp4v-es"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L50
            r7 = 1
            goto L51
        L3c:
            java.lang.String r1 = "video/hevc"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L50
            r7 = 4
            goto L51
        L46:
            java.lang.String r1 = "video/3gpp"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L50
            r7 = 0
            goto L51
        L50:
            r7 = -1
        L51:
            if (r7 == 0) goto L7d
            if (r7 == r3) goto L7d
            if (r7 == r6) goto L61
            if (r7 == r5) goto L7d
            if (r7 == r4) goto L5e
            if (r7 == r2) goto L5e
            return r0
        L5e:
            int r8 = r8 * r9
            goto L80
        L61:
            java.lang.String r7 = supwisdom.x80.d
            java.lang.String r1 = "BRAVIA 4K 2015"
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L6c
            return r0
        L6c:
            r7 = 16
            int r8 = supwisdom.x80.a(r8, r7)
            int r9 = supwisdom.x80.a(r9, r7)
            int r8 = r8 * r9
            int r8 = r8 * 16
            int r8 = r8 * 16
            goto L7f
        L7d:
            int r8 = r8 * r9
        L7f:
            r4 = 2
        L80:
            int r8 = r8 * 3
            int r4 = r4 * 2
            int r8 = r8 / r4
            return r8
        L86:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.b90.a(java.lang.String, int, int):int");
    }

    public static boolean a(boolean z, com.google.android.exoplayer2.j jVar, com.google.android.exoplayer2.j jVar2) {
        return jVar.f.equals(jVar2.f) && e(jVar) == e(jVar2) && (z || (jVar.j == jVar2.j && jVar.k == jVar2.k));
    }
}
