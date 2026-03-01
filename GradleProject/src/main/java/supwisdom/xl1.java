package supwisdom;

import com.supwisdom.superapp.ui.activity.InformationPerfectionActivity;
import com.tencent.wework.api.IWWAPIEventHandler;
import com.tencent.wework.api.model.BaseMessage;
import com.tencent.wework.api.model.WWAuthMessage;

/* JADX INFO: compiled from: InformationPerfectionActivity.java */
/* JADX INFO: loaded from: classes2.dex */
public class xl1 implements IWWAPIEventHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ InformationPerfectionActivity f9788a;

    public xl1(InformationPerfectionActivity informationPerfectionActivity) {
        this.f9788a = informationPerfectionActivity;
    }

    @Override // com.tencent.wework.api.IWWAPIEventHandler
    public void handleResp(BaseMessage baseMessage) {
        if (baseMessage instanceof WWAuthMessage.Resp) {
            WWAuthMessage.Resp resp = (WWAuthMessage.Resp) baseMessage;
            int i = resp.errCode;
            if (i == -1) {
                this.f9788a.a("登陆取消", true);
            } else if (i == 0) {
                this.f9788a.g(resp.code);
            } else {
                this.f9788a.a("登录失败", true);
            }
        }
    }
}
