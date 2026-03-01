package io.dcloud.feature.nativeObj;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.nostra13.dcloudimageloader.core.assist.FailReason;
import com.nostra13.dcloudimageloader.core.assist.ImageScaleType;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXBasicComponentType;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.ImageLoaderUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.feature.nativeObj.BannerLayout;
import io.dcloud.feature.nativeObj.NativeView;
import io.dcloud.feature.nativeObj.data.NativeImageDataItem;
import io.dcloud.feature.nativeObj.photoview.PhotoActivity;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import supwisdom.cx0;
import supwisdom.hx0;
import supwisdom.yw0;

/* JADX INFO: loaded from: classes3.dex */
public class NativeImageSlider extends NativeView {
    public static final String TF = "{clientX:%d,clientY:%d,pageX:%d,pageY:%d,screenX:%d,screenY:%d,currentImageIndex:%d}";
    public yw0 defaultOptions;
    public boolean isAutoplay;
    public boolean isFullscreen;
    public boolean isLoop;
    public View mBackgroundView;
    public BannerLayout mBannerLayout;
    public int mFistBitmapHeight;
    public int mFistBitmapWidth;
    public String mIndicator;
    public int mInterval;
    public int mSliderHeight;
    public int measureTop;

    public NativeImageSlider(Context context, IWebview iWebview, String str, String str2, JSONObject jSONObject) {
        super(context, iWebview, str, str2, jSONObject);
        this.isLoop = false;
        this.isFullscreen = true;
        this.isAutoplay = false;
        this.mInterval = 3000;
        this.mSliderHeight = 0;
        this.mFistBitmapWidth = 0;
        this.mFistBitmapHeight = 0;
        this.measureTop = 0;
        this.mIndicator = "default";
        addBannerView(iWebview);
        this.mIntercept = false;
    }

    private ArrayList<NativeImageDataItem> toArrayList(IWebview iWebview, JSONArray jSONArray) {
        ArrayList<NativeImageDataItem> arrayList = new ArrayList<>();
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                NativeImageDataItem nativeImageDataItem = new NativeImageDataItem();
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                String strConvertAppPath = PdrUtil.convertAppPath(iWebview, jSONObjectOptJSONObject.optString("src"));
                ImageLoaderUtil.addNetIconDownloadUrl(strConvertAppPath);
                nativeImageDataItem.setUrl(strConvertAppPath);
                if (jSONObjectOptJSONObject.has(AbsoluteConst.JSON_KEY_ALIGN)) {
                    nativeImageDataItem.align = jSONObjectOptJSONObject.optString(AbsoluteConst.JSON_KEY_ALIGN);
                }
                if (jSONObjectOptJSONObject.has(AbsoluteConst.JSON_KEY_VERTICAL_ALIGN)) {
                    nativeImageDataItem.verticalAlign = jSONObjectOptJSONObject.optString(AbsoluteConst.JSON_KEY_VERTICAL_ALIGN);
                }
                if (jSONObjectOptJSONObject.has("height")) {
                    nativeImageDataItem.height = jSONObjectOptJSONObject.optString("height");
                }
                if (jSONObjectOptJSONObject.has("width")) {
                    nativeImageDataItem.width = jSONObjectOptJSONObject.optString("width");
                }
                arrayList.add(nativeImageDataItem);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSliderHeight(int i, int i2) {
        int i3 = i2 * (this.mInnerWidth / i);
        if (this.mBannerLayout != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.mInnerWidth, i3);
            layoutParams.topMargin = this.mInnerTop + this.measureTop;
            layoutParams.bottomMargin = this.mInnerBottom;
            this.mBackgroundView.setLayoutParams(layoutParams);
            this.mBannerLayout.setLayoutParams(layoutParams);
            this.mSliderHeight = i3 + this.mInnerTop;
        }
    }

