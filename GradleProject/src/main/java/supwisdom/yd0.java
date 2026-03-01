package supwisdom;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class yd0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ xd0 f9877a;

    public yd0(xd0 xd0Var) {
        this.f9877a = xd0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f9877a.f9742a.b.a(String.valueOf(this.f9877a.f9742a.b.getClass().getName()).concat(" disconnecting because it was signed out."));
    }
}
