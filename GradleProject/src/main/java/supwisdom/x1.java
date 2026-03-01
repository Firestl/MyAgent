package supwisdom;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.R;
import supwisdom.c2;
import supwisdom.r0;

/* JADX INFO: compiled from: MenuDialogHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class x1 implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, c2.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public w1 f9696a;
    public r0 b;
    public u1 c;
    public c2.a d;

    public x1(w1 w1Var) {
        this.f9696a = w1Var;
    }

    public void a(IBinder iBinder) {
        w1 w1Var = this.f9696a;
        r0.a aVar = new r0.a(w1Var.e());
        u1 u1Var = new u1(aVar.b(), R.layout.abc_list_menu_item_layout);
        this.c = u1Var;
        u1Var.a(this);
        this.f9696a.a(this.c);
        aVar.a(this.c.c(), this);
        View viewI = w1Var.i();
        if (viewI != null) {
            aVar.a(viewI);
        } else {
            aVar.a(w1Var.g());
            aVar.b(w1Var.h());
        }
        aVar.a(this);
        r0 r0VarA = aVar.a();
        this.b = r0VarA;
        r0VarA.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.b.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.b.show();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.f9696a.a((y1) this.c.c().getItem(i), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.c.a(this.f9696a, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.b.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.b.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f9696a.a(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f9696a.performShortcut(i, keyEvent, 0);
    }

    public void a() {
        r0 r0Var = this.b;
        if (r0Var != null) {
            r0Var.dismiss();
        }
    }

    @Override // supwisdom.c2.a
    public void a(w1 w1Var, boolean z) {
        if (z || w1Var == this.f9696a) {
            a();
        }
        c2.a aVar = this.d;
        if (aVar != null) {
            aVar.a(w1Var, z);
        }
    }

    @Override // supwisdom.c2.a
    public boolean a(w1 w1Var) {
        c2.a aVar = this.d;
        if (aVar != null) {
            return aVar.a(w1Var);
        }
        return false;
    }
}
