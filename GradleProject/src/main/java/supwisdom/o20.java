package supwisdom;

import android.util.SparseArray;
import com.baidu.speech.core.BDSHttpRequestMaker;
import com.facebook.imageutils.JfifUtil;
import com.google.android.exoplayer2.c.a;
import com.uc.crashsdk.export.CrashStatKey;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.bouncycastle.pqc.crypto.qtesla.HashUtils;
import supwisdom.e50;
import tv.cjump.jni.DeviceUtils;

/* JADX INFO: compiled from: MatroskaExtractor.java */
/* JADX INFO: loaded from: classes.dex */
public final class o20 implements y30 {
    public static final byte[] Z;
    public static final byte[] a0;
    public static final UUID b0;
    public long A;
    public j80 B;
    public j80 C;
    public boolean D;
    public int E;
    public long F;
    public long G;
    public int H;
    public int I;
    public int[] J;
    public int K;
    public int L;
    public int M;
    public int N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public byte S;
    public int T;
    public int U;
    public int V;
    public boolean W;
    public boolean X;
    public z40 Y;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m20 f8604a;
    public final q20 b;
    public final SparseArray<c> c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final o80 f8605e;
    public final o80 f;
    public final o80 g;
    public final o80 h;
    public final o80 i;
    public final o80 j;
    public final o80 k;
    public final o80 l;
    public final o80 m;
    public ByteBuffer n;
    public long o;
    public long p;
    public long q;
    public long r;
    public long s;
    public c t;
    public boolean u;
    public int v;
    public long w;
    public boolean x;
    public long y;
    public long z;

    /* JADX INFO: compiled from: MatroskaExtractor.java */
    public static class a implements a50 {
        @Override // supwisdom.a50
        public y30[] a() {
            return new y30[]{new o20()};
        }
    }

    /* JADX INFO: compiled from: MatroskaExtractor.java */
    public final class b implements n20 {
        public b() {
        }

        @Override // supwisdom.n20
        public int a(int i) {
            return o20.this.a(i);
        }

        @Override // supwisdom.n20
        public boolean b(int i) {
            return o20.this.b(i);
        }

        @Override // supwisdom.n20
        public void c(int i) throws com.google.android.exoplayer2.n {
            o20.this.c(i);
        }

        public /* synthetic */ b(o20 o20Var, a aVar) {
            this();
        }

        @Override // supwisdom.n20
        public void a(int i, long j, long j2) throws com.google.android.exoplayer2.n {
            o20.this.a(i, j, j2);
        }

        @Override // supwisdom.n20
        public void a(int i, long j) throws com.google.android.exoplayer2.n {
            o20.this.a(i, j);
        }

        @Override // supwisdom.n20
        public void a(int i, double d) throws com.google.android.exoplayer2.n {
            o20.this.a(i, d);
        }

        @Override // supwisdom.n20
        public void a(int i, String str) throws com.google.android.exoplayer2.n {
            o20.this.a(i, str);
        }

        @Override // supwisdom.n20
        public void a(int i, int i2, v40 v40Var) throws InterruptedException, IOException {
            o20.this.a(i, i2, v40Var);
        }
    }

    /* JADX INFO: compiled from: MatroskaExtractor.java */
    public static final class c {
        public float A;
        public float B;
        public float C;
        public float D;
        public float E;
        public float F;
        public int G;
        public int H;
        public int I;
        public long J;
        public long K;
        public boolean L;
        public boolean M;
        public String N;
        public f50 O;
        public int P;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f8607a;
        public int b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f8608e;
        public byte[] f;
        public byte[] g;
        public byte[] h;
        public com.google.android.exoplayer2.c.a i;
        public int j;
        public int k;
        public int l;
        public int m;
        public int n;
        public byte[] o;
        public int p;
        public boolean q;
        public int r;
        public int s;
        public int t;
        public int u;
        public int v;
        public float w;
        public float x;
        public float y;
        public float z;

