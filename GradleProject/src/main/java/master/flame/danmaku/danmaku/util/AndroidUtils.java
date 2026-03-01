package master.flame.danmaku.danmaku.util;

import android.app.ActivityManager;
import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public class AndroidUtils {
    public static int getMemoryClass(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getMemoryClass();
    }
}
