package supwisdom;

import org.mozilla.universalchardet.prober.CharsetProber;

/* JADX INFO: loaded from: classes3.dex */
public class ov1 extends CharsetProber {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final yv1 f8724e = new zv1();
    public CharsetProber.ProbingState c;
    public int d = 0;
    public sv1 b = new sv1(f8724e);

    public ov1() {
        d();
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public String a() {
        return kv1.f8213e;
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public float b() {
        float f = 0.99f;
        if (this.d >= 6) {
            return 0.99f;
        }
        for (int i = 0; i < this.d; i++) {
            f *= 0.5f;
        }
        return 1.0f - f;
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public void d() {
        this.b.c();
        this.d = 0;
        this.c = CharsetProber.ProbingState.DETECTING;
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public CharsetProber.ProbingState a(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        while (true) {
            if (i >= i3) {
                break;
            }
            int iA = this.b.a(bArr[i]);
            if (iA == 1) {
                this.c = CharsetProber.ProbingState.NOT_ME;
                break;
            }
            if (iA == 2) {
                this.c = CharsetProber.ProbingState.FOUND_IT;
                break;
            }
            if (iA == 0 && this.b.b() >= 2) {
                this.d++;
            }
            i++;
        }
        if (this.c == CharsetProber.ProbingState.DETECTING && b() > 0.95f) {
            this.c = CharsetProber.ProbingState.FOUND_IT;
        }
        return this.c;
    }
}
