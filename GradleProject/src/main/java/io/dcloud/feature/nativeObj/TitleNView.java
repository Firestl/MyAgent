package io.dcloud.feature.nativeObj;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.dcloud.android.widget.CapsuleLayout;
import com.taobao.weex.appfram.pickers.WXPickersModule;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.layout.measurefunc.TextContentBoxMeasurement;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.ITitleNView;
import io.dcloud.common.DHInterface.IWebAppRootView;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.IWebviewStateListener;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.UniMPConfig;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TitleNViewUtil;
import io.dcloud.feature.internal.sdk.SDK;
import io.dcloud.feature.nativeObj.NativeView;
import io.dcloud.feature.nativeObj.data.ButtonDataItem;
import io.dcloud.feature.ui.nativeui.a;
import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.droidsonroids.gif.GifDrawable;
import supwisdom.av;

/* JADX INFO: loaded from: classes3.dex */
public class TitleNView extends NativeView implements ITitleNView {
    public final String CLOSE;
    public final String MENU;
    public LinearLayout centerSearchLayout;
    public TextView closeBt;
    public AtomicBoolean isSetText;
    public BadgeLinearLayout mBackButton;
    public BackGroundDrawable mBackGroundDrawable;
    public ArrayList<Object> mButtons;
    public CapsuleLayout mCapsuleLayout;
    public TextView mHomeButton;
    public IWebviewStateListenerImpl mIWebviewStateListenerImpl;
    public LinearLayout mLeftButtonLayout;
    public String mMenuButtonFontSize;
    public String mMenuButtonFontWeight;
    public int mMenuButtonTextColor;
    public ArrayList<ButtonDataItem> mMenuButtons;
    public Progress mProgress;
    public LinearLayout mRightButtonLayout;
    public View mSplitLine;
    public RelativeLayout mTitleNViewLayout;
    public TextView mTitleView;
    public RelativeLayout mTitlelayout;
    public int maxButton;
    public TextView menuBt;
    public int redDotColor;
    public EditText searchInput;

    public class BackGroundDrawable extends Drawable {
        public Rect bound;
        public Paint colorPaint;
        public Paint mPaint;
        public int shadow5PX;
        public Paint shadowPaint;
        public Shader mBackgroundBitmap = null;
        public String bitmapPath = null;
        public String repeatType = "no-repeat";
        public int mBackgroundColor = 0;
        public String shadowColor = "";
        public int shadowColorInt = 0;
        public int height = 0;
        public int offset = 0;

        public BackGroundDrawable() {
            this.shadow5PX = PdrUtil.convertToScreenInt("10px", TitleNView.this.mInnerWidth, 0, TitleNView.this.mCreateScale);
        }

        private Shader getShader(List<String> list, float f, float f2) {
            float[] gradientDirection = parseGradientDirection(list.get(0).trim(), f, f2);
            if (gradientDirection == null) {
                return null;
            }
            return new LinearGradient(gradientDirection[0], gradientDirection[1], gradientDirection[2], gradientDirection[3], PdrUtil.stringToColor(list.get(1).trim()), PdrUtil.stringToColor(list.get(2).trim()), Shader.TileMode.CLAMP);
        }

        private Paint getShadowPaint() {
            if (this.shadowPaint == null) {
                this.shadowPaint = new Paint();
            }
            return this.shadowPaint;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        private float[] parseGradientDirection(String str, float f, float f2) {
            float[] fArr = {0.0f, 0.0f, 0.0f, 0.0f};
            if (!TextUtils.isEmpty(str)) {
                str = str.replaceAll("\\s*", "").toLowerCase();
            }
            str.hashCode();
            byte b = -1;
            switch (str.hashCode()) {
                case -1352032154:
                    if (str.equals("tobottom")) {
                        b = 0;
                    }
                    break;
                case -1137407871:
                    if (str.equals("toright")) {
                        b = 1;
                    }
                    break;
                case -868157182:
                    if (str.equals("toleft")) {
                        b = 2;
                    }
                    break;
                case -172068863:
                    if (str.equals("totopleft")) {
                        b = 3;
                    }
                    break;
                case 110550266:
                    if (str.equals("totop")) {
                        b = 4;
                    }
                    break;
                case 1176531318:
                    if (str.equals("tobottomright")) {
                        b = 5;
                    }
                    break;
            }
            if (b == 0) {
                fArr[3] = f2;
            } else if (b == 1) {
                fArr[2] = f;
            } else if (b == 2) {
                fArr[0] = f;
            } else if (b == 3) {
                fArr[0] = f;
                fArr[1] = f2;
            } else if (b == 4) {
                fArr[1] = f2;
            } else {
                if (b != 5) {
                    return null;
                }
                fArr[2] = f;
                fArr[3] = f2;
            }
            return fArr;
        }

        private List<String> parseGradientValues(String str) {
            String strNextToken;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            str.trim();
            try {
                if (str.startsWith("linear-gradient")) {
                    StringTokenizer stringTokenizer = new StringTokenizer(str.substring(str.indexOf("(") + 1, str.lastIndexOf(")")), ",");
                    ArrayList arrayList = new ArrayList();
                    while (true) {
                        String str2 = null;
                        while (stringTokenizer.hasMoreTokens()) {
                            strNextToken = stringTokenizer.nextToken();
                            if (strNextToken.contains("(")) {
                                str2 = strNextToken + ",";
                            } else {
                                if (strNextToken.contains(")")) {
                                    break;
                                }
                                if (str2 != null) {
                                    str2 = str2 + strNextToken + ",";
                                } else {
                                    arrayList.add(strNextToken);
                                }
                            }
                        }
                        return arrayList;
                        arrayList.add(str2 + strNextToken);
                    }
                }
            } catch (Exception unused) {
            }
            return null;
        }

        private Bitmap scaleBitmap(Bitmap bitmap, int i, int i2) {
            if (bitmap == null) {
                return null;
            }
            if (this.repeatType.equals("repeat")) {
                return bitmap;
            }
            int height = bitmap.getHeight();
            int width = bitmap.getWidth();
            float f = i / width;
            float f2 = i2 / height;
            Matrix matrix = new Matrix();
            if (this.repeatType.equals("repeat-x")) {
                matrix.preScale(1.0f, f2);
            } else if (this.repeatType.equals("repeat-y")) {
                matrix.preScale(f, 1.0f);
            } else {
                matrix.preScale(f, f2);
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
            return bitmapCreateBitmap;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            updatebound();
            if (this.mBackgroundBitmap != null) {
                getPaint().setShader(this.mBackgroundBitmap);
                getBackgroundColorPaint().setColor(Color.argb(getBackgroundColorPaint().getAlpha(), 255, 255, 255));
                canvas.drawRect(this.bound, getBackgroundColorPaint());
            } else {
                String str = this.bitmapPath;
                if (str != null) {
                    setBackgroundImage(str);
                    this.bitmapPath = null;
                    getPaint().setShader(this.mBackgroundBitmap);
                    getBackgroundColorPaint().setColor(Color.argb(getBackgroundColorPaint().getAlpha(), 255, 255, 255));
                    canvas.drawRect(this.bound, getBackgroundColorPaint());
                } else {
                    getPaint().setColor(this.mBackgroundColor);
                }
            }
            canvas.drawRect(this.bound, getPaint());
            if (!PdrUtil.isEmpty(this.shadowColor)) {
                Rect rect = this.bound;
                int i = rect.left;
                int i2 = rect.bottom;
                int i3 = rect.right;
                int i4 = this.height;
                if (i4 == 0) {
                    i4 = this.shadow5PX;
                }
                Rect rect2 = new Rect(i, i2, i3, i4 + i2 + this.offset);
                Paint shadowPaint = getShadowPaint();
                int i5 = rect2.top;
                int i6 = this.offset;
                shadowPaint.setShader(new LinearGradient(0.0f, i5 - i6, 0.0f, rect2.bottom - i6, this.shadowColorInt, 0, Shader.TileMode.CLAMP));
                canvas.drawRect(rect2, getShadowPaint());
            }
            if (this.mBackgroundBitmap != null) {
                getPaint().setShader(null);
            }
        }

        public Paint getBackgroundColorPaint() {
            if (this.colorPaint == null) {
                this.colorPaint = new Paint();
            }
            return this.colorPaint;
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return 0;
        }

        public Paint getPaint() {
            if (this.mPaint == null) {
                this.mPaint = new Paint(1);
            }
            return this.mPaint;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            if (this.mBackgroundBitmap == null && this.bitmapPath == null) {
                return;
            }
            getPaint().setAlpha(i);
            getBackgroundColorPaint().setAlpha(i);
        }

        public void setBackgroundColor(int i) {
            if (this.mBackgroundBitmap != null) {
                setAlpha(Color.alpha(i));
            }
            this.mBackgroundColor = i;
            getShadowPaint().setAlpha(Color.alpha(i));
            invalidateSelf();
        }

        public void setBackgroundImage(String str) {
            Bitmap bitmapDecodeStream;
            Bitmap bitmapScaleBitmap;
            Rect rect = this.bound;
            if (rect == null || rect.width() == 0) {
                this.bitmapPath = str;
                return;
            }
            if (str == null) {
                return;
            }
            List<String> gradientValues = parseGradientValues(str);
            if (gradientValues == null || gradientValues.size() != 3) {
                String strConvert2AbsFullPath = TitleNView.this.mWebView.obtainApp().convert2AbsFullPath(TitleNView.this.mWebView.obtainFullUrl(), str);
                if (PdrUtil.isDeviceRootDir(strConvert2AbsFullPath)) {
                    bitmapDecodeStream = BitmapFactory.decodeFile(strConvert2AbsFullPath);
                } else {
                    try {
                        bitmapDecodeStream = BitmapFactory.decodeStream(TitleNView.this.mWebView.getContext().getAssets().open(strConvert2AbsFullPath));
                    } catch (Exception unused) {
                        bitmapDecodeStream = null;
                    }
                }
                if (bitmapDecodeStream == null) {
                    this.mBackgroundBitmap = null;
                    this.bitmapPath = null;
                    invalidateSelf();
                    return;
                }
                byte[] ninePatchChunk = bitmapDecodeStream.getNinePatchChunk();
                if (ninePatchChunk == null || !NinePatch.isNinePatchChunk(ninePatchChunk)) {
                    bitmapScaleBitmap = scaleBitmap(bitmapDecodeStream, this.bound.width(), this.bound.height());
                } else {
                    NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(bitmapDecodeStream, ninePatchChunk, new Rect(), null);
                    bitmapScaleBitmap = Bitmap.createBitmap(this.bound.width(), this.bound.height(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmapScaleBitmap);
                    ninePatchDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                    ninePatchDrawable.draw(canvas);
                }
                if (bitmapScaleBitmap == null) {
                    this.mBackgroundBitmap = null;
                } else {
                    Shader.TileMode tileMode = Shader.TileMode.REPEAT;
                    this.mBackgroundBitmap = new BitmapShader(bitmapScaleBitmap, tileMode, tileMode);
                }
            } else {
                this.mBackgroundBitmap = getShader(gradientValues, this.bound.width(), this.bound.height());
            }
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
        }

        public void setRepeatType(String str, String str2) {
            if (PdrUtil.isEmpty(str) || this.repeatType.equals(str)) {
                return;
            }
            this.repeatType = str;
            this.mBackgroundBitmap = null;
            updatebound();
            setBackgroundImage(str2);
        }

        public void setShadowColor(JSONObject jSONObject) {
            String strOptString = jSONObject.has("color") ? jSONObject.optString("color") : "";
            String strOptString2 = jSONObject.has("height") ? jSONObject.optString("height") : "10px";
            String strOptString3 = jSONObject.has("offset") ? jSONObject.optString("offset") : "0px";
            TitleNView titleNView = TitleNView.this;
            this.height = PdrUtil.convertToScreenInt(strOptString2, titleNView.mInnerWidth, 0, titleNView.mCreateScale);
            TitleNView titleNView2 = TitleNView.this;
            this.offset = PdrUtil.convertToScreenInt(strOptString3, titleNView2.mInnerWidth, 0, titleNView2.mCreateScale);
            if (strOptString.equals(this.shadowColor)) {
                return;
            }
            this.shadowColor = strOptString;
            this.shadowColorInt = PdrUtil.stringToColor(strOptString);
            invalidateSelf();
        }

        public void updatebound() {
            View view;
            if (this.bound == null) {
                this.bound = new Rect();
            }
            TitleNView titleNView = TitleNView.this;
            if (titleNView.isImmersed && titleNView.isStatusBarHas()) {
                this.bound.top = DeviceInfo.sStatusBarHeight;
            } else {
                this.bound.top = 0;
            }
            TitleNView titleNView2 = TitleNView.this;
            if (titleNView2.isStatusBar && (view = titleNView2.mStatusbarView) != null && view.getVisibility() == 0) {
                this.bound.top = DeviceInfo.sStatusBarHeight;
            }
            Rect rect = this.bound;
            TitleNView titleNView3 = TitleNView.this;
            int i = titleNView3.mInnerHeight;
            rect.bottom = rect.top + i;
            if (titleNView3.isStatusBar && titleNView3.isImmersed) {
                rect.bottom = i + DeviceInfo.sStatusBarHeight;
            }
            rect.left = 0;
            rect.right = getBounds().right;
        }
    }

    public static class IWebviewStateListenerImpl implements IWebviewStateListener {
        public SoftReference<Progress> mProgress;

        public IWebviewStateListenerImpl(Progress progress) {
            this.mProgress = new SoftReference<>(progress);
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (this.mProgress.get() == null || this.mProgress.get().getParent() == null || this.mProgress.get().getVisibility() != 0) {
                return null;
            }
            if (i == 3) {
                this.mProgress.get().updateProgress(((Integer) obj).intValue());
                return null;
            }
            if ((i != 1 && i != 5) || this.mProgress.get() == null || this.mProgress.get().isFinish()) {
                return null;
            }
            this.mProgress.get().finishProgress();
            return null;
        }
    }

    public static class Progress extends av {
        public Progress(Context context) {
            super(context);
        }
    }

    public class SearchInputDrawable extends Drawable {
        public Paint mPaint;
        public int radius;

        public SearchInputDrawable(int i, int i2) {
            Paint paint = new Paint();
            this.mPaint = paint;
            paint.setAntiAlias(true);
            this.mPaint.setColor(i);
            this.radius = i2;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            RectF rectF = new RectF(getBounds());
            float f = this.radius;
            canvas.drawRoundRect(rectF, f, f, this.mPaint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getAlpha() {
            return this.mPaint.getAlpha();
        }

        public int getDrawableColor() {
            Paint paint = this.mPaint;
            if (paint != null) {
                return paint.getColor();
            }
            return 0;
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.mPaint.setAlpha(i);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.mPaint.setColorFilter(colorFilter);
            invalidateSelf();
        }

        public void setDrawableColor(int i) {
            Paint paint = this.mPaint;
            if (paint != null) {
                paint.setColor(i);
            }
            invalidateSelf();
        }
    }

    public TitleNView(Context context, IWebview iWebview, String str, String str2, JSONObject jSONObject) {
        super(context, iWebview, str, str2, jSONObject);
        this.mTitleView = null;
        this.mBackButton = null;
        this.mHomeButton = null;
        this.mTitleNViewLayout = null;
        this.mLeftButtonLayout = null;
        this.mRightButtonLayout = null;
        this.centerSearchLayout = null;
        this.mButtons = new ArrayList<>();
        this.mMenuButtonTextColor = -16777216;
        this.mMenuButtonFontSize = "22px";
        this.mMenuButtonFontWeight = "normal";
        this.maxButton = 2;
        this.isSetText = new AtomicBoolean(false);
        this.menuBt = null;
        this.closeBt = null;
        this.CLOSE = "close";
        this.MENU = AbsoluteConst.EVENTS_MENU;
        setTag("titleNView");
        if (SDK.isUniMPSDK()) {
            initCapsuleLayout();
        }
    }

    private void addButtonOnClickListener(final String str, final IWebview iWebview, View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: io.dcloud.feature.nativeObj.TitleNView.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                TitleNView.this.buttonOnclick(str, iWebview);
            }
        });
    }

    private TextView addSelect(ViewGroup viewGroup, TextView textView, int i) {
        TextView textView2 = new TextView(getContext());
        textView2.setText("\ue661");
        textView2.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/dcloud_iconfont.ttf"));
        textView2.setSingleLine();
        textView2.setLines(1);
        textView2.setGravity(17);
        textView2.setIncludeFontPadding(false);
        textView2.getPaint().setTextSize(PdrUtil.convertToScreenInt("15px", this.mInnerWidth, i, this.mCreateScale));
        viewGroup.addView(textView2, new LinearLayout.LayoutParams(-2, -1));
        return textView2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void buttonOnclick(String str, IWebview iWebview) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.toLowerCase().startsWith(AbsoluteConst.PROTOCOL_JAVASCRIPT)) {
            IFrameView iFrameView = this.mFrameViewParent;
            if (iFrameView != null && iFrameView.obtainWindowMgr() != null && this.mFrameViewParent.obtainApp() != null) {
                Object objProcessEvent = this.mFrameViewParent.obtainWindowMgr().processEvent(IMgr.MgrType.WindowMgr, 47, this.mFrameViewParent.obtainApp().obtainAppId());
                if (objProcessEvent instanceof IFrameView) {
                    IFrameView iFrameView2 = (IFrameView) objProcessEvent;
                    if (iFrameView2.obtainWebView() != null && iFrameView2.obtainWebView().obtainWindowView() != null) {
                        iFrameView2.obtainWebView().loadUrl(str);
                        return;
                    }
                }
            }
            IWebview iWebview2 = this.mWebView;
            if (iWebview2 != null && iWebview2.obtainWindowView() != null) {
                this.mWebView.loadUrl(str);
                return;
            } else if (iWebview != null && iWebview.obtainWindowView() != null) {
                iWebview.loadUrl(str);
                return;
            }
        }
        if (iWebview != null) {
            Deprecated_JSUtil.execCallback(iWebview, str, "", JSUtil.OK, false, true);
            if (iWebview.getOpener() != null) {
                Deprecated_JSUtil.execCallback(iWebview.getOpener(), str, "", JSUtil.OK, false, true);
            }
        }
    }

