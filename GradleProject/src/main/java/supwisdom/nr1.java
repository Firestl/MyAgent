package supwisdom;

import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.util.LinkedList;
import java.util.List;
import me.leolin.shortcutbadger.impl.AdwHomeBadger;
import me.leolin.shortcutbadger.impl.ApexHomeBadger;
import me.leolin.shortcutbadger.impl.AsusHomeBadger;
import me.leolin.shortcutbadger.impl.DefaultBadger;
import me.leolin.shortcutbadger.impl.EverythingMeHomeBadger;
import me.leolin.shortcutbadger.impl.HuaweiHomeBadger;
import me.leolin.shortcutbadger.impl.NewHtcHomeBadger;
import me.leolin.shortcutbadger.impl.NovaHomeBadger;
import me.leolin.shortcutbadger.impl.OPPOHomeBader;
import me.leolin.shortcutbadger.impl.SamsungHomeBadger;
import me.leolin.shortcutbadger.impl.SonyHomeBadger;
import me.leolin.shortcutbadger.impl.TranssionHomeBadger;
import me.leolin.shortcutbadger.impl.VivoHomeBadger;
import me.leolin.shortcutbadger.impl.YandexLauncherBadger;
import me.leolin.shortcutbadger.impl.ZTEHomeBadger;
import me.leolin.shortcutbadger.impl.ZukHomeBadger;

/* JADX INFO: compiled from: ShortcutBadger.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nr1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List<Class<? extends Object>> f8557a = new LinkedList();

    static {
        f8557a.add(AdwHomeBadger.class);
        f8557a.add(ApexHomeBadger.class);
        f8557a.add(DefaultBadger.class);
        f8557a.add(NewHtcHomeBadger.class);
        f8557a.add(NovaHomeBadger.class);
        f8557a.add(SonyHomeBadger.class);
        f8557a.add(AsusHomeBadger.class);
        f8557a.add(HuaweiHomeBadger.class);
        f8557a.add(OPPOHomeBader.class);
        f8557a.add(SamsungHomeBadger.class);
        f8557a.add(ZukHomeBadger.class);
        f8557a.add(VivoHomeBadger.class);
        f8557a.add(ZTEHomeBadger.class);
        f8557a.add(EverythingMeHomeBadger.class);
        f8557a.add(YandexLauncherBadger.class);
        f8557a.add(TranssionHomeBadger.class);
    }

    public static void a(Context context, Notification notification, int i) {
        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            try {
                Object obj = notification.getClass().getDeclaredField("extraNotification").get(notification);
                obj.getClass().getDeclaredMethod("setMessageCount", Integer.TYPE).invoke(obj, Integer.valueOf(i));
            } catch (Exception e2) {
                if (Log.isLoggable("ShortcutBadger", 3)) {
                    Log.d("ShortcutBadger", "Unable to execute badge", e2);
                }
            }
        }
    }
}
