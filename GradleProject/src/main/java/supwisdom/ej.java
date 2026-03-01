package supwisdom;

import android.content.Context;
import android.view.animation.AnimationUtils;
import com.taobao.weex.ui.view.gesture.WXGesture;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import supwisdom.cj;
import supwisdom.si;

/* JADX INFO: compiled from: BindingXTimingHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class ej extends aj implements cj.a {
    public long l;
    public cj m;
    public boolean n;

    public ej(Context context, yi yiVar, Object... objArr) {
        super(context, yiVar, objArr);
        this.l = 0L;
        this.n = false;
        cj cjVar = this.m;
        if (cjVar == null) {
            this.m = cj.c();
        } else {
            cjVar.a();
        }
    }

    @Override // supwisdom.aj, supwisdom.vi
    public void a(String str, Map<String, Object> map, jj jjVar, List<Map<String, Object>> list, si.d dVar) {
        super.a(str, map, jjVar, list, dVar);
        if (this.m == null) {
            this.m = cj.c();
        }
        a("start", 0L, new Object[0]);
        this.m.a();
        this.m.a(this);
    }

    @Override // supwisdom.vi
    public boolean b(String str, String str2) {
        return true;
    }

    @Override // supwisdom.vi
    public void c(String str, String str2) {
    }

    @Override // supwisdom.aj
    public void c(Map<String, Object> map) {
        a("exit", (long) ((Double) map.get("t")).doubleValue(), new Object[0]);
        cj cjVar = this.m;
        if (cjVar != null) {
            cjVar.a();
        }
        this.l = 0L;
    }

    public final void d() {
        long jCurrentAnimationTimeMillis = 0;
        if (this.l == 0) {
            this.l = AnimationUtils.currentAnimationTimeMillis();
            this.n = false;
        } else {
            jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis() - this.l;
        }
        try {
            if (xi.f9767a) {
                xi.a(String.format(Locale.getDefault(), "[TimingHandler] timing elapsed. (t:%d)", Long.valueOf(jCurrentAnimationTimeMillis)));
            }
            lj.a(this.d, jCurrentAnimationTimeMillis);
            if (!this.n) {
                a(this.f6927a, this.d, "timing");
            }
            this.n = a(this.i, this.d);
        } catch (Exception e2) {
            xi.a("runtime error", e2);
        }
    }

    @Override // supwisdom.vi
    public void onActivityPause() {
    }

    @Override // supwisdom.vi
    public void onActivityResume() {
    }

    @Override // supwisdom.aj, supwisdom.vi
    public void onDestroy() {
        super.onDestroy();
        c();
        cj cjVar = this.m;
        if (cjVar != null) {
            cjVar.b();
            this.m = null;
        }
        this.l = 0L;
    }

    @Override // supwisdom.vi
    public boolean a(String str, String str2) {
        a(WXGesture.END, System.currentTimeMillis() - this.l, new Object[0]);
        c();
        cj cjVar = this.m;
        if (cjVar != null) {
            cjVar.a();
        }
        this.l = 0L;
        return true;
    }

    @Override // supwisdom.aj
    public void a(String str, Map<String, Object> map) {
        a("interceptor", (long) ((Double) map.get("t")).doubleValue(), Collections.singletonMap("interceptor", str));
    }

    public final void a(String str, long j, Object... objArr) {
        if (this.c != null) {
            HashMap map = new HashMap();
            map.put("state", str);
            map.put("t", Long.valueOf(j));
            map.put("token", this.g);
            if (objArr != null && objArr.length > 0 && (objArr[0] instanceof Map)) {
                map.putAll((Map) objArr[0]);
            }
            this.c.a(map);
            xi.a(">>>>>>>>>>>fire event:(" + str + "," + j + ")");
        }
    }

    @Override // supwisdom.cj.a
    public void a() {
        d();
    }
}
