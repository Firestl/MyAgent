package a.a.a.c;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.newcapec.virtualcard.entity.JsonData;
import com.taobao.weex.el.parse.Operators;
import supwisdom.u;
import supwisdom.v;

/* JADX INFO: loaded from: classes.dex */
public class c extends u {
    public static Context b;
    public static volatile c c;
    public static volatile a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f1069e;

    public static class a extends JsonData {
        public String i;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f1070a = "";
        public String b = "";
        public String c = "";
        public String d = "";

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f1071e = "";
        public String f = "";
        public String g = "";
        public String h = "";
        public String j = "";
    }

    public c(Context context, String str) {
        super(context, str);
    }

    public static c m() {
        String str = "virtual.card.v8" + v.e().c();
        if (c == null) {
            synchronized (v.class) {
                if (c == null) {
                    c = new c(b, str);
                }
            }
        }
        return c;
    }

    public final a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String strA = a(g(str), "");
            if (TextUtils.isEmpty(strA)) {
                return null;
            }
            d = (a) JSON.parseObject(strA, a.class);
            return d;
        } catch (Exception unused) {
            return null;
        }
    }

    public void a() {
        this.f9343a.edit().remove(f1069e).apply();
    }

    public c b(String str) {
        d.i = str;
        return this;
    }

    public void b() {
        b(f1069e, d.toString());
    }

    public boolean c(String str, String str2) {
        f1069e = g(str);
        a aVarA = a(str);
        if (aVarA == null) {
            aVarA = a(str2);
            if (aVarA != null) {
                b(f1069e, a(g(str2), ""));
                this.f9343a.edit().remove(g(str2)).apply();
            } else {
                aVarA = null;
            }
        }
        return (aVarA == null || TextUtils.isEmpty(aVarA.f1071e)) ? false : true;
    }

    public String d() {
        return d.f1071e;
    }

    public String e() {
        return d.j;
    }

    public String f() {
        return d.h;
    }

    public String g() {
        return d.f1070a;
    }

    public final String g(String str) {
        return str.replace(Operators.SPACE_STR, "").replace("-", "").replace(Operators.DOT_STR, "");
    }

    public String h() {
        return d.b;
    }

    public String i() {
        return d.g;
    }

    public String j() {
        return d.c;
    }

    public String k() {
        return d.d;
    }

    public void l() {
        d = new a();
    }

    public c d(String str) {
        d.f1070a = str;
        return this;
    }

    public c e(String str) {
        d.g = str;
        return this;
    }

    public c f(String str) {
        d.d = str;
        return this;
    }

    public String c() {
        return d.f;
    }

    public c c(String str) {
        d.h = str;
        return this;
    }
}
