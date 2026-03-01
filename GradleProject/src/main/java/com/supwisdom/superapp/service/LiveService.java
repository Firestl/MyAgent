package com.supwisdom.superapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/* JADX INFO: loaded from: classes2.dex */
public class LiveService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f4007a = new a(this);

    public class a extends Binder {
        public a(LiveService liveService) {
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f4007a;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }
}
