package supwisdom;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import supwisdom.i71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class m71 extends EditText implements View.OnFocusChangeListener {
    public m71(Context context, String str, int i, boolean z) {
        super(context);
        setHint(str);
        setHintTextColor(i71.b.q);
        setTextSize(1, 12.0f);
        setTextColor(-16777216);
        setPadding(i71.c.f, 0, 0, 0);
        if (z) {
            setInputType(129);
        } else {
            setInputType(1);
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        gradientDrawable.setCornerRadius(i71.c.f7925a);
        gradientDrawable.setStroke(i71.c.c, i71.b.m);
        setBackground(gradientDrawable);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, i71.c.m);
        int i2 = i71.c.i;
        layoutParams.setMargins(i2, i, i2, 0);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        setOnFocusChangeListener(this);
    }

    public final boolean a(EditText editText) {
        int scrollY = editText.getScrollY();
        int height = editText.getLayout().getHeight() - ((editText.getHeight() - editText.getCompoundPaddingTop()) - editText.getCompoundPaddingBottom());
        if (height == 0) {
            return false;
        }
        return scrollY > 0 || scrollY < height - 1;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        gradientDrawable.setCornerRadius(i71.c.f7925a);
        if (z) {
            gradientDrawable.setStroke(i71.c.c, i71.b.p);
        } else {
            gradientDrawable.setStroke(i71.c.c, i71.b.m);
        }
        view.setBackground(gradientDrawable);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (a(this)) {
            getParent().requestDisallowInterceptTouchEvent(true);
            if (motionEvent.getAction() == 1) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.onTouchEvent(motionEvent);
    }
}
