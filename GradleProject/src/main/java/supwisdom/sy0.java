package supwisdom;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.common.DHInterface.IApp;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class sy0 {
    public static String c(String str) {
        int iLastIndexOf = str.lastIndexOf(91);
        if (iLastIndexOf != -1) {
            str = str.substring(iLastIndexOf + 1);
        }
        int iIndexOf = str.indexOf(93);
        return iIndexOf == -1 ? str : str.substring(0, iIndexOf);
    }

    public static String d(String str) {
        int iIndexOf = str.indexOf("DexPathList") + 11;
        if (str.length() <= iIndexOf + 4) {
            return str;
        }
        String strSubstring = str.substring(iIndexOf);
        int iIndexOf2 = strSubstring.indexOf(93);
        if (strSubstring.charAt(0) != '[' || strSubstring.charAt(1) != '[' || iIndexOf2 < 0) {
            return str;
        }
        String[] strArrSplit = strSubstring.substring(2, iIndexOf2).split(",");
        for (int i = 0; i < strArrSplit.length; i++) {
            int iIndexOf3 = strArrSplit[i].indexOf(34);
            int iLastIndexOf = strArrSplit[i].lastIndexOf(34);
            if (iIndexOf3 > 0 && iIndexOf3 < iLastIndexOf) {
                strArrSplit[i] = strArrSplit[i].substring(iIndexOf3 + 1, iLastIndexOf);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArrSplit) {
            if (sb.length() > 0) {
                sb.append(Operators.CONDITION_IF_MIDDLE);
            }
            sb.append(str2);
        }
        return sb.toString();
    }

    public static String[] e(String str) {
        if (str.startsWith("dexPath=")) {
            int iIndexOf = str.indexOf(44);
            str = iIndexOf == -1 ? str.substring(8) : str.substring(8, iIndexOf);
        }
        return str.split(Constants.COLON_SEPARATOR);
    }

    public boolean a(File file) {
        return file.exists();
    }

    public final ClassLoader b() {
        return sy0.class.getClassLoader();
    }

    public final String a(ClassLoader classLoader, Class<?> cls) {
        try {
            Field declaredField = cls.getDeclaredField("path");
            declaredField.setAccessible(true);
            return (String) declaredField.get(classLoader);
        } catch (ClassCastException | IllegalAccessException | NoSuchFieldException unused) {
            return b(classLoader.toString());
        }
    }

    public boolean b(File file) {
        return file.isDirectory() && file.canWrite();
    }

    public static String b(String str) {
        if (str.contains("DexPathList")) {
            return d(str);
        }
        return c(str);
    }

    public File a() {
        try {
            ClassLoader classLoaderB = b();
            Class<?> cls = Class.forName("dalvik.system.PathClassLoader");
            cls.cast(classLoaderB);
            File[] fileArrA = a(a(classLoaderB, cls));
            if (fileArrA.length > 0) {
                return fileArrA[0];
            }
            return null;
        } catch (ClassCastException | ClassNotFoundException unused) {
            return null;
        }
    }

    public File[] a(String str) {
        int iLastIndexOf;
        ArrayList arrayList = new ArrayList();
        for (String str2 : e(str)) {
            if (str2.startsWith("/data/sdk1/") && (iLastIndexOf = str2.lastIndexOf(".apk")) == str2.length() - 4) {
                int iIndexOf = str2.indexOf("-");
                if (iIndexOf != -1) {
                    iLastIndexOf = iIndexOf;
                }
                File file = new File("/data/data/" + str2.substring(11, iLastIndexOf));
                if (b(file)) {
                    File file2 = new File(file, IApp.ConfigProperty.CONFIG_CACHE);
                    if ((a(file2) || file2.mkdir()) && b(file2)) {
                        arrayList.add(file2);
                    }
                }
            }
        }
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }
}
