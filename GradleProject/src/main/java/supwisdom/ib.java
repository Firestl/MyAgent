package supwisdom;

import android.content.Context;
import android.os.Build;
import android.view.PointerIcon;

/* JADX INFO: compiled from: PointerIconCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class ib {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f7934a;

    public ib(Object obj) {
        this.f7934a = obj;
    }

    public Object a() {
        return this.f7934a;
    }

    public static ib a(Context context, int i) {
        return Build.VERSION.SDK_INT >= 24 ? new ib(PointerIcon.getSystemIcon(context, i)) : new ib(null);
    }
}
