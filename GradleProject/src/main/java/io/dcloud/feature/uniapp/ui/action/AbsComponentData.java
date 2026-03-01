package io.dcloud.feature.uniapp.ui.action;

import android.view.View;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.dom.WXEvent;
import com.taobao.weex.dom.WXStyle;
import com.taobao.weex.utils.WXUtils;
import com.umeng.analytics.pro.db;
import io.dcloud.feature.uniapp.dom.AbsAttr;
import io.dcloud.feature.uniapp.dom.AbsCSSShorthand;
import io.dcloud.feature.uniapp.dom.AbsEvent;
import io.dcloud.feature.uniapp.dom.AbsStyle;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbsComponentData<T extends View> {
    public AbsAttr mAttributes;
    public AbsCSSShorthand mBorders;
    public String mComponentType;
    public AbsEvent mEvents;
    public AbsCSSShorthand mMargins;
    public AbsCSSShorthand mPaddings;
    public String mParentRef;
    public String mRef;
    public AbsStyle mStyles;
    public long renderObjectPr = 0;

    /* JADX INFO: renamed from: io.dcloud.feature.uniapp.ui.action.AbsComponentData$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$io$dcloud$feature$uniapp$dom$AbsCSSShorthand$TYPE;

        static {
            int[] iArr = new int[AbsCSSShorthand.TYPE.values().length];
            $SwitchMap$io$dcloud$feature$uniapp$dom$AbsCSSShorthand$TYPE = iArr;
            try {
                iArr[AbsCSSShorthand.TYPE.MARGIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$dcloud$feature$uniapp$dom$AbsCSSShorthand$TYPE[AbsCSSShorthand.TYPE.PADDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$dcloud$feature$uniapp$dom$AbsCSSShorthand$TYPE[AbsCSSShorthand.TYPE.BORDER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public AbsComponentData(String str, String str2, String str3) {
        this.mRef = str;
        this.mComponentType = str2;
        this.mParentRef = str3;
    }

    private void addBorder(CSSShorthand.EDGE edge, float f) {
        if (this.mBorders == null) {
            this.mBorders = new CSSShorthand();
        }
        this.mBorders.set(edge, f);
    }

    private void addMargin(CSSShorthand.EDGE edge, float f) {
        if (this.mMargins == null) {
            this.mMargins = new CSSShorthand();
        }
        this.mMargins.set(edge, f);
    }

    private void addPadding(CSSShorthand.EDGE edge, float f) {
        if (this.mPaddings == null) {
            this.mPaddings = new CSSShorthand();
        }
        this.mPaddings.set(edge, f);
    }

    public final void addAttr(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        AbsAttr absAttr = this.mAttributes;
        if (absAttr == null) {
            this.mAttributes = new WXAttr(map, 0);
        } else {
            absAttr.putAll(map);
        }
    }

    public final void addEvent(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return;
        }
        if (this.mEvents == null) {
            this.mEvents = new WXEvent();
        }
        this.mEvents.addAll(set);
    }

    public final void addShorthand(float[] fArr, AbsCSSShorthand.TYPE type) {
        if (fArr == null) {
            fArr = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        }
        if (fArr.length == 4) {
            int i = AnonymousClass1.$SwitchMap$io$dcloud$feature$uniapp$dom$AbsCSSShorthand$TYPE[type.ordinal()];
            if (i == 1) {
                AbsCSSShorthand absCSSShorthand = this.mMargins;
                if (absCSSShorthand == null) {
                    this.mMargins = new CSSShorthand(fArr);
                    return;
                } else {
                    absCSSShorthand.replace(fArr);
                    return;
                }
            }
            if (i == 2) {
                AbsCSSShorthand absCSSShorthand2 = this.mPaddings;
                if (absCSSShorthand2 == null) {
                    this.mPaddings = new CSSShorthand(fArr);
                    return;
                } else {
                    absCSSShorthand2.replace(fArr);
                    return;
                }
            }
            if (i != 3) {
                return;
            }
            AbsCSSShorthand absCSSShorthand3 = this.mBorders;
            if (absCSSShorthand3 == null) {
                this.mBorders = new CSSShorthand(fArr);
            } else {
                absCSSShorthand3.replace(fArr);
            }
        }
    }

    public void addStyle(Map<String, Object> map) {
        addStyle(map, false);
    }

    @Override // 
    /* JADX INFO: renamed from: clone */
    public abstract AbsComponentData<T> mo33clone() throws CloneNotSupportedException;

    public AbsAttr getAttrs() {
        if (this.mAttributes == null) {
            this.mAttributes = new WXAttr();
        }
        return this.mAttributes;
    }

    public AbsCSSShorthand getBorder() {
        if (this.mBorders == null) {
            this.mBorders = new CSSShorthand();
        }
        return this.mBorders;
    }

    public AbsEvent getEvents() {
        if (this.mEvents == null) {
            this.mEvents = new WXEvent();
        }
        return this.mEvents;
    }

    public AbsCSSShorthand getMargin() {
        if (this.mMargins == null) {
            this.mMargins = new CSSShorthand();
        }
        return this.mMargins;
    }

    public AbsCSSShorthand getPadding() {
        if (this.mPaddings == null) {
            this.mPaddings = new CSSShorthand();
        }
        return this.mPaddings;
    }

    public long getRenderObjectPr() {
        return this.renderObjectPr;
    }

    public AbsStyle getStyles() {
        if (this.mStyles == null) {
            this.mStyles = new WXStyle();
        }
        return this.mStyles;
    }

    public boolean isRenderPtrEmpty() {
        return this.renderObjectPr == 0;
    }

    public final void setBorders(CSSShorthand cSSShorthand) {
        this.mBorders = cSSShorthand;
    }

    public final void setMargins(CSSShorthand cSSShorthand) {
        this.mMargins = cSSShorthand;
    }

    public final void setPaddings(CSSShorthand cSSShorthand) {
        this.mPaddings = cSSShorthand;
    }

    public synchronized void setRenderObjectPr(long j) {
        if (this.renderObjectPr != j) {
            if (this.renderObjectPr != 0) {
                throw new RuntimeException("RenderObjectPr has " + j + " old renderObjectPtr " + this.renderObjectPr);
            }
            this.renderObjectPr = j;
        }
    }

    public final void addStyle(Map<String, Object> map, boolean z) {
        if (map == null || map.isEmpty()) {
            return;
        }
        AbsStyle absStyle = this.mStyles;
        if (absStyle == null) {
            this.mStyles = new WXStyle(map);
        } else {
            absStyle.putAll(map, z);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void addShorthand(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            byte b = -1;
            switch (key.hashCode()) {
                case -1971292586:
                    if (key.equals(Constants.Name.BORDER_RIGHT_WIDTH)) {
                        b = 7;
                    }
                    break;
                case -1501175880:
                    if (key.equals(Constants.Name.PADDING_LEFT)) {
                        b = 11;
                    }
                    break;
                case -1452542531:
                    if (key.equals(Constants.Name.BORDER_TOP_WIDTH)) {
                        b = 6;
                    }
                    break;
                case -1290574193:
                    if (key.equals(Constants.Name.BORDER_BOTTOM_WIDTH)) {
                        b = 8;
                    }
                    break;
                case -1081309778:
                    if (key.equals("margin")) {
                        b = 0;
                    }
                    break;
                case -1044792121:
                    if (key.equals(Constants.Name.MARGIN_TOP)) {
                        b = 2;
                    }
                    break;
                case -806339567:
                    if (key.equals("padding")) {
                        b = 10;
                    }
                    break;
                case -289173127:
                    if (key.equals(Constants.Name.MARGIN_BOTTOM)) {
                        b = 4;
                    }
                    break;
                case -223992013:
                    if (key.equals(Constants.Name.BORDER_LEFT_WIDTH)) {
                        b = 9;
                    }
                    break;
                case 90130308:
                    if (key.equals(Constants.Name.PADDING_TOP)) {
                        b = 12;
                    }
                    break;
                case 202355100:
                    if (key.equals(Constants.Name.PADDING_BOTTOM)) {
                        b = db.l;
                    }
                    break;
                case 713848971:
                    if (key.equals(Constants.Name.PADDING_RIGHT)) {
                        b = 13;
                    }
                    break;
                case 741115130:
                    if (key.equals(Constants.Name.BORDER_WIDTH)) {
                        b = 5;
                    }
                    break;
                case 975087886:
                    if (key.equals(Constants.Name.MARGIN_RIGHT)) {
                        b = 3;
                    }
                    break;
                case 1970934485:
                    if (key.equals(Constants.Name.MARGIN_LEFT)) {
                        b = 1;
                    }
                    break;
            }
            switch (b) {
                case 0:
                    addMargin(CSSShorthand.EDGE.ALL, WXUtils.fastGetFloat(map.get(key)));
                    break;
                case 1:
                    addMargin(CSSShorthand.EDGE.LEFT, WXUtils.fastGetFloat(map.get(key)));
                    break;
                case 2:
                    addMargin(CSSShorthand.EDGE.TOP, WXUtils.fastGetFloat(map.get(key)));
                    break;
                case 3:
                    addMargin(CSSShorthand.EDGE.RIGHT, WXUtils.fastGetFloat(map.get(key)));
                    break;
                case 4:
                    addMargin(CSSShorthand.EDGE.BOTTOM, WXUtils.fastGetFloat(map.get(key)));
                    break;
                case 5:
                    addBorder(CSSShorthand.EDGE.ALL, WXUtils.fastGetFloat(map.get(key)));
                    break;
                case 6:
                    addBorder(CSSShorthand.EDGE.TOP, WXUtils.fastGetFloat(map.get(key)));
                    break;
                case 7:
                    addBorder(CSSShorthand.EDGE.RIGHT, WXUtils.fastGetFloat(map.get(key)));
                    break;
                case 8:
                    addBorder(CSSShorthand.EDGE.BOTTOM, WXUtils.fastGetFloat(map.get(key)));
                    break;
                case 9:
                    addBorder(CSSShorthand.EDGE.LEFT, WXUtils.fastGetFloat(map.get(key)));
                    break;
                case 10:
                    addPadding(CSSShorthand.EDGE.ALL, WXUtils.fastGetFloat(map.get(key)));
                    break;
                case 11:
                    addPadding(CSSShorthand.EDGE.LEFT, WXUtils.fastGetFloat(map.get(key)));
                    break;
                case 12:
                    addPadding(CSSShorthand.EDGE.TOP, WXUtils.fastGetFloat(map.get(key)));
                    break;
                case 13:
                    addPadding(CSSShorthand.EDGE.RIGHT, WXUtils.fastGetFloat(map.get(key)));
                    break;
                case 14:
                    addPadding(CSSShorthand.EDGE.BOTTOM, WXUtils.fastGetFloat(map.get(key)));
                    break;
            }
        }
    }
}
