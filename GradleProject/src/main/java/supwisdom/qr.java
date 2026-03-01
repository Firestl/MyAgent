package supwisdom;

import android.os.Bundle;

/* JADX INFO: compiled from: BaseReq.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class qr {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f8947a;

    public void a(Bundle bundle) {
        if (bundle != null) {
            this.f8947a = bundle.getString("android.intent.ding.EXTRA_BASEREQ_TRANSACTION");
        }
    }

    public abstract boolean a();

    public abstract int b();

    public void b(Bundle bundle) {
        if (bundle != null) {
            bundle.putInt("android.intent.ding.EXTRA_COMMAND_TYPE", c());
            bundle.putString("android.intent.ding.EXTRA_BASEREQ_TRANSACTION", this.f8947a);
        }
    }

    public abstract int c();
}
