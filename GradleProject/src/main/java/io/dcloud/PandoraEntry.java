package io.dcloud;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.language.LanguageUtil;
import io.dcloud.feature.internal.sdk.SDK;

/* JADX INFO: loaded from: classes2.dex */
public class PandoraEntry extends Activity {

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            PandoraEntry.this.finish();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x007c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x007d A[Catch: Exception -> 0x0096, TryCatch #0 {Exception -> 0x0096, blocks: (B:2:0x0000, B:4:0x001a, B:6:0x0036, B:14:0x007d, B:17:0x0088, B:8:0x003c, B:9:0x0049, B:11:0x0075), top: B:20:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(android.content.Intent r7) {
        /*
            r6 = this;
            boolean r0 = io.dcloud.feature.internal.sdk.SDK.isUniMPSDK()     // Catch: java.lang.Exception -> L96
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L96
            r1.<init>()     // Catch: java.lang.Exception -> L96
            java.lang.String r2 = io.dcloud.common.util.BaseInfo.sDefaultBootApp     // Catch: java.lang.Exception -> L96
            r1.append(r2)     // Catch: java.lang.Exception -> L96
            java.lang.String r2 = "/www/manifest.json"
            r1.append(r2)     // Catch: java.lang.Exception -> L96
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L96
            r2 = 0
            if (r0 == 0) goto L49
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L96
            r0.<init>()     // Catch: java.lang.Exception -> L96
            java.lang.String r2 = io.dcloud.common.util.BaseInfo.sCacheFsAppsPath     // Catch: java.lang.Exception -> L96
            r0.append(r2)     // Catch: java.lang.Exception -> L96
            r0.append(r1)     // Catch: java.lang.Exception -> L96
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L96
            java.io.File r2 = new java.io.File     // Catch: java.lang.Exception -> L96
            r2.<init>(r0)     // Catch: java.lang.Exception -> L96
            boolean r0 = r2.exists()     // Catch: java.lang.Exception -> L96
            if (r0 == 0) goto L3c
            java.io.InputStream r0 = io.dcloud.common.adapter.io.DHFile.getInputStream(r2)     // Catch: java.lang.Exception -> L96
        L3a:
            r2 = r0
            goto L7a
        L3c:
            android.content.res.Resources r0 = r6.getResources()     // Catch: java.lang.Exception -> L96
            android.content.res.AssetManager r0 = r0.getAssets()     // Catch: java.lang.Exception -> L96
            java.io.InputStream r0 = r0.open(r1)     // Catch: java.lang.Exception -> L96
            goto L3a
        L49:
            java.lang.String r0 = android.os.Environment.DIRECTORY_PICTURES     // Catch: java.lang.Exception -> L96
            java.io.File r0 = r6.getExternalFilesDir(r0)     // Catch: java.lang.Exception -> L96
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L96
            r3.<init>()     // Catch: java.lang.Exception -> L96
            java.lang.String r0 = r0.getAbsolutePath()     // Catch: java.lang.Exception -> L96
            java.lang.String r4 = "files/Pictures"
            java.lang.String r5 = "apps/"
            java.lang.String r0 = r0.replace(r4, r5)     // Catch: java.lang.Exception -> L96
            r3.append(r0)     // Catch: java.lang.Exception -> L96
            r3.append(r1)     // Catch: java.lang.Exception -> L96
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Exception -> L96
            java.io.File r1 = new java.io.File     // Catch: java.lang.Exception -> L96
            r1.<init>(r0)     // Catch: java.lang.Exception -> L96
            boolean r0 = r1.exists()     // Catch: java.lang.Exception -> L96
            if (r0 == 0) goto L7a
            java.io.InputStream r0 = io.dcloud.common.adapter.io.DHFile.getInputStream(r1)     // Catch: java.lang.Exception -> L96
            goto L3a
        L7a:
            if (r2 != 0) goto L7d
            return
        L7d:
            java.lang.String r0 = io.dcloud.common.util.IOUtil.toString(r2)     // Catch: java.lang.Exception -> L96
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L96
            if (r1 == 0) goto L88
            return
        L88:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> L96
            r1.<init>(r0)     // Catch: java.lang.Exception -> L96
            int r0 = io.dcloud.common.util.PdrUtil.getConfigOrientation(r1)     // Catch: java.lang.Exception -> L96
            java.lang.String r1 = "__intetn_orientation__"
            r7.putExtra(r1, r0)     // Catch: java.lang.Exception -> L96
        L96:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.PandoraEntry.a(android.content.Intent):void");
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT < 26) {
            super.attachBaseContext(context);
        } else {
            super.attachBaseContext(LanguageUtil.updateContextLanguageAfterO(context, false));
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        AndroidResources.initAndroidResources(getApplication());
        BaseInfo.parseControl();
        if (SDK.isUniMPSDK() && intent.hasExtra("appid")) {
            BaseInfo.sDefaultBootApp = intent.getStringExtra("appid");
        }
        if (SDK.isUniMPSDK() || BaseInfo.SyncDebug) {
            a(intent);
        }
        if (intent.hasExtra(IntentConst.START_FROM_TO_CLASS)) {
            intent.setClassName(getPackageName(), intent.getStringExtra(IntentConst.START_FROM_TO_CLASS));
            intent.removeExtra(IntentConst.START_FROM_TO_CLASS);
        } else {
            intent.putExtra(IntentConst.WEBAPP_SHORT_CUT_CLASS_NAME, PandoraEntry.class.getName());
            intent.setClass(this, PandoraEntryActivity.class);
        }
        startActivity(intent);
        overridePendingTransition(0, 0);
        if (SDK.isUniMPSDK()) {
            finish();
        } else {
            new Handler().postDelayed(new a(), 20L);
        }
    }

    @Override // android.app.Activity
    public void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
    }
}
