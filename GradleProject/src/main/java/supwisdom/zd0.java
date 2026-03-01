package supwisdom;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import supwisdom.bd0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zd0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ConnectionResult f9992a;
    public final /* synthetic */ bd0.c b;

    public zd0(bd0.c cVar, ConnectionResult connectionResult) {
        this.b = cVar;
        this.f9992a = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        bd0.a aVar = (bd0.a) bd0.this.l.get(this.b.b);
        if (aVar == null) {
            return;
        }
        if (!this.f9992a.g()) {
            aVar.onConnectionFailed(this.f9992a);
            return;
        }
        bd0.c.a(this.b, true);
        if (this.b.f7058a.g()) {
            this.b.a();
            return;
        }
        try {
            this.b.f7058a.a(null, this.b.f7058a.a());
        } catch (SecurityException e2) {
            Log.e("GoogleApiManager", "Failed to get service from broker. ", e2);
            this.b.f7058a.a("Failed to get service from broker.");
            aVar.onConnectionFailed(new ConnectionResult(10));
        }
    }
}
