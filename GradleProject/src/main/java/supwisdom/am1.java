package supwisdom;

import android.os.CountDownTimer;
import com.supwisdom.superapp.ui.activity.SecureCertificationActivity;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: SecureCertificationActivity.java */
/* JADX INFO: loaded from: classes2.dex */
public class am1 extends CountDownTimer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SecureCertificationActivity f6962a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public am1(SecureCertificationActivity secureCertificationActivity, long j, long j2) {
        super(j, j2);
        this.f6962a = secureCertificationActivity;
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        this.f6962a.d.setText(this.f6962a.getResources().getString(R.string.string_get_code));
        this.f6962a.d.setTextColor(this.f6962a.getResources().getColor(R.color.color_ED663F));
    }

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
        this.f6962a.d.setText("已发送 " + (j / 1000));
        this.f6962a.d.setTextColor(this.f6962a.getResources().getColor(R.color.color_6F737A));
    }
}
