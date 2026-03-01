package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import supwisdom.bt1;
import supwisdom.dt1;
import supwisdom.us1;

/* JADX INFO: compiled from: CacheStrategy.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ot1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    public final bt1 f8710a;

    @Nullable
    public final dt1 b;

    public ot1(bt1 bt1Var, dt1 dt1Var) {
        this.f8710a = bt1Var;
        this.b = dt1Var;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0056, code lost:
    
        if (r3.b().b() == false) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(supwisdom.dt1 r3, supwisdom.bt1 r4) {
        /*
            int r0 = r3.c()
            r1 = 200(0xc8, float:2.8E-43)
            r2 = 0
            if (r0 == r1) goto L5a
            r1 = 410(0x19a, float:5.75E-43)
            if (r0 == r1) goto L5a
            r1 = 414(0x19e, float:5.8E-43)
            if (r0 == r1) goto L5a
            r1 = 501(0x1f5, float:7.02E-43)
            if (r0 == r1) goto L5a
            r1 = 203(0xcb, float:2.84E-43)
            if (r0 == r1) goto L5a
            r1 = 204(0xcc, float:2.86E-43)
            if (r0 == r1) goto L5a
            r1 = 307(0x133, float:4.3E-43)
            if (r0 == r1) goto L31
            r1 = 308(0x134, float:4.32E-43)
            if (r0 == r1) goto L5a
            r1 = 404(0x194, float:5.66E-43)
            if (r0 == r1) goto L5a
            r1 = 405(0x195, float:5.68E-43)
            if (r0 == r1) goto L5a
            switch(r0) {
                case 300: goto L5a;
                case 301: goto L5a;
                case 302: goto L31;
                default: goto L30;
            }
        L30:
            goto L59
        L31:
            java.lang.String r0 = "Expires"
            java.lang.String r0 = r3.a(r0)
            if (r0 != 0) goto L5a
            supwisdom.fs1 r0 = r3.b()
            int r0 = r0.d()
            r1 = -1
            if (r0 != r1) goto L5a
            supwisdom.fs1 r0 = r3.b()
            boolean r0 = r0.c()
            if (r0 != 0) goto L5a
            supwisdom.fs1 r0 = r3.b()
            boolean r0 = r0.b()
            if (r0 == 0) goto L59
            goto L5a
        L59:
            return r2
        L5a:
            supwisdom.fs1 r3 = r3.b()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            supwisdom.fs1 r3 = r4.b()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            r2 = 1
        L6f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.ot1.a(supwisdom.dt1, supwisdom.bt1):boolean");
    }

    /* JADX INFO: compiled from: CacheStrategy.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f8711a;
        public final bt1 b;
        public final dt1 c;
        public Date d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f8712e;
        public Date f;
        public String g;
        public Date h;
        public long i;
        public long j;
        public String k;
        public int l;

        public a(long j, bt1 bt1Var, dt1 dt1Var) {
            this.l = -1;
            this.f8711a = j;
            this.b = bt1Var;
            this.c = dt1Var;
            if (dt1Var != null) {
                this.i = dt1Var.l();
                this.j = dt1Var.j();
                us1 us1VarE = dt1Var.e();
                int iC = us1VarE.c();
                for (int i = 0; i < iC; i++) {
                    String strA = us1VarE.a(i);
                    String strB = us1VarE.b(i);
                    if (HttpHeaders.HEAD_KEY_DATE.equalsIgnoreCase(strA)) {
                        this.d = zt1.a(strB);
                        this.f8712e = strB;
                    } else if (HttpHeaders.HEAD_KEY_EXPIRES.equalsIgnoreCase(strA)) {
                        this.h = zt1.a(strB);
                    } else if (HttpHeaders.HEAD_KEY_LAST_MODIFIED.equalsIgnoreCase(strA)) {
                        this.f = zt1.a(strB);
                        this.g = strB;
                    } else if (HttpHeaders.HEAD_KEY_E_TAG.equalsIgnoreCase(strA)) {
                        this.k = strB;
                    } else if ("Age".equalsIgnoreCase(strA)) {
                        this.l = au1.a(strB, -1);
                    }
                }
            }
        }

        public final long a() {
            Date date = this.d;
            long jMax = date != null ? Math.max(0L, this.j - date.getTime()) : 0L;
            int i = this.l;
            if (i != -1) {
                jMax = Math.max(jMax, TimeUnit.SECONDS.toMillis(i));
            }
            long j = this.j;
            return jMax + (j - this.i) + (this.f8711a - j);
        }

        public final long b() {
            if (this.c.b().d() != -1) {
                return TimeUnit.SECONDS.toMillis(r0.d());
            }
            if (this.h != null) {
                Date date = this.d;
                long time = this.h.getTime() - (date != null ? date.getTime() : this.j);
                if (time > 0) {
                    return time;
                }
                return 0L;
            }
            if (this.f == null || this.c.k().g().l() != null) {
                return 0L;
            }
            Date date2 = this.d;
            long time2 = (date2 != null ? date2.getTime() : this.i) - this.f.getTime();
            if (time2 > 0) {
                return time2 / 10;
            }
            return 0L;
        }

        public ot1 c() {
            ot1 ot1VarD = d();
            return (ot1VarD.f8710a == null || !this.b.b().j()) ? ot1VarD : new ot1(null, null);
        }

        public final ot1 d() {
            if (this.c == null) {
                return new ot1(this.b, null);
            }
            if (this.b.d() && this.c.d() == null) {
                return new ot1(this.b, null);
            }
            if (!ot1.a(this.c, this.b)) {
                return new ot1(this.b, null);
            }
            fs1 fs1VarB = this.b.b();
            if (fs1VarB.h() || a(this.b)) {
                return new ot1(this.b, null);
            }
            fs1 fs1VarB2 = this.c.b();
            long jA = a();
            long jB = b();
            if (fs1VarB.d() != -1) {
                jB = Math.min(jB, TimeUnit.SECONDS.toMillis(fs1VarB.d()));
            }
            long millis = 0;
            long millis2 = fs1VarB.f() != -1 ? TimeUnit.SECONDS.toMillis(fs1VarB.f()) : 0L;
            if (!fs1VarB2.g() && fs1VarB.e() != -1) {
                millis = TimeUnit.SECONDS.toMillis(fs1VarB.e());
            }
            if (!fs1VarB2.h()) {
                long j = millis2 + jA;
                if (j < millis + jB) {
                    dt1.a aVarH = this.c.h();
                    if (j >= jB) {
                        aVarH.a("Warning", "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (jA > 86400000 && e()) {
                        aVarH.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new ot1(null, aVarH.a());
                }
            }
            String str = this.k;
            String str2 = HttpHeaders.HEAD_KEY_IF_MODIFIED_SINCE;
            if (str != null) {
                str2 = HttpHeaders.HEAD_KEY_IF_NONE_MATCH;
            } else if (this.f != null) {
                str = this.g;
            } else {
                if (this.d == null) {
                    return new ot1(this.b, null);
                }
                str = this.f8712e;
            }
            us1.a aVarB = this.b.c().b();
            it1.f7984a.a(aVarB, str2, str);
            bt1.a aVarF = this.b.f();
            aVarF.a(aVarB.a());
            return new ot1(aVarF.a(), this.c);
        }

        public final boolean e() {
            return this.c.b().d() == -1 && this.h == null;
        }

        public static boolean a(bt1 bt1Var) {
            return (bt1Var.a(HttpHeaders.HEAD_KEY_IF_MODIFIED_SINCE) == null && bt1Var.a(HttpHeaders.HEAD_KEY_IF_NONE_MATCH) == null) ? false : true;
        }
    }
}
