package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.a.a;
import com.alipay.apmobilesecuritysdk.f.b;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.tencent.connect.common.Constants;
import com.umeng.commonsdk.statistics.idtracking.k;
import java.util.HashMap;
import supwisdom.lq;

/* JADX INFO: loaded from: classes.dex */
public class TMNTokenClient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static TMNTokenClient f1596a;
    public Context b;

    public interface InitResultListener {
        void onResult(String str, int i);
    }

    public TMNTokenClient(Context context) {
        this.b = null;
        if (context == null) {
            throw new IllegalArgumentException("TMNTokenClient initialization error: context is null.");
        }
        this.b = context;
    }

    public static TMNTokenClient getInstance(Context context) {
        if (f1596a == null) {
            synchronized (TMNTokenClient.class) {
                if (f1596a == null) {
                    f1596a = new TMNTokenClient(context);
                }
            }
        }
        return f1596a;
    }

    public void intiToken(final String str, String str2, String str3, final InitResultListener initResultListener) {
        if (lq.a(str) && initResultListener != null) {
            initResultListener.onResult("", 2);
        }
        if (lq.a(str2) && initResultListener != null) {
            initResultListener.onResult("", 3);
        }
        final HashMap map = new HashMap();
        map.put(k.f5447a, UtdidWrapper.getUtdid(this.b));
        map.put("tid", "");
        map.put("userId", "");
        map.put("appName", str);
        map.put("appKeyClient", str2);
        map.put("appchannel", "openapi");
        map.put("sessionId", str3);
        map.put("rpcVersion", Constants.VIA_SHARE_TYPE_PUBLISHVIDEO);
        b.a().a(new Runnable() { // from class: com.alipay.apmobilesecuritysdk.face.TMNTokenClient.1
            @Override // java.lang.Runnable
            public void run() {
                int iA = new a(TMNTokenClient.this.b).a(map);
                InitResultListener initResultListener2 = initResultListener;
                if (initResultListener2 == null) {
                    return;
                }
                if (iA != 0) {
                    initResultListener2.onResult("", iA);
                } else {
                    initResultListener.onResult(a.a(TMNTokenClient.this.b, str), 0);
                }
            }
        });
    }
}
