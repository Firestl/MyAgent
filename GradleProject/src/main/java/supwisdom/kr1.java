package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeResultEnum;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalSocketStateResult;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.OnReceivePaySocketMessageListener;

/* JADX INFO: loaded from: classes3.dex */
public class kr1 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ gr1 f8200a;

    public kr1(gr1 gr1Var) {
        this.f8200a = gr1Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        gr1 gr1Var = this.f8200a;
        OnReceivePaySocketMessageListener onReceivePaySocketMessageListener = gr1Var.d;
        if (onReceivePaySocketMessageListener != null) {
            ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_SOCKET_CONNET_CLOSED;
            onReceivePaySocketMessageListener.onSocketStateResult(new ExternalSocketStateResult(externalCodeResultEnum.code, externalCodeResultEnum.msg, new ExternalSocketStateResult.DataBean(gr1Var.f7767e)));
        }
    }
}
