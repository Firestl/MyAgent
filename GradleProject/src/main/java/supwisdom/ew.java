package supwisdom;

import dc.squareup.okhttp3.internal.ws.WebSocketProtocol;
import java.util.Arrays;
import org.bouncycastle.math.raw.Interleave;

/* JADX INFO: loaded from: classes.dex */
public final class ew implements Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f7536a;
    public int b;

    public ew() {
        this.b = 0;
        this.f7536a = new int[1];
    }

    public static int[] e(int i) {
        return new int[(i + 31) / 32];
    }

    public void a() {
        int length = this.f7536a.length;
        for (int i = 0; i < length; i++) {
            this.f7536a[i] = 0;
        }
    }

    public int[] b() {
        return this.f7536a;
    }

    public int c(int i) {
        int i2 = this.b;
        if (i >= i2) {
            return i2;
        }
        int i3 = i / 32;
        int i4 = (~((1 << (i & 31)) - 1)) & (~this.f7536a[i3]);
        while (i4 == 0) {
            i3++;
            int[] iArr = this.f7536a;
            if (i3 == iArr.length) {
                return this.b;
            }
            i4 = ~iArr[i3];
        }
        int iNumberOfTrailingZeros = (i3 * 32) + Integer.numberOfTrailingZeros(i4);
        int i5 = this.b;
        return iNumberOfTrailingZeros > i5 ? i5 : iNumberOfTrailingZeros;
    }

    public void d() {
        int[] iArr = new int[this.f7536a.length];
        int i = (this.b - 1) / 32;
        int i2 = i + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            long j = this.f7536a[i3];
            long j2 = ((j & Interleave.M32) << 1) | ((j >> 1) & Interleave.M32);
            long j3 = ((j2 & 858993459) << 2) | ((j2 >> 2) & 858993459);
            long j4 = ((j3 & 252645135) << 4) | ((j3 >> 4) & 252645135);
            long j5 = ((j4 & 16711935) << 8) | ((j4 >> 8) & 16711935);
            iArr[i - i3] = (int) (((j5 & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 16) | ((j5 >> 16) & WebSocketProtocol.PAYLOAD_SHORT_MAX));
        }
        int i4 = this.b;
        int i5 = i2 * 32;
        if (i4 != i5) {
            int i6 = i5 - i4;
            int i7 = 1;
            for (int i8 = 0; i8 < 31 - i6; i8++) {
                i7 = (i7 << 1) | 1;
            }
            int i9 = (iArr[0] >> i6) & i7;
            for (int i10 = 1; i10 < i2; i10++) {
                int i11 = iArr[i10];
                iArr[i10 - 1] = i9 | (i11 << (32 - i6));
                i9 = (i11 >> i6) & i7;
            }
            iArr[i2 - 1] = i9;
        }
        this.f7536a = iArr;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ew)) {
            return false;
        }
        ew ewVar = (ew) obj;
        return this.b == ewVar.b && Arrays.equals(this.f7536a, ewVar.f7536a);
    }

    public int hashCode() {
        return (this.b * 31) + Arrays.hashCode(this.f7536a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.b);
        for (int i = 0; i < this.b; i++) {
            if ((i & 7) == 0) {
                sb.append(' ');
            }
            sb.append(a(i) ? 'X' : '.');
        }
        return sb.toString();
    }

    public int b(int i) {
        int i2 = this.b;
        if (i >= i2) {
            return i2;
        }
        int i3 = i / 32;
        int i4 = (~((1 << (i & 31)) - 1)) & this.f7536a[i3];
        while (i4 == 0) {
            i3++;
            int[] iArr = this.f7536a;
            if (i3 == iArr.length) {
                return this.b;
            }
            i4 = iArr[i3];
        }
        int iNumberOfTrailingZeros = (i3 * 32) + Integer.numberOfTrailingZeros(i4);
        int i5 = this.b;
        return iNumberOfTrailingZeros > i5 ? i5 : iNumberOfTrailingZeros;
    }

    public ew clone() {
        return new ew((int[]) this.f7536a.clone(), this.b);
    }

    public boolean a(int i) {
        return ((1 << (i & 31)) & this.f7536a[i / 32]) != 0;
    }

    public ew(int i) {
        this.b = i;
        this.f7536a = e(i);
    }

    public boolean a(int i, int i2, boolean z) {
        int i3;
        if (i2 < i) {
            throw new IllegalArgumentException();
        }
        if (i2 == i) {
            return true;
        }
        int i4 = i2 - 1;
        int i5 = i / 32;
        int i6 = i4 / 32;
        int i7 = i5;
        while (i7 <= i6) {
            int i8 = i7 > i5 ? 0 : i & 31;
            int i9 = i7 < i6 ? 31 : i4 & 31;
            if (i8 == 0 && i9 == 31) {
                i3 = -1;
            } else {
                i3 = 0;
                while (i8 <= i9) {
                    i3 |= 1 << i8;
                    i8++;
                }
            }
            int i10 = this.f7536a[i7] & i3;
            if (!z) {
                i3 = 0;
            }
            if (i10 != i3) {
                return false;
            }
            i7++;
        }
        return true;
    }

    public ew(int[] iArr, int i) {
        this.f7536a = iArr;
        this.b = i;
    }

    public void a(int i, int i2) {
        this.f7536a[i / 32] = i2;
    }

    public int c() {
        return this.b;
    }

    public void d(int i) {
        int[] iArr = this.f7536a;
        int i2 = i / 32;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
    }
}
