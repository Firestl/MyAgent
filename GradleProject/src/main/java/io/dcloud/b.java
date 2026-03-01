package io.dcloud;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IOnCreateSplashView;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.adapter.util.UEH;
import io.dcloud.common.core.permission.PermissionControler;
import io.dcloud.common.ui.Info.AndroidPrivacyResponse;
import io.dcloud.common.ui.b;
import io.dcloud.common.util.AppRuntime;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.ErrorDialogUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.TestUtil;
import io.dcloud.common.util.net.http.CookieManager;
import io.dcloud.f.c.a;
import io.dcloud.feature.internal.sdk.SDK;
import java.util.HashMap;
import supwisdom.j7;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b extends io.dcloud.c implements IOnCreateSplashView, io.dcloud.e {
    public String h;
    public String i;
    public AlertDialog j;
    public Runnable n;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f6346e = null;
    public String f = "Main_App";
    public EntryProxy g = null;
    public int k = 20;
    public int l = 9101;
    public int m = 9102;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Bundle f6347a;

        public a(Bundle bundle) {
            this.f6347a = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.d();
            b bVar = b.this;
            bVar.a(bVar.getIntent());
            b.this.f6346e = "Main_Path_" + b.this.f;
            io.dcloud.feature.internal.splash.a.a("Main_App");
            Logger.d(b.this.f6346e, "onCreate appid=" + b.this.f);
            b.this.onRuntimeCreate(this.f6347a);
        }
    }

    /* JADX INFO: renamed from: io.dcloud.b$b, reason: collision with other inner class name */
    public class DialogInterfaceOnClickListenerC0144b implements DialogInterface.OnClickListener {
        public DialogInterfaceOnClickListenerC0144b(b bVar) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Process.killProcess(Process.myPid());
        }
    }

    public class d implements a.b {
        public d() {
        }

        @Override // io.dcloud.f.c.a.b
        public void a(String str, boolean z) {
            DeviceInfo.oaids = str;
            SP.setBundleData(b.this.getContext(), BaseInfo.PDR, "android_ten_ids", str);
        }
    }

    public class e implements b.InterfaceC0157b {
        public e() {
        }

        @Override // io.dcloud.common.ui.b.InterfaceC0157b
        public void a(String str) {
            CookieManager.initCookieConfig(b.this.getApplication());
        }

        @Override // io.dcloud.common.ui.b.InterfaceC0157b
        public void a() {
            b.this.checkAndRequestStoragePermission();
            AppRuntime.initX5(b.this.getApplication());
            AppRuntime.initUniappPlugin(b.this.getApplication());
            CookieManager.initCookieConfig(b.this.getApplication());
        }

        @Override // io.dcloud.common.ui.b.InterfaceC0157b
        public void a(AndroidPrivacyResponse androidPrivacyResponse) {
            b.this.finish();
            Process.killProcess(Process.myPid());
        }
    }

    public class f extends PermissionUtil.Request {
        public f() {
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onDenied(String str) {
            if (str.equals("android.permission.READ_PHONE_STATE") && b.this.h != null && b.this.h.equalsIgnoreCase("ALWAYS")) {
                int i = PdrR.getInt(b.this, "string", "dcloud_permission_read_phone_state_message");
                AlertDialog alertDialog = b.this.j;
                if (alertDialog == null || !alertDialog.isShowing()) {
                    b.this.a(str, i);
                    return;
                }
                return;
            }
            if (str.equals(PermissionUtil.PMS_STORAGE)) {
                if (b.this.i != null && b.this.i.equals("once")) {
                    b.this.checkAndRequestPhoneState();
                    new Handler().postDelayed(b.this.n, r0.k);
                    return;
                }
                int i2 = PdrR.getInt(b.this, "string", "dcloud_permission_write_external_storage_message");
                AlertDialog alertDialog2 = b.this.j;
                if (alertDialog2 == null || !alertDialog2.isShowing()) {
                    b.this.a(PermissionUtil.convert2SystemPermission(str), i2);
                }
            }
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onGranted(String str) {
            if (str.equals(PermissionUtil.PMS_STORAGE)) {
                DeviceInfo.initPath(b.this.that);
                b.this.checkAndRequestPhoneState();
                new Handler().postDelayed(b.this.n, r0.k);
            }
        }
    }

    public class g implements DialogInterface.OnClickListener {
        public g() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            b.this.finish();
        }
    }

    public class h implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6353a;

        public h(String str) {
            this.f6353a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (j7.a((Activity) b.this, PermissionUtil.convert2SystemPermission(this.f6353a))) {
                b.this.a(new String[]{this.f6353a});
                return;
            }
            try {
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", b.this.getPackageName(), null));
                int i2 = b.this.l;
                if (!this.f6353a.equalsIgnoreCase("android.permission.READ_PHONE_STATE")) {
                    i2 = b.this.m;
                }
                b.this.startActivityForResult(intent, i2);
            } catch (Exception unused) {
                b.this.finish();
                Process.killProcess(Process.myPid());
            }
        }
    }

    public void checkAndRequestPhoneState() {
        String str = this.h;
        if (str != null) {
            if (!str.equalsIgnoreCase("once")) {
                if (this.h.equalsIgnoreCase("always")) {
                    a(new String[]{"android.permission.READ_PHONE_STATE"});
                }
            } else {
                if (SP.getBundleData(getContext(), "dcloud_phone_read_state", "isshow").equals("1")) {
                    return;
                }
                SP.setBundleData(getContext(), "dcloud_phone_read_state", "isshow", "1");
                a(new String[]{"android.permission.READ_PHONE_STATE"});
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void checkAndRequestStoragePermission() {
        /*
            r6 = this;
            boolean r0 = io.dcloud.feature.internal.sdk.SDK.isUniMPSDK()
            if (r0 == 0) goto L14
            android.os.Handler r0 = new android.os.Handler
            r0.<init>()
            java.lang.Runnable r1 = r6.n
            int r2 = r6.k
            long r2 = (long) r2
            r0.postDelayed(r1, r2)
            return
        L14:
            java.lang.String r0 = r6.i
            r1 = 0
            java.lang.String r2 = "android.permission.WRITE_EXTERNAL_STORAGE"
            if (r0 == 0) goto L63
            java.lang.String r3 = "once"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L43
            android.content.Context r0 = r6.getContext()
            java.lang.String r3 = "isStorageRequest"
            java.lang.String r4 = "dcloud_phone_read_state"
            java.lang.String r0 = io.dcloud.common.adapter.util.SP.getBundleData(r0, r4, r3)
            java.lang.String r5 = "1"
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L5c
            java.lang.String[] r0 = new java.lang.String[]{r2}
            android.content.Context r2 = r6.getContext()
            io.dcloud.common.adapter.util.SP.setBundleData(r2, r4, r3, r5)
            goto L67
        L43:
            java.lang.String r0 = r6.i
            java.lang.String r3 = "always"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L52
            java.lang.String[] r0 = new java.lang.String[]{r2}
            goto L67
        L52:
            java.lang.String r0 = r6.i
            java.lang.String r3 = "none"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L5e
        L5c:
            r0 = r1
            goto L67
        L5e:
            java.lang.String[] r0 = new java.lang.String[]{r2}
            goto L67
        L63:
            java.lang.String[] r0 = new java.lang.String[]{r2}
        L67:
            if (r0 == 0) goto L6d
            r6.a(r0)
            goto L7d
        L6d:
            r6.checkAndRequestPhoneState()
            android.os.Handler r0 = new android.os.Handler
            r0.<init>()
            java.lang.Runnable r2 = r6.n
            int r3 = r6.k
            long r3 = (long) r3
            r0.postDelayed(r2, r3)
        L7d:
            java.lang.String r0 = "pull"
            io.dcloud.a.a(r6, r1, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.b.checkAndRequestStoragePermission():void");
    }

    public void displayBriefMemory() {
        ((ActivityManager) getSystemService("activity")).getMemoryInfo(new ActivityManager.MemoryInfo());
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = resources.getConfiguration();
        try {
            if (!"none".equals(BaseInfo.sFontScale)) {
                float f2 = configuration.fontScale;
                float f3 = BaseInfo.sFontScaleFloat;
                if (f2 != f3) {
                    configuration.fontScale = f3;
                }
            } else if (configuration.fontScale != 1.0f) {
                configuration.fontScale = 1.0f;
            }
        } catch (Exception unused) {
        }
        return resources;
    }

    public void handleNewIntent(Intent intent) {
        EntryProxy entryProxy;
        setIntent(intent);
        a(intent);
        StringBuilder sb = new StringBuilder();
        sb.append("BaseActivity handleNewIntent =");
        sb.append(this.f);
        sb.append(";");
        sb.append(intent.getFlags() != 274726912);
        Logger.d("syncStartApp", sb.toString());
        if (intent.getFlags() == 274726912 || (entryProxy = this.g) == null) {
            return;
        }
        entryProxy.onNewIntent(this.that, intent);
    }

    public boolean hasAdService() {
        return true;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        String str;
        AlertDialog alertDialog;
        Logger.d(this.f6346e, "onActivityResult");
        PermissionUtil.onActivityResult(this.that, i, i2, intent);
        EntryProxy entryProxy = this.g;
        if (entryProxy != null) {
            entryProxy.onActivityExecute(this.that, ISysEventListener.SysEventType.onActivityResult, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), intent});
        }
        if (i == this.l && (str = this.h) != null && str.equalsIgnoreCase("always") && (alertDialog = this.j) != null && !alertDialog.isShowing()) {
            a(new String[]{"android.permission.READ_PHONE_STATE"});
        }
        if (i == this.m) {
            String str2 = this.i;
            if (str2 == null || !(str2.equalsIgnoreCase("once") || this.i.equalsIgnoreCase("none"))) {
                a(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"});
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        EntryProxy entryProxy;
        if (!BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT) {
            super.onBackPressed();
        } else {
            if (onKeyEventExecute(ISysEventListener.SysEventType.onKeyUp, 4, null) || (entryProxy = this.g) == null) {
                return;
            }
            entryProxy.destroy(this.that);
            super.onBackPressed();
        }
    }

    public void onCloseSplash() {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        try {
            Logger.d(this.f6346e, "onConfigurationChanged");
            int i = getResources().getConfiguration().orientation;
            EntryProxy entryProxy = this.g;
            if (entryProxy != null) {
                entryProxy.onConfigurationChanged(this.that, i);
            }
            super.onConfigurationChanged(configuration);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        io.dcloud.common.ui.b.a().a((Activity) this);
    }

    @Override // io.dcloud.c, io.src.dcloud.adapter.DCloudBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e();
        c();
        String metaValue = AndroidResources.getMetaValue("DCLOUD_READ_PHONE_STATE");
        this.h = metaValue;
        if (metaValue == null) {
            this.h = "once";
        }
        this.n = new a(bundle);
        String metaValue2 = AndroidResources.getMetaValue("DCLOUD_UNISTATISTICS");
        BaseInfo.isUniStatistics = false;
        if (!TextUtils.isEmpty(metaValue2) && Boolean.parseBoolean(metaValue2)) {
            BaseInfo.isUniStatistics = true;
        }
        if (!DCLoudApplicationImpl.self().isInit()) {
            String string = getString(R.string.dcloud_Init_fail_tips);
            BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT = true;
            ErrorDialogUtil.showErrorTipsAlert(this, string, new DialogInterfaceOnClickListenerC0144b(this));
            return;
        }
        String metaValue3 = AndroidResources.getMetaValue("DCLOUD_WRITE_EXTERNAL_STORAGE");
        this.i = metaValue3;
        if (metaValue3 == null) {
            this.i = "once";
        }
        io.dcloud.common.ui.b.a().a(this, new c(), false, false);
        UEH.catchUncaughtException(this.that);
        a(this.that);
        Log.d("download_manager", "BaseActivity onCreate");
        TestUtil.print(TestUtil.START_STREAM_APP, "BaseActivity onCreate");
        onRuntimePreCreate(bundle);
        onCreateSplash(this.that);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        Logger.d(this.f6346e, "onCreateOptionsMenu appid=" + this.f);
        EntryProxy entryProxy = this.g;
        return entryProxy != null ? entryProxy.onActivityExecute(this.that, ISysEventListener.SysEventType.onCreateOptionMenu, menu) : super.onCreateOptionsMenu(menu);
    }

    public abstract Object onCreateSplash(Context context);

    @Override // io.dcloud.c, io.src.dcloud.adapter.DCloudBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        io.dcloud.feature.internal.splash.a.b("Main_App");
        Logger.d(this.f6346e, "onDestroy appid=" + this.f);
        EntryProxy entryProxy = this.g;
        if (entryProxy != null) {
            entryProxy.onStop(this.that);
        }
        HashMap<String, BaseInfo.CmtInfo> map = BaseInfo.mLaunchers;
        if (map != null) {
            map.clear();
        }
        MessageHandler.removeCallbacksAndMessages();
        PermissionControler.clearCRequestPermissionsCache();
        io.dcloud.f.b.e.a().b();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Logger.e("back", "BaseActivity onKeyDown");
        if (!BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT) {
            return super.onKeyDown(i, keyEvent);
        }
        boolean zOnKeyEventExecute = keyEvent.getRepeatCount() == 0 ? onKeyEventExecute(ISysEventListener.SysEventType.onKeyDown, i, keyEvent) : onKeyEventExecute(ISysEventListener.SysEventType.onKeyLongPress, i, keyEvent);
        if (zOnKeyEventExecute && i == 4) {
            onBackPressed();
        }
        return zOnKeyEventExecute ? zOnKeyEventExecute : super.onKeyDown(i, keyEvent);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public boolean onKeyEventExecute(ISysEventListener.SysEventType sysEventType, int i, KeyEvent keyEvent) {
        EntryProxy entryProxy = this.g;
        if (entryProxy != null) {
            return entryProxy.onActivityExecute(this.that, sysEventType, new Object[]{Integer.valueOf(i), keyEvent});
        }
        return false;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        if (!BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT) {
            return super.onKeyLongPress(i, keyEvent);
        }
        EntryProxy entryProxy = this.g;
        boolean zOnActivityExecute = entryProxy != null ? entryProxy.onActivityExecute(this.that, ISysEventListener.SysEventType.onKeyLongPress, new Object[]{Integer.valueOf(i), keyEvent}) : false;
        return zOnActivityExecute ? zOnActivityExecute : super.onKeyLongPress(i, keyEvent);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        EntryProxy entryProxy;
        if (!BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT) {
            return super.onKeyUp(i, keyEvent);
        }
        Logger.d(this.f6346e, "onKeyUp");
        boolean zOnActivityExecute = false;
        if (i != 4 && (entryProxy = this.g) != null) {
            zOnActivityExecute = entryProxy.onActivityExecute(this.that, ISysEventListener.SysEventType.onKeyUp, new Object[]{Integer.valueOf(i), keyEvent});
        }
        return zOnActivityExecute ? zOnActivityExecute : super.onKeyUp(i, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        Logger.d(this.f6346e, "onLowMemory");
        displayBriefMemory();
    }

    @Override // io.src.dcloud.adapter.DCloudBaseActivity
    public void onNewIntentImpl(Intent intent) {
        super.onNewIntentImpl(intent);
        Logger.d("syncStartApp", "BaseActivity onNewIntent appid=" + this.f);
        handleNewIntent(intent);
    }

    @Override // io.dcloud.c, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Logger.d(this.f6346e, "onPause appid=" + this.f);
        EntryProxy entryProxy = this.g;
        if (entryProxy != null) {
            entryProxy.onPause(this.that);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, supwisdom.j7.c
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        PermissionUtil.onSystemPermissionsResult(this.that, i, strArr, iArr);
        EntryProxy entryProxy = this.g;
        if (entryProxy != null) {
            entryProxy.onActivityExecute(this.that, ISysEventListener.SysEventType.onRequestPermissionsResult, new Object[]{Integer.valueOf(i), strArr, iArr});
        }
        PermissionControler.runNextRequestPermission(this, i);
    }

    @Override // io.dcloud.c, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        a(getIntent());
        PermissionUtil.onRequestSysPermissionResume(this.that);
        Logger.d(this.f6346e, "onResume appid=" + this.f);
        EntryProxy entryProxy = this.g;
        if (entryProxy != null) {
            entryProxy.onResume(this.that);
        }
        if (Build.VERSION.SDK_INT < 21 || BaseInfo.mDeStatusBarBackground != -111111) {
            return;
        }
        BaseInfo.mDeStatusBarBackground = getWindow().getStatusBarColor();
    }

    public void onRuntimeCreate(Bundle bundle) {
        Logger.d(this.f6346e, "onRuntimeCreate appid=" + this.f);
        EntryProxy entryProxyInit = EntryProxy.init(this.that);
        this.g = entryProxyInit;
        entryProxyInit.onCreate(this.that, bundle, BaseInfo.sRuntimeMode, (IOnCreateSplashView) null);
    }

    public void onRuntimePreCreate(Bundle bundle) {
        Log.d(this.f6346e, "onRuntimePreCreate appid=" + this.f);
        this.that.getWindow().setFormat(-3);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null && getIntent() != null && getIntent().getExtras() != null) {
            bundle.putAll(getIntent().getExtras());
        }
        Logger.d(this.f6346e, "onSaveInstanceState");
        EntryProxy entryProxy = this.g;
        if (entryProxy != null) {
            entryProxy.onActivityExecute(this.that, ISysEventListener.SysEventType.onSaveInstanceState, new Object[]{bundle});
        }
        super.onSaveInstanceState(bundle);
    }

    public void setSecondPrivacyAlert() {
        io.dcloud.common.ui.b.a().a(this, new e(), true, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void updateParam(String str, Object obj) {
        if ("tab_change".equals(str)) {
            Logger.d("BaseActivity updateParam newintent value(appid)=" + obj);
            this.g.getCoreHandler().dispatchEvent(IMgr.MgrType.AppMgr, 21, obj);
            return;
        }
        if ("closewebapp".equals(str)) {
            Logger.e("IAN", "updateParam closewebapp");
            Activity activity = (Activity) obj;
            Bundle extras = activity.getIntent().getExtras();
            String string = (extras == null || !extras.containsKey("appid")) ? null : extras.getString("appid");
            if (TextUtils.isEmpty(string)) {
                string = BaseInfo.sDefaultBootApp;
            }
            if (activity instanceof IActivityHandler) {
                ((IActivityHandler) activity).closeAppStreamSplash(string);
            }
            this.g.getCoreHandler().dispatchEvent(null, 0, new Object[]{activity, activity.getIntent(), string});
            Logger.e("IAN", "updateParam closewebapp WEBAPP_QUIT");
        }
    }

    private void c() {
        String metaValue = AndroidResources.getMetaValue("DClOUD_SECURITY_POLICY");
        if (TextUtils.isEmpty(metaValue) || !metaValue.equals("safe")) {
            BaseInfo.isDefense = false;
        } else {
            BaseInfo.isDefense = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (PdrUtil.isSupportOaid()) {
            if (PdrUtil.isEmpty(DeviceInfo.oaids) || DeviceInfo.oaids.equals(Operators.OR)) {
                DeviceInfo.oaids = SP.getBundleData(getContext(), BaseInfo.PDR, "android_ten_ids");
                new io.dcloud.f.c.a(new d()).b(this);
            }
        }
    }

    private void e() {
        io.dcloud.f.b.e.a().a(new io.dcloud.f.b.f().getPdrModuleMap());
    }

    public class c implements b.InterfaceC0157b {
        public c() {
        }

        @Override // io.dcloud.common.ui.b.InterfaceC0157b
        public void a(String str) {
            if (str.equalsIgnoreCase("custom")) {
                new Handler().postDelayed(b.this.n, r0.k);
                io.dcloud.a.a(b.this, null, "pull", null);
            } else {
                b.this.checkAndRequestStoragePermission();
            }
            CookieManager.initCookieConfig(b.this.getApplication());
        }

        @Override // io.dcloud.common.ui.b.InterfaceC0157b
        public void a() {
            b.this.checkAndRequestStoragePermission();
            AppRuntime.initX5(b.this.getApplication());
            AppRuntime.initUniappPlugin(b.this.getApplication());
            CookieManager.initCookieConfig(b.this.getApplication());
        }

        @Override // io.dcloud.common.ui.b.InterfaceC0157b
        public void a(AndroidPrivacyResponse androidPrivacyResponse) {
            if (TextUtils.isEmpty(androidPrivacyResponse.second.message)) {
                b.this.finish();
                Process.killProcess(Process.myPid());
            } else {
                b.this.setSecondPrivacyAlert();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String[] strArr) {
        PermissionUtil.useSystemPermissions(this.that, strArr, new f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.that);
        if (i == 0) {
            i = PdrR.getInt(this, "string", "app_name");
        }
        AlertDialog alertDialogCreate = builder.setMessage(i).setPositiveButton(android.R.string.ok, new h(str)).setNegativeButton(android.R.string.cancel, new g()).create();
        this.j = alertDialogCreate;
        alertDialogCreate.setCanceledOnTouchOutside(false);
        this.j.setCancelable(false);
        this.j.show();
    }

    private void a(Context context) {
        if (SDK.isUniMPSDK()) {
            return;
        }
        try {
            String string = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("DCLOUD_AD_ID");
            HashMap map = new HashMap(1);
            map.put("adid", string);
            io.dcloud.a.a(context, null, "save", map);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null || !extras.containsKey("appid")) {
            return;
        }
        this.f = extras.getString("appid");
    }
}
