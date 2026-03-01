package supwisdom;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.internal.zao;
import supwisdom.bd0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class de0<T> implements pk0<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final bd0 f7327a;
    public final int b;
    public final wc0<?> c;
    public final long d;

    public de0(bd0 bd0Var, int i, wc0<?> wc0Var, long j) {
        this.f7327a = bd0Var;
        this.b = i;
        this.c = wc0Var;
        this.d = j;
    }

    @Override // supwisdom.pk0
    public final void a(qk0<T> qk0Var) {
        int iC;
        int i;
        int i2;
        int i3;
        int iC2;
        long j;
        long jCurrentTimeMillis;
        if (this.f7327a.c()) {
            boolean zF = this.d > 0;
            RootTelemetryConfiguration rootTelemetryConfigurationA = qf0.b().a();
            if (rootTelemetryConfigurationA == null) {
                iC = 5000;
                i = 0;
                i2 = 100;
            } else {
                if (!rootTelemetryConfigurationA.e()) {
                    return;
                }
                zF &= rootTelemetryConfigurationA.f();
                iC = rootTelemetryConfigurationA.c();
                int iD = rootTelemetryConfigurationA.d();
                int iG = rootTelemetryConfigurationA.g();
                bd0.a aVarA = this.f7327a.a(this.c);
                if (aVarA != null && aVarA.b().isConnected() && (aVarA.b() instanceof BaseGmsClient)) {
                    ConnectionTelemetryConfiguration connectionTelemetryConfigurationA = a(aVarA, this.b);
                    if (connectionTelemetryConfigurationA == null) {
                        return;
                    }
                    boolean z = connectionTelemetryConfigurationA.f() && this.d > 0;
                    iD = connectionTelemetryConfigurationA.c();
                    zF = z;
                }
                i = iG;
                i2 = iD;
            }
            bd0 bd0Var = this.f7327a;
            if (qk0Var.e()) {
                i3 = 0;
                iC2 = 0;
            } else {
                if (qk0Var.c()) {
                    i3 = 100;
                } else {
                    Exception excA = qk0Var.a();
                    if (excA instanceof ApiException) {
                        Status status = ((ApiException) excA).getStatus();
                        int iE = status.e();
                        ConnectionResult connectionResultC = status.c();
                        iC2 = connectionResultC == null ? -1 : connectionResultC.c();
                        i3 = iE;
                    } else {
                        i3 = 101;
                    }
                }
                iC2 = -1;
            }
            if (zF) {
                j = this.d;
                jCurrentTimeMillis = System.currentTimeMillis();
            } else {
                j = 0;
                jCurrentTimeMillis = 0;
            }
            bd0Var.a(new zao(this.b, i3, iC2, j, jCurrentTimeMillis), i, iC, i2);
        }
    }

    public static ConnectionTelemetryConfiguration a(bd0.a<?> aVar, int i) {
        int[] iArrD;
        ConnectionTelemetryConfiguration connectionTelemetryConfigurationV = ((BaseGmsClient) aVar.b()).v();
        if (connectionTelemetryConfigurationV != null) {
            boolean z = false;
            if (connectionTelemetryConfigurationV.e() && ((iArrD = connectionTelemetryConfigurationV.d()) == null || lh0.a(iArrD, i))) {
                z = true;
            }
            if (z && aVar.m() < connectionTelemetryConfigurationV.c()) {
                return connectionTelemetryConfigurationV;
            }
        }
        return null;
    }

    public static <T> de0<T> a(bd0 bd0Var, int i, wc0<?> wc0Var) {
        if (!bd0Var.c()) {
            return null;
        }
        boolean zF = true;
        RootTelemetryConfiguration rootTelemetryConfigurationA = qf0.b().a();
        if (rootTelemetryConfigurationA != null) {
            if (!rootTelemetryConfigurationA.e()) {
                return null;
            }
            zF = rootTelemetryConfigurationA.f();
            bd0.a aVarA = bd0Var.a(wc0Var);
            if (aVarA != null && aVarA.b().isConnected() && (aVarA.b() instanceof BaseGmsClient)) {
                ConnectionTelemetryConfiguration connectionTelemetryConfigurationA = a(aVarA, i);
                if (connectionTelemetryConfigurationA == null) {
                    return null;
                }
                aVarA.n();
                zF = connectionTelemetryConfigurationA.f();
            }
        }
        return new de0<>(bd0Var, i, wc0Var, zF ? System.currentTimeMillis() : 0L);
    }
}