    private void caculateTitleMargin() {
        RelativeLayout relativeLayout = this.mTitlelayout;
        if (relativeLayout != null && relativeLayout.getVisibility() == 0) {
            int iConvertToScreenInt = PdrUtil.convertToScreenInt("10px", this.mInnerWidth, 0, this.mCreateScale);
            initLeftButtonLayout();
            initRightButtonLayout();
            int width = this.mLeftButtonLayout.getWidth();
            int width2 = this.mRightButtonLayout.getWidth();
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mTitlelayout.getLayoutParams();
            int i = iConvertToScreenInt / 2;
            int i2 = this.mInnerHeight - i;
            TextView textView = this.mTitleView;
            int iMin = ((int) Math.min(((this.mTitleNViewLayout.getWidth() - i2) - (textView != null ? textView.getPaint().measureText("张磊 ") : 0.0f)) / 2.0f, Math.max(width, width2))) + i;
            if (layoutParams.rightMargin == iMin) {
                return;
            }
            int[] rules = layoutParams.getRules();
            if (rules == null || rules.length <= 0 || rules[1] <= 0) {
                layoutParams.rightMargin = iMin;
                layoutParams.leftMargin = iMin;
            } else {
                layoutParams.rightMargin = iMin;
            }
            this.mTitlelayout.setLayoutParams(layoutParams);
            this.mTitlelayout.bringToFront();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void capsuleButtonClick(String str) {
        IWebview iWebview = this.mWebView;
        if (iWebview == null || iWebview.obtainFrameView() == null) {
            return;
        }
        str.hashCode();
        if (str.equals(AbsoluteConst.EVENTS_MENU)) {
            Bundle bundle = new Bundle();
            bundle.putString("appid", this.mWebView.obtainApp().obtainAppId());
            bundle.putString("type", AbsoluteConst.EVENTS_MENU);
            IWebview iWebview2 = this.mWebView;
            iWebview2.obtainFrameView().obtainWindowMgr().processEvent(IMgr.MgrType.WindowMgr, 80, new Object[]{iWebview2, bundle});
            if (UniMPConfig.isCapsuleMenuIntercept) {
                return;
            }
            showCapsuleMenu(getMenuArray());
            return;
        }
        if (!str.equals("close")) {
            throw new IllegalStateException("Unexpected value: " + str);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("appid", this.mWebView.obtainApp().obtainAppId());
        bundle2.putString("type", "close");
        IWebview iWebview3 = this.mWebView;
        Object[] objArr = {iWebview3, bundle2};
        AbsMgr absMgrObtainWindowMgr = iWebview3.obtainFrameView().obtainWindowMgr();
        IMgr.MgrType mgrType = IMgr.MgrType.WindowMgr;
        absMgrObtainWindowMgr.processEvent(mgrType, 80, objArr);
        if (UniMPConfig.isCapsuleCloseIntercept) {
            return;
        }
        this.mWebView.obtainFrameView().obtainWindowMgr().processEvent(mgrType, 20, this.mWebView.obtainApp().obtainAppId());
    }

    private View createButton(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, IWebview iWebview, String str9, String str10, boolean z, String str11, boolean z2, String str12, String str13) {
        int iStringToColor;
        int iStringToColor2;
        int iStringToColor3;
        int iConvertToScreenInt;
        BadgeRelateiveLayout badgeRelateiveLayout = new BadgeRelateiveLayout(this, getContext(), this.mCreateScale, this.redDotColor);
        badgeRelateiveLayout.setOrientation(0);
        badgeRelateiveLayout.setGravity(17);
        badgeRelateiveLayout.setPadding(0, 0, 0, 0);
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        StringBuilder sb = new StringBuilder();
        sb.append("TitleNView.Button.");
        sb.append(str == null ? "" : str);
        textView.setTag(sb.toString());
        textView.setId(View.generateViewId());
        textView.setSingleLine();
        textView.setLines(1);
        textView.setIncludeFontPadding(false);
        if (!PdrUtil.isEmpty(str12) && (iConvertToScreenInt = PdrUtil.convertToScreenInt(str12, this.mAppScreenWidth, 0, this.mCreateScale)) > 0) {
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setMaxWidth(iConvertToScreenInt);
        }
        try {
            iStringToColor = Color.parseColor(str2);
        } catch (Exception unused) {
            iStringToColor = PdrUtil.stringToColor(str2);
        }
        int i = iStringToColor;
        try {
            iStringToColor2 = Color.parseColor(str3);
        } catch (Exception unused2) {
            iStringToColor2 = PdrUtil.stringToColor(str3);
        }
        int i2 = iStringToColor2;
        textView.setTextColor(createColorStateList(i, i2));
        if (Constants.Value.BOLD.equals(str4)) {
            textView.getPaint().setFakeBoldText(true);
        }
        setTextAndFont(str, str6, str7, textView, TextUtils.isEmpty(str7));
        int iConvertToScreenInt2 = PdrUtil.convertToScreenInt(TextUtils.isEmpty(str5) ? "transparent".equals(str9) ? "22px" : "27px" : "17px", this.mInnerWidth, 0, this.mCreateScale);
        textView.getPaint().setTextSize(PdrUtil.convertToScreenInt(str5, this.mInnerWidth, iConvertToScreenInt2, this.mCreateScale));
        try {
            iStringToColor3 = Color.parseColor(str10);
        } catch (Exception unused3) {
            iStringToColor3 = PdrUtil.stringToColor(str10);
        }
        NativeViewBackButtonDrawable nativeViewBackButtonDrawable = new NativeViewBackButtonDrawable("transparent".equals(str9) ? iStringToColor3 : 0);
        nativeViewBackButtonDrawable.setWidth(str13);
        badgeRelateiveLayout.addView(textView, new LinearLayout.LayoutParams(-2, -1, 1.0f));
        if (z2) {
            addSelect(badgeRelateiveLayout, textView, iConvertToScreenInt2).setTextColor(createColorStateList(i, i2));
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }
        badgeRelateiveLayout.setBackground(nativeViewBackButtonDrawable);
        addButtonOnClickListener(str8, iWebview, badgeRelateiveLayout);
        badgeRelateiveLayout.setBadgeStr(str11);
        badgeRelateiveLayout.setDrawRedDot(z);
        this.mButtons.add(badgeRelateiveLayout);
        return badgeRelateiveLayout;
    }

    private ColorStateList createColorStateList(int i, int i2) {
        return new ColorStateList(new int[][]{new int[]{R.attr.state_pressed, R.attr.state_enabled}, new int[]{R.attr.state_enabled, R.attr.state_focused}, new int[]{R.attr.state_enabled}, new int[]{R.attr.state_focused}, new int[]{R.attr.state_window_focused}, new int[0]}, new int[]{i2, i, i, i, i, i});
    }

    private TextView getCapsuleButton(String str) {
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setId(View.generateViewId());
        textView.setSingleLine();
        textView.setLines(1);
        textView.setIncludeFontPadding(false);
        textView.getPaint().setFakeBoldText(true);
        textView.getPaint().setTextSize(PdrUtil.convertToScreenInt("19px", this.mInnerWidth, 0, this.mCreateScale));
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(getContext().getAssets(), "fonts/dcloud_iconfont.ttf");
        if (typefaceCreateFromAsset != null) {
            textView.setTypeface(typefaceCreateFromAsset);
        }
        return textView;
    }

    private String getIconPath(String str) {
        if (PdrUtil.isNetPath(str)) {
            return str;
        }
        String strConvert2AbsFullPath = this.mApp.convert2AbsFullPath(this.mWebView.obtainFullUrl(), str);
        if (strConvert2AbsFullPath != null && PdrUtil.isDeviceRootDir(strConvert2AbsFullPath)) {
            return "file://" + strConvert2AbsFullPath;
        }
        if (strConvert2AbsFullPath != null && strConvert2AbsFullPath.startsWith("/") && strConvert2AbsFullPath.length() > 1) {
            strConvert2AbsFullPath = strConvert2AbsFullPath.substring(1);
        }
        return "file:///android_asset/" + strConvert2AbsFullPath;
    }

    private JSONArray getMenuArray() {
        JSONObject jSONObject;
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < this.mMenuButtons.size(); i++) {
            ButtonDataItem buttonDataItem = this.mMenuButtons.get(i);
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("title", TextUtils.isEmpty(buttonDataItem.getTitle()) ? buttonDataItem.getText() : buttonDataItem.getTitle());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            jSONArray.put(jSONObject2);
        }
        if (this.mMenuButtons.size() > 0) {
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("title", "");
                jSONObject3.put("type", "interval");
                jSONArray.put(jSONObject3);
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        if (SDK.sDefaultMenuButton != null) {
            try {
                JSONObject jSONObject4 = new JSONObject(SDK.sDefaultMenuButton);
                if (jSONObject4.has(AbsoluteConst.EVENTS_MENU) && (jSONObject = jSONObject4.getJSONObject(AbsoluteConst.EVENTS_MENU)) != null && jSONObject.has("buttons")) {
                    JSONArray jSONArray2 = jSONObject.getJSONArray("buttons");
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        jSONArray.put(jSONArray2.getJSONObject(i2));
                    }
                }
            } catch (JSONException e4) {
                e4.printStackTrace();
            }
        }
        return jSONArray;
    }

    private void initCapsuleLayout() {
        JSONObject jSONObject;
        if (this.mCapsuleLayout == null && SDK.isCapsule) {
            this.mMenuButtons = new ArrayList<>();
            int iConvertToScreenInt = this.mInnerHeight - PdrUtil.convertToScreenInt("12px", this.mAppScreenWidth, 0, this.mCreateScale);
            CapsuleLayout capsuleLayout = new CapsuleLayout(getContext(), iConvertToScreenInt / 2);
            this.mCapsuleLayout = capsuleLayout;
            capsuleLayout.setId(View.generateViewId());
            this.mCapsuleLayout.setGravity(16);
            this.mCapsuleLayout.setOrientation(0);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, iConvertToScreenInt);
            layoutParams.addRule(15);
            layoutParams.addRule(11);
            float f = this.mCreateScale;
            int i = (int) (13.0f * f);
            int i2 = (int) (f * 10.0f);
            layoutParams.rightMargin = i;
            initTitleNViewLayout();
            this.mTitleNViewLayout.addView(this.mCapsuleLayout, layoutParams);
            if (this.menuBt == null) {
                TextView capsuleButton = getCapsuleButton(AbsoluteConst.EVENTS_MENU);
                this.menuBt = capsuleButton;
                capsuleButton.setText("\ue606");
            }
            this.menuBt.setTextColor(-16777216);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.leftMargin = i;
            layoutParams2.gravity = 17;
            layoutParams2.rightMargin = i2;
            this.mCapsuleLayout.a(this.menuBt, layoutParams2, CapsuleLayout.ButtonType.LIFT, new View.OnClickListener() { // from class: io.dcloud.feature.nativeObj.TitleNView.12
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TitleNView.this.capsuleButtonClick(AbsoluteConst.EVENTS_MENU);
                }
            });
            this.mCapsuleLayout.a(this.mCreateScale);
            if (this.closeBt == null) {
                TextView capsuleButton2 = getCapsuleButton("close");
                this.closeBt = capsuleButton2;
                capsuleButton2.setText("\ue650");
            }
            this.closeBt.setTextColor(-16777216);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams3.rightMargin = i;
            layoutParams3.gravity = 17;
            layoutParams3.leftMargin = i2;
            this.mCapsuleLayout.a(this.closeBt, layoutParams3, CapsuleLayout.ButtonType.RIGHT, new View.OnClickListener() { // from class: io.dcloud.feature.nativeObj.TitleNView.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TitleNView.this.capsuleButtonClick("close");
                }
            });
            if (TextUtils.isEmpty(SDK.sDefaultMenuButton)) {
                return;
            }
            try {
                JSONObject jSONObject2 = new JSONObject(SDK.sDefaultMenuButton);
                if (jSONObject2.has(AbsoluteConst.EVENTS_MENU) && (jSONObject = jSONObject2.getJSONObject(AbsoluteConst.EVENTS_MENU)) != null) {
                    if (jSONObject.has(WXPickersModule.KEY_TEXT_COLOR)) {
                        this.mMenuButtonTextColor = PdrUtil.stringToColor(jSONObject.getString(WXPickersModule.KEY_TEXT_COLOR));
                    }
                    if (jSONObject.has(Constants.Name.FONT_SIZE) && !TextUtils.isEmpty(jSONObject.getString(Constants.Name.FONT_SIZE))) {
                        this.mMenuButtonFontSize = jSONObject.getString(Constants.Name.FONT_SIZE);
                    }
                    if (jSONObject.has(Constants.Name.FONT_WEIGHT)) {
                        if (Constants.Value.BOLD.equals(jSONObject.getString(Constants.Name.FONT_WEIGHT))) {
                            this.mMenuButtonFontWeight = Constants.Value.BOLD;
                        } else {
                            this.mMenuButtonFontWeight = "normal";
                        }
                    }
                }
                if (jSONObject2.has("capsuleButtonStyle")) {
                    setCapsuleButtonStyle(jSONObject2.getJSONObject("capsuleButtonStyle"));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void initCenterSearchLayout() {
        if (this.centerSearchLayout == null) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            this.centerSearchLayout = linearLayout;
            linearLayout.setId(View.generateViewId());
            this.centerSearchLayout.setGravity(16);
            this.centerSearchLayout.setOrientation(0);
            this.centerSearchLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, this.mInnerHeight - PdrUtil.convertToScreenInt("12px", this.mAppScreenWidth, 0, this.mCreateScale));
            LinearLayout linearLayout2 = this.mRightButtonLayout;
            if (linearLayout2 != null) {
                layoutParams.addRule(0, linearLayout2.getId());
            } else {
                layoutParams.addRule(11);
            }
            LinearLayout linearLayout3 = this.mLeftButtonLayout;
            if (linearLayout3 != null) {
                layoutParams.addRule(1, linearLayout3.getId());
            } else {
                layoutParams.addRule(9);
            }
            layoutParams.addRule(15);
            layoutParams.rightMargin = PdrUtil.convertToScreenInt("5px", this.mAppScreenWidth, 0, this.mCreateScale);
            layoutParams.leftMargin = PdrUtil.convertToScreenInt("5px", this.mAppScreenWidth, 0, this.mCreateScale);
            this.mTitleNViewLayout.addView(this.centerSearchLayout, layoutParams);
        }
    }

    private void initLeftButtonLayout() {
        if (this.mLeftButtonLayout == null) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            this.mLeftButtonLayout = linearLayout;
            linearLayout.setId(View.generateViewId());
            this.mLeftButtonLayout.setGravity(16);
            this.mLeftButtonLayout.setOrientation(0);
            this.mLeftButtonLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
            layoutParams.addRule(15);
            layoutParams.addRule(9);
            this.mTitleNViewLayout.addView(this.mLeftButtonLayout, layoutParams);
        }
    }

