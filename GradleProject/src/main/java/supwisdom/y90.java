package supwisdom;

/* JADX INFO: compiled from: CompositeSequenceableLoader.java */
/* JADX INFO: loaded from: classes.dex */
public final class y90 implements nb0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final nb0[] f9857a;

    public y90(nb0[] nb0VarArr) {
        this.f9857a = nb0VarArr;
    }

    @Override // supwisdom.nb0
    public boolean a(long j) {
        boolean zA;
        boolean z = false;
        do {
            long jI = i();
            if (jI == Long.MIN_VALUE) {
                break;
            }
            zA = false;
            for (nb0 nb0Var : this.f9857a) {
                if (nb0Var.i() == jI) {
                    zA |= nb0Var.a(j);
                }
            }
            z |= zA;
        } while (zA);
        return z;
    }

    @Override // supwisdom.nb0
    public long i() {
        long jMin = Long.MAX_VALUE;
        for (nb0 nb0Var : this.f9857a) {
            long jI = nb0Var.i();
            if (jI != Long.MIN_VALUE) {
                jMin = Math.min(jMin, jI);
            }
        }
        if (jMin == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return jMin;
    }
}
