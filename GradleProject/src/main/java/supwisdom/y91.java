package supwisdom;

import android.os.IInterface;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class y91 extends o91 {
    public y91() {
        super(aa1.c.a());
    }

    public static y91 d() {
        return new y91();
    }

    @Override // supwisdom.q91
    public void a() {
        aa1.c.a((IInterface) c());
        c("package");
        t91.c("PackageManagerStub", "replace package service");
    }

    @Override // supwisdom.q91
    public boolean b() {
        return aa1.c.a() == c();
    }
}
