package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import androidx.appcompat.R;
import supwisdom.jb;
import supwisdom.k3;
import supwisdom.m2;
import supwisdom.m3;
import supwisdom.nc;
import supwisdom.p2;
import supwisdom.t2;
import supwisdom.u2;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatEditText extends EditText implements jb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m2 f1148a;
    public final u2 b;
    public final t2 c;

    public AppCompatEditText(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        m2 m2Var = this.f1148a;
        if (m2Var != null) {
            m2Var.a();
        }
        u2 u2Var = this.b;
        if (u2Var != null) {
            u2Var.a();
        }
    }

    @Override // supwisdom.jb
    public ColorStateList getSupportBackgroundTintList() {
        m2 m2Var = this.f1148a;
        if (m2Var != null) {
            return m2Var.b();
        }
        return null;
    }

    @Override // supwisdom.jb
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        m2 m2Var = this.f1148a;
        if (m2Var != null) {
            return m2Var.c();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        t2 t2Var;
        return (Build.VERSION.SDK_INT >= 28 || (t2Var = this.c) == null) ? super.getTextClassifier() : t2Var.a();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        p2.a(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        m2 m2Var = this.f1148a;
        if (m2Var != null) {
            m2Var.b(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        m2 m2Var = this.f1148a;
        if (m2Var != null) {
            m2Var.a(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(nc.a(this, callback));
    }

    @Override // supwisdom.jb
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        m2 m2Var = this.f1148a;
        if (m2Var != null) {
            m2Var.b(colorStateList);
        }
    }

    @Override // supwisdom.jb
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        m2 m2Var = this.f1148a;
        if (m2Var != null) {
            m2Var.a(mode);
        }
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

    public AppCompatEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.editTextStyle);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet, int i) {
        super(m3.b(context), attributeSet, i);
        k3.a(this, getContext());
        m2 m2Var = new m2(this);
        this.f1148a = m2Var;
        m2Var.a(attributeSet, i);
        u2 u2Var = new u2(this);
        this.b = u2Var;
        u2Var.a(attributeSet, i);
        this.b.a();
        this.c = new t2(this);
    }
}
