package supwisdom;

import com.facebook.imageformat.DefaultImageFormatChecker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bouncycastle.math.ec.rfc7748.X25519Field;

/* JADX INFO: compiled from: Atom.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class y20 {
    public static final int A0;
    public static final int B0;
    public static final int C0;
    public static final int D0;
    public static final int E0;
    public static final int F0;
    public static final int G0;
    public static final int H0;
    public static final int I0;
    public static final int J0;
    public static final int K0;
    public static final int L0;
    public static final int M0;
    public static final int N0;
    public static final int O0;
    public static final int l0;
    public static final int m0;
    public static final int n0;
    public static final int o0;
    public static final int p0;
    public static final int q0;
    public static final int r0;
    public static final int s0;
    public static final int t0;
    public static final int u0;
    public static final int v0;
    public static final int w0;
    public static final int x0;
    public static final int y0;
    public static final int z0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9840a;
    public static final int b = x80.g(DefaultImageFormatChecker.HEIF_HEADER_PREFIX);
    public static final int c = x80.g("avc1");
    public static final int d = x80.g("avc3");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f9839e = x80.g("hvc1");
    public static final int f = x80.g("hev1");
    public static final int g = x80.g("s263");
    public static final int h = x80.g("d263");
    public static final int i = x80.g("mdat");
    public static final int j = x80.g("mp4a");
    public static final int k = x80.g(".mp3");
    public static final int l = x80.g("wave");
    public static final int m = x80.g("lpcm");
    public static final int n = x80.g("sowt");
    public static final int o = x80.g("ac-3");
    public static final int p = x80.g("dac3");
    public static final int q = x80.g("ec-3");
    public static final int r = x80.g("dec3");
    public static final int s = x80.g("dtsc");
    public static final int t = x80.g("dtsh");
    public static final int u = x80.g("dtsl");
    public static final int v = x80.g("dtse");
    public static final int w = x80.g("ddts");
    public static final int x = x80.g("tfdt");
    public static final int y = x80.g("tfhd");
    public static final int z = x80.g("trex");
    public static final int A = x80.g("trun");
    public static final int B = x80.g("sidx");
    public static final int C = x80.g("moov");
    public static final int D = x80.g("mvhd");
    public static final int E = x80.g("trak");
    public static final int F = x80.g("mdia");
    public static final int G = x80.g("minf");
    public static final int H = x80.g("stbl");
    public static final int I = x80.g("avcC");
    public static final int J = x80.g("hvcC");
    public static final int K = x80.g("esds");
    public static final int L = x80.g("moof");
    public static final int M = x80.g("traf");
    public static final int N = x80.g("mvex");
    public static final int O = x80.g("mehd");
    public static final int P = x80.g("tkhd");
    public static final int Q = x80.g("edts");
    public static final int R = x80.g("elst");
    public static final int S = x80.g("mdhd");
    public static final int T = x80.g("hdlr");
    public static final int U = x80.g("stsd");
    public static final int V = x80.g("pssh");
    public static final int W = x80.g("sinf");
    public static final int X = x80.g("schm");
    public static final int Y = x80.g("schi");
    public static final int Z = x80.g("tenc");
    public static final int a0 = x80.g("encv");
    public static final int b0 = x80.g("enca");
    public static final int c0 = x80.g("frma");
    public static final int d0 = x80.g("saiz");
    public static final int e0 = x80.g("saio");
    public static final int f0 = x80.g("sbgp");
    public static final int g0 = x80.g("sgpd");
    public static final int h0 = x80.g("uuid");
    public static final int i0 = x80.g("senc");
    public static final int j0 = x80.g("pasp");
    public static final int k0 = x80.g("TTML");

    /* JADX INFO: compiled from: Atom.java */
    public static final class a extends y20 {
        public final long P0;
        public final List<b> Q0;
        public final List<a> R0;

        public a(int i, long j) {
            super(i);
            this.P0 = j;
            this.Q0 = new ArrayList();
            this.R0 = new ArrayList();
        }

        public void a(b bVar) {
            this.Q0.add(bVar);
        }

        public b d(int i) {
            int size = this.Q0.size();
            for (int i2 = 0; i2 < size; i2++) {
                b bVar = this.Q0.get(i2);
                if (bVar.f9840a == i) {
                    return bVar;
                }
            }
            return null;
        }

        public a e(int i) {
            int size = this.R0.size();
            for (int i2 = 0; i2 < size; i2++) {
                a aVar = this.R0.get(i2);
                if (aVar.f9840a == i) {
                    return aVar;
                }
            }
            return null;
        }

        @Override // supwisdom.y20
        public String toString() {
            return y20.c(this.f9840a) + " leaves: " + Arrays.toString(this.Q0.toArray()) + " containers: " + Arrays.toString(this.R0.toArray());
        }

        public void a(a aVar) {
            this.R0.add(aVar);
        }
    }

    /* JADX INFO: compiled from: Atom.java */
    public static final class b extends y20 {
        public final o80 P0;

        public b(int i, o80 o80Var) {
            super(i);
            this.P0 = o80Var;
        }
    }

    static {
        x80.g("vmhd");
        l0 = x80.g("mp4v");
        m0 = x80.g("stts");
        n0 = x80.g("stss");
        o0 = x80.g("ctts");
        p0 = x80.g("stsc");
        q0 = x80.g("stsz");
        r0 = x80.g("stz2");
        s0 = x80.g("stco");
        t0 = x80.g("co64");
        u0 = x80.g("tx3g");
        v0 = x80.g("wvtt");
        w0 = x80.g("stpp");
        x0 = x80.g("c608");
        y0 = x80.g("samr");
        z0 = x80.g("sawb");
        A0 = x80.g("udta");
        B0 = x80.g("meta");
        C0 = x80.g("ilst");
        D0 = x80.g("mean");
        E0 = x80.g("name");
        F0 = x80.g("data");
        G0 = x80.g("emsg");
        H0 = x80.g("st3d");
        I0 = x80.g("sv3d");
        J0 = x80.g("proj");
        K0 = x80.g("vp08");
        L0 = x80.g("vp09");
        M0 = x80.g("vpcC");
        N0 = x80.g("camm");
        O0 = x80.g("alac");
    }

    public y20(int i2) {
        this.f9840a = i2;
    }

    public static int a(int i2) {
        return (i2 >> 24) & 255;
    }

    public static int b(int i2) {
        return i2 & X25519Field.M24;
    }

    public static String c(int i2) {
        return "" + ((char) ((i2 >> 24) & 255)) + ((char) ((i2 >> 16) & 255)) + ((char) ((i2 >> 8) & 255)) + ((char) (i2 & 255));
    }

    public String toString() {
        return c(this.f9840a);
    }
}
