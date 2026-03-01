package com.g.gysdk.a;

import android.text.TextUtils;
import android.util.Pair;
import com.g.gysdk.GyErrorCode;
import com.g.gysdk.a.ab;
import com.g.gysdk.a.al;
import com.g.gysdk.a.d;
import com.taobao.weex.el.parse.Operators;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final as f2015a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f2016e;
    public final int f;
    public volatile v h;
    public long i;
    public boolean j;
    public String k;
    public volatile String b = "";
    public volatile String c = "";
    public final AtomicInteger g = new AtomicInteger(0);
    public boolean d = false;

    public interface a {
        void a(u uVar);
    }

    public interface b {
        void a(v vVar);
    }

    public interface c {
        void a(w wVar);
    }

    public interface d {
        void a(x xVar);
    }

    public static class e extends Pair<String, String> {
        public e(String str, String str2) {
            super(str, str2);
        }

        public static e a(String str, String str2) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return null;
            }
            return new e(str, str2);
        }

        public static boolean a(e eVar, e eVar2) {
            return eVar == eVar2 || (eVar != null && eVar.equals(eVar2));
        }

        @Override // android.util.Pair
        public String toString() {
            return "Key{appId='" + ((String) ((Pair) this).first) + Operators.SINGLE_QUOTE + ", appKey='" + ((String) ((Pair) this).second) + Operators.SINGLE_QUOTE + Operators.BLOCK_END_STR;
        }
    }

    public n(as asVar, int i) {
        this.f2015a = asVar;
        this.f2016e = "ELogin_" + asVar.f1981e + "_INIT";
        this.f = i;
    }

    public String a() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.g.get());
        sb.append("-");
        sb.append(this.j);
        sb.append("-");
        sb.append(this.i);
        if (this.k != null) {
            str = "-" + this.k;
        } else {
            str = "";
        }
        sb.append(str);
        return sb.toString();
    }

    public abstract void a(int i, a aVar);

    public abstract void a(int i, b bVar);

    public void a(v vVar) {
        String str;
        this.h = vVar;
        int i = this.f;
        if (i <= 0 || !s.b) {
            return;
        }
        v.a(i, vVar);
        if (vVar == null) {
            str = "ELogin_" + this.f2015a.f1981e + " clear preLogin cache";
        } else {
            str = "ELogin_" + this.f2015a.f1981e + " save preLogin cache to db, number=" + vVar.i();
        }
        ak.a(str);
    }

    public abstract void a(String str, int i, c cVar);

    public void a(final String str, final String str2, final String str3, int i, final d dVar) {
        if (str2 == null || str3 == null) {
            dVar.a(new x(this.f2015a, "校验-未取token", 0L, null));
        } else {
            final long jCurrentTimeMillis = System.currentTimeMillis();
            al.a(al.b.Work, new Runnable() { // from class: com.g.gysdk.a.n.1
                @Override // java.lang.Runnable
                public void run() {
                    ab.b bVarA = ab.a(str3, str, n.this.f2015a.g, str2);
                    x xVar = new x(n.this.f2015a, bVarA != null ? bVarA.i() : "校验-网络请求错误", jCurrentTimeMillis, str2);
                    xVar.a((bVarA == null || bVarA.g() != 20000) ? GyErrorCode.SERVER_RETURN_ERROR : GyErrorCode.SUCCESS);
                    dVar.a(xVar);
                }
            });
        }
    }

    public final void a(String str, String str2, boolean z) {
        String str3;
        if (this.g.compareAndSet(0, 1)) {
            this.j = z;
            long jCurrentTimeMillis = System.currentTimeMillis();
            String str4 = this.f2015a.f1981e;
            StringBuilder sb = new StringBuilder();
            sb.append("ELogin_");
            sb.append(str4);
            sb.append(" init with appId:");
            sb.append(str);
            sb.append(", appKey:");
            sb.append(str2);
            sb.append(", builtin:");
            sb.append(z);
            ak.a(sb.toString());
            this.b = str;
            this.c = str2;
            try {
                b();
                this.g.set(2);
                this.i = System.currentTimeMillis() - jCurrentTimeMillis;
                ak.a("ELogin_" + str4 + " init success");
                if (s.b) {
                    if (this.f > 0) {
                        v vVarA = v.a(this.f);
                        if (vVarA == null) {
                            str3 = "ELogin_" + str4 + " do not have preLogin db cache";
                        } else {
                            boolean zK = vVarA.k();
                            if (zK) {
                                this.h = vVarA;
                            }
                            str3 = "ELogin_" + str4 + " get preLogin cache from db, number=" + vVarA.i() + ", isValid=" + zK;
                        }
                    } else {
                        str3 = "ELogin_" + str4 + " do not support prelogin db cache";
                    }
                    ak.a(str3);
                }
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    public void a(boolean z) {
        this.d = z;
    }

    public abstract void b();

    public final int c() {
        return this.g.get();
    }

    public String d() {
        return this.f2016e;
    }

    public v e() {
        return this.h;
    }

    public boolean f() {
        if (com.g.gysdk.a.d.a(d.a.DEBUG_UI)) {
            return true;
        }
        v vVarE = e();
        return vVarE != null && vVarE.k();
    }
}
