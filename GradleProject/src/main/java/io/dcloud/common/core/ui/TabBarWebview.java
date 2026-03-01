package io.dcloud.common.core.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaWebview;
import io.dcloud.common.adapter.ui.ReceiveJSValue;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import supwisdom.ev;

/* JADX INFO: loaded from: classes2.dex */
public class TabBarWebview extends AdaWebview {
    public boolean isVisible;
    public IApp mApp;
    public JSONArray mChildJson;
    public ArrayList<String> mPagePaths;
    public ViewGroup mRoot;
    public float mScale;
    public int mSelectIndex;
    public ev mTabBar;
    public JSONObject mTabBarJson;
    public ArrayList<io.dcloud.common.core.ui.b> mTabItems;
    public FrameLayout mTabLayout;
    public l mWindowMgr;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.b f6367a;

        public a(TabBarWebview tabBarWebview, io.dcloud.common.core.ui.b bVar) {
            this.f6367a = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6367a.resize();
        }
    }

    public class b implements Animation.AnimationListener {
        public b() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            TabBarWebview.this.mTabBar.setVisibility(8);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TabBarWebview.this.mTabBar.setVisibility(8);
        }
    }

    public class d implements Animation.AnimationListener {
        public d() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            TabBarWebview tabBarWebview = TabBarWebview.this;
            tabBarWebview.setTabItemsBottomMargin(tabBarWebview.mTabBar.getTabHeight());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.b f6371a;

        public e(TabBarWebview tabBarWebview, io.dcloud.common.core.ui.b bVar) {
            this.f6371a = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewGroup viewGroup = (ViewGroup) this.f6371a.obtainWebviewParent().obtainMainView();
            if (viewGroup.getHeight() != this.f6371a.obtainMainView().getHeight()) {
                AdaFrameItem.LayoutParamsUtil.setViewLayoutParams(viewGroup, 0, 0, -1, -1);
            }
            this.f6371a.resize();
        }
    }

    public TabBarWebview(Context context, IApp iApp, l lVar, io.dcloud.common.core.ui.c cVar, org.json.JSONObject jSONObject) {
        super(context);
        this.mScale = 3.0f;
        this.mSelectIndex = 0;
        this.mFrameView = cVar;
        this.mWindowMgr = lVar;
        this.mTabItems = new ArrayList<>();
        this.mPagePaths = new ArrayList<>();
        initWebviewUUID("TabBar");
        initPagePaths(JSON.parseObject(jSONObject.toString()));
        this.mScale = context.getResources().getDisplayMetrics().density;
        this.mApp = iApp;
        JSONObject object = JSON.parseObject(jSONObject.toString());
        this.mTabBarJson = object;
        this.mChildJson = object.getJSONArray("child");
        this.mTabLayout = new FrameLayout(context);
        if (this.mTabBarJson.containsKey("selected")) {
            String string = this.mTabBarJson.getString("selected");
            if (!TextUtils.isEmpty(string)) {
                this.mSelectIndex = Integer.valueOf(string).intValue();
            }
        }
        this.mRoot = (FrameLayout) cVar.obtainMainView();
        ev evVar = new ev(context, this.mTabBarJson, this.mScale, iApp);
        this.mTabBar = evVar;
        setMainView(evVar);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.bottomMargin = this.mTabBar.getTabHeight();
        this.mRoot.addView(this.mTabLayout, layoutParams);
        TabBarWebviewMgr.getInstance().setLancheTabBar(this);
        this.isVisible = true;
        cVar.addFrameItem(this, new ViewGroup.LayoutParams(-1, -1));
    }

    private void initPagePaths(JSONObject jSONObject) {
        if (jSONObject.containsKey("list")) {
            JSONArray jSONArray = jSONObject.getJSONArray("list");
            for (int i = 0; i < jSONArray.size(); i++) {
                String string = jSONArray.getJSONObject(i).getString("pagePath");
                if (!TextUtils.isEmpty(string)) {
                    if (string.startsWith("/")) {
                        string = string.substring(1);
                    }
                    this.mPagePaths.add(string);
                }
            }
        }
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
    public void append(String str, ICallBack iCallBack) {
        l lVar;
        if (TextUtils.isEmpty(str) || (lVar = this.mWindowMgr) == null) {
            iCallBack.onCallBack(-1, null);
            return;
        }
        IMgr.MgrType mgrType = IMgr.MgrType.FeatureMgr;
        IApp iApp = this.mApp;
        Object objProcessEvent = lVar.processEvent(mgrType, 10, new Object[]{iApp, "ui", "findWebview", new String[]{iApp.obtainAppId(), str}});
        if (objProcessEvent == null || !(objProcessEvent instanceof IWebview)) {
            iCallBack.onCallBack(-1, null);
            return;
        }
        IFrameView iFrameViewObtainFrameView = ((IWebview) objProcessEvent).obtainFrameView();
        if (iFrameViewObtainFrameView instanceof io.dcloud.common.core.ui.b) {
            append((io.dcloud.common.core.ui.b) iFrameViewObtainFrameView);
            iCallBack.onCallBack(0, null);
        }
    }

    public boolean checkPagePathIsTab(String str) {
        Iterator<String> it = this.mPagePaths.iterator();
        while (it.hasNext()) {
            if (it.next().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public boolean checkUrlToReload(String str) {
        try {
            for (io.dcloud.common.core.ui.b bVar : this.mTabItems) {
                if (str.endsWith(".js")) {
                    bVar.mWindowMgr.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{bVar.obtainApp(), "weex,io.dcloud.feature.weex.WeexFeature", "updateReload", new Object[]{str}});
                    return true;
                }
                if (bVar.obtainWebView().obtainUrl().startsWith(str)) {
                    bVar.obtainWebView().reload();
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.adapter.ui.AdaContainerFrameItem, io.dcloud.common.adapter.ui.AdaFrameItem
    public void dispose() {
        super.dispose();
        ev evVar = this.mTabBar;
        if (evVar != null) {
            evVar.c();
        }
        this.mTabItems.clear();
        ArrayList<String> arrayList = this.mPagePaths;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void evalJS(String str) {
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void evalJS(String str, ReceiveJSValue.ReceiveJSValueCallback receiveJSValueCallback) {
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public void executeScript(String str) {
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public float getScale() {
        return this.mScale;
    }

    public int getSelectIndex() {
        return this.mSelectIndex;
    }

    public String getTabBarHeight() {
        return this.mTabBar.getTabHeightStr().substring(0, this.mTabBar.getTabHeightStr().length() - 2);
    }

    public void hideTabBar(JSONObject jSONObject) {
        if (this.isVisible) {
            boolean zBooleanValue = jSONObject.containsKey(GlideExecutor.ANIMATION_EXECUTOR_NAME) ? jSONObject.getBoolean(GlideExecutor.ANIMATION_EXECUTOR_NAME).booleanValue() : false;
            setTabItemsBottomMargin(0);
            this.mTabBar.bringToFront();
            this.mTabBar.a();
            if (zBooleanValue) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, this.mTabBar.getTabHeight() + this.mTabBar.getMidHeight());
                translateAnimation.setDuration(100L);
                translateAnimation.setAnimationListener(new b());
                this.mTabBar.startAnimation(translateAnimation);
            } else {
                this.mTabBar.postDelayed(new c(), 150L);
            }
            this.isVisible = false;
        }
    }

    public void hideTabBarRedDot(JSONObject jSONObject) {
        this.mTabBar.a(jSONObject);
    }

    public boolean isInsertLauch() {
        if (this.mChildJson != null) {
            for (int i = 0; i < this.mChildJson.size(); i++) {
                if (this.mChildJson.getString(i).equals("lauchwebview")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public IApp obtainApp() {
        return this.mApp;
    }

    @Override // io.dcloud.common.adapter.ui.AdaWebview, io.dcloud.common.DHInterface.IWebview
    public ViewGroup obtainWindowView() {
        return this.mRoot;
    }

    @Override // io.dcloud.common.adapter.ui.AdaContainerFrameItem, io.dcloud.common.adapter.ui.AdaFrameItem
    public void onResize() {
        super.onResize();
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
    public void popFrame(String str) {
        if (this.mTabLayout != null) {
            l lVar = this.mWindowMgr;
            IMgr.MgrType mgrType = IMgr.MgrType.FeatureMgr;
            IApp iApp = this.mApp;
            Object objProcessEvent = lVar.processEvent(mgrType, 10, new Object[]{iApp, "ui", "findWebview", new String[]{iApp.obtainAppId(), str}});
            if (objProcessEvent == null || !(objProcessEvent instanceof IWebview)) {
                return;
            }
            ((IWebview) objProcessEvent).obtainFrameView().obtainMainView().setVisibility(8);
        }
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
    public void pushFrame(String str) {
        if (this.mTabLayout != null) {
            l lVar = this.mWindowMgr;
            IMgr.MgrType mgrType = IMgr.MgrType.FeatureMgr;
            IApp iApp = this.mApp;
            Object objProcessEvent = lVar.processEvent(mgrType, 10, new Object[]{iApp, "ui", "findWebview", new String[]{iApp.obtainAppId(), str}});
            if (objProcessEvent == null || !(objProcessEvent instanceof IWebview)) {
                return;
            }
            ((IWebview) objProcessEvent).obtainFrameView().obtainMainView().setVisibility(0);
        }
    }

    public void removeFrameView(io.dcloud.common.core.ui.b bVar) {
        FrameLayout frameLayout;
        if (bVar == null || (frameLayout = this.mTabLayout) == null) {
            return;
        }
        frameLayout.removeView(bVar.obtainMainView());
        if (this.mTabItems.contains(bVar)) {
            this.mTabItems.remove(bVar);
        }
    }

    public void removeTabBarBadge(JSONObject jSONObject) {
        this.mTabBar.b(jSONObject);
    }

    public void setClickCallBack(ICallBack iCallBack) {
        this.mTabBar.setSingleCallbackListener(iCallBack);
    }

    public void setDoubleClickCallBack(ICallBack iCallBack) {
        this.mTabBar.setDoubleCallbackListener(iCallBack);
    }

    public void setItem(JSONObject jSONObject) {
        this.mTabBar.setTabBarItem(jSONObject);
    }

    public void setMask(JSONObject jSONObject) {
        this.mTabBar.setMask(jSONObject);
    }

    public void setMaskButtonClickCallBack(ICallBack iCallBack) {
        this.mTabBar.setMaskCallbackListener(iCallBack);
    }

    public void setMidButtonClickCallBack(ICallBack iCallBack) {
        this.mTabBar.setMidCallbackListener(iCallBack);
    }

    public void setStyle(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.mTabBar.setTabBarStyle(jSONObject);
            if (jSONObject.containsKey("height")) {
                setTabItemsBottomMargin(this.isVisible ? this.mTabBar.getTabHeight() : 0);
            }
        }
    }

    public void setTabBarBadge(JSONObject jSONObject) {
        this.mTabBar.setTabBarBadge(jSONObject);
    }

    public void setTabItemsBottomMargin(int i) {
        if (this.mTabItems != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mTabLayout.getLayoutParams();
            layoutParams.bottomMargin = i;
            this.mTabLayout.setLayoutParams(layoutParams);
            for (io.dcloud.common.core.ui.b bVar : this.mTabItems) {
                if (bVar != null && bVar.obtainMainView() != null) {
                    org.json.JSONObject jSONObject = new org.json.JSONObject();
                    try {
                        jSONObject.put("top", "0px");
                        jSONObject.put("bottom", i > 0 ? this.mTabBar.getTabHeightStr() : "0px");
                        jSONObject.put("isTab", true);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    bVar.obtainFrameOptions().updateViewData(jSONObject);
                    bVar.obtainMainView().post(new e(this, bVar));
                }
            }
            this.mFrameView.resize();
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebview
    public void show(Animation animation) {
    }

    public void showTabBar(JSONObject jSONObject) {
        if (this.isVisible) {
            return;
        }
        if (jSONObject.containsKey(GlideExecutor.ANIMATION_EXECUTOR_NAME) ? jSONObject.getBoolean(GlideExecutor.ANIMATION_EXECUTOR_NAME).booleanValue() : false) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, this.mTabBar.getTabHeight() + this.mTabBar.getMidHeight(), 0.0f);
            translateAnimation.setDuration(100L);
            translateAnimation.setAnimationListener(new d());
            this.mTabBar.startAnimation(translateAnimation);
        } else {
            setTabItemsBottomMargin(this.mTabBar.getTabHeight());
        }
        this.mTabBar.setVisibility(0);
        this.isVisible = true;
    }

    public void showTabBarRedDot(JSONObject jSONObject) {
        this.mTabBar.c(jSONObject);
    }

    public void switchSelect(int i) {
        this.mSelectIndex = i;
        this.mTabBar.b(i);
    }

    public void tabItemActive(io.dcloud.common.core.ui.b bVar) {
        for (int i = 0; i < this.mTabItems.size(); i++) {
            io.dcloud.common.core.ui.b bVar2 = this.mTabItems.get(i);
            if (bVar == null || bVar2 != bVar) {
                this.mTabItems.get(i).obtainMainView().setImportantForAccessibility(4);
                bVar2.obtainMainView().setImportantForAccessibility(4);
            } else {
                bVar2.obtainMainView().setImportantForAccessibility(0);
            }
        }
    }

    public void updateMidButton(JSONObject jSONObject) {
        this.mTabBar.d(jSONObject);
    }

    public void append(io.dcloud.common.core.ui.b bVar) {
        if (bVar != null) {
            View viewObtainMainView = bVar.obtainMainView();
            if (viewObtainMainView.getParent() != null) {
                bVar.p();
                bVar.getAnimOptions().mOption = (byte) 1;
                bVar.k.b(bVar);
                if (bVar.e()) {
                    this.mWindowMgr.processEvent(IMgr.MgrType.WindowMgr, 28, bVar.b);
                    bVar.b = null;
                }
                bVar.r();
                bVar.b(false);
                bVar.getAnimOptions().mOption = (byte) 0;
                ((ViewGroup) viewObtainMainView.getParent()).removeView(viewObtainMainView);
            }
            bVar.setTabItem(true);
            this.mWindowMgr.processEvent(IMgr.MgrType.WindowMgr, 22, bVar);
            org.json.JSONObject jSONObject = new org.json.JSONObject();
            try {
                jSONObject.put("top", "0px");
                jSONObject.put("bottom", this.isVisible ? this.mTabBar.getTabHeightStr() : "0px");
                jSONObject.put("isTab", true);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            bVar.obtainFrameOptions().setParentViewRect(this.mFrameView.obtainFrameOptions());
            bVar.obtainFrameOptions().updateViewData(jSONObject);
            bVar.setParentFrameItem(this.mFrameView);
            this.mFrameView.mChildArrayList.add(bVar);
            bVar.inStack = true;
            bVar.isChildOfFrameView = true;
            this.mTabLayout.addView(viewObtainMainView, new FrameLayout.LayoutParams(-1, -1));
            if (!this.mTabItems.contains(bVar)) {
                this.mTabItems.add(bVar);
            }
            if (viewObtainMainView.getImportantForAccessibility() == 4) {
                viewObtainMainView.setImportantForAccessibility(0);
            }
            bVar.obtainMainView().post(new a(this, bVar));
        }
    }
}
