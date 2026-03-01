package io.dcloud.common.adapter.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import io.dcloud.base.R;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PullRefreshView extends View {
    public static final byte FLAG_MOVEED = 1;
    public static final byte FLAG_NO_THING = -1;
    public static final byte FLAG_STARTED = 0;
    public static int HEIGHT = 25;
    public static int MAX_FRAME_COUNT = 9;
    public static final byte STATE_NO_REFRESH = 0;
    public static final byte STATE_ON_MOVE_ING = 1;
    public static final byte STATE_ON_OVER = 2;
    public static final byte STATE_ON_REFRESH_ING = 3;
    public static final String TAG = "PullRefreshView";
    public static final byte TYPE_PULL_DOWN_REFRESH = 1;
    public static final byte TYPE_PULL_UP_REFRESH = 0;
    public static final int color_tr = 16711920;
    public byte SCROLL_STATE_MAX;
    public byte SCROLL_STATE_MIDDLE;
    public byte SCROLL_STATE_MIN;
    public int changeStateHeight;
    public int contentLeft;
    public int contentTop;
    public int contentWidth;
    public RectF dst;
    public int fontSize;
    public int icon_x;
    public int icon_y;
    public int index;
    public float lastScrollY;
    public boolean mCaptureTouchEnd;
    public String mContent_down;
    public String mContent_over;
    public String mContent_refresh;
    public boolean mEnableScrollMaxHeight;
    public boolean mEnableScrollMinHeight;
    public byte mFlag;
    public float mFontScale;
    public Bitmap mIcon;
    public AdaFrameItem mParent;
    public boolean mRefreshState;
    public int mScrollHeight;
    public byte mScrollState;
    public String mSecInfo;
    public String mShowContent;
    public byte mState;
    public byte mType;
    public Timer mUpdateProgressBar;
    public AdaWebview mWebview;
    public float mWebviewScale;
    public int maxPullHeight;
    public Paint paint;
    public int sScreenHeight;
    public int sScreenWidth;
    public Rect src;
    public float startX;
    public float startY;
    public boolean touch_started;

    /* JADX INFO: renamed from: io.dcloud.common.adapter.ui.PullRefreshView$2, reason: invalid class name */
    public static class AnonymousClass2 extends TimerTask {
        public View child;
        public int fromX;
        public int fromY;
        public ViewGroup parent;
        public int timesCount;
        public int toX;
        public int toY;
        public int vX;
        public int vY;
        public final /* synthetic */ Timer val$_timer;
        public final /* synthetic */ ViewGroup val$pParent;
        public final /* synthetic */ View val$pView;
        public final /* synthetic */ int val$x;
        public final /* synthetic */ int val$y;
        public final int TIME = 500;
        public int flagTimes = 1;

        public AnonymousClass2(View view, ViewGroup viewGroup, int i, int i2, Timer timer) {
            this.val$pView = view;
            this.val$pParent = viewGroup;
            this.val$x = i;
            this.val$y = i2;
            this.val$_timer = timer;
            this.child = view;
            this.parent = viewGroup;
            this.toX = i;
            this.toY = i2;
            this.fromX = viewGroup.getScrollX();
            this.fromY = this.parent.getScrollY();
            this.timesCount = 10;
            this.vX = Math.abs(this.toX - this.fromX) / this.timesCount;
            int iAbs = Math.abs(this.toY - this.fromY) / this.timesCount;
            this.vY = iAbs;
            if (iAbs >= 5) {
                this.vY = 5;
                this.timesCount = Math.abs(this.toY - this.fromY) / this.vY;
            }
            this.vX = Math.abs(this.toX - this.fromX) / this.timesCount;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            int i = this.fromX + this.vX;
            int i2 = this.fromY + this.vY;
            if (this.flagTimes == this.timesCount) {
                i = this.toX;
                i2 = this.toY;
            }
            PullRefreshView.scrollToByMessage(this.parent, i, i2);
            if (this.flagTimes == this.timesCount) {
                if (this.child != null) {
                    this.parent.post(new Runnable() { // from class: io.dcloud.common.adapter.ui.PullRefreshView.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                            anonymousClass2.parent.removeView(anonymousClass2.child);
                        }
                    });
                }
                this.val$_timer.cancel();
            }
            this.fromX = i;
            this.fromY = i2;
            this.flagTimes++;
        }
    }

    public PullRefreshView(AdaFrameItem adaFrameItem, AdaWebview adaWebview) {
        super(adaFrameItem.getContext());
        this.mType = (byte) 1;
        this.mShowContent = null;
        this.mSecInfo = null;
        this.mIcon = null;
        this.mFontScale = 1.2f;
        this.changeStateHeight = 100;
        this.maxPullHeight = 100;
        this.paint = new Paint();
        this.index = 0;
        this.contentLeft = 0;
        this.contentTop = 0;
        this.contentWidth = 0;
        this.mState = (byte) 0;
        this.touch_started = false;
        this.mCaptureTouchEnd = false;
        this.mFlag = (byte) 0;
        this.mScrollHeight = 0;
        this.SCROLL_STATE_MIN = (byte) 0;
        this.SCROLL_STATE_MAX = (byte) 1;
        this.SCROLL_STATE_MIDDLE = (byte) 2;
        this.mScrollState = (byte) 0;
        this.mEnableScrollMinHeight = true;
        this.mEnableScrollMaxHeight = true;
        this.mRefreshState = false;
        this.mUpdateProgressBar = null;
        this.mParent = adaFrameItem;
        this.mWebview = adaWebview;
        this.mContent_down = getResources().getString(R.string.dcloud_drop_down_refresh1);
        this.mContent_over = getResources().getString(R.string.dcloud_drop_down_refresh2);
        this.mContent_refresh = getResources().getString(R.string.dcloud_drop_down_refresh3);
        this.sScreenWidth = adaWebview.obtainApp().getInt(0);
        this.sScreenHeight = adaWebview.obtainApp().getInt(1);
        this.mWebviewScale = adaWebview.getScale();
        init(null);
        this.paint.setAntiAlias(true);
    }

    public static void scrollToByMessage(final View view, final int i, final int i2) {
        view.post(new Runnable() { // from class: io.dcloud.common.adapter.ui.PullRefreshView.3
            @Override // java.lang.Runnable
            public void run() {
                view.scrollTo(i, i2);
            }
        });
    }

    public static void smoothScrollTo(ViewGroup viewGroup, View view, int i, int i2, int i3) {
        Timer timer = new Timer();
        timer.schedule(new AnonymousClass2(view, viewGroup, i, i2, timer), 0L, i3);
    }

    private void startUpdateScreenTimer() {
        stopUpdateScreenTimer();
        Timer timer = new Timer();
        this.mUpdateProgressBar = timer;
        timer.schedule(new TimerTask() { // from class: io.dcloud.common.adapter.ui.PullRefreshView.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                PullRefreshView.this.updateScreen();
            }
        }, 0L, 100L);
    }

    private void stopUpdateScreenTimer() {
        Timer timer = this.mUpdateProgressBar;
        if (timer != null) {
            timer.cancel();
            this.mUpdateProgressBar = null;
        }
    }

    private boolean updateScrollState(byte b) {
        this.mScrollState = b;
        boolean z = false;
        if (b == this.SCROLL_STATE_MAX) {
            this.mScrollHeight = this.maxPullHeight;
            if (this.mEnableScrollMaxHeight) {
                this.mEnableScrollMaxHeight = false;
                z = true;
            }
            this.mEnableScrollMinHeight = true;
            return z;
        }
        if (b == this.SCROLL_STATE_MIN) {
            this.mEnableScrollMaxHeight = true;
            this.mScrollHeight = 0;
            if (!this.mEnableScrollMinHeight) {
                return false;
            }
            this.mEnableScrollMinHeight = false;
        } else {
            if (b != this.SCROLL_STATE_MIDDLE) {
                return false;
            }
            this.mEnableScrollMinHeight = true;
            this.mEnableScrollMaxHeight = true;
        }
        return true;
    }

    public void changeStringInfo(String str) {
        this.mShowContent = str;
        this.fontSize = (int) (DeviceInfo.DEFAULT_FONT_SIZE * DeviceInfo.sDensity * this.mFontScale);
        this.paint.setAntiAlias(true);
        int length = str.length();
        float[] fArr = new float[length];
        this.paint.getTextWidths(str, fArr);
        float f = 0.0f;
        for (int i = 0; i < length; i++) {
            f += fArr[i];
        }
        this.contentWidth = (int) f;
    }

    public void init(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(PlatformUtil.getResInputStream(AbsoluteConst.RES_PROGRASS_SNOW1), null, options);
        this.mIcon = bitmapDecodeStream;
        int height = bitmapDecodeStream.getHeight();
        HEIGHT = height;
        float f = this.sScreenWidth;
        this.contentLeft = (int) (0.43f * f);
        this.icon_x = ((int) (f * 0.41f)) - height;
        int i = HEIGHT;
        this.src = new Rect(0, 0, i, i);
        float f2 = HEIGHT;
        this.dst = new RectF(0.0f, 150.0f, f2, f2);
        MAX_FRAME_COUNT = bitmapDecodeStream.getWidth() / HEIGHT;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.mState != 0) {
            canvas.drawColor(-1907998);
            this.paint.setColor(-16777216);
            this.paint.setTextSize(this.fontSize);
            canvas.drawText(this.mShowContent, this.contentLeft, this.contentTop, this.paint);
            Bitmap bitmap = this.mIcon;
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, this.src, this.dst, this.paint);
            }
        }
    }

    public void onExecuting() {
        Logger.d(TAG, "onExecuting");
        this.mState = (byte) 3;
        this.mRefreshState = true;
        this.mFlag = (byte) -1;
        Logger.d(TAG, "onExecuting; mFlag = FLAG_NO_THING");
        changeStringInfo(this.mContent_refresh);
        this.mScrollHeight = this.changeStateHeight;
        smoothScrollTo((ViewGroup) this.mParent.obtainMainView(), null, 0, -this.changeStateHeight, 1);
    }

    public boolean onMove(float f, float f2) {
        int i = (int) (((f2 - this.lastScrollY) * this.maxPullHeight) / this.mWebview.mFrameView.mViewOptions.height);
        if (Math.abs(i) < 1) {
            if (this.mScrollHeight > 0) {
                return true;
            }
            float f3 = i;
            if ((f3 <= 0.5f && f3 >= -0.5f) || this.mFlag != 0) {
                return false;
            }
            this.mFlag = (byte) 1;
            return false;
        }
        int i2 = this.mScrollHeight + i;
        this.mScrollHeight = i2;
        boolean zUpdateScrollState = i2 >= this.maxPullHeight ? updateScrollState(this.SCROLL_STATE_MAX) : i2 <= 0 ? updateScrollState(this.SCROLL_STATE_MIN) : updateScrollState(this.SCROLL_STATE_MIDDLE);
        byte b = this.mState;
        if (b != 3) {
            if (b == 1 && this.mScrollHeight >= this.changeStateHeight) {
                this.mState = (byte) 2;
                changeStringInfo(this.mContent_over);
            } else if (b == 2 && this.mScrollHeight < this.changeStateHeight) {
                this.mState = (byte) 1;
                changeStringInfo(this.mContent_down);
            }
        }
        if (zUpdateScrollState) {
            if (this.mFlag == 0) {
                this.mFlag = (byte) 1;
                Logger.d(TAG, "onMove; mFlag=FLAG_MOVEED");
                startUpdateScreenTimer();
            }
            this.mParent.obtainMainView().scrollBy(0, -i);
            this.lastScrollY = f2;
        }
        return zUpdateScrollState;
    }

    public void onPullDown_end() {
        if (this.mScrollHeight <= this.changeStateHeight) {
            this.mState = (byte) 0;
            this.mScrollHeight = 0;
            this.mFlag = (byte) -1;
            Logger.d(TAG, "onPullDown_end; mFlag = FLAG_NO_THING");
            changeStringInfo(this.mContent_down);
            Timer timer = this.mUpdateProgressBar;
            if (timer != null) {
                timer.cancel();
            }
            this.mParent.obtainMainView().scrollTo(0, 0);
            stopUpdateScreenTimer();
        } else {
            smoothScrollToStateHeight(true);
        }
        this.mRefreshState = false;
    }

    public void onPullDown_start(float f, float f2) {
        if (this.touch_started) {
            return;
        }
        Logger.d(TAG, "onPullDown_start");
        this.startX = f;
        this.startY = f2;
        this.lastScrollY = f2;
        if (getParent() == null) {
            ViewGroup viewGroup = (ViewGroup) this.mParent.obtainMainView();
            int i = this.maxPullHeight;
            ViewOptions viewOptions = this.mWebview.mViewOptions;
            viewGroup.addView(this, 0, new AbsoluteLayout.LayoutParams(-1, i, viewOptions.left, viewOptions.top - i));
        }
        byte b = this.mState;
        if (b == 0) {
            this.mState = (byte) 1;
            this.mFlag = (byte) 0;
        } else if (b == 3) {
            this.mFlag = (byte) 0;
        }
        this.touch_started = true;
    }

    public void parseJsonOption(JSONObject jSONObject) {
        try {
            if (!jSONObject.isNull("height")) {
                int iConvertToScreenInt = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject, "height"), this.mWebview.mFrameView.mViewOptions.height, this.changeStateHeight, this.mWebviewScale);
                this.changeStateHeight = iConvertToScreenInt;
                this.maxPullHeight = iConvertToScreenInt;
            }
            if (!jSONObject.isNull(AbsoluteConst.PULL_REFRESH_RANGE)) {
                this.maxPullHeight = PdrUtil.convertToScreenInt(jSONObject.getString(AbsoluteConst.PULL_REFRESH_RANGE), this.mWebview.mFrameView.mViewOptions.height, this.changeStateHeight, this.mWebviewScale);
            }
            if (!jSONObject.isNull(AbsoluteConst.PULL_REFRESH_CONTENTDOWN)) {
                changeStringInfo(JSONUtil.getString(jSONObject.getJSONObject(AbsoluteConst.PULL_REFRESH_CONTENTDOWN), AbsoluteConst.PULL_REFRESH_CAPTION));
            }
            if (!jSONObject.isNull(AbsoluteConst.PULL_REFRESH_CONTENTOVER)) {
                this.mContent_over = JSONUtil.getString(jSONObject.getJSONObject(AbsoluteConst.PULL_REFRESH_CONTENTOVER), AbsoluteConst.PULL_REFRESH_CAPTION);
            }
            if (!jSONObject.isNull(AbsoluteConst.PULL_REFRESH_CONTENTREFRESH)) {
                this.mContent_refresh = JSONUtil.getString(jSONObject.getJSONObject(AbsoluteConst.PULL_REFRESH_CONTENTREFRESH), AbsoluteConst.PULL_REFRESH_CAPTION);
            }
            int iMax = Math.max(this.maxPullHeight - this.changeStateHeight, 0);
            Paint.FontMetricsInt fontMetricsInt = DeviceInfo.sPaint.getFontMetricsInt();
            int i = this.changeStateHeight;
            this.contentTop = (i >> 1) + iMax + ((fontMetricsInt.bottom - fontMetricsInt.top) >> 1);
            int i2 = iMax + ((i - HEIGHT) >> 1);
            this.icon_y = i2;
            this.dst.set(this.icon_x, i2, r2 + r0, i2 + r0);
            Logger.d(TAG, "height=" + this.changeStateHeight + ";range=" + this.maxPullHeight + ";contentdown=" + this.mShowContent);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void setColorByParentChild(View view) {
        for (int i = 0; i != 2; i++) {
            view = (View) view.getParent();
            view.setBackgroundColor(color_tr);
        }
    }

    public void smoothScrollToStateHeight(boolean z) {
        if (z) {
            this.mScrollHeight = this.changeStateHeight;
            smoothScrollTo((ViewGroup) this.mParent.obtainMainView(), null, 0, -this.changeStateHeight, 1);
        } else if (this.mScrollHeight > this.changeStateHeight) {
            smoothScrollToStateHeight(true);
        } else {
            this.mRefreshState = false;
        }
    }

    public void updateScreen() {
        int i = this.index + 1;
        this.index = i;
        if (i >= MAX_FRAME_COUNT) {
            this.index = 0;
        }
        Rect rect = this.src;
        int i2 = HEIGHT;
        int i3 = this.index;
        rect.set(i2 * i3, 0, (i3 + 1) * i2, i2);
        postInvalidate();
    }
}
