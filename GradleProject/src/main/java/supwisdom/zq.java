package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public final class zq implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ yq f10021a;

    public zq(yq yqVar) {
        this.f10021a = yqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f10021a.b();
        } catch (Exception e2) {
            ar.a(e2);
        }
    }
}
