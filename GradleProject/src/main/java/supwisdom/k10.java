package supwisdom;

import android.support.v4.media.session.MediaSessionCompat;
import com.baidu.speech.core.BDSHttpRequestMaker;
import com.facebook.imageutils.JfifUtil;
import java.nio.ByteBuffer;
import net.ossrs.yasea.SrsFlvMuxer;

/* JADX INFO: compiled from: Ac3Util.java */
/* JADX INFO: loaded from: classes.dex */
public final class k10 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f8108a = {1, 2, 3, 6};
    public static final int[] b = {48000, 44100, 32000};
    public static final int[] c = {24000, SrsFlvMuxer.SrsCodecAudioSampleRate.R22050, 16000};
    public static final int[] d = {2, 1, 2, 3, 3, 4, 4, 5};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f8109e = {32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, MediaSessionCompat.MAX_BITMAP_SIZE_IN_DP, 384, 448, 512, 576, 640};
    public static final int[] f = {69, 87, 104, 121, 139, 174, JfifUtil.MARKER_RST0, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINISH, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    /* JADX INFO: compiled from: Ac3Util.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f8110a;
        public final int b;
        public final int c;
        public final int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f8111e;

        public b(String str, int i, int i2, int i3, int i4) {
            this.f8110a = str;
            this.c = i;
            this.b = i2;
            this.d = i3;
            this.f8111e = i4;
        }
    }

    public static int a() {
        return 1536;
    }

    public static com.google.android.exoplayer2.j a(o80 o80Var, String str, String str2, com.google.android.exoplayer2.c.a aVar) {
        int i = b[(o80Var.g() & 192) >> 6];
        int iG = o80Var.g();
        int i2 = d[(iG & 56) >> 3];
        if ((iG & 4) != 0) {
            i2++;
        }
        return com.google.android.exoplayer2.j.a(str, "audio/ac3", null, -1, -1, i2, i, null, aVar, 0, str2);
    }

    public static com.google.android.exoplayer2.j b(o80 o80Var, String str, String str2, com.google.android.exoplayer2.c.a aVar) {
        o80Var.d(2);
        int i = b[(o80Var.g() & 192) >> 6];
        int iG = o80Var.g();
        int i2 = d[(iG & 14) >> 1];
        if ((iG & 1) != 0) {
            i2++;
        }
        return com.google.android.exoplayer2.j.a(str, "audio/eac3", null, -1, -1, i2, i, null, aVar, 0, str2);
    }

    public static b a(n80 n80Var) {
        int iC;
        int i;
        int i2;
        String str;
        int i3;
        int i4;
        int iB = n80Var.b();
        n80Var.b(40);
        boolean z = n80Var.c(5) == 16;
        n80Var.a(iB);
        int i5 = 6;
        if (z) {
            n80Var.b(21);
            int iC2 = (n80Var.c(11) + 1) * 2;
            int iC3 = n80Var.c(2);
            if (iC3 == 3) {
                i4 = c[n80Var.c(2)];
            } else {
                i5 = f8108a[n80Var.c(2)];
                i4 = b[iC3];
            }
            iC = n80Var.c(3);
            i3 = i5 * 256;
            i = iC2;
            i2 = i4;
            str = "audio/eac3";
        } else {
            n80Var.b(32);
            int iC4 = n80Var.c(2);
            int iA = a(iC4, n80Var.c(6));
            n80Var.b(8);
            iC = n80Var.c(3);
            if ((iC & 1) != 0 && iC != 1) {
                n80Var.b(2);
            }
            if ((iC & 4) != 0) {
                n80Var.b(2);
            }
            if (iC == 2) {
                n80Var.b(2);
            }
            i = iA;
            i2 = b[iC4];
            str = "audio/ac3";
            i3 = 1536;
        }
        return new b(str, d[iC] + (n80Var.d() ? 1 : 0), i2, i, i3);
    }

    public static int a(byte[] bArr) {
        if (bArr.length < 5) {
            return -1;
        }
        return a((bArr[4] & 192) >> 6, bArr[4] & 63);
    }

    public static int a(ByteBuffer byteBuffer) {
        return (((byteBuffer.get(byteBuffer.position() + 4) & 192) >> 6) != 3 ? f8108a[(byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4] : 6) * 256;
    }

    public static int a(int i, int i2) {
        int i3 = i2 / 2;
        if (i < 0) {
            return -1;
        }
        int[] iArr = b;
        if (i >= iArr.length || i2 < 0) {
            return -1;
        }
        int[] iArr2 = f;
        if (i3 >= iArr2.length) {
            return -1;
        }
        int i4 = iArr[i];
        if (i4 == 44100) {
            return (iArr2[i3] + (i2 % 2)) * 2;
        }
        int i5 = f8109e[i3];
        return i4 == 32000 ? i5 * 6 : i5 * 4;
    }
}
