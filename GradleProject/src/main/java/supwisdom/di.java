package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeOnlineResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeResultEnum;
import com.synjones.mobilegroup.libofflinecodesdk.beans.JsonOperate;
import supwisdom.ai;

/* JADX INFO: loaded from: classes.dex */
public class di implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f7344a;
    public final /* synthetic */ int b;
    public final /* synthetic */ ai.d c;

    public di(ai.d dVar, String str, int i) {
        this.c = dVar;
        this.f7344a = str;
        this.b = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.c.b != null) {
            ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_NO;
            JsonOperate.toJson(new ExternalCodeOnlineResult(externalCodeResultEnum.code, externalCodeResultEnum.msg, new ExternalCodeOnlineResult.DataBean(this.f7344a, this.b + "", System.currentTimeMillis() + "")));
            this.c.b.onGetOnlineCodeResult(new ExternalCodeOnlineResult(externalCodeResultEnum.code, externalCodeResultEnum.msg, new ExternalCodeOnlineResult.DataBean(this.f7344a, this.b + "", System.currentTimeMillis() + "")));
        }
    }
}
