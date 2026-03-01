package com.igexin.push.core.a.c;

import android.app.NotificationManager;
import android.text.TextUtils;
import com.igexin.push.core.b.t;
import com.igexin.push.core.d;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.HashSet;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class n implements PushMessageInterface {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3336a = com.igexin.push.core.b.f + n.class.getName();

    public static void a(String str) {
        try {
            com.igexin.c.a.c.a.a(f3336a + "|del condition taskid = " + str, new Object[0]);
            d.a.f3384a.i.a("message", new String[]{"taskid"}, new String[]{str});
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            com.igexin.c.a.c.a.b(f3336a, "del condition" + th.toString());
        }
    }

    public static void a(String str, BaseActionBean baseActionBean) {
        if (baseActionBean == null) {
            return;
        }
        com.igexin.push.core.b.l lVar = (com.igexin.push.core.b.l) baseActionBean;
        String str2 = lVar.q;
        HashSet<String> hashSet = com.igexin.push.core.e.aj.get(str2);
        Integer num = com.igexin.push.core.e.ak.get(lVar.q);
        if (hashSet != null && !hashSet.isEmpty()) {
            hashSet.remove(str);
        }
        if (TextUtils.isEmpty(str2) || num == null || hashSet == null || !hashSet.isEmpty()) {
            return;
        }
        ((NotificationManager) com.igexin.push.core.e.l.getSystemService("notification")).cancel(num.intValue());
        com.igexin.push.core.e.aj.remove(str2);
        com.igexin.push.core.e.ak.remove(str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0183 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x016b A[PHI: r4 r5
  0x016b: PHI (r4v3 boolean) = (r4v2 boolean), (r4v10 boolean) binds: [B:63:0x0175, B:57:0x0169] A[DONT_GENERATE, DONT_INLINE]
  0x016b: PHI (r5v4 android.database.Cursor) = (r5v3 android.database.Cursor), (r5v6 android.database.Cursor) binds: [B:63:0x0175, B:57:0x0169] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.igexin.push.extension.mod.PushMessageInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean executeAction(com.igexin.push.extension.mod.PushTaskBean r17, com.igexin.push.extension.mod.BaseActionBean r18) {
        /*
            Method dump skipped, instruction units count: 621
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.n.executeAction(com.igexin.push.extension.mod.PushTaskBean, com.igexin.push.extension.mod.BaseActionBean):boolean");
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public BaseActionBean parseAction(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("do") || !jSONObject.has("actionid") || !jSONObject.has("taskid")) {
                return null;
            }
            t tVar = new t();
            tVar.setType(com.igexin.push.core.b.o);
            tVar.setActionId(jSONObject.getString("actionid"));
            tVar.setDoActionId(jSONObject.getString("do"));
            tVar.f3368a = jSONObject.getString("taskid");
            tVar.b = jSONObject.optBoolean(AbsoluteConst.INSTALL_OPTIONS_FORCE);
            return tVar;
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return null;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        return PushMessageInterface.ActionPrepareState.success;
    }
}
