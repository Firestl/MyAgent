package com.igexin.push.core.i.a;

import android.graphics.Bitmap;
import android.util.Log;
import com.igexin.push.core.i.a.d;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class n implements d {
    public static final String f = "n";
    public static final int g = 4096;
    public static final int h = -1;
    public static final int i = -1;
    public static final int j = 4;
    public static final int k = 255;
    public static final int l = 0;
    public boolean A;
    public int B;
    public int C;
    public int D;
    public int E;
    public Boolean F;
    public Bitmap.Config G;
    public int[] m;
    public final int[] n;
    public final d.a o;
    public ByteBuffer p;
    public byte[] q;
    public j r;
    public short[] s;
    public byte[] t;
    public byte[] u;
    public byte[] v;
    public int[] w;
    public int x;
    public i y;
    public Bitmap z;

    public n(d.a aVar) {
        this.n = new int[256];
        this.G = Bitmap.Config.ARGB_8888;
        this.o = aVar;
        this.y = new i();
    }

    public n(d.a aVar, i iVar, ByteBuffer byteBuffer) {
        this(aVar, iVar, byteBuffer, 1);
    }

    public n(d.a aVar, i iVar, ByteBuffer byteBuffer, int i2) {
        this(aVar);
        a(iVar, byteBuffer, i2);
    }

    private int a(int i2, int i3, int i4) {
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        for (int i10 = i2; i10 < this.C + i2; i10++) {
            byte[] bArr = this.v;
            if (i10 >= bArr.length || i10 >= i3) {
                break;
            }
            int i11 = this.m[bArr[i10] & 255];
            if (i11 != 0) {
                i5 += (i11 >> 24) & 255;
                i6 += (i11 >> 16) & 255;
                i7 += (i11 >> 8) & 255;
                i8 += i11 & 255;
                i9++;
            }
        }
        int i12 = i2 + i4;
        for (int i13 = i12; i13 < this.C + i12; i13++) {
            byte[] bArr2 = this.v;
            if (i13 >= bArr2.length || i13 >= i3) {
                break;
            }
            int i14 = this.m[bArr2[i13] & 255];
            if (i14 != 0) {
                i5 += (i14 >> 24) & 255;
                i6 += (i14 >> 16) & 255;
                i7 += (i14 >> 8) & 255;
                i8 += i14 & 255;
                i9++;
            }
        }
        if (i9 == 0) {
            return 0;
        }
        return ((i5 / i9) << 24) | ((i6 / i9) << 16) | ((i7 / i9) << 8) | (i8 / i9);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0043  */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v32, types: [short] */
    /* JADX WARN: Type inference failed for: r5v34 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.graphics.Bitmap a(com.igexin.push.core.i.a.g r34, com.igexin.push.core.i.a.g r35) {
        /*
            Method dump skipped, instruction units count: 1071
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.i.a.n.a(com.igexin.push.core.i.a.g, com.igexin.push.core.i.a.g):android.graphics.Bitmap");
    }

    private void a(g gVar) {
        g gVar2 = gVar;
        int[] iArr = this.w;
        int i2 = gVar2.h;
        int i3 = gVar2.f;
        int i4 = gVar2.g;
        int i5 = gVar2.f3474e;
        boolean z = this.x == 0;
        int i6 = this.E;
        byte[] bArr = this.v;
        int[] iArr2 = this.m;
        int i7 = 0;
        byte b = -1;
        while (i7 < i2) {
            int i8 = (i7 + i3) * i6;
            int i9 = i8 + i5;
            int i10 = i9 + i4;
            int i11 = i8 + i6;
            if (i11 < i10) {
                i10 = i11;
            }
            int i12 = gVar2.g * i7;
            int i13 = i9;
            while (i13 < i10) {
                byte b2 = bArr[i12];
                int i14 = i2;
                int i15 = b2 & 255;
                if (i15 != b) {
                    int i16 = iArr2[i15];
                    if (i16 != 0) {
                        iArr[i13] = i16;
                    } else {
                        b = b2;
                    }
                }
                i12++;
                i13++;
                i2 = i14;
            }
            i7++;
            gVar2 = gVar;
        }
        Boolean bool = this.F;
        this.F = Boolean.valueOf((bool != null && bool.booleanValue()) || (this.F == null && z && b != -1));
    }

    private void b(g gVar) {
        Boolean bool;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        g gVar2 = gVar;
        int[] iArr = this.w;
        int i8 = gVar2.h;
        int i9 = this.C;
        int i10 = i8 / i9;
        int i11 = gVar2.f / i9;
        int i12 = gVar2.g / i9;
        int i13 = gVar2.f3474e / i9;
        boolean z = this.x == 0;
        int i14 = this.C;
        int i15 = this.E;
        int i16 = this.D;
        byte[] bArr = this.v;
        int[] iArr2 = this.m;
        Boolean bool2 = this.F;
        int i17 = 0;
        int i18 = 0;
        int i19 = 1;
        int i20 = 8;
        while (true) {
            bool = bool2;
            if (i17 >= i10) {
                break;
            }
            if (gVar2.i) {
                if (i18 >= i10) {
                    i2 = i10;
                    int i21 = i19 + 1;
                    if (i21 == 2) {
                        i19 = i21;
                        i18 = 4;
                    } else if (i21 != 3) {
                        i19 = i21;
                        if (i21 == 4) {
                            i18 = 1;
                            i20 = 2;
                        }
                    } else {
                        i19 = i21;
                        i18 = 2;
                        i20 = 4;
                    }
                } else {
                    i2 = i10;
                }
                i3 = i18 + i20;
            } else {
                i2 = i10;
                i3 = i18;
                i18 = i17;
            }
            int i22 = i18 + i11;
            boolean z2 = i14 == 1;
            if (i22 < i16) {
                int i23 = i22 * i15;
                int i24 = i23 + i13;
                int i25 = i24 + i12;
                int i26 = i23 + i15;
                if (i26 < i25) {
                    i25 = i26;
                }
                i4 = i3;
                int i27 = i17 * i14 * gVar2.g;
                if (z2) {
                    int i28 = i24;
                    while (true) {
                        i5 = i11;
                        if (i28 >= i25) {
                            break;
                        }
                        int i29 = iArr2[bArr[i27] & 255];
                        if (i29 != 0) {
                            iArr[i28] = i29;
                        } else if (z && bool == null) {
                            bool = Boolean.TRUE;
                        }
                        i27 += i14;
                        i28++;
                        i11 = i5;
                    }
                } else {
                    i5 = i11;
                    int i30 = ((i25 - i24) * i14) + i27;
                    int i31 = i24;
                    while (true) {
                        i6 = i12;
                        if (i31 < i25) {
                            int i32 = gVar2.g;
                            int i33 = i13;
                            int i34 = i27;
                            int i35 = 0;
                            int i36 = 0;
                            int i37 = 0;
                            int i38 = 0;
                            int i39 = 0;
                            while (true) {
                                if (i34 >= this.C + i27) {
                                    i7 = i15;
                                    break;
                                }
                                byte[] bArr2 = this.v;
                                i7 = i15;
                                if (i34 >= bArr2.length || i34 >= i30) {
                                    break;
                                }
                                int i40 = this.m[bArr2[i34] & 255];
                                if (i40 != 0) {
                                    i35 += (i40 >> 24) & 255;
                                    i36 += (i40 >> 16) & 255;
                                    i37 += (i40 >> 8) & 255;
                                    i38 += i40 & 255;
                                    i39++;
                                }
                                i34++;
                                i15 = i7;
                            }
                            int i41 = i32 + i27;
                            for (int i42 = i41; i42 < this.C + i41; i42++) {
                                byte[] bArr3 = this.v;
                                if (i42 >= bArr3.length || i42 >= i30) {
                                    break;
                                }
                                int i43 = this.m[bArr3[i42] & 255];
                                if (i43 != 0) {
                                    i35 += (i43 >> 24) & 255;
                                    i36 += (i43 >> 16) & 255;
                                    i37 += (i43 >> 8) & 255;
                                    i38 += i43 & 255;
                                    i39++;
                                }
                            }
                            int i44 = i39 == 0 ? 0 : ((i35 / i39) << 24) | ((i36 / i39) << 16) | ((i37 / i39) << 8) | (i38 / i39);
                            if (i44 != 0) {
                                iArr[i31] = i44;
                            } else if (z && bool == null) {
                                bool = Boolean.TRUE;
                            }
                            i27 += i14;
                            i31++;
                            gVar2 = gVar;
                            i12 = i6;
                            i13 = i33;
                            i15 = i7;
                        }
                    }
                    bool2 = bool;
                    i17++;
                    gVar2 = gVar;
                    i11 = i5;
                    i12 = i6;
                    i10 = i2;
                    i18 = i4;
                    i13 = i13;
                    i15 = i15;
                }
            } else {
                i4 = i3;
                i5 = i11;
            }
            i6 = i12;
            bool2 = bool;
            i17++;
            gVar2 = gVar;
            i11 = i5;
            i12 = i6;
            i10 = i2;
            i18 = i4;
            i13 = i13;
            i15 = i15;
        }
        if (this.F == null) {
            this.F = Boolean.valueOf(bool == null ? false : bool.booleanValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [short] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    private void c(g gVar) {
        int i2;
        int i3;
        int i4;
        int i5;
        short s;
        n nVar = this;
        if (gVar != null) {
            nVar.p.position(gVar.n);
        }
        if (gVar == null) {
            i iVar = nVar.y;
            i2 = iVar.h;
            i3 = iVar.i;
        } else {
            i2 = gVar.g;
            i3 = gVar.h;
        }
        int i6 = i2 * i3;
        byte[] bArr = nVar.v;
        if (bArr == null || bArr.length < i6) {
            nVar.v = nVar.o.a(i6);
        }
        byte[] bArr2 = nVar.v;
        if (nVar.s == null) {
            nVar.s = new short[4096];
        }
        short[] sArr = nVar.s;
        if (nVar.t == null) {
            nVar.t = new byte[4096];
        }
        byte[] bArr3 = nVar.t;
        if (nVar.u == null) {
            nVar.u = new byte[4097];
        }
        byte[] bArr4 = nVar.u;
        int iQ = q();
        int i7 = 1 << iQ;
        int i8 = i7 + 1;
        int i9 = i7 + 2;
        int i10 = iQ + 1;
        int i11 = (1 << i10) - 1;
        for (int i12 = 0; i12 < i7; i12++) {
            sArr[i12] = 0;
            bArr3[i12] = (byte) i12;
        }
        byte[] bArr5 = nVar.q;
        int i13 = i10;
        int i14 = i9;
        int i15 = i11;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = -1;
        int i23 = 0;
        int i24 = 0;
        while (true) {
            if (i16 >= i6) {
                break;
            }
            if (i17 == 0) {
                int iQ2 = q();
                if (iQ2 <= 0) {
                    i4 = i16;
                    i5 = i10;
                } else {
                    ByteBuffer byteBuffer = nVar.p;
                    i4 = i16;
                    i5 = i10;
                    byteBuffer.get(nVar.q, 0, Math.min(iQ2, byteBuffer.remaining()));
                }
                if (iQ2 <= 0) {
                    nVar.B = 3;
                    break;
                } else {
                    i17 = iQ2;
                    i18 = 0;
                }
            } else {
                i4 = i16;
                i5 = i10;
            }
            i20 += (bArr5[i18] & 255) << i19;
            i18++;
            i17--;
            int i25 = i19 + 8;
            int i26 = i14;
            int i27 = i13;
            int i28 = i22;
            int i29 = i23;
            while (i25 >= i27) {
                int i30 = i20 & i15;
                i20 >>= i27;
                i25 -= i27;
                if (i30 == i7) {
                    i26 = i9;
                    i15 = i11;
                    i27 = i5;
                    i28 = -1;
                } else if (i30 != i8) {
                    if (i28 == -1) {
                        bArr2[i21] = bArr3[i30];
                        i21++;
                        i4++;
                        i28 = i30;
                        i29 = i28;
                    } else {
                        if (i30 >= i26) {
                            bArr4[i24] = (byte) i29;
                            i24++;
                            s = i28;
                        } else {
                            s = i30;
                        }
                        while (s >= i7) {
                            bArr4[i24] = bArr3[s];
                            i24++;
                            s = sArr[s];
                        }
                        int i31 = bArr3[s] & 255;
                        byte b = (byte) i31;
                        bArr2[i21] = b;
                        while (true) {
                            i21++;
                            i4++;
                            if (i24 <= 0) {
                                break;
                            }
                            i24--;
                            bArr2[i21] = bArr4[i24];
                        }
                        if (i26 < 4096) {
                            sArr[i26] = (short) i28;
                            bArr3[i26] = b;
                            i26++;
                            if ((i26 & i15) == 0 && i26 < 4096) {
                                i27++;
                                i15 += i26;
                            }
                        }
                        i28 = i30;
                        i29 = i31;
                    }
                }
            }
            i22 = i28;
            i19 = i25;
            i14 = i26;
            i13 = i27;
            i16 = i4;
            i10 = i5;
            i23 = i29;
            nVar = this;
        }
        Arrays.fill(bArr2, i21, i6, (byte) 0);
    }

    private j p() {
        if (this.r == null) {
            this.r = new j();
        }
        return this.r;
    }

    private int q() {
        return this.p.get() & 255;
    }

    private int r() {
        int iQ = q();
        if (iQ <= 0) {
            return iQ;
        }
        ByteBuffer byteBuffer = this.p;
        byteBuffer.get(this.q, 0, Math.min(iQ, byteBuffer.remaining()));
        return iQ;
    }

    private Bitmap s() {
        Boolean bool = this.F;
        Bitmap bitmapA = this.o.a(this.E, this.D, (bool == null || bool.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.G, this.x);
        bitmapA.setHasAlpha(true);
        return bitmapA;
    }

    @Override // com.igexin.push.core.i.a.d
    public final int a() {
        return this.y.h;
    }

    @Override // com.igexin.push.core.i.a.d
    public final int a(int i2) {
        if (i2 >= 0) {
            i iVar = this.y;
            if (i2 < iVar.f3480e) {
                return iVar.g.get(i2).m;
            }
        }
        return -1;
    }

    @Override // com.igexin.push.core.i.a.d
    public final int a(InputStream inputStream, int i2) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i2 > 0 ? i2 + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int i3 = inputStream.read(bArr, 0, 16384);
                    if (i3 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i3);
                }
                byteArrayOutputStream.flush();
                a(byteArrayOutputStream.toByteArray());
            } catch (IOException e2) {
                Log.w(f, "Error reading data from stream", e2);
            }
        } else {
            this.B = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e3) {
                Log.w(f, "Error closing stream", e3);
            }
        }
        return this.B;
    }

    @Override // com.igexin.push.core.i.a.d
    public final synchronized int a(byte[] bArr) {
        if (this.r == null) {
            this.r = new j();
        }
        j jVar = this.r;
        if (bArr != null) {
            jVar.a(ByteBuffer.wrap(bArr));
        } else {
            jVar.c = null;
            jVar.d.d = 2;
        }
        i iVarB = jVar.b();
        this.y = iVarB;
        if (bArr != null) {
            a(iVarB, bArr);
        }
        return this.B;
    }

    @Override // com.igexin.push.core.i.a.d
    public final void a(Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888 || config == Bitmap.Config.RGB_565) {
            this.G = config;
            return;
        }
        throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + Bitmap.Config.ARGB_8888 + " or " + Bitmap.Config.RGB_565);
    }

    @Override // com.igexin.push.core.i.a.d
    public final synchronized void a(i iVar, ByteBuffer byteBuffer) {
        a(iVar, byteBuffer, 1);
    }

    @Override // com.igexin.push.core.i.a.d
    public final synchronized void a(i iVar, ByteBuffer byteBuffer, int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("Sample size must be >=0, not: ".concat(String.valueOf(i2)));
        }
        int iHighestOneBit = Integer.highestOneBit(i2);
        this.B = 0;
        this.y = iVar;
        this.x = -1;
        ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.p = byteBufferAsReadOnlyBuffer;
        byteBufferAsReadOnlyBuffer.position(0);
        this.p.order(ByteOrder.LITTLE_ENDIAN);
        this.A = false;
        Iterator<g> it = iVar.g.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().k == 3) {
                this.A = true;
                break;
            }
        }
        this.C = iHighestOneBit;
        this.E = iVar.h / iHighestOneBit;
        this.D = iVar.i / iHighestOneBit;
        this.v = this.o.a(iVar.h * iVar.i);
        this.w = this.o.b(this.E * this.D);
    }

    @Override // com.igexin.push.core.i.a.d
    public final synchronized void a(i iVar, byte[] bArr) {
        a(iVar, ByteBuffer.wrap(bArr));
    }

    @Override // com.igexin.push.core.i.a.d
    public final int b() {
        return this.y.i;
    }

    @Override // com.igexin.push.core.i.a.d
    public final ByteBuffer c() {
        return this.p;
    }

    @Override // com.igexin.push.core.i.a.d
    public final int d() {
        return this.B;
    }

    @Override // com.igexin.push.core.i.a.d
    public final void e() {
        this.x = (this.x + 1) % this.y.f3480e;
    }

    @Override // com.igexin.push.core.i.a.d
    public final int f() {
        int i2;
        i iVar = this.y;
        int i3 = iVar.f3480e;
        if (i3 <= 0 || (i2 = this.x) < 0) {
            return 0;
        }
        if (i2 < 0 || i2 >= i3) {
            return -1;
        }
        return iVar.g.get(i2).m;
    }

    @Override // com.igexin.push.core.i.a.d
    public final int g() {
        return this.y.f3480e;
    }

    @Override // com.igexin.push.core.i.a.d
    public final int h() {
        return this.x;
    }

    @Override // com.igexin.push.core.i.a.d
    public final void i() {
        this.x = -1;
    }

    @Override // com.igexin.push.core.i.a.d
    @Deprecated
    public final int j() {
        int i2 = this.y.o;
        if (i2 == -1) {
            return 1;
        }
        return i2;
    }

    @Override // com.igexin.push.core.i.a.d
    public final int k() {
        return this.y.o;
    }

    @Override // com.igexin.push.core.i.a.d
    public final int l() {
        int i2 = this.y.o;
        if (i2 == -1) {
            return 1;
        }
        if (i2 == 0) {
            return 0;
        }
        return i2 + 1;
    }

    @Override // com.igexin.push.core.i.a.d
    public final int m() {
        return this.p.limit() + this.v.length + (this.w.length * 4);
    }

    @Override // com.igexin.push.core.i.a.d
    public final synchronized Bitmap n() {
        if (this.y.f3480e <= 0 || this.x < 0) {
            com.igexin.c.a.c.a.b(f, "Unable to decode frame, frameCount=" + this.y.f3480e + ", framePointer=" + this.x);
            this.B = 1;
        }
        if (this.B != 1 && this.B != 2) {
            this.B = 0;
            if (this.q == null) {
                this.q = this.o.a(255);
            }
            g gVar = this.y.g.get(this.x);
            int i2 = this.x - 1;
            g gVar2 = i2 >= 0 ? this.y.g.get(i2) : null;
            int[] iArr = gVar.o != null ? gVar.o : this.y.c;
            this.m = iArr;
            if (iArr == null) {
                com.igexin.c.a.c.a.b(f, "No valid color table found for frame #" + this.x);
                this.B = 1;
                return null;
            }
            if (gVar.j) {
                System.arraycopy(iArr, 0, this.n, 0, iArr.length);
                int[] iArr2 = this.n;
                this.m = iArr2;
                iArr2[gVar.l] = 0;
                if (gVar.k == 2 && this.x == 0) {
                    this.F = Boolean.TRUE;
                }
            }
            return a(gVar, gVar2);
        }
        com.igexin.c.a.c.a.b(f, "Unable to decode frame, status=" + this.B);
        return null;
    }

    @Override // com.igexin.push.core.i.a.d
    public final void o() {
        this.y = null;
        Bitmap bitmap = this.z;
        if (bitmap != null) {
            this.o.a(bitmap);
        }
        this.z = null;
        this.p = null;
        this.F = null;
        this.o.a();
    }
}
