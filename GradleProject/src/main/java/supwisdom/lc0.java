package supwisdom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.RecentlyNonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public class lc0 extends yc {
    public Dialog l;
    public DialogInterface.OnCancelListener m;
    public Dialog n;

    @Override // supwisdom.yc
    public Dialog a(Bundle bundle) {
        Dialog dialog = this.l;
        if (dialog != null) {
            return dialog;
        }
        a(false);
        if (this.n == null) {
            this.n = new AlertDialog.Builder(getActivity()).create();
        }
        return this.n;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(@RecentlyNonNull DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.m;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    public static lc0 a(@RecentlyNonNull Dialog dialog, DialogInterface.OnCancelListener onCancelListener) {
        lc0 lc0Var = new lc0();
        pf0.a(dialog, "Cannot display null dialog");
        Dialog dialog2 = dialog;
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        lc0Var.l = dialog2;
        if (onCancelListener != null) {
            lc0Var.m = onCancelListener;
        }
        return lc0Var;
    }

    @Override // supwisdom.yc
    public void a(@RecentlyNonNull dd ddVar, String str) {
        super.a(ddVar, str);
    }
}
