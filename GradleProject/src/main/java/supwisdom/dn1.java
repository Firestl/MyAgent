package supwisdom;

import android.app.Activity;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;

/* JADX INFO: compiled from: ScreenBrightUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class dn1 {
    public static int a(Activity activity) {
        try {
            return Settings.System.getInt(activity.getContentResolver(), "screen_brightness");
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static void a(Activity activity, int i) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.screenBrightness = Float.valueOf(i).floatValue() * 0.003921569f;
        Log.d("lxy", "set  lp.screenBrightness == " + attributes.screenBrightness);
        activity.getWindow().setAttributes(attributes);
    }
}
