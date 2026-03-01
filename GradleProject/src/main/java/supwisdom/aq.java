package supwisdom;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.app.EnvUtils;

/* JADX INFO: loaded from: classes.dex */
public class aq {
    public static String a(Context context) {
        if (EnvUtils.a()) {
            return "https://mobilegw.alipaydev.com/mgw.htm";
        }
        if (context == null) {
            return uo.f9427a;
        }
        String str = uo.f9427a;
        return TextUtils.isEmpty(str) ? uo.f9427a : str;
    }
}
