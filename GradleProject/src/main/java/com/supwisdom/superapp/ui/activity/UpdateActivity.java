package com.supwisdom.superapp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.AppVersionInfo;
import com.supwisdom.zueb.R;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import rx.exceptions.OnErrorFailedException;
import supwisdom.et1;
import supwisdom.lm1;
import supwisdom.mj1;
import supwisdom.pz1;
import supwisdom.sh1;
import supwisdom.sw1;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateActivity extends WXBaseActivity implements View.OnClickListener {
    public static String t = "KEY_UPDATE_STAUTS";
    public static String u = "KEY_DOWNLAOD_URL";
    public static String v = "KEY_DESC";
    public static String w = "com.supwisdom.zueb.INSTALL_APK";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f4371a;
    public View b;
    public View c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4372e;
    public String f;
    public String g;
    public ProgressBar h;
    public int i;
    public String j;
    public LinearLayout k;
    public TextView l;
    public TextView m;
    public String o;
    public CheckBox p;
    public long q;
    public LinearLayout r;
    public int n = 101;

    @SuppressLint({"HandlerLeak"})
    public Handler s = new a();

    public class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    UpdateActivity updateActivity = UpdateActivity.this;
                    updateActivity.b(updateActivity.g, updateActivity.j);
                    return;
                } else {
                    if (i != 3) {
                        return;
                    }
                    Toast.makeText(UpdateActivity.this, "下载apk失败   " + UpdateActivity.this.o, 0).show();
                    return;
                }
            }
            UpdateActivity updateActivity2 = UpdateActivity.this;
            updateActivity2.h.setProgress(updateActivity2.i);
            UpdateActivity.this.l.setText(UpdateActivity.this.i + "%");
            UpdateActivity.this.m.setText(UpdateActivity.this.i + "/100");
        }
    }

    public class b implements sw1<et1> {
        public b() {
        }

        @Override // supwisdom.sw1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onNext(et1 et1Var) {
            UpdateActivity.this.a(et1Var);
        }

        @Override // supwisdom.sw1
        public void onCompleted() {
        }

        @Override // supwisdom.sw1
        public void onError(Throwable th) {
            try {
                if (th instanceof OnErrorFailedException) {
                    th.printStackTrace();
                } else {
                    UpdateActivity.this.o = th.getMessage();
                    UpdateActivity.this.s.sendEmptyMessage(3);
                }
            } catch (Exception e2) {
                lm1.a(e2);
                e2.printStackTrace();
            }
        }
    }

    public void b(String str, String str2) {
        File file = new File(str, str2);
        if (!file.exists()) {
            Log.d("app_update", "apk file is not exist");
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                intent.setFlags(1);
                intent.setDataAndType(FileProvider.getUriForFile(this, getPackageName() + ".UploadFileProvider", file), "application/vnd.android.package-archive");
                if (Build.VERSION.SDK_INT >= 26 && !getPackageManager().canRequestPackageInstalls()) {
                    l();
                    return;
                }
            } else {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                intent.setFlags(268435456);
            }
            if (getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                startActivity(intent);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void l() {
        startActivityForResult(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse("package:" + getPackageName())), this.n);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Log.d("app_update", "" + i2 + Constants.COLON_SEPARATOR + this.n);
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(i2);
        Toast.makeText(this, sb.toString(), 1);
        if (i2 == -1 && i == this.n) {
            Log.d("app_update", "" + this.g + Constants.COLON_SEPARATOR + this.j);
            b(this.g, this.j);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.closeBt) {
            if (this.p.isChecked()) {
                this.q = System.currentTimeMillis();
                sh1.c.a("checkBox", Boolean.valueOf(this.p.isChecked()));
            }
            TimeUnit.HOURS.toMillis(24L);
            setResult(99);
            finish();
            return;
        }
        if (id == R.id.updateBt) {
            this.f4371a.setVisibility(8);
            this.h.setVisibility(0);
            this.k.setVisibility(0);
            this.b.setVisibility(8);
            mj1.b().l(this.f4372e).b(pz1.c()).a(pz1.c()).a(new b());
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.j = bundle.getString("apk_name");
            this.g = bundle.getString("apk_path");
        }
        Log.d("app_update", "onCreate");
        setContentView(R.layout.layout_update);
        this.f4371a = (TextView) findViewById(R.id.updateTitleTxt);
        this.p = (CheckBox) findViewById(R.id.check_box);
        this.r = (LinearLayout) findViewById(R.id.ll_check_box);
        this.c = findViewById(R.id.closeBt);
        this.b = findViewById(R.id.updateBt);
        this.h = (ProgressBar) findViewById(R.id.proBar);
        this.k = (LinearLayout) findViewById(R.id.ll_percent);
        this.l = (TextView) findViewById(R.id.tv_percent1);
        this.m = (TextView) findViewById(R.id.tv_percent2);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d = getIntent().getIntExtra(t, 0);
        this.f4372e = getIntent().getStringExtra(u);
        this.f = getIntent().getStringExtra(v);
        if (this.d == AppVersionInfo.STATUS_TYPE_FORCE_UPDATE) {
            this.c.setVisibility(4);
            this.r.setVisibility(8);
        }
        String str = this.f;
        if (str != null) {
            this.f4371a.setText(str);
        }
        this.h.setMax(100);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (w.equals(intent.getAction())) {
            int i = extras.getInt("android.content.pm.extra.STATUS");
            String string = extras.getString("android.content.pm.extra.STATUS_MESSAGE");
            switch (i) {
                case -1:
                    startActivity((Intent) extras.get("android.intent.extra.INTENT"));
                    break;
                case 0:
                    Toast.makeText(this, "Install succeeded!", 0).show();
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    Toast.makeText(this, "Install failed! " + i + ", " + string, 0).show();
                    break;
                default:
                    Toast.makeText(this, "Unrecognized status received from installer: " + i, 0).show();
                    break;
            }
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Log.d("app_update", "onPause");
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Log.d("app_update", "onResume");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        String str = this.j;
        if (str != null) {
            bundle.putString("apk_name", str);
        }
        String str2 = this.g;
        if (str2 != null) {
            bundle.putString("apk_path", str2);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        Log.d("app_update", "onStart");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Log.d("app_update", "onStop");
    }

    public final void a(et1 et1Var) {
        String str;
        InputStream inputStreamByteStream;
        byte[] bArr;
        int iContentLength;
        int i;
        FileOutputStream fileOutputStream = null;
        if (!"mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable()) {
            str = getFilesDir().getAbsolutePath() + File.separator;
        } else {
            str = getExternalFilesDir(null).getAbsolutePath() + File.separator;
        }
        this.j = "superapp";
        this.g = str + "oil";
        try {
            inputStreamByteStream = et1Var.byteStream();
            File file = new File(this.g);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                fileOutputStream = new FileOutputStream(new File(this.g, this.j));
            } catch (FileNotFoundException e2) {
                lm1.a(e2);
                e2.printStackTrace();
            }
            bArr = new byte[8192];
            iContentLength = (int) et1Var.contentLength();
            i = 0;
        } catch (Throwable th) {
            lm1.a(th);
            th.printStackTrace();
            return;
        }
        while (true) {
            try {
                int i2 = inputStreamByteStream.read(bArr);
                if (i2 != -1) {
                    i += i2;
                    this.i = (int) (((i * 1.0f) / iContentLength) * 100.0f);
                    this.s.sendEmptyMessage(1);
                    fileOutputStream.write(bArr, 0, i2);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    inputStreamByteStream.close();
                    this.s.sendEmptyMessage(2);
                    return;
                }
            } catch (Throwable th2) {
                lm1.a(th2);
                th2.printStackTrace();
                return;
            }
            lm1.a(th);
            th.printStackTrace();
            return;
        }
    }
}
