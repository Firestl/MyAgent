package supwisdom;

import com.supwisdom.superapp.ui.activity.SecureCertificationActivity;
import com.tencent.wework.api.IWWAPIEventHandler;
import com.tencent.wework.api.model.BaseMessage;
import com.tencent.wework.api.model.WWAuthMessage;

/* JADX INFO: compiled from: SecureCertificationActivity.java */
/* JADX INFO: loaded from: classes2.dex */
public class bm1 implements IWWAPIEventHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SecureCertificationActivity f7078a;

    public bm1(SecureCertificationActivity secureCertificationActivity) {
        this.f7078a = secureCertificationActivity;
    }

    @Override // com.tencent.wework.api.IWWAPIEventHandler
    public void handleResp(BaseMessage baseMessage) {
        if (baseMessage instanceof WWAuthMessage.Resp) {
            WWAuthMessage.Resp resp = (WWAuthMessage.Resp) baseMessage;
            int i = resp.errCode;
            if (i == -1) {
                this.f7078a.h("登陆取消");
            } else if (i != 0) {
                this.f7078a.h("登录失败");
            } else {
                SecureCertificationActivity secureCertificationActivity = this.f7078a;
                secureCertificationActivity.b("workweixin", secureCertificationActivity.h, resp.code);
            }
        }
    }
}
