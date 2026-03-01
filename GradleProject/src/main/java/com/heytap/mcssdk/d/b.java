package com.heytap.mcssdk.d;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.heytap.msp.push.mode.BaseMode;
import com.heytap.msp.push.mode.DataMessage;

/* JADX INFO: loaded from: classes.dex */
public class b extends c {
    @Override // com.heytap.mcssdk.d.d
    public BaseMode a(Context context, int i, Intent intent) {
        if (4103 != i && 4098 != i) {
            return null;
        }
        BaseMode baseModeA = a(intent);
        com.heytap.mcssdk.d.k().a((DataMessage) baseModeA, com.heytap.mcssdk.d.b, i);
        return baseModeA;
    }

    @Override // com.heytap.mcssdk.d.c
    public BaseMode a(Intent intent) {
        try {
            DataMessage dataMessage = new DataMessage();
            dataMessage.setMessageID(com.heytap.mcssdk.f.a.b(intent.getStringExtra("messageID")));
            dataMessage.setTaskID(com.heytap.mcssdk.f.a.b(intent.getStringExtra("taskID")));
            dataMessage.setAppPackage(com.heytap.mcssdk.f.a.b(intent.getStringExtra("appPackage")));
            dataMessage.setTitle(com.heytap.mcssdk.f.a.b(intent.getStringExtra("title")));
            dataMessage.setContent(com.heytap.mcssdk.f.a.b(intent.getStringExtra("content")));
            dataMessage.setDescription(com.heytap.mcssdk.f.a.b(intent.getStringExtra("description")));
            String strB = com.heytap.mcssdk.f.a.b(intent.getStringExtra(com.heytap.mcssdk.a.a.i));
            dataMessage.setNotifyID(TextUtils.isEmpty(strB) ? 0 : Integer.parseInt(strB));
            return dataMessage;
        } catch (Exception e2) {
            com.heytap.mcssdk.f.c.b("OnHandleIntent--" + e2.getMessage());
            return null;
        }
    }
}
