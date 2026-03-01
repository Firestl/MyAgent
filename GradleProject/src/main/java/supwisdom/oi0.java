package supwisdom;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.search.GoogleNowAuthState;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class oi0 implements ck0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Status f8676a;
    public final GoogleNowAuthState b;

    public oi0(Status status, GoogleNowAuthState googleNowAuthState) {
        this.f8676a = status;
        this.b = googleNowAuthState;
    }

    @Override // supwisdom.tc0
    public final Status getStatus() {
        return this.f8676a;
    }
}
