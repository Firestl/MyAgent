package com.baidu.idl.face.platform.utils;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class ImageExif {
    public static final String TAG = "CameraExif";

    public static int getOrientation(byte[] bArr) {
        int i;
        int i2;
        if (bArr == null) {
            return 0;
        }
        int i3 = 0;
        while (i3 + 3 < bArr.length) {
            int i4 = i3 + 1;
            if ((bArr[i3] & 255) == 255) {
                int i5 = bArr[i4] & 255;
                if (i5 != 255) {
                    i4++;
                    if (i5 != 216 && i5 != 1) {
                        if (i5 != 217 && i5 != 218) {
                            int iPack = pack(bArr, i4, 2, false);
                            if (iPack >= 2 && (i2 = i4 + iPack) <= bArr.length) {
                                if (i5 == 225 && iPack >= 8 && pack(bArr, i4 + 2, 4, false) == 1165519206 && pack(bArr, i4 + 6, 2, false) == 0) {
                                    i3 = i4 + 8;
                                    i = iPack - 8;
                                    break;
                                }
                                i3 = i2;
                            } else {
                                Log.e(TAG, "Invalid length");
                                return 0;
                            }
                        }
                    }
                }
                i3 = i4;
            }
            i3 = i4;
        }
        i = 0;
        if (i > 8) {
            int iPack2 = pack(bArr, i3, 4, false);
            if (iPack2 != 1229531648 && iPack2 != 1296891946) {
                Log.e(TAG, "Invalid byte order");
                return 0;
            }
            boolean z = iPack2 == 1229531648;
            int iPack3 = pack(bArr, i3 + 4, 4, z) + 2;
            if (iPack3 < 10 || iPack3 > i) {
                Log.e(TAG, "Invalid offset");
                return 0;
            }
            int i6 = i3 + iPack3;
            int i7 = i - iPack3;
            int iPack4 = pack(bArr, i6 - 2, 2, z);
            while (true) {
                int i8 = iPack4 - 1;
                if (iPack4 <= 0 || i7 < 12) {
                    break;
                }
                if (pack(bArr, i6, 2, z) == 274) {
                    int iPack5 = pack(bArr, i6 + 8, 2, z);
                    if (iPack5 == 1) {
                        return 0;
                    }
                    if (iPack5 == 3) {
                        return 180;
                    }
                    if (iPack5 == 6) {
                        return 90;
                    }
                    if (iPack5 == 8) {
                        return 270;
                    }
                    Log.i(TAG, "Unsupported orientation");
                    return 0;
                }
                i6 += 12;
                i7 -= 12;
                iPack4 = i8;
            }
        }
        Log.i(TAG, "Orientation not found");
        return 0;
    }

    public static int pack(byte[] bArr, int i, int i2, boolean z) {
        int i3;
        if (z) {
            i += i2 - 1;
            i3 = -1;
        } else {
            i3 = 1;
        }
        int i4 = 0;
        while (true) {
            int i5 = i2 - 1;
            if (i2 <= 0) {
                return i4;
            }
            if (i >= 0 && bArr != null && i < bArr.length) {
                i4 = (i4 << 8) | (bArr[i] & 255);
            }
            i += i3;
            i2 = i5;
        }
    }
}
