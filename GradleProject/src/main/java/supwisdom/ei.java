package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeOnlineResult;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.GetOnlineCodeListener;
import supwisdom.ai;

/* JADX INFO: loaded from: classes.dex */
public class ei implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f7502a;
    public final /* synthetic */ String b;
    public final /* synthetic */ ai.d c;

    public ei(ai.d dVar, String str, String str2) {
        this.c = dVar;
        this.f7502a = str;
        this.b = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        GetOnlineCodeListener getOnlineCodeListener = this.c.b;
        if (getOnlineCodeListener != null) {
            getOnlineCodeListener.onGetOnlineCodeResult(new ExternalCodeOnlineResult(this.f7502a, this.b));
        }
    }
}
