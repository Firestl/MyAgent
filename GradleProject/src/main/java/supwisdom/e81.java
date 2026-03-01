package supwisdom;

import java.util.Locale;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class e81 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f7450a;
    public String b;
    public String c;
    public boolean d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f7451e;

    public e81(String str, String str2, String str3) {
        this.f7450a = str;
        this.b = str2;
        this.c = str3;
        String lowerCase = str2.toLowerCase(Locale.ROOT);
        lowerCase.hashCode();
        if (lowerCase.equals("android")) {
            this.f7451e = "android.png";
        } else {
            this.f7451e = "iphone.png";
        }
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.f7450a;
    }

    public String c() {
        return this.f7451e;
    }

    public String d() {
        return this.b;
    }

    public boolean e() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }
}
