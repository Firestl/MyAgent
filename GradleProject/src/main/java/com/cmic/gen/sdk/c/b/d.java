package com.cmic.gen.sdk.c.b;

import com.tencent.stat.DeviceInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class d extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1707a;
    public final String b;
    public final String c;
    public String d = "authz";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1708e;

    public d(String str, String str2, String str3) {
        this.f1707a = str;
        this.b = str2;
        this.c = str3;
    }

    @Override // com.cmic.gen.sdk.c.b.g
    public String a() {
        return this.f1707a;
    }

    @Override // com.cmic.gen.sdk.c.b.g
    public String a(String str) {
        return null;
    }

    @Override // com.cmic.gen.sdk.c.b.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(DeviceInfo.TAG_VERSION, this.b);
            jSONObject.put("data", this.c);
            jSONObject.put("userCapaid", this.f1708e);
            jSONObject.put("funcType", this.d);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void b(String str) {
        this.d = str;
    }

    public void c(String str) {
        this.f1708e = str;
    }
}
