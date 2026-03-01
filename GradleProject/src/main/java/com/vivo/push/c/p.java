package com.vivo.push.c;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.model.UnvarnishedMessage;
import java.util.HashMap;

/* JADX INFO: compiled from: OnMessageReceiveTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class p extends y {
    public p(com.vivo.push.o oVar) {
        super(oVar);
    }

    @Override // com.vivo.push.l
    public final void a(com.vivo.push.o oVar) {
        com.vivo.push.b.o oVar2 = (com.vivo.push.b.o) oVar;
        com.vivo.push.e.a().a(new com.vivo.push.b.h(String.valueOf(oVar2.f())));
        if (!ClientConfigManagerImpl.getInstance(this.f5616a).isEnablePush()) {
            com.vivo.push.util.o.d("OnMessageTask", "command  " + oVar + " is ignore by disable push ");
            com.vivo.push.b.x xVar = new com.vivo.push.b.x(1020L);
            HashMap<String, String> map = new HashMap<>();
            map.put("messageID", String.valueOf(oVar2.f()));
            Context context = this.f5616a;
            String strB = com.vivo.push.util.z.b(context, context.getPackageName());
            if (!TextUtils.isEmpty(strB)) {
                map.put("remoteAppId", strB);
            }
            xVar.a(map);
            com.vivo.push.e.a().a(xVar);
            return;
        }
        if (com.vivo.push.e.a().g() && !a(com.vivo.push.util.z.d(this.f5616a), oVar2.d(), oVar2.i())) {
            com.vivo.push.b.x xVar2 = new com.vivo.push.b.x(1021L);
            HashMap<String, String> map2 = new HashMap<>();
            map2.put("messageID", String.valueOf(oVar2.f()));
            Context context2 = this.f5616a;
            String strB2 = com.vivo.push.util.z.b(context2, context2.getPackageName());
            if (!TextUtils.isEmpty(strB2)) {
                map2.put("remoteAppId", strB2);
            }
            xVar2.a(map2);
            com.vivo.push.e.a().a(xVar2);
            return;
        }
        UnvarnishedMessage unvarnishedMessageE = oVar2.e();
        if (unvarnishedMessageE == null) {
            com.vivo.push.util.o.a("OnMessageTask", " message is null");
            return;
        }
        com.vivo.push.util.o.d("OnMessageTask", "tragetType is " + unvarnishedMessageE.getTargetType() + " ; target is " + unvarnishedMessageE.getTragetContent());
        com.vivo.push.m.b(new q(this, unvarnishedMessageE));
    }
}
