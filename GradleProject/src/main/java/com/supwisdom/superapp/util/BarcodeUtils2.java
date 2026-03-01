package com.supwisdom.superapp.util;

import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Hashtable;
import supwisdom.an1;

/* JADX INFO: loaded from: classes2.dex */
public class BarcodeUtils2 {

    public enum ScanResultType {
        None,
        User,
        Group,
        PAY,
        AA
    }

    public static Bitmap a(byte[] bArr, int i) {
        try {
            return a(bArr, 300, 300, i);
        } catch (WriterException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Bitmap a(byte[] bArr, int i, int i2, int i3) throws WriterException {
        Hashtable hashtable = new Hashtable();
        hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hashtable.put(EncodeHintType.MARGIN, Integer.valueOf(i3));
        BitMatrix bitMatrixA = new an1().a(bArr, BarcodeFormat.QR_CODE, i, i2, hashtable);
        int width = bitMatrixA.getWidth();
        int height = bitMatrixA.getHeight();
        int[] iArr = new int[width * height];
        for (int i4 = 0; i4 < height; i4++) {
            for (int i5 = 0; i5 < width; i5++) {
                if (bitMatrixA.get(i5, i4)) {
                    iArr[(i4 * width) + i5] = -16777216;
                }
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }
}
