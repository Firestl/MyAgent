package supwisdom;

/* JADX INFO: loaded from: classes3.dex */
public class dw1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f7390a;
    public String b;
    public String c;
    public int d;

    public dw1(String str) {
        this.c = str;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(long j) {
        this.f7390a = j;
    }

    public void a(String str) {
        this.b = str;
    }

    public boolean a() {
        return this.f7390a > System.currentTimeMillis();
    }

    public void b() {
        this.f7390a = 0L;
    }
}
