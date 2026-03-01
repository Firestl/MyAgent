package supwisdom;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import supwisdom.i71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class i81 extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ImageView f7929a;
    public n71 b;
    public n71 c;
    public j71 d;

    public i81(Context context) {
        super(context);
        setLayoutParams(new LinearLayout.LayoutParams(-1, v71.a(64.0f)));
        View view = new View(context);
        view.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i71.b.g);
        gradientDrawable.setCornerRadius(i71.c.f7925a);
        gradientDrawable.setStroke(i71.c.c, i71.b.m);
        view.setBackground(gradientDrawable);
        addView(view);
        this.f7929a = new ImageView(context);
        int i = i71.c.h;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.addRule(9);
        layoutParams.addRule(15);
        int i2 = i71.c.g;
        layoutParams.setMargins(i2, 0, 0, 0);
        this.f7929a.setLayoutParams(layoutParams);
        this.f7929a.setImageBitmap(v71.a(context, "android.png"));
        addView(this.f7929a);
        this.b = new n71(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(10);
        int i3 = i71.c.n;
        int i4 = i71.c.o;
        layoutParams2.setMargins(i3, i4, i3, 0);
        this.b.setLayoutParams(layoutParams2);
        this.b.a(i71.b.l4);
        addView(this.b);
        this.c = new n71(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(12);
        layoutParams3.setMargins(i3, 0, i3, i4);
        this.c.setLayoutParams(layoutParams3);
        this.c.a(i71.b.m4);
        addView(this.c);
        this.d = new j71(context);
        int i5 = i71.c.f7926e;
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(i5, i5);
        layoutParams4.addRule(11);
        layoutParams4.addRule(15);
        layoutParams4.setMargins(0, 0, i2, 0);
        this.d.setLayoutParams(layoutParams4);
        addView(this.d);
    }
}
