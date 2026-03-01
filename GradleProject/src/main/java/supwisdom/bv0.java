package supwisdom;

import android.content.Context;
import android.os.Debug;
import android.text.TextUtils;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.json.JSONObject;
import supwisdom.ev0;

/* JADX INFO: compiled from: HandlerManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class bv0 {
    public static final String f = "bv0";
    public static final int g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap<String, cv0> f7108a = new LinkedHashMap<>();
    public final LinkedHashMap<String, av0> b = new LinkedHashMap<>();
    public final Context c;
    public xu0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f7109e;

    static {
        g = Debug.isDebuggerConnected() ? 60 : 16;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public bv0(Context context, Collection<av0> collection) {
        this.c = context;
        if (context instanceof xu0) {
            this.d = (xu0) context;
        }
        a(collection);
    }

    public void a(Collection<av0> collection) {
        if (this.f7109e) {
            this.f7108a.clear();
            this.b.clear();
        }
        Iterator<av0> it = collection.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
        if (this.f7109e) {
            b();
        }
    }

    public final void b() {
        for (av0 av0Var : this.b.values()) {
            if (av0Var.d) {
                a(av0Var.f6986a);
            } else {
                this.f7108a.put(av0Var.f6986a, null);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final supwisdom.cv0 b(java.lang.String r5) {
        /*
            r4 = this;
            r0 = 0
            if (r5 == 0) goto L12
            java.lang.String r1 = ""
            boolean r1 = r1.equals(r5)     // Catch: java.lang.Exception -> L10
            if (r1 != 0) goto L12
            java.lang.Class r1 = java.lang.Class.forName(r5)     // Catch: java.lang.Exception -> L10
            goto L13
        L10:
            r1 = move-exception
            goto L29
        L12:
            r1 = r0
        L13:
            if (r1 == 0) goto L17
            r2 = 1
            goto L18
        L17:
            r2 = 0
        L18:
            java.lang.Class<supwisdom.cv0> r3 = supwisdom.cv0.class
            boolean r3 = r3.isAssignableFrom(r1)     // Catch: java.lang.Exception -> L10
            r2 = r2 & r3
            if (r2 == 0) goto L47
            java.lang.Object r1 = r1.newInstance()     // Catch: java.lang.Exception -> L10
            supwisdom.cv0 r1 = (supwisdom.cv0) r1     // Catch: java.lang.Exception -> L10
            r0 = r1
            goto L47
        L29:
            r1.printStackTrace()
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Error adding handler "
            r2.append(r3)
            r2.append(r5)
            java.lang.String r5 = "."
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            r1.println(r5)
        L47:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.bv0.b(java.lang.String):supwisdom.cv0");
    }

    public void a() {
        this.f7109e = true;
        this.f7108a.clear();
        b();
    }

    public void a(String str, String str2, ev0.d dVar) {
        cv0 cv0VarA = a(str);
        if (cv0VarA == null) {
            Log.i(f, "exec() call to unknown plugin: " + str);
            return;
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            cv0VarA.a(!TextUtils.isEmpty(str2) ? new JSONObject(str2) : new JSONObject(), dVar);
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            if (jCurrentTimeMillis2 > g) {
                Log.w(f, "THREAD WARNING: exec() call to " + str + Operators.DOT_STR + str2 + " blocked the main thread for " + jCurrentTimeMillis2 + "ms. Plugin should use CordovaInterface.getThreadPool().");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public cv0 a(String str) {
        cv0 cv0VarB = this.f7108a.get(str);
        if (cv0VarB == null) {
            av0 av0Var = this.b.get(str);
            if (av0Var == null) {
                return null;
            }
            cv0 cv0Var = av0Var.c;
            cv0VarB = cv0Var != null ? cv0Var : b(av0Var.b);
            cv0VarB.a(str, this.c, this.d);
            this.f7108a.put(str, cv0VarB);
        }
        return cv0VarB;
    }

    public void a(av0 av0Var) {
        this.b.put(av0Var.f6986a, av0Var);
        cv0 cv0Var = av0Var.c;
        if (cv0Var != null) {
            cv0Var.a(av0Var.f6986a, this.c, this.d);
            this.f7108a.put(av0Var.f6986a, av0Var.c);
        }
    }
}
