package com.g.gysdk;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class EloginActivityParam {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Activity f1933a;
    public TextView b;
    public TextView c;
    public View d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CheckBox f1934e;
    public TextView f;
    public View.OnClickListener g;
    public UIErrorListener h;

    public interface UIErrorListener {
        void onError(String str);
    }

    public EloginActivityParam() {
    }

    public EloginActivityParam(EloginActivityParam eloginActivityParam) {
        this.f1933a = eloginActivityParam.f1933a;
        this.b = eloginActivityParam.b;
        this.c = eloginActivityParam.c;
        this.d = eloginActivityParam.d;
        this.f1934e = eloginActivityParam.f1934e;
        this.f = eloginActivityParam.f;
        this.g = eloginActivityParam.g;
        this.h = eloginActivityParam.h;
    }

    public Activity getActivity() {
        return this.f1933a;
    }

    public View getLoginButton() {
        return this.d;
    }

    public View.OnClickListener getLoginOnClickListener() {
        return this.g;
    }

    public TextView getNumberTextview() {
        return this.b;
    }

    public CheckBox getPrivacyCheckbox() {
        return this.f1934e;
    }

    public TextView getPrivacyTextview() {
        return this.f;
    }

    public TextView getSloganTextview() {
        return this.c;
    }

    public UIErrorListener getUiErrorListener() {
        return this.h;
    }

    public boolean isValid() {
        return (this.f1933a == null || this.b == null || this.c == null || this.d == null || this.f1934e == null || this.f == null) ? false : true;
    }

    public EloginActivityParam setActivity(Activity activity) {
        this.f1933a = activity;
        return this;
    }

    public EloginActivityParam setLoginButton(View view) {
        this.d = view;
        return this;
    }

    public EloginActivityParam setLoginOnClickListener(View.OnClickListener onClickListener) {
        this.g = onClickListener;
        return this;
    }

    public EloginActivityParam setNumberTextview(TextView textView) {
        this.b = textView;
        return this;
    }

    public EloginActivityParam setPrivacyCheckbox(CheckBox checkBox) {
        this.f1934e = checkBox;
        return this;
    }

    public EloginActivityParam setPrivacyTextview(TextView textView) {
        this.f = textView;
        return this;
    }

    public EloginActivityParam setSloganTextview(TextView textView) {
        this.c = textView;
        return this;
    }

    public EloginActivityParam setUiErrorListener(UIErrorListener uIErrorListener) {
        this.h = uIErrorListener;
        return this;
    }
}
