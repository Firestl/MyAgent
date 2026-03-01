package supwisdom;

import android.database.ContentObserver;
import android.util.Log;

/* JADX INFO: compiled from: IdentifierIdObserver.java */
/* JADX INFO: loaded from: classes3.dex */
public class mw1 extends ContentObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f8447a;
    public int b;
    public lw1 c;

    public mw1(lw1 lw1Var, int i, String str) {
        super(null);
        this.c = lw1Var;
        this.b = i;
        this.f8447a = str;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        lw1 lw1Var = this.c;
        if (lw1Var != null) {
            lw1Var.a(this.b, this.f8447a);
        } else {
            Log.e("VMS_SDK_Observer", "mIdentifierIdClient is null");
        }
    }
}
