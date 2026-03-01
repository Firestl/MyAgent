package com.igexin.push.core.a.c;

import android.content.Context;
import android.content.Intent;
import com.igexin.push.core.b.p;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class k implements PushMessageInterface {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3333a = "com.igexin.push.core.a.c.k";

    public static void a(Context context, String str) {
        try {
            Intent launchIntentForPackage = context.getApplicationContext().getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage != null) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                intent.setFlags(270532608);
                intent.setComponent(launchIntentForPackage.getComponent());
                context.startActivity(intent);
            }
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0044 A[Catch: Exception -> 0x00d2, TRY_ENTER, TryCatch #0 {Exception -> 0x00d2, blocks: (B:15:0x0044, B:17:0x005d, B:18:0x0066, B:20:0x006c, B:21:0x007b, B:22:0x007f, B:24:0x0096, B:26:0x009e, B:29:0x00aa, B:31:0x00b0, B:32:0x00c0, B:34:0x00c4), top: B:39:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007f A[Catch: Exception -> 0x00d2, TryCatch #0 {Exception -> 0x00d2, blocks: (B:15:0x0044, B:17:0x005d, B:18:0x0066, B:20:0x006c, B:21:0x007b, B:22:0x007f, B:24:0x0096, B:26:0x009e, B:29:0x00aa, B:31:0x00b0, B:32:0x00c0, B:34:0x00c4), top: B:39:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00aa A[Catch: Exception -> 0x00d2, TryCatch #0 {Exception -> 0x00d2, blocks: (B:15:0x0044, B:17:0x005d, B:18:0x0066, B:20:0x006c, B:21:0x007b, B:22:0x007f, B:24:0x0096, B:26:0x009e, B:29:0x00aa, B:31:0x00b0, B:32:0x00c0, B:34:0x00c4), top: B:39:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c0 A[Catch: Exception -> 0x00d2, TryCatch #0 {Exception -> 0x00d2, blocks: (B:15:0x0044, B:17:0x005d, B:18:0x0066, B:20:0x006c, B:21:0x007b, B:22:0x007f, B:24:0x0096, B:26:0x009e, B:29:0x00aa, B:31:0x00b0, B:32:0x00c0, B:34:0x00c4), top: B:39:0x0042 }] */
    @Override // com.igexin.push.extension.mod.PushMessageInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean executeAction(com.igexin.push.extension.mod.PushTaskBean r10, com.igexin.push.extension.mod.BaseActionBean r11) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.k.executeAction(com.igexin.push.extension.mod.PushTaskBean, com.igexin.push.extension.mod.BaseActionBean):boolean");
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public BaseActionBean parseAction(JSONObject jSONObject) {
        try {
            p pVar = new p();
            pVar.setType(com.igexin.push.core.b.q);
            pVar.setActionId(jSONObject.getString("actionid"));
            pVar.setDoActionId(jSONObject.getString("do"));
            if (jSONObject.has("appstartupid")) {
                pVar.f3363a = jSONObject.getJSONObject("appstartupid").getString("android");
            }
            if (jSONObject.has("is_autostart")) {
                pVar.d = jSONObject.getString("is_autostart");
            }
            if (jSONObject.has("appid")) {
                pVar.b = jSONObject.getString("appid");
            }
            if (jSONObject.has("noinstall_action")) {
                pVar.c = jSONObject.getString("noinstall_action");
            }
            return pVar;
        } catch (JSONException e2) {
            com.igexin.c.a.c.a.a(e2);
            return null;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        return PushMessageInterface.ActionPrepareState.success;
    }
}
