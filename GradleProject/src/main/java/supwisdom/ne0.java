package supwisdom;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import supwisdom.kd0;
import supwisdom.mc0;

/* JADX INFO: Add missing generic type declarations: [A, ResultT] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ne0<A, ResultT> extends kd0<A, ResultT> {
    public final /* synthetic */ kd0.a d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ne0(kd0.a aVar, Feature[] featureArr, boolean z, int i) {
        super(featureArr, z, i);
        this.d = aVar;
    }

    /* JADX WARN: Incorrect types in method signature: (TA;Lsupwisdom/rk0<TResultT;>;)V */
    @Override // supwisdom.kd0
    public final void a(mc0.b bVar, rk0 rk0Var) throws RemoteException {
        this.d.f8164a.a(bVar, rk0Var);
    }
}
