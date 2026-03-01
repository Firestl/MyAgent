package supwisdom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Environment;
import com.dmcbig.mediapicker.utils.FileUtils;
import io.dcloud.common.DHInterface.IFeature;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: ZpImageUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class ln1 {
    public static File a(Context context, String str, int i, int i2, int i3) {
        int iA = a(str);
        Bitmap bitmapA = a(str, i, i2);
        if (iA != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(iA);
            bitmapA = Bitmap.createBitmap(bitmapA, 0, 0, bitmapA.getWidth(), bitmapA.getHeight(), matrix, true);
        }
        String str2 = c(context) + System.nanoTime() + FileUtils.JPEG_FILE_SUFFIX;
        if (bitmapA == null) {
            return null;
        }
        return a(str2, bitmapA, i3);
    }

    @SuppressLint({"NewApi"})
    public static boolean b() {
        if (Build.VERSION.SDK_INT >= 9) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    public static String c(Context context) {
        return a(context) + File.separator + "img" + File.separator;
    }

    @SuppressLint({"NewApi"})
    public static File b(Context context) {
        if (a()) {
            return context.getExternalCacheDir();
        }
        return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/"));
    }

    public static String a(Context context) {
        File fileB = b(context);
        return (!(Environment.getExternalStorageState() == "mounted" || !b()) || fileB == null) ? context.getCacheDir().getPath() : fileB.getPath();
    }

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 8;
    }

    public static File a(String str, Bitmap bitmap, long j) {
        File file = new File(str.substring(0, str.lastIndexOf("/")));
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }
        if (j <= 0) {
            j = 400;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (byteArrayOutputStream.toByteArray().length / 1024 > 1024) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
            i = 50;
        }
        while (((long) byteArrayOutputStream.toByteArray().length) / 1024 > j * 1.1d && i > 20) {
            byteArrayOutputStream.reset();
            i -= 10;
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        }
        try {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            bitmap.recycle();
            return new File(str);
        } catch (Throwable th) {
            bitmap.recycle();
            throw th;
        }
    }

    public static int a(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(IFeature.F_ORIENTATION, 1);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt != 6) {
                return attributeInt != 8 ? 0 : 270;
            }
            return 90;
        } catch (IOException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static Bitmap a(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static int a(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 <= i2 && i4 <= i) {
            return 1;
        }
        int iRound = Math.round(i3 / i2);
        int iRound2 = Math.round(i4 / i);
        return iRound < iRound2 ? iRound : iRound2;
    }
}
