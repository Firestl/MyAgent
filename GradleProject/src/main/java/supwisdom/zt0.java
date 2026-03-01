package supwisdom;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class zt0 implements Handler.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ au0 f10029a;

    public zt0(au0 au0Var) {
        this.f10029a = au0Var;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message == null || message.what != 1001) {
            return false;
        }
        this.f10029a.a(8002003);
        return true;
    }
}
