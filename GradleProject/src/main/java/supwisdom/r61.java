package supwisdom;

import com.sangfor.dx.util.MutabilityException;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class r61 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f9017a;

    public r61() {
        this.f9017a = true;
    }

    public final boolean c() {
        return !this.f9017a;
    }

    public final boolean d() {
        return this.f9017a;
    }

    public void e() {
        this.f9017a = false;
    }

    public final void f() {
        if (!this.f9017a) {
            throw new MutabilityException("immutable instance");
        }
    }

    public final void g() {
        if (this.f9017a) {
            throw new MutabilityException("mutable instance");
        }
    }

    public r61(boolean z) {
        this.f9017a = z;
    }
}
