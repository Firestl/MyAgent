package supwisdom;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

/* JADX INFO: compiled from: LogHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class tu0 implements uu0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f9317a = true;
    public int b = 2;

    public boolean a() {
        return this.f9317a;
    }

    @Override // supwisdom.uu0
    public void d(String str, String str2) {
        a(2, str, str2);
    }

    @Override // supwisdom.uu0
    public void e(String str, String str2, Throwable th) {
        a(6, str, str2, th);
    }

    public boolean a(int i) {
        return i >= this.b;
    }

    public void a(int i, String str, String str2) {
        a(i, str, str2, null);
    }

    public void a(int i, String str, String str2, Throwable th) {
        if (a() && a(i)) {
            if (i == 2) {
                Log.v(str, str2, th);
                return;
            }
            if (i == 3) {
                Log.d(str, str2, th);
                return;
            }
            if (i == 4) {
                Log.i(str, str2, th);
                return;
            }
            if (i == 5) {
                Log.w(str, str2, th);
                return;
            }
            if (i == 6) {
                Log.e(str, str2, th);
            } else {
                if (i != 8) {
                    return;
                }
                if (Integer.valueOf(Build.VERSION.SDK).intValue() > 8) {
                    a(str, str2, th);
                } else {
                    Log.e(str, str2, th);
                }
            }
        }
    }

    @TargetApi(8)
    public final void a(String str, String str2, Throwable th) {
        Log.wtf(str, str2, th);
    }
}
