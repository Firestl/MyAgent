package com.alipay.mobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.a.a;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.umeng.commonsdk.statistics.idtracking.k;
import java.util.HashMap;
import java.util.Map;
import supwisdom.lq;

/* JADX INFO: loaded from: classes.dex */
public class SecurityClientMobile {
    public static synchronized String GetApdid(Context context, Map<String, String> map) {
        HashMap map2 = new HashMap();
        map2.put(k.f5447a, lq.a(map, k.f5447a, ""));
        map2.put("tid", lq.a(map, "tid", ""));
        map2.put("userId", lq.a(map, "userId", ""));
        APSecuritySdk.getInstance(context).initToken(0, map2, null);
        return a.a(context);
    }
}
