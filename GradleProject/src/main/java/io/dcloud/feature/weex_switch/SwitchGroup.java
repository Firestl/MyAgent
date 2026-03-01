package io.dcloud.feature.weex_switch;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes3.dex */
public class SwitchGroup extends LinearLayout {
    public int i;
    public static final int DEFAULT_WIDTH = dp2pxInt(58.0f);
    public static final int DEFAULT_HEIGHT = dp2pxInt(36.0f);

    public SwitchGroup(Context context) {
        super(context);
        this.i = 0;
        addView(new SwitchButton(context), new LinearLayout.LayoutParams(-1, -1));
    }

    public static float dp2px(float f) {
        return TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
    }

    public static int dp2pxInt(float f) {
        return (int) dp2px(f);
    }

    public void disableClipOnParents(View view) {
        int i;
        if (view.getParent() == null) {
            return;
        }
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).setClipChildren(false);
        }
        if (!(view.getParent() instanceof View) || (i = this.i) >= 2) {
            return;
        }
        this.i = i + 1;
        disableClipOnParents((View) view.getParent());
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            SwitchButton switchButton = (SwitchButton) getChildAt(i5);
            switchButton.layout(0, 0, switchButton.getMeasuredWidth(), ((int) (switchButton.getMeasuredHeight() + switchButton.getShadowBottomSize())) + 0);
        }
        disableClipOnParents(this);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode == 0 || mode == Integer.MIN_VALUE) {
            i = View.MeasureSpec.makeMeasureSpec(DEFAULT_WIDTH, 1073741824);
        }
        if (mode2 == 0 || mode2 == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec(DEFAULT_HEIGHT, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public SwitchGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = 0;
    }

    public SwitchGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = 0;
    }
}
