package com.newcapec.virtualcard;

import a.a.a.c.c;
import android.content.Context;
import com.newcapec.virtualcard.activity.MainActivity;
import com.newcapec.virtualcard.activity.PasswordActivity;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import supwisdom.b0;
import supwisdom.d0;
import supwisdom.g;
import supwisdom.i;
import supwisdom.m;
import supwisdom.n;
import supwisdom.p;
import supwisdom.r;
import supwisdom.v;

/* JADX INFO: loaded from: classes2.dex */
public class VirtualCard {
    public static boolean isT6;

    public static class a extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3850a;

        public a(Context context) {
            this.f3850a = context;
        }

        @Override // supwisdom.n
        public void a() {
            PasswordActivity.a(this.f3850a);
        }

        @Override // supwisdom.n
        public void a(String str, String str2) {
            b0.a(this.f3850a, g.a(str, str2));
        }

        @Override // supwisdom.n
        public void b() {
            MainActivity.a(this.f3850a, true);
        }

        @Override // supwisdom.n
        public void d() {
            b0.a(this.f3850a, "授权过期，请重新激活");
            PasswordActivity.a(this.f3850a);
        }
    }

    static {
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            System.out.println("运行环境没有BouncyCastleProvider");
            Security.addProvider(new BouncyCastleProvider());
        }
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) != null) {
            double version = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME).getVersion();
            System.out.println("原有version=" + version);
        }
        isT6 = false;
    }

    public static void clearUserData() {
        c.m().a();
        v.e().b(-1);
        v.e().a(0);
    }

    public static void init(Context context, boolean z) {
        b0.f6999a = context;
        v.b = context;
        c.b = context;
        b0.b = context;
        i.a(context);
        d0.f7277a = z;
        int i = R.string.is_t6;
        isT6 = Boolean.valueOf(i < 0 ? false : "true".equals(b0.b.getString(i))).booleanValue();
    }

    public static void open(Context context, String str, String str2, String str3) {
        v.e().b("schoolCodeKey", str);
        v.e().f9343a.edit().putBoolean("tokenStatus", false).apply();
        v.e().a(false);
        m pVar = isT6 ? new p() : new r();
        pVar.a(new a(context));
        pVar.a(str, str2, str3);
    }
}
