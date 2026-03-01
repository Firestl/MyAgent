package io.dcloud.feature.barcode2;

import android.text.TextUtils;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.util.JSUtil;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class BarcodeProxyMgr {
    public static BarcodeProxyMgr mInstance;
    public LinkedHashMap<String, BarcodeProxy> mBProxyCaches = new LinkedHashMap<>();
    public AbsMgr mFeatureMgr;

    public static BarcodeProxyMgr getBarcodeProxyMgr() {
        if (mInstance == null) {
            mInstance = new BarcodeProxyMgr();
        }
        return mInstance;
    }

    public Object doForFeature(String str, Object obj) {
        if (!str.equals("appendToFrameView")) {
            return null;
        }
        Object[] objArr = (Object[]) obj;
        AdaFrameView adaFrameView = (AdaFrameView) objArr[0];
        String str2 = (String) objArr[1];
        adaFrameView.obtainApp().obtainAppId();
        BarcodeProxy barcodeProxyByUuid = getBarcodeProxyByUuid(str2);
        if (barcodeProxyByUuid == null) {
            return null;
        }
        barcodeProxyByUuid.appendToFrameView(adaFrameView);
        return null;
    }

    public String execute(IWebview iWebview, String str, String[] strArr) {
        BarcodeProxy barcodeProxy;
        if (str.equals("getBarcodeById")) {
            BarcodeProxy barcodeProxyById = getBarcodeProxyById(strArr[0]);
            if (barcodeProxyById != null) {
                return JSUtil.wrapJsVar(barcodeProxyById.getJsBarcode());
            }
        } else {
            String str2 = strArr[0];
            if (this.mBProxyCaches.containsKey(str2)) {
                barcodeProxy = this.mBProxyCaches.get(str2);
            } else {
                barcodeProxy = new BarcodeProxy();
                this.mBProxyCaches.put(str2, barcodeProxy);
            }
            barcodeProxy.execute(iWebview, str, strArr);
        }
        return null;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public IWebview findWebviewByUuid(IWebview iWebview, String str) {
        Object objProcessEvent;
        AbsMgr absMgr = this.mFeatureMgr;
        if (absMgr == null || (objProcessEvent = absMgr.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "ui", "findWebview", new String[]{iWebview.obtainApp().obtainAppId(), str}})) == null) {
            return null;
        }
        return (IWebview) objProcessEvent;
    }

    public BarcodeProxy getBarcodeProxyById(String str) {
        Iterator<String> it = this.mBProxyCaches.keySet().iterator();
        while (it.hasNext()) {
            BarcodeProxy barcodeProxy = this.mBProxyCaches.get(it.next());
            if (!TextUtils.isEmpty(barcodeProxy.mId) && barcodeProxy.mId.equals(str)) {
                return barcodeProxy;
            }
        }
        return null;
    }

    public BarcodeProxy getBarcodeProxyByUuid(String str) {
        if (this.mBProxyCaches.containsKey(str)) {
            return this.mBProxyCaches.get(str);
        }
        return null;
    }

    public void onDestroy() {
        Iterator<String> it = this.mBProxyCaches.keySet().iterator();
        while (it.hasNext()) {
            BarcodeProxy barcodeProxyRemove = this.mBProxyCaches.remove(it.next());
            if (barcodeProxyRemove != null) {
                barcodeProxyRemove.onDestroy();
            }
        }
        this.mBProxyCaches.clear();
    }

    public void removeBarcodeProxy(String str) {
        BarcodeProxy barcodeProxyRemove = this.mBProxyCaches.remove(str);
        if (barcodeProxyRemove != null) {
            barcodeProxyRemove.onDestroy();
        }
    }

    public void setFeatureMgr(AbsMgr absMgr) {
        this.mFeatureMgr = absMgr;
    }
}
