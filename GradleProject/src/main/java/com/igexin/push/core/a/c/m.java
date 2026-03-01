package com.igexin.push.core.a.c;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.igexin.push.core.b.s;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class m implements PushMessageInterface {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3335a = com.igexin.push.config.c.f3286a;

    /* JADX WARN: Removed duplicated region for block: B:12:0x002b A[PHI: r5 r12
  0x002b: PHI (r5v7 java.lang.String) = (r5v5 java.lang.String), (r5v8 java.lang.String) binds: [B:16:0x0061, B:11:0x0029] A[DONT_GENERATE, DONT_INLINE]
  0x002b: PHI (r12v7 java.lang.String) = (r12v6 java.lang.String), (r12v8 java.lang.String) binds: [B:16:0x0061, B:11:0x0029] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(com.igexin.push.core.b.s r11, java.lang.String r12) {
        /*
            java.lang.String r0 = r11.f3367a
            if (r0 != 0) goto L5
            return
        L5:
            int r12 = r0.indexOf(r12)
            r1 = -1
            if (r12 != r1) goto Ld
            return
        Ld:
            r2 = 0
            java.lang.String r3 = "&"
            int r4 = r0.indexOf(r3)
            java.lang.String r5 = ""
            r6 = 0
            java.lang.String r7 = "="
            if (r4 != r1) goto L36
            int r1 = r12 + (-1)
            java.lang.String r5 = r0.substring(r6, r1)
            java.lang.String r12 = r0.substring(r12)
            boolean r0 = r12.contains(r7)
            if (r0 == 0) goto L9c
        L2b:
            int r0 = r12.indexOf(r7)
            int r0 = r0 + 1
            java.lang.String r2 = r12.substring(r0)
            goto L9c
        L36:
            int r8 = r12 + (-1)
            char r9 = r0.charAt(r8)
            r10 = 63
            if (r9 != r10) goto L64
            java.lang.String r1 = r0.substring(r6, r12)
            int r3 = r4 + 1
            java.lang.String r3 = r0.substring(r3)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r1)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            java.lang.String r12 = r0.substring(r12, r4)
            boolean r0 = r12.contains(r7)
            if (r0 == 0) goto L9c
            goto L2b
        L64:
            char r4 = r0.charAt(r8)
            r9 = 38
            if (r4 != r9) goto L9c
            java.lang.String r2 = r0.substring(r6, r8)
            java.lang.String r12 = r0.substring(r12)
            int r0 = r12.indexOf(r3)
            if (r0 == r1) goto L82
            java.lang.String r5 = r12.substring(r0)
            java.lang.String r12 = r12.substring(r6, r0)
        L82:
            int r0 = r12.indexOf(r7)
            int r0 = r0 + 1
            java.lang.String r12 = r12.substring(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r2)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r2 = r12
        L9c:
            r11.f3367a = r5
            r11.d = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.m.a(com.igexin.push.core.b.s, java.lang.String):void");
    }

    public static void a(String str, Context context) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            s sVar = new s();
            sVar.f3367a = str;
            a(sVar, com.igexin.push.core.b.A);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setFlags(268435456);
            intent.setPackage(sVar.d);
            intent.setData(Uri.parse(sVar.a()));
            context.startActivity(intent);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final boolean executeAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        s sVar = (s) baseActionBean;
        a(sVar, com.igexin.push.core.b.A);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setFlags(268435456);
        intent.setPackage(sVar.d);
        intent.setData(Uri.parse(sVar.a()));
        try {
            com.igexin.push.core.e.l.startActivity(intent);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
        sVar.a();
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
            if (!jSONObject.has("url") || !jSONObject.has("do") || !jSONObject.has("actionid")) {
                return null;
            }
            String string = jSONObject.getString("url");
            if (string.equals("")) {
                return null;
            }
            s sVar = new s();
            sVar.setType(com.igexin.push.core.b.u);
            sVar.setActionId(jSONObject.getString("actionid"));
            sVar.setDoActionId(jSONObject.getString("do"));
            sVar.f3367a = string;
            if (jSONObject.has("is_withcid") && "true".equals(jSONObject.getString("is_withcid"))) {
                sVar.b = true;
            }
            if (jSONObject.has("is_withnettype") && "true".equals(jSONObject.getString("is_withnettype"))) {
                sVar.c = true;
            }
            return sVar;
        } catch (JSONException e2) {
            com.igexin.c.a.c.a.a(e2);
            return null;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        return PushMessageInterface.ActionPrepareState.success;
    }
}
