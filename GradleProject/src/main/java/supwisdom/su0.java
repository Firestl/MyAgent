package supwisdom;

import android.text.TextUtils;
import com.efs.sdk.base.Constants;
import com.loopj.android.http.RequestParams;
import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import cz.msebera.android.httpclient.message.BasicHeader;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: JsonStreamerEntity.java */
/* JADX INFO: loaded from: classes2.dex */
public class su0 implements ao1 {
    public static final byte[] b;
    public static final xn1 c;
    public static final xn1 d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f9213a = new HashMap();

    static {
        new UnsupportedOperationException("Unsupported operation in this implementation.");
        "true".getBytes();
        AbsoluteConst.FALSE.getBytes();
        b = com.igexin.push.core.b.m.getBytes();
        a("name");
        a("type");
        a("contents");
        c = new BasicHeader("Content-Type", RequestParams.APPLICATION_JSON);
        d = new BasicHeader(HttpHeaders.HEAD_KEY_CONTENT_ENCODING, Constants.CP_GZIP);
    }

    public su0(vu0 vu0Var, boolean z, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a(str);
    }

    public static byte[] a(String str) {
        if (str == null) {
            return b;
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append(Operators.QUOTE);
        int length = str.length();
        int i = -1;
        while (true) {
            i++;
            if (i >= length) {
                sb.append(Operators.QUOTE);
                return sb.toString().getBytes();
            }
            char cCharAt = str.charAt(i);
            if (cCharAt == '\f') {
                sb.append("\\f");
            } else if (cCharAt == '\r') {
                sb.append("\\r");
            } else if (cCharAt == '\"') {
                sb.append("\\\"");
            } else if (cCharAt != '\\') {
                switch (cCharAt) {
                    case '\b':
                        sb.append("\\b");
                        break;
                    case '\t':
                        sb.append("\\t");
                        break;
                    case '\n':
                        sb.append("\\n");
                        break;
                    default:
                        if (cCharAt <= 31 || ((cCharAt >= 127 && cCharAt <= 159) || (cCharAt >= 8192 && cCharAt <= 8447))) {
                            String hexString = Integer.toHexString(cCharAt);
                            sb.append("\\u");
                            int length2 = 4 - hexString.length();
                            for (int i2 = 0; i2 < length2; i2++) {
                                sb.append('0');
                            }
                            sb.append(hexString.toUpperCase(Locale.US));
                        } else {
                            sb.append(cCharAt);
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
    }

    @Override // supwisdom.ao1
    public long b() {
        return -1L;
    }

    public void a(String str, Object obj) {
        this.f9213a.put(str, obj);
    }

    @Override // supwisdom.ao1
    public xn1 a() {
        return c;
    }
}
