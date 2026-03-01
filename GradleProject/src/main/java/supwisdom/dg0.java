package supwisdom;

import android.os.Bundle;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class dg0 implements BaseGmsClient.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ad0 f7336a;

    public dg0(ad0 ad0Var) {
        this.f7336a = ad0Var;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.a
    public final void onConnected(Bundle bundle) {
        this.f7336a.onConnected(bundle);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.a
    public final void onConnectionSuspended(int i) {
        this.f7336a.onConnectionSuspended(i);
    }
}
