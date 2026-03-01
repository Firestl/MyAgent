package com.g.gysdk.view;

import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.com.chinatelecom.account.api.a.d;
import com.g.gysdk.EloginActivityParam;
import com.g.gysdk.GyCode;
import com.g.gysdk.GyErrorCode;
import com.g.gysdk.a.aj;
import com.g.gysdk.a.ak;
import com.g.gysdk.a.am;
import com.g.gysdk.a.g;
import com.g.gysdk.a.r;
import com.g.gysdk.a.s;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public int c;
    public com.g.gysdk.a d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f2076a = new AtomicBoolean(false);
    public EloginActivityParam b = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2077e = false;
    public boolean f = false;
    public boolean g = false;
    public String h = "";
    public final AtomicBoolean i = new AtomicBoolean(false);

    private int a(View view, ViewGroup viewGroup) {
        int i = 0;
        while (i < viewGroup.getChildCount() && viewGroup.getChildAt(i) != view) {
            i++;
        }
        return i;
    }

    private void a(View view, String str, boolean z) throws Throwable {
        if (z) {
            int currentTextColor = ((TextView) view).getCurrentTextColor();
            int i = (((-16777216) & currentTextColor) >> 24) & 255;
            boolean z2 = ((double) i) / 255.0d >= 0.10000000149011612d;
            ak.a(str + " 颜色 ctc:" + String.format("#%08X", Integer.valueOf(currentTextColor)) + " ctcAlpha:" + i + " ctcAlphaOK:" + z2 + " view.alpha:" + view.getAlpha());
            if (!z2 || view.getAlpha() < 0.1f) {
                throw new IllegalStateException("颜色可见度太低 " + str);
            }
        }
        if (view.getWidth() < 10 || view.getHeight() < 10) {
            throw new IllegalStateException("必要元素尺寸太小 " + str);
        }
        if (!view.isShown()) {
            throw new IllegalStateException("不得隐藏必要元素 " + str);
        }
        if (a(view, false)) {
            throw new IllegalStateException("不得遮挡必要元素 " + str);
        }
    }

    public static void a(EloginActivityParam eloginActivityParam, int i, com.g.gysdk.a aVar) {
        com.g.gysdk.b bVar;
        if (eloginActivityParam == null || !eloginActivityParam.isValid()) {
            aj.c("login, 无效的eloginParams");
            bVar = new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.PARAM_ERROR, "无效的eloginParams");
        } else {
            if (s.a().e()) {
                b bVar2 = new b();
                c.a().a(eloginActivityParam.getActivity(), bVar2);
                try {
                    bVar2.b(eloginActivityParam, i, aVar);
                    aj.a("login, 等待用户点击登录按钮");
                    return;
                } catch (Throwable th) {
                    aj.a("login, UIHelper init error", th);
                    com.g.gysdk.a.a(aVar, new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.UNKNOWN_ERROR, "UIHelper init error").a("eLogin", s.a().f()));
                    return;
                }
            }
            aj.c("login, 无效的预登录");
            bVar = new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.INVALID_PRELOGIN, "无效的预登录");
        }
        com.g.gysdk.a.a(aVar, bVar.a("eLogin", s.a().f()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (str == null) {
            str = "unknown error";
        }
        try {
            aj.c("login, UiErrorListener.onError:" + str);
            if (this.b.getUiErrorListener() != null) {
                this.b.getUiErrorListener().onError(str);
            }
        } catch (Throwable th) {
            ak.e(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(View view) {
        try {
            if (this.b.getLoginOnClickListener() == null) {
                return true;
            }
            this.b.getLoginOnClickListener().onClick(view);
            return true;
        } catch (Throwable th) {
            ak.e(th);
            return false;
        }
    }

    private boolean a(View view, boolean z) {
        Rect rect = new Rect();
        if (!(view.getGlobalVisibleRect(rect) && (rect.bottom - rect.top >= view.getMeasuredHeight()) && (rect.right - rect.left >= view.getMeasuredWidth()))) {
            return true;
        }
        while (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup.getVisibility() != 0) {
                return true;
            }
            if (z) {
                Rect rect2 = new Rect();
                viewGroup.getGlobalVisibleRect(rect2);
                if (!rect2.contains(rect)) {
                    return true;
                }
            } else {
                for (int iA = a(view, viewGroup) + 1; iA < viewGroup.getChildCount(); iA++) {
                    View childAt = viewGroup.getChildAt(iA);
                    if (childAt.getVisibility() == 0) {
                        Rect rect3 = new Rect();
                        childAt.getGlobalVisibleRect(rect3);
                        if (Rect.intersects(rect, rect3)) {
                            return true;
                        }
                    }
                }
            }
            view = viewGroup;
        }
        return false;
    }

    public static void b() {
        try {
            String strA = g.a().a(3);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(strA);
            com.g.gysdk.a.a aVar = new com.g.gysdk.a.a();
            aVar.b(jSONObject.getLong("t"));
            aVar.a(jSONObject.getString(d.f1473a));
            com.g.gysdk.a.b.a(aVar);
            g.a().a(3, "");
        } catch (Throwable th) {
            ak.e(th);
        }
    }

    private void b(EloginActivityParam eloginActivityParam, int i, com.g.gysdk.a aVar) {
        this.b = new EloginActivityParam(eloginActivityParam);
        this.c = i;
        this.d = aVar;
        this.h = s.a().f();
        c();
        com.g.gysdk.a.b.a("openAuthPage", GyErrorCode.SUCCESS, this.h, "onCreate");
        e();
        f();
    }

    private void c() {
        b();
        try {
            com.g.gysdk.a.a aVarA = com.g.gysdk.a.b.a("eLogin", false, String.valueOf(GyErrorCode.LOGIN_PAGE_DISMISSED.value), this.h, "ELoginActivity 非正常退出、onCreate时创建");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("t", aVarA.b());
            jSONObject.put(d.f1473a, aVarA.d());
            g.a().a(3, jSONObject.toString());
        } catch (Throwable th) {
            ak.e("save new log buf error", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        g.a().a(3, "");
    }

    private void e() {
        this.b.getNumberTextview().setText(s.a().g());
        this.b.getSloganTextview().setText(s.a().h());
    }

    private void f() {
        this.b.getNumberTextview().addTextChangedListener(new TextWatcher() { // from class: com.g.gysdk.view.b.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                b.this.f2077e = true;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.b.getSloganTextview().addTextChangedListener(new TextWatcher() { // from class: com.g.gysdk.view.b.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                b.this.f = true;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.b.getPrivacyTextview().addTextChangedListener(new TextWatcher() { // from class: com.g.gysdk.view.b.3
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                b.this.g = true;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.b.getLoginButton().setOnClickListener(new View.OnClickListener() { // from class: com.g.gysdk.view.b.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str;
                GyErrorCode gyErrorCode;
                if (b.this.f2076a.get()) {
                    ak.a("GyEloginUIHelper is already destroyed");
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (b.this.d != null) {
                    b.this.d.a();
                }
                boolean zIsChecked = b.this.b.getPrivacyCheckbox().isChecked();
                String strG = b.this.g();
                boolean zA = b.this.a(view);
                StringBuilder sb = new StringBuilder();
                sb.append("login, loginBt onClick isChecked:");
                sb.append(zIsChecked);
                sb.append(", loginState:");
                sb.append(strG != null ? strG : "正常");
                sb.append(" loginOnClickListener:");
                sb.append(zA);
                aj.a(sb.toString());
                com.g.gysdk.a.b.a("clickOnLogin", GyErrorCode.SUCCESS, b.this.h, com.g.gysdk.a.d.a());
                if (!zIsChecked) {
                    str = "隐私协议未打勾";
                    b.this.a("隐私协议未打勾");
                    gyErrorCode = GyErrorCode.PRIVACY_NOT_CHECKED;
                } else {
                    if (zA) {
                        b bVar = b.this;
                        if (strG == null) {
                            if (bVar.i.get()) {
                                com.g.gysdk.a.a(false, b.this.d, new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.OPERATE_DOING, "正在登录中").a("eLogin", s.a().f()));
                                return;
                            } else {
                                b.this.i.set(true);
                                s.a().b(jCurrentTimeMillis, b.this.c, new r() { // from class: com.g.gysdk.view.b.4.1
                                    @Override // com.g.gysdk.a.r
                                    public void a(com.g.gysdk.b bVar2) {
                                        b.this.i.set(false);
                                        aj.a("login, " + bVar2);
                                        com.g.gysdk.a.a(false, b.this.d, bVar2);
                                        b.this.d();
                                    }
                                });
                                return;
                            }
                        }
                        bVar.a("UI不合规不能登录：" + strG);
                        com.g.gysdk.a.b.a("eLogin", GyErrorCode.UI_NOT_COMPLIANCED, b.this.h, "UI不合规不能登录 " + strG);
                        return;
                    }
                    str = "loginOnClickListener异常";
                    b.this.a("loginOnClickListener异常");
                    gyErrorCode = GyErrorCode.ON_CLICK_EXCEPTION;
                }
                com.g.gysdk.a.b.a("eLogin", gyErrorCode, b.this.h, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String g() {
        try {
            if (!c.a().a(this.b.getActivity())) {
                throw new IllegalStateException("不得遮挡 activity");
            }
            if (this.f2077e || !TextUtils.equals(this.b.getNumberTextview().getText(), s.a().g())) {
                throw new IllegalStateException("不得修改必要元素内容 NumberTextview");
            }
            if (this.f || !TextUtils.equals(this.b.getSloganTextview().getText(), s.a().h())) {
                throw new IllegalStateException("不得修改必要元素内容 SloganTextview");
            }
            if (TextUtils.indexOf(this.b.getPrivacyTextview().getText(), s.a().i()[0]) == -1) {
                throw new IllegalStateException("不得修改必要元素内容 PrivacyTextview");
            }
            a((View) this.b.getNumberTextview(), "NumberTextview", true);
            a((View) this.b.getSloganTextview(), "SloganTextview", true);
            a(this.b.getLoginButton(), "LoginButton", false);
            a((View) this.b.getPrivacyCheckbox(), "PrivacyCheckbox", false);
            a((View) this.b.getPrivacyTextview(), "PrivacyTextview", true);
            return null;
        } catch (Throwable th) {
            ak.a("checkLoginState exception:", th);
            return am.a(th);
        }
    }

    public void a() {
        try {
            if (!this.f2076a.compareAndSet(false, true)) {
                ak.e(new IllegalStateException("onDestroy already before"));
                return;
            }
            c.a().a(this.b.getActivity(), false);
            this.b = null;
            com.g.gysdk.a.a(this.d, new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.LOGIN_PAGE_DISMISSED, "界面退出").a("eLogin", this.h));
            this.d = null;
            d();
        } catch (Throwable th) {
            ak.e("onDestroy exception", th);
        }
    }
}
