package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import supwisdom.b1;
import supwisdom.k3;
import supwisdom.m3;
import supwisdom.nc;
import supwisdom.p2;
import supwisdom.p3;
import supwisdom.u2;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatCheckedTextView extends CheckedTextView {
    public static final int[] b = {R.attr.checkMark};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u2 f1147a;

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkedTextViewStyle);
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        u2 u2Var = this.f1147a;
        if (u2Var != null) {
            u2Var.a();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        p2.a(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(b1.c(getContext(), i));
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(nc.a(this, callback));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        u2 u2Var = this.f1147a;
        if (u2Var != null) {
            u2Var.a(context, i);
        }
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet, int i) {
        super(m3.b(context), attributeSet, i);
        k3.a(this, getContext());
        u2 u2Var = new u2(this);
        this.f1147a = u2Var;
        u2Var.a(attributeSet, i);
        this.f1147a.a();
        p3 p3VarA = p3.a(getContext(), attributeSet, b, i, 0);
        setCheckMarkDrawable(p3VarA.b(0));
        p3VarA.b();
    }
}
