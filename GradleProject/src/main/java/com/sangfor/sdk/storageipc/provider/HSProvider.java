package com.sangfor.sdk.storageipc.provider;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.sangfor.sdk.device.StoreInfoManager;
import com.sangfor.sdk.storageipc.RemoteSyncManager;
import com.sangfor.sdk.utils.SFLogN;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import supwisdom.b71;
import supwisdom.c91;
import supwisdom.w61;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class HSProvider extends ContentProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ActivityManager f3958a;
    public String b;

    public final Bundle a(String str, String str2, String str3, Bundle bundle) {
        if (!str2.equals("syncPush")) {
            if (!str2.equals("syncPull")) {
                if (!str2.equals("isAlive")) {
                    return super.call(str2, str3, bundle);
                }
                SFLogN.c("HSProvider", "HSProvider remote isAlive call");
                Bundle bundle2 = new Bundle();
                bundle2.putInt("alive_key", b());
                return bundle2;
            }
            HashMap<String, String> mapA = a(bundle);
            SFLogN.c("HSProvider", "HSProvider remote syncPull call");
            if (!a(str)) {
                SFLogN.c("HSProvider", "not secure app, only provide status info: " + str);
                a(mapA);
            }
            a(str, mapA, bundle);
            return RemoteSyncManager.a(mapA);
        }
        Bundle bundle3 = new Bundle();
        SFLogN.c("HSProvider", "HSProvider remote syncPush call");
        try {
            if (!c91.a()) {
                SFLogN.b("HSProvider", "callLegacy METHOD_SYNC_PUSH failed.", "cur app isn't SubApp");
                bundle3.putInt("error", 1);
                return bundle3;
            }
            if (!TextUtils.equals(this.b, str)) {
                bundle3.putInt("error", 3);
                return bundle3;
            }
            a(str, bundle);
            bundle3.putInt("error", 0);
            return bundle3;
        } catch (Exception e2) {
            SFLogN.b("HSProvider", "callLegacy METHOD_SYNC_PUSH failed.", "sdk not init, msg:" + e2.getMessage());
            bundle3.putInt("error", 4);
            return bundle3;
        }
    }

    public final int b() {
        return 17;
    }

    public final boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            SFLogN.b("HSProvider", "isSignatureVerifyPassed return false", "callingPackage is empty");
            return false;
        }
        Context context = getContext();
        if (context == null) {
            SFLogN.b("HSProvider", "isSignatureVerifyPassed return false", "context is null");
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 0);
            if (TextUtils.isEmpty("AppListUtil.getPubKeyInfo(applicationInfo)")) {
                SFLogN.b("HSProvider", "isSignatureVerifyPassed failed", "get pubkeyInfo failed: " + str);
                return false;
            }
            ArrayList<String> whiteSignatureList = RemoteSyncManager.getWhiteSignatureList();
            if (whiteSignatureList == null || whiteSignatureList.isEmpty()) {
                SFLogN.b("HSProvider", "isSignatureVerifyPassed failed", "whiteSignaturelist is empty");
                return false;
            }
            Iterator<String> it = whiteSignatureList.iterator();
            while (it.hasNext()) {
                if ("AppListUtil.getPubKeyInfo(applicationInfo)".equalsIgnoreCase(it.next())) {
                    return true;
                }
            }
            SFLogN.b("HSProvider", "isSignatureVerifyPassed failed", "calling package is not trusted, signatureList: " + whiteSignatureList);
            return false;
        } catch (PackageManager.NameNotFoundException e2) {
            SFLogN.b("HSProvider", "isSignatureVerifyPassed failed", "application not found: " + str, e2);
            return false;
        }
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        if (!w61.a()) {
            Log.w("HSProvider", "not init sdk yet, no need sync data");
            return Bundle.EMPTY;
        }
        String strA = a();
        SFLogN.c("HSProvider", "HSProvider call function call, method: " + str + ",calling package: " + strA);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return a(strA, str, str2, bundle);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        SFLogN.c("HSProvider", "HSProvider onCreate");
        this.f3958a = (ActivityManager) getContext().getSystemService("activity");
        this.b = StoreInfoManager.b().a(getContext(), "host_data");
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public final void a(String str, HashMap<String, String> map, Bundle bundle) {
        try {
            String strA = b71.b().a().d().a(str);
            SFLogN.c("HSProvider", "getSessionData, sessionData is empty: " + TextUtils.isEmpty(strA) + " for " + str);
            if (!TextUtils.isEmpty(strA)) {
                map.put("com.sangfor.data.sdp.info.session", strA);
            }
            map.put("0", "0");
            if (map.containsKey("ShareData.SDKMode")) {
                SFLogN.b("HSProvider", "syncData should not contain SHAREDATA_SDKMODE, but contained, mode is " + map.get("ShareData.SDKMode"), "syncPull data contain SHAREDATA_SDKMODE");
            } else {
                map.put("ShareData.SDKMode", RemoteSyncManager.a(bundle).get("ShareData.SDKMode"));
            }
            map.put("com.sangfor.data.server_type", String.valueOf(3));
            Map<? extends String, ? extends String> mapA = RemoteSyncManager.a.a().a(str);
            if (mapA != null) {
                SFLogN.c("HSProvider", "cached data put to shared map");
                map.putAll(mapA);
            }
        } catch (Exception e2) {
            SFLogN.a("HSProvider", "compateOldSubAppSyncData error", "", e2);
        }
    }

    public final void a(HashMap<String, String> map) {
        if (map != null && !map.isEmpty()) {
            ArrayList<String> nonConfidentialKeys = RemoteSyncManager.getNonConfidentialKeys();
            if (nonConfidentialKeys != null && !nonConfidentialKeys.isEmpty()) {
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> next = it.next();
                    if (!nonConfidentialKeys.contains(next.getKey())) {
                        SFLogN.c("HSProvider", "remove Keys: " + next.getKey());
                        it.remove();
                    }
                }
                return;
            }
            SFLogN.b("HSProvider", "will clear map by removePrivacyDataFromMap", "nonConfidentialKeys is empty");
            map.clear();
            return;
        }
        SFLogN.b("HSProvider", "no need removePrivacyDataFromMap", "map is empty");
    }

    public final boolean a(String str) {
        if ("com.sangfor.vpn.client.awork.std".equals(getContext().getPackageName()) || "com.sangfor.vpn.client.awork".equals(getContext().getPackageName())) {
            SFLogN.c("HSProvider", "isSecureApp, current app is awork, package is " + getContext().getPackageName());
            ArrayList<String> whiteAppList = RemoteSyncManager.getWhiteAppList();
            if (whiteAppList != null && !whiteAppList.isEmpty()) {
                if (!a(str, whiteAppList)) {
                    SFLogN.b("HSProvider", "isSecureApp failed", "callingPackage is not white app,whiteAppList: " + whiteAppList);
                    return false;
                }
            } else {
                SFLogN.b("HSProvider", "isSecureApp failed", "whiteApplist is empty");
                return false;
            }
        }
        if (b(str)) {
            return true;
        }
        SFLogN.b("HSProvider", "callLegacy failed", "callingPackage's signature is not trusted: " + str);
        return false;
    }

    public final boolean a(String str, ArrayList<String> arrayList) {
        if (TextUtils.isEmpty(str)) {
            SFLogN.b("HSProvider", "isSecureApp return false", "callingPackage is empty");
            return false;
        }
        if (arrayList.contains(str)) {
            return true;
        }
        SFLogN.b("HSProvider", "isSecureApp return false", "callingPackage is not secure app: " + str);
        return false;
    }

    public final void a(String str, Bundle bundle) {
        SFLogN.c("HSProvider", "HSProvider syncPush call");
        HashMap<String, String> mapA = RemoteSyncManager.a(bundle);
        if (mapA == null) {
            SFLogN.b("HSProvider", "syncPush failed", "bundleToMap ret null");
        } else {
            mapA.put("calling_package", str);
            RemoteSyncManager.b(mapA);
        }
    }

    public final HashMap<String, String> a(Bundle bundle) {
        SFLogN.c("HSProvider", "HSProvider syncPull call");
        return RemoteSyncManager.syncPull(RemoteSyncManager.a(bundle));
    }

    @TargetApi(19)
    public final String a() {
        if (Build.VERSION.SDK_INT >= 19) {
            return getCallingPackage();
        }
        int callingPid = Binder.getCallingPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = this.f3958a.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            SFLogN.a("HSProvider", "getCallingPackageCompat return null", "processes is null");
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == callingPid) {
                return runningAppProcessInfo.pkgList[0];
            }
        }
        SFLogN.a("HSProvider", "getCallingPackageCompat return null", "target pid not found: " + callingPid);
        return null;
    }
}
