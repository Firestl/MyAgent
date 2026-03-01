package supwisdom;

import android.content.Context;

/* JADX INFO: compiled from: AppConfig.java */
/* JADX INFO: loaded from: classes2.dex */
public class sh1 {
    public static final sh1 c = new sh1();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public pr1 f9173a;
    public Context b;

    public void a(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.b = applicationContext;
        this.f9173a = new pr1(applicationContext);
    }

    public void b(String str, String str2) {
        if (str == null || "".equals(str)) {
            return;
        }
        try {
            this.f9173a.a(str.toLowerCase(), str2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String c(String str) {
        return this.f9173a.a(str.toLowerCase()) ? this.f9173a.b(str.toLowerCase(), "") : "";
    }

    public void a(String str, Boolean bool) {
        if (str == null || "".equals(str)) {
            return;
        }
        try {
            this.f9173a.a(str.toLowerCase(), bool.booleanValue());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public Integer b(String str) {
        if (this.f9173a.a(str.toLowerCase())) {
            return Integer.valueOf(this.f9173a.b(str.toLowerCase(), -1));
        }
        return -1;
    }

    public void a(String str, Integer num) {
        if (str == null || "".equals(str)) {
            return;
        }
        try {
            this.f9173a.a(str.toLowerCase(), num.intValue());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String a(String str, String str2) {
        return this.f9173a.a(str.toLowerCase()) ? this.f9173a.b(str.toLowerCase(), str2) : str2;
    }

    public Boolean a(String str) {
        if (this.f9173a.a(str.toLowerCase())) {
            return Boolean.valueOf(this.f9173a.b(str.toLowerCase(), false));
        }
        return false;
    }

    public Boolean a(String str, boolean z) {
        if (this.f9173a.a(str.toLowerCase())) {
            return Boolean.valueOf(this.f9173a.b(str.toLowerCase(), z));
        }
        return Boolean.valueOf(z);
    }
}
