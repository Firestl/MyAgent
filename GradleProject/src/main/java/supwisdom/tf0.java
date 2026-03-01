package supwisdom;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.internal.service.zak;
import com.google.android.gms.common.internal.zaaa;
import supwisdom.kd0;
import supwisdom.mc0;
import supwisdom.oc0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class tf0 extends oc0<mc0.d.C0219d> implements yf0 {
    public static final mc0.g<uf0> i = new mc0.g<>();
    public static final mc0.a<uf0, mc0.d.C0219d> j;
    public static final mc0<mc0.d.C0219d> k;

    static {
        vf0 vf0Var = new vf0();
        j = vf0Var;
        k = new mc0<>("ClientTelemetry.API", vf0Var, i);
    }

    public tf0(Context context) {
        super(context, k, mc0.d.D1, oc0.a.c);
    }

    public static final /* synthetic */ void a(zaaa zaaaVar, uf0 uf0Var, rk0 rk0Var) throws RemoteException {
        ((zak) uf0Var.r()).zaa(zaaaVar);
        rk0Var.a((Object) null);
    }

    @Override // supwisdom.yf0
    public final qk0<Void> zaa(final zaaa zaaaVar) {
        kd0.a aVarD = kd0.d();
        aVarD.a(hi0.f7841a);
        aVarD.a(false);
        aVarD.a(new id0(zaaaVar) { // from class: supwisdom.sf0

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final zaaa f9165a;

            {
                this.f9165a = zaaaVar;
            }

            @Override // supwisdom.id0
            public final void a(Object obj, Object obj2) throws RemoteException {
                tf0.a(this.f9165a, (uf0) obj, (rk0) obj2);
            }
        });
        return a(aVarD.a());
    }
}
