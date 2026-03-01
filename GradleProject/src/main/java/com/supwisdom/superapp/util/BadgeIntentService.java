package com.supwisdom.superapp.util;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import com.supwisdom.zueb.R;
import supwisdom.nr1;

/* JADX INFO: loaded from: classes2.dex */
public class BadgeIntentService extends IntentService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4392a;
    public NotificationManager b;

    public BadgeIntentService() {
        super("BadgeIntentService");
        this.f4392a = 0;
    }

    @TargetApi(26)
    public final void a() {
        this.b.createNotificationChannel(new NotificationChannel("me.leolin.shortcutbadger.example", "ShortcutBadger Sample", 3));
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.b = (NotificationManager) getSystemService("notification");
    }

    @Override // android.app.IntentService
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            int intExtra = intent.getIntExtra("badgeCount", 0);
            this.b.cancel(this.f4392a);
            this.f4392a++;
            Notification.Builder smallIcon = new Notification.Builder(getApplicationContext()).setContentTitle("ShortcutBadgerX").setContentText("testing").setNumber(intExtra).setSmallIcon(R.drawable.push_small);
            if (Build.VERSION.SDK_INT >= 26) {
                a();
                smallIcon.setChannelId("me.leolin.shortcutbadger.example");
            }
            Notification notificationBuild = smallIcon.build();
            nr1.a(getApplicationContext(), notificationBuild, intExtra);
            this.b.notify(this.f4392a, notificationBuild);
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
    }
}
