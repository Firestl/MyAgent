package com.g.gysdk.cta;

import android.app.Activity;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public interface AuthPageListener {
    void onAuthActivityCreate(Activity activity);

    void onAuthWebActivityCreate(Activity activity);

    void onLoginButtonClick();

    void onPrivacyCheckBoxClick(boolean z);

    void onPrivacyClick(String str, String str2);
}
