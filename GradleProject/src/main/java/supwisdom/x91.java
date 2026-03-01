package supwisdom;

import android.app.Instrumentation;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class x91 extends o91 {
    public static x91 f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public x91 f9725e;

    public x91() {
        super(e());
    }

    public static x91 d() {
        synchronized (x91.class) {
            if (f == null) {
                x91 x91Var = new x91();
                f = x91Var;
                try {
                    x91Var.a();
                    t91.c("InstrumentationStub", "hook Instrumentation");
                } catch (Throwable th) {
                    t91.a("InstrumentationStub", "hook Instrumentation failed", th);
                }
            }
        }
        return f;
    }

    public static Instrumentation e() {
        Instrumentation instrumentation = (Instrumentation) aa1.d.a(aa1.f6877a.a(new Object[0]));
        if (instrumentation == null) {
            t91.b("InstrumentationStub", "hook Instrumentation failed");
        }
        return instrumentation;
    }

    public static x91 f() {
        return d();
    }

    @Override // supwisdom.q91
    public void a() {
        t91.c("InstrumentationStub", "hook instrumentation...");
        aa1.d.a(aa1.f6877a.a(new Object[0]), c());
    }

    @Override // supwisdom.q91
    public boolean b() {
        return (aa1.d.a(aa1.f6877a.a(new Object[0])) == c()) || (this.f9725e != null && aa1.d.a(aa1.f6877a.a(new Object[0])) == this.f9725e.c());
    }
}
