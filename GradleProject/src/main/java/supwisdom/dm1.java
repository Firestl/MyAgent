package supwisdom;

import android.os.CountDownTimer;
import com.supwisdom.superapp.ui.activity.VertificateActivity;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: VertificateActivity.java */
/* JADX INFO: loaded from: classes2.dex */
public class dm1 extends CountDownTimer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ VertificateActivity f7365a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public dm1(VertificateActivity vertificateActivity, long j, long j2) {
        super(j, j2);
        this.f7365a = vertificateActivity;
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        this.f7365a.g.setText(this.f7365a.getResources().getString(R.string.send_code));
        this.f7365a.g.setTextColor(this.f7365a.getResources().getColor(R.color.color_ED663F));
    }

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
        this.f7365a.g.setText("已发送 " + (j / 1000));
        this.f7365a.g.setTextColor(this.f7365a.getResources().getColor(R.color.color_6F737A));
    }
}
