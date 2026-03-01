package supwisdom;

import org.mozilla.universalchardet.prober.CharsetProber;

/* JADX INFO: loaded from: classes3.dex */
public class mv1 extends CharsetProber {
    public static final tv1 f = new tv1();
    public static final uv1 g = new uv1();
    public static final vv1 h = new vv1();
    public static final wv1 i = new wv1();
    public sv1[] b;
    public int c;
    public CharsetProber.ProbingState d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f8444e;

    public mv1() {
        sv1[] sv1VarArr = new sv1[4];
        this.b = sv1VarArr;
        sv1VarArr[0] = new sv1(f);
        this.b[1] = new sv1(g);
        this.b[2] = new sv1(h);
        this.b[3] = new sv1(i);
        d();
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public String a() {
        return this.f8444e;
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public float b() {
        return 0.99f;
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public void d() {
        this.d = CharsetProber.ProbingState.DETECTING;
        int i2 = 0;
        while (true) {
            sv1[] sv1VarArr = this.b;
            if (i2 >= sv1VarArr.length) {
                this.c = sv1VarArr.length;
                this.f8444e = null;
                return;
            } else {
                sv1VarArr[i2].c();
                i2++;
            }
        }
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public CharsetProber.ProbingState a(byte[] bArr, int i2, int i3) {
        int i4 = i3 + i2;
        while (i2 < i4 && this.d == CharsetProber.ProbingState.DETECTING) {
            for (int i5 = this.c - 1; i5 >= 0; i5--) {
                int iA = this.b[i5].a(bArr[i2]);
                if (iA == 1) {
                    int i6 = this.c - 1;
                    this.c = i6;
                    if (i6 <= 0) {
                        CharsetProber.ProbingState probingState = CharsetProber.ProbingState.NOT_ME;
                        this.d = probingState;
                        return probingState;
                    }
                    if (i5 != i6) {
                        sv1[] sv1VarArr = this.b;
                        sv1 sv1Var = sv1VarArr[i6];
                        sv1VarArr[i6] = sv1VarArr[i5];
                        sv1VarArr[i5] = sv1Var;
                    }
                } else if (iA == 2) {
                    this.d = CharsetProber.ProbingState.FOUND_IT;
                    this.f8444e = this.b[i5].a();
                    return this.d;
                }
            }
            i2++;
        }
        return this.d;
    }
}
