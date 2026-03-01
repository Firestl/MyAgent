package io.dcloud.feature.weex.adapter.Fresco;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.imagepipeline.systrace.FrescoSystrace;

/* JADX INFO: loaded from: classes3.dex */
public class DCGenericDraweeHierarchyInflater {
    public static Drawable getDrawable(Context context, TypedArray typedArray, int i) {
        int resourceId = typedArray.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        return context.getResources().getDrawable(resourceId);
    }

    public static RoundingParams getRoundingParams(DCGenericDraweeHierarchyBuilder dCGenericDraweeHierarchyBuilder) {
        if (dCGenericDraweeHierarchyBuilder.getRoundingParams() == null) {
            dCGenericDraweeHierarchyBuilder.setRoundingParams(new RoundingParams());
        }
        return dCGenericDraweeHierarchyBuilder.getRoundingParams();
    }

    public static ScalingUtils.ScaleType getScaleTypeFromXml(TypedArray typedArray, int i) {
        switch (typedArray.getInt(i, -2)) {
            case -1:
                return null;
            case 0:
                return ScalingUtils.ScaleType.FIT_XY;
            case 1:
                return ScalingUtils.ScaleType.FIT_START;
            case 2:
                return ScalingUtils.ScaleType.FIT_CENTER;
            case 3:
                return ScalingUtils.ScaleType.FIT_END;
            case 4:
                return ScalingUtils.ScaleType.CENTER;
            case 5:
                return ScalingUtils.ScaleType.CENTER_INSIDE;
            case 6:
                return ScalingUtils.ScaleType.CENTER_CROP;
            case 7:
                return ScalingUtils.ScaleType.FOCUS_CROP;
            case 8:
                return ScalingUtils.ScaleType.FIT_BOTTOM_START;
            default:
                throw new RuntimeException("XML attribute not specified!");
        }
    }

    public static DCGenericDraweeHierarchyBuilder inflateBuilder(Context context, AttributeSet attributeSet) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("GenericDraweeHierarchyBuilder#inflateBuilder");
        }
        DCGenericDraweeHierarchyBuilder dCGenericDraweeHierarchyBuilderUpdateBuilder = updateBuilder(new DCGenericDraweeHierarchyBuilder(context.getResources()), context, attributeSet);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return dCGenericDraweeHierarchyBuilderUpdateBuilder;
    }

    public static DCGenericDraweeHierarchy inflateHierarchy(Context context, AttributeSet attributeSet) {
        return inflateBuilder(context, attributeSet).build();
    }

    public static DCGenericDraweeHierarchyBuilder updateBuilder(DCGenericDraweeHierarchyBuilder dCGenericDraweeHierarchyBuilder, Context context, AttributeSet attributeSet) {
        dCGenericDraweeHierarchyBuilder.getProgressBarImage();
        return dCGenericDraweeHierarchyBuilder;
    }
}
