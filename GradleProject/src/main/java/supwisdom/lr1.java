package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeResultEnum;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalSocketStateResult;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.OnReceivePaySocketMessageListener;

/* JADX INFO: loaded from: classes3.dex */
public class lr1 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f8310a;
    public final /* synthetic */ gr1 b;

    public lr1(gr1 gr1Var, String str) {
        this.b = gr1Var;
        this.f8310a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        OnReceivePaySocketMessageListener onReceivePaySocketMessageListener = this.b.d;
        if (onReceivePaySocketMessageListener != null) {
            ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_SOCKET_CONNET_FAILED;
            onReceivePaySocketMessageListener.onSocketStateResult(new ExternalSocketStateResult(externalCodeResultEnum.code, externalCodeResultEnum.msg + "," + this.f8310a, new ExternalSocketStateResult.DataBean(this.b.f7767e)));
        }
    }
}
