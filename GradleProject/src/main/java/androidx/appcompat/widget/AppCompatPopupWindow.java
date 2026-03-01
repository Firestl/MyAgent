package androidx.appcompat.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.R;
import supwisdom.mc;
import supwisdom.p3;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatPopupWindow extends PopupWindow {
    public static final boolean b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1152a;

    static {
        b = Build.VERSION.SDK_INT < 21;
    }

    public AppCompatPopupWindow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i, 0);
    }

    public final void a(Context context, AttributeSet attributeSet, int i, int i2) {
        p3 p3VarA = p3.a(context, attributeSet, R.styleable.PopupWindow, i, i2);
        if (p3VarA.g(R.styleable.PopupWindow_overlapAnchor)) {
            a(p3VarA.a(R.styleable.PopupWindow_overlapAnchor, false));
        }
        setBackgroundDrawable(p3VarA.b(R.styleable.PopupWindow_android_popupBackground));
        p3VarA.b();
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2) {
        if (b && this.f1152a) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i, int i2, int i3, int i4) {
        if (b && this.f1152a) {
            i2 -= view.getHeight();
        }
        super.update(view, i, i2, i3, i4);
    }

    public AppCompatPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i, i2);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (b && this.f1152a) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }

    public final void a(boolean z) {
        if (b) {
            this.f1152a = z;
        } else {
            mc.a(this, z);
        }
    }
}
