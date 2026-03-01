package com.igexin.push.core.a.b;

import com.igexin.assist.action.MessageManger;
import com.igexin.push.d.c.n;
import com.igexin.sdk.main.FeedbackImpl;
import com.tencent.connect.common.Constants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class e extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3313a = "PushMessageAction";

    @Override // com.igexin.push.core.a.b.a
    public final boolean a(Object obj, JSONObject jSONObject) {
        try {
            n nVar = (n) obj;
            if (jSONObject.has("action") && jSONObject.getString("action").equals(com.igexin.push.core.b.E)) {
                byte[] bArr = null;
                if (nVar.g instanceof byte[]) {
                    try {
                        new String((byte[]) nVar.g, "UTF-8");
                    } catch (Exception e2) {
                        com.igexin.c.a.c.a.a(f3313a + e2.toString(), new Object[0]);
                    }
                    bArr = (byte[]) nVar.g;
                }
                String string = jSONObject.getString("taskid");
                com.igexin.c.a.c.a.a("getui receive message : %s", jSONObject);
                if (bArr == null || !com.igexin.assist.sdk.a.a().c()) {
                    com.igexin.push.core.n.a().a(jSONObject, bArr, true);
                } else {
                    com.igexin.push.core.e.d dVarA = com.igexin.push.core.e.d.a(com.igexin.push.core.e.l);
                    if (dVarA.a(string)) {
                        String str = string + "4051" + MessageManger.getInstance().getBrandCode();
                        if (!dVarA.a(str)) {
                            FeedbackImpl.getInstance().feedbackMultiBrandMessageAction(jSONObject, "1" + MessageManger.getInstance().getBrandCode());
                            dVarA.b(str);
                        }
                    } else {
                        dVarA.b(string);
                        com.igexin.push.core.n.a().a(jSONObject, bArr, true);
                        FeedbackImpl.getInstance().feedbackMultiBrandMessageAction(jSONObject, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
                    }
                }
            }
        } catch (Exception e3) {
            com.igexin.c.a.c.a.a(e3);
            com.igexin.c.a.c.a.a("PushMessageAction|" + e3.toString(), new Object[0]);
        }
        return true;
    }
}
