package io.dcloud.common.adapter.util;

import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import com.taobao.weex.appfram.pickers.WXPickersModule;
import com.taobao.weex.common.Constants;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import java.util.HashMap;
import org.bouncycastle.jcajce.provider.config.ProviderConfigurationPermission;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ViewOptions extends ViewRect {
    public static final int BG_NONE = -1;
    public String animationAlphaBackground;
    public int coverage;
    public HashMap<String, DragBean> dragData;
    public JSONObject mPullToRefresh;
    public JSONArray mSubNViews;
    public Object mTag;
    public boolean mUseHardwave;
    public String name;
    public JSONObject titleNView;
    public JSONObject transform;
    public JSONObject transition;
    public boolean scalable = false;
    public String mInjection = "true";
    public String mPlusrequire = "normal";
    public boolean mDisablePlus = false;
    public String mScrollIndicator = ProviderConfigurationPermission.ALL_STR;
    public float opacity = -1.0f;
    public int background = -1;
    public int maskColor = -1;
    public String strBackground = null;
    public boolean webviewBGTransparent = false;
    public String strTabBG = null;
    public String errorPage = null;
    public boolean mBounce = false;
    public String mCacheMode = "default";
    public String mVideoFullscree = "auto";
    public String popGesture = "none";
    public String historyBack = "none";
    public String mGeoInject = "none";
    public boolean dragH5NeedTouchEvent = false;
    public String backButtonAutoControl = "none";
    public boolean isAnimationOptimization = false;
    public boolean isUserSelect = true;
    public String softinputMode = "adjustResize";
    public JSONObject mUniNViewJson = null;
    public JSONObject mProgressJson = null;
    public JSONObject mDebugRefresh = null;
    public JSONObject mUniPageUrl = null;
    public Boolean isTabItem = Boolean.FALSE;
    public boolean isUniH5 = false;

    public ViewOptions() {
        this.mUseHardwave = true;
        this.mUseHardwave = MobilePhoneModel.checkPhoneBanAcceleration(Build.BRAND);
    }

    public static ViewOptions createViewOptionsData(ViewOptions viewOptions, ViewRect viewRect) {
        return createViewOptionsData(viewOptions, null, viewRect);
    }

    public String getScrollIndicator() {
        return this.mScrollIndicator;
    }

    public boolean hasBackground() {
        return (this.background == -1 && (PdrUtil.isEmpty(this.strBackground) || this.strBackground.equals("transparent"))) ? false : true;
    }

    public boolean hasMask() {
        return this.maskColor != -1;
    }

    public boolean hasTransparentValue() {
        if (!isTransparent() && !PdrUtil.checkAlphaTransparent(this.background)) {
            float f = this.opacity;
            if (f < 0.0f || f >= 1.0f) {
                return false;
            }
        }
        return true;
    }

    public boolean isTabHasBg() {
        return this.isTabItem.booleanValue() && !PdrUtil.isEmpty(this.strTabBG);
    }

    public boolean isTransparent() {
        return PdrUtil.isEquals("transparent", this.strBackground);
    }

    public void setBackButtonAutoControl(JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.has("backButtonAutoControl")) {
            return;
        }
        String string = JSONUtil.getString(jSONObject, "backButtonAutoControl");
        if ("none".equals(string) || "hide".equals(string) || "close".equals(string) || "quit".equals(string)) {
            this.backButtonAutoControl = string;
        }
    }

    public void setDragData(JSONObject jSONObject, JSONObject jSONObject2, IFrameView iFrameView, IFrameView iFrameView2, String str, View view) {
        try {
            if (this.dragData == null) {
                this.dragData = new HashMap<>();
            }
            DragBean dragBean = new DragBean();
            dragBean.dragCurrentViewOp = jSONObject;
            dragBean.dragBindViewOp = jSONObject2;
            dragBean.dragBindWebView = iFrameView;
            dragBean.dragCallBackWebView = iFrameView2;
            dragBean.dragCbId = str;
            dragBean.nativeView = view;
            if (jSONObject.has("direction")) {
                String string = jSONObject.getString("direction");
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                this.dragData.put(string.toLowerCase(), dragBean);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setTitleNView(JSONObject jSONObject, IWebview iWebview) {
        if (jSONObject != null) {
            if ("transparent".equals(jSONObject.optString("type"))) {
                String strOptString = null;
                if (jSONObject.has(WXPickersModule.KEY_TITLE_COLOR)) {
                    strOptString = jSONObject.optString(WXPickersModule.KEY_TITLE_COLOR);
                } else if (jSONObject.has("titlecolor")) {
                    strOptString = jSONObject.optString("titlecolor");
                }
                if (!TextUtils.isEmpty(strOptString)) {
                    try {
                        int color = Color.parseColor(strOptString);
                        String hexString = Integer.toHexString(0);
                        if (1 == hexString.length()) {
                            hexString = "0" + hexString.toUpperCase();
                        }
                        String hexString2 = Integer.toHexString(Color.red(color));
                        if (1 == hexString2.length()) {
                            hexString2 = "0" + hexString2.toUpperCase();
                        }
                        String hexString3 = Integer.toHexString(Color.green(color));
                        if (1 == hexString3.length()) {
                            hexString3 = "0" + hexString3.toUpperCase();
                        }
                        String hexString4 = Integer.toHexString(Color.blue(color));
                        if (1 == hexString4.length()) {
                            hexString4 = "0" + hexString4.toUpperCase();
                        }
                        String str = "#" + hexString + hexString2 + hexString3 + hexString4;
                        if (jSONObject.has(WXPickersModule.KEY_TITLE_COLOR)) {
                            jSONObject.put(WXPickersModule.KEY_TITLE_COLOR, str);
                        } else if (jSONObject.has("titlecolor")) {
                            jSONObject.put("titlecolor", str);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            this.coverage = PdrUtil.convertToScreenInt("136px", PlatformUtil.SCREEN_WIDTH(iWebview.getContext()), 0, iWebview.getScale());
            this.coverage = PdrUtil.convertToScreenInt(jSONObject.optString("coverage"), PlatformUtil.SCREEN_WIDTH(iWebview.getContext()), this.coverage, iWebview.getScale());
            this.titleNView = jSONObject;
        }
    }

    @Override // io.dcloud.common.adapter.util.ViewRect
    public void updateViewData(ViewRect viewRect) {
        super.updateViewData(viewRect);
    }

    public static ViewOptions createViewOptionsData(ViewOptions viewOptions, ViewRect viewRect, ViewRect viewRect2) {
        if (viewOptions == null) {
            return null;
        }
        ViewOptions viewOptions2 = new ViewOptions();
        if (viewRect != null) {
            viewOptions2.setFrameParentViewRect(viewRect);
        }
        viewOptions2.mWebviewScale = viewOptions.mWebviewScale;
        viewOptions2.setParentViewRect(viewRect2);
        viewOptions2.updateViewData(viewOptions.mJsonViewOption);
        return viewOptions2;
    }

    @Override // io.dcloud.common.adapter.util.ViewRect
    public boolean updateViewData(JSONObject jSONObject) {
        boolean zUpdateViewData = super.updateViewData(jSONObject);
        if (jSONObject != null) {
            if (!jSONObject.isNull("isTab")) {
                this.isTabItem = Boolean.valueOf(jSONObject.optBoolean("isTab", false));
            }
            if (!jSONObject.isNull("isUniH5")) {
                this.isUniH5 = jSONObject.optBoolean("isUniH5", false);
            }
            if (!JSONUtil.isNull(jSONObject, AbsoluteConst.JSON_KEY_SCROLLINDICATOR)) {
                this.mScrollIndicator = JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_SCROLLINDICATOR);
            }
            String lowerCase = null;
            if (!jSONObject.isNull("background")) {
                try {
                    String lowerCase2 = JSONUtil.getString(jSONObject, "background").toLowerCase();
                    try {
                        if (this.isTabItem.booleanValue()) {
                            jSONObject.remove("background");
                            this.strTabBG = lowerCase2;
                            this.strBackground = null;
                            this.background = -1;
                        } else {
                            this.strBackground = lowerCase2;
                            this.background = PdrUtil.stringToColor(lowerCase2);
                        }
                        lowerCase = lowerCase2;
                    } catch (Exception e2) {
                        e = e2;
                        lowerCase = lowerCase2;
                        e.printStackTrace();
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
            if (!jSONObject.isNull(AbsoluteConst.JSON_KEY_WEBBG_TRANSPARENT)) {
                this.webviewBGTransparent = JSONUtil.getBoolean(jSONObject, AbsoluteConst.JSON_KEY_WEBBG_TRANSPARENT);
            }
            if (!jSONObject.isNull(AbsoluteConst.JSON_KEY_TABBG)) {
                lowerCase = JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_TABBG).toLowerCase();
                this.strTabBG = lowerCase;
            }
            if (!jSONObject.isNull(AbsoluteConst.JSON_KEY_ANIMATION_ALPHA_BG)) {
                try {
                    this.animationAlphaBackground = JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_ANIMATION_ALPHA_BG).toLowerCase();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            } else if (!TextUtils.isEmpty(lowerCase)) {
                try {
                    this.animationAlphaBackground = lowerCase;
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
            if (!jSONObject.isNull(AbsoluteConst.JSON_KEY_MASK)) {
                try {
                    this.maskColor = PdrUtil.stringToColor(JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_MASK).toLowerCase());
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
            if (!jSONObject.isNull(AbsoluteConst.JSON_KEY_CACHEMODE)) {
                this.mCacheMode = JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_CACHEMODE);
            }
            this.mUseHardwave = PdrUtil.parseBoolean(JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_HARDWARE_ACCELERATED), this.mUseHardwave, false);
            this.opacity = PdrUtil.parseFloat(JSONUtil.getString(jSONObject, "opacity"), this.opacity);
            this.scalable = PdrUtil.parseBoolean(JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_SCALABLE), this.scalable, false);
            this.transition = JSONUtil.getJSONObject(jSONObject, AbsoluteConst.JSON_KEY_TRANSITION);
            this.transform = JSONUtil.getJSONObject(jSONObject, "transform");
            this.errorPage = JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_ERROR_PAGE);
            this.mInjection = JSONUtil.getString(jSONObject, "injection");
            this.mPlusrequire = jSONObject.optString("plusrequire", this.mPlusrequire);
            this.mDisablePlus = jSONObject.optBoolean(AbsoluteConst.JSON_KEY_DISABLE_PLUS, this.mDisablePlus);
            this.popGesture = jSONObject.optString("popGesture", this.popGesture);
            this.historyBack = jSONObject.optString(AbsoluteConst.JSON_KEY_HISTORYBACK, this.historyBack);
            this.isUserSelect = jSONObject.optBoolean("userSelect", true);
            this.softinputMode = jSONObject.optString(AbsoluteConst.JSON_KEY_SOFTINPUT_MODE, this.softinputMode);
            if (jSONObject.has(AbsoluteConst.JSON_KEY_UNINVIEW)) {
                this.mUniNViewJson = jSONObject.optJSONObject(AbsoluteConst.JSON_KEY_UNINVIEW);
            }
            if (jSONObject.has("replacewebapi")) {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("replacewebapi");
                if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.has("geolocation")) {
                    this.mGeoInject = jSONObjectOptJSONObject.optString("geolocation", this.mGeoInject);
                }
            } else if (jSONObject.has("geolocation")) {
                this.mGeoInject = jSONObject.optString("geolocation", this.mGeoInject);
            }
            this.mProgressJson = JSONUtil.getJSONObject(jSONObject, AbsoluteConst.JSON_KEY_PROGRESS);
            JSONObject jSONObject2 = JSONUtil.getJSONObject(jSONObject, AbsoluteConst.JSON_KEY_DEBUG_REFRESH);
            this.mDebugRefresh = jSONObject2;
            if (jSONObject2 != null && jSONObject2.has("arguments")) {
                this.mUniPageUrl = JSONUtil.createJSONObject(this.mDebugRefresh.optString("arguments"));
            }
            if (jSONObject.has("uniPageUrl")) {
                this.mUniPageUrl = JSONUtil.getJSONObject(jSONObject, "uniPageUrl");
            }
            String string = JSONUtil.getString(jSONObject, "bounce");
            if ("vertical".equalsIgnoreCase(string) || Constants.Value.HORIZONTAL.equalsIgnoreCase(string) || ProviderConfigurationPermission.ALL_STR.equalsIgnoreCase(string)) {
                this.mBounce = true;
            } else {
                this.mBounce = false;
            }
            this.mVideoFullscree = JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_VIDEO_FULL_SCREEN);
            setBackButtonAutoControl(jSONObject);
            if (jSONObject.has("titleNView")) {
                this.titleNView = JSONUtil.combinJSONObject(this.titleNView, JSONUtil.getJSONObject(jSONObject, "titleNView"));
            } else if (jSONObject.has("navigationbar")) {
                this.titleNView = JSONUtil.combinJSONObject(this.titleNView, JSONUtil.getJSONObject(jSONObject, "navigationbar"));
            }
            if (jSONObject.has(AbsoluteConst.EVENTS_PULL_TO_REFRESH)) {
                this.mPullToRefresh = JSONUtil.combinJSONObject(this.mPullToRefresh, JSONUtil.getJSONObject(jSONObject, AbsoluteConst.EVENTS_PULL_TO_REFRESH));
            }
            if (jSONObject.has(AbsoluteConst.JSON_KEY_SUB_NVIEWS)) {
                this.mSubNViews = JSONUtil.getJSONArray(jSONObject, AbsoluteConst.JSON_KEY_SUB_NVIEWS);
            }
            if (jSONObject.has(AbsoluteConst.JSON_KEY_ANIMATION_OPTIMIZATION) && "auto".equals(jSONObject.optString(AbsoluteConst.JSON_KEY_ANIMATION_OPTIMIZATION))) {
                this.isAnimationOptimization = true;
            }
        }
        return zUpdateViewData;
    }
}
