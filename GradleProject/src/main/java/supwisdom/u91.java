package supwisdom;

import supwisdom.z91;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class u91 extends o91 {
    public u91() {
        super(z91.f9982a.a(new Object[0]));
    }

    public static u91 d() {
        return new u91();
    }

    @Override // supwisdom.q91
    public void a() {
        if (mb1.c()) {
            ga1.b.a(z91.a.f9983a.a(), c());
            t91.c("ActivityManagerStub", "ActivityManager hook success above android 8.0");
        } else {
            if (z91.b.b() == da1.f7322a) {
                z91.b.a(c());
            } else if (z91.b.b() == ga1.f7709a) {
                ga1.b.a(z91.b.a(), c());
            }
            t91.c("ActivityManagerStub", "ActivityManager hook success below android 8.0");
        }
    }

    @Override // supwisdom.q91
    public boolean b() {
        if (mb1.c()) {
            if (ga1.b.a(z91.a.f9983a.a()) != c()) {
                return false;
            }
        } else if (z91.b.b() == da1.f7322a) {
            z91.b.a(c());
            if (z91.b.a() != c()) {
                return false;
            }
        } else {
            if (z91.b.b() != ga1.f7709a) {
                return false;
            }
            if (ga1.b.a(z91.b.a()) != c()) {
                return false;
            }
        }
        return true;
    }
}