        public c() {
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = null;
            this.p = -1;
            this.q = false;
            this.r = -1;
            this.s = -1;
            this.t = -1;
            this.u = 1000;
            this.v = 200;
            this.w = -1.0f;
            this.x = -1.0f;
            this.y = -1.0f;
            this.z = -1.0f;
            this.A = -1.0f;
            this.B = -1.0f;
            this.C = -1.0f;
            this.D = -1.0f;
            this.E = -1.0f;
            this.F = -1.0f;
            this.G = 1;
            this.H = -1;
            this.I = 8000;
            this.J = 0L;
            this.K = 0L;
            this.M = true;
            this.N = "eng";
        }

        public static boolean b(o80 o80Var) throws com.google.android.exoplayer2.n {
            try {
                int i = o80Var.i();
                if (i == 1) {
                    return true;
                }
                if (i != 65534) {
                    return false;
                }
                o80Var.c(24);
                if (o80Var.p() == o20.b0.getMostSignificantBits()) {
                    if (o80Var.p() == o20.b0.getLeastSignificantBits()) {
                        return true;
                    }
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new com.google.android.exoplayer2.n("Error parsing MS/ACM codec private");
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:148:0x02ee  */
        /* JADX WARN: Removed duplicated region for block: B:151:0x02f6  */
        /* JADX WARN: Removed duplicated region for block: B:152:0x031d  */
        /* JADX WARN: Removed duplicated region for block: B:89:0x0154  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void a(supwisdom.z40 r43, int r44) throws com.google.android.exoplayer2.n {
            /*
                Method dump skipped, instruction units count: 1188
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.o20.c.a(supwisdom.z40, int):void");
        }

        public /* synthetic */ c(a aVar) {
            this();
        }

        public final byte[] a() {
            if (this.w == -1.0f || this.x == -1.0f || this.y == -1.0f || this.z == -1.0f || this.A == -1.0f || this.B == -1.0f || this.C == -1.0f || this.D == -1.0f || this.E == -1.0f || this.F == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            byteBufferWrap.put((byte) 0);
            byteBufferWrap.putShort((short) ((this.w * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) ((this.x * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) ((this.y * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) ((this.z * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) ((this.A * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) ((this.B * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) ((this.C * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) ((this.D * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) (this.E + 0.5f));
            byteBufferWrap.putShort((short) (this.F + 0.5f));
            byteBufferWrap.putShort((short) this.u);
            byteBufferWrap.putShort((short) this.v);
            return bArr;
        }

        public static List<byte[]> a(o80 o80Var) throws com.google.android.exoplayer2.n {
            try {
                o80Var.d(16);
                if (o80Var.m() != 826496599) {
                    return null;
                }
                byte[] bArr = o80Var.f8644a;
                for (int iD = o80Var.d() + 20; iD < bArr.length - 4; iD++) {
                    if (bArr[iD] == 0 && bArr[iD + 1] == 0 && bArr[iD + 2] == 1 && bArr[iD + 3] == 15) {
                        return Collections.singletonList(Arrays.copyOfRange(bArr, iD, bArr.length));
                    }
                }
                throw new com.google.android.exoplayer2.n("Failed to find FourCC VC1 initialization data");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new com.google.android.exoplayer2.n("Error parsing FourCC VC1 codec private");
            }
        }

        public static List<byte[]> a(byte[] bArr) throws com.google.android.exoplayer2.n {
            try {
                if (bArr[0] == 2) {
                    int i = 1;
                    int i2 = 0;
                    while (bArr[i] == -1) {
                        i2 += 255;
                        i++;
                    }
                    int i3 = i + 1;
                    int i4 = i2 + bArr[i];
                    int i5 = 0;
                    while (bArr[i3] == -1) {
                        i5 += 255;
                        i3++;
                    }
                    int i6 = i3 + 1;
                    int i7 = i5 + bArr[i3];
                    if (bArr[i6] == 1) {
                        byte[] bArr2 = new byte[i4];
                        System.arraycopy(bArr, i6, bArr2, 0, i4);
                        int i8 = i6 + i4;
                        if (bArr[i8] == 3) {
                            int i9 = i8 + i7;
                            if (bArr[i9] == 5) {
                                byte[] bArr3 = new byte[bArr.length - i9];
                                System.arraycopy(bArr, i9, bArr3, 0, bArr.length - i9);
                                ArrayList arrayList = new ArrayList(2);
                                arrayList.add(bArr2);
                                arrayList.add(bArr3);
                                return arrayList;
                            }
                            throw new com.google.android.exoplayer2.n("Error parsing vorbis codec private");
                        }
                        throw new com.google.android.exoplayer2.n("Error parsing vorbis codec private");
                    }
                    throw new com.google.android.exoplayer2.n("Error parsing vorbis codec private");
                }
                throw new com.google.android.exoplayer2.n("Error parsing vorbis codec private");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new com.google.android.exoplayer2.n("Error parsing vorbis codec private");
            }
        }
    }

    static {
        new a();
        Z = new byte[]{49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
        a0 = new byte[]{32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
        b0 = new UUID(72057594037932032L, -9223371306706625679L);
    }

    public o20() {
        this(0);
    }

    public int a(int i) {
        switch (i) {
            case com.igexin.push.core.a.c.h.b /* 131 */:
            case HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE /* 136 */:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case JfifUtil.MARKER_RST7 /* 215 */:
            case 231:
            case BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL /* 241 */:
            case 251:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 21930:
            case 21945:
            case 21946:
            case 21947:
            case 21948:
            case 21949:
            case 22186:
            case 22203:
            case 25188:
            case 2352003:
            case 2807729:
                return 2;
            case 134:
            case 17026:
            case 2274716:
                return 3;
            case 160:
            case 174:
            case DeviceUtils.EM_AARCH64 /* 183 */:
            case 187:
            case 224:
            case 225:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case 161:
            case 163:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
            case 21969:
            case 21970:
            case 21971:
            case 21972:
            case 21973:
            case 21974:
            case 21975:
            case 21976:
            case 21977:
            case 21978:
                return 5;
            default:
                return 0;
        }
    }

    @Override // supwisdom.y30
    public boolean a(v40 v40Var) throws InterruptedException, IOException {
        return new p20().a(v40Var);
    }

    public final e50 b() {
        j80 j80Var;
        j80 j80Var2;
        if (this.p == -1 || this.s == -9223372036854775807L || (j80Var = this.B) == null || j80Var.a() == 0 || (j80Var2 = this.C) == null || j80Var2.a() != this.B.a()) {
            this.B = null;
            this.C = null;
            return new e50.a(this.s);
        }
        int iA = this.B.a();
        int[] iArr = new int[iA];
        long[] jArr = new long[iA];
        long[] jArr2 = new long[iA];
        long[] jArr3 = new long[iA];
        int i = 0;
        for (int i2 = 0; i2 < iA; i2++) {
            jArr3[i2] = this.B.a(i2);
            jArr[i2] = this.p + this.C.a(i2);
        }
        while (true) {
            int i3 = iA - 1;
            if (i >= i3) {
                iArr[i3] = (int) ((this.p + this.o) - jArr[i3]);
                jArr2[i3] = this.s - jArr3[i3];
                this.B = null;
                this.C = null;
                return new k20(iArr, jArr, jArr2, jArr3);
            }
            int i4 = i + 1;
            iArr[i] = (int) (jArr[i4] - jArr[i]);
            jArr2[i] = jArr3[i4] - jArr3[i];
            i = i4;
        }
    }

    public boolean b(int i) {
        return i == 357149030 || i == 524531317 || i == 475249515 || i == 374648427;
    }

    @Override // supwisdom.y30
    public void c() {
    }

    public void c(int i) throws com.google.android.exoplayer2.n {
        if (i == 160) {
            if (this.E != 2) {
                return;
            }
            if (!this.X) {
                this.M |= 1;
            }
            a(this.c.get(this.K), this.F);
            this.E = 0;
            return;
        }
        if (i == 174) {
            if (a(this.t.f8607a)) {
                c cVar = this.t;
                cVar.a(this.Y, cVar.b);
                SparseArray<c> sparseArray = this.c;
                c cVar2 = this.t;
                sparseArray.put(cVar2.b, cVar2);
            }
            this.t = null;
            return;
        }
        if (i == 19899) {
            int i2 = this.v;
            if (i2 != -1) {
                long j = this.w;
                if (j != -1) {
                    if (i2 == 475249515) {
                        this.y = j;
                        return;
                    }
                    return;
                }
            }
            throw new com.google.android.exoplayer2.n("Mandatory element SeekID or SeekPosition not found");
        }
        if (i == 25152) {
            c cVar3 = this.t;
            if (cVar3.f8608e) {
                if (cVar3.g == null) {
                    throw new com.google.android.exoplayer2.n("Encrypted Track found but ContentEncKeyID was not found");
                }
                cVar3.i = new com.google.android.exoplayer2.c.a(new a.C0050a(b20.b, "video/webm", this.t.g));
                return;
            }
            return;
        }
        if (i == 28032) {
            c cVar4 = this.t;
            if (cVar4.f8608e && cVar4.f != null) {
                throw new com.google.android.exoplayer2.n("Combining encryption and compression is not supported");
            }
            return;
        }
        if (i == 357149030) {
            if (this.q == -9223372036854775807L) {
                this.q = 1000000L;
            }
            long j2 = this.r;
            if (j2 != -9223372036854775807L) {
                this.s = a(j2);
                return;
            }
            return;
        }
        if (i == 374648427) {
            if (this.c.size() == 0) {
                throw new com.google.android.exoplayer2.n("No valid tracks were found");
            }
            this.Y.a();
        } else if (i == 475249515 && !this.u) {
            this.Y.a(b());
            this.u = true;
        }
    }

    public o20(int i) {
        this(new l20(), i);
    }

    @Override // supwisdom.y30
    public void a(z40 z40Var) {
        this.Y = z40Var;
    }

    public o20(m20 m20Var, int i) {
        this.p = -1L;
        this.q = -9223372036854775807L;
        this.r = -9223372036854775807L;
        this.s = -9223372036854775807L;
        this.y = -1L;
        this.z = -1L;
        this.A = -9223372036854775807L;
        this.f8604a = m20Var;
        m20Var.a(new b(this, null));
        this.d = (i & 1) == 0;
        this.b = new q20();
        this.c = new SparseArray<>();
        this.g = new o80(4);
        this.h = new o80(ByteBuffer.allocate(4).putInt(-1).array());
        this.i = new o80(4);
        this.f8605e = new o80(m80.f8362a);
        this.f = new o80(4);
        this.j = new o80();
        this.k = new o80();
        this.l = new o80(8);
        this.m = new o80();
    }

    @Override // supwisdom.y30
    public void a(long j, long j2) {
        this.A = -9223372036854775807L;
        this.E = 0;
        this.f8604a.a();
        this.b.a();
        a();
    }

    @Override // supwisdom.y30
    public int a(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        this.W = false;
        boolean zA = true;
        while (zA && !this.W) {
            zA = this.f8604a.a(v40Var);
            if (zA && a(d50Var, v40Var.c())) {
                return 1;
            }
        }
        return zA ? 0 : -1;
    }

    public void a(int i, long j, long j2) throws com.google.android.exoplayer2.n {
        if (i == 160) {
            this.X = false;
            return;
        }
        if (i == 174) {
            this.t = new c(null);
            return;
        }
        if (i == 187) {
            this.D = false;
            return;
        }
        if (i == 19899) {
            this.v = -1;
            this.w = -1L;
            return;
        }
        if (i == 20533) {
            this.t.f8608e = true;
            return;
        }
        if (i == 21968) {
            this.t.q = true;
            return;
        }
        if (i == 408125543) {
            long j3 = this.p;
            if (j3 != -1 && j3 != j) {
                throw new com.google.android.exoplayer2.n("Multiple Segment elements not supported");
            }
            this.p = j;
            this.o = j2;
            return;
        }
        if (i != 475249515) {
            if (i == 524531317 && !this.u) {
                if (this.d && this.y != -1) {
                    this.x = true;
                    return;
                } else {
                    this.Y.a(new e50.a(this.s));
                    this.u = true;
                    return;
                }
            }
            return;
        }
        this.B = new j80();
        this.C = new j80();
    }

    public void a(int i, long j) throws com.google.android.exoplayer2.n {
        if (i == 20529) {
            if (j == 0) {
                return;
            }
            throw new com.google.android.exoplayer2.n("ContentEncodingOrder " + j + " not supported");
        }
        if (i == 20530) {
            if (j == 1) {
                return;
            }
            throw new com.google.android.exoplayer2.n("ContentEncodingScope " + j + " not supported");
        }
        switch (i) {
            case com.igexin.push.core.a.c.h.b /* 131 */:
                this.t.c = (int) j;
                return;
            case HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE /* 136 */:
                this.t.L = j == 1;
                return;
            case 155:
                this.G = a(j);
                return;
            case 159:
                this.t.G = (int) j;
                return;
            case 176:
                this.t.j = (int) j;
                return;
            case 179:
                this.B.a(a(j));
                return;
            case 186:
                this.t.k = (int) j;
                return;
            case JfifUtil.MARKER_RST7 /* 215 */:
                this.t.b = (int) j;
                return;
            case 231:
                this.A = a(j);
                return;
            case BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL /* 241 */:
                if (this.D) {
                    return;
                }
                this.C.a(j);
                this.D = true;
                return;
            case 251:
                this.X = true;
                return;
            case 16980:
                if (j == 3) {
                    return;
                }
                throw new com.google.android.exoplayer2.n("ContentCompAlgo " + j + " not supported");
            case 17029:
                if (j < 1 || j > 2) {
                    throw new com.google.android.exoplayer2.n("DocTypeReadVersion " + j + " not supported");
                }
                return;
            case 17143:
                if (j == 1) {
                    return;
                }
                throw new com.google.android.exoplayer2.n("EBMLReadVersion " + j + " not supported");
            case 18401:
                if (j == 5) {
                    return;
                }
                throw new com.google.android.exoplayer2.n("ContentEncAlgo " + j + " not supported");
            case 18408:
                if (j == 1) {
                    return;
                }
                throw new com.google.android.exoplayer2.n("AESSettingsCipherMode " + j + " not supported");
            case 21420:
                this.w = j + this.p;
                return;
            case 21432:
                int i2 = (int) j;
                if (i2 == 0) {
                    this.t.p = 0;
                    return;
                }
                if (i2 == 1) {
                    this.t.p = 2;
                    return;
                } else if (i2 == 3) {
                    this.t.p = 1;
                    return;
                } else {
                    if (i2 != 15) {
                        return;
                    }
                    this.t.p = 3;
                    return;
                }
            case 21680:
                this.t.l = (int) j;
                return;
            case 21682:
                this.t.n = (int) j;
                return;
            case 21690:
                this.t.m = (int) j;
                return;
            case 21930:
                this.t.M = j == 1;
                return;
            case 22186:
                this.t.J = j;
                return;
            case 22203:
                this.t.K = j;
                return;
            case 25188:
                this.t.H = (int) j;
                return;
            case 2352003:
                this.t.d = (int) j;
                return;
            case 2807729:
                this.q = j;
                return;
            default:
                switch (i) {
                    case 21945:
                        int i3 = (int) j;
                        if (i3 == 1) {
                            this.t.t = 2;
                            return;
                        } else {
                            if (i3 != 2) {
                                return;
                            }
                            this.t.t = 1;
                            return;
                        }
                    case 21946:
                        int i4 = (int) j;
                        if (i4 != 1) {
                            if (i4 == 16) {
                                this.t.s = 6;
                                return;
                            } else if (i4 == 18) {
                                this.t.s = 7;
                                return;
                            } else if (i4 != 6 && i4 != 7) {
                                return;
                            }
                        }
                        this.t.s = 3;
                        return;
                    case 21947:
                        c cVar = this.t;
                        cVar.q = true;
                        int i5 = (int) j;
                        if (i5 == 1) {
                            cVar.r = 1;
                            return;
                        }
                        if (i5 == 9) {
                            cVar.r = 6;
                            return;
                        } else {
                            if (i5 == 4 || i5 == 5 || i5 == 6 || i5 == 7) {
                                this.t.r = 2;
                                return;
                            }
                            return;
                        }
                    case 21948:
                        this.t.u = (int) j;
                        return;
                    case 21949:
                        this.t.v = (int) j;
                        return;
                    default:
                        return;
                }
        }
    }

    public void a(int i, double d) {
        if (i == 181) {
            this.t.I = (int) d;
            return;
        }
        if (i != 17545) {
            switch (i) {
                case 21969:
                    this.t.w = (float) d;
                    break;
                case 21970:
                    this.t.x = (float) d;
                    break;
                case 21971:
                    this.t.y = (float) d;
                    break;
                case 21972:
                    this.t.z = (float) d;
                    break;
                case 21973:
                    this.t.A = (float) d;
                    break;
                case 21974:
                    this.t.B = (float) d;
                    break;
                case 21975:
                    this.t.C = (float) d;
                    break;
                case 21976:
                    this.t.D = (float) d;
                    break;
                case 21977:
                    this.t.E = (float) d;
                    break;
                case 21978:
                    this.t.F = (float) d;
                    break;
            }
            return;
        }
        this.r = (long) d;
    }

    public void a(int i, String str) throws com.google.android.exoplayer2.n {
        if (i == 134) {
            this.t.f8607a = str;
            return;
        }
        if (i != 17026) {
            if (i != 2274716) {
                return;
            }
            this.t.N = str;
        } else {
            if ("webm".equals(str) || "matroska".equals(str)) {
                return;
            }
            throw new com.google.android.exoplayer2.n("DocType " + str + " not supported");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x01ef, code lost:
    
        throw new com.google.android.exoplayer2.n("EBML lacing sample size out of range.");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(int r22, int r23, supwisdom.v40 r24) throws java.lang.InterruptedException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 672
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.o20.a(int, int, supwisdom.v40):void");
    }

    public final void a(c cVar, long j) {
        if ("S_TEXT/UTF8".equals(cVar.f8607a)) {
            a(cVar);
        }
        cVar.O.a(j, this.M, this.V, 0, cVar.g);
        this.W = true;
        a();
    }

    public final void a() {
        this.N = 0;
        this.V = 0;
        this.U = 0;
        this.O = false;
        this.P = false;
        this.R = false;
        this.T = 0;
        this.S = (byte) 0;
        this.Q = false;
        this.j.a();
    }

    public final void a(v40 v40Var, int i) throws InterruptedException, IOException {
        if (this.g.c() >= i) {
            return;
        }
        if (this.g.e() < i) {
            o80 o80Var = this.g;
            byte[] bArr = o80Var.f8644a;
            o80Var.a(Arrays.copyOf(bArr, Math.max(bArr.length * 2, i)), this.g.c());
        }
        o80 o80Var2 = this.g;
        v40Var.b(o80Var2.f8644a, o80Var2.c(), i - this.g.c());
        this.g.b(i);
    }

    public final void a(v40 v40Var, c cVar, int i) throws InterruptedException, IOException {
        int i2;
        if ("S_TEXT/UTF8".equals(cVar.f8607a)) {
            int length = Z.length + i;
            if (this.k.e() < length) {
                this.k.f8644a = Arrays.copyOf(Z, length + i);
            }
            v40Var.b(this.k.f8644a, Z.length, i);
            this.k.c(0);
            this.k.b(length);
            return;
        }
        f50 f50Var = cVar.O;
        if (!this.O) {
            if (cVar.f8608e) {
                this.M &= -1073741825;
                if (!this.P) {
                    v40Var.b(this.g.f8644a, 0, 1);
                    this.N++;
                    byte[] bArr = this.g.f8644a;
                    if ((bArr[0] & com.igexin.c.a.d.g.n) != 128) {
                        this.S = bArr[0];
                        this.P = true;
                    } else {
                        throw new com.google.android.exoplayer2.n("Extension bit is set in signal byte");
                    }
                }
                if ((this.S & 1) == 1) {
                    boolean z = (this.S & 2) == 2;
                    this.M |= 1073741824;
                    if (!this.Q) {
                        v40Var.b(this.l.f8644a, 0, 8);
                        this.N += 8;
                        this.Q = true;
                        this.g.f8644a[0] = (byte) ((z ? 128 : 0) | 8);
                        this.g.c(0);
                        f50Var.a(this.g, 1);
                        this.V++;
                        this.l.c(0);
                        f50Var.a(this.l, 8);
                        this.V += 8;
                    }
                    if (z) {
                        if (!this.R) {
                            v40Var.b(this.g.f8644a, 0, 1);
                            this.N++;
                            this.g.c(0);
                            this.T = this.g.g();
                            this.R = true;
                        }
                        int i3 = this.T * 4;
                        this.g.a(i3);
                        v40Var.b(this.g.f8644a, 0, i3);
                        this.N += i3;
                        short s = (short) ((this.T / 2) + 1);
                        int i4 = (s * 6) + 2;
                        ByteBuffer byteBuffer = this.n;
                        if (byteBuffer == null || byteBuffer.capacity() < i4) {
                            this.n = ByteBuffer.allocate(i4);
                        }
                        this.n.position(0);
                        this.n.putShort(s);
                        int i5 = 0;
                        int i6 = 0;
                        while (true) {
                            i2 = this.T;
                            if (i5 >= i2) {
                                break;
                            }
                            int iT = this.g.t();
                            if (i5 % 2 == 0) {
                                this.n.putShort((short) (iT - i6));
                            } else {
                                this.n.putInt(iT - i6);
                            }
                            i5++;
                            i6 = iT;
                        }
                        int i7 = (i - this.N) - i6;
                        if (i2 % 2 == 1) {
                            this.n.putInt(i7);
                        } else {
                            this.n.putShort((short) i7);
                            this.n.putInt(0);
                        }
                        this.m.a(this.n.array(), i4);
                        f50Var.a(this.m, i4);
                        this.V += i4;
                    }
                }
            } else {
                byte[] bArr2 = cVar.f;
                if (bArr2 != null) {
                    this.j.a(bArr2, bArr2.length);
                }
            }
            this.O = true;
        }
        int iC = i + this.j.c();
        if (!"V_MPEG4/ISO/AVC".equals(cVar.f8607a) && !"V_MPEGH/ISO/HEVC".equals(cVar.f8607a)) {
            while (true) {
                int i8 = this.N;
                if (i8 >= iC) {
                    break;
                } else {
                    a(v40Var, f50Var, iC - i8);
                }
            }
        } else {
            byte[] bArr3 = this.f.f8644a;
            bArr3[0] = 0;
            bArr3[1] = 0;
            bArr3[2] = 0;
            int i9 = cVar.P;
            int i10 = 4 - i9;
            while (this.N < iC) {
                int i11 = this.U;
                if (i11 == 0) {
                    a(v40Var, bArr3, i10, i9);
                    this.f.c(0);
                    this.U = this.f.t();
                    this.f8605e.c(0);
                    f50Var.a(this.f8605e, 4);
                    this.V += 4;
                } else {
                    this.U = i11 - a(v40Var, f50Var, i11);
                }
            }
        }
        if ("A_VORBIS".equals(cVar.f8607a)) {
            this.h.c(0);
            f50Var.a(this.h, 4);
            this.V += 4;
        }
    }

    public final void a(c cVar) {
        a(this.k.f8644a, this.G);
        f50 f50Var = cVar.O;
        o80 o80Var = this.k;
        f50Var.a(o80Var, o80Var.c());
        this.V += this.k.c();
    }

    public static void a(byte[] bArr, long j) {
        byte[] bArrC;
        if (j == -9223372036854775807L) {
            bArrC = a0;
        } else {
            int i = (int) (j / 3600000000L);
            long j2 = j - (((long) i) * 3600000000L);
            int i2 = (int) (j2 / 60000000);
            long j3 = j2 - ((long) (60000000 * i2));
            int i3 = (int) (j3 / 1000000);
            bArrC = x80.c(String.format(Locale.US, "%02d:%02d:%02d,%03d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) ((j3 - ((long) (CrashStatKey.STATS_REPORT_FINISHED * i3))) / 1000))));
        }
        System.arraycopy(bArrC, 0, bArr, 19, 12);
    }

    public final void a(v40 v40Var, byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        int iMin = Math.min(i2, this.j.b());
        v40Var.b(bArr, i + iMin, i2 - iMin);
        if (iMin > 0) {
            this.j.a(bArr, i, iMin);
        }
        this.N += i2;
    }

    public final int a(v40 v40Var, f50 f50Var, int i) throws InterruptedException, IOException {
        int iA;
        int iB = this.j.b();
        if (iB > 0) {
            iA = Math.min(i, iB);
            f50Var.a(this.j, iA);
        } else {
            iA = f50Var.a(v40Var, i, false);
        }
        this.N += iA;
        this.V += iA;
        return iA;
    }

    public final boolean a(d50 d50Var, long j) {
        if (this.x) {
            this.z = j;
            d50Var.f7296a = this.y;
            this.x = false;
            return true;
        }
        if (this.u) {
            long j2 = this.z;
            if (j2 != -1) {
                d50Var.f7296a = j2;
                this.z = -1L;
                return true;
            }
        }
        return false;
    }

    public final long a(long j) throws com.google.android.exoplayer2.n {
        long j2 = this.q;
        if (j2 != -9223372036854775807L) {
            return x80.a(j, j2, 1000L);
        }
        throw new com.google.android.exoplayer2.n("Can't scale timecode prior to timecodeScale being set.");
    }

    public static boolean a(String str) {
        return "V_VP8".equals(str) || "V_VP9".equals(str) || "V_MPEG2".equals(str) || "V_MPEG4/ISO/SP".equals(str) || "V_MPEG4/ISO/ASP".equals(str) || "V_MPEG4/ISO/AP".equals(str) || "V_MPEG4/ISO/AVC".equals(str) || "V_MPEGH/ISO/HEVC".equals(str) || "V_MS/VFW/FOURCC".equals(str) || "V_THEORA".equals(str) || "A_OPUS".equals(str) || "A_VORBIS".equals(str) || "A_AAC".equals(str) || "A_MPEG/L2".equals(str) || "A_MPEG/L3".equals(str) || "A_AC3".equals(str) || "A_EAC3".equals(str) || "A_TRUEHD".equals(str) || "A_DTS".equals(str) || "A_DTS/EXPRESS".equals(str) || "A_DTS/LOSSLESS".equals(str) || "A_FLAC".equals(str) || "A_MS/ACM".equals(str) || "A_PCM/INT/LIT".equals(str) || "S_TEXT/UTF8".equals(str) || "S_VOBSUB".equals(str) || "S_HDMV/PGS".equals(str) || "S_DVBSUB".equals(str);
    }

    public static int[] a(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        return iArr.length >= i ? iArr : new int[Math.max(iArr.length * 2, i)];
    }
}
