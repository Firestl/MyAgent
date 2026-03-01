package io.dcloud.feature.weex_switch;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import com.taobao.weex.R;
import io.dcloud.common.adapter.util.Logger;

/* JADX INFO: loaded from: classes3.dex */
public class SwitchButton extends View implements Checkable {
    public final int ANIMATE_STATE_DRAGING;
    public final int ANIMATE_STATE_NONE;
    public final int ANIMATE_STATE_PENDING_DRAG;
    public final int ANIMATE_STATE_PENDING_RESET;
    public final int ANIMATE_STATE_PENDING_SETTLE;
    public final int ANIMATE_STATE_SWITCH;
    public ViewState afterState;
    public int animateState;
    public Animator.AnimatorListener animatorListener;
    public ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
    public final ArgbEvaluator argbEvaluator;
    public int background;
    public ViewState beforeState;
    public int borderWidth;
    public float bottom;
    public float buttonMaxX;
    public float buttonMinX;
    public Paint buttonPaint;
    public float buttonRadius;
    public float centerX;
    public float centerY;
    public int checkLineColor;
    public float checkLineLength;
    public int checkLineWidth;
    public int checkedColor;
    public float checkedLineOffsetX;
    public float checkedLineOffsetY;
    public float dragFraction;
    public boolean enableEffect;
    public float height;
    public boolean isChecked;
    public boolean isEventBroadcast;
    public boolean isInit;
    public boolean isTouchingDown;
    public boolean isUiInited;
    public float left;
    public OnCheckedChangeListener onCheckedChangeListener;
    public Paint paint;
    public Runnable postPendingDrag;
    public RectF rect;
    public float right;
    public int shadowColor;
    public boolean shadowEffect;
    public int shadowOffset;
    public int shadowRadius;
    public boolean showIndicator;
    public float top;
    public long touchDownTime;
    public int uncheckCircleColor;
    public float uncheckCircleOffsetX;
    public float uncheckCircleRadius;
    public int uncheckCircleWidth;
    public int uncheckColor;
    public ValueAnimator valueAnimator;
    public float viewRadius;
    public ViewState viewState;
    public float width;
    public static final int DEFAULT_WIDTH = dp2pxInt(58.0f);
    public static final int DEFAULT_HEIGHT = dp2pxInt(36.0f);

    public interface OnCheckedChangeListener {
        void onCheckedChanged(SwitchButton switchButton, boolean z);
    }

    public static class ViewState {
        public float buttonX;
        public int checkStateColor;
        public int checkedLineColor;
        public float radius;

        /* JADX INFO: Access modifiers changed from: private */
        public void copy(ViewState viewState) {
            this.buttonX = viewState.buttonX;
            this.checkStateColor = viewState.checkStateColor;
            this.checkedLineColor = viewState.checkedLineColor;
            this.radius = viewState.radius;
        }
    }