    public void addBannerView(IWebview iWebview) {
        JSONObject jSONObject = this.mStyle;
        if (jSONObject != null) {
            jSONArrayOptJSONArray = jSONObject.has("images") ? this.mStyle.optJSONArray("images") : null;
            if (this.mStyle.has("loop")) {
                this.isLoop = this.mStyle.optBoolean("loop");
            }
            if (this.mStyle.has(IApp.ConfigProperty.CONFIG_FULLSCREEN)) {
                this.isFullscreen = this.mStyle.optBoolean(IApp.ConfigProperty.CONFIG_FULLSCREEN);
            }
            if (this.mStyle.has(Constants.Name.AUTOPLAY)) {
                this.isAutoplay = this.mStyle.optBoolean(Constants.Name.AUTOPLAY);
                if (this.mStyle.has("interval")) {
                    this.mInterval = this.mStyle.optInt("interval", this.mInterval);
                }
            }
            if (this.mStyle.has(WXBasicComponentType.INDICATOR)) {
                this.mIndicator = this.mStyle.optString(WXBasicComponentType.INDICATOR);
            }
        }
        this.mWebView = iWebview;
        BannerLayout bannerLayout = new BannerLayout(getContext(), this.isLoop, false);
        this.mBannerLayout = bannerLayout;
        boolean z = this.isAutoplay;
        if (z) {
            bannerLayout.setAutoPlay(z, this.mInterval);
        }
        yw0.b bVar = new yw0.b();
        bVar.b(true);
        bVar.a(true);
        bVar.a(ImageScaleType.NONE);
        bVar.a(Bitmap.Config.RGB_565);
        bVar.a(new ColorDrawable(0));
        this.defaultOptions = bVar.a();
        this.mBannerLayout.setImageLoader(new BannerLayout.ImageLoader() { // from class: io.dcloud.feature.nativeObj.NativeImageSlider.1
            @Override // io.dcloud.feature.nativeObj.BannerLayout.ImageLoader
            public void displayImage(Context context, String str, final View view, final int i) {
                cx0.f().a(str, NativeImageSlider.this.defaultOptions, new hx0() { // from class: io.dcloud.feature.nativeObj.NativeImageSlider.1.1
                    public long startTime;

                    @Override // supwisdom.hx0
                    public void onLoadingCancelled(String str2, View view2) {
                    }

                    @Override // supwisdom.hx0
                    public void onLoadingComplete(String str2, View view2, Bitmap bitmap) {
                        NativeImageSlider nativeImageSlider = NativeImageSlider.this;
                        if (nativeImageSlider.isLayoutAdapt && i == 0) {
                            nativeImageSlider.mFistBitmapWidth = bitmap.getWidth();
                            NativeImageSlider.this.mFistBitmapHeight = bitmap.getHeight();
                            NativeImageSlider nativeImageSlider2 = NativeImageSlider.this;
                            nativeImageSlider2.updateSliderHeight(nativeImageSlider2.mFistBitmapWidth, nativeImageSlider2.mFistBitmapHeight);
                            NativeImageSlider.this.mCanvasView.requestLayout();
                        }
                        try {
                            if (!URLUtil.isNetworkUrl(str2) || System.currentTimeMillis() - this.startTime <= 500) {
                                ((ImageView) view).setImageBitmap(bitmap);
                                return;
                            }
                            ((ImageView) view).setImageBitmap(bitmap);
                            AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
                            alphaAnimation.setDuration(500L);
                            view.startAnimation(alphaAnimation);
                        } catch (Exception unused) {
                        }
                    }

                    @Override // supwisdom.hx0
                    public void onLoadingFailed(String str2, View view2, FailReason failReason) {
                    }

                    @Override // supwisdom.hx0
                    public void onLoadingStarted(String str2, View view2) {
                        this.startTime = System.currentTimeMillis();
                    }
                });
            }
        });
        this.mBannerLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() { // from class: io.dcloud.feature.nativeObj.NativeImageSlider.2
            @Override // io.dcloud.feature.nativeObj.BannerLayout.OnBannerItemClickListener
            public void onItemClick(int i) {
                NativeImageSlider nativeImageSlider = NativeImageSlider.this;
                if (!nativeImageSlider.isFullscreen) {
                    if (nativeImageSlider.mCanvasView.listenClick()) {
                        NativeImageSlider.this.mCanvasView.doTypeEvent(Constants.Event.CLICK);
                        return;
                    }
                    return;
                }
                Intent intent = new Intent(NativeImageSlider.this.getContext(), (Class<?>) PhotoActivity.class);
                intent.putParcelableArrayListExtra(PhotoActivity.IMAGE_URLS_KEY, NativeImageSlider.this.mBannerLayout.getUrls());
                intent.putExtra(PhotoActivity.IMAGE_CURRENT_INDEX_KEY, i);
                intent.putExtra(PhotoActivity.IMAGE_LOOP_KEY, true);
                intent.putExtra(PhotoActivity.IMAGE_PHOTO_KEY, true);
                intent.putExtra(PhotoActivity.IMAGE_PHOTO_TOP, NativeImageSlider.this.getTop());
                NativeImageSlider.this.getContext().startActivity(intent);
                Context context = NativeImageSlider.this.getContext();
                if (context instanceof Activity) {
                    ((Activity) context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }

            @Override // io.dcloud.feature.nativeObj.BannerLayout.OnBannerItemClickListener
            public void onItemLongClick(int i) {
            }
        });
        this.mBannerLayout.setIndicatorContainerData(BannerLayout.Position.centerBottom, 20, 10, 18, this.mIndicator);
        ArrayList<NativeImageDataItem> arrayList = toArrayList(iWebview, jSONArrayOptJSONArray);
        if (arrayList.size() > 0) {
            this.mBannerLayout.setViewUrls(arrayList, 0);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.mInnerWidth, this.isLayoutAdapt ? 0 : this.mInnerHeight);
        layoutParams.topMargin = this.mInnerTop + this.measureTop;
        layoutParams.bottomMargin = this.mInnerBottom;
        if (this.mBackgroundView == null) {
            this.mBackgroundView = new View(getContext());
        }
        addView(this.mBackgroundView, layoutParams);
        addView(this.mBannerLayout, layoutParams);
        super.attachCanvasView();
    }

    public void addImages(IWebview iWebview, JSONArray jSONArray) {
        if (this.mBannerLayout != null) {
            this.mBannerLayout.addViewUrls(toArrayList(iWebview, jSONArray), 0);
        }
    }

    @Override // io.dcloud.feature.nativeObj.NativeView
    public void attachCanvasView() {
    }

    @Override // io.dcloud.feature.nativeObj.NativeView
    public void clearNativeViewData() {
        super.clearNativeViewData();
        this.mFistBitmapHeight = 0;
        this.mFistBitmapWidth = 0;
        this.mSliderHeight = 0;
        BannerLayout bannerLayout = this.mBannerLayout;
        if (bannerLayout != null) {
            bannerLayout.clearBannerData();
        }
    }

    @Override // io.dcloud.feature.nativeObj.NativeView
    public void configurationCange() {
        super.configurationCange();
        if (this.mBannerLayout != null) {
            if (this.isLayoutAdapt) {
                int i = this.mFistBitmapHeight;
                if (i != 0) {
                    updateSliderHeight(this.mFistBitmapWidth, i);
                    return;
                }
                return;
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.mInnerWidth, this.mInnerHeight);
            layoutParams.topMargin = this.mInnerTop + this.measureTop;
            layoutParams.bottomMargin = this.mInnerBottom;
            this.mBackgroundView.setLayoutParams(layoutParams);
            this.mBannerLayout.setLayoutParams(layoutParams);
            this.mBannerLayout.requestLayout();
        }
    }

    @Override // io.dcloud.feature.nativeObj.NativeView, io.dcloud.common.DHInterface.IWaiter
    public Object doForFeature(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!str.equals("setAllowImageDownload")) {
            return super.doForFeature(str, obj);
        }
        Object[] objArr = (Object[]) obj;
        boolean zBooleanValue = ((Boolean) objArr[0]).booleanValue();
        boolean zBooleanValue2 = ((Boolean) objArr[1]).booleanValue();
        BannerLayout bannerLayout = this.mBannerLayout;
        if (bannerLayout == null) {
            return null;
        }
        bannerLayout.setAllowImageDownload(zBooleanValue, zBooleanValue2);
        return null;
    }

