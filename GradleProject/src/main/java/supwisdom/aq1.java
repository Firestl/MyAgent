package supwisdom;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ConfigSDKBean;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class aq1 extends xp1 {
    public static aq1 d;
    public String c = "save_failLog";

    public class a extends TypeToken<List<String>> {
        public a(aq1 aq1Var) {
        }
    }

    public static String c(String str) {
        return "shaApp=&qrcode=&barcode=" + str + "&sdkType=Android&sdkVersion=" + up1.c() + "&packName=" + up1.b() + "&timeStamp=" + (System.currentTimeMillis() / 1000) + "&appId=" + yp1.e().d() + "&extendInfo=&token=" + dq1.c().b();
    }

    public static aq1 d() {
        if (d == null) {
            synchronized (dq1.class) {
                if (d == null) {
                    d = new aq1();
                }
            }
        }
        return d;
    }

    @Override // supwisdom.xp1
    public String a() {
        return "app_log_sp_name";
    }

    public void a(String str) {
        List<String> listB;
        if (str == null || (listB = b()) == null || listB.size() <= 0) {
            return;
        }
        if (listB.size() > 0 && listB.get(0).equals(str)) {
            listB.remove(0);
        }
        a(this.c, new Gson().toJson(listB));
    }

    public void b(String str) {
        if (str == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<String> listB = b();
        if (listB == null || listB.size() <= 0) {
            arrayList.add(str);
        } else {
            if (listB.contains(str)) {
                return;
            }
            arrayList.addAll(listB);
            arrayList.add(str);
        }
        a(this.c, new Gson().toJson(arrayList));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String d(String str) {
        String strA;
        try {
            if (yp1.e().c() != null && yp1.e().c().data != 0 && ((ConfigSDKBean.DataBean) yp1.e().c().data).publicKey != null && !((ConfigSDKBean.DataBean) yp1.e().c().data).publicKey.isEmpty()) {
                strA = hq1.a(str, ((ConfigSDKBean.DataBean) yp1.e().c().data).publicKey);
            } else {
                strA = hq1.a(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCxVSWnudiHPtl6z+Tjqf5vOAh6haqeC6mZoQ1GuxWDC8/J7T8as9U6Ks4GpTTkxr1G+4+3jzuVKrDk5a9BYXqwHzh8EQgy4JILL7crsQfH/FFAANVMxMMQRxGOPTD5Rzkx9BNlZ2SwoFhoxAyYcIpOAk6hcn8KCx1CZ9p3WW+owIDAQAB");
            }
            return strA;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String c() {
        return this.f9798a.getString("saved_so_offcode", "");
    }

    public static String a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append("shaApp=");
        sb.append(str.substring(str.length() - 8, str.length()));
        sb.append("&qrcode=");
        if (str2.contains("SO")) {
            str2 = pp1.b.a(qp1.a(str2));
        }
        sb.append(str2);
        sb.append("&barcode=");
        sb.append(str3);
        sb.append("&sdkType=Android&sdkVersion=");
        sb.append(up1.c());
        sb.append("&packName=");
        sb.append(up1.b());
        sb.append("&timeStamp=");
        sb.append(System.currentTimeMillis() / 1000);
        sb.append("&appId=");
        sb.append(yp1.e().d());
        sb.append("&extendInfo=&token=");
        sb.append(dq1.c().b());
        return sb.toString();
    }

    public static String b(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("shaApp=");
        sb.append(str.substring(str.length() - 8, str.length()));
        sb.append("&qrcode=");
        if (str2.contains("SO")) {
            str2 = pp1.b.a(qp1.a(str2));
        }
        sb.append(str2);
        sb.append("&barcode=&sdkType=Android&sdkVersion=");
        sb.append(up1.c());
        sb.append("&packName=");
        sb.append(up1.b());
        sb.append("&timeStamp=");
        sb.append(System.currentTimeMillis() / 1000);
        sb.append("&appId=");
        sb.append(yp1.e().d());
        sb.append("&extendInfo=&token=");
        sb.append(dq1.c().b());
        return sb.toString();
    }

    public List<String> b() {
        String string = this.f9798a.getString(this.c, null);
        if (string == null) {
            return null;
        }
        try {
            return (List) new Gson().fromJson(string, new a(this).getType());
        } catch (JsonSyntaxException unused) {
            return null;
        }
    }
}
