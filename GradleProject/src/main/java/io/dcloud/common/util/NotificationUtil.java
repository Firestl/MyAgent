package io.dcloud.common.util;

import android.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationUtil {
    public static boolean isNotificationChannel = false;
    public static String sChannelId = "DC_LOCAL_NEWS";
    public static String sGroupId = "DC_LOCAL_GROUP";

    public static void cancelNotification(Context context, int i) {
        ((NotificationManager) context.getSystemService("notification")).cancel(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void createCustomNotification(android.content.Context r5, java.lang.String r6, android.graphics.Bitmap r7, java.lang.String r8, java.lang.String r9, int r10, android.app.PendingIntent r11) {
        /*
            Method dump skipped, instruction units count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.NotificationUtil.createCustomNotification(android.content.Context, java.lang.String, android.graphics.Bitmap, java.lang.String, java.lang.String, int, android.app.PendingIntent):void");
    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT < 26 || isNotificationChannel) {
            return;
        }
        isNotificationChannel = true;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        notificationManager.createNotificationChannelGroup(new NotificationChannelGroup(sGroupId, "local_badge"));
        NotificationChannel notificationChannel = new NotificationChannel(sChannelId, "Information notice", 3);
        notificationChannel.enableLights(true);
        notificationChannel.setShowBadge(true);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    public static boolean isMiuiRom(Context context) {
        String property = System.getProperty("http.agent");
        return !TextUtils.isEmpty(property) && property.toLowerCase().contains("miui");
    }

    public static void showNotification(Context context, String str, String str2, PendingIntent pendingIntent, Bitmap bitmap, int i) {
        Notification notificationBuild;
        createNotificationChannel(context);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 16) {
            Notification.Builder builder = i2 >= 26 ? new Notification.Builder(context, sChannelId) : new Notification.Builder(context);
            try {
                builder.setSmallIcon(context.createPackageContext(context.getPackageName(), 2).getResources().getIdentifier("icon", R.drawable.class.getSimpleName(), context.getPackageName()));
            } catch (Exception unused) {
            }
            builder.setLargeIcon(bitmap);
            builder.setContentTitle(str);
            builder.setContentText(str2);
            builder.setDefaults(1);
            builder.setAutoCancel(true);
            builder.setContentIntent(pendingIntent);
            notificationBuild = builder.build();
        } else {
            Notification notification = new Notification();
            try {
                notification.icon = context.createPackageContext(context.getPackageName(), 2).getResources().getIdentifier("icon", R.drawable.class.getSimpleName(), context.getPackageName());
            } catch (Exception unused2) {
            }
            notification.largeIcon = bitmap;
            notification.defaults = 1;
            notification.flags = 16;
            try {
                notification.getClass().getDeclaredMethod("setLatestEventInfo", Context.class, CharSequence.class, CharSequence.class, PendingIntent.class).invoke(notification, context, str, str2, pendingIntent);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            notificationBuild = notification;
        }
        try {
            notificationManager.notify(i, notificationBuild);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public static void showNotification(Context context, String str, String str2, Intent intent, int i, int i2, int i3, boolean z) {
        PendingIntent broadcast;
        Notification notificationBuild;
        Notification.Builder builder;
        createNotificationChannel(context);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        int i4 = Build.VERSION.SDK_INT;
        int i5 = i4 >= 23 ? 1140850688 : 1073741824;
        if (z) {
            broadcast = PendingIntent.getActivity(context, i3, intent, i5);
        } else {
            broadcast = PendingIntent.getBroadcast(context, i3, intent, i5);
        }
        if (i4 >= 16) {
            if (i4 >= 26) {
                builder = new Notification.Builder(context, sChannelId);
            } else {
                builder = new Notification.Builder(context);
            }
            if (-1 != i) {
                builder.setSmallIcon(i);
            } else {
                builder.setSmallIcon(context.getApplicationInfo().icon);
            }
            if (-1 != i2) {
                builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), i2));
            } else {
                builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), context.getApplicationInfo().icon));
            }
            builder.setContentTitle(str);
            builder.setContentText(str2);
            builder.setDefaults(1);
            builder.setAutoCancel(true);
            builder.setContentIntent(broadcast);
            notificationBuild = builder.build();
        } else {
            Notification notification = new Notification();
            notification.icon = context.getApplicationInfo().icon;
            if (-1 != i) {
                notification.icon = i;
            } else {
                notification.icon = context.getApplicationInfo().icon;
            }
            if (-1 != i2) {
                notification.largeIcon = BitmapFactory.decodeResource(context.getResources(), i2);
            } else {
                notification.largeIcon = BitmapFactory.decodeResource(context.getResources(), context.getApplicationInfo().icon);
            }
            notification.defaults = 1;
            notification.flags = 16;
            try {
                notification.getClass().getDeclaredMethod("setLatestEventInfo", Context.class, CharSequence.class, CharSequence.class, PendingIntent.class).invoke(notification, context, str, str2, broadcast);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            notificationBuild = notification;
        }
        try {
            notificationManager.notify(i3, notificationBuild);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
