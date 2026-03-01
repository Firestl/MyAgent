package supwisdom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class dv extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7385a;

    public dv(Context context) {
        super(context);
        this.f7385a = 0;
    }

    public void setStatusBarHeight(int i) {
        this.f7385a = i;
        setMeasuredDimension(-1, i);
        if (getLayoutParams() == null) {
            setLayoutParams(new ViewGroup.MarginLayoutParams(-1, this.f7385a));
        }
    }
}
