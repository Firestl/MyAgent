package supwisdom;

import android.content.Context;
import com.alipay.tscenter.biz.rpc.deviceFp.BugTrackMessageService;
import com.alipay.tscenter.biz.rpc.report.general.DataReportService;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class cr implements br {
    public static cr d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static DataReportResult f7246e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public com.alipay.android.phone.mrpc.core.w f7247a;
    public BugTrackMessageService b;
    public DataReportService c;

    public cr(Context context, String str) {
        this.f7247a = null;
        this.b = null;
        this.c = null;
        com.alipay.android.phone.mrpc.core.aa aaVar = new com.alipay.android.phone.mrpc.core.aa();
        aaVar.a(str);
        com.alipay.android.phone.mrpc.core.h hVar = new com.alipay.android.phone.mrpc.core.h(context);
        this.f7247a = hVar;
        this.b = (BugTrackMessageService) hVar.a(BugTrackMessageService.class, aaVar);
        this.c = (DataReportService) this.f7247a.a(DataReportService.class, aaVar);
    }

    public static synchronized cr a(Context context, String str) {
        if (d == null) {
            d = new cr(context, str);
        }
        return d;
    }

    @Override // supwisdom.br
    public DataReportResult a(DataReportRequest dataReportRequest) throws InterruptedException {
        if (dataReportRequest == null) {
            return null;
        }
        if (this.c != null) {
            f7246e = null;
            new Thread(new dr(this, dataReportRequest)).start();
            for (int i = 300000; f7246e == null && i >= 0; i -= 50) {
                Thread.sleep(50L);
            }
        }
        return f7246e;
    }

    @Override // supwisdom.br
    public boolean a(String str) {
        BugTrackMessageService bugTrackMessageService;
        if (lq.a(str) || (bugTrackMessageService = this.b) == null) {
            return false;
        }
        String strLogCollect = null;
        try {
            strLogCollect = bugTrackMessageService.logCollect(lq.f(str));
        } catch (Throwable unused) {
        }
        if (lq.a(strLogCollect)) {
            return false;
        }
        return ((Boolean) new JSONObject(strLogCollect).get("success")).booleanValue();
    }
}