    public int getCurrentImageIndex() {
        BannerLayout bannerLayout = this.mBannerLayout;
        if (bannerLayout != null) {
            return bannerLayout.getCurrentPosition();
        }
        return 0;
    }

    @Override // io.dcloud.feature.nativeObj.NativeView
    public String getEventJSON() {
        return StringUtil.format(TF, Integer.valueOf((int) ((this.mTouchX - this.mInnerLeft) / this.mCreateScale)), Integer.valueOf((int) ((this.mTouchY - this.mInnerTop) / this.mCreateScale)), Integer.valueOf((int) (this.mTouchX / this.mCreateScale)), Integer.valueOf((int) (this.mTouchY / this.mCreateScale)), Integer.valueOf((int) (this.mTouchX / this.mCreateScale)), Integer.valueOf((int) (this.mTouchY / this.mCreateScale)), Integer.valueOf(getCurrentImageIndex()));
    }

    @Override // io.dcloud.feature.nativeObj.NativeView
    public int getNViewContentHeight() {
        ArrayList<NativeView.Overlay> arrayList = this.mOverlays;
        if (arrayList == null) {
            return this.mAppScreenHeight;
        }
        int i = 0;
        this.mInnerHeight = this.mAppScreenHeight;
        for (NativeView.Overlay overlay : arrayList) {
            int i2 = makeRect(this, overlay.mDestJson, overlay).bottom;
            if (i2 > i) {
                i = i2;
            }
        }
        int i3 = this.mSliderHeight;
        return i3 > i ? i3 : i;
    }

