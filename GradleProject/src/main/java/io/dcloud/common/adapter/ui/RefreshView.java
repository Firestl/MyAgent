package io.dcloud.common.adapter.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import io.dcloud.base.R;
import io.dcloud.common.adapter.ui.fresh.ILoadingLayout;
import io.dcloud.common.adapter.ui.fresh.PullToRefreshBase;
import io.dcloud.common.adapter.ui.webview.DCWebView;
import io.dcloud.common.adapter.util.CanvasHelper;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class RefreshView implements PullToRefreshBase.OnStateChangeListener {
    public static final String TAG = "RefreshView";
    public RectF dst;
    public int fontSize;
    public int icon_x;
    public int icon_y;
    public String mContent_down;
    public String mContent_over;
    public String mContent_refresh;
    public AdaFrameView mFrameView;
    public JSONObject mJSONObject;
    public AdaWebview mWebview;
    public float mWebviewScale;
    public Rect src;
    public float startX;
    public float startY;
    public Bitmap mIcon = null;
    public float mFontScale = 1.2f;
    public int changeStateHeight = 100;
    public int maxPullHeight = 100;
    public Paint paint = new Paint();
    public int index = 0;
    public int MAX_FRAME_COUNT = 9;
    public int HEIGHT = 25;
    public Timer mUpdateProgressBar = null;
    public int contentLeft = 0;
    public int contentTop = 0;
    public String mShowContent = null;
    public ILoadingLayout.State mState = ILoadingLayout.State.RESET;

    public RefreshView(AdaFrameView adaFrameView, AdaWebview adaWebview) {
        this.mFrameView = adaFrameView;
        this.mContent_down = adaFrameView.getContext().getString(R.string.dcloud_drop_down_refresh1);
        this.mContent_over = adaFrameView.getContext().getString(R.string.dcloud_drop_down_refresh2);
        this.mContent_refresh = adaFrameView.getContext().getString(R.string.dcloud_drop_down_refresh3);
        this.mWebview = adaWebview;
        this.mWebviewScale = adaWebview.getScale();
        this.paint.setAntiAlias(true);
    }

    private void computePosition(Paint paint) {
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int i = this.changeStateHeight;
        int i2 = this.HEIGHT;
        int i3 = (i - i2) >> 1;
        this.icon_y = i3;
        this.contentTop = i3 + ((i2 - (fontMetricsInt.bottom - fontMetricsInt.top)) / 2);
        int i4 = this.mFrameView.obtainApp().getInt(0);
        float fMeasureText = paint.measureText(this.mShowContent);
        float f = i4;
        float f2 = 0.02f * f;
        float f3 = this.HEIGHT;
        int i5 = ((int) (((f - f2) - f3) - fMeasureText)) / 2;
        this.icon_x = i5;
        this.contentLeft = (int) (i5 + f2 + f3);
    }

    private void startUpdateScreenTimer() {
        stopUpdateScreenTimer();
        if (this.mFrameView.obtainMainView() != null) {
            Timer timer = new Timer();
            this.mUpdateProgressBar = timer;
            timer.schedule(new TimerTask() { // from class: io.dcloud.common.adapter.ui.RefreshView.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (RefreshView.this.mFrameView.obtainMainView() == null) {
                        cancel();
                    }
                    try {
                        RefreshView.this.updateScreen();
                        RefreshView.this.mFrameView.obtainMainView().postInvalidate(0, 0, RefreshView.this.mFrameView.obtainFrameOptions().width, RefreshView.this.maxPullHeight);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }, 0L, 100L);
        }
    }

    private void stopUpdateScreenTimer() {
        Timer timer = this.mUpdateProgressBar;
        if (timer != null) {
            timer.cancel();
            this.mUpdateProgressBar = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateScreen() {
        int i = this.index + 1;
        this.index = i;
        if (i >= this.MAX_FRAME_COUNT) {
            this.index = 0;
        }
        Rect rect = this.src;
        int i2 = this.HEIGHT;
        int i3 = this.index;
        rect.set(i2 * i3, 0, (i3 + 1) * i2, i2);
    }

    public void changeStringInfo(String str) {
        this.mShowContent = str;
        int length = str.length();
        float[] fArr = new float[length];
        this.paint.getTextWidths(str, fArr);
        for (int i = 0; i < length; i++) {
            float f = fArr[i];
        }
        this.mFrameView.obtainMainView().postInvalidate(0, 0, this.mFrameView.obtainFrameOptions().width, this.maxPullHeight);
    }

    public void init(String str) {
        this.fontSize = (int) (DeviceInfo.DEFAULT_FONT_SIZE * DeviceInfo.sDensity * this.mFontScale);
        this.paint.setAntiAlias(true);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(PlatformUtil.getResInputStream(AbsoluteConst.RES_PROGRASS_SNOW1), null, options);
        this.mIcon = bitmapDecodeStream;
        this.HEIGHT = bitmapDecodeStream.getHeight();
        int i = this.HEIGHT;
        this.src = new Rect(0, 0, i, i);
        float f = this.HEIGHT;
        this.dst = new RectF(0.0f, 150.0f, f, f);
        Logger.d(TAG, "height=" + this.changeStateHeight + ";range=" + this.maxPullHeight + ";contentdown=" + this.mShowContent);
        this.MAX_FRAME_COUNT = bitmapDecodeStream.getWidth() / this.HEIGHT;
    }

    public void onResize() {
        parseJsonOption(this.mJSONObject);
    }

    @Override // io.dcloud.common.adapter.ui.fresh.PullToRefreshBase.OnStateChangeListener
    public void onStateChanged(ILoadingLayout.State state, boolean z) {
        DCWebView dCWebView;
        boolean z2 = this.mState != state;
        this.mState = state;
        if (z2) {
            if (state == ILoadingLayout.State.RESET) {
                Logger.d("refresh", "RefreshView RESET");
                changeStringInfo(this.mContent_down);
                stopUpdateScreenTimer();
                return;
            }
            if (state == ILoadingLayout.State.PULL_TO_REFRESH) {
                Logger.d("refresh", "RefreshView PULL_TO_REFRESH");
                changeStringInfo(this.mContent_down);
                return;
            }
            if (state == ILoadingLayout.State.RELEASE_TO_REFRESH) {
                Logger.d("refresh", "RefreshView RELEASE_TO_REFRESH");
                changeStringInfo(this.mContent_over);
            } else if (state == ILoadingLayout.State.REFRESHING) {
                Logger.d("refresh", "RefreshView REFRESHING");
                changeStringInfo(this.mContent_refresh);
                startUpdateScreenTimer();
                AdaWebview adaWebview = this.mWebview;
                if (adaWebview == null || (dCWebView = adaWebview.mWebViewImpl) == null) {
                    return;
                }
                dCWebView.onRefresh(3);
            }
        }
    }

    public void paint(Canvas canvas, int i, int i2) {
        canvas.drawColor(-1907998);
        this.paint.setColor(-16777216);
        this.paint.setTextSize(this.fontSize);
        if (this.mShowContent == null || this.mIcon == null) {
            return;
        }
        computePosition(this.paint);
        CanvasHelper.drawString(canvas, this.mShowContent, this.contentLeft + i, this.contentTop + i2, 17, this.paint);
        RectF rectF = this.dst;
        int i3 = i + this.icon_x;
        int i4 = i2 + this.icon_y;
        int i5 = this.HEIGHT;
        rectF.set(i3, i4, i3 + i5, i4 + i5);
        canvas.drawBitmap(this.mIcon, this.src, this.dst, this.paint);
    }

    public void parseJsonOption(JSONObject jSONObject) {
        try {
            JSONObject jSONObjectCombinJSONObject = JSONUtil.combinJSONObject(this.mJSONObject, jSONObject);
            this.mJSONObject = jSONObjectCombinJSONObject;
            if (!jSONObjectCombinJSONObject.isNull("height")) {
                this.changeStateHeight = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObjectCombinJSONObject, "height"), this.mWebview.mFrameView.mViewOptions.height, this.changeStateHeight, this.mWebviewScale);
            }
            if (!jSONObjectCombinJSONObject.isNull(AbsoluteConst.PULL_REFRESH_RANGE)) {
                this.maxPullHeight = PdrUtil.convertToScreenInt(jSONObjectCombinJSONObject.getString(AbsoluteConst.PULL_REFRESH_RANGE), this.mWebview.mFrameView.mViewOptions.height, this.maxPullHeight, this.mWebviewScale);
            }
            if (!jSONObjectCombinJSONObject.isNull(AbsoluteConst.PULL_REFRESH_CONTENTDOWN)) {
                this.mContent_down = JSONUtil.getString(jSONObjectCombinJSONObject.getJSONObject(AbsoluteConst.PULL_REFRESH_CONTENTDOWN), AbsoluteConst.PULL_REFRESH_CAPTION);
            }
            if (!jSONObjectCombinJSONObject.isNull(AbsoluteConst.PULL_REFRESH_CONTENTOVER)) {
                this.mContent_over = JSONUtil.getString(jSONObjectCombinJSONObject.getJSONObject(AbsoluteConst.PULL_REFRESH_CONTENTOVER), AbsoluteConst.PULL_REFRESH_CAPTION);
            }
            if (!jSONObjectCombinJSONObject.isNull(AbsoluteConst.PULL_REFRESH_CONTENTREFRESH)) {
                this.mContent_refresh = JSONUtil.getString(jSONObjectCombinJSONObject.getJSONObject(AbsoluteConst.PULL_REFRESH_CONTENTREFRESH), AbsoluteConst.PULL_REFRESH_CAPTION);
            }
            init(null);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
