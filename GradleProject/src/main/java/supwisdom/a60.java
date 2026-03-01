package supwisdom;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Region;
import android.util.Log;
import android.util.SparseArray;
import com.taobao.weex.wson.Wson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.pqc.crypto.qtesla.HashUtils;

/* JADX INFO: compiled from: DvbParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class a60 {
    public static final byte[] h = {0, 7, 8, 15};
    public static final byte[] i = {0, 119, -120, -1};
    public static final byte[] j = {0, 17, 34, 51, 68, 85, Wson.BOOLEAN_TYPE_FALSE, 119, -120, -103, -86, -69, -52, -35, -18, -1};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f6858a;
    public final Paint b;
    public final Canvas c;
    public final b d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f6859e;
    public final h f;
    public Bitmap g;

    /* JADX INFO: compiled from: DvbParser.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f6860a;
        public final int[] b;
        public final int[] c;
        public final int[] d;

        public a(int i, int[] iArr, int[] iArr2, int[] iArr3) {
            this.f6860a = i;
            this.b = iArr;
            this.c = iArr2;
            this.d = iArr3;
        }
    }

    /* JADX INFO: compiled from: DvbParser.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f6861a;
        public final int b;
        public final int c;
        public final int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f6862e;
        public final int f;

        public b(int i, int i2, int i3, int i4, int i5, int i6) {
            this.f6861a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.f6862e = i5;
            this.f = i6;
        }
    }

    /* JADX INFO: compiled from: DvbParser.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f6863a;
        public final boolean b;
        public final byte[] c;
        public final byte[] d;

        public c(int i, boolean z, byte[] bArr, byte[] bArr2) {
            this.f6863a = i;
            this.b = z;
            this.c = bArr;
            this.d = bArr2;
        }
    }

    /* JADX INFO: compiled from: DvbParser.java */
    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f6864a;
        public final int b;
        public final SparseArray<e> c;

        public d(int i, int i2, int i3, SparseArray<e> sparseArray) {
            this.f6864a = i2;
            this.b = i3;
            this.c = sparseArray;
        }
    }

    /* JADX INFO: compiled from: DvbParser.java */
    public static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f6865a;
        public final int b;

        public e(int i, int i2) {
            this.f6865a = i;
            this.b = i2;
        }
    }

    /* JADX INFO: compiled from: DvbParser.java */
    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f6866a;
        public final boolean b;
        public final int c;
        public final int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f6867e;
        public final int f;
        public final int g;
        public final int h;
        public final int i;
        public final SparseArray<g> j;

        public f(int i, boolean z, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, SparseArray<g> sparseArray) {
            this.f6866a = i;
            this.b = z;
            this.c = i2;
            this.d = i3;
            this.f6867e = i5;
            this.f = i6;
            this.g = i7;
            this.h = i8;
            this.i = i9;
            this.j = sparseArray;
        }

        public void a(f fVar) {
            if (fVar == null) {
                return;
            }
            SparseArray<g> sparseArray = fVar.j;
            for (int i = 0; i < sparseArray.size(); i++) {
                this.j.put(sparseArray.keyAt(i), sparseArray.valueAt(i));
            }
        }
    }

    /* JADX INFO: compiled from: DvbParser.java */
    public static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f6868a;
        public final int b;

        public g(int i, int i2, int i3, int i4, int i5, int i6) {
            this.f6868a = i3;
            this.b = i4;
        }
    }

    /* JADX INFO: compiled from: DvbParser.java */
    public static final class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f6869a;
        public final int b;
        public final SparseArray<f> c = new SparseArray<>();
        public final SparseArray<a> d = new SparseArray<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final SparseArray<c> f6870e = new SparseArray<>();
        public final SparseArray<a> f = new SparseArray<>();
        public final SparseArray<c> g = new SparseArray<>();
        public b h;
        public d i;

        public h(int i, int i2) {
            this.f6869a = i;
            this.b = i2;
        }

        public void a() {
            this.c.clear();
            this.d.clear();
            this.f6870e.clear();
            this.f.clear();
            this.g.clear();
            this.h = null;
            this.i = null;
        }
    }

    public a60(int i2, int i3) {
        Paint paint = new Paint();
        this.f6858a = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.f6858a.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        this.f6858a.setPathEffect(null);
        Paint paint2 = new Paint();
        this.b = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.b.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        this.b.setPathEffect(null);
        this.c = new Canvas();
        this.d = new b(719, 575, 0, 719, 0, 575);
        this.f6859e = new a(0, b(), c(), d());
        this.f = new h(i2, i3);
    }

    public static int a(int i2, int i3, int i4, int i5) {
        return (i2 << 24) | (i3 << 16) | (i4 << 8) | i5;
    }

    public static f b(n80 n80Var, int i2) {
        int iC;
        int iC2;
        int iC3 = n80Var.c(8);
        n80Var.b(4);
        boolean zD = n80Var.d();
        n80Var.b(3);
        int i3 = 16;
        int iC4 = n80Var.c(16);
        int iC5 = n80Var.c(16);
        int iC6 = n80Var.c(3);
        int iC7 = n80Var.c(3);
        int i4 = 2;
        n80Var.b(2);
        int iC8 = n80Var.c(8);
        int iC9 = n80Var.c(8);
        int iC10 = n80Var.c(4);
        int iC11 = n80Var.c(2);
        n80Var.b(2);
        int i5 = i2 - 10;
        SparseArray sparseArray = new SparseArray();
        while (i5 > 0) {
            int iC12 = n80Var.c(i3);
            int iC13 = n80Var.c(i4);
            int iC14 = n80Var.c(i4);
            int iC15 = n80Var.c(12);
            int i6 = iC11;
            n80Var.b(4);
            int iC16 = n80Var.c(12);
            i5 -= 6;
            if (iC13 == 1 || iC13 == 2) {
                i5 -= 2;
                iC = n80Var.c(8);
                iC2 = n80Var.c(8);
            } else {
                iC = 0;
                iC2 = 0;
            }
            sparseArray.put(iC12, new g(iC13, iC14, iC15, iC16, iC, iC2));
            iC11 = i6;
            i4 = 2;
            i3 = 16;
        }
        return new f(iC3, zD, iC4, iC5, iC6, iC7, iC8, iC9, iC10, iC11, sparseArray);
    }

    public static a c(n80 n80Var, int i2) {
        int iC;
        int i3;
        int iC2;
        int iC3;
        int iC4;
        int i4 = 8;
        int iC5 = n80Var.c(8);
        n80Var.b(8);
        int i5 = 2;
        int i6 = i2 - 2;
        int[] iArrB = b();
        int[] iArrC = c();
        int[] iArrD = d();
        while (i6 > 0) {
            int iC6 = n80Var.c(i4);
            int iC7 = n80Var.c(i4);
            int i7 = i6 - 2;
            int[] iArr = (iC7 & 128) != 0 ? iArrB : (iC7 & 64) != 0 ? iArrC : iArrD;
            if ((iC7 & 1) != 0) {
                iC3 = n80Var.c(i4);
                iC4 = n80Var.c(i4);
                iC = n80Var.c(i4);
                iC2 = n80Var.c(i4);
                i3 = i7 - 4;
            } else {
                int iC8 = n80Var.c(6) << i5;
                int iC9 = n80Var.c(4) << 4;
                iC = n80Var.c(4) << 4;
                i3 = i7 - 2;
                iC2 = n80Var.c(i5) << 6;
                iC3 = iC8;
                iC4 = iC9;
            }
            if (iC3 == 0) {
                iC4 = 0;
                iC = 0;
                iC2 = 255;
            }
            double d2 = iC3;
            double d3 = iC4 - 128;
            double d4 = iC - 128;
            iArr[iC6] = a((byte) (255 - (iC2 & 255)), x80.a((int) (d2 + (1.402d * d3)), 0, 255), x80.a((int) ((d2 - (0.34414d * d4)) - (d3 * 0.71414d)), 0, 255), x80.a((int) (d2 + (d4 * 1.772d)), 0, 255));
            i6 = i3;
            iC5 = iC5;
            i4 = 8;
            i5 = 2;
        }
        return new a(iC5, iArrB, iArrC, iArrD);
    }

    public static int[] d() {
        int[] iArr = new int[256];
        iArr[0] = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            if (i2 < 8) {
                iArr[i2] = a(63, (i2 & 1) != 0 ? 255 : 0, (i2 & 2) != 0 ? 255 : 0, (i2 & 4) == 0 ? 0 : 255);
            } else {
                int i3 = i2 & HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE;
                if (i3 == 0) {
                    iArr[i2] = a(255, ((i2 & 1) != 0 ? 85 : 0) + ((i2 & 16) != 0 ? 170 : 0), ((i2 & 2) != 0 ? 85 : 0) + ((i2 & 32) != 0 ? 170 : 0), ((i2 & 4) == 0 ? 0 : 85) + ((i2 & 64) == 0 ? 0 : 170));
                } else if (i3 == 8) {
                    iArr[i2] = a(127, ((i2 & 1) != 0 ? 85 : 0) + ((i2 & 16) != 0 ? 170 : 0), ((i2 & 2) != 0 ? 85 : 0) + ((i2 & 32) != 0 ? 170 : 0), ((i2 & 4) == 0 ? 0 : 85) + ((i2 & 64) == 0 ? 0 : 170));
                } else if (i3 == 128) {
                    iArr[i2] = a(255, ((i2 & 1) != 0 ? 43 : 0) + 127 + ((i2 & 16) != 0 ? 85 : 0), ((i2 & 2) != 0 ? 43 : 0) + 127 + ((i2 & 32) != 0 ? 85 : 0), ((i2 & 4) == 0 ? 0 : 43) + 127 + ((i2 & 64) == 0 ? 0 : 85));
                } else if (i3 == 136) {
                    iArr[i2] = a(255, ((i2 & 1) != 0 ? 43 : 0) + ((i2 & 16) != 0 ? 85 : 0), ((i2 & 2) != 0 ? 43 : 0) + ((i2 & 32) != 0 ? 85 : 0), ((i2 & 4) == 0 ? 0 : 43) + ((i2 & 64) == 0 ? 0 : 85));
                }
            }
        }
        return iArr;
    }

    public void a() {
        this.f.a();
    }

    public List<y50> a(byte[] bArr, int i2) {
        int i3;
        SparseArray<g> sparseArray;
        n80 n80Var = new n80(bArr, i2);
        while (n80Var.a() >= 48 && n80Var.c(8) == 15) {
            a(n80Var, this.f);
        }
        h hVar = this.f;
        if (hVar.i == null) {
            return Collections.emptyList();
        }
        b bVar = hVar.h;
        if (bVar == null) {
            bVar = this.d;
        }
        Bitmap bitmap = this.g;
        if (bitmap == null || bVar.f6861a + 1 != bitmap.getWidth() || bVar.b + 1 != this.g.getHeight()) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bVar.f6861a + 1, bVar.b + 1, Bitmap.Config.ARGB_8888);
            this.g = bitmapCreateBitmap;
            this.c.setBitmap(bitmapCreateBitmap);
        }
        ArrayList arrayList = new ArrayList();
        SparseArray<e> sparseArray2 = this.f.i.c;
        for (int i4 = 0; i4 < sparseArray2.size(); i4++) {
            e eVarValueAt = sparseArray2.valueAt(i4);
            f fVar = this.f.c.get(sparseArray2.keyAt(i4));
            int i5 = eVarValueAt.f6865a + bVar.c;
            int i6 = eVarValueAt.b + bVar.f6862e;
            float f2 = i5;
            float f3 = i6;
            this.c.clipRect(f2, f3, Math.min(fVar.c + i5, bVar.d), Math.min(fVar.d + i6, bVar.f), Region.Op.REPLACE);
            a aVar = this.f.d.get(fVar.f);
            if (aVar == null && (aVar = this.f.f.get(fVar.f)) == null) {
                aVar = this.f6859e;
            }
            SparseArray<g> sparseArray3 = fVar.j;
            int i7 = 0;
            while (i7 < sparseArray3.size()) {
                int iKeyAt = sparseArray3.keyAt(i7);
                g gVarValueAt = sparseArray3.valueAt(i7);
                c cVar = this.f.f6870e.get(iKeyAt);
                c cVar2 = cVar == null ? this.f.g.get(iKeyAt) : cVar;
                if (cVar2 != null) {
                    i3 = i7;
                    sparseArray = sparseArray3;
                    a(cVar2, aVar, fVar.f6867e, gVarValueAt.f6868a + i5, i6 + gVarValueAt.b, cVar2.b ? null : this.f6858a, this.c);
                } else {
                    i3 = i7;
                    sparseArray = sparseArray3;
                }
                i7 = i3 + 1;
                sparseArray3 = sparseArray;
            }
            if (fVar.b) {
                int i8 = fVar.f6867e;
                this.b.setColor(i8 == 3 ? aVar.d[fVar.g] : i8 == 2 ? aVar.c[fVar.h] : aVar.b[fVar.i]);
                this.c.drawRect(f2, f3, fVar.c + i5, fVar.d + i6, this.b);
            }
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(this.g, i5, i6, fVar.c, fVar.d);
            int i9 = bVar.f6861a;
            int i10 = bVar.b;
            arrayList.add(new y50(bitmapCreateBitmap2, f2 / i9, 0, f3 / i10, 0, fVar.c / i9, fVar.d / i10));
            this.c.drawColor(0, PorterDuff.Mode.CLEAR);
        }
        return arrayList;
    }

    public static int[] c() {
        int[] iArr = new int[16];
        iArr[0] = 0;
        for (int i2 = 1; i2 < 16; i2++) {
            if (i2 < 8) {
                iArr[i2] = a(255, (i2 & 1) != 0 ? 255 : 0, (i2 & 2) != 0 ? 255 : 0, (i2 & 4) != 0 ? 255 : 0);
            } else {
                iArr[i2] = a(255, (i2 & 1) != 0 ? 127 : 0, (i2 & 2) != 0 ? 127 : 0, (i2 & 4) == 0 ? 0 : 127);
            }
        }
        return iArr;
    }

    public static int c(n80 n80Var, int[] iArr, byte[] bArr, int i2, int i3, Paint paint, Canvas canvas) {
        boolean z;
        int iC;
        int i4 = i2;
        boolean z2 = false;
        while (true) {
            int iC2 = n80Var.c(8);
            if (iC2 != 0) {
                z = z2;
                iC = 1;
            } else if (!n80Var.d()) {
                int iC3 = n80Var.c(7);
                if (iC3 != 0) {
                    z = z2;
                    iC = iC3;
                    iC2 = 0;
                } else {
                    iC2 = 0;
                    z = true;
                    iC = 0;
                }
            } else {
                z = z2;
                iC = n80Var.c(7);
                iC2 = n80Var.c(8);
            }
            if (iC != 0 && paint != null) {
                if (bArr != null) {
                    iC2 = bArr[iC2];
                }
                paint.setColor(iArr[iC2]);
                canvas.drawRect(i4, i3, i4 + iC, i3 + 1, paint);
            }
            i4 += iC;
            if (z) {
                return i4;
            }
            z2 = z;
        }
    }

    public static c b(n80 n80Var) {
        byte[] bArr;
        int iC = n80Var.c(16);
        n80Var.b(4);
        int iC2 = n80Var.c(2);
        boolean zD = n80Var.d();
        n80Var.b(1);
        byte[] bArr2 = null;
        if (iC2 == 1) {
            n80Var.b(n80Var.c(8) * 16);
        } else {
            if (iC2 == 0) {
                int iC3 = n80Var.c(16);
                int iC4 = n80Var.c(16);
                if (iC3 > 0) {
                    bArr2 = new byte[iC3];
                    n80Var.a(bArr2, 0, iC3);
                }
                if (iC4 > 0) {
                    bArr = new byte[iC4];
                    n80Var.a(bArr, 0, iC4);
                }
            }
            return new c(iC, zD, bArr2, bArr);
        }
        bArr = bArr2;
        return new c(iC, zD, bArr2, bArr);
    }

    public static int[] b() {
        return new int[]{0, -1, -16777216, -8421505};
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008e A[LOOP:0: B:3:0x0009->B:36:0x008e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int b(supwisdom.n80 r13, int[] r14, byte[] r15, int r16, int r17, android.graphics.Paint r18, android.graphics.Canvas r19) {
        /*
            r0 = r13
            r1 = r17
            r8 = r18
            r9 = 0
            r10 = r16
            r2 = 0
        L9:
            r3 = 4
            int r4 = r13.c(r3)
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L16
            r11 = r2
        L13:
            r12 = 1
            goto L6e
        L16:
            boolean r4 = r13.d()
            r7 = 3
            if (r4 != 0) goto L2c
            int r3 = r13.c(r7)
            if (r3 == 0) goto L29
            int r5 = r3 + 2
            r11 = r2
            r12 = r5
            r4 = 0
            goto L6e
        L29:
            r4 = 0
            r11 = 1
            goto L4d
        L2c:
            boolean r4 = r13.d()
            if (r4 != 0) goto L3f
            int r4 = r13.c(r5)
            int r5 = r4 + 4
            int r4 = r13.c(r3)
        L3c:
            r11 = r2
            r12 = r5
            goto L6e
        L3f:
            int r4 = r13.c(r5)
            if (r4 == 0) goto L6b
            if (r4 == r6) goto L67
            if (r4 == r5) goto L5c
            if (r4 == r7) goto L4f
            r11 = r2
            r4 = 0
        L4d:
            r12 = 0
            goto L6e
        L4f:
            r4 = 8
            int r4 = r13.c(r4)
            int r5 = r4 + 25
            int r4 = r13.c(r3)
            goto L3c
        L5c:
            int r4 = r13.c(r3)
            int r5 = r4 + 9
            int r4 = r13.c(r3)
            goto L3c
        L67:
            r11 = r2
            r4 = 0
            r12 = 2
            goto L6e
        L6b:
            r11 = r2
            r4 = 0
            goto L13
        L6e:
            if (r12 == 0) goto L8a
            if (r8 == 0) goto L8a
            if (r15 == 0) goto L76
            r4 = r15[r4]
        L76:
            r2 = r14[r4]
            r8.setColor(r2)
            float r3 = (float) r10
            float r4 = (float) r1
            int r2 = r10 + r12
            float r5 = (float) r2
            int r2 = r1 + 1
            float r6 = (float) r2
            r2 = r19
            r7 = r18
            r2.drawRect(r3, r4, r5, r6, r7)
        L8a:
            int r10 = r10 + r12
            if (r11 == 0) goto L8e
            return r10
        L8e:
            r2 = r11
            goto L9
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.a60.b(supwisdom.n80, int[], byte[], int, int, android.graphics.Paint, android.graphics.Canvas):int");
    }

    public static void a(n80 n80Var, h hVar) {
        int iC = n80Var.c(8);
        int iC2 = n80Var.c(16);
        int iC3 = n80Var.c(16);
        int iC4 = n80Var.c() + iC3;
        if (iC3 * 8 > n80Var.a()) {
            Log.w("DvbParser", "Data field length exceeds limit");
            n80Var.b(n80Var.a());
            return;
        }
        switch (iC) {
            case 16:
                if (iC2 == hVar.f6869a) {
                    d dVar = hVar.i;
                    d dVarA = a(n80Var, iC3);
                    if (dVarA.b != 0) {
                        hVar.i = dVarA;
                        hVar.c.clear();
                        hVar.d.clear();
                        hVar.f6870e.clear();
                    } else if (dVar != null && dVar.f6864a != dVarA.f6864a) {
                        hVar.i = dVarA;
                    }
                }
                break;
            case 17:
                d dVar2 = hVar.i;
                if (iC2 == hVar.f6869a && dVar2 != null) {
                    f fVarB = b(n80Var, iC3);
                    if (dVar2.b == 0) {
                        fVarB.a(hVar.c.get(fVarB.f6866a));
                    }
                    hVar.c.put(fVarB.f6866a, fVarB);
                }
                break;
            case 18:
                if (iC2 == hVar.f6869a) {
                    a aVarC = c(n80Var, iC3);
                    hVar.d.put(aVarC.f6860a, aVarC);
                } else if (iC2 == hVar.b) {
                    a aVarC2 = c(n80Var, iC3);
                    hVar.f.put(aVarC2.f6860a, aVarC2);
                }
                break;
            case 19:
                if (iC2 == hVar.f6869a) {
                    c cVarB = b(n80Var);
                    hVar.f6870e.put(cVarB.f6863a, cVarB);
                } else if (iC2 == hVar.b) {
                    c cVarB2 = b(n80Var);
                    hVar.g.put(cVarB2.f6863a, cVarB2);
                }
                break;
            case 20:
                if (iC2 == hVar.f6869a) {
                    hVar.h = a(n80Var);
                }
                break;
        }
        n80Var.d(iC4 - n80Var.c());
    }

    public static b a(n80 n80Var) {
        int i2;
        int iC;
        int i3;
        int i4;
        n80Var.b(4);
        boolean zD = n80Var.d();
        n80Var.b(3);
        int iC2 = n80Var.c(16);
        int iC3 = n80Var.c(16);
        if (zD) {
            int iC4 = n80Var.c(16);
            int iC5 = n80Var.c(16);
            int iC6 = n80Var.c(16);
            iC = n80Var.c(16);
            i2 = iC5;
            i4 = iC6;
            i3 = iC4;
        } else {
            i2 = iC2;
            iC = iC3;
            i3 = 0;
            i4 = 0;
        }
        return new b(iC2, iC3, i3, i2, i4, iC);
    }

    public static d a(n80 n80Var, int i2) {
        int iC = n80Var.c(8);
        int iC2 = n80Var.c(4);
        int iC3 = n80Var.c(2);
        n80Var.b(2);
        int i3 = i2 - 2;
        SparseArray sparseArray = new SparseArray();
        while (i3 > 0) {
            int iC4 = n80Var.c(8);
            n80Var.b(8);
            i3 -= 6;
            sparseArray.put(iC4, new e(n80Var.c(16), n80Var.c(16)));
        }
        return new d(iC, iC2, iC3, sparseArray);
    }

    public static void a(c cVar, a aVar, int i2, int i3, int i4, Paint paint, Canvas canvas) {
        int[] iArr;
        if (i2 == 3) {
            iArr = aVar.d;
        } else if (i2 == 2) {
            iArr = aVar.c;
        } else {
            iArr = aVar.b;
        }
        int[] iArr2 = iArr;
        a(cVar.c, iArr2, i2, i3, i4, paint, canvas);
        a(cVar.d, iArr2, i2, i3, i4 + 1, paint, canvas);
    }

    public static void a(byte[] bArr, int[] iArr, int i2, int i3, int i4, Paint paint, Canvas canvas) {
        byte[] bArr2;
        byte[] bArr3;
        n80 n80Var = new n80(bArr);
        int iA = i3;
        int i5 = i4;
        byte[] bArrA = null;
        byte[] bArrA2 = null;
        while (n80Var.a() != 0) {
            int iC = n80Var.c(8);
            if (iC != 240) {
                switch (iC) {
                    case 16:
                        if (i2 == 3) {
                            bArr3 = bArrA == null ? i : bArrA;
                        } else if (i2 == 2) {
                            bArr3 = bArrA2 == null ? h : bArrA2;
                        } else {
                            bArr2 = null;
                            iA = a(n80Var, iArr, bArr2, iA, i5, paint, canvas);
                            n80Var.e();
                        }
                        bArr2 = bArr3;
                        iA = a(n80Var, iArr, bArr2, iA, i5, paint, canvas);
                        n80Var.e();
                        break;
                    case 17:
                        iA = b(n80Var, iArr, i2 == 3 ? j : null, iA, i5, paint, canvas);
                        n80Var.e();
                        break;
                    case 18:
                        iA = c(n80Var, iArr, null, iA, i5, paint, canvas);
                        break;
                    default:
                        switch (iC) {
                            case 32:
                                bArrA2 = a(4, 4, n80Var);
                                break;
                            case 33:
                                bArrA = a(4, 8, n80Var);
                                break;
                            case 34:
                                bArrA = a(16, 8, n80Var);
                                break;
                        }
                        break;
                }
            } else {
                i5 += 2;
                iA = i3;
            }
        }
    }

    public static int a(n80 n80Var, int[] iArr, byte[] bArr, int i2, int i3, Paint paint, Canvas canvas) {
        boolean z;
        int i4;
        int iC;
        int iC2;
        int i5 = i2;
        boolean z2 = false;
        while (true) {
            int iC3 = n80Var.c(2);
            if (n80Var.d()) {
                if (n80Var.d()) {
                    iC = n80Var.c(3) + 3;
                    iC2 = n80Var.c(2);
                } else if (n80Var.d()) {
                    z = z2;
                    iC3 = 0;
                    i4 = 0;
                } else {
                    int iC4 = n80Var.c(2);
                    if (iC4 == 0) {
                        iC3 = 0;
                        z = true;
                        i4 = 0;
                    } else if (iC4 == 1) {
                        z = z2;
                        iC3 = 0;
                        i4 = 2;
                    } else if (iC4 != 2) {
                        if (iC4 == 3) {
                            iC = n80Var.c(8) + 29;
                            iC2 = n80Var.c(2);
                        }
                        z = z2;
                        iC3 = 0;
                        i4 = 0;
                    } else {
                        iC = n80Var.c(4) + 12;
                        iC2 = n80Var.c(2);
                    }
                }
                z = z2;
                i4 = iC;
                iC3 = iC2;
            } else {
                z = z2;
                i4 = 1;
            }
            if (i4 != 0 && paint != null) {
                if (bArr != null) {
                    iC3 = bArr[iC3];
                }
                paint.setColor(iArr[iC3]);
                canvas.drawRect(i5, i3, i5 + i4, i3 + 1, paint);
            }
            i5 += i4;
            if (z) {
                return i5;
            }
            z2 = z;
        }
    }

    public static byte[] a(int i2, int i3, n80 n80Var) {
        byte[] bArr = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr[i4] = (byte) n80Var.c(i3);
        }
        return bArr;
    }
}