    public SwitchButton(Context context) {
        super(context);
        this.ANIMATE_STATE_NONE = 0;
        this.ANIMATE_STATE_PENDING_DRAG = 1;
        this.ANIMATE_STATE_DRAGING = 2;
        this.ANIMATE_STATE_PENDING_RESET = 3;
        this.ANIMATE_STATE_PENDING_SETTLE = 4;
        this.ANIMATE_STATE_SWITCH = 5;
        this.isInit = true;
        this.rect = new RectF();
        this.animateState = 0;
        this.argbEvaluator = new ArgbEvaluator();
        this.isTouchingDown = false;
        this.isUiInited = false;
        this.isEventBroadcast = false;
        this.postPendingDrag = new Runnable() { // from class: io.dcloud.feature.weex_switch.SwitchButton.1
            @Override // java.lang.Runnable
            public void run() {
                if (SwitchButton.this.isInAnimating()) {
                    return;
                }
                SwitchButton.this.pendingDragState();
            }
        };
        this.animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: io.dcloud.feature.weex_switch.SwitchButton.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                int i = SwitchButton.this.animateState;
                if (i == 1 || i == 3 || i == 4) {
                    SwitchButton.this.viewState.checkedLineColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(fFloatValue, Integer.valueOf(SwitchButton.this.beforeState.checkedLineColor), Integer.valueOf(SwitchButton.this.afterState.checkedLineColor))).intValue();
                    SwitchButton.this.viewState.radius = SwitchButton.this.beforeState.radius + ((SwitchButton.this.afterState.radius - SwitchButton.this.beforeState.radius) * fFloatValue);
                    if (SwitchButton.this.animateState != 1) {
                        SwitchButton.this.viewState.buttonX = SwitchButton.this.beforeState.buttonX + ((SwitchButton.this.afterState.buttonX - SwitchButton.this.beforeState.buttonX) * fFloatValue);
                    }
                    SwitchButton.this.viewState.checkStateColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(fFloatValue, Integer.valueOf(SwitchButton.this.beforeState.checkStateColor), Integer.valueOf(SwitchButton.this.afterState.checkStateColor))).intValue();
                } else if (i == 5) {
                    SwitchButton.this.viewState.buttonX = SwitchButton.this.beforeState.buttonX + ((SwitchButton.this.afterState.buttonX - SwitchButton.this.beforeState.buttonX) * fFloatValue);
                    float f = (SwitchButton.this.viewState.buttonX - SwitchButton.this.buttonMinX) / (SwitchButton.this.buttonMaxX - SwitchButton.this.buttonMinX);
                    SwitchButton.this.viewState.checkStateColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(f, Integer.valueOf(SwitchButton.this.uncheckColor), Integer.valueOf(SwitchButton.this.checkedColor))).intValue();
                    SwitchButton.this.viewState.radius = SwitchButton.this.viewRadius * f;
                    SwitchButton.this.viewState.checkedLineColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(f, 0, Integer.valueOf(SwitchButton.this.checkLineColor))).intValue();
                }
                SwitchButton.this.postInvalidate();
            }
        };
        this.animatorListener = new Animator.AnimatorListener() { // from class: io.dcloud.feature.weex_switch.SwitchButton.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                int i = SwitchButton.this.animateState;
                if (i == 1) {
                    SwitchButton.this.animateState = 2;
                    SwitchButton.this.viewState.checkedLineColor = 0;
                    SwitchButton.this.viewState.radius = SwitchButton.this.viewRadius;
                    SwitchButton.this.postInvalidate();
                    return;
                }
                if (i == 3) {
                    SwitchButton.this.animateState = 0;
                    SwitchButton.this.postInvalidate();
                    return;
                }
                if (i == 4) {
                    SwitchButton.this.animateState = 0;
                    SwitchButton.this.postInvalidate();
                    SwitchButton.this.broadcastEvent();
                } else {
                    if (i != 5) {
                        return;
                    }
                    SwitchButton switchButton = SwitchButton.this;
                    switchButton.isChecked = true ^ switchButton.isChecked;
                    SwitchButton.this.animateState = 0;
                    SwitchButton.this.postInvalidate();
                    SwitchButton.this.broadcastEvent();
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        };
        init(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastEvent() {
        OnCheckedChangeListener onCheckedChangeListener = this.onCheckedChangeListener;
        if (onCheckedChangeListener != null) {
            this.isEventBroadcast = true;
            onCheckedChangeListener.onCheckedChanged(this, isChecked());
        }
        this.isEventBroadcast = false;
    }

    public static float dp2px(float f) {
        return TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
    }

    public static int dp2pxInt(float f) {
        return (int) dp2px(f);
    }

    private void drawArc(Canvas canvas, float f, float f2, float f3, float f4, float f5, float f6, Paint paint) {
        if (Build.VERSION.SDK_INT >= 21) {
            canvas.drawArc(f, f2, f3, f4, f5, f6, true, paint);
        } else {
            this.rect.set(f, f2, f3, f4);
            canvas.drawArc(this.rect, f5, f6, true, paint);
        }
    }

    private void drawButton(Canvas canvas, float f, float f2) {
        canvas.drawCircle(f, f2, this.buttonRadius, this.buttonPaint);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(1.0f);
        this.paint.setColor(-2236963);
        canvas.drawCircle(f, f2, this.buttonRadius, this.paint);
    }

    private void drawRoundRect(Canvas canvas, float f, float f2, float f3, float f4, float f5, Paint paint) {
        if (Build.VERSION.SDK_INT >= 21) {
            canvas.drawRoundRect(f, f2, f3, f4, f5, f5, paint);
        } else {
            this.rect.set(f, f2, f3, f4);
            canvas.drawRoundRect(this.rect, f5, f5, paint);
        }
    }

    private void drawUncheckIndicator(Canvas canvas) {
        drawUncheckIndicator(canvas, this.uncheckCircleColor, this.uncheckCircleWidth, this.right - this.uncheckCircleOffsetX, this.centerY, this.uncheckCircleRadius, this.paint);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.dragFraction = 0.5f;
        TypedArray typedArrayObtainStyledAttributes = attributeSet != null ? context.obtainStyledAttributes(attributeSet, R.styleable.SwitchButton) : null;
        this.shadowEffect = optBoolean(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_shadow_effect, true);
        this.uncheckCircleColor = optColor(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_uncheckcircle_color, 0);
        this.uncheckCircleWidth = optPixelSize(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_uncheckcircle_width, dp2pxInt(1.5f));
        this.uncheckCircleOffsetX = dp2px(10.0f);
        this.uncheckCircleRadius = optPixelSize(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_uncheckcircle_radius, dp2px(4.0f));
        this.checkedLineOffsetX = dp2px(4.0f);
        this.checkedLineOffsetY = dp2px(4.0f);
        this.shadowRadius = optPixelSize(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_shadow_radius, dp2pxInt(2.0f));
        this.shadowOffset = optPixelSize(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_shadow_offset, dp2pxInt(0.0f));
        this.shadowColor = optColor(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_shadow_color, 855638016);
        this.uncheckColor = optColor(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_uncheck_color, -2236963);
        this.checkedColor = optColor(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_checked_color, -16466430);
        this.borderWidth = optPixelSize(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_border_width, dp2pxInt(1.0f));
        this.checkLineColor = optColor(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_checkline_color, 0);
        this.checkLineWidth = optPixelSize(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_checkline_width, dp2pxInt(0.0f));
        this.checkLineLength = dp2px(0.0f);
        int iOptColor = optColor(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_button_color, -1);
        int iOptInt = optInt(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_effect_duration, 200);
        this.isChecked = optBoolean(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_checked, false);
        this.showIndicator = optBoolean(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_show_indicator, true);
        this.background = optColor(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_background, -1);
        this.enableEffect = optBoolean(typedArrayObtainStyledAttributes, R.styleable.SwitchButton_sb_enable_effect, true);
        if (typedArrayObtainStyledAttributes != null) {
            typedArrayObtainStyledAttributes.recycle();
        }
        Paint paint = new Paint(1);
        this.paint = paint;
        paint.setAntiAlias(true);
        Paint paint2 = new Paint(1);
        this.buttonPaint = paint2;
        paint2.setAntiAlias(true);
        this.buttonPaint.setColor(iOptColor);
        if (this.shadowEffect) {
            this.buttonPaint.setShadowLayer(this.shadowRadius, 0.0f, this.shadowOffset, this.shadowColor);
        }
        this.viewState = new ViewState();
        this.beforeState = new ViewState();
        this.afterState = new ViewState();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.valueAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(iOptInt);
        this.valueAnimator.setRepeatCount(0);
        this.valueAnimator.addUpdateListener(this.animatorUpdateListener);
        this.valueAnimator.addListener(this.animatorListener);
        super.setClickable(true);
        setPadding(0, 0, 0, 0);
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
    }

    private boolean isDragState() {
        return this.animateState == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isInAnimating() {
        return this.animateState != 0;
    }

    private boolean isPendingDragState() {
        int i = this.animateState;
        return i == 1 || i == 3;
    }

    public static boolean optBoolean(TypedArray typedArray, int i, boolean z) {
        return typedArray == null ? z : typedArray.getBoolean(i, z);
    }

    public static int optColor(TypedArray typedArray, int i, int i2) {
        return typedArray == null ? i2 : typedArray.getColor(i, i2);
    }

    public static int optInt(TypedArray typedArray, int i, int i2) {
        return typedArray == null ? i2 : typedArray.getInt(i, i2);
    }

    public static float optPixelSize(TypedArray typedArray, int i, float f) {
        return typedArray == null ? f : typedArray.getDimension(i, f);
    }

    private void pendingCancelDragState() {
        if (isDragState() || isPendingDragState()) {
            if (this.valueAnimator.isRunning()) {
                this.valueAnimator.cancel();
            }
            this.animateState = 3;
            this.beforeState.copy(this.viewState);
            if (isChecked()) {
                setCheckedViewState(this.afterState);
            } else {
                setUncheckViewState(this.afterState);
            }
            this.valueAnimator.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pendingDragState() {
        if (!isInAnimating() && this.isTouchingDown) {
            if (this.valueAnimator.isRunning()) {
                this.valueAnimator.cancel();
            }
            this.animateState = 1;
            this.beforeState.copy(this.viewState);
            this.afterState.copy(this.viewState);
            if (isChecked()) {
                ViewState viewState = this.afterState;
                int i = this.checkedColor;
                viewState.checkStateColor = i;
                viewState.buttonX = this.buttonMaxX;
                viewState.checkedLineColor = i;
            } else {
                ViewState viewState2 = this.afterState;
                viewState2.checkStateColor = this.uncheckColor;
                viewState2.buttonX = this.buttonMinX;
                viewState2.radius = this.viewRadius;
            }
            this.valueAnimator.start();
        }
    }

    private void pendingSettleState() {
        if (this.valueAnimator.isRunning()) {
            this.valueAnimator.cancel();
        }
        this.animateState = 4;
        this.beforeState.copy(this.viewState);
        if (isChecked()) {
            setCheckedViewState(this.afterState);
        } else {
            setUncheckViewState(this.afterState);
        }
        this.valueAnimator.start();
    }

    private void setCheckedViewState(ViewState viewState) {
        viewState.radius = this.viewRadius;
        viewState.checkStateColor = this.checkedColor;
        viewState.checkedLineColor = this.checkLineColor;
        viewState.buttonX = this.buttonMaxX;
    }

    private void setUncheckViewState(ViewState viewState) {
        viewState.radius = 0.0f;
        viewState.checkStateColor = this.uncheckColor;
        viewState.checkedLineColor = 0;
        viewState.buttonX = this.buttonMinX;
    }

    public void drawCheckedIndicator(Canvas canvas) {
        int i = this.viewState.checkedLineColor;
        float f = this.checkLineWidth;
        float f2 = this.left;
        float f3 = this.viewRadius;
        float f4 = (f2 + f3) - this.checkedLineOffsetX;
        float f5 = this.centerY;
        float f6 = this.checkLineLength;
        drawCheckedIndicator(canvas, i, f, f4, f5 - f6, (f2 + f3) - this.checkedLineOffsetY, f5 + f6, this.paint);
    }

    public float getShadowBottomSize() {
        return ((this.shadowRadius + this.shadowOffset) - this.borderWidth) - 2.0f;
    }

    public float getShadowLeftSize() {
        return (this.shadowRadius - this.borderWidth) - 2.0f;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.isChecked;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setStrokeWidth(this.borderWidth);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(this.background);
        drawRoundRect(canvas, this.left, this.top, this.right, this.bottom, this.viewRadius, this.paint);
        float f = this.viewState.radius * 0.5f;
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(this.viewState.checkStateColor);
        this.paint.setStrokeWidth(this.borderWidth + (f * 2.0f));
        drawRoundRect(canvas, this.left + f, this.top + f, this.right - f, this.bottom - f, this.viewRadius, this.paint);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setStrokeWidth(1.0f);
        float f2 = this.left;
        float f3 = this.top;
        float f4 = this.viewRadius;
        drawArc(canvas, f2, f3, f2 + (f4 * 2.0f), ((f4 * 2.0f) + f3) - this.borderWidth, 90.0f, 180.0f, this.paint);
        float f5 = this.left;
        float f6 = this.viewRadius;
        float f7 = this.top;
        canvas.drawRect(f5 + f6, f7, this.viewState.buttonX, ((f6 * 2.0f) + f7) - this.borderWidth, this.paint);
        drawButton(canvas, this.viewState.buttonX, this.centerY);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode == 0 || mode == Integer.MIN_VALUE) {
            i = View.MeasureSpec.makeMeasureSpec(DEFAULT_WIDTH, 1073741824);
        }
        if (mode2 == 0 || mode2 == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec(DEFAULT_HEIGHT, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int i5 = this.shadowRadius + this.shadowOffset;
        int i6 = this.borderWidth;
        float f = i2 - (i5 - i6);
        this.height = f;
        float f2 = i;
        this.width = f2;
        float f3 = f * 0.5f;
        this.viewRadius = f3;
        this.buttonRadius = f3 - i6;
        float f4 = i6 / 2.0f;
        this.left = f4;
        float f5 = i6 / 2.0f;
        this.top = f5;
        float f6 = f2 - (i6 / 2.0f);
        this.right = f6;
        float f7 = f - (i6 / 2.0f);
        this.bottom = f7;
        this.centerX = (f6 + f4) * 0.5f;
        this.centerY = (f5 + f7) * 0.5f;
        this.buttonMinX = (f4 / 2.0f) + f3;
        this.buttonMaxX = ((f2 - (i6 / 2.0f)) + ((i6 / 2.0f) / 2.0f)) - f3;
        if (isChecked()) {
            setCheckedViewState(this.viewState);
        } else {
            setUncheckViewState(this.viewState);
        }
        this.isUiInited = true;
        postInvalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            Logger.d("ACTION_MOVE", "ACTION_DOWN =========== " + this.dragFraction);
            this.dragFraction = 0.5f;
            this.isTouchingDown = true;
            this.touchDownTime = System.currentTimeMillis();
            removeCallbacks(this.postPendingDrag);
            postDelayed(this.postPendingDrag, 100L);
        } else if (actionMasked == 1) {
            this.isTouchingDown = false;
            removeCallbacks(this.postPendingDrag);
            if (System.currentTimeMillis() - this.touchDownTime <= 400) {
                toggle();
            } else if (isDragState()) {
                boolean z = Math.max(0.0f, Math.min(1.0f, motionEvent.getX() / ((float) getWidth()))) > 0.5f;
                if (z == isChecked()) {
                    pendingCancelDragState();
                } else {
                    this.isChecked = z;
                    pendingSettleState();
                }
            } else if (isPendingDragState()) {
                pendingCancelDragState();
            }
        } else if (actionMasked == 2) {
            float x = motionEvent.getX();
            if (isPendingDragState()) {
                float width = x / getWidth();
                this.dragFraction = width;
                this.dragFraction = Math.max(0.0f, Math.min(1.0f, width));
                Logger.d("ACTION_MOVE", "isPendingDragState =========== " + this.dragFraction);
                ViewState viewState = this.viewState;
                float f = this.buttonMinX;
                viewState.buttonX = f + ((this.buttonMaxX - f) * this.dragFraction);
            } else if (isDragState()) {
                float width2 = x / getWidth();
                this.dragFraction = width2;
                this.dragFraction = Math.max(0.0f, Math.min(1.0f, width2));
                Logger.d("ACTION_MOVE", "isDragState =========== " + this.dragFraction);
                ViewState viewState2 = this.viewState;
                float f2 = this.buttonMinX;
                float f3 = this.buttonMaxX - f2;
                float f4 = this.dragFraction;
                viewState2.buttonX = f2 + (f3 * f4);
                viewState2.checkStateColor = ((Integer) this.argbEvaluator.evaluate(f4, Integer.valueOf(this.uncheckColor), Integer.valueOf(this.checkedColor))).intValue();
                postInvalidate();
            }
        } else if (actionMasked == 3) {
            this.isTouchingDown = false;
            removeCallbacks(this.postPendingDrag);
            if (isPendingDragState() || isDragState()) {
                pendingCancelDragState();
            }
        }
        return true;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (isEnabled() || this.isInit) {
            if (z == isChecked()) {
                postInvalidate();
                this.isInit = false;
                return;
            }
            toggle(this.enableEffect, false);
        }
        this.isInit = false;
    }

    public void setCheckedColor(int i) {
        this.checkedColor = i;
    }

    public void setEnableEffect(boolean z) {
        this.enableEffect = z;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @Override // android.view.View
    public final void setOnClickListener(View.OnClickListener onClickListener) {
    }

    @Override // android.view.View
    public final void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
    }

    @Override // android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(0, 0, 0, 0);
    }

    public void setShadowEffect(boolean z) {
        if (this.shadowEffect == z) {
            return;
        }
        this.shadowEffect = z;
        if (z) {
            this.buttonPaint.setShadowLayer(this.shadowRadius, 0.0f, this.shadowOffset, this.shadowColor);
        } else {
            this.buttonPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        toggle(true);
    }

    public static int optPixelSize(TypedArray typedArray, int i, int i2) {
        return typedArray == null ? i2 : typedArray.getDimensionPixelOffset(i, i2);
    }

    public void drawCheckedIndicator(Canvas canvas, int i, float f, float f2, float f3, float f4, float f5, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i);
        paint.setStrokeWidth(f);
        canvas.drawLine(f2, f3, f4, f5, paint);
    }

    public void drawUncheckIndicator(Canvas canvas, int i, float f, float f2, float f3, float f4, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i);
        paint.setStrokeWidth(f);
        canvas.drawCircle(f2, f3, f4, paint);
    }

    public void toggle(boolean z) {
        toggle(z, true);
    }

    private void toggle(boolean z, boolean z2) {
        if ((isChecked() && this.dragFraction != 1.0f) || (!isChecked() && this.dragFraction != 0.0f)) {
            if (isEnabled() || this.isInit) {
                if (!this.isEventBroadcast) {
                    if (!this.isUiInited) {
                        this.isChecked = !this.isChecked;
                        if (z2) {
                            broadcastEvent();
                            return;
                        }
                        return;
                    }
                    if (this.valueAnimator.isRunning()) {
                        this.valueAnimator.cancel();
                    }
                    if (this.enableEffect && z) {
                        this.animateState = 5;
                        this.beforeState.copy(this.viewState);
                        if (isChecked()) {
                            Logger.d("ACTION_MOVE", "isChecked true =========== " + this.dragFraction);
                            setUncheckViewState(this.afterState);
                        } else {
                            Logger.d("ACTION_MOVE", "isChecked false =========== " + this.dragFraction);
                            setCheckedViewState(this.afterState);
                        }
                        this.valueAnimator.start();
                        return;
                    }
                    this.isChecked = !this.isChecked;
                    if (isChecked()) {
                        setCheckedViewState(this.viewState);
                    } else {
                        setUncheckViewState(this.viewState);
                    }
                    postInvalidate();
                    if (z2) {
                        broadcastEvent();
                        return;
                    }
                    return;
                }
                throw new RuntimeException("should NOT switch the state in method: [onCheckedChanged]!");
            }
            return;
        }
        pendingCancelDragState();
    }

    public SwitchButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ANIMATE_STATE_NONE = 0;
        this.ANIMATE_STATE_PENDING_DRAG = 1;
        this.ANIMATE_STATE_DRAGING = 2;
        this.ANIMATE_STATE_PENDING_RESET = 3;
        this.ANIMATE_STATE_PENDING_SETTLE = 4;
        this.ANIMATE_STATE_SWITCH = 5;
        this.isInit = true;
        this.rect = new RectF();
        this.animateState = 0;
        this.argbEvaluator = new ArgbEvaluator();
        this.isTouchingDown = false;
        this.isUiInited = false;
        this.isEventBroadcast = false;
        this.postPendingDrag = new Runnable() { // from class: io.dcloud.feature.weex_switch.SwitchButton.1
            @Override // java.lang.Runnable
            public void run() {
                if (SwitchButton.this.isInAnimating()) {
                    return;
                }
                SwitchButton.this.pendingDragState();
            }
        };
        this.animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: io.dcloud.feature.weex_switch.SwitchButton.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                int i = SwitchButton.this.animateState;
                if (i == 1 || i == 3 || i == 4) {
                    SwitchButton.this.viewState.checkedLineColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(fFloatValue, Integer.valueOf(SwitchButton.this.beforeState.checkedLineColor), Integer.valueOf(SwitchButton.this.afterState.checkedLineColor))).intValue();
                    SwitchButton.this.viewState.radius = SwitchButton.this.beforeState.radius + ((SwitchButton.this.afterState.radius - SwitchButton.this.beforeState.radius) * fFloatValue);
                    if (SwitchButton.this.animateState != 1) {
                        SwitchButton.this.viewState.buttonX = SwitchButton.this.beforeState.buttonX + ((SwitchButton.this.afterState.buttonX - SwitchButton.this.beforeState.buttonX) * fFloatValue);
                    }
                    SwitchButton.this.viewState.checkStateColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(fFloatValue, Integer.valueOf(SwitchButton.this.beforeState.checkStateColor), Integer.valueOf(SwitchButton.this.afterState.checkStateColor))).intValue();
                } else if (i == 5) {
                    SwitchButton.this.viewState.buttonX = SwitchButton.this.beforeState.buttonX + ((SwitchButton.this.afterState.buttonX - SwitchButton.this.beforeState.buttonX) * fFloatValue);
                    float f = (SwitchButton.this.viewState.buttonX - SwitchButton.this.buttonMinX) / (SwitchButton.this.buttonMaxX - SwitchButton.this.buttonMinX);
                    SwitchButton.this.viewState.checkStateColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(f, Integer.valueOf(SwitchButton.this.uncheckColor), Integer.valueOf(SwitchButton.this.checkedColor))).intValue();
                    SwitchButton.this.viewState.radius = SwitchButton.this.viewRadius * f;
                    SwitchButton.this.viewState.checkedLineColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(f, 0, Integer.valueOf(SwitchButton.this.checkLineColor))).intValue();
                }
                SwitchButton.this.postInvalidate();
            }
        };
        this.animatorListener = new Animator.AnimatorListener() { // from class: io.dcloud.feature.weex_switch.SwitchButton.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                int i = SwitchButton.this.animateState;
                if (i == 1) {
                    SwitchButton.this.animateState = 2;
                    SwitchButton.this.viewState.checkedLineColor = 0;
                    SwitchButton.this.viewState.radius = SwitchButton.this.viewRadius;
                    SwitchButton.this.postInvalidate();
                    return;
                }
                if (i == 3) {
                    SwitchButton.this.animateState = 0;
                    SwitchButton.this.postInvalidate();
                    return;
                }
                if (i == 4) {
                    SwitchButton.this.animateState = 0;
                    SwitchButton.this.postInvalidate();
                    SwitchButton.this.broadcastEvent();
                } else {
                    if (i != 5) {
                        return;
                    }
                    SwitchButton switchButton = SwitchButton.this;
                    switchButton.isChecked = true ^ switchButton.isChecked;
                    SwitchButton.this.animateState = 0;
                    SwitchButton.this.postInvalidate();
                    SwitchButton.this.broadcastEvent();
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        };
        init(context, attributeSet);
    }

    public SwitchButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ANIMATE_STATE_NONE = 0;
        this.ANIMATE_STATE_PENDING_DRAG = 1;
        this.ANIMATE_STATE_DRAGING = 2;
        this.ANIMATE_STATE_PENDING_RESET = 3;
        this.ANIMATE_STATE_PENDING_SETTLE = 4;
        this.ANIMATE_STATE_SWITCH = 5;
        this.isInit = true;
        this.rect = new RectF();
        this.animateState = 0;
        this.argbEvaluator = new ArgbEvaluator();
        this.isTouchingDown = false;
        this.isUiInited = false;
        this.isEventBroadcast = false;
        this.postPendingDrag = new Runnable() { // from class: io.dcloud.feature.weex_switch.SwitchButton.1
            @Override // java.lang.Runnable
            public void run() {
                if (SwitchButton.this.isInAnimating()) {
                    return;
                }
                SwitchButton.this.pendingDragState();
            }
        };
        this.animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: io.dcloud.feature.weex_switch.SwitchButton.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                int i2 = SwitchButton.this.animateState;
                if (i2 == 1 || i2 == 3 || i2 == 4) {
                    SwitchButton.this.viewState.checkedLineColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(fFloatValue, Integer.valueOf(SwitchButton.this.beforeState.checkedLineColor), Integer.valueOf(SwitchButton.this.afterState.checkedLineColor))).intValue();
                    SwitchButton.this.viewState.radius = SwitchButton.this.beforeState.radius + ((SwitchButton.this.afterState.radius - SwitchButton.this.beforeState.radius) * fFloatValue);
                    if (SwitchButton.this.animateState != 1) {
                        SwitchButton.this.viewState.buttonX = SwitchButton.this.beforeState.buttonX + ((SwitchButton.this.afterState.buttonX - SwitchButton.this.beforeState.buttonX) * fFloatValue);
                    }
                    SwitchButton.this.viewState.checkStateColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(fFloatValue, Integer.valueOf(SwitchButton.this.beforeState.checkStateColor), Integer.valueOf(SwitchButton.this.afterState.checkStateColor))).intValue();
                } else if (i2 == 5) {
                    SwitchButton.this.viewState.buttonX = SwitchButton.this.beforeState.buttonX + ((SwitchButton.this.afterState.buttonX - SwitchButton.this.beforeState.buttonX) * fFloatValue);
                    float f = (SwitchButton.this.viewState.buttonX - SwitchButton.this.buttonMinX) / (SwitchButton.this.buttonMaxX - SwitchButton.this.buttonMinX);
                    SwitchButton.this.viewState.checkStateColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(f, Integer.valueOf(SwitchButton.this.uncheckColor), Integer.valueOf(SwitchButton.this.checkedColor))).intValue();
                    SwitchButton.this.viewState.radius = SwitchButton.this.viewRadius * f;
                    SwitchButton.this.viewState.checkedLineColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(f, 0, Integer.valueOf(SwitchButton.this.checkLineColor))).intValue();
                }
                SwitchButton.this.postInvalidate();
            }
        };
        this.animatorListener = new Animator.AnimatorListener() { // from class: io.dcloud.feature.weex_switch.SwitchButton.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                int i2 = SwitchButton.this.animateState;
                if (i2 == 1) {
                    SwitchButton.this.animateState = 2;
                    SwitchButton.this.viewState.checkedLineColor = 0;
                    SwitchButton.this.viewState.radius = SwitchButton.this.viewRadius;
                    SwitchButton.this.postInvalidate();
                    return;
                }
                if (i2 == 3) {
                    SwitchButton.this.animateState = 0;
                    SwitchButton.this.postInvalidate();
                    return;
                }
                if (i2 == 4) {
                    SwitchButton.this.animateState = 0;
                    SwitchButton.this.postInvalidate();
                    SwitchButton.this.broadcastEvent();
                } else {
                    if (i2 != 5) {
                        return;
                    }
                    SwitchButton switchButton = SwitchButton.this;
                    switchButton.isChecked = true ^ switchButton.isChecked;
                    SwitchButton.this.animateState = 0;
                    SwitchButton.this.postInvalidate();
                    SwitchButton.this.broadcastEvent();
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        };
        init(context, attributeSet);
    }

    @TargetApi(21)
    public SwitchButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.ANIMATE_STATE_NONE = 0;
        this.ANIMATE_STATE_PENDING_DRAG = 1;
        this.ANIMATE_STATE_DRAGING = 2;
        this.ANIMATE_STATE_PENDING_RESET = 3;
        this.ANIMATE_STATE_PENDING_SETTLE = 4;
        this.ANIMATE_STATE_SWITCH = 5;
        this.isInit = true;
        this.rect = new RectF();
        this.animateState = 0;
        this.argbEvaluator = new ArgbEvaluator();
        this.isTouchingDown = false;
        this.isUiInited = false;
        this.isEventBroadcast = false;
        this.postPendingDrag = new Runnable() { // from class: io.dcloud.feature.weex_switch.SwitchButton.1
            @Override // java.lang.Runnable
            public void run() {
                if (SwitchButton.this.isInAnimating()) {
                    return;
                }
                SwitchButton.this.pendingDragState();
            }
        };
        this.animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: io.dcloud.feature.weex_switch.SwitchButton.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                int i22 = SwitchButton.this.animateState;
                if (i22 == 1 || i22 == 3 || i22 == 4) {
                    SwitchButton.this.viewState.checkedLineColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(fFloatValue, Integer.valueOf(SwitchButton.this.beforeState.checkedLineColor), Integer.valueOf(SwitchButton.this.afterState.checkedLineColor))).intValue();
                    SwitchButton.this.viewState.radius = SwitchButton.this.beforeState.radius + ((SwitchButton.this.afterState.radius - SwitchButton.this.beforeState.radius) * fFloatValue);
                    if (SwitchButton.this.animateState != 1) {
                        SwitchButton.this.viewState.buttonX = SwitchButton.this.beforeState.buttonX + ((SwitchButton.this.afterState.buttonX - SwitchButton.this.beforeState.buttonX) * fFloatValue);
                    }
                    SwitchButton.this.viewState.checkStateColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(fFloatValue, Integer.valueOf(SwitchButton.this.beforeState.checkStateColor), Integer.valueOf(SwitchButton.this.afterState.checkStateColor))).intValue();
                } else if (i22 == 5) {
                    SwitchButton.this.viewState.buttonX = SwitchButton.this.beforeState.buttonX + ((SwitchButton.this.afterState.buttonX - SwitchButton.this.beforeState.buttonX) * fFloatValue);
                    float f = (SwitchButton.this.viewState.buttonX - SwitchButton.this.buttonMinX) / (SwitchButton.this.buttonMaxX - SwitchButton.this.buttonMinX);
                    SwitchButton.this.viewState.checkStateColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(f, Integer.valueOf(SwitchButton.this.uncheckColor), Integer.valueOf(SwitchButton.this.checkedColor))).intValue();
                    SwitchButton.this.viewState.radius = SwitchButton.this.viewRadius * f;
                    SwitchButton.this.viewState.checkedLineColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(f, 0, Integer.valueOf(SwitchButton.this.checkLineColor))).intValue();
                }
                SwitchButton.this.postInvalidate();
            }
        };
        this.animatorListener = new Animator.AnimatorListener() { // from class: io.dcloud.feature.weex_switch.SwitchButton.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                int i22 = SwitchButton.this.animateState;
                if (i22 == 1) {
                    SwitchButton.this.animateState = 2;
                    SwitchButton.this.viewState.checkedLineColor = 0;
                    SwitchButton.this.viewState.radius = SwitchButton.this.viewRadius;
                    SwitchButton.this.postInvalidate();
                    return;
                }
                if (i22 == 3) {
                    SwitchButton.this.animateState = 0;
                    SwitchButton.this.postInvalidate();
                    return;
                }
                if (i22 == 4) {
                    SwitchButton.this.animateState = 0;
                    SwitchButton.this.postInvalidate();
                    SwitchButton.this.broadcastEvent();
                } else {
                    if (i22 != 5) {
                        return;
                    }
                    SwitchButton switchButton = SwitchButton.this;
                    switchButton.isChecked = true ^ switchButton.isChecked;
                    SwitchButton.this.animateState = 0;
                    SwitchButton.this.postInvalidate();
                    SwitchButton.this.broadcastEvent();
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        };
        init(context, attributeSet);
    }
}
