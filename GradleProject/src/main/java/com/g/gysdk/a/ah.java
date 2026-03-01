package com.g.gysdk.a;

import com.getui.gtc.dim.DimManager;
import com.getui.gtc.dim.DimRequest;

/* JADX INFO: loaded from: classes.dex */
public class ah {
    public static String a(String str) {
        String str2 = (String) DimManager.getInstance().get(new DimRequest.Builder().key(str).useExpiredCacheForReserve(true).build());
        return str2 == null ? "" : str2;
    }
}
