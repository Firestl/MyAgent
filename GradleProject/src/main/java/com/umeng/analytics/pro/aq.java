package com.umeng.analytics.pro;

import com.umeng.commonsdk.service.UMGlobalContext;
import org.json.JSONObject;

/* JADX INFO: compiled from: HttpPostThread.java */
/* JADX INFO: loaded from: classes2.dex */
public class aq implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f5174a = "https://ucc.umeng.com/v2/inn/fetch";
    public String b;
    public String c;
    public String d;

    public aq(String str, JSONObject jSONObject, String str2) {
        this.b = str;
        this.c = jSONObject.toString();
        this.d = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        JSONObject jSONObject = null;
        try {
            byte[] bArrA = ao.a(this.b, this.c);
            if (bArrA != null) {
                JSONObject jSONObject2 = new JSONObject(new String(bArrA));
                JSONObject jSONObject3 = new JSONObject();
                try {
                    jSONObject3.put("sourceIucc", this.d);
                    jSONObject3.put(com.igexin.push.core.b.Y, jSONObject2);
                } catch (Throwable unused) {
                }
                jSONObject = jSONObject3;
            }
        } catch (Throwable unused2) {
        }
        com.umeng.ccg.c.a(UMGlobalContext.getAppContext(), 102, com.umeng.ccg.d.a(), jSONObject);
    }
}
