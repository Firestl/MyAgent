package com.igexin.push.core.a.b;

import android.content.Intent;
import android.os.Bundle;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.message.FeedbackCmdMessage;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class j extends a {
    @Override // com.igexin.push.core.a.b.a
    public final boolean a(Object obj, JSONObject jSONObject) {
        String str;
        String str2;
        String str3;
        long j;
        String str4;
        String str5;
        try {
            if (!jSONObject.has("action") || !jSONObject.getString("action").equals("sendmessage_feedback")) {
                return true;
            }
            String string = jSONObject.getString("appid");
            String string2 = jSONObject.getString("taskid");
            String string3 = jSONObject.getString("actionid");
            String string4 = jSONObject.getString("result");
            long j2 = jSONObject.getLong("timestamp");
            com.igexin.c.a.c.a.a("SendMessageFeedbackAction|appid:" + string + "|taskid:" + string2 + "|actionid:" + string3, new Object[0]);
            com.igexin.push.core.l lVarA = com.igexin.push.core.l.a();
            if (com.igexin.push.core.e.f3403a == null || !com.igexin.push.core.e.f3403a.equals(string)) {
                str = "timestamp";
                str2 = "result";
                str3 = "actionid";
                j = j2;
                str4 = string4;
                str5 = string3;
            } else {
                Bundle bundle = new Bundle();
                bundle.putInt("action", 10010);
                str = "timestamp";
                j = j2;
                str2 = "result";
                str4 = string4;
                str3 = "actionid";
                str5 = string3;
                bundle.putSerializable(PushConsts.KEY_CMD_MSG, new FeedbackCmdMessage(string2, string3, string4, j, 10006));
                lVarA.a(bundle);
            }
            Intent intentD = com.igexin.push.core.l.d();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("action", 10006);
            bundle2.putString("appid", string);
            bundle2.putString("taskid", string2);
            bundle2.putString(str3, str5);
            bundle2.putString(str2, str4);
            bundle2.putLong(str, j);
            intentD.putExtras(bundle2);
            com.igexin.push.core.e.l.sendBroadcast(intentD, com.igexin.push.core.e.ac);
            return true;
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            return true;
        }
    }
}
