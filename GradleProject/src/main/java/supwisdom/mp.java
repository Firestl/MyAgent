package supwisdom;

import android.content.Context;
import com.lzy.okgo.model.HttpHeaders;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import supwisdom.dp;

/* JADX INFO: loaded from: classes.dex */
public class mp extends ip {
    @Override // supwisdom.ip
    public String a(pp ppVar, String str, JSONObject jSONObject) {
        return str;
    }

    @Override // supwisdom.ip
    public Map<String, String> a(boolean z, String str) {
        return new HashMap();
    }

    @Override // supwisdom.ip
    public JSONObject a() {
        return null;
    }

    @Override // supwisdom.ip
    public fp a(pp ppVar, Context context, String str) throws Throwable {
        vp.b("mspl", "mdap post");
        byte[] bArrA = yo.a(str.getBytes(Charset.forName("UTF-8")));
        HashMap map = new HashMap();
        map.put("utdId", qp.d().c());
        map.put("logHeader", "RAW");
        map.put("bizCode", "alipaysdk");
        map.put("productId", "alipaysdk_android");
        map.put(HttpHeaders.HEAD_KEY_CONTENT_ENCODING, "Gzip");
        map.put("productVersion", "15.8.00");
        dp.b bVarA = dp.a(context, new dp.a("https://loggw-exsdk.alipay.com/loggw/logUpload.do", map, bArrA));
        vp.b("mspl", "mdap got " + bVarA);
        if (bVarA == null) {
            throw new RuntimeException("Response is null");
        }
        boolean zA = ip.a(bVarA);
        try {
            byte[] bArrB = bVarA.b;
            if (zA) {
                bArrB = yo.b(bArrB);
            }
            return new fp("", new String(bArrB, Charset.forName("UTF-8")));
        } catch (Exception e2) {
            vp.a(e2);
            return null;
        }
    }
}
