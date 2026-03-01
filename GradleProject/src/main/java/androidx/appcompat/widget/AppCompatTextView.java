package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import supwisdom.b1;
import supwisdom.ba;
import supwisdom.gc;
import supwisdom.jb;
import supwisdom.k3;
import supwisdom.m2;
import supwisdom.m3;
import supwisdom.nc;
import supwisdom.p2;
import supwisdom.pc;
import supwisdom.q8;
import supwisdom.t2;
import supwisdom.u2;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatTextView extends TextView implements jb, pc, gc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m2 f1165a;
    public final u2 b;
    public final t2 c;
    public Future<ba> d;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    public final void d() {
        Future<ba> future = this.d;
        if (future != null) {
            try {
                this.d = null;
                nc.a(this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        m2 m2Var = this.f1165a;
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

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return nc.b(this);
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return nc.c(this);
    }

    @Override // supwisdom.jb
    public ColorStateList getSupportBackgroundTintList() {
        m2 m2Var = this.f1165a;
        if (m2Var != null) {
            return m2Var.b();
        }
        return null;
    }

    @Override // supwisdom.jb
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        m2 m2Var = this.f1165a;
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

    @Override // android.widget.TextView
    public CharSequence getText() {
        d();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        t2 t2Var;
        return (Build.VERSION.SDK_INT >= 28 || (t2Var = this.c) == null) ? super.getTextClassifier() : t2Var.a();
    }

    public ba.a getTextMetricsParamsCompat() {
        return nc.f(this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        p2.a(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.a(z, i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        d();
        super.onMeasure(i, i2);
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
        m2 m2Var = this.f1165a;
        if (m2Var != null) {
            m2Var.b(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        m2 m2Var = this.f1165a;
        if (m2Var != null) {
            m2Var.a(i);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.k();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.k();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.k();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.k();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(nc.a(this, callback));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            nc.a(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            nc.b(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i) {
        nc.c(this, i);
    }

    public void setPrecomputedText(ba baVar) {
        nc.a(this, baVar);
    }

    @Override // supwisdom.jb
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        m2 m2Var = this.f1165a;
        if (m2Var != null) {
            m2Var.b(colorStateList);
        }
    }

    @Override // supwisdom.jb
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        m2 m2Var = this.f1165a;
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
    public void setTextClassifier(TextClassifier textClassifier) {
        t2 t2Var;
        if (Build.VERSION.SDK_INT >= 28 || (t2Var = this.c) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            t2Var.a(textClassifier);
        }
    }

    public void setTextFuture(Future<ba> future) {
        this.d = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(ba.a aVar) {
        nc.a(this, aVar);
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

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i) {
        Typeface typefaceA = (typeface == null || i <= 0) ? null : q8.a(getContext(), typeface, i);
        if (typefaceA != null) {
            typeface = typefaceA;
        }
        super.setTypeface(typeface, i);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int i) {
        super(m3.b(context), attributeSet, i);
        k3.a(this, getContext());
        m2 m2Var = new m2(this);
        this.f1165a = m2Var;
        m2Var.a(attributeSet, i);
        u2 u2Var = new u2(this);
        this.b = u2Var;
        u2Var.a(attributeSet, i);
        this.b.a();
        this.c = new t2(this);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i != 0 ? b1.c(context, i) : null, i2 != 0 ? b1.c(context, i2) : null, i3 != 0 ? b1.c(context, i3) : null, i4 != 0 ? b1.c(context, i4) : null);
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.k();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i != 0 ? b1.c(context, i) : null, i2 != 0 ? b1.c(context, i2) : null, i3 != 0 ? b1.c(context, i3) : null, i4 != 0 ? b1.c(context, i4) : null);
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.k();
        }
    }
}
