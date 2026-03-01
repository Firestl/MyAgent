package supwisdom;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import supwisdom.i71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class x71 extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public LinearLayout f9717a;
    public TextView b;

    public x71(Context context) {
        super(context);
        setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout = new LinearLayout(context);
        this.f9717a = linearLayout;
        addView(linearLayout);
        this.f9717a.setOrientation(1);
        int i = i71.c.t;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, -2);
        layoutParams.addRule(13);
        this.f9717a.setMinimumHeight(i);
        this.f9717a.setLayoutParams(layoutParams);
        this.f9717a.setHorizontalGravity(17);
        this.f9717a.setGravity(17);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i71.b.s);
        gradientDrawable.setCornerRadius(i71.c.f7925a);
        gradientDrawable.setAlpha(204);
        this.f9717a.setBackground(gradientDrawable);
        ProgressBar progressBar = new ProgressBar(context);
        this.f9717a.addView(progressBar);
        int i2 = i71.c.m;
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i2, i2);
        int i3 = i71.c.o;
        layoutParams2.setMargins(0, i3, 0, 0);
        progressBar.setLayoutParams(layoutParams2);
        TextView textView = new TextView(context);
        this.b = textView;
        textView.setGravity(17);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.gravity = 17;
        layoutParams3.setMargins(i3, i3, i3, i3);
        this.b.setLayoutParams(layoutParams3);
        this.b.setTextColor(-1);
        this.b.setTextSize(14.0f);
        this.b.setText(i71.b.S3);
        this.f9717a.addView(this.b);
    }

    public void a(int i) {
        this.f9717a.setVisibility(i);
    }
}
