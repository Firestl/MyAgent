package supwisdom;

import android.util.Log;
import com.google.android.exoplayer2.i.q;

/* JADX INFO: compiled from: ChunkedTrackBlacklistUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class s90 {
    public static boolean a(k70 k70Var, int i, Exception exc) {
        return a(k70Var, i, exc, 60000L);
    }

    public static boolean a(k70 k70Var, int i, Exception exc, long j) {
        if (!a(exc)) {
            return false;
        }
        boolean zA = k70Var.a(i, j);
        int i2 = ((q.e) exc).responseCode;
        if (zA) {
            Log.w("ChunkedTrackBlacklist", "Blacklisted: duration=" + j + ", responseCode=" + i2 + ", format=" + k70Var.a(i));
        } else {
            Log.w("ChunkedTrackBlacklist", "Blacklisting failed (cannot blacklist last enabled track): responseCode=" + i2 + ", format=" + k70Var.a(i));
        }
        return zA;
    }

    public static boolean a(Exception exc) {
        if (!(exc instanceof q.e)) {
            return false;
        }
        int i = ((q.e) exc).responseCode;
        return i == 404 || i == 410;
    }
}
