package supwisdom;

import android.content.Context;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.view.gesture.WXGesture;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: AbstractScrollEventHandler.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class bj extends aj {
    public int l;
    public int m;
    public boolean n;

    public bj(Context context, yi yiVar, Object... objArr) {
        super(context, yiVar, objArr);
        this.n = false;
    }

    @Override // supwisdom.vi
    public boolean a(String str, String str2) {
        c();
        this.n = false;
        a(WXGesture.END, this.l, this.m, 0.0d, 0.0d, 0.0d, 0.0d, new Object[0]);
        return true;
    }

    @Override // supwisdom.aj
    public void c(Map<String, Object> map) {
        a("exit", ((Double) map.get("internal_x")).doubleValue(), ((Double) map.get("internal_y")).doubleValue(), 0.0d, 0.0d, 0.0d, 0.0d, new Object[0]);
    }

    @Override // supwisdom.aj, supwisdom.vi
    public void onDestroy() {
        super.onDestroy();
        this.n = false;
    }

    @Override // supwisdom.aj
    public void a(String str, Map<String, Object> map) {
        a("interceptor", ((Double) map.get("internal_x")).doubleValue(), ((Double) map.get("internal_y")).doubleValue(), ((Double) map.get("dx")).doubleValue(), ((Double) map.get(Constants.Name.DISTANCE_Y)).doubleValue(), ((Double) map.get("tdx")).doubleValue(), ((Double) map.get("tdy")).doubleValue(), Collections.singletonMap("interceptor", str));
    }

    public void a(int i, int i2, int i3, int i4, int i5, int i6) {
        bj bjVar;
        if (xi.f9767a) {
            xi.a(String.format(Locale.getDefault(), "[ScrollHandler] scroll changed. (contentOffsetX:%d,contentOffsetY:%d,dx:%d,dy:%d,tdx:%d,tdy:%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)));
        }
        this.l = i;
        this.m = i2;
        if (this.n) {
            bjVar = this;
        } else {
            this.n = true;
            bjVar = this;
            bjVar.a("start", i, i2, i3, i4, i5, i6, new Object[0]);
        }
        try {
            lj.a(bjVar.d, i, i2, i3, i4, i5, i6, bjVar.h.a());
            if (bjVar.a(bjVar.i, bjVar.d)) {
                return;
            }
            bjVar.a(bjVar.f6927a, bjVar.d, Constants.Event.SCROLL);
        } catch (Exception e2) {
            xi.a("runtime error", e2);
        }
    }

    public void a(String str, double d, double d2, double d3, double d4, double d5, double d6, Object... objArr) {
        if (this.c != null) {
            HashMap map = new HashMap();
            map.put("state", str);
            double dA = this.h.a().a(d, new Object[0]);
            double dA2 = this.h.a().a(d2, new Object[0]);
            map.put(Constants.Name.X, Double.valueOf(dA));
            map.put(Constants.Name.Y, Double.valueOf(dA2));
            double dA3 = this.h.a().a(d3, new Object[0]);
            double dA4 = this.h.a().a(d4, new Object[0]);
            map.put("dx", Double.valueOf(dA3));
            map.put(Constants.Name.DISTANCE_Y, Double.valueOf(dA4));
            double dA5 = this.h.a().a(d5, new Object[0]);
            double dA6 = this.h.a().a(d6, new Object[0]);
            map.put("tdx", Double.valueOf(dA5));
            map.put("tdy", Double.valueOf(dA6));
            map.put("token", this.g);
            if (objArr != null && objArr.length > 0 && (objArr[0] instanceof Map)) {
                map.putAll((Map) objArr[0]);
            }
            this.c.a(map);
            xi.a(">>>>>>>>>>>fire event:(" + str + "," + dA + "," + dA2 + "," + dA3 + "," + dA4 + "," + dA5 + "," + dA6 + ")");
        }
    }
}
