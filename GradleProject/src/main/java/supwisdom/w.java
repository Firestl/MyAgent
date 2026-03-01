package supwisdom;

import android.view.View;
import com.newcapec.virtualcard.activity.MainActivity;
import supwisdom.x;

/* JADX INFO: loaded from: classes.dex */
public class w implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ x f9570a;

    public w(x xVar) {
        this.f9570a = xVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        x.a aVar = this.f9570a.f9686a;
        if (aVar != null) {
            MainActivity.b bVar = (MainActivity.b) aVar;
            MainActivity.d(MainActivity.this);
            bVar.f3854a.dismiss();
        }
    }
}
