package supwisdom;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class eg0 implements BaseGmsClient.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ gd0 f7492a;

    public eg0(gd0 gd0Var) {
        this.f7492a = gd0Var;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.b
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.f7492a.onConnectionFailed(connectionResult);
    }
}
