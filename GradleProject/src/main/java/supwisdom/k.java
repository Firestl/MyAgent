package supwisdom;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.igexin.sdk.PushConsts;
import com.loopj.android.http.RequestParams;
import com.newcapec.virtualcard.R;
import com.newcapec.virtualcard.entity.ECardResData;
import com.newcapec.virtualcard.entity.V8SResData;
import com.newcapec.virtualcard.net.HttpUtil;
import com.newcapec.virtualcard.net.NetResponse;
import com.taobao.weex.common.Constants;
import io.dcloud.feature.payment.weixin.WeiXinPay;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class k {

    public class a extends TypeReference<ECardResData<String>> {
        public a(k kVar) {
        }
    }

    public class b extends TypeReference<ECardResData<String>> {
        public b(k kVar) {
        }
    }

    public class c extends TypeReference<ECardResData<String>> {
        public c(k kVar) {
        }
    }

    public k() {
        v.e().c();
    }

    public ECardResData<String> a(String str) throws Exception {
        a();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("outid", (Object) a.a.a.c.c.m().f());
        jSONObject.put("devcode", (Object) b0.a());
        jSONObject.put("userpubkey", (Object) str);
        NetResponse netResponseB = b("S06002", jSONObject.toJSONString());
        if (netResponseB.isSuccess()) {
            return (ECardResData) JSON.parseObject(netResponseB.getContent(), new c(this), new Feature[0]);
        }
        throw new Exception(netResponseB.getMessage());
    }

    public ECardResData<String> a(String str, String str2) throws Exception {
        a();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("schoolcode", (Object) str);
        jSONObject.put("token", (Object) str2);
        NetResponse netResponseB = b("queryIdserialByToken", jSONObject.toJSONString());
        if (netResponseB.isSuccess()) {
            return (ECardResData) JSON.parseObject(netResponseB.getContent(), new a(this), new Feature[0]);
        }
        throw new Exception(netResponseB.getMessage());
    }

    public final NetResponse b(String str, String str2) {
        d0.a("T6Service", "request-command: " + str);
        d0.a("T6Service", "request-plaintext: " + str2);
        HashMap map = new HashMap();
        map.put("Content-Type", "application/json;charset=UTF-8");
        map.put("Token", v.e().a("apiTokenKey", ""));
        String strB = hw0.b(str2, b0.a(R.string.sm4_key));
        Log.d("T6Service", "request: encryptData:" + strB);
        map.put(WeiXinPay.PayInfoResult.KEY_SIGN, h0.a(strB));
        return HttpUtil.sendPost(String.format("%s/lightappService/business/", b0.a(R.string.server)) + str, map, strB);
    }

    public final void a() throws Exception {
        if (v.e().d()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(PushConsts.KEY_CLIENT_ID, (Object) b0.a(R.string.clientid));
        jSONObject.put("secretid", (Object) b0.a(R.string.secretid));
        HashMap map = new HashMap();
        map.put("Content-Type", RequestParams.APPLICATION_JSON);
        NetResponse netResponseSendPost = HttpUtil.sendPost(String.format("%s/lightappService/business/api/token", b0.a(R.string.server)), map, jSONObject.toJSONString());
        if (netResponseSendPost.getCode() != 200) {
            throw new Exception(netResponseSendPost.getMessage());
        }
        V8SResData v8SResData = (V8SResData) JSON.parseObject(netResponseSendPost.getContent(), V8SResData.class);
        if (v8SResData.isSuccess()) {
            Log.d("T6Service", "getToken: 获取Token成功");
            v.e().b("apiTokenKey", JSON.parseObject(v8SResData.getResultData()).getString("token"));
            v.e().f9343a.edit().putBoolean("tokenStatus", true).apply();
            return;
        }
        throw new Exception(v8SResData.getMessage());
    }

    public ECardResData<String> a(String[] strArr) throws Exception {
        String strA;
        a();
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : strArr) {
            stringBuffer.append(str);
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("outid", (Object) a.a.a.c.c.m().f());
        String string = stringBuffer.toString();
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(b0.a(R.string.aes_key).getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] bytes = string.getBytes("utf-8");
            cipher.init(1, secretKeySpec);
            strA = f0.a(cipher.doFinal(bytes));
        } catch (Exception e2) {
            e2.printStackTrace();
            strA = null;
        }
        jSONObject.put(Constants.Value.PASSWORD, (Object) strA);
        NetResponse netResponseB = b("S01006", jSONObject.toJSONString());
        if (netResponseB.isSuccess()) {
            return (ECardResData) JSON.parseObject(netResponseB.getContent(), new b(this), new Feature[0]);
        }
        throw new Exception(netResponseB.getMessage());
    }
}
