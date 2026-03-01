package supwisdom;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import supwisdom.zs1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class cp1 {
    public static HashMap<String, np1> b = new HashMap<>();
    public static String c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public zs1 f7237a;

    public cp1() {
        c = a();
    }

    public final String a() {
        return bq1.f().e();
    }

    public zs1 b() {
        if (this.f7237a == null) {
            zs1.b bVar = new zs1.b();
            bVar.a(new ep1((fp1) this));
            TimeUnit timeUnit = TimeUnit.SECONDS;
            bVar.a(20L, timeUnit);
            bVar.c(20L, timeUnit);
            bVar.a(true);
            this.f7237a = bVar.a();
        }
        return this.f7237a;
    }
}
