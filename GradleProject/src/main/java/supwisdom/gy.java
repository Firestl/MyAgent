package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public final class gy extends ky {
    public gy(ew ewVar) {
        super(ewVar);
    }

    @Override // supwisdom.ny
    public int a(int i) {
        return i < 10000 ? i : i - 10000;
    }

    @Override // supwisdom.ny
    public void c(StringBuilder sb, int i) {
        if (i < 10000) {
            sb.append("(3202)");
        } else {
            sb.append("(3203)");
        }
    }
}
