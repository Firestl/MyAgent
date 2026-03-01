package com.unicom.xiaowo.account.shield.c;

import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.unicom.xiaowo.account.shield.c.a;
import com.unicom.xiaowo.account.shield.e.i;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a.InterfaceC0136a f5539a;

    public b(a.InterfaceC0136a interfaceC0136a) {
        this.f5539a = null;
        this.f5539a = interfaceC0136a;
    }

    public void a(int i, String str) {
        a(i, str, "");
    }

    public void a(int i, String str, String str2) {
        try {
            if (this.f5539a == null) {
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("resultCode", i);
            jSONObject.put("resultMsg", str);
            jSONObject.put("resultData", "");
            jSONObject.put("traceId", str2);
            jSONObject.put("operatorType", "CU");
            this.f5539a.a(jSONObject.toString());
            this.f5539a = null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(String str, String str2) throws JSONException {
        if (this.f5539a == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("resultCode", 0);
        jSONObject.put("resultMsg", str);
        if (TextUtils.isEmpty(str2)) {
            jSONObject.put("resultData", "");
        } else {
            JSONObject jSONObject2 = new JSONObject(str2);
            jSONObject2.put(RemoteMessageConst.MSGID, i.a("" + System.currentTimeMillis()));
            jSONObject2.put("operatorType", (Object) null);
            jSONObject.put("resultData", jSONObject2);
        }
        jSONObject.put("operatorType", "CU");
        this.f5539a.a(jSONObject.toString());
        this.f5539a = null;
    }
}
