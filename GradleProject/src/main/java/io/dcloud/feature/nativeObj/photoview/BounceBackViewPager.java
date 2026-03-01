package io.dcloud.feature.nativeObj.photoview;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.animation.TranslateAnimation;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes3.dex */
public class BounceBackViewPager extends ViewPager {
    public static final float RATIO = 0.5f;
    public static final float SCROLL_WIDTH = 10.0f;
    public int currentPosition;
    public boolean handleDefault;
    public Rect mRect;
    public float preX;

    public BounceBackViewPager(Context context) {
        super(context);
        this.currentPosition = 0;
        this.mRect = new Rect();
        this.handleDefault = true;
        this.preX = 0.0f;
        if (Integer.parseInt(Build.VERSION.SDK) >= 9) {
            setOverScrollMode(2);
        }
    }

    private void onTouchActionUp() {
        if (this.mRect.isEmpty()) {
            return;
        }
        recoveryPosition();
    }

    private void recoveryPosition() {
        TranslateAnimation translateAnimation = new TranslateAnimation(getLeft(), this.mRect.left, 0.0f, 0.0f);
        translateAnimation.setDuration(300L);
        startAnimation(translateAnimation);
        Rect rect = this.mRect;
        layout(rect.left, rect.top, rect.right, rect.bottom);
        this.mRect.setEmpty();
        this.handleDefault = true;
    }

    private void whetherConditionIsRight(float f) {
        if (this.mRect.isEmpty()) {
            this.mRect.set(getLeft(), getTop(), getRight(), getBottom());
        }
        this.handleDefault = false;
        int i = (int) (f * 0.5f);
        layout(getLeft() + i, getTop(), getRight() + i, getBottom());
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            if (motionEvent.getAction() == 0) {
                this.preX = motionEvent.getX();
                this.currentPosition = getCurrentItem();
            }
            return super.onInterceptTouchEvent(motionEvent);
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1) {
            onTouchActionUp();
        } else if (action == 2) {
            if (getAdapter().getCount() == 1) {
                float x = motionEvent.getX();
                float f = x - this.preX;
                this.preX = x;
                if (f > 10.0f || f < -10.0f) {
                    whetherConditionIsRight(f);
                } else if (!this.handleDefault) {
                    int i = (int) (f * 0.5f);
                    if (getLeft() + i != this.mRect.left) {
                        layout(getLeft() + i, getTop(), getRight() + i, getBottom());
                    }
                }
            } else {
                int i2 = this.currentPosition;
                if (i2 == 0 || i2 == getAdapter().getCount() - 1) {
                    float x2 = motionEvent.getX();
                    float f2 = x2 - this.preX;
                    this.preX = x2;
                    if (this.currentPosition == 0) {
                        if (f2 > 10.0f) {
                            whetherConditionIsRight(f2);
                        } else if (!this.handleDefault) {
                            int i3 = (int) (f2 * 0.5f);
                            if (getLeft() + i3 >= this.mRect.left) {
                                layout(getLeft() + i3, getTop(), getRight() + i3, getBottom());
                            }
                        }
                    } else if (f2 < -10.0f) {
                        whetherConditionIsRight(f2);
                    } else if (!this.handleDefault) {
                        int i4 = (int) (f2 * 0.5f);
                        if (getRight() + i4 <= this.mRect.right) {
                            layout(getLeft() + i4, getTop(), getRight() + i4, getBottom());
                        }
                    }
                } else {
                    this.handleDefault = true;
                }
            }
            if (!this.handleDefault) {
                return true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }
}
