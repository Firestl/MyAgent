package io.dcloud.common.adapter.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import io.dcloud.PdrR;
import io.dcloud.common.DHInterface.IWebview;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class WaitingView {
    public String mOncloseFunId;
    public PopupWindow mWaitingWin;
    public IWebview mWebview;
    public String uuid;
    public WaitingViewImpl waitingViewImpl;

    public class PopupWindowImpl extends PopupWindow implements View.OnTouchListener, PopupWindow.OnDismissListener {
        public boolean modal;

        public PopupWindowImpl(Context context) {
            super(context);
            this.modal = false;
            setTouchInterceptor(this);
            setOnDismissListener(this);
            setBackgroundDrawable(new BitmapDrawable(Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8)));
        }

        public void init(boolean z) {
            this.modal = z;
            setFocusable(z);
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            WaitingView.this.mWaitingWin = null;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (motionEvent.getAction() != 1 || !WaitingView.isInRect(x, y, WaitingView.this.waitingViewImpl.mAbsRect)) {
                return true;
            }
            WaitingView waitingView = WaitingView.this;
            if (!waitingView.waitingViewImpl.padlock) {
                return true;
            }
            waitingView.close();
            return false;
        }
    }

    public class WaitingViewImpl extends ViewGroup {
        public int backgroundColor;
        public int height;
        public Rect mAbsRect;
        public ProgressBar mProgressBar;
        public RectF mRectF;
        public String mTextAligin;
        public String mTextColor;
        public TextView mTitleView;
        public boolean padlock;
        public Paint paint;
        public PopupWindow pw;
        public float round;
        public int width;

        public WaitingViewImpl(Context context, PopupWindow popupWindow, String str) {
            super(context);
            this.mProgressBar = null;
            this.mTextAligin = null;
            this.mTextColor = null;
            this.mTitleView = null;
            this.round = 10.0f;
            this.paint = new Paint();
            this.mRectF = null;
            this.mAbsRect = null;
            this.backgroundColor = 0;
            this.pw = null;
            this.pw = popupWindow;
            setPadding(10, 10, 10, 10);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas canvas) {
            this.paint.setColor(this.backgroundColor);
            this.paint.setAntiAlias(true);
            RectF rectF = this.mRectF;
            float f = this.round;
            canvas.drawRoundRect(rectF, f, f, this.paint);
            super.dispatchDraw(canvas);
        }

        public void initBackground(int i) {
            this.backgroundColor = i;
        }

        public void initProgressBar(String str, int i) {
            ProgressBar progressBar = (ProgressBar) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(PdrR.LAYOUT_SNOW_BLACK_PROGRESS, (ViewGroup) null).findViewById(PdrR.ID_PROGRESSBAR);
            this.mProgressBar = progressBar;
            if (progressBar.getParent() != null) {
                ((ViewGroup) this.mProgressBar.getParent()).removeView(this.mProgressBar);
            }
            addView(this.mProgressBar);
        }

        public void initProgressBar1(int i) {
            ProgressBar progressBar = new ProgressBar(getContext());
            this.mProgressBar = progressBar;
            addView(progressBar);
        }

        public void initTitleView(String str) {
            this.mTitleView = new TextView(getContext()) { // from class: io.dcloud.common.adapter.ui.WaitingView.WaitingViewImpl.1
                @Override // android.widget.TextView, android.view.View
                public void onMeasure(int i, int i2) {
                    int i3;
                    String string = getText().toString();
                    int i4 = 0;
                    if (TextUtils.isEmpty(string)) {
                        i3 = 0;
                    } else {
                        int length = string.split("\n").length;
                        TextPaint paint = getPaint();
                        i3 = 0;
                        int i5 = 0;
                        while (i4 < length) {
                            float fCeil = (float) Math.ceil(Layout.getDesiredWidth(r8[i4], paint));
                            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
                            i3 += fontMetricsInt.bottom - fontMetricsInt.top;
                            if (fCeil > i5) {
                                i5 = (int) fCeil;
                            }
                            i4++;
                        }
                        i4 = i5;
                    }
                    setMeasuredDimension(i4, i3);
                }
            };
            WaitingView.this.waitingViewImpl.mTitleView.setTextColor(-1);
            String str2 = this.mTextAligin;
            if (str2 == null) {
                WaitingView.this.waitingViewImpl.mTitleView.setGravity(17);
            } else if ("left".equals(str2)) {
                WaitingView.this.waitingViewImpl.mTitleView.setGravity(3);
            } else if ("right".equals(this.mTextAligin)) {
                WaitingView.this.waitingViewImpl.mTitleView.setGravity(5);
            } else {
                WaitingView.this.waitingViewImpl.mTitleView.setGravity(17);
            }
            this.mTitleView.setText(str);
            addView(this.mTitleView);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            int childCount = getChildCount();
            getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = getPaddingRight();
            int paddingBottom = getPaddingBottom();
            int i5 = i3 - i;
            int i6 = i4 - i2;
            int i7 = i2 + paddingTop;
            int measuredHeight = this.mProgressBar.getMeasuredHeight();
            TextView textView = this.mTitleView;
            int measuredHeight2 = measuredHeight + (textView != null ? textView.getMeasuredHeight() + paddingTop : 0);
            int i8 = (i6 - paddingTop) - paddingBottom;
            int i9 = i7 + (i8 > measuredHeight2 ? (i8 - measuredHeight2) >> 1 : 0);
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = getChildAt(i10);
                int measuredWidth = ((i5 - childAt.getMeasuredWidth()) >> 1) + 0;
                int iMin = Math.min(childAt.getMeasuredWidth() + measuredWidth, i3 - paddingRight);
                int iMin2 = Math.min(childAt.getMeasuredHeight() + i9, i4 - paddingBottom);
                childAt.layout(measuredWidth, i9, iMin, iMin2);
                i9 = iMin2 + paddingTop;
            }
        }

        @Override // android.view.View
        public void onMeasure(int i, int i2) {
            int childCount = getChildCount();
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = getPaddingRight();
            int paddingBottom = getPaddingBottom();
            measureChildren(i, i2);
            int i3 = paddingTop;
            int i4 = 0;
            int iMax = 0;
            while (i4 < childCount) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() != 8) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    iMax = Math.max(iMax, measuredWidth);
                    i3 += measuredHeight + (i4 == childCount + (-1) ? 0 : paddingTop);
                }
                i4++;
            }
            int i5 = iMax + paddingLeft + paddingRight;
            int i6 = i3 + paddingBottom;
            int i7 = this.width;
            if (i7 != -2) {
                i5 = i7;
            }
            int i8 = this.height;
            if (i8 != -2) {
                i6 = i8;
            }
            setMeasuredDimension(i5, i6);
            this.mRectF = new RectF(0.0f, 0.0f, i5, i6);
            this.mAbsRect = new Rect(0, 0, i5, i6);
            this.pw.update(i5, i6);
        }
    }

    public WaitingView(IWebview iWebview) {
        this(iWebview, null, null, null);
    }

    public static boolean isInRect(int i, int i2, Rect rect) {
        return i > rect.left && i < rect.right && i2 > rect.top && i2 < rect.bottom;
    }

    public void close() {
        PopupWindow popupWindow = this.mWaitingWin;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public void updateTitle(String str) {
        WaitingViewImpl waitingViewImpl = this.waitingViewImpl;
        TextView textView = waitingViewImpl.mTitleView;
        if (textView == null) {
            waitingViewImpl.initTitleView(str);
        } else {
            textView.setText(str);
        }
    }

    public WaitingView(IWebview iWebview, String str, JSONObject jSONObject, String str2) {
        this.mWaitingWin = null;
        this.waitingViewImpl = null;
        this.mWebview = iWebview;
        this.mOncloseFunId = str2;
        Context context = iWebview.getContext();
        PopupWindowImpl popupWindowImpl = new PopupWindowImpl(context);
        popupWindowImpl.init(false);
        popupWindowImpl.setOutsideTouchable(false);
        WaitingViewImpl waitingViewImpl = new WaitingViewImpl(context, popupWindowImpl, "");
        this.waitingViewImpl = waitingViewImpl;
        waitingViewImpl.initProgressBar(null, -872415232);
        if (str != null) {
            this.waitingViewImpl.initTitleView(str);
        }
        popupWindowImpl.setContentView(this.waitingViewImpl);
        WaitingViewImpl waitingViewImpl2 = this.waitingViewImpl;
        waitingViewImpl2.width = -2;
        waitingViewImpl2.height = -2;
        popupWindowImpl.setWindowLayoutMode(-2, -2);
        this.mWaitingWin = popupWindowImpl;
        popupWindowImpl.showAtLocation(iWebview.obtainWindowView(), 17, 0, 0);
    }
}
