package supwisdom;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.JSUtil;
import java.util.Map;

/* JADX INFO: compiled from: AuthResult.java */
/* JADX INFO: loaded from: classes2.dex */
public class pm1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f8805a;
    public String b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f8806e;

    public pm1(Map<String, String> map, boolean z) {
        if (map == null) {
            return;
        }
        for (String str : map.keySet()) {
            if (TextUtils.equals(str, "resultStatus")) {
                this.f8805a = map.get(str);
            } else if (TextUtils.equals(str, "result")) {
                this.b = map.get(str);
            } else if (TextUtils.equals(str, "memo")) {
                this.c = map.get(str);
            }
        }
        for (String str2 : this.b.split("&")) {
            if (str2.startsWith("alipay_open_id")) {
                a(a("alipay_open_id=", str2), z);
            } else if (str2.startsWith("auth_code")) {
                this.f8806e = a(a("auth_code=", str2), z);
            } else if (str2.startsWith("result_code")) {
                this.d = a(a("result_code=", str2), z);
            }
        }
    }

    public final String a(String str, boolean z) {
        if (!z || TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.startsWith(JSUtil.QUOTE)) {
            str = str.replaceFirst(JSUtil.QUOTE, "");
        }
        return str.endsWith(JSUtil.QUOTE) ? str.substring(0, str.length() - 1) : str;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.f8805a;
    }

    public String toString() {
        return "authCode={" + this.f8806e + "}; resultStatus={" + this.f8805a + "}; memo={" + this.c + "}; result={" + this.b + Operators.BLOCK_END_STR;
    }

    public final String a(String str, String str2) {
        return str2.substring(str.length(), str2.length());
    }

    public String a() {
        return this.f8806e;
    }
}
