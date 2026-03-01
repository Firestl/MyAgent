package io.dcloud.nineoldandroids.animation;

import android.view.View;
import com.taobao.weex.common.Constants;
import io.dcloud.feature.weex_amap.adapter.Constant;
import io.dcloud.nineoldandroids.util.FloatProperty;
import io.dcloud.nineoldandroids.util.IntProperty;
import io.dcloud.nineoldandroids.util.Property;
import io.dcloud.nineoldandroids.view.animation.AnimatorProxy;

/* JADX INFO: loaded from: classes3.dex */
public final class PreHoneycombCompat {
    public static Property<View, Float> ALPHA = new FloatProperty<View>(Constant.JSONKEY.ALPHE) { // from class: io.dcloud.nineoldandroids.animation.PreHoneycombCompat.1
        @Override // io.dcloud.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getAlpha());
        }

        @Override // io.dcloud.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setAlpha(f);
        }
    };
    public static Property<View, Float> PIVOT_X = new FloatProperty<View>("pivotX") { // from class: io.dcloud.nineoldandroids.animation.PreHoneycombCompat.2
        @Override // io.dcloud.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getPivotX());
        }

        @Override // io.dcloud.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setPivotX(f);
        }
    };
    public static Property<View, Float> PIVOT_Y = new FloatProperty<View>("pivotY") { // from class: io.dcloud.nineoldandroids.animation.PreHoneycombCompat.3
        @Override // io.dcloud.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getPivotY());
        }

        @Override // io.dcloud.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setPivotY(f);
        }
    };
    public static Property<View, Float> TRANSLATION_X = new FloatProperty<View>("translationX") { // from class: io.dcloud.nineoldandroids.animation.PreHoneycombCompat.4
        @Override // io.dcloud.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getTranslationX());
        }

        @Override // io.dcloud.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setTranslationX(f);
        }
    };
    public static Property<View, Float> TRANSLATION_Y = new FloatProperty<View>("translationY") { // from class: io.dcloud.nineoldandroids.animation.PreHoneycombCompat.5
        @Override // io.dcloud.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getTranslationY());
        }

        @Override // io.dcloud.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setTranslationY(f);
        }
    };
    public static Property<View, Float> ROTATION = new FloatProperty<View>("rotation") { // from class: io.dcloud.nineoldandroids.animation.PreHoneycombCompat.6
        @Override // io.dcloud.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getRotation());
        }

        @Override // io.dcloud.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setRotation(f);
        }
    };
    public static Property<View, Float> ROTATION_X = new FloatProperty<View>("rotationX") { // from class: io.dcloud.nineoldandroids.animation.PreHoneycombCompat.7
        @Override // io.dcloud.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getRotationX());
        }

        @Override // io.dcloud.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setRotationX(f);
        }
    };
    public static Property<View, Float> ROTATION_Y = new FloatProperty<View>("rotationY") { // from class: io.dcloud.nineoldandroids.animation.PreHoneycombCompat.8
        @Override // io.dcloud.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getRotationY());
        }

        @Override // io.dcloud.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setRotationY(f);
        }
    };
    public static Property<View, Float> SCALE_X = new FloatProperty<View>("scaleX") { // from class: io.dcloud.nineoldandroids.animation.PreHoneycombCompat.9
        @Override // io.dcloud.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getScaleX());
        }

        @Override // io.dcloud.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setScaleX(f);
        }
    };
    public static Property<View, Float> SCALE_Y = new FloatProperty<View>("scaleY") { // from class: io.dcloud.nineoldandroids.animation.PreHoneycombCompat.10
        @Override // io.dcloud.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getScaleY());
        }

        @Override // io.dcloud.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setScaleY(f);
        }
    };
    public static Property<View, Integer> SCROLL_X = new IntProperty<View>("scrollX") { // from class: io.dcloud.nineoldandroids.animation.PreHoneycombCompat.11
        @Override // io.dcloud.nineoldandroids.util.Property
        public Integer get(View view) {
            return Integer.valueOf(AnimatorProxy.wrap(view).getScrollX());
        }

        @Override // io.dcloud.nineoldandroids.util.IntProperty
        public void setValue(View view, int i) {
            AnimatorProxy.wrap(view).setScrollX(i);
        }
    };
    public static Property<View, Integer> SCROLL_Y = new IntProperty<View>("scrollY") { // from class: io.dcloud.nineoldandroids.animation.PreHoneycombCompat.12
        @Override // io.dcloud.nineoldandroids.util.Property
        public Integer get(View view) {
            return Integer.valueOf(AnimatorProxy.wrap(view).getScrollY());
        }

        @Override // io.dcloud.nineoldandroids.util.IntProperty
        public void setValue(View view, int i) {
            AnimatorProxy.wrap(view).setScrollY(i);
        }
    };
    public static Property<View, Float> X = new FloatProperty<View>(Constants.Name.X) { // from class: io.dcloud.nineoldandroids.animation.PreHoneycombCompat.13
        @Override // io.dcloud.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getX());
        }

        @Override // io.dcloud.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setX(f);
        }
    };
    public static Property<View, Float> Y = new FloatProperty<View>(Constants.Name.Y) { // from class: io.dcloud.nineoldandroids.animation.PreHoneycombCompat.14
        @Override // io.dcloud.nineoldandroids.util.Property
        public Float get(View view) {
            return Float.valueOf(AnimatorProxy.wrap(view).getY());
        }

        @Override // io.dcloud.nineoldandroids.util.FloatProperty
        public void setValue(View view, float f) {
            AnimatorProxy.wrap(view).setY(f);
        }
    };
}
