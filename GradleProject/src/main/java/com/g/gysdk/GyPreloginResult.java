package com.g.gysdk;

/* JADX INFO: loaded from: classes.dex */
public class GyPreloginResult {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1944a;
    public String b;
    public String c;
    public String d;

    public String getOperator() {
        return this.d;
    }

    public String getPrivacyName() {
        return this.b;
    }

    public String getPrivacyUrl() {
        return this.c;
    }

    public boolean isValid() {
        return this.f1944a;
    }

    public void setOperator(String str) {
        this.d = str;
    }

    public void setPrivacyName(String str) {
        this.b = str;
    }

    public void setPrivacyUrl(String str) {
        this.c = str;
    }

    public void setValid(boolean z) {
        this.f1944a = z;
    }
}
