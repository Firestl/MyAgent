package supwisdom;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.view.gesture.WXGesture;
import io.dcloud.feature.weex_amap.adapter.Constant;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import supwisdom.nj;
import supwisdom.si;

/* JADX INFO: compiled from: BindingXOrientationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class dj extends aj implements nj.a {
    public a A;
    public boolean l;
    public double m;
    public double n;
    public double o;
    public double p;
    public double q;
    public double r;
    public nj s;
    public oj t;
    public oj u;
    public oj v;
    public String w;
    public LinkedList<Double> x;
    public uj y;
    public uj z;

    /* JADX INFO: compiled from: BindingXOrientationHandler.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public double f7348a;
        public double b;
        public double c;

        public a(double d, double d2, double d3) {
            this.f7348a = d;
            this.b = d2;
            this.c = d3;
        }
    }

    public dj(Context context, yi yiVar, Object... objArr) {
        super(context, yiVar, objArr);
        this.l = false;
        this.x = new LinkedList<>();
        this.y = new uj(0.0d, 0.0d, 1.0d);
        this.z = new uj(0.0d, 1.0d, 1.0d);
        this.A = new a(0.0d, 0.0d, 0.0d);
        if (context != null) {
            this.s = nj.a(context);
        }
    }

    @Override // supwisdom.aj, supwisdom.vi
    public void a(String str, Map<String, Object> map, jj jjVar, List<Map<String, Object>> list, si.d dVar) {
        String lowerCase;
        super.a(str, map, jjVar, list, dVar);
        if (map != null) {
            String str2 = (String) map.get("sceneType");
            lowerCase = TextUtils.isEmpty(str2) ? "2d" : str2.toLowerCase();
        } else {
            lowerCase = null;
        }
        if (TextUtils.isEmpty(lowerCase) || (!"2d".equals(lowerCase) && !"3d".equals(lowerCase))) {
            lowerCase = "2d";
        }
        this.w = lowerCase;
        xi.a("[ExpressionOrientationHandler] sceneType is " + lowerCase);
        if ("2d".equals(lowerCase)) {
            this.t = new oj(null, Double.valueOf(90.0d), null);
            this.u = new oj(Double.valueOf(0.0d), null, Double.valueOf(90.0d));
        } else if ("3d".equals(lowerCase)) {
            this.v = new oj(null, null, null);
        }
    }

    @Override // supwisdom.vi
    public boolean b(String str, String str2) {
        nj njVar = this.s;
        if (njVar == null) {
            return false;
        }
        njVar.a(this);
        return this.s.b(1);
    }

    @Override // supwisdom.vi
    public void c(String str, String str2) {
    }

    public final boolean c(double d, double d2, double d3) {
        if (this.v != null) {
            this.x.add(Double.valueOf(d));
            if (this.x.size() > 5) {
                this.x.removeFirst();
            }
            a(this.x, 360);
            LinkedList<Double> linkedList = this.x;
            pj pjVarA = this.v.a(d, d2, d3, (linkedList.get(linkedList.size() - 1).doubleValue() - this.m) % 360.0d);
            if (Double.isNaN(pjVarA.f8797a) || Double.isNaN(pjVarA.b) || Double.isNaN(pjVarA.c) || Double.isInfinite(pjVarA.f8797a) || Double.isInfinite(pjVarA.b) || Double.isInfinite(pjVarA.c)) {
                return false;
            }
            a aVar = this.A;
            aVar.f7348a = pjVarA.f8797a;
            aVar.b = pjVarA.b;
            aVar.c = pjVarA.c;
        }
        return true;
    }

    @Override // supwisdom.vi
    public void onActivityPause() {
        nj njVar = this.s;
        if (njVar != null) {
            njVar.e();
        }
    }

    @Override // supwisdom.vi
    public void onActivityResume() {
        nj njVar = this.s;
        if (njVar != null) {
            njVar.b(1);
        }
    }

    @Override // supwisdom.aj, supwisdom.vi
    public void onDestroy() {
        super.onDestroy();
        nj njVar = this.s;
        if (njVar != null) {
            njVar.b(this);
            this.s.e();
        }
        if (this.f6927a != null) {
            this.f6927a.clear();
            this.f6927a = null;
        }
    }

    public final boolean b(double d, double d2, double d3) {
        if (this.t != null && this.u != null) {
            this.x.add(Double.valueOf(d));
            if (this.x.size() > 5) {
                this.x.removeFirst();
            }
            a(this.x, 360);
            LinkedList<Double> linkedList = this.x;
            double dDoubleValue = (linkedList.get(linkedList.size() - 1).doubleValue() - this.m) % 360.0d;
            pj pjVarA = this.t.a(d, d2, d3, dDoubleValue);
            pj pjVarA2 = this.u.a(d, d2, d3, dDoubleValue);
            this.y.a(0.0d, 0.0d, 1.0d);
            this.y.a(pjVarA);
            this.z.a(0.0d, 1.0d, 1.0d);
            this.z.a(pjVarA2);
            double degrees = Math.toDegrees(Math.acos(this.y.f9416a)) - 90.0d;
            double degrees2 = Math.toDegrees(Math.acos(this.z.b)) - 90.0d;
            if (Double.isNaN(degrees) || Double.isNaN(degrees2) || Double.isInfinite(degrees) || Double.isInfinite(degrees2)) {
                return false;
            }
            double dRound = Math.round(degrees);
            double dRound2 = Math.round(degrees2);
            a aVar = this.A;
            aVar.f7348a = dRound;
            aVar.b = dRound2;
        }
        return true;
    }

    @Override // supwisdom.vi
    public boolean a(String str, String str2) {
        c();
        if (this.s == null) {
            return false;
        }
        a(WXGesture.END, this.p, this.q, this.r, new Object[0]);
        return this.s.b(this);
    }

    @Override // supwisdom.aj
    public void c(Map<String, Object> map) {
        a("exit", ((Double) map.get(Constant.JSONKEY.ALPHE)).doubleValue(), ((Double) map.get("beta")).doubleValue(), ((Double) map.get("gamma")).doubleValue(), new Object[0]);
    }

    @Override // supwisdom.nj.a
    public void a(double d, double d2, double d3) {
        double d4;
        char c;
        boolean zC;
        double dRound = Math.round(d);
        double dRound2 = Math.round(d2);
        double dRound3 = Math.round(d3);
        if (dRound == this.p && dRound2 == this.q && dRound3 == this.r) {
            return;
        }
        if (this.l) {
            d4 = dRound3;
            c = 0;
        } else {
            this.l = true;
            c = 0;
            a("start", dRound, dRound2, dRound3, new Object[0]);
            this.m = dRound;
            this.n = dRound2;
            d4 = dRound3;
            this.o = d4;
        }
        if ("2d".equals(this.w)) {
            zC = b(dRound, dRound2, d4);
        } else {
            zC = "3d".equals(this.w) ? c(dRound, dRound2, d4) : false;
        }
        if (zC) {
            a aVar = this.A;
            double d5 = aVar.f7348a;
            double d6 = aVar.b;
            double d7 = aVar.c;
            this.p = dRound;
            this.q = dRound2;
            this.r = d4;
            try {
                if (xi.f9767a) {
                    Locale locale = Locale.getDefault();
                    Object[] objArr = new Object[6];
                    objArr[c] = Double.valueOf(dRound);
                    objArr[1] = Double.valueOf(dRound2);
                    objArr[2] = Double.valueOf(d4);
                    objArr[3] = Double.valueOf(d5);
                    objArr[4] = Double.valueOf(d6);
                    objArr[5] = Double.valueOf(d7);
                    xi.a(String.format(locale, "[OrientationHandler] orientation changed. (alpha:%f,beta:%f,gamma:%f,x:%f,y:%f,z:%f)", objArr));
                }
                lj.a(this.d, dRound, dRound2, d4, this.m, this.n, this.o, d5, d6, d7);
                if (a(this.i, this.d)) {
                    return;
                }
                a(this.f6927a, this.d, Constants.Name.ORIENTATION);
            } catch (Exception e2) {
                xi.a("runtime error", e2);
            }
        }
    }

    public final void a(List<Double> list, int i) {
        int size = list.size();
        if (size > 1) {
            for (int i2 = 1; i2 < size; i2++) {
                int i3 = i2 - 1;
                if (list.get(i3) != null && list.get(i2) != null) {
                    if (list.get(i2).doubleValue() - list.get(i3).doubleValue() < (-i) / 2) {
                        double d = i;
                        list.set(i2, Double.valueOf(list.get(i2).doubleValue() + ((Math.floor(list.get(i3).doubleValue() / d) + 1.0d) * d)));
                    }
                    if (list.get(i2).doubleValue() - list.get(i3).doubleValue() > i / 2) {
                        list.set(i2, Double.valueOf(list.get(i2).doubleValue() - ((double) i)));
                    }
                }
            }
        }
    }

    @Override // supwisdom.aj
    public void a(String str, Map<String, Object> map) {
        a("interceptor", ((Double) map.get(Constant.JSONKEY.ALPHE)).doubleValue(), ((Double) map.get("beta")).doubleValue(), ((Double) map.get("gamma")).doubleValue(), Collections.singletonMap("interceptor", str));
    }

    public final void a(String str, double d, double d2, double d3, Object... objArr) {
        if (this.c != null) {
            HashMap map = new HashMap();
            map.put("state", str);
            map.put(Constant.JSONKEY.ALPHE, Double.valueOf(d));
            map.put("beta", Double.valueOf(d2));
            map.put("gamma", Double.valueOf(d3));
            map.put("token", this.g);
            if (objArr != null && objArr.length > 0 && (objArr[0] instanceof Map)) {
                map.putAll((Map) objArr[0]);
            }
            this.c.a(map);
            xi.a(">>>>>>>>>>>fire event:(" + str + "," + d + "," + d2 + "," + d3 + ")");
        }
    }
}
