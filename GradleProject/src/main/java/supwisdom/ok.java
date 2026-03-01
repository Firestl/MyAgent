package supwisdom;

import com.alibaba.dt.AChartsLib.decorators.blockDeocators.AxisDecorator;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AxisXDataSet.java */
/* JADX INFO: loaded from: classes.dex */
public class ok<T> {
    public List<T> b;
    public double d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AxisDecorator.XAxisLocation f8682a = AxisDecorator.XAxisLocation.BOTTOM;
    public List<yk<T>> c = new ArrayList();

    public ok(List list) {
        this.b = new ArrayList();
        this.b = list;
        e();
    }

    public AxisDecorator.XAxisLocation a() {
        return this.f8682a;
    }

    public double b() {
        return this.d;
    }

    public List<T> c() {
        return this.b;
    }

    public List<yk<T>> d() {
        return this.c;
    }

    public void e() {
        for (int i = 0; i < this.b.size(); i++) {
            T t = this.b.get(i);
            this.c.add(new yk<>(i, this.b.get(i)));
            if (t instanceof String) {
                this.b.size();
                this.d = 0.0d;
            }
        }
    }
}
