package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: VerifyAccountDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class jj1 extends Dialog implements View.OnClickListener {
    public static String j = "phone";
    public static String k = "email";
    public static String l = "face";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f8070a;
    public TextView b;
    public TextView c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f8071e;
    public zi1 f;
    public boolean g;
    public boolean h;
    public boolean i;

    public jj1(Context context, boolean z, boolean z2, boolean z3, zi1 zi1Var) {
        super(context, R.style.Dialog);
        this.g = z;
        this.h = z2;
        this.i = z3;
        this.f = zi1Var;
        a();
    }

    public final void a() {
        Window window = getWindow();
        window.requestFeature(1);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_verifyaccount);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 80;
        window.setAttributes(attributes);
        this.f8070a = (TextView) findViewById(R.id.dialogPhone);
        this.d = (TextView) findViewById(R.id.dialogFace);
        this.b = (TextView) findViewById(R.id.dialogEmail);
        this.c = (TextView) findViewById(R.id.dialogCancel);
        this.f8071e = (TextView) findViewById(R.id.tv_safe_question);
        this.c.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.f8070a.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.f8071e.setOnClickListener(this);
        if (!fn1.Q) {
            this.d.setVisibility(8);
        }
        if (!this.h) {
            this.b.setVisibility(8);
        }
        if (!this.g) {
            this.f8070a.setVisibility(8);
        }
        if (this.i) {
            return;
        }
        this.f8071e.setVisibility(8);
    }

    public void b() {
        if (isShowing()) {
            return;
        }
        show();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.dialogPhone) {
            if (this.f != null) {
                dismiss();
                this.f.c(j);
            }
            return;
        }
        if (id == R.id.tv_safe_question) {
            if (this.f != null) {
                dismiss();
                this.f.c("question");
                return;
            }
            return;
        }
        switch (id) {
            case R.id.dialogCancel /* 2131296524 */:
                dismiss();
                break;
            case R.id.dialogEmail /* 2131296525 */:
                if (this.f != null) {
                    dismiss();
                    this.f.c(k);
                }
                break;
            case R.id.dialogFace /* 2131296526 */:
                if (this.f != null) {
                    dismiss();
                    this.f.c(l);
                }
                break;
        }
    }
}
