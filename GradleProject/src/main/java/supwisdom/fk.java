package supwisdom;

import java.util.List;

/* JADX INFO: compiled from: CombinedChartData.java */
/* JADX INFO: loaded from: classes.dex */
public class fk extends lk {
    public List<ek> i;

    @Override // supwisdom.lk, supwisdom.ek
    public double a() {
        if (this.f == this.g) {
            return 0.5d;
        }
        boolean z = true;
        boolean z2 = true;
        for (ek ekVar : this.i) {
            if (!(ekVar instanceof hk)) {
                z2 = false;
            }
            if (ekVar.f().size() > 0 && ((ok) ekVar.f().get(0)).d().size() > 1) {
                z = false;
            }
        }
        if (!z && z2) {
            return this.g;
        }
        double d = this.g;
        double d2 = this.h;
        return d + (d2 / 2.0d);
    }

    @Override // supwisdom.ek
    public double c() {
        Double dValueOf = null;
        for (ek ekVar : this.i) {
            if (!ekVar.i()) {
                if (dValueOf == null) {
                    dValueOf = Double.valueOf(ekVar.c());
                } else if (dValueOf.doubleValue() < ekVar.c()) {
                    dValueOf = Double.valueOf(ekVar.c());
                }
            }
        }
        if (dValueOf == null) {
            return 0.0d;
        }
        return dValueOf.doubleValue();
    }

    @Override // supwisdom.lk, supwisdom.ek
    public double d() {
        if (this.f == this.g) {
            return -0.5d;
        }
        boolean z = true;
        boolean z2 = true;
        for (ek ekVar : this.i) {
            if (!(ekVar instanceof hk)) {
                z2 = false;
            }
            if (ekVar.f().size() > 0 && ((ok) ekVar.f().get(0)).d().size() > 1) {
                z = false;
            }
        }
        if (!z && z2) {
            return this.f;
        }
        double d = this.f;
        double d2 = this.h;
        return d - (d2 / 2.0d);
    }

    @Override // supwisdom.ek
    public double e() {
        Double dValueOf = null;
        for (ek ekVar : this.i) {
            if (!ekVar.i()) {
                if (dValueOf == null) {
                    dValueOf = Double.valueOf(ekVar.e());
                } else if (dValueOf.doubleValue() > ekVar.e()) {
                    dValueOf = Double.valueOf(ekVar.e());
                }
            }
        }
        if (dValueOf == null) {
            return 0.0d;
        }
        return dValueOf.doubleValue();
    }

    @Override // supwisdom.lk
    public double j() {
        boolean z = true;
        boolean z2 = true;
        for (ek ekVar : this.i) {
            if (!(ekVar instanceof hk)) {
                z2 = false;
            }
            if (ekVar.f().size() > 0 && ((ok) ekVar.f().get(0)).d().size() > 1) {
                z = false;
            }
        }
        if (z) {
            return super.j();
        }
        if (z2) {
            return 0.0d;
        }
        return super.j();
    }

    public List<ek> k() {
        return this.i;
    }
}
