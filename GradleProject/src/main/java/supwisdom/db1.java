package supwisdom;

import android.text.TextUtils;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class db1 {
    public boolean a(q91 q91Var) {
        if (q91Var != null) {
            try {
                q91Var.a();
                return q91Var.b();
            } catch (Throwable th) {
                t91.a("BaseBusiness", "install Hooker failed,%s", TextUtils.isEmpty(th.getMessage()) ? "" : th.getMessage());
            }
        }
        return false;
    }
}
