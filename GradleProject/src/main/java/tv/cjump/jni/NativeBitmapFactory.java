package tv.cjump.jni;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import io.dcloud.common.constant.AbsoluteConst;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes3.dex */
public class NativeBitmapFactory {
    public static Field nativeIntField = null;
    public static boolean nativeLibLoaded = false;
    public static boolean notLoadAgain = false;

    public static native Bitmap createBitmap(int i, int i2, int i3, boolean z);

    public static Bitmap createBitmap(int i, int i2, Bitmap.Config config) {
        return createBitmap(i, i2, config, config.equals(Bitmap.Config.ARGB_8888));
    }

    public static native Bitmap createBitmap19(int i, int i2, int i3, boolean z);

    public static Bitmap createNativeBitmap(int i, int i2, Bitmap.Config config, boolean z) {
        int nativeConfig = getNativeConfig(config);
        return Build.VERSION.SDK_INT == 19 ? createBitmap19(i, i2, nativeConfig, z) : createBitmap(i, i2, nativeConfig, z);
    }

    public static int getNativeConfig(Bitmap.Config config) {
        try {
            if (nativeIntField == null) {
                return 0;
            }
            return nativeIntField.getInt(config);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return 0;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return 0;
        }
    }

    public static native boolean init();

    public static void initField() {
        try {
            Field declaredField = Bitmap.Config.class.getDeclaredField("nativeInt");
            nativeIntField = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            nativeIntField = null;
            e2.printStackTrace();
        }
    }

    public static boolean isInNativeAlloc() {
        return Build.VERSION.SDK_INT < 11 || (nativeLibLoaded && nativeIntField != null);
    }

    public static void loadLibs() {
        if (notLoadAgain) {
            return;
        }
        if (!DeviceUtils.isRealARMArch() && !DeviceUtils.isRealX86Arch()) {
            notLoadAgain = true;
            nativeLibLoaded = false;
            return;
        }
        if (nativeLibLoaded) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT < 11 || Build.VERSION.SDK_INT >= 23) {
                notLoadAgain = true;
                nativeLibLoaded = false;
            } else {
                System.loadLibrary("ndkbitmap");
                nativeLibLoaded = true;
            }
        } catch (Error e2) {
            e2.printStackTrace();
            notLoadAgain = true;
            nativeLibLoaded = false;
        } catch (Exception e3) {
            e3.printStackTrace();
            notLoadAgain = true;
            nativeLibLoaded = false;
        }
        if (nativeLibLoaded) {
            if (init()) {
                initField();
                if (!testLib()) {
                    release();
                    notLoadAgain = true;
                    nativeLibLoaded = false;
                }
            } else {
                release();
                notLoadAgain = true;
                nativeLibLoaded = false;
            }
        }
        Log.e("NativeBitmapFactory", AbsoluteConst.EVENTS_LOADED + nativeLibLoaded);
    }

    public static void recycle(Bitmap bitmap) {
        bitmap.recycle();
    }

    public static native boolean release();

    public static void releaseLibs() {
        if (nativeLibLoaded) {
            release();
        }
        nativeIntField = null;
        nativeLibLoaded = false;
    }

    @SuppressLint({"NewApi"})
    public static boolean testLib() {
        if (nativeIntField == null) {
            return false;
        }
        Bitmap bitmapCreateNativeBitmap = null;
        try {
            try {
                bitmapCreateNativeBitmap = createNativeBitmap(2, 2, Bitmap.Config.ARGB_8888, true);
                boolean zIsPremultiplied = bitmapCreateNativeBitmap != null && bitmapCreateNativeBitmap.getWidth() == 2 && bitmapCreateNativeBitmap.getHeight() == 2;
                if (zIsPremultiplied) {
                    if (Build.VERSION.SDK_INT >= 17 && !bitmapCreateNativeBitmap.isPremultiplied()) {
                        bitmapCreateNativeBitmap.setPremultiplied(true);
                    }
                    Canvas canvas = new Canvas(bitmapCreateNativeBitmap);
                    Paint paint = new Paint();
                    paint.setColor(-65536);
                    paint.setTextSize(20.0f);
                    canvas.drawRect(0.0f, 0.0f, bitmapCreateNativeBitmap.getWidth(), bitmapCreateNativeBitmap.getHeight(), paint);
                    canvas.drawText("TestLib", 0.0f, 0.0f, paint);
                    if (Build.VERSION.SDK_INT >= 17) {
                        zIsPremultiplied = bitmapCreateNativeBitmap.isPremultiplied();
                    }
                }
                if (bitmapCreateNativeBitmap != null) {
                    bitmapCreateNativeBitmap.recycle();
                }
                return zIsPremultiplied;
            } catch (Error unused) {
                if (bitmapCreateNativeBitmap != null) {
                    bitmapCreateNativeBitmap.recycle();
                }
                return false;
            } catch (Exception e2) {
                Log.e("NativeBitmapFactory", "exception:" + e2.toString());
                if (bitmapCreateNativeBitmap != null) {
                    bitmapCreateNativeBitmap.recycle();
                }
                return false;
            }
        } catch (Throwable th) {
            if (bitmapCreateNativeBitmap != null) {
                bitmapCreateNativeBitmap.recycle();
            }
            throw th;
        }
    }

    public static Bitmap createBitmap(int i, int i2, Bitmap.Config config, boolean z) {
        return (!nativeLibLoaded || nativeIntField == null) ? Bitmap.createBitmap(i, i2, config) : createNativeBitmap(i, i2, config, z);
    }
}
