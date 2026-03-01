package io.dcloud.common.core.ui;

import android.content.Context;
import android.widget.FrameLayout;
import io.dcloud.common.DHInterface.IApp;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class c extends b {
    public TabBarWebview u;
    public b v;

    public c(Context context, l lVar, IApp iApp, a aVar, int i, JSONObject jSONObject) {
        super(context, lVar, iApp, aVar, i, null);
        TabBarWebview tabBarWebview = new TabBarWebview(context, iApp, lVar, this, jSONObject);
        this.u = tabBarWebview;
        this.l = tabBarWebview;
        tabBarWebview.setFrameId("tab");
    }

    public void c(b bVar) {
        if (this.u.isInsertLauch()) {
            this.u.append(bVar);
            d(bVar);
        }
    }

    public boolean checkPagePathIsTab(String str) {
        TabBarWebview tabBarWebview = this.u;
        if (tabBarWebview != null) {
            return tabBarWebview.checkPagePathIsTab(str);
        }
        return false;
    }

    public void d(b bVar) {
        this.v = bVar;
        TabBarWebview tabBarWebview = this.u;
        if (tabBarWebview != null) {
            tabBarWebview.tabItemActive(bVar);
        }
    }

    @Override // io.dcloud.common.core.ui.b, io.dcloud.common.adapter.ui.AdaFrameView, io.dcloud.common.adapter.ui.AdaContainerFrameItem, io.dcloud.common.adapter.ui.AdaFrameItem
    public void dispose() {
        super.dispose();
        this.v = null;
        this.u = null;
    }

    @Override // io.dcloud.common.core.ui.b, io.dcloud.common.adapter.ui.AdaFrameView
    public void initMainView(Context context, int i, Object obj) {
        setMainView(new FrameLayout(context));
    }

    public void removeFrameView(b bVar) {
        TabBarWebview tabBarWebview = this.u;
        if (tabBarWebview != null) {
            tabBarWebview.removeFrameView(bVar);
        }
    }

    public b u() {
        return this.v;
    }
}
