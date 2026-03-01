package supwisdom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import com.google.android.material.R;
import supwisdom.wb;

/* JADX INFO: compiled from: BaseTransientBottomBar.java */
/* JADX INFO: loaded from: classes.dex */
public class gn0 extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AccessibilityManager f7748a;
    public final wb.a b;
    public fn0 c;
    public en0 d;

    /* JADX INFO: compiled from: BaseTransientBottomBar.java */
    public class a implements wb.a {
        public a() {
        }

        @Override // supwisdom.wb.a
        public void onTouchExplorationStateChanged(boolean z) {
            gn0.this.setClickableOrFocusableBasedOnAccessibility(z);
        }
    }

    public gn0(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setClickableOrFocusableBasedOnAccessibility(boolean z) {
        setClickable(!z);
        setFocusable(z);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        en0 en0Var = this.d;
        if (en0Var != null) {
            en0Var.onViewAttachedToWindow(this);
        }
        lb.R(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        en0 en0Var = this.d;
        if (en0Var != null) {
            en0Var.onViewDetachedFromWindow(this);
        }
        wb.b(this.f7748a, this.b);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        fn0 fn0Var = this.c;
        if (fn0Var != null) {
            fn0Var.a(this, i, i2, i3, i4);
        }
    }

    public void setOnAttachStateChangeListener(en0 en0Var) {
        this.d = en0Var;
    }

    public void setOnLayoutChangeListener(fn0 fn0Var) {
        this.c = fn0Var;
    }

    public gn0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SnackbarLayout);
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SnackbarLayout_elevation)) {
            lb.a(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
        }
        typedArrayObtainStyledAttributes.recycle();
        this.f7748a = (AccessibilityManager) context.getSystemService("accessibility");
        a aVar = new a();
        this.b = aVar;
        wb.a(this.f7748a, aVar);
        setClickableOrFocusableBasedOnAccessibility(this.f7748a.isTouchExplorationEnabled());
    }
}
