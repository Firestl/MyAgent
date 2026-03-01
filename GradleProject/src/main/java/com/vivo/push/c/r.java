package com.vivo.push.c;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.util.NotifyAdapterUtil;
import java.util.HashMap;

/* JADX INFO: compiled from: OnNotificationArrivedReceiveTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r extends y {

    /* JADX INFO: compiled from: OnNotificationArrivedReceiveTask.java */
    public interface a {
        void a();

        void b();
    }

    public r(com.vivo.push.o oVar) {
        super(oVar);
    }

    @Override // com.vivo.push.l
    public final void a(com.vivo.push.o oVar) {
        if (oVar == null) {
            com.vivo.push.util.o.a("OnNotificationArrivedTask", "command is null");
            return;
        }
        boolean zIsEnablePush = ClientConfigManagerImpl.getInstance(this.f5616a).isEnablePush();
        com.vivo.push.b.q qVar = (com.vivo.push.b.q) oVar;
        Context context = this.f5616a;
        if (!com.vivo.push.util.s.d(context, context.getPackageName())) {
            com.vivo.push.b.x xVar = new com.vivo.push.b.x(2101L);
            HashMap<String, String> map = new HashMap<>();
            map.put("messageID", String.valueOf(qVar.f()));
            Context context2 = this.f5616a;
            String strB = com.vivo.push.util.z.b(context2, context2.getPackageName());
            if (!TextUtils.isEmpty(strB)) {
                map.put("remoteAppId", strB);
            }
            xVar.a(map);
            com.vivo.push.e.a().a(xVar);
            return;
        }
        com.vivo.push.e.a().a(new com.vivo.push.b.h(String.valueOf(qVar.f())));
        com.vivo.push.util.o.d("OnNotificationArrivedTask", "PushMessageReceiver " + this.f5616a.getPackageName() + " isEnablePush :" + zIsEnablePush);
        if (!zIsEnablePush) {
            com.vivo.push.b.x xVar2 = new com.vivo.push.b.x(1020L);
            HashMap<String, String> map2 = new HashMap<>();
            map2.put("messageID", String.valueOf(qVar.f()));
            Context context3 = this.f5616a;
            String strB2 = com.vivo.push.util.z.b(context3, context3.getPackageName());
            if (!TextUtils.isEmpty(strB2)) {
                map2.put("remoteAppId", strB2);
            }
            xVar2.a(map2);
            com.vivo.push.e.a().a(xVar2);
            return;
        }
        if (com.vivo.push.e.a().g() && !a(com.vivo.push.util.z.d(this.f5616a), qVar.e(), qVar.i())) {
            com.vivo.push.b.x xVar3 = new com.vivo.push.b.x(1021L);
            HashMap<String, String> map3 = new HashMap<>();
            map3.put("messageID", String.valueOf(qVar.f()));
            Context context4 = this.f5616a;
            String strB3 = com.vivo.push.util.z.b(context4, context4.getPackageName());
            if (!TextUtils.isEmpty(strB3)) {
                map3.put("remoteAppId", strB3);
            }
            xVar3.a(map3);
            com.vivo.push.e.a().a(xVar3);
            return;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            NotificationManager notificationManager = (NotificationManager) this.f5616a.getSystemService("notification");
            if (notificationManager != null && !notificationManager.areNotificationsEnabled()) {
                com.vivo.push.util.o.b("OnNotificationArrivedTask", "pkg name : " + this.f5616a.getPackageName() + " notify switch is false");
                com.vivo.push.util.o.b(this.f5616a, "通知开关关闭，导致通知无法展示，请到设置页打开应用通知开关");
                com.vivo.push.b.x xVar4 = new com.vivo.push.b.x(2104L);
                HashMap<String, String> map4 = new HashMap<>();
                map4.put("messageID", String.valueOf(qVar.f()));
                Context context5 = this.f5616a;
                String strB4 = com.vivo.push.util.z.b(context5, context5.getPackageName());
                if (!TextUtils.isEmpty(strB4)) {
                    map4.put("remoteAppId", strB4);
                }
                xVar4.a(map4);
                com.vivo.push.e.a().a(xVar4);
                return;
            }
            if (Build.VERSION.SDK_INT >= 26 && notificationManager != null) {
                try {
                    NotificationChannel notificationChannel = notificationManager.getNotificationChannel(NotifyAdapterUtil.PRIMARY_CHANNEL);
                    if (notificationChannel != null && notificationChannel.getImportance() == 0) {
                        com.vivo.push.util.o.b("OnNotificationArrivedTask", "pkg name : " + this.f5616a.getPackageName() + " notify channel switch is false");
                        com.vivo.push.util.o.b(this.f5616a, "通知通道开关关闭，导致通知无法展示，请到设置页打开应用通知开关");
                        HashMap map5 = new HashMap();
                        map5.put("messageID", String.valueOf(qVar.f()));
                        String strB5 = com.vivo.push.util.z.b(this.f5616a, this.f5616a.getPackageName());
                        if (!TextUtils.isEmpty(strB5)) {
                            map5.put("remoteAppId", strB5);
                        }
                        com.vivo.push.util.e.a(2121L, map5);
                        return;
                    }
                } catch (Exception unused) {
                    com.vivo.push.util.o.b("OnNotificationArrivedTask", "判断通知通道出现系统错误");
                }
            }
        }
        InsideNotificationItem insideNotificationItemD = qVar.d();
        if (insideNotificationItemD == null) {
            com.vivo.push.util.o.a("OnNotificationArrivedTask", "notify is null");
            com.vivo.push.util.o.c(this.f5616a, "通知内容为空，" + qVar.f());
            com.vivo.push.util.e.a(this.f5616a, qVar.f(), 1027L);
            return;
        }
        com.vivo.push.util.o.d("OnNotificationArrivedTask", "tragetType is " + insideNotificationItemD.getTargetType() + " ; target is " + insideNotificationItemD.getTragetContent());
        com.vivo.push.m.b(new s(this, insideNotificationItemD, qVar));
    }
}
