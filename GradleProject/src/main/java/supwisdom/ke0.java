package supwisdom;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.zace;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ke0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ zace f8166a;

    public ke0(zace zaceVar) {
        this.f8166a = zaceVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f8166a.g.b(new ConnectionResult(4));
    }
}
