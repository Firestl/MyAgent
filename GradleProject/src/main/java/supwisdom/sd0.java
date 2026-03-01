package supwisdom;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import supwisdom.bd0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class sd0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9154a;

    public sd0(int i) {
        this.f9154a = i;
    }

    public static Status b(RemoteException remoteException) {
        return new Status(19, remoteException.getClass().getSimpleName() + ": " + remoteException.getLocalizedMessage());
    }

    public abstract void a(Status status);

    public abstract void a(Exception exc);

    public abstract void a(bd0.a<?> aVar) throws DeadObjectException;

    public abstract void a(we0 we0Var, boolean z);
}
