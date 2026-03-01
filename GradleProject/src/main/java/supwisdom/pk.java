package supwisdom;

import com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator;
import java.util.ArrayList;
import java.util.List;
import supwisdom.yk;

/* JADX INFO: compiled from: AxisYDataSet.java */
/* JADX INFO: loaded from: classes.dex */
public class pk<T extends yk> {
    public List<Double> c;
    public ml x;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AxisDecorator.YAxisLocation f8800a = AxisDecorator.YAxisLocation.LEFT;
    public AxisDecorator.XAxisLocation b = AxisDecorator.XAxisLocation.BOTTOM;
    public List<yk<Double>> d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8801e = -16776961;
    public boolean f = true;
    public Double g = null;
    public Double h = null;
    public String i = "";
    public boolean j = false;
    public boolean k = false;
    public boolean l = false;
    public boolean m = false;
    public int n = 8;
    public int o = 16;
    public int p = -1;
    public boolean q = false;
    public int r = tn.a(15.0f);
    public int s = -16777216;
    public float t = 20.0f;
    public int u = -2;
    public int v = 24;
    public int w = -65536;

    public pk(List<Double> list) {
        this.c = new ArrayList();
        this.c = list;
        j();
    }

    public AxisDecorator.XAxisLocation a() {
        return this.b;
    }

    public AxisDecorator.YAxisLocation b() {
        return this.f8800a;
    }

    public ml c() {
        return this.x;
    }

    public int d() {
        return this.f8801e;
    }

    public double e() {
        return this.h.doubleValue();
    }

    public double f() {
        return this.g.doubleValue();
    }

    public List<Double> g() {
        return this.c;
    }

    public String h() {
        return this.i;
    }

    public List<yk<Double>> i() {
        return this.d;
    }

    public void j() {
        for (int i = 0; i < this.c.size(); i++) {
            yk<Double> ykVar = new yk<>(i, this.c.get(i));
            if (this.c.get(i) == null) {
                this.d.add(ykVar);
            } else {
                Double d = this.g;
                if (d == null || this.h == null) {
                    this.g = this.c.get(i);
                    this.h = this.c.get(i);
                    this.d.add(ykVar);
                } else {
                    if (d != null && d.doubleValue() > this.c.get(i).doubleValue()) {
                        this.g = this.c.get(i);
                    }
                    Double d2 = this.h;
                    if (d2 != null && d2.doubleValue() < this.c.get(i).doubleValue()) {
                        this.h = this.c.get(i);
                    }
                    this.d.add(ykVar);
                }
            }
        }
        if (this.g == null || this.h == null) {
            Double dValueOf = Double.valueOf(0.0d);
            this.g = dValueOf;
            this.h = dValueOf;
        }
    }

    public boolean k() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = z;
    }
}
