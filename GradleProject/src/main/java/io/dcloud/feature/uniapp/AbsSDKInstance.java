package io.dcloud.feature.uniapp;

import android.content.Context;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import com.taobao.weex.IWXActivityStateListener;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.adapter.IDrawableLoader;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.adapter.URIAdapter;
import com.taobao.weex.bridge.EventResult;
import com.taobao.weex.common.WXModule;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public interface AbsSDKInstance extends IWXActivityStateListener, View.OnLayoutChangeListener {
    void addEventListener(String str, String str2);

    void fireEvent(String str, String str2);

    void fireEvent(String str, String str2, Map<String, Object> map);

    void fireEvent(String str, String str2, Map<String, Object> map, Map<String, Object> map2);

    void fireEvent(String str, String str2, Map<String, Object> map, Map<String, Object> map2, List<Object> list);

    void fireEvent(String str, String str2, Map<String, Object> map, Map<String, Object> map2, List<Object> list, EventResult eventResult);

    void fireGlobalEventCallback(String str, Map<String, Object> map);

    void fireModuleEvent(String str, WXModule wXModule, Map<String, Object> map);

    String getBundleUrl();

    Context getContext();

    int getDefaultFontSize();

    IDrawableLoader getDrawableLoader();

    IWXImgLoaderAdapter getImgLoaderAdapter();

    String getInstanceId();

    int getInstanceViewPortWidth();

    float getInstanceViewPortWidthWithFloat();

    WXSDKInstance getParentInstance();

    String getRenderType();

    View getRootView();

    URIAdapter getURIAdapter();

    int getWeexHeight();

    int getWeexWidth();

    boolean isCompilerWithUniapp();

    boolean isDestroy();

    boolean isFrameViewShow();

    boolean isImmersive();

    boolean onBackPressed();

    void onCreateFinish();

    boolean onCreateOptionsMenu(Menu menu);

    void onShowAnimationEnd();

    boolean onSupportNavigateUp();

    void registerRenderListener(IWXRenderListener iWXRenderListener);

    void reloadImages();

    void reloadPage(boolean z);

    void removeEventListener(String str);

    void removeEventListener(String str, String str2);

    Uri rewriteUri(Uri uri, String str);

    void runOnUiThread(Runnable runnable);

    void setBundleUrl(String str);

    void setDefaultFontSize(int i);

    void setImmersive(boolean z);

    void setRenderType(String str);

    void setSize(int i, int i2);
}
