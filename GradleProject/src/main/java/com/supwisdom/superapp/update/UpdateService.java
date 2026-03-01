package com.supwisdom.superapp.update;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.supwisdom.zueb.R;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.utils.WXLogUtils;
import java.io.File;
import supwisdom.jm1;
import supwisdom.km1;
import supwisdom.q7;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateService extends IntentService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public NotificationManager f4390a;
    public q7.d b;

    public UpdateService() {
        super("UpdateService");
    }

    @Override // android.app.IntentService
    public void onHandleIntent(Intent intent) {
        if (intent == null || !"com.taobao.weex.service.action.UPDATE".equals(intent.getAction())) {
            return;
        }
        a(intent.getStringExtra("com.taobao.weex.service.extra.URL"));
    }

    public final void a(String str) {
        this.f4390a = (NotificationManager) getSystemService("notification");
        q7.d dVar = new q7.d(this);
        this.b = dVar;
        dVar.b(jm1.a(R.string.update_downloading));
        dVar.a((CharSequence) (jm1.a(R.string.update_progress) + " 0%"));
        dVar.c(jm1.a(R.string.update_downloading));
        dVar.a(System.currentTimeMillis());
        dVar.a(0);
        dVar.b(R.drawable.push);
        dVar.a(100, 0, false);
        this.f4390a.notify(10006024, this.b.a());
        WXLogUtils.e("Update", "start download");
        km1.a(str, new a(getCacheDir().getAbsolutePath(), "playground.apk"));
    }

    public class a extends km1.b {

        /* JADX INFO: renamed from: com.supwisdom.superapp.update.UpdateService$a$a, reason: collision with other inner class name */
        public class RunnableC0086a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Exception f4391a;

            public RunnableC0086a(Exception exc) {
                this.f4391a = exc;
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(UpdateService.this, "Failed to update:" + this.f4391a.getMessage(), 0).show();
            }
        }

        public a(String str, String str2) {
            super(str, str2);
        }

        @Override // supwisdom.km1.b
        public void a(float f) {
            float f2 = 100.0f * f;
            if (f2 - f >= 1.0f) {
                int i = (int) f2;
                UpdateService.this.b.a((CharSequence) (jm1.a(R.string.update_progress) + i + "%"));
                UpdateService.this.b.a(100, i, false);
                UpdateService.this.f4390a.notify(10006024, UpdateService.this.b.a());
                WXLogUtils.d("Update", "progress:" + i);
            }
        }

        @Override // supwisdom.km1.b
        public void a(File file) {
            WXLogUtils.d("Update", "success: " + file.getAbsolutePath());
            UpdateService.this.f4390a.cancel(10006024);
            Uri uriFromFile = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            if (Build.VERSION.SDK_INT >= 24) {
                intent.setFlags(1);
                intent.setDataAndType(FileProvider.getUriForFile(WXEnvironment.getApplication(), "com.supwisdom.zueb.fileprovider", file), "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(uriFromFile, "application/vnd.android.package-archive");
                intent.addFlags(268435456);
            }
            UpdateService.this.startActivity(intent);
        }

        @Override // supwisdom.km1.b
        public void a(Exception exc) {
            WXSDKManager.getInstance().postOnUiThread(new RunnableC0086a(exc), 0L);
        }
    }
}
