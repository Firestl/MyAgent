package com.cmic.gen.sdk.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class GenAuthThemeConfig {
    public static final String PLACEHOLDER = "$$运营商条款$$";
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public String G;
    public boolean H;
    public GenBackPressedListener I;
    public GenLoginClickListener J;
    public GenCheckBoxListener K;
    public GenCheckedChangeListener L;
    public String M;
    public String N;
    public int O;
    public int P;
    public boolean Q;
    public String R;
    public String S;
    public String T;
    public String U;
    public String V;
    public String W;
    public String X;
    public String Y;
    public String Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f1746a;
    public int aa;
    public boolean ab;
    public int ac;
    public int ad;
    public boolean ae;
    public int af;
    public int ag;
    public int ah;
    public int ai;
    public int aj;
    public boolean ak;
    public String al;
    public String am;
    public String an;
    public String ao;
    public int ap;
    public int aq;
    public int ar;
    public int as;
    public int at;
    public int au;
    public int av;
    public boolean aw;
    public boolean ax;
    public String ay;
    public int b;
    public boolean c;
    public View d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1747e;
    public int f;
    public String g;
    public int h;
    public int i;
    public int j;
    public String k;
    public int l;
    public int m;
    public ImageView.ScaleType n;
    public int o;
    public boolean p;
    public int q;
    public int r;
    public int s;
    public int t;
    public String u;
    public boolean v;
    public int w;
    public boolean x;
    public int y;
    public String z;

    public static class Builder {
        public String G;
        public boolean H;
        public GenBackPressedListener I;
        public GenLoginClickListener J;
        public GenCheckBoxListener K;
        public GenCheckedChangeListener L;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1748a;
        public int ak;
        public String al;
        public String am;
        public String an;
        public String ao;
        public int ap;
        public int aq;
        public int ar;
        public int as;
        public String ay;
        public String g;
        public int b = 0;
        public boolean c = false;
        public View d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1749e = -1;
        public int f = -1;
        public int h = 17;
        public int i = -1;
        public int j = -16742704;
        public String k = "return_bg";
        public int l = -2;
        public int m = -2;
        public ImageView.ScaleType n = ImageView.ScaleType.CENTER;
        public int o = 18;
        public boolean p = false;
        public int q = -16742704;
        public int r = 0;
        public int s = 184;
        public int t = 0;
        public String u = "本机号码一键登录";
        public boolean v = true;
        public int w = 15;
        public boolean x = false;
        public int y = -1;
        public String z = "umcsdk_login_btn_bg";
        public int A = -1;
        public int B = 36;
        public int C = 46;
        public int D = 46;
        public int E = 254;
        public int F = 0;
        public String M = "umcsdk_check_image";
        public String N = "umcsdk_uncheck_image";
        public int O = 9;
        public int P = 9;
        public boolean Q = false;
        public String R = "登录即同意$$运营商条款$$并使用本机号码登录";
        public String S = null;
        public String T = null;
        public String U = null;
        public String V = null;
        public String W = null;
        public String X = null;
        public String Y = null;
        public String Z = null;
        public int aa = 10;
        public boolean ab = false;
        public int ac = -10066330;
        public int ad = -16007674;
        public boolean ae = false;
        public int af = 52;
        public int ag = 52;
        public int ah = 0;
        public int ai = 30;
        public boolean aj = true;
        public int at = 0;
        public int au = -1;
        public int av = 0;
        public boolean aw = true;
        public boolean ax = true;

        public GenAuthThemeConfig build() {
            return new GenAuthThemeConfig(this);
        }

        public Builder setAppLanguageType(int i) {
            this.av = i;
            return this;
        }

        public Builder setAuthContentView(View view) {
            this.d = view;
            this.f1749e = -1;
            return this;
        }

        public Builder setAuthLayoutResID(int i) {
            this.f1749e = i;
            this.d = null;
            return this;
        }

        public Builder setAuthPageActIn(String str, String str2) {
            this.al = str;
            this.am = str2;
            return this;
        }

        public Builder setAuthPageActOut(String str, String str2) {
            this.an = str2;
            this.ao = str;
            return this;
        }

        public Builder setAuthPageWindowMode(int i, int i2) {
            this.ap = i;
            this.aq = i2;
            return this;
        }

        public Builder setAuthPageWindowOffset(int i, int i2) {
            this.ar = i;
            this.as = i2;
            return this;
        }

        public Builder setBackButton(boolean z) {
            this.ax = z;
            return this;
        }

        public Builder setCheckBoxImgPath(String str, String str2, int i, int i2) {
            this.M = str;
            this.N = str2;
            this.O = i;
            this.P = i2;
            return this;
        }

        public Builder setCheckBoxLocation(int i) {
            this.ak = i;
            return this;
        }

        public Builder setCheckTipText(String str) {
            boolean z = TextUtils.isEmpty(str) || str.length() > 100;
            this.H = z;
            if (z) {
                str = "请勾选同意服务条款";
            }
            this.G = str;
            return this;
        }

        public Builder setCheckedImgPath(String str) {
            this.M = str;
            return this;
        }

        public Builder setClauseColor(int i, int i2) {
            this.ac = i;
            this.ad = i2;
            return this;
        }

        public Builder setClauseLayoutResID(int i, String str) {
            this.f = i;
            this.g = str;
            return this;
        }

        public Builder setFitsSystemWindows(boolean z) {
            this.aw = z;
            return this;
        }

        public Builder setGenBackPressedListener(GenBackPressedListener genBackPressedListener) {
            this.I = genBackPressedListener;
            return this;
        }

        public Builder setGenCheckBoxListener(GenCheckBoxListener genCheckBoxListener) {
            this.K = genCheckBoxListener;
            return this;
        }

        public Builder setGenCheckedChangeListener(GenCheckedChangeListener genCheckedChangeListener) {
            this.L = genCheckedChangeListener;
            return this;
        }

        public Builder setLogBtn(int i, int i2) {
            this.A = i;
            this.B = i2;
            return this;
        }

        public Builder setLogBtnClickListener(GenLoginClickListener genLoginClickListener) {
            this.J = genLoginClickListener;
            return this;
        }

        public Builder setLogBtnImgPath(String str) {
            this.z = str;
            return this;
        }

        public Builder setLogBtnMargin(int i, int i2) {
            this.C = i;
            this.D = i2;
            return this;
        }

        public Builder setLogBtnOffsetY(int i) {
            this.E = i;
            this.F = 0;
            return this;
        }

        public Builder setLogBtnOffsetY_B(int i) {
            this.F = i;
            this.E = 0;
            return this;
        }

        public Builder setLogBtnText(String str) {
            if (!TextUtils.isEmpty(str) && !Pattern.compile("^\\s*\\n*$").matcher(str).matches()) {
                this.u = str;
                this.v = false;
            }
            return this;
        }

        public Builder setLogBtnText(String str, int i, int i2, boolean z) {
            if (!TextUtils.isEmpty(str) && !Pattern.compile("^\\s*\\n*$").matcher(str).matches()) {
                this.u = str;
                this.v = false;
            }
            this.y = i;
            this.w = i2;
            this.x = z;
            return this;
        }

        public Builder setLogBtnTextColor(int i) {
            this.y = i;
            return this;
        }

        public Builder setNavColor(int i) {
            this.j = i;
            return this;
        }

        public Builder setNavTextColor(int i) {
            this.i = i;
            return this;
        }

        public Builder setNavTextSize(int i) {
            this.h = i;
            return this;
        }

        public Builder setNumFieldOffsetY(int i) {
            this.s = i;
            this.t = 0;
            return this;
        }

        public Builder setNumFieldOffsetY_B(int i) {
            this.t = i;
            this.s = 0;
            return this;
        }

        public Builder setNumberColor(int i) {
            this.q = i;
            return this;
        }

        public Builder setNumberOffsetX(int i) {
            this.r = i;
            return this;
        }

        public Builder setNumberSize(int i, boolean z) {
            if (i > 8) {
                this.o = i;
                this.p = z;
            }
            return this;
        }

        public Builder setPrivacyAlignment(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
            if (str.contains(GenAuthThemeConfig.PLACEHOLDER)) {
                this.R = str;
                this.S = str2;
                this.T = str3;
                this.U = str4;
                this.V = str5;
                this.W = str6;
                this.X = str7;
                this.Y = str8;
                this.Z = str9;
            }
            return this;
        }

        public Builder setPrivacyAnimation(String str) {
            this.ay = str;
            return this;
        }

        public Builder setPrivacyBookSymbol(boolean z) {
            this.aj = z;
            return this;
        }

        public Builder setPrivacyMargin(int i, int i2) {
            this.af = i;
            this.ag = i2;
            return this;
        }

        public Builder setPrivacyOffsetY(int i) {
            this.ah = i;
            this.ai = 0;
            return this;
        }

        public Builder setPrivacyOffsetY_B(int i) {
            this.ai = i;
            this.ah = 0;
            return this;
        }

        public Builder setPrivacyState(boolean z) {
            this.Q = z;
            return this;
        }

        public Builder setPrivacyText(int i, int i2, int i3, boolean z, boolean z2) {
            this.aa = i;
            this.ac = i2;
            this.ad = i3;
            this.ae = z;
            this.ab = z2;
            return this;
        }

        public Builder setStatusBar(int i, boolean z) {
            this.b = i;
            this.c = z;
            return this;
        }

        public Builder setThemeId(int i) {
            this.au = i;
            return this;
        }

        public Builder setUncheckedImgPath(String str) {
            this.N = str;
            return this;
        }

        public Builder setWebDomStorage(boolean z) {
            this.f1748a = z;
            return this;
        }

        public Builder setWindowBottom(int i) {
            this.at = i;
            return this;
        }
    }

    public GenAuthThemeConfig(Builder builder) {
        this.v = true;
        this.W = null;
        this.X = null;
        this.Y = null;
        this.Z = null;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.f1747e = builder.f1749e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
        this.k = builder.k;
        this.l = builder.l;
        this.m = builder.m;
        this.n = builder.n;
        this.o = builder.o;
        this.p = builder.p;
        this.q = builder.q;
        this.r = builder.r;
        this.s = builder.s;
        this.t = builder.t;
        this.u = builder.u;
        this.v = builder.v;
        this.w = builder.w;
        this.x = builder.x;
        this.y = builder.y;
        this.z = builder.z;
        this.A = builder.A;
        this.B = builder.B;
        this.C = builder.C;
        this.D = builder.D;
        this.E = builder.E;
        this.F = builder.F;
        this.G = builder.G;
        this.H = builder.H;
        this.I = builder.I;
        this.J = builder.J;
        this.K = builder.K;
        this.L = builder.L;
        this.M = builder.M;
        this.N = builder.N;
        this.O = builder.O;
        this.P = builder.P;
        this.Q = builder.Q;
        this.R = builder.R;
        this.S = builder.S;
        this.T = builder.T;
        this.U = builder.U;
        this.V = builder.V;
        this.W = builder.W;
        this.X = builder.X;
        this.Y = builder.Y;
        this.Z = builder.Z;
        this.aa = builder.aa;
        this.ab = builder.ab;
        this.ac = builder.ac;
        this.ad = builder.ad;
        this.ae = builder.ae;
        this.ag = builder.af;
        this.ah = builder.ag;
        this.ai = builder.ah;
        this.aj = builder.ai;
        this.ak = builder.aj;
        this.af = builder.ak;
        this.al = builder.al;
        this.am = builder.am;
        this.an = builder.an;
        this.ao = builder.ao;
        this.ap = builder.ap;
        this.aq = builder.aq;
        this.ar = builder.ar;
        this.as = builder.as;
        this.at = builder.at;
        this.au = builder.au;
        this.av = builder.av;
        this.aw = builder.aw;
        this.ax = builder.ax;
        this.ay = builder.ay;
        this.f1746a = builder.f1748a;
    }

    public String getActivityIn() {
        return this.ao;
    }

    public String getActivityOut() {
        return this.am;
    }

    public int getAppLanguageType() {
        return this.av;
    }

    public String getAuthPageActIn() {
        return this.al;
    }

    public String getAuthPageActOut() {
        return this.an;
    }

    public int getCheckBoxLocation() {
        return this.af;
    }

    public String getCheckTipText() {
        if (this.H) {
            int i = this.av;
            this.G = i == 1 ? "請勾選同意服務條款" : i == 2 ? "Please check to agree to the terms of service" : "请勾选同意服务条款";
        }
        return this.G;
    }

    public int getCheckedImgHeight() {
        return this.P;
    }

    public String getCheckedImgPath() {
        return this.M;
    }

    public int getCheckedImgWidth() {
        return this.O;
    }

    public int getClauseBaseColor() {
        return this.ac;
    }

    public int getClauseColor() {
        return this.ad;
    }

    public int getClauseLayoutResID() {
        return this.f;
    }

    public String getClauseLayoutReturnID() {
        return this.g;
    }

    public String getClauseName() {
        return this.S;
    }

    public String getClauseName2() {
        return this.U;
    }

    public String getClauseName3() {
        return this.W;
    }

    public String getClauseName4() {
        return this.Y;
    }

    public String getClauseUrl() {
        return this.T;
    }

    public String getClauseUrl2() {
        return this.V;
    }

    public String getClauseUrl3() {
        return this.X;
    }

    public String getClauseUrl4() {
        return this.Z;
    }

    public View getContentView() {
        return this.d;
    }

    public GenBackPressedListener getGenBackPressedListener() {
        return this.I;
    }

    public GenCheckBoxListener getGenCheckBoxListener() {
        return this.K;
    }

    public GenCheckedChangeListener getGenCheckedChangeListener() {
        return this.L;
    }

    public GenLoginClickListener getGenLoginClickListener() {
        return this.J;
    }

    public int getLayoutResID() {
        return this.f1747e;
    }

    public String getLogBtnBackgroundPath() {
        return this.z;
    }

    public int getLogBtnHeight() {
        return this.B;
    }

    public int getLogBtnMarginLeft() {
        return this.C;
    }

    public int getLogBtnMarginRight() {
        return this.D;
    }

    public int getLogBtnOffsetY() {
        return this.E;
    }

    public int getLogBtnOffsetY_B() {
        return this.F;
    }

    public String getLogBtnText() {
        if (this.v) {
            int i = this.av;
            this.u = i == 1 ? "本機號碼登錄" : i == 2 ? "Login" : this.u;
        }
        return this.u;
    }

    public int getLogBtnTextColor() {
        return this.y;
    }

    public int getLogBtnTextSize() {
        return this.w;
    }

    public int getLogBtnWidth() {
        return this.A;
    }

    public int getNavColor() {
        return this.j;
    }

    public int getNavReturnImgHeight() {
        return this.m;
    }

    public String getNavReturnImgPath() {
        return this.k;
    }

    public ImageView.ScaleType getNavReturnImgScaleType() {
        return this.n;
    }

    public int getNavReturnImgWidth() {
        return this.l;
    }

    public int getNavTextColor() {
        return this.i;
    }

    public int getNavTextSize() {
        return this.h;
    }

    public int getNumFieldOffsetY() {
        return this.s;
    }

    public int getNumFieldOffsetY_B() {
        return this.t;
    }

    public int getNumberColor() {
        return this.q;
    }

    public int getNumberOffsetX() {
        return this.r;
    }

    public int getNumberSize() {
        return this.o;
    }

    public String getPrivacy() {
        return this.R;
    }

    public String getPrivacyAnimation() {
        return this.ay;
    }

    public int getPrivacyMarginLeft() {
        return this.ag;
    }

    public int getPrivacyMarginRight() {
        return this.ah;
    }

    public int getPrivacyOffsetY() {
        return this.ai;
    }

    public int getPrivacyOffsetY_B() {
        return this.aj;
    }

    public int getPrivacyTextSize() {
        return this.aa;
    }

    public int getStatusBarColor() {
        return this.b;
    }

    public int getThemeId() {
        return this.au;
    }

    public String getUncheckedImgPath() {
        return this.N;
    }

    public boolean getWebStorage() {
        return this.f1746a;
    }

    public int getWindowBottom() {
        return this.at;
    }

    public int getWindowHeight() {
        return this.aq;
    }

    public int getWindowWidth() {
        return this.ap;
    }

    public int getWindowX() {
        return this.ar;
    }

    public int getWindowY() {
        return this.as;
    }

    public boolean isBackButton() {
        return this.ax;
    }

    public boolean isFitsSystemWindows() {
        return this.aw;
    }

    public boolean isLightColor() {
        return this.c;
    }

    public boolean isLogBtnTextBold() {
        return this.x;
    }

    public boolean isNumberBold() {
        return this.p;
    }

    public boolean isPrivacyBookSymbol() {
        return this.ak;
    }

    public boolean isPrivacyState() {
        return this.Q;
    }

    public boolean isPrivacyTextBold() {
        return this.ab;
    }

    public boolean isPrivacyTextGravityCenter() {
        return this.ae;
    }
}
