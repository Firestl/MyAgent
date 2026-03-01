package supwisdom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.supwisdom.zueb.R;
import io.dcloud.feature.sdk.Interface.IDCUniMPAppSplashView;

/* JADX INFO: compiled from: SplashView.java */
/* JADX INFO: loaded from: classes2.dex */
public class cm1 implements IDCUniMPAppSplashView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public FrameLayout f7231a;

    @Override // io.dcloud.feature.sdk.Interface.IDCUniMPAppSplashView
    public View getSplashView(Context context, String str, String str2, String str3) {
        this.f7231a = new FrameLayout(context);
        ImageView imageView = new ImageView(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.drawable.lauch);
        this.f7231a.addView(imageView, layoutParams);
        return this.f7231a;
    }

    @Override // io.dcloud.feature.sdk.Interface.IDCUniMPAppSplashView
    public void onCloseSplash(ViewGroup viewGroup) {
        if (viewGroup != null) {
            viewGroup.removeView(this.f7231a);
        }
    }
}
