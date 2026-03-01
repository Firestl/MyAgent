package com.heytap.mcssdk;

import android.content.Context;
import android.content.Intent;
import com.heytap.mcssdk.f.f;
import com.heytap.msp.push.callback.IDataMessageCallBackService;
import com.heytap.msp.push.mode.BaseMode;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static void a(final Context context, final Intent intent, final IDataMessageCallBackService iDataMessageCallBackService) {
        if (context == null) {
            com.heytap.mcssdk.f.c.e("context is null , please check param of parseIntent()");
            return;
        }
        if (intent == null) {
            com.heytap.mcssdk.f.c.e("intent is null , please check param of parseIntent()");
        } else if (iDataMessageCallBackService == null) {
            com.heytap.mcssdk.f.c.e("callback is null , please check param of parseIntent()");
        } else {
            f.a(new Runnable() { // from class: com.heytap.mcssdk.c.1
                @Override // java.lang.Runnable
                public final void run() {
                    List<BaseMode> listA = com.heytap.mcssdk.d.c.a(context, intent);
                    if (listA == null) {
                        return;
                    }
                    for (BaseMode baseMode : listA) {
                        if (baseMode != null) {
                            for (com.heytap.mcssdk.e.c cVar : d.k().p()) {
                                if (cVar != null) {
                                    cVar.a(context, baseMode, iDataMessageCallBackService);
                                }
                            }
                        }
                    }
                }
            });
        }
    }
}
