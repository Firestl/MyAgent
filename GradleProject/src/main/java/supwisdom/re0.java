package supwisdom;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import supwisdom.bd0;
import supwisdom.mc0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class re0<ResultT> extends pe0 {
    public final kd0<mc0.b, ResultT> b;
    public final rk0<ResultT> c;
    public final jd0 d;

    public re0(int i, kd0<mc0.b, ResultT> kd0Var, rk0<ResultT> rk0Var, jd0 jd0Var) {
        super(i);
        this.c = rk0Var;
        this.b = kd0Var;
        this.d = jd0Var;
        if (i == 2 && kd0Var.a()) {
            throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        }
    }

    @Override // supwisdom.sd0
    public final void a(bd0.a<?> aVar) throws DeadObjectException {
        try {
            this.b.a(aVar.b(), this.c);
        } catch (DeadObjectException e2) {
            throw e2;
        } catch (RemoteException e3) {
            a(sd0.b(e3));
        } catch (RuntimeException e4) {
            a(e4);
        }
    }

    @Override // supwisdom.pe0
    public final Feature[] b(bd0.a<?> aVar) {
        return this.b.b();
    }

    @Override // supwisdom.pe0
    public final boolean c(bd0.a<?> aVar) {
        return this.b.a();
    }

    @Override // supwisdom.sd0
    public final void a(Status status) {
        this.c.b(this.d.a(status));
    }

    @Override // supwisdom.sd0
    public final void a(Exception exc) {
        this.c.b(exc);
    }

    @Override // supwisdom.sd0
    public final void a(we0 we0Var, boolean z) {
        we0Var.a(this.c, z);
    }
}
