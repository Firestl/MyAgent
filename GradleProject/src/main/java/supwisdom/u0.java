package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R;
import supwisdom.i1;
import supwisdom.ta;

/* JADX INFO: compiled from: AppCompatDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class u0 extends Dialog implements s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t0 f9344a;
    public final ta.a b;

    /* JADX INFO: compiled from: AppCompatDialog.java */
    public class a implements ta.a {
        public a() {
        }

        @Override // supwisdom.ta.a
        public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
            return u0.this.a(keyEvent);
        }
    }

    public u0(Context context, int i) {
        super(context, a(context, i));
        this.b = new a();
        t0 t0VarA = a();
        t0VarA.d(a(context, i));
        t0VarA.a((Bundle) null);
    }

    public boolean a(int i) {
        return a().b(i);
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().a(view, layoutParams);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        a().g();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return ta.a(this.b, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i) {
        return (T) a().a(i);
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        a().f();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        a().e();
        super.onCreate(bundle);
        a().a(bundle);
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        a().j();
    }

    @Override // supwisdom.s0
    public void onSupportActionModeFinished(i1 i1Var) {
    }

    @Override // supwisdom.s0
    public void onSupportActionModeStarted(i1 i1Var) {
    }

    @Override // supwisdom.s0
    public i1 onWindowStartingSupportActionMode(i1.a aVar) {
        return null;
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        a().c(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        a().a(charSequence);
    }

    public t0 a() {
        if (this.f9344a == null) {
            this.f9344a = t0.a(this, this);
        }
        return this.f9344a;
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        a().a(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().b(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        a().a(getContext().getString(i));
    }

    public static int a(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public boolean a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
