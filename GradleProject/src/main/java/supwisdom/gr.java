package supwisdom;

import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class gr {
    public static DataReportRequest a(ir irVar) {
        DataReportRequest dataReportRequest = new DataReportRequest();
        if (irVar == null) {
            return null;
        }
        dataReportRequest.os = irVar.f7974a;
        dataReportRequest.rpcVersion = irVar.g;
        dataReportRequest.bizType = "1";
        HashMap map = new HashMap();
        dataReportRequest.bizData = map;
        map.put("apdid", irVar.b);
        dataReportRequest.bizData.put("apdidToken", irVar.c);
        dataReportRequest.bizData.put("umidToken", irVar.d);
        dataReportRequest.bizData.put("dynamicKey", irVar.f7975e);
        dataReportRequest.deviceData = irVar.f;
        return dataReportRequest;
    }

    public static hr a(DataReportResult dataReportResult) {
        hr hrVar = new hr();
        if (dataReportResult == null) {
            return null;
        }
        hrVar.f7635a = dataReportResult.success;
        hrVar.b = dataReportResult.resultCode;
        Map<String, String> map = dataReportResult.resultData;
        if (map != null) {
            hrVar.c = map.get("apdid");
            hrVar.d = map.get("apdidToken");
            hrVar.g = map.get("dynamicKey");
            hrVar.h = map.get("timeInterval");
            hrVar.i = map.get("webrtcUrl");
            hrVar.j = "";
            String str = map.get("drmSwitch");
            if (lq.b(str)) {
                if (str.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str.charAt(0));
                    hrVar.f7867e = sb.toString();
                }
                if (str.length() >= 3) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str.charAt(2));
                    hrVar.f = sb2.toString();
                }
            }
            if (map.containsKey("apse_degrade")) {
                hrVar.k = map.get("apse_degrade");
            }
        }
        return hrVar;
    }
}
