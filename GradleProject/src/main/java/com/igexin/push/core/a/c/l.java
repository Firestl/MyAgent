package com.igexin.push.core.a.c;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.igexin.push.core.b.r;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class l implements PushMessageInterface {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3334a = com.igexin.push.config.c.f3286a;

    public static void a(String str, Context context) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Intent intentA = com.igexin.push.g.d.a(str);
            intentA.setPackage(context.getPackageName());
            intentA.addFlags(268435456);
            context.startActivity(intentA);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final boolean executeAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        String id;
        String messageId;
        String doActionId;
        r rVar = (r) baseActionBean;
        try {
            Intent intentA = com.igexin.push.g.d.a(rVar.b);
            intentA.setPackage(com.igexin.push.core.e.l.getPackageName());
            intentA.addFlags(268435456);
            if (com.igexin.push.g.c.b(intentA, com.igexin.push.core.e.l)) {
                com.igexin.push.core.e.l.startActivity(intentA);
                com.igexin.push.core.a.b.d();
                id = pushTaskBean.getTaskId();
                messageId = pushTaskBean.getMessageId();
                doActionId = rVar.getDoActionId();
            } else {
                com.igexin.c.a.c.a.a(f3334a, "execute failed, activity not exist");
                com.igexin.c.a.c.a.a(f3334a + "|execute failed, activity not exist", new Object[0]);
                com.igexin.push.core.a.b.d();
                id = pushTaskBean.getId();
                messageId = pushTaskBean.getMessageId();
                doActionId = rVar.f3366a;
            }
            com.igexin.push.core.a.b.a(id, messageId, doActionId);
            return true;
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            com.igexin.push.core.a.b.d();
            com.igexin.push.core.a.b.a(pushTaskBean.getId(), pushTaskBean.getMessageId(), rVar.f3366a);
            return true;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final BaseActionBean parseAction(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("do") || !jSONObject.has("actionid") || !jSONObject.has("type") || !jSONObject.has("uri") || !jSONObject.has("do_failed")) {
                return null;
            }
            String strOptString = jSONObject.optString("uri");
            if (TextUtils.isEmpty(strOptString)) {
                return null;
            }
            r rVar = new r();
            rVar.setType(com.igexin.push.core.b.p);
            rVar.setActionId(jSONObject.getString("actionid"));
            rVar.setDoActionId(jSONObject.getString("do"));
            rVar.b = strOptString;
            rVar.f3366a = jSONObject.optString("do_failed");
            return rVar;
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return null;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        r rVar = (r) baseActionBean;
        try {
            Intent intentA = com.igexin.push.g.d.a(rVar.b);
            intentA.setPackage(com.igexin.push.core.e.l.getPackageName());
            intentA.addFlags(268435456);
            if (com.igexin.push.g.c.b(intentA, com.igexin.push.core.e.l)) {
                return PushMessageInterface.ActionPrepareState.success;
            }
            com.igexin.c.a.c.a.a(f3334a, "execute failed, activity not exist");
            com.igexin.c.a.c.a.a(f3334a + "|execute failed, activity not exist", new Object[0]);
            com.igexin.push.core.a.b.d();
            com.igexin.push.core.a.b.a(pushTaskBean.getId(), pushTaskBean.getMessageId(), rVar.f3366a);
            return PushMessageInterface.ActionPrepareState.stop;
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            com.igexin.push.core.a.b.d();
            com.igexin.push.core.a.b.a(pushTaskBean.getId(), pushTaskBean.getMessageId(), rVar.f3366a);
            return PushMessageInterface.ActionPrepareState.stop;
        }
    }
}
