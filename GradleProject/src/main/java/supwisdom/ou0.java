package supwisdom;

import com.hihonor.push.sdk.internal.HonorPushErrorEnum;
import supwisdom.pu0;

/* JADX INFO: loaded from: classes.dex */
public class ou0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8713a;
    public final /* synthetic */ pu0.a b;

    public ou0(pu0.a aVar, int i) {
        this.b = aVar;
        this.f8713a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.a(HonorPushErrorEnum.fromCode(this.f8713a));
    }
}
