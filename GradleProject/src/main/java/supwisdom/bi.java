package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeCompoundResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeResultEnum;
import com.synjones.mobilegroup.libofflinecodesdk.beans.GetOfflineCodeResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.JsonOperate;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.GetOnlineAndOfflineListener;
import supwisdom.ai;

/* JADX INFO: loaded from: classes.dex */
public class bi implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f7071a;
    public final /* synthetic */ int b;
    public final /* synthetic */ GetOfflineCodeResult c;
    public final /* synthetic */ ai.c d;

    public bi(ai.c cVar, String str, int i, GetOfflineCodeResult getOfflineCodeResult) {
        this.d = cVar;
        this.f7071a = str;
        this.b = i;
        this.c = getOfflineCodeResult;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.d.b != null) {
            ExternalCodeResultEnum externalCodeResultEnum = ExternalCodeResultEnum.ERROR_NO;
            String str = externalCodeResultEnum.code;
            String str2 = externalCodeResultEnum.msg;
            StringBuilder sb = new StringBuilder();
            sb.append(bq1.f().a(this.f7071a + ""));
            sb.append("");
            String string = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(bq1.f().b(this.f7071a + ""));
            sb2.append("");
            String string2 = sb2.toString();
            String str3 = this.b + "";
            GetOfflineCodeResult getOfflineCodeResult = this.c;
            JsonOperate.toJson(new ExternalCodeCompoundResult(str, str2, new ExternalCodeCompoundResult.DataBean(string, string2, str3, getOfflineCodeResult.barCode, getOfflineCodeResult.qrCode)));
            GetOnlineAndOfflineListener getOnlineAndOfflineListener = this.d.b;
            String str4 = externalCodeResultEnum.code;
            String str5 = externalCodeResultEnum.msg;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(bq1.f().a(this.f7071a + ""));
            sb3.append("");
            String string3 = sb3.toString();
            StringBuilder sb4 = new StringBuilder();
            sb4.append(bq1.f().b(this.f7071a + ""));
            sb4.append("");
            String string4 = sb4.toString();
            String str6 = this.b + "";
            GetOfflineCodeResult getOfflineCodeResult2 = this.c;
            getOnlineAndOfflineListener.onGetOnlineAndOffline(new ExternalCodeCompoundResult(str4, str5, new ExternalCodeCompoundResult.DataBean(string3, string4, str6, getOfflineCodeResult2.barCode, getOfflineCodeResult2.qrCode)));
            bq1.f().c(this.f7071a + "");
        }
    }
}
