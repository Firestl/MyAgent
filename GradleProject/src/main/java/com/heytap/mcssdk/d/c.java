package com.heytap.mcssdk.d;

import android.content.Context;
import android.content.Intent;
import com.heytap.msp.push.mode.BaseMode;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class c implements d {
    public static List<BaseMode> a(Context context, Intent intent) {
        int i;
        BaseMode baseModeA;
        if (intent == null) {
            return null;
        }
        try {
            i = Integer.parseInt(com.heytap.mcssdk.f.a.b(intent.getStringExtra("type")));
        } catch (Exception e2) {
            com.heytap.mcssdk.f.c.e("MessageParser--getMessageByIntent--Exception:" + e2.getMessage());
            i = 4096;
        }
        com.heytap.mcssdk.f.c.b("MessageParser--getMessageByIntent--type:".concat(String.valueOf(i)));
        ArrayList arrayList = new ArrayList();
        for (d dVar : com.heytap.mcssdk.d.k().o()) {
            if (dVar != null && (baseModeA = dVar.a(context, i, intent)) != null) {
                arrayList.add(baseModeA);
            }
        }
        return arrayList;
    }

    public abstract BaseMode a(Intent intent);
}
