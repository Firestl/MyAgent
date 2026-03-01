package supwisdom;

import com.tencent.open.SocialConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: RecogResult.java */
/* JADX INFO: loaded from: classes2.dex */
public class pj1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String[] f8798a;
    public String b;
    public String c;
    public int d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8799e = -1;

    public static pj1 e(String str) {
        pj1 pj1Var = new pj1();
        pj1Var.b(str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt("error");
            int iOptInt2 = jSONObject.optInt("sub_error");
            pj1Var.a(iOptInt);
            pj1Var.a(jSONObject.optString(SocialConstants.PARAM_APP_DESC));
            pj1Var.d(jSONObject.optString("result_type"));
            pj1Var.b(iOptInt2);
            if (iOptInt == 0) {
                pj1Var.c(jSONObject.getString("origin_result"));
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("results_recognition");
                if (jSONArrayOptJSONArray != null) {
                    int length = jSONArrayOptJSONArray.length();
                    String[] strArr = new String[length];
                    for (int i = 0; i < length; i++) {
                        strArr[i] = jSONArrayOptJSONArray.getString(i);
                    }
                    pj1Var.a(strArr);
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return pj1Var;
    }

    public void a(String[] strArr) {
        this.f8798a = strArr;
    }

    public int b() {
        return this.d;
    }

    public void b(String str) {
    }

    public void c(String str) {
    }

    public String[] c() {
        return this.f8798a;
    }

    public void d(String str) {
        this.c = str;
    }

    public boolean f() {
        return "final_result".equals(this.c);
    }

    public boolean g() {
        return "nlu_result".equals(this.c);
    }

    public boolean h() {
        return "partial_result".equals(this.c);
    }

    public void a(int i) {
        this.d = i;
    }

    public void b(int i) {
        this.f8799e = i;
    }

    public int d() {
        return this.f8799e;
    }

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public boolean e() {
        return this.d != 0;
    }
}
