package supwisdom;

import android.text.TextUtils;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class g {
    public static String a(String str, String str2) {
        String string = (TextUtils.isEmpty(str2) || !(str2.startsWith("CORE") || Pattern.matches("^\\d{4}$", str2))) ? null : i.f7891a.getString(str2);
        Object[] objArr = new Object[2];
        objArr[0] = str;
        if (!TextUtils.isEmpty(string)) {
            str2 = string;
        }
        objArr[1] = str2;
        return String.format("[%s]%s", objArr);
    }
}
