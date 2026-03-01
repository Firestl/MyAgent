package supwisdom;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.R;
import androidx.appcompat.app.AlertController;

/* JADX INFO: compiled from: AlertDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class r0 extends u0 implements DialogInterface {
    public final AlertController c;

    /* JADX INFO: compiled from: AlertDialog.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AlertController.f f8992a;
        public final int b;

        public a(Context context) {
            this(context, r0.b(context, 0));
        }

        public a a(View view) {
            this.f8992a.g = view;
            return this;
        }

        public Context b() {
            return this.f8992a.f1090a;
        }

        public a(Context context, int i) {
            this.f8992a = new AlertController.f(new ContextThemeWrapper(context, r0.b(context, i)));
            this.b = i;
        }

        public a a(CharSequence charSequence) {
            this.f8992a.h = charSequence;
            return this;
        }

        public a b(CharSequence charSequence) {
            this.f8992a.f = charSequence;
            return this;
        }

        public a a(Drawable drawable) {
            this.f8992a.d = drawable;
            return this;
        }

        public a a(int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f8992a;
            fVar.i = fVar.f1090a.getText(i);
            this.f8992a.k = onClickListener;
            return this;
        }

        public a a(DialogInterface.OnKeyListener onKeyListener) {
            this.f8992a.u = onKeyListener;
            return this;
        }

        public a a(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f8992a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            return this;
        }

        public a a(ListAdapter listAdapter, int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.f8992a;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            fVar.I = i;
            fVar.H = true;
            return this;
        }

        public r0 a() {
            r0 r0Var = new r0(this.f8992a.f1090a, this.b);
            this.f8992a.a(r0Var.c);
            r0Var.setCancelable(this.f8992a.r);
            if (this.f8992a.r) {
                r0Var.setCanceledOnTouchOutside(true);
            }
            r0Var.setOnCancelListener(this.f8992a.s);
            r0Var.setOnDismissListener(this.f8992a.t);
            DialogInterface.OnKeyListener onKeyListener = this.f8992a.u;
            if (onKeyListener != null) {
                r0Var.setOnKeyListener(onKeyListener);
            }
            return r0Var;
        }
    }

    public r0(Context context, int i) {
        super(context, b(context, i));
        this.c = new AlertController(getContext(), this, getWindow());
    }

    public static int b(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    @Override // supwisdom.u0, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c.b();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.c.a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.c.b(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // supwisdom.u0, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.c.b(charSequence);
    }

    public ListView b() {
        return this.c.a();
    }
}
