package io.dcloud.weex;

import android.os.Handler;
import android.view.MotionEvent;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.view.gesture.WXGestureType;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class ViewHover {
    public static final String VIEW_HOVER_EVENT = "view_hover_event";
    public WXComponent component;
    public Handler handler;
    public JSONObject hoverClass;
    public int hoverStartTime;
    public int hoverStayTime;
    public boolean hoverStopPropagation;
    public boolean isHover;
    public boolean isReceiveTouch;
    public Map<String, Object> originalStyles;
    public Runnable touchEndRunnable;
    public Runnable touchStartRunnable;

    public ViewHover(WXComponent wXComponent) {
        this(wXComponent, null);
    }

    public void destroy() {
        this.component = null;
        this.originalStyles = null;
    }

    public int getHoverStartTime() {
        return this.hoverStartTime;
    }

    public int getHoverStayTime() {
        return this.hoverStayTime;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void handleMotionEvent(WXGestureType wXGestureType, MotionEvent motionEvent) {
        if (this.hoverClass == null || !this.isReceiveTouch) {
            return;
        }
        String string = wXGestureType.toString();
        byte b = -1;
        switch (string.hashCode()) {
            case -1578593149:
                if (string.equals(AbsoluteConst.EVENTS_WEBVIEW_ONTOUCH_START)) {
                    b = 0;
                }
                break;
            case -819532484:
                if (string.equals("touchend")) {
                    b = 2;
                }
                break;
            case 364536720:
                if (string.equals("touchmove")) {
                    b = 1;
                }
                break;
            case 2127979129:
                if (string.equals("touchcancel")) {
                    b = 3;
                }
                break;
        }
        if (b == 0) {
            this.handler.removeCallbacks(this.touchEndRunnable);
            this.handler.removeCallbacks(this.touchStartRunnable);
            this.handler.postDelayed(this.touchStartRunnable, this.hoverStartTime);
        } else if (b == 2) {
            this.isHover = true;
            this.handler.removeCallbacks(this.touchEndRunnable);
            this.handler.postDelayed(this.touchEndRunnable, this.hoverStayTime);
        } else {
            if (b != 3) {
                return;
            }
            this.isHover = true;
            this.handler.removeCallbacks(this.touchEndRunnable);
            this.handler.postDelayed(this.touchEndRunnable, this.hoverStayTime);
        }
    }

    public boolean isHoverStopPropagation() {
        return this.hoverStopPropagation;
    }

    public void setHoverStartTime(int i) {
        this.hoverStartTime = i;
    }

    public void setHoverStayTime(int i) {
        this.hoverStayTime = i;
    }

    public void setHoverStopPropagation(boolean z) {
        this.hoverStopPropagation = z;
    }

    public void setReceiveTouch(boolean z) {
        this.isReceiveTouch = z;
    }

    public void update(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        JSONObject jSONObject2 = this.hoverClass;
        if (jSONObject2 == null) {
            this.hoverClass = jSONObject;
        } else {
            jSONObject2.putAll(jSONObject);
        }
    }

    public Map<String, Object> updateStatusAndGetUpdateStyles(boolean z) {
        JSONObject jSONObject = new JSONObject();
        if (z) {
            return this.hoverClass;
        }
        if (this.originalStyles == null) {
            return jSONObject;
        }
        Set<String> setKeySet = this.hoverClass.keySet();
        JSONObject jSONObject2 = new JSONObject();
        for (String str : setKeySet) {
            if (this.originalStyles.containsKey(str)) {
                jSONObject2.put(str, this.originalStyles.get(str));
            } else {
                jSONObject2.put(str, "");
            }
        }
        return jSONObject2;
    }

    public ViewHover(WXComponent wXComponent, JSONObject jSONObject) {
        this.hoverStopPropagation = false;
        this.hoverClass = null;
        this.hoverStartTime = 50;
        this.hoverStayTime = 400;
        this.isHover = false;
        this.isReceiveTouch = true;
        this.handler = new Handler();
        this.touchStartRunnable = new Runnable() { // from class: io.dcloud.weex.ViewHover.1
            @Override // java.lang.Runnable
            public void run() {
                if (ViewHover.this.isHover || ViewHover.this.component == null || ViewHover.this.component.getInstance() == null) {
                    return;
                }
                ViewHover viewHover = ViewHover.this;
                viewHover.originalStyles = viewHover.component.getStyles().mo31clone();
                ViewHover.this.component.setHoverClassStatus(true);
            }
        };
        this.touchEndRunnable = new Runnable() { // from class: io.dcloud.weex.ViewHover.2
            @Override // java.lang.Runnable
            public void run() {
                if (ViewHover.this.component == null || ViewHover.this.component.getInstance() == null) {
                    return;
                }
                ViewHover.this.component.setHoverClassStatus(false);
                ViewHover.this.isHover = false;
            }
        };
        update(jSONObject);
        this.component = wXComponent;
    }
}
