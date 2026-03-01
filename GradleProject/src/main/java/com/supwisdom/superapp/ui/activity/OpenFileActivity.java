package com.supwisdom.superapp.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.webkit.ValueCallback;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.zueb.R;
import com.taobao.weex.common.Constants;
import com.vivo.push.PushClientConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class OpenFileActivity extends WXBaseActivity implements ValueCallback<String> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4325a = null;

    public OpenFileActivity() {
        new ArrayList();
    }

    public void b(Context context, String str) {
        HashMap map = new HashMap();
        map.put(Constants.Scheme.LOCAL, "true");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PushClientConstants.TAG_PKG_NAME, context.getApplicationContext().getPackageName());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        map.put("menuData", jSONObject.toString());
    }

    public final void e(String str) {
    }

    public final void f(String str) {
        for (File file : new File(str).listFiles()) {
            e(file.getPath());
        }
    }

    @Override // android.webkit.ValueCallback
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public void onReceiveValue(String str) {
        Log.d("test", "onReceiveValue,val =" + str);
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_open);
        this.f4325a = Environment.getExternalStorageDirectory().toString();
        String str = File.separator + "mnt" + File.separator + "sdcard" + getIntent().getStringExtra("path");
        HashMap map = new HashMap();
        map.put(Constants.Scheme.LOCAL, "true");
        map.put("entryId", "2");
        map.put("allowAutoDestory", "true");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PushClientConstants.TAG_PKG_NAME, getApplication().getPackageName());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        map.put("menuData", jSONObject.toString());
        b(this, str);
        f(this.f4325a);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, supwisdom.j7.c
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }
}
