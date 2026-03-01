package com.newcapec.virtualcard.activity;

import a.a.a.a.g;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.newcapec.virtualcard.R;
import com.newcapec.virtualcard.entity.PayResult;
import java.util.Locale;
import supwisdom.e;
import supwisdom.f;

/* JADX INFO: loaded from: classes2.dex */
public class PaySuccessActivity extends g {
    public TextView b;
    public TextView c;
    public LinearLayout d;

    public static void a(Context context, PayResult payResult) {
        Intent intent = new Intent(context, (Class<?>) PaySuccessActivity.class);
        intent.putExtra("data", payResult);
        context.startActivity(intent);
    }

    @Override // a.a.a.a.g, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.virtual_card_activity_pay_success);
        findViewById(R.id.iv_back).setOnClickListener(new e(this));
        this.b = (TextView) findViewById(R.id.tv_pay_money);
        this.c = (TextView) findViewById(R.id.tv_pay_address);
        this.d = (LinearLayout) findViewById(R.id.layout_detail);
        findViewById(R.id.tv_pay_complete).setOnClickListener(new f(this));
        PayResult payResult = (PayResult) getIntent().getSerializableExtra("data");
        if (payResult == null) {
            return;
        }
        this.b.setText(String.format(Locale.CHINA, "%.2f", Float.valueOf(Float.parseFloat(payResult.getPayMoney()) / 100.0f)));
        this.c.setText(payResult.getPayLocation());
        this.d.removeAllViews();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this);
        for (PayResult.PayInfoItem payInfoItem : payResult.getItemList()) {
            View viewInflate = layoutInflaterFrom.inflate(R.layout.virtual_card_widget_pay_success_detail, (ViewGroup) this.d, false);
            ((TextView) viewInflate.findViewById(R.id.tv_name)).setText(String.format("%s:", payInfoItem.getName()));
            ((TextView) viewInflate.findViewById(R.id.tv_value)).setText(payInfoItem.getValue());
            this.d.addView(viewInflate);
        }
    }
}
