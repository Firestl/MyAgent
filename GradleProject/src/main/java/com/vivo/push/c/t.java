package com.vivo.push.c;

import android.text.TextUtils;
import com.vivo.push.c.r;
import java.util.HashMap;

/* JADX INFO: compiled from: OnNotificationArrivedReceiveTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t implements r.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ s f5598a;

    public t(s sVar) {
        this.f5598a = sVar;
    }

    @Override // com.vivo.push.c.r.a
    public final void a() {
        long jL = com.vivo.push.e.a().l();
        if (jL < 1400 && jL != 1340) {
            com.vivo.push.util.o.b("OnNotificationArrivedTask", "引擎版本太低，不支持正向展示功能，pushEngineSDKVersion：".concat(String.valueOf(jL)));
            return;
        }
        HashMap map = new HashMap();
        map.put("srt", "1");
        map.put("message_id", String.valueOf(this.f5598a.b.f()));
        String strB = com.vivo.push.util.z.b(this.f5598a.c.f5616a, this.f5598a.c.f5616a.getPackageName());
        if (!TextUtils.isEmpty(strB)) {
            map.put("app_id", strB);
        }
        map.put("type", "1");
        map.put("dtp", "1");
        com.vivo.push.util.e.a(6L, map);
    }

    @Override // com.vivo.push.c.r.a
    public final void b() {
        HashMap map = new HashMap();
        map.put("messageID", String.valueOf(this.f5598a.b.f()));
        String strB = com.vivo.push.util.z.b(this.f5598a.c.f5616a, this.f5598a.c.f5616a.getPackageName());
        if (!TextUtils.isEmpty(strB)) {
            map.put("remoteAppId", strB);
        }
        com.vivo.push.util.e.a(2122L, map);
    }
}
