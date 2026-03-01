package supwisdom;

import com.uc.crashsdk.export.LogType;
import java.nio.ByteBuffer;
import net.ossrs.yasea.SrsFlvMuxer;
import org.bouncycastle.crypto.generators.Poly1305KeyGenerator;

/* JADX INFO: compiled from: DtsUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class o10 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f8599a = {1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    public static final int[] b = {-1, 8000, 16000, 32000, -1, -1, SrsFlvMuxer.SrsCodecAudioSampleRate.R11025, SrsFlvMuxer.SrsCodecAudioSampleRate.R22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
    public static final int[] c = {64, 112, 128, 192, 224, 256, 384, 448, 512, 640, 768, 896, 1024, 1152, 1280, 1536, 1920, 2048, LogType.UNEXP_LOW_MEMORY, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};

    public static com.google.android.exoplayer2.j a(byte[] bArr, String str, String str2, com.google.android.exoplayer2.c.a aVar) {
        n80 n80Var = new n80(bArr);
        n80Var.b(60);
        int i = f8599a[n80Var.c(6)];
        int i2 = b[n80Var.c(4)];
        int iC = n80Var.c(5);
        int[] iArr = c;
        int i3 = iC >= iArr.length ? -1 : (iArr[iC] * 1000) / 2;
        n80Var.b(10);
        return com.google.android.exoplayer2.j.a(str, "audio/vnd.dts", null, i3, -1, i + (n80Var.c(2) > 0 ? 1 : 0), i2, null, aVar, 0, str2);
    }

    public static int b(byte[] bArr) {
        return (((bArr[7] & 240) >> 4) | ((bArr[5] & 2) << 12) | ((bArr[6] & 255) << 4)) + 1;
    }

    public static int a(byte[] bArr) {
        return ((((bArr[5] & Poly1305KeyGenerator.R_MASK_LOW_2) >> 2) | ((bArr[4] & 1) << 6)) + 1) * 32;
    }

    public static int a(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        return ((((byteBuffer.get(iPosition + 5) & Poly1305KeyGenerator.R_MASK_LOW_2) >> 2) | ((byteBuffer.get(iPosition + 4) & 1) << 6)) + 1) * 32;
    }
}
