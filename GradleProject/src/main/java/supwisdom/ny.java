package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public abstract class ny extends my {
    public ny(ew ewVar) {
        super(ewVar);
    }

    public abstract int a(int i);

    public final void b(StringBuilder sb, int i, int i2) {
        int iA = a().a(i, i2);
        c(sb, iA);
        int iA2 = a(iA);
        int i3 = 100000;
        for (int i4 = 0; i4 < 5; i4++) {
            if (iA2 / i3 == 0) {
                sb.append('0');
            }
            i3 /= 10;
        }
        sb.append(iA2);
    }

    public abstract void c(StringBuilder sb, int i);
}
