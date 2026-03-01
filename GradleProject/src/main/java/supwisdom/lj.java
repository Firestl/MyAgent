package supwisdom;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import com.taobao.weex.utils.WXResourceUtils;
import com.tencent.qphone.base.util.QLog;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSUtil;
import io.dcloud.feature.payment.weixin.WeiXinPay;
import io.dcloud.feature.weex_amap.adapter.Constant;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONException;
import supwisdom.yi;

/* JADX INFO: compiled from: JSMath.java */
/* JADX INFO: loaded from: classes.dex */
public class lj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Object f8294a = new k();
    public static Object b = new s();
    public static Object c = new t();
    public static Object d = new u();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Object f8295e = new v();
    public static Object f = new w();
    public static Object g = new x();
    public static Object h = new y();
    public static Object i = new z();
    public static Object j = new a();
    public static Object k = new b();
    public static Object l = new c();
    public static Object m = new d();
    public static Object n = new e();
    public static Object o = new f();
    public static Object p = new g();
    public static Object q = new h();
    public static Object r = new i();
    public static Object s = new j();
    public static Object t = Double.valueOf(3.141592653589793d);
    public static Object u = Double.valueOf(2.718281828459045d);
    public static Object v = new l();
    public static Object w = new m();
    public static Object x = new n();
    public static Object y = new o();
    public static Object z = new p();
    public static ArgbEvaluator A = new ArgbEvaluator();
    public static Object B = new q();
    public static Object C = new r();

    /* JADX INFO: compiled from: JSMath.java */
    public static class a implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.sqrt(((Double) arrayList.get(0)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class b implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.cbrt(((Double) arrayList.get(0)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class c implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.log(((Double) arrayList.get(0)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class d implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.abs(((Double) arrayList.get(0)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class e implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            if (dDoubleValue > 0.0d) {
                return 1;
            }
            if (dDoubleValue == 0.0d) {
                return 0;
            }
            if (dDoubleValue < 0.0d) {
                return -1;
            }
            return Double.valueOf(Double.NaN);
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class f implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.ceil(((Double) arrayList.get(0)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class g implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.floor(((Double) arrayList.get(0)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class h implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Long.valueOf(Math.round(((Double) arrayList.get(0)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class i implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            if (arrayList == null) {
                return null;
            }
            if (arrayList.size() < 1) {
                return null;
            }
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            int size = arrayList.size();
            for (int i = 1; i < size; i++) {
                double dDoubleValue2 = ((Double) arrayList.get(i)).doubleValue();
                if (dDoubleValue2 > dDoubleValue) {
                    dDoubleValue = dDoubleValue2;
                }
            }
            return Double.valueOf(dDoubleValue);
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class j implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            if (arrayList == null) {
                return null;
            }
            if (arrayList.size() < 1) {
                return null;
            }
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            int size = arrayList.size();
            for (int i = 1; i < size; i++) {
                double dDoubleValue2 = ((Double) arrayList.get(i)).doubleValue();
                if (dDoubleValue2 < dDoubleValue) {
                    dDoubleValue = dDoubleValue2;
                }
            }
            return Double.valueOf(dDoubleValue);
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class k implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.sin(((Double) arrayList.get(0)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class l implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            if (arrayList == null || arrayList.size() < 2) {
                return null;
            }
            return arrayList;
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class m implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            if (arrayList == null || arrayList.size() < 2) {
                return null;
            }
            return arrayList;
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class n implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            if (arrayList == null || arrayList.size() < 6) {
                return null;
            }
            return arrayList;
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class o implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            if (arrayList == null || arrayList.size() < 3) {
                return null;
            }
            return Integer.valueOf(Color.rgb((int) ((Double) arrayList.get(0)).doubleValue(), (int) ((Double) arrayList.get(1)).doubleValue(), (int) ((Double) arrayList.get(2)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class p implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            if (arrayList == null || arrayList.size() < 4) {
                return null;
            }
            return Integer.valueOf(Color.argb((int) (((Double) arrayList.get(3)).doubleValue() * 255.0d), (int) ((Double) arrayList.get(0)).doubleValue(), (int) ((Double) arrayList.get(1)).doubleValue(), (int) ((Double) arrayList.get(2)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class q implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            int iB = lj.b((String) arrayList.get(0));
            int iB2 = lj.b((String) arrayList.get(1));
            return lj.A.evaluate((float) Math.min(1.0d, Math.max(0.0d, ((Double) arrayList.get(2)).doubleValue())), Integer.valueOf(iB), Integer.valueOf(iB2));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class r implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            return arrayList;
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class s implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.cos(((Double) arrayList.get(0)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class t implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.tan(((Double) arrayList.get(0)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class u implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.asin(((Double) arrayList.get(0)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class v implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.acos(((Double) arrayList.get(0)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class w implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.atan(((Double) arrayList.get(0)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class x implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.atan2(((Double) arrayList.get(0)).doubleValue(), ((Double) arrayList.get(1)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class y implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.pow(((Double) arrayList.get(0)).doubleValue(), ((Double) arrayList.get(1)).doubleValue()));
        }
    }

    /* JADX INFO: compiled from: JSMath.java */
    public static class z implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            return Double.valueOf(Math.exp(((Double) arrayList.get(0)).doubleValue()));
        }
    }

    public static int b(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Unknown color");
        }
        if (str.startsWith("'") || str.startsWith(JSUtil.QUOTE)) {
            str = str.substring(1, str.length() - 1);
        }
        int color = Color.parseColor(str);
        return Color.argb(255, Color.red(color), Color.green(color), Color.blue(color));
    }

    public static void a(Map<String, Object> map, double d2, double d3, yi.c cVar) {
        map.put(Constants.Name.X, Double.valueOf(cVar.a(d2, new Object[0])));
        map.put(Constants.Name.Y, Double.valueOf(cVar.a(d3, new Object[0])));
        map.put("internal_x", Double.valueOf(d2));
        map.put("internal_y", Double.valueOf(d3));
    }

    public static void a(Map<String, Object> map, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
        map.put(Constant.JSONKEY.ALPHE, Double.valueOf(d2));
        map.put("beta", Double.valueOf(d3));
        map.put("gamma", Double.valueOf(d4));
        map.put("dalpha", Double.valueOf(d2 - d5));
        map.put("dbeta", Double.valueOf(d3 - d6));
        map.put("dgamma", Double.valueOf(d4 - d7));
        map.put(Constants.Name.X, Double.valueOf(d8));
        map.put(Constants.Name.Y, Double.valueOf(d9));
        map.put(com.umeng.analytics.pro.bm.aH, Double.valueOf(d10));
    }

    public static void a(Map<String, Object> map, double d2) {
        map.put("t", Double.valueOf(d2));
    }

    public static void a(Map<String, Object> map, double d2, double d3, double d4, double d5, double d6, double d7, yi.c cVar) {
        map.put(Constants.Name.X, Double.valueOf(cVar.a(d2, new Object[0])));
        map.put(Constants.Name.Y, Double.valueOf(cVar.a(d3, new Object[0])));
        map.put("dx", Double.valueOf(cVar.a(d4, new Object[0])));
        map.put(Constants.Name.DISTANCE_Y, Double.valueOf(cVar.a(d5, new Object[0])));
        map.put("tdx", Double.valueOf(cVar.a(d6, new Object[0])));
        map.put("tdy", Double.valueOf(cVar.a(d7, new Object[0])));
        map.put("internal_x", Double.valueOf(d2));
        map.put("internal_y", Double.valueOf(d3));
    }

    public static void a(Map<String, Object> map) {
        map.put("sin", f8294a);
        map.put("cos", b);
        map.put("tan", c);
        map.put("asin", d);
        map.put("acos", f8295e);
        map.put("atan", f);
        map.put("atan2", g);
        map.put("pow", h);
        map.put(com.umeng.analytics.pro.aw.b, i);
        map.put("sqrt", j);
        map.put("cbrt", k);
        map.put("log", l);
        map.put("abs", m);
        map.put(WeiXinPay.PayInfoResult.KEY_SIGN, n);
        map.put("ceil", o);
        map.put("floor", p);
        map.put(AbsoluteConst.JSON_KEY_ROUND, q);
        map.put("max", r);
        map.put("min", s);
        map.put("PI", t);
        map.put(QLog.TAG_REPORTLEVEL_USER, u);
        map.put("translate", v);
        map.put("scale", w);
        map.put("matrix", x);
        map.put(WXResourceUtils.RGB, y);
        map.put(WXResourceUtils.RGBA, z);
        map.put("evaluateColor", B);
        map.put("asArray", C);
    }
}
