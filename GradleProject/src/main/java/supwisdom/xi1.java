package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: NoticeLoginDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class xi1 extends Dialog implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f9768a;
    public a b;
    public String c;
    public TextView d;

    /* JADX INFO: compiled from: NoticeLoginDialog.java */
    public interface a {
        void a();
    }

    public xi1(Context context, String str) {
        super(context, R.style.Dialog);
        this.c = "";
        this.c = str;
        a();
    }

    public final void a() {
        Window window = getWindow();
        window.requestFeature(1);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_login_notice);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 17;
        window.setAttributes(attributes);
        this.f9768a = (TextView) findViewById(R.id.tv_confirm);
        TextView textView = (TextView) findViewById(R.id.tv_notice_content);
        this.d = textView;
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.d.setMovementMethod(LinkMovementMethod.getInstance());
        String str = this.c;
        if (str != null && !"".equals(str)) {
            this.d.setText(Html.fromHtml(this.c));
        }
        Linkify.addLinks(this.d, 1);
        this.f9768a.setOnClickListener(this);
        this.d.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.tv_confirm) {
            return;
        }
        dismiss();
        a aVar = this.b;
        if (aVar != null) {
            aVar.a();
        }
    }
}
