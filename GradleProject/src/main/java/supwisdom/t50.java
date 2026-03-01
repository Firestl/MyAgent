package supwisdom;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import com.google.zxing.oned.Code39Reader;
import com.umeng.commonsdk.debug.UMLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.bouncycastle.pqc.crypto.qtesla.HashUtils;

/* JADX INFO: compiled from: Cea708Decoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class t50 extends u50 {
    public final o80 f = new o80();
    public final n80 g = new n80();
    public final int h;
    public final a[] i;
    public a j;
    public List<y50> k;
    public List<y50> l;
    public b m;
    public int n;

    /* JADX INFO: compiled from: Cea708Decoder.java */
    public static final class a {
        public static final int[] A;
        public static final int[] B;
        public static final boolean[] C;
        public static final int[] D;
        public static final int[] E;
        public static final int[] F;
        public static final int[] G;
        public static final int w = a(2, 2, 2, 0);
        public static final int x = a(0, 0, 0, 0);
        public static final int y;
        public static final int[] z;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<SpannableString> f9254a = new LinkedList();
        public final SpannableStringBuilder b = new SpannableStringBuilder();
        public boolean c;
        public boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f9255e;
        public boolean f;
        public int g;
        public int h;
        public int i;
        public int j;
        public boolean k;
        public int l;
        public int m;
        public int n;
        public int o;
        public int p;
        public int q;
        public int r;
        public int s;
        public int t;
        public int u;
        public int v;

        static {
            int iA = a(0, 0, 0, 3);
            y = iA;
            z = new int[]{0, 0, 0, 0, 0, 2, 0};
            A = new int[]{0, 0, 0, 0, 0, 0, 2};
            B = new int[]{3, 3, 3, 3, 3, 3, 1};
            C = new boolean[]{false, false, false, true, true, true, false};
            int i = x;
            D = new int[]{i, iA, i, i, iA, i, i};
            E = new int[]{0, 1, 2, 3, 4, 3, 4};
            F = new int[]{0, 0, 0, 0, 0, 3, 3};
            G = new int[]{i, i, i, i, i, iA, iA};
        }

        public a() {
            b();
        }

        public boolean a() {
            return !d() || (this.f9254a.isEmpty() && this.b.length() == 0);
        }

        public void b() {
            c();
            this.c = false;
            this.d = false;
            this.f9255e = 4;
            this.f = false;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            this.j = 15;
            this.k = true;
            this.l = 0;
            this.m = 0;
            this.n = 0;
            int i = x;
            this.o = i;
            this.s = w;
            this.u = i;
        }

        public void c() {
            this.f9254a.clear();
            this.b.clear();
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.t = -1;
            this.v = 0;
        }

        public boolean d() {
            return this.c;
        }

        public boolean e() {
            return this.d;
        }

        public void f() {
            int length = this.b.length();
            if (length > 0) {
                this.b.delete(length - 1, length);
            }
        }

        public SpannableString g() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.b);
            int length = spannableStringBuilder.length();
            if (length > 0) {
                if (this.p != -1) {
                    spannableStringBuilder.setSpan(new StyleSpan(2), this.p, length, 33);
                }
                if (this.q != -1) {
                    spannableStringBuilder.setSpan(new UnderlineSpan(), this.q, length, 33);
                }
                if (this.r != -1) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.s), this.r, length, 33);
                }
                if (this.t != -1) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(this.u), this.t, length, 33);
                }
            }
            return new SpannableString(spannableStringBuilder);
        }

        public s50 h() {
            Layout.Alignment alignment;
            float f;
            float f2;
            if (a()) {
                return null;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i = 0; i < this.f9254a.size(); i++) {
                spannableStringBuilder.append((CharSequence) this.f9254a.get(i));
                spannableStringBuilder.append('\n');
            }
            spannableStringBuilder.append((CharSequence) g());
            int i2 = this.l;
            if (i2 == 0) {
                alignment = Layout.Alignment.ALIGN_NORMAL;
            } else if (i2 == 1) {
                alignment = Layout.Alignment.ALIGN_OPPOSITE;
            } else if (i2 != 2) {
                if (i2 != 3) {
                    throw new IllegalArgumentException("Unexpected justification value: " + this.l);
                }
                alignment = Layout.Alignment.ALIGN_NORMAL;
            } else {
                alignment = Layout.Alignment.ALIGN_CENTER;
            }
            Layout.Alignment alignment2 = alignment;
            if (this.f) {
                f = this.h / 99.0f;
                f2 = this.g / 99.0f;
            } else {
                f = this.h / 209.0f;
                f2 = this.g / 74.0f;
            }
            float f3 = (f * 0.9f) + 0.05f;
            float f4 = (f2 * 0.9f) + 0.05f;
            int i3 = this.i;
            int i4 = i3 % 3 == 0 ? 0 : i3 % 3 == 1 ? 1 : 2;
            int i5 = this.i;
            return new s50(spannableStringBuilder, alignment2, f4, 0, i4, f3, i5 / 3 == 0 ? 0 : i5 / 3 == 1 ? 1 : 2, Float.MIN_VALUE, this.o != x, this.o, this.f9255e);
        }

        public void a(boolean z2) {
            this.d = z2;
        }

        public void a(boolean z2, boolean z3, boolean z4, int i, boolean z5, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.c = true;
            this.d = z2;
            this.k = z3;
            this.f9255e = i;
            this.f = z5;
            this.g = i2;
            this.h = i3;
            this.i = i6;
            int i9 = i4 + 1;
            if (this.j != i9) {
                this.j = i9;
                while (true) {
                    if ((!z3 || this.f9254a.size() < this.j) && this.f9254a.size() < 15) {
                        break;
                    } else {
                        this.f9254a.remove(0);
                    }
                }
            }
            if (i7 != 0 && this.m != i7) {
                this.m = i7;
                int i10 = i7 - 1;
                a(D[i10], y, C[i10], 0, A[i10], B[i10], z[i10]);
            }
            if (i8 == 0 || this.n == i8) {
                return;
            }
            this.n = i8;
            int i11 = i8 - 1;
            a(0, 1, 1, false, false, F[i11], E[i11]);
            a(w, G[i11], x);
        }

        public static int b(int i, int i2, int i3) {
            return a(i, i2, i3, 0);
        }

        public void a(int i, int i2, boolean z2, int i3, int i4, int i5, int i6) {
            this.o = i;
            this.l = i6;
        }

        public void a(int i, int i2, int i3, boolean z2, boolean z3, int i4, int i5) {
            if (this.p != -1) {
                if (!z2) {
                    this.b.setSpan(new StyleSpan(2), this.p, this.b.length(), 33);
                    this.p = -1;
                }
            } else if (z2) {
                this.p = this.b.length();
            }
            if (this.q == -1) {
                if (z3) {
                    this.q = this.b.length();
                }
            } else {
                if (z3) {
                    return;
                }
                this.b.setSpan(new UnderlineSpan(), this.q, this.b.length(), 33);
                this.q = -1;
            }
        }

        public void a(int i, int i2, int i3) {
            if (this.r != -1 && this.s != i) {
                this.b.setSpan(new ForegroundColorSpan(this.s), this.r, this.b.length(), 33);
            }
            if (i != w) {
                this.r = this.b.length();
                this.s = i;
            }
            if (this.t != -1 && this.u != i2) {
                this.b.setSpan(new BackgroundColorSpan(this.u), this.t, this.b.length(), 33);
            }
            if (i2 != x) {
                this.t = this.b.length();
                this.u = i2;
            }
        }

        public void a(int i, int i2) {
            if (this.v != i) {
                a('\n');
            }
            this.v = i;
        }

        public void a(char c) {
            if (c == '\n') {
                this.f9254a.add(g());
                this.b.clear();
                if (this.p != -1) {
                    this.p = 0;
                }
                if (this.q != -1) {
                    this.q = 0;
                }
                if (this.r != -1) {
                    this.r = 0;
                }
                if (this.t != -1) {
                    this.t = 0;
                }
                while (true) {
                    if ((!this.k || this.f9254a.size() < this.j) && this.f9254a.size() < 15) {
                        return;
                    } else {
                        this.f9254a.remove(0);
                    }
                }
            } else {
                this.b.append(c);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0021  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int a(int r4, int r5, int r6, int r7) {
            /*
                r0 = 4
                r1 = 0
                supwisdom.e80.a(r4, r1, r0)
                supwisdom.e80.a(r5, r1, r0)
                supwisdom.e80.a(r6, r1, r0)
                supwisdom.e80.a(r7, r1, r0)
                r0 = 1
                r2 = 255(0xff, float:3.57E-43)
                if (r7 == 0) goto L21
                if (r7 == r0) goto L21
                r3 = 2
                if (r7 == r3) goto L1e
                r3 = 3
                if (r7 == r3) goto L1c
                goto L21
            L1c:
                r7 = 0
                goto L23
            L1e:
                r7 = 127(0x7f, float:1.78E-43)
                goto L23
            L21:
                r7 = 255(0xff, float:3.57E-43)
            L23:
                if (r4 <= r0) goto L28
                r4 = 255(0xff, float:3.57E-43)
                goto L29
            L28:
                r4 = 0
            L29:
                if (r5 <= r0) goto L2e
                r5 = 255(0xff, float:3.57E-43)
                goto L2f
            L2e:
                r5 = 0
            L2f:
                if (r6 <= r0) goto L33
                r1 = 255(0xff, float:3.57E-43)
            L33:
                int r4 = android.graphics.Color.argb(r7, r4, r5, r1)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.t50.a.a(int, int, int, int):int");
        }
    }

    /* JADX INFO: compiled from: Cea708Decoder.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9256a;
        public final int b;
        public final byte[] c;
        public int d = 0;

        public b(int i, int i2) {
            this.f9256a = i;
            this.b = i2;
            this.c = new byte[(i2 * 2) - 1];
        }
    }

    public t50(int i) {
        this.h = i == -1 ? 1 : i;
        this.i = new a[8];
        for (int i2 = 0; i2 < 8; i2++) {
            this.i[i2] = new a();
        }
        this.j = this.i[0];
        p();
    }

    @Override // supwisdom.u50
    public void a(a70 a70Var) {
        this.f.a(a70Var.c.array(), a70Var.c.limit());
        while (this.f.b() >= 3) {
            int iG = this.f.g() & 7;
            int i = iG & 3;
            boolean z = (iG & 4) == 4;
            byte bG = (byte) this.f.g();
            byte bG2 = (byte) this.f.g();
            if (i == 2 || i == 3) {
                if (z) {
                    if (i == 3) {
                        i();
                        int i2 = (bG & 192) >> 6;
                        int i3 = bG & 63;
                        if (i3 == 0) {
                            i3 = 64;
                        }
                        b bVar = new b(i2, i3);
                        this.m = bVar;
                        byte[] bArr = bVar.c;
                        int i4 = bVar.d;
                        bVar.d = i4 + 1;
                        bArr[i4] = bG2;
                    } else {
                        e80.a(i == 2);
                        b bVar2 = this.m;
                        if (bVar2 == null) {
                            Log.e("Cea708Decoder", "Encountered DTVCC_PACKET_DATA before DTVCC_PACKET_START");
                        } else {
                            byte[] bArr2 = bVar2.c;
                            int i5 = bVar2.d;
                            int i6 = i5 + 1;
                            bVar2.d = i6;
                            bArr2[i5] = bG;
                            bVar2.d = i6 + 1;
                            bArr2[i6] = bG2;
                        }
                    }
                    b bVar3 = this.m;
                    if (bVar3.d == (bVar3.b * 2) - 1) {
                        i();
                    }
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void b(int i) {
        int i2 = 1;
        switch (i) {
            case 128:
            case 129:
            case 130:
            case com.igexin.push.core.a.c.h.b /* 131 */:
            case 132:
            case 133:
            case 134:
            case 135:
                int i3 = i - 128;
                if (this.n != i3) {
                    this.n = i3;
                    this.j = this.i[i3];
                }
                break;
            case HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE /* 136 */:
                while (i2 <= 8) {
                    if (this.g.d()) {
                        this.i[8 - i2].c();
                    }
                    i2++;
                }
                break;
            case 137:
                for (int i4 = 1; i4 <= 8; i4++) {
                    if (this.g.d()) {
                        this.i[8 - i4].a(true);
                    }
                }
                break;
            case 138:
                while (i2 <= 8) {
                    if (this.g.d()) {
                        this.i[8 - i2].a(false);
                    }
                    i2++;
                }
                break;
            case 139:
                for (int i5 = 1; i5 <= 8; i5++) {
                    if (this.g.d()) {
                        this.i[8 - i5].a(!r0.e());
                    }
                }
                break;
            case 140:
                while (i2 <= 8) {
                    if (this.g.d()) {
                        this.i[8 - i2].b();
                    }
                    i2++;
                }
                break;
            case 141:
                this.g.b(8);
                break;
            case 142:
                break;
            case 143:
                p();
                break;
            case 144:
                if (this.j.d()) {
                    k();
                } else {
                    this.g.b(16);
                }
                break;
            case com.igexin.push.config.c.G /* 145 */:
                if (this.j.d()) {
                    l();
                } else {
                    this.g.b(24);
                }
                break;
            case 146:
                if (this.j.d()) {
                    m();
                } else {
                    this.g.b(16);
                }
                break;
            case 147:
            case Code39Reader.ASTERISK_ENCODING /* 148 */:
            case 149:
            case 150:
            default:
                Log.w("Cea708Decoder", "Invalid C1 command: " + i);
                break;
            case 151:
                if (this.j.d()) {
                    n();
                } else {
                    this.g.b(32);
                }
                break;
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
                int i6 = i - 152;
                i(i6);
                if (this.n != i6) {
                    this.n = i6;
                    this.j = this.i[i6];
                }
                break;
        }
    }

    @Override // supwisdom.u50, supwisdom.w10
    public void c() {
        super.c();
        this.k = null;
        this.l = null;
        this.n = 0;
        this.j = this.i[0];
        p();
        this.m = null;
    }

    public final void d(int i) {
        if (i <= 135) {
            this.g.b(32);
            return;
        }
        if (i <= 143) {
            this.g.b(40);
        } else if (i <= 159) {
            this.g.b(2);
            this.g.b(this.g.c(6) * 8);
        }
    }

    @Override // supwisdom.u50
    public boolean e() {
        return this.k != this.l;
    }

    @Override // supwisdom.u50
    public m60 f() {
        List<y50> list = this.k;
        this.l = list;
        return new w50(list);
    }

    public final void g(int i) {
        if (i == 32) {
            this.j.a(' ');
        }
        if (i == 33) {
            this.j.a((char) 160);
            return;
        }
        if (i == 37) {
            this.j.a((char) 8230);
            return;
        }
        if (i == 42) {
            this.j.a((char) 352);
            return;
        }
        if (i == 44) {
            this.j.a((char) 338);
            return;
        }
        if (i == 63) {
            this.j.a((char) 376);
            return;
        }
        if (i == 57) {
            this.j.a((char) 8482);
            return;
        }
        if (i == 58) {
            this.j.a((char) 353);
            return;
        }
        if (i == 60) {
            this.j.a((char) 339);
            return;
        }
        if (i == 61) {
            this.j.a((char) 8480);
            return;
        }
        switch (i) {
            case 48:
                this.j.a((char) 9608);
                break;
            case 49:
                this.j.a((char) 8216);
                break;
            case 50:
                this.j.a((char) 8217);
                break;
            case 51:
                this.j.a((char) 8220);
                break;
            case 52:
                this.j.a((char) 8221);
                break;
            case 53:
                this.j.a((char) 8226);
                break;
            default:
                switch (i) {
                    case 118:
                        this.j.a((char) 8539);
                        break;
                    case 119:
                        this.j.a((char) 8540);
                        break;
                    case 120:
                        this.j.a((char) 8541);
                        break;
                    case 121:
                        this.j.a((char) 8542);
                        break;
                    case 122:
                        this.j.a(UMLog.HORIZONTAL_LINE);
                        break;
                    case 123:
                        this.j.a((char) 9488);
                        break;
                    case 124:
                        this.j.a(UMLog.BOTTOM_LEFT_CORNER);
                        break;
                    case 125:
                        this.j.a((char) 9472);
                        break;
                    case 126:
                        this.j.a((char) 9496);
                        break;
                    case 127:
                        this.j.a(UMLog.TOP_LEFT_CORNER);
                        break;
                    default:
                        Log.w("Cea708Decoder", "Invalid G2 character: " + i);
                        break;
                }
                break;
        }
    }

    public final void h(int i) {
        if (i == 160) {
            this.j.a((char) 13252);
            return;
        }
        Log.w("Cea708Decoder", "Invalid G3 character: " + i);
        this.j.a('_');
    }

    public final void i() {
        if (this.m == null) {
            return;
        }
        j();
        this.m = null;
    }

    public final void j() {
        b bVar = this.m;
        int i = bVar.d;
        if (i != (bVar.b * 2) - 1) {
            Log.w("Cea708Decoder", "DtvCcPacket ended prematurely; size is " + ((this.m.b * 2) - 1) + ", but current index is " + this.m.d + " (sequence number " + this.m.f9256a + "); ignoring packet");
            return;
        }
        this.g.a(bVar.c, i);
        int iC = this.g.c(3);
        int iC2 = this.g.c(5);
        if (iC == 7) {
            this.g.b(2);
            iC += this.g.c(6);
        }
        if (iC2 == 0) {
            if (iC != 0) {
                Log.w("Cea708Decoder", "serviceNumber is non-zero (" + iC + ") when blockSize is 0");
                return;
            }
            return;
        }
        if (iC != this.h) {
            return;
        }
        boolean z = false;
        while (this.g.a() > 0) {
            int iC3 = this.g.c(8);
            if (iC3 == 16) {
                int iC4 = this.g.c(8);
                if (iC4 <= 31) {
                    c(iC4);
                } else {
                    if (iC4 <= 127) {
                        g(iC4);
                    } else if (iC4 <= 159) {
                        d(iC4);
                    } else if (iC4 <= 255) {
                        h(iC4);
                    } else {
                        Log.w("Cea708Decoder", "Invalid extended command: " + iC4);
                    }
                    z = true;
                }
            } else if (iC3 <= 31) {
                a(iC3);
            } else {
                if (iC3 <= 127) {
                    e(iC3);
                } else if (iC3 <= 159) {
                    b(iC3);
                } else if (iC3 <= 255) {
                    f(iC3);
                } else {
                    Log.w("Cea708Decoder", "Invalid base command: " + iC3);
                }
                z = true;
            }
        }
        if (z) {
            this.k = o();
        }
    }

    public final void k() {
        this.j.a(this.g.c(4), this.g.c(2), this.g.c(2), this.g.d(), this.g.d(), this.g.c(3), this.g.c(3));
    }

    public final void l() {
        int iA = a.a(this.g.c(2), this.g.c(2), this.g.c(2), this.g.c(2));
        int iA2 = a.a(this.g.c(2), this.g.c(2), this.g.c(2), this.g.c(2));
        this.g.b(2);
        this.j.a(iA, iA2, a.b(this.g.c(2), this.g.c(2), this.g.c(2)));
    }

    public final void m() {
        this.g.b(4);
        int iC = this.g.c(4);
        this.g.b(2);
        this.j.a(iC, this.g.c(6));
    }

    public final void n() {
        int iA = a.a(this.g.c(2), this.g.c(2), this.g.c(2), this.g.c(2));
        int iC = this.g.c(2);
        int iB = a.b(this.g.c(2), this.g.c(2), this.g.c(2));
        if (this.g.d()) {
            iC |= 4;
        }
        boolean zD = this.g.d();
        int iC2 = this.g.c(2);
        int iC3 = this.g.c(2);
        int iC4 = this.g.c(2);
        this.g.b(8);
        this.j.a(iA, iB, zD, iC, iC2, iC3, iC4);
    }

    public final List<y50> o() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 8; i++) {
            if (!this.i[i].a() && this.i[i].e()) {
                arrayList.add(this.i[i].h());
            }
        }
        Collections.sort(arrayList);
        return Collections.unmodifiableList(arrayList);
    }

    public final void p() {
        for (int i = 0; i < 8; i++) {
            this.i[i].b();
        }
    }

    public final void e(int i) {
        if (i == 127) {
            this.j.a((char) 9835);
        } else {
            this.j.a((char) (i & 255));
        }
    }

    public final void f(int i) {
        this.j.a((char) (i & 255));
    }

    public final void i(int i) {
        a aVar = this.i[i];
        this.g.b(2);
        boolean zD = this.g.d();
        boolean zD2 = this.g.d();
        boolean zD3 = this.g.d();
        int iC = this.g.c(3);
        boolean zD4 = this.g.d();
        int iC2 = this.g.c(7);
        int iC3 = this.g.c(8);
        int iC4 = this.g.c(4);
        int iC5 = this.g.c(4);
        this.g.b(2);
        int iC6 = this.g.c(6);
        this.g.b(2);
        aVar.a(zD, zD2, zD3, iC, zD4, iC2, iC3, iC5, iC6, iC4, this.g.c(3), this.g.c(3));
    }

    public final void c(int i) {
        if (i <= 7) {
            return;
        }
        if (i <= 15) {
            this.g.b(8);
        } else if (i <= 23) {
            this.g.b(16);
        } else if (i <= 31) {
            this.g.b(24);
        }
    }

    public final void a(int i) {
        if (i != 0) {
            if (i == 3) {
                this.k = o();
                return;
            }
            if (i != 8) {
                switch (i) {
                    case 12:
                        p();
                        break;
                    case 13:
                        this.j.a('\n');
                        break;
                    case 14:
                        break;
                    default:
                        if (i >= 17 && i <= 23) {
                            Log.w("Cea708Decoder", "Currently unsupported COMMAND_EXT1 Command: " + i);
                            this.g.b(8);
                        } else if (i >= 24 && i <= 31) {
                            Log.w("Cea708Decoder", "Currently unsupported COMMAND_P16 Command: " + i);
                            this.g.b(16);
                        } else {
                            Log.w("Cea708Decoder", "Invalid C0 command: " + i);
                        }
                        break;
                }
                return;
            }
            this.j.f();
        }
    }
}
