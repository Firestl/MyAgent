package io.dcloud.common.core.ui;

/* JADX INFO: loaded from: classes2.dex */
public class TabBarWebviewMgr {
    public static TabBarWebviewMgr mInstance;
    public TabBarWebview mLaunchTabBar;

    public static TabBarWebviewMgr getInstance() {
        if (mInstance == null) {
            mInstance = new TabBarWebviewMgr();
        }
        return mInstance;
    }

    public TabBarWebview getLaunchTabBar() {
        return this.mLaunchTabBar;
    }

    public void setLancheTabBar(TabBarWebview tabBarWebview) {
        this.mLaunchTabBar = tabBarWebview;
    }
}
