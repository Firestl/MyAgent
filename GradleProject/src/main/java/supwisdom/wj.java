package supwisdom;

import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import com.taobao.weex.utils.WXUtils;
import java.util.Map;

/* JADX INFO: compiled from: BindingXGestureHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class wj extends fj {
    public boolean s;
    public WXGesture t;

    public wj(Context context, yi yiVar, Object... objArr) {
        super(context, yiVar, objArr);
        this.s = false;
        this.t = null;
    }

    @Override // supwisdom.fj, supwisdom.vi
    public boolean a(String str, String str2) {
        WXGesture wXGesture;
        boolean zA = super.a(str, str2);
        if (!this.s || (wXGesture = this.t) == null) {
            return zA;
        }
        try {
            return zA | wXGesture.removeTouchListener(this);
        } catch (Throwable th) {
            xi.b("[ExpressionGestureHandler]  disabled failed." + th.getMessage());
            return zA;
        }
    }

    @Override // supwisdom.aj, supwisdom.vi
    public void b(Map<String, Object> map) {
        super.b(map);
        if (map != null) {
            this.s = WXUtils.getBoolean(map.get("experimentalGestureFeatures"), false).booleanValue();
        }
    }

    @Override // supwisdom.fj, supwisdom.vi
    public boolean b(String str, String str2) {
        if (!this.s) {
            return super.b(str, str2);
        }
        WXComponent wXComponentA = zj.a(TextUtils.isEmpty(this.f) ? this.f6928e : this.f, str);
        if (wXComponentA == null) {
            return super.b(str, str2);
        }
        KeyEvent.Callback hostView = wXComponentA.getHostView();
        if ((hostView instanceof ViewGroup) && (hostView instanceof WXGestureObservable)) {
            try {
                WXGesture gestureListener = ((WXGestureObservable) hostView).getGestureListener();
                this.t = gestureListener;
                if (gestureListener != null) {
                    gestureListener.addOnTouchListener(this);
                    xi.a("[ExpressionGestureHandler] onCreate success. {source:" + str + ",type:" + str2 + Operators.BLOCK_END_STR);
                    return true;
                }
                return super.b(str, str2);
            } catch (Throwable th) {
                xi.b("experimental gesture features open failed." + th.getMessage());
                return super.b(str, str2);
            }
        }
        return super.b(str, str2);
    }
}
