package supwisdom;

import com.taobao.weex.WXEnvironment;

/* JADX INFO: compiled from: CheckForUpdateUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class jm1 {
    public static String a(int i) {
        return WXEnvironment.getApplication() != null ? WXEnvironment.getApplication().getString(i) : "";
    }
}
