package supwisdom;

import com.google.android.gms.common.internal.BaseGmsClient;
import supwisdom.bd0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class xd0 implements BaseGmsClient.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ bd0.a f9742a;

    public xd0(bd0.a aVar) {
        this.f9742a = aVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.e
    public final void a() {
        bd0.this.p.post(new yd0(this));
    }
}
