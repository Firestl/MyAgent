package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: LoginLoadingDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class vi1 extends Dialog {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f9514a;

    public vi1(Context context) {
        super(context, R.style.MyDialogStyle);
        this.f9514a = context;
        a();
    }

    public final void a() {
        Window window = getWindow();
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_loading_login);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = a(this.f9514a, 150.0f);
        attributes.width = a(this.f9514a, 150.0f);
        attributes.gravity = 17;
        window.setAttributes(attributes);
    }

    public int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
