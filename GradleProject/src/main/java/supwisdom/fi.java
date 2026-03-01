package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.beans.CodeBarPayInfoBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodePayListResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeResultEnum;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.GetPayListListener;
import java.util.ArrayList;
import supwisdom.ai;

/* JADX INFO: loaded from: classes.dex */
public class fi implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ai.f f7608a;

    public fi(ai.f fVar) {
        this.f7608a = fVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f7608a.b != null) {
            ArrayList arrayList = new ArrayList();
            for (CodeBarPayInfoBean.DataBean dataBean : bq1.f().b()) {
                arrayList.add(new ExternalCodePayListResult.DataBean(dataBean.account, dataBean.expdate, dataBean.lostflag + "", dataBean.name, dataBean.payacc, dataBean.id + ""));
            }
            GetPayListListener getPayListListener = this.f7608a.b;
            ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_NO;
            getPayListListener.onGetPayListResult(new ExternalCodePayListResult(externalCodeResultEnum.code, externalCodeResultEnum.msg, arrayList));
        }
    }
}
