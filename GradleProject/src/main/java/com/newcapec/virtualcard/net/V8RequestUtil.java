package com.newcapec.virtualcard.net;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.igexin.sdk.PushConsts;
import com.loopj.android.http.RequestParams;
import com.newcapec.virtualcard.R;
import com.newcapec.virtualcard.VirtualCard;
import com.newcapec.virtualcard.entity.ECardResData;
import com.newcapec.virtualcard.entity.V8SResData;
import io.dcloud.feature.payment.weixin.WeiXinPay;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import supwisdom.b0;
import supwisdom.d0;
import supwisdom.gw0;
import supwisdom.h0;
import supwisdom.hw0;

/* JADX INFO: loaded from: classes2.dex */
public class V8RequestUtil {
    public static final String COMMAND_QUERY_TOKEN = "queryTokenInfo";
    public static final Handler MAIN_HANDLER;
    public static final String SERVICE_ADDRESS_GET_TOKEN;
    public static final String TAG = "V8RequestUtil";
    public static final String URL_SUFFIX;

    public interface ResultCallBack {
        void onFail(String str);

        void onSuccess(String str, String str2);
    }

    static {
        URL_SUFFIX = VirtualCard.isT6 ? "%s/lightappService/business/" : "%s/openplartform/business/";
        MAIN_HANDLER = new Handler(Looper.getMainLooper());
        SERVICE_ADDRESS_GET_TOKEN = VirtualCard.isT6 ? "%s/lightappService/business/api/token" : "%s/openplartform/auth/api/token";
    }

    public static String getApiToken() throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(PushConsts.KEY_CLIENT_ID, (Object) b0.a(R.string.clientid));
        jSONObject.put("secretid", (Object) b0.a(R.string.secretid));
        HashMap map = new HashMap();
        map.put("Content-Type", RequestParams.APPLICATION_JSON);
        NetResponse netResponseSendPost = HttpUtil.sendPost(String.format(SERVICE_ADDRESS_GET_TOKEN, b0.a(R.string.server)), map, jSONObject.toJSONString());
        int code = netResponseSendPost.getCode();
        if (code != 200) {
            throw new Exception(String.format("获取token失败(%s)：%s", Integer.valueOf(code), netResponseSendPost.getMessage()));
        }
        String content = netResponseSendPost.getContent();
        Log.d(TAG, "getApiToken: 获取api Token result=" + content);
        V8SResData v8SResData = (V8SResData) JSON.parseObject(content, V8SResData.class);
        if (!v8SResData.isSuccess()) {
            throw new Exception(String.format("获取token失败(%s)", v8SResData.getMessage()));
        }
        Log.d(TAG, "getApiToken: 获取api Token成功");
        return JSON.parseObject(v8SResData.getResultData()).getString("token");
    }

    public static void getAppToken(final String str, final String str2, final ResultCallBack resultCallBack) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() { // from class: com.newcapec.virtualcard.net.V8RequestUtil.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String apiToken = V8RequestUtil.getApiToken();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("schoolcode", (Object) str);
                    jSONObject.put("idserial", (Object) gw0.b(b0.a(R.string.des_key), str2));
                    jSONObject.put("txdate", (Object) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    final NetResponse netResponseSendData = V8RequestUtil.sendData(apiToken, V8RequestUtil.COMMAND_QUERY_TOKEN, jSONObject.toJSONString());
                    ECardResData eCardResData = (ECardResData) JSON.parseObject(netResponseSendData.getContent(), new TypeReference<ECardResData<String>>() { // from class: com.newcapec.virtualcard.net.V8RequestUtil.1.1
                    }, new Feature[0]);
                    if (eCardResData == null || !eCardResData.isSuccess()) {
                        if (resultCallBack != null) {
                            V8RequestUtil.MAIN_HANDLER.post(new Runnable() { // from class: com.newcapec.virtualcard.net.V8RequestUtil.1.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    resultCallBack.onFail(netResponseSendData.getContent());
                                }
                            });
                            return;
                        }
                        return;
                    }
                    String strA = hw0.a((String) eCardResData.getResultData(), b0.a(R.string.sm4_key));
                    Log.d(V8RequestUtil.TAG, "genAppToken: decodeData=" + strA);
                    final String string = JSON.parseObject(strA).getString("token");
                    final String string2 = JSON.parseObject(strA).getString("effectdate");
                    if (resultCallBack != null) {
                        V8RequestUtil.MAIN_HANDLER.post(new Runnable() { // from class: com.newcapec.virtualcard.net.V8RequestUtil.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                resultCallBack.onSuccess(string, string2);
                            }
                        });
                    }
                    d0.a(V8RequestUtil.TAG, "run: genAppToken: newToken=" + string);
                    d0.a(V8RequestUtil.TAG, "run: genAppToken: effectdate=" + string2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (resultCallBack != null) {
                        V8RequestUtil.MAIN_HANDLER.post(new Runnable() { // from class: com.newcapec.virtualcard.net.V8RequestUtil.1.4
                            @Override // java.lang.Runnable
                            public void run() {
                                resultCallBack.onFail(e2.getMessage());
                            }
                        });
                    }
                }
            }
        });
    }

    public static NetResponse sendData(String str, String str2, String str3) {
        d0.a("V8ServiceRequestUtil", "request-plaintext: " + str3);
        HashMap map = new HashMap();
        map.put("Content-Type", "application/json;charset=UTF-8");
        map.put("Token", str);
        String strB = hw0.b(str3, b0.a(R.string.sm4_key));
        map.put(WeiXinPay.PayInfoResult.KEY_SIGN, h0.a(strB));
        return HttpUtil.sendPost(String.format(URL_SUFFIX, b0.a(R.string.server)) + str2, map, strB);
    }
}
