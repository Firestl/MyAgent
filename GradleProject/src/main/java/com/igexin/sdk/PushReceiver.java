package com.igexin.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.getui.gtc.base.GtcProvider;
import com.igexin.c.a.c.a;
import com.igexin.push.core.ServiceManager;
import com.igexin.push.core.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class PushReceiver extends BroadcastReceiver {
    public static final String TAG = "p-receive";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            Context applicationContext = context.getApplicationContext();
            if (intent != null && intent.getAction() != null) {
                GtcProvider.setContext(applicationContext.getApplicationContext());
                String action = intent.getAction();
                if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action) || PushConsts.ACTION_BROADCAST_USER_PRESENT.equals(action)) {
                    Context applicationContext2 = applicationContext.getApplicationContext();
                    b.d();
                    Intent intent2 = new Intent(applicationContext2, (Class<?>) b.a(applicationContext));
                    intent2.putExtra("action", action);
                    ServiceManager.getInstance().b(applicationContext, intent2);
                }
            }
        } catch (Throwable th) {
            a.a(th);
            a.a("p-receive|" + th.toString(), new Object[0]);
        }
    }
}
