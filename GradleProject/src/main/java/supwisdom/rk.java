package supwisdom;

/* JADX INFO: compiled from: NightingaleRoseDataSet.java */
/* JADX INFO: loaded from: classes.dex */
public class rk extends sk {
    public Float E;
    public int[] F;

    @Override // supwisdom.sk, supwisdom.pk
    public void j() {
        float fDoubleValue = 0.0f;
        for (int i = 0; i < this.c.size(); i++) {
            Double d = this.c.get(i);
            fDoubleValue = (float) (((double) fDoubleValue) + d.doubleValue());
            Double d2 = this.g;
            if (d2 == null || this.h == null) {
                this.g = d;
                this.h = d;
            } else {
                if (d2 != null && d2.doubleValue() > d.doubleValue()) {
                    this.g = this.c.get(i);
                }
                Double d3 = this.h;
                if (d3 != null && d3.doubleValue() < d.doubleValue()) {
                    this.h = this.c.get(i);
                }
            }
        }
        if (this.E == null) {
            this.E = Float.valueOf(fDoubleValue);
        }
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            this.d.add(new al(i2, this.c.get(i2), 360.0f / this.c.size()));
        }
    }

    @Override // supwisdom.sk
    public int[] l() {
        return this.F;
    }
}
