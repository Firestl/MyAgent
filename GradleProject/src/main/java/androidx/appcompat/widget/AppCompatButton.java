package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import androidx.appcompat.R;
import supwisdom.gc;
import supwisdom.jb;
import supwisdom.k3;
import supwisdom.m2;
import supwisdom.m3;
import supwisdom.nc;
import supwisdom.pc;
import supwisdom.u2;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatButton extends Button implements jb, gc, pc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m2 f1145a;
    public final u2 b;

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.buttonStyle);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        m2 m2Var = this.f1145a;
        if (m2Var != null) {
            m2Var.a();
        }
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.a();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (gc.C1) {
            return super.getAutoSizeMaxTextSize();
        }
        u2 u2Var = this.b;
        if (u2Var != null) {
            return u2Var.c();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (gc.C1) {
            return super.getAutoSizeMinTextSize();
        }
        u2 u2Var = this.b;
        if (u2Var != null) {
            return u2Var.d();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (gc.C1) {
            return super.getAutoSizeStepGranularity();
        }
        u2 u2Var = this.b;
        if (u2Var != null) {
            return u2Var.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (gc.C1) {
            return super.getAutoSizeTextAvailableSizes();
        }
        u2 u2Var = this.b;
        return u2Var != null ? u2Var.f() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (gc.C1) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        u2 u2Var = this.b;
        if (u2Var != null) {
            return u2Var.g();
        }
        return 0;
    }

    @Override // supwisdom.jb
    public ColorStateList getSupportBackgroundTintList() {
        m2 m2Var = this.f1145a;
        if (m2Var != null) {
            return m2Var.b();
        }
        return null;
    }

    @Override // supwisdom.jb
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        m2 m2Var = this.f1145a;
        if (m2Var != null) {
            return m2Var.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.b.h();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.b.i();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.a(z, i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        u2 u2Var = this.b;
        if (u2Var == null || gc.C1 || !u2Var.j()) {
            return;
        }
        this.b.b();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        if (gc.C1) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.a(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) throws IllegalArgumentException {
        if (gc.C1) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.a(iArr, i);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (gc.C1) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.a(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        m2 m2Var = this.f1145a;
        if (m2Var != null) {
            m2Var.b(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        m2 m2Var = this.f1145a;
        if (m2Var != null) {
            m2Var.a(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(nc.a(this, callback));
    }

    public void setSupportAllCaps(boolean z) {
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.a(z);
        }
    }

    @Override // supwisdom.jb
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        m2 m2Var = this.f1145a;
        if (m2Var != null) {
            m2Var.b(colorStateList);
        }
    }

    @Override // supwisdom.jb
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        m2 m2Var = this.f1145a;
        if (m2Var != null) {
            m2Var.a(mode);
        }
    }

    @Override // supwisdom.pc
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.b.a(colorStateList);
        this.b.a();
    }

    @Override // supwisdom.pc
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.b.a(mode);
        this.b.a();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.a(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        if (gc.C1) {
            super.setTextSize(i, f);
            return;
        }
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.a(i, f);
        }
    }

    public AppCompatButton(Context context, AttributeSet attributeSet, int i) {
        super(m3.b(context), attributeSet, i);
        k3.a(this, getContext());
        m2 m2Var = new m2(this);
        this.f1145a = m2Var;
        m2Var.a(attributeSet, i);
        u2 u2Var = new u2(this);
        this.b = u2Var;
        u2Var.a(attributeSet, i);
        this.b.a();
    }
}
