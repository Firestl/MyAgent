package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeInitResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.JsonOperate;
import supwisdom.ai;

/* JADX INFO: loaded from: classes.dex */
public class ii implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f7956a;
    public final /* synthetic */ String b;
    public final /* synthetic */ ai.g c;

    public ii(ai.g gVar, String str, String str2) {
        this.c = gVar;
        this.f7956a = str;
        this.b = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.c.b != null) {
            JsonOperate.toJson(new ExternalCodeInitResult(this.f7956a, this.b));
            this.c.b.onInitResult(new ExternalCodeInitResult(this.f7956a, this.b));
        }
    }
}
