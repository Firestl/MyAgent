package supwisdom;

import android.util.Pair;
import java.util.ArrayList;
import net.ossrs.yasea.SrsFlvMuxer;

/* JADX INFO: compiled from: CodecSpecificDataUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class f80 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f7579a = {0, 0, 0, 1};
    public static final int[] b = {96000, 88200, 64000, 48000, 44100, 32000, 24000, SrsFlvMuxer.SrsCodecAudioSampleRate.R22050, 16000, 12000, SrsFlvMuxer.SrsCodecAudioSampleRate.R11025, 8000, 7350};
    public static final int[] c = {0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    public static Pair<Integer, Integer> a(byte[] bArr) {
        n80 n80Var = new n80(bArr);
        int iA = a(n80Var);
        int iB = b(n80Var);
        int iC = n80Var.c(4);
        if (iA == 5 || iA == 29) {
            iB = b(n80Var);
            if (a(n80Var) == 22) {
                iC = n80Var.c(4);
            }
        }
        int i = c[iC];
        e80.a(i != -1);
        return Pair.create(Integer.valueOf(iB), Integer.valueOf(i));
    }

    public static byte[] a(int i, int i2, int i3) {
        return new byte[]{(byte) (((i << 3) & 248) | ((i2 >> 1) & 7)), (byte) (((i2 << 7) & 128) | ((i3 << 3) & 120))};
    }

    public static byte[][] b(byte[] bArr) {
        if (!b(bArr, 0)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int iA = 0;
        do {
            arrayList.add(Integer.valueOf(iA));
            iA = a(bArr, iA + f7579a.length);
        } while (iA != -1);
        byte[][] bArr2 = new byte[arrayList.size()][];
        int i = 0;
        while (i < arrayList.size()) {
            int iIntValue = ((Integer) arrayList.get(i)).intValue();
            int iIntValue2 = (i < arrayList.size() + (-1) ? ((Integer) arrayList.get(i + 1)).intValue() : bArr.length) - iIntValue;
            byte[] bArr3 = new byte[iIntValue2];
            System.arraycopy(bArr, iIntValue, bArr3, 0, iIntValue2);
            bArr2[i] = bArr3;
            i++;
        }
        return bArr2;
    }

    public static byte[] a(int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        int i5 = -1;
        while (true) {
            int[] iArr = b;
            if (i4 >= iArr.length) {
                break;
            }
            if (i == iArr[i4]) {
                i5 = i4;
            }
            i4++;
        }
        int i6 = -1;
        while (true) {
            int[] iArr2 = c;
            if (i3 >= iArr2.length) {
                break;
            }
            if (i2 == iArr2[i3]) {
                i6 = i3;
            }
            i3++;
        }
        if (i != -1 && i6 != -1) {
            return a(2, i5, i6);
        }
        throw new IllegalArgumentException("Invalid sample rate or number of channels: " + i + ", " + i2);
    }

    public static boolean b(byte[] bArr, int i) {
        if (bArr.length - i <= f7579a.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr2 = f7579a;
            if (i2 >= bArr2.length) {
                return true;
            }
            if (bArr[i + i2] != bArr2[i2]) {
                return false;
            }
            i2++;
        }
    }

    public static int b(n80 n80Var) {
        int iC = n80Var.c(4);
        if (iC == 15) {
            return n80Var.c(24);
        }
        e80.a(iC < 13);
        return b[iC];
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = f7579a;
        byte[] bArr3 = new byte[bArr2.length + i2];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, i, bArr3, f7579a.length, i2);
        return bArr3;
    }

    public static int a(byte[] bArr, int i) {
        int length = bArr.length - f7579a.length;
        while (i <= length) {
            if (b(bArr, i)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int a(n80 n80Var) {
        int iC = n80Var.c(5);
        return iC == 31 ? n80Var.c(6) + 32 : iC;
    }
}
