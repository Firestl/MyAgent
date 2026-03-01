package supwisdom;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import supwisdom.i71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class j71 extends ImageView {
    public j71(Context context) {
        super(context);
        int i = i71.c.f7926e;
        setLayoutParams(new LinearLayout.LayoutParams(i, i));
        setImageBitmap(v71.a(context, "uncheck.png"));
        setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    public void a(boolean z) {
        if (z) {
            setImageBitmap(v71.a(getContext(), "checked.png"));
        } else {
            setImageBitmap(v71.a(getContext(), "uncheck.png"));
        }
    }
}
