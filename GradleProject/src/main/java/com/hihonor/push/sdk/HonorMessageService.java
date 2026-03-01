package com.hihonor.push.sdk;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import android.util.Log;
import com.hihonor.push.sdk.common.data.DownMsgType;
import java.lang.ref.WeakReference;
import supwisdom.gu0;
import supwisdom.iu0;
import supwisdom.ju0;
import supwisdom.lu0;
import supwisdom.ot0;
import supwisdom.pt0;
import supwisdom.qt0;
import supwisdom.tt0;
import supwisdom.yt0;

/* JADX INFO: loaded from: classes.dex */
public abstract class HonorMessageService extends Service {
    public static final /* synthetic */ int b = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Messenger f2583a = new Messenger(new a(Looper.getMainLooper(), this));

    public static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<HonorMessageService> f2584a;

        public a(Looper looper, HonorMessageService honorMessageService) {
            super(looper);
            this.f2584a = new WeakReference<>(honorMessageService);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            HonorMessageService honorMessageService = this.f2584a.get();
            if (honorMessageService == null) {
                Log.w("HonorMessageService", "service is null for release");
                return;
            }
            Log.i("HonorMessageService", "handle message for broadcast.");
            Bundle data = message.getData();
            if (data != null) {
                Intent intent = new Intent();
                intent.putExtras(data);
                int i = HonorMessageService.b;
                honorMessageService.a(intent);
            }
        }
    }

    public final void a(Intent intent) {
        try {
            if (TextUtils.equals(intent.getStringExtra("event_type"), DownMsgType.RECEIVE_TOKEN)) {
                String stringExtra = intent.getStringExtra("push_token");
                Context contextA = gu0.b.a();
                yt0 yt0Var = yt0.b;
                if (!TextUtils.equals(stringExtra, yt0Var.b(contextA))) {
                    yt0Var.a(contextA, stringExtra);
                }
                Log.i("HonorMessageService", "onNewToken");
                a(stringExtra);
            } else {
                Log.i("HonorMessageService", "parse remote data start.");
                pt0 pt0VarA = tt0.a(new ju0(intent));
                qt0 qt0Var = new qt0(this);
                pt0VarA.getClass();
                pt0VarA.a(new lu0(iu0.c.f7986a, qt0Var));
            }
            Log.i("HonorMessageService", "dispatch message end.");
        } catch (Exception e2) {
            String str = "dispatch message error. " + e2.getMessage();
        }
    }

    public void a(String str) {
    }

    public void a(ot0 ot0Var) {
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.f2583a.getBinder();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        Log.i("HonorMessageService", "handle message for service.");
        a(intent);
        return 2;
    }
}
