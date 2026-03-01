package supwisdom;

/* JADX INFO: compiled from: OrientationEvaluator.java */
/* JADX INFO: loaded from: classes.dex */
public class oj {
    public Double b;
    public Double c;
    public Double d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public pj f8679a = new pj(0.0d, 0.0d, 0.0d, 1.0d);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f8680e = 0.0d;
    public double f = 0.0d;
    public double g = 0.0d;
    public final uj h = new uj(0.0d, 0.0d, 1.0d);
    public final gj i = new gj();
    public final pj j = new pj();
    public final pj k = new pj(-Math.sqrt(0.5d), 0.0d, 0.0d, Math.sqrt(0.5d));

    public oj(Double d, Double d2, Double d3) {
        this.b = null;
        this.c = null;
        this.d = null;
        this.b = d;
        this.c = d2;
        this.d = d3;
    }

    public pj a(double d, double d2, double d3, double d4) {
        Double d5 = this.b;
        double radians = Math.toRadians(d5 != null ? d5.doubleValue() : d4 + this.f8680e);
        Double d6 = this.c;
        double radians2 = Math.toRadians(d6 != null ? d6.doubleValue() : this.f + d2);
        Double d7 = this.d;
        a(this.f8679a, radians, radians2, Math.toRadians(d7 != null ? d7.doubleValue() : d3 + this.g), 0.0d);
        return this.f8679a;
    }

    public final void a(pj pjVar, double d, double d2, double d3, double d4) {
        this.i.a(d2, d, -d3, "YXZ");
        pjVar.a(this.i);
        pjVar.a(this.k);
        pj pjVar2 = this.j;
        pjVar2.a(this.h, -d4);
        pjVar.a(pjVar2);
    }
}
