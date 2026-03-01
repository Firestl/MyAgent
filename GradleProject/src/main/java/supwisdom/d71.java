package supwisdom;

import com.sangfor.sdk.base.SFAuthType;
import java.util.Map;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class d71 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j81 f7302a;

    /* JADX INFO: compiled from: Proguard */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d71 f7303a = new d71();
    }

    public void a(SFAuthType sFAuthType, Map<String, String> map) {
        this.f7302a.a(sFAuthType, map);
    }

    public d71() {
        if (p81.a()) {
            this.f7302a = e71.a();
        } else {
            this.f7302a = f71.c();
        }
        new a71(this.f7302a);
        new y61(this.f7302a);
        new x61(this.f7302a);
        new z61(this.f7302a);
        new c71(this.f7302a);
    }

    public static d71 a() {
        return b.f7303a;
    }
}
