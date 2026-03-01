package supwisdom;

import android.text.TextUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import supwisdom.e50;

/* JADX INFO: compiled from: WebvttExtractor.java */
/* JADX INFO: loaded from: classes.dex */
public final class lb0 implements y30 {
    public static final Pattern g = Pattern.compile("LOCAL:([^,]+)");
    public static final Pattern h = Pattern.compile("MPEGTS:(\\d+)");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8274a;
    public final u80 b;
    public z40 d;
    public int f;
    public final o80 c = new o80();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f8275e = new byte[1024];

    public lb0(String str, u80 u80Var) {
        this.f8274a = str;
        this.b = u80Var;
    }

    @Override // supwisdom.y30
    public boolean a(v40 v40Var) throws InterruptedException, IOException {
        throw new IllegalStateException();
    }

    @Override // supwisdom.y30
    public void c() {
    }

    @Override // supwisdom.y30
    public void a(z40 z40Var) {
        this.d = z40Var;
        z40Var.a(new e50.a(-9223372036854775807L));
    }

    @Override // supwisdom.y30
    public void a(long j, long j2) {
        throw new IllegalStateException();
    }

    @Override // supwisdom.y30
    public int a(v40 v40Var, d50 d50Var) throws InterruptedException, IOException {
        int iD = (int) v40Var.d();
        int i = this.f;
        byte[] bArr = this.f8275e;
        if (i == bArr.length) {
            this.f8275e = Arrays.copyOf(bArr, ((iD != -1 ? iD : bArr.length) * 3) / 2);
        }
        byte[] bArr2 = this.f8275e;
        int i2 = this.f;
        int iA = v40Var.a(bArr2, i2, bArr2.length - i2);
        if (iA != -1) {
            int i3 = this.f + iA;
            this.f = i3;
            if (iD == -1 || i3 != iD) {
                return 0;
            }
        }
        a();
        return -1;
    }

    public final void a() throws com.google.android.exoplayer2.n {
        o80 o80Var = new o80(this.f8275e);
        try {
            x60.a(o80Var);
            long jD = 0;
            long jA = 0;
            while (true) {
                String strY = o80Var.y();
                if (!TextUtils.isEmpty(strY)) {
                    if (strY.startsWith("X-TIMESTAMP-MAP")) {
                        Matcher matcher = g.matcher(strY);
                        if (matcher.find()) {
                            Matcher matcher2 = h.matcher(strY);
                            if (matcher2.find()) {
                                jA = x60.a(matcher.group(1));
                                jD = u80.d(Long.parseLong(matcher2.group(1)));
                            } else {
                                throw new com.google.android.exoplayer2.n("X-TIMESTAMP-MAP doesn't contain media timestamp: " + strY);
                            }
                        } else {
                            throw new com.google.android.exoplayer2.n("X-TIMESTAMP-MAP doesn't contain local timestamp: " + strY);
                        }
                    }
                } else {
                    Matcher matcherB = x60.b(o80Var);
                    if (matcherB == null) {
                        a(0L);
                        return;
                    }
                    long jA2 = x60.a(matcherB.group(1));
                    long jC = this.b.c((jD + jA2) - jA);
                    f50 f50VarA = a(jC - jA2);
                    this.c.a(this.f8275e, this.f);
                    f50VarA.a(this.c, this.f);
                    f50VarA.a(jC, 1, this.f, 0, null);
                    return;
                }
            }
        } catch (com.google.android.exoplayer2.g.f e2) {
            throw new com.google.android.exoplayer2.n(e2);
        }
    }

    public final f50 a(long j) {
        f50 f50VarA = this.d.a(0, 3);
        f50VarA.a(com.google.android.exoplayer2.j.a((String) null, "text/vtt", (String) null, -1, 0, this.f8274a, (com.google.android.exoplayer2.c.a) null, j));
        this.d.a();
        return f50VarA;
    }
}
