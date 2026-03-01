package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/* JADX INFO: compiled from: DialogFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class yc extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Handler f9869a;
    public Runnable b = new a();
    public int c = 0;
    public int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9870e = true;
    public boolean f = true;
    public int g = -1;
    public Dialog h;
    public boolean i;
    public boolean j;
    public boolean k;

    /* JADX INFO: compiled from: DialogFragment.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            yc ycVar = yc.this;
            Dialog dialog = ycVar.h;
            if (dialog != null) {
                ycVar.onDismiss(dialog);
            }
        }
    }

    public Dialog a(Bundle bundle) {
        throw null;
    }

    public void a(dd ddVar, String str) {
        this.j = false;
        this.k = true;
        hd hdVarA = ddVar.a();
        hdVarA.a(this, str);
        hdVarA.a();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        Bundle bundle2;
        super.onActivityCreated(bundle);
        if (this.f) {
            View view = getView();
            if (view != null) {
                if (view.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                this.h.setContentView(view);
            }
            FragmentActivity activity = getActivity();
            if (activity != null) {
                this.h.setOwnerActivity(activity);
            }
            this.h.setCancelable(this.f9870e);
            this.h.setOnCancelListener(this);
            this.h.setOnDismissListener(this);
            if (bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
                return;
            }
            this.h.onRestoreInstanceState(bundle2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.k) {
            return;
        }
        this.j = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9869a = new Handler();
        this.f = this.mContainerId == 0;
        if (bundle != null) {
            this.c = bundle.getInt("android:style", 0);
            this.d = bundle.getInt("android:theme", 0);
            this.f9870e = bundle.getBoolean("android:cancelable", true);
            this.f = bundle.getBoolean("android:showsDialog", this.f);
            this.g = bundle.getInt("android:backStackId", -1);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Dialog dialog = this.h;
        if (dialog != null) {
            this.i = true;
            dialog.setOnDismissListener(null);
            this.h.dismiss();
            if (!this.j) {
                onDismiss(this.h);
            }
            this.h = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        if (this.k || this.j) {
            return;
        }
        this.j = true;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.i) {
            return;
        }
        a(true, true);
    }

    @Override // androidx.fragment.app.Fragment
    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        if (!this.f) {
            return super.onGetLayoutInflater(bundle);
        }
        Dialog dialogA = a(bundle);
        this.h = dialogA;
        if (dialogA == null) {
            return (LayoutInflater) this.mHost.c().getSystemService("layout_inflater");
        }
        a(dialogA, this.c);
        return (LayoutInflater) this.h.getContext().getSystemService("layout_inflater");
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        Bundle bundleOnSaveInstanceState;
        super.onSaveInstanceState(bundle);
        Dialog dialog = this.h;
        if (dialog != null && (bundleOnSaveInstanceState = dialog.onSaveInstanceState()) != null) {
            bundle.putBundle("android:savedDialogState", bundleOnSaveInstanceState);
        }
        int i = this.c;
        if (i != 0) {
            bundle.putInt("android:style", i);
        }
        int i2 = this.d;
        if (i2 != 0) {
            bundle.putInt("android:theme", i2);
        }
        boolean z = this.f9870e;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.f;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i3 = this.g;
        if (i3 != -1) {
            bundle.putInt("android:backStackId", i3);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = this.h;
        if (dialog != null) {
            this.i = false;
            dialog.show();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Dialog dialog = this.h;
        if (dialog != null) {
            dialog.hide();
        }
    }

    public void a(boolean z, boolean z2) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.k = false;
        Dialog dialog = this.h;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.h.dismiss();
            if (!z2) {
                if (Looper.myLooper() == this.f9869a.getLooper()) {
                    onDismiss(this.h);
                } else {
                    this.f9869a.post(this.b);
                }
            }
        }
        this.i = true;
        if (this.g >= 0) {
            requireFragmentManager().a(this.g, 1);
            this.g = -1;
            return;
        }
        hd hdVarA = requireFragmentManager().a();
        hdVarA.c(this);
        if (z) {
            hdVarA.b();
        } else {
            hdVarA.a();
        }
    }

    public void a(boolean z) {
        this.f = z;
    }

    public void a(Dialog dialog, int i) {
        if (i != 1 && i != 2) {
            if (i != 3) {
                return;
            } else {
                dialog.getWindow().addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }
}
