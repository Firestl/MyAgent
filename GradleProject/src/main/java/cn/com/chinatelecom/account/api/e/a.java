package cn.com.chinatelecom.account.api.e;

import android.content.Context;
import android.net.Network;
import android.os.Build;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.CtAuth;
import cn.com.chinatelecom.account.api.d.g;
import com.huawei.hms.framework.common.ContainerUtils;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1511a = "a";
    public static HashMap<String, String> b = new HashMap<>();

    public static long a(Context context) {
        return c.b(context, "key_difference_time", 0L);
    }

    public static cn.com.chinatelecom.account.api.d.d a(Context context, HttpURLConnection httpURLConnection, boolean z) {
        if (!z) {
            return null;
        }
        cn.com.chinatelecom.account.api.d.d dVar = new cn.com.chinatelecom.account.api.d.d();
        try {
            Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
            List<String> list = headerFields.get("p");
            if (list != null && list.size() > 0) {
                String str = list.get(0);
                CtAuth.info(f1511a, "request protocol : " + str);
                dVar.b = false;
            }
            List<String> list2 = headerFields.get("Set-Cookie");
            if (list2 != null && list2.size() > 0) {
                int i = 0;
                while (true) {
                    if (i >= list2.size()) {
                        break;
                    }
                    String str2 = list2.get(0);
                    if (!TextUtils.isEmpty(str2) && str2.contains("gw_auth")) {
                        dVar.f1504a = a(str2, "gw_auth");
                        break;
                    }
                    i++;
                }
            }
            List<String> list3 = headerFields.get("Log-Level");
            if (list3 != null && !list3.isEmpty()) {
                for (int i2 = 0; i2 < list3.size(); i2++) {
                    String str3 = list3.get(0);
                    if (!TextUtils.isEmpty(str3)) {
                        f.a(context, str3);
                    }
                }
            }
            List<String> list4 = headerFields.get("p-reset");
            if (list4 != null && !list4.isEmpty()) {
                String str4 = list4.get(0);
                if (!TextUtils.isEmpty(str4)) {
                    a(context, str4);
                }
            }
            List<String> list5 = headerFields.get("p-ikgx");
            if (list5 != null && !list5.isEmpty()) {
                String str5 = list5.get(0);
                if (!TextUtils.isEmpty(str5)) {
                    dVar.c = str5;
                    g.d = str5;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return dVar;
    }

    public static cn.com.chinatelecom.account.api.d.d a(HttpURLConnection httpURLConnection) {
        cn.com.chinatelecom.account.api.d.d dVar = new cn.com.chinatelecom.account.api.d.d();
        try {
            Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
            List<String> list = headerFields.get("rdt_allow");
            if (list != null && list.size() > 0) {
                dVar.d = list.get(0);
                CtAuth.info(f1511a, "request method : " + dVar.d);
            }
            List<String> list2 = headerFields.get("p-ikgx");
            if (list2 != null && !list2.isEmpty()) {
                String str = list2.get(0);
                if (!TextUtils.isEmpty(str)) {
                    dVar.c = str;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return dVar;
    }

    public static synchronized String a(int i) {
        return i == cn.com.chinatelecom.account.api.a.d ? "presdk" : "preauthIfaa";
    }

    public static String a(String str, String str2) {
        try {
            String[] strArrSplit = str.split(";");
            for (int i = 0; i < strArrSplit.length; i++) {
                if (strArrSplit[i].contains(str2)) {
                    return strArrSplit[i].split(ContainerUtils.KEY_VALUE_DELIMITER)[1];
                }
            }
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static JSONObject a(Context context, cn.com.chinatelecom.account.api.d.h hVar, String str, Network network, boolean z, String str2) {
        JSONObject jSONObject;
        if (hVar == null || (jSONObject = hVar.b) == null) {
            return j.b();
        }
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (hVar.f1510a != -1 && !TextUtils.isEmpty(str)) {
            int iOptInt = jSONObject.optInt("result");
            String strOptString = jSONObject.optString("data");
            if (!TextUtils.isEmpty(strOptString)) {
                try {
                    JSONObject jSONObject2 = new JSONObject(h.a(strOptString, str));
                    if (iOptInt == 0) {
                        jSONObject2.put("gwAuth", hVar.c);
                    }
                    if (iOptInt == -10020) {
                        jSONObject.put("taskId", str);
                    }
                    jSONObject.put("data", jSONObject2);
                } catch (Throwable th) {
                    CtAuth.warn(f1511a, "dct", th);
                    jSONObject.put("data", (Object) null);
                }
            }
            if (iOptInt != 30002 || !z) {
                if (iOptInt == -10009 || iOptInt == -30001) {
                    long jOptLong = jSONObject.optLong("timeStamp", -1L);
                    if (jOptLong == -1) {
                        b(context);
                    } else {
                        a(context, jOptLong);
                    }
                }
                return jSONObject;
            }
            JSONObject jSONObject3 = (JSONObject) jSONObject.opt("data");
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArrayOptJSONArray = jSONObject3.optJSONArray("urls");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(jSONArrayOptJSONArray.getString(i));
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return a(context, arrayList, str, network, str2);
        }
        return jSONObject;
    }

    public static JSONObject a(Context context, List<String> list, String str, Network network, String str2) {
        for (int i = 0; i < list.size(); i++) {
            try {
                String str3 = list.get(i);
                if (!TextUtils.isEmpty(str3)) {
                    if (!g.c(context) && Build.VERSION.SDK_INT < 21) {
                        cn.com.chinatelecom.account.api.d.f.a(context, str3);
                    }
                    g.a aVar = new g.a();
                    try {
                        aVar.b(str2);
                        try {
                            aVar.a(network);
                            JSONObject jSONObjectA = a(context, new cn.com.chinatelecom.account.api.d.b(context).a(str3, "", 0, aVar.a()), str, network, false, str2);
                            if (jSONObjectA != null && jSONObjectA.optInt("result") == 0) {
                                return jSONObjectA;
                            }
                        } catch (Throwable th) {
                            th = th;
                            th.printStackTrace();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return j.a(80001, cn.com.chinatelecom.account.api.a.d.a(j.b) + "- redirect 30002 ");
    }

    public static void a(Context context, long j) {
        if (j > 0) {
            c.a(context, "key_difference_time", j - System.currentTimeMillis());
        }
    }

    public static void a(Context context, String str) {
        c.a(context, "key_p_rset_v3.8.12", str);
    }

    public static void b(Context context) {
        String strA = d.a();
        g.a aVar = new g.a();
        aVar.a("reqTimestamp");
        aVar.b(strA);
        JSONObject jSONObject = new cn.com.chinatelecom.account.api.d.b(context).a(g.b(), "", 1, aVar.a()).b;
        if (jSONObject != null) {
            a(context, jSONObject.optLong("msg", -1L));
        }
    }
}
