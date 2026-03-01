package io.dcloud.feature.nativeObj.richtext;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nostra13.dcloudimageloader.core.assist.FailReason;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.nativeObj.INativeViewChildView;
import io.dcloud.feature.nativeObj.NativeView;
import io.dcloud.feature.nativeObj.richtext.dom.ImgDomElement;
import java.io.InputStream;
import org.json.JSONObject;
import supwisdom.cx0;
import supwisdom.hx0;

/* JADX INFO: loaded from: classes3.dex */
public class RichTextLayout {

    public static class DefaultAssets implements IAssets {
        public boolean isClick;
        public String mCallBackId;
        public NativeView mNativeView;
        public IWebview mWebview;

        public DefaultAssets(IWebview iWebview) {
            this(iWebview, null);
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public InputStream convert2InputStream(String str) {
            return this.mWebview.obtainApp().obtainResInStream(this.mWebview.obtainFullUrl(), str);
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public float convertHeight(String str, float f) {
            return this.mNativeView != null ? PdrUtil.parseFloat(str, r0.mInnerHeight, f, getScale()) : PdrUtil.parseFloat(str, this.mWebview.obtainApp().getInt(1), f, getScale());
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public float convertWidth(String str, float f) {
            return this.mNativeView != null ? PdrUtil.parseFloat(str, r0.mInnerWidth, f, getScale()) : PdrUtil.parseFloat(str, this.mWebview.obtainApp().getInt(0), f, getScale());
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public int getDefaultColor(boolean z) {
            return z ? -16776961 : -16777216;
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public String getOnClickCallBackId() {
            return this.mCallBackId;
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public float getScale() {
            NativeView nativeView = this.mNativeView;
            return nativeView != null ? nativeView.mCreateScale : this.mWebview.getScale();
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public boolean isClick() {
            return this.isClick;
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public void loadResource(final ImgDomElement.AsycLoader asycLoader) {
            cx0.f().a(asycLoader.url, new hx0() { // from class: io.dcloud.feature.nativeObj.richtext.RichTextLayout.DefaultAssets.1
                @Override // supwisdom.hx0
                public void onLoadingCancelled(String str, View view) {
                }

                @Override // supwisdom.hx0
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    asycLoader.onComplete(bitmap);
                }

                @Override // supwisdom.hx0
                public void onLoadingFailed(String str, View view, FailReason failReason) {
                }

                @Override // supwisdom.hx0
                public void onLoadingStarted(String str, View view) {
                }
            });
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public void setClick(boolean z) {
            this.isClick = z;
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public void setOnClickCallBackId(String str) {
            this.mCallBackId = str;
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public int stringToColor(String str) {
            return PdrUtil.stringToColor(str);
        }

        public DefaultAssets(IWebview iWebview, NativeView nativeView) {
            this.mWebview = null;
            this.mNativeView = null;
            this.isClick = false;
            this.mCallBackId = null;
            this.mWebview = iWebview;
            this.mNativeView = nativeView;
        }
    }

    public static class RichTextLayoutHolder extends LinearLayout implements IAssets, INativeViewChildView {
        public boolean isClick;
        public String mCallBackId;
        public DefaultAssets mDefaultAssets;
        public String mItemId;
        public TextView mMainView;
        public NativeView mNativeView;
        public int mNativeViewHeight;
        public IWebview mWebView;

        public RichTextLayoutHolder(Context context, IWebview iWebview, NativeView nativeView, String str) {
            super(context);
            this.mMainView = null;
            this.mWebView = null;
            this.mNativeView = null;
            this.mItemId = null;
            this.mNativeViewHeight = -2;
            this.mDefaultAssets = null;
            this.isClick = false;
            this.mCallBackId = null;
            this.mWebView = iWebview;
            this.mNativeView = nativeView;
            this.mItemId = str;
            TextView textView = new TextView(context) { // from class: io.dcloud.feature.nativeObj.richtext.RichTextLayout.RichTextLayoutHolder.1
                @Override // android.widget.TextView, android.view.View
                public boolean onTouchEvent(MotionEvent motionEvent) {
                    boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
                    Object tag = getTag();
                    if ((tag instanceof String) && Boolean.parseBoolean((String) tag)) {
                        return zOnTouchEvent;
                    }
                    return false;
                }
            };
            this.mMainView = textView;
            addView(textView);
            this.mDefaultAssets = new DefaultAssets(iWebview, nativeView);
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public InputStream convert2InputStream(String str) {
            return this.mDefaultAssets.convert2InputStream(str);
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public float convertHeight(String str, float f) {
            return this.mDefaultAssets.convertHeight(str, f);
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public float convertWidth(String str, float f) {
            return this.mDefaultAssets.convertWidth(str, f);
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public int getDefaultColor(boolean z) {
            return this.mDefaultAssets.getDefaultColor(z);
        }

        public IWebview getIWebview() {
            return this.mWebView;
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public String getOnClickCallBackId() {
            return this.mCallBackId;
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public float getScale() {
            return this.mNativeView.mCreateScale;
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public boolean isClick() {
            return this.isClick;
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public void loadResource(ImgDomElement.AsycLoader asycLoader) {
            this.mDefaultAssets.loadResource(asycLoader);
        }

        @Override // io.dcloud.feature.nativeObj.INativeViewChildView
        public View obtainMainView() {
            return this.mMainView;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
            int i3 = layoutParams.width;
            if (i3 != -2 && i3 != -1) {
                i = View.MeasureSpec.makeMeasureSpec(Math.min(this.mNativeView.mInnerWidth, i3), View.MeasureSpec.getMode(i));
            }
            int i4 = layoutParams.height;
            if (i4 != -2 && i4 != -1) {
                i2 = View.MeasureSpec.makeMeasureSpec(Math.min(this.mNativeViewHeight, i4), View.MeasureSpec.getMode(i2));
            }
            super.onMeasure(i, i2);
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public void setClick(boolean z) {
            this.isClick = z;
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public void setOnClickCallBackId(String str) {
            this.mCallBackId = str;
        }

        @Override // io.dcloud.feature.nativeObj.richtext.IAssets
        public int stringToColor(String str) {
            return this.mDefaultAssets.stringToColor(str);
        }

        @Override // io.dcloud.feature.nativeObj.INativeViewChildView
        public void updateLayout() {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new FrameLayout.LayoutParams(this.mNativeView.mInnerWidth, this.mNativeViewHeight);
            }
            NativeView nativeView = this.mNativeView;
            layoutParams.topMargin = nativeView.mInnerTop + (nativeView.isStatusBar() ? DeviceInfo.sStatusBarHeight : 0);
            NativeView nativeView2 = this.mNativeView;
            layoutParams.leftMargin = nativeView2.mInnerLeft;
            layoutParams.width = nativeView2.mInnerWidth;
            int i = nativeView2.mInnerHeight;
            this.mNativeViewHeight = i;
            if (i == 0 && TextUtils.equals("wrap_content", nativeView2.mStyle.optString("height"))) {
                this.mNativeViewHeight = -2;
            }
            layoutParams.height = this.mNativeViewHeight;
            setLayoutParams(layoutParams);
        }
    }

    public static RichTextLayoutHolder makeRichText(Context context, IWebview iWebview, NativeView nativeView, String str, JSONObject jSONObject, JSONObject jSONObject2, String str2) {
        return makeRichText(new RichTextLayoutHolder(context, iWebview, nativeView, str2), str, jSONObject, jSONObject2);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static io.dcloud.feature.nativeObj.richtext.RichTextLayout.RichTextLayoutHolder makeRichText(io.dcloud.feature.nativeObj.richtext.RichTextLayout.RichTextLayoutHolder r19, java.lang.String r20, org.json.JSONObject r21, org.json.JSONObject r22) {
        /*
            Method dump skipped, instruction units count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.nativeObj.richtext.RichTextLayout.makeRichText(io.dcloud.feature.nativeObj.richtext.RichTextLayout$RichTextLayoutHolder, java.lang.String, org.json.JSONObject, org.json.JSONObject):io.dcloud.feature.nativeObj.richtext.RichTextLayout$RichTextLayoutHolder");
    }

    public static TextView makeRichText(Object[] objArr) {
        IWebview iWebviewObtainWebView = ((IFrameView) objArr[0]).obtainWebView();
        String str = (String) objArr[1];
        JSONObject jSONObject = (JSONObject) objArr[2];
        TextView textView = new TextView(iWebviewObtainWebView.getContext());
        RichTextParser.updateFromHTML(new DefaultAssets(iWebviewObtainWebView) { // from class: io.dcloud.feature.nativeObj.richtext.RichTextLayout.1
            @Override // io.dcloud.feature.nativeObj.richtext.RichTextLayout.DefaultAssets, io.dcloud.feature.nativeObj.richtext.IAssets
            public int getDefaultColor(boolean z) {
                return z ? -16776961 : -1;
            }
        }, iWebviewObtainWebView, textView, str, jSONObject, (ICallBack) objArr[3]);
        return textView;
    }
}
