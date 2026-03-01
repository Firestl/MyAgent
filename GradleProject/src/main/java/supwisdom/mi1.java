package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.supwisdom.superapp.entity.response.ActivieInformationResponse;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: ActiveAccountDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class mi1 extends Dialog implements View.OnClickListener {
    public static String f = "alipay";
    public static String g = "face";
    public static String h = "phone";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f8396a;
    public TextView b;
    public TextView c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public zi1 f8397e;

    public mi1(Context context, zi1 zi1Var) {
        super(context, R.style.Dialog);
        this.f8397e = zi1Var;
        a();
    }

    public final void a() {
        Window window = getWindow();
        window.requestFeature(1);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_activeaccount);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 80;
        window.setAttributes(attributes);
        this.b = (TextView) findViewById(R.id.dialogAlipay);
        this.f8396a = (TextView) findViewById(R.id.dialogFace);
        this.d = (TextView) findViewById(R.id.dialogPhone);
        TextView textView = (TextView) findViewById(R.id.dialogCancel);
        this.c = textView;
        textView.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.f8396a.setOnClickListener(this);
        this.d.setOnClickListener(this);
    }

    public void b() {
        if (isShowing()) {
            return;
        }
        show();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialogAlipay /* 2131296523 */:
                if (this.f8397e != null) {
                    dismiss();
                    this.f8397e.c(f);
                }
                break;
            case R.id.dialogCancel /* 2131296524 */:
                dismiss();
                break;
            case R.id.dialogFace /* 2131296526 */:
                if (this.f8397e != null) {
                    dismiss();
                    this.f8397e.c(g);
                }
                break;
            case R.id.dialogPhone /* 2131296528 */:
                if (this.f8397e != null) {
                    dismiss();
                    this.f8397e.c(h);
                }
                break;
        }
    }

    public void a(ActivieInformationResponse activieInformationResponse) {
        if (activieInformationResponse != null) {
            if (!activieInformationResponse.getActivationModeConfig().isAlipayEnabled()) {
                this.b.setVisibility(8);
            }
            if (!activieInformationResponse.getActivationModeConfig().isPreMobileVerifyEnabled()) {
                this.d.setVisibility(8);
            }
            if (activieInformationResponse.getActivationModeConfig().isFaceVerifyEnabled()) {
                return;
            }
            this.f8396a.setVisibility(8);
        }
    }
}
