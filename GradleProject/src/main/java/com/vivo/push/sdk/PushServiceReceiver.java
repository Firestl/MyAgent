package com.vivo.push.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import com.vivo.push.PushClient;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.e;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.o;
import com.vivo.push.util.q;

/* JADX INFO: loaded from: classes2.dex */
public class PushServiceReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static HandlerThread f5625a;
    public static Handler b;
    public static a c = new a();

    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f5626a;
        public String b;

        public static /* synthetic */ void a(a aVar, Context context, String str) {
            aVar.f5626a = ContextDelegate.getContext(context);
            aVar.b = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            NetworkInfo networkInfoA = q.a(this.f5626a);
            if (!(networkInfoA != null ? networkInfoA.isConnectedOrConnecting() : false)) {
                o.d("PushServiceReceiver", this.f5626a.getPackageName() + ": 无网络  by " + this.b);
                o.a(this.f5626a, "触发静态广播:无网络(" + this.b + "," + this.f5626a.getPackageName() + ")");
                return;
            }
            o.d("PushServiceReceiver", this.f5626a.getPackageName() + ": 执行开始出发动作: " + this.b);
            o.a(this.f5626a, "触发静态广播(" + this.b + "," + this.f5626a.getPackageName() + ")");
            e.a().a(this.f5626a);
            if (ClientConfigManagerImpl.getInstance(this.f5626a).isCancleBroadcastReceiver()) {
                return;
            }
            PushClient.getInstance(this.f5626a).initialize();
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Context context2 = ContextDelegate.getContext(context);
        String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action) || "android.intent.action.ACTION_POWER_CONNECTED".equals(action) || "android.intent.action.ACTION_POWER_DISCONNECTED".equals(action)) {
            if (f5625a == null) {
                HandlerThread handlerThread = new HandlerThread("PushServiceReceiver");
                f5625a = handlerThread;
                handlerThread.start();
                b = new Handler(f5625a.getLooper());
            }
            o.d("PushServiceReceiver", context2.getPackageName() + ": start PushSerevice for by " + action + "  ; handler : " + b);
            a.a(c, context2, action);
            b.removeCallbacks(c);
            b.postDelayed(c, 2000L);
        }
    }
}
