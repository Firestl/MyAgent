package io.dcloud.feature.uniapp.ui.component;

import android.content.Intent;
import android.util.Pair;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.Scrollable;
import com.taobao.weex.ui.component.WXBaseScroller;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXImage;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.WXImageView;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.utils.WXUtils;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import java.util.ArrayList;
import supwisdom.lb;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbsVContainer<T extends ViewGroup> extends WXComponent<T> {
    public ArrayList<WXComponent> mChildren;

    @Deprecated
    public AbsVContainer(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, z, basicComponentData);
    }

    private void doViewTreeRecycleImageView(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof WXImageView) {
                if (z) {
                    ((WXImageView) childAt).autoReleaseImage();
                } else {
                    ((WXImageView) childAt).autoRecoverImage();
                }
            } else if (childAt instanceof ViewGroup) {
                doViewTreeRecycleImageView((ViewGroup) childAt, z);
            }
        }
    }

    public void addChild(WXComponent wXComponent) {
        addChild(wXComponent, -1);
    }

    public void addSubView(View view, int i) {
        if (view == null || getRealView() == null) {
            return;
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        if (i >= getRealView().getChildCount()) {
            i = -1;
        }
        if (i == -1) {
            getRealView().addView(view);
        } else {
            getRealView().addView(view, i);
        }
        WXSDKInstance wXComponent = getInstance();
        if (wXComponent != null) {
            wXComponent.getApmForInstance().hasAddView = true;
        }
    }

    public void appendTreeCreateFinish() {
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void applyLayoutAndEvent(AbsBasicComponent absBasicComponent) {
        if (isLazy()) {
            return;
        }
        if (absBasicComponent == null) {
            absBasicComponent = this;
        }
        super.applyLayoutAndEvent(absBasicComponent);
        int iChildCount = childCount();
        for (int i = 0; i < iChildCount; i++) {
            getChild(i).applyLayoutAndEvent(((WXVContainer) absBasicComponent).getChild(i));
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void bindComponentData(AbsBasicComponent absBasicComponent) {
        if (isLazy()) {
            return;
        }
        if (absBasicComponent == null) {
            absBasicComponent = this;
        }
        super.bindComponentData(absBasicComponent);
        int iChildCount = childCount();
        for (int i = 0; i < iChildCount; i++) {
            getChild(i).bindData(((WXVContainer) absBasicComponent).getChild(i));
        }
    }

    public int childCount() {
        ArrayList<WXComponent> arrayList = this.mChildren;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public void createChildViewAt(int i) {
        Pair<WXComponent, Integer> pairRearrangeIndexAndGetChild = rearrangeIndexAndGetChild(i);
        Object obj = pairRearrangeIndexAndGetChild.first;
        if (obj != null) {
            WXComponent wXComponent = (WXComponent) obj;
            wXComponent.createView();
            if (wXComponent.isVirtualComponent()) {
                return;
            }
            addSubView(wXComponent.getHostView(), ((Integer) pairRearrangeIndexAndGetChild.second).intValue());
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void createViewImpl() {
        super.createViewImpl();
        int iChildCount = childCount();
        for (int i = 0; i < iChildCount; i++) {
            createChildViewAt(i);
        }
        if (getHostView() != null) {
            getHostView().setClipToPadding(false);
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void destroy() {
        ArrayList<WXComponent> arrayList = this.mChildren;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.mChildren.get(i).destroy();
            }
            this.mChildren.clear();
        }
        super.destroy();
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public View detachViewAndClearPreInfo() {
        View viewDetachViewAndClearPreInfo = super.detachViewAndClearPreInfo();
        if (this.mChildren != null) {
            int iChildCount = childCount();
            for (int i = 0; i < iChildCount; i++) {
                this.mChildren.get(i).detachViewAndClearPreInfo();
            }
        }
        return viewDetachViewAndClearPreInfo;
    }

    public abstract View getBoxShadowHost(boolean z);

    public WXComponent getChild(int i) {
        ArrayList<WXComponent> arrayList = this.mChildren;
        if (arrayList == null || i < 0 || i >= arrayList.size()) {
            return null;
        }
        return this.mChildren.get(i);
    }

    public int getChildCount() {
        return childCount();
    }

    public ViewGroup.LayoutParams getChildLayoutParams(WXComponent wXComponent, View view, int i, int i2, int i3, int i4, int i5, int i6) {
        ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
        if (layoutParams == null) {
            return new ViewGroup.LayoutParams(i, i2);
        }
        layoutParams.width = i;
        layoutParams.height = i2;
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return layoutParams;
        }
        setMarginsSupportRTL((ViewGroup.MarginLayoutParams) layoutParams, i3, i5, i4, i6);
        return layoutParams;
    }

    public int getChildrenLayoutTopOffset() {
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.taobao.weex.ui.component.WXComponent
    public Scrollable getFirstScroller() {
        if (this instanceof Scrollable) {
            return (Scrollable) this;
        }
        for (int i = 0; i < getChildCount(); i++) {
            Scrollable firstScroller = getChild(i).getFirstScroller();
            if (firstScroller != null) {
                return firstScroller;
            }
        }
        return null;
    }

    public void ignoreFocus() {
        T hostView = getHostView();
        if (hostView != null) {
            hostView.setFocusable(false);
            hostView.setFocusableInTouchMode(false);
            hostView.clearFocus();
        }
    }

    public final int indexOf(WXComponent wXComponent) {
        return this.mChildren.indexOf(wXComponent);
    }

    public void interceptFocus() {
        T hostView = getHostView();
        if (hostView != null) {
            hostView.setFocusable(true);
            hostView.setFocusableInTouchMode(true);
            hostView.setDescendantFocusability(131072);
            hostView.requestFocus();
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void notifyAppearStateChange(String str, String str2) {
        ArrayList<WXComponent> arrayList;
        super.notifyAppearStateChange(str, str2);
        if (getHostView() == null || (arrayList = this.mChildren) == null) {
            return;
        }
        for (WXComponent wXComponent : arrayList) {
            if (wXComponent.getHostView() != null && wXComponent.getHostView().getVisibility() != 0) {
                str = Constants.Event.DISAPPEAR;
            }
            wXComponent.notifyAppearStateChange(str, str2);
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public boolean onActivityBack() {
        super.onActivityBack();
        int iChildCount = childCount();
        for (int i = 0; i < iChildCount; i++) {
            getChild(i).onActivityBack();
        }
        return false;
    }

    @Override // com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityCreate() {
        super.onActivityCreate();
        int iChildCount = childCount();
        for (int i = 0; i < iChildCount; i++) {
            getChild(i).onActivityCreate();
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityDestroy() {
        super.onActivityDestroy();
        int iChildCount = childCount();
        for (int i = 0; i < iChildCount; i++) {
            getChild(i).onActivityDestroy();
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityPause() {
        super.onActivityPause();
        int iChildCount = childCount();
        for (int i = 0; i < iChildCount; i++) {
            getChild(i).onActivityPause();
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        int iChildCount = childCount();
        for (int i3 = 0; i3 < iChildCount; i3++) {
            getChild(i3).onActivityResult(i, i2, intent);
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityResume() {
        super.onActivityResume();
        int iChildCount = childCount();
        for (int i = 0; i < iChildCount; i++) {
            getChild(i).onActivityResume();
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityStart() {
        super.onActivityStart();
        int iChildCount = childCount();
        for (int i = 0; i < iChildCount; i++) {
            getChild(i).onActivityStart();
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityStop() {
        super.onActivityStop();
        int iChildCount = childCount();
        for (int i = 0; i < iChildCount; i++) {
            getChild(i).onActivityStop();
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        int iChildCount = childCount();
        for (int i = 0; i < iChildCount; i++) {
            getChild(i).onCreateOptionsMenu(menu);
        }
        return false;
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void onRenderFinish(int i) {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            WXComponent child = getChild(i2);
            if (child != null) {
                child.mTraceInfo.uiQueueTime = this.mTraceInfo.uiQueueTime;
                child.onRenderFinish(i);
            }
        }
        super.onRenderFinish(i);
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        int iChildCount = childCount();
        for (int i2 = 0; i2 < iChildCount; i2++) {
            getChild(i2).onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public Pair<WXComponent, Integer> rearrangeIndexAndGetChild(int i) {
        if (i < 0) {
            i = childCount() - 1;
        }
        return i < 0 ? new Pair<>(null, Integer.valueOf(i)) : new Pair<>(getChild(i), Integer.valueOf(i));
    }

    @UniJSMethod
    public void recoverImageList(String str) {
        if (getHostView() != null && lb.K(getHostView()) && (getHostView() instanceof ViewGroup)) {
            if (WXUtils.getBoolean(str, false).booleanValue()) {
                doViewTreeRecycleImageView(getHostView(), false);
                return;
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                WXComponent child = getChild(i);
                if ((child instanceof WXImage) && (((WXImage) child).getHostView() instanceof WXImageView)) {
                    WXImageView wXImageView = (WXImageView) child.getHostView();
                    if (wXImageView != null && lb.K(wXImageView)) {
                        wXImageView.autoRecoverImage();
                    }
                } else if (child instanceof WXVContainer) {
                    ((WXVContainer) child).recoverImageList(str);
                }
            }
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void recycled() {
        if (this.mChildren != null && !isFixed() && getAttrs().canRecycled()) {
            int size = this.mChildren.size();
            for (int i = 0; i < size; i++) {
                this.mChildren.get(i).recycled();
            }
        }
        super.recycled();
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void refreshData(WXComponent wXComponent) {
        if (wXComponent == null) {
            wXComponent = this;
        }
        super.refreshData(wXComponent);
        int iChildCount = childCount();
        for (int i = 0; i < iChildCount; i++) {
            getChild(i).refreshData(((WXVContainer) wXComponent).getChild(i));
        }
    }

    @UniJSMethod
    public void releaseImageList(String str) {
        if (getHostView() != null && lb.K(getHostView()) && (getHostView() instanceof ViewGroup)) {
            if (WXUtils.getBoolean(str, false).booleanValue()) {
                doViewTreeRecycleImageView(getHostView(), true);
                return;
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                WXComponent child = getChild(i);
                if ((child instanceof WXImage) && (((WXImage) child).getHostView() instanceof WXImageView)) {
                    WXImageView wXImageView = (WXImageView) child.getHostView();
                    if (wXImageView != null && lb.K(wXImageView)) {
                        wXImageView.autoReleaseImage();
                    }
                } else if (child instanceof WXVContainer) {
                    ((WXVContainer) child).releaseImageList(str);
                }
            }
        }
    }

    public void remove(WXComponent wXComponent, boolean z) {
        ArrayList<WXComponent> arrayList;
        if (wXComponent == null || (arrayList = this.mChildren) == null || arrayList.size() == 0) {
            return;
        }
        this.mChildren.remove(wXComponent);
        if (getInstance() != null && getInstance().getRootView() != null && wXComponent.isFixed()) {
            getInstance().removeFixedView(wXComponent.getHostView());
        } else if (getRealView() != null) {
            if (wXComponent.isVirtualComponent()) {
                wXComponent.removeVirtualComponent();
            } else {
                ViewParent parent = null;
                if ((wXComponent.getParent() instanceof WXBaseScroller) && wXComponent.getHostView() != null) {
                    parent = wXComponent.getHostView().getParent();
                }
                if (parent == null || !(parent instanceof ViewGroup)) {
                    getRealView().removeView(wXComponent.getHostView());
                } else {
                    ((ViewGroup) parent).removeView(wXComponent.getHostView());
                }
            }
        }
        if (z) {
            wXComponent.destroy();
        }
    }

    public abstract void removeBoxShadowHost();

    public void requestDisallowInterceptTouchEvent(boolean z) {
        WXGesture wXGesture = this.mGesture;
        if (wXGesture != null) {
            if (wXGesture.isRequestDisallowInterceptTouchEvent()) {
                return;
            } else {
                this.mGesture.setRequestDisallowInterceptTouchEvent(z);
            }
        }
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(z);
        }
    }

    @Deprecated
    public AbsVContainer(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        this.mChildren = new ArrayList<>();
    }

    public void addChild(WXComponent wXComponent, int i) {
        if (wXComponent == null || i < -1) {
            return;
        }
        wXComponent.mDeepInComponentTree = this.mDeepInComponentTree + 1;
        getInstance().setMaxDomDeep(wXComponent.mDeepInComponentTree);
        if (i >= this.mChildren.size()) {
            i = -1;
        }
        if (i == -1) {
            this.mChildren.add(wXComponent);
        } else {
            this.mChildren.add(i, wXComponent);
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public ViewGroup getRealView() {
        return (ViewGroup) super.getRealView();
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    @Deprecated
    public ViewGroup getView() {
        return getHostView();
    }

    public AbsVContainer(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        this.mChildren = new ArrayList<>();
    }
}
