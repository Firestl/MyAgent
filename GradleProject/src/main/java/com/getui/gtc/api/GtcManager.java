package com.getui.gtc.api;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.getui.gtc.api.GtcIdCallback;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.ProcessSwitchContract;
import com.getui.gtc.base.publish.Broker;
import com.getui.gtc.base.publish.Subscriber;
import com.getui.gtc.base.util.BundleCompat;
import com.getui.gtc.base.util.CommonUtil;
import com.getui.gtc.d.a;
import com.getui.gtc.dim.Caller;
import com.getui.gtc.dim.DimManager;
import com.getui.gtc.g.b;
import com.getui.gtc.i.c.a;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class GtcManager implements Subscriber {
    public static String methodName;

    public static class InstanceHolder {
        public static final GtcManager instance = new GtcManager();
    }

    public GtcManager() {
    }

    private void checkSdkInfo(SdkInfo sdkInfo) {
        if (TextUtils.isEmpty(sdkInfo.getModuleName())) {
            a.c("moduleName not set for sdkinfo");
            throw new RuntimeException("moduleName not set for sdkinfo");
        }
        if (TextUtils.isEmpty(sdkInfo.getAppid())) {
            a.c("appid not set for sdkinfo");
            throw new RuntimeException("appid not set for sdkinfo");
        }
        if (TextUtils.isEmpty(sdkInfo.getVersion())) {
            a.c("version not set for sdkinfo");
            throw new RuntimeException("version not set for sdkinfo");
        }
    }

    private Bundle createBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(ProcessSwitchContract.CLASS_NAME, GtcManager.class.getName());
        bundle.putString(ProcessSwitchContract.GET_INSTANCE, methodName);
        return bundle;
    }

    public static Caller getFromTrace() {
        String className;
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int i = -1;
            String name = GtcManager.class.getName();
            for (int i2 = 2; i2 < stackTrace.length; i2++) {
                if (!stackTrace[i2].getClassName().equals(name)) {
                    if (i > 0) {
                        break;
                    }
                } else {
                    i = i2;
                }
            }
            className = stackTrace[i + 1].getClassName();
        } catch (Throwable th) {
            a.b(th);
        }
        if (className.startsWith("com.igexin")) {
            return Caller.PUSH;
        }
        if (className.startsWith("com.g.gysdk")) {
            return Caller.GY;
        }
        if (className.startsWith("com.getui.gs")) {
            return Caller.IDO;
        }
        if (className.startsWith("com.sdk.plus")) {
            return Caller.WUS;
        }
        return Caller.UNKNOWN;
    }

    public static GtcManager getInstance() {
        methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return InstanceHolder.instance;
    }

    public ClassLoader getClassLoader(Bundle bundle) {
        return b.a(bundle);
    }

    @Deprecated
    public void init(Context context, GtcIdCallback.Stub stub) {
        initialize(context, stub);
    }

    @Deprecated
    public String initialize(Context context, GtcIdCallback.Stub stub) {
        return initialize(context, getFromTrace(), stub);
    }

    public String initialize(Context context, Caller caller, GtcIdCallback.Stub stub) {
        if (CommonUtil.isGtcProcess()) {
            DimManager.getInstance().set("dim-2-2-3-1", "dim-2-2-3-1", caller != null ? caller.name() : null);
            return a.C0035a.f2148a.a(stub);
        }
        GtcProvider.setContext(context);
        Bundle bundleCreateBundle = createBundle();
        bundleCreateBundle.putString(ProcessSwitchContract.METHOD_NAME, "gtc-1-1");
        bundleCreateBundle.putString("gtc-1-3", caller != null ? caller.name() : null);
        BundleCompat.putBinder(bundleCreateBundle, "gtc-1-2", stub);
        Bundle bundleSubscribe = Broker.getInstance().subscribe(bundleCreateBundle);
        Object obj = bundleSubscribe.get(ProcessSwitchContract.METHOD_EXCEPTION);
        if (obj instanceof Throwable) {
            com.getui.gtc.i.c.a.a("initialize", (Throwable) obj);
        }
        return bundleSubscribe.getString(ProcessSwitchContract.METHOD_RETURN);
    }

    public boolean loadBundle(Context context, Bundle bundle) {
        if (context != null) {
            GtcProvider.setContext(context.getApplicationContext());
        }
        return b.a(context, bundle);
    }

    public void loadSdk(SdkInfo sdkInfo) {
        checkSdkInfo(sdkInfo);
        if (CommonUtil.isGtcProcess()) {
            a.C0035a.f2148a.a(sdkInfo);
            return;
        }
        Bundle bundleCreateBundle = createBundle();
        bundleCreateBundle.putString(ProcessSwitchContract.METHOD_NAME, "gtc-2-1");
        bundleCreateBundle.putParcelable("gtc-2-2", sdkInfo);
        Bundle bundleSubscribe = Broker.getInstance().subscribe(bundleCreateBundle);
        if (bundleSubscribe.get(ProcessSwitchContract.METHOD_EXCEPTION) != null) {
            com.getui.gtc.i.c.a.b((Throwable) bundleSubscribe.get(ProcessSwitchContract.METHOD_EXCEPTION));
        }
    }

    @Override // com.getui.gtc.base.publish.Subscriber
    public void receive(Bundle bundle, Bundle bundle2) {
        ArrayList arrayList = new ArrayList();
        try {
            Throwable th = (Throwable) bundle2.getSerializable(ProcessSwitchContract.METHOD_EXCEPTION);
            if (th != null) {
                arrayList.add(th);
            }
            String string = bundle.getString(ProcessSwitchContract.METHOD_NAME);
            if (TextUtils.isEmpty(string)) {
                throw new RuntimeException("methodName missed");
            }
            byte b = -1;
            int iHashCode = string.hashCode();
            if (iHashCode != 337397854) {
                if (iHashCode != 337398815) {
                    if (iHashCode == 337399776 && string.equals("gtc-3-1")) {
                        b = 2;
                    }
                } else if (string.equals("gtc-2-1")) {
                    b = 1;
                }
            } else if (string.equals("gtc-1-1")) {
                b = 0;
            }
            if (b == 0) {
                DimManager.getInstance().set("dim-2-2-3-1", "dim-2-2-3-1", bundle.getString("gtc-1-3"));
                bundle2.putString(ProcessSwitchContract.METHOD_RETURN, a.C0035a.f2148a.a(GtcIdCallback.Stub.asInterface(BundleCompat.getBinder(bundle, "gtc-1-2"))));
            } else if (b == 1) {
                a.C0035a.f2148a.a((SdkInfo) bundle.getParcelable("gtc-2-2"));
            } else if (b == 2) {
                bundle.getString("gtc-3-2");
                a.C0035a.f2148a.a(bundle.getIntArray("gtc-3-3"));
            }
        } catch (Throwable th2) {
            try {
                arrayList.add(th2);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    com.getui.gtc.i.c.a.a((Throwable) it.next());
                }
            } finally {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    com.getui.gtc.i.c.a.a((Throwable) it2.next());
                }
            }
        }
    }

    public void removeExt(String str, int[] iArr) {
        if (CommonUtil.isGtcProcess()) {
            a.C0035a.f2148a.a(iArr);
            return;
        }
        Bundle bundleCreateBundle = createBundle();
        bundleCreateBundle.putString(ProcessSwitchContract.METHOD_NAME, "gtc-3-1");
        bundleCreateBundle.putString("gtc-3-2", str);
        bundleCreateBundle.putIntArray("gtc-3-3", iArr);
        Bundle bundleSubscribe = Broker.getInstance().subscribe(bundleCreateBundle);
        if (bundleSubscribe.get(ProcessSwitchContract.METHOD_EXCEPTION) != null) {
            com.getui.gtc.i.c.a.b((Throwable) bundleSubscribe.get(ProcessSwitchContract.METHOD_EXCEPTION));
        }
    }
}
