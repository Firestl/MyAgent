package supwisdom;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public class so {

    public static final class a {
        public static synchronized String a(Context context, String str, String str2) {
            vp.a("RecordPref", "stat append " + str2 + " , " + str);
            if (context != null && !TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(str2)) {
                    str2 = UUID.randomUUID().toString();
                }
                C0231a c0231aB = b(context);
                if (c0231aB.f9194a.size() > 20) {
                    c0231aB.f9194a.clear();
                }
                c0231aB.f9194a.put(str2, str);
                a(context, c0231aB);
                return str2;
            }
            return null;
        }

        public static synchronized C0231a b(Context context) {
            try {
                String strB = xp.b(null, context, "alipay_cashier_statistic_record", null);
                if (TextUtils.isEmpty(strB)) {
                    return new C0231a();
                }
                return new C0231a(strB);
            } catch (Throwable th) {
                vp.a(th);
                return new C0231a();
            }
        }

        /* JADX INFO: renamed from: supwisdom.so$a$a, reason: collision with other inner class name */
        public static final class C0231a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final LinkedHashMap<String, String> f9194a = new LinkedHashMap<>();

            public C0231a() {
            }

            public String a() {
                try {
                    JSONArray jSONArray = new JSONArray();
                    for (Map.Entry<String, String> entry : this.f9194a.entrySet()) {
                        JSONArray jSONArray2 = new JSONArray();
                        jSONArray2.put(entry.getKey()).put(entry.getValue());
                        jSONArray.put(jSONArray2);
                    }
                    return jSONArray.toString();
                } catch (Throwable th) {
                    vp.a(th);
                    return new JSONArray().toString();
                }
            }

            public C0231a(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                        this.f9194a.put(jSONArray2.getString(0), jSONArray2.getString(1));
                    }
                } catch (Throwable th) {
                    vp.a(th);
                }
            }
        }

        public static synchronized String a(Context context) {
            vp.a("RecordPref", "stat peek");
            if (context == null) {
                return null;
            }
            C0231a c0231aB = b(context);
            if (c0231aB.f9194a.isEmpty()) {
                return null;
            }
            try {
                return c0231aB.f9194a.entrySet().iterator().next().getValue();
            } catch (Throwable th) {
                vp.a(th);
                return null;
            }
        }

        public static synchronized int a(Context context, String str) {
            vp.a("RecordPref", "stat remove " + str);
            if (context != null && !TextUtils.isEmpty(str)) {
                C0231a c0231aB = b(context);
                if (c0231aB.f9194a.isEmpty()) {
                    return 0;
                }
                try {
                    ArrayList arrayList = new ArrayList();
                    for (Map.Entry<String, String> entry : c0231aB.f9194a.entrySet()) {
                        if (str.equals(entry.getValue())) {
                            arrayList.add(entry.getKey());
                        }
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        c0231aB.f9194a.remove((String) it.next());
                    }
                    a(context, c0231aB);
                    return arrayList.size();
                } catch (Throwable th) {
                    vp.a(th);
                    int size = c0231aB.f9194a.size();
                    a(context, new C0231a());
                    return size;
                }
            }
            return 0;
        }

        public static synchronized void a(Context context, C0231a c0231a) {
            if (c0231a == null) {
                try {
                    c0231a = new C0231a();
                } catch (Throwable th) {
                    vp.a(th);
                }
            }
            xp.a(null, context, "alipay_cashier_statistic_record", c0231a.a());
        }
    }

    public static final class b {

        public static class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f9195a;
            public final /* synthetic */ Context b;

            public a(String str, Context context) {
                this.f9195a = str;
                this.b = context;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty(this.f9195a) || b.b(this.b, this.f9195a)) {
                    for (int i = 0; i < 4; i++) {
                        String strA = a.a(this.b);
                        if (TextUtils.isEmpty(strA) || !b.b(this.b, strA)) {
                            return;
                        }
                    }
                }
            }
        }

        public static synchronized boolean b(Context context, String str) {
            vp.a("mspl", "stat sub " + str);
            try {
                if ((vo.v().g() ? new lp() : new mp()).a((pp) null, context, str) == null) {
                    return false;
                }
                a.a(context, str);
                return true;
            } catch (Throwable th) {
                vp.a(th);
                return false;
            }
        }

        public static synchronized void a(Context context, to toVar, String str, String str2) {
            if (context == null || toVar == null || str == null) {
                return;
            }
            a(context, toVar.a(str), str2);
        }

        public static synchronized void a(Context context, String str, String str2) {
            if (context == null) {
                return;
            }
            if (!TextUtils.isEmpty(str)) {
                a.a(context, str, str2);
            }
            new Thread(new a(str, context)).start();
        }
    }

    public static final class c {
        public static synchronized long a(Context context) {
            long j;
            long j2 = 0;
            try {
                String strB = xp.b(null, context, "alipay_cashier_statistic_v", null);
                if (!TextUtils.isEmpty(strB)) {
                    j2 = Long.parseLong(strB);
                }
            } catch (Throwable unused) {
            }
            j = j2 + 1;
            try {
                xp.a(null, context, "alipay_cashier_statistic_v", Long.toString(j));
            } catch (Throwable unused2) {
            }
            return j;
        }
    }

    public static synchronized void a(Context context, pp ppVar, String str, String str2) {
        if (context == null || ppVar == null) {
            return;
        }
        try {
            a.a(context, ppVar.i.a(str), str2);
        } catch (Throwable th) {
            vp.a(th);
        }
    }

    public static synchronized void b(Context context, pp ppVar, String str, String str2) {
        if (context == null || ppVar == null) {
            return;
        }
        b.a(context, ppVar.i, str, str2);
    }

    public static void b(pp ppVar, String str, String str2, String str3) {
        if (ppVar == null) {
            return;
        }
        ppVar.i.b(str, str2, str3);
    }

    public static void a(pp ppVar, String str, Throwable th) {
        if (ppVar == null || th == null || th.getClass() == null) {
            return;
        }
        ppVar.i.a(str, th.getClass().getSimpleName(), th);
    }

    public static void a(pp ppVar, String str, String str2, Throwable th, String str3) {
        if (ppVar == null) {
            return;
        }
        ppVar.i.a(str, str2, th, str3);
    }

    public static void a(pp ppVar, String str, String str2, Throwable th) {
        if (ppVar == null) {
            return;
        }
        ppVar.i.a(str, str2, th);
    }

    public static void a(pp ppVar, String str, String str2, String str3) {
        if (ppVar == null) {
            return;
        }
        ppVar.i.a(str, str2, str3);
    }

    public static void a(pp ppVar, String str, String str2) {
        if (ppVar == null) {
            return;
        }
        ppVar.i.a(str, str2);
    }
}
