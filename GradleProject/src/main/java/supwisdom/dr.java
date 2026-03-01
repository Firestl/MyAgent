package supwisdom;

import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

/* JADX INFO: loaded from: classes.dex */
public class dr implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ DataReportRequest f7372a;
    public final /* synthetic */ cr b;

    public dr(cr crVar, DataReportRequest dataReportRequest) {
        this.b = crVar;
        this.f7372a = dataReportRequest;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            DataReportResult unused = cr.f7246e = this.b.c.reportData(this.f7372a);
        } catch (Throwable th) {
            DataReportResult unused2 = cr.f7246e = new DataReportResult();
            cr.f7246e.success = false;
            cr.f7246e.resultCode = "static data rpc upload error, " + lq.a(th);
            new StringBuilder("rpc failed:").append(lq.a(th));
        }
    }
}
