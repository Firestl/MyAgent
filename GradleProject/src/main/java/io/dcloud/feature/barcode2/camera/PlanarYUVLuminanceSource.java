package io.dcloud.feature.barcode2.camera;

import android.graphics.Bitmap;
import supwisdom.uv;

/* JADX INFO: loaded from: classes2.dex */
public final class PlanarYUVLuminanceSource extends uv {
    public final int dataHeight;
    public final int dataWidth;
    public final int left;
    public uv rotateLuminanceSource;
    public final int top;
    public final byte[] yuvData;

    public PlanarYUVLuminanceSource(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        super(i5, i6);
        this.rotateLuminanceSource = null;
        if (i5 <= i && i6 <= i2) {
            this.yuvData = bArr;
            this.dataWidth = i;
            this.dataHeight = i2;
            this.left = i3;
            this.top = i4;
            return;
        }
        throw new IllegalArgumentException("Crop rectangle does not fit within image data.width=" + i5 + ";dataWidth=" + i + ";height=" + i6 + ";dataHeight=" + i2);
    }

    public int getDataHeight() {
        return this.dataHeight;
    }

    public int getDataWidth() {
        return this.dataWidth;
    }

    @Override // supwisdom.uv
    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        int i = this.dataWidth;
        if (width == i && height == this.dataHeight) {
            return this.yuvData;
        }
        int i2 = width * height;
        byte[] bArr = new byte[i2];
        int i3 = (this.top * i) + this.left;
        if (width == i) {
            System.arraycopy(this.yuvData, i3, bArr, 0, i2);
            return bArr;
        }
        byte[] bArr2 = this.yuvData;
        for (int i4 = 0; i4 < height; i4++) {
            System.arraycopy(bArr2, i3, bArr, i4 * width, width);
            i3 += this.dataWidth;
        }
        return bArr;
    }

    @Override // supwisdom.uv
    public byte[] getRow(int i, byte[] bArr) {
        if (i < 0 || i >= getHeight()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + i);
        }
        int width = getWidth();
        if (bArr == null || bArr.length < width) {
            bArr = new byte[width];
        }
        System.arraycopy(this.yuvData, ((i + this.top) * this.dataWidth) + this.left, bArr, 0, width);
        return bArr;
    }

    @Override // supwisdom.uv
    public boolean isCropSupported() {
        return true;
    }

    @Override // supwisdom.uv
    public boolean isRotateSupported() {
        return true;
    }

    public Bitmap renderCroppedGreyscaleBitmap() {
        return renderCroppedGreyscaleBitmap(false);
    }

    @Override // supwisdom.uv
    public uv rotateCounterClockwise() {
        if (this.rotateLuminanceSource == null) {
            byte[] matrix = getMatrix();
            int height = getHeight();
            int width = getWidth();
            byte[] bArr = new byte[matrix.length];
            int i = (height - 1) * width;
            for (int i2 = 0; i2 < height; i2++) {
                int i3 = i2 * width;
                for (int i4 = 0; i4 < width; i4++) {
                    bArr[i3 + i4] = matrix[i - (i4 * width)];
                }
                i++;
            }
            this.rotateLuminanceSource = new PlanarYUVLuminanceSource(bArr, width, height, 0, 0, width, height);
        }
        return this.rotateLuminanceSource;
    }

    public Bitmap renderCroppedGreyscaleBitmap(boolean z) {
        int width = getWidth();
        int height = getHeight();
        int[] iArr = new int[width * height];
        byte[] bArr = this.yuvData;
        if (z) {
            int i = ((this.top + height) * this.dataWidth) + this.left;
            for (int i2 = 0; i2 < height; i2++) {
                int i3 = i2 * width;
                for (int i4 = 0; i4 < width; i4++) {
                    iArr[i3 + i4] = ((bArr[i - (this.dataWidth * i4)] & 255) * 65793) | (-16777216);
                }
                i++;
            }
        } else {
            int i5 = (this.top * this.dataWidth) + this.left;
            for (int i6 = 0; i6 < height; i6++) {
                int i7 = i6 * width;
                for (int i8 = 0; i8 < width; i8++) {
                    iArr[i7 + i8] = ((bArr[i5 + i8] & 255) * 65793) | (-16777216);
                }
                i5 += this.dataWidth;
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }
}
