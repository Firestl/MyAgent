package supwisdom;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import java.nio.charset.Charset;
import java.util.List;

/* JADX INFO: compiled from: Tx3gDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class n60 extends c60 {
    public static final int u = x80.g("styl");
    public static final int v = x80.g("tbox");
    public final o80 n;
    public boolean o;
    public int p;
    public int q;
    public String r;
    public float s;
    public int t;

    public n60(List<byte[]> list) {
        super("Tx3gDecoder");
        this.n = new o80();
        a(list);
    }

    public static void b(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3, int i4, int i5) {
        if (i != i2) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan((i >>> 8) | ((i & 255) << 24)), i3, i4, i5 | 33);
        }
    }

    public final void a(List<byte[]> list) {
        if (list == null || list.size() != 1 || (list.get(0).length != 48 && list.get(0).length != 53)) {
            this.p = 0;
            this.q = -1;
            this.r = "sans-serif";
            this.o = false;
            this.s = 0.85f;
            return;
        }
        byte[] bArr = list.get(0);
        this.p = bArr[24];
        this.q = ((bArr[26] & 255) << 24) | ((bArr[27] & 255) << 16) | ((bArr[28] & 255) << 8) | (bArr[29] & 255);
        this.r = "Serif".equals(new String(bArr, 43, bArr.length - 43)) ? "serif" : "sans-serif";
        this.t = bArr[25] * 20;
        boolean z = (bArr[0] & 32) != 0;
        this.o = z;
        if (!z) {
            this.s = 0.85f;
            return;
        }
        float f = ((bArr[11] & 255) | ((bArr[10] & 255) << 8)) / this.t;
        this.s = f;
        this.s = x80.a(f, 0.0f, 0.95f);
    }

    @Override // supwisdom.c60
    public m60 a(byte[] bArr, int i, boolean z) throws com.google.android.exoplayer2.g.f {
        this.n.a(bArr, i);
        String strA = a(this.n);
        if (strA.isEmpty()) {
            return o60.b;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(strA);
        a(spannableStringBuilder, this.p, 0, 0, spannableStringBuilder.length(), 16711680);
        b(spannableStringBuilder, this.q, -1, 0, spannableStringBuilder.length(), 16711680);
        a(spannableStringBuilder, this.r, "sans-serif", 0, spannableStringBuilder.length(), 16711680);
        float fA = this.s;
        while (this.n.b() >= 8) {
            int iD = this.n.d();
            int iN = this.n.n();
            int iN2 = this.n.n();
            if (iN2 == u) {
                a(this.n.b() >= 2);
                int iH = this.n.h();
                for (int i2 = 0; i2 < iH; i2++) {
                    a(this.n, spannableStringBuilder);
                }
            } else if (iN2 == v && this.o) {
                a(this.n.b() >= 2);
                fA = x80.a(this.n.h() / this.t, 0.0f, 0.95f);
            }
            this.n.c(iD + iN);
        }
        return new o60(new y50(spannableStringBuilder, null, fA, 0, 0, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE));
    }

    public static String a(o80 o80Var) throws com.google.android.exoplayer2.g.f {
        char cF;
        a(o80Var.b() >= 2);
        int iH = o80Var.h();
        if (iH == 0) {
            return "";
        }
        if (o80Var.b() >= 2 && ((cF = o80Var.f()) == 65279 || cF == 65534)) {
            return o80Var.a(iH, Charset.forName("UTF-16"));
        }
        return o80Var.a(iH, Charset.forName("UTF-8"));
    }

    public final void a(o80 o80Var, SpannableStringBuilder spannableStringBuilder) throws com.google.android.exoplayer2.g.f {
        a(o80Var.b() >= 12);
        int iH = o80Var.h();
        int iH2 = o80Var.h();
        o80Var.d(2);
        int iG = o80Var.g();
        o80Var.d(1);
        int iN = o80Var.n();
        a(spannableStringBuilder, iG, this.p, iH, iH2, 0);
        b(spannableStringBuilder, iN, this.q, iH, iH2, 0);
    }

    public static void a(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3, int i4, int i5) {
        if (i != i2) {
            int i6 = i5 | 33;
            boolean z = (i & 1) != 0;
            boolean z2 = (i & 2) != 0;
            if (z) {
                if (z2) {
                    spannableStringBuilder.setSpan(new StyleSpan(3), i3, i4, i6);
                } else {
                    spannableStringBuilder.setSpan(new StyleSpan(1), i3, i4, i6);
                }
            } else if (z2) {
                spannableStringBuilder.setSpan(new StyleSpan(2), i3, i4, i6);
            }
            boolean z3 = (i & 4) != 0;
            if (z3) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i3, i4, i6);
            }
            if (z3 || z || z2) {
                return;
            }
            spannableStringBuilder.setSpan(new StyleSpan(0), i3, i4, i6);
        }
    }

    public static void a(SpannableStringBuilder spannableStringBuilder, String str, String str2, int i, int i2, int i3) {
        if (str != str2) {
            spannableStringBuilder.setSpan(new TypefaceSpan(str), i, i2, i3 | 33);
        }
    }

    public static void a(boolean z) throws com.google.android.exoplayer2.g.f {
        if (!z) {
            throw new com.google.android.exoplayer2.g.f("Unexpected subtitle format.");
        }
    }
}
