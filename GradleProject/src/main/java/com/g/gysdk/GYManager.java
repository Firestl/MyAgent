package com.g.gysdk;

import android.content.Context;
import com.cmic.sso.sdk.AuthRegisterViewConfig;
import com.g.gysdk.a.aj;
import com.g.gysdk.a.at;
import com.g.gysdk.a.c;
import com.g.gysdk.a.d;
import com.g.gysdk.a.s;
import com.g.gysdk.cta.AuthPageListener;
import com.g.gysdk.cta.ELoginThemeConfig;
import com.igexin.push.g.o;
import com.umeng.analytics.pro.f;

/* JADX INFO: loaded from: classes.dex */
public class GYManager {
    public static final int TIMEOUT_MAX = 20000;
    public static final int TIMEOUT_MIN = 1000;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final GYManager f1936a = new GYManager();
    }

    public static int a(int i) {
        StringBuilder sb;
        String str;
        if (i < 0) {
            sb = new StringBuilder();
            sb.append("GyManager.checkTimeout:");
            sb.append(i);
            str = " < 0, change to:";
        } else {
            if (i < 1000) {
                aj.b("GyManager.checkTimeout:" + i + " too small, change to:1000");
                return 1000;
            }
            if (i <= 20000) {
                return i;
            }
            sb = new StringBuilder();
            sb.append("GyManager.checkTimeout:");
            sb.append(i);
            str = " too large, change to:";
        }
        sb.append(str);
        sb.append(20000);
        aj.b(sb.toString());
        return 20000;
    }

    private void b(int i) {
        aj.b(aj.d("GyManager.setTestMode").a("test", i).toString());
        d.f1999a = i;
    }

    public static GYManager getInstance() {
        return a.f1936a;
    }

    @Deprecated
    public void addRegisterViewConfig(String str, AuthRegisterViewConfig authRegisterViewConfig) {
        aj.b(aj.d("Deprecated GyManager.addRegisterViewConfig").a("id", str).a("viewConfig", authRegisterViewConfig).a("please use", "eAccountLogin(EloginActivityParam, int, GyCallBack) on your activity created").toString());
        com.g.gysdk.cta.b.a().a(str, authRegisterViewConfig);
    }

    @Deprecated
    public void cancelELogin() {
        aj.b(aj.d("Deprecated GyManager.cancelELogin").a("please use", "eAccountLogin(EloginActivityParam, int, GyCallBack) on your activity created").toString());
        com.g.gysdk.cta.b.a().c();
    }

    public void eAccountLogin(EloginActivityParam eloginActivityParam, int i, GyCallBack gyCallBack) {
        d.m = 3;
        d.n = eloginActivityParam == null ? 1 : 3;
        aj.a(aj.d("GyManager.eAccountLogin").a("activityParam", eloginActivityParam).a("gyCallBack", gyCallBack).toString());
        c.a(true, eloginActivityParam, a(i), new com.g.gysdk.a(gyCallBack, true));
    }

    @Deprecated
    public void eAccountLogin(ELoginThemeConfig eLoginThemeConfig, GyCallBack gyCallBack) {
        d.m = 2;
        d.n = eLoginThemeConfig == null ? 1 : 2;
        aj.b(aj.d("Deprecated GyManager.eAccountLogin").a("themeConfig", eLoginThemeConfig).a("gyCallBack", gyCallBack).a("please use", "eAccountLogin(EloginActivityParam, int, GyCallBack) on your activity created").toString());
        com.g.gysdk.cta.b.a().a(eLoginThemeConfig, new com.g.gysdk.a(gyCallBack, true));
    }

    public void ePreLogin(int i, GyCallBack gyCallBack) {
        aj.a(aj.d("GyManager.ePreLogin").a("timeout", i).a("gyCallBack", gyCallBack).toString());
        c.a(a(i), new com.g.gysdk.a(gyCallBack, true));
    }

    @Deprecated
    public void finishAuthActivity() {
        aj.b(aj.d("Deprecated GyManager.finishAuthActivity").a("please use", "eAccountLogin(EloginActivityParam, int, GyCallBack) on your activity created").toString());
        com.g.gysdk.cta.b.a().b();
    }

    public GyPreloginResult getPreLoginResult() {
        aj.a(aj.d("GyManager.getPreLoginResult").toString());
        return c.a();
    }

    public int getSimCount(Context context) {
        return at.a(context);
    }

    public void getVerifyToken(String str, int i, GyCallBack gyCallBack) {
        aj.a(aj.d("GyManager.getVerifyToken").a("phone", str).a("timeout", i).a("gyCallBack", gyCallBack).toString());
        c.a(str, a(i), new com.g.gysdk.a(gyCallBack, true));
    }

    public String getVersion() {
        return "GY-3.1.0.0";
    }

    @Deprecated
    public void init(Context context) {
        aj.b(aj.d("Deprecated GyManager.init").a(f.X, context).a("please use", "init(GyConfig)").toString());
        init(context, null);
    }

    public void init(Context context, GyCallBack gyCallBack) {
        init(GyConfig.with(context).callBack(gyCallBack).build());
    }

    public void init(GyConfig gyConfig) {
        if (gyConfig == null) {
            aj.c("GyManager.init gyConfig==null");
            return;
        }
        GyCallBack gyCallBack = gyConfig.l;
        d.l = gyCallBack == null ? 1 : 2;
        aj.b = gyConfig.i;
        s.a().a(gyConfig);
        d.a(gyConfig.h);
        aj.a("GyManager.init " + gyConfig);
        if (gyCallBack == null) {
            gyCallBack = new GyCallBack() { // from class: com.g.gysdk.GYManager.1
                @Override // com.g.gysdk.GyCallBack
                public void onFailed(GYResponse gYResponse) {
                    GyMessageReceiver.sendMessage(1, gYResponse);
                }

                @Override // com.g.gysdk.GyCallBack
                public void onSuccess(GYResponse gYResponse) {
                    GyMessageReceiver.sendMessage(1, gYResponse);
                }
            };
        }
        c.a(gyConfig.f1939a, new com.g.gysdk.a(gyCallBack, true));
    }

    public boolean isPreLoginResultValid() {
        aj.a(aj.d("GyManager.isPreLoginResultValid").toString());
        return c.a(true);
    }

    @Deprecated
    public boolean isPrivacyChecked() {
        aj.b(aj.d("Deprecated GyManager.isPrivacyChecked").a("please use", "eAccountLogin(EloginActivityParam, int, GyCallBack) on your activity created").toString());
        return com.g.gysdk.cta.b.a().j();
    }

    @Deprecated
    public void login(int i, ELoginThemeConfig eLoginThemeConfig, GyCallBack gyCallBack) {
        d.m = 1;
        d.n = eLoginThemeConfig == null ? 1 : 2;
        aj.b(aj.d("Deprecated GYManager.login").a("timeout", i).a(com.igexin.push.core.b.Y, eLoginThemeConfig).a("gyCallBack", gyCallBack).a("please contact", o.f3602a).toString());
        com.g.gysdk.cta.b.a().a(a(i), eLoginThemeConfig, new com.g.gysdk.a(gyCallBack, true));
    }

    public void releaseAll() {
        com.g.gysdk.view.c.a().c();
    }

    @Deprecated
    public void setAuthPageListener(AuthPageListener authPageListener) {
        aj.b(aj.d("Deprecated GyManager.setAuthPageListener").a("authPageListener", authPageListener).a("please use", "eAccountLogin(EloginActivityParam, int, GyCallBack) on your activity created").toString());
        com.g.gysdk.cta.b.a().a(authPageListener);
    }

    public void setChannel(String str) {
        aj.b(aj.d("GyManager.setChannel").a("channel", str).toString());
        d.a(str);
    }

    public void setDebug(boolean z) {
        aj.a(aj.d("GyManager.setDebug").a("isDebug", z).toString());
        aj.b = z;
    }

    public void setELoginDebug(boolean z) {
        aj.a(aj.d("GyManager.setELoginDebug").a("debug", z).toString());
        s.a().a(z);
    }

    public void setTestMode(boolean z, boolean z2) {
        if (z || z2) {
            aj.c(aj.d("单元测试模式，仅用于调试！\nGyManager.setTestMode").a("onlyDebugUI", z).a("notTokenUpload", z2).toString());
        }
        b((z ? 1 : 0) | (z2 ? 2 : 0));
    }

    @Deprecated
    public void stopLoading() {
        aj.b(aj.d("Deprecated GyManager.stopLoading").a("please use", "eAccountLogin(EloginActivityParam, int, GyCallBack) on your activity created").toString());
        com.g.gysdk.cta.b.a().k();
    }

    public void verifyPhoneNumber(String str, String str2, String str3, int i, GyCallBack gyCallBack) {
        aj.a(aj.d("GYManager.verifyPhoneNumber").a("accessCode", str).a("processId", str2).a("phone", str3).a("operatorType", i).a("gyCallBack", gyCallBack).toString());
        c.a(str, str2, str3, i, -1, new com.g.gysdk.a(gyCallBack, false));
    }
}
