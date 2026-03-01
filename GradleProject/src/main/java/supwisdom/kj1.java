package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: WarningDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class kj1 extends Dialog {
    public kj1(Context context, int i) {
        super(context, i);
        a();
    }

    public final void a() {
        Window window = getWindow();
        window.requestFeature(1);
        setContentView(R.layout.warning_dialog);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 17;
        window.setAttributes(attributes);
    }

    public void b() {
        if (isShowing()) {
            return;
        }
        show();
    }
}
