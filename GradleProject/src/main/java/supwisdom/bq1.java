package supwisdom;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.synjones.mobilegroup.libofflinecodesdk.beans.BarcodeParBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.CodeBarPayInfoBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.JsonOperate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class bq1 extends xp1 {
    public static bq1 c;

    public class a extends TypeToken<Map<String, Integer>> {
        public a(bq1 bq1Var) {
        }
    }

    public class b extends TypeToken<Map<String, Integer>> {
        public b(bq1 bq1Var) {
        }
    }

    public class c extends TypeToken<Map<String, Integer>> {
        public c(bq1 bq1Var) {
        }
    }

    public class d extends TypeToken<Map<String, Integer>> {
        public d(bq1 bq1Var) {
        }
    }

    public static bq1 f() {
        if (c == null) {
            synchronized (bq1.class) {
                if (c == null) {
                    c = new bq1();
                }
            }
        }
        return c;
    }

    @Override // supwisdom.xp1
    public String a() {
        return "payment_data_manger_sp_name";
    }

    public void a(String str, int i) {
        Map map = (Map) JsonOperate.fromJson(this.f9798a.getString("maxOfflineCountMap", ""), new a(this).getType());
        if (map == null) {
            map = new HashMap();
        }
        map.put(str, Integer.valueOf(i));
        a("maxOfflineCountMap", JsonOperate.toJson(map));
    }

    public int b(String str) {
        Map map = (Map) JsonOperate.fromJson(this.f9798a.getString("currentOfflineCountMap", ""), new d(this).getType());
        if (map == null) {
            map = new HashMap();
        }
        if (map.get(str) == null) {
            map.put(str + "", 1);
        }
        for (Map.Entry entry : map.entrySet()) {
            if (((String) entry.getKey()).equals(str)) {
                return ((Integer) entry.getValue()).intValue();
            }
        }
        return 1;
    }

    public void c(String str) {
        Map map = (Map) JsonOperate.fromJson(this.f9798a.getString("currentOfflineCountMap", ""), new c(this).getType());
        if (map == null) {
            map = new HashMap();
        }
        map.put(str, Integer.valueOf(b(str) + 1));
        a("currentOfflineCountMap", JsonOperate.toJson(map));
    }

    public String d() {
        return this.f9798a.getString("pos_sn", "");
    }

    public String e() {
        return this.f9798a.getString("key_server_url", "");
    }

    public int a(String str) {
        Map map = (Map) JsonOperate.fromJson(this.f9798a.getString("maxOfflineCountMap", ""), new b(this).getType());
        if (map == null) {
            map = new HashMap();
        }
        for (Map.Entry entry : map.entrySet()) {
            if (((String) entry.getKey()).equals(str)) {
                return ((Integer) entry.getValue()).intValue();
            }
        }
        return 0;
    }

    public HashMap<String, CodeBarPayInfoBean.DataBean> c() {
        HashMap<String, CodeBarPayInfoBean.DataBean> map;
        String string = this.f9798a.getString("mCodeBarPayInfoMap", "");
        if (TextUtils.isEmpty(string)) {
            map = null;
        } else {
            map = new HashMap<>();
            try {
                JSONObject jSONObject = new JSONObject(string);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, hp1.a(new JSONObject(jSONObject.getJSONObject(next).toString())));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (map == null) {
            map = new HashMap<>();
        }
        map.toString();
        return map;
    }

    public List<CodeBarPayInfoBean.DataBean> b() {
        ArrayList arrayList;
        String string = this.f9798a.getString("mCodeBarPayInfoList", "");
        if (TextUtils.isEmpty(string)) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            try {
                JSONArray jSONArray = new JSONArray(string);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(hp1.a(jSONArray.getJSONObject(i)));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        Objects.toString(arrayList);
        return arrayList;
    }

    public void a(List<CodeBarPayInfoBean.DataBean> list) {
        String string;
        String str = null;
        if (list != null) {
            try {
                if (list.size() == 0) {
                    string = "";
                } else {
                    JSONArray jSONArray = new JSONArray();
                    Iterator<CodeBarPayInfoBean.DataBean> it = list.iterator();
                    while (it.hasNext()) {
                        jSONArray.put(it.next().toJsonObject());
                    }
                    string = jSONArray.toString();
                }
                str = string;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            a("mCodeBarPayInfoList", str);
            return;
        }
        a("mCodeBarPayInfoList", (String) null);
    }

    public void a(Map<String, BarcodeParBean> map) {
        String strA;
        if (map != null) {
            try {
                strA = rp1.a(map);
            } catch (JSONException e2) {
                e2.printStackTrace();
                strA = "";
            }
            a("mBarcodeParMap", strA);
        }
    }
}
