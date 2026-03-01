package io.dcloud.feature.sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import io.dcloud.WebAppActivity;
import io.dcloud.base.R;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.util.AppRuntime;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.feature.internal.sdk.SDK;
import io.dcloud.feature.sdk.IDCUniMPServer;
import io.dcloud.feature.sdk.Interface.IDCUniMPAppSplashView;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.j7;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DCUniMPActivity extends WebAppActivity {
    public String D;
    public IDCUniMPAppSplashView E;
    public View F;
    public String G;
    public JSONObject K;
    public ArrayList<f> L;
    public IDCUniMPServer mServer;
    public ServiceConnection mServiceConnection = new a();
    public boolean H = false;
    public boolean I = false;
    public JSONObject J = null;

    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onNullBinding(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DCUniMPActivity.this.mServer = IDCUniMPServer.Stub.asInterface(iBinder);
            if (TextUtils.isEmpty(DCUniMPActivity.this.G)) {
                return;
            }
            try {
                DCUniMPActivity dCUniMPActivity = DCUniMPActivity.this;
                dCUniMPActivity.mServer.setRunningAppid(dCUniMPActivity.G);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            DCUniMPActivity dCUniMPActivity = DCUniMPActivity.this;
            dCUniMPActivity.mServer = null;
            dCUniMPActivity.bindMiniAppService();
        }
    }

    public class b implements DialogInterface.OnClickListener {
        public b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            DCUniMPActivity.this.finish();
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f6588a;

        public c(Activity activity) {
            this.f6588a = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            DCUniMPActivity dCUniMPActivity = DCUniMPActivity.this;
            dCUniMPActivity.K = io.dcloud.feature.unimp.e.a(this.f6588a, dCUniMPActivity.G);
        }
    }

    public class d implements DialogInterface.OnClickListener {
        public d() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            DCUniMPActivity.this.finish();
        }
    }

    public class e implements MessageHandler.IMessages {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f6590a;

        public e(int i) {
            this.f6590a = i;
        }

        @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
        public void execute(Object obj) {
            Bundle bundle = new Bundle();
            bundle.putInt("code", this.f6590a);
            DCUniMPActivity.this.callBack(SDK.UNIMP_ERROR_KEY, bundle);
        }
    }

    public class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String[] f6591a;
        public int b;

        public f(DCUniMPActivity dCUniMPActivity, String[] strArr, int i) {
            this.f6591a = strArr;
            this.b = i;
        }
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (!PdrUtil.isEmpty(this.G)) {
            bundle.putString("appid", this.G);
        }
        if (!PdrUtil.isEmpty(this.D)) {
            bundle.putString(io.dcloud.feature.sdk.b.h, this.D);
        }
        bundle.putBoolean("isCapsule", SDK.isCapsule);
    }

    private void i() {
        boolean z = this.H;
        boolean booleanExtra = getIntent().getBooleanExtra("isPre", false);
        this.H = booleanExtra;
        if (booleanExtra) {
            getIntent().removeExtra("isPre");
            moveTaskToBack(true);
        } else {
            if (!z || booleanExtra || this.L == null) {
                return;
            }
            for (int i = 0; i < this.L.size(); i++) {
                f fVar = this.L.get(i);
                requestUniMPPermissions(fVar.f6591a, fVar.b);
            }
            this.L.clear();
        }
    }

    @Override // io.dcloud.WebAppActivity
    public void addViewToContentView(View view) {
        FrameLayout frameLayoutObtainActivityContentView = obtainActivityContentView();
        int iIndexOfChild = frameLayoutObtainActivityContentView.indexOfChild(this.F);
        int childCount = frameLayoutObtainActivityContentView.getChildCount();
        if (childCount <= 0) {
            frameLayoutObtainActivityContentView.addView(view);
            return;
        }
        for (int i = childCount - 1; i >= 0; i--) {
            View childAt = frameLayoutObtainActivityContentView.getChildAt(i);
            if (childAt != view) {
                if ("AppRootView".equals(childAt.getTag())) {
                    frameLayoutObtainActivityContentView.addView(view, i);
                    frameLayoutObtainActivityContentView.removeView(childAt);
                    return;
                } else if (i == 0) {
                    if (childAt == this.F) {
                        frameLayoutObtainActivityContentView.addView(view, 0);
                    } else if (iIndexOfChild > 0) {
                        frameLayoutObtainActivityContentView.addView(view, iIndexOfChild - 1);
                    } else {
                        frameLayoutObtainActivityContentView.addView(view);
                    }
                }
            }
        }
    }

    public abstract void bindMiniAppService();

    @Override // io.dcloud.WebAppActivity, io.dcloud.c, io.dcloud.common.DHInterface.IActivityHandler
    public void callBack(String str, Bundle bundle) {
        str.hashCode();
        if (!str.equals(SDK.UNIMP_ERROR_KEY)) {
            uniMPServerCallBack(str, bundle);
            super.callBack(str, bundle);
            return;
        }
        int i = bundle.getInt("code");
        String str2 = null;
        switch (i) {
            case -1003:
                str2 = String.format(getString(io.dcloud.feature.sdk.b.g), this.G);
                break;
            case -1002:
                str2 = String.format(getString(io.dcloud.feature.sdk.b.f6601e), this.G);
                break;
            case -1001:
                str2 = String.format(getString(io.dcloud.feature.sdk.b.f), this.G);
                break;
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        a(this, str2, new d());
    }

    @Override // io.dcloud.WebAppActivity, android.app.Activity
    public void finish() {
        if (SDK.isEnableBackground) {
            super.finishAndRemoveTask();
        } else {
            super.finish();
        }
        overridePendingTransition(R.anim.dcloud_unimp_def_motionless, R.anim.dcloud_unimp_close_exit);
    }

    @Override // io.dcloud.WebAppActivity, io.dcloud.b
    public void handleNewIntent(Intent intent) {
        intent.removeExtra(io.dcloud.feature.sdk.b.h);
        intent.removeExtra("isCapsule");
        super.handleNewIntent(intent);
    }

    @Override // io.dcloud.WebAppActivity, io.dcloud.c, io.dcloud.common.DHInterface.IActivityHandler
    public boolean isMultiProcessMode() {
        return true;
    }

    @Override // android.app.Activity
    public boolean moveTaskToBack(boolean z) {
        boolean zMoveTaskToBack = super.moveTaskToBack(z);
        overridePendingTransition(R.anim.dcloud_unimp_def_motionless, R.anim.dcloud_unimp_close_exit);
        return zMoveTaskToBack;
    }

    @Override // io.dcloud.WebAppActivity, io.dcloud.c, io.dcloud.common.DHInterface.IActivityHandler
    public void onAsyncStartAppEnd(String str, Object obj) {
        if (obj == null) {
            return;
        }
        JSONObject jSONObject = (JSONObject) obj;
        int iOptInt = jSONObject.optInt("code");
        setRequestedOrientation(jSONObject.optInt("screenOrientation"));
        if (iOptInt < 0) {
            MessageHandler.sendMessage(new e(iOptInt), null);
        }
    }

    @Override // io.dcloud.WebAppActivity, io.dcloud.c, io.dcloud.common.DHInterface.IActivityHandler
    public Object onAsyncStartAppStart(String str) {
        if (this.K == null) {
            this.K = io.dcloud.feature.unimp.e.a(this, str);
        }
        try {
            this.K.put("screenOrientation", io.dcloud.feature.unimp.e.a(this, str, this.K));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        AppRuntime.getInstanceMgr().registerUniappService(this, str);
        return this.K;
    }

    @Override // io.dcloud.WebAppActivity, io.dcloud.b, io.dcloud.common.DHInterface.IOnCreateSplashView
    public void onCloseSplash() {
        super.onCloseSplash();
        IDCUniMPAppSplashView iDCUniMPAppSplashView = this.E;
        if (iDCUniMPAppSplashView != null) {
            iDCUniMPAppSplashView.onCloseSplash(obtainActivityContentView());
        }
        if (!this.H || this.mServer == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("appid", this.G);
        bundle.putInt("code", 1);
        uniMPServerCallBack("unimprepready", bundle);
    }

    @Override // io.dcloud.WebAppActivity, io.dcloud.b, io.dcloud.c, io.src.dcloud.adapter.DCloudBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (bundle != null && !getIntent().hasExtra("appid")) {
            a(bundle);
        }
        if (getIntent().hasExtra("isPreUniJS")) {
            this.I = getIntent().getBooleanExtra("isPreUniJS", this.I);
        }
        if (getIntent().hasExtra("host_unimp_info")) {
            SDK.mHostInfo = getIntent().getStringExtra("host_unimp_info");
            getIntent().removeExtra("host_unimp_info");
        }
        String string = null;
        if (getIntent().hasExtra(io.dcloud.feature.sdk.b.h)) {
            this.D = getIntent().getStringExtra(io.dcloud.feature.sdk.b.h);
            getIntent().removeExtra(io.dcloud.feature.sdk.b.h);
        } else {
            this.D = null;
        }
        if (getIntent().hasExtra("isCapsule")) {
            SDK.isCapsule = getIntent().getBooleanExtra("isCapsule", false);
            getIntent().removeExtra("isCapsule");
        }
        this.G = getIntent().getStringExtra("appid");
        AppRuntime.getInstanceMgr().loadWeexToAppid(this, this.G, this.I);
        BaseInfo.sDefaultBootApp = this.G;
        bindMiniAppService();
        AndroidResources.initAndroidResources(this);
        a((Activity) this);
        super.onCreate(bundle);
        if (TextUtils.isEmpty(BaseInfo.sDefaultBootApp)) {
            string = getString(io.dcloud.feature.sdk.b.c);
        } else if (!BaseInfo.sDefaultBootApp.startsWith("__UNI__")) {
            string = getString(io.dcloud.feature.sdk.b.f6600a);
        }
        if (!TextUtils.isEmpty(string)) {
            BaseInfo.sDefaultBootApp = "";
            getIntent().removeExtra("appid");
        }
        if (!TextUtils.isEmpty(string)) {
            a(this, string, new b());
        }
        IDCUniMPServer iDCUniMPServer = this.mServer;
        if (iDCUniMPServer != null) {
            try {
                iDCUniMPServer.setRunningAppid(this.G);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // io.dcloud.WebAppActivity
    public void onCreateAdSplash(Context context) {
    }

    @Override // io.dcloud.WebAppActivity, io.dcloud.b, io.dcloud.common.DHInterface.IOnCreateSplashView
    public Object onCreateSplash(Context context) {
        String str;
        String strOptString;
        Window window = getWindow();
        int i = Build.VERSION.SDK_INT;
        if (i >= 19 && i <= 25) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.flags |= 1024;
            window.setAttributes(attributes);
        } else if (i > 25) {
            window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() | 1280);
            window.setStatusBarColor(0);
        }
        if (!TextUtils.isEmpty(this.D) && this.F == null) {
            try {
                this.E = (IDCUniMPAppSplashView) PlatformUtil.newInstance(this.D, null, null);
                JSONObject jSONObject = this.J;
                str = "";
                if (jSONObject != null) {
                    String strOptString2 = jSONObject.has("name") ? this.J.optString("name") : "";
                    strOptString = this.J.has("icon") ? this.J.optString("icon") : "";
                    str = strOptString2;
                } else {
                    strOptString = "";
                }
                View splashView = this.E.getSplashView(context, this.G, str, strOptString);
                this.F = splashView;
                if (splashView != null) {
                    setViewAsContentView(splashView, new FrameLayout.LayoutParams(-1, -1));
                }
            } catch (Exception unused) {
            }
        }
        this.I = false;
        return null;
    }

    @Override // io.dcloud.WebAppActivity, io.dcloud.b, io.dcloud.c, io.src.dcloud.adapter.DCloudBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.mServer != null) {
            Bundle bundle = new Bundle();
            bundle.putString("appid", this.G);
            uniMPServerCallBack("uni_oncloseapp", bundle);
        }
        unbindService(this.mServiceConnection);
        this.F = null;
        this.D = null;
    }

    @Override // io.dcloud.WebAppActivity, io.dcloud.b, io.src.dcloud.adapter.DCloudBaseActivity
    public void onNewIntentImpl(Intent intent) {
        i();
        super.onNewIntentImpl(intent);
    }

    @Override // io.dcloud.WebAppActivity, io.dcloud.b, io.dcloud.c, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Bundle bundle = new Bundle();
        bundle.putInt("state", 2);
        uniMPServerCallBack("unimp_on_state", bundle);
    }

    @Override // io.dcloud.WebAppActivity, io.dcloud.b, io.dcloud.c, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Bundle bundle = new Bundle();
        bundle.putInt("state", 1);
        uniMPServerCallBack("unimp_on_state", bundle);
    }

    @Override // io.dcloud.WebAppActivity, io.dcloud.b, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        b(bundle);
        super.onSaveInstanceState(bundle);
    }

    public void openUniMP(String str) {
    }

    public final void requestUniMPPermissions(String[] strArr, int i) {
        if (!this.H) {
            j7.a(this, strArr, i);
            return;
        }
        f fVar = new f(this, strArr, i);
        if (this.L == null) {
            this.L = new ArrayList<>();
        }
        this.L.add(fVar);
    }

    public abstract void uniMPServerCallBack(String str, Bundle bundle);

    private void a(Activity activity) {
        ThreadPool.self().addThreadTask(new c(activity), true);
    }

    private void a(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (bundle.containsKey("appid")) {
            this.I = true;
            getIntent().putExtra("appid", bundle.getString("appid"));
            bundle.remove("appid");
        }
        if (bundle.containsKey(io.dcloud.feature.sdk.b.h)) {
            Intent intent = getIntent();
            String str = io.dcloud.feature.sdk.b.h;
            intent.putExtra(str, bundle.getString(str));
            bundle.remove(io.dcloud.feature.sdk.b.h);
        }
        if (bundle.containsKey("isCapsule")) {
            getIntent().putExtra("isCapsule", bundle.getBoolean("isCapsule"));
            bundle.remove("isCapsule");
        }
    }

    private void a(Context context, String str, DialogInterface.OnClickListener onClickListener) {
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context).create();
        alertDialogCreate.setMessage(str);
        alertDialogCreate.setButton(-1, context.getString(R.string.dcloud_common_ok), onClickListener);
        alertDialogCreate.show();
    }
}
