package com.supwisdom.superapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.zueb.R;
import supwisdom.sh1;

/* JADX INFO: loaded from: classes2.dex */
public class AuthorDialogActivity extends WXBaseActivity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f4027a;
    public TextView b;
    public TextView c;

    public /* synthetic */ void a(JSONObject jSONObject, View view) {
        jSONObject.put("sysInfoAuthorizeState", "1");
        sh1.c.a("isAgreeInfo", (Boolean) true);
        finish();
    }

    public /* synthetic */ void b(JSONObject jSONObject, View view) {
        jSONObject.put("sysInfoAuthorizeState", "0");
        sh1.c.a("isAgreeInfo", (Boolean) false);
        finish();
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_service_background);
        this.f4027a = (TextView) findViewById(R.id.tv_url_name);
        this.b = (TextView) findViewById(R.id.tv_negative);
        this.c = (TextView) findViewById(R.id.tv_positive);
        String stringExtra = getIntent().getStringExtra("shareTitle");
        final JSONObject jSONObject = new JSONObject();
        if (stringExtra != null) {
            this.f4027a.setText(stringExtra.concat("  申请获得以下权限："));
        }
        this.c.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.sj1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f9183a.a(jSONObject, view);
            }
        });
        this.b.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.tj1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f9297a.b(jSONObject, view);
            }
        });
    }
}
