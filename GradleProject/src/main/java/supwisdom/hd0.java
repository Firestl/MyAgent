package supwisdom;

import android.os.RemoteException;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.Feature;
import supwisdom.mc0;
import supwisdom.mc0.b;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class hd0<A extends mc0.b, L> {
    public abstract void a();

    public abstract void a(@RecentlyNonNull A a2, @RecentlyNonNull rk0<Void> rk0Var) throws RemoteException;

    @RecentlyNullable
    public abstract Feature[] b();

    public final boolean c() {
        throw null;
    }
}
