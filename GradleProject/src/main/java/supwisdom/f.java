package supwisdom;

import android.view.View;
import com.newcapec.virtualcard.activity.PaySuccessActivity;

/* JADX INFO: loaded from: classes.dex */
public class f implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PaySuccessActivity f7553a;

    public f(PaySuccessActivity paySuccessActivity) {
        this.f7553a = paySuccessActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f7553a.finish();
    }
}
