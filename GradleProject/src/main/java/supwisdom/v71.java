package supwisdom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.sangfor.sdk.utils.SFLogN;
import java.io.InputStream;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class v71 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f9488a = 512;
    public static String b = "ro.build.characteristics";
    public static String c = "isPhone";
    public static DisplayMetrics d;

    public static int a(float f) {
        return (int) ((d.density * f) + 0.5f);
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        d = context.getResources().getDisplayMetrics();
        return true;
    }

    public static boolean c(Context context) {
        String strA = u71.a(b, c);
        return TextUtils.isEmpty(strA) ? (context.getResources().getConfiguration().screenLayout & 15) >= 3 : strA.contains("tablet");
    }

    public static int a() {
        return d.heightPixels;
    }

    public static int b() {
        return d.widthPixels;
    }

    public static void a(Activity activity) {
        try {
            Method declaredMethod = Activity.class.getDeclaredMethod("convertFromTranslucent", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(activity, new Object[0]);
        } catch (Throwable unused) {
        }
    }

    public static void b(Activity activity) {
        int i = Build.VERSION.SDK_INT;
        if (i < 26 || i > 27 || activity.getApplicationInfo().targetSdkVersion < 26) {
            return;
        }
        a(activity);
    }

    public static boolean c(Activity activity) {
        int i;
        int i2;
        if (c((Context) activity)) {
            return false;
        }
        if (c((Context) activity)) {
            i = 2;
            i2 = 0;
        } else {
            i = 1;
            i2 = 1;
        }
        b(activity);
        if (i != activity.getResources().getConfiguration().orientation) {
            activity.setRequestedOrientation(i2);
            return true;
        }
        activity.setRequestedOrientation(i2);
        return false;
    }

    public static Bitmap a(Context context, String str) throws Throwable {
        InputStream inputStreamOpen;
        InputStream inputStream = null;
        Bitmap bitmapDecodeStream = null;
        try {
            inputStreamOpen = context.getAssets().open(str);
        } catch (Exception e2) {
            e = e2;
            inputStreamOpen = null;
        } catch (Throwable th) {
            th = th;
            vb1.a(inputStream);
            throw th;
        }
        try {
            try {
                bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen);
            } catch (Throwable th2) {
                th = th2;
                inputStream = inputStreamOpen;
                vb1.a(inputStream);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            SFLogN.a("UIHelper", "load Assets failed: " + str, e);
        }
        vb1.a(inputStreamOpen);
        return bitmapDecodeStream;
    }

    public static int a(Context context) {
        if (!c(context)) {
            return b();
        }
        if (context.getResources().getConfiguration().orientation == 2) {
            return Math.min(a(), a(f9488a));
        }
        return b();
    }
}
