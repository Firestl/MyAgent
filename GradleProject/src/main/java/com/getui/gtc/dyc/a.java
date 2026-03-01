package com.getui.gtc.dyc;

import android.os.Bundle;
import android.os.RemoteException;
import com.getui.gtc.base.ProcessSwitchContract;
import com.getui.gtc.base.publish.Broker;
import com.getui.gtc.base.publish.Subscriber;
import com.getui.gtc.base.util.BundleCompat;
import com.getui.gtc.base.util.CommonUtil;
import com.getui.gtc.dyc.Callback;
import com.getui.gtc.dyc.b.b;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class a implements Subscriber {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f2189a;

    /* JADX INFO: renamed from: com.getui.gtc.dyc.a$a, reason: collision with other inner class name */
    public static class C0041a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static a f2192a = new a();
    }

    public a() {
    }

    public static a a() {
        f2189a = Thread.currentThread().getStackTrace()[2].getMethodName();
        return C0041a.f2192a;
    }

    private Bundle d() {
        Bundle bundle = new Bundle();
        bundle.putString(ProcessSwitchContract.CLASS_NAME, a.class.getName());
        bundle.putString(ProcessSwitchContract.GET_INSTANCE, f2189a);
        return bundle;
    }

    public Map<String, String> a(final b bVar) {
        if (CommonUtil.isGtcProcess()) {
            return f.a().a(bVar);
        }
        Bundle bundleD = d();
        bundleD.putString(ProcessSwitchContract.METHOD_NAME, "dyc-1-1");
        bundleD.putParcelable("dyc-1-2", bVar);
        if (bVar.i() != null) {
            BundleCompat.putBinder(bundleD, "dyc-1-3", new Callback.a() { // from class: com.getui.gtc.dyc.a.2
                @Override // com.getui.gtc.dyc.Callback
                public void a(Map map, Map map2) throws RemoteException {
                    bVar.i().a(map, map2);
                }

                @Override // com.getui.gtc.dyc.Callback
                public void b(String str) throws RemoteException {
                    bVar.i().b(str);
                }
            });
        }
        return (Map) Broker.getInstance().subscribe(bundleD).get(ProcessSwitchContract.METHOD_RETURN);
    }

    public Map<String, String> a(String str) {
        if (CommonUtil.isGtcProcess()) {
            return f.a().a(str);
        }
        Bundle bundleD = d();
        bundleD.putString(ProcessSwitchContract.METHOD_NAME, "dyc-2-1");
        bundleD.putString("dyc-2-2", str);
        return (Map) Broker.getInstance().subscribe(bundleD).get(ProcessSwitchContract.METHOD_RETURN);
    }

    public void a(String str, Map<String, String> map) {
        if (CommonUtil.isGtcProcess()) {
            f.a().a(str, map);
            return;
        }
        Bundle bundleD = d();
        bundleD.putString(ProcessSwitchContract.METHOD_NAME, "dyc-4-1");
        bundleD.putString("dyc-4-2", str);
        bundleD.putSerializable("dyc-4-3", (HashMap) map);
        Broker.getInstance().subscribe(bundleD);
    }

    public Map<String, Map<String, String>> c() {
        if (CommonUtil.isGtcProcess()) {
            return f.a().c();
        }
        Bundle bundleD = d();
        bundleD.putString(ProcessSwitchContract.METHOD_NAME, "dyc-3-1");
        return (Map) Broker.getInstance().subscribe(bundleD).get(ProcessSwitchContract.METHOD_RETURN);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b2 A[DONT_GENERATE, LOOP:0: B:39:0x00ac->B:41:0x00b2, LOOP_END] */
    @Override // com.getui.gtc.base.publish.Subscriber
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void receive(android.os.Bundle r8, android.os.Bundle r9) {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dyc.a.receive(android.os.Bundle, android.os.Bundle):void");
    }
}
