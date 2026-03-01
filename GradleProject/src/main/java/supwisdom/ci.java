package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeCompoundResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.JsonOperate;
import supwisdom.ai;

/* JADX INFO: loaded from: classes.dex */
public class ci implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f7214a;
    public final /* synthetic */ String b;
    public final /* synthetic */ ai.c c;

    public ci(ai.c cVar, String str, String str2) {
        this.c = cVar;
        this.f7214a = str;
        this.b = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.c.b != null) {
            JsonOperate.toJson(new ExternalCodeCompoundResult(this.f7214a, this.b));
            this.c.b.onGetOnlineAndOffline(new ExternalCodeCompoundResult(this.f7214a, this.b));
        }
    }
}
