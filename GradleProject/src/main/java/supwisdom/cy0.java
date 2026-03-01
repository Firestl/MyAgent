package supwisdom;

import android.content.Context;
import android.os.Environment;
import io.dcloud.common.DHInterface.IApp;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class cy0 {
    public static File a(Context context) {
        return a(context, true);
    }

    public static File b(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), IApp.ConfigProperty.CONFIG_CACHE);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                by0.d("Unable to create external cache directory", new Object[0]);
                return null;
            }
            try {
                new File(file, ".nomedia").createNewFile();
            } catch (IOException unused) {
                by0.c("Can't create \".nomedia\" file in application external cache directory", new Object[0]);
            }
        }
        return file;
    }

    public static File c(Context context) {
        File fileA = a(context);
        File file = new File(fileA, "uil-images");
        return (file.exists() || file.mkdir()) ? file : fileA;
    }

    public static boolean d(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public static File a(Context context, boolean z) {
        File fileB = (z && "mounted".equals(Environment.getExternalStorageState()) && d(context)) ? b(context) : null;
        if (fileB == null) {
            fileB = context.getCacheDir();
        }
        if (fileB != null) {
            return fileB;
        }
        String str = "/data/data/" + context.getPackageName() + "/cache/";
        by0.d("Can't define system cache directory! '%s' will be used.", str);
        return new File(str);
    }
}
