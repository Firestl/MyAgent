package supwisdom;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: InfoSecurityDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class ti1 extends Dialog implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Boolean f9295a;
    public Boolean b;
    public int c;
    public a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f9296e;
    public View f;
    public TextView g;
    public TextView h;

    /* JADX INFO: compiled from: InfoSecurityDialog.java */
    public interface a {
        void a();
    }

    public ti1(Context context, Boolean bool, Boolean bool2, int i) {
        super(context, R.style.Dialog);
        this.b = bool;
        this.f9295a = bool2;
        this.c = i;
        a();
    }

    public final void a() {
        Window window = getWindow();
        window.requestFeature(1);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_info_security);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 17;
        window.setAttributes(attributes);
        this.f9296e = (TextView) findViewById(R.id.tv_positive);
        this.f = findViewById(R.id.tv_negative);
        this.g = (TextView) findViewById(R.id.tv_remind);
        this.h = (TextView) findViewById(R.id.tv_info);
        if (this.b.booleanValue() && this.f9295a.booleanValue()) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(8);
        }
        this.g.setVisibility(this.c == 0 ? 8 : 0);
        this.f9296e.setOnClickListener(this);
        this.f.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        a aVar;
        int id = view.getId();
        if (id == R.id.tv_negative) {
            dismiss();
        } else if (id == R.id.tv_positive && (aVar = this.d) != null) {
            aVar.a();
        }
    }

    public void a(a aVar) {
        this.d = aVar;
    }
}
