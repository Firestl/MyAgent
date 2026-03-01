package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodePayListResult;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.GetPayListListener;
import supwisdom.ai;

/* JADX INFO: loaded from: classes.dex */
public class gi implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f7736a;
    public final /* synthetic */ String b;
    public final /* synthetic */ ai.f c;

    public gi(ai.f fVar, String str, String str2) {
        this.c = fVar;
        this.f7736a = str;
        this.b = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        GetPayListListener getPayListListener = this.c.b;
        if (getPayListListener != null) {
            getPayListListener.onGetPayListResult(new ExternalCodePayListResult(this.f7736a, this.b));
        }
    }
}
