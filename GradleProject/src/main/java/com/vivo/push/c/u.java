package com.vivo.push.c;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.util.NotifyAdapterUtil;
import io.dcloud.common.adapter.util.DeviceInfo;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: OnNotificationClickTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u extends y {
    public u(com.vivo.push.o oVar) {
        super(oVar);
    }

    public static Intent b(Intent intent, Map<String, String> map) {
        if (map != null && map.entrySet() != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry != null && entry.getKey() != null) {
                    intent.putExtra(entry.getKey(), entry.getValue());
                }
            }
        }
        return intent;
    }

    @Override // com.vivo.push.l
    public final void a(com.vivo.push.o oVar) {
        com.vivo.push.b.p pVar = (com.vivo.push.b.p) oVar;
        InsideNotificationItem insideNotificationItemF = pVar.f();
        if (insideNotificationItemF == null) {
            com.vivo.push.util.o.d("OnNotificationClickTask", "current notification item is null");
            return;
        }
        UPSNotificationMessage uPSNotificationMessageA = com.vivo.push.util.p.a(insideNotificationItemF);
        boolean zEquals = this.f5616a.getPackageName().equals(pVar.d());
        if (zEquals) {
            NotifyAdapterUtil.cancelNotify(this.f5616a);
        }
        if (!zEquals) {
            com.vivo.push.util.o.a("OnNotificationClickTask", "notify is " + uPSNotificationMessageA + " ; isMatch is " + zEquals);
            return;
        }
        com.vivo.push.b.x xVar = new com.vivo.push.b.x(1030L);
        HashMap<String, String> map = new HashMap<>();
        map.put("type", "2");
        map.put("messageID", String.valueOf(pVar.e()));
        map.put("platform", this.f5616a.getPackageName());
        Context context = this.f5616a;
        String strB = com.vivo.push.util.z.b(context, context.getPackageName());
        if (!TextUtils.isEmpty(strB)) {
            map.put("remoteAppId", strB);
        }
        xVar.a(map);
        com.vivo.push.e.a().a(xVar);
        com.vivo.push.util.o.d("OnNotificationClickTask", "notification is clicked by skip type[" + uPSNotificationMessageA.getSkipType() + Operators.ARRAY_END_STR);
        int skipType = uPSNotificationMessageA.getSkipType();
        boolean z = true;
        if (skipType == 1) {
            new Thread(new v(this, this.f5616a, uPSNotificationMessageA.getParams())).start();
            return;
        }
        if (skipType == 2) {
            String skipContent = uPSNotificationMessageA.getSkipContent();
            if (!skipContent.startsWith(DeviceInfo.HTTP_PROTOCOL) && !skipContent.startsWith(DeviceInfo.HTTPS_PROTOCOL)) {
                z = false;
            }
            if (!z) {
                com.vivo.push.util.o.a("OnNotificationClickTask", "url not legal");
                return;
            }
            Uri uri = Uri.parse(skipContent);
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            intent.setFlags(268435456);
            b(intent, uPSNotificationMessageA.getParams());
            try {
                this.f5616a.startActivity(intent);
                return;
            } catch (Exception unused) {
                com.vivo.push.util.o.a("OnNotificationClickTask", "startActivity error : ".concat(String.valueOf(uri)));
                return;
            }
        }
        if (skipType != 3 && skipType != 4) {
            com.vivo.push.util.o.a("OnNotificationClickTask", "illegitmacy skip type error : " + uPSNotificationMessageA.getSkipType());
            return;
        }
        String skipContent2 = uPSNotificationMessageA.getSkipContent();
        try {
            Intent uri2 = Intent.parseUri(skipContent2, 1);
            String str = uri2.getPackage();
            if (!TextUtils.isEmpty(str) && !this.f5616a.getPackageName().equals(str)) {
                com.vivo.push.util.o.a("OnNotificationClickTask", "open activity error : local pkgName is " + this.f5616a.getPackageName() + "; but remote pkgName is " + uri2.getPackage());
                return;
            }
            String packageName = uri2.getComponent() == null ? null : uri2.getComponent().getPackageName();
            if (!TextUtils.isEmpty(packageName) && !this.f5616a.getPackageName().equals(packageName)) {
                com.vivo.push.util.o.a("OnNotificationClickTask", "open activity component error : local pkgName is " + this.f5616a.getPackageName() + "; but remote pkgName is " + uri2.getPackage());
                return;
            }
            uri2.setSelector(null);
            uri2.setPackage(this.f5616a.getPackageName());
            uri2.addFlags(335544320);
            b(uri2, uPSNotificationMessageA.getParams());
            ActivityInfo activityInfoResolveActivityInfo = uri2.resolveActivityInfo(this.f5616a.getPackageManager(), 65536);
            if (activityInfoResolveActivityInfo == null || activityInfoResolveActivityInfo.exported) {
                this.f5616a.startActivity(uri2);
                return;
            }
            com.vivo.push.util.o.a("OnNotificationClickTask", "activity is not exported : " + activityInfoResolveActivityInfo.toString());
        } catch (Exception e2) {
            com.vivo.push.util.o.a("OnNotificationClickTask", "open activity error : ".concat(String.valueOf(skipContent2)), e2);
        }
    }
}
