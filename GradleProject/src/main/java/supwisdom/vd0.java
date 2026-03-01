package supwisdom;

import supwisdom.bd0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class vd0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9504a;
    public final /* synthetic */ bd0.a b;

    public vd0(bd0.a aVar, int i) {
        this.b = aVar;
        this.f9504a = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.b.a(this.f9504a);
    }
}
