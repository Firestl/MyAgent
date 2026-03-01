package com.g.gysdk.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cmic.sso.sdk.AuthRegisterViewConfig;
import com.g.gysdk.EloginActivityParam;
import com.g.gysdk.GyCode;
import com.g.gysdk.GyErrorCode;
import com.g.gysdk.GyPreloginResult;
import com.g.gysdk.R;
import com.g.gysdk.a.ai;
import com.g.gysdk.a.aj;
import com.g.gysdk.a.ak;
import com.g.gysdk.a.ao;
import com.g.gysdk.a.ap;
import com.g.gysdk.a.d;
import com.g.gysdk.a.s;
import com.g.gysdk.cta.ELoginThemeConfig;
import com.g.gysdk.cta.b;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class ELoginActivity extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Field f2057a;
    public RelativeLayout d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public RelativeLayout f2058e;
    public ImageButton f;
    public TextView g;
    public ImageView h;
    public RelativeLayout i;
    public TextView j;
    public TextView k;
    public RelativeLayout l;
    public TextView m;
    public LoadingImageView n;
    public TextView o;
    public LinearLayout p;
    public CheckBox q;
    public TextView r;
    public GTGifView s;
    public ELoginThemeConfig.Builder t;
    public String u;
    public AnimateSurfaceFrame v;
    public FrameLayout w;
    public final String c = ELoginActivity.class.getSimpleName();
    public boolean x = false;
    public int y = 0;
    public Resources.Theme z = null;
    public List<AuthRegisterViewConfig> A = new ArrayList();
    public boolean b = true;

    public static class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public AuthRegisterViewConfig f2067a;

        public a(AuthRegisterViewConfig authRegisterViewConfig) {
            this.f2067a = authRegisterViewConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                if (this.f2067a.getCustomInterface() != null) {
                    this.f2067a.getCustomInterface().onClick(view.getContext());
                }
            } catch (Throwable th) {
                ap.a(th);
            }
        }
    }

    private void a() {
        com.g.gysdk.cta.b.a().a(new b.a() { // from class: com.g.gysdk.view.ELoginActivity.3
            @Override // com.g.gysdk.cta.b.a
            public void a() {
                try {
                    ELoginActivity.this.finish();
                } catch (Throwable th) {
                    ap.a(th);
                }
            }

            @Override // com.g.gysdk.cta.b.a
            public void b() {
                try {
                    ELoginActivity.this.d.post(new Runnable() { // from class: com.g.gysdk.view.ELoginActivity.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ELoginActivity.this.h();
                        }
                    });
                } catch (Throwable th) {
                    ap.a(th);
                }
            }

            @Override // com.g.gysdk.cta.b.a
            public boolean c() {
                try {
                    return ELoginActivity.this.q.isChecked();
                } catch (Throwable th) {
                    ap.a(th);
                    return false;
                }
            }
        });
        ELoginThemeConfig eLoginThemeConfigG = com.g.gysdk.cta.b.a().g();
        this.t = eLoginThemeConfigG == null ? new ELoginThemeConfig.Builder() : eLoginThemeConfigG.getBuilder();
        try {
            this.d = (RelativeLayout) findViewById(R.id.gy_e_login_bg_layout);
            this.f2058e = (RelativeLayout) findViewById(R.id.gy_e_login_nav_layout);
            this.f = (ImageButton) findViewById(R.id.gy_e_login_nav_back);
            this.g = (TextView) findViewById(R.id.gy_e_login_nav_title);
            this.h = (ImageView) findViewById(R.id.gy_e_login_logo);
            this.i = (RelativeLayout) findViewById(R.id.gy_e_login_main_layout);
            this.j = (TextView) findViewById(R.id.gy_e_login_number_tv);
            this.k = (TextView) findViewById(R.id.gy_e_login_brand);
            this.l = (RelativeLayout) findViewById(R.id.gy_e_login_submit_layout);
            this.m = (TextView) findViewById(R.id.gy_e_login_submit_tv);
            this.n = (LoadingImageView) findViewById(R.id.gy_e_login_submit_iv);
            this.s = (GTGifView) findViewById(R.id.gy_e_login_submit_gif);
            this.o = (TextView) findViewById(R.id.gy_e_login_switch_tv);
            this.p = (LinearLayout) findViewById(R.id.gy_e_login_privacy_ll);
            this.q = (CheckBox) findViewById(R.id.gy_e_login_check);
            this.r = (TextView) findViewById(R.id.gy_e_login_param_tv);
            AnimateSurfaceFrame animateSurfaceFrame = (AnimateSurfaceFrame) findViewById(R.id.gy_e_login_animate_surface_view);
            this.v = animateSurfaceFrame;
            animateSurfaceFrame.a(com.g.gysdk.cta.b.a().g());
            this.w = (FrameLayout) findViewById(R.id.gy_e_login_fr_nav);
            b();
            c();
            e();
        } catch (Throwable th) {
            ap.b("页面元素加载异常:" + th);
            ap.a(th);
            throw th;
        }
    }

    private void a(RelativeLayout.LayoutParams layoutParams, int i, int i2, int i3) {
        if (i3 != 0) {
            layoutParams.addRule(9);
            layoutParams.leftMargin = com.g.gysdk.cta.a.a(getApplicationContext(), i3);
        } else {
            layoutParams.addRule(14);
        }
        if (i2 == 0) {
            layoutParams.addRule(10);
            layoutParams.topMargin = com.g.gysdk.cta.a.a(getApplicationContext(), i);
        } else {
            layoutParams.addRule(12);
            layoutParams.bottomMargin = com.g.gysdk.cta.a.a(getApplicationContext(), i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        LoadingImageView loadingImageView;
        int i;
        if (str == null) {
            return;
        }
        if (ao.a(str)) {
            this.s.b();
            loadingImageView = this.n;
            i = 8;
        } else {
            if (!ao.b(str)) {
                return;
            }
            this.s.a();
            loadingImageView = this.n;
            i = 0;
        }
        loadingImageView.setVisibility(i);
    }

    private void b() {
        try {
            if (this.t.getStatusBarColor().intValue() != 0) {
                return;
            }
            int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
            int dimensionPixelSize = identifier > 0 ? getResources().getDimensionPixelSize(identifier) : 0;
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.w.getLayoutParams();
            layoutParams.addRule(10);
            layoutParams.topMargin = dimensionPixelSize;
            this.w.setLayoutParams(layoutParams);
        } catch (Throwable th) {
            ap.a(th);
        }
    }

    private void c() {
        CheckBox checkBox;
        int iA;
        CheckBox checkBox2;
        Drawable unCheckedDrawable;
        try {
            if (this.t.getAuthBGDrawable() != null) {
                this.d.setBackgroundDrawable(this.t.getAuthBGDrawable());
            } else if (TextUtils.isEmpty(this.t.getAuthBGColor())) {
                this.d.setBackgroundResource(com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getAuthBGImgPath()));
            } else {
                try {
                    this.d.setBackgroundColor(Color.parseColor(this.t.getAuthBGColor()));
                } catch (Throwable unused) {
                    Log.e("gysdk", "授权页背景颜色解析失败，请检查！");
                }
            }
            if (this.t.isAuthNavGone()) {
                this.f2058e.setVisibility(8);
            } else {
                this.f2058e.setBackgroundColor(this.t.getNavColor());
                if (this.t.isAuthNavTransparent()) {
                    this.f2058e.getBackground().setAlpha(0);
                }
                ViewGroup.LayoutParams layoutParams = this.f2058e.getLayoutParams();
                layoutParams.height = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getAuthNavHeight());
                this.f2058e.setLayoutParams(layoutParams);
            }
            this.g.setText(this.t.getNavText());
            this.g.setTextColor(this.t.getNavTextColor());
            this.g.setTextSize(this.t.getNavTextSize());
            this.g.setTypeface(this.t.getNavTextTypeface());
            this.f.setBackgroundColor(0);
            if (this.t.isNavReturnImgHidden()) {
                this.f.setVisibility(8);
            } else {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f.getLayoutParams();
                layoutParams2.width = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getReturnImgWidth());
                layoutParams2.height = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getReturnImgHeight());
                layoutParams2.leftMargin = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getReturnImgOffsetX());
                if (this.t.isReturnImgCenterInVertical()) {
                    layoutParams2.gravity = 17;
                } else {
                    layoutParams2.topMargin = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getReturnImgOffsetY());
                }
                this.f.setLayoutParams(layoutParams2);
                this.f.setImageResource(com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getNavReturnImgPath()));
                this.f.setOnClickListener(new View.OnClickListener() { // from class: com.g.gysdk.view.ELoginActivity.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ELoginActivity.this.finish();
                    }
                });
            }
            if (this.t.isLogoHidden()) {
                this.h.setVisibility(8);
            } else {
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.h.getLayoutParams();
                layoutParams3.width = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLogoWidth());
                layoutParams3.height = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLogoHeight());
                a(layoutParams3, this.t.getLogoOffsetY(), this.t.getLogoOffsetY_B(), this.t.getLogoOffsetX());
                this.h.setLayoutParams(layoutParams3);
                if (!TextUtils.isEmpty(this.t.getLogoImgPath())) {
                    if (new File(this.t.getLogoImgPath()).exists()) {
                        try {
                            this.h.setImageBitmap(ai.a(this.t.getLogoImgPath(), com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLogoWidth()), com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLogoHeight())));
                        } catch (Throwable th) {
                            ap.a(th);
                        }
                    } else {
                        this.h.setImageResource(com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLogoImgPath()));
                    }
                }
            }
            this.o.setTextColor(this.t.getSwitchColor());
            this.o.setText(this.t.getSwitchText());
            this.o.setTextSize(this.t.getSwitchSize());
            this.o.setTypeface(this.t.getSwitchViewTypeface());
            if (this.t.isSwitchAccHidden()) {
                this.o.setVisibility(4);
            } else {
                RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.o.getLayoutParams();
                layoutParams4.width = -2;
                layoutParams4.height = -2;
                a(layoutParams4, this.t.getSwitchAccOffsetY(), this.t.getSwitchOffsetY_B(), this.t.getSwitchOffsetX());
                this.o.setLayoutParams(layoutParams4);
            }
            this.o.setOnClickListener(new View.OnClickListener() { // from class: com.g.gysdk.view.ELoginActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    com.g.gysdk.a.a(com.g.gysdk.cta.b.a().h(), new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.SWITCH_LOGIN_PAGE, "点击切换").a("eLogin", s.a().f()));
                }
            });
            this.m.setText(this.t.getLoginButtonText());
            this.m.setTextColor(this.t.getLoginButtonColor());
            this.m.setTextSize(this.t.getLogBtnTextSize());
            this.m.setTypeface(this.t.getLogBtnTextViewTypeface());
            d();
            try {
                if (this.t.getLoginButtonBgDrawable() == null) {
                    this.l.setBackgroundResource(com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLoginImgPath()));
                } else if (Build.VERSION.SDK_INT >= 16) {
                    this.l.setBackground(this.t.getLoginButtonBgDrawable());
                } else {
                    this.l.setBackgroundDrawable(this.t.getLoginButtonBgDrawable());
                }
            } catch (Throwable th2) {
                ap.a(th2);
            }
            RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.l.getLayoutParams();
            layoutParams5.width = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLogBtnWidth());
            layoutParams5.height = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLogBtnHeight());
            a(layoutParams5, this.t.getLogBtnOffsetY(), this.t.getLogBtnOffsetY_B(), this.t.getLogBtnOffsetX());
            this.l.setLayoutParams(layoutParams5);
            this.j.setTypeface(this.t.getNumberViewTypeface());
            this.j.setTextColor(this.t.getNumberColor());
            this.j.setTextSize(this.t.getNumberSize());
            RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.j.getLayoutParams();
            layoutParams6.width = -2;
            layoutParams6.height = -2;
            a(layoutParams6, this.t.getNumFieldOffsetY(), this.t.getNumFieldOffsetY_B(), this.t.getNumFieldOffsetX());
            this.j.setLayoutParams(layoutParams6);
            this.k.setTextColor(this.t.getSloganColor());
            this.k.setTextSize(this.t.getSloganSize());
            this.k.setTypeface(this.t.getSloganViewTypeface());
            RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) this.k.getLayoutParams();
            layoutParams7.width = -2;
            layoutParams7.height = -2;
            a(layoutParams7, this.t.getSloganOffsetY(), this.t.getSloganOffsetY_B(), this.t.getSloganOffsetX());
            this.k.setLayoutParams(layoutParams7);
            RelativeLayout.LayoutParams layoutParams8 = (RelativeLayout.LayoutParams) this.p.getLayoutParams();
            layoutParams8.width = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getPrivacyLayoutWidth());
            layoutParams8.height = -2;
            a(layoutParams8, this.t.getPrivacyOffsetY(), this.t.getPrivacyOffsetY_B(), this.t.getPrivacyOffsetX());
            this.p.setLayoutParams(layoutParams8);
            this.r.setTextColor(this.t.getBaseClauseColor());
            this.r.setTextSize(this.t.getPrivacyClausetextSize());
            GyPreloginResult gyPreloginResultA = com.g.gysdk.a.c.a();
            if (gyPreloginResultA != null) {
                TextView textView = this.r;
                StringBuilder sb = new StringBuilder();
                sb.append(this.t.isHasQuotationMarkOnCarrierProtocol() ? "《" : "");
                sb.append(gyPreloginResultA.getPrivacyName());
                sb.append(this.t.isHasQuotationMarkOnCarrierProtocol() ? "》" : "");
                com.g.gysdk.cta.a.a(textView, sb.toString(), gyPreloginResultA.getPrivacyUrl(), this.t, getApplicationContext());
            }
            this.r.setLongClickable(false);
            if (this.t.isPrivacyState()) {
                this.q.setChecked(true);
                if (this.t.getCheckedDrawable() != null) {
                    checkBox2 = this.q;
                    unCheckedDrawable = this.t.getCheckedDrawable();
                    checkBox2.setBackgroundDrawable(unCheckedDrawable);
                } else {
                    checkBox = this.q;
                    iA = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getCheckedImgPath());
                    checkBox.setBackgroundResource(iA);
                }
            } else {
                this.q.setChecked(false);
                if (this.t.getUnCheckedDrawable() != null) {
                    checkBox2 = this.q;
                    unCheckedDrawable = this.t.getUnCheckedDrawable();
                    checkBox2.setBackgroundDrawable(unCheckedDrawable);
                } else {
                    checkBox = this.q;
                    iA = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getUnCheckedImgPath());
                    checkBox.setBackgroundResource(iA);
                }
            }
            LinearLayout.LayoutParams layoutParams9 = (LinearLayout.LayoutParams) this.q.getLayoutParams();
            layoutParams9.width = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getPrivacyCheckBoxWidth());
            layoutParams9.height = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getPrivacyCheckBoxHeight());
            if (this.t.getPrivacyCheckBoxOffsetY() != 0) {
                layoutParams9.topMargin = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getPrivacyCheckBoxOffsetY());
            }
            this.q.setLayoutParams(layoutParams9);
            this.q.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.g.gysdk.view.ELoginActivity.6
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    CheckBox checkBox3;
                    Context applicationContext;
                    String unCheckedImgPath;
                    CheckBox checkBox4;
                    Drawable unCheckedDrawable2;
                    ELoginThemeConfig.Builder builder = ELoginActivity.this.t;
                    if (z) {
                        if (builder.getCheckedDrawable() != null) {
                            checkBox4 = ELoginActivity.this.q;
                            unCheckedDrawable2 = ELoginActivity.this.t.getCheckedDrawable();
                            checkBox4.setBackgroundDrawable(unCheckedDrawable2);
                        } else {
                            checkBox3 = ELoginActivity.this.q;
                            applicationContext = ELoginActivity.this.getApplicationContext();
                            unCheckedImgPath = ELoginActivity.this.t.getCheckedImgPath();
                            checkBox3.setBackgroundResource(com.g.gysdk.cta.a.a(applicationContext, unCheckedImgPath));
                        }
                    }
                    if (builder.getUnCheckedDrawable() != null) {
                        checkBox4 = ELoginActivity.this.q;
                        unCheckedDrawable2 = ELoginActivity.this.t.getUnCheckedDrawable();
                        checkBox4.setBackgroundDrawable(unCheckedDrawable2);
                    } else {
                        checkBox3 = ELoginActivity.this.q;
                        applicationContext = ELoginActivity.this.getApplicationContext();
                        unCheckedImgPath = ELoginActivity.this.t.getUnCheckedImgPath();
                        checkBox3.setBackgroundResource(com.g.gysdk.cta.a.a(applicationContext, unCheckedImgPath));
                    }
                }
            });
            this.q.setOnClickListener(new View.OnClickListener() { // from class: com.g.gysdk.view.ELoginActivity.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    try {
                        com.g.gysdk.cta.b.a().onPrivacyCheckBoxClick(ELoginActivity.this.q.isChecked());
                    } catch (Throwable th3) {
                        ap.a(th3);
                    }
                }
            });
        } catch (Throwable th3) {
            ap.b("页面加载异常" + th3);
            ap.a(th3);
            throw th3;
        }
    }

    private void d() {
        if (TextUtils.isEmpty(this.t.getLoadingView())) {
            return;
        }
        int iA = ao.a(this.t.getLoadingView(), getApplicationContext());
        try {
            String strA = ao.a(getApplicationContext().getResources().openRawResource(iA));
            this.u = strA;
            if (ao.a(strA)) {
                this.s.setGifResource(iA);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.s.getLayoutParams();
                layoutParams.width = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLoadingViewWidth());
                layoutParams.height = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLoadingViewHeight());
                layoutParams.rightMargin = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLoadingViewOffsetRight());
                if (this.t.isLoadingViewCenterInVertical()) {
                    layoutParams.gravity = 17;
                } else {
                    layoutParams.topMargin = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLoadingViewOffsetY());
                }
                this.s.setLayoutParams(layoutParams);
                this.s.a();
                return;
            }
            if (ao.b(this.u)) {
                this.n.setImageResource(com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLoadingView()));
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.n.getLayoutParams();
                layoutParams2.width = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLoadingViewWidth());
                layoutParams2.height = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLoadingViewHeight());
                layoutParams2.rightMargin = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLoadingViewOffsetRight());
                if (this.t.isLoadingViewCenterInVertical()) {
                    layoutParams2.gravity = 17;
                } else {
                    layoutParams2.topMargin = com.g.gysdk.cta.a.a(getApplicationContext(), this.t.getLoadingViewOffsetY());
                }
                this.n.setLayoutParams(layoutParams2);
            }
        } catch (Exception e2) {
            ap.a((Throwable) e2);
        }
    }

    private void e() {
        try {
            HashMap<String, AuthRegisterViewConfig> mapE = com.g.gysdk.cta.b.a().e();
            if (mapE != null && !mapE.isEmpty()) {
                Iterator<String> it = mapE.keySet().iterator();
                while (it.hasNext()) {
                    AuthRegisterViewConfig authRegisterViewConfig = mapE.get(it.next());
                    this.A.add(authRegisterViewConfig);
                    try {
                        View view = authRegisterViewConfig.getView();
                        view.setOnClickListener(new a(authRegisterViewConfig));
                        (authRegisterViewConfig.getRootViewId() == 1 ? this.f2058e : this.i).addView(view);
                    } catch (Throwable th) {
                        ap.a(th);
                    }
                }
            }
        } catch (Throwable th2) {
            ap.a(th2);
        }
    }

    private void f() {
        try {
            if (this.A != null && !this.A.isEmpty()) {
                for (AuthRegisterViewConfig authRegisterViewConfig : this.A) {
                    try {
                        (authRegisterViewConfig.getRootViewId() == 1 ? this.f2058e : this.i).removeView(authRegisterViewConfig.getView());
                    } catch (Throwable th) {
                        ap.a(th);
                    }
                }
                com.g.gysdk.cta.b.a().d();
                ap.a((Object) "clearCustomView");
            }
        } catch (Throwable th2) {
            ap.a(th2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        try {
            this.n.b();
            this.q.setEnabled(false);
            this.l.setEnabled(false);
        } catch (Throwable th) {
            ap.a(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        try {
            this.n.c();
            this.q.setEnabled(true);
            this.l.setEnabled(true);
        } catch (Throwable th) {
            ap.a(th);
        }
    }

    public static void start() {
        try {
            ak.a("EloginActivity startActivity");
            Intent intent = new Intent(d.b, (Class<?>) ELoginActivity.class);
            intent.addFlags(268435456);
            d.b.startActivity(intent);
        } catch (Throwable th) {
            aj.a("EloginActivity 启动失败，请检查是否已加入manifest中", th);
            com.g.gysdk.a.a(com.g.gysdk.cta.b.a().h(), new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.SHOW_LOGIN_ERROR, "EloginActivity startActivity failed").a("eLogin", ""));
        }
    }

    public void fixLeak(Context context) {
        InputMethodManager inputMethodManager;
        if (this.b && (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) != null) {
            String[] strArr = {"mLastSrvView"};
            for (int i = 0; i < 1; i++) {
                String str = strArr[i];
                try {
                    if (this.f2057a == null) {
                        this.f2057a = inputMethodManager.getClass().getDeclaredField(str);
                    }
                    if (this.f2057a == null) {
                        this.b = false;
                    }
                    if (this.f2057a != null) {
                        ap.a((Object) "field is not null");
                        this.f2057a.setAccessible(true);
                        this.f2057a.set(inputMethodManager, null);
                    }
                } catch (Throwable unused) {
                }
            }
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.g.gysdk.cta.b.a().onAuthActivityCreate(this);
        this.x = true;
        int i = this.y;
        if (i != 0) {
            setTheme(i);
            this.y = 0;
        }
        Resources.Theme theme = this.z;
        if (theme != null) {
            setTheme(theme);
            this.z = null;
        }
        setContentView(R.layout.gy_activity_e_login);
        try {
            a();
            setFinishOnTouchOutside(false);
            com.g.gysdk.a.c.a(true, new EloginActivityParam().setActivity(this).setNumberTextview(this.j).setSloganTextview(this.k).setLoginButton(this.l).setPrivacyCheckbox(this.q).setPrivacyTextview(this.r).setLoginOnClickListener(new View.OnClickListener() { // from class: com.g.gysdk.view.ELoginActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    try {
                        com.g.gysdk.cta.b.a().onLoginButtonClick();
                        if (ELoginActivity.this.q.isChecked()) {
                            ELoginActivity.this.g();
                            ELoginActivity.this.a(ELoginActivity.this.u);
                        } else if (ELoginActivity.this.t.isShowPrivacyUncheckedToast()) {
                            Toast.makeText(ELoginActivity.this.getApplicationContext(), ELoginActivity.this.t.getPrivacyUnCheckedToastText(), 1).show();
                        }
                    } catch (Throwable th) {
                        ap.a(th);
                    }
                }
            }).setUiErrorListener(new EloginActivityParam.UIErrorListener() { // from class: com.g.gysdk.view.ELoginActivity.1
                @Override // com.g.gysdk.EloginActivityParam.UIErrorListener
                public void onError(String str) {
                    Log.e(ELoginActivity.this.c, str);
                }
            }), com.g.gysdk.cta.b.a().i(), com.g.gysdk.cta.b.a().h());
        } catch (Throwable th) {
            aj.a("EloginActivity init exception:", th);
            com.g.gysdk.a.a(com.g.gysdk.cta.b.a().h(), new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.AUTH_PAGE_ERROR, "").a("eLogin", s.a().f()));
            finish();
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        try {
            com.g.gysdk.cta.b.a().f();
            f();
            this.n.c();
            fixLeak(this);
            this.v.a();
        } catch (Throwable th) {
            ap.a(th);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            if (this.t != null && this.t.isDialogTheme()) {
                com.g.gysdk.cta.a.a(this, this.t.getDialogWidth(), this.t.getDialogHeight(), this.t.getDialogX(), this.t.getDialogY(), this.t.isDialogBottom());
            }
            ap.a((Object) "onResume...");
        } catch (Throwable th) {
            ap.a(th);
        }
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        try {
            com.g.gysdk.cta.a.a(this.t.getStatusBarColor(), this.t.getNavigationBarColor(), this);
            com.g.gysdk.cta.a.a(this.t.isLightColor(), this);
        } catch (Throwable th) {
            ap.a(th);
        }
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.x) {
            super.setTheme(i);
            i = 0;
        }
        this.y = i;
    }

    @Override // android.view.ContextThemeWrapper
    public void setTheme(Resources.Theme theme) {
        if (this.x) {
            super.setTheme(theme);
            theme = null;
        }
        this.z = theme;
    }

    public void stopLoading() {
        h();
    }
}
