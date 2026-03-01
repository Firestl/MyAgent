package supwisdom;

/* JADX INFO: compiled from: ValueFormatter.java */
/* JADX INFO: loaded from: classes.dex */
public class hn {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f7852a;
    public int b;

    public hn(String str, int i) {
        this.f7852a = "Number";
        this.b = 2;
        if (str != null) {
            this.f7852a = str;
        }
        this.b = i;
    }

    public String a(String str) {
        return !wn.a(str) ? str : this.f7852a.equals("Number") ? xn.a(str, this.b, true, false, false) : this.f7852a.equals("shortenNumber") ? Double.valueOf(str) != null ? xn.a(Double.valueOf(str), this.b, true) : xn.a(Double.valueOf(0.0d), this.b, true) : this.f7852a.equals("percentage") ? xn.a(str, this.b, false, true, true) : xn.a(str);
    }

    public String a(Number number) {
        if (this.f7852a.equals("Number")) {
            return xn.a(number, this.b, true, false, false);
        }
        if (this.f7852a.equals("shortenNumber")) {
            return xn.a(number, this.b, true);
        }
        if (this.f7852a.equals("percentage")) {
            return xn.a(number, this.b, false, true, true);
        }
        return xn.a(String.valueOf(number));
    }
}
