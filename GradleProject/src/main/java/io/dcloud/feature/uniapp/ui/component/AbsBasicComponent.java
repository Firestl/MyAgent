package io.dcloud.feature.uniapp.ui.component;

import android.view.View;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.ui.action.GraphicPosition;
import com.taobao.weex.ui.action.GraphicSize;
import io.dcloud.feature.uniapp.dom.AbsAttr;
import io.dcloud.feature.uniapp.dom.AbsCSSShorthand;
import io.dcloud.feature.uniapp.dom.AbsEvent;
import io.dcloud.feature.uniapp.dom.AbsStyle;
import io.dcloud.feature.uniapp.ui.action.AbsComponentData;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbsBasicComponent<T extends View> {
    public AbsComponentData mBasicComponentData;
    public String mComponentType;
    public Object mExtra;
    public boolean mIsLayoutRTL;
    public GraphicPosition mLayoutPosition;
    public GraphicSize mLayoutSize;
    public String mRef;
    public float mViewPortWidth = 750.0f;

    public AbsBasicComponent(AbsComponentData absComponentData) {
        this.mBasicComponentData = absComponentData;
        this.mRef = absComponentData.mRef;
        this.mComponentType = absComponentData.mComponentType;
    }

    public final void addAttr(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        this.mBasicComponentData.addAttr(map);
    }

    public final void addEvent(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return;
        }
        this.mBasicComponentData.addEvent(set);
    }

    public final void addShorthand(Map<String, String> map) {
        AbsComponentData absComponentData;
        if (map.isEmpty() || (absComponentData = this.mBasicComponentData) == null) {
            return;
        }
        absComponentData.addShorthand(map);
    }

    public final void addStyle(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        this.mBasicComponentData.addStyle(map);
    }

    public void bindComponent(AbsBasicComponent absBasicComponent) {
        this.mComponentType = absBasicComponent.getComponentType();
        this.mRef = absBasicComponent.getRef();
    }

    public AbsAttr getAttrs() {
        return this.mBasicComponentData.getAttrs();
    }

    public AbsComponentData getBasicComponentData() {
        return this.mBasicComponentData;
    }

    public AbsCSSShorthand getBorder() {
        return this.mBasicComponentData.getBorder();
    }

    public float getCSSLayoutBottom() {
        GraphicPosition graphicPosition = this.mLayoutPosition;
        if (graphicPosition == null) {
            return 0.0f;
        }
        return graphicPosition.getBottom();
    }

    public float getCSSLayoutLeft() {
        GraphicPosition graphicPosition = this.mLayoutPosition;
        if (graphicPosition == null) {
            return 0.0f;
        }
        return graphicPosition.getLeft();
    }

    public float getCSSLayoutRight() {
        GraphicPosition graphicPosition = this.mLayoutPosition;
        if (graphicPosition == null) {
            return 0.0f;
        }
        return graphicPosition.getRight();
    }

    public float getCSSLayoutTop() {
        GraphicPosition graphicPosition = this.mLayoutPosition;
        if (graphicPosition == null) {
            return 0.0f;
        }
        return graphicPosition.getTop();
    }

    public String getComponentType() {
        return this.mComponentType;
    }

    public AbsEvent getEvents() {
        return this.mBasicComponentData.getEvents();
    }

    public Object getExtra() {
        return this.mExtra;
    }

    public float getLayoutHeight() {
        GraphicSize graphicSize = this.mLayoutSize;
        if (graphicSize == null) {
            return 0.0f;
        }
        return graphicSize.getHeight();
    }

    public GraphicPosition getLayoutPosition() {
        if (this.mLayoutPosition == null) {
            this.mLayoutPosition = new GraphicPosition(0.0f, 0.0f, 0.0f, 0.0f);
        }
        return this.mLayoutPosition;
    }

    public GraphicSize getLayoutSize() {
        if (this.mLayoutSize == null) {
            this.mLayoutSize = new GraphicSize(0.0f, 0.0f);
        }
        return this.mLayoutSize;
    }

    public float getLayoutWidth() {
        GraphicSize graphicSize = this.mLayoutSize;
        if (graphicSize == null) {
            return 0.0f;
        }
        return graphicSize.getWidth();
    }

    public AbsCSSShorthand getMargin() {
        return this.mBasicComponentData.getMargin();
    }

    public AbsCSSShorthand getPadding() {
        return this.mBasicComponentData.getPadding();
    }

    public String getRef() {
        return this.mRef;
    }

    public AbsStyle getStyles() {
        return this.mBasicComponentData.getStyles();
    }

    public int getViewPortWidth() {
        return Math.round(this.mViewPortWidth);
    }

    public float getViewPortWidthForFloat() {
        return this.mViewPortWidth;
    }

    public boolean isLayoutRTL() {
        return this.mIsLayoutRTL;
    }

    public final void setBorders(CSSShorthand cSSShorthand) {
        this.mBasicComponentData.setBorders(cSSShorthand);
    }

    public void setIsLayoutRTL(boolean z) {
        this.mIsLayoutRTL = z;
    }

    public void setLayoutPosition(GraphicPosition graphicPosition) {
        this.mLayoutPosition = graphicPosition;
    }

    public void setLayoutSize(GraphicSize graphicSize) {
        this.mLayoutSize = graphicSize;
    }

    public final void setMargins(CSSShorthand cSSShorthand) {
        this.mBasicComponentData.setMargins(cSSShorthand);
    }

    public final void setPaddings(CSSShorthand cSSShorthand) {
        this.mBasicComponentData.setPaddings(cSSShorthand);
    }

    public void setViewPortWidth(float f) {
        this.mViewPortWidth = f;
    }

    public void updateExtra(Object obj) {
        this.mExtra = obj;
    }

    public final void updateStyle(Map<String, Object> map, boolean z) {
        if (map == null || map.isEmpty()) {
            return;
        }
        this.mBasicComponentData.getStyles().updateStyle(map, z);
    }

    public final void addStyle(Map<String, Object> map, boolean z) {
        if (map == null || map.isEmpty()) {
            return;
        }
        this.mBasicComponentData.addStyle(map, z);
    }
}