    private void initRightButtonLayout() {
        if (this.mRightButtonLayout == null) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            this.mRightButtonLayout = linearLayout;
            linearLayout.setId(View.generateViewId());
            this.mRightButtonLayout.setGravity(16);
            this.mRightButtonLayout.setOrientation(0);
            this.mRightButtonLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
            layoutParams.addRule(15);
            CapsuleLayout capsuleLayout = this.mCapsuleLayout;
            if (capsuleLayout != null) {
                layoutParams.addRule(0, capsuleLayout.getId());
            } else {
                layoutParams.addRule(11);
            }
            this.mTitleNViewLayout.addView(this.mRightButtonLayout, layoutParams);
        }
    }

    private void initTitleNViewLayout() {
        boolean z;
        IFrameView iFrameView;
        if (this.mTitleNViewLayout == null) {
            this.mTitleNViewLayout = new RelativeLayout(getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.mInnerHeight);
            ViewParent parent = getParent();
            int i = 0;
            if (!TextUtils.equals(this.mStyle.optString("position", AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE), AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE) && parent != null && (parent instanceof ViewGroup)) {
                int i2 = 0;
                while (true) {
                    ViewGroup viewGroup = (ViewGroup) parent;
                    if (i2 >= viewGroup.getChildCount()) {
                        break;
                    }
                    Object tag = viewGroup.getChildAt(i2).getTag();
                    if (tag != null && "StatusBar".equalsIgnoreCase(tag.toString())) {
                        z = true;
                        break;
                    }
                    i2++;
                }
            } else {
                z = false;
            }
            if (!z && (iFrameView = this.mFrameViewParent) != null && iFrameView.obtainApp().obtainStatusBarMgr().isImmersive) {
                i = 0 + DeviceInfo.sStatusBarHeight;
            }
            layoutParams.topMargin = i;
            addView(this.mTitleNViewLayout, layoutParams);
        }
    }

