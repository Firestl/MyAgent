package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeInitResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeResultEnum;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.OffInitListener;
import supwisdom.ai;

/* JADX INFO: loaded from: classes.dex */
public class hi implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ai.g f7840a;

    public hi(ai.g gVar) {
        this.f7840a = gVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        OffInitListener offInitListener = this.f7840a.b;
        if (offInitListener != null) {
            ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_NO;
            offInitListener.onInitResult(new ExternalCodeInitResult(externalCodeResultEnum.code, externalCodeResultEnum.msg));
        }
    }
}
