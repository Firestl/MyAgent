package com.newcapec.virtualcard.activity;

import a.a.a.a.g;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import com.newcapec.virtualcard.R;
import com.newcapec.virtualcard.widget.NumberKeyboardWidget;
import com.newcapec.virtualcard.widget.PasswordInputView;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import supwisdom.a;
import supwisdom.a0;
import supwisdom.b;
import supwisdom.c;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordActivity extends g {
    public PasswordInputView b;
    public a0 c;

    public static void a(Activity activity, int i) {
        Intent intent = new Intent(activity, (Class<?>) PasswordActivity.class);
        intent.putExtra("arg_return", true);
        intent.setFlags(PKIFailureInfo.duplicateCertReq);
        activity.startActivityForResult(intent, i);
    }

    public static void a(Context context) {
        Intent intent = new Intent(context, (Class<?>) PasswordActivity.class);
        intent.setFlags(PKIFailureInfo.duplicateCertReq);
        context.startActivity(intent);
    }

    @Override // a.a.a.a.g, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.virtual_card_activity_password);
        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new a(this));
        PasswordInputView passwordInputView = (PasswordInputView) findViewById(R.id.view_password_input);
        this.b = passwordInputView;
        passwordInputView.setInputType(0);
        this.b.setEncryptInterface(new b(this));
        ((NumberKeyboardWidget) findViewById(R.id.view_number_keyboard)).setNumberKeyboardListener(new c(this));
        this.c = new a0(this);
    }
}
