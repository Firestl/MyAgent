package supwisdom;

/* JADX INFO: compiled from: Vector3.java */
/* JADX INFO: loaded from: classes.dex */
public class uj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f9416a;
    public double b;
    public double c;

    public uj(double d, double d2, double d3) {
        this.f9416a = d;
        this.b = d2;
        this.c = d3;
    }

    public void a(double d, double d2, double d3) {
        this.f9416a = d;
        this.b = d2;
        this.c = d3;
    }

    public uj a(pj pjVar) {
        double d = this.f9416a;
        double d2 = this.b;
        double d3 = this.c;
        double d4 = pjVar.f8797a;
        double d5 = pjVar.b;
        double d6 = pjVar.c;
        double d7 = pjVar.d;
        double d8 = ((d7 * d) + (d5 * d3)) - (d6 * d2);
        double d9 = ((d7 * d2) + (d6 * d)) - (d4 * d3);
        double d10 = ((d7 * d3) + (d4 * d2)) - (d5 * d);
        double d11 = -d4;
        double d12 = ((d * d11) - (d2 * d5)) - (d3 * d6);
        double d13 = -d6;
        double d14 = -d5;
        this.f9416a = (((d8 * d7) + (d12 * d11)) + (d9 * d13)) - (d10 * d14);
        this.b = (((d9 * d7) + (d12 * d14)) + (d10 * d11)) - (d8 * d13);
        this.c = (((d10 * d7) + (d12 * d13)) + (d8 * d14)) - (d9 * d11);
        return this;
    }
}
