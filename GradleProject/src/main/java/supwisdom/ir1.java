package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeResultEnum;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalSocketStateResult;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.OnReceivePaySocketMessageListener;

/* JADX INFO: loaded from: classes3.dex */
public class ir1 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ gr1 f7977a;

    public ir1(gr1 gr1Var) {
        this.f7977a = gr1Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        gr1 gr1Var = this.f7977a;
        OnReceivePaySocketMessageListener onReceivePaySocketMessageListener = gr1Var.d;
        if (onReceivePaySocketMessageListener != null) {
            onReceivePaySocketMessageListener.onSocketStateResult(new ExternalSocketStateResult(ExternalCodeResultEnum.ERROR_NO.code, "socket连接成功", new ExternalSocketStateResult.DataBean(gr1Var.f7767e)));
        }
    }
}
