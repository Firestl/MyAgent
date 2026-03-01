package supwisdom;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/* JADX INFO: compiled from: RomUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class cn1 {
    public static int a() {
        if (e()) {
            return 3;
        }
        if (d()) {
            return 1;
        }
        if (c()) {
            return 2;
        }
        return b() ? 3 : 4;
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean c() {
        String str = Build.DISPLAY;
        if (!TextUtils.isEmpty(str) && str.contains("Flyme")) {
            for (String str2 : str.split(Operators.SPACE_STR)) {
                if (str2.matches("^[4-9]\\.(\\d+\\.)+\\S*")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean d() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            String property = properties.getProperty("ro.miui.ui.version.code", null);
            if (property != null) {
                return Integer.parseInt(property) >= 4;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean e() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            String property = properties.getProperty("ro.miui.ui.version.code", null);
            if (property != null) {
                return Integer.parseInt(property) >= 5;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
