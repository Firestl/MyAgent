package supwisdom;

import android.os.Bundle;

/* JADX INFO: compiled from: BaseResp.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class rr {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9087a;
    public String b;

    public abstract int a();

    public void a(Bundle bundle) {
        if (bundle != null) {
            this.f9087a = bundle.getInt("android.intent.ding.EXTRA_BASEREQ_ERROR_CODE");
            this.b = bundle.getString("android.intent.ding.EXTRA_BASEREQ_ERROR_STRING");
            bundle.getString("android.intent.ding.EXTRA_BASEREQ_TRANSACTION");
        }
    }
}
