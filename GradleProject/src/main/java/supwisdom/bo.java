package supwisdom;

import com.alibaba.dt.AChartsLib.charts.Chart;
import java.util.HashMap;
import java.util.Stack;

/* JADX INFO: compiled from: ViewportHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class bo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Chart f7083a;
    public float b = 0.0f;
    public float c = 0.0f;
    public float d = 0.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f7084e = 0.0f;
    public Stack<a> f = new Stack<>();
    public final HashMap<String, String> g = new HashMap<>();

    /* JADX INFO: compiled from: ViewportHandler.java */
    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f7085a;
        public float b;
        public float c;
        public float d;

        public a(bo boVar, float f, float f2, float f3, float f4) {
            this.f7085a = f;
            this.b = f2;
            this.c = f3;
            this.d = f4;
        }
    }

    public bo(Chart chart) {
        this.f7083a = chart;
    }

    public bo a(float f) {
        this.f7084e = f;
        return this;
    }

    public bo b(float f) {
        this.b = f;
        return this;
    }

    public float c() {
        return this.b;
    }

    public float d() {
        return this.c;
    }

    public float e() {
        return this.d;
    }

    public void f() {
        a aVarPop = this.f.pop();
        this.b = aVarPop.f7085a;
        this.c = aVarPop.b;
        this.d = aVarPop.c;
        this.f7084e = aVarPop.d;
    }

    public void g() {
        this.f.push(new a(this, this.b, this.d, this.c, this.f7084e));
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(bo.class.getSimpleName());
        stringBuffer.append(" mBlockStartLeft = ");
        stringBuffer.append(this.b);
        stringBuffer.append(" mBlockStartRight = ");
        stringBuffer.append(this.c);
        stringBuffer.append(" mBlockStartTop = ");
        stringBuffer.append(this.d);
        stringBuffer.append(" mBlockStartBottom = ");
        stringBuffer.append(this.f7084e);
        return stringBuffer.toString();
    }

    public void a(String str, String str2) {
        synchronized (this.g) {
            this.g.put(str, str2);
        }
    }

    public float b() {
        return this.f7084e;
    }

    public bo c(float f) {
        this.c = f;
        return this;
    }

    public bo d(float f) {
        this.d = f;
        return this;
    }

    public void a() {
        synchronized (this.g) {
            this.g.clear();
        }
    }
}
