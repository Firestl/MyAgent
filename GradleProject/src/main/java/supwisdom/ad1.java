package supwisdom;

import android.opengl.GLES20;
import com.baidu.speech.core.BDSHttpRequestMaker;
import com.facebook.imageutils.JfifUtil;
import com.google.zxing.oned.Code39Reader;
import com.seu.magicfilter.utils.MagicFilterType;
import com.tencent.ijk.media.player.IjkMediaPlayer;
import io.dcloud.feature.audio.recorder.RecorderTask;
import io.dcloud.feature.livepusher.R;
import java.nio.ByteBuffer;
import org.bouncycastle.pqc.crypto.qtesla.HashUtils;
import tv.cjump.jni.DeviceUtils;

/* JADX INFO: compiled from: MagicSakuraFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public class ad1 extends ud1 {
    public int[] w;
    public int x;
    public int y;
    public int z;

    /* JADX INFO: compiled from: MagicSakuraFilter.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GLES20.glGenTextures(1, ad1.this.w, 0);
            GLES20.glBindTexture(3553, ad1.this.w[0]);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            byte[] bArr = new byte[1024];
            int[] iArr = {95, 95, 96, 97, 97, 98, 99, 99, 100, 101, 101, 102, 103, 104, 104, 105, 106, 106, 107, 108, 108, 109, 110, 111, 111, 112, 113, 113, 114, 115, 115, 116, 117, 117, 118, 119, 120, 120, 121, 122, 122, 123, 124, 124, 125, 126, 127, 127, 128, 129, 129, 130, com.igexin.push.core.a.c.h.b, com.igexin.push.core.a.c.h.b, 132, 133, 133, 134, 135, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE, 137, 138, 138, 139, 140, 140, 141, 142, 143, 143, 144, com.igexin.push.config.c.G, com.igexin.push.config.c.G, 146, 147, 147, Code39Reader.ASTERISK_ENCODING, 149, 149, 150, 151, 152, 152, 153, 154, 154, 155, 156, 156, 157, 158, 159, 159, 160, 161, 161, 162, 163, 163, 164, 165, 165, 166, 167, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_128_RATE, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_128_RATE, 169, 170, 170, 171, 172, 172, 173, 174, 175, 175, 176, 177, 177, 178, 179, 179, 180, 181, 181, 182, DeviceUtils.EM_AARCH64, 184, 184, 185, 186, 186, 187, 188, 188, 189, 190, 191, 191, 192, 193, 193, 194, 195, 195, 196, 197, 197, 198, 199, 200, 200, 201, 202, 202, 203, 204, 204, 205, 206, 207, 207, JfifUtil.MARKER_RST0, 209, 209, IjkMediaPlayer.MEDIA_HLS_KEY_ERROR, 211, 211, IjkMediaPlayer.MEDIA_VIDEO_DECODER_ERROR, 213, 213, 214, JfifUtil.MARKER_RST7, JfifUtil.MARKER_SOI, JfifUtil.MARKER_SOI, 217, 218, 218, 219, RecorderTask.FRAME_COUNT, RecorderTask.FRAME_COUNT, 221, 222, 223, 223, 224, 225, 225, 226, 227, 227, 228, 229, 229, 230, 231, 232, 232, 233, 234, 234, 235, 236, 236, 237, 238, 239, 239, 240, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, BDSHttpRequestMaker.TYPE_DOWNLOAD_THIRD_DATA, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINISH, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINISH, 244, 245, 245, 246, 247, 248, 248, 249, 250, 250, 251, 252, 252, com.igexin.push.config.c.E, 254, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255};
            for (int i = 0; i < 256; i++) {
                int i2 = i * 4;
                bArr[i2] = (byte) iArr[i];
                bArr[i2 + 1] = (byte) iArr[i];
                bArr[i2 + 2] = (byte) iArr[i];
                bArr[i2 + 3] = (byte) iArr[i];
            }
            GLES20.glTexImage2D(3553, 0, 6408, 256, 1, 0, 6408, 5121, ByteBuffer.wrap(bArr));
        }
    }

    public ad1() {
        super(MagicFilterType.SAKURA, R.raw.romance);
        this.w = new int[]{-1};
    }

    @Override // supwisdom.ud1
    public void c(int i, int i2) {
        super.c(i, i2);
        GLES20.glUniform1f(this.z, 1.0f / i);
        GLES20.glUniform1f(this.y, 1.0f / i2);
    }

    @Override // supwisdom.ud1
    public void i() {
        super.i();
        GLES20.glDeleteTextures(1, this.w, 0);
        this.w[0] = -1;
    }

    @Override // supwisdom.ud1
    public void j() {
        if (this.w[0] != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, 0);
            GLES20.glActiveTexture(33984);
        }
    }

    @Override // supwisdom.ud1
    public void k() {
        if (this.w[0] != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.w[0]);
            GLES20.glUniform1i(this.x, 3);
        }
    }

    @Override // supwisdom.ud1
    public void l() {
        super.l();
        this.x = GLES20.glGetUniformLocation(f(), "curve");
        this.z = GLES20.glGetUniformLocation(f(), "texelWidthOffset");
        this.y = GLES20.glGetUniformLocation(f(), "texelHeightOffset");
    }

    @Override // supwisdom.ud1
    public void m() {
        super.m();
        a(new a());
    }
}
