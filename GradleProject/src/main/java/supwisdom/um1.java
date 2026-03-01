package supwisdom;

import android.content.Context;
import android.os.Environment;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.WebStorage;
import android.webkit.WebViewDatabase;
import java.io.File;
import java.math.BigDecimal;

/* JADX INFO: compiled from: CleanDataUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class um1 {
    public static void a(Context context) {
        WebStorage.getInstance().deleteAllData();
        WebViewDatabase.getInstance(context).clearUsernamePassword();
        WebViewDatabase.getInstance(context).clearHttpAuthUsernamePassword();
        GeolocationPermissions.getInstance().clearAll();
    }

    public static String b(Context context) {
        long jA = a(context.getCacheDir());
        if (Environment.getExternalStorageState().equals("mounted")) {
            jA += a(context.getExternalCacheDir());
        }
        return a(jA + nm1.b.a().longValue());
    }

    public static void a() {
        CookieManager.getInstance().removeAllCookies(null);
    }

    public static long a(File file) {
        File[] fileArrListFiles;
        long length;
        long j = 0;
        if (file != null && (fileArrListFiles = file.listFiles()) != null && fileArrListFiles.length > 0) {
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (fileArrListFiles[i].isDirectory()) {
                    length = a(fileArrListFiles[i]);
                } else {
                    length = fileArrListFiles[i].length();
                }
                j += length;
            }
        }
        return j;
    }

    public static String a(double d) {
        double d2 = d / 1024.0d;
        double d3 = d2 / 1024.0d;
        if (d3 < 1.0d) {
            return new BigDecimal(Double.toString(d2)).setScale(2, 4).toPlainString() + "KB";
        }
        double d4 = d3 / 1024.0d;
        if (d4 < 1.0d) {
            return new BigDecimal(Double.toString(d3)).setScale(2, 4).toPlainString() + "MB";
        }
        double d5 = d4 / 1024.0d;
        if (d5 < 1.0d) {
            return new BigDecimal(Double.toString(d4)).setScale(2, 4).toPlainString() + "GB";
        }
        return BigDecimal.valueOf(d5).setScale(2, 4).toPlainString() + "TB";
    }
}
