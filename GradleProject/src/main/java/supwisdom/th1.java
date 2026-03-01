package supwisdom;

import android.content.Context;
import com.supwisdom.superapp.util.HashUtil;

/* JADX INFO: compiled from: DataCache.java */
/* JADX INFO: loaded from: classes2.dex */
public class th1 {
    public static String b = "KEY_GETUI_MSG_IDS";
    public static final th1 c = new th1();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f9293a;

    public void a(Context context) {
        this.f9293a = om1.b(context);
    }

    public String a(String str) {
        return HashUtil.a(str, HashUtil.HashType.MD5.toString());
    }

    public boolean a(String str, String str2, Object obj) {
        return rm1.a(this.f9293a, str2, obj);
    }

    public <T> T a(String str, String str2) {
        T t = (T) rm1.a(this.f9293a, str2);
        if (t == null) {
            return null;
        }
        return t;
    }

    public String a() {
        return this.f9293a;
    }
}
