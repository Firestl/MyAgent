package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: SecurityCertificationDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class gj1 extends Dialog implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f7741a;
    public TextView b;
    public zi1 c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f7742e;
    public Boolean f;
    public Boolean g;
    public Boolean h;

    public gj1(Context context, zi1 zi1Var, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4) {
        super(context, R.style.Dialog);
        this.c = zi1Var;
        this.f = bool;
        this.g = bool2;
        this.h = bool4;
        a();
    }

    public final void a() {
        Window window = getWindow();
        window.requestFeature(1);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_certification_platform);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 80;
        window.setAttributes(attributes);
        this.b = (TextView) findViewById(R.id.dialogPhone);
        this.d = (TextView) findViewById(R.id.dialogEmail);
        this.f7742e = (TextView) findViewById(R.id.dialogPlatform);
        this.f7741a = (TextView) findViewById(R.id.dialogCancel);
        this.b.setVisibility(this.f.booleanValue() ? 0 : 8);
        this.d.setVisibility(this.g.booleanValue() ? 0 : 8);
        this.f7742e.setVisibility(this.h.booleanValue() ? 0 : 8);
        this.f7741a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.f7742e.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialogCancel /* 2131296524 */:
                dismiss();
                break;
            case R.id.dialogEmail /* 2131296525 */:
                if (this.c != null) {
                    dismiss();
                    this.c.c("email");
                }
                break;
            case R.id.dialogPhone /* 2131296528 */:
                if (this.c != null) {
                    dismiss();
                    this.c.c("phone");
                }
                break;
            case R.id.dialogPlatform /* 2131296529 */:
                if (this.c != null) {
                    dismiss();
                    this.c.c("platform");
                }
                break;
        }
    }
}
