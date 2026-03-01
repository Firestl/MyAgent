package io.dcloud.common.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class CompressUtil {
    public static String compressImage(String str, String str2, boolean z) {
        Bitmap bitmapDecodeByteArray;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byte[] bArrInputStream2ByteArr = inputStream2ByteArr(str);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArrInputStream2ByteArr, 0, bArrInputStream2ByteArr.length, options);
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap.CompressFormat compressFormat = z ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG;
            int i = options.outWidth;
            if (i >= 1080 || options.outHeight >= 1080) {
                int i2 = options.outHeight;
                if (i2 < 1080 || i < 1080) {
                    float f = i / (i2 * 1.0f);
                    if (f > 2.0f || f < 0.5d) {
                        bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrInputStream2ByteArr, 0, bArrInputStream2ByteArr.length, options);
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmapDecodeByteArray.compress(compressFormat, 73, byteArrayOutputStream);
                    } else {
                        Matrix matrix = new Matrix();
                        int i3 = options.outWidth;
                        int i4 = options.outHeight;
                        float f2 = 1080.0f / (i3 > i4 ? i3 : i4);
                        matrix.setScale(f2, f2);
                        Bitmap bitmapDecodeByteArray2 = BitmapFactory.decodeByteArray(bArrInputStream2ByteArr, 0, bArrInputStream2ByteArr.length, options);
                        bitmapDecodeByteArray = Bitmap.createBitmap(bitmapDecodeByteArray2, 0, 0, bitmapDecodeByteArray2.getWidth(), bitmapDecodeByteArray2.getHeight(), matrix, true);
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmapDecodeByteArray.compress(compressFormat, 75, byteArrayOutputStream);
                    }
                } else {
                    Matrix matrix2 = new Matrix();
                    int i5 = options.outWidth;
                    int i6 = options.outHeight;
                    float f3 = 1080.0f / (i5 > i6 ? i6 : i5);
                    matrix2.setScale(f3, f3);
                    Bitmap bitmapDecodeByteArray3 = BitmapFactory.decodeByteArray(bArrInputStream2ByteArr, 0, bArrInputStream2ByteArr.length, options);
                    bitmapDecodeByteArray = Bitmap.createBitmap(bitmapDecodeByteArray3, 0, 0, bitmapDecodeByteArray3.getWidth(), bitmapDecodeByteArray3.getHeight(), matrix2, true);
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmapDecodeByteArray.compress(compressFormat, 70, byteArrayOutputStream);
                }
            } else {
                bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrInputStream2ByteArr, 0, bArrInputStream2ByteArr.length, options);
                byteArrayOutputStream = new ByteArrayOutputStream();
                bitmapDecodeByteArray.compress(compressFormat, 75, byteArrayOutputStream);
            }
            try {
                try {
                    File file = new File(str2);
                    File parentFile = file.getParentFile();
                    if (!parentFile.exists() && !parentFile.mkdirs()) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception unused) {
                        }
                        return "";
                    }
                    if (!file.exists() && !file.createNewFile()) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception unused2) {
                        }
                        return "";
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                    fileOutputStream.close();
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception unused3) {
                    }
                    if (bitmapDecodeByteArray != null) {
                        bitmapDecodeByteArray.recycle();
                    }
                    return str2;
                } catch (Exception unused4) {
                    byteArrayOutputStream.close();
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception unused5) {
                    }
                    throw th;
                }
            } catch (Exception unused6) {
            }
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] inputStream2ByteArr(String str) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i == -1) {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (Exception unused) {
            return null;
        }
    }
}
