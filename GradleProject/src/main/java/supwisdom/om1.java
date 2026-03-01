package supwisdom;

import android.content.Context;
import java.io.File;

/* JADX INFO: compiled from: AppEnv.java */
/* JADX INFO: loaded from: classes2.dex */
public class om1 {
    public static String a(Context context) {
        File dir = context.getDir("za-cache", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getPath();
    }

    public static String b(Context context) {
        return a(context) + File.separator + "za_data_cache";
    }

    public static String c(Context context) {
        File dir = context.getDir("uni-cache", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getPath();
    }

    public static String d(Context context) {
        File dir = context.getDir("user-cache", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getPath();
    }

    public static boolean b(String str) {
        return new File(str).exists();
    }

    public static void a(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }
}
