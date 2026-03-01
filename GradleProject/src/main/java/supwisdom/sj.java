package supwisdom;

import android.view.animation.Interpolator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: compiled from: TimingFunctions.java */
/* JADX INFO: loaded from: classes.dex */
public class sj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Object f9178a = new k();
    public static Object b = new v();
    public static final h0<g0> c = new h0<>(4);
    public static Object d = new z();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Object f9179e = new a0();
    public static Object f = new b0();
    public static Object g = new c0();
    public static Object h = new d0();
    public static Object i = new e0();
    public static Object j = new f0();
    public static Object k = new a();
    public static Object l = new b();
    public static Object m = new c();
    public static Object n = new d();
    public static Object o = new e();
    public static Object p = new f();
    public static Object q = new g();
    public static Object r = new h();
    public static Object s = new i();
    public static Object t = new j();
    public static Object u = new l();
    public static Object v = new m();
    public static Object w = new n();
    public static Object x = new o();
    public static Object y = new p();
    public static Object z = new q();
    public static Object A = new r();
    public static Object B = new s();
    public static Object C = new t();
    public static Object D = new u();
    public static Object E = new w();
    public static Object F = new x();
    public static Object G = new y();

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class a implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = (Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4) - 1.0d;
            return Double.valueOf(((-dDoubleValue3) * ((((dMin * dMin) * dMin) * dMin) - 1.0d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class a0 implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4;
            return Double.valueOf(((-dDoubleValue3) * dMin * (dMin - 2.0d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class b implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4) / (dDoubleValue4 / 2.0d);
            if (dMin < 1.0d) {
                return Double.valueOf(((dDoubleValue3 / 2.0d) * dMin * dMin * dMin * dMin) + dDoubleValue2);
            }
            double d = dMin - 2.0d;
            return Double.valueOf((((-dDoubleValue3) / 2.0d) * ((((d * d) * d) * d) - 2.0d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class b0 implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4) / (dDoubleValue4 / 2.0d);
            if (dMin < 1.0d) {
                return Double.valueOf(((dDoubleValue3 / 2.0d) * dMin * dMin) + dDoubleValue2);
            }
            double d = dMin - 1.0d;
            return Double.valueOf((((-dDoubleValue3) / 2.0d) * ((d * (d - 2.0d)) - 1.0d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class c implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4;
            return Double.valueOf((dDoubleValue3 * dMin * dMin * dMin * dMin * dMin) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class c0 implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4;
            return Double.valueOf((dDoubleValue3 * dMin * dMin * dMin) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class d implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = (Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4) - 1.0d;
            return Double.valueOf((dDoubleValue3 * ((dMin * dMin * dMin * dMin * dMin) + 1.0d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class d0 implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = (Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4) - 1.0d;
            return Double.valueOf((dDoubleValue3 * ((dMin * dMin * dMin) + 1.0d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class e implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4) / (dDoubleValue4 / 2.0d);
            if (dMin < 1.0d) {
                return Double.valueOf(((dDoubleValue3 / 2.0d) * dMin * dMin * dMin * dMin * dMin) + dDoubleValue2);
            }
            double d = dMin - 2.0d;
            return Double.valueOf(((dDoubleValue3 / 2.0d) * ((d * d * d * d * d) + 2.0d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class e0 implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4) / (dDoubleValue4 / 2.0d);
            if (dMin < 1.0d) {
                return Double.valueOf(((dDoubleValue3 / 2.0d) * dMin * dMin * dMin) + dDoubleValue2);
            }
            double d = dMin - 2.0d;
            return Double.valueOf(((dDoubleValue3 / 2.0d) * ((d * d * d) + 2.0d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class f implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            return Double.valueOf(((-dDoubleValue3) * Math.cos((Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4) * 1.5707963267948966d)) + dDoubleValue3 + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class f0 implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4;
            return Double.valueOf((dDoubleValue3 * dMin * dMin * dMin * dMin) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class g implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            return Double.valueOf((dDoubleValue3 * Math.sin((Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4) * 1.5707963267948966d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class g0 implements Interpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f9180a;
        public float b;
        public float c;
        public float d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Interpolator f9181e;

        public g0(float f, float f2, float f3, float f4) {
            this.f9180a = f;
            this.b = f2;
            this.c = f3;
            this.d = f4;
            this.f9181e = cc.a(f, f2, f3, f4);
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return this.f9181e.getInterpolation(f);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class h implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            return Double.valueOf((((-dDoubleValue3) / 2.0d) * (Math.cos((Math.min(dDoubleValue, dDoubleValue4) * 3.141592653589793d) / dDoubleValue4) - 1.0d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class i implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4);
            if (dMin != 0.0d) {
                dDoubleValue2 += dDoubleValue3 * Math.pow(2.0d, ((dMin / dDoubleValue4) - 1.0d) * 10.0d);
            }
            return Double.valueOf(dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class j implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4);
            return Double.valueOf(dMin == dDoubleValue4 ? dDoubleValue2 + dDoubleValue3 : dDoubleValue2 + (dDoubleValue3 * ((-Math.pow(2.0d, (dMin * (-10.0d)) / dDoubleValue4)) + 1.0d)));
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class k implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            return Double.valueOf((dDoubleValue3 * (Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class l implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4);
            if (dMin == 0.0d) {
                return Double.valueOf(dDoubleValue2);
            }
            if (dMin == dDoubleValue4) {
                return Double.valueOf(dDoubleValue2 + dDoubleValue3);
            }
            double d = dMin / (dDoubleValue4 / 2.0d);
            return d < 1.0d ? Double.valueOf(((dDoubleValue3 / 2.0d) * Math.pow(2.0d, (d - 1.0d) * 10.0d)) + dDoubleValue2) : Double.valueOf(((dDoubleValue3 / 2.0d) * ((-Math.pow(2.0d, (d - 1.0d) * (-10.0d))) + 2.0d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class m implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4;
            return Double.valueOf(((-dDoubleValue3) * (Math.sqrt(1.0d - (dMin * dMin)) - 1.0d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class n implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = (Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4) - 1.0d;
            return Double.valueOf((dDoubleValue3 * Math.sqrt(1.0d - (dMin * dMin))) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class o implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4) / (dDoubleValue4 / 2.0d);
            if (dMin < 1.0d) {
                return Double.valueOf((((-dDoubleValue3) / 2.0d) * (Math.sqrt(1.0d - (dMin * dMin)) - 1.0d)) + dDoubleValue2);
            }
            double d = dMin - 2.0d;
            return Double.valueOf(((dDoubleValue3 / 2.0d) * (Math.sqrt(1.0d - (d * d)) + 1.0d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class p implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4);
            if (dMin == 0.0d) {
                return Double.valueOf(dDoubleValue2);
            }
            double d = dMin / dDoubleValue4;
            if (d == 1.0d) {
                return Double.valueOf(dDoubleValue2 + dDoubleValue3);
            }
            double d2 = 0.3d * dDoubleValue4;
            double d3 = d - 1.0d;
            return Double.valueOf((-(dDoubleValue3 * Math.pow(2.0d, d3 * 10.0d) * Math.sin((((d3 * dDoubleValue4) - (dDoubleValue3 < Math.abs(dDoubleValue3) ? d2 / 4.0d : (d2 / 6.283185307179586d) * Math.asin(dDoubleValue3 / dDoubleValue3))) * 6.283185307179586d) / d2))) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class q implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4);
            if (dMin == 0.0d) {
                return Double.valueOf(dDoubleValue2);
            }
            double d = dMin / dDoubleValue4;
            if (d == 1.0d) {
                return Double.valueOf(dDoubleValue2 + dDoubleValue3);
            }
            double d2 = 0.3d * dDoubleValue4;
            return Double.valueOf((Math.pow(2.0d, d * (-10.0d)) * dDoubleValue3 * Math.sin((((d * dDoubleValue4) - (dDoubleValue3 < Math.abs(dDoubleValue3) ? d2 / 4.0d : (d2 / 6.283185307179586d) * Math.asin(dDoubleValue3 / dDoubleValue3))) * 6.283185307179586d) / d2)) + dDoubleValue3 + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class r implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4);
            if (dMin == 0.0d) {
                return Double.valueOf(dDoubleValue2);
            }
            double d = dMin / (dDoubleValue4 / 2.0d);
            if (d == 2.0d) {
                return Double.valueOf(dDoubleValue2 + dDoubleValue3);
            }
            double d2 = 0.44999999999999996d * dDoubleValue4;
            double dAsin = dDoubleValue3 < Math.abs(dDoubleValue3) ? d2 / 4.0d : (d2 / 6.283185307179586d) * Math.asin(dDoubleValue3 / dDoubleValue3);
            if (d < 1.0d) {
                double d3 = d - 1.0d;
                return Double.valueOf((dDoubleValue3 * Math.pow(2.0d, d3 * 10.0d) * Math.sin((((d3 * dDoubleValue4) - dAsin) * 6.283185307179586d) / d2) * (-0.5d)) + dDoubleValue2);
            }
            double d4 = d - 1.0d;
            return Double.valueOf((Math.pow(2.0d, (-10.0d) * d4) * dDoubleValue3 * Math.sin((((d4 * dDoubleValue4) - dAsin) * 6.283185307179586d) / d2) * 0.5d) + dDoubleValue3 + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class s implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4;
            return Double.valueOf((dDoubleValue3 * dMin * dMin * ((2.70158d * dMin) - 1.70158d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class t implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = (Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4) - 1.0d;
            return Double.valueOf((dDoubleValue3 * ((dMin * dMin * ((2.70158d * dMin) + 1.70158d)) + 1.0d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class u implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4) / (dDoubleValue4 / 2.0d);
            if (dMin < 1.0d) {
                return Double.valueOf(((dDoubleValue3 / 2.0d) * dMin * dMin * ((3.5949095d * dMin) - 2.5949095d)) + dDoubleValue2);
            }
            double d = dMin - 2.0d;
            return Double.valueOf(((dDoubleValue3 / 2.0d) * ((d * d * ((3.5949095d * d) + 2.5949095d)) + 2.0d)) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class v implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dDoubleValue5 = ((Double) arrayList.get(4)).doubleValue();
            double dDoubleValue6 = ((Double) arrayList.get(5)).doubleValue();
            double dDoubleValue7 = ((Double) arrayList.get(6)).doubleValue();
            double dDoubleValue8 = ((Double) arrayList.get(7)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4);
            if (dMin == dDoubleValue4) {
                return Double.valueOf(dDoubleValue2 + dDoubleValue3);
            }
            float f = (float) dDoubleValue5;
            float f2 = (float) dDoubleValue6;
            float f3 = (float) dDoubleValue7;
            float f4 = (float) dDoubleValue8;
            g0 g0VarB = sj.b(f, f2, f3, f4);
            if (g0VarB == null) {
                g0VarB = new g0(f, f2, f3, f4);
                sj.c.a(g0VarB);
            }
            return Double.valueOf((dDoubleValue3 * ((double) g0VarB.getInterpolation((float) (dMin / dDoubleValue4)))) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class w implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            return Double.valueOf(sj.c(Math.min(dDoubleValue, dDoubleValue4), dDoubleValue2, dDoubleValue3, dDoubleValue4));
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class x implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            return Double.valueOf(sj.d(Math.min(dDoubleValue, dDoubleValue4), dDoubleValue2, dDoubleValue3, dDoubleValue4));
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class y implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4);
            return dMin < dDoubleValue4 / 2.0d ? Double.valueOf((sj.c(dMin * 2.0d, 0.0d, dDoubleValue3, dDoubleValue4) * 0.5d) + dDoubleValue2) : Double.valueOf((sj.d((dMin * 2.0d) - dDoubleValue4, 0.0d, dDoubleValue3, dDoubleValue4) * 0.5d) + (dDoubleValue3 * 0.5d) + dDoubleValue2);
        }
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class z implements kj {
        @Override // supwisdom.kj
        public Object a(ArrayList<Object> arrayList) throws JSONException, NumberFormatException {
            double dDoubleValue = ((Double) arrayList.get(0)).doubleValue();
            double dDoubleValue2 = ((Double) arrayList.get(1)).doubleValue();
            double dDoubleValue3 = ((Double) arrayList.get(2)).doubleValue();
            double dDoubleValue4 = ((Double) arrayList.get(3)).doubleValue();
            double dMin = Math.min(dDoubleValue, dDoubleValue4) / dDoubleValue4;
            return Double.valueOf((dDoubleValue3 * dMin * dMin) + dDoubleValue2);
        }
    }

    public static double c(double d2, double d3, double d4, double d5) {
        return (d4 - d(d5 - d2, 0.0d, d4, d5)) + d3;
    }

    public static double d(double d2, double d3, double d4, double d5) {
        double d6;
        double d7;
        double d8;
        double d9 = d2 / d5;
        if (d9 < 0.36363636363636365d) {
            d8 = 7.5625d * d9 * d9;
        } else {
            if (d9 < 0.7272727272727273d) {
                double d10 = d9 - 0.5454545454545454d;
                d6 = 7.5625d * d10 * d10;
                d7 = 0.75d;
            } else if (d9 < 0.9090909090909091d) {
                double d11 = d9 - 0.8181818181818182d;
                d6 = 7.5625d * d11 * d11;
                d7 = 0.9375d;
            } else {
                double d12 = d9 - 0.9545454545454546d;
                d6 = 7.5625d * d12 * d12;
                d7 = 0.984375d;
            }
            d8 = d6 + d7;
        }
        return (d4 * d8) + d3;
    }

    public static g0 b(float f2, float f3, float f4, float f5) {
        for (g0 g0Var : c.a()) {
            if (Float.compare(g0Var.f9180a, f2) == 0 && Float.compare(g0Var.c, f4) == 0 && Float.compare(g0Var.b, f3) == 0 && Float.compare(g0Var.d, f5) == 0) {
                return g0Var;
            }
        }
        return null;
    }

    /* JADX INFO: compiled from: TimingFunctions.java */
    public static class h0<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ArrayDeque<T> f9182a;

        public h0(int i) {
            this.f9182a = new ArrayDeque<>(i);
        }

        public void a(T t) {
            if (this.f9182a.size() < 4) {
                this.f9182a.addLast(t);
            } else {
                this.f9182a.removeFirst();
                this.f9182a.addLast(t);
            }
        }

        public Deque<T> a() {
            return this.f9182a;
        }
    }

    public static void a(Map<String, Object> map) {
        map.put("linear", f9178a);
        map.put("easeInQuad", d);
        map.put("easeOutQuad", f9179e);
        map.put("easeInOutQuad", f);
        map.put("easeInCubic", g);
        map.put("easeOutCubic", h);
        map.put("easeInOutCubic", i);
        map.put("easeInQuart", j);
        map.put("easeOutQuart", k);
        map.put("easeInOutQuart", l);
        map.put("easeInQuint", m);
        map.put("easeOutQuint", n);
        map.put("easeInOutQuint", o);
        map.put("easeInSine", p);
        map.put("easeOutSine", q);
        map.put("easeInOutSine", r);
        map.put("easeInExpo", s);
        map.put("easeOutExpo", t);
        map.put("easeInOutExpo", u);
        map.put("easeInCirc", v);
        map.put("easeOutCirc", w);
        map.put("easeInOutCirc", x);
        map.put("easeInElastic", y);
        map.put("easeOutElastic", z);
        map.put("easeInOutElastic", A);
        map.put("easeInBack", B);
        map.put("easeOutBack", C);
        map.put("easeInOutBack", D);
        map.put("easeInBounce", E);
        map.put("easeOutBounce", F);
        map.put("easeInOutBounce", G);
        map.put("cubicBezier", b);
    }
}
