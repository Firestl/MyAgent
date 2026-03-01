package com.supwisdom.superapp.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.zueb.R;

/* JADX INFO: loaded from: classes2.dex */
public class OfflineCodeActivity extends WXBaseActivity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Handler f4324a = new Handler(Looper.getMainLooper());
    public String b = "";

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_offline_code);
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Log.i(WXBaseActivity.TAG, "onResume: ========");
        finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }
}
