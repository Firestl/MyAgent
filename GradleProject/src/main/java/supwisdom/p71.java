package supwisdom;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;
import supwisdom.i71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class p71 extends k71 {
    public p71(Context context) {
        super(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, i71.c.j);
        int i = i71.c.i;
        layoutParams.setMargins(i, i71.c.h, i, i71.c.g);
        setLayoutParams(layoutParams);
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.b = gradientDrawable;
        gradientDrawable.setColor(i71.b.i);
        this.b.setCornerRadius(i71.c.b);
        setBackground(this.b);
        setTextColor(-1);
        setTextSize(16.0f);
        setGravity(17);
    }

    @Override // supwisdom.k71
    public void a(boolean z) {
        setEnabled(z);
        if (z) {
            this.b.setColor(i71.b.i);
            setTextColor(-1);
        } else {
            this.b.setColor(i71.b.k);
            setTextColor(i71.b.l);
        }
    }

    @Override // supwisdom.k71
    public void a() {
        this.b.setColor(i71.b.j);
        setTextColor(-1);
    }
}
