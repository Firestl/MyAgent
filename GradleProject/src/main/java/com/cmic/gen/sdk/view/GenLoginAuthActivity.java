package com.cmic.gen.sdk.view;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cmic.gen.sdk.auth.GenAuthnHelper;
import com.cmic.gen.sdk.auth.GenTokenListener;
import com.cmic.gen.sdk.e.h;
import com.cmic.gen.sdk.e.n;
import com.cmic.gen.sdk.e.q;
import com.cmic.gen.sdk.view.b;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GenLoginAuthActivity extends Activity implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1750a = GenLoginAuthActivity.class.getSimpleName();
    public GenAuthThemeConfig A;
    public int B;
    public int C;
    public boolean D;
    public Dialog E;
    public Handler b;
    public Context c;
    public RelativeLayout d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d f1751e;
    public d f;
    public d g;
    public d h;
    public d i;
    public ArrayList<d> j;
    public ArrayList<String> k;
    public String[] l;
    public com.cmic.gen.sdk.a m;
    public com.cmic.gen.sdk.auth.a n;
    public CheckBox p;
    public RelativeLayout q;
    public RelativeLayout r;
    public GenTokenListener v;
    public RelativeLayout x;
    public String y;
    public String z;
    public String o = "";
    public long s = 0;
    public int t = 0;
    public a u = null;
    public boolean w = true;

    public static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<GenLoginAuthActivity> f1760a;

        public a(GenLoginAuthActivity genLoginAuthActivity) {
            this.f1760a = new WeakReference<>(genLoginAuthActivity);
        }

        private void a(Message message) {
            GenLoginAuthActivity genLoginAuthActivity = this.f1760a.get();
            if (genLoginAuthActivity == null || message.what != 1) {
                return;
            }
            genLoginAuthActivity.c();
            genLoginAuthActivity.k();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                a(message);
            } catch (Exception e2) {
                com.cmic.gen.sdk.d.c.b.add(e2);
                e2.printStackTrace();
            }
        }
    }

    public static class b extends n.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<GenLoginAuthActivity> f1761a;
        public WeakReference<c> b;

        public b(GenLoginAuthActivity genLoginAuthActivity, c cVar) {
            this.f1761a = new WeakReference<>(genLoginAuthActivity);
            this.b = new WeakReference<>(cVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b() {
            c cVar = this.b.get();
            if (this.f1761a.get() == null || cVar == null) {
                return false;
            }
            return cVar.a(false);
        }

        @Override // com.cmic.gen.sdk.e.n.a
        public void a() {
            final GenLoginAuthActivity genLoginAuthActivity = this.f1761a.get();
            genLoginAuthActivity.m.a("logintype", 1);
            h.a(true, false);
            genLoginAuthActivity.n.b(genLoginAuthActivity.m, new com.cmic.gen.sdk.auth.b() { // from class: com.cmic.gen.sdk.view.GenLoginAuthActivity.b.1
                @Override // com.cmic.gen.sdk.auth.b
                public void a(String str, String str2, com.cmic.gen.sdk.a aVar, JSONObject jSONObject) {
                    if (b.this.b()) {
                        long jB = aVar.b("loginTime", 0L);
                        String strB = aVar.b("phonescrip");
                        if (jB != 0) {
                            aVar.a("loginTime", System.currentTimeMillis() - jB);
                        }
                        if (!"103000".equals(str) || TextUtils.isEmpty(strB)) {
                            genLoginAuthActivity.w = false;
                            com.cmic.gen.sdk.d.a.a("authClickFailed");
                        } else {
                            com.cmic.gen.sdk.d.a.a("authClickSuccess");
                            genLoginAuthActivity.w = true;
                        }
                        genLoginAuthActivity.a(str, str2, aVar, jSONObject);
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        genLoginAuthActivity.u.sendEmptyMessage(1);
                    }
                }
            });
        }
    }

    public class c implements Runnable {
        public com.cmic.gen.sdk.a b;
        public boolean c;

        public c(com.cmic.gen.sdk.a aVar) {
            this.b = aVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized boolean a(boolean z) {
            boolean z2;
            z2 = this.c;
            this.c = z;
            return !z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a(true)) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("resultCode", "102507");
                    jSONObject.put("resultString", "请求超时");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                GenLoginAuthActivity.this.w = false;
                com.cmic.gen.sdk.d.a.a("authClickFailed");
                GenLoginAuthActivity.this.u.sendEmptyMessage(1);
                long jB = this.b.b("loginTime", 0L);
                if (jB != 0) {
                    this.b.a("loginTime", System.currentTimeMillis() - jB);
                }
                GenLoginAuthActivity.this.a("102507", "请求超时", this.b, jSONObject);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, com.cmic.gen.sdk.a aVar, JSONObject jSONObject) {
        GenAuthnHelper genAuthnHelper;
        try {
            if (this.b == null) {
                this.b = new Handler(getMainLooper());
                this.u = new a(this);
            }
            this.b.removeCallbacksAndMessages(null);
            if ("103000".equals(str)) {
                if (GenAuthnHelper.getInstance((Context) this) == null || com.cmic.gen.sdk.e.e.c(aVar.b("traceId")) == null) {
                    return;
                }
                aVar.a("keepListener", true);
                genAuthnHelper = GenAuthnHelper.getInstance((Context) this);
            } else {
                if ("200020".equals(str)) {
                    if (GenAuthnHelper.getInstance((Context) this) != null) {
                        if (com.cmic.gen.sdk.e.e.c(aVar.b("traceId")) != null) {
                            GenAuthnHelper.getInstance((Context) this).callBackResult(str, str2, aVar, jSONObject);
                        }
                        a();
                        return;
                    }
                    return;
                }
                aVar.a("keepListener", true);
                genAuthnHelper = GenAuthnHelper.getInstance((Context) this);
            }
            genAuthnHelper.callBackResult(str, str2, aVar, jSONObject);
        } catch (Exception e2) {
            com.cmic.gen.sdk.e.c.a(f1750a, "CallbackResult:未知错误");
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        try {
            com.cmic.gen.sdk.d.a.a("authPageOut");
            a("200020", "登录页面关闭", this.m, null);
        } catch (Exception e2) {
            com.cmic.gen.sdk.d.c.b.add(e2);
            e2.printStackTrace();
        }
    }

    private void d() {
        String str;
        com.cmic.gen.sdk.a aVarD = com.cmic.gen.sdk.e.e.d(getIntent().getStringExtra("traceId"));
        this.m = aVarD;
        if (aVarD == null) {
            this.m = new com.cmic.gen.sdk.a(0);
        }
        this.v = com.cmic.gen.sdk.e.e.c(this.m.b("traceId", ""));
        getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        this.b = new Handler(getMainLooper());
        this.u = new a(this);
        this.o = this.m.b("securityphone");
        com.cmic.gen.sdk.e.c.b(f1750a, "mSecurityPhone value is " + this.o);
        String strB = this.m.b("operatortype", "");
        com.cmic.gen.sdk.e.c.b(f1750a, "operator value is " + strB);
        if (this.A.getAppLanguageType() == 1) {
            this.l = com.cmic.gen.sdk.c.b;
        } else if (this.A.getAppLanguageType() == 2) {
            this.l = com.cmic.gen.sdk.c.c;
        } else {
            this.l = com.cmic.gen.sdk.c.f1692a;
        }
        if (strB.equals("1")) {
            this.y = this.l[0];
            str = "https://wap.cmpassport.com/resources/html/contract.html";
        } else if (strB.equals("3")) {
            this.y = this.l[1];
            str = "https://e.189.cn/sdk/agreement/detail.do?hidetop=true";
        } else {
            this.y = this.l[2];
            str = "https://opencloud.wostore.cn/authz/resource/html/disclaimer.html?fromsdk=true";
        }
        d dVar = new d(this.c, R.style.Theme.Translucent.NoTitleBar, this.y, str);
        this.f1751e = dVar;
        dVar.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.cmic.gen.sdk.view.GenLoginAuthActivity.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                    GenLoginAuthActivity.this.f1751e.b();
                }
                return true;
            }
        });
        this.j = new ArrayList<>();
        this.k = new ArrayList<>();
        if (!TextUtils.isEmpty(this.A.getClauseUrl())) {
            d dVar2 = new d(this.c, R.style.Theme.Translucent.NoTitleBar, this.A.getClauseName(), this.A.getClauseUrl());
            this.f = dVar2;
            dVar2.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.cmic.gen.sdk.view.GenLoginAuthActivity.2
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                        GenLoginAuthActivity.this.f.b();
                    }
                    return true;
                }
            });
            this.j.add(this.f);
            this.k.add(this.A.getClauseName());
        }
        if (!TextUtils.isEmpty(this.A.getClauseUrl2())) {
            d dVar3 = new d(this.c, R.style.Theme.Translucent.NoTitleBar, this.A.getClauseName2(), this.A.getClauseUrl2());
            this.g = dVar3;
            dVar3.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.cmic.gen.sdk.view.GenLoginAuthActivity.3
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                        GenLoginAuthActivity.this.g.b();
                    }
                    return true;
                }
            });
            this.j.add(this.g);
            this.k.add(this.A.getClauseName2());
        }
        if (!TextUtils.isEmpty(this.A.getClauseUrl3())) {
            d dVar4 = new d(this.c, R.style.Theme.Translucent.NoTitleBar, this.A.getClauseName3(), this.A.getClauseUrl3());
            this.h = dVar4;
            dVar4.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.cmic.gen.sdk.view.GenLoginAuthActivity.4
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                        GenLoginAuthActivity.this.h.b();
                    }
                    return true;
                }
            });
            this.j.add(this.h);
            this.k.add(this.A.getClauseName3());
        }
        if (!TextUtils.isEmpty(this.A.getClauseUrl4())) {
            d dVar5 = new d(this.c, R.style.Theme.Translucent.NoTitleBar, this.A.getClauseName4(), this.A.getClauseUrl4());
            this.i = dVar5;
            dVar5.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.cmic.gen.sdk.view.GenLoginAuthActivity.5
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                        GenLoginAuthActivity.this.i.b();
                    }
                    return true;
                }
            });
            this.j.add(this.i);
            this.k.add(this.A.getClauseName4());
        }
        j();
        if (this.A.isPrivacyBookSymbol()) {
            for (int i = 0; i < this.k.size(); i++) {
                String str2 = String.format("《%s》", this.k.get(i));
                this.z = this.z.replaceFirst(this.k.get(i), str2);
                this.k.set(i, str2);
            }
        }
        com.cmic.gen.sdk.view.b.a().a(new b.a() { // from class: com.cmic.gen.sdk.view.GenLoginAuthActivity.6
            @Override // com.cmic.gen.sdk.view.b.a
            public void a() {
                GenLoginAuthActivity.this.b.removeCallbacksAndMessages(null);
                if (GenLoginAuthActivity.this.f1751e != null && GenLoginAuthActivity.this.f1751e.isShowing()) {
                    GenLoginAuthActivity.this.f1751e.dismiss();
                }
                if (GenLoginAuthActivity.this.f != null && GenLoginAuthActivity.this.f.isShowing()) {
                    GenLoginAuthActivity.this.f.dismiss();
                }
                GenLoginAuthActivity.this.a(true);
            }
        });
    }

    private void e() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.r.getLayoutParams();
        if (this.A.getNumFieldOffsetY() > 0 || this.A.getNumFieldOffsetY_B() < 0) {
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            this.r.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            com.cmic.gen.sdk.e.c.b(f1750a, "mPhoneLayout.getMeasuredHeight()=" + this.r.getMeasuredHeight());
            if (this.A.getNumFieldOffsetY() <= 0 || (this.B - this.r.getMeasuredHeight()) - e.a(this.c, this.A.getNumFieldOffsetY()) <= 0) {
                layoutParams.addRule(12, -1);
            } else {
                com.cmic.gen.sdk.e.c.b(f1750a, "numberField_top");
                layoutParams.addRule(10, -1);
                layoutParams.setMargins(0, e.a(this.c, this.A.getNumFieldOffsetY()), 0, 0);
            }
        } else if (this.A.getNumFieldOffsetY_B() <= 0 || (this.B - this.r.getMeasuredHeight()) - e.a(this.c, this.A.getNumFieldOffsetY_B()) <= 0) {
            layoutParams.addRule(10, -1);
        } else {
            com.cmic.gen.sdk.e.c.b(f1750a, "numberField_bottom");
            layoutParams.addRule(12, -1);
            layoutParams.setMargins(0, 0, 0, e.a(this.c, this.A.getNumFieldOffsetY_B()));
        }
        this.r.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.d.getLayoutParams();
        int iMax = Math.max(this.A.getLogBtnMarginLeft(), 0);
        int iMax2 = Math.max(this.A.getLogBtnMarginRight(), 0);
        if (this.A.getLogBtnOffsetY() > 0 || this.A.getLogBtnOffsetY_B() < 0) {
            if (this.A.getLogBtnOffsetY() <= 0 || this.B - e.a(this.c, this.A.getLogBtnHeight() + this.A.getLogBtnOffsetY()) <= 0) {
                layoutParams2.addRule(12, -1);
                layoutParams2.setMargins(e.a(this.c, iMax), 0, e.a(this.c, iMax2), 0);
            } else {
                com.cmic.gen.sdk.e.c.b(f1750a, "logBtn_top");
                layoutParams2.addRule(10, -1);
                layoutParams2.setMargins(e.a(this.c, iMax), e.a(this.c, this.A.getLogBtnOffsetY()), e.a(this.c, iMax2), 0);
            }
        } else if (this.A.getLogBtnOffsetY_B() <= 0 || this.B - e.a(this.c, this.A.getLogBtnHeight() + this.A.getLogBtnOffsetY_B()) <= 0) {
            layoutParams2.addRule(10, -1);
            layoutParams2.setMargins(e.a(this.c, iMax), 0, e.a(this.c, iMax2), 0);
        } else {
            com.cmic.gen.sdk.e.c.b(f1750a, "logBtn_bottom");
            layoutParams2.addRule(12, -1);
            layoutParams2.setMargins(e.a(this.c, iMax), 0, e.a(this.c, iMax2), e.a(this.c, this.A.getLogBtnOffsetY_B()));
        }
        this.d.setLayoutParams(layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.q.getLayoutParams();
        int privacyMarginLeft = this.A.getPrivacyMarginLeft() >= 0 ? this.A.getCheckedImgWidth() > 30 ? this.A.getPrivacyMarginLeft() : this.A.getPrivacyMarginLeft() - (30 - this.A.getCheckedImgWidth()) : this.A.getCheckedImgWidth() > 30 ? 0 : -(30 - this.A.getCheckedImgWidth());
        int iMax3 = Math.max(this.A.getPrivacyMarginRight(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.q.measure(iMakeMeasureSpec2, iMakeMeasureSpec2);
        if (this.A.getPrivacyOffsetY() > 0 || this.A.getPrivacyOffsetY_B() < 0) {
            if (this.A.getPrivacyOffsetY() <= 0 || (this.B - this.q.getMeasuredHeight()) - e.a(this.c, this.A.getPrivacyOffsetY()) <= 0) {
                com.cmic.gen.sdk.e.c.b(f1750a, "privacy_bottom=" + privacyMarginLeft);
                layoutParams3.addRule(12, -1);
                layoutParams3.setMargins(e.a(this.c, (float) privacyMarginLeft), 0, e.a(this.c, (float) iMax3), 0);
            } else {
                com.cmic.gen.sdk.e.c.b(f1750a, "privacy_top = " + this.q.getMeasuredHeight());
                layoutParams3.addRule(10, -1);
                layoutParams3.setMargins(e.a(this.c, (float) privacyMarginLeft), e.a(this.c, (float) this.A.getPrivacyOffsetY()), e.a(this.c, (float) iMax3), 0);
            }
        } else if (this.A.getPrivacyOffsetY_B() <= 0 || (this.B - this.q.getMeasuredHeight()) - e.a(this.c, this.A.getPrivacyOffsetY_B()) <= 0) {
            layoutParams3.addRule(10, -1);
            layoutParams3.setMargins(e.a(this.c, privacyMarginLeft), 0, e.a(this.c, iMax3), 0);
            com.cmic.gen.sdk.e.c.b(f1750a, "privacy_top");
        } else {
            com.cmic.gen.sdk.e.c.b(f1750a, "privacy_bottom=" + this.q.getMeasuredHeight());
            layoutParams3.addRule(12, -1);
            layoutParams3.setMargins(e.a(this.c, (float) privacyMarginLeft), 0, e.a(this.c, (float) iMax3), e.a(this.c, (float) this.A.getPrivacyOffsetY_B()));
        }
        this.q.setLayoutParams(layoutParams3);
    }

    private void f() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
            getWindow().addFlags(134217728);
            if (this.A.getStatusBarColor() != 0) {
                getWindow().addFlags(Integer.MIN_VALUE);
                getWindow().clearFlags(67108864);
                getWindow().setStatusBarColor(this.A.getStatusBarColor());
                getWindow().setNavigationBarColor(this.A.getStatusBarColor());
            }
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.A.isLightColor()) {
                getWindow().getDecorView().setSystemUiVisibility(8192);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(0);
            }
        }
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        View contentView = this.A.getContentView();
        if (contentView != null) {
            ViewParent parent = contentView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(contentView);
            }
            relativeLayout.addView(contentView);
        } else if (this.A.getLayoutResID() != -1) {
            getLayoutInflater().inflate(this.A.getLayoutResID(), relativeLayout);
        }
        setContentView(relativeLayout);
        int requestedOrientation = getRequestedOrientation();
        this.B = e.b(this.c);
        int iA = e.a(this.c);
        this.C = iA;
        boolean z = true;
        if ((requestedOrientation == 1 && iA > this.B) || (requestedOrientation == 0 && this.C < this.B)) {
            int i = this.C;
            this.C = this.B;
            this.B = i;
        }
        com.cmic.gen.sdk.e.c.b(f1750a, "orientation = " + requestedOrientation + "--screenWidth = " + this.C + "--screenHeight = " + this.B);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (this.A.getWindowWidth() != 0) {
            getWindow().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            getWindowManager().getDefaultDisplay().getSize(new Point());
            attributes.width = e.a(this.c, this.A.getWindowWidth());
            int iA2 = e.a(this.c, this.A.getWindowHeight());
            attributes.height = iA2;
            this.C = attributes.width;
            this.B = iA2;
            attributes.x = e.a(this.c, this.A.getWindowX());
            if (this.A.getWindowBottom() == 1) {
                getWindow().setGravity(80);
            } else {
                attributes.y = e.a(this.c, this.A.getWindowY());
            }
            getWindow().setAttributes(attributes);
        }
        relativeLayout.setFitsSystemWindows(this.A.isFitsSystemWindows());
        relativeLayout.setClipToPadding(true);
        try {
            g();
            relativeLayout.addView(this.r);
            relativeLayout.addView(h());
            relativeLayout.addView(i());
            e();
            this.d.setOnClickListener(this);
            this.x.setOnClickListener(this);
            this.p.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.cmic.gen.sdk.view.GenLoginAuthActivity.7
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                    CheckBox checkBox;
                    GenLoginAuthActivity genLoginAuthActivity;
                    String str;
                    if (GenLoginAuthActivity.this.A.getGenCheckedChangeListener() != null) {
                        GenLoginAuthActivity.this.A.getGenCheckedChangeListener().onCheckedChanged(z2);
                    }
                    boolean z3 = true;
                    if (z2) {
                        GenLoginAuthActivity.this.d.setEnabled(true);
                        try {
                            GenLoginAuthActivity.this.p.setBackgroundResource(com.cmic.gen.sdk.view.c.b(GenLoginAuthActivity.this, GenLoginAuthActivity.this.A.getCheckedImgPath()));
                            return;
                        } catch (Exception unused) {
                            checkBox = GenLoginAuthActivity.this.p;
                            genLoginAuthActivity = GenLoginAuthActivity.this;
                            str = "umcsdk_check_image";
                        }
                    } else {
                        RelativeLayout relativeLayout2 = GenLoginAuthActivity.this.d;
                        if (GenLoginAuthActivity.this.A.getGenCheckBoxListener() == null && TextUtils.isEmpty(GenLoginAuthActivity.this.A.getCheckTipText())) {
                            z3 = false;
                        }
                        relativeLayout2.setEnabled(z3);
                        try {
                            GenLoginAuthActivity.this.p.setBackgroundResource(com.cmic.gen.sdk.view.c.b(GenLoginAuthActivity.this, GenLoginAuthActivity.this.A.getUncheckedImgPath()));
                            return;
                        } catch (Exception unused2) {
                            checkBox = GenLoginAuthActivity.this.p;
                            genLoginAuthActivity = GenLoginAuthActivity.this;
                            str = "umcsdk_uncheck_image";
                        }
                    }
                    checkBox.setBackgroundResource(com.cmic.gen.sdk.view.c.b(genLoginAuthActivity, str));
                }
            });
            k();
            try {
                if (this.A.isPrivacyState()) {
                    this.p.setChecked(true);
                    this.p.setBackgroundResource(com.cmic.gen.sdk.view.c.b(this, this.A.getCheckedImgPath()));
                    this.d.setEnabled(true);
                    return;
                }
                this.p.setChecked(false);
                RelativeLayout relativeLayout2 = this.d;
                if (this.A.getGenCheckBoxListener() == null && TextUtils.isEmpty(this.A.getCheckTipText())) {
                    z = false;
                }
                relativeLayout2.setEnabled(z);
                this.p.setBackgroundResource(com.cmic.gen.sdk.view.c.b(this, this.A.getUncheckedImgPath()));
            } catch (Exception unused) {
                this.p.setChecked(false);
            }
        } catch (Exception e2) {
            com.cmic.gen.sdk.d.c.b.add(e2);
            e2.printStackTrace();
            com.cmic.gen.sdk.e.c.a(f1750a, e2.toString());
            a("200040", "UI资源加载异常", this.m, null);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:0|2|(1:4)(11:(2:7|(1:9)(1:10))|11|24|12|15|(1:17)|18|26|19|22|23)|5|11|24|12|15|(0)|18|26|19|22|23) */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0069, code lost:
    
        r0.setTextSize(2, 18.0f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0094, code lost:
    
        r0.setTextColor(-13421773);
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void g() {
        /*
            r6 = this;
            android.widget.RelativeLayout r0 = new android.widget.RelativeLayout
            r0.<init>(r6)
            r6.r = r0
            r1 = 13107(0x3333, float:1.8367E-41)
            r0.setId(r1)
            android.widget.RelativeLayout$LayoutParams r0 = new android.widget.RelativeLayout$LayoutParams
            r1 = -2
            r2 = -1
            r0.<init>(r2, r1)
            android.widget.RelativeLayout r2 = r6.r
            r2.setLayoutParams(r0)
            android.widget.TextView r0 = new android.widget.TextView
            r0.<init>(r6)
            android.widget.RelativeLayout$LayoutParams r2 = new android.widget.RelativeLayout$LayoutParams
            r2.<init>(r1, r1)
            r1 = 15
            r0.setGravity(r1)
            com.cmic.gen.sdk.view.GenAuthThemeConfig r1 = r6.A
            int r1 = r1.getNumberOffsetX()
            r3 = 0
            if (r1 != 0) goto L36
            r1 = 13
        L32:
            r2.addRule(r1)
            goto L5d
        L36:
            if (r1 <= 0) goto L5d
            int r4 = r6.C
            int r5 = r0.getWidth()
            int r4 = r4 - r5
            android.content.Context r5 = r6.c
            float r1 = (float) r1
            int r5 = com.cmic.gen.sdk.view.e.a(r5, r1)
            int r4 = r4 - r5
            if (r4 <= 0) goto L53
            android.content.Context r4 = r6.c
            int r1 = com.cmic.gen.sdk.view.e.a(r4, r1)
            r2.setMargins(r1, r3, r3, r3)
            goto L5d
        L53:
            java.lang.String r1 = com.cmic.gen.sdk.view.GenLoginAuthActivity.f1750a
            java.lang.String r4 = "RelativeLayout.ALIGN_PARENT_RIGHT"
            com.cmic.gen.sdk.e.c.b(r1, r4)
            r1 = 11
            goto L32
        L5d:
            r1 = 2
            com.cmic.gen.sdk.view.GenAuthThemeConfig r4 = r6.A     // Catch: java.lang.Exception -> L69
            int r4 = r4.getNumberSize()     // Catch: java.lang.Exception -> L69
            float r4 = (float) r4     // Catch: java.lang.Exception -> L69
            r0.setTextSize(r1, r4)     // Catch: java.lang.Exception -> L69
            goto L6e
        L69:
            r4 = 1099956224(0x41900000, float:18.0)
            r0.setTextSize(r1, r4)
        L6e:
            java.lang.String r1 = r6.o
            r0.setText(r1)
            com.cmic.gen.sdk.view.GenAuthThemeConfig r1 = r6.A
            boolean r1 = r1.isNumberBold()
            if (r1 == 0) goto L80
            android.graphics.Typeface r1 = android.graphics.Typeface.DEFAULT_BOLD
            r0.setTypeface(r1)
        L80:
            r1 = 30583(0x7777, float:4.2856E-41)
            r0.setId(r1)
            android.widget.RelativeLayout r1 = r6.r
            r1.addView(r0, r2)
            com.cmic.gen.sdk.view.GenAuthThemeConfig r1 = r6.A     // Catch: java.lang.Exception -> L94
            int r1 = r1.getNumberColor()     // Catch: java.lang.Exception -> L94
            r0.setTextColor(r1)     // Catch: java.lang.Exception -> L94
            goto L9a
        L94:
            r1 = -13421773(0xffffffffff333333, float:-2.3819765E38)
            r0.setTextColor(r1)
        L9a:
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r3)
            android.widget.RelativeLayout r1 = r6.r
            r1.measure(r0, r0)
            java.lang.String r0 = com.cmic.gen.sdk.view.GenLoginAuthActivity.f1750a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "mPhoneLayout.getMeasuredHeight()="
            r1.append(r2)
            android.widget.RelativeLayout r2 = r6.r
            int r2 = r2.getMeasuredHeight()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.cmic.gen.sdk.e.c.b(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.gen.sdk.view.GenLoginAuthActivity.g():void");
    }

    private RelativeLayout h() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.d = relativeLayout;
        relativeLayout.setId(17476);
        this.d.setLayoutParams(new RelativeLayout.LayoutParams(e.a(this.c, this.A.getLogBtnWidth()), e.a(this.c, this.A.getLogBtnHeight())));
        TextView textView = new TextView(this);
        textView.setTextSize(2, this.A.getLogBtnTextSize());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        textView.setLayoutParams(layoutParams);
        if (this.A.isLogBtnTextBold()) {
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        }
        this.d.addView(textView);
        textView.setText(this.A.getLogBtnText());
        try {
            textView.setTextColor(this.A.getLogBtnTextColor());
        } catch (Exception unused) {
            textView.setTextColor(-1);
        }
        try {
            this.d.setBackgroundResource(com.cmic.gen.sdk.view.c.b(this.c, this.A.getLogBtnBackgroundPath()));
        } catch (Exception e2) {
            e2.printStackTrace();
            this.d.setBackgroundResource(com.cmic.gen.sdk.view.c.b(this.c, "umcsdk_login_btn_bg"));
        }
        return this.d;
    }

    private RelativeLayout i() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.q = relativeLayout;
        relativeLayout.setHorizontalGravity(1);
        this.q.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        int checkedImgWidth = this.A.getCheckedImgWidth();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(e.a(this.c, Math.max(checkedImgWidth, 30)), e.a(this.c, Math.max(this.A.getCheckedImgHeight(), 30)));
        if (this.A.getCheckBoxLocation() == 1) {
            layoutParams.addRule(15, -1);
        }
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        this.x = relativeLayout2;
        relativeLayout2.setId(34952);
        this.x.setLayoutParams(layoutParams);
        CheckBox checkBox = new CheckBox(this);
        this.p = checkBox;
        checkBox.setChecked(false);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(e.a(this.c, this.A.getCheckedImgWidth()), e.a(this.c, this.A.getCheckedImgHeight()));
        layoutParams2.setMargins(e.a(this.c, checkedImgWidth > 30 ? 0.0f : 30 - checkedImgWidth), 0, 0, 0);
        layoutParams2.addRule(11, -1);
        if (this.A.getCheckBoxLocation() == 1) {
            layoutParams2.addRule(15, -1);
        }
        this.p.setLayoutParams(layoutParams2);
        this.x.addView(this.p);
        this.q.addView(this.x);
        TextView textView = new TextView(this);
        textView.setTextSize(2, this.A.getPrivacyTextSize());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.setMargins(e.a(this.c, 5.0f), 0, 0, e.a(this.c, 5.0f));
        layoutParams3.addRule(1, 34952);
        textView.setLayoutParams(layoutParams3);
        this.q.addView(textView);
        textView.setTextColor(this.A.getClauseBaseColor());
        textView.setText(e.a(this, this.z, this.y, this.f1751e, this.j, this.k));
        textView.setLineSpacing(8.0f, 1.0f);
        textView.setIncludeFontPadding(false);
        if (this.A.isPrivacyTextBold()) {
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (this.A.isPrivacyTextGravityCenter()) {
            textView.setGravity(17);
        }
        textView.setHighlightColor(getResources().getColor(R.color.transparent));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.p.setButtonDrawable(new ColorDrawable());
        try {
            this.p.setBackgroundResource(com.cmic.gen.sdk.view.c.b(this, this.A.getUncheckedImgPath()));
        } catch (Exception unused) {
            this.p.setBackgroundResource(com.cmic.gen.sdk.view.c.b(this, "umcsdk_uncheck_image"));
        }
        return this.q;
    }

    private String j() {
        this.z = this.A.getPrivacy();
        if (this.A.isPrivacyBookSymbol()) {
            this.y = String.format("《%s》", this.y);
        }
        if (this.z.contains(GenAuthThemeConfig.PLACEHOLDER)) {
            this.z = this.z.replace(GenAuthThemeConfig.PLACEHOLDER, this.y);
        }
        return this.z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.d.setClickable(true);
        this.p.setClickable(true);
    }

    private void l() {
        this.d.setClickable(false);
        this.p.setClickable(false);
    }

    private void m() {
        try {
            if (this.t >= 5) {
                Toast.makeText(this.c, "网络不稳定,请返回重试其他登录方式", 1).show();
                this.d.setClickable(true);
                return;
            }
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement stackTraceElement : stackTrace) {
                com.cmic.gen.sdk.e.c.a("stack", stackTraceElement.getClassName());
                String className = stackTraceElement.getClassName();
                if (!TextUtils.isEmpty(className) && className.contains("com.cmic.gen.sdk.activity") && !sb.toString().contains(className)) {
                    sb.append(className);
                    sb.append(";");
                }
            }
            this.m.a("loginTime", System.currentTimeMillis());
            String strB = this.m.b("traceId", "");
            if (!TextUtils.isEmpty(strB) && com.cmic.gen.sdk.e.e.a(strB)) {
                String strC = q.c();
                this.m.a("traceId", strC);
                com.cmic.gen.sdk.e.e.a(strC, this.v);
            }
            b();
            l();
            c cVar = new c(this.m);
            this.b.postDelayed(cVar, GenAuthnHelper.getInstance((Context) this).getOverTime());
            n.a(new b(this, cVar));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a() {
        Dialog dialog = null;
        this.b.removeCallbacksAndMessages(null);
        d dVar = this.f1751e;
        if (dVar != null && dVar.isShowing()) {
            this.f1751e.dismiss();
        }
        d dVar2 = this.f;
        if (dVar2 != null && dVar2.isShowing()) {
            this.f.dismiss();
        }
        c();
        this.E = null;
        if (0 != 0 && dialog.isShowing()) {
            this.E.dismiss();
        }
        RelativeLayout relativeLayout = this.q;
        if (relativeLayout != null) {
            relativeLayout.clearAnimation();
        }
        finish();
        if (this.A.getAuthPageActOut() == null || this.A.getActivityIn() == null) {
            return;
        }
        overridePendingTransition(com.cmic.gen.sdk.view.c.c(this, this.A.getActivityIn()), com.cmic.gen.sdk.view.c.c(this, this.A.getAuthPageActOut()));
    }

    public void b() {
        com.cmic.gen.sdk.e.c.a(f1750a, "loginClickStart");
        try {
            this.D = true;
            if (this.A.getGenLoginClickListener() != null) {
                this.A.getGenLoginClickListener().onLoginClickStart(this.c, null);
            } else {
                if (this.E != null) {
                    this.E.show();
                    return;
                }
                AlertDialog alertDialogCreate = new AlertDialog.Builder(this).create();
                this.E = alertDialogCreate;
                alertDialogCreate.setCancelable(false);
                this.E.setCanceledOnTouchOutside(false);
                this.E.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.cmic.gen.sdk.view.GenLoginAuthActivity.8
                    @Override // android.content.DialogInterface.OnKeyListener
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        return i == 4;
                    }
                });
                RelativeLayout relativeLayout = new RelativeLayout(this.E.getContext());
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
                ImageView imageView = new ImageView(this.E.getContext());
                imageView.setImageResource(com.cmic.gen.sdk.view.c.b(this.c, "dialog_loading"));
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(80, 80);
                layoutParams.addRule(13, -1);
                relativeLayout.addView(imageView, layoutParams);
                if (this.E.getWindow() != null) {
                    this.E.getWindow().setDimAmount(0.0f);
                }
                this.E.show();
                this.E.setContentView(relativeLayout);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        com.cmic.gen.sdk.e.c.a(f1750a, "loginClickStart");
    }

    public void c() {
        try {
            com.cmic.gen.sdk.e.c.a(f1750a, "loginClickComplete");
            if (this.A.getGenLoginClickListener() != null && this.D) {
                this.D = false;
                this.A.getGenLoginClickListener().onLoginClickComplete(this.c, null);
            } else if (this.E != null && this.E.isShowing()) {
                this.E.dismiss();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            int id = view.getId();
            if (id != 17476) {
                if (id == 26214) {
                    a(false);
                    return;
                } else {
                    if (id != 34952) {
                        return;
                    }
                    if (this.p.isChecked()) {
                        this.p.setChecked(false);
                        return;
                    } else {
                        this.p.setChecked(true);
                        return;
                    }
                }
            }
            if (!this.p.isChecked()) {
                if (this.A.getPrivacyAnimation() != null) {
                    this.q.startAnimation(AnimationUtils.loadAnimation(this.c, com.cmic.gen.sdk.view.c.c(this.c, this.A.getPrivacyAnimation())));
                }
                if (this.A.getGenCheckBoxListener() != null) {
                    this.A.getGenCheckBoxListener().onLoginClick(this.c, null);
                    return;
                } else if (!TextUtils.isEmpty(this.A.getCheckTipText())) {
                    Toast.makeText(this.c, this.A.getCheckTipText(), 1).show();
                    return;
                }
            }
            this.t++;
            m();
        } catch (Exception e2) {
            com.cmic.gen.sdk.d.c.b.add(e2);
            e2.printStackTrace();
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            try {
                finish();
            } catch (Exception e2) {
                if (this.m == null) {
                    this.m = new com.cmic.gen.sdk.a(0);
                }
                this.m.a().f1723a.add(e2);
                com.cmic.gen.sdk.e.c.a(f1750a, e2.toString());
                e2.printStackTrace();
                a("200025", "发生未知错误", this.m, null);
                return;
            }
        }
        this.c = this;
        GenAuthThemeConfig authThemeConfig = GenAuthnHelper.getInstance((Context) this).getAuthThemeConfig();
        this.A = authThemeConfig;
        if (authThemeConfig != null) {
            if (authThemeConfig.getThemeId() != -1) {
                setTheme(this.A.getThemeId());
            }
            if (this.A.getAuthPageActIn() != null && this.A.getActivityOut() != null) {
                overridePendingTransition(com.cmic.gen.sdk.view.c.c(this, this.A.getAuthPageActIn()), com.cmic.gen.sdk.view.c.c(this, this.A.getActivityOut()));
            }
        }
        com.cmic.gen.sdk.d.a.a("authPageIn");
        this.s = System.currentTimeMillis();
        this.n = com.cmic.gen.sdk.auth.a.a(this);
        d();
        f();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        try {
            this.b.removeCallbacksAndMessages(null);
            com.cmic.gen.sdk.d.a.a("timeOnAuthPage", (System.currentTimeMillis() - this.s) + "");
            com.cmic.gen.sdk.d.a.a("authPrivacyState", this.p.isChecked() ? "1" : "0");
            com.cmic.gen.sdk.d.a.a(this.c.getApplicationContext(), this.m);
            com.cmic.gen.sdk.d.a.a();
            this.E = null;
            com.cmic.gen.sdk.view.b.a().c();
            this.u.removeCallbacksAndMessages(null);
        } catch (Exception e2) {
            com.cmic.gen.sdk.e.c.a(f1750a, "GenLoginAuthActivity clear failed");
            com.cmic.gen.sdk.d.c.b.add(e2);
            e2.printStackTrace();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.isCanceled() || keyEvent.getRepeatCount() != 0) {
            return true;
        }
        if (this.A.getGenBackPressedListener() != null) {
            this.A.getGenBackPressedListener().onBackPressed();
        }
        if (this.A.getWindowWidth() != 0 && !this.A.isBackButton()) {
            return true;
        }
        a(false);
        return true;
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            if (this.m != null) {
                this.m.a("loginMethod", "loginAuth");
            }
            GenAuthnHelper.getInstance((Context) this).loginPageInCallBack("200087", null);
        } catch (Exception e2) {
            this.m.a().f1723a.add(e2);
            a("200025", "发生未知错误", this.m, null);
        }
    }
}
