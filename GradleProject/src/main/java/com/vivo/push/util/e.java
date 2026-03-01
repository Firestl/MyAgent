package com.vivo.push.util;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;

/* JADX INFO: compiled from: ClientReportUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {
    public static boolean a(Context context, long j, long j2) {
        o.d("ClientReportUtil", "report message: " + j + ", reportType: " + j2);
        com.vivo.push.b.x xVar = new com.vivo.push.b.x(j2);
        HashMap<String, String> map = new HashMap<>();
        map.put("messageID", String.valueOf(j));
        String strB = z.b(context, context.getPackageName());
        if (!TextUtils.isEmpty(strB)) {
            map.put("remoteAppId", strB);
        }
        xVar.a(map);
        com.vivo.push.e.a().a(xVar);
        return true;
    }

    public static boolean a(long j, HashMap<String, String> map) {
        com.vivo.push.b.x xVar = new com.vivo.push.b.x(j);
        xVar.a(map);
        xVar.d();
        com.vivo.push.e.a().a(xVar);
        return true;
    }
}
