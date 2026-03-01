package supwisdom;

import android.annotation.TargetApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import com.sangfor.sdk.common.ui.Sangfor_b.Sangfor_i;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class y81 implements u81 {
    @Override // supwisdom.u81
    @TargetApi(19)
    public void a(Window window, int i) {
        window.addFlags(67108864);
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        View viewFindViewWithTag = viewGroup.findViewWithTag("ghStatusBarView");
        if (viewFindViewWithTag == null) {
            viewFindViewWithTag = new Sangfor_i(window.getContext());
            viewFindViewWithTag.setTag("ghStatusBarView");
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 48;
            viewFindViewWithTag.setLayoutParams(layoutParams);
            viewGroup.addView(viewFindViewWithTag);
        }
        viewFindViewWithTag.setBackgroundColor(i);
        w81.a(window, true);
    }
}
