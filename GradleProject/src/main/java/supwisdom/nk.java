package supwisdom;

import java.util.List;

/* JADX INFO: compiled from: StackedBarChartData.java */
/* JADX INFO: loaded from: classes.dex */
public class nk extends dk {
    public List<dk> i;

    @Override // supwisdom.lk, supwisdom.ek
    public double b() {
        return super.b();
    }

    @Override // supwisdom.ek
    public double c() {
        Double dValueOf = null;
        for (dk dkVar : this.i) {
            if (!dkVar.i()) {
                if (dValueOf == null) {
                    dValueOf = Double.valueOf(dkVar.b());
                } else if (dValueOf.doubleValue() < dkVar.b()) {
                    dValueOf = Double.valueOf(dkVar.b());
                }
            }
        }
        if (dValueOf == null) {
            return 0.0d;
        }
        return dValueOf.doubleValue();
    }

    @Override // supwisdom.ek
    public double e() {
        Double dValueOf = null;
        for (dk dkVar : this.i) {
            if (!dkVar.i()) {
                if (dValueOf == null) {
                    dValueOf = Double.valueOf(dkVar.e());
                } else if (dValueOf.doubleValue() > dkVar.e()) {
                    dValueOf = Double.valueOf(dkVar.e());
                }
            }
        }
        if (dValueOf == null) {
            return 0.0d;
        }
        return dValueOf.doubleValue();
    }

    public List<dk> k() {
        return this.i;
    }
}
