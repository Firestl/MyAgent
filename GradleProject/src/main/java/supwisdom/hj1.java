package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.supwisdom.zueb.R;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SecurityDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class hj1 extends Dialog implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f7844a;
    public TextView b;
    public zi1 c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f7845e;
    public TextView f;
    public LinearLayoutCompat g;
    public LinearLayoutCompat h;
    public TextView i;
    public TextView j;

    public hj1(Context context, zi1 zi1Var, String str) {
        super(context, R.style.Dialog);
        this.c = zi1Var;
        this.d = str;
        a();
        b();
    }

    public final void a() {
        Window window = getWindow();
        window.requestFeature(1);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_security_info);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 80;
        window.setAttributes(attributes);
        this.b = (TextView) findViewById(R.id.dialogPhone);
        this.f7845e = (TextView) findViewById(R.id.dialogEmail);
        this.f = (TextView) findViewById(R.id.tv_safe_question);
        this.f7844a = (TextView) findViewById(R.id.dialogCancel);
        this.g = (LinearLayoutCompat) findViewById(R.id.select_security_basis);
        this.h = (LinearLayoutCompat) findViewById(R.id.ll_security_name);
        this.i = (TextView) findViewById(R.id.dialogMobile);
        this.j = (TextView) findViewById(R.id.tv_safe_identification);
        this.f7844a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.f7845e.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.j.setOnClickListener(this);
    }

    public void b() {
        try {
            if (this.d != null) {
                JSONObject jSONObject = new JSONObject(this.d);
                if (!jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("identitySecureMobileEnabled") || jSONObject.getJSONObject("data").getJSONObject("completed").getJSONObject("securityMobile").getString(com.umeng.commonsdk.internal.utils.f.f5404a).equals(com.igexin.push.core.b.m)) {
                    this.b.setVisibility(8);
                }
                if (!jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("identitySecureEmailAddressEnabled") || jSONObject.getJSONObject("data").getJSONObject("completed").getJSONObject("securityEmailAddress").getString(com.umeng.commonsdk.internal.utils.f.f5404a).equals(com.igexin.push.core.b.m)) {
                    this.f7845e.setVisibility(8);
                }
                if (!jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("identitySecureQuestionEnabled") || !jSONObject.getJSONObject("data").getJSONObject("completed").getJSONObject("securityQuestion").getBoolean("completed")) {
                    this.f.setVisibility(8);
                }
                if (this.b.getVisibility() == 8 && this.f7845e.getVisibility() == 8 && this.f.getVisibility() == 8) {
                    if (com.igexin.push.core.b.m.equals(jSONObject.getJSONObject("data").getJSONObject("safetyCertificate").getString("preMobile"))) {
                        dismiss();
                    } else {
                        this.h.setVisibility(0);
                        this.g.setVisibility(8);
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
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
            case R.id.dialogMobile /* 2131296527 */:
                if (this.c != null) {
                    dismiss();
                    this.c.c("phone_safe");
                }
                break;
            case R.id.dialogPhone /* 2131296528 */:
                if (this.c != null) {
                    dismiss();
                    this.c.c("phone");
                }
                break;
            case R.id.tv_safe_identification /* 2131297259 */:
                if (this.c != null) {
                    dismiss();
                    this.c.c("identity_safe");
                }
                break;
            case R.id.tv_safe_question /* 2131297260 */:
                if (this.c != null) {
                    dismiss();
                    this.c.c("question");
                }
                break;
        }
    }
}
