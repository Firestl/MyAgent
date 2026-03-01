package supwisdom;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;
import supwisdom.i71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class q71 extends k71 {
    public final int g;

    public q71(Context context) {
        super(context);
        int i = i71.c.c;
        this.g = i;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, i71.c.j);
        int i2 = i71.c.i;
        layoutParams.setMargins(i2, 0, i2, i71.c.g);
        setLayoutParams(layoutParams);
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.b = gradientDrawable;
        gradientDrawable.setColor(-1);
        this.b.setStroke(i, i71.b.o);
        this.b.setCornerRadius(i71.c.b);
        setBackground(this.b);
        setTextColor(i71.b.n);
        setTextSize(16.0f);
        setGravity(17);
    }

    @Override // supwisdom.k71
    public void a(boolean z) {
        setEnabled(z);
        if (z) {
            this.b.setColor(-1);
            this.b.setStroke(this.g, i71.b.o);
            setTextColor(i71.b.n);
        } else {
            this.b.setColor(i71.b.k);
            this.b.setStroke(this.g, i71.b.o);
            setTextColor(i71.b.l);
        }
    }

    @Override // supwisdom.k71
    public void a() {
        this.b.setColor(-1);
        GradientDrawable gradientDrawable = this.b;
        int i = this.g;
        int i2 = i71.b.j;
        gradientDrawable.setStroke(i, i2);
        setTextColor(i2);
    }
}
