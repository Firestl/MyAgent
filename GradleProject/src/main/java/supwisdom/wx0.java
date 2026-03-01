package supwisdom;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.nostra13.dcloudimageloader.core.assist.ViewScaleType;

/* JADX INFO: loaded from: classes2.dex */
public interface wx0 {
    View a();

    boolean a(Drawable drawable);

    boolean b();

    ViewScaleType c();

    int getHeight();

    int getId();

    int getWidth();

    boolean setImageBitmap(Bitmap bitmap);
}
