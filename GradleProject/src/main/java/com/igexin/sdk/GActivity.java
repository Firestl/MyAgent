package com.igexin.sdk;

import android.app.Activity;
import android.os.Bundle;
import com.igexin.push.core.ServiceManager;

/* JADX INFO: loaded from: classes2.dex */
public class GActivity extends Activity {
    public static final String TAG = GActivity.class.getName();

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ServiceManager.getInstance().a((Activity) this);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }
}
