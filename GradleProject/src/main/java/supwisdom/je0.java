package supwisdom;

import com.google.android.gms.common.api.internal.zace;
import com.google.android.gms.signin.internal.zak;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class je0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ zak f8051a;
    public final /* synthetic */ zace b;

    public je0(zace zaceVar, zak zakVar) {
        this.b = zaceVar;
        this.f8051a = zakVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.b.zab(this.f8051a);
    }
}
