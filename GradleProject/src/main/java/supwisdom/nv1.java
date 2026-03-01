package supwisdom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mozilla.universalchardet.prober.CharsetProber;

/* JADX INFO: loaded from: classes3.dex */
public class nv1 extends CharsetProber {
    public CharsetProber.ProbingState b;
    public List<CharsetProber> c;
    public CharsetProber d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8583e;

    public nv1() {
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        arrayList.add(new ov1());
        this.c.add(new lv1());
        d();
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public String a() {
        if (this.d == null) {
            b();
            if (this.d == null) {
                this.d = this.c.get(0);
            }
        }
        return this.d.a();
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public float b() {
        CharsetProber.ProbingState probingState = this.b;
        if (probingState == CharsetProber.ProbingState.FOUND_IT) {
            return 0.99f;
        }
        if (probingState == CharsetProber.ProbingState.NOT_ME) {
            return 0.01f;
        }
        float f = 0.0f;
        for (CharsetProber charsetProber : this.c) {
            if (charsetProber.c()) {
                float fB = charsetProber.b();
                if (f < fB) {
                    this.d = charsetProber;
                    f = fB;
                }
            }
        }
        return f;
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public void d() {
        this.f8583e = 0;
        for (CharsetProber charsetProber : this.c) {
            charsetProber.d();
            charsetProber.a(true);
            this.f8583e++;
        }
        this.d = null;
        this.b = CharsetProber.ProbingState.DETECTING;
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public CharsetProber.ProbingState a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        int i3 = i2 + i;
        boolean z = true;
        int i4 = 0;
        while (i < i3) {
            if ((bArr[i] & com.igexin.c.a.d.g.n) != 0) {
                bArr2[i4] = bArr[i];
                i4++;
                z = true;
            } else if (z) {
                bArr2[i4] = bArr[i];
                i4++;
                z = false;
            }
            i++;
        }
        Iterator<CharsetProber> it = this.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CharsetProber next = it.next();
            if (next.c()) {
                CharsetProber.ProbingState probingStateA = next.a(bArr2, 0, i4);
                CharsetProber.ProbingState probingState = CharsetProber.ProbingState.FOUND_IT;
                if (probingStateA == probingState) {
                    this.d = next;
                    this.b = probingState;
                    break;
                }
                CharsetProber.ProbingState probingState2 = CharsetProber.ProbingState.NOT_ME;
                if (probingStateA == probingState2) {
                    next.a(false);
                    int i5 = this.f8583e - 1;
                    this.f8583e = i5;
                    if (i5 <= 0) {
                        this.b = probingState2;
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        return this.b;
    }
}
