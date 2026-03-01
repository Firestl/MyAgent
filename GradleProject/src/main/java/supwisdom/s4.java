package supwisdom;

/* JADX INFO: compiled from: CurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class s4 {

    /* JADX INFO: compiled from: CurveFit.java */
    public static class a extends s4 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public double f9120a;
        public double[] b;

        public a(double d, double[] dArr) {
            this.f9120a = d;
            this.b = dArr;
        }

        @Override // supwisdom.s4
        public void a(double d, double[] dArr) {
            double[] dArr2 = this.b;
            System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        }

        @Override // supwisdom.s4
        public double b(double d, int i) {
            return 0.0d;
        }

        @Override // supwisdom.s4
        public void b(double d, double[] dArr) {
            for (int i = 0; i < this.b.length; i++) {
                dArr[i] = 0.0d;
            }
        }

        @Override // supwisdom.s4
        public void a(double d, float[] fArr) {
            int i = 0;
            while (true) {
                double[] dArr = this.b;
                if (i >= dArr.length) {
                    return;
                }
                fArr[i] = (float) dArr[i];
                i++;
            }
        }

        @Override // supwisdom.s4
        public double a(double d, int i) {
            return this.b[i];
        }

        @Override // supwisdom.s4
        public double[] a() {
            return new double[]{this.f9120a};
        }
    }

    public static s4 a(int i, double[] dArr, double[][] dArr2) {
        if (dArr.length == 1) {
            i = 2;
        }
        return i != 0 ? i != 2 ? new u4(dArr, dArr2) : new a(dArr[0], dArr2[0]) : new v4(dArr, dArr2);
    }

    public abstract double a(double d, int i);

    public abstract void a(double d, double[] dArr);

    public abstract void a(double d, float[] fArr);

    public abstract double[] a();

    public abstract double b(double d, int i);

    public abstract void b(double d, double[] dArr);

    public static s4 a(int[] iArr, double[] dArr, double[][] dArr2) {
        return new r4(iArr, dArr, dArr2);
    }
}
