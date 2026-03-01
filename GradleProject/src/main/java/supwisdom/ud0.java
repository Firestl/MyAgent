package supwisdom;

import supwisdom.xc0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ud0 implements xc0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ bd0 f9396a;

    public ud0(bd0 bd0Var) {
        this.f9396a = bd0Var;
    }

    @Override // supwisdom.xc0.a
    public final void a(boolean z) {
        this.f9396a.p.sendMessage(this.f9396a.p.obtainMessage(1, Boolean.valueOf(z)));
    }
}
