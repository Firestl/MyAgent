package com.supwisdom.superapp.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f4003a = false;
    public Handler b = new Handler();
    public Runnable c = new a();

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String shortClassName = ((ActivityManager) AlarmService.this.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getShortClassName();
            Toast.makeText(AlarmService.this.getApplicationContext(), "当前运行的程序为" + shortClassName, 1).show();
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (!this.f4003a) {
            this.f4003a = true;
            this.b.postDelayed(this.c, 1000L);
            stopSelf();
        }
        return 1;
    }
}
