package io.dcloud.feature.barcode2.decoding;

import android.graphics.Bitmap;
import supwisdom.uv;

/* JADX INFO: loaded from: classes2.dex */
public class BitmapLuminanceSource extends uv {
    public byte[] bitmapPixels;

    public BitmapLuminanceSource(Bitmap bitmap) {
        super(bitmap.getWidth(), bitmap.getHeight());
        int width = bitmap.getWidth() * bitmap.getHeight();
        int[] iArr = new int[width];
        this.bitmapPixels = new byte[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(iArr, 0, getWidth(), 0, 0, getWidth(), getHeight());
        for (int i = 0; i < width; i++) {
            this.bitmapPixels[i] = (byte) iArr[i];
        }
    }

    @Override // supwisdom.uv
    public byte[] getMatrix() {
        return this.bitmapPixels;
    }

    @Override // supwisdom.uv
    public byte[] getRow(int i, byte[] bArr) {
        System.arraycopy(this.bitmapPixels, i * getWidth(), bArr, 0, getWidth());
        return bArr;
    }
}
