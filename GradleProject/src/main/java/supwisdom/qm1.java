package supwisdom;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import com.igexin.sdk.PushManager;

/* JADX INFO: compiled from: BridgeUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class qm1 {
    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return String.valueOf(context.getPackageManager().getApplicationLabel(context.getApplicationInfo()));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void a(Context context, int i) {
        PushManager.getInstance().setBadgeNum(context, 0);
        if ("Xiaomi".equals(Build.MANUFACTURER)) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null && Build.VERSION.SDK_INT >= 26) {
                NotificationChannel notificationChannel = new NotificationChannel(context.getPackageName(), a(context), 3);
                notificationChannel.setShowBadge(true);
                notificationChannel.enableLights(false);
                notificationChannel.enableVibration(false);
                notificationChannel.setSound(null, null);
                notificationManager.createNotificationChannel(notificationChannel);
                new Notification.Builder(context, context.getPackageName()).setNumber(i).build();
                return;
            }
            return;
        }
        PushManager.getInstance().setBadgeNum(context, i);
    }
}
