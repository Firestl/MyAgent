package XI.CA.XI;

import android.database.ContentObserver;

/* JADX INFO: loaded from: classes.dex */
public class kM extends ContentObserver {
    public int K0;

    /* JADX INFO: renamed from: XI, reason: collision with root package name */
    public String f1050XI;
    public K0 kM;

    public kM(K0 k0, int i, String str) {
        super(null);
        this.kM = k0;
        this.K0 = i;
        this.f1050XI = str;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        K0 k0 = this.kM;
        if (k0 != null) {
            k0.K0(this.K0, this.f1050XI);
        }
    }
}
