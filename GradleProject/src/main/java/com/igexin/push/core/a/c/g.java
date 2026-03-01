package com.igexin.push.core.a.c;

import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class g implements PushMessageInterface {
    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final boolean executeAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        com.igexin.push.core.b.j jVar = (com.igexin.push.core.b.j) baseActionBean;
        com.igexin.push.config.e.a(jVar.b, jVar.f3350a);
        com.igexin.push.core.e.a();
        if (baseActionBean.getDoActionId().equals("")) {
            return true;
        }
        com.igexin.push.core.a.b.d();
        com.igexin.push.core.a.b.a(pushTaskBean.getTaskId(), pushTaskBean.getMessageId(), baseActionBean.getDoActionId());
        return true;
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final BaseActionBean parseAction(JSONObject jSONObject) {
        try {
            com.igexin.push.core.b.j jVar = new com.igexin.push.core.b.j();
            jVar.setType(com.igexin.push.core.b.z);
            jVar.setActionId(jSONObject.optString("actionid"));
            jVar.setDoActionId(jSONObject.optString("do"));
            jVar.f3350a = jSONObject.optBoolean("gdOther", true);
            jVar.b = jSONObject.optBoolean("gdMe", true);
            return jVar;
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return null;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        return PushMessageInterface.ActionPrepareState.success;
    }
}
