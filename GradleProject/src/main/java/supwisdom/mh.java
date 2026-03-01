package supwisdom;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: compiled from: WindowIdApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class mh implements nh {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WindowId f8394a;

    public mh(View view) {
        this.f8394a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof mh) && ((mh) obj).f8394a.equals(this.f8394a);
    }

    public int hashCode() {
        return this.f8394a.hashCode();
    }
}
