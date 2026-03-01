package supwisdom;

/* JADX INFO: loaded from: classes3.dex */
public class sv1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public yv1 f9216a;
    public int b = 0;
    public int c;
    public int d;

    public sv1(yv1 yv1Var) {
        this.f9216a = yv1Var;
    }

    public String a() {
        return this.f9216a.a();
    }

    public int b() {
        return this.c;
    }

    public void c() {
        this.b = 0;
    }

    public int a(byte b) {
        int iA = this.f9216a.a(b);
        if (this.b == 0) {
            this.d = 0;
            this.c = this.f9216a.a(iA);
        }
        int iA2 = this.f9216a.a(iA, this.b);
        this.b = iA2;
        this.d++;
        return iA2;
    }
}
