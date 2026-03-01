package com.cmic.gen.sdk.c.b;

import android.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f1709a;
    public byte[] b;
    public String c;
    public byte[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1710e;
    public boolean f = false;

    @Override // com.cmic.gen.sdk.c.b.g
    public String a() {
        return this.f1709a.a();
    }

    @Override // com.cmic.gen.sdk.c.b.g
    public String a(String str) {
        return null;
    }

    public void a(a aVar) {
        this.f1709a = aVar;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public void a(byte[] bArr) {
        this.b = bArr;
    }

    @Override // com.cmic.gen.sdk.c.b.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        if (this.f) {
            try {
                jSONObject.put("encrypted", this.c);
                jSONObject.put("encryptedIV", Base64.encodeToString(this.d, 0));
                jSONObject.put("reqdata", com.cmic.gen.sdk.e.a.a(this.b, this.f1709a.toString(), this.d));
                jSONObject.put("securityreinforce", this.f1710e);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject;
    }

    public void b(String str) {
        this.f1710e = str;
    }

    public void b(byte[] bArr) {
        this.d = bArr;
    }

    public a c() {
        return this.f1709a;
    }

    public void c(String str) {
        this.c = str;
    }
}
