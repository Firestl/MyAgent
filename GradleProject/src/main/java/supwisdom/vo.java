package supwisdom;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.taobao.weex.utils.FunctionParser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class vo {
    public static vo u;
    public static final char[] v = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_UPPER, 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', 'Z', FunctionParser.Lexer.A_LOWER, 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', FunctionParser.Lexer.Z_LOWER, FunctionParser.Lexer.PLUS, '/'};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9532a = 10000;
    public boolean b = false;
    public String c = "https://h5.m.taobao.com/mlapp/olist.html";
    public int d = 10;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9533e = true;
    public boolean f = true;
    public boolean g = false;
    public boolean h = false;
    public boolean i = true;
    public boolean j = true;
    public String k = "";
    public boolean l = false;
    public boolean m = false;
    public boolean n = false;
    public boolean o = true;
    public String p = "";
    public String q = "";
    public boolean r = false;
    public List<b> s = null;
    public int t = -1;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ pp f9534a;
        public final /* synthetic */ Context b;

        public a(pp ppVar, Context context) {
            this.f9534a = ppVar;
            this.b = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                fp fpVarA = new kp().a(this.f9534a, this.b);
                if (fpVarA != null) {
                    vo.this.b(fpVarA.b());
                    vo.this.a(pp.e());
                }
            } catch (Throwable th) {
                vp.a(th);
            }
        }
    }

    public static vo v() {
        if (u == null) {
            vo voVar = new vo();
            u = voVar;
            voVar.s();
        }
        return u;
    }

    public boolean b() {
        return this.b;
    }

    public boolean c() {
        return this.f9533e;
    }

    public boolean d() {
        return this.f;
    }

    public String e() {
        return this.c;
    }

    public int f() {
        return this.d;
    }

    public boolean g() {
        return this.h;
    }

    public boolean h() {
        return this.i;
    }

    public boolean i() {
        return this.j;
    }

    public String j() {
        return this.k;
    }

    public boolean k() {
        return this.l;
    }

    public boolean l() {
        return this.m;
    }

    public boolean m() {
        return this.n;
    }

    public boolean n() {
        return this.o;
    }

    public boolean o() {
        return this.r;
    }

    public String p() {
        return this.p;
    }

    public String q() {
        return this.q;
    }

    public List<b> r() {
        return this.s;
    }

    public void s() {
        Context contextA = qp.d().a();
        String strB = xp.b(pp.e(), contextA, "alipay_cashier_dynamic_config", null);
        try {
            this.t = Integer.parseInt(xp.b(pp.e(), contextA, "utdid_factor", "-1"));
        } catch (Exception unused) {
        }
        a(strB);
    }

    public final JSONObject t() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("timeout", a());
        jSONObject.put("h5_port_degrade", b());
        jSONObject.put("tbreturl", e());
        jSONObject.put("configQueryInterval", f());
        jSONObject.put("launchAppSwitch", b.a(r()));
        jSONObject.put("scheme_pay_2", c());
        jSONObject.put("intercept_batch", d());
        jSONObject.put("deg_log_mcgw", g());
        jSONObject.put("deg_start_srv_first", h());
        jSONObject.put("prev_jump_dual", i());
        jSONObject.put("use_sc_only", j());
        jSONObject.put("bind_use_imp", k());
        jSONObject.put("retry_bnd_once", l());
        jSONObject.put("skip_trans", m());
        jSONObject.put("up_before_pay", n());
        jSONObject.put("use_sc_lck_a", o());
        jSONObject.put("lck_k", p());
        jSONObject.put("bind_with_startActivity", q());
        return jSONObject;
    }

    public final int u() {
        String strC = qp.d().c();
        if (TextUtils.isEmpty(strC)) {
            return -1;
        }
        String strReplaceAll = strC.replaceAll(ContainerUtils.KEY_VALUE_DELIMITER, "");
        if (strReplaceAll.length() >= 5) {
            strReplaceAll = strReplaceAll.substring(0, 5);
        }
        int iD = (int) (d(strReplaceAll) % 10000);
        return iD < 0 ? iD * (-1) : iD;
    }

    public static int c(String str) {
        for (int i = 0; i < 64; i++) {
            if (str.equals(String.valueOf(v[i]))) {
                return i;
            }
        }
        return 0;
    }

    public static long d(String str) {
        return a(str, 6);
    }

    public final void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("st_sdk_config");
            if (jSONObjectOptJSONObject != null) {
                a(jSONObjectOptJSONObject);
            } else {
                vp.c("DynCon", "empty config");
            }
        } catch (Throwable th) {
            vp.a(th);
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f9535a;
        public final int b;
        public final String c;

        public b(String str, int i, String str2) {
            this.f9535a = str;
            this.b = i;
            this.c = str2;
        }

        public static b a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new b(jSONObject.optString("pn"), jSONObject.optInt("v", 0), jSONObject.optString("pk"));
        }

        public String toString() {
            return String.valueOf(a(this));
        }

        public static List<b> a(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                b bVarA = a(jSONArray.optJSONObject(i));
                if (bVarA != null) {
                    arrayList.add(bVarA);
                }
            }
            return arrayList;
        }

        public static JSONObject a(b bVar) {
            if (bVar == null) {
                return null;
            }
            try {
                return new JSONObject().put("pn", bVar.f9535a).put("v", bVar.b).put("pk", bVar.c);
            } catch (JSONException e2) {
                vp.a(e2);
                return null;
            }
        }

        public static JSONArray a(List<b> list) {
            if (list == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<b> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(a(it.next()));
            }
            return jSONArray;
        }
    }

    public int a() {
        int i = this.f9532a;
        if (i >= 1000 && i <= 20000) {
            vp.a("DynCon", "time = " + this.f9532a);
            return this.f9532a;
        }
        vp.a("DynCon", "time(def) = 10000");
        return 10000;
    }

    public final void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            a(new JSONObject(str));
        } catch (Throwable th) {
            vp.a(th);
        }
    }

    public final void a(pp ppVar) {
        try {
            xp.a(ppVar, qp.d().a(), "alipay_cashier_dynamic_config", t().toString());
        } catch (Exception e2) {
            vp.a(e2);
        }
    }

    public final void a(JSONObject jSONObject) {
        this.f9532a = jSONObject.optInt("timeout", 10000);
        this.b = jSONObject.optBoolean("h5_port_degrade", false);
        this.c = jSONObject.optString("tbreturl", "https://h5.m.taobao.com/mlapp/olist.html").trim();
        this.d = jSONObject.optInt("configQueryInterval", 10);
        this.s = b.a(jSONObject.optJSONArray("launchAppSwitch"));
        this.f9533e = jSONObject.optBoolean("scheme_pay_2", true);
        this.f = jSONObject.optBoolean("intercept_batch", true);
        this.h = jSONObject.optBoolean("deg_log_mcgw", false);
        this.i = jSONObject.optBoolean("deg_start_srv_first", true);
        this.j = jSONObject.optBoolean("prev_jump_dual", true);
        this.k = jSONObject.optString("use_sc_only", "");
        this.l = jSONObject.optBoolean("bind_use_imp", false);
        this.m = jSONObject.optBoolean("retry_bnd_once", false);
        this.n = jSONObject.optBoolean("skip_trans", false);
        this.o = jSONObject.optBoolean("up_before_pay", true);
        this.p = jSONObject.optString("lck_k", "");
        this.r = jSONObject.optBoolean("use_sc_lck_a", false);
        this.q = jSONObject.optString("bind_with_startActivity", "");
    }

    public void a(pp ppVar, Context context) {
        new Thread(new a(ppVar, context)).start();
    }

    public boolean a(Context context, int i) {
        if (this.t == -1) {
            this.t = u();
            xp.a(pp.e(), context, "utdid_factor", String.valueOf(this.t));
        }
        return this.t < i;
    }

    public static long a(String str, int i) {
        int iPow = (int) Math.pow(2.0d, i);
        int length = str.length();
        long j = 0;
        int i2 = 0;
        int i3 = length;
        while (i2 < length) {
            int i4 = i2 + 1;
            j += ((long) Integer.parseInt(String.valueOf(c(str.substring(i2, i4))))) * ((long) Math.pow(iPow, i3 - 1));
            i3--;
            i2 = i4;
        }
        return j;
    }
}
