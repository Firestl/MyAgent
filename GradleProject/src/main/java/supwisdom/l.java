package supwisdom;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.igexin.sdk.PushConsts;
import com.loopj.android.http.RequestParams;
import com.newcapec.smcrypto.SMKeyAlg;
import com.newcapec.virtualcard.R;
import com.newcapec.virtualcard.entity.ApplyOfflineAuthReqData;
import com.newcapec.virtualcard.entity.CloseOfflineCodeReqData;
import com.newcapec.virtualcard.entity.ECardResData;
import com.newcapec.virtualcard.entity.OpenOfflineCodeReqData;
import com.newcapec.virtualcard.entity.QueryOnlinePayStatusReqData;
import com.newcapec.virtualcard.entity.QueryPayStatusReqData;
import com.newcapec.virtualcard.entity.V8SGetKeyReqData;
import com.newcapec.virtualcard.entity.V8SGetKeyResult;
import com.newcapec.virtualcard.entity.V8SResData;
import com.newcapec.virtualcard.net.HttpUtil;
import com.newcapec.virtualcard.net.NetResponse;
import io.dcloud.feature.payment.weixin.WeiXinPay;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f8224a = v.e().c();

    public class a extends TypeReference<ECardResData<String>> {
        public a(l lVar) {
        }
    }

    public class b extends TypeReference<ECardResData<String>> {
        public b(l lVar) {
        }
    }

    public class c extends TypeReference<ECardResData<String>> {
        public c(l lVar) {
        }
    }

    public class d extends TypeReference<ECardResData<String>> {
        public d(l lVar) {
        }
    }

    public class e extends TypeReference<ECardResData<String>> {
        public e(l lVar) {
        }
    }

    public class f extends TypeReference<ECardResData<String>> {
        public f(l lVar) {
        }
    }

    public class g extends TypeReference<ECardResData<String>> {
        public g(l lVar) {
        }
    }

    public class h extends TypeReference<ECardResData<String>> {
        public h(l lVar) {
        }
    }

    public ECardResData<String> a(String str) throws Exception {
        c();
        String str2 = "";
        if (!v.e().a("keyStatus", false)) {
            V8SGetKeyReqData v8SGetKeyReqData = new V8SGetKeyReqData();
            v8SGetKeyReqData.setSchoolcode(this.f8224a);
            v8SGetKeyReqData.setRandomNo(System.currentTimeMillis() + "");
            NetResponse netResponseC = c("downloadSecretKey", v8SGetKeyReqData.toString());
            if (netResponseC.getCode() != 200) {
                throw new Exception(netResponseC.getMessage());
            }
            V8SResData v8SResData = (V8SResData) JSON.parseObject(netResponseC.getContent(), V8SResData.class);
            if (!v8SResData.isSuccess()) {
                throw new Exception(v8SResData.getMessage());
            }
            V8SGetKeyResult v8SGetKeyResult = (V8SGetKeyResult) JSON.parseObject(b0.a(JSON.parseObject(hw0.a(v8SResData.getResultData(), b0.a(R.string.sm4_key))).getString("encryptdata"), b0.a(R.string.app_priKey), b0.a(R.string.platform_pubKey)), V8SGetKeyResult.class);
            a.a.a.c.c cVarM = a.a.a.c.c.m();
            cVarM.d(v8SGetKeyResult.getSchoolprvkey());
            a.a.a.c.c.d.b = v8SGetKeyResult.getSchoolpubkey();
            cVarM.b();
            v.e().a(true);
        }
        ApplyOfflineAuthReqData applyOfflineAuthReqData = new ApplyOfflineAuthReqData();
        ApplyOfflineAuthReqData.ApplyOfflineAuthInnerReqData applyOfflineAuthInnerReqData = new ApplyOfflineAuthReqData.ApplyOfflineAuthInnerReqData();
        applyOfflineAuthReqData.setSchoolcode(this.f8224a);
        applyOfflineAuthInnerReqData.setIdserial(a.a.a.c.c.m().f());
        applyOfflineAuthInnerReqData.setDevcode(b0.a());
        applyOfflineAuthInnerReqData.setUserpubkey(str);
        String string = applyOfflineAuthInnerReqData.toString();
        String strH = a.a.a.c.c.m().h();
        String strG = a.a.a.c.c.m().g();
        String strA = b0.a(R.string.platform_pubKey);
        SMKeyAlg sMKeyAlg = new SMKeyAlg(Logger.getLogger("SmcryptoUtil"));
        int length = string.length();
        byte[] bArr = new byte[length + 194];
        int[] iArr = new int[1];
        int iSafetySend = sMKeyAlg.SafetySend("sersecretkeyforv8offlinecode", strH, strG, strA, string.getBytes(), length, bArr, iArr);
        if (iSafetySend != 8000) {
            throw new Exception("sendSafeData:" + iSafetySend);
        }
        int i = iArr[0];
        for (int i2 = 0; i2 < i; i2++) {
            str2 = (str2 + "0123456789abcdef".charAt((bArr[i2] & 240) >> 4)) + "0123456789abcdef".charAt(bArr[i2] & 15);
        }
        applyOfflineAuthReqData.setEncryptdata(str2);
        NetResponse netResponseC2 = c("applyOfflineAuthorization", applyOfflineAuthReqData.toString());
        if (netResponseC2.isSuccess()) {
            return (ECardResData) JSON.parseObject(netResponseC2.getContent(), new d(this), new Feature[0]);
        }
        throw new Exception(netResponseC2.getMessage());
    }

    public ECardResData<String> a(String str, String str2) throws Exception {
        c();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("schoolcode", (Object) str);
        jSONObject.put("token", (Object) str2);
        NetResponse netResponseC = c("queryIdserialByToken", jSONObject.toJSONString());
        if (netResponseC.isSuccess()) {
            return (ECardResData) JSON.parseObject(netResponseC.getContent(), new b(this), new Feature[0]);
        }
        throw new Exception(netResponseC.getMessage());
    }

    public ECardResData<String> b() throws Exception {
        c();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("currentDate", (Object) new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        NetResponse netResponseC = c("getQrcodeSkinConfig", jSONObject.toJSONString());
        if (netResponseC.isSuccess()) {
            return (ECardResData) JSON.parseObject(netResponseC.getContent(), new a(this), new Feature[0]);
        }
        throw new Exception(netResponseC.getMessage());
    }

    public ECardResData<String> b(String str) throws Exception {
        c();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("idserial", (Object) a.a.a.c.c.m().f());
        jSONObject.put("ip", (Object) str);
        NetResponse netResponseC = c("getPaycode", jSONObject.toJSONString());
        d0.a("V8Service", "getOnlinePayCode: " + netResponseC.toString());
        if (netResponseC.isSuccess()) {
            return (ECardResData) JSON.parseObject(netResponseC.getContent(), new e(this), new Feature[0]);
        }
        throw new Exception(netResponseC.getMessage());
    }

    public ECardResData<String> b(String str, String str2) throws Exception {
        c();
        QueryOnlinePayStatusReqData queryOnlinePayStatusReqData = new QueryOnlinePayStatusReqData();
        queryOnlinePayStatusReqData.setIdserial(a.a.a.c.c.m().f());
        queryOnlinePayStatusReqData.setIp(str2);
        queryOnlinePayStatusReqData.setPaycode(str);
        NetResponse netResponseC = c("queryPaycodeStatus", queryOnlinePayStatusReqData.toString());
        if (netResponseC.isSuccess()) {
            return (ECardResData) JSON.parseObject(netResponseC.getContent(), new g(this), new Feature[0]);
        }
        throw new Exception(netResponseC.getMessage());
    }

    public final void c() throws Exception {
        if (v.e().d()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(PushConsts.KEY_CLIENT_ID, (Object) b0.a(R.string.clientid));
        jSONObject.put("secretid", (Object) b0.a(R.string.secretid));
        HashMap map = new HashMap();
        map.put("Content-Type", RequestParams.APPLICATION_JSON);
        NetResponse netResponseSendPost = HttpUtil.sendPost(String.format("%s/openplartform/auth/api/token", b0.a(R.string.server)), map, jSONObject.toJSONString());
        if (netResponseSendPost.getCode() != 200) {
            throw new Exception(netResponseSendPost.getMessage());
        }
        V8SResData v8SResData = (V8SResData) JSON.parseObject(netResponseSendPost.getContent(), V8SResData.class);
        if (!v8SResData.isSuccess()) {
            throw new Exception(v8SResData.getMessage());
        }
        Log.d("V8Service", "getToken: 获取Token成功");
        v.e().b("apiTokenKey", JSON.parseObject(v8SResData.getResultData()).getString("token"));
        v.e().f9343a.edit().putBoolean("tokenStatus", true).apply();
    }

    public ECardResData<String> c(String str) throws Exception {
        c();
        QueryPayStatusReqData queryPayStatusReqData = new QueryPayStatusReqData();
        queryPayStatusReqData.setSchoolcode(this.f8224a);
        queryPayStatusReqData.setIdserial(a.a.a.c.c.m().f());
        queryPayStatusReqData.setDevcode(b0.a());
        queryPayStatusReqData.setPaycode(str);
        NetResponse netResponseC = c("queryOfflineCodeStatus", queryPayStatusReqData.toString());
        if (netResponseC.isSuccess()) {
            return (ECardResData) JSON.parseObject(netResponseC.getContent(), new f(this), new Feature[0]);
        }
        throw new Exception(netResponseC.getMessage());
    }

    public final NetResponse c(String str, String str2) {
        d0.a("V8Service", "request-command: " + str);
        d0.a("V8Service", "request-plaintext: " + str2);
        HashMap map = new HashMap();
        map.put("Content-Type", "application/json;charset=UTF-8");
        map.put("Token", v.e().a("apiTokenKey", ""));
        String strB = hw0.b(str2, b0.a(R.string.sm4_key));
        Log.d("V8Service", "request: encryptData:" + strB);
        map.put(WeiXinPay.PayInfoResult.KEY_SIGN, h0.a(strB));
        return HttpUtil.sendPost(String.format("%s/openplartform/business/", b0.a(R.string.server)) + str, map, strB);
    }

    public ECardResData<String> a() throws Exception {
        c();
        CloseOfflineCodeReqData closeOfflineCodeReqData = new CloseOfflineCodeReqData();
        closeOfflineCodeReqData.setSchoolcode(this.f8224a);
        closeOfflineCodeReqData.setIdserial(a.a.a.c.c.m().f());
        closeOfflineCodeReqData.setDevcode(b0.a());
        NetResponse netResponseC = c("closeOfflineCode", closeOfflineCodeReqData.toString());
        if (netResponseC.isSuccess()) {
            return (ECardResData) JSON.parseObject(netResponseC.getContent(), new h(this), new Feature[0]);
        }
        throw new Exception(netResponseC.getMessage());
    }

    public ECardResData<String> a(String[] strArr) throws Exception {
        c();
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : strArr) {
            stringBuffer.append(gw0.a(str, "1", b0.a(R.string.des_key)));
        }
        OpenOfflineCodeReqData openOfflineCodeReqData = new OpenOfflineCodeReqData();
        openOfflineCodeReqData.setSchoolcode(this.f8224a);
        openOfflineCodeReqData.setIdserial(a.a.a.c.c.m().f());
        openOfflineCodeReqData.setDevcode(b0.a());
        openOfflineCodeReqData.setTxpasswd(stringBuffer.toString());
        NetResponse netResponseC = c("offlineCodeapplyBind", openOfflineCodeReqData.toString());
        if (netResponseC.isSuccess()) {
            return (ECardResData) JSON.parseObject(netResponseC.getContent(), new c(this), new Feature[0]);
        }
        throw new Exception(netResponseC.getMessage());
    }
}
