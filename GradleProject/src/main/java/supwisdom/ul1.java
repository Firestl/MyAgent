package supwisdom;

import android.os.CountDownTimer;
import com.supwisdom.superapp.ui.activity.BindMessageActivity;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: BindMessageActivity.java */
/* JADX INFO: loaded from: classes2.dex */
public class ul1 extends CountDownTimer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ BindMessageActivity f9423a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ul1(BindMessageActivity bindMessageActivity, long j, long j2) {
        super(j, j2);
        this.f9423a = bindMessageActivity;
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        this.f9423a.d.setText(this.f9423a.getResources().getString(R.string.send_code));
        this.f9423a.d.setTextColor(this.f9423a.getResources().getColor(R.color.color_ED663F));
    }

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
        this.f9423a.d.setText("已发送 " + (j / 1000));
        this.f9423a.d.setTextColor(this.f9423a.getResources().getColor(R.color.color_6F737A));
    }
}
