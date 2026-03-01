package supwisdom;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class v extends u {
    public static Context b;
    public static volatile v c;

    public v(Context context) {
        super(context, "virtual.card.v8");
    }

    public static v e() {
        if (c == null) {
            synchronized (v.class) {
                if (c == null) {
                    c = new v(b);
                }
            }
        }
        return c;
    }

    public int a() {
        return this.f9343a.getInt("qrCodeColor", 0);
    }

    public int b() {
        return this.f9343a.getInt("qrCodeType", 2);
    }

    public String c() {
        return a("schoolCodeKey", "");
    }

    public boolean d() {
        return this.f9343a.getBoolean("tokenStatus", false);
    }

    public void a(boolean z) {
        this.f9343a.edit().putBoolean("keyStatus", z).apply();
    }

    public void b(int i) {
        this.f9343a.edit().putInt("qrCodeType", i).apply();
    }

    public void a(int i) {
        this.f9343a.edit().putInt("qrCodeColor", i).apply();
    }
}
