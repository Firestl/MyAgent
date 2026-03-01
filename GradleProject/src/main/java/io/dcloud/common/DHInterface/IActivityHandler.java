package io.dcloud.common.DHInterface;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import io.dcloud.feature.internal.reflect.BroadcastReceiver;

/* JADX INFO: loaded from: classes2.dex */
public interface IActivityHandler extends IActivityDelegate {
    void addClickStatusbarCallBack(ICallBack iCallBack);

    void callBack(String str, Bundle bundle);

    void closeAppStreamSplash(String str);

    void closeSideBar();

    int getActivityState();

    Context getContext();

    String getUrlByFilePath(String str, String str2);

    boolean hasAdService();

    boolean isMultiProcessMode();

    void onAsyncStartAppEnd(String str, Object obj);

    Object onAsyncStartAppStart(String str);

    void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter);

    void removeClickStatusbarCallBack(ICallBack iCallBack);

    void sendLocalBroadcast(Intent intent);

    void setSideBarVisibility(int i);

    void setSplashCloseListener(String str, ICallBack iCallBack);

    void setViewAsContentView(View view, FrameLayout.LayoutParams layoutParams);

    void setWebViewIntoPreloadView(View view);

    void showSplashWaiting();

    void sideBarHideMenu();

    void sideBarShowMenu(String str, String str2, IWebview iWebview, String str3);

    void unregisterReceiver(BroadcastReceiver broadcastReceiver);

    void updateParam(String str, Object obj);

    void updateSplash(String str);
}
