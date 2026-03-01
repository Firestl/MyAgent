package io.dcloud.feature.nativeObj;

import android.text.TextUtils;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.INativeBitmap;
import io.dcloud.common.DHInterface.IWaiter;
import io.dcloud.common.DHInterface.IWebview;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class FeatureImpl implements IFeature, IWaiter {
    public static HashMap<String, NativeBitmapMgr> mNativeBitMapMs = new HashMap<>();
    public String mCurrentAppId;
    public AbsMgr mFeatureMgr;

    public static void destroyNativeView(String str, NativeView nativeView) {
        if (mNativeBitMapMs.containsKey(str)) {
            mNativeBitMapMs.get(str).destroyNativeView(nativeView);
        }
    }

    public static INativeBitmap getNativeBitmap(String str, String str2) {
        if (mNativeBitMapMs.containsKey(str)) {
            return mNativeBitMapMs.get(str).getBitmapByUuid(str2);
        }
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (mNativeBitMapMs.containsKey(str)) {
                mNativeBitMapMs.get(str).destroy();
                mNativeBitMapMs.remove(str);
                return;
            }
            return;
        }
        if (str == null) {
            for (Map.Entry<String, NativeBitmapMgr> entry : mNativeBitMapMs.entrySet()) {
                entry.getValue().destroy();
                mNativeBitMapMs.remove(entry.getValue());
            }
            mNativeBitMapMs.clear();
        }
    }

    @Override // io.dcloud.common.DHInterface.IWaiter
    public Object doForFeature(String str, Object obj) {
        if (str.equals("getNativeBitmap")) {
            String[] strArr = (String[]) obj;
            return getNativeBitmap(strArr[0], strArr[1]);
        }
        String strObtainAppId = ((IFrameView) ((Object[]) obj)[0]).obtainApp().obtainAppId();
        if (!mNativeBitMapMs.containsKey(strObtainAppId)) {
            mNativeBitMapMs.put(strObtainAppId, new NativeBitmapMgr());
        }
        return mNativeBitMapMs.containsKey(strObtainAppId) ? mNativeBitMapMs.get(strObtainAppId).doForFeature(str, obj) : mNativeBitMapMs.get(this.mCurrentAppId).doForFeature(str, obj);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        String strObtainAppId = iWebview.obtainApp().obtainAppId();
        this.mCurrentAppId = strObtainAppId;
        if (!mNativeBitMapMs.containsKey(strObtainAppId)) {
            mNativeBitMapMs.put(this.mCurrentAppId, new NativeBitmapMgr());
        }
        return mNativeBitMapMs.get(this.mCurrentAppId).execute(iWebview, str, strArr);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.mFeatureMgr = absMgr;
        if (mNativeBitMapMs == null) {
            mNativeBitMapMs = new HashMap<>();
        }
    }
}
