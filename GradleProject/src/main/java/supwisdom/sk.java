package supwisdom;

/* JADX INFO: compiled from: PolarYDataSet.java */
/* JADX INFO: loaded from: classes.dex */
public class sk extends pk {
    public float A;
    public float B;
    public kn C;
    public int D;
    public Float y;
    public int[] z;

    @Override // supwisdom.pk
    public void j() {
        float f = 0.0f;
        int i = 0;
        while (true) {
            if (i >= this.c.size()) {
                break;
            }
            double dDoubleValue = this.c.get(i) != null ? this.c.get(i).doubleValue() : 0.0d;
            f = (float) (((double) f) + dDoubleValue);
            Double d = this.g;
            if (d == null || this.h == null) {
                this.g = Double.valueOf(dDoubleValue);
                this.h = Double.valueOf(dDoubleValue);
            } else {
                if (d != null && d.doubleValue() > dDoubleValue) {
                    this.g = this.c.get(i);
                }
                Double d2 = this.h;
                if (d2 != null && d2.doubleValue() < dDoubleValue) {
                    this.h = this.c.get(i);
                }
            }
            i++;
        }
        if (this.y == null) {
            this.y = Float.valueOf(f);
        }
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            Double dValueOf = this.c.get(i2);
            if (dValueOf == null) {
                dValueOf = Double.valueOf(0.0d);
            }
            double dDoubleValue2 = (dValueOf.doubleValue() / ((double) this.y.floatValue())) * 360.0d;
            if (dDoubleValue2 < 0.001d) {
                dDoubleValue2 = 0.0010000000474974513d;
            }
            this.d.add(new al(i2, dValueOf, (float) dDoubleValue2));
        }
    }

    public int[] l() {
        return this.z;
    }

    public kn m() {
        return this.C;
    }

    public float n() {
        return this.A;
    }

    public int o() {
        return this.D;
    }

    public float p() {
        return this.B;
    }
}
