package com.baidu.idl.face.platform.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.os.Environment;
import android.text.TextUtils;
import io.dcloud.common.DHInterface.IFeature;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class BitmapUtils {
    public static final int CONNECTTIMEOUT = 3000;
    public static final int DEFAULT_IMAGE_JPG_QUALITY = 50;
    public static final int DEFAULT_JPEG_QUALITY = 90;
    public static final int DEFAULT_MAX_SIZE_CELL_NETWORK = 600;
    public static final int IMAGEBOUND = 128;
    public static final String IMAGE_KEY_SUFFIX = "jpg";
    public static final int MAXLENTH = 1024;
    public static final int PIC_COMPRESS_SIZE = 4;
    public static final int QUALITY = 100;
    public static final int QUESTION_IMAGE_JPG_QUALITY = 75;
    public static final int QUESTION_MAX_SIZE_CELL_NETWORK = 1024;
    public static final int ROTATE0 = 0;
    public static final int ROTATE180 = 180;
    public static final int ROTATE270 = 270;
    public static final int ROTATE360 = 360;
    public static final int ROTATE90 = 90;
    public static final String TAG = "ImageUtils";

    public static String bitmapToJpegBase64(Bitmap bitmap, int i, float f) {
        try {
            float fMax = f / Math.max(bitmap.getWidth(), bitmap.getHeight());
            if (fMax < 1.0f) {
                bitmap = scale(bitmap, fMax);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return Base64Utils.encodeToString(byteArray, 2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Bitmap calculateInSampleSize(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (height <= i2 && width <= i) {
            return bitmap;
        }
        float f = i2 / height;
        float f2 = i / width;
        if (f >= f2) {
            f = f2;
        }
        return scale(bitmap, f);
    }

    public static void compressToFile(Bitmap bitmap, String str) throws Throwable {
        compressToFile(bitmap, 90, str);
    }

    public static int computeInitialSampleSize(BitmapFactory.Options options, int i, int i2) {
        int iMin;
        double d = options.outWidth;
        double d2 = options.outHeight;
        int iCeil = i2 == -1 ? 1 : (int) Math.ceil(Math.sqrt((d * d2) / ((double) i2)));
        if (i == -1) {
            iMin = 128;
        } else {
            double d3 = i;
            iMin = (int) Math.min(Math.floor(d / d3), Math.floor(d2 / d3));
        }
        if (iMin < iCeil) {
            return iCeil;
        }
        if (i2 == -1 && i == -1) {
            return 1;
        }
        return i == -1 ? iCeil : iMin;
    }

    public static int computeSampleSize(BitmapFactory.Options options, int i, int i2) {
        int iComputeInitialSampleSize = computeInitialSampleSize(options, i, i2);
        if (iComputeInitialSampleSize > 8) {
            return ((iComputeInitialSampleSize + 7) / 8) * 8;
        }
        int i3 = 1;
        while (i3 < iComputeInitialSampleSize) {
            i3 <<= 1;
        }
        return i3;
    }

    public static Bitmap createBitmap(Context context, byte[] bArr, float f) {
        Bitmap bitmapRotateBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmapDecodeByteArray = null;
        try {
            int iMin = Math.min(DensityUtils.getDisplayWidth(context), DensityUtils.getDisplayHeight(context));
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            options.inSampleSize = computeSampleSize(options, iMin, 1048576);
            options.inJustDecodeBounds = false;
            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            bitmapRotateBitmap = rotateBitmap(f, bitmapDecodeByteArray);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            if (bitmapDecodeByteArray != null && !bitmapDecodeByteArray.isRecycled()) {
                bitmapDecodeByteArray.recycle();
            }
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            options.inSampleSize = computeSampleSize(options, -1, (options.outWidth * options.outHeight) / 4);
            options.inJustDecodeBounds = false;
            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            bitmapRotateBitmap = rotateBitmap(f, bitmapDecodeByteArray);
        }
        if (bitmapRotateBitmap != bitmapDecodeByteArray && bitmapDecodeByteArray != null) {
            bitmapDecodeByteArray.recycle();
        }
        return bitmapRotateBitmap;
    }

    public static Bitmap createLivenessBitmap(Context context, int[] iArr, Rect rect, Rect rect2, int i) {
        Bitmap bitmapCreateBitmap;
        Bitmap bitmapRotateBitmap = null;
        try {
            new BitmapFactory.Options().inJustDecodeBounds = false;
            bitmapCreateBitmap = Bitmap.createBitmap(iArr, rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError e2) {
            e = e2;
            bitmapCreateBitmap = null;
        }
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(bitmapCreateBitmap, rect2.left, rect2.top, rect2.width(), rect2.height());
            bitmapRotateBitmap = rotateBitmap(i, bitmapCreateBitmap);
        } catch (OutOfMemoryError e3) {
            e = e3;
            e.printStackTrace();
            if (bitmapCreateBitmap != null) {
                bitmapCreateBitmap.recycle();
            }
        }
        if (bitmapRotateBitmap != bitmapCreateBitmap && bitmapCreateBitmap != null) {
            bitmapCreateBitmap.recycle();
        }
        return bitmapRotateBitmap;
    }

    public static int decodeImageDegree(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(IFeature.F_ORIENTATION, 1);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt != 6) {
                return attributeInt != 8 ? 0 : 270;
            }
            return 90;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static boolean ensureDirectoryExist(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return true;
        }
        try {
            file.mkdirs();
            return true;
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static int getQuealityParams(Context context, String str) {
        if (str == null) {
        }
        return 50;
    }

    public static int getSizeParams(Context context, String str) {
        if (str == null) {
        }
        return 600;
    }

    public static String getTakePictureCacheDir(Context context) {
        File externalStorageDirectory = TextUtils.equals("mounted", Environment.getExternalStorageState()) ? Environment.getExternalStorageDirectory() : null;
        if (externalStorageDirectory != null) {
            return externalStorageDirectory.getAbsolutePath();
        }
        return null;
    }

    public static Bitmap loadBitmapFromFile(String str) {
        if (str == null) {
            return null;
        }
        try {
            return BitmapFactory.decodeFile(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        } catch (OutOfMemoryError e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static Bitmap rotateBitmap(float f, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        if (f == 0.0f) {
            return bitmap;
        }
        matrix.setRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.net.Uri saveTakePictureImage(byte[] r1, java.lang.String r2, java.lang.String r3) throws java.lang.Throwable {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r2, r3)
            r2 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L22
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L22
            r3.write(r1)     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L46
            r3.flush()     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L46
            r3.close()     // Catch: java.io.IOException -> L15
            goto L19
        L15:
            r1 = move-exception
            r1.printStackTrace()
        L19:
            android.net.Uri r1 = android.net.Uri.fromFile(r0)
            return r1
        L1e:
            r1 = move-exception
            goto L24
        L20:
            r1 = move-exception
            goto L48
        L22:
            r1 = move-exception
            r3 = r2
        L24:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L46
            boolean r1 = r0.exists()     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L46
            if (r1 == 0) goto L3b
            boolean r1 = r0.isFile()     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L46
            if (r1 == 0) goto L3b
            r0.delete()     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L46
            goto L3b
        L37:
            r1 = move-exception
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L46
        L3b:
            if (r3 == 0) goto L45
            r3.close()     // Catch: java.io.IOException -> L41
            goto L45
        L41:
            r1 = move-exception
            r1.printStackTrace()
        L45:
            return r2
        L46:
            r1 = move-exception
            r2 = r3
        L48:
            if (r2 == 0) goto L52
            r2.close()     // Catch: java.io.IOException -> L4e
            goto L52
        L4e:
            r2 = move-exception
            r2.printStackTrace()
        L52:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.idl.face.platform.utils.BitmapUtils.saveTakePictureImage(byte[], java.lang.String, java.lang.String):android.net.Uri");
    }

    public static boolean saveTakePictureResult(Context context, String str, Bitmap bitmap) throws Throwable {
        boolean zCreateNewFile;
        String takePictureCacheDir = getTakePictureCacheDir(context);
        if (TextUtils.isEmpty(takePictureCacheDir)) {
            return false;
        }
        String str2 = takePictureCacheDir + File.separator + str;
        if (!ensureDirectoryExist(new File(str2))) {
            return false;
        }
        String str3 = String.format("B%s.%s", Long.valueOf(System.currentTimeMillis() / 1000), IMAGE_KEY_SUFFIX);
        File file = new File(str + File.separator + str3);
        try {
            zCreateNewFile = !file.exists() ? file.createNewFile() : true;
        } catch (IOException unused) {
            zCreateNewFile = false;
        }
        if (!zCreateNewFile) {
            return false;
        }
        compressToFile(bitmap, 100, str2 + File.separator + str3);
        return true;
    }

    public static Bitmap scale(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static void compressToFile(Bitmap bitmap, int i, String str) throws Throwable {
        compressToFile(bitmap, Bitmap.CompressFormat.JPEG, i, str);
    }

    public static void compressToFile(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i, String str) throws Throwable {
        File file = new File(str);
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    bitmap.compress(compressFormat, i, fileOutputStream2);
                    fileOutputStream2.flush();
                    IoUtils.closeQuietly(fileOutputStream2);
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    try {
                        if (file.exists() && file.isFile()) {
                            file.delete();
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    IoUtils.closeQuietly(fileOutputStream);
                } catch (Exception unused) {
                    fileOutputStream = fileOutputStream2;
                    try {
                        if (file.exists() && file.isFile()) {
                            file.delete();
                        }
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                    IoUtils.closeQuietly(fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    IoUtils.closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
            } catch (Exception unused2) {
            }
        } catch (Throwable th2) {
            th = th2;
            IoUtils.closeQuietly(fileOutputStream);
            throw th;
        }
    }

    public static int decodeImageDegree(byte[] bArr) {
        return ImageExif.getOrientation(bArr);
    }

    public static Bitmap loadBitmapFromFile(Context context, String str) {
        if (str == null) {
            return null;
        }
        try {
            return createBitmap(context, str, decodeImageDegree(str));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        } catch (OutOfMemoryError e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static Bitmap scale(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i / width, i2 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static String bitmapToJpegBase64(Context context, String str, Bitmap bitmap) {
        float sizeParams = getSizeParams(context, str) * 1.0f;
        int quealityParams = getQuealityParams(context, str);
        try {
            float fMax = sizeParams / Math.max(bitmap.getWidth(), bitmap.getHeight());
            if (fMax < 1.0f) {
                bitmap = scale(bitmap, fMax);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quealityParams, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return Base64Utils.encodeToString(byteArray, 2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Bitmap createLivenessBitmap(Context context, int[] iArr, Rect rect) {
        try {
            return Bitmap.createBitmap(iArr, rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String bitmapToJpegBase64(Bitmap bitmap, int i) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Exception unused) {
            byteArrayOutputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            String strEncodeToString = Base64Utils.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            try {
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return strEncodeToString;
        } catch (Exception unused2) {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            if (byteArrayOutputStream2 != null) {
                try {
                    byteArrayOutputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static Bitmap createBitmap(Context context, String str, int i) {
        Bitmap bitmapRotateBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmapDecodeFile = null;
        try {
            int iMin = Math.min(DensityUtils.getDisplayWidth(context), DensityUtils.getDisplayHeight(context));
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            options.inSampleSize = computeSampleSize(options, iMin, 1048576);
            options.inJustDecodeBounds = false;
            bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
            bitmapRotateBitmap = rotateBitmap(i, bitmapDecodeFile);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            if (bitmapDecodeFile != null) {
                bitmapDecodeFile.recycle();
            }
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            options.inSampleSize = computeSampleSize(options, -1, (options.outWidth * options.outHeight) / 4);
            options.inJustDecodeBounds = false;
            bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
            bitmapRotateBitmap = rotateBitmap(i, bitmapDecodeFile);
        }
        if (bitmapRotateBitmap != bitmapDecodeFile && bitmapDecodeFile != null) {
            bitmapDecodeFile.recycle();
        }
        return bitmapRotateBitmap;
    }

    public static Bitmap createBitmap(Context context, byte[] bArr, int i) {
        Bitmap bitmapRotateBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmapDecodeByteArray = null;
        try {
            Math.min(DensityUtils.getDisplayWidth(context), DensityUtils.getDisplayHeight(context));
            options.inJustDecodeBounds = false;
            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            bitmapRotateBitmap = rotateBitmap(i, bitmapDecodeByteArray);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            if (bitmapDecodeByteArray != null) {
                bitmapDecodeByteArray.recycle();
            }
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            options.inSampleSize = computeSampleSize(options, -1, (options.outWidth * options.outHeight) / 4);
            options.inJustDecodeBounds = false;
            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            bitmapRotateBitmap = rotateBitmap(i, bitmapDecodeByteArray);
        }
        if (bitmapRotateBitmap != bitmapDecodeByteArray && bitmapDecodeByteArray != null) {
            bitmapDecodeByteArray.recycle();
        }
        return bitmapRotateBitmap;
    }

    public static Bitmap createBitmap(Context context, int[] iArr, Rect rect, Rect rect2, int i) {
        Bitmap bitmapCreateBitmap;
        Bitmap bitmapRotateBitmap = null;
        try {
            new BitmapFactory.Options().inJustDecodeBounds = false;
            bitmapCreateBitmap = Bitmap.createBitmap(iArr, rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError e2) {
            e = e2;
            bitmapCreateBitmap = null;
        }
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(bitmapCreateBitmap, rect2.left, rect2.top, rect2.width(), rect2.height());
            bitmapRotateBitmap = rotateBitmap(i, bitmapCreateBitmap);
        } catch (OutOfMemoryError e3) {
            e = e3;
            e.printStackTrace();
            if (bitmapCreateBitmap != null) {
                bitmapCreateBitmap.recycle();
            }
        }
        if (bitmapRotateBitmap != bitmapCreateBitmap && bitmapCreateBitmap != null) {
            bitmapCreateBitmap.recycle();
        }
        return bitmapRotateBitmap;
    }

    public static Bitmap createBitmap(Context context, int i, int i2, int[] iArr) {
        Bitmap bitmapCreateBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            Math.min(DensityUtils.getDisplayWidth(context), DensityUtils.getDisplayHeight(context));
            options.inJustDecodeBounds = false;
            bitmapCreateBitmap = Bitmap.createBitmap(iArr, i, i2, Bitmap.Config.RGB_565);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            bitmapCreateBitmap = null;
        }
        if (bitmapCreateBitmap != null && bitmapCreateBitmap != null) {
            bitmapCreateBitmap.recycle();
        }
        return null;
    }
}
