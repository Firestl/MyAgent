package io.dcloud.media.video.ijkplayer.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import io.dcloud.feature.weex_amap.adapter.Constant;

/* JADX INFO: loaded from: classes3.dex */
public final class AnimHelper {
    public AnimHelper() {
        throw new AssertionError();
    }

    public static void doClipViewHeight(final View view, int i, int i2, int i3) {
        ValueAnimator duration = ValueAnimator.ofInt(i, i2).setDuration(i3);
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.dcloud.media.video.ijkplayer.utils.AnimHelper.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = iIntValue;
                view.setLayoutParams(layoutParams);
            }
        });
        duration.setInterpolator(new AccelerateInterpolator());
        duration.start();
    }

    public static void doClipViewWidth(final View view, int i, int i2, int i3) {
        ValueAnimator duration = ValueAnimator.ofInt(i, i2).setDuration(i3);
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: io.dcloud.media.video.ijkplayer.utils.AnimHelper.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = iIntValue;
                view.setLayoutParams(layoutParams);
            }
        });
        duration.setInterpolator(new AccelerateInterpolator());
        duration.start();
    }

    public static void doSlideRightIn(View view, int i, int i2, int i3) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "translationX", i, i2);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, Constant.JSONKEY.ALPHE, 0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(i3);
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.start();
    }
}
