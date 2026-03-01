package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.e.h;
import com.umeng.commonsdk.statistics.idtracking.k;
import java.util.HashMap;
import java.util.Map;
import supwisdom.lq;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static synchronized Map<String, String> a(Context context, Map<String, String> map) {
        HashMap map2;
        map2 = new HashMap();
        String strA = lq.a(map, "tid", "");
        String strA2 = lq.a(map, k.f5447a, "");
        String strA3 = lq.a(map, "userId", "");
        String strA4 = lq.a(map, "appName", "");
        String strA5 = lq.a(map, "appKeyClient", "");
        String strA6 = lq.a(map, "tmxSessionId", "");
        String strF = h.f(context);
        String strA7 = lq.a(map, "sessionId", "");
        map2.put("AC1", strA);
        map2.put("AC2", strA2);
        map2.put("AC3", "");
        map2.put("AC4", strF);
        map2.put("AC5", strA3);
        map2.put("AC6", strA6);
        map2.put("AC7", "");
        map2.put("AC8", strA4);
        map2.put("AC9", strA5);
        if (lq.b(strA7)) {
            map2.put("AC10", strA7);
        }
        return map2;
    }
}
