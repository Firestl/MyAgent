package supwisdom;

import com.sangfor.dx.dex.code.LocalList;
import java.util.HashSet;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class jz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8101a;
    public vz0 b;
    public ez0 c;
    public gz0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public wz0 f8102e;
    public LocalList f;
    public lz0 g;

    /* JADX INFO: compiled from: Proguard */
    public interface a {
        int a(u41 u41Var);
    }

    public jz0(int i, vz0 vz0Var, ez0 ez0Var) {
        if (vz0Var == null) {
            throw new NullPointerException("unprocessedInsns == null");
        }
        if (ez0Var == null) {
            throw new NullPointerException("unprocessedCatches == null");
        }
        this.f8101a = i;
        this.b = vz0Var;
        this.c = ez0Var;
        this.d = null;
        this.f8102e = null;
        this.f = null;
        this.g = null;
    }

    public void a(a aVar) {
        this.b.a(aVar);
    }

    public HashSet<b61> b() {
        return this.c.b();
    }

    public gz0 c() {
        a();
        return this.d;
    }

    public HashSet<u41> d() {
        return this.b.e();
    }

    public lz0 e() {
        a();
        return this.g;
    }

    public LocalList f() {
        a();
        return this.f;
    }

    public wz0 g() {
        a();
        return this.f8102e;
    }

    public boolean h() {
        return this.c.a();
    }

    public boolean i() {
        return this.b.f();
    }

    public boolean j() {
        return this.f8101a != 1 && this.b.g();
    }

    public final void a() {
        if (this.g != null) {
            return;
        }
        lz0 lz0VarC = this.b.c();
        this.g = lz0VarC;
        this.f8102e = wz0.a(lz0VarC, this.f8101a);
        this.f = LocalList.a(this.g);
        this.d = this.c.build();
        this.b = null;
        this.c = null;
    }
}
