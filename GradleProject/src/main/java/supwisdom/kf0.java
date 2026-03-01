package supwisdom;

import android.os.Looper;
import android.util.Log;
import androidx.annotation.RecentlyNonNull;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class kf0 {
    public static void a(@RecentlyNonNull Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("null reference");
        }
    }

    public static void b(@RecentlyNonNull String str) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            return;
        }
        String strValueOf = String.valueOf(Thread.currentThread());
        String strValueOf2 = String.valueOf(Looper.getMainLooper().getThread());
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 56 + String.valueOf(strValueOf2).length());
        sb.append("checkNotMainThread: current thread ");
        sb.append(strValueOf);
        sb.append(" IS the main thread ");
        sb.append(strValueOf2);
        sb.append(Operators.AND_NOT);
        Log.e("Asserts", sb.toString());
        throw new IllegalStateException(str);
    }

    public static void a(@RecentlyNonNull String str) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return;
        }
        String strValueOf = String.valueOf(Thread.currentThread());
        String strValueOf2 = String.valueOf(Looper.getMainLooper().getThread());
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 57 + String.valueOf(strValueOf2).length());
        sb.append("checkMainThread: current thread ");
        sb.append(strValueOf);
        sb.append(" IS NOT the main thread ");
        sb.append(strValueOf2);
        sb.append(Operators.AND_NOT);
        Log.e("Asserts", sb.toString());
        throw new IllegalStateException(str);
    }
}
