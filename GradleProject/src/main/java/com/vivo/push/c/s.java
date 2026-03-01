package com.vivo.push.c;

import android.content.Context;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.vivo.push.model.InsideNotificationItem;
import java.util.HashMap;

/* JADX INFO: compiled from: OnNotificationArrivedReceiveTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ InsideNotificationItem f5597a;
    public final /* synthetic */ com.vivo.push.b.q b;
    public final /* synthetic */ r c;

    public s(r rVar, InsideNotificationItem insideNotificationItem, com.vivo.push.b.q qVar) {
        this.c = rVar;
        this.f5597a = insideNotificationItem;
        this.b = qVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        char c;
        r rVar = this.c;
        if (((y) rVar).b.onNotificationMessageArrived(rVar.f5616a, com.vivo.push.util.p.a(this.f5597a))) {
            com.vivo.push.util.o.b("OnNotificationArrivedTask", "pkg name : " + this.c.f5616a.getPackageName() + " 应用主动拦截通知");
            com.vivo.push.util.o.b(this.c.f5616a, "应用主动拦截通知，导致通知无法展示，如需打开请在onNotificationMessageArrived中返回false");
            HashMap map = new HashMap();
            map.put("messageID", String.valueOf(this.b.f()));
            String strB = com.vivo.push.util.z.b(this.c.f5616a, this.c.f5616a.getPackageName());
            if (!TextUtils.isEmpty(strB)) {
                map.put("remoteAppId", strB);
            }
            com.vivo.push.util.e.a(2120L, map);
            return;
        }
        Context context = this.c.f5616a;
        InsideNotificationItem insideNotificationItem = this.f5597a;
        long jF = this.b.f();
        r rVar2 = this.c;
        com.vivo.push.util.k kVar = new com.vivo.push.util.k(context, insideNotificationItem, jF, ((y) rVar2).b.isAllowNet(rVar2.f5616a), new t(this));
        boolean zIsShowBigPicOnMobileNet = this.f5597a.isShowBigPicOnMobileNet();
        String purePicUrl = this.f5597a.getPurePicUrl();
        if (TextUtils.isEmpty(purePicUrl)) {
            purePicUrl = this.f5597a.getCoverUrl();
        }
        if (!TextUtils.isEmpty(purePicUrl)) {
            com.vivo.push.util.o.c("OnNotificationArrivedTask", "showCode=".concat(String.valueOf(zIsShowBigPicOnMobileNet)));
            if (zIsShowBigPicOnMobileNet) {
                com.vivo.push.util.o.a(this.c.f5616a, "mobile net show");
            } else {
                com.vivo.push.util.o.a(this.c.f5616a, "mobile net unshow");
                NetworkInfo networkInfoA = com.vivo.push.util.q.a(this.c.f5616a);
                if (networkInfoA != null && networkInfoA.getState() == NetworkInfo.State.CONNECTED) {
                    int type = networkInfoA.getType();
                    c = type == 1 ? (char) 2 : type == 0 ? (char) 1 : (char) 3;
                } else {
                    c = 0;
                }
                if (c == 1) {
                    purePicUrl = null;
                    this.f5597a.clearCoverUrl();
                    this.f5597a.clearPurePicUrl();
                }
            }
        }
        kVar.execute(this.f5597a.getIconUrl(), purePicUrl);
    }
}
