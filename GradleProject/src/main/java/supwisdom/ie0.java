package supwisdom;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import supwisdom.bd0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ie0<T> extends pe0 {
    public final rk0<T> b;

    public ie0(int i, rk0<T> rk0Var) {
        super(i);
        this.b = rk0Var;
    }

    @Override // supwisdom.sd0
    public void a(Status status) {
        this.b.b(new ApiException(status));
    }

    public abstract void d(bd0.a<?> aVar) throws RemoteException;

    @Override // supwisdom.sd0
    public void a(Exception exc) {
        this.b.b(exc);
    }

    @Override // supwisdom.sd0
    public final void a(bd0.a<?> aVar) throws DeadObjectException {
        try {
            d(aVar);
        } catch (DeadObjectException e2) {
            a(sd0.b(e2));
            throw e2;
        } catch (RemoteException e3) {
            a(sd0.b(e3));
        } catch (RuntimeException e4) {
            a(e4);
        }
    }
}
