package supwisdom;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: AuthorizeInfoDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class ni1 extends Dialog implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f8531a;
    public TextView b;
    public TextView c;
    public a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f8532e;

    /* JADX INFO: compiled from: AuthorizeInfoDialog.java */
    public interface a {
        void a();

        void b();
    }

    public ni1(Context context, String str) {
        super(context, R.style.Dialog);
        this.f8532e = str;
        a();
    }

    public final void a() {
        Window window = getWindow();
        window.requestFeature(1);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_infomation);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 17;
        window.setAttributes(attributes);
        this.b = (TextView) findViewById(R.id.tv_negative);
        this.c = (TextView) findViewById(R.id.tv_positive);
        TextView textView = (TextView) findViewById(R.id.tv_url_name);
        this.f8531a = textView;
        String str = this.f8532e;
        if (str != null) {
            textView.setText(str.concat("  申请获得以下权限："));
        }
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        a aVar;
        int id = view.getId();
        if (id != R.id.tv_negative) {
            if (id == R.id.tv_positive && (aVar = this.d) != null) {
                aVar.b();
                sh1.c.a("isAgreeInfo", (Boolean) true);
                return;
            }
            return;
        }
        a aVar2 = this.d;
        if (aVar2 != null) {
            aVar2.a();
            sh1.c.a("isAgreeInfo", (Boolean) false);
        }
    }

    public void a(a aVar) {
        this.d = aVar;
    }
}