    public static boolean isBase64Image(String str) {
        return Pattern.compile("^data:image/.*;base64,").matcher(str).find();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isStatusBarHas() {
        IWebview iWebview = this.mWebView;
        if (iWebview == null || iWebview.obtainFrameView() == null) {
            return false;
        }
        return ((AdaFrameItem) this.mWebView.obtainFrameView()).obtainFrameOptions().isStatusbar;
    }

    private void layoutSubtitleIcon(String str, String str2, String str3, String str4, String str5, String str6, ImageView imageView, TextView textView, int i, String str7, String str8) {
        GifDrawable gifDrawable;
        int iConvertToScreenInt = PdrUtil.convertToScreenInt("10px", this.mInnerWidth, 0, this.mCreateScale);
        if (-1 == this.mTitlelayout.indexOfChild(imageView)) {
            this.mTitlelayout.addView(imageView);
        }
        if (PdrUtil.isEmpty(str)) {
            imageView.setVisibility(8);
        } else {
            int iConvertToScreenInt2 = PdrUtil.convertToScreenInt(str8, this.mInnerWidth, 0, this.mCreateScale);
            if (iConvertToScreenInt2 <= 0) {
                iConvertToScreenInt2 = this.mInnerHeight - iConvertToScreenInt;
            }
            int i2 = this.mInnerHeight;
            if (iConvertToScreenInt2 > i2) {
                iConvertToScreenInt2 = i2;
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iConvertToScreenInt2, iConvertToScreenInt2);
            layoutParams.addRule(9);
            layoutParams.addRule(15);
            layoutParams.rightMargin = iConvertToScreenInt / 2;
            int iConvertToScreenInt3 = PdrUtil.convertToScreenInt(str2, this.mInnerWidth, 0, this.mCreateScale);
            boolean zIsBase64Image = isBase64Image(str);
            if (zIsBase64Image) {
                gifDrawable = new GifDrawable(Base64.decode(str.replaceFirst("^data:image/.*;base64,", ""), 0));
            } else {
                try {
                    gifDrawable = new GifDrawable(getContext().getContentResolver(), Uri.parse(getIconPath(str)));
                } catch (IOException | Exception unused) {
                    gifDrawable = null;
                }
            }
            if (iConvertToScreenInt3 <= 0) {
                RequestManager requestManagerWith = Glide.with(getContext());
                if (gifDrawable != null) {
                    imageView.setImageDrawable(gifDrawable);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                } else {
                    requestManagerWith.load(zIsBase64Image ? Base64.decode(str.replaceFirst("^data:image/.*;base64,", ""), 0) : getIconPath(str)).transform(new CenterCrop()).into(imageView);
                }
            } else {
                RequestManager requestManagerWith2 = Glide.with(getContext());
                if (gifDrawable != null) {
                    gifDrawable.setTransform(new GIFCornerRadiusTransform(iConvertToScreenInt3, iConvertToScreenInt2));
                    imageView.setImageDrawable(gifDrawable);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                } else {
                    requestManagerWith2.load(zIsBase64Image ? Base64.decode(str.replaceFirst("^data:image/.*;base64,", ""), 0) : getIconPath(str)).transform(new MultiTransformation(new CenterCrop(), new RoundedCorners(iConvertToScreenInt3))).into(imageView);
                }
            }
            if (-1 == this.mTitlelayout.indexOfChild(imageView)) {
                this.mTitlelayout.addView(imageView, layoutParams);
            } else {
                imageView.setLayoutParams(layoutParams);
            }
            imageView.setVisibility(0);
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(10);
        if (imageView.getVisibility() == 0 || str7.equals("left") || str7.equals("auto")) {
            layoutParams2.addRule(1, imageView.getId());
        } else {
            layoutParams2.addRule(14);
        }
        if (-1 == this.mTitlelayout.indexOfChild(this.mTitleView)) {
            this.mTitlelayout.addView(this.mTitleView, layoutParams2);
        } else {
            this.mTitleView.setLayoutParams(layoutParams2);
        }
        if (-1 == this.mTitlelayout.indexOfChild(textView)) {
            this.mTitlelayout.addView(textView);
        }
        if (PdrUtil.isEmpty(str3)) {
            textView.setVisibility(8);
        } else {
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams3.addRule(3, this.mTitleView.getId());
            if (imageView.getVisibility() == 0 || str7.equals("left") || str7.equals("auto")) {
                layoutParams3.addRule(1, imageView.getId());
            } else {
                layoutParams3.addRule(14);
            }
            textView.setText(str3);
            if ("clip".equals(str6)) {
                textView.setEllipsize(null);
            } else {
                textView.setEllipsize(TextUtils.TruncateAt.END);
            }
            textView.setSingleLine();
            textView.setLines(1);
            textView.setTextColor(PdrUtil.isEmpty(str4) ? i : PdrUtil.stringToColor(str4));
            textView.getPaint().setTextSize(PdrUtil.convertToScreenInt(PdrUtil.isEmpty(str5) ? "12px" : str5, this.mInnerWidth, 0, this.mCreateScale));
            if (-1 == this.mTitlelayout.indexOfChild(textView)) {
                this.mTitlelayout.addView(textView, layoutParams3);
            } else {
                textView.setLayoutParams(layoutParams3);
            }
            textView.setVisibility(0);
        }
        if (textView.getVisibility() == 0) {
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.mTitleView.getLayoutParams();
            layoutParams4.addRule(10);
            layoutParams4.removeRule(15);
            this.mTitleView.setLayoutParams(layoutParams2);
            return;
        }
        RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.mTitleView.getLayoutParams();
        layoutParams5.removeRule(10);
        layoutParams5.addRule(15);
        this.mTitleView.setLayoutParams(layoutParams2);
    }

    private void setButtonColor(View view, String str, String str2, String str3) {
        int iStringToColor;
        int iStringToColor2;
        int iStringToColor3;
        if (view != null) {
            Drawable background = view.getBackground();
            if (background != null && (background instanceof NativeViewBackButtonDrawable)) {
                try {
                    iStringToColor3 = Color.parseColor(str);
                } catch (Exception unused) {
                    iStringToColor3 = PdrUtil.stringToColor(str);
                }
                NativeViewBackButtonDrawable nativeViewBackButtonDrawable = (NativeViewBackButtonDrawable) background;
                if (iStringToColor3 != nativeViewBackButtonDrawable.getDrawableColor()) {
                    nativeViewBackButtonDrawable.setDrawableColor(iStringToColor3);
                }
            }
            try {
                iStringToColor = Color.parseColor(str2);
            } catch (Exception unused2) {
                iStringToColor = PdrUtil.stringToColor(str2);
            }
            try {
                iStringToColor2 = Color.parseColor(str3);
            } catch (Exception unused3) {
                iStringToColor2 = PdrUtil.stringToColor(str3);
            }
            if ((view instanceof RelativeLayout) || (view instanceof BadgeRelateiveLayout)) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    TextView textView = (TextView) viewGroup.getChildAt(i);
                    if (iStringToColor != textView.getTextColors().getDefaultColor()) {
                        textView.setTextColor(createColorStateList(iStringToColor, iStringToColor2));
                    }
                }
                return;
            }
            if (view instanceof TextView) {
                TextView textView2 = (TextView) view;
                if (iStringToColor != textView2.getTextColors().getDefaultColor()) {
                    textView2.setTextColor(createColorStateList(iStringToColor, iStringToColor2));
                    return;
                }
                return;
            }
            if (view instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) view;
                for (int i2 = 0; i2 < linearLayout.getChildCount(); i2++) {
                    View childAt = linearLayout.getChildAt(i2);
                    if (childAt instanceof TextView) {
                        TextView textView3 = (TextView) childAt;
                        if (iStringToColor != textView3.getTextColors().getDefaultColor()) {
                            textView3.setTextColor(createColorStateList(iStringToColor, iStringToColor2));
                        }
                    } else if (childAt instanceof RelativeLayout) {
                        TextView textView4 = (TextView) ((RelativeLayout) childAt).getChildAt(0);
                        if (iStringToColor != textView4.getTextColors().getDefaultColor()) {
                            textView4.setTextColor(createColorStateList(iStringToColor, iStringToColor2));
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTextAndFont(String str, String str2, String str3, TextView textView, boolean z) {
        Typeface typeface;
        String strConvert2AbsFullPath;
        if (z || str3.equals("none")) {
            textView.setText(str);
            if (this.mApp == null || this.mWebView == null || TextUtils.isEmpty(str2)) {
                typeface = null;
            } else {
                if (str2.contains("__wap2app.ttf")) {
                    strConvert2AbsFullPath = BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/__wap2app.ttf";
                    if (!new File(strConvert2AbsFullPath).exists()) {
                        strConvert2AbsFullPath = this.mWebView.obtainApp().convert2AbsFullPath(this.mWebView.obtainFullUrl(), str2);
                    }
                } else {
                    strConvert2AbsFullPath = this.mWebView.obtainApp().convert2AbsFullPath(this.mWebView.obtainFullUrl(), str2);
                }
                typeface = NativeTypefaceFactory.getTypeface(this.mWebView.obtainApp(), strConvert2AbsFullPath);
            }
        } else {
            typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/dcloud_iconfont.ttf");
            str3.hashCode();
            switch (str3) {
                case "forward":
                    str = "\ue600";
                    break;
                case "back":
                    str = "\ue601";
                    break;
                case "home":
                    str = "\ue605";
                    break;
                case "menu":
                    str = "\ue606";
                    break;
                case "close":
                    str = "\ue650";
                    break;
                case "share":
                    str = "\ue602";
                    break;
                case "favorite":
                    str = "\ue604";
                    break;
            }
            textView.setText(str);
        }
        if (typeface != null) {
            textView.setTypeface(typeface);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setTextGravity(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = 1
            r2 = -1364013995(0xffffffffaeb2cc55, float:-8.1307995E-11)
            if (r0 == r2) goto L29
            r2 = 3317767(0x32a007, float:4.649182E-39)
            if (r0 == r2) goto L1f
            r2 = 108511772(0x677c21c, float:4.6598146E-35)
            if (r0 == r2) goto L15
            goto L33
        L15:
            java.lang.String r0 = "right"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L33
            r4 = 1
            goto L34
        L1f:
            java.lang.String r0 = "left"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L33
            r4 = 0
            goto L34
        L29:
            java.lang.String r0 = "center"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L33
            r4 = 2
            goto L34
        L33:
            r4 = -1
        L34:
            if (r4 == 0) goto L73
            java.lang.String r0 = "\ue660 "
            if (r4 == r1) goto L56
            android.widget.EditText r4 = r3.searchInput
            r1 = 17
            r4.setGravity(r1)
            android.widget.EditText r4 = r3.searchInput
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r4.setHint(r5)
            goto L80
        L56:
            android.widget.EditText r4 = r3.searchInput
            r1 = 8388613(0x800005, float:1.175495E-38)
            r4.setGravity(r1)
            android.widget.EditText r4 = r3.searchInput
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r4.setHint(r5)
            goto L80
        L73:
            android.widget.EditText r4 = r3.searchInput
            r0 = 8388611(0x800003, float:1.1754948E-38)
            r4.setGravity(r0)
            android.widget.EditText r4 = r3.searchInput
            r4.setHint(r5)
        L80:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.nativeObj.TitleNView.setTextGravity(java.lang.String, java.lang.String):void");
    }

    private void showCapsuleMenu(final JSONArray jSONArray) {
        try {
            a aVar = Build.VERSION.SDK_INT >= 21 ? new a(this.mWebView.getActivity()) : new a(this.mWebView.getActivity(), R.style.Theme.Light.NoTitleBar);
            aVar.a(new a.b() { // from class: io.dcloud.feature.nativeObj.TitleNView.14
                @Override // io.dcloud.feature.ui.nativeui.a.b
                public void initCancelText(TextView textView) {
                    TextPaint paint = textView.getPaint();
                    if (TitleNView.this.mMenuButtonFontWeight.equals(Constants.Value.BOLD)) {
                        paint.setFakeBoldText(true);
                    } else {
                        paint.setFakeBoldText(false);
                    }
                }

                @Override // io.dcloud.feature.ui.nativeui.a.b
                public void initTextItem(int i, TextView textView, String str) {
                    if (TitleNView.this.mMenuButtons.size() > i) {
                        ButtonDataItem buttonDataItem = (ButtonDataItem) TitleNView.this.mMenuButtons.get(i);
                        TitleNView.this.setTextAndFont(str, buttonDataItem.getFontSrc(), buttonDataItem.getFontType(), textView, TextUtils.isEmpty(buttonDataItem.getFontType()));
                    } else if (jSONArray.length() > i) {
                        if (jSONArray.optJSONObject(i).optString("type", "").equals("interval")) {
                            textView.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e5e5e5")));
                            textView.getLayoutParams().height = 10;
                            return;
                        }
                        textView.setText(str);
                    }
                    TextPaint paint = textView.getPaint();
                    if (TitleNView.this.mMenuButtonFontWeight.equals(Constants.Value.BOLD)) {
                        paint.setFakeBoldText(true);
                    } else {
                        paint.setFakeBoldText(false);
                    }
                    textView.setTextColor(TitleNView.this.mMenuButtonTextColor);
                }

                @Override // io.dcloud.feature.ui.nativeui.a.b
                public boolean onDismiss(int i) {
                    int i2 = i - 1;
                    return i2 > 0 && jSONArray.length() > i2 && jSONArray.optJSONObject(i2).optString("type", "").equals("interval");
                }

                @Override // io.dcloud.feature.ui.nativeui.a.b
                public void onItemClick(int i) {
                    int i2 = i - 1;
                    if (i2 < 0) {
                        return;
                    }
                    if (TitleNView.this.mMenuButtons.size() <= i2) {
                        if (jSONArray.length() > i2) {
                            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i2);
                            if (jSONObjectOptJSONObject.optString("type", "").equals("interval") || jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("id")) {
                                return;
                            }
                            String strOptString = jSONObjectOptJSONObject.optString("id");
                            Bundle bundle = new Bundle();
                            bundle.putString("id", strOptString);
                            bundle.putString("appid", TitleNView.this.mWebView.obtainApp().obtainAppId());
                            IWebview iWebview = TitleNView.this.mWebView;
                            iWebview.obtainFrameView().obtainWindowMgr().processEvent(IMgr.MgrType.WindowMgr, 77, new Object[]{iWebview, bundle});
                            return;
                        }
                        return;
                    }
                    ButtonDataItem buttonDataItem = (ButtonDataItem) TitleNView.this.mMenuButtons.get(i2);
                    if (buttonDataItem != null) {
                        AbsMgr absMgrObtainWindowMgr = TitleNView.this.mWebView.obtainFrameView().obtainWindowMgr();
                        IMgr.MgrType mgrType = IMgr.MgrType.FeatureMgr;
                        IApp iApp = TitleNView.this.mApp;
                        Object objProcessEvent = absMgrObtainWindowMgr.processEvent(mgrType, 10, new Object[]{iApp, "ui", "findWebview", new String[]{iApp.obtainAppId(), buttonDataItem.getWebviewUuid()}});
                        IWebview iWebview2 = null;
                        if (objProcessEvent != null && (objProcessEvent instanceof IWebview)) {
                            iWebview2 = (IWebview) objProcessEvent;
                        }
                        if (iWebview2 == null) {
                            iWebview2 = TitleNView.this.mWebView;
                        }
                        TitleNView.this.buttonOnclick(buttonDataItem.getOnclick(), iWebview2);
                    }
                }
            });
            aVar.b(getContext().getString(io.dcloud.base.R.string.dcloud_common_cancel)).d(this.mMenuButtonTextColor).a(PdrUtil.parseFloat(this.mMenuButtonFontSize, 0.0f, 0.0f, 1.0f)).a(jSONArray).a(true).b(false).e(0);
            aVar.j();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void updateCapsuleLayout() {
        CapsuleLayout capsuleLayout = this.mCapsuleLayout;
        if (capsuleLayout == null || capsuleLayout.i) {
            return;
        }
        String backgroundColor = getBackgroundColor();
        if (TextUtils.isEmpty(backgroundColor)) {
            return;
        }
        if (this.mCapsuleLayout.a(PdrUtil.stringToColor(backgroundColor)) == 1) {
            this.closeBt.setTextColor(-16777216);
            this.menuBt.setTextColor(-16777216);
        } else {
            this.closeBt.setTextColor(-1);
            this.menuBt.setTextColor(-1);
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
    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void addBackButton(String str, String str2, String str3, JSONObject jSONObject) {
        TextView textView;
        TextView textView2;
        TextView textView3;
        String str4;
        String strChangeColorAlpha;
        int iStringToColor;
        int iStringToColor2;
        initTitleNViewLayout();
        initLeftButtonLayout();
        BadgeLinearLayout badgeLinearLayout = this.mBackButton;
        if (badgeLinearLayout == null) {
            BadgeLinearLayout badgeLinearLayout2 = new BadgeLinearLayout(this, getContext(), this.mCreateScale, this.redDotColor);
            this.mBackButton = badgeLinearLayout2;
            badgeLinearLayout2.setContentDescription(getResources().getString(io.dcloud.base.R.string.dcloud_titlenview_back_button_description));
            this.mBackButton.setTag("TitleNView.BackButton");
            this.mBackButton.setGravity(17);
            textView = new TextView(getContext());
            textView.setGravity(17);
            textView.setId(View.generateViewId());
            textView.setIncludeFontPadding(false);
            textView2 = new TextView(getContext());
            textView2.setGravity(17);
            textView2.setIncludeFontPadding(false);
            textView3 = new TextView(getContext());
            textView3.setGravity(17);
            textView3.setIncludeFontPadding(false);
            textView3.setTextColor(-1);
            textView3.setVisibility(8);
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            relativeLayout.setGravity(16);
            this.mBackButton.addView(textView, new LinearLayout.LayoutParams(-2, 500));
            this.mBackButton.addView(relativeLayout, new LinearLayout.LayoutParams(-2, -1));
            relativeLayout.addView(textView2, new RelativeLayout.LayoutParams(-2, -1));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(15);
            relativeLayout.addView(textView3, layoutParams);
        } else {
            textView = (TextView) badgeLinearLayout.getChildAt(0);
            RelativeLayout relativeLayout2 = (RelativeLayout) this.mBackButton.getChildAt(1);
            textView2 = (TextView) relativeLayout2.getChildAt(0);
            textView3 = (TextView) relativeLayout2.getChildAt(1);
        }
        IFrameView iFrameView = this.mFrameViewParent;
        if (iFrameView == null || 5 != iFrameView.getFrameType()) {
            textView.setText((jSONObject == null || !jSONObject.has("text")) ? "\ue601" : jSONObject.optString("text"));
        } else {
            textView.setText((jSONObject == null || !jSONObject.has("text")) ? "\ue650" : jSONObject.optString("text"));
        }
        textView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/dcloud_iconfont.ttf"));
        textView2.setText((jSONObject == null || !jSONObject.has("title")) ? "" : jSONObject.optString("title"));
        this.mBackButton.setOnClickListener(new View.OnClickListener() { // from class: io.dcloud.feature.nativeObj.TitleNView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Activity activity;
                IApp iApp = TitleNView.this.mApp;
                if (iApp == null || (activity = iApp.getActivity()) == null) {
                    return;
                }
                activity.onBackPressed();
            }
        });
        String strOptString = "16px";
        if ("transparent".equals(str3)) {
            textView.getPaint().setTextSize(PdrUtil.convertToScreenInt((jSONObject == null || !jSONObject.has(Constants.Name.FONT_SIZE)) ? "22px" : jSONObject.optString(Constants.Name.FONT_SIZE), this.mInnerWidth, 0, this.mCreateScale));
            textView2.getPaint().setTextSize(PdrUtil.convertToScreenInt((jSONObject == null || !jSONObject.has("titleSize")) ? "16px" : jSONObject.optString("titleSize"), this.mInnerWidth, 0, this.mCreateScale));
            IFrameView iFrameView2 = this.mFrameViewParent;
            if (iFrameView2 == null || 5 != iFrameView2.getFrameType()) {
                this.mBackButton.setPadding(0, 0, PdrUtil.convertToScreenInt("2px", this.mInnerWidth, 0, this.mCreateScale), 0);
            } else {
                this.mBackButton.setPadding(0, 0, 0, 0);
            }
            NativeViewBackButtonDrawable nativeViewBackButtonDrawable = new NativeViewBackButtonDrawable(PdrUtil.stringToColor((jSONObject == null || !jSONObject.has("background")) ? TitleNViewUtil.TRANSPARENT_BUTTON_BACKGROUND_COLOR : jSONObject.optString("background")));
            nativeViewBackButtonDrawable.setWidth("backButton");
            this.mBackButton.setBackground(nativeViewBackButtonDrawable);
            str4 = TitleNViewUtil.TRANSPARENT_BUTTON_TEXT_COLOR;
            strChangeColorAlpha = TitleNViewUtil.changeColorAlpha(TitleNViewUtil.TRANSPARENT_BUTTON_TEXT_COLOR, 0.3f);
        } else {
            textView.getPaint().setTextSize(PdrUtil.convertToScreenInt((jSONObject == null || !jSONObject.has(Constants.Name.FONT_SIZE)) ? "27px" : jSONObject.optString(Constants.Name.FONT_SIZE), this.mInnerWidth, 0, this.mCreateScale));
            textView2.getPaint().setTextSize(PdrUtil.convertToScreenInt((jSONObject == null || !jSONObject.has("titleSize")) ? "16px" : jSONObject.optString("titleSize"), this.mInnerWidth, 0, this.mCreateScale));
            str4 = str;
            strChangeColorAlpha = str2;
        }
        if (jSONObject != null && jSONObject.has("badgeSize")) {
            strOptString = jSONObject.optString("badgeSize");
        }
        int iConvertToScreenInt = PdrUtil.convertToScreenInt(strOptString, this.mInnerWidth, 0, this.mCreateScale);
        textView3.getPaint().setTextSize(iConvertToScreenInt);
        ViewGroup.LayoutParams layoutParams2 = textView3.getLayoutParams();
        layoutParams2.height = PdrUtil.convertToScreenInt("2px", this.mInnerWidth, 0, this.mCreateScale) + iConvertToScreenInt;
        textView3.setLayoutParams(layoutParams2);
        textView3.setMinWidth(layoutParams2.height);
        int i = iConvertToScreenInt / 4;
        textView3.setPadding(i, getPaddingTop(), i, getPaddingBottom());
        String strOptString2 = (jSONObject == null || !jSONObject.has(Constants.Name.FONT_WEIGHT)) ? "normal" : jSONObject.optString(Constants.Name.FONT_WEIGHT);
        if (strOptString2.equals("normal")) {
            textView.getPaint().setFakeBoldText(false);
        } else if (strOptString2.equals(Constants.Value.BOLD)) {
            textView.getPaint().setFakeBoldText(true);
        }
        String strOptString3 = (jSONObject == null || !jSONObject.has("titleWeight")) ? "normal" : jSONObject.optString("titleWeight");
        if (strOptString3.equals("normal")) {
            textView2.getPaint().setFakeBoldText(false);
        } else if (strOptString3.equals(Constants.Value.BOLD)) {
            textView2.getPaint().setFakeBoldText(true);
        }
        String strOptString4 = null;
        String strOptString5 = (jSONObject == null || !jSONObject.has("badgeText")) ? null : jSONObject.optString("badgeText");
        this.mBackButton.setBadgeStr(strOptString5);
        this.mBackButton.setDrawRedDot(jSONObject != null && jSONObject.has("redDot") && jSONObject.optBoolean("redDot"));
        if (PdrUtil.isEmpty(strOptString5)) {
            textView3.setVisibility(8);
        } else {
            textView3.setText(strOptString5, TextView.BufferType.SPANNABLE);
            textView3.setVisibility(0);
        }
        String strOptString6 = (jSONObject == null || !jSONObject.has("badgeBackground")) ? null : jSONObject.optString("badgeBackground");
        if (jSONObject != null && jSONObject.has("badgeColor")) {
            strOptString4 = jSONObject.optString("badgeColor");
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        if (PdrUtil.isEmpty(strOptString6)) {
            gradientDrawable.setColor(-65536);
        } else {
            gradientDrawable.setColor(PdrUtil.stringToColor(strOptString6));
        }
        gradientDrawable.setCornerRadius(layoutParams2.height / 2);
        textView3.setBackground(gradientDrawable);
        if (!PdrUtil.isEmpty(strOptString4)) {
            textView3.setTextColor(PdrUtil.stringToColor(strOptString4));
        }
        try {
            iStringToColor = Color.parseColor(str4);
        } catch (Exception unused) {
            iStringToColor = PdrUtil.stringToColor(str4);
        }
        try {
            iStringToColor2 = Color.parseColor(strChangeColorAlpha);
        } catch (Exception unused2) {
            iStringToColor2 = PdrUtil.stringToColor(strChangeColorAlpha);
        }
        textView.setTextColor(createColorStateList(iStringToColor, iStringToColor2));
        textView2.setTextColor(createColorStateList(iStringToColor, iStringToColor2));
        if (this.mBackButton.getParent() != null) {
            ((ViewGroup) this.mBackButton.getParent()).removeView(this.mBackButton);
        }
        if (-1 == this.mLeftButtonLayout.indexOfChild(this.mBackButton)) {
            int iConvertToScreenInt2 = this.mInnerHeight - PdrUtil.convertToScreenInt("12px", this.mAppScreenWidth, 0, this.mCreateScale);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, iConvertToScreenInt2);
            layoutParams3.leftMargin = PdrUtil.convertToScreenInt("5px", this.mAppScreenWidth, 0, this.mCreateScale);
            this.mBackButton.setMinimumWidth(iConvertToScreenInt2);
            this.mLeftButtonLayout.addView(this.mBackButton, 0, layoutParams3);
        }
        this.mBackButton.setVisibility(0);
        requestLayout();
        caculateTitleMargin();
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void addHomeButton(String str, String str2, String str3) {
        int iStringToColor;
        int iStringToColor2;
        initTitleNViewLayout();
        initLeftButtonLayout();
        initRightButtonLayout();
        if (this.mHomeButton == null) {
            TextView textView = new TextView(getContext());
            this.mHomeButton = textView;
            textView.setGravity(17);
            this.mHomeButton.setTag("TitleNView.HomeButton");
            this.mHomeButton.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/dcloud_iconfont.ttf"));
            this.mHomeButton.setText("\ue605");
            this.mHomeButton.setIncludeFontPadding(false);
            this.mHomeButton.setOnClickListener(new View.OnClickListener() { // from class: io.dcloud.feature.nativeObj.TitleNView.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    IApp iApp = TitleNView.this.mApp;
                    if (iApp == null || iApp.getActivity() == null) {
                        return;
                    }
                    TitleNView.this.mFrameViewParent.obtainWebAppRootView().goHome(TitleNView.this.mFrameViewParent);
                }
            });
            if ("transparent".equals(str3)) {
                this.mHomeButton.getPaint().setTextSize(PdrUtil.convertToScreenInt("22px", this.mInnerWidth, 0, this.mCreateScale));
                NativeViewBackButtonDrawable nativeViewBackButtonDrawable = new NativeViewBackButtonDrawable(Color.parseColor(TitleNViewUtil.TRANSPARENT_BUTTON_BACKGROUND_COLOR));
                nativeViewBackButtonDrawable.setWidth("");
                this.mHomeButton.setBackground(nativeViewBackButtonDrawable);
                str = TitleNViewUtil.TRANSPARENT_BUTTON_TEXT_COLOR;
                str2 = TitleNViewUtil.changeColorAlpha(TitleNViewUtil.TRANSPARENT_BUTTON_TEXT_COLOR, 0.3f);
            } else {
                this.mHomeButton.getPaint().setTextSize(PdrUtil.convertToScreenInt("27px", this.mInnerWidth, 0, this.mCreateScale));
            }
            try {
                iStringToColor = Color.parseColor(str);
            } catch (Exception unused) {
                iStringToColor = PdrUtil.stringToColor(str);
            }
            try {
                iStringToColor2 = Color.parseColor(str2);
            } catch (Exception unused2) {
                iStringToColor2 = PdrUtil.stringToColor(str2);
            }
            this.mHomeButton.setTextColor(createColorStateList(iStringToColor, iStringToColor2));
        }
        int iConvertToScreenInt = this.mInnerHeight - PdrUtil.convertToScreenInt("12px", this.mAppScreenWidth, 0, this.mCreateScale);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(iConvertToScreenInt, iConvertToScreenInt);
        if (this.mHomeButton.getParent() != null) {
            ((ViewGroup) this.mHomeButton.getParent()).removeView(this.mHomeButton);
        }
        if (this.mRightButtonLayout.getChildCount() == 0) {
            layoutParams.rightMargin = PdrUtil.convertToScreenInt("5px", this.mAppScreenWidth, 0, this.mCreateScale);
            this.mRightButtonLayout.addView(this.mHomeButton, 0, layoutParams);
        } else if (this.mRightButtonLayout.getChildCount() == 1 && -1 == this.mRightButtonLayout.indexOfChild(this.mHomeButton)) {
            layoutParams.rightMargin = PdrUtil.convertToScreenInt("5px", this.mAppScreenWidth, 0, this.mCreateScale);
            this.mRightButtonLayout.addView(this.mHomeButton, 0, layoutParams);
        } else if (this.mRightButtonLayout.getChildCount() == 2 && -1 == this.mRightButtonLayout.indexOfChild(this.mHomeButton)) {
            if (this.mLeftButtonLayout.getChildCount() == 1 && this.mBackButton != null && -1 == this.mLeftButtonLayout.indexOfChild(this.mHomeButton)) {
                layoutParams.leftMargin = PdrUtil.convertToScreenInt("5px", this.mAppScreenWidth, 0, this.mCreateScale);
                this.mLeftButtonLayout.addView(this.mHomeButton, 1, layoutParams);
            } else if (this.mLeftButtonLayout.getChildCount() == 2 && -1 == this.mLeftButtonLayout.indexOfChild(this.mHomeButton)) {
                LinearLayout linearLayout = this.mLeftButtonLayout;
                linearLayout.removeView(linearLayout.getChildAt(1));
                layoutParams.leftMargin = PdrUtil.convertToScreenInt("5px", this.mAppScreenWidth, 0, this.mCreateScale);
                this.mLeftButtonLayout.addView(this.mHomeButton, 1, layoutParams);
            }
        }
        this.mHomeButton.setVisibility(0);
        requestLayout();
        caculateTitleMargin();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0072  */
    @Override // io.dcloud.common.DHInterface.ITitleNView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addLeftButton(java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, io.dcloud.common.DHInterface.IWebview r28, java.lang.String r29, java.lang.String r30, boolean r31, java.lang.String r32, boolean r33, java.lang.String r34) {
        /*
            r17 = this;
            r15 = r17
            r14 = r26
            r0 = r17
            r1 = r18
            r2 = r20
            r3 = r21
            r4 = r22
            r5 = r23
            r6 = r24
            r7 = r25
            r16 = r26
            r8 = r27
            r9 = r28
            r10 = r29
            r11 = r30
            r12 = r31
            r13 = r32
            r14 = r33
            r15 = r34
            android.view.View r0 = r0.createButton(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            r17.initTitleNViewLayout()
            r17.initLeftButtonLayout()
            r1 = r17
            android.widget.LinearLayout r2 = r1.mLeftButtonLayout
            int r2 = r2.indexOfChild(r0)
            r3 = -1
            if (r3 != r2) goto Lad
            int r2 = r1.mInnerHeight
            int r4 = r1.mAppScreenWidth
            float r5 = r1.mCreateScale
            r6 = 0
            java.lang.String r7 = "12px"
            int r4 = io.dcloud.common.util.PdrUtil.convertToScreenInt(r7, r4, r6, r5)
            int r2 = r2 - r4
            int r4 = r1.mAppScreenWidth
            float r5 = r1.mCreateScale
            r7 = r34
            int r4 = io.dcloud.common.util.PdrUtil.convertToScreenInt(r7, r4, r3, r5)
            int r5 = r1.mAppScreenWidth
            float r7 = r1.mCreateScale
            r8 = r26
            int r3 = io.dcloud.common.util.PdrUtil.convertToScreenInt(r8, r5, r3, r7)
            java.lang.String r5 = "auto"
            java.lang.String r7 = "5px"
            if (r4 <= 0) goto L66
            if (r3 <= r4) goto L66
            goto L72
        L66:
            boolean r3 = io.dcloud.common.util.PdrUtil.isEmpty(r26)
            if (r3 != 0) goto L7f
            boolean r3 = r8.equals(r5)
            if (r3 == 0) goto L74
        L72:
            r3 = -2
            goto L80
        L74:
            int r3 = r1.mAppScreenWidth
            float r4 = r1.mCreateScale
            int r3 = io.dcloud.common.util.PdrUtil.convertToScreenInt(r8, r3, r2, r4)
            java.lang.String r7 = "0px"
            goto L80
        L7f:
            r3 = r2
        L80:
            int r4 = r1.mAppScreenWidth
            float r9 = r1.mCreateScale
            int r4 = io.dcloud.common.util.PdrUtil.convertToScreenInt(r7, r4, r6, r9)
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams
            r6.<init>(r3, r2)
            r6.leftMargin = r4
            android.widget.LinearLayout r2 = r1.mLeftButtonLayout
            r2.addView(r0, r6)
            boolean r2 = io.dcloud.common.util.PdrUtil.isEmpty(r26)
            if (r2 != 0) goto Lad
            boolean r2 = r8.equals(r5)
            if (r2 == 0) goto Lad
            if (r33 == 0) goto Lad
            int r2 = r0.getPaddingTop()
            int r3 = r0.getPaddingBottom()
            r0.setPadding(r4, r2, r4, r3)
        Lad:
            r17.caculateTitleMargin()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.nativeObj.TitleNView.addLeftButton(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String, boolean, java.lang.String, boolean, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00ac  */
    @Override // io.dcloud.common.DHInterface.ITitleNView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addRightButton(java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, io.dcloud.common.DHInterface.IWebview r28, java.lang.String r29, java.lang.String r30, boolean r31, java.lang.String r32, boolean r33, java.lang.String r34) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.nativeObj.TitleNView.addRightButton(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String, boolean, java.lang.String, boolean, java.lang.String):void");
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void addSearchInput(final String str, String str2, String str3, String str4, String str5, String str6, boolean z, final boolean z2, final IWebview iWebview) {
        boolean z3;
        final TextView textView;
        int iStringToColor;
        int iStringToColor2;
        int iStringToColor3;
        initTitleNViewLayout();
        initLeftButtonLayout();
        initRightButtonLayout();
        initCenterSearchLayout();
        RelativeLayout relativeLayout = this.mTitlelayout;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(4);
        }
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(getContext().getAssets(), "fonts/dcloud_iconfont.ttf");
        int iConvertToScreenInt = PdrUtil.convertToScreenInt("13px", this.mInnerWidth, 0, this.mCreateScale);
        int iConvertToScreenInt2 = PdrUtil.convertToScreenInt("8px", this.mAppScreenWidth, 0, this.mCreateScale);
        if (this.searchInput == null) {
            TextView textView2 = new TextView(getContext());
            textView2.setGravity(17);
            textView2.setTypeface(typefaceCreateFromAsset);
            textView2.setText("\ue660 ");
            textView2.setIncludeFontPadding(false);
            float f = iConvertToScreenInt;
            textView2.getPaint().setTextSize(f);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
            layoutParams.leftMargin = iConvertToScreenInt2;
            this.centerSearchLayout.addView(textView2, layoutParams);
            EditText editText = new EditText(getContext());
            this.searchInput = editText;
            editText.setTag("TitleNView.SearchInput");
            int i = iConvertToScreenInt2 / 2;
            this.searchInput.setPadding(0, i, 0, i);
            this.searchInput.setPaddingRelative(0, i, 0, i);
            this.searchInput.setIncludeFontPadding(false);
            this.searchInput.setGravity(17);
            this.searchInput.setSingleLine();
            this.searchInput.setLines(1);
            this.searchInput.setTypeface(typefaceCreateFromAsset);
            this.searchInput.setImeOptions(3);
            this.searchInput.getPaint().setTextSize(f);
            this.searchInput.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            z3 = true;
        } else {
            z3 = false;
        }
        initTitleNViewLayout();
        if (this.searchInput.getParent() != null) {
            ((ViewGroup) this.searchInput.getParent()).removeView(this.searchInput);
        }
        if (-1 == this.centerSearchLayout.indexOfChild(this.searchInput)) {
            this.centerSearchLayout.addView(this.searchInput, 1, new LinearLayout.LayoutParams(-1, -2, 1.0f));
        }
        TextView textView3 = this.centerSearchLayout.getChildAt(0) instanceof TextView ? (TextView) this.centerSearchLayout.getChildAt(0) : null;
        if (textView3 != null) {
            if (str.equalsIgnoreCase("left")) {
                textView3.setVisibility(0);
            } else if (this.searchInput.getText() == null || this.searchInput.getText().toString().length() <= 0) {
                textView3.setVisibility(8);
            } else {
                textView3.setVisibility(0);
            }
        }
        if (this.centerSearchLayout.getChildCount() > 2) {
            textView = (TextView) this.centerSearchLayout.getChildAt(2);
        } else {
            TextView textView4 = new TextView(getContext());
            textView4.setGravity(17);
            textView4.setTypeface(typefaceCreateFromAsset);
            textView4.setText("\ue650");
            textView4.setTextColor(-1);
            textView4.setTextSize(0, iConvertToScreenInt);
            textView4.setIncludeFontPadding(false);
            textView4.getPaint().setFakeBoldText(true);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(100.0f);
            gradientDrawable.setColor(-4802890);
            textView4.setBackground(gradientDrawable);
            int i2 = iConvertToScreenInt2 / 4;
            textView4.setPadding(i2, i2, i2, i2);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.rightMargin = iConvertToScreenInt2;
            this.centerSearchLayout.addView(textView4, 2, layoutParams2);
            textView4.setOnClickListener(new View.OnClickListener() { // from class: io.dcloud.feature.nativeObj.TitleNView.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TitleNView.this.searchInput.setText("");
                }
            });
            textView4.setVisibility(8);
            textView = textView4;
        }
        if (PdrUtil.isEmpty(str2)) {
            iStringToColor = Color.parseColor("#8fffffff");
        } else {
            try {
                iStringToColor = Color.parseColor(str2);
            } catch (Exception unused) {
                iStringToColor = PdrUtil.stringToColor(str2);
            }
        }
        int iConvertToScreenInt3 = PdrUtil.isEmpty(str3) ? 0 : PdrUtil.convertToScreenInt(str3, this.mInnerWidth, 0, this.mCreateScale);
        String str7 = !PdrUtil.isEmpty(str4) ? str4 : "";
        this.searchInput.setHint("\ue660 " + str7);
        if (PdrUtil.isEmpty(str5)) {
            iStringToColor2 = Color.parseColor("#CCCCCC");
        } else {
            try {
                iStringToColor2 = Color.parseColor(str5);
            } catch (Exception unused2) {
                iStringToColor2 = PdrUtil.stringToColor(str5);
            }
        }
        if (textView3 != null) {
            textView3.setTextColor(iStringToColor2);
        }
        this.searchInput.setHintTextColor(iStringToColor2);
        this.searchInput.setEllipsize(TextUtils.TruncateAt.END);
        this.searchInput.setBackground(null);
        this.centerSearchLayout.setBackground(new SearchInputDrawable(iStringToColor, iConvertToScreenInt3));
        if (PdrUtil.isEmpty(str6)) {
            iStringToColor3 = -16777216;
        } else {
            try {
                iStringToColor3 = Color.parseColor(str6);
            } catch (Exception unused3) {
                iStringToColor3 = PdrUtil.stringToColor(str6);
            }
        }
        this.searchInput.setTextColor(iStringToColor3);
        if (z3) {
            this.searchInput.addTextChangedListener(new TextWatcher() { // from class: io.dcloud.feature.nativeObj.TitleNView.5
                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
                    if (charSequence.toString().length() > 0) {
                        textView.setVisibility(0);
                    } else {
                        textView.setVisibility(8);
                    }
                    String jsResponseText = JSUtil.toJsResponseText(charSequence.toString());
                    IWebview iWebview2 = TitleNView.this.mWebView;
                    if (iWebview2 != null && iWebview2.obtainFrameView() != null && (TitleNView.this.mWebView.obtainFrameView() instanceof AdaFrameView)) {
                        ((AdaFrameView) TitleNView.this.mWebView.obtainFrameView()).dispatchFrameViewEvents(AbsoluteConst.EVENT_TITLENVIEW_SEARCHINPUT_CHANGED, StringUtil.format("{text:\"%s\"}", jsResponseText));
                        return;
                    }
                    IWebview iWebview3 = iWebview;
                    if (iWebview3 == null || iWebview3.obtainFrameView() == null || !(iWebview.obtainFrameView() instanceof AdaFrameView)) {
                        return;
                    }
                    ((AdaFrameView) iWebview.obtainFrameView()).dispatchFrameViewEvents(AbsoluteConst.EVENT_TITLENVIEW_SEARCHINPUT_CHANGED, StringUtil.format("{text:\"%s\"}", jsResponseText));
                }
            });
        }
        final TextView textView5 = textView3;
        final String str8 = str7;
        this.searchInput.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: io.dcloud.feature.nativeObj.TitleNView.6
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z4) {
                if (!TitleNView.this.isSetText.get()) {
                    IWebview iWebview2 = TitleNView.this.mWebView;
                    if (iWebview2 != null && iWebview2.obtainFrameView() != null && (TitleNView.this.mWebView.obtainFrameView() instanceof AdaFrameView)) {
                        ((AdaFrameView) TitleNView.this.mWebView.obtainFrameView()).dispatchFrameViewEvents(AbsoluteConst.EVENT_TITLENVIEW_SEARCHINPUT_FOCUSCHANGED, StringUtil.format("{focus:%b}", Boolean.valueOf(z4)));
                    }
                    IWebview iWebview3 = iWebview;
                    if (iWebview3 != null && iWebview3.obtainFrameView() != null && (iWebview.obtainFrameView() instanceof AdaFrameView)) {
                        ((AdaFrameView) iWebview.obtainFrameView()).dispatchFrameViewEvents(AbsoluteConst.EVENT_TITLENVIEW_SEARCHINPUT_FOCUSCHANGED, StringUtil.format("{focus:%b}", Boolean.valueOf(z4)));
                    }
                }
                if (z4) {
                    TitleNView.this.searchInput.setGravity(3);
                    textView5.setVisibility(0);
                    TitleNView.this.searchInput.setHint(str8);
                } else if (TitleNView.this.searchInput.getText().toString().length() < 1) {
                    TitleNView.this.setTextGravity(str, str8);
                    if (str.equalsIgnoreCase("left")) {
                        textView5.setVisibility(0);
                    } else {
                        textView5.setVisibility(8);
                    }
                }
            }
        });
        this.searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: io.dcloud.feature.nativeObj.TitleNView.7
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView6, int i3, KeyEvent keyEvent) {
                if (i3 == 3) {
                    String jsResponseText = JSUtil.toJsResponseText(textView6.getText().toString());
                    IWebview iWebview2 = TitleNView.this.mWebView;
                    if (iWebview2 != null && iWebview2.obtainFrameView() != null && (TitleNView.this.mWebView.obtainFrameView() instanceof AdaFrameView)) {
                        ((AdaFrameView) TitleNView.this.mWebView.obtainFrameView()).dispatchFrameViewEvents(AbsoluteConst.EVENT_TITLENVIEW_SEARCHINPUT_CONFIRMED, StringUtil.format("{text:\"%s\"}", jsResponseText));
                        return true;
                    }
                    IWebview iWebview3 = iWebview;
                    if (iWebview3 != null && iWebview3.obtainFrameView() != null && (iWebview.obtainFrameView() instanceof AdaFrameView)) {
                        ((AdaFrameView) iWebview.obtainFrameView()).dispatchFrameViewEvents(AbsoluteConst.EVENT_TITLENVIEW_SEARCHINPUT_CONFIRMED, StringUtil.format("{text:\"%s\"}", jsResponseText));
                    }
                }
                return false;
            }
        });
        if (this.searchInput.getText() == null || this.searchInput.getText().toString().length() < 1) {
            setTextGravity(str, str7);
        }
        boolean z4 = !z;
        this.searchInput.setCursorVisible(z4);
        this.searchInput.setFocusable(z4);
        this.searchInput.setFocusableInTouchMode(z4);
        final IWebview iWebview2 = this.mWebView;
        if (iWebview2 == null) {
            iWebview2 = iWebview;
        }
        try {
            final boolean zDidCloseSplash = ((IWebAppRootView) ((AdaFrameView) iWebview2.obtainFrameView()).getParent()).didCloseSplash();
            if (!zDidCloseSplash) {
                iWebview2.obtainApp().registerSysEventListener(new ISysEventListener() { // from class: io.dcloud.feature.nativeObj.TitleNView.8
                    @Override // io.dcloud.common.DHInterface.ISysEventListener
                    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                        ISysEventListener.SysEventType sysEventType2;
                        if (z2 && sysEventType == (sysEventType2 = ISysEventListener.SysEventType.onSplashclosed)) {
                            TitleNView.this.searchInput.requestFocus();
                            if (!DeviceInfo.isIMEShow) {
                                DeviceInfo.showIME(TitleNView.this.searchInput);
                            }
                            iWebview2.obtainApp().unregisterSysEventListener(this, sysEventType2);
                        }
                        if (z2) {
                            return false;
                        }
                        DeviceInfo.hideIME(TitleNView.this.searchInput);
                        return false;
                    }
                }, ISysEventListener.SysEventType.onSplashclosed);
            }
            iWebview2.obtainFrameView().addFrameViewListener(new IEventCallback() { // from class: io.dcloud.feature.nativeObj.TitleNView.9
                @Override // io.dcloud.common.DHInterface.IEventCallback
                public Object onCallBack(String str9, Object obj) {
                    if (!AbsoluteConst.EVENTS_SHOW_ANIMATION_END.equalsIgnoreCase(str9) || !z2 || !zDidCloseSplash) {
                        return null;
                    }
                    TitleNView.this.searchInput.requestFocus();
                    if (!DeviceInfo.isIMEShow) {
                        DeviceInfo.showIME(TitleNView.this.searchInput);
                    }
                    iWebview2.obtainFrameView().removeFrameViewListener(this);
                    return null;
                }
            });
        } catch (Exception unused4) {
        }
        if (!z2) {
            DeviceInfo.hideIME(this.searchInput);
        }
        if (z) {
            this.searchInput.setOnClickListener(new View.OnClickListener() { // from class: io.dcloud.feature.nativeObj.TitleNView.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    IWebview iWebview3 = TitleNView.this.mWebView;
                    if (iWebview3 != null && iWebview3.obtainFrameView() != null && (TitleNView.this.mWebView.obtainFrameView() instanceof AdaFrameView)) {
                        ((AdaFrameView) TitleNView.this.mWebView.obtainFrameView()).dispatchFrameViewEvents(AbsoluteConst.EVENT_TITLENVIEW_SEARCHINPUT_CLICKED, String.format(Operators.SPACE_STR, new Object[0]));
                        return;
                    }
                    IWebview iWebview4 = iWebview;
                    if (iWebview4 == null || iWebview4.obtainFrameView() == null || !(iWebview.obtainFrameView() instanceof AdaFrameView)) {
                        return;
                    }
                    ((AdaFrameView) iWebview.obtainFrameView()).dispatchFrameViewEvents(AbsoluteConst.EVENT_TITLENVIEW_SEARCHINPUT_CLICKED, String.format(Operators.SPACE_STR, new Object[0]));
                }
            });
        }
        this.searchInput.setVisibility(0);
        requestLayout();
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void clearButtons() {
        LinearLayout linearLayout = this.mRightButtonLayout;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
        LinearLayout linearLayout2 = this.mLeftButtonLayout;
        if (linearLayout2 != null) {
            linearLayout2.removeAllViews();
        }
        ArrayList<ButtonDataItem> arrayList = this.mMenuButtons;
        if (arrayList != null) {
            arrayList.clear();
        }
        ArrayList<Object> arrayList2 = this.mButtons;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
    }

