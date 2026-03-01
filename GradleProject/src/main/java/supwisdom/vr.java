package supwisdom;

import android.os.Bundle;
import android.util.Log;

/* JADX INFO: compiled from: SendAuth.java */
/* JADX INFO: loaded from: classes.dex */
public class vr extends qr {
    public String b;
    public String c;

    @Override // supwisdom.qr
    public boolean a() {
        String str = this.b;
        if (str == null || str.length() == 0 || this.b.length() > 1024) {
            Log.e("SendDDAuth.Req", "checkArgs fail, scope is invalid");
            return false;
        }
        String str2 = this.c;
        if (str2 == null || str2.length() <= 1024) {
            return true;
        }
        Log.e("SendDDAuth.Req", "checkArgs fail, state is invalid");
        return false;
    }

    @Override // supwisdom.qr
    public int b() {
        return 20160101;
    }

    @Override // supwisdom.qr
    public void b(Bundle bundle) {
        super.b(bundle);
        if (bundle != null) {
            bundle.putString("android.intent.ding.EXTRA_SEND_AUTH_SCOPE", this.b);
            bundle.putString("android.intent.ding.EXTRA_SEND_AUTH_STATE", this.c);
        }
    }

    @Override // supwisdom.qr
    public int c() {
        return 100;
    }
}
