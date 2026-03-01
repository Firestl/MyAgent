package supwisdom;

import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class qf0 {
    public static qf0 b;
    public static final RootTelemetryConfiguration c = new RootTelemetryConfiguration(0, false, false, 0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public RootTelemetryConfiguration f8912a;

    @RecentlyNonNull
    public static synchronized qf0 b() {
        if (b == null) {
            b = new qf0();
        }
        return b;
    }

    public final synchronized void a(RootTelemetryConfiguration rootTelemetryConfiguration) {
        if (rootTelemetryConfiguration == null) {
            this.f8912a = c;
            return;
        }
        if (this.f8912a == null || this.f8912a.g() < rootTelemetryConfiguration.g()) {
            this.f8912a = rootTelemetryConfiguration;
        }
    }

    @RecentlyNullable
    public final RootTelemetryConfiguration a() {
        return this.f8912a;
    }
}
