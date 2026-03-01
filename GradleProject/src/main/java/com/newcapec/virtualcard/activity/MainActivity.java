package com.newcapec.virtualcard.activity;

import a.a.a.a.g;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.newcapec.virtualcard.R;
import com.newcapec.virtualcard.VirtualCard;
import com.newcapec.virtualcard.entity.PayResult;
import com.newcapec.virtualcard.entity.QrCodeType;
import supwisdom.a0;
import supwisdom.e0;
import supwisdom.m;
import supwisdom.n;
import supwisdom.ne;
import supwisdom.p;
import supwisdom.r;
import supwisdom.v;
import supwisdom.x;
import supwisdom.z;

/* JADX INFO: loaded from: classes2.dex */
public class MainActivity extends g implements View.OnClickListener {
    public ImageView b;
    public TextView c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f3851e;
    public TextView f;
    public LinearLayout g;
    public LinearLayout h;
    public RelativeLayout i;
    public a0 j;
    public m k;
    public QrCodeType l;
    public boolean m = true;
    public QrCodeType n;

    public class b implements x.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ x f3854a;

        public b(x xVar) {
            this.f3854a = xVar;
        }
    }

    public MainActivity() {
        this.n = v.e().b() == 0 ? QrCodeType.ONLINE : QrCodeType.OFFLINE_IN_ONE;
    }

    public static void a(Context context, boolean z) {
        Intent intent = new Intent(context, (Class<?>) MainActivity.class);
        intent.putExtra("arg_update_auth", z);
        context.startActivity(intent);
    }

    public static /* synthetic */ void a(MainActivity mainActivity) {
        mainActivity.j.dismiss();
    }

    public static /* synthetic */ void d(MainActivity mainActivity) {
        mainActivity.d();
        mainActivity.k.f();
    }

    public final void a(PayResult payResult) {
        PaySuccessActivity.a(this, payResult);
    }

    public final void b() {
        PasswordActivity.a(this, 0);
    }

    public final void b(boolean z) {
        this.j.show();
        this.k.a(z);
    }

    public final void c() {
        Intent intent = new Intent("action_qr_code");
        intent.putExtra("code", this.k.b());
        ne.a(this).a(intent);
    }

    public final void d() {
        this.j.show();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = new Configuration();
        configuration.setToDefaults();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return resources;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && i2 == -1) {
            b(this.n == QrCodeType.ONLINE);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0098  */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onClick(android.view.View r4) {
        /*
            r3 = this;
            int r4 = r4.getId()
            int r0 = com.newcapec.virtualcard.R.id.iv_back
            if (r0 != r4) goto Ld
            r3.finish()
            goto L9c
        Ld:
            int r0 = com.newcapec.virtualcard.R.id.iv_more
            if (r0 != r4) goto L22
            supwisdom.x r4 = new supwisdom.x
            r4.<init>(r3)
            com.newcapec.virtualcard.activity.MainActivity$b r0 = new com.newcapec.virtualcard.activity.MainActivity$b
            r0.<init>(r4)
            r4.f9686a = r0
            r4.show()
            goto L9c
        L22:
            int r0 = com.newcapec.virtualcard.R.id.iv_close_tips
            r1 = 0
            if (r0 != r4) goto L31
            android.widget.LinearLayout r4 = r3.h
            r0 = 8
            r4.setVisibility(r0)
            r3.m = r1
            goto L9c
        L31:
            int r0 = com.newcapec.virtualcard.R.id.iv_intro
            if (r0 != r4) goto L39
            com.newcapec.virtualcard.activity.VirtualCardIntroActivity.a(r3)
            goto L9c
        L39:
            int r0 = com.newcapec.virtualcard.R.id.iv_qr_code
            if (r0 != r4) goto L47
            supwisdom.m r4 = r3.k
            java.lang.String r4 = r4.b()
            com.newcapec.virtualcard.activity.QrCodeActivity.a(r3, r4)
            goto L9c
        L47:
            int r0 = com.newcapec.virtualcard.R.id.tv_refresh_text
            java.lang.String r2 = "MainActivity"
            if (r0 != r4) goto L6a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "onClick ll_refresh: "
            r4.append(r0)
            com.newcapec.virtualcard.entity.QrCodeType r0 = r3.n
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            supwisdom.d0.a(r2, r4)
            com.newcapec.virtualcard.entity.QrCodeType r4 = r3.n
            com.newcapec.virtualcard.entity.QrCodeType r0 = com.newcapec.virtualcard.entity.QrCodeType.ONLINE
            if (r4 != r0) goto L99
            goto L98
        L6a:
            int r0 = com.newcapec.virtualcard.R.id.tv_fast_mode
            if (r0 == r4) goto L72
            int r0 = com.newcapec.virtualcard.R.id.tv_high_mode
            if (r0 != r4) goto L9c
        L72:
            com.newcapec.virtualcard.entity.QrCodeType r4 = r3.n
            com.newcapec.virtualcard.entity.QrCodeType r0 = com.newcapec.virtualcard.entity.QrCodeType.OFFLINE
            if (r4 != r0) goto L7a
            com.newcapec.virtualcard.entity.QrCodeType r0 = com.newcapec.virtualcard.entity.QrCodeType.ONLINE
        L7a:
            r3.l = r0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "onClick: mTempType="
            r4.append(r0)
            com.newcapec.virtualcard.entity.QrCodeType r0 = r3.l
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            supwisdom.d0.a(r2, r4)
            com.newcapec.virtualcard.entity.QrCodeType r4 = r3.l
            com.newcapec.virtualcard.entity.QrCodeType r0 = com.newcapec.virtualcard.entity.QrCodeType.ONLINE
            if (r4 != r0) goto L99
        L98:
            r1 = 1
        L99:
            r3.b(r1)
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newcapec.virtualcard.activity.MainActivity.onClick(android.view.View):void");
    }

    @Override // a.a.a.a.g, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.virtual_card_activity_main);
        e0.a(this, true, 0, false);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.screenBrightness = 1.0f;
        getWindow().setAttributes(attributes);
        View viewFindViewById = findViewById(R.id.view_status_bar);
        ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        layoutParams.height = identifier > 0 ? getResources().getDimensionPixelSize(identifier) : 0;
        int i = Build.VERSION.SDK_INT;
        if (i == 21) {
            viewFindViewById.setVisibility(0);
            viewFindViewById.setBackgroundColor(-16777216);
        } else if (i < 21) {
            viewFindViewById.setVisibility(8);
        }
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_more).setOnClickListener(this);
        ImageView imageView = (ImageView) findViewById(R.id.iv_qr_code);
        this.b = imageView;
        imageView.setOnClickListener(this);
        findViewById(R.id.tv_refresh_text).setOnClickListener(this);
        findViewById(R.id.tv_fast_mode).setOnClickListener(this);
        findViewById(R.id.tv_high_mode).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_close_tips).setOnClickListener(this);
        findViewById(R.id.iv_intro).setOnClickListener(this);
        this.i = (RelativeLayout) findViewById(R.id.rl_mode_switch);
        this.d = (TextView) findViewById(R.id.tv_fast_mode);
        this.f3851e = (TextView) findViewById(R.id.tv_high_mode);
        this.h = (LinearLayout) findViewById(R.id.ll_tips);
        this.c = (TextView) findViewById(R.id.tv_name_code);
        this.f = (TextView) findViewById(R.id.tv_offline_tip);
        this.g = (LinearLayout) findViewById(R.id.ll_qr_code);
        a();
        this.j = new a0(this);
        m pVar = VirtualCard.isT6 ? new p() : new r();
        this.k = pVar;
        pVar.a(new a());
        this.k.d();
        if (getIntent().getBooleanExtra("arg_update_auth", false)) {
            this.k.a();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.k.e();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        b(this.n == QrCodeType.ONLINE);
    }

    public class a extends n {

        /* JADX INFO: renamed from: com.newcapec.virtualcard.activity.MainActivity$a$a, reason: collision with other inner class name */
        public class C0082a implements z.a {
            public C0082a() {
            }

            @Override // supwisdom.z.a
            public void a(z zVar) {
                MainActivity.this.b();
                zVar.dismiss();
            }
        }

        public a() {
        }

        @Override // supwisdom.n
        public void a() {
            MainActivity.a(MainActivity.this);
            MainActivity.this.b();
        }

        @Override // supwisdom.n
        public void a(PayResult payResult) {
            MainActivity.this.a(payResult);
        }

        @Override // supwisdom.n
        public void a(String str, String str2) {
            MainActivity mainActivity = MainActivity.this;
            if (mainActivity.n == QrCodeType.ONLINE || mainActivity.l != null) {
                MainActivity.this.a(supwisdom.g.a(str, str2));
            }
            MainActivity mainActivity2 = MainActivity.this;
            mainActivity2.l = null;
            MainActivity.a(mainActivity2);
        }

        @Override // supwisdom.n
        public void d() {
            MainActivity.a(MainActivity.this);
            MainActivity.this.a("授权信息失效，请重新开启虚拟卡码～", (z.a) new C0082a(), false);
        }

        @Override // supwisdom.n
        public void e() {
            MainActivity.a(MainActivity.this);
            MainActivity.this.b("已暂停使用虚拟卡");
            MainActivity.this.finish();
        }

        @Override // supwisdom.n
        public void a(Bitmap bitmap) {
            MainActivity.this.c();
            MainActivity mainActivity = MainActivity.this;
            QrCodeType qrCodeType = mainActivity.l;
            if (qrCodeType != null) {
                mainActivity.n = qrCodeType;
                mainActivity.l = null;
            }
            MainActivity mainActivity2 = MainActivity.this;
            QrCodeType qrCodeType2 = mainActivity2.n;
            mainActivity2.b.setImageBitmap(null);
            mainActivity2.c.setText(mainActivity2.k.c());
            if (qrCodeType2 == QrCodeType.ONLINE) {
                mainActivity2.g.setBackgroundResource(0);
                mainActivity2.i.setVisibility(0);
                mainActivity2.i.setBackgroundResource(R.drawable.virtual_card_change_layout_bg_online);
                mainActivity2.d.setVisibility(0);
                mainActivity2.f3851e.setVisibility(8);
                mainActivity2.h.setVisibility(mainActivity2.m ? 0 : 8);
            } else {
                if (qrCodeType2 == QrCodeType.OFFLINE) {
                    mainActivity2.i.setVisibility(0);
                    mainActivity2.i.setBackgroundResource(R.drawable.virtual_card_change_layout_bg_offline);
                    mainActivity2.g.setBackgroundResource(R.drawable.virtual_card_offline_code_board);
                    mainActivity2.d.setVisibility(8);
                    mainActivity2.f3851e.setVisibility(0);
                    mainActivity2.h.setVisibility(8);
                    mainActivity2.f.setVisibility(0);
                    MainActivity.this.b.setImageBitmap(bitmap);
                    MainActivity.a(MainActivity.this);
                    MainActivity.this.k.g();
                }
                mainActivity2.g.setBackgroundResource(0);
                mainActivity2.i.setVisibility(8);
                mainActivity2.h.setVisibility(8);
                mainActivity2.i.setVisibility(8);
                mainActivity2.d.setVisibility(8);
                mainActivity2.f3851e.setVisibility(8);
            }
            mainActivity2.f.setVisibility(8);
            MainActivity.this.b.setImageBitmap(bitmap);
            MainActivity.a(MainActivity.this);
            MainActivity.this.k.g();
        }
    }
}
