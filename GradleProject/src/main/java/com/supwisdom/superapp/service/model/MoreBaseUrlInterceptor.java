package com.supwisdom.superapp.service.model;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.supwisdom.superapp.WXApplication;
import java.io.IOException;
import java.util.List;
import supwisdom.bt1;
import supwisdom.dt1;
import supwisdom.fn1;
import supwisdom.vs1;
import supwisdom.ws1;

/* JADX INFO: loaded from: classes2.dex */
public class MoreBaseUrlInterceptor implements ws1 {
    @Override // supwisdom.ws1
    public dt1 intercept(ws1.a aVar) throws IOException {
        PackageInfo packageInfo;
        vs1 vs1VarE = null;
        try {
            packageInfo = WXApplication.instance.getPackageManager().getPackageInfo(WXApplication.instance.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            packageInfo = null;
        }
        String str = packageInfo.versionName;
        bt1 bt1VarRequest = aVar.request();
        vs1 vs1VarG = bt1VarRequest.g();
        String strConcat = "SWSuperApp/".concat(str).concat("(").concat(Build.MANUFACTURER).concat(Build.PRODUCT).concat(Build.BRAND);
        bt1.a aVarF = bt1VarRequest.f();
        aVarF.b("User-Agent", strConcat.concat(Build.VERSION.RELEASE).concat(")"));
        List<String> listB = bt1VarRequest.b("urlname");
        if (listB == null || listB.size() <= 0) {
            return aVar.a(bt1VarRequest);
        }
        aVarF.a("urlName");
        String str2 = listB.get(0);
        String str3 = fn1.d;
        if ("PortalBaseUrl".equals(str2)) {
            str3 = fn1.f7621e;
            vs1VarE = vs1.e(str3);
        } else if ("PersonalBaseUrl".equals(str2)) {
            str3 = fn1.f;
            vs1VarE = vs1.e(str3);
        } else if ("LoginBaseURL".equals(str2)) {
            str3 = fn1.d;
            vs1VarE = vs1.e(str3);
        } else if ("refactorServerUrl".equals(str2)) {
            str3 = fn1.h + "/";
            vs1VarE = vs1.e(fn1.h);
        }
        vs1.e(fn1.d).c();
        String strC = vs1.e(vs1VarG.toString().replace(fn1.d, str3)).c();
        vs1.a aVarI = vs1VarG.i();
        aVarI.g(vs1VarE.n());
        aVarI.c(vs1VarE.g());
        aVarI.a(vs1VarE.k());
        aVarI.a(strC);
        aVarF.a(aVarI.a());
        return aVar.a(aVarF.a());
    }
}
