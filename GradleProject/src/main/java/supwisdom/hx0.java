package supwisdom;

import android.graphics.Bitmap;
import android.view.View;
import com.nostra13.dcloudimageloader.core.assist.FailReason;

/* JADX INFO: loaded from: classes2.dex */
public interface hx0 {
    void onLoadingCancelled(String str, View view);

    void onLoadingComplete(String str, View view, Bitmap bitmap);

    void onLoadingFailed(String str, View view, FailReason failReason);

    void onLoadingStarted(String str, View view);
}
