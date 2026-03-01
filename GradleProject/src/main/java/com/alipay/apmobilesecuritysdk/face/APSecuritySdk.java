package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.a.a;
import com.alipay.apmobilesecuritysdk.e.d;
import com.alipay.apmobilesecuritysdk.e.g;
import com.alipay.apmobilesecuritysdk.e.h;
import com.alipay.apmobilesecuritysdk.e.i;
import com.alipay.apmobilesecuritysdk.f.b;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.tencent.connect.common.Constants;
import com.umeng.commonsdk.statistics.idtracking.k;
import java.util.HashMap;
import java.util.Map;
import supwisdom.lq;

/* JADX INFO: loaded from: classes.dex */
public class APSecuritySdk {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static APSecuritySdk f1593a;
    public static Object c = new Object();
    public Context b;

    public interface InitResultListener {
        void onResult(TokenResult tokenResult);
    }

    public class TokenResult {
        public String apdid;
        public String apdidToken;
        public String clientKey;
        public String umidToken;

        public TokenResult() {
        }
    }

    public APSecuritySdk(Context context) {
        this.b = context;
    }

    public static APSecuritySdk getInstance(Context context) {
        if (f1593a == null) {
            synchronized (c) {
                if (f1593a == null) {
                    f1593a = new APSecuritySdk(context);
                }
            }
        }
        return f1593a;
    }

    public static String getUtdid(Context context) {
        return UtdidWrapper.getUtdid(context);
    }

    public String getApdidToken() {
        String strA = a.a(this.b, "");
        if (lq.a(strA)) {
            initToken(0, new HashMap(), null);
        }
        return strA;
    }

    public String getSdkName() {
        return "APPSecuritySDK-ALIPAYSDK";
    }

    public String getSdkVersion() {
        return "3.4.0.201910161639";
    }

    public synchronized TokenResult getTokenResult() {
        TokenResult tokenResult;
        tokenResult = new TokenResult();
        try {
            tokenResult.apdidToken = a.a(this.b, "");
            tokenResult.clientKey = h.f(this.b);
            tokenResult.apdid = a.a(this.b);
            tokenResult.umidToken = UmidSdkWrapper.getSecurityToken(this.b);
            if (lq.a(tokenResult.apdid) || lq.a(tokenResult.apdidToken) || lq.a(tokenResult.clientKey)) {
                initToken(0, new HashMap(), null);
            }
        } catch (Throwable unused) {
        }
        return tokenResult;
    }

    public void initToken(int i, Map<String, String> map, final InitResultListener initResultListener) {
        com.alipay.apmobilesecuritysdk.b.a.a().a(i);
        String strB = h.b(this.b);
        String strC = com.alipay.apmobilesecuritysdk.b.a.a().c();
        if (lq.b(strB) && !lq.a(strB, strC)) {
            com.alipay.apmobilesecuritysdk.e.a.a(this.b);
            d.a(this.b);
            g.a(this.b);
            i.h();
        }
        if (!lq.a(strB, strC)) {
            h.c(this.b, strC);
        }
        String strA = lq.a(map, k.f5447a, "");
        String strA2 = lq.a(map, "tid", "");
        String strA3 = lq.a(map, "userId", "");
        if (lq.a(strA)) {
            strA = UtdidWrapper.getUtdid(this.b);
        }
        final HashMap map2 = new HashMap();
        map2.put(k.f5447a, strA);
        map2.put("tid", strA2);
        map2.put("userId", strA3);
        map2.put("appName", "");
        map2.put("appKeyClient", "");
        map2.put("appchannel", "");
        map2.put("rpcVersion", Constants.VIA_SHARE_TYPE_PUBLISHVIDEO);
        b.a().a(new Runnable() { // from class: com.alipay.apmobilesecuritysdk.face.APSecuritySdk.1
            @Override // java.lang.Runnable
            public void run() {
                new a(APSecuritySdk.this.b).a(map2);
                InitResultListener initResultListener2 = initResultListener;
                if (initResultListener2 != null) {
                    initResultListener2.onResult(APSecuritySdk.this.getTokenResult());
                }
            }
        });
    }
}
