package supwisdom;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class ut0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f9441a;
    public String b;
    public String c;
    public String d;

    public boolean a() {
        return ((TextUtils.isEmpty(this.d) && TextUtils.isEmpty(this.c)) || TextUtils.isEmpty(this.f9441a)) ? false : true;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.f9441a;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.b;
    }

    public String toString() {
        return this.f9441a + "|" + this.c + "|" + this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public void c(String str) {
        this.b = str;
    }

    public void a(String str) {
        this.f9441a = str;
    }
}
