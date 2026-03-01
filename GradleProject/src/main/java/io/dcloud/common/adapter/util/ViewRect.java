package io.dcloud.common.adapter.util;

import android.os.Build;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ViewRect {
    public static final int DEFAULT_MARGIN = 0;
    public static byte DOCK_BOTTOM = 6;
    public static byte DOCK_LEFT = 3;
    public static byte DOCK_RIGHT = 4;
    public static byte DOCK_TOP = 5;
    public static byte POSITION_ABSOLUTE = 1;
    public static byte POSITION_DOCK = 2;
    public static byte POSITION_STATIC;
    public int anim_left;
    public int anim_top;
    public int bottom;
    public int height;
    public int left;
    public String mStatusbarColor;
    public int right;
    public int top;
    public int width;
    public ViewRect mParentViewRect = null;
    public ViewRect mFrameParentViewRect = null;
    public ArrayList<ViewRect> mRelViewRectDockSet = null;
    public float mWebviewScale = 1.0f;
    public String margin = String.valueOf(0);
    public boolean isStatusbar = false;
    public byte mPosition = POSITION_STATIC;
    public byte mDock = DOCK_TOP;
    public JSONObject mJsonViewOption = JSONUtil.createJSONObject("{}");
    public boolean isStatusbarDodifyHeight = false;
    public boolean isNotHeightFullScreen = false;
    public boolean doUpdate = false;
    public boolean allowUpdate = true;

    /* JADX WARN: Removed duplicated region for block: B:58:0x0154  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void layoutDockViewRect(io.dcloud.common.adapter.util.ViewRect r16, io.dcloud.common.adapter.util.ViewRect r17, boolean r18) {
        /*
            Method dump skipped, instruction units count: 353
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.adapter.util.ViewRect.layoutDockViewRect(io.dcloud.common.adapter.util.ViewRect, io.dcloud.common.adapter.util.ViewRect, boolean):void");
    }

    private void layoutWithRelViewRect() {
        ArrayList<ViewRect> arrayList = this.mRelViewRectDockSet;
        if (arrayList == null) {
            return;
        }
        Iterator<ViewRect> it = arrayList.iterator();
        while (it.hasNext()) {
            layoutDockViewRect(this, it.next());
        }
    }

    public void checkValueIsPercentage(String str, int i, int i2, boolean z, boolean z2) {
        try {
            if (!this.mJsonViewOption.isNull(str) || z) {
                if ((this.mJsonViewOption.isNull(str) || this.mJsonViewOption.getString(str).indexOf("%") < 0) && !z2) {
                    this.mJsonViewOption.put(str, i / this.mWebviewScale);
                    return;
                }
                if (i2 > 0) {
                    this.mJsonViewOption.put(str, ((i * 100) / i2) + "%");
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void commitUpdate2JSONObject(boolean z, boolean z2) {
        ViewRect viewRect = this.mFrameParentViewRect;
        int i = viewRect != null ? viewRect.width : this.mParentViewRect.width;
        if (viewRect == null) {
            viewRect = this.mParentViewRect;
        }
        int i2 = viewRect.height;
        checkValueIsPercentage("left", this.left, i, z, z2);
        checkValueIsPercentage("top", this.top, i2, z, z2);
        checkValueIsPercentage("width", this.width, i, z, z2);
        checkValueIsPercentage("height", this.height, i2, z, z2);
        checkValueIsPercentage("right", this.right, i, z, z2);
        checkValueIsPercentage("bottom", this.bottom, i2, z, z2);
    }

    public void delRelViewRect(ViewRect viewRect) {
        ArrayList<ViewRect> arrayList = this.mRelViewRectDockSet;
        if (arrayList != null) {
            arrayList.remove(viewRect);
        }
    }

    public ViewRect getParentViewRect() {
        return this.mParentViewRect;
    }

    public boolean hasHeightAbsolutevalue() {
        JSONObject jSONObject = this.mJsonViewOption;
        return (jSONObject == null || !jSONObject.has("height") || this.mJsonViewOption.isNull("height")) ? this.mJsonViewOption.has("bottom") && this.mJsonViewOption.has("top") : true ^ JSONUtil.getString(this.mJsonViewOption, "height").endsWith("%");
    }

    public boolean hasRectChanged(ViewRect viewRect, ViewRect viewRect2) {
        return (viewRect.left == viewRect2.left && viewRect.top == viewRect2.top && viewRect.height == viewRect2.height && viewRect.width == viewRect2.width) ? false : true;
    }

    public boolean isBottomAbsolute() {
        JSONObject jSONObject = this.mJsonViewOption;
        return jSONObject != null && jSONObject.has("bottom");
    }

    public boolean isHeightAbsolute() {
        JSONObject jSONObject = this.mJsonViewOption;
        if (jSONObject == null) {
            return true;
        }
        if (!jSONObject.has("height") || this.mJsonViewOption.isNull("height")) {
            if (this.mJsonViewOption.has("bottom") && this.mJsonViewOption.has("top")) {
                return true;
            }
            if (this.mJsonViewOption.has("height") || this.mJsonViewOption.isNull("height")) {
            }
        } else if (!JSONUtil.getString(this.mJsonViewOption, "height").equals("100%")) {
            return true;
        }
        return false;
    }

    public void onScreenChanged(int i, int i2) {
        updateViewData(this.mJsonViewOption, i, i2);
    }

    public void putRelViewRect(ViewRect viewRect) {
        if (this.mRelViewRectDockSet == null) {
            this.mRelViewRectDockSet = new ArrayList<>();
        }
        this.mRelViewRectDockSet.add(viewRect);
    }

    public void setFrameParentViewRect(ViewRect viewRect) {
        this.mFrameParentViewRect = viewRect;
        this.mFrameParentViewRect = null;
    }

    public void setParentViewRect(ViewRect viewRect) {
        this.mParentViewRect = viewRect;
    }

    public void setUpdateAction(boolean z) {
        this.doUpdate = z;
    }

    public boolean updateViewData(JSONObject jSONObject, int i, int i2) {
        return updateViewData(jSONObject, i, i2, this.mWebviewScale);
    }

    public void onScreenChanged() {
        updateViewData(this.mJsonViewOption);
    }

    public boolean updateViewData(JSONObject jSONObject, int i, int i2, float f) {
        int i3;
        boolean z;
        int i4;
        boolean z2;
        boolean zIsEquals;
        JSONObject jSONObject2 = jSONObject;
        JSONObject jSONObject3 = this.mJsonViewOption;
        if (jSONObject3 == null) {
            return false;
        }
        if (jSONObject3 != null) {
            JSONUtil.combinJSONObject(jSONObject3, jSONObject2);
            jSONObject2 = this.mJsonViewOption;
        } else {
            this.mJsonViewOption = jSONObject2;
        }
        int i5 = i2 < 0 ? this.height + this.bottom + this.top : i2;
        int i6 = i < 0 ? this.width : i;
        int i7 = this.left;
        int i8 = this.top;
        int i9 = this.width;
        int i10 = this.height;
        if (jSONObject2.has(AbsoluteConst.JSONKEY_STATUSBAR) && BaseInfo.isImmersive && Build.VERSION.SDK_INT >= 19) {
            this.isStatusbar = true;
            JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject(AbsoluteConst.JSONKEY_STATUSBAR);
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.has("background")) {
                this.mStatusbarColor = jSONObjectOptJSONObject.optString("background", this.mStatusbarColor);
            }
        }
        boolean z3 = this.doUpdate || !jSONObject2.isNull("left");
        boolean z4 = this.doUpdate || !jSONObject2.isNull("right");
        boolean z5 = this.doUpdate || !jSONObject2.isNull("top");
        if (this.doUpdate || !jSONObject2.isNull("width")) {
            i3 = i10;
            z = true;
        } else {
            i3 = i10;
            z = false;
        }
        if (this.doUpdate || !jSONObject2.isNull("height")) {
            i4 = i7;
            z2 = true;
        } else {
            i4 = i7;
            z2 = false;
        }
        boolean z6 = z5;
        boolean z7 = this.doUpdate || !jSONObject2.isNull("bottom");
        this.left = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject2, "left"), i6, 0, f);
        this.top = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject2, "top"), i5, 0, f);
        this.width = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject2, "width"), i6, z ? this.width : i6, f);
        int iConvertToScreenInt = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject2, "height"), i5, z2 ? this.height : i5, f);
        this.height = iConvertToScreenInt;
        if (z2 && iConvertToScreenInt < i5) {
            this.isNotHeightFullScreen = true;
            if (this.isStatusbar) {
                this.isStatusbarDodifyHeight = true;
            }
        }
        this.right = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject2, "right"), i6, this.right, f);
        this.bottom = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject2, "bottom"), i5, this.bottom, f);
        if (jSONObject2.isNull("margin")) {
            zIsEquals = false;
        } else {
            String string = JSONUtil.getString(jSONObject2, "margin");
            this.margin = string;
            zIsEquals = PdrUtil.isEquals("auto", string);
        }
        if (z3) {
            if (!z && z4) {
                this.width = (i6 - this.left) - this.right;
            }
        } else if (!z && z4) {
            this.left = -this.right;
        } else if (z && !z4 && zIsEquals) {
            this.left = (i6 - this.width) / 2;
        } else if (z && z4) {
            this.left = (i6 - this.width) - this.right;
        }
        if (z6) {
            if (!z2 && z7) {
                this.height = (i5 - this.top) - this.bottom;
            }
        } else if (!z2 && z7) {
            this.top = -this.bottom;
        } else if (z2 && !z7 && zIsEquals) {
            this.top = (i5 - this.height) / 2;
        } else if (z2 && z7) {
            this.top = (i5 - this.height) - this.bottom;
        }
        layoutWithRelViewRect();
        layoutDockViewRect(this.mParentViewRect, this, false);
        int i11 = this.left;
        boolean z8 = (i4 == i11 && i8 == this.top && i3 == this.height && i9 == this.width) ? false : true;
        this.anim_left = i11;
        this.anim_top = this.top;
        return z8;
    }

    public void commitUpdate2JSONObject() {
        commitUpdate2JSONObject(false, false);
    }

    public void updateViewData(ViewRect viewRect) {
        this.mWebviewScale = viewRect.mWebviewScale;
        this.left = viewRect.left;
        this.top = viewRect.top;
        this.width = viewRect.width;
        this.height = viewRect.height;
        this.right = viewRect.right;
        this.bottom = viewRect.bottom;
        updateViewData(viewRect.mJsonViewOption);
    }

    public boolean updateViewData(JSONObject jSONObject) {
        ViewRect viewRect = this.mParentViewRect;
        if (viewRect == null) {
            return false;
        }
        return updateViewData(jSONObject, viewRect.width, viewRect.height);
    }

    public static void layoutDockViewRect(ViewRect viewRect, ViewRect viewRect2) {
        layoutDockViewRect(viewRect, viewRect2, true);
    }
}
