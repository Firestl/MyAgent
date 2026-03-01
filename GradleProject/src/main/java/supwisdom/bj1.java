package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: PermissionHintDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class bj1 extends Dialog {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f7073a;
    public String b;

    public bj1(Context context, String str, String str2) {
        super(context, R.style.Dialog);
        this.f7073a = str;
        this.b = str2;
        a();
    }

    public final void a() {
        Window window = getWindow();
        window.requestFeature(1);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_permission_hint);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 48;
        window.setAttributes(attributes);
        TextView textView = (TextView) findViewById(R.id.tv_hint_title);
        TextView textView2 = (TextView) findViewById(R.id.tv_hint_content);
        textView.setText(this.f7073a);
        textView2.setText(this.b);
    }
}
