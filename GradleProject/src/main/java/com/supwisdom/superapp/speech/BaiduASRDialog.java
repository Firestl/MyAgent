package com.supwisdom.superapp.speech;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import com.supwisdom.superapp.WXBaseActivity;
import java.util.ArrayList;
import java.util.Arrays;
import supwisdom.pj1;
import supwisdom.rj1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaiduASRDialog extends WXBaseActivity implements rj1.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4008a;
    public int b = 0;
    public Bundle c = new Bundle();
    public rj1 d = rj1.e();

    public abstract void a(float f);

    public abstract void a(int i, int i2);

    @Override // supwisdom.rj1.b
    public void a(pj1 pj1Var) {
    }

    @Override // supwisdom.rj1.b
    public void a(String[] strArr, pj1 pj1Var) {
        b(strArr);
    }

    public abstract void b(String[] strArr);

    @Override // supwisdom.rj1.b
    public void b(String[] strArr, pj1 pj1Var) {
        this.b = 0;
        b(strArr);
        Intent intent = new Intent();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(strArr));
        intent.putStringArrayListExtra("results", arrayList);
        setResult(-1, intent);
    }

    public void l() {
    }

    public final void m() {
        try {
            boolean z = getPackageManager().getActivityInfo(new ComponentName(getPackageName(), getClass().getName()), 128).exported;
            if (z) {
                throw new AndroidRuntimeException(getClass().getName() + ", 'android:exported' should be false, please modify AndroidManifest.xml");
            }
            Log.d("export", "exported:" + z);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public Bundle n() {
        return this.c;
    }

    public abstract void o();

    @Override // supwisdom.rj1.b
    public void onAsrAudio(byte[] bArr, int i, int i2) {
    }

    @Override // supwisdom.rj1.b
    public void onAsrBegin() {
        this.b = 4;
        o();
    }

    @Override // supwisdom.rj1.b
    public void onAsrEnd() {
        this.b = 5;
    }

    @Override // supwisdom.rj1.b
    public void onAsrExit() {
        p();
        a(0, 0);
    }

    @Override // supwisdom.rj1.b
    public void onAsrLongFinish() {
    }

    @Override // supwisdom.rj1.b
    public void onAsrOnlineNluResult(String str) {
    }

    @Override // supwisdom.rj1.b
    public void onAsrReady() {
        this.b = 3;
        q();
    }

    @Override // supwisdom.rj1.b
    public void onAsrVolume(int i, int i2) {
        a(i);
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m();
        Log.i("BaiduASRDialog", "DigitalDialogInput obtained");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.c.putAll(extras);
        }
        this.d.a((rj1.b) this);
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // supwisdom.rj1.b
    public void onOfflineLoaded() {
    }

    @Override // supwisdom.rj1.b
    public void onOfflineUnLoaded() {
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (isFinishing()) {
            return;
        }
        finish();
    }

    public abstract void p();

    public abstract void q();

    public abstract void r();

    public void s() {
    }

    public void t() {
        this.f4008a = this.c.getString("prompt_text");
        r();
    }

    @Override // supwisdom.rj1.b
    public void a(int i, int i2, String str, pj1 pj1Var) {
        a(i, i2);
    }
}
