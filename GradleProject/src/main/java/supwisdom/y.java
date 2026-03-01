package supwisdom;

import android.view.View;
import supwisdom.z;

/* JADX INFO: loaded from: classes.dex */
public class y implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ z f9830a;

    public y(z zVar) {
        this.f9830a = zVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        z zVar = this.f9830a;
        z.a aVar = zVar.c;
        if (aVar != null) {
            aVar.a(zVar);
            this.f9830a.dismiss();
        }
    }
}
