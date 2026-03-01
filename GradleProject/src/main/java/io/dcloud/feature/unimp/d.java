package io.dcloud.feature.unimp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.taobao.weex.bridge.WXBridgeManager;
import io.dcloud.EntryProxy;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.UniMPConfig;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.internal.sdk.SDK;
import io.dcloud.feature.sdk.IDCUniMPCallBack;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6696a = null;
    public RemoteCallbackList<IDCUniMPCallBack> b = new RemoteCallbackList<>();
    public ArrayList<io.dcloud.feature.sdk.a> c = new ArrayList<>();

    public class a implements ICallBack {
        public a(d dVar) {
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            return null;
        }
    }

    public RemoteCallbackList<IDCUniMPCallBack> a() {
        return this.b;
    }

    public String b() {
        return this.f6696a;
    }

    public void c(Bundle bundle) {
        UniMPConfig.isCapsuleMenuIntercept = bundle.getBoolean(AbsoluteConst.EVENTS_MENU, false);
        UniMPConfig.isCapsuleCloseIntercept = bundle.getBoolean("close", false);
    }

    public boolean d(Bundle bundle) {
        Activity entryActivity = EntryProxy.getInstnace().getEntryActivity();
        if (entryActivity != null) {
            PackageManager packageManager = entryActivity.getPackageManager();
            Intent intent = (Intent) bundle.getParcelable(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK);
            intent.removeFlags(268435456);
            int i = bundle.getInt("enterAnim");
            int i2 = bundle.getInt("exitAnim");
            if (packageManager.queryIntentActivities(intent, 0) != null) {
                entryActivity.startActivity(intent);
                if (i == -1 || i2 == -1) {
                    return true;
                }
                entryActivity.overridePendingTransition(i, i2);
                return true;
            }
        }
        return false;
    }

    public String e(Bundle bundle) {
        Object objInvokeMethod;
        if (!bundle.containsKey("instanceId") || (objInvokeMethod = PlatformUtil.invokeMethod("com.taobao.weex.bridge.WXBridgeManager", "getInstance")) == null) {
            return null;
        }
        PlatformUtil.invokeMethod(objInvokeMethod, WXBridgeManager.METHOD_CALLBACK, new Class[]{String.class, String.class, Object.class, Boolean.TYPE}, bundle.getString("instanceId"), bundle.getString(SDK.UNIMP_EVENT_CALLBACKID), bundle.getBoolean("isJson", false) ? JSON.parse(bundle.getString("data")) : bundle.getString("data"), Boolean.valueOf(bundle.getBoolean("isKeepAlive")));
        return null;
    }

    public boolean a(String str, int i) {
        if (EntryProxy.getInstnace() == null || EntryProxy.getInstnace().getCoreHandler() == null) {
            return false;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("appid", (Object) str);
        jSONObject.put("isStopApp", (Object) Boolean.TRUE);
        jSONObject.put("quitModel", (Object) Integer.valueOf(i));
        EntryProxy.getInstnace().getCoreHandler().dispatchEvent(IMgr.MgrType.WindowMgr, 20, jSONObject);
        this.f6696a = null;
        return true;
    }

    public void b(String str) {
        this.f6696a = str;
    }

    public String b(Bundle bundle) {
        if (EntryProxy.getInstnace() == null || EntryProxy.getInstnace().getCoreHandler() == null) {
            return null;
        }
        EntryProxy.getInstnace().getCoreHandler().dispatchEvent(IMgr.MgrType.WindowMgr, 79, bundle);
        return null;
    }

    public void b(IDCUniMPCallBack iDCUniMPCallBack) {
        a().unregister(iDCUniMPCallBack);
    }

    public String a(String str) {
        Object objDispatchEvent;
        if (EntryProxy.getInstnace() == null || EntryProxy.getInstnace().getCoreHandler() == null || TextUtils.isEmpty(str) || (objDispatchEvent = EntryProxy.getInstnace().getCoreHandler().dispatchEvent(IMgr.MgrType.WindowMgr, 78, str)) == null) {
            return null;
        }
        return objDispatchEvent.toString();
    }

    public void a(Application application, String str) {
        PlatformUtil.invokeMethod(PlatformUtil.invokeMethod("io.dcloud.feature.weex.WeexInstanceMgr", "self"), "preUniMP", new Class[]{Application.class, String.class, ICallBack.class}, application, str, new a(this));
    }

    public void a(Bundle bundle) {
        Activity entryActivity;
        if (EntryProxy.getInstnace() == null || EntryProxy.getInstnace().getCoreHandler() == null || (entryActivity = EntryProxy.getInstnace().getEntryActivity()) == null) {
            return;
        }
        entryActivity.moveTaskToBack(true);
        entryActivity.overridePendingTransition(R.anim.dcloud_unimp_def_motionless, R.anim.dcloud_unimp_close_exit);
    }

    public String a(Context context, String str) {
        org.json.JSONObject jSONObjectOptJSONObject;
        String str2 = context.getFilesDir().getAbsolutePath() + "/apps/" + str + "/" + BaseInfo.APP_WWW_FS_DIR + BaseInfo.sConfigXML;
        if (BaseInfo.SyncDebug) {
            str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + context.getPackageName() + "/apps/" + str + "/" + BaseInfo.APP_WWW_FS_DIR + BaseInfo.sConfigXML;
        }
        org.json.JSONObject configData = PdrUtil.getConfigData(context.getApplicationContext(), str, str2, false);
        if (configData == null || !configData.has("version") || (jSONObjectOptJSONObject = configData.optJSONObject("version")) == null) {
            return null;
        }
        return jSONObjectOptJSONObject.toString();
    }

    public void a(String str, Bundle bundle) {
        if ("uni_oncloseapp".equals(str)) {
            this.f6696a = null;
        }
        try {
            if (this.b.getRegisteredCallbackCount() == 0) {
                this.c.add(new io.dcloud.feature.sdk.a(str, bundle));
                return;
            }
            int iBeginBroadcast = this.b.beginBroadcast();
            for (int i = 0; i < iBeginBroadcast; i++) {
                try {
                    ((IDCUniMPCallBack) this.b.getBroadcastItem(i)).callBack(str, bundle);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
            this.b.finishBroadcast();
        } catch (IllegalStateException unused) {
        }
    }

    public void a(IDCUniMPCallBack iDCUniMPCallBack) {
        a().register(iDCUniMPCallBack);
        if (this.c.size() > 0) {
            for (io.dcloud.feature.sdk.a aVar : this.c) {
                a(aVar.f6599a, aVar.b);
            }
            this.c.clear();
        }
    }
}
