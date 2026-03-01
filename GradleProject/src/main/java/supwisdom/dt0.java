package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.ossrs.yasea.SrsFlvMuxer;

/* JADX INFO: compiled from: AudioSpecificConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class dt0 extends et0 {
    public static Map<Integer, Integer> R = new HashMap();
    public static Map<Integer, String> S = new HashMap();
    public int A;
    public boolean B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public boolean Q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f7377a;
    public int b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7378e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;

    static {
        R.put(0, 96000);
        R.put(1, 88200);
        R.put(2, 64000);
        R.put(3, 48000);
        R.put(4, 44100);
        R.put(5, 32000);
        R.put(6, 24000);
        R.put(7, Integer.valueOf(SrsFlvMuxer.SrsCodecAudioSampleRate.R22050));
        R.put(8, 16000);
        R.put(9, 12000);
        R.put(10, Integer.valueOf(SrsFlvMuxer.SrsCodecAudioSampleRate.R11025));
        R.put(11, 8000);
        S.put(1, "AAC main");
        S.put(2, "AAC LC");
        S.put(3, "AAC SSR");
        S.put(4, "AAC LTP");
        S.put(5, "SBR");
        S.put(6, "AAC Scalable");
        S.put(7, "TwinVQ");
        S.put(8, "CELP");
        S.put(9, "HVXC");
        S.put(10, "(reserved)");
        S.put(11, "(reserved)");
        S.put(12, "TTSI");
        S.put(13, "Main synthetic");
        S.put(14, "Wavetable synthesis");
        S.put(15, "General MIDI");
        S.put(16, "Algorithmic Synthesis and Audio FX");
        S.put(17, "ER AAC LC");
        S.put(18, "(reserved)");
        S.put(19, "ER AAC LTP");
        S.put(20, "ER AAC Scalable");
        S.put(21, "ER TwinVQ");
        S.put(22, "ER BSAC");
        S.put(23, "ER AAC LD");
        S.put(24, "ER CELP");
        S.put(25, "ER HVXC");
        S.put(26, "ER HILN");
        S.put(27, "ER Parametric");
        S.put(28, "SSC");
        S.put(29, "PS");
        S.put(30, "MPEG Surround");
        S.put(31, "(escape)");
        S.put(32, "Layer-1");
        S.put(33, "Layer-2");
        S.put(34, "Layer-3");
        S.put(35, "DST");
        S.put(36, "ALS");
        S.put(37, "SLS");
        S.put(38, "SLS non-core");
        S.put(39, "ER AAC ELD");
        S.put(40, "SMR Simple");
        S.put(41, "SMR Main");
    }

    public final int a() {
        return 0;
    }

    public void a(int i) {
        this.b = i;
    }

    public ByteBuffer b() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(c());
        ks.c(byteBufferAllocate, 5);
        ks.c(byteBufferAllocate, c() - 2);
        ft0 ft0Var = new ft0(byteBufferAllocate);
        ft0Var.a(this.b, 5);
        ft0Var.a(this.c, 4);
        if (this.c == 15) {
            throw new UnsupportedOperationException("can't serialize that yet");
        }
        ft0Var.a(this.f7378e, 4);
        return byteBufferAllocate;
    }

    public int c() {
        if (this.b == 2) {
            return a() + 4;
        }
        throw new UnsupportedOperationException("can't serialize that yet");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || dt0.class != obj.getClass()) {
            return false;
        }
        dt0 dt0Var = (dt0) obj;
        return this.y == dt0Var.y && this.x == dt0Var.x && this.z == dt0Var.z && this.b == dt0Var.b && this.f7378e == dt0Var.f7378e && this.s == dt0Var.s && this.r == dt0Var.r && this.o == dt0Var.o && this.n == dt0Var.n && this.H == dt0Var.H && this.f == dt0Var.f && this.k == dt0Var.k && this.t == dt0Var.t && this.A == dt0Var.A && this.j == dt0Var.j && this.i == dt0Var.i && this.m == dt0Var.m && this.q == dt0Var.q && this.B == dt0Var.B && this.N == dt0Var.N && this.O == dt0Var.O && this.P == dt0Var.P && this.M == dt0Var.M && this.K == dt0Var.K && this.J == dt0Var.J && this.L == dt0Var.L && this.G == dt0Var.G && this.F == dt0Var.F && this.C == dt0Var.C && this.u == dt0Var.u && this.w == dt0Var.w && this.v == dt0Var.v && this.E == dt0Var.E && this.D == dt0Var.D && this.Q == dt0Var.Q && this.h == dt0Var.h && this.l == dt0Var.l && this.d == dt0Var.d && this.c == dt0Var.c && this.g == dt0Var.g && this.p == dt0Var.p && this.I == dt0Var.I && Arrays.equals(this.f7377a, dt0Var.f7377a);
    }

    public int hashCode() {
        byte[] bArr = this.f7377a;
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((bArr != null ? Arrays.hashCode(bArr) : 0) * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + this.f7378e) * 31) + this.f) * 31) + this.g) * 31) + this.h) * 31) + this.i) * 31) + this.j) * 31) + this.k) * 31) + this.l) * 31) + this.m) * 31) + this.n) * 31) + this.o) * 31) + this.p) * 31) + this.q) * 31) + this.r) * 31) + this.s) * 31) + this.t) * 31) + this.u) * 31) + this.v) * 31) + this.w) * 31) + this.x) * 31) + this.y) * 31) + this.z) * 31) + this.A) * 31) + (this.B ? 1 : 0)) * 31) + this.C) * 31) + this.D) * 31) + this.E) * 31) + this.F) * 31) + this.G) * 31) + this.H) * 31) + this.I) * 31) + this.J) * 31) + this.K) * 31) + this.L) * 31) + this.M) * 31) + this.N) * 31) + this.O) * 31) + this.P) * 31) + (this.Q ? 1 : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AudioSpecificConfig");
        sb.append("{configBytes=");
        sb.append(is.a(this.f7377a));
        sb.append(", audioObjectType=");
        sb.append(this.b);
        sb.append(" (");
        sb.append(S.get(Integer.valueOf(this.b)));
        sb.append(")");
        sb.append(", samplingFrequencyIndex=");
        sb.append(this.c);
        sb.append(" (");
        sb.append(R.get(Integer.valueOf(this.c)));
        sb.append(")");
        sb.append(", samplingFrequency=");
        sb.append(this.d);
        sb.append(", channelConfiguration=");
        sb.append(this.f7378e);
        if (this.f > 0) {
            sb.append(", extensionAudioObjectType=");
            sb.append(this.f);
            sb.append(" (");
            sb.append(S.get(Integer.valueOf(this.f)));
            sb.append(")");
            sb.append(", sbrPresentFlag=");
            sb.append(this.g);
            sb.append(", psPresentFlag=");
            sb.append(this.h);
            sb.append(", extensionSamplingFrequencyIndex=");
            sb.append(this.i);
            sb.append(" (");
            sb.append(R.get(Integer.valueOf(this.i)));
            sb.append(")");
            sb.append(", extensionSamplingFrequency=");
            sb.append(this.j);
            sb.append(", extensionChannelConfiguration=");
            sb.append(this.k);
        }
        sb.append(", syncExtensionType=");
        sb.append(this.p);
        if (this.B) {
            sb.append(", frameLengthFlag=");
            sb.append(this.q);
            sb.append(", dependsOnCoreCoder=");
            sb.append(this.r);
            sb.append(", coreCoderDelay=");
            sb.append(this.s);
            sb.append(", extensionFlag=");
            sb.append(this.t);
            sb.append(", layerNr=");
            sb.append(this.u);
            sb.append(", numOfSubFrame=");
            sb.append(this.v);
            sb.append(", layer_length=");
            sb.append(this.w);
            sb.append(", aacSectionDataResilienceFlag=");
            sb.append(this.x);
            sb.append(", aacScalefactorDataResilienceFlag=");
            sb.append(this.y);
            sb.append(", aacSpectralDataResilienceFlag=");
            sb.append(this.z);
            sb.append(", extensionFlag3=");
            sb.append(this.A);
        }
        if (this.Q) {
            sb.append(", isBaseLayer=");
            sb.append(this.C);
            sb.append(", paraMode=");
            sb.append(this.D);
            sb.append(", paraExtensionFlag=");
            sb.append(this.E);
            sb.append(", hvxcVarMode=");
            sb.append(this.F);
            sb.append(", hvxcRateMode=");
            sb.append(this.G);
            sb.append(", erHvxcExtensionFlag=");
            sb.append(this.H);
            sb.append(", var_ScalableFlag=");
            sb.append(this.I);
            sb.append(", hilnQuantMode=");
            sb.append(this.J);
            sb.append(", hilnMaxNumLine=");
            sb.append(this.K);
            sb.append(", hilnSampleRateCode=");
            sb.append(this.L);
            sb.append(", hilnFrameLength=");
            sb.append(this.M);
            sb.append(", hilnContMode=");
            sb.append(this.N);
            sb.append(", hilnEnhaLayer=");
            sb.append(this.O);
            sb.append(", hilnEnhaQuantMode=");
            sb.append(this.P);
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public void c(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.f7378e = i;
    }
}
