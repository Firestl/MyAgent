package supwisdom;

import android.os.Bundle;

/* JADX INFO: compiled from: SendAuth.java */
/* JADX INFO: loaded from: classes.dex */
public class wr extends rr {
    public String c;

    @Override // supwisdom.rr
    public int a() {
        return 100;
    }

    @Override // supwisdom.rr
    public void a(Bundle bundle) {
        super.a(bundle);
        if (bundle != null) {
            this.c = bundle.getString("android.intent.ding.EXTRA_SEND_AUTH_CODE", null);
            bundle.getString("android.intent.ding.EXTRA_SEND_AUTH_STATE", null);
        }
    }
}
