package supwisdom;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class we0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<BasePendingResult<?>, Boolean> f9615a = Collections.synchronizedMap(new WeakHashMap());
    public final Map<rk0<?>, Boolean> b = Collections.synchronizedMap(new WeakHashMap());

    public final <TResult> void a(rk0<TResult> rk0Var, boolean z) {
        this.b.put(rk0Var, Boolean.valueOf(z));
        rk0Var.a().a(new xe0(this, rk0Var));
    }

    public final void b() {
        a(false, bd0.r);
    }

    public final boolean a() {
        return (this.f9615a.isEmpty() && this.b.isEmpty()) ? false : true;
    }

    public final void a(int i, String str) {
        StringBuilder sb = new StringBuilder("The connection to Google Play services was lost");
        if (i == 1) {
            sb.append(" due to service disconnection.");
        } else if (i == 3) {
            sb.append(" due to dead object exception.");
        }
        if (str != null) {
            sb.append(" Last reason for disconnect: ");
            sb.append(str);
        }
        a(true, new Status(20, sb.toString()));
    }

    public final void a(boolean z, Status status) {
        HashMap map;
        HashMap map2;
        synchronized (this.f9615a) {
            map = new HashMap(this.f9615a);
        }
        synchronized (this.b) {
            map2 = new HashMap(this.b);
        }
        for (Map.Entry entry : map.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).b(status);
            }
        }
        for (Map.Entry entry2 : map2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((rk0) entry2.getKey()).b((Exception) new ApiException(status));
            }
        }
    }
}
