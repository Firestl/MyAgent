package com.sangfor.sdk.auth.trustterminal.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.sangfor.sdk.R;
import com.sangfor.sdk.auth.BaseAuthActivity;
import com.sangfor.sdk.auth.Sangfor_b;
import com.sangfor.sdk.base.SFAuthType;
import com.sangfor.sdk.base.SFErrorCode;
import com.sangfor.sdk.base.authdevice.AuthDeviceInfo;
import com.sangfor.sdk.utils.SFLogN;
import java.io.Serializable;
import supwisdom.c81;
import supwisdom.i71;
import supwisdom.l81;
import supwisdom.v71;
import supwisdom.w81;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseTrustTerminalActivity extends BaseAuthActivity implements Sangfor_b.b {
    public RelativeLayout f;
    public LinearLayout h;
    public ImageView i;
    public View.OnClickListener j;
    public l81 m;
    public Resources g = null;
    public AuthDeviceInfo k = null;
    public SFAuthType l = SFAuthType.AUTH_TYPE_NONE;

    /* JADX INFO: compiled from: Proguard */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BaseTrustTerminalActivity.this.g();
            BaseTrustTerminalActivity.this.finish();
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3904a;

        static {
            int[] iArr = new int[Sangfor_b.Sangfor_c.values().length];
            f3904a = iArr;
            try {
                iArr[Sangfor_b.Sangfor_c.EVENT_UNSUPPORT_SECOND_AUTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3904a[Sangfor_b.Sangfor_c.EVENT_AUTH_FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static void Sangfor_a(Context context, Class<? extends BaseTrustTerminalActivity> cls, SFAuthType sFAuthType, AuthDeviceInfo authDeviceInfo) {
        Intent intent = new Intent(context.getApplicationContext(), cls);
        intent.putExtra("auth_key_next_auth_type", sFAuthType);
        intent.putExtra("auth_key_auth_device_data", authDeviceInfo);
        context.startActivity(intent);
    }

    @Override // com.sangfor.sdk.auth.Sangfor_b.b
    public int a() {
        return 10;
    }

    public void a(l81 l81Var) {
        a(i71.b.q, l81Var.b);
    }

    public final void f() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(67108864);
        intent.addCategory("android.intent.category.HOME");
        startActivity(intent);
    }

    public void g() {
        SFLogN.c("BaseTrustTerminalActivity", "handleAuthBack currentAuthType:" + this.l.intValue());
        c81.a().a(new l81((long) SFErrorCode.SF_ERROR_AUTH_TERMINAL_CANCLE_UNBIND.value(), this.l.intValue(), this.g.getString(R.string.terminal_cancle_unbind_message), ""));
    }

    public abstract void h();

    public void i() {
        Intent intent = getIntent();
        if (intent == null) {
            SFLogN.c("BaseTrustTerminalActivity", "get intent is null");
            finish();
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra("auth_key_next_auth_type");
        if (!(serializableExtra instanceof SFAuthType)) {
            SFLogN.b("BaseTrustTerminalActivity", "Target is not an nextAuthType: " + serializableExtra, "");
            finish();
            return;
        }
        this.l = (SFAuthType) serializableExtra;
        Serializable serializableExtra2 = intent.getSerializableExtra("auth_key_auth_device_data");
        if (serializableExtra2 instanceof AuthDeviceInfo) {
            this.k = (AuthDeviceInfo) serializableExtra2;
            SFLogN.c("BaseTrustTerminalActivity", "initIntent:" + this.k.toString());
            return;
        }
        SFLogN.b("BaseTrustTerminalActivity", "Target is not an AuthDeviceInfo: " + serializableExtra2, "");
        finish();
    }

    public void j() {
        k();
        Sangfor_b.b().b(this);
        ImageView imageView = this.i;
        if (imageView != null) {
            imageView.setOnClickListener(this.j);
        } else {
            SFLogN.c("BaseTrustTerminalActivity", "initOnClickListener fail, mBackIv is null");
        }
    }

    public void k() {
        this.j = new a();
    }

    @Override // com.sangfor.sdk.auth.BaseAuthActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        setTheme(android.R.style.Theme.Holo.Light.NoActionBar);
        super.onCreate(bundle);
        this.g = getResources();
        v71.c((Activity) this);
        this.f = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.f.setGravity(1);
        this.f.setLayoutParams(layoutParams);
        this.f.setBackgroundColor(-1);
        this.h = new LinearLayout(this);
        this.h.setLayoutParams(new LinearLayout.LayoutParams(v71.a((Context) this), -1));
        this.h.setBackgroundColor(-1);
        this.h.setOrientation(1);
        ImageView imageView = new ImageView(this);
        this.i = imageView;
        int i = i71.c.w;
        imageView.setLayoutParams(new LinearLayout.LayoutParams(i, i));
        ImageView imageView2 = this.i;
        int i2 = i71.c.o;
        imageView2.setPadding(i2, i2, i2, i2);
        this.i.setImageBitmap(v71.a(this, "back.png"));
        this.h.addView(this.i);
        i();
        h();
        j();
        this.f.addView(this.h);
        setContentView(this.f);
        w81.a(this, -1);
    }

    @Override // com.sangfor.sdk.auth.BaseAuthActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Sangfor_b.b().c(this);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        f();
        return true;
    }

    @Override // com.sangfor.sdk.auth.Sangfor_b.b
    public void a(Sangfor_b.Sangfor_c sangfor_c, l81 l81Var) {
        this.m = l81Var;
        int i = b.f3904a[sangfor_c.ordinal()];
        if (i == 1 || i == 2) {
            c();
            a(l81Var);
        }
    }
}
