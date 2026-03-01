package supwisdom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.sangfor.sdk.base.SFEventType;
import com.sangfor.sdk.base.SFLaunchReason;
import com.sangfor.sdk.base.SFOnlineType;
import com.sangfor.sdk.base.SFSDKMode;
import com.sangfor.sdk.device.StoreInfoManager;
import com.sangfor.sdk.storageipc.RemoteSyncManager;
import com.sangfor.sdk.utils.SFLogN;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import supwisdom.aa1;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class sb1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public m81 f9149a;
    public Handler b;

    /* JADX INFO: compiled from: Proguard */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ tb1 f9150a;

        public a(tb1 tb1Var) {
            this.f9150a = tb1Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            sb1.this.f9149a.a(SFEventType.SFEventTypeLaunchApp, this.f9150a);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final sb1 f9151a = new sb1(null);
    }

    public /* synthetic */ sb1(rb1 rb1Var) {
        this();
    }

    public final HashMap<String, String> b(Intent intent) {
        if (intent.getExtras() == null) {
            return null;
        }
        try {
            String stringExtra = intent.getStringExtra("com.sangfor.easyapp.extra_all_shared_data");
            if (stringExtra == null) {
                return null;
            }
            return RemoteSyncManager.a(stringExtra);
        } catch (Throwable th) {
            SFLogN.b("MasterSlaveModeManager", "updateCompleteData failed.", "exception occured, msg:" + th.getMessage());
            return null;
        }
    }

    public final boolean c(Object obj) {
        return c(aa1.a.f6878a.a(obj));
    }

    public sb1() {
    }

    public final void a(Intent intent) {
        if (intent == null) {
            return;
        }
        intent.removeExtra("com.sangfor.easyapp.extra_hostapp_go_back_base_intent");
        intent.removeExtra("com.sangfor.easyapp.extra_hostapp_go_back_package");
        intent.removeExtra("com.sangfor.easyapp.extra_all_shared_data");
        intent.removeExtra("com.sangfor.easyapp.extra_user_data");
    }

    public final boolean c(Intent intent) {
        if (intent == null) {
            SFLogN.b("MasterSlaveModeManager", "isInterceptForIntent donothing", "intent is null");
            return false;
        }
        SFLogN.c("MasterSlaveModeManager", "isInterceptForIntent: " + intent);
        try {
            Context contextB = w61.b();
            if (contextB != null) {
                SFLogN.c("MasterSlaveModeManager", "use self classloader");
                intent.setExtrasClassLoader(contextB.getClassLoader());
            }
        } catch (Exception e2) {
            SFLogN.a("MasterSlaveModeManager", "isInterceptForIntent error", "exception occured, msg:" + e2.getMessage());
        }
        if (((Intent) intent.getParcelableExtra("com.sangfor.easyapp.extra_hostapp_go_back_base_intent")) != null && !TextUtils.isEmpty(intent.getStringExtra("com.sangfor.easyapp.extra_hostapp_go_back_package"))) {
            SFLogN.c("MasterSlaveModeManager", "is launch app event");
            return true;
        }
        SFLogN.c("MasterSlaveModeManager", "not launch app event");
        String stringExtra = intent.getStringExtra("com.sangfor.easyapp.extra_all_shared_data");
        if (stringExtra != null && !TextUtils.isEmpty(stringExtra)) {
            SFLogN.c("MasterSlaveModeManager", "has launch app extra date");
            return true;
        }
        SFLogN.c("MasterSlaveModeManager", "there is no EXTRA_ALL_SHARED_DATA");
        return false;
    }

    public void b(Object obj) {
        SFLogN.c("MasterSlaveModeManager", "handleNewIntent in");
        a(aa1.c.f6880a.a(obj));
    }

    public e91 a(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            String str = map.get("calling_package");
            if (TextUtils.isEmpty(str)) {
                SFLogN.b("MasterSlaveModeManager", "updateCompleteData failed.", "callingPackage is empty.");
                return null;
            }
            try {
                SFLaunchReason sFLaunchReasonValueOf = SFLaunchReason.ValueOf(Integer.parseInt((String) Objects.requireNonNull(map.get("launch_reason"))));
                e91 e91Var = new e91();
                e91Var.a(str);
                e91Var.a(sFLaunchReasonValueOf);
                e91Var.b(map.get("sdk_version"));
                String str2 = map.get("sdk_mode");
                if (TextUtils.isEmpty(str2)) {
                    SFLogN.b("MasterSlaveModeManager", "generateLaunchInfo get sdkMode fail", "SDK_MODE_KEY value is null");
                } else {
                    SFLogN.c("MasterSlaveModeManager", "generateLaunchInfo get sdkMode = " + str2);
                    try {
                        e91Var.b(Integer.parseInt(str2));
                    } catch (NumberFormatException unused) {
                        SFLogN.a("MasterSlaveModeManager", "generateLaunchInfo setSrcSdkMode is fail", "intent sdk mode is not Integer");
                        e91Var.b(SFSDKMode.MODE_VPN.intValue());
                    }
                }
                String str3 = map.get("hostapp_log_level");
                SFLogN.c("MasterSlaveModeManager", "generateLaunchInfo get logLevel = " + str3);
                try {
                    e91Var.a(Integer.parseInt(str3));
                } catch (NumberFormatException unused2) {
                    SFLogN.a("MasterSlaveModeManager", "generateLaunchInfo setSFLogLevel is fail", "intent logLevel is not Integer");
                    e91Var.a(SFLogN.a());
                }
                return e91Var;
            } catch (Exception e2) {
                SFLogN.a("MasterSlaveModeManager", "processUpdateResult failed", "setLaunchReason exception occurred,map:" + map + " msg:" + e2.getMessage());
            }
        }
        return null;
    }

    public final boolean b(Map<String, String> map) {
        if (!c91.a()) {
            SFLogN.c("MasterSlaveModeManager", "isLaunchforHost is not subApp");
            return true;
        }
        String strA = StoreInfoManager.b().a();
        String str = map.get("calling_package");
        if (TextUtils.isEmpty(strA)) {
            SFLogN.c("MasterSlaveModeManager", "host PackageName is empty");
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            SFLogN.c("MasterSlaveModeManager", "callingPkg is empty");
            return true;
        }
        if (TextUtils.equals(str, strA)) {
            SFLogN.c("MasterSlaveModeManager", "hostPackage is equals callingPkg .");
            return true;
        }
        SFLogN.c("MasterSlaveModeManager", "hostPackage is not equals callingPkg .");
        return false;
    }

    public void b(Intent intent, Map<String, String> map) {
        a(intent, map, null);
    }

    public static sb1 a() {
        return b.f9151a;
    }

    public void a(Object obj) {
        if (!c(obj)) {
            SFLogN.c("MasterSlaveModeManager", "no master slave intent ,no need handle");
            return;
        }
        Intent intentA = aa1.a.f6878a.a(obj);
        Intent intent = (Intent) intentA.getParcelableExtra("com.sangfor.easyapp.extra_hostapp_go_back_base_intent");
        if (intentA.hasExtra("com.sangfor.easyapp.extra_launch_hostapp_reason")) {
            String stringExtra = intentA.getStringExtra("com.sangfor.easyapp.extra_launch_hostapp_reason");
            SFLogN.c("MasterSlaveModeManager", "EXTRA_LAUNCH_HOSTAPP_REASON:  " + stringExtra);
            intent.putExtra("com.sangfor.easyapp.extra_launch_hostapp_reason", stringExtra);
        }
        Bundle bundleExtra = intentA.getBundleExtra("com.sangfor.easyapp.extra_user_data");
        HashMap<String, String> mapB = b(intentA);
        a(intentA);
        aa1.a.f6878a.a(obj, intentA);
        SFLogN.c("MasterSlaveModeManager", "isIntercept, will sendAppCallbackListenerMsg...");
        a(intent, mapB, bundleExtra);
    }

    public final void a(List<Intent> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (Intent intent : list) {
            if (c(intent)) {
                Intent intent2 = (Intent) intent.getParcelableExtra("com.sangfor.easyapp.extra_hostapp_go_back_base_intent");
                if (intent.hasExtra("com.sangfor.easyapp.extra_launch_hostapp_reason")) {
                    String stringExtra = intent.getStringExtra("com.sangfor.easyapp.extra_launch_hostapp_reason");
                    SFLogN.c("MasterSlaveModeManager", "EXTRA_LAUNCH_HOSTAPP_REASON:  " + stringExtra);
                    intent2.putExtra("com.sangfor.easyapp.extra_launch_hostapp_reason", stringExtra);
                }
                Bundle bundleExtra = intent.getBundleExtra("com.sangfor.easyapp.extra_user_data");
                HashMap<String, String> mapB = b(intent);
                a(intent);
                if (intent2 == null && mapB == null) {
                    SFLogN.c("MasterSlaveModeManager", "handleOnNewIntent callbackIntent is null, map is null");
                } else {
                    a(intent2, mapB, bundleExtra);
                }
            }
        }
    }

    public final void a(Intent intent, Map<String, String> map, Bundle bundle) {
        SFLogN.c("MasterSlaveModeManager", "sendAppCallbackListenerMsg in");
        if (map == null && intent == null) {
            SFLogN.b("MasterSlaveModeManager", "sendAppCallbackListenerMsg do nothing", "intent and map is null");
            return;
        }
        if (this.f9149a == null) {
            SFLogN.b("MasterSlaveModeManager", "sendAppCallbackListenerMsg do nothing", "mSFAppCallbackListener is null");
            return;
        }
        if (!b(map)) {
            SFLogN.b("MasterSlaveModeManager", "sendAppCallbackListenerMsg do nothing", "not hostapp launch");
            return;
        }
        e91 e91VarA = a(intent, map);
        if (e91VarA == null) {
            SFLogN.b("MasterSlaveModeManager", "sendAppCallbackListenerMsg do nothing", "finalLaunchInfo is null");
            return;
        }
        if (bundle != null) {
            SFLogN.c("MasterSlaveModeManager", "user data add to launchInfo");
            e91VarA.a(bundle);
        }
        boolean z = Looper.getMainLooper() == Looper.myLooper();
        tb1 tb1Var = new tb1(0L, "", "", e91VarA);
        if (z) {
            this.f9149a.a(SFEventType.SFEventTypeLaunchApp, tb1Var);
        } else {
            this.b.post(new a(tb1Var));
        }
    }

    public final e91 a(Intent intent, Map<String, String> map) {
        e91 e91VarA = a(map);
        if (e91VarA != null) {
            if (c91.a()) {
                SFLogN.b("MasterSlaveModeManager", "sendAppCallbackListenerMsg do nothing", "it's host app");
                String str = map.get("all_shared_data");
                if (!TextUtils.isEmpty(str)) {
                    tb1 tb1Var = new tb1(0L, "", "", e91VarA);
                    m81 m81Var = this.f9149a;
                    if (m81Var != null) {
                        m81Var.a(SFEventType.SFEventTypeLaunchAppPre, tb1Var);
                    }
                    SFOnlineType sFOnlineTypeA = ((d91) b71.b().a().f()).a(str);
                    SFLogN.c("MasterSlaveModeManager", "updateCompleteData ret:" + sFOnlineTypeA);
                    if (sFOnlineTypeA == SFOnlineType.UNKNOWN) {
                        SFLogN.b("MasterSlaveModeManager", "generateLaunchInfo failed.", "updateCompleteData ret SFOnlineType.UNKNOWN");
                        return e91VarA;
                    }
                    if (sFOnlineTypeA == SFOnlineType.INNER) {
                        return e91VarA;
                    }
                    e91VarA.a(sFOnlineTypeA);
                } else {
                    SFLogN.c("MasterSlaveModeManager", "generateLaunchInfo in, sharedData in map is empty.");
                }
            }
            if (intent != null) {
                ub1 ub1Var = new ub1();
                ub1Var.b(intent.getComponent().getPackageName());
                ub1Var.a(intent.getComponent().getClassName());
                ub1Var.a(intent);
                e91VarA.a(ub1Var);
            }
        } else {
            if (intent == null) {
                SFLogN.b("MasterSlaveModeManager", "generateLaunchInfo failed.", "generateLaunchInfo ret null and intent is also null");
                return null;
            }
            SFLogN.c("MasterSlaveModeManager", "launchInfo not have data, maybe is old app pull this");
            ub1 ub1Var2 = new ub1();
            ub1Var2.b(intent.getComponent().getPackageName());
            ub1Var2.a(intent.getComponent().getClassName());
            ub1Var2.a(intent);
            e91VarA = new e91();
            e91VarA.a(SFLaunchReason.Launch_HOSTAPP_AUTH_AUTHORIZATION);
            if (intent.hasExtra("com.sangfor.easyapp.extra_launch_hostapp_reason")) {
                String stringExtra = intent.getStringExtra("com.sangfor.easyapp.extra_launch_hostapp_reason");
                SFLogN.c("MasterSlaveModeManager", "launch reason is: " + stringExtra);
                if (TextUtils.equals(stringExtra, "kLaunchShowAppLock")) {
                    SFLogN.c("MasterSlaveModeManager", "old app pull for applock");
                    e91VarA.a(SFLaunchReason.Launch_HOSTAPP_APPLOCK_AUTHORIZATION);
                }
            }
            e91VarA.a(ub1Var2.a());
            e91VarA.a(ub1Var2);
        }
        return e91VarA;
    }
}
