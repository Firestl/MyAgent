package com.zx.a.I8b7;

import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class u {
    public static boolean a() {
        try {
            if (TextUtils.isEmpty(t2.A)) {
                return false;
            }
            if (System.currentTimeMillis() - t2.t >= new JSONObject(t2.A).getLong("frequency") * 1000) {
                return false;
            }
            y1.a("report freq c true");
            return true;
        } catch (Exception e2) {
            y1.a(e2);
            return false;
        }
    }
}
