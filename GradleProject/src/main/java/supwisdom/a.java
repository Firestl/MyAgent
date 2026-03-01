package supwisdom;

import android.view.View;
import com.newcapec.virtualcard.activity.PasswordActivity;

/* JADX INFO: loaded from: classes.dex */
public class a implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PasswordActivity f6835a;

    public a(PasswordActivity passwordActivity) {
        this.f6835a = passwordActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f6835a.finish();
    }
}