    @Override // io.dcloud.feature.nativeObj.NativeView
    public void clearNativeViewData() {
        IFrameView iFrameView = this.mFrameViewParent;
        if (iFrameView != null && (iFrameView instanceof AdaFrameView)) {
            ((AdaFrameView) iFrameView).removeNativeViewChild(this);
        }
        postDelayed(new Runnable() { // from class: io.dcloud.feature.nativeObj.TitleNView.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    TitleNView.this.setVisibility(8);
                    if (TitleNView.this.getParent() != null) {
                        ((ViewGroup) TitleNView.this.getParent()).removeView(TitleNView.this);
                    }
                    TitleNView.this.clearViewData();
                    if (TitleNView.this.mButtons != null) {
                        for (Object obj : TitleNView.this.mButtons) {
                            if (obj instanceof BadgeRelateiveLayout) {
                                View view = (View) obj;
                                if (view.getBackground() != null) {
                                    view.getBackground().setCallback(null);
                                }
                            }
                        }
                        TitleNView.this.mButtons.clear();
                    }
                    TitleNView.this.mFrameViewParent = null;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, 200);
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void clearSearchInput() {
        LinearLayout linearLayout = this.centerSearchLayout;
        if (linearLayout == null || this.mTitleNViewLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        this.mTitleNViewLayout.removeView(this.centerSearchLayout);
        this.centerSearchLayout = null;
        this.searchInput = null;
        RelativeLayout relativeLayout = this.mTitlelayout;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public String getBackgroundColor() {
        return getStyleBackgroundColor();
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public String getStatusBarColor() {
        JSONObject jSONObjectOptJSONObject;
        int iStringToColor;
        if (!this.isImmersed) {
            return "{color:-1,alpha:true}";
        }
        if (this.mStyle.has(AbsoluteConst.JSONKEY_STATUSBAR) && (jSONObjectOptJSONObject = this.mStyle.optJSONObject(AbsoluteConst.JSONKEY_STATUSBAR)) != null && jSONObjectOptJSONObject.has("backgroundnoalpha")) {
            String strOptString = jSONObjectOptJSONObject.optString("backgroundnoalpha");
            try {
                iStringToColor = Color.parseColor(strOptString);
            } catch (Exception unused) {
                iStringToColor = PdrUtil.stringToColor(strOptString);
            }
            return "{color:" + iStringToColor + ",alpha:false}";
        }
        View view = this.mStatusbarView;
        if (view != null) {
            Drawable background = view.getBackground();
            if (!(background instanceof ColorDrawable)) {
                return "{color:-1,alpha:true}";
            }
            return "{color:" + ((ColorDrawable) background).getColor() + ",alpha:true}";
        }
        ViewParent parent = getParent();
        if (!TextUtils.equals(this.mStyle.optString("position", AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE), AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE) || parent == null || !(parent instanceof ViewGroup)) {
            return "{color:-1,alpha:true}";
        }
        int i = 0;
        while (true) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if (i >= viewGroup.getChildCount()) {
                return "{color:-1,alpha:true}";
            }
            Object tag = viewGroup.getChildAt(i).getTag();
            if (tag != null && "StatusBar".equalsIgnoreCase(tag.toString())) {
                Drawable background2 = viewGroup.getChildAt(i).getBackground();
                if (background2 instanceof ColorDrawable) {
                    return "{color:" + ((ColorDrawable) background2).getColor() + ",alpha:true}";
                }
            }
            i++;
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public int getTitleColor() {
        return this.mTitleView.getTextColors().getDefaultColor();
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public String getTitleNViewSearchInputText() {
        EditText editText = this.searchInput;
        return editText != null ? editText.getText().toString() : "";
    }

    @Override // io.dcloud.feature.nativeObj.NativeView, io.dcloud.common.DHInterface.INativeView
    public String getViewType() {
        return AbsoluteConst.NATIVE_TITLE_N_VIEW;
    }

    @Override // io.dcloud.feature.nativeObj.NativeView
    public void init() {
        View view;
        super.init();
        updateCapsuleLayout();
        this.redDotColor = -65536;
        if (this.mStyle != null) {
            if (this.mBackGroundDrawable == null) {
                BackGroundDrawable backGroundDrawable = new BackGroundDrawable();
                this.mBackGroundDrawable = backGroundDrawable;
                setBackgroundDrawable(backGroundDrawable);
            }
            if (this.mStyle.has(AbsoluteConst.JSONKEY_STATUSBAR) && this.mStyle.has(Constants.Name.BACKGROUND_IMAGE) && (view = this.mStatusbarView) != null) {
                view.setVisibility(8);
            }
            this.mBackGroundDrawable.setBackgroundColor(this.mBackGroundColor);
            this.mBackGroundDrawable.updatebound();
            this.mBackGroundDrawable.setBackgroundImage(this.mBackgroundImageSrc);
            this.mBackGroundDrawable.setAlpha(Color.alpha(this.mBackGroundColor));
            this.mBackGroundDrawable.invalidateSelf();
            if (!this.mStyle.has("redDotColor") || PdrUtil.isEmpty(this.mStyle.optString("redDotColor"))) {
                return;
            }
            this.redDotColor = PdrUtil.stringToColor(this.mStyle.optString("redDotColor"));
        }
    }

    @Override // io.dcloud.feature.nativeObj.NativeView
    public void measureChildViewToTop(int i) {
        super.measureChildViewToTop(i);
        RelativeLayout relativeLayout = this.mTitleNViewLayout;
        if (relativeLayout == null || relativeLayout.getLayoutParams() == null) {
            return;
        }
        ((FrameLayout.LayoutParams) this.mTitleNViewLayout.getLayoutParams()).topMargin = i;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        caculateTitleMargin();
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void removeBackButton() {
        BadgeLinearLayout badgeLinearLayout = this.mBackButton;
        if (badgeLinearLayout != null) {
            badgeLinearLayout.setVisibility(8);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void removeHomeButton() {
        TextView textView = this.mHomeButton;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void removeSplitLine() {
        RelativeLayout relativeLayout;
        View view = this.mSplitLine;
        if (view != null && (relativeLayout = this.mTitleNViewLayout) != null) {
            relativeLayout.removeView(view);
        }
        this.mSplitLine = null;
    }

    @Override // io.dcloud.feature.nativeObj.NativeView
    public void resetNativeView() {
        int iIntValue;
        IFrameView iFrameView = this.mFrameViewParent;
        if (iFrameView != null) {
            try {
                String titleNViewId = TitleNViewUtil.getTitleNViewId(iFrameView);
                if (this.mOverlayMaps.containsKey(titleNViewId)) {
                    iIntValue = this.mOverlayMaps.get(titleNViewId).intValue();
                    this.mOverlayMaps.clear();
                    this.mOverlayMaps.put(titleNViewId, Integer.valueOf(iIntValue));
                } else {
                    this.mOverlayMaps.clear();
                    iIntValue = -1;
                }
                if (-1 != iIntValue) {
                    NativeView.Overlay overlay = this.mOverlays.get(iIntValue);
                    Iterator<NativeView.Overlay> it = this.mOverlays.iterator();
                    while (it.hasNext()) {
                        NativeView.Overlay next = it.next();
                        if (next != overlay) {
                            NativeBitmap nativeBitmap = next.mNativeBitmap;
                            if (nativeBitmap != null) {
                                nativeBitmap.recycle(true);
                            }
                            it.remove();
                        }
                    }
                } else {
                    clearViewData();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            clearViewData();
        }
        clearAnimate();
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setBackButtonColor(String str, String str2, String str3) {
        setButtonColor(this.mBackButton, str, str2, str3);
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setBackgroundColor(String str) {
        setStyleBackgroundColor(str);
        updateCapsuleLayout();
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setBackgroundImage(String str) {
        if (this.mBackgroundImageSrc.equals(str)) {
            return;
        }
        this.mBackgroundImageSrc = str;
        if (this.mBackGroundDrawable == null) {
            this.mBackGroundDrawable = new BackGroundDrawable();
        }
        this.mBackGroundDrawable.setBackgroundImage(this.mBackgroundImageSrc);
        setBackgroundDrawable(this.mBackGroundDrawable);
        this.mBackGroundDrawable.invalidateSelf();
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setBackgroundRepeat(String str) {
        BackGroundDrawable backGroundDrawable = this.mBackGroundDrawable;
        if (backGroundDrawable != null) {
            backGroundDrawable.setRepeatType(str, this.mBackgroundImageSrc);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setBadgeText(JSONObject jSONObject, boolean z) {
        int iOptInt = jSONObject.optInt("index");
        String strOptString = jSONObject.optString("text");
        if (!z) {
            strOptString = "";
        }
        if (iOptInt >= this.mButtons.size() || iOptInt < 0) {
            return;
        }
        Object obj = this.mButtons.get(iOptInt);
        if (obj instanceof BadgeRelateiveLayout) {
            ((BadgeRelateiveLayout) obj).setBadgeStr(strOptString);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setButtonColorByIndex(int i, String str, String str2, String str3) {
        Object obj = this.mButtons.get(i);
        if (obj == null || !(obj instanceof View)) {
            return;
        }
        setButtonColor((View) obj, str, str2, str3);
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setButtonsColor(String str, String str2, String str3) {
        for (int i = 0; i < this.mButtons.size(); i++) {
            setButtonColorByIndex(i, str, str2, str3);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setCapsuleButtonStyle(JSONObject jSONObject) {
        CapsuleLayout capsuleLayout = this.mCapsuleLayout;
        if (capsuleLayout == null || jSONObject == null) {
            return;
        }
        capsuleLayout.i = true;
        Log.e("shutao", "setCapsuleButtonStyle---" + jSONObject);
        if (jSONObject.has("backgroundColor")) {
            this.mCapsuleLayout.setBackgroundColor(PdrUtil.stringToColor(jSONObject.optString("backgroundColor")));
        }
        if (jSONObject.has(Constants.Name.BORDER_COLOR)) {
            this.mCapsuleLayout.setRoundColor(PdrUtil.stringToColor(jSONObject.optString(Constants.Name.BORDER_COLOR)));
        }
        if (jSONObject.has("highlightColor")) {
            int iStringToColor = PdrUtil.stringToColor(jSONObject.optString("highlightColor"));
            TextView textView = this.menuBt;
            if (textView != null) {
                this.mCapsuleLayout.a(textView, CapsuleLayout.ButtonType.LIFT, iStringToColor);
            }
            TextView textView2 = this.closeBt;
            if (textView2 != null) {
                this.mCapsuleLayout.a(textView2, CapsuleLayout.ButtonType.RIGHT, iStringToColor);
            }
        }
        if (jSONObject.has(WXPickersModule.KEY_TEXT_COLOR)) {
            int iStringToColor2 = PdrUtil.stringToColor(jSONObject.optString(WXPickersModule.KEY_TEXT_COLOR));
            TextView textView3 = this.menuBt;
            if (textView3 != null) {
                textView3.setTextColor(iStringToColor2);
            }
            TextView textView4 = this.closeBt;
            if (textView4 != null) {
                textView4.setTextColor(iStringToColor2);
            }
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setHomeButtonColor(String str, String str2, String str3) {
        setButtonColor(this.mHomeButton, str, str2, str3);
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setIconSubTitleStyle(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        ImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout = this.mTitlelayout;
        if (relativeLayout != null) {
            if (relativeLayout.getChildAt(0) instanceof ImageView) {
                imageView = (ImageView) this.mTitlelayout.getChildAt(0);
            } else {
                imageView = new ImageView(getContext());
                imageView.setId(View.generateViewId());
            }
            ImageView imageView2 = imageView;
            if (this.mTitlelayout.getChildAt(2) instanceof TextView) {
                textView = (TextView) this.mTitlelayout.getChildAt(2);
            } else {
                textView = new TextView(getContext());
                textView.setId(View.generateViewId());
            }
            layoutSubtitleIcon(str, str2, str3, str4, str5, str6, imageView2, textView, PdrUtil.stringToColor(str7), str8, str9);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setProgress(String str, String str2) {
        int iStringToColor;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Progress progress = this.mProgress;
            if (progress != null) {
                if (progress.isFinish()) {
                    this.mProgress.setVisibility(0);
                    return;
                } else {
                    this.mProgress.finishProgress();
                    return;
                }
            }
            return;
        }
        if (this.mProgress == null) {
            Progress progress2 = new Progress(getContext());
            this.mProgress = progress2;
            progress2.setTag("TitleNView.Progress");
        }
        try {
            iStringToColor = Color.parseColor(str2);
        } catch (Exception unused) {
            iStringToColor = PdrUtil.stringToColor(str2);
        }
        this.mProgress.setColorInt(iStringToColor);
        this.mProgress.setHeightInt(PdrUtil.convertToScreenInt(str, this.mInnerWidth, 0, this.mCreateScale));
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setRedDot(JSONObject jSONObject, boolean z) {
        int iOptInt = jSONObject.optInt("index");
        if (iOptInt >= this.mButtons.size() || iOptInt < 0) {
            return;
        }
        Object obj = this.mButtons.get(iOptInt);
        if (obj instanceof BadgeRelateiveLayout) {
            ((BadgeRelateiveLayout) obj).setDrawRedDot(z);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setRedDotColor(int i) {
        if (this.redDotColor != i) {
            this.redDotColor = i;
            this.mBackButton.setRedDotColor(i);
            if (this.mButtons.size() > 0) {
                for (Object obj : this.mButtons) {
                    if (obj instanceof BadgeRelateiveLayout) {
                        ((BadgeRelateiveLayout) obj).setRedDotColor(this.redDotColor);
                    }
                }
            }
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setSearchInputColor(String str) {
        Drawable background;
        int iStringToColor;
        LinearLayout linearLayout = this.centerSearchLayout;
        if (linearLayout == null || (background = linearLayout.getBackground()) == null || !(background instanceof SearchInputDrawable)) {
            return;
        }
        try {
            iStringToColor = Color.parseColor(str);
        } catch (Exception unused) {
            iStringToColor = PdrUtil.stringToColor(str);
        }
        SearchInputDrawable searchInputDrawable = (SearchInputDrawable) background;
        if (iStringToColor != searchInputDrawable.getDrawableColor()) {
            searchInputDrawable.setDrawableColor(iStringToColor);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setSearchInputFocus(boolean z) {
        EditText editText = this.searchInput;
        if (editText != null) {
            if (!z) {
                editText.clearFocus();
                DeviceInfo.hideIME(this.searchInput);
            } else {
                DeviceInfo.hideIME(editText);
                if (this.searchInput.requestFocus()) {
                    DeviceInfo.showIME(this.searchInput);
                }
            }
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setShadow(JSONObject jSONObject) {
        BackGroundDrawable backGroundDrawable = this.mBackGroundDrawable;
        if (backGroundDrawable != null) {
            backGroundDrawable.setShadowColor(jSONObject);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setSplitLine(String str, String str2) {
        int iStringToColor;
        if (this.mSplitLine == null) {
            View view = new View(getContext());
            this.mSplitLine = view;
            view.setTag("TitleNView.SplitLine");
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(12);
            this.mSplitLine.setLayoutParams(layoutParams);
        }
        initTitleNViewLayout();
        RelativeLayout relativeLayout = this.mTitleNViewLayout;
        if (relativeLayout != null && -1 == relativeLayout.indexOfChild(this.mSplitLine)) {
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(12);
            this.mTitleNViewLayout.addView(this.mSplitLine, layoutParams2);
        }
        try {
            iStringToColor = Color.parseColor(str2);
        } catch (Exception unused) {
            iStringToColor = PdrUtil.stringToColor(str2);
        }
        this.mSplitLine.setBackgroundColor(iStringToColor);
        this.mSplitLine.getLayoutParams().height = PdrUtil.convertToScreenInt(str, this.mInnerWidth, 0, this.mCreateScale);
        this.mSplitLine.requestLayout();
        this.mSplitLine.invalidate();
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setStatusBarColor(int i) {
        if (!this.isImmersed) {
            return;
        }
        if (this.mStatusbarView != null) {
            if (TextUtils.isEmpty(this.mBackgroundImageSrc) || !isStatusBar()) {
                this.mStatusbarView.setBackgroundColor(i);
                this.mStatusbarView.invalidate();
                return;
            }
            return;
        }
        ViewParent parent = getParent();
        if (!TextUtils.equals(this.mStyle.optString("position", AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE), AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE) || parent == null || !(parent instanceof ViewGroup)) {
            return;
        }
        int i2 = 0;
        while (true) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if (i2 >= viewGroup.getChildCount()) {
                return;
            }
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getTag() != null && "StatusBar".equalsIgnoreCase(childAt.getTag().toString())) {
                childAt.setBackgroundColor(i);
                childAt.invalidate();
                return;
            }
            i2++;
        }
    }

    @Override // io.dcloud.feature.nativeObj.NativeView, io.dcloud.common.DHInterface.INativeView
    public void setStyleBackgroundColor(int i) {
        this.mBackGroundDrawable.setBackgroundColor(i);
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setTitle(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        ImageView imageView;
        TextView textView;
        int iStringToColor;
        initTitleNViewLayout();
        initLeftButtonLayout();
        if (this.mTitlelayout == null) {
            this.mTitlelayout = new RelativeLayout(getContext());
        }
        if (this.mTitlelayout.getChildAt(0) instanceof ImageView) {
            imageView = (ImageView) this.mTitlelayout.getChildAt(0);
        } else {
            imageView = new ImageView(getContext());
            imageView.setId(View.generateViewId());
        }
        ImageView imageView2 = imageView;
        if (this.mTitleView == null) {
            TextView textView2 = new TextView(getContext());
            this.mTitleView = textView2;
            textView2.setTag("TitleNView.Title");
            this.mTitleView.setLines(1);
            this.mTitleView.setSingleLine(true);
            this.mTitleView.setIncludeFontPadding(false);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            this.mTitleView.setLayoutParams(layoutParams);
            if ("clip".equals(str4)) {
                this.mTitleView.setEllipsize(null);
            } else {
                this.mTitleView.setEllipsize(TextUtils.TruncateAt.END);
            }
            this.mTitleView.setId(View.generateViewId());
        }
        if (this.mTitlelayout.getChildAt(2) instanceof TextView) {
            textView = (TextView) this.mTitlelayout.getChildAt(2);
        } else {
            textView = new TextView(getContext());
            textView.setId(View.generateViewId());
        }
        TextView textView3 = textView;
        if (!TextUtils.isEmpty(str) && !str.equals(this.mTitleView.getText())) {
            this.mTitleView.setText(str);
        }
        this.mTitleView.getPaint().setTextSize(PdrUtil.convertToScreenInt(str3, this.mInnerWidth, PdrUtil.convertToScreenInt("17px", this.mInnerWidth, 0, this.mCreateScale), this.mCreateScale));
        try {
            iStringToColor = Color.parseColor(str2);
        } catch (Exception unused) {
            iStringToColor = PdrUtil.stringToColor(str2);
        }
        int i = iStringToColor;
        this.mTitleView.setTextColor(i);
        if (-1 == this.mTitleNViewLayout.indexOfChild(this.mTitlelayout)) {
            this.mTitleNViewLayout.addView(this.mTitlelayout);
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        int iConvertToScreenInt = PdrUtil.convertToScreenInt("88px", this.mInnerWidth, 0, this.mCreateScale);
        if (str5.equals("left") || str5.equals("auto")) {
            layoutParams2.addRule(1, this.mLeftButtonLayout.getId());
            layoutParams2.addRule(15);
            layoutParams2.removeRule(13);
            layoutParams2.leftMargin = PdrUtil.convertToScreenInt("10px", this.mInnerWidth, 0, this.mCreateScale);
        } else {
            layoutParams2.addRule(13);
            layoutParams2.removeRule(1);
            layoutParams2.removeRule(15);
            layoutParams2.leftMargin = iConvertToScreenInt;
        }
        layoutSubtitleIcon(str6, str7, str8, str9, str10, str11, imageView2, textView3, i, str5, str12);
        layoutParams2.rightMargin = iConvertToScreenInt;
        this.mTitlelayout.setLayoutParams(layoutParams2);
        EditText editText = this.searchInput;
        if (editText == null || editText.getVisibility() != 0) {
            return;
        }
        this.mTitlelayout.setVisibility(4);
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setTitleAlign(String str) {
        RelativeLayout relativeLayout = this.mTitlelayout;
        if (relativeLayout == null) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
        if (str.equals("left") || str.equals("auto")) {
            layoutParams.addRule(1, this.mLeftButtonLayout.getId());
            layoutParams.addRule(15);
            layoutParams.removeRule(13);
            layoutParams.leftMargin = PdrUtil.convertToScreenInt("5px", this.mInnerWidth, 0, this.mCreateScale);
        } else {
            layoutParams.addRule(13);
            layoutParams.removeRule(1);
            layoutParams.removeRule(15);
            layoutParams.leftMargin = layoutParams.rightMargin;
        }
        this.mTitlelayout.setLayoutParams(layoutParams);
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setTitleColor(String str) {
        int iStringToColor;
        TextView textView = this.mTitleView;
        if (textView != null) {
            int defaultColor = textView.getTextColors().getDefaultColor();
            try {
                iStringToColor = Color.parseColor(str);
            } catch (Exception unused) {
                iStringToColor = PdrUtil.stringToColor(str);
            }
            if (iStringToColor != defaultColor) {
                this.mTitleView.setTextColor(iStringToColor);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x01e4  */
    @Override // io.dcloud.common.DHInterface.ITitleNView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean setTitleNViewButtonStyle(int r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, java.lang.String r29, io.dcloud.common.DHInterface.IWebview r30, java.lang.String r31, java.lang.String r32, java.lang.String r33, java.lang.String r34, java.lang.String r35, java.lang.String r36, java.lang.String r37, java.lang.String r38) {
        /*
            Method dump skipped, instruction units count: 537
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.nativeObj.TitleNView.setTitleNViewButtonStyle(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setTitleNViewPadding(int i, int i2, int i3, int i4) {
        initTitleNViewLayout();
        this.mTitleNViewLayout.setPadding(i, i2, i3, i4);
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setTitleNViewSearchInputText(String str) {
        EditText editText = this.searchInput;
        if (editText != null) {
            editText.setText(str);
            this.searchInput.setSelection(str.length());
            if (this.searchInput.isFocusable()) {
                return;
            }
            this.isSetText.set(true);
            this.searchInput.setFocusable(true);
            this.searchInput.setFocusableInTouchMode(true);
            this.searchInput.requestFocus();
            this.searchInput.setFocusable(false);
            this.searchInput.setFocusableInTouchMode(false);
            this.isSetText.set(false);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setTitleOverflow(String str) {
        if (this.mTitleView == null || TextUtils.isEmpty(str)) {
            return;
        }
        if ("clip".equals(str)) {
            this.mTitleView.setEllipsize(null);
        } else {
            this.mTitleView.setEllipsize(TextUtils.TruncateAt.END);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setTitleSize(String str) {
        if (this.mTitleView == null || TextUtils.isEmpty(str) || !str.endsWith("px")) {
            return;
        }
        float textSize = this.mTitleView.getPaint().getTextSize();
        float fConvertToScreenInt = PdrUtil.convertToScreenInt(str, this.mInnerWidth, 0, this.mCreateScale);
        if (textSize != fConvertToScreenInt) {
            this.mTitleView.getPaint().setTextSize(fConvertToScreenInt);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setTitleText(String str) {
        if (this.mTitleView == null || TextUtils.isEmpty(str) || str.equals(this.mTitleView.getText())) {
            return;
        }
        this.mTitleView.setText(str);
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void startProgress() {
        Progress progress = this.mProgress;
        if (progress != null) {
            progress.setVisibility(0);
            this.mProgress.setAlphaInt(255);
            this.mProgress.setCurProgress(0);
            this.mProgress.setWebviewProgress(0);
            RelativeLayout relativeLayout = this.mTitleNViewLayout;
            if (relativeLayout != null && -1 == relativeLayout.indexOfChild(this.mProgress)) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) this.mProgress.getHeightInt());
                layoutParams.addRule(12);
                this.mTitleNViewLayout.addView(this.mProgress, layoutParams);
            }
            IFrameView iFrameView = this.mFrameViewParent;
            if (iFrameView == null || iFrameView.obtainWebView() == null || this.mFrameViewParent.obtainWebView().obtainWindowView() == null) {
                return;
            }
            if (this.mIWebviewStateListenerImpl != null) {
                this.mFrameViewParent.obtainWebView().removeStateListener(this.mIWebviewStateListenerImpl);
            }
            this.mIWebviewStateListenerImpl = new IWebviewStateListenerImpl(this.mProgress);
            this.mFrameViewParent.obtainWebView().addStateListener(this.mIWebviewStateListenerImpl);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void stopProgress() {
        Progress progress = this.mProgress;
        if (progress == null || progress.isFinish()) {
            return;
        }
        this.mProgress.finishProgress();
    }

    public class BadgeLinearLayout extends LinearLayout {
        public String badgeStr;
        public Rect canvasRect;
        public float circle4PX;
        public float circle8PX;
        public boolean isDrawRedDot;
        public Paint redDotPaint;
        public TextPaint textPaint;

        public BadgeLinearLayout(Context context) {
            super(context);
            this.isDrawRedDot = false;
            this.badgeStr = "";
            this.canvasRect = new Rect();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas canvas) {
            super.dispatchDraw(canvas);
            canvas.getClipBounds(this.canvasRect);
            if (this.isDrawRedDot && PdrUtil.isEmpty(this.badgeStr)) {
                float f = this.canvasRect.right;
                float f2 = this.circle4PX;
                canvas.drawCircle(f - f2, r0.top + f2, f2, this.redDotPaint);
            }
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            super.draw(canvas);
        }

        public void setBadgeStr(String str) {
            if (str == null || str.equals(this.badgeStr)) {
                return;
            }
            if (str.length() > 4) {
                this.badgeStr = str.trim().substring(0, 3) + TextContentBoxMeasurement.ELLIPSIS;
            } else {
                this.badgeStr = str.trim();
            }
            postInvalidate();
        }

        public void setDrawRedDot(boolean z) {
            if (this.isDrawRedDot != z) {
                this.isDrawRedDot = z;
                postInvalidate();
            }
        }

        public void setRedDotColor(int i) {
            this.redDotPaint.setColor(i);
            postInvalidate();
        }

        public BadgeLinearLayout(TitleNView titleNView, Context context, float f, int i) {
            this(context);
            Paint paint = new Paint();
            this.redDotPaint = paint;
            paint.setColor(i);
            this.redDotPaint.setAntiAlias(true);
            TextPaint textPaint = new TextPaint();
            this.textPaint = textPaint;
            textPaint.setColor(-1);
            this.textPaint.setFakeBoldText(true);
            this.textPaint.setTextAlign(Paint.Align.CENTER);
            float fConvertToScreenInt = PdrUtil.convertToScreenInt("8px", 0, 0, f);
            this.circle8PX = fConvertToScreenInt;
            this.textPaint.setTextSize(fConvertToScreenInt);
            this.circle4PX = PdrUtil.convertToScreenInt("4px", 0, 0, f);
        }
    }

    public class BadgeRelateiveLayout extends LinearLayout {
        public String badgeStr;
        public Rect canvasRect;
        public float circle4PX;
        public float circle8PX;
        public boolean isDrawRedDot;
        public Paint redDotPaint;
        public TextPaint textPaint;

        public BadgeRelateiveLayout(Context context) {
            super(context);
            this.isDrawRedDot = false;
            this.badgeStr = "";
            this.canvasRect = new Rect();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas canvas) {
            super.dispatchDraw(canvas);
            canvas.getClipBounds(this.canvasRect);
            if (this.isDrawRedDot && PdrUtil.isEmpty(this.badgeStr)) {
                float f = this.canvasRect.right;
                float f2 = this.circle4PX;
                canvas.drawCircle(f - f2, r0.top + f2, f2, this.redDotPaint);
            }
            if (this.badgeStr.length() > 0) {
                Rect rect = new Rect();
                TextPaint textPaint = this.textPaint;
                String str = this.badgeStr;
                textPaint.getTextBounds(str, 0, str.length(), rect);
                Paint paint = new Paint(this.redDotPaint);
                paint.setColor(-65536);
                RectF rectF = new RectF((this.canvasRect.right - Math.abs(rect.width())) - this.circle8PX, this.canvasRect.top, r4.right, r5 + Math.abs(rect.height()) + this.circle4PX);
                canvas.drawRoundRect(rectF, rectF.height() / 2.0f, rectF.height() / 2.0f, paint);
                Paint.FontMetrics fontMetrics = this.textPaint.getFontMetrics();
                canvas.drawText(this.badgeStr, rectF.centerX(), (rectF.centerY() - (fontMetrics.top / 2.0f)) - (fontMetrics.bottom / 2.0f), this.textPaint);
            }
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            super.draw(canvas);
        }

        public void setBadgeStr(String str) {
            if (str == null || str.equals(this.badgeStr)) {
                return;
            }
            if (str.length() > 4) {
                this.badgeStr = str.trim().substring(0, 3) + TextContentBoxMeasurement.ELLIPSIS;
            } else {
                this.badgeStr = str.trim();
            }
            postInvalidate();
        }

        public void setDrawRedDot(boolean z) {
            if (this.isDrawRedDot != z) {
                this.isDrawRedDot = z;
                postInvalidate();
            }
        }

        public void setRedDotColor(int i) {
            this.redDotPaint.setColor(i);
            postInvalidate();
        }

        public BadgeRelateiveLayout(TitleNView titleNView, Context context, float f, int i) {
            this(context);
            Paint paint = new Paint();
            this.redDotPaint = paint;
            paint.setColor(i);
            this.redDotPaint.setAntiAlias(true);
            TextPaint textPaint = new TextPaint();
            this.textPaint = textPaint;
            textPaint.setColor(-1);
            this.textPaint.setFakeBoldText(true);
            this.textPaint.setTextAlign(Paint.Align.CENTER);
            float fConvertToScreenInt = PdrUtil.convertToScreenInt("8px", 0, 0, f);
            this.circle8PX = fConvertToScreenInt;
            this.textPaint.setTextSize(fConvertToScreenInt);
            this.circle4PX = PdrUtil.convertToScreenInt("4px", 0, 0, f);
        }
    }

    @Override // io.dcloud.common.DHInterface.ITitleNView
    public void setTitleColor(int i) {
        TextView textView = this.mTitleView;
        if (textView == null || i == textView.getTextColors().getDefaultColor()) {
            return;
        }
        this.mTitleView.setTextColor(i);
    }
}
