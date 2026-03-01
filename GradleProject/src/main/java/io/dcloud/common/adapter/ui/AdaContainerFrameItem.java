package io.dcloud.common.adapter.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import io.dcloud.common.DHInterface.IContainerView;
import io.dcloud.common.DHInterface.INativeView;
import io.dcloud.common.DHInterface.ITypeofAble;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AdaContainerFrameItem extends AdaFrameItem implements IContainerView {
    public boolean isITypeofAble;
    public ArrayList<AdaFrameItem> mChildArrayList;
    public ArrayList<INativeView> mChildNativeViewList;

    public AdaContainerFrameItem(Context context) {
        super(context);
        this.isITypeofAble = false;
        this.mChildArrayList = null;
        this.mChildNativeViewList = null;
        this.mChildArrayList = new ArrayList<>(1);
    }

    @Override // io.dcloud.common.DHInterface.IContainerView
    public void addFrameItem(AdaFrameItem adaFrameItem, ViewGroup.LayoutParams layoutParams) {
        addFrameItem(adaFrameItem, -1, layoutParams);
    }

    public void addNativeViewChild(INativeView iNativeView) {
        if (this.mChildNativeViewList == null) {
            this.mChildNativeViewList = new ArrayList<>();
        }
        if (iNativeView == null || this.mChildNativeViewList.contains(iNativeView)) {
            return;
        }
        this.mChildNativeViewList.add(iNativeView);
    }

    public boolean checkITypeofAble() {
        boolean z = this.isITypeofAble;
        if (z) {
            return z;
        }
        for (AdaFrameItem adaFrameItem : this.mChildArrayList) {
            if (adaFrameItem instanceof AdaContainerFrameItem) {
                return ((AdaContainerFrameItem) adaFrameItem).checkITypeofAble();
            }
        }
        return false;
    }

    public void clearView() {
        ArrayList<AdaFrameItem> arrayList = this.mChildArrayList;
        if (arrayList != null) {
            Iterator<AdaFrameItem> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().dispose();
            }
            this.mChildArrayList.clear();
        }
        View view = this.mViewImpl;
        if ((view instanceof ViewGroup) && view != null) {
            ((ViewGroup) view).removeAllViews();
        }
        this.isITypeofAble = false;
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void dispose() {
        clearView();
        super.dispose();
    }

    public ArrayList<AdaFrameItem> getChilds() {
        return this.mChildArrayList;
    }

    public ViewGroup obtainMainViewGroup() {
        return (ViewGroup) this.mViewImpl;
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public boolean onDispose() {
        boolean zOnDispose = super.onDispose();
        ArrayList<AdaFrameItem> arrayList = this.mChildArrayList;
        if (arrayList != null) {
            Iterator<AdaFrameItem> it = arrayList.iterator();
            while (it.hasNext()) {
                zOnDispose |= it.next().onDispose();
            }
        }
        return zOnDispose;
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void onPopFromStack(boolean z) {
        super.onPopFromStack(z);
        ArrayList<AdaFrameItem> arrayList = this.mChildArrayList;
        if (arrayList != null) {
            Iterator<AdaFrameItem> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onPopFromStack(z);
            }
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void onPushToStack(boolean z) {
        super.onPushToStack(z);
        ArrayList<AdaFrameItem> arrayList = this.mChildArrayList;
        if (arrayList != null) {
            Iterator<AdaFrameItem> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onPushToStack(z);
            }
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void onResize() {
        super.onResize();
        Iterator<AdaFrameItem> it = this.mChildArrayList.iterator();
        while (it.hasNext()) {
            it.next().onResize();
        }
    }

    @Override // io.dcloud.common.DHInterface.IContainerView
    public void removeAllFrameItem() {
        if (this.mViewImpl != null) {
            clearView();
            ((ViewGroup) this.mViewImpl).removeAllViews();
        }
        this.isITypeofAble = false;
    }

    @Override // io.dcloud.common.DHInterface.IContainerView
    public void removeFrameItem(AdaFrameItem adaFrameItem) {
        if (adaFrameItem instanceof ITypeofAble) {
            this.isITypeofAble = false;
        }
        View view = this.mViewImpl;
        if (view != null) {
            ((ViewGroup) view).removeView(adaFrameItem.obtainMainView());
            this.mChildArrayList.remove(adaFrameItem);
        }
    }

    public void removeNativeViewChild(INativeView iNativeView) {
        ArrayList<INativeView> arrayList = this.mChildNativeViewList;
        if (arrayList == null || !arrayList.contains(iNativeView)) {
            return;
        }
        this.mChildNativeViewList.remove(iNativeView);
    }

    public void sortNativeViewBringToFront() {
        ArrayList<INativeView> arrayList = this.mChildNativeViewList;
        if (arrayList != null) {
            for (INativeView iNativeView : arrayList) {
                if (iNativeView.obtanMainView() != null && iNativeView.obtanMainView().getParent() != null) {
                    iNativeView.obtanMainView().bringToFront();
                }
            }
        }
    }

    @Override // io.dcloud.common.DHInterface.IContainerView
    public void addFrameItem(AdaFrameItem adaFrameItem) {
        addFrameItem(adaFrameItem, -1);
    }

    public void addFrameItem(AdaFrameItem adaFrameItem, int i, ViewGroup.LayoutParams layoutParams) {
        if (adaFrameItem instanceof ITypeofAble) {
            this.isITypeofAble = true;
        }
        if (this.mViewImpl instanceof ViewGroup) {
            View viewObtainMainView = adaFrameItem.obtainMainView();
            ViewParent parent = viewObtainMainView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(viewObtainMainView);
                this.mChildArrayList.remove(adaFrameItem);
            }
            if (((ViewGroup) this.mViewImpl).getChildCount() < i) {
                i = ((ViewGroup) this.mViewImpl).getChildCount();
            }
            ((ViewGroup) this.mViewImpl).addView(viewObtainMainView, i, layoutParams);
            if (i < 0 || i > this.mChildArrayList.size()) {
                i = this.mChildArrayList.size();
            }
            this.mChildArrayList.add(i, adaFrameItem);
        }
    }

    public void removeAllFrameItem(boolean z) {
        if (z) {
            View view = this.mViewImpl;
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (!(childAt instanceof INativeView)) {
                        viewGroup.removeView(childAt);
                    }
                }
                ArrayList<AdaFrameItem> arrayList = this.mChildArrayList;
                if (arrayList != null) {
                    Iterator<AdaFrameItem> it = arrayList.iterator();
                    while (it.hasNext()) {
                        it.next().dispose();
                    }
                    this.mChildArrayList.clear();
                    return;
                }
                return;
            }
            return;
        }
        removeAllFrameItem();
    }

    public void addFrameItem(AdaFrameItem adaFrameItem, int i) {
        ViewGroup.LayoutParams layoutParams = adaFrameItem.obtainMainView().getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -1);
        }
        addFrameItem(adaFrameItem, i, layoutParams);
    }
}
