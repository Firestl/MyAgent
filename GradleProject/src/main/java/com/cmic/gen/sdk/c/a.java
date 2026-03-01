package com.cmic.gen.sdk.c;

import android.text.TextUtils;
import com.cmic.gen.sdk.c.b.d;
import com.cmic.gen.sdk.c.b.g;
import com.cmic.gen.sdk.c.c.c;
import com.cmic.gen.sdk.e.q;
import com.lzy.okgo.model.HttpHeaders;
import io.dcloud.common.util.net.NetWork;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1693a;
    public String b;

    private c a(String str, String str2, String str3, g gVar) {
        c cVar = new c(str, gVar, str3, str2);
        if (str3.equals("GET")) {
            cVar.a("Content-Type", NetWork.CONTENT_TYPE_UPLOAD);
        }
        return cVar;
    }

    public c a(c cVar, com.cmic.gen.sdk.c.d.b bVar, com.cmic.gen.sdk.a aVar) {
        List<String> list;
        Map<String, List<String>> mapB = bVar.b();
        if (TextUtils.isEmpty(this.f1693a) && (list = mapB.get("pplocation")) != null && list.size() > 0) {
            this.f1693a = list.get(0);
        }
        q.b(aVar, String.valueOf(bVar.a()));
        List<String> list2 = mapB.get(HttpHeaders.HEAD_KEY_LOCATION);
        if (list2 == null || list2.isEmpty()) {
            list2 = mapB.get(HttpHeaders.HEAD_KEY_LOCATION.toLowerCase());
        }
        if (list2 != null && list2.size() > 0) {
            String str = list2.get(0);
            this.b = str;
            if (!TextUtils.isEmpty(str)) {
                String strB = aVar.b("operatortype", "0");
                q.a(aVar, "2".equals(strB) ? "getUnicomMobile" : "3".equals(strB) ? "getTelecomMobile" : "NONE");
            }
        }
        com.cmic.gen.sdk.e.c.b(HttpHeaders.HEAD_KEY_LOCATION, this.b);
        c cVarA = a(this.b, cVar.f(), "GET", new com.cmic.gen.sdk.c.b.c(cVar.k().a()));
        cVarA.a(cVar.h());
        return cVarA;
    }

    public String a() {
        return this.f1693a;
    }

    public c b(c cVar, com.cmic.gen.sdk.c.d.b bVar, com.cmic.gen.sdk.a aVar) {
        String strB = aVar.b("operatortype", "0");
        q.a(aVar, "2".equals(strB) ? "getNewUnicomPhoneNumberNotify" : "3".equals(strB) ? "getNewTelecomPhoneNumberNotify" : "NONE");
        q.b(aVar, String.valueOf(bVar.a()));
        d dVar = new d(cVar.k().a(), "1.0", bVar.c());
        dVar.c(aVar.b("userCapaid"));
        if (aVar.c("logintype") == 3 || aVar.b("isRisk", false)) {
            dVar.b("pre");
        } else {
            dVar.b("authz");
        }
        c cVarA = a(this.f1693a, cVar.f(), "POST", dVar);
        cVarA.a(cVar.h());
        this.f1693a = null;
        return cVarA;
    }
}
