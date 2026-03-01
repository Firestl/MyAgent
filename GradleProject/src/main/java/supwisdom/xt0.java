package supwisdom;

import android.os.Looper;
import android.util.Log;
import com.hihonor.push.framework.aidl.IPushInvoke;
import com.hihonor.push.sdk.internal.HonorPushErrorEnum;
import java.util.concurrent.atomic.AtomicInteger;
import supwisdom.pu0;
import supwisdom.rt0;

/* JADX INFO: loaded from: classes.dex */
public class xt0 implements rt0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicInteger f9809a = new AtomicInteger(1);
    public volatile IPushInvoke b;
    public final rt0.a c;
    public au0 d;

    public xt0(rt0.a aVar) {
        this.c = aVar;
    }

    public boolean a() {
        return this.f9809a.get() == 3 || this.f9809a.get() == 4;
    }

    public final void a(int i) {
        Log.i("PushConnectionClient", "notifyFailed result: " + i);
        rt0.a aVar = this.c;
        if (aVar != null) {
            pu0.a aVar2 = (pu0.a) aVar;
            aVar2.getClass();
            if (Looper.myLooper() == pu0.this.f8830a.getLooper()) {
                aVar2.a(HonorPushErrorEnum.fromCode(i));
            } else {
                pu0.this.f8830a.post(new ou0(aVar2, i));
            }
        }
    }
}
