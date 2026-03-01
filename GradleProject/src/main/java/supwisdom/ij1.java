package supwisdom;

import android.app.Dialog;
import android.view.View;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: ShareDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class ij1 extends Dialog implements View.OnClickListener {
    public static String b = "微信";
    public static String c = "朋友圈";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f7960a;

    /* JADX INFO: compiled from: ShareDialog.java */
    public interface a {
        void a(String str);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f7960a != null) {
            switch (view.getId()) {
                case R.id.tv_wechat /* 2131297290 */:
                    this.f7960a.a(b);
                    dismiss();
                    break;
                case R.id.tv_wechat_circle /* 2131297291 */:
                    this.f7960a.a(c);
                    dismiss();
                    break;
            }
        }
    }
}
