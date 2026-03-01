package com.vivo.push.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.vivo.push.b;
import com.vivo.push.o;
import com.vivo.push.util.s;
import java.util.List;

/* JADX INFO: compiled from: CommandBridge.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public static void a(Context context, String str, o oVar) {
        boolean zC = oVar.c();
        b bVarA = b.a(context, zC ? "com.vivo.vms.upstageservice" : "com.vivo.vms.aidlservice");
        boolean zA = bVarA.a();
        if (TextUtils.isEmpty(oVar.a())) {
            oVar.a(context.getPackageName());
        }
        if (zA && !"com.vivo.pushservice".equals(context.getPackageName())) {
            com.vivo.push.a aVar = new com.vivo.push.a(oVar.a(), str, new Bundle());
            oVar.a(aVar);
            if (bVarA.a(aVar.b())) {
                return;
            }
            com.vivo.push.util.o.b("CommandBridge", "send command error by aidl");
            com.vivo.push.util.o.c(context, "send command error by aidl");
        }
        Intent intent = new Intent("com.vivo.pushservice.action.METHOD");
        intent.setPackage(str);
        intent.setClassName(str, zC ? "com.vivo.push.sdk.service.UpstageService" : "com.vivo.push.sdk.service.PushService");
        oVar.a(intent);
        try {
            a(context, intent);
        } catch (Exception e2) {
            com.vivo.push.util.o.a("CommandBridge", "CommandBridge startService exception: ", e2);
        }
    }

    public static void a(Context context, Intent intent) throws Exception {
        if (context != null) {
            try {
                context.startService(intent);
                return;
            } catch (Exception e2) {
                com.vivo.push.util.o.a("CommandBridge", "start service error", e2);
                intent.setComponent(null);
                context.sendBroadcast(intent);
                return;
            }
        }
        com.vivo.push.util.o.d("CommandBridge", "enter startService context is null");
        throw new Exception("context is null");
    }

    public static void a(Context context, o oVar, String str) {
        try {
            boolean zD = s.d(context, str);
            String str2 = zD ? "com.vivo.pushservice.action.RECEIVE" : "com.vivo.pushclient.action.RECEIVE";
            if (!TextUtils.isEmpty(str)) {
                if (zD || a(context, str2, str)) {
                    if (TextUtils.isEmpty(oVar.a())) {
                        oVar.a(context.getPackageName());
                    }
                    Intent intent = new Intent();
                    intent.setFlags(1048576);
                    if (!TextUtils.isEmpty(str2)) {
                        intent.setAction(str2);
                    }
                    intent.setPackage(str);
                    intent.setClassName(str, zD ? "com.vivo.push.sdk.service.CommandService" : "com.vivo.push.sdk.service.CommandClientService");
                    intent.putExtra("security_avoid_pull", com.vivo.push.util.a.a(context).a("com.vivo.pushservice"));
                    oVar.b(intent);
                    intent.putExtra("command_type", "reflect_receiver");
                    a(context, intent);
                    return;
                }
                return;
            }
            com.vivo.push.util.o.c(context, "消息接受者包名为空！");
            throw new Exception("消息接受者包名为空！");
        } catch (Exception e2) {
            com.vivo.push.util.o.a("CommandBridge", "CommandBridge sendCommandToClient exception", e2);
        }
    }

    public static boolean a(Context context, String str, String str2) {
        Intent intent = new Intent(str);
        intent.setPackage(str2);
        try {
            List<ResolveInfo> listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 576);
            if (listQueryBroadcastReceivers != null && listQueryBroadcastReceivers.size() > 0) {
                return true;
            }
            com.vivo.push.util.o.b("CommandBridge", "action check error：action>>" + str + ";pkgname>>" + str2);
            return false;
        } catch (Exception unused) {
            com.vivo.push.util.o.b("CommandBridge", "queryBroadcastReceivers error");
            return false;
        }
    }
}
