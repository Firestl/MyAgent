package com.supwisdom.superapp.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.zueb.R;

/* JADX INFO: loaded from: classes2.dex */
public class PaySuccessAty extends WXBaseActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f4330a;
    public TextView b;
    public String[] c = new String[0];
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f4331e;
    public TextView f;
    public TextView g;

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backBt /* 2131296356 */:
            case R.id.backBt2 /* 2131296357 */:
                finish();
                break;
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_pay_success);
        this.f4330a = (TextView) findViewById(R.id.backBt);
        this.b = (TextView) findViewById(R.id.backBt2);
        this.f = (TextView) findViewById(R.id.pay_number);
        this.d = (TextView) findViewById(R.id.pay_type);
        this.f4331e = (TextView) findViewById(R.id.pay_where);
        this.g = (TextView) findViewById(R.id.pay_time);
        this.f4330a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        String[] strArrSplit = getIntent().getStringExtra("data").split("\\&");
        this.c = strArrSplit;
        this.g.setText(strArrSplit[10]);
        double d = Double.parseDouble(TextUtils.isEmpty(this.c[1]) ? "0" : this.c[1]) / 100.0d;
        this.f.setText("¥" + d);
        this.f4331e.setText(this.c[8]);
        if (this.c[3].equals("###")) {
            this.d.setText("一卡通");
        }
    }
}
