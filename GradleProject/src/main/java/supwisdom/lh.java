package supwisdom;

import android.os.IBinder;

/* JADX INFO: compiled from: WindowIdApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class lh implements nh {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final IBinder f8291a;

    public lh(IBinder iBinder) {
        this.f8291a = iBinder;
    }

    public boolean equals(Object obj) {
        return (obj instanceof lh) && ((lh) obj).f8291a.equals(this.f8291a);
    }

    public int hashCode() {
        return this.f8291a.hashCode();
    }
}
