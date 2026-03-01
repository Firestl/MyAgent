package io.src.dcloud.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.RuningAcitvityUtil;
import java.io.File;
import supwisdom.ne;

/* JADX INFO: loaded from: classes3.dex */
public class DCloudBaseActivity extends FragmentActivity implements IReflectAble {
    public AlertDialog mDebugDialog;
    public DebugSocketStatusReceiver mDebugSocketStatusReceiver;
    public boolean mIsWeexDebuging;
    public Dialog mLoadingPD;
    public SocketCheckReceiver mSocketCheckReceiver;
    public Activity that = this;
    public int loadingSecond = 0;

    public class DebugSocketStatusReceiver extends BroadcastReceiver {
        public DebugSocketStatusReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DCloudBaseActivity.this.debugSocketAlert(intent.getStringExtra(AbsoluteConst.WEEX_DEBUG_CONNECT_BROADCAST_KEY), true);
        }
    }

    public class SocketCheckReceiver extends BroadcastReceiver {
        public SocketCheckReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra(AbsoluteConst.WEEX_DEBUG_PING_IP_KEY);
            if (DCloudBaseActivity.this.mLoadingPD != null) {
                DCloudBaseActivity.this.loadingSecond = 0;
                ((TextView) DCloudBaseActivity.this.mLoadingPD.findViewById(R.id.debugTV)).setText(context.getString(R.string.dcloud_debug_connecting) + "\n(" + stringExtra + ")");
            }
        }
    }

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String str;
            boolean z;
            Object objInvokeMethod = PlatformUtil.invokeMethod("io.dcloud.feature.weex.WeexDevtoolImpl", "getDebugSocketStatus", null, null, null);
            while (true) {
                str = (String) objInvokeMethod;
                if (str != null) {
                    z = false;
                    break;
                } else if (DCloudBaseActivity.this.loadingSecond >= 6) {
                    z = true;
                    break;
                } else {
                    SystemClock.sleep(1000L);
                    DCloudBaseActivity.access$208(DCloudBaseActivity.this);
                    objInvokeMethod = PlatformUtil.invokeMethod("io.dcloud.feature.weex.WeexDevtoolImpl", "getDebugSocketStatus", null, null, null);
                }
            }
            DCloudBaseActivity.this.debugSocketAlert(str, false, z);
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6773a;

        public class a implements DialogInterface.OnClickListener {
            public a() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + "Android/data/" + RuningAcitvityUtil.getAppName(DCloudBaseActivity.this) + "/apps/" + BaseInfo.sDefaultBootApp + "/www/__nvue_debug__");
                if (file.exists()) {
                    file.delete();
                }
                DCloudBaseActivity.this.startActivity(Intent.makeRestartActivityTask(DCloudBaseActivity.this.getPackageManager().getLaunchIntentForPackage(DCloudBaseActivity.this.getPackageName()).getComponent()));
                Runtime.getRuntime().exit(0);
            }
        }

        /* JADX INFO: renamed from: io.src.dcloud.adapter.DCloudBaseActivity$b$b, reason: collision with other inner class name */
        public class DialogInterfaceOnClickListenerC0200b implements DialogInterface.OnClickListener {
            public DialogInterfaceOnClickListenerC0200b() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                DCloudBaseActivity.this.startActivity(Intent.makeRestartActivityTask(DCloudBaseActivity.this.getPackageManager().getLaunchIntentForPackage(DCloudBaseActivity.this.getPackageName()).getComponent()));
                Runtime.getRuntime().exit(0);
            }
        }

        public b(String str) {
            this.f6773a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            DCloudBaseActivity.this.dismissDebugLoading();
            if (DCloudBaseActivity.this.mDebugDialog == null) {
                DCloudBaseActivity.this.mDebugDialog = new AlertDialog.Builder(DCloudBaseActivity.this).setTitle("").setMessage(this.f6773a).setPositiveButton(R.string.dcloud_debug_reconnection_service, new DialogInterfaceOnClickListenerC0200b()).setNegativeButton(R.string.dcloud_common_cancel, new a()).setCancelable(false).show();
            } else {
                if (DCloudBaseActivity.this.mDebugDialog.isShowing()) {
                    return;
                }
                DCloudBaseActivity.this.mDebugDialog.setMessage(this.f6773a);
                DCloudBaseActivity.this.mDebugDialog.show();
            }
        }
    }

    public static /* synthetic */ int access$208(DCloudBaseActivity dCloudBaseActivity) {
        int i = dCloudBaseActivity.loadingSecond;
        dCloudBaseActivity.loadingSecond = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void debugSocketAlert(String str, boolean z, boolean z2) {
        if (z2) {
            dismissDebugLoading();
        }
        debugSocketAlert(str, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissDebugLoading() {
        Dialog dialog = this.mLoadingPD;
        if (dialog != null) {
            dialog.dismiss();
            this.mLoadingPD = null;
        }
    }

    private boolean isWeexDebuging() {
        String appName = RuningAcitvityUtil.getAppName(this);
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append("Android/data/");
        sb.append(appName);
        sb.append("/apps/");
        sb.append(BaseInfo.sDefaultBootApp);
        sb.append("/www/__nvue_debug__");
        return new File(sb.toString()).exists();
    }

    private void showDebugLoading() {
        Dialog dialog = new Dialog(this);
        this.mLoadingPD = dialog;
        dialog.getWindow().setGravity(17);
        this.mLoadingPD.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.mLoadingPD.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.mLoadingPD.setCancelable(false);
        this.mLoadingPD.show();
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.dcloud_weex_debug_progress, (ViewGroup) null);
        TextView textView = (TextView) viewGroup.findViewById(R.id.debugTV);
        String str = (String) PlatformUtil.invokeMethod("io.dcloud.feature.weex.WeexDevtoolImpl", "getCurrentPingIP", null, null, null);
        if (str != null) {
            textView.setText(getString(R.string.dcloud_debug_connecting) + "\n(" + str + ")");
        }
        this.mLoadingPD.setContentView(viewGroup);
        new Thread(new a()).start();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (BaseInfo.SyncDebug) {
            boolean zIsWeexDebuging = isWeexDebuging();
            this.mIsWeexDebuging = zIsWeexDebuging;
            if (zIsWeexDebuging) {
                this.mDebugSocketStatusReceiver = new DebugSocketStatusReceiver();
                ne.a(this).a(this.mDebugSocketStatusReceiver, new IntentFilter(AbsoluteConst.WEEX_DEBUG_CONNECT_BROADCAST));
                this.mSocketCheckReceiver = new SocketCheckReceiver();
                ne.a(this).a(this.mSocketCheckReceiver, new IntentFilter(AbsoluteConst.WEEX_DEBUG_PING_BROADCAST));
                showDebugLoading();
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.mDebugSocketStatusReceiver != null) {
            ne.a(this).a(this.mDebugSocketStatusReceiver);
            ne.a(this).a(this.mSocketCheckReceiver);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onNewIntentImpl(intent);
    }

    public void onNewIntentImpl(Intent intent) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void debugSocketAlert(String str, boolean z) {
        String str2;
        if (AbsoluteConst.WEEX_DEBUG_CONNECT_SUCCESS.equalsIgnoreCase(str)) {
            dismissDebugLoading();
            AlertDialog alertDialog = this.mDebugDialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
                return;
            }
            return;
        }
        if (z) {
            str2 = getString(R.string.dcloud_debug_break_off_reason) + "\n";
        } else {
            str2 = getString(R.string.dcloud_debug_cannot_connect) + "\n";
        }
        new Handler(Looper.getMainLooper()).post(new b(str2 + getString(R.string.dcloud_debug_possible_causes)));
    }
}
