package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import java.io.IOException;
import okio.Okio;
import okio.Sink;
import okio.Source;
import supwisdom.ue1;

/* JADX INFO: compiled from: HttpTransport.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qf1 implements yf1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final of1 f8913a;
    public final mf1 b;

    public qf1(of1 of1Var, mf1 mf1Var) {
        this.f8913a = of1Var;
        this.b = mf1Var;
    }

    @Override // supwisdom.yf1
    public Sink a(se1 se1Var, long j) throws IOException {
        if ("chunked".equalsIgnoreCase(se1Var.a("Transfer-Encoding"))) {
            return this.b.f();
        }
        if (j != -1) {
            return this.b.a(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    @Override // supwisdom.yf1
    public ue1.b b() throws IOException {
        return this.b.i();
    }

    @Override // supwisdom.yf1
    public boolean c() {
        return ("close".equalsIgnoreCase(this.f8913a.f().a(HttpHeaders.HEAD_KEY_CONNECTION)) || "close".equalsIgnoreCase(this.f8913a.g().a(HttpHeaders.HEAD_KEY_CONNECTION)) || this.b.d()) ? false : true;
    }

    @Override // supwisdom.yf1
    public void finishRequest() throws IOException {
        this.b.c();
    }

    public final Source b(ue1 ue1Var) throws IOException {
        if (!of1.b(ue1Var)) {
            return this.b.b(0L);
        }
        if ("chunked".equalsIgnoreCase(ue1Var.a("Transfer-Encoding"))) {
            return this.b.a(this.f8913a);
        }
        long jA = rf1.a(ue1Var);
        return jA != -1 ? this.b.b(jA) : this.b.g();
    }

    @Override // supwisdom.yf1
    public void a(uf1 uf1Var) throws IOException {
        this.b.a(uf1Var);
    }

    @Override // supwisdom.yf1
    public void a(se1 se1Var) throws IOException {
        this.f8913a.p();
        this.b.a(se1Var.c(), tf1.a(se1Var, this.f8913a.e().e().b().type(), this.f8913a.e().d()));
    }

    @Override // supwisdom.yf1
    public void a() throws IOException {
        if (c()) {
            this.b.h();
        } else {
            this.b.b();
        }
    }

    @Override // supwisdom.yf1
    public ve1 a(ue1 ue1Var) throws IOException {
        return new sf1(ue1Var.g(), Okio.buffer(b(ue1Var)));
    }
}
