package com.umeng.analytics.pro;

import java.util.ArrayList;
import org.json.JSONObject;

/* JADX INFO: compiled from: AplAction.java */
/* JADX INFO: loaded from: classes2.dex */
public class ad extends ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5161a;
    public String b;

    public ad(String str, ArrayList<ac> arrayList) {
        super(str, arrayList);
        this.f5161a = "";
        this.b = "";
    }

    @Override // com.umeng.analytics.pro.ab, com.umeng.analytics.pro.ai
    public JSONObject a(String str, JSONObject jSONObject) {
        JSONObject jSONObjectA = super.a(str, jSONObject);
        if (jSONObjectA != null) {
            try {
                jSONObjectA.put(com.umeng.ccg.a.s, this.f5161a);
                jSONObjectA.put("action", this.b);
            } catch (Throwable unused) {
            }
        }
        return jSONObjectA;
    }

    public void c(String str) {
        this.f5161a = str;
    }

    public String d() {
        return this.f5161a;
    }

    public String e() {
        return this.b;
    }

    public void d(String str) {
        this.b = str;
    }
}
