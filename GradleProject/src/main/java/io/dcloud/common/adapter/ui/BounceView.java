package io.dcloud.common.adapter.ui;

import android.text.TextUtils;
import io.dcloud.common.adapter.ui.AdaWebViewParent;
import io.dcloud.common.adapter.ui.fresh.ILoadingLayout;
import io.dcloud.common.adapter.ui.fresh.PullToRefreshBase;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import org.json.JSONObject;
import supwisdom.cv;

/* JADX INFO: loaded from: classes2.dex */
public class BounceView implements PullToRefreshBase.OnStateChangeListener {
    public static final String STATE_CHANGED_TEMPLATE = "{status:'%s'}";
    public static final String[] keys = {"top", "left", "right", "bottom"};
    public int[] changeStateHeights;
    public ILoadingLayout.State mCurState;
    public AdaFrameView mFrameView;
    public JSONObject mJSONObject;
    public String[] mPositions;
    public boolean[] mSupports;
    public AdaWebview mWebview;
    public float mWebviewScale;
    public int[] maxPullHeights;

    public BounceView(AdaFrameView adaFrameView, AdaWebview adaWebview) {
        String[] strArr = keys;
        this.changeStateHeights = new int[strArr.length];
        this.maxPullHeights = new int[strArr.length];
        this.mPositions = new String[strArr.length];
        this.mSupports = new boolean[strArr.length];
        this.mCurState = null;
        this.mFrameView = adaFrameView;
        this.mWebview = adaWebview;
        this.mWebviewScale = adaWebview.getScaleOfOpenerWebview();
    }

    public void checkOffset(AdaFrameView adaFrameView, final AdaWebViewParent.PullToRefreshWebViewExt pullToRefreshWebViewExt, JSONObject jSONObject, int i, int i2) {
        JSONObject jSONObject2 = JSONUtil.getJSONObject(jSONObject, "offset");
        if (jSONObject2 != null) {
            String string = JSONUtil.getString(jSONObject2, "top");
            String string2 = JSONUtil.getString(jSONObject2, "left");
            String string3 = JSONUtil.getString(jSONObject2, "right");
            if (TextUtils.isEmpty(string)) {
                if (!TextUtils.isEmpty(string3) && (adaFrameView.obtainMainView() instanceof cv)) {
                    ((cv) adaFrameView.obtainMainView()).a("right", string3, this.mWebview.getScale());
                    return;
                } else {
                    if (TextUtils.isEmpty(string2) || !(adaFrameView.obtainMainView() instanceof cv)) {
                        return;
                    }
                    ((cv) adaFrameView.obtainMainView()).a("left", string2, this.mWebview.getScale());
                    return;
                }
            }
            AdaWebview adaWebview = this.mWebview;
            int iConvertToScreenInt = PdrUtil.convertToScreenInt(string, adaWebview.mViewOptions.height, i2, adaWebview.getScale());
            if (iConvertToScreenInt < i) {
                pullToRefreshWebViewExt.smoothScrollTo(-iConvertToScreenInt);
                this.mWebview.obtainWindowView().postDelayed(new Runnable() { // from class: io.dcloud.common.adapter.ui.BounceView.1
                    @Override // java.lang.Runnable
                    public void run() {
                        pullToRefreshWebViewExt.smoothScrollTo(0);
                    }
                }, 250L);
            } else {
                if (iConvertToScreenInt <= i2) {
                    i2 = iConvertToScreenInt;
                }
                pullToRefreshWebViewExt.smoothScrollTo(-i2);
                pullToRefreshWebViewExt.doPullRefreshing(true, 250L);
            }
        }
    }

    public void onResize() {
        parseJsonOption(this.mJSONObject);
    }

    @Override // io.dcloud.common.adapter.ui.fresh.PullToRefreshBase.OnStateChangeListener
    public void onStateChanged(ILoadingLayout.State state, boolean z) {
        boolean z2 = this.mCurState != state;
        this.mCurState = state;
        if (!z2 || state == ILoadingLayout.State.RESET) {
            return;
        }
        if (state == ILoadingLayout.State.PULL_TO_REFRESH) {
            Logger.d("refresh", "BounceView PULL_TO_REFRESH");
            this.mWebview.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_DRAG_BOUNCE, StringUtil.format(STATE_CHANGED_TEMPLATE, AbsoluteConst.BOUNCE_BEFORE_CHANGE_OFFSET));
        } else if (state == ILoadingLayout.State.RELEASE_TO_REFRESH) {
            Logger.d("refresh", "BounceView RELEASE_TO_REFRESH");
            this.mWebview.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_DRAG_BOUNCE, StringUtil.format(STATE_CHANGED_TEMPLATE, AbsoluteConst.BOUNCE_AFTER_CHANGE_OFFSET));
        } else if (state == ILoadingLayout.State.REFRESHING) {
            Logger.d("refresh", "BounceView REFRESHING");
            this.mWebview.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_DRAG_BOUNCE, StringUtil.format(STATE_CHANGED_TEMPLATE, AbsoluteConst.BOUNCE_DRAG_END_AFTER_CHANG_EOFFSET));
        }
    }

    public void parseJsonOption(JSONObject jSONObject) {
        try {
            JSONObject jSONObjectCombinJSONObject = JSONUtil.combinJSONObject(this.mJSONObject, jSONObject);
            this.mJSONObject = jSONObjectCombinJSONObject;
            int i = 0;
            if (!jSONObjectCombinJSONObject.isNull("position")) {
                JSONObject jSONObject2 = JSONUtil.getJSONObject(jSONObjectCombinJSONObject, "position");
                int i2 = 0;
                while (true) {
                    String[] strArr = keys;
                    if (i2 >= strArr.length) {
                        break;
                    }
                    if (!jSONObject2.isNull(strArr[i2])) {
                        String string = JSONUtil.getString(jSONObject2, strArr[i2]);
                        if ("none".equals(string)) {
                            this.mSupports[i2] = false;
                        } else if ("auto".equals(string)) {
                            this.mSupports[i2] = true;
                            int[] iArr = this.maxPullHeights;
                            iArr[i2] = this.mWebview.mViewOptions.height / 3;
                            this.changeStateHeights[i2] = iArr[i2] / 2;
                        } else {
                            this.mSupports[i2] = true;
                            int[] iArr2 = this.maxPullHeights;
                            int i3 = this.mWebview.mViewOptions.height;
                            iArr2[i2] = PdrUtil.convertToScreenInt(string, i3, i3 / 3, this.mWebviewScale);
                            this.changeStateHeights[i2] = this.maxPullHeights[i2] / 2;
                        }
                    }
                    i2++;
                }
            } else {
                this.mSupports[0] = true;
                int[] iArr3 = this.maxPullHeights;
                iArr3[0] = this.mWebview.mViewOptions.height / 3;
                this.changeStateHeights[0] = iArr3[0] / 2;
            }
            if (jSONObjectCombinJSONObject.isNull(AbsoluteConst.BOUNCE_CHANGEOFFSET)) {
                this.changeStateHeights[0] = this.maxPullHeights[0] / 2;
                return;
            }
            JSONObject jSONObject3 = JSONUtil.getJSONObject(jSONObjectCombinJSONObject, AbsoluteConst.BOUNCE_CHANGEOFFSET);
            while (true) {
                String[] strArr2 = keys;
                if (i >= strArr2.length) {
                    return;
                }
                if (!jSONObject3.isNull(strArr2[i])) {
                    this.changeStateHeights[i] = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject3, strArr2[i]), this.mWebview.mViewOptions.height, this.maxPullHeights[i] / 2, this.mWebviewScale);
                }
                i++;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
