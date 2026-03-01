package supwisdom;

import java.util.ArrayList;
import java.util.List;
import supwisdom.pk;

/* JADX INFO: compiled from: ChartData.java */
/* JADX INFO: loaded from: classes.dex */
public class ek<T, K extends pk> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<T> f7507a = new ArrayList();
    public List<T> b = new ArrayList();
    public List<K> c = new ArrayList();
    public List<K> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f7508e;
    public double f;
    public double g;
    public double h;

    public double a() {
        double d = this.f;
        double d2 = this.g;
        if (d == d2) {
            return 0.5d;
        }
        return d2;
    }

    public double b() {
        Double dValueOf = null;
        for (int i = 0; i < this.f7507a.size(); i++) {
            for (int i2 = 0; i2 < ((ok) this.f7507a.get(i)).d().size(); i2++) {
                double dDoubleValue = 0.0d;
                for (K k : this.d) {
                    if (i2 >= k.i().size()) {
                        break;
                    }
                    dDoubleValue = k.i().get(i2).b() == null ? dDoubleValue + 0.0d : dDoubleValue + k.i().get(i2).b().doubleValue();
                }
                if (dValueOf == null) {
                    dValueOf = Double.valueOf(dDoubleValue);
                } else if (dValueOf.doubleValue() < dDoubleValue) {
                    dValueOf = Double.valueOf(dDoubleValue);
                }
            }
        }
        return dValueOf.doubleValue();
    }

    public double c() {
        Double dValueOf = null;
        for (K k : this.d) {
            if (dValueOf == null) {
                dValueOf = Double.valueOf(k.e());
            } else if (dValueOf.doubleValue() < k.e()) {
                dValueOf = Double.valueOf(k.e());
            }
        }
        dValueOf.doubleValue();
        return dValueOf.doubleValue();
    }

    public double d() {
        double d = this.f;
        if (d == this.g) {
            return -0.5d;
        }
        return d;
    }

    public double e() {
        Double dValueOf = null;
        for (K k : this.d) {
            if (dValueOf == null) {
                dValueOf = Double.valueOf(k.f());
            } else if (dValueOf.doubleValue() > k.f()) {
                dValueOf = Double.valueOf(k.f());
            }
        }
        double dDoubleValue = dValueOf.doubleValue();
        this.f7508e = dDoubleValue;
        if (dDoubleValue > 0.0d) {
            return 0.0d;
        }
        return dValueOf.doubleValue();
    }

    public List<T> f() {
        return this.b;
    }

    public List<K> g() {
        return this.d;
    }

    public List<K> h() {
        return this.c;
    }

    public boolean i() {
        List<K> list;
        return this.f7507a.size() == 0 || (list = this.d) == null || list.size() == 0;
    }
}
