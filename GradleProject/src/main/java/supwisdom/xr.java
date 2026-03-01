package supwisdom;

import android.os.Bundle;
import android.util.Log;
import supwisdom.sr;

/* JADX INFO: compiled from: SendMessageToDD.java */
/* JADX INFO: loaded from: classes.dex */
public class xr extends qr {
    public sr b;
    public int c = 0;

    public xr(Bundle bundle) {
        a(bundle);
    }

    @Override // supwisdom.qr
    public void a(Bundle bundle) {
        super.a(bundle);
        if (bundle != null) {
            this.b = sr.a.a(bundle);
            this.c = bundle.getInt("android.intent.ding.EXTRA_SEND_MESSAGE_SCENE");
        }
    }

    @Override // supwisdom.qr
    public void b(Bundle bundle) {
        super.b(bundle);
        if (bundle != null) {
            bundle.putAll(sr.a.a(this.b));
            bundle.putInt("android.intent.ding.EXTRA_SEND_MESSAGE_SCENE", this.c);
        }
    }

    @Override // supwisdom.qr
    public int c() {
        return this.b.c();
    }

    @Override // supwisdom.qr
    public final boolean a() {
        sr srVar = this.b;
        if (srVar == null) {
            Log.e("SendMessageToDD.Req", "checkArgs fail ,message is null");
            return false;
        }
        return srVar.a();
    }

    @Override // supwisdom.qr
    public int b() {
        sr srVar = this.b;
        if (srVar == null) {
            return Integer.MAX_VALUE;
        }
        return srVar.b();
    }
}
