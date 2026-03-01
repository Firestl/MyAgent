package supwisdom;

import android.annotation.TargetApi;
import android.media.MediaCodec;

/* JADX INFO: compiled from: CryptoInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class v10 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f9466a;
    public byte[] b;
    public int c;
    public int[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f9467e;
    public int f;
    public int g;
    public int h;
    public final MediaCodec.CryptoInfo i;
    public final b j;

    /* JADX INFO: compiled from: CryptoInfo.java */
    @TargetApi(24)
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MediaCodec.CryptoInfo f9468a;
        public final MediaCodec.CryptoInfo.Pattern b;

        public b(MediaCodec.CryptoInfo cryptoInfo) {
            this.f9468a = cryptoInfo;
            this.b = new MediaCodec.CryptoInfo.Pattern(0, 0);
        }

        public final void a(int i, int i2) {
            this.b.set(i, i2);
            this.f9468a.setPattern(this.b);
        }
    }

    public v10() {
        byte b2 = 0;
        MediaCodec.CryptoInfo cryptoInfoB = x80.f9718a >= 16 ? b() : null;
        this.i = cryptoInfoB;
        this.j = x80.f9718a >= 24 ? new b(cryptoInfoB) : null;
    }

    public void a(int i, int[] iArr, int[] iArr2, byte[] bArr, byte[] bArr2, int i2) {
        this.f = i;
        this.d = iArr;
        this.f9467e = iArr2;
        this.b = bArr;
        this.f9466a = bArr2;
        this.c = i2;
        this.g = 0;
        this.h = 0;
        if (x80.f9718a >= 16) {
            c();
        }
    }

    @TargetApi(16)
    public final MediaCodec.CryptoInfo b() {
        return new MediaCodec.CryptoInfo();
    }

    @TargetApi(16)
    public final void c() {
        MediaCodec.CryptoInfo cryptoInfo = this.i;
        cryptoInfo.numSubSamples = this.f;
        cryptoInfo.numBytesOfClearData = this.d;
        cryptoInfo.numBytesOfEncryptedData = this.f9467e;
        cryptoInfo.key = this.b;
        cryptoInfo.iv = this.f9466a;
        cryptoInfo.mode = this.c;
        if (x80.f9718a >= 24) {
            this.j.a(this.g, this.h);
        }
    }

    @TargetApi(16)
    public MediaCodec.CryptoInfo a() {
        return this.i;
    }
}
