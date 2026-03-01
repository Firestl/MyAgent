package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import supwisdom.se1;
import supwisdom.ue1;

/* JADX INFO: compiled from: CacheStrategy.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kf1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final se1 f8170a;
    public final ue1 b;

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0056, code lost:
    
        if (r3.b().b() == false) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(supwisdom.ue1 r3, supwisdom.se1 r4) {
        /*
            int r0 = r3.e()
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
            supwisdom.de1 r0 = r3.b()
            int r0 = r0.d()
            r1 = -1
            if (r0 != r1) goto L5a
            supwisdom.de1 r0 = r3.b()
            boolean r0 = r0.c()
            if (r0 != 0) goto L5a
            supwisdom.de1 r0 = r3.b()
            boolean r0 = r0.b()
            if (r0 == 0) goto L59
            goto L5a
        L59:
            return r2
        L5a:
            supwisdom.de1 r3 = r3.b()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            supwisdom.de1 r3 = r4.b()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            r2 = 1
        L6f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.kf1.a(supwisdom.ue1, supwisdom.se1):boolean");
    }

    public kf1(se1 se1Var, ue1 ue1Var) {
        this.f8170a = se1Var;
        this.b = ue1Var;
    }

    /* JADX INFO: compiled from: CacheStrategy.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f8171a;
        public final se1 b;
        public final ue1 c;
        public Date d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f8172e;
        public Date f;
        public String g;
        public Date h;
        public long i;
        public long j;
        public String k;
        public int l;

        public b(long j, se1 se1Var, ue1 ue1Var) {
            this.l = -1;
            this.f8171a = j;
            this.b = se1Var;
            this.c = ue1Var;
            if (ue1Var != null) {
                oe1 oe1VarG = ue1Var.g();
                int iB = oe1VarG.b();
                for (int i = 0; i < iB; i++) {
                    String strA = oe1VarG.a(i);
                    String strB = oe1VarG.b(i);
                    if (HttpHeaders.HEAD_KEY_DATE.equalsIgnoreCase(strA)) {
                        this.d = nf1.a(strB);
                        this.f8172e = strB;
                    } else if (HttpHeaders.HEAD_KEY_EXPIRES.equalsIgnoreCase(strA)) {
                        this.h = nf1.a(strB);
                    } else if (HttpHeaders.HEAD_KEY_LAST_MODIFIED.equalsIgnoreCase(strA)) {
                        this.f = nf1.a(strB);
                        this.g = strB;
                    } else if (HttpHeaders.HEAD_KEY_E_TAG.equalsIgnoreCase(strA)) {
                        this.k = strB;
                    } else if ("Age".equalsIgnoreCase(strA)) {
                        this.l = lf1.a(strB, -1);
                    } else if (rf1.c.equalsIgnoreCase(strA)) {
                        this.i = Long.parseLong(strB);
                    } else if (rf1.d.equalsIgnoreCase(strA)) {
                        this.j = Long.parseLong(strB);
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
            return jMax + (j - this.i) + (this.f8171a - j);
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
            if (this.f == null || this.c.l().h().getQuery() != null) {
                return 0L;
            }
            Date date2 = this.d;
            long time2 = (date2 != null ? date2.getTime() : this.i) - this.f.getTime();
            if (time2 > 0) {
                return time2 / 10;
            }
            return 0L;
        }

        public kf1 c() {
            kf1 kf1VarD = d();
            return (kf1VarD.f8170a == null || !this.b.b().j()) ? kf1VarD : new kf1(null, 0 == true ? 1 : 0);
        }

        public final kf1 d() {
            ue1 ue1Var = null;
            byte b = 0;
            byte b2 = 0;
            byte b3 = 0;
            byte b4 = 0;
            byte b5 = 0;
            byte b6 = 0;
            byte b7 = 0;
            byte b8 = 0;
            byte b9 = 0;
            byte b10 = 0;
            byte b11 = 0;
            byte b12 = 0;
            if (this.c == null) {
                return new kf1(this.b, ue1Var);
            }
            if (this.b.d() && this.c.f() == null) {
                return new kf1(this.b, b11 == true ? 1 : 0);
            }
            if (!kf1.a(this.c, this.b)) {
                return new kf1(this.b, b9 == true ? 1 : 0);
            }
            de1 de1VarB = this.b.b();
            if (de1VarB.h() || a(this.b)) {
                return new kf1(this.b, b2 == true ? 1 : 0);
            }
            long jA = a();
            long jB = b();
            if (de1VarB.d() != -1) {
                jB = Math.min(jB, TimeUnit.SECONDS.toMillis(de1VarB.d()));
            }
            long millis = 0;
            long millis2 = de1VarB.f() != -1 ? TimeUnit.SECONDS.toMillis(de1VarB.f()) : 0L;
            de1 de1VarB2 = this.c.b();
            if (!de1VarB2.g() && de1VarB.e() != -1) {
                millis = TimeUnit.SECONDS.toMillis(de1VarB.e());
            }
            if (!de1VarB2.h()) {
                long j = millis2 + jA;
                if (j < millis + jB) {
                    ue1.b bVarJ = this.c.j();
                    if (j >= jB) {
                        bVarJ.a("Warning", "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (jA > 86400000 && e()) {
                        bVarJ.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new kf1(b7 == true ? 1 : 0, bVarJ.a());
                }
            }
            se1.b bVarF = this.b.f();
            String str = this.k;
            if (str != null) {
                bVarF.b(HttpHeaders.HEAD_KEY_IF_NONE_MATCH, str);
            } else if (this.f != null) {
                bVarF.b(HttpHeaders.HEAD_KEY_IF_MODIFIED_SINCE, this.g);
            } else if (this.d != null) {
                bVarF.b(HttpHeaders.HEAD_KEY_IF_MODIFIED_SINCE, this.f8172e);
            }
            se1 se1VarA = bVarF.a();
            return a(se1VarA) ? new kf1(se1VarA, this.c) : new kf1(se1VarA, b4 == true ? 1 : 0);
        }

        public final boolean e() {
            return this.c.b().d() == -1 && this.h == null;
        }

        public static boolean a(se1 se1Var) {
            return (se1Var.a(HttpHeaders.HEAD_KEY_IF_MODIFIED_SINCE) == null && se1Var.a(HttpHeaders.HEAD_KEY_IF_NONE_MATCH) == null) ? false : true;
        }
    }
}
