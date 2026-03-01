package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import androidx.appcompat.R;
import supwisdom.b1;
import supwisdom.jb;
import supwisdom.k3;
import supwisdom.m2;
import supwisdom.m3;
import supwisdom.n2;
import supwisdom.oc;
import supwisdom.u2;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatRadioButton extends RadioButton implements oc, jb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n2 f1153a;
    public final m2 b;
    public final u2 c;

    public AppCompatRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.radioButtonStyle);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        m2 m2Var = this.b;
        if (m2Var != null) {
            m2Var.a();
        }
        u2 u2Var = this.c;
        if (u2Var != null) {
            u2Var.a();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        n2 n2Var = this.f1153a;
        return n2Var != null ? n2Var.a(compoundPaddingLeft) : compoundPaddingLeft;
    }

    @Override // supwisdom.jb
    public ColorStateList getSupportBackgroundTintList() {
        m2 m2Var = this.b;
        if (m2Var != null) {
            return m2Var.b();
        }
        return null;
    }

    @Override // supwisdom.jb
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        m2 m2Var = this.b;
        if (m2Var != null) {
            return m2Var.c();
        }
        return null;
    }

    public ColorStateList getSupportButtonTintList() {
        n2 n2Var = this.f1153a;
        if (n2Var != null) {
            return n2Var.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        n2 n2Var = this.f1153a;
        if (n2Var != null) {
            return n2Var.c();
        }
        return null;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        m2 m2Var = this.b;
        if (m2Var != null) {
            m2Var.b(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        m2 m2Var = this.b;
        if (m2Var != null) {
            m2Var.a(i);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        n2 n2Var = this.f1153a;
        if (n2Var != null) {
            n2Var.d();
        }
    }

    @Override // supwisdom.jb
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        m2 m2Var = this.b;
        if (m2Var != null) {
            m2Var.b(colorStateList);
        }
    }

    @Override // supwisdom.jb
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        m2 m2Var = this.b;
        if (m2Var != null) {
            m2Var.a(mode);
        }
    }

    @Override // supwisdom.oc
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        n2 n2Var = this.f1153a;
        if (n2Var != null) {
            n2Var.a(colorStateList);
        }
    }

    @Override // supwisdom.oc
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        n2 n2Var = this.f1153a;
        if (n2Var != null) {
            n2Var.a(mode);
        }
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(m3.b(context), attributeSet, i);
        k3.a(this, getContext());
        n2 n2Var = new n2(this);
        this.f1153a = n2Var;
        n2Var.a(attributeSet, i);
        m2 m2Var = new m2(this);
        this.b = m2Var;
        m2Var.a(attributeSet, i);
        u2 u2Var = new u2(this);
        this.c = u2Var;
        u2Var.a(attributeSet, i);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(b1.c(getContext(), i));
    }
}
