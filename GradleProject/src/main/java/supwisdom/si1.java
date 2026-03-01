package supwisdom;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.supwisdom.zueb.R;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: InfoConfirmDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class si1 extends Dialog implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public JSONObject f9176a;
    public a b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9177e;
    public boolean f;
    public boolean g;
    public boolean h;

    /* JADX INFO: compiled from: InfoConfirmDialog.java */
    public interface a {
        void a();
    }

    public si1(Context context, JSONObject jSONObject) {
        super(context, R.style.Dialog);
        this.c = "";
        this.d = "";
        this.f9176a = jSONObject;
        a();
    }

    public final void a() {
        Window window = getWindow();
        window.requestFeature(1);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_infomation_confirm);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 17;
        window.setAttributes(attributes);
        TextView textView = (TextView) findViewById(R.id.tv_info_name);
        TextView textView2 = (TextView) findViewById(R.id.tv_info_account);
        TextView textView3 = (TextView) findViewById(R.id.tv_info_phone);
        TextView textView4 = (TextView) findViewById(R.id.tv_info_email);
        TextView textView5 = (TextView) findViewById(R.id.tv_confirm_info);
        TextView textView6 = (TextView) findViewById(R.id.tv_cancel_change);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_security_mobile);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.ll_security_email);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        try {
            textView.setText(this.f9176a.getJSONObject("data").getString("userName"));
            textView2.setText(this.f9176a.getJSONObject("data").getString("accountNames"));
            this.c = this.f9176a.getJSONObject("data").getJSONObject("completed").getJSONObject("securityMobile").getString(com.umeng.commonsdk.internal.utils.f.f5404a);
            this.f9177e = this.f9176a.getJSONObject("data").getJSONObject("completed").getJSONObject("securityMobile").getBoolean("forced");
            this.f = this.f9176a.getJSONObject("data").getJSONObject("completed").getJSONObject("securityMobile").getBoolean("required");
            this.d = this.f9176a.getJSONObject("data").getJSONObject("completed").getJSONObject("securityEmailAddress").getString(com.umeng.commonsdk.internal.utils.f.f5404a);
            this.g = this.f9176a.getJSONObject("data").getJSONObject("completed").getJSONObject("securityEmailAddress").getBoolean("forced");
            this.h = this.f9176a.getJSONObject("data").getJSONObject("completed").getJSONObject("securityEmailAddress").getBoolean("required");
            if (this.f9177e || this.f) {
                linearLayout.setVisibility(0);
            } else {
                linearLayout.setVisibility(8);
            }
            if (this.g || this.h) {
                linearLayout2.setVisibility(0);
            } else {
                linearLayout2.setVisibility(8);
            }
            if (com.igexin.push.core.b.m.equals(this.c) || this.c.equals("")) {
                textView3.setText("未绑定");
            } else {
                textView3.setText(this.c);
            }
            if (com.igexin.push.core.b.m.equals(this.d) || this.d.equals("")) {
                textView4.setText("未绑定");
            } else {
                textView4.setText(this.d);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_cancel_change) {
            dismiss();
        } else {
            if (id != R.id.tv_confirm_info) {
                return;
            }
            a aVar = this.b;
            if (aVar != null) {
                aVar.a();
            }
            dismiss();
        }
    }

    public void a(a aVar) {
        this.b = aVar;
    }
}
