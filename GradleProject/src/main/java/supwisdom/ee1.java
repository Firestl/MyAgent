package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import okio.Sink;
import supwisdom.pe1;
import supwisdom.se1;

/* JADX INFO: compiled from: Call.java */
/* JADX INFO: loaded from: classes2.dex */
public class ee1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final re1 f7479a;
    public boolean b;
    public volatile boolean c;
    public se1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public of1 f7480e;

    /* JADX INFO: compiled from: Call.java */
    public class b implements pe1.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f7481a;
        public final boolean b;

        public b(int i, se1 se1Var, boolean z) {
            this.f7481a = i;
            this.b = z;
        }

        @Override // supwisdom.pe1.a
        public ue1 a(se1 se1Var) throws IOException {
            if (this.f7481a >= ee1.this.f7479a.u().size()) {
                return ee1.this.a(se1Var, this.b);
            }
            return ee1.this.f7479a.u().get(this.f7481a).a(ee1.this.new b(this.f7481a + 1, se1Var, this.b));
        }
    }

    /* JADX INFO: compiled from: Call.java */
    public final class c extends bf1 {
        public final fe1 b;
        public final boolean c;

        @Override // supwisdom.bf1
        public void a() {
            IOException e2;
            ue1 ue1VarA;
            boolean z = true;
            try {
                try {
                    ue1VarA = ee1.this.a(this.c);
                } catch (IOException e3) {
                    e2 = e3;
                    z = false;
                }
                try {
                    if (ee1.this.c) {
                        this.b.a(ee1.this.d, new IOException("Canceled"));
                    } else {
                        this.b.a(ue1VarA);
                    }
                } catch (IOException e4) {
                    e2 = e4;
                    if (z) {
                        ze1.f9996a.log(Level.INFO, "Callback failure for " + ee1.this.b(), (Throwable) e2);
                    } else {
                        this.b.a(ee1.this.f7480e.f(), e2);
                    }
                }
            } finally {
                ee1.this.f7479a.i().b(this);
            }
        }

        public String b() {
            return ee1.this.d.h().getHost();
        }

        public c(fe1 fe1Var, boolean z) {
            super("OkHttp %s", ee1.this.d.i());
            this.b = fe1Var;
            this.c = z;
        }
    }

    public ee1(re1 re1Var, se1 se1Var) {
        this.f7479a = re1Var.a();
        this.d = se1Var;
    }

    public final String b() {
        String str = this.c ? "canceled call" : "call";
        try {
            return str + " to " + new URL(this.d.h(), "/...").toString();
        } catch (MalformedURLException unused) {
            return str;
        }
    }

    public ue1 a() throws IOException {
        synchronized (this) {
            if (!this.b) {
                this.b = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        try {
            this.f7479a.i().a(this);
            ue1 ue1VarA = a(false);
            if (ue1VarA != null) {
                return ue1VarA;
            }
            throw new IOException("Canceled");
        } finally {
            this.f7479a.i().b(this);
        }
    }

    public void a(fe1 fe1Var) {
        a(fe1Var, false);
    }

    public void a(fe1 fe1Var, boolean z) {
        synchronized (this) {
            if (!this.b) {
                this.b = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        this.f7479a.i().a(new c(fe1Var, z));
    }

    public final ue1 a(boolean z) throws IOException {
        return new b(0, this.d, z).a(this.d);
    }

    public ue1 a(se1 se1Var, boolean z) throws IOException {
        ue1 ue1VarG;
        se1 se1VarD;
        te1 te1VarA = se1Var.a();
        if (te1VarA != null) {
            se1.b bVarF = se1Var.f();
            qe1 qe1VarB = te1VarA.b();
            if (qe1VarB == null) {
                long jA = te1VarA.a();
                if (jA != -1) {
                    bVarF.b(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, Long.toString(jA));
                    bVarF.a("Transfer-Encoding");
                } else {
                    bVarF.b("Transfer-Encoding", "chunked");
                    bVarF.a(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
                }
                se1Var = bVarF.a();
            } else {
                qe1VarB.toString();
                throw null;
            }
        }
        this.f7480e = new of1(this.f7479a, se1Var, false, false, z, null, null, null, null);
        int i = 0;
        while (!this.c) {
            try {
                this.f7480e.o();
                this.f7480e.m();
                ue1VarG = this.f7480e.g();
                se1VarD = this.f7480e.d();
            } catch (IOException e2) {
                of1 of1VarA = this.f7480e.a(e2, (Sink) null);
                if (of1VarA != null) {
                    this.f7480e = of1VarA;
                } else {
                    throw e2;
                }
            }
            if (se1VarD == null) {
                if (!z) {
                    this.f7480e.n();
                }
                return ue1VarG;
            }
            i++;
            if (i <= 20) {
                if (!this.f7480e.a(se1VarD.h())) {
                    this.f7480e.n();
                }
                this.f7480e = new of1(this.f7479a, se1VarD, false, false, z, this.f7480e.a(), null, null, ue1VarG);
            } else {
                throw new ProtocolException("Too many follow-up requests: " + i);
            }
        }
        this.f7480e.n();
        return null;
    }
}
