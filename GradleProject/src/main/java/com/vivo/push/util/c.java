package com.vivo.push.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/* JADX INFO: compiled from: BitmapUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {
    public static Bitmap a(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = i / width;
        float f2 = i2 / height;
        try {
            Matrix matrix = new Matrix();
            matrix.postScale(f, f2);
            return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        } catch (Exception unused) {
            return bitmap;
        }
    }
}
