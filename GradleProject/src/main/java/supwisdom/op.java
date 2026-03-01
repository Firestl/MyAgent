package supwisdom;

import android.text.TextUtils;
import io.dcloud.common.util.JSUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class op {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public com.alipay.sdk.protocol.a f8691a;
    public String[] b;

    public op(String str, com.alipay.sdk.protocol.a aVar) {
        this.f8691a = aVar;
    }

    public static void a(op opVar) {
        String[] strArrB = opVar.b();
        if (strArrB.length == 3 && TextUtils.equals("tid", strArrB[0])) {
            rp rpVarB = rp.b(qp.d().a());
            if (TextUtils.isEmpty(strArrB[1]) || TextUtils.isEmpty(strArrB[2])) {
                return;
            }
            rpVarB.a(strArrB[1], strArrB[2]);
        }
    }

    public static String[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split(";");
    }

    public String[] b() {
        return this.b;
    }

    public static List<op> a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        String[] strArrB = b(jSONObject.optString("name", ""));
        for (int i = 0; i < strArrB.length; i++) {
            com.alipay.sdk.protocol.a aVarA = com.alipay.sdk.protocol.a.a(strArrB[i]);
            if (aVarA != com.alipay.sdk.protocol.a.None) {
                op opVar = new op(strArrB[i], aVarA);
                opVar.b = a(strArrB[i]);
                arrayList.add(opVar);
            }
        }
        return arrayList;
    }

    public static String[] a(String str) {
        ArrayList arrayList = new ArrayList();
        int iIndexOf = str.indexOf(40);
        int iLastIndexOf = str.lastIndexOf(41);
        if (iIndexOf == -1 || iLastIndexOf == -1 || iLastIndexOf <= iIndexOf) {
            return null;
        }
        for (String str2 : str.substring(iIndexOf + 1, iLastIndexOf).split("' *, *'", -1)) {
            arrayList.add(str2.trim().replaceAll("'", "").replaceAll(JSUtil.QUOTE, ""));
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public com.alipay.sdk.protocol.a a() {
        return this.f8691a;
    }
}
