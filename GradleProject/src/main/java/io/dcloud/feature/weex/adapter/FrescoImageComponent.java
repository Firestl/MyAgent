package io.dcloud.feature.weex.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.widget.ImageView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.RoundingParams;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.CSSConstants;
import com.taobao.weex.layout.ContentBoxMeasurement;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXImage;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.border.BorderDrawable;
import com.taobao.weex.utils.WXDomUtils;
import com.taobao.weex.utils.WXViewUtils;
import com.umeng.analytics.pro.db;
import io.dcloud.common.DHInterface.IApp;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class FrescoImageComponent extends WXImage {
    public String mResizeMode;

    public class CustomScaleType implements ScalingUtils.ScaleType {
        public float dxf;
        public float dyf;

        public CustomScaleType(float f, float f2) {
            this.dxf = f;
            this.dyf = f2;
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.ScaleType
        public Matrix getTransform(Matrix matrix, Rect rect, int i, int i2, float f, float f2) {
            float f3 = i;
            float realPxByWidth = WXViewUtils.getRealPxByWidth(f3, FrescoImageComponent.this.getInstance().getInstanceViewPortWidthWithFloat()) / f3;
            float fWidth = rect.left + ((rect.width() - (f3 * realPxByWidth)) * this.dxf);
            float fHeight = rect.top + ((rect.height() - (i2 * realPxByWidth)) * this.dyf);
            matrix.setScale(realPxByWidth, realPxByWidth);
            matrix.postTranslate(fWidth, fHeight);
            return matrix;
        }
    }

    public FrescoImageComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        this.mResizeMode = "scaleToFill";
        if (basicComponentData.getStyles().containsKey(Constants.Name.FLEX)) {
            return;
        }
        setContentBoxMeasurement(new ContentBoxMeasurement() { // from class: io.dcloud.feature.weex.adapter.FrescoImageComponent.1
            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void layoutAfter(float f, float f2) {
            }

            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void layoutBefore() {
            }

            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void measureInternal(float f, float f2, int i, int i2) {
                int realPxByWidth = (int) WXViewUtils.getRealPxByWidth(240.0f, FrescoImageComponent.this.getInstance().getInstanceViewPortWidthWithFloat());
                if (CSSConstants.isUndefined(f2)) {
                    f2 = realPxByWidth;
                }
                this.mMeasureHeight = f2;
            }
        });
    }

    private void updateBorderRadius() {
        BorderDrawable borderDrawable = WXViewUtils.getBorderDrawable(getHostView());
        RoundingParams roundingParams = new RoundingParams();
        roundingParams.setCornersRadii(borderDrawable != null ? borderDrawable.getBorderInnerRadius(new RectF(0.0f, 0.0f, WXDomUtils.getContentWidth(this), WXDomUtils.getContentHeight(this))) : new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f});
        ((FrescoImageView) getHostView()).getHierarchy().setRoundingParams(roundingParams);
    }

    @Override // com.taobao.weex.ui.component.WXImage
    public void onImageFinish(boolean z, Map map) {
        super.onImageFinish(z, map);
        if (map != null) {
            String str = this.mResizeMode;
            byte b = -1;
            int iHashCode = str.hashCode();
            if (iHashCode != -1614504594) {
                if (iHashCode == -1387149201 && str.equals("widthFix")) {
                    b = 0;
                }
            } else if (str.equals("heightFix")) {
                b = 1;
            }
            if (b == 0) {
                float f = Integer.parseInt(map.get("height").toString()) * (getLayoutWidth() / Integer.parseInt(map.get("width").toString()));
                if (getLayoutHeight() != f) {
                    WXBridgeManager.getInstance().setStyleHeight(getInstanceId(), getRef(), f);
                    return;
                }
                return;
            }
            if (b != 1) {
                return;
            }
            float f2 = Integer.parseInt(map.get("width").toString()) * (getLayoutHeight() / Integer.parseInt(map.get("height").toString()));
            if (getLayoutWidth() != f2) {
                WXBridgeManager.getInstance().setStyleWidth(getInstanceId(), getRef(), f2);
            }
        }
    }

    @WXComponentProp(name = "fadeShow")
    public void setFadeAnim(String str) {
        if (TextUtils.isEmpty(str) || getHostView() == null) {
            return;
        }
        ((FrescoImageView) getHostView()).setFadeShow(Boolean.valueOf(str).booleanValue());
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    @Override // com.taobao.weex.ui.component.WXImage, com.taobao.weex.ui.component.WXComponent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean setProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            r1 = -934437708(0xffffffffc84d9cb4, float:-210546.81)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L2a
            r1 = 3357091(0x3339a3, float:4.704286E-39)
            if (r0 == r1) goto L20
            r1 = 2049757303(0x7a2cd077, float:2.243258E35)
            if (r0 == r1) goto L16
            goto L34
        L16:
            java.lang.String r0 = "resizeMode"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L34
            r0 = 0
            goto L35
        L20:
            java.lang.String r0 = "mode"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L34
            r0 = 2
            goto L35
        L2a:
            java.lang.String r0 = "resize"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L34
            r0 = 1
            goto L35
        L34:
            r0 = -1
        L35:
            r1 = 0
            if (r0 == 0) goto L55
            if (r0 == r3) goto L4b
            if (r0 == r2) goto L41
            boolean r5 = super.setProperty(r5, r6)
            return r5
        L41:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r1)
            if (r5 == 0) goto L4a
            r4.setResizeMode(r5)
        L4a:
            return r3
        L4b:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r1)
            if (r5 == 0) goto L54
            r4.setResizeMode(r5)
        L54:
            return r3
        L55:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r1)
            if (r5 == 0) goto L5e
            r4.setResizeMode(r5)
        L5e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.weex.adapter.FrescoImageComponent.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.taobao.weex.ui.component.WXImage
    public void setResizeMode(String str) {
        FrescoImageView frescoImageView = (FrescoImageView) getHostView();
        ScalingUtils.ScaleType customScaleType = ScalingUtils.ScaleType.FIT_XY;
        this.mResizeMode = str;
        if (!TextUtils.isEmpty(str)) {
            byte b = -1;
            switch (str.hashCode()) {
                case -1881872635:
                    if (str.equals("stretch")) {
                        b = 2;
                    }
                    break;
                case -1687059567:
                    if (str.equals("top right")) {
                        b = 12;
                    }
                    break;
                case -1614504594:
                    if (str.equals("heightFix")) {
                        b = 16;
                    }
                    break;
                case -1387149201:
                    if (str.equals("widthFix")) {
                        b = 15;
                    }
                    break;
                case -1383228885:
                    if (str.equals("bottom")) {
                        b = 7;
                    }
                    break;
                case -1364013995:
                    if (str.equals("center")) {
                        b = 8;
                    }
                    break;
                case -1362001767:
                    if (str.equals("aspectFit")) {
                        b = 4;
                    }
                    break;
                case -1024435214:
                    if (str.equals("top left")) {
                        b = 11;
                    }
                    break;
                case -797304696:
                    if (str.equals("scaleToFill")) {
                        b = 3;
                    }
                    break;
                case -667379492:
                    if (str.equals("bottom left")) {
                        b = 13;
                    }
                    break;
                case 115029:
                    if (str.equals("top")) {
                        b = 6;
                    }
                    break;
                case 3317767:
                    if (str.equals("left")) {
                        b = 9;
                    }
                    break;
                case 94852023:
                    if (str.equals(IApp.ConfigProperty.CONFIG_COVER)) {
                        b = 0;
                    }
                    break;
                case 108511772:
                    if (str.equals("right")) {
                        b = 10;
                    }
                    break;
                case 727618043:
                    if (str.equals("aspectFill")) {
                        b = 5;
                    }
                    break;
                case 791733223:
                    if (str.equals("bottom right")) {
                        b = db.l;
                    }
                    break;
                case 951526612:
                    if (str.equals("contain")) {
                        b = 1;
                    }
                    break;
            }
            switch (b) {
                case 0:
                    customScaleType = ScalingUtils.ScaleType.CENTER_CROP;
                    break;
                case 1:
                    customScaleType = ScalingUtils.ScaleType.FIT_CENTER;
                    break;
                case 2:
                    customScaleType = ScalingUtils.ScaleType.FIT_XY;
                    break;
                case 3:
                    customScaleType = ScalingUtils.ScaleType.FIT_XY;
                    break;
                case 4:
                    customScaleType = ScalingUtils.ScaleType.FIT_CENTER;
                    break;
                case 5:
                    customScaleType = ScalingUtils.ScaleType.CENTER_CROP;
                    break;
                case 6:
                    customScaleType = new CustomScaleType(0.5f, 0.0f);
                    break;
                case 7:
                    customScaleType = new CustomScaleType(0.5f, 1.0f);
                    break;
                case 8:
                    customScaleType = new CustomScaleType(0.5f, 0.5f);
                    break;
                case 9:
                    customScaleType = new CustomScaleType(0.0f, 0.5f);
                    break;
                case 10:
                    customScaleType = new CustomScaleType(1.0f, 0.5f);
                    break;
                case 11:
                    customScaleType = new CustomScaleType(0.0f, 0.0f);
                    break;
                case 12:
                    customScaleType = new CustomScaleType(1.0f, 0.0f);
                    break;
                case 13:
                    customScaleType = new CustomScaleType(0.0f, 1.0f);
                    break;
                case 14:
                    customScaleType = new CustomScaleType(1.0f, 1.0f);
                    break;
                case 15:
                case 16:
                    customScaleType = ScalingUtils.ScaleType.FIT_CENTER;
                    break;
            }
        }
        frescoImageView.getHierarchy().setActualImageScaleType(customScaleType);
    }

    @Override // com.taobao.weex.ui.component.WXImage, com.taobao.weex.ui.component.WXComponent
    public void updateProperties(Map<String, Object> map) {
        if (getHostView() != null) {
            super.updateProperties(map);
            updateBorderRadius();
        }
    }

    @Override // com.taobao.weex.ui.component.WXImage, com.taobao.weex.ui.component.WXComponent
    public ImageView initComponentHostView(Context context) {
        FrescoImageView frescoImageView = new FrescoImageView(context);
        frescoImageView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
        return frescoImageView;
    }
}
