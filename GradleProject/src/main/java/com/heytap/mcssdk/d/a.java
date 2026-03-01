package com.heytap.mcssdk.d;

import android.content.Context;
import android.content.Intent;
import com.heytap.msp.push.mode.BaseMode;

/* JADX INFO: loaded from: classes.dex */
public class a extends c {
    @Override // com.heytap.mcssdk.d.d
    public BaseMode a(Context context, int i, Intent intent) {
        if (4105 == i) {
            return a(intent);
        }
        return null;
    }

    @Override // com.heytap.mcssdk.d.c
    public BaseMode a(Intent intent) {
        try {
            com.heytap.mcssdk.c.a aVar = new com.heytap.mcssdk.c.a();
            aVar.a(Integer.parseInt(com.heytap.mcssdk.f.a.b(intent.getStringExtra("command"))));
            aVar.b(Integer.parseInt(com.heytap.mcssdk.f.a.b(intent.getStringExtra("code"))));
            aVar.e(com.heytap.mcssdk.f.a.b(intent.getStringExtra("content")));
            aVar.a(com.heytap.mcssdk.f.a.b(intent.getStringExtra(com.heytap.mcssdk.a.a.l)));
            aVar.b(com.heytap.mcssdk.f.a.b(intent.getStringExtra(com.heytap.mcssdk.a.a.m)));
            aVar.f(com.heytap.mcssdk.f.a.b(intent.getStringExtra("appPackage")));
            com.heytap.mcssdk.f.c.b("OnHandleIntent-message:" + aVar.toString());
            return aVar;
        } catch (Exception e2) {
            com.heytap.mcssdk.f.c.b("OnHandleIntent--" + e2.getMessage());
            return null;
        }
    }
}
