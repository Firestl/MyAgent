package supwisdom;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class se0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j4<wc0<?>, ConnectionResult> f9156a;
    public final j4<wc0<?>, String> b;
    public final rk0<Map<wc0<?>, String>> c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9157e;

    public final Set<wc0<?>> a() {
        return this.f9156a.keySet();
    }

    public final void a(wc0<?> wc0Var, ConnectionResult connectionResult, String str) {
        this.f9156a.put(wc0Var, connectionResult);
        this.b.put(wc0Var, str);
        this.d--;
        if (!connectionResult.g()) {
            this.f9157e = true;
        }
        if (this.d == 0) {
            if (!this.f9157e) {
                this.c.a(this.b);
            } else {
                this.c.a(new AvailabilityException(this.f9156a));
            }
        }
    }
}
