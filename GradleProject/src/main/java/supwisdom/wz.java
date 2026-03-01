package supwisdom;

import com.dcloud.zxing2.qrcode.decoder.ErrorCorrectionLevel;
import com.tencent.rtmp.TXLiveConstants;

/* JADX INFO: loaded from: classes.dex */
public final class wz {
    public static final int[][] c = {new int[]{21522, 0}, new int[]{20773, 1}, new int[]{24188, 2}, new int[]{23371, 3}, new int[]{17913, 4}, new int[]{16590, 5}, new int[]{20375, 6}, new int[]{19104, 7}, new int[]{30660, 8}, new int[]{29427, 9}, new int[]{32170, 10}, new int[]{30877, 11}, new int[]{26159, 12}, new int[]{25368, 13}, new int[]{27713, 14}, new int[]{26998, 15}, new int[]{5769, 16}, new int[]{5054, 17}, new int[]{7399, 18}, new int[]{6608, 19}, new int[]{1890, 20}, new int[]{597, 21}, new int[]{3340, 22}, new int[]{TXLiveConstants.PLAY_WARNING_VIDEO_DISCONTINUITY, 23}, new int[]{13663, 24}, new int[]{12392, 25}, new int[]{16177, 26}, new int[]{14854, 27}, new int[]{9396, 28}, new int[]{8579, 29}, new int[]{11994, 30}, new int[]{11245, 31}};
    public static final int[] d = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ErrorCorrectionLevel f9684a;
    public final byte b;

    public wz(int i) {
        this.f9684a = ErrorCorrectionLevel.forBits((i >> 3) & 3);
        this.b = (byte) (i & 7);
    }

    public static wz a(int i, int i2) {
        wz wzVarB = b(i, i2);
        return wzVarB != null ? wzVarB : b(i ^ 21522, i2 ^ 21522);
    }

    public static wz b(int i, int i2) {
        int iC;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        for (int[] iArr : c) {
            int i5 = iArr[0];
            if (i5 == i || i5 == i2) {
                return new wz(iArr[1]);
            }
            int iC2 = c(i, i5);
            if (iC2 < i3) {
                i4 = iArr[1];
                i3 = iC2;
            }
            if (i != i2 && (iC = c(i2, i5)) < i3) {
                i4 = iArr[1];
                i3 = iC;
            }
        }
        if (i3 <= 3) {
            return new wz(i4);
        }
        return null;
    }

    public static int c(int i, int i2) {
        int i3 = i ^ i2;
        int[] iArr = d;
        return iArr[i3 & 15] + iArr[(i3 >>> 4) & 15] + iArr[(i3 >>> 8) & 15] + iArr[(i3 >>> 12) & 15] + iArr[(i3 >>> 16) & 15] + iArr[(i3 >>> 20) & 15] + iArr[(i3 >>> 24) & 15] + iArr[(i3 >>> 28) & 15];
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof wz)) {
            return false;
        }
        wz wzVar = (wz) obj;
        return this.f9684a == wzVar.f9684a && this.b == wzVar.b;
    }

    public int hashCode() {
        return (this.f9684a.ordinal() << 3) | this.b;
    }

    public byte a() {
        return this.b;
    }

    public ErrorCorrectionLevel b() {
        return this.f9684a;
    }
}
