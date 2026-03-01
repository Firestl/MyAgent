package supwisdom;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.service.zaj;
import com.google.android.gms.common.internal.service.zak;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class uf0 extends mf0<zak> {
    public uf0(Context context, Looper looper, lf0 lf0Var, ad0 ad0Var, gd0 gd0Var) {
        super(context, looper, 270, lf0Var, ad0Var, gd0Var);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.service.IClientTelemetryService");
        return iInterfaceQueryLocalInterface instanceof zak ? (zak) iInterfaceQueryLocalInterface : new zaj(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, supwisdom.mc0.f
    public final int d() {
        return 203390000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] l() {
        return hi0.b;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String s() {
        return "com.google.android.gms.common.internal.service.IClientTelemetryService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String t() {
        return "com.google.android.gms.common.telemetry.service.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean w() {
        return true;
    }
}
