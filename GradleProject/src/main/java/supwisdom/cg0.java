package supwisdom;

import android.app.Activity;
import android.content.Intent;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class cg0 extends ag0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Intent f7207a;
    public final /* synthetic */ Activity b;
    public final /* synthetic */ int c;

    public cg0(Intent intent, Activity activity, int i) {
        this.f7207a = intent;
        this.b = activity;
        this.c = i;
    }

    @Override // supwisdom.ag0
    public final void a() {
        Intent intent = this.f7207a;
        if (intent != null) {
            this.b.startActivityForResult(intent, this.c);
        }
    }
}
