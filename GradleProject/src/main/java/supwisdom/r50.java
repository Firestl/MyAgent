package supwisdom;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import com.baidu.speech.core.BDSHttpRequestMaker;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.facebook.imageutils.JfifUtil;
import com.tencent.ijk.media.player.IjkMediaPlayer;
import com.umeng.analytics.pro.q;
import io.dcloud.feature.audio.recorder.RecorderTask;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: Cea608Decoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class r50 extends u50 {
    public static final int[] r = {11, 1, 3, 12, 14, 5, 7, 9};
    public static final int[] s = {0, 4, 8, 12, 16, 20, 24, 28};
    public static final int[] t = {-1, -16711936, -16776961, -16711681, -65536, DefaultImageHeaderParser.VP8_HEADER_MASK, -65281};
    public static final int[] u = {32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 225, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINISH, 250, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 231, 247, 209, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, 9632};
    public static final int[] v = {174, 176, 189, 191, 8482, 162, 163, 9834, 224, 32, 232, 226, 234, 238, 244, 251};
    public static final int[] w = {193, 201, 211, 218, RecorderTask.FRAME_COUNT, 252, 8216, 161, 42, 39, q.a.D, 169, 8480, 8226, 8220, 8221, 192, 194, 199, 200, 202, 203, 235, 206, 207, 239, IjkMediaPlayer.MEDIA_VIDEO_DECODER_ERROR, 217, 249, 219, 171, 187};
    public static final int[] x = {195, 227, 205, 204, 236, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, BDSHttpRequestMaker.TYPE_DOWNLOAD_THIRD_DATA, 213, 245, 123, 125, 92, 94, 95, 124, 126, 196, 228, 214, 246, 223, 165, 164, 9474, 197, 229, JfifUtil.MARKER_SOI, 248, 9484, 9488, 9492, 9496};
    public final int g;
    public final int h;
    public List<y50> k;
    public List<y50> l;
    public int m;
    public int n;
    public boolean o;
    public byte p;
    public byte q;
    public final o80 f = new o80();
    public final LinkedList<a> i = new LinkedList<>();
    public a j = new a(0, 4);

    /* JADX INFO: compiled from: Cea608Decoder.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<CharacterStyle> f9012a = new ArrayList();
        public final List<C0230a> b = new ArrayList();
        public final List<SpannableString> c = new LinkedList();
        public final SpannableStringBuilder d = new SpannableStringBuilder();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f9013e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;

        /* JADX INFO: renamed from: supwisdom.r50$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Cea608Decoder.java */
        public static class C0230a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final CharacterStyle f9014a;
            public final int b;
            public final int c;

            public C0230a(CharacterStyle characterStyle, int i, int i2) {
                this.f9014a = characterStyle;
                this.b = i;
                this.c = i2;
            }
        }

        public a(int i, int i2) {
            a(i, i2);
        }

        public void a(int i, int i2) {
            this.f9012a.clear();
            this.b.clear();
            this.c.clear();
            this.d.clear();
            this.f9013e = 15;
            this.f = 0;
            this.g = 0;
            this.h = i;
            this.i = i2;
            this.j = -1;
        }

        public void b() {
            int length = this.d.length();
            if (length > 0) {
                this.d.delete(length - 1, length);
            }
        }

        public int c() {
            return this.f9013e;
        }

        public void d() {
            this.c.add(e());
            this.d.clear();
            this.f9012a.clear();
            this.b.clear();
            this.j = -1;
            int iMin = Math.min(this.i, this.f9013e);
            while (this.c.size() >= iMin) {
                this.c.remove(0);
            }
        }

        public SpannableString e() {
            int length = this.d.length();
            int i = 0;
            for (int i2 = 0; i2 < this.f9012a.size(); i2++) {
                this.d.setSpan(this.f9012a.get(i2), 0, length, 33);
            }
            while (i < this.b.size()) {
                C0230a c0230a = this.b.get(i);
                int size = this.b.size();
                int i3 = c0230a.c;
                this.d.setSpan(c0230a.f9014a, c0230a.b, i < size - i3 ? this.b.get(i3 + i).b : length, 33);
                i++;
            }
            if (this.j != -1) {
                this.d.setSpan(new UnderlineSpan(), this.j, length, 33);
            }
            return new SpannableString(this.d);
        }

        public y50 f() {
            float f;
            int i;
            int i2;
            int i3;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i4 = 0; i4 < this.c.size(); i4++) {
                spannableStringBuilder.append((CharSequence) this.c.get(i4));
                spannableStringBuilder.append('\n');
            }
            spannableStringBuilder.append((CharSequence) e());
            if (spannableStringBuilder.length() == 0) {
                return null;
            }
            int i5 = this.f + this.g;
            int length = i5 - ((32 - i5) - spannableStringBuilder.length());
            if (this.h == 2 && Math.abs(length) < 3) {
                f = 0.5f;
                i = 1;
            } else if (this.h != 2 || length <= 0) {
                f = ((i5 / 32.0f) * 0.8f) + 0.1f;
                i = 0;
            } else {
                f = (((32 - r3) / 32.0f) * 0.8f) + 0.1f;
                i = 2;
            }
            if (this.h == 1 || (i2 = this.f9013e) > 7) {
                i2 = (this.f9013e - 15) - 2;
                i3 = 2;
            } else {
                i3 = 0;
            }
            return new y50(spannableStringBuilder, Layout.Alignment.ALIGN_NORMAL, i2, 1, i3, f, i, Float.MIN_VALUE);
        }

        public String toString() {
            return this.d.toString();
        }

        public void c(int i) {
            this.g = i;
        }

        public void b(int i) {
            this.f = i;
        }

        public boolean a() {
            return this.f9012a.isEmpty() && this.b.isEmpty() && this.c.isEmpty() && this.d.length() == 0;
        }

        public void a(int i) {
            this.f9013e = i;
        }

        public void a(CharacterStyle characterStyle) {
            this.f9012a.add(characterStyle);
        }

        public void a(CharacterStyle characterStyle, int i) {
            this.b.add(new C0230a(characterStyle, this.d.length(), i));
        }

        public void a(boolean z) {
            if (z) {
                this.j = this.d.length();
            } else if (this.j != -1) {
                this.d.setSpan(new UnderlineSpan(), this.j, this.d.length(), 33);
                this.j = -1;
            }
        }

        public void a(char c) {
            this.d.append(c);
        }
    }

    public r50(String str, int i) {
        this.g = "application/x-mp4-cea-608".equals(str) ? 2 : 3;
        if (i == 3 || i == 4) {
            this.h = 2;
        } else {
            this.h = 1;
        }
        a(0);
        j();
    }

    public static boolean c(byte b, byte b2) {
        return (b & 247) == 17 && (b2 & 240) == 32;
    }

    public static char d(byte b) {
        return (char) v[b & 15];
    }

    public static boolean d(byte b, byte b2) {
        return (b & 240) == 16 && (b2 & 192) == 64;
    }

    public static boolean e(byte b, byte b2) {
        return (b & 247) == 23 && b2 >= 33 && b2 <= 35;
    }

    public static boolean f(byte b, byte b2) {
        return (b & 247) == 20 && (b2 & 240) == 32;
    }

    public static boolean g(byte b) {
        return (b & 240) == 16;
    }

    @Override // supwisdom.u50
    public void a(a70 a70Var) {
        this.f.a(a70Var.c.array(), a70Var.c.limit());
        boolean z = false;
        boolean zA = false;
        while (true) {
            int iB = this.f.b();
            int i = this.g;
            if (iB < i) {
                break;
            }
            byte bG = i == 2 ? (byte) -4 : (byte) this.f.g();
            byte bG2 = (byte) (this.f.g() & 127);
            byte bG3 = (byte) (this.f.g() & 127);
            if ((bG & 6) == 4 && (this.h != 1 || (bG & 1) == 0)) {
                if (this.h != 2 || (bG & 1) == 1) {
                    if (bG2 != 0 || bG3 != 0) {
                        if ((bG2 & 247) == 17 && (bG3 & 240) == 48) {
                            this.j.a(d(bG3));
                        } else if ((bG2 & 246) == 18 && (bG3 & com.umeng.analytics.pro.co.k) == 32) {
                            this.j.b();
                            if ((bG2 & 1) == 0) {
                                this.j.a(e(bG3));
                            } else {
                                this.j.a(f(bG3));
                            }
                        } else if ((bG2 & com.umeng.analytics.pro.co.k) == 0) {
                            zA = a(bG2, bG3);
                        } else {
                            this.j.a(c(bG2));
                            if ((bG3 & com.umeng.analytics.pro.co.k) != 0) {
                                this.j.a(c(bG3));
                            }
                        }
                        z = true;
                    }
                }
            }
        }
        if (z) {
            if (!zA) {
                this.o = false;
            }
            int i2 = this.m;
            if (i2 == 1 || i2 == 3) {
                this.k = i();
            }
        }
    }

    public final void b(byte b, byte b2) {
        int i = r[b & 7];
        if ((b2 & 32) != 0) {
            i++;
        }
        if (i != this.j.c()) {
            if (this.m != 1 && !this.j.a()) {
                a aVar = new a(this.m, this.n);
                this.j = aVar;
                this.i.add(aVar);
            }
            this.j.a(i);
        }
        if ((b2 & 1) == 1) {
            this.j.a(new UnderlineSpan());
        }
        int i2 = (b2 >> 1) & 15;
        if (i2 > 7) {
            this.j.b(s[i2 & 7]);
        } else if (i2 != 7) {
            this.j.a(new ForegroundColorSpan(t[i2]));
        } else {
            this.j.a(new StyleSpan(2));
            this.j.a(new ForegroundColorSpan(-1));
        }
    }

    @Override // supwisdom.u50, supwisdom.w10
    public void c() {
        super.c();
        this.k = null;
        this.l = null;
        a(0);
        j();
        this.n = 4;
        this.o = false;
        this.p = (byte) 0;
        this.q = (byte) 0;
    }

    @Override // supwisdom.u50, supwisdom.w10
    public void d() {
    }

    @Override // supwisdom.u50
    public boolean e() {
        return this.k != this.l;
    }

    @Override // supwisdom.u50
    public m60 f() {
        List<y50> list = this.k;
        this.l = list;
        return new w50(list);
    }

    public final List<y50> i() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.i.size(); i++) {
            y50 y50VarF = this.i.get(i).f();
            if (y50VarF != null) {
                arrayList.add(y50VarF);
            }
        }
        return arrayList;
    }

    public final void j() {
        this.j.a(this.m, this.n);
        this.i.clear();
        this.i.add(this.j);
    }

    public static char e(byte b) {
        return (char) w[b & com.umeng.analytics.pro.co.j];
    }

    public static char f(byte b) {
        return (char) x[b & com.umeng.analytics.pro.co.j];
    }

    public static char c(byte b) {
        return (char) u[(b & 127) - 32];
    }

    public final void b(byte b) {
        if (b == 32) {
            a(2);
            return;
        }
        if (b != 41) {
            switch (b) {
                case 37:
                    this.n = 2;
                    a(1);
                    break;
                case 38:
                    this.n = 3;
                    a(1);
                    break;
                case 39:
                    this.n = 4;
                    a(1);
                    break;
                default:
                    int i = this.m;
                    if (i != 0) {
                        if (b != 33) {
                            switch (b) {
                                case 44:
                                    this.k = null;
                                    if (i == 1 || i == 3) {
                                        j();
                                    }
                                    break;
                                case 45:
                                    if (i == 1 && !this.j.a()) {
                                        this.j.d();
                                        break;
                                    }
                                    break;
                                case 46:
                                    j();
                                    break;
                                case 47:
                                    this.k = i();
                                    j();
                                    break;
                            }
                        } else {
                            this.j.b();
                            break;
                        }
                    }
                    break;
            }
            return;
        }
        a(3);
    }

    public final boolean a(byte b, byte b2) {
        boolean zG = g(b);
        if (zG) {
            if (this.o && this.p == b && this.q == b2) {
                this.o = false;
                return true;
            }
            this.o = true;
            this.p = b;
            this.q = b2;
        }
        if (c(b, b2)) {
            a(b2);
        } else if (d(b, b2)) {
            b(b, b2);
        } else if (e(b, b2)) {
            this.j.c(b2 + com.umeng.analytics.pro.co.k);
        } else if (f(b, b2)) {
            b(b2);
        }
        return zG;
    }

    public final void a(byte b) {
        this.j.a((b & 1) == 1);
        int i = (b >> 1) & 15;
        if (i == 7) {
            this.j.a(new StyleSpan(2), 2);
            this.j.a(new ForegroundColorSpan(-1), 1);
        } else {
            this.j.a(new ForegroundColorSpan(t[i]), 1);
        }
    }

    public final void a(int i) {
        int i2 = this.m;
        if (i2 == i) {
            return;
        }
        this.m = i;
        j();
        if (i2 == 3 || i == 1 || i == 0) {
            this.k = null;
        }
    }
}
