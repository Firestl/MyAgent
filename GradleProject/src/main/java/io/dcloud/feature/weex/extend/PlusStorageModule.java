package io.dcloud.feature.weex.extend;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.appfram.storage.StorageResultHandler;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.feature.weex.WeexInstanceMgr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class PlusStorageModule extends WXModule {
    public HashMap<String, SharedPreferences> mAllAppNStorages = new HashMap<>(1);

    private SharedPreferences getAppNStorage() {
        IWebview iWebviewFindWebview = WeexInstanceMgr.self().findWebview(this.mWXSDKInstance);
        if (iWebviewFindWebview == null) {
            return null;
        }
        String strObtainAppId = iWebviewFindWebview.obtainFrameView().obtainApp().obtainAppId();
        SharedPreferences sharedPreferences = this.mAllAppNStorages.get(strObtainAppId);
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        SharedPreferences sharedPreferencesInitAppNStorages = initAppNStorages(strObtainAppId);
        this.mAllAppNStorages.put(strObtainAppId, sharedPreferencesInitAppNStorages);
        return sharedPreferencesInitAppNStorages;
    }

    private int getBundleDataSize(SharedPreferences sharedPreferences) {
        try {
            return sharedPreferences.getAll().size();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private List<String> getBundleKeys(SharedPreferences sharedPreferences) {
        Map<String, ?> all = sharedPreferences.getAll();
        if (all.size() > 0) {
            return new ArrayList(all.keySet());
        }
        return null;
    }

    private SharedPreferences initAppNStorages(String str) {
        return SP.getOrCreateBundle(str + "_storages");
    }

    @JSMethod(uiThread = false)
    public void getAllKeys(JSCallback jSCallback) {
        SharedPreferences appNStorage = getAppNStorage();
        if (appNStorage == null) {
            StorageResultHandler.handleNoHandlerError(jSCallback);
            return;
        }
        Map<String, Object> allkeysResult = StorageResultHandler.getAllkeysResult(getBundleKeys(appNStorage));
        if (jSCallback != null) {
            jSCallback.invoke(allkeysResult);
        }
    }

    @JSMethod(uiThread = false)
    public void getItem(String str, JSCallback jSCallback) {
        if (TextUtils.isEmpty(str)) {
            StorageResultHandler.handleInvalidParam(jSCallback);
            return;
        }
        SharedPreferences appNStorage = getAppNStorage();
        Map<String, Object> itemResult = appNStorage != null ? StorageResultHandler.getItemResult(appNStorage.getString(str, null)) : StorageResultHandler.getItemResult(null);
        if (jSCallback != null) {
            jSCallback.invoke(itemResult);
        }
    }

    @JSMethod(uiThread = false)
    public void length(JSCallback jSCallback) {
        if (getAppNStorage() == null) {
            StorageResultHandler.handleNoHandlerError(jSCallback);
            return;
        }
        Map<String, Object> lengthResult = StorageResultHandler.getLengthResult(getBundleDataSize(r0));
        if (jSCallback != null) {
            jSCallback.invoke(lengthResult);
        }
    }

    @JSMethod(uiThread = false)
    public void removeItem(String str, JSCallback jSCallback) {
        Map<String, Object> mapRemoveItemResult;
        if (TextUtils.isEmpty(str)) {
            StorageResultHandler.handleInvalidParam(jSCallback);
            return;
        }
        SharedPreferences appNStorage = getAppNStorage();
        if (appNStorage != null) {
            SP.removeBundleData(appNStorage, str);
            mapRemoveItemResult = StorageResultHandler.removeItemResult(true);
        } else {
            mapRemoveItemResult = StorageResultHandler.removeItemResult(false);
        }
        if (jSCallback != null) {
            jSCallback.invoke(mapRemoveItemResult);
        }
    }

    @JSMethod(uiThread = false)
    public void setItem(String str, String str2, JSCallback jSCallback) {
        Map<String, Object> itemResult;
        if (TextUtils.isEmpty(str) || str2 == null) {
            StorageResultHandler.handleInvalidParam(jSCallback);
            return;
        }
        SharedPreferences appNStorage = getAppNStorage();
        if (appNStorage != null) {
            SP.setBundleData(appNStorage, str, str2);
            itemResult = StorageResultHandler.setItemResult(true);
        } else {
            itemResult = StorageResultHandler.setItemResult(false);
        }
        if (jSCallback != null) {
            jSCallback.invoke(itemResult);
        }
    }
}