    @Override // io.dcloud.feature.nativeObj.NativeView, io.dcloud.common.DHInterface.INativeView
    public String getViewType() {
        return AbsoluteConst.NATIVE_IMAGESLIDER;
    }

    @Override // io.dcloud.feature.nativeObj.NativeView
    public void init() {
        super.init();
        if (this.mBackgroundView == null) {
            this.mBackgroundView = new View(getContext());
        }
        this.mBackgroundView.setBackgroundColor(this.mBackGroundColor);
    }

    @Override // io.dcloud.feature.nativeObj.NativeView
    public void interceptTouchEvent(boolean z) {
        this.mIntercept = false;
    }

    @Override // io.dcloud.feature.nativeObj.NativeView
    public void measureChildViewToTop(int i) {
        super.measureChildViewToTop(i);
        this.measureTop = i;
        if (this.mBannerLayout != null) {
            configurationCange();
        }
    }

    public void setImages(IWebview iWebview, JSONArray jSONArray) {
        if (this.mBannerLayout != null) {
            this.mBannerLayout.setViewUrls(toArrayList(iWebview, jSONArray), 0);
        }
    }

    @Override // io.dcloud.feature.nativeObj.NativeView
    public void setStyle(JSONObject jSONObject, boolean z) {
        super.setStyle(jSONObject, z);
        if (this.mBannerLayout != null) {
            JSONObject jSONObject2 = this.mStyle;
            if (jSONObject2 != null) {
                jSONArrayOptJSONArray = jSONObject2.has("images") ? this.mStyle.optJSONArray("images") : null;
                if (this.mStyle.has("loop")) {
                    this.isLoop = this.mStyle.optBoolean("loop");
                }
                if (this.mStyle.has(IApp.ConfigProperty.CONFIG_FULLSCREEN)) {
                    this.isFullscreen = this.mStyle.optBoolean(IApp.ConfigProperty.CONFIG_FULLSCREEN);
                }
                if (this.mStyle.has(WXBasicComponentType.INDICATOR)) {
                    this.mIndicator = this.mStyle.optString(WXBasicComponentType.INDICATOR);
                }
            }
            ArrayList<NativeImageDataItem> arrayList = toArrayList(this.mWebView, jSONArrayOptJSONArray);
            this.mBannerLayout.setImageLoop(Boolean.valueOf(this.isLoop));
            this.mBannerLayout.setIndicatorType(this.mIndicator);
            this.mBannerLayout.setViewUrls(arrayList, 0);
        }
    }

    @Override // io.dcloud.feature.nativeObj.NativeView, io.dcloud.common.DHInterface.INativeView
    public void setStyleBackgroundColor(int i) {
        super.setStyleBackgroundColor(i);
        this.mBackgroundView.setBackgroundColor(this.mBackGroundColor);
    }
}
