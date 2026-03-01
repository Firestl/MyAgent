package supwisdom;

import com.taobao.weex.common.Constants;
import java.util.Comparator;

/* JADX INFO: loaded from: classes2.dex */
public final class jx0 {

    public class a implements Comparator<String> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(String str, String str2) {
            return str.substring(0, str.lastIndexOf("_")).compareTo(str2.substring(0, str2.lastIndexOf("_")));
        }
    }

    public static Comparator<String> a() {
        return new a();
    }

    public static String a(String str, ix0 ix0Var) {
        return str + "_" + ix0Var.b() + Constants.Name.X + ix0Var.a();
    }

    public static void a(String str, sw0 sw0Var) {
        for (String str2 : sw0Var.a()) {
            if (str2.startsWith(str)) {
                sw0Var.remove(str2);
                return;
            }
        }
    }
}
