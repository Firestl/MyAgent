package supwisdom;

import com.dcloud.zxing2.FormatException;
import org.bouncycastle.pqc.crypto.qtesla.HashUtils;

/* JADX INFO: loaded from: classes.dex */
public final class yw {
    public static final yw[] h = h();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9924a;
    public final int b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9925e;
    public final c f;
    public final int g;

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9926a;
        public final int b;

        public int a() {
            return this.f9926a;
        }

        public int b() {
            return this.b;
        }

        public b(int i, int i2) {
            this.f9926a = i;
            this.b = i2;
        }
    }

    public yw(int i, int i2, int i3, int i4, int i5, c cVar) {
        this.f9924a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.f9925e = i5;
        this.f = cVar;
        int iB = cVar.b();
        int iA = 0;
        for (b bVar : cVar.a()) {
            iA += bVar.a() * (bVar.b() + iB);
        }
        this.g = iA;
    }

    public static yw[] h() {
        int i = 1;
        int i2 = 5;
        int i3 = 8;
        yw ywVar = new yw(3, 14, 14, 12, 12, new c(10, new b(i, i3)));
        int i4 = 2;
        int i5 = 12;
        int i6 = 18;
        yw ywVar2 = new yw(7, 22, 22, 20, 20, new c(20, new b(i, 30)));
        int i7 = 6;
        int i8 = 36;
        int i9 = 62;
        int i10 = 56;
        int i11 = 68;
        b bVar = new b(i, 5);
        b bVar2 = new b(i, 10);
        b bVar3 = new b(i, 16);
        return new yw[]{new yw(1, 10, 10, 8, 8, new c(i2, new b(i, 3))), new yw(2, 12, 12, 10, 10, new c(7, new b(i, i2))), ywVar, new yw(4, 16, 16, 14, 14, new c(i5, new b(i, i5))), new yw(5, 18, 18, 16, 16, new c(14, new b(i, i6))), new yw(6, 20, 20, 18, 18, new c(i6, new b(i, 22))), ywVar2, new yw(8, 24, 24, 22, 22, new c(24, new b(i, i8))), new yw(9, 26, 26, 24, 24, new c(28, new b(i, 44))), new yw(10, 32, 32, 14, 14, new c(i8, new b(i, i9))), new yw(11, 36, 36, 16, 16, new c(42, new b(i, 86))), new yw(12, 40, 40, 18, 18, new c(48, new b(i, 114))), new yw(13, 44, 44, 20, 20, new c(i10, new b(i, 144))), new yw(14, 48, 48, 22, 22, new c(i11, new b(i, 174))), new yw(15, 52, 52, 24, 24, new c(42, new b(i4, 102))), new yw(16, 64, 64, 14, 14, new c(i10, new b(i4, 140))), new yw(17, 72, 72, 16, 16, new c(i8, new b(4, 92))), new yw(18, 80, 80, 18, 18, new c(48, new b(4, 114))), new yw(19, 88, 88, 20, 20, new c(i10, new b(4, 144))), new yw(20, 96, 96, 22, 22, new c(i11, new b(4, 174))), new yw(21, 104, 104, 24, 24, new c(i10, new b(i7, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE))), new yw(22, 120, 120, 18, 18, new c(i11, new b(i7, 175))), new yw(23, 132, 132, 20, 20, new c(i9, new b(i3, 163))), new yw(24, 144, 144, 22, 22, new c(i9, new b(i3, 156), new b(i4, 155))), new yw(25, 8, 18, 6, 16, new c(7, bVar)), new yw(26, 8, 32, 6, 14, new c(11, bVar2)), new yw(27, 12, 26, 10, 24, new c(14, bVar3)), new yw(28, 12, 36, 10, 16, new c(i6, new b(i, 22))), new yw(29, 16, 36, 14, 16, new c(24, new b(i, 32))), new yw(30, 16, 48, 14, 22, new c(28, new b(i, 49)))};
    }

    public int a() {
        return this.f9925e;
    }

    public int b() {
        return this.d;
    }

    public c c() {
        return this.f;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.b;
    }

    public int f() {
        return this.g;
    }

    public int g() {
        return this.f9924a;
    }

    public String toString() {
        return String.valueOf(this.f9924a);
    }

    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9927a;
        public final b[] b;

        public b[] a() {
            return this.b;
        }

        public int b() {
            return this.f9927a;
        }

        public c(int i, b bVar) {
            this.f9927a = i;
            this.b = new b[]{bVar};
        }

        public c(int i, b bVar, b bVar2) {
            this.f9927a = i;
            this.b = new b[]{bVar, bVar2};
        }
    }

    public static yw a(int i, int i2) throws FormatException {
        if ((i & 1) != 0 || (i2 & 1) != 0) {
            throw FormatException.getFormatInstance();
        }
        for (yw ywVar : h) {
            if (ywVar.b == i && ywVar.c == i2) {
                return ywVar;
            }
        }
        throw FormatException.getFormatInstance();
    }
}
