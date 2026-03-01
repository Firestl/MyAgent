package supwisdom;

import java.util.Arrays;
import org.mozilla.universalchardet.prober.CharsetProber;

/* JADX INFO: loaded from: classes3.dex */
public class lv1 extends CharsetProber {
    public static final yv1 f = new rv1();
    public CharsetProber.ProbingState c;
    public sv1 b = new sv1(f);
    public pv1 d = new pv1();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f8320e = new byte[2];

    public lv1() {
        d();
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public String a() {
        return kv1.d;
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public float b() {
        return this.d.a();
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public void d() {
        this.b.c();
        this.c = CharsetProber.ProbingState.DETECTING;
        this.d.c();
        Arrays.fill(this.f8320e, (byte) 0);
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public CharsetProber.ProbingState a(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        int i4 = i;
        while (true) {
            if (i4 >= i3) {
                break;
            }
            int iA = this.b.a(bArr[i4]);
            if (iA == 1) {
                this.c = CharsetProber.ProbingState.NOT_ME;
                break;
            }
            if (iA == 2) {
                this.c = CharsetProber.ProbingState.FOUND_IT;
                break;
            }
            if (iA == 0) {
                int iB = this.b.b();
                if (i4 == i) {
                    byte[] bArr2 = this.f8320e;
                    bArr2[1] = bArr[i];
                    this.d.a(bArr2, 0, iB);
                } else {
                    this.d.a(bArr, i4 - 1, iB);
                }
            }
            i4++;
        }
        this.f8320e[0] = bArr[i3 - 1];
        if (this.c == CharsetProber.ProbingState.DETECTING && this.d.b() && b() > 0.95f) {
            this.c = CharsetProber.ProbingState.FOUND_IT;
        }
        return this.c;
    }
}
