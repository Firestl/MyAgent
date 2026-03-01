package com.supwisdom.superapp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.zueb.R;
import supwisdom.sh1;

/* JADX INFO: loaded from: classes2.dex */
public class FacePrivacyActivity extends WXBaseActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f4095a;
    public ImageView b;
    public TextView c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4096e;
    public String f;
    public String g;
    public String h;

    public class a extends ClickableSpan {
        public a() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent(FacePrivacyActivity.this, (Class<?>) PrivacyActivity.class);
            intent.putExtra("policyType", 3);
            FacePrivacyActivity.this.startActivity(intent);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    public final void l() {
        String string = getResources().getString(R.string.string_face_privacy);
        String string2 = getResources().getString(R.string.string_face_key);
        int iIndexOf = string.indexOf(string2);
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_ED663F)), iIndexOf, string2.length() + iIndexOf, 34);
        spannableString.setSpan(new a(), iIndexOf, string2.length() + iIndexOf, 34);
        this.c.setMovementMethod(LinkMovementMethod.getInstance());
        this.c.setText(spannableString);
    }

    public final void m() {
        this.f4096e = getIntent().getStringExtra("verifyType");
        this.f = getIntent().getStringExtra("account");
        this.g = getIntent().getStringExtra("deviceId");
        this.h = getIntent().getStringExtra("privateKey");
        l();
    }

    public final void n() {
        this.f4095a = (TextView) findViewById(R.id.tv_back);
        this.b = (ImageView) findViewById(R.id.iv_agree);
        this.c = (TextView) findViewById(R.id.tv_face_privacy);
        this.d = (TextView) findViewById(R.id.tv_face_positive);
        this.f4095a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.d.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_agree) {
            this.b.setSelected(!r3.isSelected());
            this.d.setSelected(!r3.isSelected());
            return;
        }
        if (id == R.id.tv_back) {
            onBackPressed();
            return;
        }
        if (id != R.id.tv_face_positive) {
            return;
        }
        if (!this.d.isSelected()) {
            Toast.makeText(this, "请阅读并同意人脸识别隐私协议", 0).show();
            return;
        }
        sh1.c.a("isAgreeFacePrivacy", (Boolean) true);
        Intent intent = new Intent(this, (Class<?>) FaceLivenessExpActivity.class);
        String str = this.f4096e;
        if (str != null) {
            intent.putExtra("verifyType", str);
        }
        String str2 = this.f;
        if (str2 != null) {
            intent.putExtra("account", str2);
        }
        String str3 = this.g;
        if (str3 != null) {
            intent.putExtra("deviceId", str3);
        }
        String str4 = this.h;
        if (str4 != null) {
            intent.putExtra("privateKey", str4);
        }
        startActivity(intent);
        finish();
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_face_privacy);
        n();
        m();
    }
}
